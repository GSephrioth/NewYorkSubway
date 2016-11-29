package SubwayMap;

import WeightedGraph.Edge;
import WeightedGraph.MinHeap;
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

    public List<Stop> Dijkstra(String stationName,String endStationName) throws Exception {
        Stop endStation = null;
        Stop startStation = null;

        for (Map.Entry<String, Stop> i : vertexSet.entrySet()){
            if(i.getValue().getName().equals(stationName)){
                startStation = i.getValue();
            }
            if(i.getValue().getName().equals(endStationName)){
                endStation = i.getValue();
            }
            if(endStation != null && startStation != null)break;
        }
        List<Stop> resultStops = new LinkedList<>();
        List<Road> resultRoads = new ArrayList<>();
        Map<Vertex, Edge> result = new HashMap<>();
        MinHeap minHeap = new MinHeap();
        MinHeap roadRecord = new MinHeap();
        List<Edge> temp;   // temp store the Edge List of the Current Vertex
        if (graph.isEmpty() || !graph.containsKey(startStation))
            throw new Exception("Can`t find the Start Vertex in Graph");
        Vertex currentVertex = startStation;
        Road currentEdge = new Road(startStation, startStation, 0,false);

        result.put(startStation, currentEdge);
        /**
         * go through all the Edges to find the minimum Path
         * Complexity: E * log(V)
         * */
        while (result.size() < graph.size()) {

            temp = graph.get(currentVertex);
            if (temp == null || temp.isEmpty()) break;
            /**
             * Complexity: log(V) * number of Edges Linked to Current Vertex
             * */
            for (Edge e : temp) {
                if (!result.keySet().contains(e.getEndVertex())) {
                    // Replace the element having the same endVertex or add the element
                    Edge t = new Road((Stop)currentEdge.getStartVertex(), (Stop)e.getEndVertex(), currentEdge.getWeight() + e.getWeight(),false);
                    // keep the road record
                    Edge record = new Road((Stop)e.getStartVertex(), (Stop)e.getEndVertex(), currentEdge.getWeight() + e.getWeight(),false);
                    /**
                     * Replace the element having the same endVertex or add the element
                     * Complexity: log(V-1)
                     */
                    minHeap.update(t);
                    roadRecord.update(record); // keep the road record
                }
            }

            // select the edge with minimum weight
            currentEdge = (Road)minHeap.popMin();
            currentVertex = currentEdge.getEndVertex();


            // add the selected edge to the MST
            result.put(currentVertex, currentEdge);
            resultRoads.add((Road)roadRecord.popMin()); // keep the road record

            if(currentVertex.equals(endStation))break;
        }


        Stop tempStop = resultRoads.get(resultRoads.size()-1).getStartStation();
        resultStops.add(tempStop);
        while(!tempStop.equals(startStation)){
            for(Road r: resultRoads){
                if(r.getEndStation().equals(tempStop)){
                    tempStop = r.getStartStation();
                    resultStops.add(tempStop);
                    break;
                }
            }
        }
        return resultStops;
    }
}
