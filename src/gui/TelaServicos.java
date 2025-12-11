package gui;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Font;

public class TelaServicos extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaServicos frame = new TelaServicos();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaServicos() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 350);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JLabel lblTitulo = new JLabel("--- MENU SERVIÇOS ---");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblTitulo, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        Font fontePadrao = new Font("Arial", Font.PLAIN, 14);

        JButton btnContratarServico = new JButton("CONTRATAR SERVIÇO");
        btnContratarServico.setFont(fontePadrao);
        btnContratarServico.setBounds(10, 30, 220, 45);
        btnContratarServico.addActionListener(e -> {
            new TelaContratarServico().setVisible(true);
            dispose();
        });
        panel.add(btnContratarServico);

        JButton btnListarServicos = new JButton("LISTAR SERVIÇOS");
        btnListarServicos.setFont(fontePadrao);
        btnListarServicos.setBounds(250, 30, 220, 45);
        btnListarServicos.addActionListener(e -> {
            new TelaListaServicos().setVisible(true);
            dispose();
        });
        panel.add(btnListarServicos);

        JButton btnContratarPacote = new JButton("CONTRATAR PACOTE");
        btnContratarPacote.setFont(fontePadrao);
        btnContratarPacote.setBounds(10, 100, 220, 45);
        btnContratarPacote.addActionListener(e -> {
            new TelaContratarPacote().setVisible(true);
            dispose();
        });
        panel.add(btnContratarPacote);

        JButton btnBuscarServico = new JButton("BUSCAR SERVIÇOS");
        btnBuscarServico.setFont(fontePadrao);
        btnBuscarServico.setBounds(250, 100, 220, 45);
        btnBuscarServico.addActionListener(e -> {
            new TelaBuscarServicos().setVisible(true);
            dispose();
        });
        panel.add(btnBuscarServico);

        JButton btnCancelarServico = new JButton("CANCELAR SERVIÇOS");
        btnCancelarServico.setFont(fontePadrao);
        btnCancelarServico.setBounds(160, 170, 180, 45);
        btnCancelarServico.addActionListener(e -> {
            new TelaCancelarServicos().setVisible(true);
            dispose();
        });
        panel.add(btnCancelarServico);

        JButton btnVoltar = new JButton("VOLTAR");
        btnVoltar.setFont(new Font("Arial", Font.PLAIN, 12));
        btnVoltar.setBounds(180, 240, 130, 35);
        btnVoltar.addActionListener(e -> {
            new TelaPrincipal().setVisible(true);
            dispose();
        });
        panel.add(btnVoltar);
    }
}
