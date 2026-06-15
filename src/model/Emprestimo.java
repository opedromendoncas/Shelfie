package model;

import java.time.LocalDate;

public class Emprestimo {
    private Jogo jogo;
    private Amigo amigo;
    private LocalDate dataEmprestimo;
    private boolean ativo;

    public Emprestimo(Jogo jogo, Amigo amigo) {
        this.jogo = jogo;
        this.amigo = amigo;
        this.dataEmprestimo = LocalDate.now(); // pega a data de hoje
        this.ativo = true;
    }

    // getters e setters
    public Jogo getJogo() { return jogo; }
    public Amigo getAmigo() { return amigo; }
    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}