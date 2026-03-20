/**
 * @file Assignment1_ScheduleReminder.java
 * @brief Assignment 1, Q3: Schedule reminder program that calculates
 *        the number of days between today and a user-specified date.
 * @author Cheolwon Park
 * @date 2026-03-21
 */

import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Assignment1_ScheduleReminder {

    /**
     * Prompts the user for a date (yyyy-MM-dd) via a GUI dialog,
     * calculates the day difference from today, and displays the result.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            String inputDateStr = JOptionPane.showInputDialog(
                    "Enter a date to check the D-day (yyyy-MM-dd):");

            if (inputDateStr == null || inputDateStr.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "No date entered.");
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);

            Date inputDate = sdf.parse(inputDateStr.trim());
            Date today = sdf.parse(sdf.format(new Date()));

            long diffInMillis = inputDate.getTime() - today.getTime();
            long diffInDays = diffInMillis / (1000 * 60 * 60 * 24);

            String message;
            if (diffInDays == 0) {
                message = "That date is today!";
            } else if (diffInDays > 0) {
                message = diffInDays + " day(s) remaining.";
            } else {
                message = Math.abs(diffInDays) + " day(s) have passed.";
            }

            JOptionPane.showMessageDialog(null, message);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Please enter a valid date (e.g., 2025-04-01).");
        }
    }
}
