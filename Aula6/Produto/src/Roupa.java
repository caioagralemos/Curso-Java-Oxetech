public class Roupa extends Produto {
    double calcularDesconto() {
        if (this.preco > 100) {
            return this.preco - (this.preco * 0.15);
        } else {
            return this.preco - (this.preco * 0.05);
        }
    }
    public Roupa(String nome, double preco) {
        super(nome, preco);
    }
}
