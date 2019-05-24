package Generals;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Generals {

    public static int getUserInputInt(String text){
        int usrInput = 0;
        Scanner sc = new Scanner(System.in);
        System.out.print(text);
        try{
            usrInput = sc.nextInt();
            return usrInput;
        }catch(InputMismatchException e){
            System.out.println("Debe insertar un entero valido...");
            return getUserInputInt(text);
        }
    }

    public static float getUserInputFloat(String text){
        float usrInput = 0;
        Scanner sc = new Scanner(System.in);
        System.out.print(text);
        try{
            usrInput = sc.nextFloat();
            return usrInput;
        }catch(InputMismatchException e){
            System.out.println("Debe insertar un numero valido...");
            return getUserInputFloat(text);
        }
    }

    public static String getUserInputString(String textQuery){
        System.out.print(textQuery);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static void printCommandNotRecognized(String command){
        clearConsole();
        System.out.println("The command '" + command + "' was not recognized, please try again...");
        try{
            System.in.read();
        }catch(IOException e){
            System.out.println("ERROR: Could not handle System.in.read()!");
        }
    }

    public static void pressEnterToContinue(String infoText){
        System.out.print(infoText);
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }

    public static String generateRandomCode(int length){
        char[] availChars = new char[]
                {
                        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
                        't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
                };
        char[] code = new char[length];
        for(int i = 0 ; i < length ; i++){
            code[i] = availChars[(int)Math.floor(Math.random() * availChars.length)];
        }
        return String.valueOf(code);
    }

    public final static void clearConsole() {
        //Clears Screen in java
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                System.out.print("\033[H\033[2J");
            System.out.flush();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final static void printMenuSelection(String[] items, int beginning){
        System.out.println();
        for(int i = beginning ; i < items.length + beginning ; i++){
            System.out.println(i + ") " + items[i - beginning]);
        }
        System.out.println();
    }

    public final static void printDataSeries(String[][] items){
        System.out.println();
        for (String[] item : items) {
            for (String s : item) {
                System.out.println(s);
            }
        }
        System.out.println();
    }

}
