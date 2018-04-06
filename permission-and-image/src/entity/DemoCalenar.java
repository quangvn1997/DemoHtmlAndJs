package entity;

import java.util.Calendar;
import java.util.TimeZone;

public class DemoCalenar {
    public static String generateTimeFromLong(long timeMLS) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+7"));
        calendar.setTimeInMillis(timeMLS);
        return  calendar.getTime().toString();
    }
}
