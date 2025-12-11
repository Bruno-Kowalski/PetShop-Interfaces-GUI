package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TelaBuscarPets extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textCpfBusca;
    private JTextArea areaResultado;

    public TelaBuscarPets() {

        setTitle("Buscar Pet");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("BUSCAR PET");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelBusca = new JPanel();
        panelBusca.setLayout(new BoxLayout(panelBusca, BoxLayout.X_AXIS));

        JLabel lblCpf = new JLabel("CPF do Dono: ");
        lblCpf.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        textCpfBusca = new JTextField();

        JButton btnBuscar = new JButton("BUSCAR");
        btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnBuscar.addActionListener(this::buscarPet);

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
            new TelaPets().setVisible(true);
            dispose();
        });

        panelSul.add(btnVoltar);
        contentPane.add(panelSul, BorderLayout.SOUTH);
    }

    private void buscarPet(ActionEvent e) {

        String cpfBusca = textCpfBusca.getText().trim();

        if (cpfBusca.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Digite um CPF para buscar!",
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (TelaListaPets.modeloTabela == null) {
            JOptionPane.showMessageDialog(this,
                "Nenhum pet cadastrado ainda!",
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean encontrado = false;
        StringBuilder texto = new StringBuilder();

        for (int i = 0; i < TelaListaPets.modeloTabela.getRowCount(); i++) {

            String cpf = TelaListaPets.modeloTabela.getValueAt(i, 1).toString();

            if (cpf.equals(cpfBusca)) {

                int id = (int) TelaListaPets.modeloTabela.getValueAt(i, 0);
                String nome = TelaListaPets.modeloTabela.getValueAt(i, 2).toString();
                String especie = TelaListaPets.modeloTabela.getValueAt(i, 3).toString();
                String raca = TelaListaPets.modeloTabela.getValueAt(i, 4).toString();
                String idade = TelaListaPets.modeloTabela.getValueAt(i, 5).toString();
                String peso = TelaListaPets.modeloTabela.getValueAt(i, 6).toString();

                texto.append("ID: ").append(id)
                     .append("\nNome do PET: ").append(nome)
                     .append("\nEspécie: ").append(especie)
                     .append("\nRaça: ").append(raca)
                     .append("\nIdade: ").append(idade + " anos")
                     .append("\nPeso: ").append(peso + "kg")
                     .append("\n----------------------------------\n");

                encontrado = true;
            }
        }

        if (encontrado) {
            areaResultado.setText(texto.toString());
        } else {
            areaResultado.setText("Nenhum pet encontrado para este CPF!");
        }
    }
}
