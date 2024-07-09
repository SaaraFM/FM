import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Empresa empresa = new Empresa();

        System.out.print("Informe o número de funcionários: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("Funcionário " + (i + 1) + ":");
            System.out.print("ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Nome: ");
            String nome = sc.nextLine();
            System.out.print("Salário: ");
            double salario = sc.nextDouble();

            Funcionario funcionario = new Funcionario(id, nome, salario);
            if (!empresa.adicionarFuncionario(funcionario)) {
                System.out.println("ID repetido. Funcionário não adicionado.");
            }
        }

        System.out.print("Informe o ID do funcionário para aumentar o salário: ");
        int id = sc.nextInt();
        System.out.print("Informe a porcentagem de aumento: ");
        double percentual = sc.nextDouble();

        if (empresa.aumentarSalarioFuncionario(id, percentual)) {
            System.out.println("Salário atualizado com sucesso.");
        } else {
            System.out.println("Funcionário não encontrado.");
        }

        System.out.println("Lista de funcionários atualizada:");
        empresa.listarFuncionarios();

        sc.close();
    }
}
