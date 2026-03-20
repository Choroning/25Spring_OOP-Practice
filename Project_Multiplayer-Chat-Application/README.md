# Multiplayer Chat Application

A multi-threaded TCP-based chat system implemented in Java, featuring room management, concurrent client handling, and both console and GUI clients.

## Architecture

```
ChatServer (main server loop)
  |-- UserManager (tracks connected users)
  |-- MessageHandler (routes protocol messages)
  |     |-- ChatRoom (manages room membership and broadcasting)
  |-- ClientSession (per-client thread)

ChatProtocol (shared message format)

ChatClient (console client)
ChatGUI (Swing-based graphical client)
```

## Files

| File | Description |
|:-----|:------------|
| `ChatServer.java` | Multi-threaded server managing connections and rooms |
| `ChatClient.java` | Console-based client with text commands |
| `ChatGUI.java` | Swing-based graphical chat interface |
| `ChatProtocol.java` | Message protocol definitions (JOIN, MSG, LEAVE, LIST, etc.) |
| `MessageHandler.java` | Routes incoming messages to appropriate handlers |
| `ChatRoom.java` | Room management: create, join, leave, broadcast |
| `UserManager.java` | User tracking: registration, lookup, broadcast |

## Protocol

| Command | Format | Description |
|:--------|:-------|:------------|
| JOIN | `JOIN:username` | Register with the server |
| MSG | `MSG:roomName:content` | Send a message to a room |
| LEAVE | `LEAVE:roomName` | Leave a room |
| LIST | `LIST` | List all available rooms |
| ROOM | `ROOM:roomName` | Join an existing room |
| CREATE | `CREATE:roomName` | Create and join a new room |
| USERS | `USERS:` | List online users |

## How to Run

### Compile

```bash
javac *.java
```

### Start Server

```bash
java ChatServer
```

### Connect via Console Client

```bash
java ChatClient
```

### Connect via GUI Client

```bash
java ChatGUI
```

## Client Commands (Console)

```
<text>            -- Send message to General room
/msg <room> <msg> -- Send message to a specific room
/rooms            -- List all rooms
/users            -- List online users
/join <room>      -- Join an existing room
/create <room>    -- Create and join a new room
/leave <room>     -- Leave a room
/help             -- Show help
/quit             -- Disconnect
```

## Design Concepts

This project integrates concepts from multiple course chapters:

- **Chapter 07-08**: Object-oriented class design with encapsulation and inheritance
- **Chapter 09**: Collections framework (ConcurrentHashMap, CopyOnWriteArrayList)
- **Chapter 10**: Stream-based I/O over sockets
- **Chapter 12**: Swing GUI components (JFrame, JTextArea, JButton, layout managers)
- **Chapter 13**: Multi-threading (one thread per client)
- **Chapter 14**: TCP socket programming (ServerSocket, Socket)
- **Chapter 15**: Thread synchronization (concurrent collections, thread-safe operations)

## References

- MIT 6.005: Software Construction — Concurrency and Thread Safety
- Stanford CS108: Object-Oriented Systems Design — Network Programming in Java
