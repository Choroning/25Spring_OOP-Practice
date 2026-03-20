/**
 * @file FlowLayoutDemo.java
 * @brief Demonstrates FlowLayout by placing calculator operation buttons
 *        in a JFrame with configurable alignment and gaps.
 * @author Cheolwon Park
 * @date 2026-03-21
 */

import javax.swing.*;
import java.awt.*;

public class FlowLayoutDemo extends JFrame {

    /**
     * Creates a frame with FlowLayout and five arithmetic operation buttons.
     */
    public FlowLayoutDemo() {
        setTitle("FlowLayout Demo");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container container = getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 40));

        container.add(new JButton("Add"));
        container.add(new JButton("Sub"));
        container.add(new JButton("Mul"));
        container.add(new JButton("Div"));
        container.add(new JButton("Calculate"));

        setVisible(true);
    }

    /**
     * Entry point.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(FlowLayoutDemo::new);
    }
}
