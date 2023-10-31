public class Main {
    public static void main(String[] args) {
        Aluno caio = new Aluno("Caio", "123120021");
        System.out.println(caio.getNome() + ' ' + caio.getClass());

        Professor ulpio = new Professor("Ulpio", 4928.12f);
        System.out.println("Professor " + ulpio.getNome() + " recebe: R$ " + ulpio.getSalario());

        Cachorro luke = new Cachorro("Luke", "Bulldog Francês");
        luke.late();
    }
}