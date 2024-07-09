
import java.io.*;
import java.util.*;

public class GerenciadorNomes {
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        List<String> nomes = new ArrayList<>();
        String nome;

        System.out.println("Digite os nomes (digite SAIR para encerrar):");
        while (true) {
            nome = scanner.nextLine();
            if (nome.equalsIgnoreCase("SAIR")) {
                break;
            }
            nomes.add(nome);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("nomes.txt"))) {
            for (String n : nomes) {
                writer.write(n);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }

        System.out.println("Nomes gravados no arquivo. Lendo nomes do arquivo:");

        try (BufferedReader reader = new BufferedReader(new FileReader("nomes.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler do arquivo: " + e.getMessage());
        }
    }
}
