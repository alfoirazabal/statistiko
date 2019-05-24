package Objs.LinearRegressions;

import java.io.Serializable;

public class Point implements Serializable {

    public String pointName;
    public float x;
    public float y;

    public Point(String pointName, float x, float y){
        this.pointName = pointName;
        this.x = x;
        this.y = y;
    }

    public static String Stringify(Point p){
        return p.pointName + " | X: " + p.x + " | Y: " + p.y;
    }

}
