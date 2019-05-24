package Screens.NormalDistributions.NormalDistributions;

import Data.Data;
import Generals.Generals;
import Objs.NormalDistributions.Evaluation;
import Screens.NormalDistributions.MenuNormDist;

public class MenuAddNormalDistribution {

    public static void Show(){

        Generals.clearConsole();
        System.out.println("STATISTIKO - Nueva Evaluación");
        System.out.println();
        System.out.println("En cualquier momento puede escribir 'cancelar' para cancelar.");
        System.out.println();
        String name = "";
        String description = "";
        String varName = "";
        boolean finished = false;
        while(!name.equals("cancelar") && !description.equals("cancelar") && !varName.equals("cancelar") && !finished){
            name = Generals.getUserInputString("NOMBRE: ");
            description = Generals.getUserInputString("DESCRIPCIÓN: ");
            varName = Generals.getUserInputString("NOMBRE DE VARIABLE A MEDIR: ");
            finished = true;
        }
        if(name.equals("cancelar") || description.equals("cancelar") || varName.equals("cancelar")){
            Generals.pressEnterToContinue("Agregado cancelado.");
            MenuNormDist.Show();
        }else{
            Evaluation e = new Evaluation(name, description, varName);
            Data.evaluations.add(e);
            Data.serialize();
            System.out.println();
            Generals.pressEnterToContinue("Datos Guardados.");
            MenuNormDist.Show();
        }

    }

}
