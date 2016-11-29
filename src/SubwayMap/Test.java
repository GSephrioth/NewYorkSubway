package SubwayMap;

import WeightedGraph.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * a temporary Test class for this package
 * Created by cxz on 2016/11/16.
 */
public class Test {
    public static void test() {
        // Test time and date
//        DateFormat dtf =new SimpleDateFormat("HH:mm:ss");
//        TimeZone timeZone = TimeZone.getTimeZone("US/Eastern");
//        Date now0 = new Date(2016,10,25);
//        Date now1 = new Date(2016,10,26);
//        Date now = new Date(2016,10,27);
//
//        dtf.setTimeZone(timeZone);
//        System.out.println(dtf.format(now));
//        System.out.println(getWeek(now0));
//        System.out.println(getWeek(now1));
//        System.out.println(getWeek(now));

// Test SubwayMap

        Scanner s = new Scanner(System.in);
        SubwayMap swm = new SubwayMap();
//        System.out.println(swm);
        System.out.println("Please Input a Station Name:");
        try {
            String stationName = s.nextLine();
            swm.Dijkstra(stationName);
        }catch (Exception e){
            System.err.println("Can not find the station!");
            System.err.println("Please input a valid value ~");
        }
// Test DB connection
//        DBconnection DB = new DBconnection();
//        DB.Connect();
//        String findTrips = "SELECT trip_id,Min(arrival_time) FROM stop_times WHERE arrival_time > '16:44:90'\n" +
//                "      AND arrival_time < '17:00:00'\n" +
//                "      AND trip_id LIKE '%WKD%'\n" +
//                "      AND stop_id = '134N'";
//
//        ResultSet rs = DB.Query(findTrips);
//        try{
//            while(rs.next()){
//                String str = rs.getString(1);
//                Date date = new Date(rs.getDate(2).getDate());
//                System.out.println(str);
//                System.out.println(date);
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }

    }

//    /**
//     * Get which day it is during the week
//     * @param date
//     * @return String SAT SUN or WKD
//     */
//    public static String getWeek(Date date){
//        String[] result = {"WKD","SAT","SUN"};
//        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
//        String week = sdf.format(date);
//        if(week.equals("Sunday"))return result[2];
//        if(week.equals("Saturday"))return result[1];
//        return result[0];
//    }

}
