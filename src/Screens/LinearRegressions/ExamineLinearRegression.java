package Screens.LinearRegressions;

import Data.Data;
import Generals.Generals;
import Objs.LinearRegressions.Equation;
import Objs.LinearRegressions.LinearRegression;
import Objs.LinearRegressions.LinearRegressionData;
import Screens.LinearRegressions.Points.AddPoint;
import Screens.LinearRegressions.Points.DeletePoint;
import Screens.LinearRegressions.Points.ViewPoints;

public class ExamineLinearRegression {

    public static LinearRegression r;
    public static LinearRegressionData lrd = null;

    public static void Show(LinearRegression reg){

        r = reg;

        Generals.clearConsole();
        System.out.println("STATISTIKO - Examinando Regresion");
        System.out.println();
        try{
            lrd = new LinearRegressionData(r.points);
            System.out.println("EQ: " + Equation.Stringify(lrd.eq, 2));
            System.out.println("AVG X: " + lrd.avgX);
            System.out.println("AVG Y: " + lrd.avgY);
            System.out.println("RSQRD: " + lrd.rSqrd);
            System.out.println("SEE: " + lrd.see);
        }catch (LinearRegressionData.NotEnoughData ex){
            System.out.println(ex.toString());
        }
        String options[] = {
                "Atras",
                "Agregar Puntos",
                "Ver Puntos",
                "Obtener X: " + r.xVarName + " de Y: " + r.yVarName,
                "Obtener Y: " + r.yVarName + " de X: " + r.xVarName,
                "Borrar Puntos",
                "Borrar Regresion"
        };
        Generals.printMenuSelection(options, 0);
        System.out.println();
        int usrOption = Generals.getUserInputInt(": ");
        switch (usrOption){
            case 0:
                MenuLinearRegression.Show();
                break;
            case 1:
                AddPoint.Show(r);
                break;
            case 2:
                ViewPoints.Show(r);
                break;
            case 3:
                ShowGetXFromY();
                break;
            case 4:
                ShowGetYFromX();
                break;
            case 5:
                DeletePoint.Show(r);
                break;
            case 6:
                ConfirmDeleteRegression();
                break;
            default:
                Generals.printCommandNotRecognized(String.valueOf(usrOption));
                Show(r);
        }

    }

    public static void ConfirmDeleteRegression(){
        Generals.clearConsole();
        System.out.println("STATISTIKO - Confirmar borrado de Regresion");
        System.out.println();
        String usrConf = Generals.getUserInputString("Escriba 'y' para confirmar el " +
                "borrado, otra cosa para cancelar: ");
        if(usrConf.equals("y")){
            Data.linearRegressions.remove(r);
            Data.serialize();
            Generals.pressEnterToContinue("Se ha borrado la Regresion");
            MenuLinearRegression.Show();
        }else{
            Generals.pressEnterToContinue("El borrado ha sido cancelado");
            Show(r);
        }
    }

    public static void ShowGetXFromY(){
        Generals.clearConsole();
        System.out.println("STATISTIKO - Obtener X de Y");
        System.out.println();
        if(lrd != null){
            float valOfY = Generals.getUserInputFloat("Y(" + r.yVarName + "): ");
            float valOfX = lrd.eq.getXFromY(valOfY);
            System.out.println();
            System.out.println("X(" + r.xVarName + "): " + valOfX);
            System.out.println();
            Generals.pressEnterToContinue("Presione enter para continuar");
        }else{
            Generals.pressEnterToContinue("Se necesitan más puntos.");
        }
        Show(r);
    }

    public static void ShowGetYFromX(){
        Generals.clearConsole();
        System.out.println("STATISTIKO - Obtener Y de X");
        System.out.println();
        if(lrd != null){
            float valOfX = Generals.getUserInputFloat("X(" + r.xVarName + "): ");
            float valOfY = lrd.eq.getYFromX(valOfX);
            System.out.println();
            System.out.println("Y(" + r.yVarName + "): " + valOfY);
            System.out.println();
            Generals.pressEnterToContinue("Presione enter para continuar");
        }else{
            Generals.pressEnterToContinue("Se necesitan más puntos.");
        }
        Show(r);
    }

}
