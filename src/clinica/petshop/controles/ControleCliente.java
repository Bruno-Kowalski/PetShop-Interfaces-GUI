package clinica.petshop.controles;

import clinica.petshop.modelos.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ControleCliente {
    private Scanner scanner;
    private List<Cliente> listaClientes;

    public ControleCliente (Scanner scanner) {
        this.scanner = scanner;
        this.listaClientes = new ArrayList<>();
    }

    public void cadastrarCliente() {
        System.out.println("\n--- Cadastro de Cliente ---");

        System.out.println("Nome: ");
        String nome = scanner.nextLine();

        System.out.println("CPF: ");
        String cpf = scanner.nextLine();

        if (buscarClientePorCpf(cpf) != null) {
            System.out.println("Já existe um cliente com esse CPF!");
            return;
        }

        System.out.println("Telefone: ");
        String telefone = scanner.nextLine();

        System.out.println("E-mail: ");
        String email = scanner.nextLine();

        Cliente cliente = new Cliente(nome, cpf, telefone, email);
        listaClientes.add(cliente);

        System.out.println("Cliente cadastrado com sucesso! ");
    }

    public void listarClientes() {
        if (listaClientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        System.out.println("\n======== Lista de Clientes ========");
        for (Cliente cliente : listaClientes) {
            System.out.println("\nCliente: " + cliente.getNome() + "\nCPF: " + cliente.getCpf() + "\nTelefone: " + cliente.getTelefone() + "\nE-mail: " + cliente.getEmail());
        }
    }

    public void buscarCliente() {
        System.out.println("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();

        Cliente resultado = buscarClientePorCpf(cpf);

        if (resultado != null) {
            System.out.println("\nCliente encontrado: " + resultado.getNome() + "\nCPF: " + resultado.getCpf() + "\nTelefone: " + resultado.getTelefone());
        } else {
            System.out.println("Cliente não encontrado!");
        }
    }

    public void excluirCliente() {
        System.out.println("Digite o CPF do cliente que deseja excluir: ");
        String cpf = scanner.nextLine();

        Cliente cliente = buscarClientePorCpf(cpf);

        if (cliente != null) {
            listaClientes.remove(cliente);
            System.out.println("Cliente: " + cliente.getNome() + " excluído com sucesso!");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public Cliente buscarClientePorCpf(String cpf) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }



}
