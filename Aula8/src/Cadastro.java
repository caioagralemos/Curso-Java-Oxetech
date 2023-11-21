import java.util.InputMismatchException;
import java.util.Scanner;

public class Cadastro {
    public Scanner input = new Scanner(System.in);
    private String nome;
    private String cpf;
    private int idade;

    public Cadastro() {
        constructor();
    }
    private void constructor() {
        String nome = "";
        String cpf = "";
        int idade = -1;

        do {
            try {
                System.out.println("Digite seu nome: ");
                nome = input.nextLine();
                if (nome.isEmpty()) {
                    throw new Nulo("Nome nulo");
                }
            } catch (Nulo e) {
                System.out.println(e.mensagem);
            }
        } while (nome.isEmpty());

        do {
            try {
                System.out.println("Digite seu CPF: ");
                cpf = input.nextLine();
                if (cpf.isEmpty()) {
                    throw new Nulo("CPF nulo");
                }
            } catch (Nulo e) {
                System.out.println(e.mensagem);
            }
        } while (cpf.isEmpty());

        do {
            try {
                System.out.println("Digite sua Idade: ");
                idade = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("O valor que você inseriu é inválido!");
                input.nextLine();
            }
        } while (idade == -1);

        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
    }

}
