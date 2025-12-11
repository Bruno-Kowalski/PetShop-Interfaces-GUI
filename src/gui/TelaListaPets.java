package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class TelaListaPets extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static DefaultTableModel modeloTabela = new DefaultTableModel(
        new Object[][] {},
        new String[] { "ID", "CPF Dono", "Nome", "Espécie", "Raça", "Idade", "Peso" }
    ); 

    private JTable tabelaPets;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaListaPets frame = new TelaListaPets();
                frame.setVisible(true);
            } catch (Exception e) { e.printStackTrace(); }
        });
    }

    public TelaListaPets() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 300);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("--- LISTA DE PETS ---");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        contentPane.add(lblTitulo, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        tabelaPets = new JTable(modeloTabela);
        scrollPane.setViewportView(tabelaPets);

        JPanel panelSul = new JPanel();
        JButton btnVoltar = new JButton("VOLTAR");
        btnVoltar.setFont(new Font("Arial", Font.PLAIN, 12));
        btnVoltar.addActionListener(e -> {
            new TelaPets().setVisible(true);
            dispose();
        });
        panelSul.add(btnVoltar);
        contentPane.add(panelSul, BorderLayout.SOUTH);
    }
}
