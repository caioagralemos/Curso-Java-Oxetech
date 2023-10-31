public class Animal {
    private String nome;
    private String raca;

    public Animal(String nome, String raca) {
        this.nome = nome;
        this.raca = raca;
    }

    public String caminha() {
        System.out.println(this.nome + " está caminhando.");
        return this.nome + " está caminhando.";
    }

    public String getNome() {
        return nome;
    }

    public String getRaca() {
        return raca;
    }
}
