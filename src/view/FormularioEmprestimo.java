package view;

import controller.Sistema;
import model.Amigo;
import model.Jogo;
import model.Emprestimo;
import javax.swing.*;
import java.awt.*;

public class FormularioEmprestimo extends JDialog {
    private JComboBox<String> cbJogos;
    private JComboBox<String> cbAmigos;
    private Sistema control; // mudado de sistema para control
    private boolean gravado = false;
    private Jogo jogoEscolhido;
    private Amigo amigoEscolhido;

    public FormularioEmprestimo(Window pai, Sistema sistema) {
        super(pai, "Empréstimo de Jogo", ModalityType.APPLICATION_MODAL);
        this.control = sistema;
        
        // configurando tamanho e layout da janelinha
        setSize(390, 270);
        setLocationRelativeTo(pai);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // painel central pros seletores
        JPanel pnlInputs = new JPanel(new GridLayout(2, 2, 10, 25));
        pnlInputs.setBackground(Color.WHITE);
        pnlInputs.setBorder(BorderFactory.createEmptyBorder(35, 22, 15, 22));

        pnlInputs.add(new JLabel("Qual o jogo?"));
        cbJogos = new JComboBox<>();
        // preenche o combo so com o que da pra emprestar
        for (Jogo j : control.getListaJogos()) {
            if (j.getStatus().equalsIgnoreCase("Disponível")) {
                cbJogos.addItem(j.getNome());
            }
        }
        pnlInputs.add(cbJogos);

        pnlInputs.add(new JLabel("Para quem?"));
        cbAmigos = new JComboBox<>();
        for (Amigo a : control.getListaAmigos()) {
            cbAmigos.addItem(a.getNome());
        }
        pnlInputs.add(cbAmigos);

        // botoes de acao
        JPanel pnlSul = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 12));
        pnlSul.setBackground(new Color(242, 242, 242));

        JButton btnOk = new JButton("Confirmar");
        JButton btnSair = new JButton("Sair");

        Aparencia.estilizarBotao(btnOk);
        Aparencia.estilizarBotaoSecundario(btnSair);

        btnOk.addActionListener(e -> {
            // verifica se tem itens selecionados nos combos
            if (cbJogos.getSelectedIndex() == -1 || cbAmigos.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(this, "Selecione o jogo e o amigo!", "Atenção", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String nomeJ = (String) cbJogos.getSelectedItem();
            String nomeA = (String) cbAmigos.getSelectedItem();

            // acha o objeto do amigo na lista
            Amigo escolhido = null;
            for(Amigo a : control.getListaAmigos()) {
                if(a.getNome().equals(nomeA)) escolhido = a;
            }

            // checa a regra do limite de 2 jogos
            int totalAtivo = control.contarEmprestimosAtivos(escolhido);
            
            if (totalAtivo >= 2) {
                JOptionPane.showMessageDialog(this, nomeA + " já está com 2 jogos. Precisa devolver um primeiro!", "Limite excedido", JOptionPane.ERROR_MESSAGE);
            } else {
                // localiza o jogo e fecha a janela para o controller salvar
                for(Jogo j : control.getListaJogos()) {
                    if(j.getNome().equals(nomeJ)) this.jogoEscolhido = j;
                }
                this.amigoEscolhido = escolhido;
                this.gravado = true;
                dispose();
            }
        });

        btnSair.addActionListener(e -> dispose());

        pnlSul.add(btnSair);
        pnlSul.add(btnOk);

        add(pnlInputs, BorderLayout.CENTER);
        add(pnlSul, BorderLayout.SOUTH);
    }

    public boolean isSalvo() { return gravado; }
    public Jogo getJogoSelecionado() { return jogoEscolhido; }
    public Amigo getAmigoSelecionado() { return amigoEscolhido; }
}