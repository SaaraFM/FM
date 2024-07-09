import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Empregado empregado = new Empregado();

        System.out.println("Digite o nome do empregado:");
        empregado.nome = scanner.nextLine();

        System.out.println("Digite o salário bruto:");
        empregado.salarioBruto = scanner.nextDouble();

        System.out.println("Digite o imposto:");
        empregado.imposto = scanner.nextDouble();

        System.out.printf("Empregado: %s, $ %.2f\n", empregado.nome, empregado.salarioLiquido());

        System.out.println("Qual a porcentagem para aumentar o salário?");
        double porcentagem = scanner.nextDouble();
        empregado.aumentarSalario(porcentagem);

        System.out.printf("Dados atualizados: %s, $ %.2f\n", empregado.nome, empregado.salarioLiquido());

        scanner.close();
    }
}
