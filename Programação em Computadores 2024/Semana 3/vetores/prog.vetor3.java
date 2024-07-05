
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int[] vetor1 = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        int[] vetor2 = {2, 4, 6, 8, 10, 1, 3, 5, 7, 9};
        ArrayList<Integer> interseccao = new ArrayList<>();
        
        for (int i = 0; i < vetor1.length; i++) {
            for (int j = 0; j < vetor2.length; j++) {
                if (vetor1[i] == vetor2[j] && !interseccao.contains(vetor1[i])) {
                    interseccao.add(vetor1[i]);
                }
            }
        }
        
        System.out.println("Intersecção dos vetores:");
        for (int valor : interseccao) {
            System.out.println(valor);
        }
    }
}
