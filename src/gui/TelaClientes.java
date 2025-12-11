package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;

public class TelaClientes extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaClientes frame = new TelaClientes();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaClientes() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JLabel lblTitulo = new JLabel("--- MENU CLIENTES ---");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblTitulo, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        Font fontePadrao = new Font("Arial", Font.PLAIN, 14);

        JButton btnCadastrarCliente = new JButton("CADASTRAR CLIENTE");
        btnCadastrarCliente.setFont(fontePadrao);
        btnCadastrarCliente.setBounds(10, 26, 187, 51);
        btnCadastrarCliente.addActionListener(e -> {
            new TelaCadastrarCliente().setVisible(true);
            dispose();
        });
        panel.add(btnCadastrarCliente);

        JButton btnListarCliente = new JButton("LISTAR CLIENTE");
        btnListarCliente.setFont(fontePadrao);
        btnListarCliente.setBounds(225, 26, 189, 51);
        btnListarCliente.addActionListener(e -> {
            new TelaListaClientes().setVisible(true);
            dispose();
        });
        panel.add(btnListarCliente);

        JButton btnBuscarCliente = new JButton("BUSCAR CLIENTE");
        btnBuscarCliente.setFont(fontePadrao);
        btnBuscarCliente.setBounds(10, 106, 187, 51);
        btnBuscarCliente.addActionListener(e -> {
            new TelaBuscarClientes().setVisible(true);
            dispose();
        });
        panel.add(btnBuscarCliente);

        JButton btnExcluirCliente = new JButton("EXCLUIR CLIENTE");
        btnExcluirCliente.setFont(fontePadrao);
        btnExcluirCliente.setBounds(225, 106, 189, 51);
        btnExcluirCliente.addActionListener(e -> {
            new TelaExcluirCliente().setVisible(true);
            dispose();
        });
        panel.add(btnExcluirCliente);

        JButton btnVoltar = new JButton("VOLTAR");
        btnVoltar.setFont(new Font("Arial", Font.PLAIN, 12));
        btnVoltar.setBounds(150, 190, 130, 35);
        btnVoltar.addActionListener(e -> {
            new TelaPrincipal().setVisible(true);
            dispose();
        });
        panel.add(btnVoltar);
    }
}
