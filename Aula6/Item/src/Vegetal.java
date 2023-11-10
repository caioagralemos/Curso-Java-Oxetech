public class Vegetal extends Item {
    double calcularDesconto(int peso) {
        if (peso > 3) {
            return 0.10;
        } else {
            return 0.05;
        }
    }
    public Vegetal(String nome, double preco) {
        super(nome, preco);
    }
}
