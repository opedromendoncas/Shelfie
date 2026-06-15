package model;

public abstract class Pessoa {
    // campos protegidos para as filhas usarem
    protected String nome;
    protected String telefone;

    public Pessoa(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    // metodo que cada um vai mostrar do seu jeito (polimorfismo)
    public abstract String getDados();

    // getters e setters para o nosso sistema acessar os nomes
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
}