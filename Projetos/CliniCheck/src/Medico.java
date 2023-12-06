import java.util.ArrayList;

public class Medico {
    private final String nome;
    private final String cpf;
    private final String crm;
    private final double salario;
    public ArrayList<String> especialidade;
    public ArrayList<Data> horarios_marcados;

    public Medico(String nome, String cpf, String crm, double salario, ArrayList<String> especialidade, ArrayList<Data> horarios_marcados) {
        this.nome = nome;
        this.cpf = cpf;
        this.crm = crm;
        this.salario = salario;
        this.especialidade = especialidade;
        this.horarios_marcados = horarios_marcados;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCrm() {
        return crm;
    }
}
