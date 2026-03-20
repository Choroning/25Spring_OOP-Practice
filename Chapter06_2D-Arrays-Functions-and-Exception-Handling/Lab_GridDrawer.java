/**
 * @file    Lab_GridDrawer.java
 * @brief Draws a grid of lines using a 2D array of Point objects,
 *        demonstrating 2D array usage in graphical applications.
 * @author Cheolwon Park
 * @date 2026-03-21
 */

import javax.swing.*;
import java.awt.*;

public class GridDrawer extends JFrame {

    private static final int FRAME_SIZE = 600;
    private static final int NUM_SQUARES = 10;
    private static final int SQUARE_SIZE = FRAME_SIZE / NUM_SQUARES;

    private final Point[][] gridPoints;

    /**
     * Constructs the frame and initializes the grid point array.
     */
    public GridDrawer() {
        super("2D Array Grid Drawer");
        setSize(FRAME_SIZE, FRAME_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        gridPoints = new Point[NUM_SQUARES + 1][NUM_SQUARES + 1];
        for (int r = 0; r < gridPoints.length; r++) {
            for (int c = 0; c < gridPoints[r].length; c++) {
                gridPoints[r][c] = new Point(c * SQUARE_SIZE, r * SQUARE_SIZE);
            }
        }
    }

    /**
     * Paints horizontal and vertical grid lines.
     *
     * @param g the Graphics context
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Draw horizontal lines
        for (int r = 0; r < gridPoints.length; r++) {
            for (int c = 0; c < gridPoints[r].length - 1; c++) {
                Point p = gridPoints[r][c];
                Point q = gridPoints[r][c + 1];
                g.drawLine(p.x, p.y, q.x, q.y);
            }
        }

        // Draw vertical lines
        for (int c = 0; c < gridPoints[0].length; c++) {
            for (int r = 0; r < gridPoints.length - 1; r++) {
                Point p = gridPoints[r][c];
                Point q = gridPoints[r + 1][c];
                g.drawLine(p.x, p.y, q.x, q.y);
            }
        }
    }

    /**
     * Entry point for the grid drawing application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new GridDrawer();
    }
}
