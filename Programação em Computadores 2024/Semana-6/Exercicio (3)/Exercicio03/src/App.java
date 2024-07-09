public class App {
    public static void main(String[] args) {
        Conta cc = new ContaCorrente("João", "12345", "001", 1000);
        Conta cp = new ContaPoupanca("Maria", "67890", "002", 1500);
        Conta ci = new ContaInvestimento("Ana", "54321", "003", 2000);

        cc.deposito(500);
        cp.saque(100);
        ci.transferePara(cp, 500);

        System.out.println(cc);
        System.out.println(cp);
        System.out.println(ci);
    }
}