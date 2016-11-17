package SubwayMap;

import WeightedGraph.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * a temporary Test class for this package
 * Created by cxz on 2016/11/16.
 */
public class Test {
    public static void test() {
        // read a set of Stops
        Set<Stop> vertexSet = new HashSet<>();
        Stop tempV;

        DBconnection DB = new DBconnection();
        DB.Connect();
        String findStops = "SELECT * FROM stops WHERE stop_id LIKE '%N' OR stop_id LIKE '%S'";
        ResultSet rs = DB.Query(findStops);
        try {
            while (rs.next()) {
                tempV = new Stop(rs.getString("stop_id"), rs.getString("stop_name"), rs.getFloat("stop_lat"), rs.getFloat("stop_lon"), rs.getString("parent_station"));
                vertexSet.add(tempV);
            }
        } catch (SQLException sqle) {
            sqle.getErrorCode();
        }

        for (Stop i : vertexSet) {
            System.out.println(i.getStation());
        }

        // read roads for walk from 'transfers'


        DB.Close();
    }
}
