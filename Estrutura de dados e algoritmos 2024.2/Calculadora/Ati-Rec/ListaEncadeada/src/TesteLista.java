import java.util.Scanner;

public class TesteLista {
    public static void main(String[] args) {
        ListaDuplamenteEncadeada lista = new ListaDuplamenteEncadeada();

        lista.insereOrdenado(2);
        lista.insereOrdenado(3);
        lista.insereOrdenado(9);
        lista.insereOrdenado(8);

        // Menu: inserir e mostrar listas---------------------------------------------
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n----------Menu----------");
            System.out.println("1 - Inserir valor");
            System.out.println("2 - Mostrar lista");
            System.out.println("3 - Mostrar números pares");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

        // Mensagens de retorno de insereOrdenado-------------------------------------
            switch (opcao) {
                case 1:
                    System.out.print("\nDigite um número para ser inserido a lista: ");
                    int valor = scanner.nextInt();
                    boolean inserido = lista.insereOrdenado(valor);

                    if (inserido) {
                        System.out.println("Número inserido na lista com sucesso.");
                    } else {
                        System.out.println("O número já existe na lista.");
                    }
                    break;
                case 2:
                    System.out.println("\nLista com todos os números:");
                    lista.mostraLista();
                    break;
                case 3:
                    System.out.println("\nSublista com números pares:");
                    ListaDuplamenteEncadeada pares = lista.retornaListaPares();
                    pares.mostraLista();
                    break;
                case 0:
                    System.out.println("\nSaindo...");
                    break;
                default:
                    System.out.println("\nOpção inválida!");
            }
        } while (opcao != 0);

        scanner.close();
    }
}