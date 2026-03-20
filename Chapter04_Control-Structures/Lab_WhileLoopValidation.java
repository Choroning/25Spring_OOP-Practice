/**
 * @file    Lab_WhileLoopValidation.java
 * @brief Demonstrates a while loop for input validation using JOptionPane.
 *        Repeatedly prompts the user until a valid age (0--130) is entered.
 * @author Cheolwon Park
 * @date 2026-03-21
 */

import javax.swing.JOptionPane;

public class WhileLoopValidation {

    /**
     * Prompts the user via a GUI dialog to enter a valid age.
     * Continues prompting until the age falls within the range [0, 130].
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        String inputStr = JOptionPane.showInputDialog(null,
                "Please enter your age (0-130):");

        if (inputStr == null) {
            System.out.println("Input cancelled by user.");
            return;
        }

        int age;
        try {
            age = Integer.parseInt(inputStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number format.");
            return;
        }

        while (age < 0 || age > 130) {
            JOptionPane.showMessageDialog(null,
                    "Invalid age entered. Please try again.");
            inputStr = JOptionPane.showInputDialog(null,
                    "Please enter your age (0-130):");

            if (inputStr == null) {
                System.out.println("Input cancelled by user.");
                return;
            }

            try {
                age = Integer.parseInt(inputStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid number format.");
                return;
            }
        }

        System.out.println("Valid age entered: " + age);
    }
}
