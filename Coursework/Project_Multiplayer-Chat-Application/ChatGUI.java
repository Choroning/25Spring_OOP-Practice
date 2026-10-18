/**
 * @file ChatGUI.java
 * @brief Swing-based graphical client for the multiplayer chat system.
 *        Provides a text area for messages, input field, and room controls.
 * @author Cheolwon Park
 * @date 2025-06-11
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ChatGUI extends JFrame {

    private static final String DEFAULT_HOST = "localhost";
    private static final int DEFAULT_PORT = 12345;

    private JTextArea chatArea;
    private JTextField inputField;
    private JTextField roomField;
    private JButton sendButton;
    private JButton joinRoomButton;

    private PrintWriter out;
    private BufferedReader in;
    private Socket socket;
    private String username;
    private String currentRoom = "General";

    /**
     * Constructs the chat GUI and initiates connection setup.
     */
    public ChatGUI() {
        setTitle("Chat Client");
        setSize(600, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        layoutComponents();
        attachListeners();

        setVisible(true);
        connectToServer();
    }

    /** Initializes Swing components. */
    private void initComponents() {
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setFont(new Font("SansSerif", Font.PLAIN, 13));

        inputField = new JTextField();
        inputField.setFont(new Font("SansSerif", Font.PLAIN, 13));

        roomField = new JTextField("General", 12);

        sendButton = new JButton("Send");
        joinRoomButton = new JButton("Join Room");
    }

    /** Arranges components in the frame. */
    private void layoutComponents() {
        // Top panel: room controls
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Room:"));
        topPanel.add(roomField);
        topPanel.add(joinRoomButton);
        add(topPanel, BorderLayout.NORTH);

        // Center: chat display
        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        // Bottom panel: input
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    /** Attaches event listeners. */
    private void attachListeners() {
        ActionListener sendAction = e -> sendMessage();
        sendButton.addActionListener(sendAction);
        inputField.addActionListener(sendAction);

        joinRoomButton.addActionListener(e -> {
            String room = roomField.getText().trim();
            if (!room.isEmpty() && out != null) {
                out.println(ChatProtocol.joinRoom(room));
                currentRoom = room;
                chatArea.append("[Joined room: " + room + "]\n");
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                disconnect();
            }
        });
    }

    /** Sends the current input as a message. */
    private void sendMessage() {
        String text = inputField.getText().trim();
        if (!text.isEmpty() && out != null) {
            out.println(ChatProtocol.message(currentRoom, text));
            inputField.setText("");
        }
    }

    /** Connects to the server and registers the user. */
    private void connectToServer() {
        username = JOptionPane.showInputDialog(this,
                "Enter your username:", "Login", JOptionPane.QUESTION_MESSAGE);

        if (username == null || username.trim().isEmpty()) {
            chatArea.append("No username provided. Exiting.\n");
            return;
        }
        username = username.trim();

        try {
            socket = new Socket(DEFAULT_HOST, DEFAULT_PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            // Read welcome
            String welcome = in.readLine();
            chatArea.append(welcome + "\n");

            // Register
            out.println(ChatProtocol.join(username));
            setTitle("Chat Client - " + username);

            // Start listener thread
            Thread listener = new Thread(() -> {
                try {
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        final String displayMsg = msg;
                        SwingUtilities.invokeLater(() ->
                                chatArea.append(displayMsg + "\n"));
                    }
                } catch (IOException e) {
                    SwingUtilities.invokeLater(() ->
                            chatArea.append("Disconnected from server.\n"));
                }
            });
            listener.setDaemon(true);
            listener.start();

        } catch (IOException e) {
            chatArea.append("Cannot connect to server: " + e.getMessage() + "\n");
        }
    }

    /** Disconnects from the server. */
    private void disconnect() {
        try {
            if (out != null) out.close();
            if (in != null) in.close();
            if (socket != null && !socket.isClosed()) socket.close();
        } catch (IOException ignored) {}
    }

    /**
     * Entry point for the GUI chat client.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChatGUI::new);
    }
}
