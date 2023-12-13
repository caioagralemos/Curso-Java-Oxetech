import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;

public class CliniCheck {
    ArrayList<Conta> contas = new ArrayList<>();
    ArrayList<Medico> medicos = new ArrayList<>();
    ArrayList<Paciente> pacientes = new ArrayList<>();
    ArrayList<Consulta> consultas = new ArrayList<>();
    ArrayList<String> cpfs = new ArrayList<>();
    Conta usuario;
    Gson gson = new Gson();
    Scanner scanner = new Scanner(System.in);

    public CliniCheck() {
        try {
            Path path_contas = Paths.get("./data/contas.json");
            byte[] contas_jsonData = Files.readAllBytes(path_contas);
            String contas_json = new String(contas_jsonData);
            contas = gson.fromJson(contas_json, new TypeToken<ArrayList<Conta>>(){}.getType());
        } catch (Exception ignored) {}

        System.out.println("\nBem vindo(a) ao CliniCheck.");
        System.out.println("CliniCheck é uma aplicação de recepção de policlínica.");
        System.out.println("Use-o para gerenciar e marcar novas consultas.\n");

        while (true) {
            boolean off = false;
            boolean acc_found = false;
            System.out.print("Digite o nome de usuário: ");
            String username = scanner.nextLine().strip().toLowerCase();

            if (!contas.isEmpty()) {
                for(Conta c: contas) {
                    if (c.user.equals(username)) {
                        if (c.login()) {
                            usuario = c;
                            off = true;
                            acc_found = true;
                            break;
                        } else {
                            output("Senha incorreta. Tente novamente\n");
                        }
                    }
                }
            } else {
                output("Não foi encontrada uma conta com esse nome. Criando nova conta");
                System.out.print("Digite sua nova senha: ");
                String password = scanner.nextLine();
                try {
                    Conta novo_user = new Conta(username, password);
                    contas.add(novo_user);
                    usuario = novo_user;
                    off = true;
                    break;
                } catch (Exception e) {
                    output("Algo deu errado. Tente novamente");
                }
            }
            if (!acc_found) {
                output("Não foi encontrada uma conta com esse nome. Criando nova conta");
                System.out.print("Digite sua nova senha: ");
                String password = scanner.nextLine();
                try {
                    Conta novo_user = new Conta(username, password);
                    contas.add(novo_user);
                    usuario = novo_user;
                    off = true;
                    break;
                } catch (Exception e) {
                    output("Algo deu errado. Tente novamente");
                }
            }
            if (off) { break; }
        }

        try {
            Path path_medicos = Paths.get("./data/" + usuario.user + "/m.json");
            byte[] medicos_jsonData = Files.readAllBytes(path_medicos);
            String medicos_json = new String(medicos_jsonData);
            medicos = gson.fromJson(medicos_json, new TypeToken<ArrayList<Medico>>(){}.getType());
        } catch (Exception ignored) {}

        try {
            Path path_pacientes = Paths.get("./data/" + usuario.user + "/p.json");
            byte[] pacientes_jsonData = Files.readAllBytes(path_pacientes);
            String pacientes_json = new String(pacientes_jsonData);
            pacientes = gson.fromJson(pacientes_json, new TypeToken<ArrayList<Paciente>>(){}.getType());
        } catch (Exception ignored) {}

        try {
            Path path_consultas = Paths.get("./data/" + usuario.user + "/c.json");
            byte[] consultas_jsonData = Files.readAllBytes(path_consultas);
            String consultas_json = new String(consultas_jsonData);
            consultas = gson.fromJson(consultas_json, new TypeToken<ArrayList<Consulta>>(){}.getType());
        } catch (Exception ignored) {}

        try {
            Path path_cpfs = Paths.get("./data/" + usuario.user + "/cpfs.json");
            byte[] cpfs_jsonData = Files.readAllBytes(path_cpfs);
            String cpfs_json = new String(cpfs_jsonData);
            cpfs = gson.fromJson(cpfs_json, new TypeToken<ArrayList<String>>(){}.getType());
        } catch (Exception ignored) {}

        userInterface();
    }

    private void output(String texto) {
        System.out.println("CliniCheck diz: " + texto);
    }

