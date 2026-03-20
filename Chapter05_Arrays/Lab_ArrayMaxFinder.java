/**
 * @file    Lab_ArrayMaxFinder.java
 * @brief Reads five integers from the user and finds the maximum value
 *        using an array and a linear scan.
 * @author Cheolwon Park
 * @date 2026-03-21
 */

import java.util.Scanner;

public class ArrayMaxFinder {

    /**
     * Reads five integers from standard input and prints the maximum.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] values = new int[5];

        System.out.println("Please enter five integer values:");
        for (int i = 0; i < values.length; i++) {
            System.out.print("  Value " + (i + 1) + ": ");
            values[i] = scanner.nextInt();
        }

        int max = values[0];
        for (int i = 1; i < values.length; i++) {
            if (values[i] > max) {
                max = values[i];
            }
        }

        System.out.println("Maximum value is: " + max);
        scanner.close();
    }
}
