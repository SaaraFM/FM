import java.util.Scanner;

public class Fatorial {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o primeiro número: ");
        int numero1 = scanner.nextInt();
        System.out.println("Digite o segundo número: ");
        int numero2 = scanner.nextInt();

        long fatorial1 = 1;
        for (int i = 1; i <= numero1; i++) {
            fatorial1 *= i;
        }

        long fatorial2 = 1;
        for (int i = 1; i <= numero2; i++) {
            fatorial2 *= i;
        }

        System.out.println("Fatorial do primeiro número: " + fatorial1);
        System.out.println("Fatorial do segundo número: " + fatorial2);
    }
}
