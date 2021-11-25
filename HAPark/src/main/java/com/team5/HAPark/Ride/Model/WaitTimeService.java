package com.team5.HAPark.Ride.Model;

import com.team5.HAPark.Ride.Persistence.RidePersistence;
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

    RidePersistence ridePersistence=new RidePersistence();


    public WaitTime calculateWaitTime(int rideId) throws SQLException {
        WaitTime waitTime = new WaitTime();
        LocalTime temp = LocalTime.of(00,00,00);
        log.info("temp:{}",temp);
        TimeSlot timeSlot = ridePersistence.getRideTimeSlot(rideId);

        for (Integer key : timeSlot.getMap().keySet()) {
            //numberOfSeatsReserved=max_occupancy-availability;
            int numberOfSeatsReserved = ridePersistence.getRideMaxOccupancy(rideId) - timeSlot.getMap().get(key);
            log.info("max occupancy {}",ridePersistence.getRideMaxOccupancy(1));
            log.info("availability {}",timeSlot.getMap().get(key));
            log.info("number of seats reserve {}",numberOfSeatsReserved);
            int rideRounds = numberOfSeatsReserved / 10;
            log.info("ride rounds {}",rideRounds);

            Time duration = ridePersistence.getRideDuration(rideId);
            log.info("duration {}",duration);
            LocalTime durationInLocalTime = duration.toLocalTime();
            String durationString = duration.toString();
            log.info("duration in local time {}",durationInLocalTime);

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