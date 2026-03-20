/**
 * @file HW3_CalculatorClient.java
 * @brief Homework 3, Q3 Client: Connects to the real-time calculator server,
 *        sends arithmetic commands, and displays results.
 * @author Cheolwon Park
 * @date 2026-03-21
 */

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class HW3_CalculatorClient {

    private static final String HOST = "localhost";
    private static final int PORT = 6000;

    /**
     * Connects to the calculator server, reads user ID, then enters
     * an interactive command loop.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT)) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);
            AtomicBoolean waiting = new AtomicBoolean(false);

            // Read user ID
            String idLine = in.readLine();
            String userId = idLine.substring(3); // "ID:UserN" -> "UserN"
            System.out.println("Connected to server on port " + PORT + ".");
            System.out.println("Welcome to the Real-time Calculator.");
            System.out.println("Enter: <operator><integer> (e.g., +10, *5, /3)");
            System.out.println();

            // Listener thread for server responses
            Thread listener = new Thread(() -> {
                try {
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        if (msg.contains("caused an overflow")) {
                            System.out.println("\n[Overflow] " + msg);
                            System.exit(0);
                        } else if (msg.equals("SUCCESS")) {
                            System.out.println("[Accepted] Operation successful.");
                            System.out.println();
                            waiting.set(false);
                        } else if (!msg.startsWith("ID:")) {
                            System.out.println(msg);
                            System.out.println();
                            waiting.set(false);
                        }
                    }
                } catch (IOException ignored) {}
            });
            listener.setDaemon(true);
            listener.start();

            // Input loop
            while (true) {
                System.out.print(userId + ") ");
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) continue;
                if (input.equalsIgnoreCase("exit")) {
                    out.println("exit");
                    System.out.println("Connection closed.");
                    break;
                }
                waiting.set(true);
                out.println(input);
                while (waiting.get()) {
                    Thread.sleep(10);
                }
            }
        } catch (IOException e) {
            System.err.println("Cannot connect to server: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
