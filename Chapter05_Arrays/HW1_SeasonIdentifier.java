/**
 * @file HW1_SeasonIdentifier.java
 * @brief Homework 1, Q9: Determines the season for a user-specified date
 *        using switch-case on the month value.
 * @author Cheolwon Park
 * @date 2026-03-21
 */

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HW1_SeasonIdentifier {

    /**
     * Prompts the user for a date and displays the corresponding season.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog(null,
                "Enter a date (YYYY-MM-DD):",
                "Season Checker",
                JOptionPane.QUESTION_MESSAGE);

        if (input == null || input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "No input provided.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            Date date = sdf.parse(input.trim());

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int month = cal.get(Calendar.MONTH) + 1;

            String season = switch (month) {
                case 3, 4, 5   -> "Spring";
                case 6, 7, 8   -> "Summer";
                case 9, 10, 11 -> "Autumn";
                case 12, 1, 2  -> "Winter";
                default        -> "Unknown";
            };

            JOptionPane.showMessageDialog(null,
                    "The date falls in: " + season,
                    "Result", JOptionPane.INFORMATION_MESSAGE);

        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null,
                    "Invalid date format. Please use YYYY-MM-DD.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
