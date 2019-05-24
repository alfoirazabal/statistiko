package Screens;

import Generals.Generals;
import Screens.LinearRegressions.MenuLinearRegression;
import Screens.NormalDistributions.MenuNormDist;

public class MenuMain {

    private static boolean firstTime = true;

    public static void Show(){

        Generals.clearConsole();
        if(firstTime){
            System.out.println("BIENVENIDO A STATISTIKO!");
            System.out.println("By Alfo.");
            System.out.println();
            Generals.pressEnterToContinue("Press enter to continue...");
            firstTime = false;
            Generals.clearConsole();
        }
        System.out.println("STATISTIKO");
        String[] menuItems = {
                "Salir",
                "Analizador de Estadísticas de Distribuciones Normales",
                "Analizador de Regresiones Lineales"
        };
        Generals.printMenuSelection(menuItems, 0);
        int usrInput = Generals.getUserInputInt(": ");
        switch (usrInput){
            case 0:
                System.exit(0);
                break;
            case 1:
                MenuNormDist.Show();
                break;
            case 2:
                MenuLinearRegression.Show();
                break;
            default:
                Generals.printCommandNotRecognized(String.valueOf(usrInput));
                MenuMain.Show();
        }

    }
}
