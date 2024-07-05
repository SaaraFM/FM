import java.util.Scanner;

public class MediaAritmetica {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double soma = 0;

        for (int i = 1; i <= 10; i++) {
            System.out.println("Digite o " + i + "º número: ");
            double numero = scanner.nextDouble();
            soma += numero;
        }

        double media = soma / 10;
        System.out.println("A média aritmética é: " + media);
    }
}
