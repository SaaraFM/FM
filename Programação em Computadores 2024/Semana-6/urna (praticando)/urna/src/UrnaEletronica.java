public class UrnaEletronica {
    private int votosBrancos;
    private int votosNulos;
    private int[] votosCandidatos;
    private boolean apuracaoConcluida;

    public UrnaEletronica(int numeroDeCandidatos) {
        votosBrancos = 0;
        votosNulos = 0;
        votosCandidatos = new int[numeroDeCandidatos];
        apuracaoConcluida = false;
    }

    public void votar(int numeroCandidato) {
        if (apuracaoConcluida) {
            throw new IllegalStateException("A apuração já foi concluída. Não são permitidos mais votos.");
        }
        if (numeroCandidato >= 0 && numeroCandidato < votosCandidatos.length) {
            votosCandidatos[numeroCandidato]++;
        } else {
            throw new IllegalArgumentException("Número de candidato inválido.");
        }
    }

    public void votarEmBranco() {
        if (apuracaoConcluida) {
            throw new IllegalStateException("A apuração já foi concluída. Não são permitidos mais votos.");
        }
        votosBrancos++;
    }

    public void anularVoto() {
        if (apuracaoConcluida) {
            throw new IllegalStateException("A apuração já foi concluída. Não são permitidos mais votos.");
        }
        votosNulos++;
    }

    public void apurarEleicao() {
        apuracaoConcluida = true;
        System.out.println("Apuração dos votos:");
        System.out.println("Votos Brancos: " + votosBrancos);
        System.out.println("Votos Nulos: " + votosNulos);
        for (int i = 0; i < votosCandidatos.length; i++) {
            System.out.println("Candidato " + i + ": " + votosCandidatos[i] + " votos");
        }
    }
}
