import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;

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

            System.out.println("1 - Marcar Consulta (faltam datas)");
            System.out.println("2 - Ver Consultas");
            System.out.println("3 - Adicionar Médicos");
            System.out.println("4 - Adicionar Pacientes");
            System.out.println("5 - Gerenciar Dados");
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
                gerenciarUsuarios();
            } else {
                System.out.println();
                output("Digite S para confirmar sua saída do programa: ");
                String escolha2 = scanner.nextLine().strip().toUpperCase();
                if (escolha2.equals("S")) {
                    output("Tentando salvar os seus dados...");

                    if(!consultas.isEmpty()) {
                        String json_consultas = gson.toJson(consultas);
                        try (FileWriter fileWriter = new FileWriter("./consultas.json")) {
                            fileWriter.write(json_consultas);
                            output("Consultas salvas com sucesso.");
                        } catch (IOException e) {
                            output("Não foi possível salvar suas consultas.");
                        }
                    }

                    if(!medicos.isEmpty()) {
                        String json_medicos = gson.toJson(medicos);
                        try (FileWriter fileWriter = new FileWriter("./medicos.json")) {
                            fileWriter.write(json_medicos);
                            output("Médicos salvos com sucesso.");
                        } catch (IOException e) {
                            output("Não foi possível salvar seus médicos.");
                        }
                    }

                    if (!pacientes.isEmpty()) {
                        String json_pacientes = gson.toJson(pacientes);
                        try (FileWriter fileWriter = new FileWriter("./pacientes.json")) {
                            fileWriter.write(json_pacientes);
                            output("Pacientes salvos com sucesso.");
                        } catch (IOException e) {
                            output("Não foi possível salvar seus pacientes.");
                        }
                    }

                    output("Obrigado por utilizar o CliniCheck.");
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
        output("Médico adicionado com sucesso.");
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
        output("Paciente adicionado com sucesso.");
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
                contador++;
            }
            int escolha;
            while (true) {
                try {
                    System.out.print("Digite aqui: ");
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

        Consulta nova_consulta = new Consulta(consultas.size(), medico, paciente, doenca, data);
        consultas.add(nova_consulta);
        output("Consulta adicionada com sucesso.");
        System.out.println(nova_consulta);
    }

    private Paciente consultarPaciente() {
        if (pacientes.isEmpty()) {
            output("Não há pacientes cadastrados!");
            return null;
        }
        Paciente paciente = null;
        while (true) {
            output("Identificando Paciente - digite 1 para CPF ou 2 para SUS: ");
            String escolha_paciente = scanner.nextLine().strip();
            while (!escolha_paciente.equals("1") && !escolha_paciente.equals("2")) {
                output("Algo deu errado.");
                output("Identificando Paciente - digite 1 para CPF ou 2 para SUS ou /menu pra sair: ");
                escolha_paciente = scanner.nextLine().strip();
                if (escolha_paciente.equals("/menu")) {
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
                output("Não foi possível encontrar paciente com esse CPF ou cartão SUS.");
            }
        }
        return paciente;
    }

    private Medico consultarMedico(String doenca) {
        if (medicos.isEmpty()) {
            output("Não há médicos cadastrados!");
            return null;
        }
        Medico medico = null;
        while (true) {
            output("Identificando Médico - digite 1 para CPF ou 2 para CRM: ");
            String escolha_medico = scanner.nextLine().strip();
            while (!escolha_medico.equals("1") && !escolha_medico.equals("2")) {
                output("Algo deu errado.");
                output("Identificando Médico - digite 1 para CPF ou 2 para CRM ou /menu pra sair: ");
                escolha_medico = scanner.nextLine().strip();
                if (escolha_medico.equals("/menu")) {
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
                output("Não foi possível encontrar médico com esse CPF ou CRM.");
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
            if (c.getPaciente().getCpf().equals(paciente.getCpf()) && c.getPaciente().getSUS().equals(paciente.getSUS())) {
                consultas_paciente.add(c);
            }
        }

        if (consultas_paciente.isEmpty()) {
            output("Esse paciente não tem consultas marcadas.");
        } else {
            output("CONSULTAS DE " + paciente.getNome().toUpperCase());
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
            if (c.getMedico().getCrm().equals(medico.getCrm()) && c.getMedico().getCpf().equals(medico.getCpf())) {
                consultas_medico.add(c);
            }
        }

        if (consultas_medico.isEmpty()) {
            output("Esse médico não tem consultas marcadas.");
        } else {
            output("CONSULTAS DE DR(A). " + medico.getNome().toUpperCase());
            for (Consulta c: consultas_medico) {
                System.out.println(c);
            }
        }
    }

    private void verConsultas() {
        if (consultas.isEmpty()) {
            output("Não há consultas marcadas!");
            return;
        }

        System.out.print("Digite 1 para ver consultas por médico ou 2 por paciente: ");
        String escolha = scanner.nextLine();

        while (escolha.isBlank() || (!escolha.equals("1") && !escolha.equals("2"))) {
            output("Valor inválido.");
            output("Digite 1 para ver consultas por médico ou 2 por paciente ou /menu pra voltar ao menu: ");
            escolha = scanner.nextLine();
            if(escolha.equals("/menu")) {
                return;
            }
        }

        if (escolha.equals("1")) {
            verConsultasMedico();
        } else {
            verConsultasPaciente();
        }
    }

    private void gerenciarUsuarios() {
        if (pacientes.isEmpty() && medicos.isEmpty()) {
            output("Não há usuários cadastrados!");
            return;
        }

        System.out.println();
        output("Escolha sua ação:\n");

        System.out.println("1 - Ver Médicos");
        System.out.println("2 - Ver Pacientes");
        System.out.println("3 - Editar Médicos (especialidades)");
        System.out.println("4 - Editar Pacientes (laudos)");
        System.out.println("5 - Remover Consultas");

        System.out.print("Digite aqui: ");
        String escolha = scanner.nextLine();

        while (escolha.isBlank() || (!escolha.equals("1") && !escolha.equals("2")
                && !escolha.equals("3") && !escolha.equals("4") && !escolha.equals("5"))) {
            output("Valor inválido.");
            output("Escolha sua ação ou /menu pra voltar ao menu:\n");

            System.out.println("1 - Ver Médicos");
            System.out.println("2 - Ver Pacientes");
            System.out.println("3 - Editar Médicos (especialidades)");
            System.out.println("4 - Editar Pacientes (laudos)");
            System.out.println("5 - Remover Consultas");

            System.out.print("Digite aqui: ");
            escolha = scanner.nextLine();
            if(escolha.equals("/menu")) {
                return;
            }
        }

        switch (escolha) {
            case "1" -> verMedicos();
            case "2" -> verPacientes();
            case "3" -> editarMedicos();
            case "4" -> editarPacientes();
            default -> removerConsultas();
        }
    }

    private void verPacientes() {
        pacientes.sort(Comparator.comparing(Paciente::getNome));

        System.out.println();
        output("MOSTRANDO PACIENTES - ");
        int contador = 1;
        for (Paciente p: pacientes) {
            System.out.println("Paciente " + contador + ": " + p);
            contador++;
        }
    }

    private void verMedicos() {
        medicos.sort(Comparator.comparing(Medico::getNome));

        System.out.println();
        output("MOSTRANDO MÉDICOS - ");
        int contador = 1;
        for (Medico m: medicos) {
            System.out.println("Médico " + contador + ": " + m);
            contador++;
        }
    }

    private void editarMedicos() {
        System.out.println();
        output("EDITANDO MÉDICOS - ");

        if (medicos.isEmpty()) {
            output("Não há médicos cadastrados!");
            return;
        }

        Medico medico = consultarMedico("");
        if (medico == null) {
            return;
        }

        System.out.print("Escolha 1 para adicionar especialidades ou 2 para remover especialidades: ");
        String escolha = scanner.nextLine();

        while (!escolha.equals("1") && !escolha.equals("2")) {
            output("Escolha inválida. Digite /menu pra voltar ao menu.");
            System.out.print("Escolha 1 para adicionar especialidades ou 2 para remover especialidades: ");
            escolha = scanner.nextLine();

            if(escolha.equals("/menu")) {
                return;
            }
        }

        if(escolha.equals("1")) {
            output("Digite as especialidades do médico e digite -1 quando tiver concluído: ");
            int contador = medico.especialidade.size();
            while (true) {
                System.out.print("Digite aqui (Especialidade nº" + contador + "): ");
                String especialidade = scanner.nextLine().strip().toUpperCase();

                if(especialidade.equals("-1")) {
                    break;
                }

                if (!especialidade.isBlank()) {
                    contador++;
                    medico.especialidade.add(especialidade);
                }
            }
        } else {
            if (medico.especialidade.isEmpty()) {
                output("Médico não tem nenhuma especialidade.");
                return;
            } else {
                output("Escolha a especialidade do médico a ser removida: ");
                int contador = 0;
                for (String d : medico.especialidade) {
                    System.out.println((contador + 1) + " - " + d);
                    contador++;
                }
                int escolha2;
                while (true) {
                    try {
                        System.out.print("Digite aqui: ");
                        escolha2 = Integer.parseInt(scanner.nextLine().strip());
                        if(escolha2 <= (medico.especialidade.size()+1) && escolha2 > 0) {
                            medico.especialidade.remove(escolha2-1);
                            break;
                        } else {
                            throw new Exception();
                        }
                    } catch (Exception e) {
                        output("Algo deu errado.\n");
                        output("Escolha a especialidade do médico a ser removida: ");
                    }
                }
            }
        }
    }

    private void editarPacientes() {
        System.out.println();
        output("EDITANDO PACIENTES - ");

        if (pacientes.isEmpty()) {
            return;
        }

        Paciente paciente = consultarPaciente();
        if (paciente == null) {
            output("Algo deu errado.");
            return;
        }

        System.out.print("Escolha 1 para adicionar uma doença ou 2 para remover uma doença: ");
        String escolha = scanner.nextLine();

        while (!escolha.equals("1") && !escolha.equals("2")) {
            output("Escolha inválida. Digite /menu pra voltar ao menu.");
            System.out.print("Escolha 1 para adicionar uma doença ou 2 para remover uma doença: ");
            escolha = scanner.nextLine();

            if(escolha.equals("/menu")) {
                return;
            }
        }

        if(escolha.equals("1")) {
            output("Digite as doenças a serem adicionadas e digite -1 quando tiver concluído: ");
            int contador = paciente.laudo.size();
            while (true) {
                System.out.print("Digite aqui (Especialidade nº" + contador + "): ");
                String doenca = scanner.nextLine().strip().toUpperCase();

                if(doenca.equals("-1")) {
                    break;
                }

                if (!doenca.isBlank()) {
                    contador++;
                    paciente.laudo.add(doenca);
                }
            }
        } else {
            if (paciente.laudo.isEmpty()) {
                output("Paciente não possuí doenças no laudo.");
                return;
            } else {
                output("Escolha a doença a ser removida: ");
                int contador = 0;
                for (String d : paciente.laudo) {
                    System.out.println((contador + 1) + " - " + d);
                    contador++;
                }
                int escolha2;
                while (true) {
                    try {
                        System.out.print("Digite aqui: ");
                        escolha2 = Integer.parseInt(scanner.nextLine().strip());
                        if(escolha2 <= (paciente.laudo.size()+1) && escolha2 > 0) {
                            paciente.laudo.remove(escolha2-1);
                            break;
                        } else {
                            throw new Exception();
                        }
                    } catch (Exception e) {
                        output("Algo deu errado.\n");
                        output("Escolha a doença a ser removida: ");
                    }
                }
            }
        }
    }

    private void removerConsultas() {
        if (consultas.isEmpty()) {
            output("Você não tem consultas registradas!");
            return;
        }

        System.out.println();
        output("REMOVENDO CONSULTAS - ");
        while (true) {
            for (Consulta c: consultas) {
                System.out.println(c);
            }

            output("Digite o ID da consulta que deseja remover ou -1 para sair");
            System.out.print("Digite aqui: ");

            try {
                int escolha = Integer.parseInt(scanner.nextLine());
                if (escolha == -1) {
                    return;
                }
                int contador = 0;
                for (Consulta c: consultas) {
                    if(c.getId() == escolha) {
                        consultas.remove(contador);
                        output("Consulta removida com sucesso.\n");
                        break;
                    }
                    contador++;
                }
            } catch (Exception e) {
                output("Algo deu errado. Tente novamente");
            }
        }
    }
}
