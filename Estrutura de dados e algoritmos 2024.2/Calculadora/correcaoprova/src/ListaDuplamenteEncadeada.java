public class ListaDuplamenteEncadeada {
    Nodo inicio;
    Nodo ultimo;

    // Classe interna Nodo
    private class Nodo {
        int dado;
        Nodo proximo;
    }

    // Construtor
    public ListaDuplamenteEncadeada() {
        inicio = null;
        ultimo = null;
    }

    // Método para inserir um valor no final da lista
    public void inserirNoFim(int valor) {
        Nodo novo = new Nodo();
        novo.dado = valor;
        novo.proximo = null;

        if (inicio == null) {  // Se a lista estiver vazia
            inicio = novo;
            ultimo = novo;
        } else {
            ultimo.proximo = novo;
            ultimo = novo;
        }
    }

    // Método para imprimir a lista
    public void imprimirLista() {
        Nodo atual = inicio;
        while (atual != null) {
            System.out.print(atual.dado + " ");
            atual = atual.proximo;
        }
        System.out.println();
    }

    // Método obterNodoNaPosicao (como fornecido anteriormente)
    private Nodo obterNodoNaPosicao(int posicao) {
        Nodo atual = inicio;
        for (int i = 0; atual != null && i < posicao; i++) {
            atual = atual.proximo;
        }
        return atual;
    }

    // Método trocarValores (como fornecido anteriormente)
    public void trocarValores(int posicao1, int posicao2) {
        if (posicao1 == posicao2) return;

        Nodo nodo1 = obterNodoNaPosicao(posicao1);
        Nodo nodo2 = obterNodoNaPosicao(posicao2);

        if (nodo1 != null && nodo2 != null) {
            int temp = nodo1.dado;
            nodo1.dado = nodo2.dado;
            nodo2.dado = temp;
        }
    }

    // Método somaMaiorMenor (como fornecido anteriormente)
    @SuppressWarnings("null")
    public int somaMaiorMenor() {
        if (inicio == null) return (Integer) null;
        
    
        int maior = inicio.dado;
        int menor = inicio.dado;
        Nodo atual = inicio;
    
        // Percorre a lista
        while (atual != null) {
            if (atual.dado > maior) {
                maior = atual.dado;
            }
            if (atual.dado < menor) {
                menor = atual.dado;
            }
            atual = atual.proximo;
        }
    
        return maior + menor;
    }
}


