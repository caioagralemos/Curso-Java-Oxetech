public abstract class Produto {
    String nome;
    double preco;
    abstract double calcularDesconto();
    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }
}
