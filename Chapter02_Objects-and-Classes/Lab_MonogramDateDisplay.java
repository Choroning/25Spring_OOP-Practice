/**
 * @file    Lab_MonogramDateDisplay.java
 * @brief Reads a full name via dialog, computes initials monogram, and displays today's date.
 * @author Cheolwon Park
 * @date 2025-03-10
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 * Prompts the user for their full name (first, middle, last),
 * extracts the initials to form a monogram, and shows the result
 * along with today's date in a dialog box.
 */
public class MonogramDateDisplay {

    /**
     * Main entry point.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        String name = JOptionPane.showInputDialog(
                null, "Enter your full name (first, middle, last):");

        if (name == null || name.trim().isEmpty()) {
            System.out.println("No input provided. Exiting.");
            return;
        }

        // Extract first, middle, and last names
        String space = " ";
        String first = name.substring(0, name.indexOf(space));
        name = name.substring(name.indexOf(space) + 1);
        String middle = name.substring(0, name.indexOf(space));
        String last = name.substring(name.indexOf(space) + 1);

        // Compute the monogram from initials
        String monogram = first.substring(0, 1)
                + middle.substring(0, 1)
                + last.substring(0, 1);

        // Get current date information
        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");

        // Display result
        JOptionPane.showMessageDialog(null,
                "Your monogram is " + monogram + "\n"
                        + "Today is " + dateFormat.format(today));
    }
}
