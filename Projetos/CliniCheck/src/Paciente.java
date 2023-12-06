import java.util.ArrayList;

public class Paciente {
    private final String nome;
    private final String cpf;
    private final String cartao_sus;
    public ArrayList<String> laudo;

    public Paciente(String nome, String cpf, String cartao_sus, ArrayList<String> laudo) {
        this.nome = nome;
        this.cpf = cpf;
        this.cartao_sus = cartao_sus;
        this.laudo = laudo;
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
}
