public class Carne extends Item {
    double calcularDesconto(int peso) {
        if (peso > 1) {
            return 0.08;
        } else {
            return 0.0;
        }
    }
    public Carne(String nome, double preco) {
        super(nome, preco);
    }
}
