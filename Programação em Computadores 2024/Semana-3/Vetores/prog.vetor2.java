
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numeros = new int[10];
        int contador = 0;
        
        while (contador < 10) {
            System.out.println("Digite um número:");
            int numero = scanner.nextInt();
            
            boolean existe = false;
            for (int i = 0; i < contador; i++) {
                if (numeros[i] == numero) {
                    existe = true;
                    break;
                }
            }
            
            if (!existe) {
                numeros[contador] = numero;
                contador++;
            } else {
                System.out.println("Digite outro número.");
            }
        }
        
        System.out.println("Vetor final:");
        for (int valor : numeros) {
            System.out.println(valor);
        }
    }
}
