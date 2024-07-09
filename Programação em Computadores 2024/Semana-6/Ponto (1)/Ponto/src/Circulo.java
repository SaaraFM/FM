public class Circulo extends Ponto {
    private double raio;

    public Circulo(double x, double y, double raio) {
        super(x, y);
        this.raio = raio;
    }

    public double getRaio() {
        return raio;
    }

    public double calcularArea() {
        return Math.PI * raio * raio;
    }

    public void moverCirculo(double dx, double dy) {
        super.y += dy;
    }
}