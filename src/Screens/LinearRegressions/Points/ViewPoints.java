package Screens.LinearRegressions.Points;

import Generals.Generals;
import Objs.LinearRegressions.LinearRegression;
import Objs.LinearRegressions.Point;
import Screens.LinearRegressions.ExamineLinearRegression;

public class ViewPoints {

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
        Generals.pressEnterToContinue("Presione enter para continuar.");
        ExamineLinearRegression.Show(r);

    }

}
