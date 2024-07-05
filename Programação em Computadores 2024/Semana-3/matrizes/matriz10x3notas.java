
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double[][] notas = new double[10][3];
        System.out.println("Digite as notas de 10 alunos em 3 provas:");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 3; j++) {
                notas[i][j] = input.nextDouble();
            }
        }
        int[] piorNota = new int[3];
        for (int i = 0; i < 10; i++) {
            double menorNota = notas[i][0];
            int indiceMenorNota = 0;
            for (int j = 1; j < 3; j++) {
                if (notas[i][j] < menorNota) {
                    menorNota = notas[i][j];
                    indiceMenorNota = j;
                }
            }
            piorNota[indiceMenorNota]++;
        }
        System.out.println("Número de alunos com pior nota na prova 1: " + piorNota[0]);
        System.out.println("Número de alunos com pior nota na prova 2: " + piorNota[1]);
        System.out.println("Número de alunos com pior nota na prova 3: " + piorNota[2]);
    }
}
