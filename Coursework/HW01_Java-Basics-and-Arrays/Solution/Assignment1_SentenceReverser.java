/**
 * @file Assignment1_SentenceReverser.java
 * @brief Assignment 1 Q1: Reverses each word in an English sentence while capitalizing the first letter.
 * @author Cheolwon Park
 * @date 2025-03-28
 */

import javax.swing.JOptionPane;

/**
 * Reads an English sentence from a dialog, reverses each word individually,
 * capitalizes the first letter of each reversed word, and prints the result.
 */
public class Assignment1_SentenceReverser {

    /**
     * Main entry point.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Input dialog
        String sentence = JOptionPane.showInputDialog(
                null, "Please write any English sentence:");

        if (sentence == null) {
            System.out.println("Input was cancelled.");
            return;
        }

        // Convert to lowercase for uniform processing
        sentence = sentence.toLowerCase();
        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (word.isEmpty()) {
                continue;
            }

            // Reverse the word
            String reversed = new StringBuilder(word).reverse().toString();

            // Capitalize first letter if it is alphabetic
            char firstChar = reversed.charAt(0);
            String rest = reversed.substring(1);

            if (Character.isLetter(firstChar)) {
                reversed = Character.toUpperCase(firstChar) + rest;
            }
            result.append(reversed).append(" ");
        }

        // Output the result
        System.out.println(result.toString().trim());
    }
}
