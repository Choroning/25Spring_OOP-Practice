/**
 * @file SimpleChatServer.java
 * @brief A simple TCP chat server that exchanges messages with a single client.
 *        Demonstrates ServerSocket, accept(), and BufferedReader/Writer usage.
 * @author Cheolwon Park
 * @date 2026-03-21
 */

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SimpleChatServer {

    private static final int PORT = 9999;

    /**
     * Starts the server, waits for a client, then alternates reading
     * and writing messages until "bye" is received.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        ServerSocket listener = null;
        Socket socket = null;
        Scanner scanner = new Scanner(System.in);

        try {
            listener = new ServerSocket(PORT);
            System.out.println("Waiting for connection on port " + PORT + "...");

            socket = listener.accept();
            System.out.println("Client connected!");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream()));

            while (true) {
                // Read from client
                String clientMessage = in.readLine();
                if (clientMessage == null || clientMessage.equalsIgnoreCase("bye")) {
                    System.out.println("Connection terminated by client.");
                    break;
                }
                System.out.println("Client: " + clientMessage);

                // Write to client
                System.out.print("Server>> ");
                String serverMessage = scanner.nextLine();
                out.write(serverMessage + "\n");
                out.flush();
            }

        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        } finally {
            try {
                if (scanner != null) scanner.close();
                if (socket != null) socket.close();
                if (listener != null) listener.close();
            } catch (IOException e) {
                System.err.println("Cleanup error: " + e.getMessage());
            }
        }
    }
}
