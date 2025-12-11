package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TelaCancelarServicos extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tabelaServicos;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaCancelarServicos frame = new TelaCancelarServicos();
                frame.setVisible(true);
            } catch (Exception e) { e.printStackTrace(); }
        });
    }

    public TelaCancelarServicos() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 400);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("--- CANCELAR SERVIÇO ---");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        contentPane.add(lblTitulo, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        tabelaServicos = new JTable(TelaListaServicos.modeloTabela);
        scrollPane.setViewportView(tabelaServicos);

        JPanel panelSul = new JPanel();
        JButton btnCancelar = new JButton("CANCELAR SERVIÇO");
        btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
        btnCancelar.addActionListener(e -> cancelarServicoSelecionado());
        panelSul.add(btnCancelar);

        JButton btnVoltar = new JButton("VOLTAR");
        btnVoltar.setFont(new Font("Arial", Font.PLAIN, 12));
        btnVoltar.addActionListener(e -> {
            new TelaServicos().setVisible(true);
            dispose();
        });
        panelSul.add(btnVoltar);

        contentPane.add(panelSul, BorderLayout.SOUTH);
    }

    private void cancelarServicoSelecionado() {
        int linhaSelecionada = tabelaServicos.getSelectedRow();

        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um serviço para cancelar!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String data = TelaListaServicos.modeloTabela.getValueAt(linhaSelecionada, 5).toString(); // coluna Data
        if (data == null || data.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Este serviço não está agendado e não pode ser cancelado!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacao = JOptionPane.showConfirmDialog(this,
                "Tem certeza que deseja cancelar este serviço?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            TelaListaServicos.modeloTabela.removeRow(linhaSelecionada);
            JOptionPane.showMessageDialog(this, "Serviço cancelado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
