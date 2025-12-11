package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TelaBuscarClientes extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textCpfBusca;
    private JTextArea areaResultado;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaBuscarClientes frame = new TelaBuscarClientes();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaBuscarClientes() {
        setTitle("Buscar Cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("BUSCAR CLIENTE");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelBusca = new JPanel();
        panelBusca.setLayout(new BoxLayout(panelBusca, BoxLayout.X_AXIS));
        JLabel lblCpf = new JLabel("CPF do Cliente: ");
        lblCpf.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        textCpfBusca = new JTextField();

        JButton btnBuscar = new JButton("BUSCAR");
        btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnBuscar.addActionListener(this::buscarCliente);

        panelBusca.add(lblCpf);
        panelBusca.add(Box.createHorizontalStrut(10));
        panelBusca.add(textCpfBusca);
        panelBusca.add(Box.createHorizontalStrut(10));
        panelBusca.add(btnBuscar);

        contentPane.add(panelBusca, BorderLayout.NORTH);

        areaResultado = new JTextArea("Digite um CPF e clique em BUSCAR...");
        areaResultado.setEditable(false);
        areaResultado.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        areaResultado.setLineWrap(true);
        areaResultado.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(areaResultado);
        contentPane.add(scroll, BorderLayout.CENTER);

        JPanel panelSul = new JPanel();
        JButton btnVoltar = new JButton("VOLTAR");
        btnVoltar.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        btnVoltar.addActionListener(e -> {
            new TelaClientes().setVisible(true);
            dispose();
        });

        panelSul.add(btnVoltar);
        contentPane.add(panelSul, BorderLayout.SOUTH);
    }

    private void buscarCliente(ActionEvent e) {

        String cpfBusca = textCpfBusca.getText().trim();

        if (cpfBusca.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Digite um CPF para buscar!",
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (TelaListaClientes.modeloTabela == null) {
            JOptionPane.showMessageDialog(this,
                "Nenhum cliente cadastrado ainda!",
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean encontrado = false;
        StringBuilder texto = new StringBuilder();

        for (int i = 0; i < TelaListaClientes.modeloTabela.getRowCount(); i++) {
            String cpf = TelaListaClientes.modeloTabela.getValueAt(i, 1).toString();

            if (cpf.equals(cpfBusca)) {
                String nome = TelaListaClientes.modeloTabela.getValueAt(i, 0).toString();
                String telefone = TelaListaClientes.modeloTabela.getValueAt(i, 2).toString();
                String email = TelaListaClientes.modeloTabela.getValueAt(i, 3).toString();

                texto.append("Nome: ").append(nome)
                     .append("\nCPF: ").append(cpf)
                     .append("\nTelefone: ").append(telefone)
                     .append("\nEmail: ").append(email);

                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            areaResultado.setText(texto.toString());
        } else {
            areaResultado.setText("Cliente não encontrado!");
        }
    }
}
