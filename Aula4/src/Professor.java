public class Professor extends Pessoa{
    private float salario;
    public Professor(String nome, float salario) {
        super(nome);
        this.salario = salario;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }
}
