/**
 * @file Assignment2_SortingComparison.java
 * @brief Assignment 2, Q7: Compares the performance of Bubble Sort and
 *        Quick Sort on a randomly generated integer array.
 * @author Cheolwon Park
 * @date 2025-04-12
 */

import java.util.Arrays;
import java.util.Random;

public class Assignment2_SortingComparison {

    /**
     * Generates a random array, sorts it with both algorithms,
     * measures execution time, and verifies correctness.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        final int SIZE = 10000;
        int[] original = new int[SIZE];
        Random rand = new Random();

        for (int i = 0; i < SIZE; i++) {
            original[i] = rand.nextInt(SIZE * 10);
        }

        int[] bubbleArray = Arrays.copyOf(original, original.length);
        int[] quickArray = Arrays.copyOf(original, original.length);

        // Bubble Sort
        long bubbleStart = System.nanoTime();
        bubbleSort(bubbleArray);
        double bubbleMs = (System.nanoTime() - bubbleStart) / 1_000_000.0;

        // Quick Sort
        long quickStart = System.nanoTime();
        quickSort(quickArray, 0, quickArray.length - 1);
        double quickMs = (System.nanoTime() - quickStart) / 1_000_000.0;

        System.out.println("Array size:      " + SIZE);
        System.out.printf("Bubble Sort:     %.3f ms%n", bubbleMs);
        System.out.printf("Quick Sort:      %.3f ms%n", quickMs);
        System.out.printf("Speedup factor:  %.1fx%n", bubbleMs / quickMs);
        System.out.println("Results match:   " + Arrays.equals(bubbleArray, quickArray));
    }

    /**
     * Sorts the array using the bubble sort algorithm with early termination.
     *
     * @param arr the array to sort
     */
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    /**
     * Recursively sorts the array using the quicksort algorithm.
     *
     * @param arr  the array to sort
     * @param low  the starting index
     * @param high the ending index
     */
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = partition(arr, low, high);
            quickSort(arr, low, pivot - 1);
            quickSort(arr, pivot + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
}
