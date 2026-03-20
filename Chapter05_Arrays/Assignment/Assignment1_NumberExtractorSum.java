/**
 * @file Assignment1_NumberExtractorSum.java
 * @brief Assignment 1, Q8: Extracts all real numbers from a mixed-content
 *        string using regex and computes their sum.
 * @author Cheolwon Park
 * @date 2026-03-21
 */

import javax.swing.*;
import java.util.regex.*;

public class Assignment1_NumberExtractorSum {

    /**
     * Prompts the user for a string containing mixed text, extracts
     * all numeric values via regex, and prints their sum.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog(null,
                "Enter a sentence with mixed text and numbers:",
                "Number Extractor",
                JOptionPane.QUESTION_MESSAGE);

        if (input == null || input.trim().isEmpty()) {
            System.out.println("No input provided.");
            return;
        }

        // Match integers and decimal numbers
        Pattern pattern = Pattern.compile("\\d+(?:\\.\\d+)?");
        Matcher matcher = pattern.matcher(input);

        double sum = 0;
        StringBuilder expression = new StringBuilder();

        while (matcher.find()) {
            String numberStr = matcher.group();
            double number = Double.parseDouble(numberStr);
            sum += number;

            if (expression.length() > 0) {
                expression.append(" + ");
            }
            expression.append(numberStr);
        }

        System.out.print("Sum of all numbers: ");
        if (expression.length() > 0) {
            System.out.println(expression + " = " + sum);
        } else {
            System.out.println("0 (no numbers found)");
        }
    }
}
