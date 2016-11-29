package SubwayMap;

import WeightedGraph.Edge;
import WeightedGraph.Vertex;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * A whole subway map based on Stops and Roads
 * which uses Graph model.
 * Created by cxz on 2016/11/16.
 */
public class SubwayMap extends WeightedGraph.Graph {
    HashMap<String, Stop> vertexSet = new HashMap<>();
    TimeZone timeZone = TimeZone.getTimeZone("US/Eastern");

    public SubwayMap(){
        super();
        Stop tempStop;
        Road tempRoad;
        Calendar cal = Calendar.getInstance(timeZone);

        DBconnection DB = new DBconnection();
        DB.Connect();
        String findStops = "SELECT * FROM stops WHERE stop_id LIKE '%N' OR stop_id LIKE '%S'";

        ResultSet rsFindStops = DB.Query(findStops);
        try {
            // up all the stops into a set
            while (rsFindStops.next()) {
                tempStop = new Stop(rsFindStops.getString("stop_id"), rsFindStops.getString("stop_name"), rsFindStops.getFloat("stop_lat"), rsFindStops.getFloat("stop_lon"), rsFindStops.getString("parent_station"));
                vertexSet.put(tempStop.getId(), tempStop);
            }
            // for each stop in the vertexset, find all the roads connected to it, and put into the graph
            for (Map.Entry<String, Stop> i : vertexSet.entrySet()) {

                Stop fromStop = i.getValue();

                // find walking roads from table: 'transfers'.
                String findTransfers = "SELECT * FROM transfers WHERE from_stop_id = '" + fromStop.getStation() + "'";

                ResultSet rsFindTransfers = DB.Query(findTransfers);
                while (rsFindTransfers.next()) {
                    String toStation = rsFindTransfers.getString("to_stop_id");
                    if (toStation.equals(fromStop.getStation())) {
                        if (fromStop.getId().charAt(fromStop.getId().length() - 1) == 'N') {
                            tempRoad = new Road(fromStop, vertexSet.get(toStation + "S"), rsFindTransfers.getInt("min_transfer_time"), true);
                            addEdge(tempRoad);
                        } else {
                            tempRoad = new Road(fromStop, vertexSet.get(toStation + "N"), rsFindTransfers.getInt("min_transfer_time"), true);
                            addEdge(tempRoad);
                        }
                    } else {
                        tempRoad = new Road(fromStop, vertexSet.get(toStation + "S"), rsFindTransfers.getInt("min_transfer_time"), true);
                        addEdge(tempRoad);
                        tempRoad = new Road(fromStop, vertexSet.get(toStation + "N"), rsFindTransfers.getInt("min_transfer_time"), true);
                        addEdge(tempRoad);
                    }
                }
                // find railway roads from tables: 'stop_times' and 'trips'
                String findTrip;

            }
        } catch (SQLException sqle) {
            sqle.getErrorCode();
        }
        DB.getClass();
    }

    /**
     * Get which day it is during the week
     * @param date
     * @return String SAT SUN or WKD
     */
    String getWeek(Date date){
        String[] result = {"WKD","SAT","SUN"};
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        String week = sdf.format(date);
        if(week.equals("Sunday"))return result[2];
        if(week.equals("Saturday"))return result[1];
        return result[0];
    }
    /***/
}
