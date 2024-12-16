import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestaArvoreBinaria {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        NodoArvore arvore = null;

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Inserir valores na árvore");
            System.out.println("2. Inverter subárvores");
            System.out.println("3. Comparar duas árvores");
            System.out.println("4. Converter para lista duplamente encadeada");
            System.out.println("5. Mostrar estrutura da árvore");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite os valores para a árvore, separados por espaço: ");
                    scanner.nextLine();
                    String[] valores = scanner.nextLine().split(" ");
                    for (String v : valores) {
                        int valor = Integer.parseInt(v);
                        if (arvore == null) {
                            arvore = new NodoArvore(valor);
                        } else {
                            arvore.inserir(valor);
                        }
                    }
                    System.out.println("Árvore criada com sucesso.");
                    break;

                case 2:
                    if (arvore != null) {
                        System.out.println("Estrutura antes da inversão:");
                        arvore.mostrarEstrutura("", false);
                        arvore.inverterSubarvores();
                        System.out.println("Estrutura após a inversão:");
                        arvore.mostrarEstrutura("", false);
                    } else {
                        System.out.println("A árvore está vazia.");
                    }
                    break;

                case 3:
                    if (arvore != null) {
                        System.out.print(
                                "Digite os valores para a segunda árvore, separados por espaço e depois que terminar aperte enter: ");
                        scanner.nextLine();
                        String[] valores2 = scanner.nextLine().split(" ");
                        NodoArvore outraArvore = null;
                        for (String v : valores2) {
                            int valor = Integer.parseInt(v);
                            if (outraArvore == null) {
                                outraArvore = new NodoArvore(valor);
                            } else {
                                outraArvore.inserir(valor);
                            }
                        }
                        System.out.println("Estrutura da primeira árvore:");
                        arvore.mostrarEstrutura("", false);
                        System.out.println("Estrutura da segunda árvore:");
                        outraArvore.mostrarEstrutura("", false);
                        if (arvore.comparar(outraArvore)) {
                            System.out.println("As árvores são iguais.");
                        } else {
                            System.out.println("As árvores são diferentes.");
                        }
                    } else {
                        System.out.println("A árvore está vazia.");
                    }
                    break;

                case 4:
                    if (arvore != null) {
                        ListaDuplamenteEncadeada lista = new ListaDuplamenteEncadeada();
                        List<Integer> elementos = new ArrayList<>();
                        arvore.percursoSimetrico(elementos);
                        for (int elemento : elementos) {
                            lista.adicionar(elemento);
                        }
                        System.out.println("Lista duplamente encadeada gerada:");
                        lista.mostrar();
                    } else {
                        System.out.println("A árvore está vazia.");
                    }
                    break;

                case 5:
                    if (arvore != null) {
                        arvore.mostrarEstrutura("", false);
                    } else {
                        System.out.println("A árvore está vazia.");
                    }
                    break;

                case 6:
                    System.out.println("Encerrando o programa.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
