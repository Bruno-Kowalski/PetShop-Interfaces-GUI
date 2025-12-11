package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class TelaListaClientes extends JFrame {

    private JPanel contentPane;
    public static DefaultTableModel modeloTabela; 
    private JTable tabelaClientes;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaListaClientes frame = new TelaListaClientes();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaListaClientes() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 200, 900, 300);
        setLocationRelativeTo(null); // 

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("--- LISTA DE CLIENTES ---");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        contentPane.add(lblTitulo, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        if (modeloTabela == null) {
            modeloTabela = new DefaultTableModel(
                new Object[][] {},
                new String[] { "Nome", "CPF", "Telefone", "Email" }
            );
        }

        tabelaClientes = new JTable(modeloTabela);
        scrollPane.setViewportView(tabelaClientes);

        JPanel panelSul = new JPanel();

        JButton btnAtualizar = new JButton("ATUALIZAR DADOS");
        btnAtualizar.setFont(new Font("Arial", Font.PLAIN, 12));
        btnAtualizar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                "Dados atualizados com sucesso!",
                "Confirmação",
                JOptionPane.INFORMATION_MESSAGE);
        });
        panelSul.add(btnAtualizar);

        JButton btnVoltar = new JButton("VOLTAR");
        btnVoltar.setFont(new Font("Arial", Font.PLAIN, 12));
        btnVoltar.addActionListener(e -> {
            new TelaClientes().setVisible(true);
            dispose();
        });
        panelSul.add(btnVoltar);

        contentPane.add(panelSul, BorderLayout.SOUTH);
    }
}
