/**
 * @file    Lab_SimpleChatClient.java
 * @brief A simple TCP chat client that connects to SimpleChatServer
 *        and exchanges messages in alternating fashion.
 * @author Cheolwon Park
 * @date 2026-03-21
 */

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SimpleChatClient {

    private static final String HOST = "localhost";
    private static final int PORT = 9999;

    /**
     * Connects to the server and alternates sending/receiving messages
     * until "bye" is entered.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Socket socket = null;
        Scanner scanner = new Scanner(System.in);

        try {
            socket = new Socket(HOST, PORT);
            System.out.println("Connected to server on port " + PORT + ".");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream()));

            while (true) {
                // Write to server
                System.out.print("Client>> ");
                String clientMessage = scanner.nextLine();

                out.write(clientMessage + "\n");
                out.flush();

                if (clientMessage.equalsIgnoreCase("bye")) {
                    System.out.println("Connection closed.");
                    break;
                }

                // Read from server
                String serverMessage = in.readLine();
                if (serverMessage == null) {
                    System.out.println("Server disconnected.");
                    break;
                }
                System.out.println("Server: " + serverMessage);
            }

        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        } finally {
            try {
                if (scanner != null) scanner.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                System.err.println("Cleanup error: " + e.getMessage());
            }
        }
    }
}
