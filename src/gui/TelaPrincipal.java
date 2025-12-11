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

public class TelaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaPrincipal frame = new TelaPrincipal();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaPrincipal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null); 

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JLabel lblPetClinica = new JLabel("======= BEM-VINDO À PETCLÍNICA =======");
        lblPetClinica.setFont(new Font("Arial", Font.BOLD, 14));
        lblPetClinica.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblPetClinica, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JButton btnClientes = new JButton("GERENCIAR CLIENTES");
        btnClientes.setFont(new Font("Arial", Font.PLAIN, 14));
        btnClientes.setBounds(90, 11, 243, 39);
        btnClientes.addActionListener(e -> {
            new TelaClientes().setVisible(true);
            dispose();
        });
        panel.add(btnClientes);

        JButton btnPets = new JButton("GERENCIAR PETS");
        btnPets.setFont(new Font("Arial", Font.PLAIN, 14));
        btnPets.setBounds(90, 61, 243, 39);
        btnPets.addActionListener(e -> {
            new TelaPets().setVisible(true);
            dispose();
        });
        panel.add(btnPets);

        JButton btnServicos = new JButton("GERENCIAR SERVIÇOS");
        btnServicos.setFont(new Font("Arial", Font.PLAIN, 14));
        btnServicos.setBounds(90, 111, 243, 39);
        btnServicos.addActionListener(e -> {
            new TelaServicos().setVisible(true);
            dispose();
        });
        panel.add(btnServicos);

        JButton btnSair = new JButton("SAIR");
        btnSair.setFont(new Font("Arial", Font.PLAIN, 12));
        btnSair.setBounds(115, 184, 200, 30); // menor largura e altura
        btnSair.addActionListener(e -> System.exit(0));
        panel.add(btnSair);
    }
}
