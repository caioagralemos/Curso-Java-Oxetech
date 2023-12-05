import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Scanner;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.reflect.TypeToken;

public class TaskManager {
    ArrayList<Task> tasks = new ArrayList<>();
    Gson gson = new Gson();
    Scanner scanner = new Scanner(System.in);
    Data hoje = new Data();
    String leitura = "";

    public TaskManager() {
        try {
            Path path = Paths.get("./data.json");
            byte[] jsonData = Files.readAllBytes(path);
            String json = new String(jsonData);
            tasks = gson.fromJson(json, new TypeToken<ArrayList<Task>>(){}.getType());
        } catch (Exception ignored) {}
        userInterface();
    }

    private void userInterface() {
        System.out.println("\nBem vindo(a) ao TaskManager.\n");
        System.out.println("TaskManager é uma aplicação para gerenciar tarefas (to-do list).");
        System.out.println("Use-o para criar novas tarefas e gerenciar tarefas existentes.");

        while (true) {
            System.out.println();
            output("Você pode me controlar usando os seguintes comandos:\n");

            System.out.println("1 - NewTask");
            System.out.println("2 - MyTasks");
            System.out.println("3 - EditTask");
            System.out.println("4 - RemoveTask");
            System.out.println("5 - SearchTask");
            System.out.println("6 - ConcludeTask");
            System.out.println("S - Exit\n");

            input();

            if (leitura.equals("1")) {
                System.out.println("\nAdicionando tarefa:".toUpperCase());
                novaTask();
            } else if (leitura.equals("2")) {
                System.out.println("\nPrintando tarefas:".toUpperCase());
                printaTasks();
            } else if (leitura.equals("3")) {
                System.out.println("\nEditando tarefas:".toUpperCase());
                editaTask();
            } else if (leitura.equals("4")) {
                System.out.println("\nRemovendo tarefa:".toUpperCase());
                removeTask();
            } else if (leitura.equals("5")) {
                System.out.println("\nPesquisando tarefas por categoria:".toUpperCase());
                pesquisarTasks();
            } else if (leitura.equals("6")) {
                System.out.println("\nMarcando tarefa:".toUpperCase());
                marcarTask();
            } else {
                System.out.println();
                output("Digite S para confirmar sua saída do programa: ");
                String escolha2 = scanner.nextLine().strip().toUpperCase();
                if (escolha2.equals("S")) {
                    output("Tentando salvar os seus dados...");
                    String json = gson.toJson(tasks);
                    try (FileWriter fileWriter = new FileWriter("./data.json")) {
                        fileWriter.write(json);
                        output("Dados salvos com sucesso.");
                    } catch (IOException e) {
                        output("Não foi possível salvar seus dados.");
                    }
                    output("Obrigado por utilizar o TaskManager.");
                    break;
                }
            }
        }
    }

    public void input() {
        System.out.print("Digite aqui: ");
        this.leitura = scanner.nextLine();
    }

    public void output(String value) {
        System.out.println("TaskManager: " + value);
    }

    private void novaTask() {
        output("Digite o nome da sua task: ");
        String nome = scanner.nextLine();

        while (nome.isEmpty()) {
            output("Você precisa informar o titulo. Para voltar ao menu, digite /voltar");
            nome = scanner.nextLine();
            if (nome.equals("/voltar")) {
                return;
            }
        }

        output("Digite a categoria da sua task: ");
        String categoria = scanner.nextLine();

        while (categoria.isEmpty()) {
            output("Você precisa informar a categoria. Para voltar ao menu, digite /voltar");
            categoria = scanner.nextLine();
            if (categoria.equals("/voltar")) {
                return;
            }
        }

        output("Digite a descrição da sua task: ");
        String descricao = scanner.nextLine();

        while (descricao.isEmpty()) {
            output("Você precisa informar a descrição. Para voltar ao menu, digite /voltar");
            descricao = scanner.nextLine();
            if (descricao.equals("/voltar")) {
                return;
            }
        }

        Data d;
        while (true) {
            try {
                output("Digite a data da sua task (dia): ");
                int dia = scanner.nextInt();

                output("Digite a data da sua task (mes): ");
                int mes = scanner.nextInt();

                output("Digite a data da sua task (ano): ");
                int ano = scanner.nextInt();
                scanner.nextLine();

                d = new Data(dia, mes, ano);
                break;
            } catch (Error e) {
                output("Algo deu errado com sua data. Tente novamente\n");
            }
        }

        tasks.add(new Task(nome, categoria, descricao, d));
        output("Task adicionada com sucesso.");
    }

