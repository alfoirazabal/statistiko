package Objs.NormalDistributions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Evaluation implements Serializable {

    public String name;
    public String description;
    public String variableName;
    public List<PointGroup> points = new ArrayList<>();

    public class EvaluationData{
        public float average;
        public float median;
        public float mode;
        public float variance;
        public float stdDev;
        public float gini;
        public float pearsonsSkewment;
        public float kurtosis;
    }

    public class PointNotFound extends Exception{
        public String toString(){
            return "Point not found!";
        }
    }
    public class MorePointsAreNeeded extends Exception{
        public String toString(){ return "Needs at least 4 points!"; }
    }
    public class NoPointFound extends Exception{
        public String toString(){ return "No point was found!"; }
    }

    public Evaluation(String name, String description, String variableName){
        this.name = name;
        this.description = description;
        this.variableName = variableName;
    }

    public PointGroup getPointByNum(float num) throws PointNotFound{
        for (PointGroup point : points) {
            if(point.numToEval == num){
                return point;
            }
        }
        throw new PointNotFound();
    }

    public static List<Float> toIndividualPoints(Evaluation e){
        List<Float> individualPoints = new ArrayList<>();
        for (PointGroup point : e.points) {
            for(int i = 0 ; i < point.times ; i++){
                individualPoints.add(point.numToEval);
            }
        }
        return individualPoints;
    }

    public final void addIndividualPoints(List<Float> points){
        for (Float point : points) {
            try{
                getPointByNum(point).times++;
            }catch(PointNotFound e){
                this.points.add(new PointGroup(1, point));
            }
        }
    }

    public final PointGroup getPointByNumToEval(float num) throws NoPointFound{
        for (PointGroup point : this.points) {
            if(point.numToEval == num){
                return point;
            }
        }
        throw new NoPointFound();
    }

    public static final int getTotalNumberOfPoints(Evaluation e){
        int currNum = 0;
        for (PointGroup point : e.points) {
            currNum += point.times;
        }
        return currNum;
    }

    public static void drawStringedGraph(Evaluation e, int maxBarLength){
        String[] lines = getStringedGraph(e, maxBarLength);
        for (String line : lines) {
            System.out.println(line);
        }
    }

    private static String[] getStringedGraph(Evaluation e, int maxBarLength){
        List<PointGroup> points = sortEvPointByNumToEval(e.points);
        float[][] pointValueTimes = new float[points.size()][2];
        for (int i = 0; i < pointValueTimes.length; i++) {
            pointValueTimes[i][0] = points.get(i).numToEval;
            pointValueTimes[i][1] = points.get(i).times;
        }
        float max = findMaxTimeInPointGroupList(points);
        String[] lines = new String[pointValueTimes.length];
        for (int i = 0; i < lines.length; i++) {
            lines[i] = floatToFixedStringOfXLength(pointValueTimes[i][0], 4);
            int currentTimeExtension = (int)Math.floor(pointValueTimes[i][1] * maxBarLength / max);
            for (int j = 0; j < currentTimeExtension; j++) {
                lines[i] += "█";
            }
        }
        return lines;
    }

    private static List<PointGroup> sortEvPointByNumToEval(List<PointGroup> pg){
        List<PointGroup> npg = new ArrayList<>();
        for (PointGroup pointGroup : pg) {
            npg.add(new PointGroup(pointGroup.times, pointGroup.numToEval));
        }
        //Bubble sort
        PointGroup aux;
        for (int i = 0; i < npg.size(); i++) {
            for (int j = 0; j < npg.size(); j++) {
                if(npg.get(i).numToEval < npg.get(j).numToEval){
                    aux = npg.get(i);
                    npg.set(i, npg.get(j));
                    npg.set(j, aux);
                }
            }
        }
        return npg;
    }

    private static float findMaxTimeInPointGroupList(List<PointGroup> pg){
        float currMax = Float.MIN_VALUE;
        for (PointGroup pointGroup : pg) {
            if(pointGroup.times > currMax){
                currMax = pointGroup.times;
            }
        }
        return currMax;
    }

    private static String floatToFixedStringOfXLength(float num, int length){
        String toOutput = "";
        char[] floatVal = new char[length];
        char[] numToStr = String.valueOf(num).toCharArray();
        for(int i = 0 ; i < numToStr.length ; i++){
            if(i < length){
                floatVal[i] = numToStr[i];
            }
        }
        if(numToStr.length < length){
            for(int i = numToStr.length ; i < length ; i++){
                floatVal[i] = '0';
            }
        }
        for (int i = 0; i < length; i++) {
            toOutput += floatVal[i];
        }
        toOutput += " ";
        return toOutput;
    }

    public EvaluationData getEvalData() throws MorePointsAreNeeded {
        EvaluationData ed = new EvaluationData();
        List<Float> indPoints = toIndividualPoints(this);
        int n = indPoints.size();
        if(indPoints.size() < 4){
            throw new MorePointsAreNeeded();
        }else{
            System.out.println();
            Collections.sort(indPoints);    //Sort list numerically

            //Calc Average
            float sum = 0;
            for (Float indPoint : indPoints) {
                sum += indPoint;
            }
            float avg = sum / indPoints.size();

            //Calc Median
            float median = indPoints.get((int)Math.floor((double)(indPoints.size() / 2)));

            //Calc Mode
            float foundMaxRep = 0;
            float foundMode = Float.MIN_VALUE;
            for (PointGroup point : this.points) {
                int repetitions = 0;
                for (Float indPoint : indPoints) {
                    if(point.numToEval == indPoint){
                        repetitions++;
                    }
                }
                if(repetitions > foundMaxRep){
                    foundMode = point.numToEval;
                    foundMaxRep = repetitions;
                }
            }
            if(foundMode == Float.MIN_VALUE){
                //Throw Exception
            }
            float mode = foundMode;

            //Calc Variance
            float sumMinusAvgsSqrd = 0;
            for (Float indPoint : indPoints) {
                sumMinusAvgsSqrd += (Math.pow(indPoint - avg, 2));
            }
            float variance = sumMinusAvgsSqrd / indPoints.size();

            //Calc StdDev
            float sdcomp1 = 0;
            float sdcomp2 = n - 1;
            for (Float indPoint : indPoints) {
                sdcomp1 += (float)Math.pow((indPoint - avg), 2);
            }
            float stdDev = (float)Math.sqrt(sdcomp1 / sdcomp2);

            //Calc Gini
            float giniNumerator = 0;
            float giniDenominator = 0;
            for (int i = 0; i < indPoints.size(); i++) {
                for (int j = 0; j < indPoints.size(); j++) {
                    giniNumerator += Math.abs(indPoints.get(i) - indPoints.get(j));
                }
                giniDenominator += indPoints.get(i);
            }
            giniDenominator = 2 * indPoints.size() * giniDenominator;
            float gini = giniNumerator / giniDenominator;

            //Calc Pearsons Skewment
            float pearsonsSkewment = (avg - mode) / stdDev;

            //Calc Kurtosis
            float kcomp11;
            float kcomp12;
            float kcomp2;
            float kcomp31;
            float kcomp32;
            kcomp11 = n * (n + 1);
            kcomp12 = (n - 1) * (n - 2) * (n - 3);
            kcomp2 = 0;
            for (Float indPoint : indPoints) {
                float comp21 = indPoint - avg;
                float comp22 = stdDev;
                kcomp2 += Math.pow((comp21 / comp22), 4);
            }
            kcomp31 = 3 * (float)Math.pow((n - 1), 2);
            kcomp32 = (n - 2) * (n - 3);
            float kcomp1 = kcomp11 / kcomp12;
            float kcomp3 = kcomp31 / kcomp32;
            float kurtosis = (kcomp1 * kcomp2) - kcomp3;

            //Add to object ed
            ed.average = avg;
            ed.kurtosis = kurtosis;
            ed.median = median;
            ed.mode = mode;
            ed.pearsonsSkewment = pearsonsSkewment;
            ed.stdDev = stdDev;
            ed.variance = variance;
            ed.gini = gini;

            return ed;
        }

    }

}
