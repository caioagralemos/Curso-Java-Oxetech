public class Retangulo implements AreaCalculavel{
    double base;
    double altura;

    public double calculaArea() {
        return base*altura;
    }

    public Retangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }
}
