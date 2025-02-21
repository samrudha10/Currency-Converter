package currencyconverter;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.HashMap;

public class Cconverter extends JFrame {
    
    private final HashMap<String, BigDecimal> exchangeRates;
    private JTextField jTextamount;
    private JComboBox<String> txtfrom, txtto;
    private JButton convert;

    public Cconverter() {
        exchangeRates = new HashMap<>();
        loadExchangeRates();
        initComponents();
    }

    private void initComponents() {
        setTitle("Currency Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 200);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        panel.add(new JLabel("Amount:"));
        jTextamount = new JTextField();
        panel.add(jTextamount);

        panel.add(new JLabel("From:"));
        txtfrom = new JComboBox<>(new String[]{"Dollar", "Indian Rupees", "Euro", "Pound"});
        panel.add(txtfrom);

        panel.add(new JLabel("To:"));
        txtto = new JComboBox<>(new String[]{"Indian Rupees", "Dollar", "Pound", "Euro"});
        panel.add(txtto);

        convert = new JButton("Convert");
        convert.addActionListener(e -> convertCurrency());
        panel.add(convert);

        add(panel);
        setVisible(true);
    }

    private void loadExchangeRates() {
        exchangeRates.put("Dollar->Indian Rupees", new BigDecimal("77.34"));
        exchangeRates.put("Indian Rupees->Dollar", new BigDecimal("0.0129"));
        exchangeRates.put("Dollar->Euro", new BigDecimal("0.96"));
        exchangeRates.put("Euro->Dollar", new BigDecimal("1.04"));
        exchangeRates.put("Dollar->Pound", new BigDecimal("0.82"));
        exchangeRates.put("Pound->Dollar", new BigDecimal("1.22"));
        exchangeRates.put("Indian Rupees->Pound", new BigDecimal("0.0106"));
        exchangeRates.put("Pound->Indian Rupees", new BigDecimal("94.43"));
        exchangeRates.put("Indian Rupees->Euro", new BigDecimal("0.0124"));
        exchangeRates.put("Euro->Indian Rupees", new BigDecimal("80.43"));
        exchangeRates.put("Pound->Euro", new BigDecimal("1.17"));
        exchangeRates.put("Euro->Pound", new BigDecimal("0.85"));
        
        // Future improvement: Fetch rates dynamically
        // fetchExchangeRatesFromAPI();
    }

    private void convertCurrency() {
        try {
            BigDecimal amount = new BigDecimal(jTextamount.getText());

            if (amount.compareTo(BigDecimal.ZERO) < 0) {
                JOptionPane.showMessageDialog(this, "Amount cannot be negative.");
                return;
            }

            String from = txtfrom.getSelectedItem().toString();
            String to = txtto.getSelectedItem().toString();

            if (from.equals(to)) {
                JOptionPane.showMessageDialog(this, "The Amount is: " + amount + " " + to);
                return;
            }

            String key = from + "->" + to;
            if (exchangeRates.containsKey(key)) {
                BigDecimal convertedAmount = amount.multiply(exchangeRates.get(key));
                JOptionPane.showMessageDialog(this, "The Amount is: " + convertedAmount + " " + to);
            } else {
                JOptionPane.showMessageDialog(this, "Conversion rate not available.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric amount.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Cconverter::new);
    }
}
