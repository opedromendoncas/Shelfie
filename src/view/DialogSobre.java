package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class DialogSobre extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();

    public DialogSobre() {

        setTitle("Sobre o Shelfie");
        setModal(true);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(Aparencia.FUNDO_TELAS);

        Aparencia.estilizarPainel(contentPanel);
        contentPanel.setBorder(new EmptyBorder(20, 25, 20, 25));
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        getContentPane().add(contentPanel, BorderLayout.CENTER);

        // Logo
        try {
            ImageIcon icon = new ImageIcon("assets/logoblue.png");
            Image img = icon.getImage();

            JLabel logo = new JLabel(new ImageIcon(img));
            logo.setAlignmentX(Component.CENTER_ALIGNMENT);
            contentPanel.add(logo);
        } catch (Exception e) {
            JLabel logo = new JLabel("SHELFIE");
            logo.setFont(new Font("Segoe UI", Font.BOLD, 28));
            logo.setForeground(Aparencia.COR_DESTAQUE);
            logo.setAlignmentX(Component.CENTER_ALIGNMENT);
            contentPanel.add(logo);
        }

        contentPanel.add(Box.createVerticalStrut(10));

        // Título
        JLabel titulo = new JLabel("SHELFIE");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(Aparencia.COR_DESTAQUE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(titulo);

        contentPanel.add(Box.createVerticalStrut(5));

        // Subtítulo
        JLabel subtitulo = new JLabel("Controle de Jogos de Tabuleiro");
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitulo.setForeground(Aparencia.COR_TEXTO);
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(subtitulo);

        contentPanel.add(Box.createVerticalStrut(10));

        // Versão
        JLabel versao = new JLabel("Versão 1.0");
        versao.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        versao.setForeground(Aparencia.COR_TEXTO);
        versao.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(versao);

        contentPanel.add(Box.createVerticalStrut(20));

        // Desenvolvedores
        JLabel lblDevs = new JLabel("Desenvolvedores:");
        lblDevs.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblDevs.setForeground(Aparencia.COR_TEXTO);
        lblDevs.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(lblDevs);

        contentPanel.add(Box.createVerticalStrut(5));

        JLabel lblDev1 = new JLabel("Bárbara Lima");
        lblDev1.setForeground(Aparencia.COR_TEXTO);
        lblDev1.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(lblDev1);

        JLabel lblDev2 = new JLabel("Julia Tavares");
        lblDev2.setForeground(Aparencia.COR_TEXTO);
        lblDev2.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(lblDev2);

        JLabel lblDev3 = new JLabel("Letícia Miranda");
        lblDev3.setForeground(Aparencia.COR_TEXTO);
        lblDev3.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(lblDev3);

        JLabel lblDev4 = new JLabel("Lucas Viana");
        lblDev4.setForeground(Aparencia.COR_TEXTO);
        lblDev4.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(lblDev4);

        JLabel lblDev5 = new JLabel("Mileny Nazário");
        lblDev5.setForeground(Aparencia.COR_TEXTO);
        lblDev5.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(lblDev5);

        JLabel lblDev6 = new JLabel("Pedro Mendonça");
        lblDev6.setForeground(Aparencia.COR_TEXTO);
        lblDev6.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(lblDev6);

        JLabel lblDev7 = new JLabel("Ullisses Morais");
        lblDev7.setForeground(Aparencia.COR_TEXTO);
        lblDev7.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(lblDev7);

        contentPanel.add(Box.createVerticalStrut(15));

        // Informações acadêmicas
        JLabel lblDisciplina = new JLabel("Disciplina: Programação Orientada a Objetos");
        lblDisciplina.setForeground(Aparencia.COR_TEXTO);
        lblDisciplina.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(lblDisciplina);

        JLabel lblAno = new JLabel("Ano: 2026");
        lblAno.setForeground(Aparencia.COR_TEXTO);
        lblAno.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(lblAno);

        contentPanel.add(Box.createVerticalStrut(15));

        // Descrição
        JLabel lblDescricao1 = new JLabel("Sistema para gerenciamento de coleções");
        lblDescricao1.setForeground(Aparencia.COR_TEXTO);
        lblDescricao1.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(lblDescricao1);

        JLabel lblDescricao2 = new JLabel("de jogos de tabuleiro.");
        lblDescricao2.setForeground(Aparencia.COR_TEXTO);
        lblDescricao2.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(lblDescricao2);

        contentPanel.add(Box.createVerticalStrut(20));

        // Rodapé
        JLabel copyright = new JLabel("© Shelfie 2026");
        copyright.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        copyright.setForeground(Aparencia.COR_MENU);
        copyright.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(copyright);

        // Painel do botão
        JPanel buttonPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPane.setBackground(Aparencia.FUNDO_TELAS);

        JButton fecharButton = new JButton("Fechar");
        Aparencia.estilizarBotao(fecharButton);
        fecharButton.addActionListener(e -> dispose());

        buttonPane.add(fecharButton);

        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        // Ajusta automaticamente o tamanho da janela
        pack();

        // Centraliza na tela
        setLocationRelativeTo(null);
    }
}
