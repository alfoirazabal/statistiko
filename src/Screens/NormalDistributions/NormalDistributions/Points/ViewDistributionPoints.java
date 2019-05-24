package Screens.NormalDistributions.NormalDistributions.Points;

import Generals.Generals;
import Objs.NormalDistributions.Evaluation;
import Objs.NormalDistributions.PointGroup;
import Screens.NormalDistributions.ExamineNormDist;

public class ViewDistributionPoints {

    public static void Show(Evaluation e){

        Generals.clearConsole();
        System.out.println("STATISTIKO - Ver puntos de la distribución");
        System.out.println();
        for(int i = 0 ; i < e.points.size() ; i++){
            PointGroup pg = e.points.get(i);
            System.out.println("[T: " + pg.times + ", V: " + pg.numToEval + "]");
        }
        System.out.println();
        Generals.pressEnterToContinue("Presionar enter para continuar.");
        ExamineNormDist.Show(e);

    }

}
