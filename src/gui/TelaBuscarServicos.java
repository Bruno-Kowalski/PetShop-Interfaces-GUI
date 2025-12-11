package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class TelaBuscarServicos extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textCpfBusca;
    private JTextArea areaResultado;

    public TelaBuscarServicos() {
        setTitle("Buscar Serviços");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 450);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("BUSCAR SERVIÇOS");
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
        btnBuscar.addActionListener(this::buscarServicos);

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
            new TelaServicos().setVisible(true);
            dispose();
        });
        panelSul.add(btnVoltar);
        contentPane.add(panelSul, BorderLayout.SOUTH);
    }

    private String buscarNomeCliente(String cpfBusca) {

        DefaultTableModel modelo = TelaListaClientes.modeloTabela;

        if (modelo == null || modelo.getRowCount() == 0) return null;

        for (int i = 0; i < modelo.getRowCount(); i++) {

            String cpf = String.valueOf(modelo.getValueAt(i, 1));

            if (cpf.equals(cpfBusca)) {
                return String.valueOf(modelo.getValueAt(i, 0)); // Nome
            }
        }

        return null;
    }

    private void buscarServicos(ActionEvent e) {

        String cpfBusca = textCpfBusca.getText().trim();

        if (cpfBusca.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite um CPF!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nomeCliente = buscarNomeCliente(cpfBusca);

        if (nomeCliente == null) {
            areaResultado.setText("Nenhum cliente encontrado com esse CPF!");
            return;
        }

        DefaultTableModel modeloServicos = TelaListaServicos.modeloTabela;

        if (modeloServicos.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Nenhum serviço cadastrado!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        StringBuilder resultado = new StringBuilder();

        resultado.append("CLIENTE: ").append(nomeCliente).append("\n");
        resultado.append("CPF: ").append(cpfBusca).append("\n");
        resultado.append("======================================\n\n");

        boolean encontrado = false;

        for (int i = 0; i < modeloServicos.getRowCount(); i++) {

            String cpf = String.valueOf(modeloServicos.getValueAt(i, 2));

            if (cpf.equals(cpfBusca)) {

                encontrado = true;

                resultado.append("ID: ").append(modeloServicos.getValueAt(i, 0)).append("\n");
                resultado.append("Serviço: ").append(modeloServicos.getValueAt(i, 1)).append("\n");
                resultado.append("Pet: ").append(modeloServicos.getValueAt(i, 4)).append("\n");
                resultado.append("Data: ").append(modeloServicos.getValueAt(i, 5)).append("\n");
                resultado.append("Descrição: ").append(modeloServicos.getValueAt(i, 6)).append("\n");
                resultado.append("Valor: R$ ").append(modeloServicos.getValueAt(i, 7)).append("\n");
                resultado.append("--------------------------------------\n\n");
            }
        }

        if (!encontrado) {
            resultado.append("Nenhum serviço encontrado para este CPF!");
        }

        areaResultado.setText(resultado.toString());
    }
}
