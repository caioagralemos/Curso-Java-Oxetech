import java.util.ArrayList;

public class Paciente {
    private String nome;
    private String cpf;
    private String cartao_sus;
    private ArrayList<String> laudo;

    public Paciente(String nome, String cpf, String cartao_sus, ArrayList<String> laudo) {
        this.nome = nome;
        this.cpf = cpf;
        this.cartao_sus = cartao_sus;
        this.laudo = laudo;
    }
}
