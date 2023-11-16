public class Main {
    public static void main(String[] args) {
        AreaCalculavel[] formas = new AreaCalculavel[5];
        formas[0] = new Circulo(2);
        formas[1] = new Quadrado(9);
        formas[2] = new Retangulo(3, 1);
        formas[3] = new Circulo(2);
        formas[4] = new Retangulo(7, 2);

        for (AreaCalculavel forma: formas) {
            System.out.println(forma.calculaArea());
        }
    }
}