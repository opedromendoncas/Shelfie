package view;

import controller.Sistema;
import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {
    private Sistema control; // o motor do programa
    private JPanel cards;
    private CardLayout layout;
    private PainelJogos pJogos;

    public TelaPrincipal() {
        this.control = new Sistema();
        
        // configuracoes base da janela
        setTitle("Shelfie - Controle de Jogos de Tabuleiro"); 
        setSize(1050, 720); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocationRelativeTo(null); 
        
        getContentPane().setLayout(new BorderLayout()); 

     // barra lateral de menus
        JPanel menuLateral = new JPanel();
        menuLateral.setLayout(new BoxLayout(menuLateral, BoxLayout.Y_AXIS));
        menuLateral.setPreferredSize(new Dimension(220, 0));
        menuLateral.setBackground(Aparencia.COR_MENU);

        // logo do sistema (texto + icone na direita)
        JLabel logoTxt = new JLabel("  SHELFIE");
        try {
            // carrega o icone e coloca ele do lado direito 
            ImageIcon iconeLogo = new ImageIcon("assets/logowhite.png");
            logoTxt.setIcon(iconeLogo);
            logoTxt.setHorizontalTextPosition(SwingConstants.LEFT); // texto na esquerda do icone
            logoTxt.setIconTextGap(10); // espacinho entre eles
        } catch (Exception e) {
            // se nao achar a imagem, o programa segue so com o texto
        }
        
        logoTxt.setFont(new Font("Segoe UI", Font.BOLD, 26));
        logoTxt.setForeground(Color.WHITE);
        logoTxt.setBorder(BorderFactory.createEmptyBorder(45, 10, 55, 10));
        menuLateral.add(logoTxt);

        // botoes do menu
        menuLateral.add(criarItemMenu("Início", "HOME"));
        menuLateral.add(criarItemMenu("Meu Acervo", "JOGOS"));
        menuLateral.add(criarItemMenu("Amigos", "AMIGOS"));
        menuLateral.add(criarItemMenu("Empréstimos", "EMPRESTIMOS"));
        menuLateral.add(criarItemMenu("Consultar", "CONSULTA"));

        // rodape da sidebar
        menuLateral.add(Box.createVerticalGlue());
        JLabel versao = new JLabel("@ Shelfie 2026  ");
        versao.setForeground(new Color(90, 100, 120));
        versao.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        versao.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 0));
        menuLateral.add(criarBotaoSobre());
        menuLateral.add(versao);

        // area onde as telas vao trocar (CardLayout)
        layout = new CardLayout();
        cards = new JPanel(layout);
        cards.setBackground(Aparencia.FUNDO_TELAS);

        // inicia as telas e coloca no baralho
        pJogos = new PainelJogos(control);
        
        cards.add(new PainelHome(layout, cards), "HOME"); 
        cards.add(pJogos, "JOGOS"); 
        cards.add(new PainelAmigos(control), "AMIGOS");
        cards.add(new PainelEmprestimos(control), "EMPRESTIMOS");
        cards.add(new PainelConsulta(control), "CONSULTA");

        add(menuLateral, BorderLayout.WEST);
        add(cards, BorderLayout.CENTER);
    }

    private JButton criarItemMenu(String label, String id) {
        JButton b = new JButton(label);
        Aparencia.estilizarBotaoMenu(b);

        b.addActionListener(e -> {
            layout.show(cards, id);
            // se for pra tela de jogos, da um refresh na tabela
            if (id.equals("JOGOS")) {
                pJogos.atualizarTabela();
            }
        });

        // efeito visual simples de hover
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                b.setBackground(new Color(50, 65, 85));
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                b.setBackground(Aparencia.COR_MENU);
            }
        });
        return b;
    }
    
    private JButton criarBotaoSobre() {
        JButton b = new JButton("Sobre");
        
        b.setIcon(new ImageIcon("assets/info.png"));
        b.setHorizontalAlignment(SwingConstants.LEFT);
        b.setIconTextGap(10);
        
        Aparencia.estilizarBotaoMenu(b);

        b.addActionListener(e -> {
            DialogSobre dialog = new DialogSobre();
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);
        });

        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                b.setBackground(new Color(50, 65, 85));
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                b.setBackground(Aparencia.COR_MENU);
            }
        });

        return b;
    }

    public static void main(String[] args) {
        // garante que a interface rode na thread certa do Swing
        SwingUtilities.invokeLater(() -> {
            new TelaPrincipal().setVisible(true);
        });
    }
}