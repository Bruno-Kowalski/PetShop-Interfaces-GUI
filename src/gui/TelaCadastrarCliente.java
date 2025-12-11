package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TelaCadastrarCliente extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textNomeCliente;
    private JTextField textCpfCliente;
    private JTextField textTelefoneCliente;
    private JTextField textEmailCliente;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaCadastrarCliente frame = new TelaCadastrarCliente();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaCadastrarCliente() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 350);
        setLocationRelativeTo(null); 

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("--- CADASTRO DE CLIENTES ---");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(120, 10, 250, 30);
        contentPane.add(lblTitulo);

        Font fonteCampos = new Font("Arial", Font.PLAIN, 13);

        JLabel lblNome = new JLabel("NOME:");
        lblNome.setFont(fonteCampos);
        lblNome.setBounds(50, 60, 100, 25);
        contentPane.add(lblNome);

        textNomeCliente = new JTextField();
        textNomeCliente.setBounds(160, 60, 250, 25);
        contentPane.add(textNomeCliente);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setFont(fonteCampos);
        lblCpf.setBounds(50, 100, 100, 25);
        contentPane.add(lblCpf);

        textCpfCliente = new JTextField();
        textCpfCliente.setBounds(160, 100, 250, 25);
        contentPane.add(textCpfCliente);

        JLabel lblTelefone = new JLabel("TELEFONE:");
        lblTelefone.setFont(fonteCampos);
        lblTelefone.setBounds(50, 140, 100, 25);
        contentPane.add(lblTelefone);

        textTelefoneCliente = new JTextField();
        textTelefoneCliente.setBounds(160, 140, 250, 25);
        contentPane.add(textTelefoneCliente);

        JLabel lblEmail = new JLabel("E-MAIL:");
        lblEmail.setFont(fonteCampos);
        lblEmail.setBounds(50, 180, 100, 25);
        contentPane.add(lblEmail);

        textEmailCliente = new JTextField();
        textEmailCliente.setBounds(160, 180, 250, 25);
        contentPane.add(textEmailCliente);

        JButton btnSalvar = new JButton("SALVAR");
        btnSalvar.setFont(new Font("Arial", Font.PLAIN, 14));
        btnSalvar.setBounds(100, 240, 130, 40);
        btnSalvar.addActionListener(e -> salvarCliente());
        contentPane.add(btnSalvar);

        JButton btnVoltar = new JButton("VOLTAR");
        btnVoltar.setFont(new Font("Arial", Font.PLAIN, 12));
        btnVoltar.setBounds(260, 245, 100, 30); // menor que o SALVAR
        btnVoltar.addActionListener(e -> {
            new TelaClientes().setVisible(true);
            dispose();
        });
        contentPane.add(btnVoltar);
    }



    private boolean telefoneValido(String telefone) {
        String numeros = telefone.replaceAll("\\D", ""); // mantém só números
        return numeros.length() >= 8 && numeros.length() <= 11;
    }

    private boolean emailValido(String email) {
        return email.contains("@") &&
               email.contains(".") &&
               email.indexOf("@") < email.lastIndexOf(".");
    }

    private void salvarCliente() {
        String nome = textNomeCliente.getText().trim();
        String cpf = textCpfCliente.getText().trim();
        String telefone = textTelefoneCliente.getText().trim();
        String email = textEmailCliente.getText().trim();

        if (nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Preencha todos os campos antes de salvar!",
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!telefoneValido(telefone)) {
            JOptionPane.showMessageDialog(this,
                "Telefone inválido! Digite somente números (8 a 11 dígitos).",
                "Erro",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!emailValido(email)) {
            JOptionPane.showMessageDialog(this,
                "E-mail inválido! Exemplo válido: usuario@dominio.com",
                "Erro",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        Object[] linha = { nome, cpf, telefone, email };

        if (TelaListaClientes.modeloTabela == null) {
            TelaListaClientes.modeloTabela = new DefaultTableModel(
                new Object[][] {},
                new String[] { "Nome", "CPF", "Telefone", "Email" }
            );
        }

        TelaListaClientes.modeloTabela.addRow(linha);

        JOptionPane.showMessageDialog(this,
            "Cliente cadastrado com sucesso!",
            "Confirmação",
            JOptionPane.INFORMATION_MESSAGE);

        textNomeCliente.setText("");
        textCpfCliente.setText("");
        textTelefoneCliente.setText("");
        textEmailCliente.setText("");
    }
}
