package clinica.petshop.servico;

import clinica.petshop.modelos.Pet;

import java.time.LocalDate;

public abstract class Servico {

    private static int proximoId = 1;

    private int id;
    private String descricao;
    private LocalDate dataAgendamento;
    private Pet pet;

    public Servico (String descricao, LocalDate dataAgendamento, Pet pet) {
        this.id = proximoId++;
        this.descricao = descricao;
        this.dataAgendamento = dataAgendamento;
        this.pet = pet;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataAgendamento() {
        return dataAgendamento;
    }

    public Pet getPet() {
        return pet;
    }

    public abstract double calcularPreco();
}




