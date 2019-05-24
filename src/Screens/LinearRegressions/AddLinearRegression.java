package Screens.LinearRegressions;

import Data.Data;
import Generals.Generals;
import Objs.LinearRegressions.LinearRegression;
import com.sun.org.apache.bcel.internal.generic.DADD;
import com.sun.org.apache.bcel.internal.generic.GETFIELD;

public class AddLinearRegression {

    public static void Show(){

        Generals.clearConsole();
        System.out.println("STATISTIKO - Agregar Regresion Lineal");
        System.out.println();
        String nombre = Generals.getUserInputString("NOMBRE: ");
        String description = Generals.getUserInputString("DESCRIPCION: ");
        String yVarName = Generals.getUserInputString("NOMBRE DE Y: ");
        String xVarName = Generals.getUserInputString("NOMBRE DE X: ");
        LinearRegression lr = new LinearRegression(nombre, description, yVarName, xVarName);
        System.out.println();
        Data.linearRegressions.add(lr);
        Data.serialize();
        Generals.pressEnterToContinue("Regresion Agregada.");
        MenuLinearRegression.Show();

    }

}
