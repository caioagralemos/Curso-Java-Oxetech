public class Livro extends Produto{
    double calcularDesconto() {
        if (this.preco > 70) {
            return this.preco - 50;
        } else {
            return this.preco;
        }
    }
    public Livro(String nome, double preco) {
        super(nome, preco);
    }
}
