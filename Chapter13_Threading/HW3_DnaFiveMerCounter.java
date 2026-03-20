/**
 * @file HW3_DnaFiveMerCounter.java
 * @brief Homework 3, Q1: Counts all 5-mer frequencies in a DNA sequence file
 *        using a rolling-hash approach. Skips windows containing 'N'.
 * @author Cheolwon Park
 * @date 2026-03-21
 */

import java.io.*;

public class HW3_DnaFiveMerCounter {

    private static final int WINDOW_SIZE = 5;
    private static final int TOTAL_MERS = 1 << (2 * WINDOW_SIZE); // 4^5 = 1024
    private static final int MASK = (1 << (2 * (WINDOW_SIZE - 1))) - 1;

    /**
     * Reads a DNA sequence from "Q1.txt", counts all 5-mer occurrences,
     * and writes results to "Q1_output.txt".
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        int[] counts = new int[TOTAL_MERS];
        int[] window = new int[WINDOW_SIZE];
        int wPos = 0, wCount = 0, nCount = 0;
        boolean dirty = false;
        int rollingIndex = 0;

        long startTime = System.nanoTime();

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("Q1.txt"))) {
            int raw;
            while ((raw = bis.read()) != -1) {
                int code = charToCode((char) raw);
                if (code == -2) continue; // skip whitespace

                if (wCount < WINDOW_SIZE) {
                    window[wPos] = code;
                    if (code == -1) { nCount++; dirty = true; }
                    else { rollingIndex = (rollingIndex << 2) | code; }
                    wPos = (wPos + 1) % WINDOW_SIZE;
                    wCount++;
                    if (wCount == WINDOW_SIZE && nCount == 0) {
                        counts[rollingIndex]++;
                    }
                } else {
                    int outgoing = window[wPos];
                    if (outgoing == -1) { nCount--; }
                    else if (nCount == 0 && !dirty) { rollingIndex &= MASK; }

                    window[wPos] = code;
                    if (code == -1) { nCount++; dirty = true; }
                    else if (nCount == 0 && !dirty) { rollingIndex = (rollingIndex << 2) | code; }
                    wPos = (wPos + 1) % WINDOW_SIZE;

                    if (nCount == 0) {
                        if (dirty) {
                            rollingIndex = computeFullIndex(window, wPos);
                            dirty = false;
                        }
                        counts[rollingIndex]++;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Q1_output.txt"))) {
            for (int i = 0; i < TOTAL_MERS; i++) {
                writer.write(indexToSequence(i) + ": " + counts[i]);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing output: " + e.getMessage());
            return;
        }

        double elapsed = (System.nanoTime() - startTime) / 1_000_000_000.0;
        System.out.printf("Completed in %.3f seconds.%n", elapsed);
    }

    private static int charToCode(char ch) {
        return switch (Character.toUpperCase(ch)) {
            case 'A' -> 0; case 'C' -> 1; case 'G' -> 2; case 'T' -> 3;
            case 'N' -> -1; default -> -2;
        };
    }

    private static int computeFullIndex(int[] window, int wPos) {
        int idx = 0;
        for (int i = 0; i < WINDOW_SIZE; i++) {
            idx = (idx << 2) | window[(wPos + i) % WINDOW_SIZE];
        }
        return idx;
    }

    private static String indexToSequence(int index) {
        char[] map = {'A', 'C', 'G', 'T'};
        char[] seq = new char[WINDOW_SIZE];
        for (int pos = WINDOW_SIZE - 1; pos >= 0; pos--) {
            seq[pos] = map[index & 0b11];
            index >>= 2;
        }
        return new String(seq);
    }
}
