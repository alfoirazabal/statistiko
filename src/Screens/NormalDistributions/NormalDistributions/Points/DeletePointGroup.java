package Screens.NormalDistributions.NormalDistributions.Points;

import Data.Data;
import Generals.Generals;
import Objs.NormalDistributions.Evaluation;
import Objs.NormalDistributions.PointGroup;
import Screens.NormalDistributions.ExamineNormDist;

public class DeletePointGroup {

    public static void Show(Evaluation e){

        Generals.clearConsole();
        System.out.println("STATISTIKO - Borrar grupo de puntos");
        System.out.println();
        System.out.println("0) Atras");
        System.out.println();
        System.out.println("Seleccionar grupo a borrar: ");
        String[] pointGroupsMenu = new String[e.points.size()];
        for (int i = 0; i < pointGroupsMenu.length; i++) {
            pointGroupsMenu[i] = e.points.get(i).toString();
        }
        Generals.printMenuSelection(pointGroupsMenu, 1);
        int usrInput = Generals.getUserInputInt(": ");
        if(usrInput == 0){
            ExamineNormDist.Show(e);
        }else{
            if(usrInput > 0 && usrInput <= pointGroupsMenu.length){
                deleteGivenPointGroup(e, e.points.get(usrInput - 1));
                Show(e);
            }else{
                Generals.printCommandNotRecognized(String.valueOf(usrInput));
                Show(e);
            }
        }

    }

    public static void deleteGivenPointGroup(Evaluation e, PointGroup p){
        String randomCode = Generals.generateRandomCode(5);
        System.out.print("Escriba '" + randomCode + "' para confirmar: ");
        String usrCode = Generals.getUserInputString(": ");
        if(randomCode.equals(usrCode)){
            e.points.remove(p);
            Data.serialize();
            System.out.println("Punto borrado!");
            Generals.pressEnterToContinue("Presione enter para continuar.");
        }else{
            Generals.pressEnterToContinue("Los códigos no coinciden. No borrado");
        }
    }

}
