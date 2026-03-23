/**
 * @file    Lab_SwitchGradingSystem.java
 * @brief Demonstrates the switch statement by converting a numeric score
 *        (0--100) into a letter grade (A--F).
 * @author Cheolwon Park
 * @date 2025-03-12
 */

import java.util.Scanner;

public class SwitchGradingSystem {

    /**
     * Prompts the user for a score and prints the corresponding letter grade.
     * Uses a switch statement on (score / 10) for efficient branching.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your score (0-100): ");
        int score = scanner.nextInt();

        if (score < 0 || score > 100) {
            System.out.println("Error: Score must be between 0 and 100.");
            scanner.close();
            return;
        }

        char grade;
        switch (score / 10) {
            case 10: // 100
            case 9:  // 90-99
                grade = 'A';
                break;
            case 8:  // 80-89
                grade = 'B';
                break;
            case 7:  // 70-79
                grade = 'C';
                break;
            case 6:  // 60-69
                grade = 'D';
                break;
            default: // 0-59
                grade = 'F';
        }

        System.out.println("Your grade is: " + grade);
        scanner.close();
    }
}
