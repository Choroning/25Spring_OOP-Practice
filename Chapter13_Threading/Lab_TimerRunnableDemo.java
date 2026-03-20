/**
 * @file    Lab_TimerRunnableDemo.java
 * @brief Demonstrates creating a thread by implementing the Runnable interface.
 *        Functionally equivalent to TimerThreadDemo but uses Runnable instead.
 * @author Cheolwon Park
 * @date 2026-03-21
 */

import java.awt.*;
import javax.swing.*;

public class TimerRunnableDemo extends JFrame {

    /**
     * Sets up a Swing frame with a JLabel and starts a timer via Runnable.
     */
    public TimerRunnableDemo() {
        setTitle("Timer Example (Runnable)");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        JLabel timerLabel = new JLabel("0");
        timerLabel.setFont(new Font("SansSerif", Font.ITALIC, 80));
        c.add(timerLabel);

        setSize(300, 170);
        setVisible(true);

        TimerRunnable runnable = new TimerRunnable(timerLabel);
        Thread thread = new Thread(runnable);
        thread.start();
    }

    /** Runnable implementation that updates a JLabel every second. */
    static class TimerRunnable implements Runnable {
        private final JLabel label;

        public TimerRunnable(JLabel label) {
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
        SwingUtilities.invokeLater(TimerRunnableDemo::new);
    }
}
