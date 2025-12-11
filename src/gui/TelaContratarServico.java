package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

public class TelaContratarServico extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textCpfCliente;
    private JComboBox<String> comboServico;
    private JComboBox<String> comboPets;
    private JTextArea areaResultado;

    private JSpinner spinnerData;
    private JPanel painelHospedagem;
    private JSpinner spinnerDiasHospedagem;

    private static int contadorId = 1;

    private HashMap<String, Double> tabelaValores = new HashMap<>();

    public TelaContratarServico() {
        setTitle("Contratar Serviço Avulso");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 700);
        setLocationRelativeTo(null);

        tabelaValores.put("Banho", 80.0);
        tabelaValores.put("Tosa", 100.0);
        tabelaValores.put("Consulta Veterinária", 200.0);
        tabelaValores.put("Hospedagem", 150.0); 
        tabelaValores.put("Adestramento", 180.0);

        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBorder(new EmptyBorder(15, 20, 15, 20));
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("CONTRATAR SERVIÇO AVULSO");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(lblTitulo);
        contentPane.add(Box.createVerticalStrut(20));

        textCpfCliente = criarCampo("CPF do Cliente:");

        JButton btnListarPets = new JButton("Listar Pets do Cliente");
        btnListarPets.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnListarPets.addActionListener(e -> listarPets());
        contentPane.add(btnListarPets);
        contentPane.add(Box.createVerticalStrut(10));

        comboPets = new JComboBox<>();
        comboPets.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        comboPets.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboPets.setAlignmentX(Component.CENTER_ALIGNMENT);
        comboPets.setEnabled(false);
        estilizarCombo("Selecione o Pet:", comboPets);

        comboServico = new JComboBox<>(new String[]{
                "Banho R$80,00",
                "Tosa R$100,00",
                "Consulta Veterinária R$200,00",
                "Hospedagem R$150,00 (por dia)",
                "Adestramento R$180,00"
        });
        estilizarCombo("Serviço:", comboServico);

        comboServico.addActionListener(e -> atualizarPainelHospedagem());

        painelHospedagem = new JPanel();
        painelHospedagem.setLayout(new BoxLayout(painelHospedagem, BoxLayout.Y_AXIS));
        painelHospedagem.setVisible(false);

        JLabel lblDias = new JLabel("Quantidade de Dias (Hospedagem):");
        lblDias.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblDias.setAlignmentX(Component.CENTER_ALIGNMENT);

        spinnerDiasHospedagem = new JSpinner(new SpinnerNumberModel(1, 1, 60, 1));
        spinnerDiasHospedagem.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        spinnerDiasHospedagem.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelHospedagem.add(lblDias);
        painelHospedagem.add(spinnerDiasHospedagem);
        painelHospedagem.add(Box.createVerticalStrut(15));

        contentPane.add(painelHospedagem);

        JLabel lblData = new JLabel("Data de Agendamento:");
        lblData.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblData.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(lblData);

        spinnerData = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinnerData, "dd/MM/yyyy");
        spinnerData.setEditor(editor);
        spinnerData.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        spinnerData.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(spinnerData);
        contentPane.add(Box.createVerticalStrut(15));

        JButton btnSalvar = new JButton("CONTRATAR SERVIÇO");
        btnSalvar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnSalvar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSalvar.addActionListener(e -> contratarServico());
        contentPane.add(btnSalvar);
        contentPane.add(Box.createVerticalStrut(10));

        JButton btnVoltar = new JButton("VOLTAR");
        btnVoltar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVoltar.addActionListener(e -> {
            new TelaServicos().setVisible(true);
            dispose();
        });
        contentPane.add(btnVoltar);

        areaResultado = new JTextArea("Resultado aparecerá aqui...");
        areaResultado.setEditable(false);
        areaResultado.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        areaResultado.setLineWrap(true);
        areaResultado.setWrapStyleWord(true);
        areaResultado.setBorder(BorderFactory.createTitledBorder("Resumo"));
        contentPane.add(Box.createVerticalStrut(20));
        contentPane.add(areaResultado);
    }

    private JTextField criarCampo(String label) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(lbl);

        JTextField campo = new JTextField();
        campo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campo.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(campo);
        contentPane.add(Box.createVerticalStrut(10));
        return campo;
    }

    private void estilizarCombo(String label, JComboBox<String> combo) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(lbl);

        combo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        combo.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(combo);
        contentPane.add(Box.createVerticalStrut(10));
    }

    private void atualizarPainelHospedagem() {
        String servico = (String) comboServico.getSelectedItem();
        painelHospedagem.setVisible(servico != null && servico.toLowerCase().contains("hospedagem"));
        revalidate();
        repaint();
    }

    private void listarPets() {
        comboPets.removeAllItems();
        String cpfBusca = textCpfCliente.getText().trim();

        if (cpfBusca.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite o CPF do cliente!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (TelaListaPets.modeloTabela == null || TelaListaPets.modeloTabela.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Nenhum pet cadastrado ainda!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean encontrado = false;

        for (int i = 0; i < TelaListaPets.modeloTabela.getRowCount(); i++) {
            String cpf = TelaListaPets.modeloTabela.getValueAt(i, 1).toString();
            if (cpf.equals(cpfBusca)) {
                int id = Integer.parseInt(TelaListaPets.modeloTabela.getValueAt(i, 0).toString());
                String nome = TelaListaPets.modeloTabela.getValueAt(i, 2).toString();
                comboPets.addItem(id + " - " + nome);
                encontrado = true;
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(this, "Nenhum pet encontrado para este CPF!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            comboPets.setEnabled(false);
        } else {
            comboPets.setEnabled(true);
        }
    }

    private String buscarNomeCliente(String cpfBusca) {
        if (TelaListaClientes.modeloTabela == null) return null;

        for (int i = 0; i < TelaListaClientes.modeloTabela.getRowCount(); i++) {
            String cpf = String.valueOf(TelaListaClientes.modeloTabela.getValueAt(i, 1));
            if (cpf.equals(cpfBusca)) {
                return String.valueOf(TelaListaClientes.modeloTabela.getValueAt(i, 0));
            }
        }
        return null;
    }

    private void contratarServico() {

        String cpf = textCpfCliente.getText().trim();
        String tipoServicoCombo = (String) comboServico.getSelectedItem();
        String petSelecionado = (String) comboPets.getSelectedItem();

        if (cpf.isEmpty() || tipoServicoCombo == null || petSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Preencha CPF, selecione um pet e o serviço!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String nomeCliente = buscarNomeCliente(cpf);
            if (nomeCliente == null) {
                JOptionPane.showMessageDialog(this, "Cliente não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int idPet = Integer.parseInt(petSelecionado.split(" - ")[0]);
            String nomePet = petSelecionado.split(" - ")[1];

            Object valorCampoData = spinnerData.getValue();

            if (!(valorCampoData instanceof Date)) {
            	JOptionPane.showMessageDialog(
            	        this,
            	        "Data Inválida!",
            	        "Aviso",
            	        JOptionPane.WARNING_MESSAGE
            	);

            }

            Date dataSelecionada = (Date) valorCampoData;
            LocalDate data = dataSelecionada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            LocalDate hoje = LocalDate.now();

            if (data.isBefore(hoje)) {
                JOptionPane.showMessageDialog(
                        this,
                        "A data do serviço não pode ser passada!\nSelecione hoje ou uma data futura.",
                        "Data inválida",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            String tipoServico = extrairChaveServico(tipoServicoCombo);

            double valorBase = tabelaValores.getOrDefault(tipoServico, 0.0);
            double valorFinal = valorBase;
            int dias = 1;

            if (tipoServico.equals("Hospedagem")) {
                dias = (Integer) spinnerDiasHospedagem.getValue();
                valorFinal = valorBase * dias;
            }

            int idServico = contadorId++;

            TelaListaServicos.modeloTabela.addRow(new Object[]{
                    idServico,
                    tipoServico,
                    cpf,
                    nomeCliente,
                    nomePet,
                    data.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    tipoServico.equals("Hospedagem") ? dias + " dias" : "Avulso",
                    valorFinal
            });

            areaResultado.setText(
                    "Serviço contratado com sucesso!\n\n" +
                            "ID Serviço: " + idServico +
                            "\nCliente: " + nomeCliente +
                            "\nCPF: " + cpf +
                            "\nPet: " + nomePet +
                            "\nServiço: " + tipoServico +
                            (tipoServico.equals("Hospedagem") ? "\nDias: " + dias : "") +
                            "\nValor Total: R$ " + valorFinal +
                            "\nData: " + data.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            );

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private String extrairChaveServico(String comboText) {
        for (String chave : tabelaValores.keySet()) {
            if (comboText.toLowerCase().contains(chave.toLowerCase())) {
                return chave;
            }
        }
        return comboText;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaContratarServico().setVisible(true));
    }
}
