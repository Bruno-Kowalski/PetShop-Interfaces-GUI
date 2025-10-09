package clinica.petshop.servico;

import clinica.petshop.modelos.Pet;

import java.time.LocalDate;

public class BanhoTosa extends Servico {

    public BanhoTosa(LocalDate dataAgendamento, Pet pet) {
        super("Banho e Tosa", dataAgendamento, pet);
    }

    @Override
    public double calcularPreco() {
        return 80;
    }
}
