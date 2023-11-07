import java.util.Objects;
import java.util.Scanner;

public class Agenda {
    public int ids;
    public Scanner input = new Scanner(System.in);
    public Contato[] contatos = new Contato[1000];
    public Agenda () {
        this.ids = 0;
        System.out.println("Agenda criada com sucesso!");
    }

    public void raiseIds() {
        this.ids++;
    }

    private void adicionarContato() {
        int id = this.ids;

        System.out.println("Digite o nome do contato: ");
        String nome = input.nextLine();
        while (Objects.equals(nome, "") || Objects.equals(nome, " ")) {
            System.out.println("Nome inválido\nDigite o nome do contato: ");
            nome = input.nextLine();
        }

        System.out.println("Digite o número do contato: ");
        String numero = input.nextLine().strip();
        while (Objects.equals(numero, "")) {
            System.out.println("Número inválido\nDigite o número do contato: ");
            numero = input.nextLine();
        }

        System.out.println("Digite o email do contato (opcional): ");
        String email = input.nextLine().strip();
        if (email.isEmpty()) {
            email = null;
        }

        contatos[ids] = new Contato(id, nome, numero, email);
        raiseIds();
    }

    private void listarContatos() {
        System.out.println("SUA AGENDA:");
        for (int i = 0; i < ids; i++) {
            System.out.println(" - CONTATO (id: " + (contatos[i].getId() + 1) + "):");
            System.out.println("    Nome: " + contatos[i].getNome());
            System.out.println("    Número: " + contatos[i].getNumero());
            if(contatos[i].getEmail() != null) {
                System.out.println("    Email: " + contatos[i].getEmail());
            }
        }
    }

    private void editarContato(int id) {
        if (id > 0 && id < this.ids) {
            int num = id - 1;
            System.out.println("ALTERANDO CONTATO:");

            System.out.println("Digite o nome do contato (ou deixe em branco para continuar com " + contatos[num].getNome() + "): ");
            String nome = input.nextLine().strip();
            if (nome != "") {
                contatos[num].setNome(nome);
            }

            System.out.println("Digite o número do contato (ou deixe em branco para continuar com '" + contatos[num].getNumero() + "'): ");
            String numero = input.nextLine().strip();
            if (numero != "") {
                contatos[num].setNumero(numero);
            }

            if (Objects.equals(contatos[num].getEmail(), "")) {
                System.out.println("Digite o email do contato (opcional): ");
                String email = input.nextLine().strip();
                if (email.isEmpty()) {
                    email = null;
                } else {
                    contatos[num].setEmail(email);
                }
            } else {
                System.out.println("Digite o email do contato (ou deixe em branco para continuar com '" + contatos[num].getEmail() + "'): ");
                String email = input.nextLine().strip();
                if (!numero.isEmpty()) {
                    contatos[num].setEmail(email);
                }
            }

        } else {
            System.out.println("ID inválido!");
        }
    }


    public void excluirContato(int id) {
        if (id > 0 && id < this.ids) {
            this.contatos[id-1] = null;
        } else {
            System.out.println("Id inválido! Não foi possível excluir");
        }
    }

    public void consultarAgenda() {
        System.out.println("CONSULTANDO SUA AGENDA:");
        String entrada;
        while (true) {
            System.out.println("Escolha sua consulta:\n   - 1 para listar seus contatos\n   - 2 para adicionar contato\n   - 3 para excluir contato\n   - qualquer outra tecla para sair");
            entrada = input.nextLine().strip();
            if (!entrada.equals("1") && !entrada.equals("2") && !entrada.equals("3")) {
                break;
            } else {
                if (entrada.equals("1")) {
                    this.listarContatos();
                } else if (entrada.equals("2")) {
                    this.adicionarContato();
                } else if (entrada.equals("3")) {
                    System.out.println("Digite o id do contato que deseja excluir:");
                    int id = input.nextInt();
                    this.excluirContato(id);
                }
            }
        }
    }

}
