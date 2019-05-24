package Screens.LinearRegressions;

import Data.Data;
import Generals.Generals;
import Objs.LinearRegressions.LinearRegression;
import Screens.MenuMain;
import Screens.NormalDistributions.ExamineNormDist;
import Screens.NormalDistributions.MenuNormDist;

public class MenuLinearRegression {

    public static void Show(){

        Generals.clearConsole();

        System.out.println("STATISTIKO - Regresiones Lineales");
        String[] menuItems = {
                "Atras",
                "Agregar Regresion"
        };
        Generals.printMenuSelection(menuItems, 0);
        for (int i = 2; i <= Data.linearRegressions.size() + 1; i++) {
            LinearRegression lr = Data.linearRegressions.get(i - 2);
            System.out.println(i + ") " + LinearRegression.Stringify(lr));
        }
        System.out.println();
        int usrInput = Generals.getUserInputInt(": ");
        switch (usrInput){
            case 0:
                MenuMain.Show();
                break;
            case 1:
                AddLinearRegression.Show();
                break;
            default:
                if(usrInput > 1 && usrInput < Data.linearRegressions.size() + 2){
                    ExamineLinearRegression.Show(Data.linearRegressions.get(usrInput - 2));
                }else{
                    Generals.printCommandNotRecognized(String.valueOf(usrInput));
                    MenuNormDist.Show();
                }
        }
    }

}
