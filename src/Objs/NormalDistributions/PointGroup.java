package Objs.NormalDistributions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PointGroup implements Serializable {

    public int times;
    public float numToEval;

    public PointGroup(int times, float numToEval){
        this.times = times;
        this.numToEval = numToEval;
    }

    public static List<Float> toIndividualPoints(PointGroup pg){
        List<Float> points = new ArrayList<>();
        for (Float point : points) {
            points.add(point);
        }
        return points;
    }

    public final String toString(){
        return "[T: " + this.times + ", V: " + this.numToEval + "]";
    }

}
