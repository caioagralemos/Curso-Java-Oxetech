public abstract class Item {
    String nome;
    double precoPorQuilo;
    abstract double calcularDesconto(int peso);
    public Item(String nome, double precoPorQuilo) {
        this.nome = nome;
        this.precoPorQuilo = precoPorQuilo;
    }
    double calcularPrecoFinal(int peso) {
        double preco = this.precoPorQuilo * peso;
        return preco - (preco * this.calcularDesconto(peso));
    }
}
