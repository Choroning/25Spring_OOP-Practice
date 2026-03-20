/**
 * @file ChatProtocol.java
 * @brief Defines the message protocol for the multiplayer chat system.
 *        All messages between client and server follow these command formats.
 * @author Cheolwon Park
 * @date 2026-03-21
 */
public final class ChatProtocol {

    /** Delimiter between command and payload */
    public static final String DELIMITER = ":";

    // Command prefixes
    public static final String CMD_JOIN   = "JOIN";
    public static final String CMD_MSG    = "MSG";
    public static final String CMD_LEAVE  = "LEAVE";
    public static final String CMD_LIST   = "LIST";
    public static final String CMD_ROOM   = "ROOM";
    public static final String CMD_CREATE = "CREATE";
    public static final String CMD_USERS  = "USERS";
    public static final String CMD_ERROR  = "ERROR";
    public static final String CMD_OK     = "OK";

    private ChatProtocol() {
        // Utility class -- do not instantiate
    }

    /**
     * Formats a JOIN message: "JOIN:username"
     *
     * @param username the user's display name
     * @return the formatted protocol message
     */
    public static String join(String username) {
        return CMD_JOIN + DELIMITER + username;
    }

    /**
     * Formats a MSG message: "MSG:roomName:content"
     *
     * @param roomName the target room
     * @param content  the message text
     * @return the formatted protocol message
     */
    public static String message(String roomName, String content) {
        return CMD_MSG + DELIMITER + roomName + DELIMITER + content;
    }

    /**
     * Formats a LEAVE message: "LEAVE:roomName"
     *
     * @param roomName the room to leave
     * @return the formatted protocol message
     */
    public static String leave(String roomName) {
        return CMD_LEAVE + DELIMITER + roomName;
    }

    /**
     * Formats a LIST request to list all rooms: "LIST"
     *
     * @return the formatted protocol message
     */
    public static String listRooms() {
        return CMD_LIST;
    }

    /**
     * Formats a ROOM join/switch command: "ROOM:roomName"
     *
     * @param roomName the room to join or switch to
     * @return the formatted protocol message
     */
    public static String joinRoom(String roomName) {
        return CMD_ROOM + DELIMITER + roomName;
    }

    /**
     * Formats a CREATE room command: "CREATE:roomName"
     *
     * @param roomName the room to create
     * @return the formatted protocol message
     */
    public static String createRoom(String roomName) {
        return CMD_CREATE + DELIMITER + roomName;
    }

    /**
     * Parses the command portion of a protocol message.
     *
     * @param message the raw protocol message
     * @return the command string (e.g., "JOIN", "MSG")
     */
    public static String getCommand(String message) {
        int idx = message.indexOf(DELIMITER);
        return idx > 0 ? message.substring(0, idx) : message;
    }

    /**
     * Parses the payload portion of a protocol message.
     *
     * @param message the raw protocol message
     * @return the payload string after the first delimiter, or empty string
     */
    public static String getPayload(String message) {
        int idx = message.indexOf(DELIMITER);
        return idx > 0 ? message.substring(idx + 1) : "";
    }
}
