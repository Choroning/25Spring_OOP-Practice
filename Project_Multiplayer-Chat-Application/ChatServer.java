/**
 * @file ChatServer.java
 * @brief Multi-threaded TCP chat server that manages rooms and routes messages.
 *        Each client connection is handled by a dedicated thread.
 * @author Cheolwon Park
 * @date 2026-03-21
 */

import java.io.*;
import java.net.*;

public class ChatServer {

    private static final int PORT = 12345;
    private final UserManager userManager = new UserManager();
    private final MessageHandler messageHandler = new MessageHandler(userManager);

    /**
     * Starts the server and listens for incoming client connections.
     */
    public void start() {
        System.out.println("Chat server starting on port " + PORT + "...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is ready. Waiting for clients.");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New connection from " + clientSocket.getRemoteSocketAddress());
                new Thread(new ClientSession(clientSocket)).start();
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }

    /**
     * Handles a single client session in its own thread.
     */
    private class ClientSession implements Runnable {
        private final Socket socket;
        private String username;

        ClientSession(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
            ) {
                // Authentication: first message must be JOIN:username
                out.println("Welcome! Send JOIN:yourname to register.");
                String joinMessage = in.readLine();
                if (joinMessage == null) return;

                String command = ChatProtocol.getCommand(joinMessage);
                if (!ChatProtocol.CMD_JOIN.equals(command)) {
                    out.println("ERROR:First message must be JOIN:username");
                    return;
                }

                username = ChatProtocol.getPayload(joinMessage);
                if (username.isEmpty()) {
                    out.println("ERROR:Username cannot be empty");
                    return;
                }

                if (!userManager.addUser(username, out)) {
                    out.println("ERROR:Username '" + username + "' is already taken");
                    return;
                }

                out.println("OK:Welcome, " + username + "! You are now connected.");
                System.out.println(username + " has joined the server.");

                // Auto-join the General room
                messageHandler.handleMessage(username, ChatProtocol.joinRoom("General"));

                // Main message loop
                String line;
                while ((line = in.readLine()) != null) {
                    if (line.trim().isEmpty()) continue;
                    messageHandler.handleMessage(username, line.trim());
                }

            } catch (IOException e) {
                // Client disconnected
            } finally {
                if (username != null) {
                    messageHandler.removeFromAllRooms(username);
                    userManager.removeUser(username);
                    System.out.println(username + " has disconnected.");
                }
                try { socket.close(); } catch (IOException ignored) {}
            }
        }
    }

    /**
     * Entry point for the chat server.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new ChatServer().start();
    }
}
