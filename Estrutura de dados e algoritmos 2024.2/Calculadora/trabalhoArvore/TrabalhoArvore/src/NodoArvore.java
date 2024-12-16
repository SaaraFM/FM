import java.util.*;

class NodoArvore {
    int valor;
    NodoArvore esquerda;
    NodoArvore direita;

    public NodoArvore(int valor) {
        this.valor = valor;
        this.esquerda = null;
        this.direita = null;
    }

    public void inserir(int valor) {
        if (valor < this.valor) {
            if (this.esquerda == null) {
                this.esquerda = new NodoArvore(valor);
            } else {
                this.esquerda.inserir(valor);
            }
        } else {
            if (this.direita == null) {
                this.direita = new NodoArvore(valor);
            } else {
                this.direita.inserir(valor);
            }
        }
    }

    @SuppressWarnings("unused")
    public void inverterSubarvores() {
        if (this == null)
            return;
        NodoArvore temp = this.esquerda;
        this.esquerda = this.direita;
        this.direita = temp;
        if (this.esquerda != null)
            this.esquerda.inverterSubarvores();
        if (this.direita != null)
            this.direita.inverterSubarvores();
    }

    public boolean comparar(NodoArvore outra) {
        if (outra == null)
            return false;
        if (this.valor != outra.valor)
            return false;
        boolean esquerdaIgual = (this.esquerda == null && outra.esquerda == null) ||
                (this.esquerda != null && this.esquerda.comparar(outra.esquerda));
        boolean direitaIgual = (this.direita == null && outra.direita == null) ||
                (this.direita != null && this.direita.comparar(outra.direita));
        return esquerdaIgual && direitaIgual;
    }

    public void percursoSimetrico(List<Integer> lista) {
        if (this.esquerda != null)
            this.esquerda.percursoSimetrico(lista);
        lista.add(this.valor);
        if (this.direita != null)
            this.direita.percursoSimetrico(lista);
    }

    public void mostrarEstrutura(String prefixo, boolean isEsquerda) {
        if (this.direita != null) {
            this.direita.mostrarEstrutura(prefixo + (isEsquerda ? "│   " : "    "), false);
        }
        System.out.println(prefixo + (isEsquerda ? "└── " : "┌── ") + this.valor);
        if (this.esquerda != null) {
            this.esquerda.mostrarEstrutura(prefixo + (isEsquerda ? "    " : "│   "), true);
        }
    }
}