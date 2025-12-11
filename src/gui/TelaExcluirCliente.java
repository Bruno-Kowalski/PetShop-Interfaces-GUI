package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TelaExcluirCliente extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textCpfExcluir;
    private JTextArea areaResultado;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaExcluirCliente frame = new TelaExcluirCliente();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaExcluirCliente() {
        setTitle("Excluir Cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("EXCLUIR CLIENTE");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelBusca = new JPanel();
        panelBusca.setLayout(new BoxLayout(panelBusca, BoxLayout.X_AXIS));

        JLabel lblCpf = new JLabel("CPF do Cliente: ");
        lblCpf.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        textCpfExcluir = new JTextField();

        JButton btnExcluir = new JButton("EXCLUIR");
        btnExcluir.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnExcluir.addActionListener(this::excluirCliente);

        panelBusca.add(lblCpf);
        panelBusca.add(Box.createHorizontalStrut(10));
        panelBusca.add(textCpfExcluir);
        panelBusca.add(Box.createHorizontalStrut(10));
        panelBusca.add(btnExcluir);

        contentPane.add(panelBusca, BorderLayout.NORTH);

        areaResultado = new JTextArea("Digite um CPF e clique em EXCLUIR...");
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

    private void excluirCliente(ActionEvent e) {

        String cpfBusca = textCpfExcluir.getText().trim();

        if (cpfBusca.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Digite um CPF para excluir!",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (TelaListaClientes.modeloTabela == null ||
            TelaListaClientes.modeloTabela.getRowCount() == 0) {

            JOptionPane.showMessageDialog(this,
                    "Nenhum cliente cadastrado ainda!",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean encontrado = false;

        for (int i = 0; i < TelaListaClientes.modeloTabela.getRowCount(); i++) {
            String cpf = TelaListaClientes.modeloTabela.getValueAt(i, 1).toString();

            if (cpf.equals(cpfBusca)) {

                String nomeCliente = TelaListaClientes.modeloTabela.getValueAt(i, 0).toString();

                TelaListaClientes.modeloTabela.removeRow(i);

                areaResultado.setText(
                    "Cliente \"" + nomeCliente + "\" com CPF " + cpfBusca + " foi EXCLUÍDO com sucesso!"
                );

                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            areaResultado.setText("Cliente NÃO encontrado!");
        }
    }
}