    private void removeTask() {
        output("Digite o título da task que deseja remover: ");
        String remove = scanner.nextLine().strip().toLowerCase();

        while (remove.isEmpty()) {
            output("Você precisa informar o titulo da task. Para voltar ao menu, digite /voltar");
            remove = scanner.nextLine();
            if (remove.equals("/voltar")) {
                return;
            }
        }

        for (int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).getTitulo().toLowerCase().equals(remove)) {
                tasks.remove(i);
                output("Tarefa removida com sucesso!");
                return;
            }
        }
        output("Task não encontrada. Tente novamente");
    }

    private void marcarTask() {
        output("Digite o título da task que deseja marcar: ");
        String marcar = scanner.nextLine().strip().toLowerCase();

        while (marcar.isEmpty()) {
            output("Você precisa informar o titulo da task. Para voltar ao menu, digite /voltar");
            marcar = scanner.nextLine();
            if (marcar.equals("/voltar")) {
                return;
            }
        }

        for (Task task : tasks) {
            if (task.getTitulo().toLowerCase().equals(marcar)) {
                task.marcarStatus();
                output("\nTarefa marcada com sucesso!");
                System.out.println(task);
                return;
            }
        }
        output("Task não encontrada. Tente novamente");
    }

    private void pesquisarTasks() {
        output("Categorias disponiveis:");
        ArrayList<String> categorias = new ArrayList<String>();

        for(int i = 0; i < tasks.size(); i++) {
            String categoria_atual = tasks.get(i).getCategoria();
            if (!categorias.contains(categoria_atual)) {
                categorias.add(categoria_atual);
                System.out.println(categoria_atual);
            }
        }
        System.out.println();

        String categoria_escolha;
        while (true) {
            output("Escolha o nome da categoria desejada:");
            categoria_escolha = scanner.nextLine().strip();
            if (categorias.contains(categoria_escolha)) {
                break;
            }
            else {
                System.out.println("Categoria não encontrada. Tente novamente");
            }
        }

        System.out.println("Suas tarefas:");
        System.out.println("-------------");

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getCategoria().equals(categoria_escolha)) {
                System.out.println(tasks.get(i).toString());
                System.out.println("-------------");
            }
        }
    }

    public void editaTask() {
        if (tasks.isEmpty()) {
            output("Você ainda não tem Tasks!");
            return;
        }

        while (true) {
            output("Digite o titulo da Task que você deseja editar ou /voltar para voltar ao menu: ");
            String escolha = scanner.nextLine().strip().toLowerCase();

            if (escolha.equals("/voltar")) {
                return;
            }

            while (escolha.isBlank()) {
                output("Para editar uma Task você precisa informar o titulo. Para voltar ao TaskManager digite /voltar");
                escolha = scanner.nextLine().strip().toLowerCase();

                if (escolha.equals("/voltar")) {
                    return;
                }
            }

            for (int i = 0; i < tasks.size(); i++) {
                if(tasks.get(i).getTitulo().toLowerCase().equals(escolha)) {
                    output("Qual o novo título para essa Task? (Deixe em branco para continuar com " + tasks.get(i).getTitulo()+ "): ");
                    String novoTitulo = scanner.nextLine().strip();
                    if (!novoTitulo.isBlank()) {
                        tasks.get(i).setTitulo(novoTitulo);
                    }

                    output("Qual a nova categoria para essa Task? (Deixe em branco para continuar com " + tasks.get(i).getCategoria() + "): ");
                    String novaCategoria = scanner.nextLine().strip();
                    if (!novaCategoria.isBlank()) {
                        tasks.get(i).setCategoria(novaCategoria);
                    }

                    output("Qual a nova descrição para essa Task? (Deixe em branco para continuar com a descrição atual): ");
                    String novaDescricao = scanner.nextLine().strip();
                    if (!novaDescricao.isBlank()) {
                        tasks.get(i).setDescricao(novaDescricao);
                    }

                    return;
                }
            }
            output("Task não encontrada. Tente novamente\n");
        }
    }

    private void printaTasks() {
        if (tasks.size() <= 0) {
            output("Você ainda não tem tasks!");
            return;
        }
        output("Digite:\n1 para printar todas as tasks\n2 para printar as tasks concluídas\noutro para printar tasks não concluidas");
        String escolha = scanner.nextLine().strip();
        if (escolha.equals("1")) {
            System.out.println("Suas tarefas:");
            System.out.println("-------------");
            for (Task task : tasks) {
                System.out.println(task.toString());
                System.out.println("-------------");
            }
        } else if (escolha.equals("2")) {
            System.out.println("Suas tarefas:");
            System.out.println("-------------");
            for (Task task : tasks) {
                if (task.isStatus().equals("Feito")) {
                    System.out.println(task);
                    System.out.println("-------------");
                }
            }
        } else {
            System.out.println("Suas tarefas:");
            System.out.println("-------------");
            for (Task task : tasks) {
                if (task.isStatus().equals("A fazer")) {
                    System.out.println(task);
                    System.out.println("-------------");
                }
            }
        }
    }
}
