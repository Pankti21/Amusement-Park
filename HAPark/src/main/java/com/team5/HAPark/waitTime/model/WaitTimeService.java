package com.team5.HAPark.waitTime.model;

import com.team5.HAPark.ride.model.Ride;
import com.team5.HAPark.ride.model.TimeSlot;
import com.team5.HAPark.ride.persistence.IRidePersistence;
import com.team5.HAPark.ride.persistence.RidePersistenceFactory;
import com.team5.HAPark.ride.persistence.WaitTime.IWaitTimePersistence;
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

    private IRidePersistence ridePersistence = new RidePersistenceFactory().createRidePersistence();
    private IWaitTimePersistence waitTimePersistence = new RidePersistenceFactory().createWaitTimePersistence();

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

    //Get wait times for all rides
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

    //Get duration in LocalTime format
    public LocalTime getDuration(int rideId) throws SQLException {
        Time duration = waitTimePersistence.getRideDuration(rideId);
        LocalTime durationInLocalTime = duration.toLocalTime();
        return durationInLocalTime;
    }

    //Get duration in string format
    public String getDurationString(int rideId) throws SQLException {
        Time duration = waitTimePersistence.getRideDuration(rideId);
        return duration.toString();
    }

    //Number of currently reservated seats = max occupancy for a time slot - available seats for a time slot
    //Rounds of rides according to reservations = number of seats reserved / capacity per round
    //WaitTime = Rounds of rides * duration of ride per round
    public WaitTime calculateWaitTime(int rideId) throws SQLException {
        WaitTime waitTime = new WaitTime();
        LocalTime temp = LocalTime.of(00,00,00);

        TimeSlot timeSlot = ridePersistence.getRideTimeSlot(rideId);

        for (Integer key : timeSlot.getMap().keySet()) {
            int numberOfSeatsReserved = waitTimePersistence.getRideMaxOccupancy(rideId) - timeSlot.getMap().get(key);

            //Each round of ride takes 10 people
            int rideRounds = numberOfSeatsReserved / 10;

            LocalTime durationInLocalTime = getDuration(rideId);
            String durationString=getDurationString(rideId);

            Long hours = Long.parseLong(durationString.substring(0, 2));
            Long mins = Long.parseLong(durationString.substring(3, 5));
            Long secs = Long.parseLong(durationString.substring(7));

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
        return waitTime;
    }
}