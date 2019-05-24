package Data;

import Objs.LinearRegressions.LinearRegression;
import Objs.NormalDistributions.Evaluation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Data{

    public static List<Evaluation> evaluations = new ArrayList<>();
    public static List<LinearRegression> linearRegressions = new ArrayList<>();
    private static String fileName = "data";

    public static void serialize(){
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            Object[] data = new Object[2];
            data[0] = evaluations;
            data[1] = linearRegressions;
            out.writeObject(data);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void deserialize(){
        if(!fileExists(fileName)){
            serialize();
        }else{
            try {
                FileInputStream fileIn = new FileInputStream(fileName);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                Object[] data = (Object[])in.readObject();
                evaluations = (List<Evaluation>)data[0];
                linearRegressions = (List<LinearRegression>)data[1];
                in.close();
                fileIn.close();
            } catch (IOException i) {
                i.printStackTrace();
                return;
            } catch (ClassNotFoundException c) {
                System.out.println("List<Objs.NormalDistributions.Evaluation> class not found");
                c.printStackTrace();
                return;
            }
        }
    }

    private static boolean fileExists(String path){
        File f = new File(path);
        return f.isFile();
    }

}
