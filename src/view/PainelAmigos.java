package view;

import controller.Sistema;
import model.Amigo;
import model.Emprestimo;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PainelAmigos extends JPanel {
    private Sistema control;
    private JTable tabela;
    private DefaultTableModel modelo;

    public PainelAmigos(Sistema sistema) {
        this.control = sistema;
        setLayout(new BorderLayout());
        setBackground(Aparencia.FUNDO_TELAS);
        
        montarComponentes();
        atualizarGrid();
    }

    private void montarComponentes() {
        // parte de cima com o titulo e o botao de add
        JPanel topo = new JPanel(new BorderLayout());
        topo.setBackground(Aparencia.FUNDO_TELAS);
        topo.setBorder(BorderFactory.createEmptyBorder(25, 25, 12, 25));

        JLabel titulo = new JLabel("MEUS AMIGOS");
        titulo.setForeground(Aparencia.COR_DESTAQUE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 21));
        topo.add(titulo, BorderLayout.WEST);

        JButton btnMais = new JButton("+ Novo Amigo");
        Aparencia.estilizarBotao(btnMais);
        topo.add(btnMais, BorderLayout.EAST);

        // configuracao da tabela
        String[] cabecalho = {"Nome do Amigo", "Telefone para Contato"};
        modelo = new DefaultTableModel(cabecalho, 0);
        tabela = new JTable(modelo);
        Aparencia.configurarTabela(tabela);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        scroll.getViewport().setBackground(Color.WHITE);

        // botoes de baixo
        JPanel base = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        base.setBackground(Aparencia.FUNDO_TELAS);
        base.setBorder(BorderFactory.createEmptyBorder(10, 10, 25, 25));

        JButton btnDel = new JButton("Remover Selecionado");
        Aparencia.estilizarBotao(btnDel);
        base.add(btnDel);

        // logica do botao novo
        btnMais.addActionListener(e -> {
            Window win = SwingUtilities.getWindowAncestor(this);
            FormularioAmigo f = new FormularioAmigo(win);
            f.setVisible(true);

            if (f.isSalvo()) {
                control.cadastrarAmigo(f.getAmigo());
                atualizarGrid();
            }
        });

        // excluir amigo (com trava de segurança)
        btnDel.addActionListener(e -> {
            int idx = tabela.getSelectedRow();
            if (idx == -1) {
                JOptionPane.showMessageDialog(this, "Escolha alguém na lista primeiro!");
                return;
            }

            Amigo alvo = control.getListaAmigos().get(idx);

            // nao deixa deletar se o cara ta com jogo do grupo
            boolean deveJogo = false;
            for (Emprestimo emp : control.getListaEmprestimos()) {
                if (emp.getAmigo().equals(alvo) && emp.isAtivo()) {
                    deveJogo = true;
                    break;
                }
            }

            if (deveJogo) {
                JOptionPane.showMessageDialog(this, "Não pode apagar! Esse amigo ainda não devolveu um jogo.");
            } else {
                int sim = JOptionPane.showConfirmDialog(this, "Apagar " + alvo.getNome() + " da lista?", "Cuidado", JOptionPane.YES_NO_OPTION);
                if (sim == JOptionPane.YES_OPTION) {
                    control.getListaAmigos().remove(alvo);
                    atualizarGrid();
                }
            }
        });

        add(topo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(base, BorderLayout.SOUTH);
    }

    public void atualizarGrid() {
        modelo.setRowCount(0);
        if (control.getListaAmigos() != null) {
            for (Amigo a : control.getListaAmigos()) {
                // pega os dados direto do objeto amigo
                Object[] dados = {a.getNome(), a.getTelefone()};
                modelo.addRow(dados);
            }
        }
    }
}