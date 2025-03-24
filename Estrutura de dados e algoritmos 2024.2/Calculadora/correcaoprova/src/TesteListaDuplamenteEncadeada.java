public class TesteListaDuplamenteEncadeada {

    public static void main(String[] args) {
        ListaDuplamenteEncadeada lista = new ListaDuplamenteEncadeada();

        // Adicionando elementos manualmente à lista (valores 10, 20, 30, 40, 50)
        lista.inserirNoFim(5);
        lista.inserirNoFim(20);
        lista.inserirNoFim(30);
        lista.inserirNoFim(40);
        lista.inserirNoFim(50);

        // Testando somaMaiorMenor
        System.out.println("Soma do maior e menor valor: " + lista.somaMaiorMenor());  // Deve retornar 10 + 50 = 60

        // Testando trocarValores entre posições 1 (20) e 3 (40)
        System.out.println("Trocando valores nas posições 0 e 3");
        lista.trocarValores(0, 3);
        lista.imprimirLista();  // Deve imprimir 10, 40, 30, 20, 50 após a troca

        // Testando trocarValores entre posições inválidas
        System.out.println("Trocando valores nas posições 0 e 10 (inexistente)");
        lista.trocarValores(0, 10);
        lista.imprimirLista();  // A lista deve permanecer inalterada
    }
}
