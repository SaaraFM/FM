public class App {
    public static void main(String[] args) {
        Circulo circulo = new Circulo(1.0, 2.0, 5.0);
        System.out.println("Área do círculo: " + circulo.calcularArea());
        circulo.moverCirculo(3.0, 4.0);
        System.out.println("Nova posição do círculo: X = " + circulo.getX() + ", Y = " + circulo.getY());
    }
}