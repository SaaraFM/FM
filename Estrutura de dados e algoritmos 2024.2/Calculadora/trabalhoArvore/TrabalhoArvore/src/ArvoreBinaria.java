import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
class NodoArvore {
    int valor;
    NodoArvore esquerda;
    NodoArvore direita;

    public NodoArvore(int valor) {
        this.valor = valor;
    }

    public void inserir(int novoValor) {
        if (novoValor < this.valor) {
            if (this.esquerda == null) {
                this.esquerda = new NodoArvore(novoValor);
            } else {
                this.esquerda.inserir(novoValor);
            }
        } else {
            if (this.direita == null) {
                this.direita = new NodoArvore(novoValor);
            } else {
                this.direita.inserir(novoValor);
            }
        }
    }

    public void inverterSubarvores() {
        NodoArvore temp = this.esquerda;
        this.esquerda = this.direita;
        this.direita = temp;
        if (this.esquerda != null) {
            this.esquerda.inverterSubarvores();
        }
        if (this.direita != null) {
            this.direita.inverterSubarvores();
        }
    }

    public boolean comparar(NodoArvore outraArvore) {
        if (outraArvore == null) {
            return false;
        }
        if (this.valor != outraArvore.valor) {
            return false;
        }
        boolean iguaisEsquerda = (this.esquerda == null && outraArvore.esquerda == null) ||
                                (this.esquerda != null && this.esquerda.comparar(outraArvore.esquerda));
        boolean iguaisDireita = (this.direita == null && outraArvore.direita == null) ||
                                (this.direita != null && this.direita.comparar(outraArvore.direita));
        return iguaisEsquerda && iguaisDireita;
    }

    public void percursoSimetrico(List<Integer> elementos) {
        if (this.esquerda != null) {
            this.esquerda.percursoSimetrico(elementos);
        }
        elementos.add(this.valor);
        if (this.direita != null) {
            this.direita.percursoSimetrico(elementos);
        }
    }

    public void mostrarEstrutura(String prefixo, boolean isDireita) {
        if (this.direita != null) {
            this.direita.mostrarEstrutura(prefixo + (isDireita ? "    " : "│   "), true);
        }
        System.out.println(prefixo + (isDireita ? "└── " : "┌── ") + this.valor);
        if (this.esquerda != null) {
            this.esquerda.mostrarEstrutura(prefixo + (isDireita ? "    " : "│   "), false);
        }
    }
}

class ListaDuplamenteEncadeada {
    static class NodoLista {
        int valor;
        NodoLista proximo;
        NodoLista anterior;

        NodoLista(int valor) {
            this.valor = valor;
        }
    }

    private NodoLista inicio;

    public void adicionar(int valor) {
        NodoLista novoNodo = new NodoLista(valor);
        if (inicio == null) {
            inicio = novoNodo;
        } else {
            NodoLista atual = inicio;
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            atual.proximo = novoNodo;
            novoNodo.anterior = atual;
        }
    }

    public void mostrar() {
        NodoLista atual = inicio;
        while (atual != null) {
            System.out.print(atual.valor + " ");
            atual = atual.proximo;
        }
        System.out.println();
    }
}
