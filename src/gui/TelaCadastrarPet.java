package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TelaCadastrarPet extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textCpfDonoPet, textNomePet, textEspeciePet, textRacaPet, textIdadePet, textPesoPet;
    private JPanel painelCamposPet;

    private static int contadorId = 1;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaCadastrarPet frame = new TelaCadastrarPet();
                frame.setVisible(true);
            } catch (Exception e) { e.printStackTrace(); }
        });
    }

    public TelaCadastrarPet() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 400);
        setLocationRelativeTo(null);

        contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("--- CADASTRO DE PET ---");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        contentPane.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelCpf = new JPanel(new GridLayout(1, 3, 5, 5));
        textCpfDonoPet = new JTextField();
        panelCpf.add(new JLabel("CPF DONO DO PET:", SwingConstants.RIGHT));
        panelCpf.add(textCpfDonoPet);
        JButton btnConfirmarCpf = new JButton("Confirmar CPF");
        panelCpf.add(btnConfirmarCpf);
        contentPane.add(panelCpf, BorderLayout.NORTH);

        painelCamposPet = new JPanel(new GridLayout(5, 2, 10, 10));
        contentPane.add(painelCamposPet, BorderLayout.CENTER);

        painelCamposPet.add(new JLabel("NOME DO PET:", SwingConstants.CENTER));
        textNomePet = new JTextField(); painelCamposPet.add(textNomePet);

        painelCamposPet.add(new JLabel("ESPÉCIE DO PET:", SwingConstants.CENTER));
        textEspeciePet = new JTextField(); painelCamposPet.add(textEspeciePet);

        painelCamposPet.add(new JLabel("RAÇA DO PET:", SwingConstants.CENTER));
        textRacaPet = new JTextField(); painelCamposPet.add(textRacaPet);

        painelCamposPet.add(new JLabel("IDADE DO PET:", SwingConstants.CENTER));
        textIdadePet = new JTextField(); painelCamposPet.add(textIdadePet);

        painelCamposPet.add(new JLabel("PESO DO PET:", SwingConstants.CENTER));
        textPesoPet = new JTextField(); painelCamposPet.add(textPesoPet);

        painelCamposPet.setVisible(false);

        JPanel panelBotoes = new JPanel();
        JButton btnSalvar = new JButton("SALVAR");
        JButton btnVoltar = new JButton("VOLTAR");
        panelBotoes.add(btnSalvar); 
        panelBotoes.add(btnVoltar);
        contentPane.add(panelBotoes, BorderLayout.SOUTH);

        btnConfirmarCpf.addActionListener(e -> {
            String cpf = textCpfDonoPet.getText().trim();
            if (cpfExisteNosClientes(cpf)) {
                JOptionPane.showMessageDialog(this, "CPF encontrado! Agora preencha os dados do pet.");
                painelCamposPet.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "CPF não encontrado nos clientes cadastrados!");
            }
        });

        btnSalvar.addActionListener(e -> salvarPet());
        btnVoltar.addActionListener(e -> { new TelaPets().setVisible(true); dispose(); });
    }

    private boolean cpfExisteNosClientes(String cpfBusca) {
        if (cpfBusca == null || cpfBusca.isEmpty()) return false;
        if (TelaListaClientes.modeloTabela == null) return false;

        for (int i = 0; i < TelaListaClientes.modeloTabela.getRowCount(); i++) {
            String cpf = TelaListaClientes.modeloTabela.getValueAt(i, 1).toString();
            if (cpf.equals(cpfBusca)) return true;
        }
        return false;
    }

    private void salvarPet() {
        String cpf = textCpfDonoPet.getText().trim();
        String nome = textNomePet.getText().trim();
        String especie = textEspeciePet.getText().trim();
        String raca = textRacaPet.getText().trim();
        String idadeStr = textIdadePet.getText().trim();
        String pesoStr = textPesoPet.getText().trim();


        if (cpf.isEmpty() || nome.isEmpty() || especie.isEmpty() || raca.isEmpty() ||
                idadeStr.isEmpty() || pesoStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Preencha todos os campos antes de salvar!",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!cpfExisteNosClientes(cpf)) {
            JOptionPane.showMessageDialog(this,
                    "CPF do dono não encontrado! Cadastre o cliente primeiro.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idade;
        try {
            idade = Integer.parseInt(idadeStr);
            if (idade <= 0) {
                JOptionPane.showMessageDialog(this,
                        "A idade deve ser maior que 0!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Idade inválida! Digite apenas números inteiros.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        float peso;
        try {
            peso = Float.parseFloat(pesoStr.replace(",", "."));
            if (peso <= 0) {
                JOptionPane.showMessageDialog(this,
                        "O peso deve ser maior que 0!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Peso inválido! Digite apenas números (ex: 4.3).",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = contadorId++;
        Object[] linha = { id, cpf, nome, especie, raca, idade, peso };
        TelaListaPets.modeloTabela.addRow(linha);

        JOptionPane.showMessageDialog(this,
                "Pet cadastrado com sucesso!",
                "Confirmação",
                JOptionPane.INFORMATION_MESSAGE);

        textNomePet.setText("");
        textEspeciePet.setText("");
        textRacaPet.setText("");
        textIdadePet.setText("");
        textPesoPet.setText("");
    }
}
