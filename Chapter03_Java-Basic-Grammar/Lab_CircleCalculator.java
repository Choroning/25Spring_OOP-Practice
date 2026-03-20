/**
 * @file    Lab_CircleCalculator.java
 * @brief Computes the area and circumference of a circle from user-provided radius.
 * @author Cheolwon Park
 * @date 2025-03-12
 */

import javax.swing.JOptionPane;

/**
 * Reads a radius value from a dialog and calculates
 * the area and circumference of the circle.
 */
public class CircleCalculator {

    /** Mathematical constant PI. */
    static final double PI = 3.141592;

    /**
     * Main entry point.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        String radiusStr = JOptionPane.showInputDialog(null, "Enter radius: ");
        if (radiusStr == null || radiusStr.trim().isEmpty()) {
            System.out.println("No input provided.");
            return;
        }

        double radius = Double.parseDouble(radiusStr);

        double area = PI * radius * radius;
        double circumference = 2.0 * PI * radius;

        JOptionPane.showMessageDialog(null,
                "Given Radius: " + radius + "\n"
                        + "Area: " + area + "\n"
                        + "Circumference: " + circumference);
    }
}
