
import java.io.*;

public class CopiadorNomes {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("nomes.txt"));
                BufferedWriter writer = new BufferedWriter(new FileWriter("nomes_copiados.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                writer.write(linha);
                writer.newLine();
            }
            System.out.println("Nomes copiados para nomes_copiados.txt");
        } catch (IOException e) {
            System.err.println("Erro ao copiar nomes: " + e.getMessage());
        }
    }
}
