package Objs.LinearRegressions;

import java.io.Serializable;
import java.util.List;

public class LinearRegressionData implements Serializable {

    public float avgX;
    public float avgY;
    public Equation eq;
    public float rSqrd;
    public float see;   //Standard Error of the Estimate

    public class NotEnoughData extends Exception{
        public String toString(){
            return "No hay suficientes datos. Se necesitan más de 3 puntos.";
        }
    }

    public LinearRegressionData(List<Point> points) throws NotEnoughData{
        if(points.size() < 3){
            throw new NotEnoughData();
        }else{
            int n = points.size();

            //Calc avg of x and y
            float sumOfX = 0;
            float sumOfY = 0;
            for (Point point : points) {
                sumOfX += point.x;
                sumOfY += point.y;
            }
            this.avgX = sumOfX / n;
            this.avgY = sumOfY / n;

            //Calc equation
            float b1n = 0;
            float b1d = 0;
            for (Point point : points) {
                float xMinAvgX = point.x - avgX;
                float yMinAvgY = point.y - avgY;
                b1n += xMinAvgX * yMinAvgY;
                b1d += Math.pow(xMinAvgX, 2);
            }
            float b1 = b1n / b1d;
            float b0 = (avgY  - (b1 * avgX));
            this.eq = new Equation(b1, b0);

            //Calculate yEst for points
            float[] estYs = new float[n];
            for (int i = 0; i < points.size(); i++) {
                estYs[i] = eq.getYFromX(points.get(i).x);
            }

            //RSqrd
            float rSqrdNom = 0;
            float rSqrdDen = 0;
            for (int i = 0; i < points.size(); i++) {
                Point currPoint = points.get(i);
                rSqrdNom += Math.pow((estYs[i] - avgY), 2);
                rSqrdDen += Math.pow((currPoint.y - avgY), 2);
            }
            this.rSqrd = rSqrdNom / rSqrdDen;

            //SEE
            float seeNum = 0;
            float seeDen = 0;
            for (int i = 0; i < points.size(); i++) {
                Point currPoint = points.get(i);
                seeNum += Math.pow((estYs[i] - currPoint.y), 2);
            }
            seeDen = n - 2;
            this.see = (float)Math.sqrt(seeNum / seeDen);
        }
    }

}
