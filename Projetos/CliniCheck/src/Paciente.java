import java.util.ArrayList;

public class Paciente {
    private final String nome;
    private final String cpf;
    private final String cartao_sus;
    public ArrayList<String> laudo;
    public ArrayList<Data> horarios_marcados;

    public Paciente(String nome, String cpf, String cartao_sus, ArrayList<String> laudo, ArrayList<Data> horarios_marcados) {
        this.nome = nome;
        this.cpf = cpf;
        this.cartao_sus = cartao_sus;
        this.laudo = laudo;
        this.horarios_marcados = horarios_marcados;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getSUS() {
        return cartao_sus;
    }

    public String toString() {
        return "Nome: " + nome + " | " +
                "CPF: " + cpf + " | " +
                "SUS: " + cartao_sus + " | " +
                "Laudo: " + laudo;
    }
}
