package view;

import controller.Sistema;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PainelHome extends JPanel {
    private CardLayout nav; 
    private JPanel telaPrincipal;

    public PainelHome(CardLayout navegador, JPanel painelConteudo) {
        this.nav = navegador;
        this.telaPrincipal = painelConteudo;
        
        setLayout(new GridBagLayout());
        setBackground(Aparencia.FUNDO_TELAS);
        montarHome();
    }

    private void montarHome() {
        JPanel miolo = new JPanel();
        miolo.setLayout(new BoxLayout(miolo, BoxLayout.Y_AXIS));
        miolo.setBackground(Aparencia.FUNDO_TELAS);

        // logo e boas-vindas
        JLabel logo = new JLabel();
        try {
            logo.setIcon(new ImageIcon("assets/logoblue.png"));
        } catch (Exception e) {
            logo.setText("🎮"); // se a imagem nao carregar nao quebra tudo
            logo.setFont(new Font("Segoe UI", Font.PLAIN, 75));
        }
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logo.setBorder(new EmptyBorder(0, 0, 15, 0));
        miolo.add(logo);

        JLabel txtBemVindo = new JLabel("BEM-VINDO AO SHELFIE");
        txtBemVindo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        txtBemVindo.setForeground(Aparencia.COR_DESTAQUE);
        txtBemVindo.setAlignmentX(Component.CENTER_ALIGNMENT);
        miolo.add(txtBemVindo);

        JLabel slogan = new JLabel("Gerencie sua coleção de board games de um jeito fácil.");
        slogan.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        slogan.setForeground(new Color(110, 110, 110));
        slogan.setBorder(new EmptyBorder(5, 0, 50, 0));
        slogan.setAlignmentX(Component.CENTER_ALIGNMENT);
        miolo.add(slogan);

        // painel com os 4 botoes grandes (atalhos)
        JPanel areaBotoes = new JPanel(new GridLayout(2, 2, 18, 18));
        areaBotoes.setBackground(Aparencia.FUNDO_TELAS);

        areaBotoes.add(criarAtalho("MEU ACERVO", "assets/games_icon.png", "JOGOS")); 
        areaBotoes.add(criarAtalho("MEUS AMIGOS", "assets/friends_icon1.png", "AMIGOS"));
        areaBotoes.add(criarAtalho("EMPRÉSTIMOS", "assets/deal_icon.png", "EMPRESTIMOS")); 
        areaBotoes.add(criarAtalho("BUSCA RÁPIDA", "assets/search_icon.png", "CONSULTA"));

        miolo.add(areaBotoes);
        GridBagConstraints gbc_miolo = new GridBagConstraints();
        gbc_miolo.gridy = 0;
        add(miolo, gbc_miolo);
    }

    private JPanel criarAtalho(String texto, String img, String destino) {
        JPanel bloco = new JPanel(new BorderLayout());
        bloco.setPreferredSize(new Dimension(210, 150)); 
        bloco.setBackground(Color.WHITE);
        bloco.setBorder(BorderFactory.createLineBorder(new Color(225, 225, 225), 2));
        bloco.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel icone = new JLabel("", SwingConstants.CENTER);
        icone.setBorder(new EmptyBorder(12, 0, 0, 0));

        try {
            icone.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            icone.setText("?"); 
            icone.setFont(new Font("Segoe UI", Font.BOLD, 35));
        }

        JLabel labelTexto = new JLabel(texto, SwingConstants.CENTER);
        labelTexto.setFont(new Font("Segoe UI", Font.BOLD, 13));
        labelTexto.setForeground(new Color(70, 70, 70));
        labelTexto.setBorder(new EmptyBorder(8, 0, 12, 0)); 

        bloco.add(icone, BorderLayout.CENTER);
        bloco.add(labelTexto, BorderLayout.SOUTH);

        // efeito de passar o mouse e clicar
        bloco.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                nav.show(telaPrincipal, destino);
            }

            public void mouseEntered(MouseEvent e) {
                bloco.setBackground(new Color(245, 250, 255));
                bloco.setBorder(BorderFactory.createLineBorder(Aparencia.COR_DESTAQUE, 2));
            }

            public void mouseExited(MouseEvent e) {
                bloco.setBackground(Color.WHITE);
                bloco.setBorder(BorderFactory.createLineBorder(new Color(225, 225, 225), 2));
            }
        });

        return bloco;
    }
}