package view;

import controller.Sistema;
import model.Emprestimo;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PainelEmprestimos extends JPanel {
    private Sistema control; // mudado de sistema para control
    private JTable tabelaEmp;
    private DefaultTableModel modelo;

    public PainelEmprestimos(Sistema sistema) {
        this.control = sistema;
        setLayout(new BorderLayout());
        setBackground(Aparencia.FUNDO_TELAS);
        
        montarInterface();
        atualizarTabela();
    }

    private void montarInterface() {
        // parte de cima: titulo e o botao de novo emprestimo
        JPanel topo = new JPanel(new BorderLayout());
        topo.setBackground(Aparencia.FUNDO_TELAS);
        topo.setBorder(BorderFactory.createEmptyBorder(25, 25, 12, 25));

        JLabel titulo = new JLabel("CONTROLE DE EMPRÉSTIMOS");
        titulo.setForeground(Aparencia.COR_DESTAQUE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 21));
        topo.add(titulo, BorderLayout.WEST);

        JButton btnNovo = new JButton("+ Novo Empréstimo");
        Aparencia.estilizarBotao(btnNovo);
        topo.add(btnNovo, BorderLayout.EAST);

        // grid com as colunas principais
        String[] cabecalho = {"Jogo", "Pegou com", "Data", "Situação"};
        modelo = new DefaultTableModel(cabecalho, 0);
        tabelaEmp = new JTable(modelo);
        Aparencia.configurarTabela(tabelaEmp);

        JScrollPane scroll = new JScrollPane(tabelaEmp);
        scroll.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        scroll.getViewport().setBackground(Color.WHITE);

        // botao de devolucao no rodape
        JPanel base = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        base.setBackground(Aparencia.FUNDO_TELAS);
        base.setBorder(BorderFactory.createEmptyBorder(12, 10, 22, 25));

        JButton btnVoltou = new JButton("Registrar Devolução");
        Aparencia.estilizarBotao(btnVoltou);

        // acao para abrir o forms de emprestimo
        btnNovo.addActionListener(e -> {
            Window pai = SwingUtilities.getWindowAncestor(this);
            FormularioEmprestimo f = new FormularioEmprestimo(pai, control);
            f.setVisible(true);
            if (f.isSalvo()) {
                control.registrarEmprestimo(f.getJogoSelecionado(), f.getAmigoSelecionado());
                atualizarTabela();
            }
        });

        // logica para dar baixa no emprestimo
        btnVoltou.addActionListener(e -> {
            int linha = tabelaEmp.getSelectedRow();
            if (linha >= 0) {
                // pega o emprestimo direto da lista do sistema
                Emprestimo emp = control.getListaEmprestimos().get(linha);
                control.registrarDevolucao(emp);
                atualizarTabela();
                JOptionPane.showMessageDialog(this, "Show! O jogo voltou para a prateleira.");
            } else {
                JOptionPane.showMessageDialog(this, "Selecione o empréstimo que quer encerrar.");
            }
        });

        base.add(btnVoltou);

        add(topo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(base, BorderLayout.SOUTH);
    }

    public void atualizarTabela() {
        modelo.setRowCount(0);
        for (Emprestimo e : control.getListaEmprestimos()) {
            // verifica se ja devolveu ou nao
            String situacao = e.isAtivo() ? "Com o amigo" : "Devolvido";
            Object[] dados = {
                e.getJogo().getNome(),
                e.getAmigo().getNome(),
                e.getDataEmprestimo(),
                situacao
            };
            modelo.addRow(dados);
        }
    }
}