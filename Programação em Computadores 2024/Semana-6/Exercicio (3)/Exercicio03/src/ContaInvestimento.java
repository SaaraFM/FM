public class ContaInvestimento extends Conta {
    public ContaInvestimento(String titular, String numeroConta, String numeroAgencia, float saldo) {
        super(titular, numeroConta, numeroAgencia, saldo, 1.01f);
    }
}
