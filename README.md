# NewYorkSubway
Implement a map of New York Subway, in order to find the fastest path between stations

File Structure
|__data
|   |__agency.txt       // nothing useful
|   |__calender.txt     // when the service is working each week, contains: service_id, start_date, end_date
|   |__calender_data.txt// exception date when the service is running or not, contains: service_id, date, exception_type
|   |__routes.txt       // 28 subway lines, contains: id, names, description, url,and color
|   |__shapes.txt       // shape is uesd to draw a line of subway route, which is useless,contains: id, latitude, longitude, shape_pt_sequence
|   |__stop_times.txt   // 535811 records,which describes time schedule, contains: trip_id, arrival_time, departure_time, stop_id, stop_sequence
|   |__stops.txt        // subway stops,contains:id, name,latitude, longitude, location_type, parent_station
|   |__transfers.txt    // transfer times from one station to another, contains: from_stop_id, to_stop_id, min_transfer_time.
|   |__trips.txt        // 2000 records, contains: route_id, service_id, trip_id, trip_headsign ? , shape_id
|
|__src
    |__Test
        |__Main.class
    |__WeightedGraph
        |__Edge.class
        |__Graph.class
        |__MinHeap.class
        |__Vertex.class

Data description
The most important data file is "trips.txt", because it combines all other data.
In the data, subway trains are traveling several trips everyday, which stops at a sequence of stations on specific times.

Each station has two stops, which adds "N" or "S" at the end of station name.
And those two stops are where the subway train really stops.
eg. Station "101" with two stops: "101S","101N".

"Transfers" time between different stations means the time walk from one to the other.
"Transfers" time of the same station means the minimum time walk in the station, like walk from "N" to "S" or from station to stop.


Problems:
1. What is "Shape"
2. Why transfer through same stop also cost time?
3. As we have the "stop_time", what is calender for?
