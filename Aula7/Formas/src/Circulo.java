public class Circulo implements AreaCalculavel{
    double raio;

    public double calculaArea() {
        return 3.14*(this.raio*this.raio);
    }

    public Circulo (double raio) {
        this.raio = raio;
    }
}
