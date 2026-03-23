/**
 * @file Assignment1_MatrixSumCalculator.java
 * @brief Assignment 1, Q6: Reads a 3x3 integer matrix from user input,
 *        computes row sums, column sums, and the total sum,
 *        then displays the results in an HTML table via JOptionPane.
 * @author Cheolwon Park
 * @date 2025-03-29
 */

import javax.swing.*;

public class Assignment1_MatrixSumCalculator {

    /**
     * Prompts the user for a 3x3 matrix, computes sums, and displays results.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        int[][] matrix = new int[3][3];
        int[] rowSum = new int[3];
        int[] colSum = new int[3];
        int totalSum = 0;

        // Input via GUI dialog
        String input = JOptionPane.showInputDialog(null,
                "Enter matrix values (e.g., 2 4 5 / 10 13 1 / 2 6 7):",
                "Matrix Input",
                JOptionPane.QUESTION_MESSAGE);

        if (input == null || input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "No input provided.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] rows = input.trim().split("/");
        if (rows.length != 3) {
            JOptionPane.showMessageDialog(null,
                    "Please enter exactly 3 rows separated by '/'.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            for (int i = 0; i < 3; i++) {
                String[] nums = rows[i].trim().split("\\s+");
                if (nums.length != 3) {
                    JOptionPane.showMessageDialog(null,
                            "Each row must contain exactly 3 numbers.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                for (int j = 0; j < 3; j++) {
                    matrix[i][j] = Integer.parseInt(nums[j]);
                    rowSum[i] += matrix[i][j];
                    colSum[j] += matrix[i][j];
                    totalSum += matrix[i][j];
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Invalid number format.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Build HTML table for display
        StringBuilder html = new StringBuilder();
        html.append("<html><table border='1' cellpadding='6'>");
        html.append("<tr><td></td><td colspan='3' align='center'>Values</td>");
        html.append("<td><b>Row Sum</b></td></tr>");

        for (int i = 0; i < 3; i++) {
            html.append("<tr><td><b>Row ").append(i + 1).append("</b></td>");
            for (int j = 0; j < 3; j++) {
                html.append("<td align='center'>").append(matrix[i][j]).append("</td>");
            }
            html.append("<td align='center' style='background:#e0f7fa;'><b>")
                .append(rowSum[i]).append("</b></td></tr>");
        }

        html.append("<tr><td><b>Col Sum</b></td>");
        for (int j = 0; j < 3; j++) {
            html.append("<td align='center' style='background:#e0f7fa;'><b>")
                .append(colSum[j]).append("</b></td>");
        }
        html.append("<td align='center' style='background:#b2ebf2;'><b>")
            .append(totalSum).append("</b></td></tr>");
        html.append("</table></html>");

        JLabel label = new JLabel(html.toString());
        JOptionPane.showMessageDialog(null, label,
                "Matrix Result", JOptionPane.INFORMATION_MESSAGE);
    }
}
