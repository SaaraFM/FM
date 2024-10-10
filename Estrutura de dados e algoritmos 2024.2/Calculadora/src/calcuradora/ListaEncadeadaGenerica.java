package calcuradora;
public class ListaEncadeadaGenerica<Item> {
    private class Nodo {
        Item dado;
        Nodo proximo;

        Nodo(Item dado) {
            this.dado = dado;
            this.proximo = null;
        }
    }

    private Nodo topo;

    public ListaEncadeadaGenerica() {
        topo = null;
    }

    public boolean estaVazia() {
        return topo == null;
    }

    public void inserirNoTopo(Item dado) {
        Nodo novoNo = new Nodo(dado);
        novoNo.proximo = topo;
        topo = novoNo;
    }

    public Item removerDoTopo() {
        if (estaVazia()) {
            return null;
        }
        Item dado = topo.dado;
        topo = topo.proximo;
        return dado;
    }

    public Item espiar() {
        return estaVazia() ? null : topo.dado;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Nodo atual = topo;
        while (atual != null) {
            sb.append(atual.dado).append(" ");
            atual = atual.proximo;
        }
        return sb.toString();
    }
}