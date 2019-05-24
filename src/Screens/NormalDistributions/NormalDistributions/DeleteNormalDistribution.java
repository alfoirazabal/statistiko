package Screens.NormalDistributions.NormalDistributions;

import Data.Data;
import Generals.Generals;
import Objs.NormalDistributions.Evaluation;
import Screens.NormalDistributions.ExamineNormDist;
import Screens.NormalDistributions.MenuNormDist;

public class DeleteNormalDistribution {

    public static void Show(Evaluation e){

        Generals.clearConsole();
        String currentCode = Generals.generateRandomCode(5);
        System.out.println("STATISTIKO - Borrar Evaluación: " + e.name);
        System.out.println();
        String usrCode = Generals.getUserInputString("Escriba '" + currentCode + "' para confirmar: ");
        if(currentCode.equals(usrCode)){
            Data.evaluations.remove(e);
            Data.serialize();
            System.out.println();
            Generals.pressEnterToContinue("Evaluación borrada, presione enter para continuar.");
            MenuNormDist.Show();
        }else{
            Generals.pressEnterToContinue("Códigos no identicos.");
            Generals.pressEnterToContinue("Presione enter para continuar.");
            ExamineNormDist.Show(e);
        }

    }

}
