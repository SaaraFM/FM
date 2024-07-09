
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double[][] matriz = new double[3][6];
        System.out.println("Digite os elementos da matriz 3x6:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                matriz[i][j] = input.nextDouble();
            }
        }
        double somaColunasImpares = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j += 2) {
                somaColunasImpares += matriz[i][j];
            }
        }
        System.out.println("Soma dos elementos das colunas ímpares: " + somaColunasImpares);
        
        double somaSegundaQuartaColuna = 0;
        for (int i = 0; i < 3; i++) {
            somaSegundaQuartaColuna += matriz[i][1] + matriz[i][3];
        }
        System.out.println("Média dos elementos da segunda e quarta colunas: " + (somaSegundaQuartaColuna / 6));
        
        for (int i = 0; i < 3; i++) {
            matriz[i][5] = matriz[i][0] + matriz[i][1];
        }
        System.out.println("Matriz modificada:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
}
