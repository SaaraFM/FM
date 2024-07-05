import java.util.Scanner;

public class Exercicio3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o valor de A: ");
        int a = scanner.nextInt();
        System.out.print("Digite o valor de B: ");
        int b = scanner.nextInt();

        int c;
        if (a == b) {
            c = a + b;
        } else {
            c = a * b;
        }

        System.out.println("O resultado é: " + c);
        scanner.close();
    }
}
