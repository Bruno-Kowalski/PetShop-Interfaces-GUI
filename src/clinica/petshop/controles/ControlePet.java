package clinica.petshop.controles;

import clinica.petshop.modelos.Cliente;
import clinica.petshop.modelos.Pet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ControlePet {
    private Scanner scanner;
    private List<Pet> listaPet;
    private ControleCliente controleCliente;

    public List<Pet> getListaPet() {
        return listaPet;
    }

    public ControlePet(Scanner scanner, ControleCliente controleCliente) {
        this.scanner = scanner;
        this.controleCliente = controleCliente;
        this.listaPet = new ArrayList<>();
    }

    public void cadastrarPet() {
        System.out.println("\n--- Cadastro de PET ---");
        System.out.print("Primeiro, digite o CPF do dono do pet: ");
        String cpf = scanner.nextLine();

        if (controleCliente.buscarClientePorCpf(cpf) == null) {
            System.out.println("Cliente não cadastrado. Cadastre o cliente antes de cadastrar o pet.");
            return;
        }

        System.out.println("Nome do Pet: ");
        String nome = scanner.nextLine();

        System.out.println("Especie: ");
        String especie = scanner.nextLine();

        System.out.println("Raça: ");
        String raca = scanner.nextLine();

        System.out.println("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Peso: ");
        float peso = scanner.nextFloat();
        scanner.nextLine();

        Cliente dono = controleCliente.buscarClientePorCpf(cpf);
        Pet pet = new Pet(nome, especie, raca, idade, peso, dono);
        listaPet.add(pet);

        System.out.println("Pet cadastrado com sucesso! ");
    }

    public void listarPet() {
        if (listaPet.isEmpty()) {
            System.out.println("Nenhum pet cadastrado.");
            return;
        }
        System.out.println("\n======== Lista de PETs ========");
        for (Pet pet : listaPet) {
            System.out.println("\nID do pet: " + pet.getId() + "\nNome: " + pet.getNome() + "\nEspécie: " + pet.getEspecie() +
                    "\nRaça: " + pet.getRaca() + "\nIdade: " + pet.getIdade() + "\nPeso: " + pet.getPeso());
        }
    }

    public void buscarPet() {
        System.out.print("Digite o ID do Pet: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Pet buscar = null;

        for (Pet pet : listaPet) {
            if (pet.getId() == id) {
                buscar = pet;
                break;
            }
        }
        if (buscar != null) {
            System.out.println("\nPet encontrado: " + buscar.getNome() + "\nEspécie: " + buscar.getEspecie());
        } else {
            System.out.println("Pet não encontrado!");
        }
    }

    public void excluirPet() {
        System.out.println("Digite o ID do PET que deseja excluir: ");
        int id = scanner.nextInt();

        Pet remover = null;

        for (Pet pet : listaPet) {
            if (pet.getId() == id) {
                remover = pet;
                break;
            }
        }
        if (remover != null) {
            listaPet.remove(remover);
            System.out.println("Pet: " + remover.getNome() + " Excluído com sucesso! ");
        } else {
            System.out.println("Pet não encontrado");
        }
    }

    public Pet buscarPetPorId(int id) {
        for (Pet pet : listaPet) {
            if (pet.getId() == id) {
                return pet;
            }
        }
        return null; // se não encontrou
    }

}
