class ListaDuplamenteEncadeada {
    class NodoLista {
        int valor;
        NodoLista proximo;
        NodoLista anterior;

        public NodoLista(int valor) {
            this.valor = valor;
            this.proximo = null;
            this.anterior = null;
        }
    }

    private NodoLista cabeca;
    private NodoLista cauda;

    public ListaDuplamenteEncadeada() {
        this.cabeca = null;
        this.cauda = null;
    }

    public void adicionar(int valor) {
        NodoLista novoNodo = new NodoLista(valor);
        if (this.cabeca == null) {
            this.cabeca = novoNodo;
            this.cauda = novoNodo;
        } else {
            this.cauda.proximo = novoNodo;
            novoNodo.anterior = this.cauda;
            this.cauda = novoNodo;
        }
    }

    public void mostrar() {
        NodoLista atual = this.cabeca;
        while (atual != null) {
            System.out.print(atual.valor + (atual.proximo != null ? " <-> " : ""));
            atual = atual.proximo;
        }
        System.out.println();
    }
}