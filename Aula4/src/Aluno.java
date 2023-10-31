public class Aluno extends Pessoa{
    // atributo FINAL não permite que outra classe herde dela
    // atributo ABSTRACT deixa a classe apenas como modelo, n sendo possivel criar uma instância
    private String matricula;
    public Aluno(String nome, String matricula) {
        super(nome);
        this.matricula = matricula;
    }
    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
