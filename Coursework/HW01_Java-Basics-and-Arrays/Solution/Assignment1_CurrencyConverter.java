/**
 * @file Assignment1_CurrencyConverter.java
 * @brief Assignment 1, Q5: Converts Korean Won (KRW) to US Dollar (USD)
 *        using a fixed exchange rate with GUI input/output.
 * @author Cheolwon Park
 * @date 2025-03-29
 */

import javax.swing.*;
import java.text.DecimalFormat;

public class Assignment1_CurrencyConverter {

    /** Fixed exchange rate: 1 USD = 1348.81 KRW */
    private static final double EXCHANGE_RATE = 1348.81;

    /**
     * Prompts the user for an amount in KRW, converts it to USD,
     * and displays the result via a GUI message dialog.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog(null,
                "Enter the amount in Korean Won:",
                "KRW to USD Converter",
                JOptionPane.QUESTION_MESSAGE);

        if (input == null || input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "No input provided.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double won;
        try {
            won = Double.parseDouble(input.trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Invalid number format.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (won < 0) {
            JOptionPane.showMessageDialog(null,
                    "Please enter a non-negative value.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double dollar = won / EXCHANGE_RATE;

        DecimalFormat df = new DecimalFormat("#,##0.00");
        String output = String.format("KRW %s = USD %s", df.format(won), df.format(dollar));

        JOptionPane.showMessageDialog(null, output,
                "Conversion Result", JOptionPane.INFORMATION_MESSAGE);
    }
}
