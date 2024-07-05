import java.util.Scanner;

public class Exercicio8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o preço do produto: ");
        double preco = scanner.nextDouble();
        System.out.print("Digite o código de pagamento: ");
        int codigo = scanner.nextInt();

        double precoFinal = 0;
        switch (codigo) {
            case 1:
                precoFinal = preco * 0.9;
                break;
            case 2:
                precoFinal = preco * 0.85;
                break;
            case 3:
                precoFinal = preco;
                break;
            case 4:
                precoFinal = preco * 1.1;
                break;
            default:
                System.out.println("Código inválido.");
        }

        System.out.println("O preço a ser pago é: " + precoFinal);
        scanner.close();
    }
}