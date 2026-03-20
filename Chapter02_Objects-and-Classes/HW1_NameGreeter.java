/**
 * @file HW1_NameGreeter.java
 * @brief Homework 1 Q2: Reads name and student ID, prints greeting and digit sum.
 * @author Cheolwon Park
 * @date 2025-03-28
 */

import javax.swing.JOptionPane;

/**
 * Prompts user for name and student ID via dialog, computes the sum
 * of all digits in the student ID, and prints a greeting message.
 */
public class HW1_NameGreeter {

    /**
     * Main entry point.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Input dialog
        String input = JOptionPane.showInputDialog(
                null,
                "Enter your name & student ID (e.g. Minseok Seo / 12345678):");

        if (input == null || input.trim().isEmpty()) {
            System.out.println("No input provided.");
            return;
        }

        // Validate format: must contain '/' separator
        String[] parts = input.split("/");
        if (parts.length < 2) {
            System.out.println("Invalid format. Example: Minseok Seo / 12345678");
            return;
        }

        String name = parts[0].trim();
        String studentId = parts[1].trim();

        // Compute digit sum
        int sum = 0;
        for (int i = 0; i < studentId.length(); i++) {
            char ch = studentId.charAt(i);
            if (Character.isDigit(ch)) {
                sum += ch - '0';
            }
        }

        // Output
        System.out.println("Hello OOP World! " + name);
        System.out.println("The sum of each number of your student number entered is " + sum);
    }
}
