package clinica.petshop.menu;

import clinica.petshop.controles.ControleCliente;
import clinica.petshop.controles.ControlePet;
import clinica.petshop.controles.ControleServico;

import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private ControleCliente controleCliente;
    private ControlePet controlePet;
    private ControleServico controleServico;

    public Menu() {
        this.controleCliente = new ControleCliente(scanner);
        this.controlePet = new ControlePet(scanner, controleCliente);
        this.controleServico = new ControleServico(scanner, controlePet, controleCliente);
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n======= BEM-VINDO À NOSSA PETCLÍNICA =======");
            System.out.println("1 - Clientes");
            System.out.println("2 - Pets");
            System.out.println("3 - Serviços");
            System.out.println("4 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = lerOpcao();

            switch (opcao) {
                case 1 -> menuClientes();
                case 2 -> menuPets();
                case 3 -> menuServicos();
                case 4 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 4);

        scanner.close();
    }

    private void menuClientes() {
        int opcao;
        do {
            System.out.println("\n--- Menu Clientes ---");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Listar Clientes");
            System.out.println("3 - Buscar Cliente");
            System.out.println("4 - Excluir Cliente");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = lerOpcao();

            switch (opcao) {
                case 1 -> controleCliente.cadastrarCliente();
                case 2 -> controleCliente.listarClientes();
                case 3 -> controleCliente.buscarCliente();
                case 4 -> controleCliente.excluirCliente();
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void menuPets() {
        int opcao;
        do {
            System.out.println("\n--- Menu Pets ---");
            System.out.println("1 - Cadastrar PET");
            System.out.println("2 - Listar PETs");
            System.out.println("3 - Buscar PET");
            System.out.println("4 - Excluir PET");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = lerOpcao();

            switch (opcao) {
                case 1 -> controlePet.cadastrarPet();
                case 2 -> controlePet.listarPet();
                case 3 -> controlePet.buscarPet();
                case 4 -> controlePet.excluirPet();
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void menuServicos() {
        int opcao;
        do {
            System.out.println("\n--- Menu Serviços ---");
            System.out.println("1 - Contratar Serviço Avulso");
            System.out.println("2 - Contratar Pacote de Serviços");
            System.out.println("3 - Listar Serviços Contratados");
            System.out.println("4 - Buscar Serviços Contratados");
            System.out.println("5 - Cancelar Serviços ou Pacotes");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    controleServico.contratarServicoAvulso();
                    break;
                case 2:
                    controleServico.contratarPacote();
                    break;
                case 3:
                    controleServico.listarServicos();
                    break;
                case 4:
                    controleServico.buscarServicos();
                    break;
                case 5:
                    controleServico.cancelarServicoOuPacote();
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private int lerOpcao() {
        try {
            int opcao = scanner.nextInt();
            scanner.nextLine();
            return opcao;
        } catch (java.util.InputMismatchException e) {
            System.out.println("Erro: Por favor, digite apenas um número válido.");
            scanner.nextLine();
            return -1;
        }
    }
}
