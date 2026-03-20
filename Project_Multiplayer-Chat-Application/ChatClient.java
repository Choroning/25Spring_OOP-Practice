/**
 * @file ChatClient.java
 * @brief TCP chat client that connects to the ChatServer and allows
 *        the user to send/receive messages via the console.
 * @author Cheolwon Park
 * @date 2026-03-21
 */

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {

    private static final String DEFAULT_HOST = "localhost";
    private static final int DEFAULT_PORT = 12345;

    /**
     * Connects to the chat server, registers a username, and enters
     * an interactive message loop.
     *
     * @param args optional: [host] [port]
     */
    public static void main(String[] args) {
        String host = args.length > 0 ? args[0] : DEFAULT_HOST;
        int port = args.length > 1 ? Integer.parseInt(args[1]) : DEFAULT_PORT;

        try (
            Socket socket = new Socket(host, port);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in)
        ) {
            // Read welcome message
            String welcome = in.readLine();
            System.out.println(welcome);

            // Register username
            System.out.print("Enter your username: ");
            String username = scanner.nextLine().trim();
            out.println(ChatProtocol.join(username));

            // Start listener thread for incoming messages
            Thread listener = new Thread(() -> {
                try {
                    String serverMsg;
                    while ((serverMsg = in.readLine()) != null) {
                        System.out.println(serverMsg);
                    }
                } catch (IOException e) {
                    System.out.println("Disconnected from server.");
                }
            });
            listener.setDaemon(true);
            listener.start();

            // Print help
            printHelp();

            // Main input loop
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) continue;

                if (input.equalsIgnoreCase("/quit")) {
                    System.out.println("Goodbye!");
                    break;
                } else if (input.equalsIgnoreCase("/help")) {
                    printHelp();
                } else if (input.equalsIgnoreCase("/rooms")) {
                    out.println(ChatProtocol.listRooms());
                } else if (input.equalsIgnoreCase("/users")) {
                    out.println("USERS:");
                } else if (input.startsWith("/join ")) {
                    String room = input.substring(6).trim();
                    out.println(ChatProtocol.joinRoom(room));
                } else if (input.startsWith("/create ")) {
                    String room = input.substring(8).trim();
                    out.println(ChatProtocol.createRoom(room));
                } else if (input.startsWith("/leave ")) {
                    String room = input.substring(7).trim();
                    out.println(ChatProtocol.leave(room));
                } else if (input.startsWith("/msg ")) {
                    // Format: /msg roomName message content
                    String rest = input.substring(5).trim();
                    int spaceIdx = rest.indexOf(' ');
                    if (spaceIdx > 0) {
                        String room = rest.substring(0, spaceIdx);
                        String msg = rest.substring(spaceIdx + 1);
                        out.println(ChatProtocol.message(room, msg));
                    } else {
                        System.out.println("Usage: /msg <room> <message>");
                    }
                } else {
                    // Default: send to General room
                    out.println(ChatProtocol.message("General", input));
                }
            }

        } catch (IOException e) {
            System.err.println("Cannot connect to server: " + e.getMessage());
        }
    }

    /** Prints available commands. */
    private static void printHelp() {
        System.out.println("Commands:");
        System.out.println("  <text>            Send message to General room");
        System.out.println("  /msg <room> <msg> Send message to a specific room");
        System.out.println("  /rooms            List all rooms");
        System.out.println("  /users            List online users");
        System.out.println("  /join <room>      Join an existing room");
        System.out.println("  /create <room>    Create and join a new room");
        System.out.println("  /leave <room>     Leave a room");
        System.out.println("  /help             Show this help");
        System.out.println("  /quit             Disconnect");
    }
}
