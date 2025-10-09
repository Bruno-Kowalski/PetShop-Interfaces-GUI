package clinica.petshop.controles;

import clinica.petshop.modelos.Cliente;
import clinica.petshop.modelos.Pet;
import clinica.petshop.pacote.PacoteServico;
import clinica.petshop.servico.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ControleServico {
    private Scanner scanner;
    private ControlePet controlePet;
    private ControleCliente controleCliente;
    private List<Servico> listaServicos;
    private List<PacoteServico> listaPacotes;

    public ControleServico(Scanner scanner, ControlePet controlePet, ControleCliente controleCliente) {
        this.scanner = scanner;
        this.controlePet = controlePet;
        this.controleCliente = controleCliente;
        this.listaServicos = new ArrayList<>();
        this.listaPacotes = new ArrayList<>();
    }

    // Serviço avulso
    public void contratarServicoAvulso() {
        System.out.println("\n==== Contratar Serviço Avulso ====");

        // Pede o CPF do cliente
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();
        System.out.println();
        Cliente cliente = controleCliente.buscarClientePorCpf(cpf);

        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }

        // Lista os pets do cliente
        System.out.println();
        System.out.println("Pets cadastrados deste cliente:");
        for (Pet p : controlePet.getListaPet()) {
            if (p.getDono().getCpf().equals(cliente.getCpf())) {
                System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome());
            }
        }

        // Seleciona o pet
        System.out.println();
        System.out.print("Digite o ID do pet para o serviço: ");
        int idPet = scanner.nextInt();
        scanner.nextLine();

        Pet petSelecionado = null;
        for (Pet p : controlePet.getListaPet()) {
            if (p.getId() == idPet && p.getDono().getCpf().equals(cliente.getCpf())) {
                petSelecionado = p;
                break;
            }
        }

        if (petSelecionado == null) {
            System.out.println("Pet não encontrado ou não pertence ao cliente!");
            return;
        }

        // Escolhe o serviço
        System.out.println("Selecione o serviço:");
        System.out.println("1 - Banho e Tosa");
        System.out.println("2 - Consulta Veterinária");
        System.out.println("3 - Hospedagem");
        System.out.println("4 - Adestramento");
        System.out.print("Opção: ");
        int tipoServico = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        contratarServico(tipoServico, petSelecionado);
    }

    private void contratarServico(int tipoServico, Pet petSelecionado) {
        LocalDate data = pedirData();
        if (data == null) return;

        Servico servico = criarServico(tipoServico, petSelecionado, data);

        if (servico != null) {
            listaServicos.add(servico);
            System.out.println("Serviço contratado com sucesso!");
            System.out.println("" + servico.getDescricao() + " | Pet: " + petSelecionado.getNome() + " | Data: " + servico.getDataAgendamento() + " | Valor: R$ " + servico.calcularPreco());
        } else {
            System.out.println("Não foi possível contratar o serviço.");
        }
    }

    // Pacote de serviços com desconto
    public void contratarPacote() {
        System.out.println("\n==== Contratar Pacote de Serviços ====");

        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();
        Cliente cliente = controleCliente.buscarClientePorCpf(cpf);

        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }

        // Lista os pets do cliente
        System.out.println();
        System.out.println("Pets cadastrados deste cliente:");
        for (Pet p : controlePet.getListaPet()) {
            if (p.getDono().getCpf().equals(cliente.getCpf())) {
                System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome());
            }
        }

        // Seleciona o pet
        System.out.println();
        System.out.print("Digite o ID do pet: ");
        int idPet = scanner.nextInt();
        scanner.nextLine();

        Pet petSelecionado = null;
        for (Pet p : controlePet.getListaPet()) {
            if (p.getId() == idPet && p.getDono().getCpf().equals(cliente.getCpf())) {
                petSelecionado = p;
                break;
            }
        }
        if (petSelecionado == null) {
            System.out.println("Pet não encontrado ou não pertence ao cliente!");
            return;
        }

        // Pede a data do pacote
        LocalDate data = pedirData();
        if (data == null) return;

        // Seleção dos serviços do pacote
        List<Servico> servicosSelecionados = new ArrayList<>();
        int opcao;
        do {
            System.out.println("\nEscolha os serviços para incluir no pacote (0 para finalizar):");
            System.out.println("1 - Banho e Tosa");
            System.out.println("2 - Consulta Veterinária");
            System.out.println("3 - Hospedagem");
            System.out.println("4 - Adestramento");
            System.out.print("Opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    servicosSelecionados.add(new BanhoTosa(data, petSelecionado));
                    break;
                case 2:
                    servicosSelecionados.add(new ConsultaVeterinaria(data, petSelecionado));
                    break;
                case 3:
                    System.out.print("Quantos dias de hospedagem? ");
                    int dias = scanner.nextInt();
                    scanner.nextLine();
                    servicosSelecionados.add(new Hospedagem(data, petSelecionado, dias));
                    break;
                case 4:
                    servicosSelecionados.add(new Adestramento(data, petSelecionado));
                    break;
                case 0:
                    System.out.println("Finalizando seleção de serviços...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        if (servicosSelecionados.isEmpty()) {
            System.out.println("Nenhum serviço selecionado. Pacote cancelado.");
            return;
        }

        // Criar pacote com 10% de desconto, armazenando cliente e pet
        System.out.println();
        PacoteServico pacote = new PacoteServico("Personalizado", servicosSelecionados, 10, cliente, petSelecionado);

        // Adiciona o pacote à lista de pacotes
        listaPacotes.add(pacote);

        // Exibir detalhes do pacote
        System.out.println("\nPacote contratado com sucesso! Serviços incluídos:");
        for (Servico s : pacote.getServicos()) {
            System.out.println("" + s.getDescricao() + " | Valor: R$ " + s.calcularPreco());
        }
        System.out.println("Valor total com desconto: R$ " + pacote.calcularPreco());
    }

    public void listarServicos() {
        List<String> clientesListados = new ArrayList<>();

        // Adiciona clientes de serviços avulsos
        for (Servico s : listaServicos) {
            if (!clientesListados.contains(s.getPet().getDono().getCpf())) {
                clientesListados.add(s.getPet().getDono().getCpf());
            }
        }

        // Adiciona clientes de pacotes
        for (PacoteServico p : listaPacotes) {
            if (!clientesListados.contains(p.getCliente().getCpf())) {
                clientesListados.add(p.getCliente().getCpf());
            }
        }

        for (String cpfCliente : clientesListados) {
            Cliente cliente = controleCliente.buscarClientePorCpf(cpfCliente);
            if (cliente == null) continue;

            System.out.println("\nCliente: " + cliente.getNome() + " | CPF: " + cliente.getCpf());
            double totalCliente = 0;

            // Serviços avulsos
            System.out.println("============================= Serviços Avulsos ============================= ");
            for (Servico serv : listaServicos) {
                if (serv.getPet().getDono().getCpf().equals(cpfCliente)) {
                    System.out.println("Serviço: " + serv.getDescricao() + " | Pet: " + serv.getPet().getNome() + " | Data: " + serv.getDataAgendamento() + " | Valor: R$ " + serv.calcularPreco());
                    totalCliente += serv.calcularPreco();
                    System.out.println();
                    System.out.println("Total do cliente: R$ " + totalCliente);
                }
            }

            // Pacotes
            System.out.println("\n========================= Pacotes de Serviços ========================= ");
            for (PacoteServico p : listaPacotes) {
                if (p.getCliente().getCpf().equals(cpfCliente)) {
                    System.out.println("Pacote: " + p.getNome() + " | Pet: " + p.getPet().getNome() + " | Data: " + p.getDataAgendamento());
                    for (Servico serv : p.getServicos()) {
                        System.out.println("" + serv.getDescricao() + " | Valor: R$ " + serv.calcularPreco());
                    }
                    double precoPacote = p.calcularPreco();
                    System.out.println();
                    System.out.println("Total pacote (com desconto): R$ " + precoPacote);
                    System.out.println();
                    totalCliente += precoPacote;
                }
            }
        }
    }

    private LocalDate pedirData() {
        System.out.print("Digite a data (yyyy-MM-dd): ");
        LocalDate data = LocalDate.parse(scanner.nextLine());
        if (data.isBefore(LocalDate.now())) {
            System.out.println("Não é possível agendar para datas passadas!");
            return null;
        }
        return data;
    }

    private Servico criarServico(int tipo, Pet pet, LocalDate data) {
        switch (tipo) {
            case 1:
                return new BanhoTosa(data, pet);
            case 2:
                return new ConsultaVeterinaria(data, pet);
            case 3:
                System.out.print("Quantos dias de hospedagem? ");
                int dias = scanner.nextInt();
                scanner.nextLine();
                return new Hospedagem(data, pet, dias);
            case 4:
                return new Adestramento(data, pet);
            default:
                System.out.println("Serviço inválido!");
                return null;
        }
    }

    public void buscarServicos() {
        System.out.println("\n==== Buscar Serviços/Pacotes por CPF ====");
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();

        Cliente cliente = controleCliente.buscarClientePorCpf(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }

        boolean encontrou = false;
        double totalCliente = 0;

        System.out.println("\nCliente: " + cliente.getNome() + " | CPF: " + cliente.getCpf());

        // Serviços avulsos
        System.out.println("\n  ======================= Serviços Avulsos ======================= ");
        for (Servico serv : listaServicos) {
            if (serv.getPet().getDono().getCpf().equals(cpf)) {
                System.out.println("Serviço: " + serv.getDescricao() + " | Pet: " + serv.getPet().getNome() + " | Data: " + serv.getDataAgendamento() + " | Valor: R$ " + serv.calcularPreco());
                totalCliente += serv.calcularPreco();
                encontrou = true;
            }
        }

        // Pacotes
        System.out.println("\n  ======================= Pacotes de Serviços ======================= ");
        for (PacoteServico p : listaPacotes) {
            if (p.getCliente().getCpf().equals(cpf)) {
                System.out.println("Pacote: " + p.getNome() + " | Pet: " + p.getPet().getNome() + " | Data: " + p.getDataAgendamento());
                for (Servico serv : p.getServicos()) {
                    System.out.println("" + serv.getDescricao() + " | Valor: R$ " + serv.calcularPreco());
                }
                double precoPacote = p.calcularPreco();
                System.out.println("Total pacote (com desconto): R$ " + precoPacote);
                totalCliente += precoPacote;
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum serviço ou pacote encontrado para este CPF.");
        } else {
            System.out.println("Total do cliente: R$ " + totalCliente);
        }
    }

    public void cancelarServicoOuPacote() {
        System.out.println("\n==== Cancelar Serviço ou Pacote ====");
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();

        Cliente cliente = controleCliente.buscarClientePorCpf(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }

        // Mostrar serviços
        System.out.println("\nServiços avulsos deste cliente:");
        int index = 1;
        List<Object> itens = new ArrayList<>(); // Lista para guardar os itens (serviço ou pacote)

        for (Servico serv : listaServicos) {
            if (serv.getPet().getDono().getCpf().equals(cpf)) {
                System.out.println(index + " - Serviço: " + serv.getDescricao() + " | Pet: " + serv.getPet().getNome() + " | Data: " + serv.getDataAgendamento() + " | Valor: R$ " + serv.calcularPreco());
                itens.add(serv);
                index++;
            }
        }

        // Mostrar pacotes
        System.out.println("\nPacotes deste cliente:");
        for (PacoteServico p : listaPacotes) {
            if (p.getCliente().getCpf().equals(cpf)) {
                System.out.println(index + " - Pacote: " + p.getNome() + " | Pet: " + p.getPet().getNome() + " | Data: " + p.getDataAgendamento() + " | Valor Total (c/ desconto): R$ " + p.calcularPreco());
                itens.add(p);
                index++;
            }
        }

        if (itens.isEmpty()) {
            System.out.println("Nenhum serviço ou pacote encontrado para este cliente.");
            return;
        }

        // Escolher item para excluir
        System.out.print("\nDigite o número do item que deseja cancelar (0 para voltar): ");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha == 0) {
            System.out.println("Operação cancelada.");
            return;
        }

        if (escolha < 1 || escolha > itens.size()) {
            System.out.println("Opção inválida!");
            return;
        }

        Object item = itens.get(escolha - 1);

        if (item instanceof Servico) {
            listaServicos.remove(item);
            System.out.println("Serviço cancelado com sucesso!");
        } else if (item instanceof PacoteServico) {
            listaPacotes.remove(item);
            System.out.println("Pacote cancelado com sucesso!");
        }
    }
}