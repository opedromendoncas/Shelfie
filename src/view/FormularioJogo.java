package view;
import model.Jogo;
import javax.swing.*;
import java.awt.*;

public class FormularioJogo extends JDialog {
    private JTextField campoNome, campoCat, campoMin, campoMax, campoTempo;
    private JComboBox<String> seletorTipo; 
    private Jogo jogo;
    private boolean salvo = false;

    public FormularioJogo(Window pai) {
        super(pai, "Cadastrar Novo Jogo", ModalityType.APPLICATION_MODAL);
        setLayout(new BorderLayout());

        // painel pra organizar os campos em grade
        JPanel painelDados = new JPanel(new GridLayout(6, 2, 8, 12));
        painelDados.setBorder(BorderFactory.createEmptyBorder(22, 18, 22, 18));

        painelDados.add(new JLabel("Nome do Jogo:")); 
        campoNome = new JTextField(); 
        painelDados.add(campoNome);
        
        painelDados.add(new JLabel("Categoria/Tema:")); 
        campoCat = new JTextField(); 
        painelDados.add(campoCat);
        
        painelDados.add(new JLabel("Mín. Jogadores:")); 
        campoMin = new JTextField(); 
        painelDados.add(campoMin);
        
        painelDados.add(new JLabel("Max. Jogadores:")); 
        campoMax = new JTextField(); 
        painelDados.add(campoMax);
        
        painelDados.add(new JLabel("Tempo (minutos):")); 
        campoTempo = new JTextField(); 
        painelDados.add(campoTempo);
        
        painelDados.add(new JLabel("Tipo:")); 
        String[] tipos = {"Cooperativo", "Competitivo"};
        seletorTipo = new JComboBox<>(tipos);
        painelDados.add(seletorTipo);

        // area dos botoes no rodape
        JPanel areaBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnSair = new JButton("Cancelar");
        JButton btnGravar = new JButton("Salvar Jogo");

        Aparencia.estilizarBotaoSecundario(btnSair);
        Aparencia.estilizarBotao(btnGravar);

        btnSair.addActionListener(e -> dispose());

        btnGravar.addActionListener(e -> {
            // valida se o nome nao ta em branco
            if (campoNome.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Escreva o nome do jogo!");
                return;
            }

            try {
                // converte os textos pra numero pra salvar no objeto
                int min = Integer.parseInt(campoMin.getText());
                int max = Integer.parseInt(campoMax.getText());
                int tempo = Integer.parseInt(campoTempo.getText());
                
                String tipo = seletorTipo.getSelectedItem().toString();

                this.jogo = new Jogo(campoNome.getText().trim(), campoCat.getText().trim(), min, max, tempo, tipo);
                this.salvo = true;
                dispose();

            } catch (NumberFormatException ex) {
                // se o usuario digitar letra onde é numero cai aqui
                JOptionPane.showMessageDialog(this, "Ops! Use apenas números em Jogadores e Tempo.", "Aviso", JOptionPane.ERROR_MESSAGE);
            }
        });

        areaBotoes.add(btnSair);
        areaBotoes.add(btnGravar);

        add(painelDados, BorderLayout.CENTER);
        add(areaBotoes, BorderLayout.SOUTH);

        setSize(370, 460); 
        setLocationRelativeTo(pai);
    }

    public boolean isSalvo() { return salvo; }
    public Jogo getJogo() { return jogo; }
}