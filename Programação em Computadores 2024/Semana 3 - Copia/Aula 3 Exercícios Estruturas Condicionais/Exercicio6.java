import java.util.Arrays;
import java.util.Scanner;

public class Exercicio6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] valores = new int[3];

        for (int i = 0; i < valores.length; i++) {
            System.out.print("Digite o " + (i + 1) + "º valor: ");
            valores[i] = scanner.nextInt();
        }

        Arrays.sort(valores);
        System.out.println("Valores em ordem decrescente: " + valores[2] + ", " + valores[1] + ", " + valores[0]);
        scanner.close();
    }
}
