package SubwayMap;

import WeightedGraph.Edge;
import WeightedGraph.Vertex;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * A whole subway map based on Stops and Roads
 * which uses Graph model.
 * Created by cxz on 2016/11/16.
 */
public class SubwayMap extends WeightedGraph.Graph {

    private SubwayMap() {
        super();
        HashMap<String, Stop> vertexSet = new HashMap<>();
        Stop tempV;
        List<Edge> tempEdgeList;
        Edge tempE;

        DBconnection DB = new DBconnection();
        DB.Connect();
        String findStops = "SELECT * FROM stops WHERE stop_id LIKE '%N' OR stop_id LIKE '%S'";
        String findTransfers;
        ResultSet rsFindStops = DB.Query(findStops);
        try {
            while (rsFindStops.next()) {
                tempV = new Stop(rsFindStops.getString("stop_id"), rsFindStops.getString("stop_name"), rsFindStops.getFloat("stop_lat"), rsFindStops.getFloat("stop_lon"), rsFindStops.getString("parent_station"));
                vertexSet.put(tempV.getId(), tempV);
            }
            for (Map.Entry<String, Stop> i : vertexSet.entrySet()) {
                Stop currentStop = i.getValue();
                findTransfers = "SELECT * FROM transfers WHERE from_stop_id = '" + currentStop.getStation() + "'";
                ResultSet rsFindTransfers = DB.Query(findStops);
                tempEdgeList = new LinkedList<>();
                while (rsFindTransfers.next()) {

                    rsFindTransfers.getString("from_stop_id");
                    rsFindTransfers.getString("to_stop_id");
                    tempE = new Road(, rsFindTransfers.getInt("min_transfer_time"));
                }
            }


        } catch (SQLException sqle) {
            sqle.getErrorCode();
        }
    }
}
