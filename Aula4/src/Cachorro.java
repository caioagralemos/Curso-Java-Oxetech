public class Cachorro extends Animal{
    public Cachorro(String nome, String raca) {
        super(nome, raca);
    }

    public String late() {
        System.out.println(this.getNome() + " está latindo!");
        return this.getNome() + " está latindo!";
    }
}
