package test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.joda.time.DateTime;
import org.joda.time.*;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormat;

class TestDate {

    public static void main(String[] args) {   
    Calendar c = new GregorianCalendar();
    String dateTime = "11/15/2013 13:35:00";
     // Format for input
    DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy kk:mm:ss");
     // Parsing the date
    DateTime jodatime = dtf.parseDateTime(dateTime);
    c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(jodatime.toString("k")));
    c.set(Calendar.MINUTE, Integer.parseInt(jodatime.toString("m")));
    c.set(Calendar.SECOND, Integer.parseInt(jodatime.toString("s")));
    Date d = c.getTime();

    System.out.println("Start point: " + jodatime.toString());
    System.out.println("Nearest whole minute: " + toNearestWholeMinute(d));
    System.out.println("Nearest whole hour: " + toNearestWholeHour(d));
    DateTime a = toNearestWholeMinute(d);
    DateTime b = toNearestWholeHour(d);
    System.out.println("Current hours:" + a.getHourOfDay());
    System.out.println("Nearest hour:" + b.getHourOfDay());
    }

    static DateTime toNearestWholeMinute(Date d) {
    Calendar c = new GregorianCalendar();
    c.setTime(d);

    if (c.get(Calendar.SECOND) >= 30)
        c.add(Calendar.MINUTE, 1);

    c.set(Calendar.SECOND, 0);

    DateTime dateTime = new DateTime(c.getTime());
    return dateTime;
    }

    static DateTime toNearestWholeHour(Date d) {
    Calendar c = new GregorianCalendar();
    c.setTime(d);

    if (c.get(Calendar.MINUTE) >= 30)
        c.add(Calendar.HOUR, 1);

    c.set(Calendar.MINUTE, 0);
    c.set(Calendar.SECOND, 0);

    DateTime dateTime = new DateTime(c.getTime());
    return dateTime;
    }

}

/*
/*
    DateTime dateTime = new DateTime(c.getTime());
    DateFormat outputformat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
    String aa = (String) outputformat.format(dateTime);
    */
