package clinica.petshop.servico;

import clinica.petshop.modelos.Pet;

import java.time.LocalDate;

public class Adestramento extends Servico {
    public Adestramento (LocalDate dataAgendamento, Pet pet) {
        super ("Adestramento ", dataAgendamento, pet);
    }

    @Override
    public double calcularPreco() {
        return 350;
    }
}
