
public class Main {
    public static void main(String[] args) {
        int[] A = {1, 0, 5, -2, -5, 7};
        int soma = A[0] + A[1] + A[5];
        System.out.println(soma);
        
        A[4] = 100;
        
        for (int valor : A) {
            System.out.println(valor);
        }
    }
}
