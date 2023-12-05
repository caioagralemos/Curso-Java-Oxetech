import java.util.ArrayList;
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
    ArrayList<Task> tasksDeHoje = new ArrayList<>();
    ArrayList<Task> tasksExpiradas = new ArrayList<>();
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

        if (!tasks.isEmpty()) {
            for (Task task: tasks) {
                if (task.prazo.compare(hoje)) {
                    if (task.prazo.hoje(hoje)) {
                        tasksDeHoje.add(task);
                    }
                } else {
                    tasksExpiradas.add(task);
                }
            }
        }

        if (!tasksExpiradas.isEmpty()) {
            for (Task task: tasksExpiradas) {
                tasks.remove(task);
            }
        }

        userInterface();
    }

    private void userInterface() {
        System.out.println("\nBem vindo(a) ao TaskManager.\n");
        System.out.println("TaskManager é uma aplicação para gerenciar tarefas (to-do list).");
        System.out.println("Use-o para criar novas tarefas e gerenciar tarefas existentes.");

        if(!tasksDeHoje.isEmpty()) {
            System.out.println("\nSuas tasks de hoje são:");
            for (Task task: tasksDeHoje) {
                System.out.println(task);
            }
        }

        while (true) {
            System.out.println();
            output("Você pode me controlar usando os seguintes comandos:\n");

            System.out.println("1 - NewTask");
            System.out.println("2 - MyTasks");
            System.out.println("3 - EditTask");
            System.out.println("4 - RemoveTask");
            System.out.println("5 - CheckTask");
            System.out.println("S - Fechar e Salvar Alterações\n");

            input();

            if (leitura.equals("1")) {
                System.out.println("\nAdicionando tarefa:".toUpperCase());
                novaTask();
            } else if (leitura.equals("2")) {
                System.out.println("\nPrintando tarefas:".toUpperCase());
                printaTasks();
            } else if (leitura.equals("3")) {
                System.out.println("\nEditando tarefas:".toUpperCase());
                editarTask();
            } else if (leitura.equals("4")) {
                System.out.println("\nRemovendo tarefa:".toUpperCase());
                removeTask();
            } else if (leitura.equals("5")) {
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

    public void editarTask() {
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

            for (Task task : tasks) {
                if (task.getTitulo().toLowerCase().equals(escolha)) {
                    output("Qual o novo título para essa Task? (Deixe em branco para continuar com " + task.getTitulo() + "): ");
                    String novoTitulo = scanner.nextLine().strip();
                    if (!novoTitulo.isBlank()) {
                        task.setTitulo(novoTitulo);
                    }

                    output("Qual a nova categoria para essa Task? (Deixe em branco para continuar com " + task.getCategoria() + "): ");
                    String novaCategoria = scanner.nextLine().strip();
                    if (!novaCategoria.isBlank()) {
                        task.setCategoria(novaCategoria);
                    }

                    output("Qual a nova descrição para essa Task? (Deixe em branco para continuar com a descrição atual): ");
                    String novaDescricao = scanner.nextLine().strip();
                    if (!novaDescricao.isBlank()) {
                        task.setDescricao(novaDescricao);
                    }

                    return;
                }
            }
            output("Task não encontrada. Tente novamente\n");
        }
    }

    private void pesquisarTasks() {
        output("Categorias disponiveis:");
        ArrayList<String> categorias = new ArrayList<>();

        for (Task task : tasks) {
            String categoria_atual = task.getCategoria();
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

        for (Task task : tasks) {
            if (task.getCategoria().equals(categoria_escolha)) {
                System.out.println(task);
                System.out.println("-------------");
            }
        }
    }

    private void printaTasks() {
        if (tasks.isEmpty()) {
            output("Você ainda não tem tasks!");
            return;
        }

        System.out.println();
        output("Escolha as tarefas que você quer consultar:\n");

        System.out.println("1 - Tarefas de Hoje");
        System.out.println("2 - Tarefas a Fazer");
        System.out.println("3 - Tarefas Concluídas");
        System.out.println("4 - Tarefas Expiradas");
        System.out.println("5 - Tarefas por Categoria");
        System.out.println("V - Voltar para o menu\n");

        System.out.print("Digite aqui: ");
        String escolha = scanner.nextLine().strip();

        while (escolha.isBlank()) {
            System.out.println("É necessário digitar sua escolha. Escolha uma das opções: ");
            escolha = scanner.nextLine().strip();
        }

        switch (escolha) {
            case "1": // hoje
                if (tasksDeHoje.isEmpty()) {
                    output("Você não tem tasks para hoje!");
                    return;
                }
                System.out.println("Suas tarefas de hoje:");
                System.out.println("-------------");
                for (Task task : tasksDeHoje) {
                    System.out.println(task.toString());
                    System.out.println("-------------");
                }
                break;
            case "2": // a fazer
                System.out.println("Suas tarefas:");
                System.out.println("-------------");
                for (Task task : tasks) {
                    if (task.isStatus().equals("A fazer")) {
                        System.out.println(task);
                        System.out.println("-------------");
                    }
                }
                break;
            case "3": // concluidas
                System.out.println("Suas tarefas:");
                System.out.println("-------------");
                for (Task task : tasks) {
                    if (task.isStatus().equals("Feito")) {
                        System.out.println(task);
                        System.out.println("-------------");
                    }
                }
                break;
            case "4": // expiradas
                if (tasksExpiradas.isEmpty()) {
                    output("Você ainda não tem tasks expiradas!");
                    return;
                }
                System.out.println("Suas tarefas:");
                System.out.println("-------------");
                for (Task task : tasksExpiradas) {
                    System.out.println(task.toString());
                    System.out.println("-------------");
                }
                break;
            case "5": // por categoria
                pesquisarTasks();
                break;
            default:
        }
    }
}
