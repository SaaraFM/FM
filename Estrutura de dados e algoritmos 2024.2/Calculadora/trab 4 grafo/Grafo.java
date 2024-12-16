import java.util.Arrays;

public class Grafo {
    private int[][] matrizConexoes;
    private int quantidadeNos;

    public Grafo(int tamanhoInicial) {
        this.quantidadeNos = tamanhoInicial;
        this.matrizConexoes = new int[tamanhoInicial][tamanhoInicial];
    }

    public void conectar(int pontoA, int pontoB) {
        verificarNosValidos(pontoA, pontoB);
        matrizConexoes[pontoA][pontoB] = 1;
        matrizConexoes[pontoB][pontoA] = 1;
    }

    public void desconectar(int pontoA, int pontoB) {
        verificarNosValidos(pontoA, pontoB);
        matrizConexoes[pontoA][pontoB] = 0;
        matrizConexoes[pontoB][pontoA] = 0;
    }

    public void adicionarNo() {
        quantidadeNos++;
        int[][] novaMatriz = new int[quantidadeNos][quantidadeNos];

        for (int i = 0; i < quantidadeNos - 1; i++) {
            novaMatriz[i] = Arrays.copyOf(matrizConexoes[i], quantidadeNos);
        }

        matrizConexoes = novaMatriz;
    }

    public void removerNo(int indice) {
        validarNoExistente(indice);
        quantidadeNos--;

        int[][] novaMatriz = new int[quantidadeNos][quantidadeNos];
        for (int i = 0, novaLinha = 0; i < quantidadeNos + 1; i++) {
            if (i == indice)
                continue;
            for (int j = 0, novaColuna = 0; j < quantidadeNos + 1; j++) {
                if (j == indice)
                    continue;
                novaMatriz[novaLinha][novaColuna++] = matrizConexoes[i][j];
            }
            novaLinha++;
        }

        matrizConexoes = novaMatriz;
    }

    public void exibirConexoes() {
        for (int[] linha : matrizConexoes) {
            for (int conexao : linha) {
                System.out.print(conexao + " ");
            }
            System.out.println();
        }
    }

    public int[][] obterMatriz() {
        return matrizConexoes;
    }

    public int obterTotalNos() {
        return quantidadeNos;
    }

    private void verificarNosValidos(int a, int b) {
        if (a < 0 || b < 0 || a >= quantidadeNos || b >= quantidadeNos) {
            throw new IllegalArgumentException("Os nós indicados não são válidos.");
        }
    }

    private void validarNoExistente(int no) {
        if (no < 0 || no >= quantidadeNos) {
            throw new IllegalArgumentException("O nó indicado não existe no grafo.");
        }
    }
}
