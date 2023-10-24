import java.util.Random;
public class Cliente {
    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.conta = new contaBancaria();
    }
    String nome;
    String cpf;
    contaBancaria conta;

    void falar(String frase) {
        System.out.println(nome.split(" ")[0] + " disse: " + frase);
    }
}
