public class Quadrado implements AreaCalculavel{
    double lado;

    public double calculaArea() {
        return lado*lado;
    }

    public Quadrado(double lado) {
        this.lado = lado;
    }
}
