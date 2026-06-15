package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Aparencia {
    // cores principais pra identidade do sistema
    public static final Color COR_MENU = new Color(26, 37, 47);   
    public static final Color COR_DESTAQUE = new Color(61, 90, 128); 
    public static final Color FUNDO_TELAS = new Color(192, 214, 223);   
    
    public static final Color COR_TEXTO = new Color(45, 52, 54);
    public static final Color BRANCO = Color.WHITE;
    public static final Color CINZA_BOTAO = new Color(180, 180, 180);

    public static void estilizarBotao(JButton btn) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setForeground(BRANCO);
        btn.setBackground(COR_DESTAQUE); 
        // bordas ajustadas pra nao ficar tao colado
        btn.setBorder(new EmptyBorder(8, 20, 8, 20));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public static void estilizarBotaoIcone(JButton btn) {
        btn.setPreferredSize(new Dimension(42, 42)); // tamanho um pouco mais quebrado
        btn.setBackground(BRANCO);
        btn.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public static void estilizarBotaoSecundario(JButton btn) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setForeground(BRANCO);
        btn.setBackground(CINZA_BOTAO);
        btn.setBorder(new EmptyBorder(8, 18, 8, 18));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    } 

    public static void estilizarBotaoMenu(JButton btn) {
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btn.setForeground(new Color(210, 210, 210)); 
        btn.setBackground(COR_MENU);
        btn.setBorder(new EmptyBorder(10, 25, 10, 25));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public static void estilizarPainel(JPanel painel) {
        painel.setBackground(FUNDO_TELAS);
    }

    public static void configurarTabela(JTable tabela) {
        // deixa as linhas mais altas pra ficar mais facil de ler
        tabela.setRowHeight(35);
        tabela.setShowGrid(false);
        tabela.setIntercellSpacing(new Dimension(0, 0));
        
        tabela.setSelectionBackground(COR_DESTAQUE);
        tabela.setSelectionForeground(Color.WHITE);
        tabela.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        tabela.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabela.getTableHeader().setBackground(BRANCO);
        tabela.getTableHeader().setReorderingAllowed(false);
        // linha cinza embaixo do cabeçalho
        tabela.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(210, 210, 210)));
    }

    public static void estilizarCampo(JTextField campo) {
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        // padding interno pro texto nao bater na borda do campo
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180)),
                BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));
    }
}