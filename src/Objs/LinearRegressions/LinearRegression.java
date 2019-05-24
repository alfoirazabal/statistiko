package Objs.LinearRegressions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LinearRegression implements Serializable {

    public String regressionName;
    public String description;
    public String yVarName;
    public String xVarName;
    public List<Point> points = new ArrayList<>();

    public LinearRegression(String regName, String desc, String yVarName, String xVarName){
        this.regressionName = regName;
        this.description = desc;
        this.yVarName = yVarName;
        this.xVarName = xVarName;
    }

    public LinearRegressionData getData() throws LinearRegressionData.NotEnoughData {
        return new LinearRegressionData(this.points);
    }

    public static String Stringify(LinearRegression lr){
        return lr.regressionName + " | " + lr.description;
    }

}
