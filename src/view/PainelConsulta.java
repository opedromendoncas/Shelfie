package view;

import controller.Sistema;
import model.Jogo;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PainelConsulta extends JPanel {
    private Sistema control; // mudado de sistema para control
    private JTextField campoBusca;
    private JLabel labelAviso;

    // variaveis pra guardar o que foi marcado no filtro
    private String fStatus = "Todos";
    private String fTipo = "Todos";
    private String fJogadores = "";
    private String fTempo = ""; 

    public PainelConsulta(Sistema sistema) {
        this.control = sistema;
        setLayout(new BorderLayout());
        setBackground(Aparencia.FUNDO_TELAS);
        montarTela();
    }

    private void montarTela() {
        JPanel miolo = new JPanel();
        miolo.setLayout(new BoxLayout(miolo, BoxLayout.Y_AXIS));
        miolo.setBackground(Aparencia.FUNDO_TELAS);
        miolo.setBorder(new EmptyBorder(55, 45, 45, 45)); 

        JLabel titulo = new JLabel("O QUE VOCÊ QUER JOGAR?");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setForeground(Aparencia.COR_DESTAQUE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        miolo.add(titulo);
        miolo.add(Box.createRigidArea(new Dimension(0, 20)));

        // linha da busca com os icones
        JPanel linhaPesquisa = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        linhaPesquisa.setBackground(Aparencia.FUNDO_TELAS);

        campoBusca = new JTextField();
        Aparencia.estilizarCampo(campoBusca);
        campoBusca.setPreferredSize(new Dimension(310, 42)); 
        
        JButton btnLixo = new JButton();
        try {
            btnLixo.setIcon(new ImageIcon("assets/trash_icon.png")); 
        } catch (Exception e) {
            btnLixo.setText("Limpar"); 
        }
        Aparencia.estilizarBotaoIcone(btnLixo);
        
        JButton btnOpcoes = new JButton();
        try {
            btnOpcoes.setIcon(new ImageIcon("assets/filter_icon.png")); 
        } catch (Exception e) {
            btnOpcoes.setText("Filtros");
        }
        Aparencia.estilizarBotaoIcone(btnOpcoes);

        linhaPesquisa.add(campoBusca);
        linhaPesquisa.add(btnLixo);
        linhaPesquisa.add(btnOpcoes);
        
        miolo.add(linhaPesquisa);
        miolo.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnIr = new JButton("PESQUISAR AGORA");
        Aparencia.estilizarBotao(btnIr);
        btnIr.setAlignmentX(Component.CENTER_ALIGNMENT);
        miolo.add(btnIr);
        miolo.add(Box.createRigidArea(new Dimension(0, 35)));

        labelAviso = new JLabel("Aguardando busca...");
        labelAviso.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        labelAviso.setForeground(new Color(110, 110, 110));
        labelAviso.setAlignmentX(Component.CENTER_ALIGNMENT);
        miolo.add(labelAviso);

        // acoes dos botoes
        btnOpcoes.addActionListener(e -> {
            JanelaFiltro pop = new JanelaFiltro((JFrame) SwingUtilities.getWindowAncestor(this));
            pop.setVisible(true);
        });

        btnLixo.addActionListener(e -> {
            campoBusca.setText("");
            fStatus = "Todos"; fTipo = "Todos";
            fJogadores = ""; fTempo = ""; 
            labelAviso.setText("Busca resetada.");
        });

        btnIr.addActionListener(e -> rodarFiltro());

        add(miolo, BorderLayout.NORTH); 
    }

    private void rodarFiltro() {
        String digitado = campoBusca.getText().toLowerCase().trim();

        // se nao tiver nada pra buscar avisa o usuario
        if (digitado.isEmpty() && fStatus.equals("Todos") && fTipo.equals("Todos") && fJogadores.isEmpty() && fTempo.isEmpty()) {
            labelAviso.setText("Escolha um filtro ou digite o nome.");
            return;
        }

        StringBuilder html = new StringBuilder("<html><center>");
        boolean achei = false;

        for (Jogo j : control.getListaJogos()) {
            boolean okNome = digitado.isEmpty() || j.getNome().toLowerCase().contains(digitado);
            boolean okStatus = fStatus.equals("Todos") || j.getStatus().equalsIgnoreCase(fStatus);
            boolean okTipo = fTipo.equals("Todos") || j.getTipo().equalsIgnoreCase(fTipo);
            
            // ve se a quantidade de pessoas bate com o intervalo do jogo
            boolean okJogadores = true;
            if (!fJogadores.isEmpty()) {
                try {
                    int n = Integer.parseInt(fJogadores);
                    okJogadores = (n >= j.getJogMin() && n <= j.getJogMax());
                } catch (Exception ex) { okJogadores = true; }
            }

            // ve se o tempo do jogo cabe no tempo que o usuario tem
            boolean okTempo = true;
            if (!fTempo.isEmpty()) {
                try {
                    int tMax = Integer.parseInt(fTempo);
                    okTempo = (j.getTempoMedio() <= tMax); 
                } catch (Exception ex) { okTempo = true; }
            }

            if (okNome && okStatus && okTipo && okJogadores && okTempo) {
                html.append("<br><font color='#3d5a80' size='5'>")
                    .append(j.getNome().toUpperCase()).append("</font><br>")
                    .append("Status: ").append(j.getStatus()).append("<br>")
                    .append("Duração: ").append(j.getTempoMedio()).append(" min<br>");
                achei = true;
            }
        }

        if (!achei) {
            labelAviso.setText("Nenhum jogo encontrado com esses filtros.");
        } else {
            labelAviso.setText(html.append("</center></html>").toString());
        }
    }

 // janelinha de filtros
    private class JanelaFiltro extends JDialog {
        public JanelaFiltro(Frame pai) {
            super(pai, "Configurar Filtros", true);
            setSize(300, 500); // 
            setLocationRelativeTo(pai);
            

            setLayout(new GridLayout(10, 1, 4, 4)); 
            
            JPanel p = (JPanel)getContentPane();
            p.setBorder(new EmptyBorder(15, 20, 15, 20));
            p.setBackground(Color.WHITE);

            JComboBox<String> comboS = new JComboBox<>(new String[]{"Todos", "Disponível", "Emprestado"});
            comboS.setSelectedItem(fStatus);
            
            JComboBox<String> comboT = new JComboBox<>(new String[]{"Todos", "Cooperativo", "Competitivo"});
            comboT.setSelectedItem(fTipo);
            
            JTextField tfJ = new JTextField(fJogadores);
            JTextField tfT = new JTextField(fTempo); 

            add(new JLabel("Status:")); add(comboS);
            add(new JLabel("Tipo:")); add(comboT);
            add(new JLabel("Quantas pessoas?")); add(tfJ);
            add(new JLabel("Tempo máximo:")); add(tfT);

            JButton btnSim = new JButton("APLICAR FILTROS");
            Aparencia.estilizarBotao(btnSim);
            
            // botao de cancelar
            JButton btnNao = new JButton("CANCELAR");
            Aparencia.estilizarBotao(btnNao);

            btnNao.setBackground(new Color(180, 180, 180)); 

            btnSim.addActionListener(e -> {
                fStatus = (String) comboS.getSelectedItem();
                fTipo = (String) comboT.getSelectedItem();
                fJogadores = tfJ.getText().trim();
                fTempo = tfT.getText().trim(); 
                dispose(); 
                rodarFiltro();
            });

            btnNao.addActionListener(e -> dispose());

            add(btnSim);
            add(btnNao);
        }
    }
}