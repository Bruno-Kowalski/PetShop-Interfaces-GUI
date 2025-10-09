package clinica.petshop.pacote;

import clinica.petshop.modelos.Cliente;
import clinica.petshop.modelos.Pet;
import clinica.petshop.servico.Servico;

import java.time.LocalDate;
import java.util.List;

public class PacoteServico {
    private String nome;
    private List<Servico> servicos;
    private double desconto;
    private Cliente cliente;
    private Pet pet;

    public PacoteServico(String nome, List<Servico> servicos, double desconto, Cliente cliente, Pet pet) {
        this.nome = nome;
        this.servicos = servicos;
        this.desconto = desconto;
        this.cliente = cliente;
        this.pet = pet;
    }

    public double calcularPreco() {
        double total = 0;
        for (Servico s : servicos) {
            total += s.calcularPreco();
        }
        return total * (1 - desconto / 100.0); // aplica desconto corretamente
    }

    public String getNome() {
        return nome;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public double getDesconto() {
        return desconto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Pet getPet() {
        return pet;
    }

    public LocalDate getDataAgendamento() {
        if (!servicos.isEmpty()) {
            return servicos.get(0).getDataAgendamento(); // pega a data do primeiro serviço
        }
        return null;
    }
}
