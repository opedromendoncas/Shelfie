package view;

import model.Amigo;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class FormularioAmigo extends JDialog {
    private JTextField campoNome;
    private JFormattedTextField campoFone; 
    private boolean gravado = false;
    private Amigo amigo;

    public FormularioAmigo(Window pai) {
        super(pai, "Adicionar Amigo", ModalityType.APPLICATION_MODAL);
        
        setSize(340, 240); 
        setLocationRelativeTo(pai);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        JPanel pnlInputs = new JPanel(new GridLayout(2, 2, 8, 18));
        pnlInputs.setBackground(Color.WHITE);
        pnlInputs.setBorder(BorderFactory.createEmptyBorder(28, 22, 12, 22));

        pnlInputs.add(new JLabel("Nome completo:"));
        campoNome = new JTextField();
        Aparencia.estilizarCampo(campoNome);
        pnlInputs.add(campoNome);

        pnlInputs.add(new JLabel("Telefone:"));
        
        // tenta colocar a mascara no campo de texto
        try {
            MaskFormatter m = new MaskFormatter("(##) #####-####");
            m.setPlaceholderCharacter('_'); 
            campoFone = new JFormattedTextField(m);
        } catch (ParseException e) {
            // se a mascara falhar ele cria um campo de texto comum
            campoFone = new JFormattedTextField();
        }
        
        Aparencia.estilizarCampo(campoFone);
        pnlInputs.add(campoFone);

        JPanel pnlBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 12));
        pnlBotoes.setBackground(new Color(240, 240, 240));

        JButton btnOk = new JButton("Salvar Contato");
        JButton btnVoltar = new JButton("Cancelar");

        Aparencia.estilizarBotao(btnOk);
        Aparencia.estilizarBotaoSecundario(btnVoltar);

        btnOk.addActionListener(e -> {
            // tira os simbolos da mascara pra ver se tem numero mesmo
            String fonePuro = campoFone.getText().replace("(", "").replace(")", "").replace("-", "").replace("_", "").trim();
            
            if (campoNome.getText().trim().isEmpty() || fonePuro.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha tudo antes de salvar!");
            } else {
                this.amigo = new Amigo(campoNome.getText().trim(), campoFone.getText().trim());
                this.gravado = true;
                dispose();
            }
        });

        btnVoltar.addActionListener(e -> dispose());

        pnlBotoes.add(btnVoltar);
        pnlBotoes.add(btnOk);

        add(pnlInputs, BorderLayout.CENTER);
        add(pnlBotoes, BorderLayout.SOUTH);
    }

    public boolean isSalvo() { return gravado; }
    public Amigo getAmigo() { return amigo; }
}