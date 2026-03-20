/**
 * @file    Lab_ExecutionTimeMeasurement.java
 * @brief Measures the execution time of nested loops using System.nanoTime().
 *        Demonstrates performance benchmarking techniques in Java.
 * @author Cheolwon Park
 * @date 2026-03-21
 */
public class ExecutionTimeMeasurement {

    /**
     * Runs a triple-nested loop and measures the elapsed time in milliseconds.
     * Uses System.nanoTime() for high-precision timing.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        // Triple nested loop to simulate a computationally intensive task
        for (int i = 0; i <= 1000; i++) {
            for (int j = 0; j <= 1000; j++) {
                for (int k = 0; k <= 1000; k++) {
                    // Empty body: loop overhead itself is measured
                }
            }
        }

        long endTime = System.nanoTime();
        double elapsedMs = (endTime - startTime) / 1_000_000.0;

        System.out.printf("Elapsed time: %.2f ms%n", elapsedMs);
    }
}
