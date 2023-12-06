import java.util.ArrayList;

public class Medico {
    private String nome;
    private String cpf;
    private String crm;
    private double salario;
    private ArrayList<String> especialidade;
    private ArrayList<Data> horarios_marcados;

    public Medico(String nome, String cpf, String crm, ArrayList<String> especialidade, ArrayList<Data> horarios_marcados) {
        this.nome = nome;
        this.cpf = cpf;
        this.crm = crm;
        this.especialidade = especialidade;
        this.horarios_marcados = horarios_marcados;
    }
}
