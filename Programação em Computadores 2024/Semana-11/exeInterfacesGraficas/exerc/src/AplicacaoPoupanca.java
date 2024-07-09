import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AplicacaoPoupanca {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Poupex");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelJuros = new JLabel("Juros ao mês %:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(labelJuros, gbc);
        
        JTextField textJuros = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(textJuros, gbc);
        
        JLabel labelAnos = new JLabel("Num. de anos:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        frame.add(labelAnos, gbc);
        
        JTextField textAnos = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        frame.add(textAnos, gbc);
        
        JLabel labelDeposito = new JLabel("Depósito mensal R$:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        frame.add(labelDeposito, gbc);
        
        JTextField textDeposito = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        frame.add(textDeposito, gbc);
        
        JLabel labelTotal = new JLabel("Total poupado R$:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        frame.add(labelTotal, gbc);
        
        JTextField textTotal = new JTextField();
        textTotal.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        frame.add(textTotal, gbc);
        
        JButton buttonOk = new JButton("OK");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        frame.add(buttonOk, gbc);
        
        buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularPoupanca(textJuros, textAnos, textDeposito, textTotal);
            }
        });

        frame.setVisible(true);
    }

    private static void calcularPoupanca(JTextField textJuros, JTextField textAnos, JTextField textDeposito, JTextField textTotal) {
        if (textJuros.getText().isEmpty() || textAnos.getText().isEmpty() || textDeposito.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
            return;
        }

        double jurosMensal = Double.parseDouble(textJuros.getText());
        int numAnos = Integer.parseInt(textAnos.getText());
        double depositoMensal = Double.parseDouble(textDeposito.getText());

        double totalPoupado = 0;
        int numMeses = numAnos * 12;

        for (int i = 0; i < numMeses; i++) {
            totalPoupado += depositoMensal;
            totalPoupado *= (1 + jurosMensal / 100);
        }

        textTotal.setText(String.format("%.2f", totalPoupado));
    }
}
