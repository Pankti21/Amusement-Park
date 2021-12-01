package com.team5.HAPark.Ride.Model;

import com.team5.HAPark.Ride.Persistence.IRidePersistence;
import com.team5.HAPark.Ride.Persistence.RidePersistence;
import com.team5.HAPark.Ride.Persistence.WaitTime.IWaitTimePersistence;
import com.team5.HAPark.Ride.Persistence.WaitTime.WaitTimePersistence;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class WaitTimeService {

    IRidePersistence ridePersistence=new RidePersistence();
    IWaitTimePersistence waitTimePersistence=new WaitTimePersistence();

    public WaitTimeService(IRidePersistence ridePersistence) {
        this.ridePersistence = ridePersistence;
    }

    public WaitTimeService(IWaitTimePersistence waitTimePersistence) {
        this.waitTimePersistence=waitTimePersistence;
    }

    public WaitTimeService() {

    }

    public WaitTimeService(IRidePersistence ridePersistence, IWaitTimePersistence waitTimePersistence) {
        this.waitTimePersistence=waitTimePersistence;
        this.ridePersistence=ridePersistence;
    }

    public List<HashMap<Integer,LocalTime>> getWaitTimes() throws SQLException {
        List<Ride> rides= ridePersistence.getAllRides();
        List<HashMap<Integer,LocalTime>> waitTimes=new ArrayList<>();
        for (Ride ride:rides){
            WaitTimeService waitTimeService=new WaitTimeService();
            WaitTime waitTime=waitTimeService.calculateWaitTime(ride.getId());
            waitTimes.add(waitTime.getWaitTime());
        }
        return waitTimes;
    }

    public LocalTime getDuration(int rideId) throws SQLException {
        Time duration = waitTimePersistence.getRideDuration(rideId);
        LocalTime durationInLocalTime = duration.toLocalTime();
        return durationInLocalTime;
    }

    public String getDurationString(int rideId) throws SQLException {
        Time duration = waitTimePersistence.getRideDuration(rideId);
        return duration.toString();
    }

    public WaitTime calculateWaitTime(int rideId) throws SQLException {
        WaitTime waitTime = new WaitTime();
        LocalTime temp = LocalTime.of(00,00,00);
        //log.info("temp:{}",temp);
        TimeSlot timeSlot = ridePersistence.getRideTimeSlot(rideId);

        for (Integer key : timeSlot.getMap().keySet()) {
            //numberOfSeatsReserved=max_occupancy-availability;
            int numberOfSeatsReserved = waitTimePersistence.getRideMaxOccupancy(rideId) - timeSlot.getMap().get(key);
           // log.info("max occupancy {}",ridePersistence.getRideMaxOccupancy(1));
            //log.info("availability {}",timeSlot.getMap().get(key));
            //log.info("number of seats reserve {}",numberOfSeatsReserved);
            int rideRounds = numberOfSeatsReserved / 10;
            //log.info("ride rounds {}",rideRounds);

            //log.info("duration {}",duration);
            LocalTime durationInLocalTime = getDuration(rideId);
            String durationString=getDurationString(rideId);

            //log.info("duration in local time {}",durationInLocalTime);

            Long hours = Long.parseLong(durationString.substring(0, 2));
            Long mins = Long.parseLong(durationString.substring(3, 5));
            Long secs = Long.parseLong(durationString.substring(7));
            log.info("{} {} {}",hours,mins,secs);

            if(rideRounds>0) {
                for (int i = 0; i < rideRounds - 1; i++) {
                    temp = durationInLocalTime.plusSeconds(secs);
                    log.info("temp in sec:{}", temp);
                    temp = temp.plusMinutes(mins);
                    log.info("temp in min:{}", temp);
                    temp = temp.plusHours(hours);
                    log.info("temp in hours:{}", temp);
                    log.info("temp:{}", temp);
                    durationInLocalTime = temp;
                }
                waitTime.getWaitTime().put(key, temp);
            }
            else {
                waitTime.getWaitTime().put(key,LocalTime.of(00,00,00));
            }
        }

        log.info("waittime:{}",waitTime.toString());
        return waitTime;
    }
}