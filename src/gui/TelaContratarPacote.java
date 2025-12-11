package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class TelaContratarPacote extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textCpfCliente;
    private JComboBox<String> comboPets;
    private JCheckBox chkBanho, chkTosa, chkConsulta, chkVacina, chkHospedagem;
    private JSpinner spinnerDiasHospedagem;
    private JTextArea areaResultado;
    private JSpinner spinnerData;
    private static int contadorId = 1;

    public TelaContratarPacote() {
        setTitle("Contratar Pacote de Serviços");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 700);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBorder(new EmptyBorder(15, 20, 15, 20));
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("CONTRATAR PACOTE DE SERVIÇOS");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(lblTitulo);
        contentPane.add(Box.createVerticalStrut(20));

        textCpfCliente = criarCampoCentralizado("CPF do Cliente:");

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

        chkBanho = new JCheckBox("Banho - R$80");
        chkTosa = new JCheckBox("Tosa - R$100");
        chkHospedagem = new JCheckBox("Hospedagem - R$150/dia");
        chkConsulta = new JCheckBox("Consulta - R$200");
        chkVacina = new JCheckBox("Adestramento - R$350");

        JPanel painelServicos = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        painelServicos.add(chkBanho);
        painelServicos.add(chkTosa);
        painelServicos.add(chkHospedagem);
        painelServicos.add(chkConsulta);
        painelServicos.add(chkVacina);
        painelServicos.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(painelServicos);
        contentPane.add(Box.createVerticalStrut(10));

        JLabel lblDias = new JLabel("Dias de Hospedagem:");
        lblDias.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblDias.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(lblDias);

        spinnerDiasHospedagem = new JSpinner(new SpinnerNumberModel(1, 1, 30, 1));
        spinnerDiasHospedagem.setMaximumSize(new Dimension(100, 30));
        spinnerDiasHospedagem.setAlignmentX(Component.CENTER_ALIGNMENT);
        spinnerDiasHospedagem.setEnabled(false);
        chkHospedagem.addActionListener(e -> spinnerDiasHospedagem.setEnabled(chkHospedagem.isSelected()));
        contentPane.add(spinnerDiasHospedagem);
        contentPane.add(Box.createVerticalStrut(10));

        JLabel lblData = new JLabel("Data de Agendamento:");
        lblData.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblData.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(lblData);

        spinnerData = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinnerData, "dd/MM/yyyy");
        spinnerData.setEditor(editor);
        spinnerData.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        spinnerData.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(spinnerData);
        contentPane.add(Box.createVerticalStrut(10));

        JButton btnSalvar = new JButton("CONTRATAR PACOTE");
        btnSalvar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnSalvar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSalvar.addActionListener(e -> contratarPacote());
        contentPane.add(Box.createVerticalStrut(15));
        contentPane.add(btnSalvar);

        JButton btnVoltar = new JButton("VOLTAR");
        btnVoltar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVoltar.addActionListener(e -> {
            new TelaServicos().setVisible(true);
            dispose();
        });
        contentPane.add(Box.createVerticalStrut(10));
        contentPane.add(btnVoltar);

        areaResultado = new JTextArea("Resultado aparecerá aqui...");
        areaResultado.setEditable(false);
        areaResultado.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        areaResultado.setLineWrap(true);
        areaResultado.setWrapStyleWord(true);

        JScrollPane scrollResumo = new JScrollPane(areaResultado);
        scrollResumo.setBorder(BorderFactory.createTitledBorder("Resumo"));
        scrollResumo.setPreferredSize(new Dimension(750, 200));
        scrollResumo.setAlignmentX(Component.CENTER_ALIGNMENT);

        contentPane.add(Box.createVerticalStrut(20));
        contentPane.add(scrollResumo);
    }

    private JTextField criarCampoCentralizado(String label) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(lbl);

        JTextField campo = new JTextField();
        campo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        campo.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(campo);
        contentPane.add(Box.createVerticalStrut(10));
        return campo;
    }

    private void estilizarCombo(String label, JComboBox<String> combo) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(lbl);

        combo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        combo.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(combo);
        contentPane.add(Box.createVerticalStrut(10));
    }

    private void listarPets() {
        if (TelaListaPets.modeloTabela == null || TelaListaPets.modeloTabela.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this,
                    "A lista de pets ainda não foi aberta ou nenhum pet foi cadastrado!",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String cpfBusca = textCpfCliente.getText().trim();
        if (cpfBusca.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite o CPF do cliente!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        comboPets.removeAllItems();
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

        comboPets.setEnabled(encontrado);
        if (!encontrado)
            JOptionPane.showMessageDialog(this, "Nenhum pet encontrado para este CPF!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
    }

    private String buscarNomeCliente(String cpfBusca) {
        if (TelaListaClientes.modeloTabela == null) return null;

        for (int i = 0; i < TelaListaClientes.modeloTabela.getRowCount(); i++) {
            Object cpfObj = TelaListaClientes.modeloTabela.getValueAt(i, 1);
            if (cpfObj != null && cpfObj.toString().equals(cpfBusca)) {
                Object nomeObj = TelaListaClientes.modeloTabela.getValueAt(i, 0);
                return nomeObj == null ? "" : nomeObj.toString();
            }
        }
        return null;
    }

    private void contratarPacote() {
        String cpf = textCpfCliente.getText().trim();
        String petSelecionado = (String) comboPets.getSelectedItem();

        if (cpf.isEmpty() || petSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Preencha CPF e selecione um Pet!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {

            Date dataSelecionada = (Date) spinnerData.getValue();
            LocalDate dataAgendamento = dataSelecionada.toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDate();

            LocalDate hoje = LocalDate.now();

            if (dataAgendamento.isBefore(hoje)) {
            	JOptionPane.showMessageDialog(
            	        this,
            	        "Data Inválida!",
            	        "Aviso",
            	        JOptionPane.WARNING_MESSAGE
            	);

            }

            String nomeCliente = buscarNomeCliente(cpf);
            if (nomeCliente == null) {
                JOptionPane.showMessageDialog(this, "Cliente não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int idPet = Integer.parseInt(petSelecionado.split(" - ")[0]);
            String nomePet = petSelecionado.split(" - ")[1];

            double totalSemDesconto = 0;
            StringBuilder servicos = new StringBuilder();

            if (chkBanho.isSelected()) {
                totalSemDesconto += 80;
                servicos.append("Banho, ");
            }
            if (chkTosa.isSelected()) {
                totalSemDesconto += 100;
                servicos.append("Tosa, ");
            }
            if (chkConsulta.isSelected()) {
                totalSemDesconto += 200;
                servicos.append("Consulta, ");
            }
            if (chkVacina.isSelected()) {
                totalSemDesconto += 350;
                servicos.append("Adestramento, ");
            }
            if (chkHospedagem.isSelected()) {
                int dias = (int) spinnerDiasHospedagem.getValue();
                double valor = dias * 150;
                totalSemDesconto += valor;
                servicos.append("Hospedagem (" + dias + " dias), ");
            }

            if (servicos.length() == 0) {
                JOptionPane.showMessageDialog(this, "Selecione ao menos 1 serviço!", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String servicosFinal = servicos.substring(0, servicos.length() - 2);

            double desconto = totalSemDesconto * 0.10;
            double totalComDesconto = totalSemDesconto - desconto;

            adicionarServicoNaTabela(
                    "Pacote",
                    cpf,
                    nomeCliente,
                    idPet,
                    nomePet,
                    dataAgendamento,
                    servicosFinal,
                    totalComDesconto
            );

            String resumo = "CPF: " + cpf + "\n" +
                    "Cliente: " + nomeCliente + "\n" +
                    "Pet ID: " + idPet + " - " + nomePet + "\n" +
                    "Data: " + dataAgendamento + "\n\n" +
                    "Serviços: " + servicosFinal + "\n\n" +
                    "Total sem desconto: R$" + String.format("%.2f", totalSemDesconto) +
                    "\nDesconto (10%): -R$" + String.format("%.2f", desconto) +
                    "\nTOTAL FINAL: R$" + String.format("%.2f", totalComDesconto);

            areaResultado.setText(resumo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao contratar pacote: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void adicionarServicoNaTabela(String tipo, String cpfCliente, String nomeCliente,
                                         int idPet, String nomePet, LocalDate dataAgendamento,
                                         String descricao, double valor) {

        String dataStr = dataAgendamento.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        int idServico = contadorId++;

        Object[] row = new Object[]{
                idServico,             
                tipo,                   
                cpfCliente,             
                nomeCliente,            
                idPet + " - " + nomePet,
                dataStr,                
                descricao,              
                valor                   
        };

        TelaListaServicos.modeloTabela.addRow(row);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaContratarPacote().setVisible(true));
    }
}
