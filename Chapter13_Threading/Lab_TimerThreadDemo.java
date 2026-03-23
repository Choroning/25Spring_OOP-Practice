/**
 * @file    Lab_TimerThreadDemo.java
 * @brief Demonstrates creating a thread by extending Thread.
 *        Displays a timer that increments every second using a Swing JLabel.
 * @author Cheolwon Park
 * @date 2025-05-21
 */

import java.awt.*;
import javax.swing.*;

public class TimerThreadDemo extends JFrame {

    /**
     * Sets up a Swing frame with a JLabel and starts a timer thread.
     */
    public TimerThreadDemo() {
        setTitle("Timer Example (Thread)");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        JLabel timerLabel = new JLabel("0");
        timerLabel.setFont(new Font("SansSerif", Font.ITALIC, 80));
        c.add(timerLabel);

        setSize(300, 170);
        setVisible(true);

        // Start the timer thread
        TimerThread timer = new TimerThread(timerLabel);
        timer.start();
    }

    /** Thread that updates a JLabel every second. */
    static class TimerThread extends Thread {
        private final JLabel label;

        public TimerThread(JLabel label) {
            this.label = label;
        }

        @Override
        public void run() {
            int count = 0;
            while (true) {
                label.setText(Integer.toString(count++));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

    /**
     * Entry point.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TimerThreadDemo::new);
    }
}
