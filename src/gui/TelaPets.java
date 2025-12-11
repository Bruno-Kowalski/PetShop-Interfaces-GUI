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

public class TelaPets extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaPets frame = new TelaPets();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaPets() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null); 

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JLabel lblTitulo = new JLabel("--- MENU PETS ---");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblTitulo, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        Font fontePadrao = new Font("Arial", Font.PLAIN, 14);

        JButton btnCadastrarPet = new JButton("CADASTRAR PET");
        btnCadastrarPet.setFont(fontePadrao);
        btnCadastrarPet.setBounds(26, 28, 155, 53);
        btnCadastrarPet.addActionListener(e -> {
            new TelaCadastrarPet().setVisible(true);
            dispose();
        });
        panel.add(btnCadastrarPet);

        JButton btnListarPets = new JButton("LISTAR PET");
        btnListarPets.setFont(fontePadrao);
        btnListarPets.setBounds(239, 28, 155, 53);
        btnListarPets.addActionListener(e -> {
            new TelaListaPets().setVisible(true);
            dispose();
        });
        panel.add(btnListarPets);

        JButton btnBuscarPet = new JButton("BUSCAR PET");
        btnBuscarPet.setFont(fontePadrao);
        btnBuscarPet.setBounds(26, 109, 155, 53);
        btnBuscarPet.addActionListener(e -> {
            new TelaBuscarPets().setVisible(true);
            dispose();
        });
        panel.add(btnBuscarPet);

        JButton btnExcluirPet = new JButton("EXCLUIR PET");
        btnExcluirPet.setFont(fontePadrao);
        btnExcluirPet.setBounds(239, 109, 155, 53);
        btnExcluirPet.addActionListener(e -> {
            new TelaExcluirPet().setVisible(true);
            dispose();
        });
        panel.add(btnExcluirPet);

        JButton btnVoltar = new JButton("VOLTAR");
        btnVoltar.setFont(new Font("Arial", Font.PLAIN, 12));
        btnVoltar.setBounds(145, 190, 130, 35);
        btnVoltar.addActionListener(e -> {
            new TelaPrincipal().setVisible(true);
            dispose();
        });
        panel.add(btnVoltar);
    }
}
