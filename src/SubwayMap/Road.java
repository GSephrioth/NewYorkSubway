package SubwayMap;

/**
 * Road connecting two stops
 * Created by cxz on 2016/11/16.
 */
public class Road extends WeightedGraph.Edge {

    private Boolean walk; // False if road is read from 'stop_times'; True if road is read from 'transfers'

    public Boolean isWalk() {
        return walk;
    }

    public Road(Stop from, Stop to, int time, boolean walk){
        super(from,to,time);
        this.walk = walk;
    }
    public Stop getStartStation(){return (Stop)super.getStartVertex();}
    public Stop getEndStation(){return (Stop)super.getEndVertex();}
}
