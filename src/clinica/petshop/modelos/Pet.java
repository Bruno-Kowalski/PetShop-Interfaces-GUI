package clinica.petshop.modelos;

public class Pet {

    private static int proximoid = 1;

    private int id;
    private String nome;
    private String especie;
    private String raca;
    private int idade;
    private float peso;
    private Cliente dono;


    public Pet (String nome, String especie, String raca, int idade, float peso, Cliente dono) {
        this.id = proximoid++;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.idade = idade;
        this.peso = peso;
        this.dono = dono;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome (String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie (String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca (String raca) {
        this.raca = raca;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade (int idade) {
        this.idade = idade;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso (float peso) {
        this.peso = peso;
    }

    public Cliente getDono() {
        return dono;
    }
}

