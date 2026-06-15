package view;

import controller.Sistema;
import model.Jogo;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PainelJogos extends JPanel {
    private Sistema control; // mudado para control para manter o padrão
    private JTable grid;
    private DefaultTableModel modelo;

    public PainelJogos(Sistema sistema) {
        this.control = sistema;
        setLayout(new BorderLayout());
        setBackground(Aparencia.FUNDO_TELAS);
        
        iniciarComponentes();
        atualizarTabela(); 
    }

    private void iniciarComponentes() {
        // cabecalho com o titulo e o botao de add novo
        JPanel topo = new JPanel(new BorderLayout());
        topo.setBackground(Aparencia.FUNDO_TELAS);
        topo.setBorder(BorderFactory.createEmptyBorder(25, 25, 12, 25));

        JLabel titulo = new JLabel("MEU ACERVO DE JOGOS");
        titulo.setForeground(Aparencia.COR_DESTAQUE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 21));
        topo.add(titulo, BorderLayout.WEST);

        JButton btnNovo = new JButton("+ Adicionar Jogo");
        Aparencia.estilizarBotao(btnNovo);
        topo.add(btnNovo, BorderLayout.EAST);

        // tabela propriamente dita
        String[] colunas = {"Nome", "Categoria", "Jogadores", "Duração", "Tipo", "Status"};
        modelo = new DefaultTableModel(colunas, 0);
        grid = new JTable(modelo);
        Aparencia.configurarTabela(grid);

        JScrollPane scroll = new JScrollPane(grid);
        scroll.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        scroll.getViewport().setBackground(Color.WHITE);

        // botoes de acao no rodape
        JPanel acoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        acoes.setBackground(Aparencia.FUNDO_TELAS);
        acoes.setBorder(BorderFactory.createEmptyBorder(10, 10, 25, 25));

        JButton btnLixo = new JButton("Remover Jogo");
        Aparencia.estilizarBotao(btnLixo);
        acoes.add(btnLixo);

        // logica de exclusao
        btnLixo.addActionListener(e -> {
            int idx = grid.getSelectedRow();
            if (idx >= 0) {
                Jogo alvo = control.getListaJogos().get(idx);

                // trava pra nao apagar jogo que ta na rua
                if (alvo.getStatus().equalsIgnoreCase("Emprestado")) {
                    JOptionPane.showMessageDialog(this, "Não dá pra apagar! O jogo está emprestado.");
                } else {
                    int op = JOptionPane.showConfirmDialog(this, "Deseja mesmo remover " + alvo.getNome() + "?", "Atenção", JOptionPane.YES_NO_OPTION);
                    if (op == JOptionPane.YES_OPTION) {
                        control.getListaJogos().remove(alvo);
                        atualizarTabela(); 
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um jogo primeiro!");
            }
        });

        // logica pra abrir o form de cadastro
        btnNovo.addActionListener(e -> {
            Window win = SwingUtilities.getWindowAncestor(this);
            FormularioJogo form = new FormularioJogo(win);
            form.setVisible(true);

            if (form.isSalvo()) {
                control.cadastrarJogo(form.getJogo());
                atualizarTabela();
            }
        });

        add(topo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(acoes, BorderLayout.SOUTH);
    }

    public void atualizarTabela() {
        modelo.setRowCount(0);
        ArrayList<Jogo> lista = control.getListaJogos();

        for (Jogo j : lista) {
            Object[] linha = {
                j.getNome(),
                j.getCategoria(),
                j.getJogMin() + " a " + j.getJogMax(), 
                j.getTempoMedio() + " min",
                j.getTipo(),
                j.getStatus()
            };
            modelo.addRow(linha);
        }
    }
}