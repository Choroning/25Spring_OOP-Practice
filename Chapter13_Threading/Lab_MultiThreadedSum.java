/**
 * @file    Lab_MultiThreadedSum.java
 * @brief Compares single-threaded vs. multi-threaded summation of 2 billion
 *        integers, demonstrating the performance benefit of parallelism.
 * @author Cheolwon Park
 * @date 2026-03-21
 */
public class MultiThreadedSum {

    private static final int NUM_THREADS = 8;
    private static final int TOTAL_COUNT = 2_000_000_000;

    /** Shared sum accumulated by all threads. */
    private static volatile long totalSum = 0;

    /** Thread that computes a partial sum over a given range. */
    static class SumThread extends Thread {
        private final int start;
        private final int end;
        private long partialSum = 0;

        public SumThread(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            long temp = 0;
            for (int i = start; i <= end; i++) {
                temp += i;
            }
            partialSum = temp;
        }

        public long getPartialSum() {
            return partialSum;
        }
    }

    /**
     * Runs both single-threaded and multi-threaded summation,
     * then prints execution times and speedup factor.
     *
     * @param args command-line arguments (not used)
     * @throws InterruptedException if a thread is interrupted
     */
    public static void main(String[] args) throws InterruptedException {
        // Single-threaded
        long singleStart = System.currentTimeMillis();
        long singleSum = 0;
        for (int i = 0; i < TOTAL_COUNT; i++) {
            singleSum += i;
        }
        double singleMs = System.currentTimeMillis() - singleStart;

        // Multi-threaded
        long multiStart = System.currentTimeMillis();
        SumThread[] threads = new SumThread[NUM_THREADS];
        int chunkSize = TOTAL_COUNT / NUM_THREADS;

        for (int i = 0; i < NUM_THREADS; i++) {
            int start = i * chunkSize;
            int end = (i + 1) * chunkSize - 1;
            threads[i] = new SumThread(start, end);
            threads[i].start();
        }

        long multiSum = 0;
        for (SumThread t : threads) {
            t.join();
            multiSum += t.getPartialSum();
        }
        double multiMs = System.currentTimeMillis() - multiStart;

        System.out.println("Single-threaded: sum=" + singleSum + ", time=" + singleMs + " ms");
        System.out.println("Multi-threaded:  sum=" + multiSum + ", time=" + multiMs + " ms");
        System.out.printf("Speedup:         %.2fx%n", singleMs / multiMs);
    }
}
