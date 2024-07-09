import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Retangulo retangulo = new Retangulo();

        System.out.println("Informe a largura e a altura do retângulo:");
        retangulo.largura = scanner.nextDouble();
        retangulo.altura = scanner.nextDouble();

        System.out.printf("ÁREA = %.2f\n", retangulo.area());
        System.out.printf("PERÍMETRO = %.2f\n", retangulo.perimetro());
        System.out.printf("DIAGONAL = %.2f\n", retangulo.diagonal());

        scanner.close();
    }
}
