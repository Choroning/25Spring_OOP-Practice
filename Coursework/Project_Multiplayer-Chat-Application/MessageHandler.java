/**
 * @file MessageHandler.java
 * @brief Routes incoming protocol messages to the appropriate handler.
 *        Acts as the central message dispatcher for the chat server.
 * @author Cheolwon Park
 * @date 2025-06-11
 */

import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MessageHandler {

    private final UserManager userManager;
    private final Map<String, ChatRoom> rooms = new ConcurrentHashMap<>();

    /**
     * Creates a MessageHandler with the given UserManager.
     * Initializes a default "General" room.
     *
     * @param userManager the user management service
     */
    public MessageHandler(UserManager userManager) {
        this.userManager = userManager;
        rooms.put("General", new ChatRoom("General"));
    }

    /**
     * Processes a raw protocol message from a client.
     *
     * @param username the sender's username
     * @param rawMessage the raw protocol message
     */
    public void handleMessage(String username, String rawMessage) {
        String command = ChatProtocol.getCommand(rawMessage);
        String payload = ChatProtocol.getPayload(rawMessage);

        switch (command) {
            case ChatProtocol.CMD_MSG -> handleChatMessage(username, payload);
            case ChatProtocol.CMD_ROOM -> handleJoinRoom(username, payload);
            case ChatProtocol.CMD_CREATE -> handleCreateRoom(username, payload);
            case ChatProtocol.CMD_LEAVE -> handleLeaveRoom(username, payload);
            case ChatProtocol.CMD_LIST -> handleListRooms(username);
            case ChatProtocol.CMD_USERS -> handleListUsers(username);
            default -> sendError(username, "Unknown command: " + command);
        }
    }

    /**
     * Routes a chat message to the specified room.
     *
     * @param sender  the message sender
     * @param payload format: "roomName:messageContent"
     */
    private void handleChatMessage(String sender, String payload) {
        int idx = payload.indexOf(ChatProtocol.DELIMITER);
        if (idx <= 0) {
            sendError(sender, "Invalid message format. Use MSG:room:message");
            return;
        }
        String roomName = payload.substring(0, idx);
        String content = payload.substring(idx + 1);

        ChatRoom room = rooms.get(roomName);
        if (room == null) {
            sendError(sender, "Room '" + roomName + "' does not exist.");
            return;
        }
        if (!room.hasMember(sender)) {
            sendError(sender, "You are not in room '" + roomName + "'.");
            return;
        }
        room.sendMessage(sender, content);
    }

    /**
     * Handles a request to join a room.
     *
     * @param username the user requesting to join
     * @param roomName the target room
     */
    private void handleJoinRoom(String username, String roomName) {
        ChatRoom room = rooms.get(roomName);
        if (room == null) {
            sendError(username, "Room '" + roomName + "' does not exist. Use CREATE to make one.");
            return;
        }
        PrintWriter writer = userManager.getWriter(username);
        if (writer != null) {
            room.addMember(username, writer);
            userManager.sendToUser(username,
                    ChatProtocol.CMD_OK + ChatProtocol.DELIMITER + "Joined room: " + roomName);
        }
    }

    /**
     * Handles a request to create a new room.
     *
     * @param username the user creating the room
     * @param roomName the new room name
     */
    private void handleCreateRoom(String username, String roomName) {
        if (rooms.containsKey(roomName)) {
            sendError(username, "Room '" + roomName + "' already exists.");
            return;
        }
        ChatRoom newRoom = new ChatRoom(roomName);
        rooms.put(roomName, newRoom);

        PrintWriter writer = userManager.getWriter(username);
        if (writer != null) {
            newRoom.addMember(username, writer);
        }
        userManager.sendToUser(username,
                ChatProtocol.CMD_OK + ChatProtocol.DELIMITER + "Created and joined room: " + roomName);
    }

    /**
     * Handles a request to leave a room.
     *
     * @param username the user leaving
     * @param roomName the room to leave
     */
    private void handleLeaveRoom(String username, String roomName) {
        ChatRoom room = rooms.get(roomName);
        if (room != null) {
            room.removeMember(username);
            userManager.sendToUser(username,
                    ChatProtocol.CMD_OK + ChatProtocol.DELIMITER + "Left room: " + roomName);
        }
    }

    /**
     * Sends a list of all available rooms to the requesting user.
     *
     * @param username the requesting user
     */
    private void handleListRooms(String username) {
        StringBuilder sb = new StringBuilder("Available rooms: ");
        for (Map.Entry<String, ChatRoom> entry : rooms.entrySet()) {
            sb.append(entry.getKey())
              .append(" (").append(entry.getValue().getMemberCount()).append(") ");
        }
        userManager.sendToUser(username, sb.toString().trim());
    }

    /**
     * Sends a list of all online users to the requesting user.
     *
     * @param username the requesting user
     */
    private void handleListUsers(String username) {
        String userList = "Online users: " + String.join(", ", userManager.getOnlineUsers());
        userManager.sendToUser(username, userList);
    }

    /**
     * Removes a user from all rooms they are a member of.
     *
     * @param username the user to remove
     */
    public void removeFromAllRooms(String username) {
        for (ChatRoom room : rooms.values()) {
            if (room.hasMember(username)) {
                room.removeMember(username);
            }
        }
    }

    /**
     * Sends an error message to a specific user.
     *
     * @param username the target user
     * @param errorMsg the error description
     */
    private void sendError(String username, String errorMsg) {
        userManager.sendToUser(username,
                ChatProtocol.CMD_ERROR + ChatProtocol.DELIMITER + errorMsg);
    }
}
