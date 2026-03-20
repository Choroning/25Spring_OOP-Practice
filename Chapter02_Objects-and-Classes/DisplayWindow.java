/**
 * @file DisplayWindow.java
 * @brief Demonstrates creating and displaying a simple JFrame window.
 * @author Cheolwon Park
 * @date 2025-03-10
 */

import javax.swing.JFrame;

/**
 * A basic program that creates a JFrame window with a title and specified size.
 * This demonstrates the fundamental usage of Java Swing GUI components.
 */
public class DisplayWindow {

    /**
     * Main entry point. Creates and displays a simple window.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        JFrame myWindow = new JFrame();

        myWindow.setSize(300, 200);
        myWindow.setTitle("My First Java Program");
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myWindow.setVisible(true);
    }
}
