import java.util.LinkedList;

class ListaDuplamenteEncadeada {
    private LinkedList<Integer> lista;

    public ListaDuplamenteEncadeada() {
        this.lista = new LinkedList<>();
    }

    // Método para inserção ordenada-----------------------------------------------------
    public boolean insereOrdenado(Integer valor) {
        if (valor == null) {
            return false; 
        }

        if (lista.contains(valor)) {
            return false; 
        }

        int posicao = 0;
        while (posicao < lista.size() && lista.get(posicao) < valor) {
            posicao++;
        }
        lista.add(posicao, valor);
        return true;
    }

    // Retorna uma nova lista com elementos pares----------------------------------------
    public ListaDuplamenteEncadeada retornaListaPares() {
        ListaDuplamenteEncadeada sublista = new ListaDuplamenteEncadeada();
        for (Integer valor : lista) {
            if (valor % 2 == 0) {
                sublista.insereOrdenado(valor);
            }
        }
        return sublista;
    }

    // Mostra os valores da lista---------------------------------------------------------
    public void mostraLista() {
        if (lista.isEmpty()) {
            System.out.println("\nA lista está vazia.");
            return;
        }

        System.out.print("\nLista: ");
        for (Integer valor : lista) {
            System.out.print(valor + " ");
        }
        System.out.println();
    }
}