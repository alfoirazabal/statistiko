package Screens.NormalDistributions;

import Data.Data;
import Generals.Generals;
import Objs.NormalDistributions.Evaluation;
import Screens.MenuMain;
import Screens.NormalDistributions.NormalDistributions.MenuAddNormalDistribution;

public class MenuNormDist {

    public static void Show(){

        Generals.clearConsole();
        System.out.println("STATISTIKO - Distribuciones Normales");
        System.out.println();
        System.out.println("0) Atras");
        System.out.println("1) Agregar Evaluación");
        System.out.println();
        for(int i = 2 ; i <= Data.evaluations.size() + 1 ; i++){
            Evaluation e = Data.evaluations.get(i - 2);
            System.out.println(i + ") " + e.name + " | " + e.description);
        }
        System.out.println();
        int usrInput = Generals.getUserInputInt(": ");
        switch (usrInput){
            case 0:
                MenuMain.Show();
                break;
            case 1:
                MenuAddNormalDistribution.Show();
                break;
            default:
                if(usrInput > 1 && usrInput < Data.evaluations.size() + 2){
                    ExamineNormDist.Show(Data.evaluations.get(usrInput - 2));
                }else{
                    Generals.printCommandNotRecognized(String.valueOf(usrInput));
                    MenuNormDist.Show();
                }
        }

    }

}
