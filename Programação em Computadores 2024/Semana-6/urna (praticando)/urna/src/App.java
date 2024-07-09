public class App {
    public static void main(String[] args) {
        System.out.println("Iniciando a urna eletrônica com 3 candidatos.");

        UrnaEletronica urna = new UrnaEletronica(3);

        try {
            urna.votar(0);
            urna.votar(1);
            urna.votar(1);
            urna.votarEmBranco();
            urna.anularVoto();
            urna.apurarEleicao();
            urna.votar(0);
        } catch (IllegalStateException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        System.out.println("Encerrando a urna eletrônica.");
    }
}
