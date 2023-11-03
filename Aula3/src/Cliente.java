import java.util.Random;
public class Cliente {
    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        contas = new contaBancaria[3];
    }
    String nome;
    String cpf;
    contaBancaria [] contas;

    void falar(String frase) {
        System.out.println(nome.split(" ")[0] + " disse: " + frase);
    }

    void adicionarConta() {
        boolean success = false;
        for (int i = 0; i < this.contas.length; i++) {
            if (this.contas[i] == null) {
                this.contas[i] = new contaBancaria();
                success = true;
                break;
            }
        }

        if (success == true) {
            System.out.println("Conta adicionada com sucesso.");
        } else {
            System.out.println("Não foi possível adicionar a conta.");
        }
    }

    void adicionarContaEspecial(float valorCredito) {
        boolean success = false;
        for (int i = 0; i < this.contas.length; i++) {
            if (this.contas[i] == null) {
                this.contas[i] = new contaEspecial(valorCredito);
                success = true;
                break;
            }
        }

        if (success == true) {
            System.out.println("Conta especial adicionada com sucesso.");
        } else {
            System.out.println("Não foi possível adicionar a conta.");
        }
    }

    void removerConta() {
        boolean success = false;
        for (int i = this.contas.length-1; i >= 0; i++) {
            if (this.contas[i] != null) {
                this.contas[i] = null;
                success = true;
                break;
            }
        }

        if (success == true) {
            System.out.println("Conta removida com sucesso.");
        } else {
                System.out.println("Não foi possível remover a conta.");
        }
    }

    void listarContas() {
        for(int i = 0; i < this.contas.length; i++) {
            if (this.contas[i] != null) {
                System.out.println("Conta " + (i+1) + ":");
                this.contas[i].Extrato();
            }
        }
    }
}
