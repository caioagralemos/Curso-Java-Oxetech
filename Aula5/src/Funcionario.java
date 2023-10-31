public class Funcionario extends Pessoa {
    final private String departamento;
    final private int salario;
    public Funcionario(String nome, int idade, String cpf, String departamento, int salario) {
        super(nome,idade,cpf);
        this.departamento = departamento;
        this.salario = salario;
    }

    public int getRendaAnual() {
        return salario*13;
    }

    public String getDepartamento() {
        return departamento;
    }
}
