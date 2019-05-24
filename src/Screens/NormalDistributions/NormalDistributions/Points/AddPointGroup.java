package Screens.NormalDistributions.NormalDistributions.Points;

import Data.Data;
import Generals.Generals;
import Objs.NormalDistributions.Evaluation;
import Objs.NormalDistributions.PointGroup;
import Screens.NormalDistributions.ExamineNormDist;

public class AddPointGroup {

    public static void Show(Evaluation e){
        printHeader();
        String currUsrCommand = "s";
        while(currUsrCommand.equals("s")){
            int times = Generals.getUserInputInt("T: ");
            float value = Generals.getUserInputFloat("V: ");
            try{
                PointGroup existingPoint = e.getPointByNum(value);
                existingPoint.times += times;
                System.out.println("El grupo de puntos de valor '" + value + "' ya existe, y sus repeticiones " +
                        "aumentaron en " + times);
            }catch(Evaluation.PointNotFound pointNotFound){
                //Since the point value is not found, create new value
                PointGroup pg = new PointGroup(times, value);
                e.points.add(pg);
            }
            System.out.println();
            currUsrCommand = Generals.getUserInputString("Continuar? s / n: ");
            printHeader();
        }
        Data.serialize();
        Generals.pressEnterToContinue("Datos guardados, presione enter para continuar.");
        ExamineNormDist.Show(e);
    }

    public static void printHeader(){
        Generals.clearConsole();
        System.out.println("STATISTIKO - Add Point Group");
        System.out.println();
    }

}
