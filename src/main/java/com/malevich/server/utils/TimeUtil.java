package com.malevich.server.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeUtil {
    public static final String OPENING_TIME = "9:00";
    public static final String CLOSING_TIME = "23:00";
    public static final String TIME_FORMAT_PATTERN = "HH:mm";

    public static String shiftTime(String time, int hours) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT_PATTERN);

        calendar.setTime(format.parse(time));
        long timeMilis = calendar.getTimeInMillis();
        calendar.setTime(format.parse(OPENING_TIME));
        long openingTimeMilis = calendar.getTimeInMillis();
        calendar.setTime(format.parse(CLOSING_TIME));
        long closingTimeMilis = calendar.getTimeInMillis();
        calendar.set(Calendar.HOUR, hours);
        long hoursMilis = calendar.getTimeInMillis() * (hours < 0 ? -1 : 1);
        timeMilis += hoursMilis;

        if (timeMilis > closingTimeMilis) {
            timeMilis = closingTimeMilis;
        } else if (timeMilis < openingTimeMilis) {
            timeMilis = openingTimeMilis;
        }

        return format.format(timeMilis);
    }
}
