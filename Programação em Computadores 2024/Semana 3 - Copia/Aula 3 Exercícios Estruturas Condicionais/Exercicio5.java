import java.util.Scanner;

public class Exercicio5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite um número: ");
        int numero = scanner.nextInt();

        int resultado;
        if (numero % 2 == 0) {
            resultado = numero * 5;
        } else {
            resultado = numero * 7;
        }

        System.out.println("O resultado é: " + resultado);
        scanner.close();
    }
}
