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
        String findStops = "SELECT * FROM stops WHERE stop_id NOT LIKE '%N' AND stop_id NOT LIKE '%S'";

        ResultSet rsFindStops = DB.Query(findStops);
        try {
            // up all the stops into a set
            while (rsFindStops.next()) {
                tempStop = new Stop(rsFindStops.getString("stop_id"), rsFindStops.getString("stop_name"), rsFindStops.getFloat("stop_lat"), rsFindStops.getFloat("stop_lon"));
                vertexSet.put(tempStop.getId(), tempStop);
            }
            // for each stop in the vertexset, find all the roads connected to it, and put into the graph
            for (Map.Entry<String, Stop> i : vertexSet.entrySet()) {

                Stop fromStop = i.getValue();

                // find walking roads from table: 'transfers'.
                String findTransfers = "SELECT * FROM transfers WHERE from_stop_id = '" + fromStop.getId() + "'";

                ResultSet rsFindTransfers = DB.Query(findTransfers);
                while (rsFindTransfers.next()) {
                    String toStationId = rsFindTransfers.getString("to_stop_id");
                    tempRoad = new Road(fromStop, vertexSet.get(toStationId), rsFindTransfers.getInt("min_transfer_time"), true);
                    addEdge(tempRoad);
                }
                // find railway roads from tables: 'subway_graph'
                String findTrip = "SELECT * FROM subway_graph WHERE src_stop_id = '" + fromStop.getId() + "'";

                ResultSet rsFindTrip = DB.Query(findTrip);
                while (rsFindTrip.next()) {
                    String toStationId = rsFindTrip.getString("dest_stop_id");
                    tempRoad = new Road(fromStop, vertexSet.get(toStationId), rsFindTrip.getInt("duration"), true);
                    addEdge(tempRoad);
                }
            }
        } catch (SQLException sqle) {
            sqle.getErrorCode();
        }
        DB.getClass();
    }

    public Map<Vertex, Edge> Dijkstra(String StationName) throws Exception {
        Stop startStop = null;
        for (Map.Entry<String, Stop> i : vertexSet.entrySet()){
            if(i.getValue().getName().equals(StationName)){
                startStop = i.getValue();
                break;
            }
        }
        return super.Dijkstra(startStop);
    }
}
