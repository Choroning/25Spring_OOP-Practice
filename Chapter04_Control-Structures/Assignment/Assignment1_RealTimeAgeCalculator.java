/**
 * @file Assignment1_RealTimeAgeCalculator.java
 * @brief Assignment 1, Q4: Calculates the user's real-time age with precision
 *        down to seconds given a birth date.
 * @author Cheolwon Park
 * @date 2026-03-21
 */

import javax.swing.*;
import java.text.*;
import java.util.*;

public class Assignment1_RealTimeAgeCalculator {

    /**
     * Prompts the user for their name and birth date, then computes
     * and displays the real-time age in years, months, days, hours, minutes, and seconds.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog(null,
                "Enter your name and date of birth (e.g., John Doe / 1995-06-15)",
                "Age Calculator",
                JOptionPane.QUESTION_MESSAGE);

        if (input == null || !input.contains("/")) {
            System.out.println("Invalid input format.");
            return;
        }

        String[] parts = input.split("/");
        String name = parts[0].trim();
        String birthDateStr = parts[1].trim();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        Date birthDate;

        try {
            birthDate = inputFormat.parse(birthDateStr);
            if (birthDate.after(now)) {
                System.out.println("Error: Birth date cannot be in the future.");
                return;
            }
        } catch (ParseException e) {
            System.out.println("Error: Invalid date format. Use yyyy-MM-dd.");
            return;
        }

        Calendar birthCal = Calendar.getInstance();
        birthCal.setTime(birthDate);
        Calendar nowCal = Calendar.getInstance();
        nowCal.setTime(now);

        int years = nowCal.get(Calendar.YEAR) - birthCal.get(Calendar.YEAR);
        int months = nowCal.get(Calendar.MONTH) - birthCal.get(Calendar.MONTH);
        int days = nowCal.get(Calendar.DAY_OF_MONTH) - birthCal.get(Calendar.DAY_OF_MONTH);
        int hours = nowCal.get(Calendar.HOUR_OF_DAY) - birthCal.get(Calendar.HOUR_OF_DAY);
        int minutes = nowCal.get(Calendar.MINUTE) - birthCal.get(Calendar.MINUTE);
        int seconds = nowCal.get(Calendar.SECOND) - birthCal.get(Calendar.SECOND);

        // Normalize negative values by borrowing from the next larger unit
        if (seconds < 0) { seconds += 60; minutes--; }
        if (minutes < 0) { minutes += 60; hours--; }
        if (hours < 0)   { hours += 24;   days--; }
        if (days < 0) {
            nowCal.add(Calendar.MONTH, -1);
            days += nowCal.getActualMaximum(Calendar.DAY_OF_MONTH);
            months--;
        }
        if (months < 0) { months += 12; years--; }

        System.out.println("Current time:       " + dateFormat.format(now));
        System.out.println("Date of birth:      " + birthDateStr + " 00:00:00");
        System.out.println("============================================");
        System.out.printf("Hello %s, your real-time age is:%n", name);
        System.out.printf("%2d Yrs %2d Mons %2d Days %2d hrs %2d min %2d sec%n",
                years, months, days, hours, minutes, seconds);
    }
}
