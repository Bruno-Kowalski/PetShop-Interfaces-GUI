package clinica.petshop.servico;

import clinica.petshop.modelos.Pet;

import java.time.LocalDate;

public class ConsultaVeterinaria extends Servico {
    public ConsultaVeterinaria(LocalDate dataAgendameto, Pet pet) {
        super("Consulta Veterinária", dataAgendameto, pet);
    }

    @Override
    public double calcularPreco() {
        return 250;
    }
}
