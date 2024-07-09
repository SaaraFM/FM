import java.time.LocalDate;

public class Funcionario {
    private String nome;
    private String matricula;
    private double salario;
    private LocalDate dataAdmissao;
    private String cpf;

    public Funcionario(String nome, String matricula, double salario, LocalDate dataAdmissao, String cpf) {
        this.nome = nome;
        this.matricula = matricula;
        this.salario = salario;
        this.dataAdmissao = dataAdmissao;
        this.cpf = cpf;
    }

    public void receberAumento(double aumento) {
        this.salario += aumento;
    }

    public double calcularGanhoBrutoAnual() {
        return this.salario * 12;
    }

    public double calcularImposto() {
        double impostoINSS = this.salario * 0.11;
        double impostoIR = 0;
        if (this.salario > 2500) {
            impostoIR = (this.salario - 2500) * 0.175;
        }
        return (impostoINSS + impostoIR) * 12;
    }

    public double calcularGanhoLiquidoMensal() {
        double impostoINSS = this.salario * 0.11;
        double impostoIR = 0;
        if (this.salario > 2500) {
            impostoIR = (this.salario - 2500) * 0.175;
        }
        return this.salario - (impostoINSS + impostoIR);
    }

    public double calcularGanhoLiquidoAnual() {
        return calcularGanhoBrutoAnual() - calcularImposto();
    }
}
