import java.util.Scanner;

public class Exercicio9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o número de identificação do aluno: ");
        int id = scanner.nextInt();
        System.out.print("Digite a primeira nota: ");
        double nota1 = scanner.nextDouble();
        System.out.print("Digite a segunda nota: ");
        double nota2 = scanner.nextDouble();
        System.out.print("Digite a terceira nota: ");
        double nota3 = scanner.nextDouble();
        System.out.print("Digite a média dos exercícios: ");
        double mediaExercicios = scanner.nextDouble();

        double mediaAproveitamento = (nota1 + nota2 * 2 + nota3 * 3 + mediaExercicios) / 7;

        char conceito;
        if (mediaAproveitamento >= 90) {
            conceito = 'A';
        } else if (mediaAproveitamento >= 75) {
            conceito = 'B';
        } else if (mediaAproveitamento >= 60) {
            conceito = 'C';
        } else if (mediaAproveitamento >= 40) {
            conceito = 'D';
        } else {
            conceito = 'E';
        }

        System.out.println("Número do aluno: " + id);
        System.out.println("Notas: " + nota1 + ", " + nota2 + ", " + nota3);
        System.out.println("Média dos exercícios: " + mediaExercicios);
        System.out.println("Média de aproveitamento: " + mediaAproveitamento);
        System.out.println("Conceito: " + conceito);

        if (conceito == 'A' || conceito == 'B' || conceito == 'C') {
            System.out.println("Aprovado");
        } else {
            System.out.println("Reprovado");
        }

        scanner.close();
    }
}