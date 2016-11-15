# NewYorkSubway
Implement a map of New York Subway, in order to find the fastest path between stations

File Structure
|__data
|   |__agency.txt       // nothing useful
|   |__calender.txt     // service_id ? start_date ? end_date ?
|   |__calender_data.txt// service_id ? date ? exception_type ?
|   |__routes.txt       // 28 subway lines, contains:id, names, description, url,and color
|   |__shapes.txt       // shape ? contains id, latitude, longitude, shape_pt_sequence?
|   |__stop_times.txt   // 50000 records,which describes time schedule, contains: trip_id, arrival_time, departure_time, stop_id, stop_sequence
|   |__stops.txt        // subway stops,contains:id, name,latitude, longitude, location_type, parent_station
|   |__transfers.txt    // transfer times from one station to another, contains: from_stop_id, to_stop_id, min_transfer_time.
|   |__trips.txt        // 2000 records, contains: trip_id, service_id, trip_id, trip_headsign ? , shape_id
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

Problems:
1. What is "Shape"
2. Why transfer through same stop also cost time?
3. As we have the "stop_time", what is calender for?
