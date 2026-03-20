/**
 * @file    Lab_BasicSwingFrame.java
 * @brief Creates a 300x300 Swing frame with a blue background,
 *        demonstrating JFrame subclassing, content pane, and layout.
 * @author Cheolwon Park
 * @date 2026-03-21
 */

import javax.swing.*;
import java.awt.*;

public class BasicSwingFrame extends JFrame {

    /**
     * Constructs a frame with title, size, blue background,
     * and exit-on-close behavior.
     */
    public BasicSwingFrame() {
        setTitle("Welcome to GUI Environment!");
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setBackground(Color.BLUE);

        setVisible(true);
    }

    /**
     * Entry point.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(BasicSwingFrame::new);
    }
}
