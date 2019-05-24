package Objs.LinearRegressions;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Equation implements Serializable {

    public float m;
    public float n;

    public Equation(float m, float n){
        this.m = m;
        this.n = n;
    }

    public static String Stringify(Equation eq, int decimals){
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(decimals);
        String nString = df.format(Math.abs(eq.n));
        if(eq.n < 0){
            nString = "- " + nString;
        }else{
            nString = "+ " + nString;
        }
        return "y = " + df.format(eq.m) + "x " + nString;
    }

    public float getYFromX(float x){
        return this.m * x + this.n;
    }

    public float getXFromY(float y){
        return ((y - this.n) / this.m);
    }

}
