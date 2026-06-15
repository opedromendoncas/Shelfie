package model;

public class Amigo extends Pessoa {

    public Amigo(String nome, String telefone) {
        // o super envia os dados para a classe mae (Pessoa)
        super(nome, telefone);
    }

    @Override
    public String getDados() {
        // usando o polimorfismo
        return "Amigo: " + nome + " | Contato: " + telefone;
    }
}