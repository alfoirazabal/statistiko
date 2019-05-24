package Screens.LinearRegressions.Points;

import Data.Data;
import Generals.Generals;
import Objs.LinearRegressions.LinearRegression;
import Objs.LinearRegressions.Point;
import Screens.LinearRegressions.ExamineLinearRegression;

public class DeletePoint {

    public static void Show(LinearRegression r){

        Generals.clearConsole();
        System.out.println("STATISTIKO - Borrar punto");
        System.out.println();
        System.out.println("0) Atras");
        System.out.println();
        System.out.println("Puntos: ");
        System.out.println();
        for (int i = 0; i < r.points.size(); i++) {
            System.out.println((i + 1) + ") " + Point.Stringify(r.points.get(i)));
        }
        System.out.println();
        int usrInput = Generals.getUserInputInt(": ");
        if(usrInput == 0){
            ExamineLinearRegression.Show(r);
        }else{
            if(usrInput > 0 && usrInput <= r.points.size()){
                ConfirmDeletePoint(r, r.points.get(usrInput - 1));
            }else{
                Generals.printCommandNotRecognized(String.valueOf(usrInput));
                Show(r);
            }
        }

    }

    public static void ConfirmDeletePoint(LinearRegression r, Point p){
        Generals.clearConsole();
        System.out.println("STATISTIKO - Confirm delete point");
        System.out.println();
        String usrInput = Generals.getUserInputString("Escriba 'y' para confirmar, otra cosa " +
                "para cancelar.");
        if(usrInput.equals("y")){
            r.points.remove(p);
            Data.serialize();
            Generals.pressEnterToContinue("El punto fue borrado con exito");
            Show(r);
        }else{
            Generals.pressEnterToContinue("Punto no borrado");
            Show(r);
        }
    }

}
