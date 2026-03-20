/**
 * @file HW2_GreetingDialog.java
 * @brief Homework 2, Q4: Greeting dialog program that validates
 *        the user's first name (letters only) and prints a farewell message.
 * @author Cheolwon Park
 * @date 2026-03-21
 */

import javax.swing.JOptionPane;

public class HW2_GreetingDialog {

    /**
     * Prompts the user for a valid alphabetic first name via GUI dialog,
     * then prints a goodbye message to the console.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        String name;

        while (true) {
            name = JOptionPane.showInputDialog(null,
                    "What is your first name?",
                    "Greeting",
                    JOptionPane.QUESTION_MESSAGE);

            if (name == null) {
                System.out.println("No name entered. Program terminates.");
                return;
            }

            name = name.trim();

            if (!name.matches("^[A-Za-z]+$")) {
                JOptionPane.showMessageDialog(null,
                        "Invalid name. Please enter letters only.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                continue;
            }
            break;
        }

        System.out.println("Goodbye " + name + " from the OOP World!");
    }
}
