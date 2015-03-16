
package test;

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;
import java.sql.Timestamp;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.joda.time.DateTime;
import org.joda.time.DateTime.*;
import org.joda.time.*;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormat;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.*;
import org.json.simple.JSONArray.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.jayway.jsonpath.*;
//import com.eclipsesource.json.ParseException;

import java.text.SimpleDateFormat;

//import com.eclipsesource.json.*;

import java.io.*;

class TestDate {
       public static String apiKey = "";
       private static Double Latitute = 37.776289;
       private static Double Longitude = -122.395234;
       private static String a = "http://api.wunderground.com/api/%s/history_%s/q/%s,%s.json";
       public static String jsonString;
       //private String finalUrl;
       private String city;
       private String cityUrl;
       private static JSONObject jsonObject;
       private static FileInputStream in = null;
       private static FileOutputStream out = null;

    public static void main(String[] args) {   
        Calendar c = new GregorianCalendar();
        String dateTime = "11/15/2013 13:35:00";
        DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy kk:mm:ss");
        DateTime jodatime = dtf.parseDateTime(dateTime);

        c.set(Calendar.MONTH, Integer.parseInt(jodatime.toString("M")));
        c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(jodatime.toString("d")));
        c.set(Calendar.YEAR, Integer.parseInt(jodatime.toString("y")));        
        c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(jodatime.toString("k")));
        c.set(Calendar.MINUTE, Integer.parseInt(jodatime.toString("m")));
        c.set(Calendar.SECOND, Integer.parseInt(jodatime.toString("s")));
        java.util.Date d = c.getTime();
        System.out.println("Date" + d);
        System.out.println("Start point: " + jodatime.toString());
        System.out.println("Nearest whole minute: " + toNearestWholeMinute(d));
        System.out.println("Nearest whole hour: " + toNearestWholeHour(d));
        DateTime ab = toNearestWholeHour(d);
        System.out.println(Integer.parseInt(ab.toString("M")));
        System.out.println(Integer.parseInt(ab.toString("d")));
        System.out.println(Integer.parseInt(ab.toString("y")));
        String timeStamp = ab.toString("y") + ab.toString("M") + ab.toString("d");
        //"http://api.wunderground.com/api/%s/conditions/q/%s,%s.json"
        //20060405
        System.out.println("Timestamp");
        String finalUrl = String.format(a, apiKey, timeStamp,Double.toString(Latitute), Double.toString(Longitude));
        System.out.println(finalUrl);
        System.out.println("Final");
        URL url;
        HttpURLConnection conn;
        BufferedReader rd;
        String line;
        String result = "";
        try {
           url = new URL(finalUrl);
           conn = (HttpURLConnection) url.openConnection();
           conn.setRequestMethod("GET");
           rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
           while ((line = rd.readLine()) != null) {
              result += line;
           }
           rd.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonString = result;
        System.out.println(jsonString);
        System.out.println("Yahi tak bhai");
        JSONParser jsonParser = new JSONParser();
        try {
          jsonObject = (JSONObject) jsonParser.parse(jsonString);
        } catch (ParseException ex) {
          ex.printStackTrace();
        } 
        String aaaaa = "$.history.observations[" + String.valueOf(0) + "].tempm";
        Object check = Configuration.defaultConfiguration().jsonProvider().parse(jsonString);
        String jso = JsonPath.read(check, aaaaa);
        Double atReturn =  new Double((jso).toString());
        System.out.println(atReturn);
    }

    static DateTime toNearestWholeMinute(java.util.Date d) {
        Calendar c = new GregorianCalendar();
        c.setTime(d);

        if (c.get(Calendar.SECOND) >= 30)
            c.add(Calendar.MINUTE, 1);

        c.set(Calendar.SECOND, 0);  

        DateTime dateTime = new DateTime(c.getTime());
        return dateTime;
    }

    static DateTime toNearestWholeHour(java.util.Date d) {
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
