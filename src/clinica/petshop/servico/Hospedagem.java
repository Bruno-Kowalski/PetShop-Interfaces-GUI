package clinica.petshop.servico;

import clinica.petshop.modelos.Pet;

import java.time.LocalDate;

public class Hospedagem extends Servico {
    private int dias;

    public Hospedagem(LocalDate dataAgendamento, Pet pet, int dias) {
        super ("Hospedagem", dataAgendamento, pet);
        this.dias = dias;
    }

    @Override
    public double calcularPreco() {
        return dias * 80;
    }

    public int getDias() {
        return dias;
    }
}
