/**
 * @file Assignment3_CalculatorServer.java
 * @brief Assignment 3, Q3 Server: Real-time calculator server that maintains
 *        a shared value updated by multiple clients via arithmetic commands.
 *        Supports concurrent clients with overflow detection.
 * @author Cheolwon Park
 * @date 2026-03-21
 */

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Assignment3_CalculatorServer {

    private static final int PORT = 6000;
    private static final AtomicInteger currentValue = new AtomicInteger(0);
    private static final CopyOnWriteArrayList<ClientHandler> handlers = new CopyOnWriteArrayList<>();
    private static final AtomicInteger clientCount = new AtomicInteger(0);
    private static final DateTimeFormatter FORMAT =
            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    /**
     * Starts the server and accepts client connections in a loop.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("[" + now() + "] Server started on port " + PORT + ". Value = 0.");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                String userName = "User" + clientCount.incrementAndGet();
                System.out.println("[" + now() + "] " + userName + " connected.");
                ClientHandler handler = new ClientHandler(clientSocket, userName);
                handlers.add(handler);
                new Thread(handler).start();
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }

    private static String now() { return LocalDateTime.now().format(FORMAT); }

    private static void broadcast(String msg) {
        for (ClientHandler h : handlers) h.sendMessage(msg);
    }

    /** Handles a single client connection in its own thread. */
    static class ClientHandler implements Runnable {
        private final Socket socket;
        private final String userName;
        private PrintWriter out;

        ClientHandler(Socket socket, String userName) {
            this.socket = socket;
            this.userName = userName;
            try {
                this.out = new PrintWriter(socket.getOutputStream(), true);
                out.println("ID:" + userName);
            } catch (IOException e) {
                System.err.println("Setup error for " + userName);
            }
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    line = line.trim();
                    if (line.equalsIgnoreCase("exit")) {
                        System.out.println("[" + now() + "] " + userName + " disconnected.");
                        break;
                    }
                    processCommand(line);
                }
            } catch (IOException ignored) {
            } finally {
                handlers.remove(this);
                try { socket.close(); } catch (IOException ignored) {}
            }
        }

        private void processCommand(String line) {
            if (line.length() < 2) {
                out.println("[Declined] Invalid input.");
                return;
            }
            char op = line.charAt(0);
            if ("+-*/".indexOf(op) < 0) {
                out.println("[Declined] Invalid operator.");
                return;
            }
            int operand;
            try {
                operand = Integer.parseInt(line.substring(1).trim());
            } catch (NumberFormatException e) {
                out.println("[Declined] Invalid operand.");
                return;
            }
            if (op == '/' && operand == 0) {
                out.println("[Declined] Cannot divide by zero.");
                return;
            }
            final int operandVal = operand;
            final char operator = op;
            int[] resultHolder = new int[1];
            int newVal = currentValue.accumulateAndGet(operandVal, (oldVal, x) -> {
                long r = switch (operator) {
                    case '+' -> (long) oldVal + x;
                    case '-' -> (long) oldVal - x;
                    case '*' -> (long) oldVal * x;
                    case '/' -> (long) oldVal / x;
                    default -> Long.MIN_VALUE;
                };
                if (r > Integer.MAX_VALUE || r < Integer.MIN_VALUE) {
                    resultHolder[0] = 1; // overflow flag
                    return oldVal; // keep old value on overflow
                }
                return (int) r;
            });
            if (resultHolder[0] == 1) {
                broadcast("**" + userName + "** caused an overflow.");
                return;
            }
            System.out.println("[" + now() + "] [" + userName + "] " + op + operand
                    + " -> value=" + newVal);
            out.println("SUCCESS");
        }

        void sendMessage(String msg) {
            if (out != null) out.println(msg);
        }
    }
}
