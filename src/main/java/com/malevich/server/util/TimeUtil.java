package com.malevich.server.util;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimeUtil {
    public static final Time OPENING_TIME = Time.valueOf("9:00:00");
    public static final Time CLOSING_TIME = Time.valueOf("23:00:00");
    public static final long ONE_HOUR_MILIS = 1000 * 60 * 60;

    public static Time shiftTime(Time time, int hours)  {

        Time shiftedTime = new Time(time.getTime());
        long timeMilis = time.getTime();
        timeMilis += ONE_HOUR_MILIS * hours;

        if (timeMilis >= CLOSING_TIME.getTime()) {
            timeMilis = CLOSING_TIME.getTime();
        }
        if (timeMilis <= OPENING_TIME.getTime()) {
            timeMilis = OPENING_TIME.getTime();
        }
        shiftedTime.setTime(timeMilis);
        return shiftedTime;
    }

}
