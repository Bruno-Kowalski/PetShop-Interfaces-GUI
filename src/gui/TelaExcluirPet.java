package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TelaExcluirPet extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textCpfExcluir;
    private JTextArea areaResultado;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaExcluirPet frame = new TelaExcluirPet();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaExcluirPet() {
        setTitle("Excluir Pet");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 420);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout(12, 12));
        contentPane.setBorder(new EmptyBorder(16, 16, 16, 16));
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("EXCLUIR PET");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelBusca = new JPanel();
        panelBusca.setLayout(new BoxLayout(panelBusca, BoxLayout.X_AXIS));
        panelBusca.setBorder(new EmptyBorder(6, 6, 6, 6));

        JLabel lblCpf = new JLabel("CPF do Dono: ");
        lblCpf.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        textCpfExcluir = new JTextField();
        textCpfExcluir.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JButton btnExcluir = new JButton("EXCLUIR");
        btnExcluir.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnExcluir.addActionListener(this::excluirPet);

        panelBusca.add(lblCpf);
        panelBusca.add(Box.createHorizontalStrut(10));
        panelBusca.add(textCpfExcluir);
        panelBusca.add(Box.createHorizontalStrut(10));
        panelBusca.add(btnExcluir);

        contentPane.add(panelBusca, BorderLayout.NORTH);

        areaResultado = new JTextArea("Digite o CPF do dono e clique em EXCLUIR...");
        areaResultado.setEditable(false);
        areaResultado.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        areaResultado.setLineWrap(true);
        areaResultado.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(areaResultado);
        contentPane.add(scroll, BorderLayout.CENTER);

        JPanel panelSul = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnVoltar = new JButton("VOLTAR");
        btnVoltar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnVoltar.addActionListener(e -> {
            new TelaPets().setVisible(true);
            dispose();
        });
        panelSul.add(btnVoltar);
        contentPane.add(panelSul, BorderLayout.SOUTH);
    }

    private void excluirPet(ActionEvent e) {
        String cpfBusca = textCpfExcluir.getText().trim();

        if (cpfBusca.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Digite um CPF para excluir!",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (TelaListaPets.modeloTabela == null || TelaListaPets.modeloTabela.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this,
                    "Nenhum pet cadastrado ainda!",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        StringBuilder listaPets = new StringBuilder("Pets do CPF " + cpfBusca + ":\n");
        boolean encontrado = false;

        for (int i = 0; i < TelaListaPets.modeloTabela.getRowCount(); i++) {
            String cpf = TelaListaPets.modeloTabela.getValueAt(i, 1).toString();
            if (cpf.equals(cpfBusca)) {
                int id = (int) TelaListaPets.modeloTabela.getValueAt(i, 0);
                String nome = TelaListaPets.modeloTabela.getValueAt(i, 2).toString();
                listaPets.append("ID: ").append(id).append(" | Nome: ").append(nome).append("\n");
                encontrado = true;
            }
        }

        if (!encontrado) {
            areaResultado.setText("Nenhum pet encontrado para este CPF!");
            return;
        }

        String idStr = JOptionPane.showInputDialog(this,
                listaPets.toString() + "\nDigite o ID do pet que deseja excluir:");

        if (idStr == null || idStr.trim().isEmpty()) {
            areaResultado.setText("Exclusão cancelada.");
            return;
        }

        try {
            int idExcluir = Integer.parseInt(idStr.trim());
            boolean excluido = false;

            for (int i = 0; i < TelaListaPets.modeloTabela.getRowCount(); i++) {
                int id = (int) TelaListaPets.modeloTabela.getValueAt(i, 0);
                String cpf = TelaListaPets.modeloTabela.getValueAt(i, 1).toString();

                if (id == idExcluir && cpf.equals(cpfBusca)) {
                    String nome = TelaListaPets.modeloTabela.getValueAt(i, 2).toString();

                    int resp = JOptionPane.showConfirmDialog(
                            this,
                            "Confirma exclusão do pet \"" + nome + "\" (ID " + idExcluir + ")?",
                            "Confirmar exclusão",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (resp != JOptionPane.YES_OPTION) {
                        areaResultado.setText("Exclusão cancelada.");
                        return;
                    }

                    TelaListaPets.modeloTabela.removeRow(i);

                    JOptionPane.showMessageDialog(
                            this,
                            "PET \"" + nome + "\" foi EXCLUÍDO com sucesso!",
                            "Sucesso",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    areaResultado.setText("");

                    excluido = true;
                    break;
                }
            }

            if (!excluido) {
                areaResultado.setText("Nenhum pet encontrado com esse ID para o CPF informado!");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
