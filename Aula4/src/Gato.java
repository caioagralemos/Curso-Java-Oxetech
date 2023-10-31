public class Gato extends Animal{
    public Gato(String nome, String raca) {
        super(nome, raca);
    }

    public String late() {
        System.out.println(this.getNome() + " está miando!");
        return this.getNome() + " está miando!";
    }
}
