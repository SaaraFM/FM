
public class Conta {
    protected String titular;
    protected String numeroConta;
    protected String numeroAgencia;
    protected float saldo;
    protected String status;
    protected float percentual;

    public Conta(String titular, String numeroConta, String numeroAgencia, float saldo, float percentual) {
        this.titular = titular;
        this.numeroConta = numeroConta;
        this.numeroAgencia = numeroAgencia;
        this.saldo = saldo;
        this.percentual = percentual;
        alteraStatus();
    }

    public void saque(float valor) {
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
            alteraStatus();
            atualizar();
        }
    }

    public void deposito(float valor) {
        if (valor > 0) {
            saldo += valor;
            alteraStatus();
            atualizar();
        }
    }

    public void alteraStatus() {
        status = saldo >= 0 ? "positivo" : "negativo";
    }

    public void atualizar() {
        saldo *= percentual;
    }

    public void transferePara(Conta destino, float valor) {
        if (valor > 0 && saldo >= valor) {
            this.saque(valor);
            destino.deposito(valor);
        }
    }

    @Override
    public String toString() {
        return "Conta{" +
                "titular='" + titular + "'" +
                ", numeroConta='" + numeroConta + "'" +
                ", numeroAgencia='" + numeroAgencia + "'" +
                ", saldo=" + saldo +
                ", status='" + status + "'" +
                '}';
    }
}
