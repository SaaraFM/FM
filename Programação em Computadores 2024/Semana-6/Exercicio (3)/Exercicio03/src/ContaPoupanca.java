
public class ContaPoupanca extends Conta {
    public ContaPoupanca(String titular, String numeroConta, String numeroAgencia, float saldo) {
        super(titular, numeroConta, numeroAgencia, saldo, 1.005f);
    }
}
