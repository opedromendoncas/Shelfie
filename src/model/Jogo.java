package model;

public class Jogo {
    private String nome;
    private String categoria;
    private int jogMin;
    private int jogMax;
    private int tempoMedio;
    private String tipo; // cooperativo ou competitivo
    private String status;

    public Jogo(String nome, String categoria, int jogMin, int jogMax, int tempoMedio, String tipo) {
        this.nome = nome;
        this.categoria = categoria;
        this.jogMin = jogMin;
        this.jogMax = jogMax;
        this.tempoMedio = tempoMedio;
        this.tipo = tipo;
        this.status = "Disponível"; // todo jogo novo começa na estante
    }

    // getters e setters pro sistema conseguir ler e mudar os dados
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public int getJogMin() { return jogMin; }
    public void setJogMin(int jogMin) { this.jogMin = jogMin; }
    public int getJogMax() { return jogMax; }
    public void setJogMax(int jogMax) { this.jogMax = jogMax; }
    public int getTempoMedio() { return tempoMedio; }
    public void setTempoMedio(int tempoMedio) { this.tempoMedio = tempoMedio; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
