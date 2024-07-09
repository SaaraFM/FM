import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Aluno aluno = new Aluno();

        System.out.println("Digite o nome do aluno:");
        aluno.nome = scanner.nextLine();

        System.out.println("Digite a nota do primeiro trimestre (vale até 30):");
        aluno.nota1 = scanner.nextDouble();

        System.out.println("Digite a nota do segundo trimestre (vale até 35):");
        aluno.nota2 = scanner.nextDouble();

        System.out.println("Digite a nota do terceiro trimestre (vale até 35):");
        aluno.nota3 = scanner.nextDouble();

        System.out.printf("NOTA FINAL = %.2f\n", aluno.notaFinal());

        if (aluno.estaAprovado()) {
            System.out.println("PASSOU");
        } else {
            System.out.println("NÃO PASSOU");
            System.out.printf("FALTOU %.2f PONTOS\n", aluno.pontosFaltantes());
        }

        scanner.close();
    }
}