    private void userInterface() {
        output("Logado com sucesso! Bem vindo(a), " + usuario.user);

        while (true) {
            System.out.println();
            output("Você pode me controlar usando os seguintes comandos:\n");

            System.out.println("1 - Marcar Consulta");
            System.out.println("2 - Ver Consultas");
            System.out.println("3 - Adicionar Médico");
            System.out.println("4 - Adicionar Paciente");
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

                    File dataFolder = new File("./data");
                    if (!dataFolder.exists()) {
                        dataFolder.mkdir();
                    }

                    File userFolder = new File("./data/" + this.usuario.user);
                    if(!userFolder.exists()) {
                        userFolder.mkdir();
                    }

                    if(!contas.isEmpty()) {
                        String json_contas = gson.toJson(contas);
                        try (FileWriter fileWriter = new FileWriter("./data/contas.json")) {
                            fileWriter.write(json_contas);
                        } catch (IOException e) {
                            output("Não foi possível salvar suas contas.");
                        }
                    }

                    if(!consultas.isEmpty()) {
                        String json_consultas = gson.toJson(consultas);
                        try (FileWriter fileWriter = new FileWriter("./data/" + usuario.user + "/c.json")) {
                            fileWriter.write(json_consultas);
                        } catch (IOException e) {
                            output("Não foi possível salvar suas consultas.");
                        }
                    }

                    if(!medicos.isEmpty()) {
                        String json_medicos = gson.toJson(medicos);
                        try (FileWriter fileWriter = new FileWriter("./data/" + usuario.user + "/m.json")) {
                            fileWriter.write(json_medicos);
                        } catch (IOException e) {
                            output("Não foi possível salvar seus médicos.");
                        }
                    }

                    if (!pacientes.isEmpty()) {
                        String json_pacientes = gson.toJson(pacientes);
                        try (FileWriter fileWriter = new FileWriter("./data/" + usuario.user + "/p.json")) {
                            fileWriter.write(json_pacientes);
                        } catch (IOException e) {
                            output("Não foi possível salvar seus pacientes.");
                        }
                    }

                    if (!cpfs.isEmpty()) {
                        String json_cpfs = gson.toJson(cpfs);
                        try (FileWriter fileWriter = new FileWriter("./data/" + usuario.user + "/cpfs.json")) {
                            fileWriter.write(json_cpfs);
                        } catch (IOException e) {
                            output("Não foi possível salvar seus CPFS.");
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

        if (cpfs.contains(cpf)) {
            output("Esse CPF já possui cadastro no sistema.");
            return;
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
                if(!especialidades.contains(especialidade)) {
                    contador++;
                    especialidades.add(especialidade);
                } else {
                    output("Médico já possui essa especialidade.");
                }
            }
        }

        Medico novo_medico = new Medico(nome, cpf, crm, salario, especialidades, new ArrayList<>());
        medicos.add(novo_medico);
        cpfs.add(cpf);
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

        if (cpfs.contains(cpf)) {
            output("Esse CPF já possui cadastro no sistema.");
            return;
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
                if(!laudo.contains(doenca)) {
                    contador++;
                    laudo.add(doenca);
                } else {
                    output("Paciente já tem essa doença registrada no laudo.");
                }
            }
        }

        Paciente novo_paciente = new Paciente(nome, cpf, cartao_sus, laudo, new ArrayList<>());
        cpfs.add(cpf);
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
                        System.out.println();
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
        System.out.println();

        // Data
        Data data_da_consulta;
        while (true) {
            try {
                output("Digite o dia de sua consulta: ");
                int dia = Integer.parseInt(scanner.nextLine());

                output("Digite o mês de sua consulta: ");
                int mes = Integer.parseInt(scanner.nextLine());

                output("Digite o ano de sua consulta: ");
                int ano = Integer.parseInt(scanner.nextLine());

                data_da_consulta = new Data(dia, mes, ano);
                System.out.println();
                break;
            } catch (Error e) {
                output("Algo deu errado. Tente novamente");
            }
        }

        ArrayList<Integer> horas_disponiveis = new ArrayList<>();
        horas_disponiveis.add(8);
        horas_disponiveis.add(9);
        horas_disponiveis.add(10);
        horas_disponiveis.add(11);
        horas_disponiveis.add(14);
        horas_disponiveis.add(15);
        horas_disponiveis.add(16);
        horas_disponiveis.add(17);

        ArrayList<Integer> horas_indisponiveis = new ArrayList<>();

        for (Data d: medico.horarios_marcados) {
            if(d.dia == data_da_consulta.dia && d.mes == data_da_consulta.mes && d.ano == data_da_consulta.ano) {
                if (horas_disponiveis.contains(d.hora)) {
                    horas_indisponiveis.add(d.hora);
                }
            }
        }

        if (!horas_indisponiveis.isEmpty()) {
            for (Integer hora: horas_indisponiveis) {
                horas_disponiveis.remove(hora);
            }
        }

        if (horas_disponiveis.isEmpty()) {
            output("Esse médico não tem horários disponiveis nesse dia. Tente novamente mais tarde ou com outra data.\n");
            return;
        }

        while (true) {
            try {
                output("Horas disponíveis:");
                int contador = 1;
                for (Integer hora: horas_disponiveis) {
                    if (hora < 10) {
                        System.out.print(contador + " -  0" + hora + ":00 |");
                    } else {
                        System.out.print(contador + " - " + hora + ":00 |");
                    }
                    contador++;
                }

                System.out.print(" Escolha seu horário pelo índice: ");
                int choice = Integer.parseInt(scanner.nextLine());
                data_da_consulta.setHora(horas_disponiveis.get(choice-1));

                if (!paciente.horarios_marcados.contains(data_da_consulta)) {
                    medico.horarios_marcados.add(data_da_consulta);
                    paciente.horarios_marcados.add(data_da_consulta);
                    break;
                } else {
                    throw new Error();
                }
            } catch (Error e) {
                output("Algo deu errado. Tente novamente\n");
            }
        }

        usuario.setContador();
        Consulta nova_consulta = new Consulta(usuario.contador_consultas, medico, paciente, doenca, data_da_consulta);
        consultas.add(nova_consulta);
        output("Consulta adicionada com sucesso.\n");
        System.out.println(nova_consulta);
    }

    private Paciente consultarPaciente() {
        if (pacientes.isEmpty()) {
            output("Não há pacientes cadastrados!");
            return null;
        }
        Paciente paciente = null;
        while (true) {
            System.out.print("Identificando Paciente - digite 1 para CPF ou 2 para SUS: ");
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
                output("Paciente " + paciente.getNome() + " registrado com sucesso.\n");
                break;
            } else {
                output("Não foi possível encontrar paciente com esse CPF ou cartão SUS.\n");
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
            System.out.print("Identificando Médico - digite 1 para CPF ou 2 para CRM: ");
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
                    output("Médico(a) " + medico.getNome() + " registrado(a) com sucesso.\n");
                    break;
                } else {
                    if (medico.especialidade.contains(doenca)) {
                        output("Médico(a) " + medico.getNome() + " registrado(a) com sucesso.\n");
                        break;
                    } else {
                        output("Esse médico(a) " + medico.getNome() + " não atende consultas de " + doenca + ".\n");
                    }
                }
            } else {
                output("Não foi possível encontrar médico com esse CPF ou CRM.\n");
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
        System.out.println("5 - Remover Médicos");
        System.out.println("6 - Remover Pacientes");
        System.out.println("7 - Remover Consultas\n");

        System.out.print("Digite aqui: ");
        String escolha = scanner.nextLine();

        while (escolha.isBlank() || (!escolha.equals("1") && !escolha.equals("2")
                && !escolha.equals("3") && !escolha.equals("4") && !escolha.equals("5")
                && !escolha.equals("6") && !escolha.equals("7"))) {
            output("Valor inválido.");
            output("Escolha sua ação ou /menu pra voltar ao menu:\n");

            System.out.println("1 - Ver Médicos");
            System.out.println("2 - Ver Pacientes");
            System.out.println("3 - Editar Médicos (especialidades)");
            System.out.println("4 - Editar Pacientes (laudos)");
            System.out.println("5 - Excluir Médicos");
            System.out.println("6 - Excluir Pacientes");
            System.out.println("7 - Remover Consultas\n");

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
            case "5" -> excluirMedico();
            case "6" -> excluirPaciente();
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
                    if(!medico.especialidade.contains(especialidade)) {
                        contador++;
                        medico.especialidade.add(especialidade);
                    } else {
                        output("Médico já possui essa especialidade.");
                    }
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
                            output("Especialidade removida com sucesso.");
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
                    if(!paciente.laudo.contains(doenca)) {
                        contador++;
                        paciente.laudo.add(doenca);
                    } else {
                        output("Paciente já tem essa doença registrada no laudo.");
                    }
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
                            output("Doença removida com sucesso.");
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

    private void excluirMedico() {
        Medico medico = consultarMedico("");
        if (medico == null) {
            return;
        }

        ArrayList<Consulta> consultas_a_remover = new ArrayList<>();

        for(Consulta c: consultas) {
            if (c.getMedico().getCpf().equals(medico.getCpf())) {
                consultas_a_remover.add(c);
            }
        }

        if(!consultas_a_remover.isEmpty()) {
            for(Consulta c: consultas_a_remover) {
                Paciente p = c.getPaciente();
                Data d = c.getData();
                p.horarios_marcados.remove(d);
                consultas.remove(c);
            }
        }

        medicos.remove(medico);

        output("Médico excluído com sucesso.");
    }

    private void excluirPaciente() {
        Paciente paciente = consultarPaciente();
        if (paciente == null) {
            return;
        }

        ArrayList<Consulta> consultas_a_remover = new ArrayList<>();

        for(Consulta c: consultas) {
            if (c.getPaciente().getCpf().equals(paciente.getCpf())) {
                consultas_a_remover.add(c);
            }
        }

        if(!consultas_a_remover.isEmpty()) {
            for(Consulta c: consultas_a_remover) {
                Medico m = c.getMedico();
                Data d = c.getData();
                m.horarios_marcados.remove(d);
                consultas.remove(c);
            }
        }

        pacientes.remove(paciente);

        output("Paciente excluído com sucesso.");
    }
}