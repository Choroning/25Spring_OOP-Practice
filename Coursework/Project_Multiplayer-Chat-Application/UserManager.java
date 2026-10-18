/**
 * @file UserManager.java
 * @brief Manages connected users in the chat system.
 *        Provides thread-safe user registration, removal, and lookup.
 * @author Cheolwon Park
 * @date 2025-06-11
 */

import java.io.PrintWriter;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class UserManager {

    /** Maps username to the user's output stream */
    private final Map<String, PrintWriter> users = new ConcurrentHashMap<>();

    /**
     * Registers a new user. Returns false if the username is already taken.
     *
     * @param username the desired display name
     * @param writer   the user's output stream for sending messages
     * @return true if registration succeeded, false if name is taken
     */
    public boolean addUser(String username, PrintWriter writer) {
        return users.putIfAbsent(username, writer) == null;
    }

    /**
     * Removes a user from the system.
     *
     * @param username the user to remove
     */
    public void removeUser(String username) {
        users.remove(username);
    }

    /**
     * Retrieves the output stream for a specific user.
     *
     * @param username the target user
     * @return the PrintWriter, or null if the user does not exist
     */
    public PrintWriter getWriter(String username) {
        return users.get(username);
    }

    /**
     * Checks if a username is currently connected.
     *
     * @param username the name to check
     * @return true if the user is online
     */
    public boolean isOnline(String username) {
        return users.containsKey(username);
    }

    /**
     * Returns an unmodifiable set of all online usernames.
     *
     * @return set of online usernames
     */
    public Set<String> getOnlineUsers() {
        return Collections.unmodifiableSet(users.keySet());
    }

    /**
     * Sends a message to a specific user.
     *
     * @param username the target user
     * @param message  the message to send
     */
    public void sendToUser(String username, String message) {
        PrintWriter writer = users.get(username);
        if (writer != null) {
            writer.println(message);
            writer.flush();
        }
    }

    /**
     * Broadcasts a message to all connected users.
     *
     * @param message the message to broadcast
     */
    public void broadcastAll(String message) {
        for (PrintWriter writer : users.values()) {
            writer.println(message);
            writer.flush();
        }
    }

    /**
     * Returns the number of currently connected users.
     *
     * @return the user count
     */
    public int getUserCount() {
        return users.size();
    }
}
