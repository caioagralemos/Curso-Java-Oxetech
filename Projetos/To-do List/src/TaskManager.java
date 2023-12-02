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
    ArrayList<Task> tasks = new ArrayList<Task>();
    Gson gson = new Gson();
    Scanner input = new Scanner(System.in);

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
        System.out.println("Bem-vindo(a) ao TaskManager.");
        while (true) {
            System.out.println("\nEscolha:\n1 para adicionar\n2 para remover\n3 para marcar ou desmarcar uma tarefa\n4 para printar tarefas\noutro para sair\nsua escolha: ");
            String escolha = input.nextLine().strip();
            if (escolha.equals("1")) {
                System.out.println("\nAdicionando tarefa:".toUpperCase());
                novaTask();
            } else if (escolha.equals("2")) {
                System.out.println("\nRemovendo tarefa:".toUpperCase());
                removeTask();
            } else if (escolha.equals("3")) {
                System.out.println("\nMarcando tarefa:".toUpperCase());
                marcarTask();
            } else if (escolha.equals("4")) {
                System.out.println("\nPrintando tarefas:".toUpperCase());
                printaTasks();
            } else {
                System.out.println("Digite S para confirmar sua saída do programa: ");
                String escolha2 = input.nextLine().strip().toUpperCase();
                if (escolha2.equals("S")) {
                    System.out.println("Tentando salvar os seus dados...");
                    String json = gson.toJson(tasks);
                    try (FileWriter fileWriter = new FileWriter("./data.json")) {
                        fileWriter.write(json);
                        System.out.println("Dados salvos com sucesso.");
                    } catch (IOException e) {
                        System.out.println("Não foi possível salvar seus dados.");
                    }
                    System.out.println("Obrigado por utilizar o TaskManager.");
                    break;
                }
            }
        }
    }

    private void novaTask() {
        System.out.println("Digite o nome da sua task: ");
        String nome = input.nextLine();

        System.out.println("Digite a categoria da sua task: ");
        String categoria = input.nextLine();

        System.out.println("Digite a descrição da sua task: ");
        String descricao = input.nextLine();

        Data d;
        while (true) {
            try {
                System.out.println("Digite a data da sua task (dia): ");
                int dia = input.nextInt();

                System.out.println("Digite a data da sua task (mes): ");
                int mes = input.nextInt();

                System.out.println("Digite a data da sua task (ano): ");
                int ano = input.nextInt();
                input.nextLine();

                d = new Data(dia, mes, ano);
                break;
            } catch (Error e) {
                System.out.println("Algo deu errado com sua data. Tente novamente\n");
            }
        }

        tasks.add(new Task(nome, categoria, descricao, d));
    }

    private void removeTask() {
        System.out.println("Digite o título da task que deseja remover: ");
        String remove = input.nextLine().strip().toLowerCase();
        for (int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).getTitulo().toLowerCase().equals(remove)) {
                tasks.remove(i);
                System.out.println("Tarefa removida com sucesso!");
                return;
            }
        }
        System.out.println("Task não encontrada. Tente novamente");
    }

    private void marcarTask() {
        System.out.println("Digite o título da task que deseja marcar: ");
        String marcar = input.nextLine().strip().toLowerCase();
        for (int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).getTitulo().toLowerCase().equals(marcar)) {
                tasks.get(i).marcarStatus();
                System.out.println("Tarefa marcada com sucesso!");
                System.out.println(tasks.get(i).toString());
                return;
            }
        }
        System.out.println("Task não encontrada. Tente novamente");
    }

    private void printaTasks() {
        System.out.println("Digite:\n1 para printar todas as tasks\n2 para printar as tasks concluídas\noutro para printar tasks não concluidas");
        String escolha = input.nextLine().strip();
        if (escolha.equals("1")) {
            System.out.println("Suas tarefas:");
            System.out.println("-------------");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(tasks.get(i).toString());
                System.out.println("-------------");
            }
        } else if (escolha.equals("2")) {
            System.out.println("Suas tarefas:");
            System.out.println("-------------");
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).isStatus().equals("Feito")) {
                    System.out.println(tasks.get(i).toString());
                    System.out.println("-------------");
                }
            }
        } else {
            System.out.println("Suas tarefas:");
            System.out.println("-------------");
            for (int i = 0; i < tasks.size(); i++) {
                if (!tasks.get(i).isStatus().equals("A fazer")) {
                    System.out.println(tasks.get(i).toString());
                    System.out.println("-------------");
                }
            }
        }
    }
}
