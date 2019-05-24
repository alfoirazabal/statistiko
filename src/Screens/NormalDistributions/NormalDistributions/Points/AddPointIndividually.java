package Screens.NormalDistributions.NormalDistributions.Points;

import Generals.Generals;
import Objs.NormalDistributions.Evaluation;
import Screens.NormalDistributions.ExamineNormDist;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AddPointIndividually {

    public static void Show(Evaluation e){

        List<Float> points = new ArrayList<>();

        Generals.clearConsole();
        System.out.println("STATISTIKO - Agregar Puntos Individualmente");
        System.out.println();
        System.out.println("Inserte puntos (numeros), para terminar, inserte una letra.");
        System.out.println();
        boolean numberInserted = true;
        while(numberInserted){
            try{
                System.out.print(": ");
                Scanner sc = new Scanner(System.in);
                float numToInsert = sc.nextFloat();
                points.add(numToInsert);
            }catch(InputMismatchException iME){
                numberInserted = false;
            }
        }
        System.out.println();
        e.addIndividualPoints(points);
        Data.Data.serialize();
        Generals.pressEnterToContinue("Datos Guardados, presione enter para continuar.");
        ExamineNormDist.Show(e);
    }

}
