import java.util.Scanner;

public class Exercicio2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o sexo (M/F): ");
        char sexo = scanner.next().charAt(0);
        System.out.print("Digite o estado civil (CASADA/SOLTEIRA): ");
        String estadoCivil = scanner.next();

        if (sexo == 'F' && estadoCivil.equalsIgnoreCase("CASADA")) {
            System.out.print("Digite o tempo de casada (anos): ");
            int tempoCasada = scanner.nextInt();
        }

        scanner.close();
    }
}
