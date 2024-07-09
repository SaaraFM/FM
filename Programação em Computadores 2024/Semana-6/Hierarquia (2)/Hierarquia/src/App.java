public class App {
    public static void main(String[] args) {
        Animal animal = new Animal("Leão", "Panthera leo", "Felidae", 5);
        Mamifero mamifero = new Mamifero("Urso", "Ursidae", "Carnívora", 3, "Grosso");
        Aves ave = new Aves("Águia", "Accipitridae", "Carnívoras", 4, "Curvo");

        System.out.println(animal.alimentar());
        System.out.println(mamifero.alimentar());
        System.out.println(ave.alimentar());
        System.out.println(ave.voar());
    }
}
