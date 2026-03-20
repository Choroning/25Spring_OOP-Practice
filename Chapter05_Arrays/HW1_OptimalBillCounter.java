/**
 * @file HW1_OptimalBillCounter.java
 * @brief Homework 1, Q7: Determines the optimal combination of Korean
 *        banknotes and coins for a given amount using a greedy algorithm.
 * @author Cheolwon Park
 * @date 2026-03-21
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class HW1_OptimalBillCounter {

    /** Available denominations in descending order (KRW) */
    private static final int[] DENOMINATIONS = {50000, 10000, 5000, 1000, 500, 100, 50, 10};

    /**
     * Reads an amount from the user and displays the optimal bill/coin breakdown.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter an amount in KRW: ");
        int amount;
        try {
            amount = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error: Please enter a valid integer.");
            scanner.close();
            return;
        }

        if (amount < 0) {
            System.out.println("Error: Please enter a non-negative amount.");
            scanner.close();
            return;
        }

        if (amount % 10 != 0) {
            System.out.println("Error: Amount must be a multiple of 10 (minimum coin unit).");
            scanner.close();
            return;
        }

        int originalAmount = amount;
        int[] counts = new int[DENOMINATIONS.length];

        // Greedy decomposition
        for (int i = 0; i < DENOMINATIONS.length; i++) {
            counts[i] = amount / DENOMINATIONS[i];
            amount %= DENOMINATIONS[i];
        }

        // Display results
        System.out.println("\nAmount: " + originalAmount + " KRW");
        System.out.println("Breakdown:");
        for (int i = 0; i < DENOMINATIONS.length; i++) {
            if (counts[i] > 0) {
                System.out.printf("  %,6d KRW x %d%n", DENOMINATIONS[i], counts[i]);
            }
        }

        scanner.close();
    }
}
