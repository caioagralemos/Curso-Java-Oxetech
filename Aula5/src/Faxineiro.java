public class Faxineiro extends Funcionario {
    private int horasTrabalhadas;
    public Faxineiro(String nome, int idade, String cpf, String departamento, int salario, int horasTrabalhadas) {
        super(nome,idade,cpf,departamento,salario);
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public int getHorasSemanais() {
        return horasTrabalhadas * 5;
    }

    public void limpar() {
        System.out.println(this.getNome() + " está limpando!");
    }
}
