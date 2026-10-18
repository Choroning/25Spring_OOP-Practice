/**
 * @file ChatRoom.java
 * @brief Represents a single chat room that manages its member list
 *        and handles message broadcasting within the room.
 * @author Cheolwon Park
 * @date 2025-06-11
 */

import java.io.PrintWriter;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChatRoom {

    private final String name;
    /** Maps username to PrintWriter for each member in this room */
    private final Map<String, PrintWriter> members = new ConcurrentHashMap<>();

    /**
     * Creates a chat room with the given name.
     *
     * @param name the room name
     */
    public ChatRoom(String name) {
        this.name = name;
    }

    /** @return the room name */
    public String getName() {
        return name;
    }

    /**
     * Adds a user to this room.
     *
     * @param username the user's display name
     * @param writer   the user's output stream
     */
    public void addMember(String username, PrintWriter writer) {
        members.put(username, writer);
        broadcast("[" + username + " joined the room]");
    }

    /**
     * Removes a user from this room.
     *
     * @param username the user to remove
     */
    public void removeMember(String username) {
        members.remove(username);
        broadcast("[" + username + " left the room]");
    }

    /**
     * Broadcasts a message to all members of this room.
     *
     * @param message the message to broadcast
     */
    public void broadcast(String message) {
        for (PrintWriter writer : members.values()) {
            writer.println(message);
            writer.flush();
        }
    }

    /**
     * Sends a formatted chat message from a specific user.
     *
     * @param sender  the message sender's username
     * @param content the message content
     */
    public void sendMessage(String sender, String content) {
        broadcast("[" + name + "] " + sender + ": " + content);
    }

    /**
     * Checks if a user is in this room.
     *
     * @param username the username to check
     * @return true if the user is a member
     */
    public boolean hasMember(String username) {
        return members.containsKey(username);
    }

    /**
     * Returns the set of usernames in this room.
     *
     * @return unmodifiable set of member names
     */
    public Set<String> getMemberNames() {
        return Collections.unmodifiableSet(members.keySet());
    }

    /**
     * Returns the number of members in this room.
     *
     * @return member count
     */
    public int getMemberCount() {
        return members.size();
    }
}
