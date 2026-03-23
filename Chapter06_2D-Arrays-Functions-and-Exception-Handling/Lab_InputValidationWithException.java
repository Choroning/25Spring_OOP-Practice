/**
 * @file    Lab_InputValidationWithException.java
 * @brief Demonstrates exception-based input validation with a Scanner.
 *        Retries on invalid input using InputMismatchException handling.
 * @author Cheolwon Park
 * @date 2025-04-02
 */

import java.util.Scanner;
import java.util.InputMismatchException;

public class InputValidationWithException {

    /**
     * Reads three valid integers from the user, retrying on invalid input,
     * then prints their sum.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter three integers:");

        int sum = 0;
        for (int i = 0; i < 3; i++) {
            System.out.print("  Value " + (i + 1) + ": ");
            try {
                sum += scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("  Invalid input. Please enter an integer.");
                scanner.next(); // consume the invalid token
                i--;            // retry the same index
            }
        }

        System.out.println("Sum of input values: " + sum);
        scanner.close();
    }
}
