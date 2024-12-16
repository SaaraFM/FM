import java.util.List;
import java.util.Scanner;

public class TestaGrafo {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Grafo grafoAtual = null;

        while (true) {
            System.out.println("\nSelecione uma ação:");
            System.out.println("1) Criar um grafo novo");
            System.out.println("2) Conectar dois nós");
            System.out.println("3) Desconectar dois nós");
            System.out.println("4) Adicionar novo nó");
            System.out.println("5) Remover um nó");
            System.out.println("6) Mostrar a matriz de conexões");
            System.out.println("7) Realizar busca em largura");
            System.out.println("8) Sair do programa");
            System.out.print("Opção escolhida: ");
            int escolha = entrada.nextInt();

            try {
                switch (escolha) {
                    case 1:
                        System.out.print("Quantos nós o grafo terá inicialmente? ");
                        int quantidade = entrada.nextInt();
                        grafoAtual = new Grafo(quantidade);
                        System.out.println("Novo grafo criado com sucesso!");
                        break;
                    case 2:
                        verificarGrafo(grafoAtual);
                        System.out.print("Informe o primeiro nó: ");
                        int noA = entrada.nextInt();
                        System.out.print("Informe o segundo nó: ");
                        int noB = entrada.nextInt();
                        grafoAtual.conectar(noA, noB);
                        System.out.println("Nós conectados com sucesso.");
                        break;
                    case 3:
                        verificarGrafo(grafoAtual);
                        System.out.print("Informe o primeiro nó: ");
                        noA = entrada.nextInt();
                        System.out.print("Informe o segundo nó: ");
                        noB = entrada.nextInt();
                        grafoAtual.desconectar(noA, noB);
                        System.out.println("Conexão removida com sucesso.");
                        break;
                    case 4:
                        verificarGrafo(grafoAtual);
                        grafoAtual.adicionarNo();
                        System.out.println("Novo nó adicionado.");
                        break;
                    case 5:
                        verificarGrafo(grafoAtual);
                        System.out.print("Qual nó você deseja remover? ");
                        int noRemovido = entrada.nextInt();
                        grafoAtual.removerNo(noRemovido);
                        System.out.println("Nó removido com sucesso.");
                        break;
                    case 6:
                        verificarGrafo(grafoAtual);
                        System.out.println("Exibindo a matriz de conexões:");
                        grafoAtual.exibirConexoes();
                        break;
                    case 7:
                        verificarGrafo(grafoAtual);
                        System.out.print("Informe o nó inicial para a busca: ");
                        int inicio = entrada.nextInt();
                        BuscaLargura busca = new BuscaLargura(grafoAtual);
                        List<Integer> resultado = busca.explorar(inicio);
                        System.out.println("Nós visitados na busca: " + resultado);
                        break;
                    case 8:
                        System.out.println("Encerrando o programa. Até logo!");
                        entrada.close();
                        return;
                    default:
                        System.out.println("Escolha inválida, tente novamente.");
                }
            } catch (Exception erro) {
                System.out.println("Erro: " + erro.getMessage());
            }
        }
    }

    private static void verificarGrafo(Grafo grafoAtual) {
        if (grafoAtual == null) {
            throw new IllegalStateException("O grafo ainda não foi criado.");
        }
    }
}
