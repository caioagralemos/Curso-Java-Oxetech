public class Fruta extends Item {
    double calcularDesconto(int peso) {
        if (peso > 2) {
            return 0.05;
        } else {
            return 0.0;
        }
    }
    public Fruta(String nome, double preco) {
        super(nome, preco);
    }
}
