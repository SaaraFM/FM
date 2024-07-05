import java.util.Scanner;

public class SomaMenoresQue40 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int soma = 0;

        for (int i = 1; i <= 10; i++) {
            System.out.println("Digite o " + i + "º número: ");
            int numero = scanner.nextInt();
            if (numero < 40) {
                soma += numero;
            }
        }

        System.out.println("A soma dos números menores que 40 é: " + soma);
    }
}
