public class Aves extends Animal {
    private String tipoDeBico;

    public Aves(String nome, String especie, String familia, int idade, String tipoDeBico) {
        super(nome, especie, familia, idade);
        this.tipoDeBico = tipoDeBico;
    }

    @Override
    public String alimentar() {
        return "alimentou na classe Ave";
    }

    public String voar() {
        return "voa na classe Ave";
    }
}