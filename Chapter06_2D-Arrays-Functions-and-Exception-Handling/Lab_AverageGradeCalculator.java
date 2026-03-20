/**
 * @file    Lab_AverageGradeCalculator.java
 * @brief Computes the average GPA across all semesters using a 2D array.
 * @author Cheolwon Park
 * @date 2026-03-21
 */
public class AverageGradeCalculator {

    /**
     * Calculates and displays the overall average grade from a
     * 2D array of semester GPAs.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        double[][] scores = {
            {3.3, 3.4},
            {3.5, 3.6},
            {3.7, 4.0},
            {4.1, 4.2}
        };

        double sum = 0;
        int count = 0;

        for (double[] yearScores : scores) {
            for (double score : yearScores) {
                sum += score;
                count++;
            }
        }

        System.out.printf("Average GPA: %.2f%n", sum / count);
    }
}
