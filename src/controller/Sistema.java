package controller;

import model.Jogo;
import model.Amigo;
import model.Emprestimo;
import java.util.ArrayList;

public class Sistema {
    private ArrayList<Jogo> listaJogos;
    private ArrayList<Amigo> listaAmigos;
    private ArrayList<Emprestimo> listaEmprestimos; 

    public Sistema() {
        this.listaJogos = new ArrayList<>();
        this.listaAmigos = new ArrayList<>();
        this.listaEmprestimos = new ArrayList<>(); 
    }

    // metodos jogos
    public void cadastrarJogo(Jogo j) { listaJogos.add(j); }
    public ArrayList<Jogo> getListaJogos() { return listaJogos; }

    // metodos amiguchos
    public void cadastrarAmigo(Amigo a) { listaAmigos.add(a); }
    public ArrayList<Amigo> getListaAmigos() { return listaAmigos; }

    // agora o registro ja muda o status do jogo na hora pra nao dar erro na consulta
    public void registrarEmprestimo(Jogo jogo, Amigo amigo) {
        jogo.setStatus("Emprestado"); // marca como emprestado
        Emprestimo novo = new Emprestimo(jogo, amigo);
        listaEmprestimos.add(novo);
    }

    public void registrarDevolucao(Emprestimo e) {
        // ao devolver, o jogo volta a ficar disponivel pros outros
        e.getJogo().setStatus("Disponível");
        e.setAtivo(false); // encerra o emprestimo
    }

    public ArrayList<Emprestimo> getListaEmprestimos() {
        return listaEmprestimos;
    }
    
    // metodo novo pra ajudar o formulario a contar quantos jogos o cara ja tem
    public int contarEmprestimosAtivos(Amigo amigo) {
        int total = 0;
        for (Emprestimo e : listaEmprestimos) {
            // se o emprestimo ta no nome do cara e ainda nao devolveram
            if (e.getAmigo().getNome().equals(amigo.getNome()) && e.isAtivo()) {
                total++;
            }
        }
        return total;
    }
}