package Screens.NormalDistributions.NormalDistributions.Points;

import Data.Data;
import Generals.Generals;
import Objs.NormalDistributions.Evaluation;
import Objs.NormalDistributions.PointGroup;
import Screens.NormalDistributions.ExamineNormDist;

public class DeletePointsFromGroup {

    public static void Show(Evaluation e){

        Generals.clearConsole();
        System.out.println("STATISTIKO - Borrar grupo de puntos");
        System.out.println();
        System.out.println("0) Atras");
        System.out.println();
        System.out.println("Seleccionar grupo del cual borrar: ");
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
                deleteFromGroup(e.points.get(usrInput - 1));
                Show(e);
            }else{
                Generals.printCommandNotRecognized(String.valueOf(usrInput));
                Show(e);
            }
        }

    }

    public static void deleteFromGroup(PointGroup pg){
        Generals.clearConsole();
        System.out.println("STATISTIKO - Borrar puntos del grupo: " + pg.toString());
        System.out.println();
        int timesToDelete = Generals.getUserInputInt("Cantidad de puntos a borrar: ");
        if(timesToDelete > pg.times){
            System.out.println();
            Generals.pressEnterToContinue("No se pueden borrar más puntos de los que existen.");
            deleteFromGroup(pg);
        }else{
            System.out.println();
            String generatedCode = Generals.generateRandomCode(5);
            String usrCode = Generals.getUserInputString("Escriba '" + generatedCode + "' para confirmar: ");
            if(generatedCode.equals(usrCode)){
                pg.times -= timesToDelete;
                Data.serialize();
                System.out.println();
                Generals.pressEnterToContinue("Borrados " + timesToDelete + " puntos de " + pg.toString());
            }else{
                System.out.println();
                Generals.pressEnterToContinue("Los codigos no coinciden. Puntos no borrados");
            }

        }
    }

}
