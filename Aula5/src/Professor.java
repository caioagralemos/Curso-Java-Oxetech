public class Professor extends Funcionario {
    private String[] materias = new String[2];
    public Professor(String nome, int idade, String cpf, String departamento, int salario, String materia) {
        super(nome,idade,cpf,departamento,salario);
        this.materias[0] = materia;
    }

    public void addMateria(String materia) {
        if (this.materias.length >= 2) {
            System.out.println("O professor já tem 2 matérias!");
        } else {
            this.materias[1] = materia;
        }
    }

    public void ensinarMateria() {
        System.out.println("O professor " + this.getNome() + " está ensinando.");
    }
}
