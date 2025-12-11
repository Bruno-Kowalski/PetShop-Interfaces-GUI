package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class TelaListaServicos extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static DefaultTableModel modeloTabela = new DefaultTableModel(
        new Object[][] {},
        new String[] { "ID", "Tipo", "CPF Cliente", "Nome Cliente", "Pet", "Data", "Descrição", "Valor" }
    );

    private JTable tabelaServicos;

    public TelaListaServicos() {
        setTitle("Lista de Serviços");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 400);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout(10, 10));
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("--- LISTA DE SERVIÇOS ---");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));

        JPanel topo = new JPanel();
        topo.setLayout(new BoxLayout(topo, BoxLayout.Y_AXIS));
        topo.add(lblTitulo);

        contentPane.add(topo, BorderLayout.NORTH);

         tabelaServicos = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaServicos);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel panelSul = new JPanel();
        JButton btnVoltar = new JButton("VOLTAR");

        btnVoltar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVoltar.addActionListener(e -> {
            new TelaServicos().setVisible(true);
            dispose();
        });

        panelSul.add(btnVoltar);
        contentPane.add(panelSul, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        new TelaListaServicos().setVisible(true);
    }
}
