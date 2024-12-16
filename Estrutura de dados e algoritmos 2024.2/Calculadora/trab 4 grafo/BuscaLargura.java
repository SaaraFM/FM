import java.util.*;

public class BuscaLargura {
    private final Grafo estrutura;

    public BuscaLargura(Grafo estrutura) {
        this.estrutura = estrutura;
    }

    public List<Integer> explorar(int pontoInicial) {
        if (pontoInicial < 0 || pontoInicial >= estrutura.obterTotalNos()) {
            throw new IllegalArgumentException("Ponto inicial inválido.");
        }

        List<Integer> visitados = new ArrayList<>();
        boolean[] foiVisitado = new boolean[estrutura.obterTotalNos()];
        Queue<Integer> fila = new LinkedList<>();

        fila.add(pontoInicial);
        foiVisitado[pontoInicial] = true;

        while (!fila.isEmpty()) {
            int atual = fila.poll();
            visitados.add(atual);

            for (int i = 0; i < estrutura.obterTotalNos(); i++) {
                if (estrutura.obterMatriz()[atual][i] == 1 && !foiVisitado[i]) {
                    fila.add(i);
                    foiVisitado[i] = true;
                }
            }
        }

        return visitados;
    }
}
