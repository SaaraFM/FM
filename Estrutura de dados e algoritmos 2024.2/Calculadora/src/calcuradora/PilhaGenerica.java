package calcuradora;
public class PilhaGenerica<Item> {
    private ListaEncadeadaGenerica<Item> lista;

    public PilhaGenerica() {
        lista = new ListaEncadeadaGenerica<>();
    }

    public void empilhar(Item dado) {
        lista.inserirNoTopo(dado);
    }

    public Item desempilhar() {
        return lista.removerDoTopo();
    }

    public Item espiar() {
        return lista.espiar();
    }

    public boolean estaVazia() {
        return lista.estaVazia();
    }

    @Override
    public String toString() {
        return lista.toString();
    }
}