package Screens.LinearRegressions.Points;

import Data.Data;
import Generals.Generals;
import Objs.LinearRegressions.LinearRegression;
import Objs.LinearRegressions.Point;
import Screens.LinearRegressions.ExamineLinearRegression;

import java.util.ArrayList;
import java.util.List;

public class AddPoint {

    public static void Show(LinearRegression r){

        List<Point> points = new ArrayList<>();

        Generals.clearConsole();
        System.out.println("STATISTIKO - Agregando Punto");
        System.out.println();
        boolean keepAdding = true;
        while(keepAdding){
            String pointName = Generals.getUserInputString("NOMBRE: ");
            float x = Generals.getUserInputFloat("X: ");
            float y = Generals.getUserInputFloat("Y: ");
            Point p = new Point(pointName, x, y);
            r.points.add(p);
            String keepAddingQuestion = Generals.getUserInputString("Para seguir escriba 'y', de lo " +
                    "contrario escriba otra cosa: ");
            if(!keepAddingQuestion.toLowerCase().equals("y")){
                keepAdding = false;
            }
        }
        System.out.println();
        Data.serialize();
        Generals.pressEnterToContinue("Datos guardados");
        ExamineLinearRegression.Show(r);

    }

}
