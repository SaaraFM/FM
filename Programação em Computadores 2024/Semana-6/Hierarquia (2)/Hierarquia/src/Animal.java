public class Animal {
    protected String nome;
    protected String especie;
    protected String familia;
    protected int idade;

    public Animal(String nome, String especie, String familia, int idade) {
        this.nome = nome;
        this.especie = especie;
        this.familia = familia;
        this.idade = idade;
    }

    public String alimentar() {
        return "alimentou na classe Animal";
    }
}
