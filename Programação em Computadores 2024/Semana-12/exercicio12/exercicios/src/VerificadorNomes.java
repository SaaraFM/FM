
import java.io.*;
import java.util.*;

public class VerificadorNomes {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            List<String> nomes = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader("nomes.txt"))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    nomes.add(linha);
                }
            } catch (IOException e) {
                System.err.println("Erro ao ler do arquivo: " + e.getMessage());
            }

            System.out.println("Digite um nome para verificar (digite SAIR para encerrar):");
            while (true) {
                String nome = scanner.nextLine();
                if (nome.equalsIgnoreCase("SAIR")) {
                    break;
                }
                if (nomes.contains(nome)) {
                    System.out.println("Nome já cadastrado");
                } else {
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter("nomes.txt", true))) {
                        writer.write(nome);
                        writer.newLine();
                        nomes.add(nome);
                    } catch (IOException e) {
                        System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
                    }
                    System.out.println("Nome armazenado");
                }
            }
        }
    }
}
