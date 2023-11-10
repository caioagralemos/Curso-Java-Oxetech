public class Eletronico extends Produto{
    double calcularDesconto() {
        return this.preco - (this.preco * 0.1);
    }
    public Eletronico(String nome, double preco) {
        super(nome, preco);
    }
}
