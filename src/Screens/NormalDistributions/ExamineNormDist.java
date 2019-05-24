package Screens.NormalDistributions;

import Generals.Generals;
import Objs.NormalDistributions.Evaluation;
import Screens.NormalDistributions.NormalDistributions.DeleteNormalDistribution;
import Screens.NormalDistributions.NormalDistributions.Points.*;

public class ExamineNormDist {

    public static void Show(Evaluation e){

        Generals.clearConsole();
        System.out.println("STATISTIKO - Examine Normal Distribution: ");
        String[] evalData = new String[8];
        try{
            Evaluation.EvaluationData ed = e.getEvalData();
            evalData[0] = "PROMEDIO / MEDIA:        " + ed.average;
            evalData[1] = "MEDIANA:                 " + ed.median;
            evalData[2] = "MODA:                    " + ed.mode;
            evalData[3] = "VARIANZA:                " + ed.variance;
            evalData[4] = "DESV. EST. DE MUESTRA:   " + ed.stdDev;
            evalData[5] = "ASIMETRÍA DE PEARSONS:   " + ed.pearsonsSkewment;
            evalData[6] = "KURTOSIS:                " + ed.kurtosis;
            evalData[7] = "GINI:                    " + ed.gini;
        }catch(Evaluation.MorePointsAreNeeded eMPAN){
            evalData = new String[1];
            evalData[0] = "Necesito mas de 3 puntos para calcular datos...";
        }
        String[] nDistData = {
                ("NOMBRE: " + e.name),
                ("DESCRIPCION: " + e.description),
                ("VARIABLE A MEDIR: " + e.variableName),
                ("TOTAL DE PUNTOS: " + Evaluation.getTotalNumberOfPoints(e)),
                (""),
                ("--- DATOS DE LA EVALUACION ---"),
                ("")
        };
        String[][] toOutput = {nDistData, evalData};
        Generals.printDataSeries(toOutput);
        String[] options = {
                "Atras",
                "Ver",
                "Dibujar",
                "Agregar Puntos Individualmente",
                "Agregar Puntos Agrupados",
                "Borrar Grupo de Puntos",
                "Borrar Puntos de un Grupo",
                "Eliminar Evaluación"
        };
        Generals.printMenuSelection(options, 0);

        int usrInput = Generals.getUserInputInt(": ");
        switch (usrInput){
            case 0:
                MenuNormDist.Show();
                break;
            case 1:
                ViewDistributionPoints.Show(e);
                break;
            case 2:
                drawGraph(e);
                Show(e);
                break;
            case 3:
                AddPointIndividually.Show(e);
                break;
            case 4:
                AddPointGroup.Show(e);
                break;
            case 5:
                DeletePointGroup.Show(e);
                break;
            case 6:
                DeletePointsFromGroup.Show(e);
                break;
            case 7:
                DeleteNormalDistribution.Show(e);
                break;
            default:
                Generals.printCommandNotRecognized(String.valueOf(usrInput));
                ExamineNormDist.Show(e);
        }
    }

    public static void drawGraph(Evaluation e){
        Generals.clearConsole();
        System.out.println("STATISTIKO - Examinando Gráfica: " + e.name);
        System.out.println();
        Evaluation.drawStringedGraph(e, 30);
        System.out.println();
        Generals.pressEnterToContinue("Presione enter para continuar");
    }

}
