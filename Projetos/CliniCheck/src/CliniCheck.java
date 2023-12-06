import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class CliniCheck {
    ArrayList<Medico> medicos = new ArrayList<>();
    ArrayList<Paciente> pacientes = new ArrayList<>();
    ArrayList<Consulta> consultas = new ArrayList<>();
    Gson gson = new Gson();
    Scanner scanner = new Scanner(System.in);

    public CliniCheck() {
        try {
            Path path_medicos = Paths.get("./medicos.json");
            byte[] medicos_jsonData = Files.readAllBytes(path_medicos);
            String medicos_json = new String(medicos_jsonData);
            medicos = gson.fromJson(medicos_json, new TypeToken<ArrayList<Medico>>(){}.getType());
        } catch (Exception ignored) {}

        try {
            Path path_pacientes = Paths.get("./pacientes.json");
            byte[] pacientes_jsonData = Files.readAllBytes(path_pacientes);
            String pacientes_json = new String(pacientes_jsonData);
            pacientes = gson.fromJson(pacientes_json, new TypeToken<ArrayList<Paciente>>(){}.getType());
        } catch (Exception ignored) {}

        try {
            Path path_consultas = Paths.get("./consultas.json");
            byte[] consultas_jsonData = Files.readAllBytes(path_consultas);
            String consultas_json = new String(consultas_jsonData);
            consultas = gson.fromJson(consultas_json, new TypeToken<ArrayList<Consulta>>(){}.getType());
        } catch (Exception ignored) {}

        userInterface();
    }

    private void output(String texto) {
        System.out.println("CliniCheck diz: " + texto);
    }

    private void userInterface() {
        System.out.println("\nBem vindo(a) ao CliniCheck.\n");
        System.out.println("CliniCheck é uma aplicação de recepção de policlínica.");
        System.out.println("Use-o para gerenciar e marcar novas consultas.");

        while (true) {
            System.out.println();
            output("Você pode me controlar usando os seguintes comandos:\n");

            System.out.println("1 - Marcar Consulta");
            System.out.println("2 - Ver Consultas");
            System.out.println("3 - Adicionar Médicos");
            System.out.println("4 - Adicionar Pacientes");
            System.out.println("5 - Gerenciar Usuários");
            System.out.println("S - Fechar e Salvar Alterações\n");

            System.out.print("Digite aqui: ");
            String escolha = scanner.nextLine();

            if (escolha.equals("1")) {
                System.out.println("\nMarcando consulta:".toUpperCase());
                adicionarConsulta();
            } else if (escolha.equals("2")) {
                System.out.println("\nChecando consultas:".toUpperCase());
                verConsultas();
            } else if (escolha.equals("3")) {
                System.out.println("\nAdicionando médico:".toUpperCase());
                adicionarMedico();
            } else if (escolha.equals("4")) {
                System.out.println("\nAdicionando paciente:".toUpperCase());
                adicionarPaciente();
            } else if (escolha.equals("5")) {
                System.out.println("\nGerenciando usuários:".toUpperCase());
            } else {
                System.out.println();
                output("Digite S para confirmar sua saída do programa: ");
                String escolha2 = scanner.nextLine().strip().toUpperCase();
                if (escolha2.equals("S")) {
                    output("Tentando salvar os seus dados...");

                    String json_consultas = gson.toJson(consultas);
                    try (FileWriter fileWriter = new FileWriter("./consultas.json")) {
                        fileWriter.write(json_consultas);
                        output("Consultas salvas com sucesso.");
                    } catch (IOException e) {
                        output("Não foi possível salvar suas consultas.");
                    }

                    String json_medicos = gson.toJson(medicos);
                    try (FileWriter fileWriter = new FileWriter("./medicos.json")) {
                        fileWriter.write(json_medicos);
                        output("Médicos salvos com sucesso.");
                    } catch (IOException e) {
                        output("Não foi possível salvar seus médicos.");
                    }

                    String json_pacientes = gson.toJson(pacientes);
                    try (FileWriter fileWriter = new FileWriter("./pacientes.json")) {
                        fileWriter.write(json_pacientes);
                        output("Pacientes salvos com sucesso.");
                    } catch (IOException e) {
                        output("Não foi possível salvar seus pacientes.");
                    }

                    output("Obrigado por utilizar o TaskManager.");
                    break;
                }
            }
        }
    }

    private void adicionarMedico() {
        output("Digite o nome do médico: ");
        String nome = scanner.nextLine().strip();
        while (nome.isBlank()) {
            output("O nome do médico não pode ficar em branco. Digite o nome do médico ou /menu pra voltar ao menu: ");
            nome = scanner.nextLine();
            if(nome.equals("/menu")) {
                return;
            }
        }

        output("Digite o CPF do médico: ");
        String cpf = scanner.nextLine().strip().replace("-", "").replace(".", "");
        while (cpf.isBlank() || cpf.length() != 11) {
            output("CPF inválido. Digite o CPF ou /menu pra voltar ao menu: ");
            cpf = scanner.nextLine().strip().replace("-", "").replace(".", "");
            if(cpf.equals("/menu")) {
                return;
            }
        }

        output("Digite o CRM do médico: ");
        String crm = scanner.nextLine().strip();
        while (crm.isBlank()) {
            output("CRM inválido. Digite o CRM ou /menu pra voltar ao menu: ");
            crm = scanner.nextLine().strip();
            if(crm.equals("/menu")) {
                return;
            }
        }

        double salario;
        while (true) {
            output("Digite o salário do médico: ");
            try {
                salario = Double.parseDouble(scanner.nextLine());
                break;
            } catch (Exception e) {
                output("Algo deu errado. Tente novamente");
            }
        }

        output("Digite as especialidades do médico e digite -1 quando tiver concluído: ");
        ArrayList<String> especialidades = new ArrayList<>();
        int contador = 1;
        while (true) {
            System.out.print("Digite aqui (Especialidade nº" + contador + "): ");
            String especialidade = scanner.nextLine().strip().toUpperCase();

            if(especialidade.equals("-1")) {
                break;
            }

            if (!especialidade.isBlank()) {
                contador++;
                especialidades.add(especialidade);
            }
        }

        ArrayList<Data> horarios_marcados = new ArrayList<>();

        Medico novo_medico = new Medico(nome, cpf, crm, salario, especialidades, horarios_marcados);
        medicos.add(novo_medico);
    }

    private void adicionarPaciente() {
        output("Digite o nome do paciente: ");
        String nome = scanner.nextLine().strip();
        while (nome.isBlank()) {
            output("O nome do paciente não pode ficar em branco. Digite o nome do paciente ou /menu pra voltar ao menu: ");
            nome = scanner.nextLine();
            if(nome.equals("/menu")) {
                return;
            }
        }

        output("Digite o CPF do paciente: ");
        String cpf = scanner.nextLine().strip().replace("-", "").replace(".", "");
        while (cpf.isBlank() || cpf.length() != 11) {
            output("CPF inválido. Digite o CPF ou /menu pra voltar ao menu: ");
            cpf = scanner.nextLine().strip().replace("-", "").replace(".", "");
            if(cpf.equals("/menu")) {
                return;
            }
        }

        output("Digite o número do Cartão SUS do paciente: ");
        String cartao_sus = scanner.nextLine().strip();
        while (cartao_sus.isBlank()) {
            output("Cartão SUS inválido. Digite o número do Cartão SUS ou /menu pra voltar ao menu: ");
            cartao_sus = scanner.nextLine();
            if(cartao_sus.equals("/menu")) {
                return;
            }
        }

        output("Digite o laudo do paciente e digite -1 quando tiver concluído: ");
        ArrayList<String> laudo = new ArrayList<>();
        int contador = 1;
        while (true) {
            System.out.print("Digite aqui (Doença nº" + contador + "): ");
            String doenca = scanner.nextLine().strip().toUpperCase();

            if(doenca.equals("-1")) {
                break;
            }

            if (!doenca.isBlank()) {
                contador++;
                laudo.add(doenca);
            }
        }

        Paciente novo_paciente = new Paciente(nome, cpf, cartao_sus, laudo);
        pacientes.add(novo_paciente);
    }

    private void adicionarConsulta() {
        // Paciente
        Paciente paciente = consultarPaciente();
        if (paciente == null) {
            return;
        }

        String doenca;
        if(paciente.laudo.isEmpty()) {
            output("Paciente não possui doenças no laudo.");
            return;
        } else {
            output("Escolha a doença do paciente a ser consultada: ");
            int contador = 0;
            for (String d: paciente.laudo) {
                System.out.println((contador+1) + " - " + d);
            }
            int escolha;
            while (true) {
                try {
                    escolha = Integer.parseInt(scanner.nextLine().strip());
                    if(escolha <= (paciente.laudo.size()+1) && escolha > 0) {
                        doenca = paciente.laudo.get(escolha-1);
                        break;
                    } else {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    output("Algo deu errado.\n");
                    output("Escolha a doença do paciente a ser consultada: ");
                }
            }
        }

        // Médico
        Medico medico = consultarMedico(doenca);
        if(medico == null) {
            return;
        }

        Data data = new Data();

        Consulta nova_consulta = new Consulta(medico, paciente, doenca, data);
        consultas.add(nova_consulta);
    }

    private Paciente consultarPaciente() {
        Paciente paciente = null;
        while (true) {
            output("Identificando Paciente - digite 1 para CPF ou 2 para SUS: ");
            String escolha_paciente = scanner.nextLine().strip();
            while (!escolha_paciente.equals("1") && !escolha_paciente.equals("2")) {
                output("Algo deu errado.");
                output("Identificando Paciente - digite 1 para CPF ou 2 para SUS ou /sair pra sair: ");
                escolha_paciente = scanner.nextLine().strip();
                if (escolha_paciente.equals("/sair")) {
                    return null;
                }
            }
            if (escolha_paciente.equals("1")) {
                // trabalhando com CPF
                System.out.print("Digite o CPF: ");
                String cpf = scanner.nextLine().strip().replace("-", "").replace(".", "");
                while (cpf.isBlank() || cpf.length() != 11) {
                    output("CPF inválido. Digite o CPF ou /menu pra voltar ao menu: ");
                    cpf = scanner.nextLine().strip().replace("-", "").replace(".", "");
                    if(cpf.equals("/menu")) {
                        return null;
                    }
                }
                for (Paciente p: pacientes) {
                    if (p.getCpf().equals(cpf)) {
                        paciente = p;
                        break;
                    }
                }
            } else {
                // trabalhando com cartão SUS
                System.out.print("Digite o nº do cartão SUS: ");
                String sus = scanner.nextLine().strip().replace("-", "").replace(".", "");
                while (sus.isBlank()) {
                    output("Cartão SUS inválido. Digite o Cartão SUS ou /menu pra voltar ao menu: ");
                    sus = scanner.nextLine().strip().replace("-", "").replace(".", "");
                    if(sus.equals("/menu")) {
                        return null;
                    }
                }
                for (Paciente p: pacientes) {
                    if (p.getSUS().equals(sus)) {
                        paciente = p;
                        break;
                    }
                }
            }
            if (paciente != null) {
                output("Paciente " + paciente.getNome() + " registrado com sucesso.");
                break;
            } else {
                output("Não foi possível encontrar paciente com esse CPF.");
            }
        }
        return paciente;
    }

    private Medico consultarMedico(String doenca) {
        Medico medico = null;
        while (true) {
            output("Identificando Médico - digite 1 para CPF ou 2 para CRM: ");
            String escolha_medico = scanner.nextLine().strip();
            while (!escolha_medico.equals("1") && !escolha_medico.equals("2")) {
                output("Algo deu errado.");
                output("Identificando Médico - digite 1 para CPF ou 2 para CRM ou /sair pra sair: ");
                escolha_medico = scanner.nextLine().strip();
                if (escolha_medico.equals("/sair")) {
                    return null;
                }
            }
            if (escolha_medico.equals("1")) {
                // trabalhando com CPF
                System.out.print("Digite o CPF: ");
                String cpf = scanner.nextLine().strip().replace("-", "").replace(".", "");
                while (cpf.isBlank() || cpf.length() != 11) {
                    output("CPF inválido. Digite o CPF ou /menu pra voltar ao menu: ");
                    cpf = scanner.nextLine().strip().replace("-", "").replace(".", "");
                    if(cpf.equals("/menu")) {
                        return null;
                    }
                }
                for (Medico m: medicos) {
                    if (m.getCpf().equals(cpf)) {
                        medico = m;
                        break;
                    }
                }
            } else {
                // trabalhando com CRM
                System.out.print("Digite o CRM: ");
                String crm = scanner.nextLine().strip().replace("-", "").replace(".", "");
                while (crm.isBlank()) {
                    output("CRM inválido. Digite o CRM ou /menu pra voltar ao menu: ");
                    crm = scanner.nextLine().strip().replace("-", "").replace(".", "");
                    if(crm.equals("/menu")) {
                        return null;
                    }
                }
                for (Medico m: medicos) {
                    if (m.getCrm().equals(crm)) {
                        medico = m;
                        break;
                    }
                }
            }
            if(medico != null) {
                if (doenca.isBlank()) {
                    output("Médico(a) " + medico.getNome() + " registrado(a) com sucesso.");
                    break;
                } else {
                    if (medico.especialidade.contains(doenca)) {
                        output("Médico(a) " + medico.getNome() + " registrado(a) com sucesso.");
                        break;
                    } else {
                        output("Esse médico(a) " + medico.getNome() + " não atende consultas de " + doenca + ".");
                    }
                }
            } else {
                output("Não foi possível encontrar médico com esse CPF.");
            }
        }
        return medico;
    }

    private void verConsultasPaciente() {
        Paciente paciente = consultarPaciente();
        if (paciente == null) {
            return;
        }

        ArrayList<Consulta> consultas_paciente = new ArrayList<>();

        for (Consulta c: consultas) {
            if (c.getPaciente() == paciente) {
                consultas_paciente.add(c);
            }
        }

        if (consultas_paciente.isEmpty()) {
            output("Esse paciente não tem consultas marcadas.");
        } else {
            output("CONSULTAS DE " + paciente.getNome());
            for (Consulta c: consultas_paciente) {
                System.out.println(c);
            }
        }
    }

    private void verConsultasMedico() {
        Medico medico = consultarMedico("");
        if (medico == null) {
            return;
        }

        ArrayList<Consulta> consultas_medico = new ArrayList<>();

        for (Consulta c: consultas) {
            if (c.getMedico() == medico) {
                consultas_medico.add(c);
            }
        }

        if (consultas_medico.isEmpty()) {
            output("Esse médico não tem consultas marcadas.");
        } else {
            output("CONSULTAS DE DR(A). " + medico.getNome());
            for (Consulta c: consultas_medico) {
                System.out.println(c);
            }
        }
    }

    private void verConsultas() {
        System.out.print("Digite 1 para ver consultas por médico ou 2 por paciente: ");
        String escolha = scanner.nextLine();

        while (escolha.isBlank() || (!escolha.equals("1") && !escolha.equals("2"))) {
            output("Valor inválido.");
            output("Digite 1 para ver consultas por médico ou 2 por paciente ou /sair pra voltar ao menu: ");
            escolha = scanner.nextLine();
            if(escolha.equals("/sair")) {
                return;
            }
        }

        if (escolha.equals("1")) {
            verConsultasMedico();
        } else {
            verConsultasPaciente();
        }
    }
}
