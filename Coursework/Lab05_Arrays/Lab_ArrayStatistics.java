/**
 * @file    Lab_ArrayStatistics.java
 * @brief Computes maximum, minimum, count, and mean of user-supplied integers.
 *        Demonstrates extracting array operations into reusable static methods.
 * @author Cheolwon Park
 * @date 2025-03-27
 */

import java.util.Scanner;

public class ArrayStatistics {

    /**
     * Reads five integers, then prints statistics using helper methods.
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

        System.out.println("Maximum value: " + getMaximum(values));
        System.out.println("Minimum value: " + getMinimum(values));
        System.out.println("Element count: " + values.length);
        System.out.printf("Mean value:    %.2f%n", getMean(values));

        scanner.close();
    }

    /**
     * Finds the maximum value in the array.
     *
     * @param array the input array (must have at least one element)
     * @return the maximum value
     */
    public static int getMaximum(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    /**
     * Finds the minimum value in the array.
     *
     * @param array the input array (must have at least one element)
     * @return the minimum value
     */
    public static int getMinimum(int[] array) {
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    /**
     * Computes the arithmetic mean of the array elements.
     *
     * @param array the input array (must have at least one element)
     * @return the mean as a double
     */
    public static double getMean(int[] array) {
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return (double) sum / array.length;
    }
}
