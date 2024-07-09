public class Aluno {
    String nome;
    double nota1;
    double nota2;
    double nota3;

    public double notaFinal() {
        return nota1 + nota2 + nota3;
    }

    public boolean estaAprovado() {
        return notaFinal() >= 60;
    }

    public double pontosFaltantes() {
        if (estaAprovado()) {
            return 0;
        } else {
            return 60 - notaFinal();
        }
    }
}