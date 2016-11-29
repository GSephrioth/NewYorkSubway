package SubwayMap;

import java.util.Calendar;
import java.util.Date;

/**
 * Stops where the train actually pick and drop passengers
 * Created by cxz on 2016/11/16.
 */
public class Stop extends WeightedGraph.Vertex {
    private String name;
    private Float latitude;
    private Float longitude;

    Stop(String id,String name, Float latitude, Float longitude){
        super(id);
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getId() {
        return getVertexName();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Float getLatitude() {
        return latitude;
    }
    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }
    public Float getLongitude() {
        return longitude;
    }
    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        String str;
        str =   "[Station: " + this.name +", "+
                "Stop_id: "+super.getVertexName()+"]";
        return str;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Stop.class != o.getClass()) return false;
        Stop v = (Stop) o;
        return super.getVertexName() != null ? super.getVertexName().equals(v.getVertexName()) : v.getVertexName() == null;
    }
}
