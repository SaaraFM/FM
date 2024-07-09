
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[][] matriz = new int[5][5];
        System.out.println("Digite os elementos da matriz 5x5:");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matriz[i][j] = input.nextInt();
            }
        }
        System.out.println("Digite o valor a ser buscado:");
        int x = input.nextInt();
        boolean encontrado = false;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matriz[i][j] == x) {
                    System.out.println("Valor encontrado na linha " + (i + 1) + ", coluna " + (j + 1));
                    encontrado = true;
                }
            }
        }
        if (!encontrado) {
            System.out.println("Não encontrado.");
        }
    }
}
