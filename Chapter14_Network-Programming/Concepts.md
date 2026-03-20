# Chapter 14 -- Network Programming

> **Last Updated:** 2026-03-21

---

## Table of Contents

- [1. Socket Fundamentals](#1-socket-fundamentals)
  - [1.1 What is a Socket?](#11-what-is-a-socket)
  - [1.2 TCP vs. UDP](#12-tcp-vs-udp)
  - [1.3 Socket Pair (4-tuple)](#13-socket-pair-4-tuple)
- [2. Java TCP Sockets](#2-java-tcp-sockets)
  - [2.1 ServerSocket](#21-serversocket)
  - [2.2 Socket (Client)](#22-socket-client)
  - [2.3 Client-Server Interaction Flow](#23-client-server-interaction-flow)
- [3. Reading and Writing over Sockets](#3-reading-and-writing-over-sockets)
- [4. Multi-client Server Architecture](#4-multi-client-server-architecture)
- [5. GUI-based Network Applications](#5-gui-based-network-applications)
- [Summary](#summary)

---

<br>

## 1. Socket Fundamentals

### 1.1 What is a Socket?

A socket is the combination of an IP address and a port number. It is the endpoint for communication between two machines over TCP/IP.

### 1.2 TCP vs. UDP

| Feature | TCP | UDP |
|:--------|:----|:----|
| Connection | Connection-oriented (3-way handshake) | Connectionless |
| Reliability | Guaranteed delivery, ordering | Best-effort, no ordering |
| Speed | Slower due to overhead | Faster, minimal overhead |
| Use cases | HTTP, SSH, file transfer | DNS, video streaming, gaming |

### 1.3 Socket Pair (4-tuple)

A TCP connection is uniquely identified by:
`(client IP, client port, server IP, server port)`

---

<br>

## 2. Java TCP Sockets

### 2.1 ServerSocket

```java
ServerSocket serverSocket = new ServerSocket(9999);
System.out.println("Waiting for connection...");
Socket clientSocket = serverSocket.accept(); // blocks until client connects
System.out.println("Client connected!");
```

### 2.2 Socket (Client)

```java
Socket socket = new Socket("localhost", 9999);
// connection established
```

### 2.3 Client-Server Interaction Flow

```
Server                              Client
------                              ------
ServerSocket(port)
  accept() <-------- 3-way --------> Socket(host, port)
  read from connectionSocket  <---  write to socket
  write to connectionSocket  --->   read from socket
  close                             close
```

---

<br>

## 3. Reading and Writing over Sockets

```java
// Server side
BufferedReader in = new BufferedReader(
    new InputStreamReader(socket.getInputStream()));
BufferedWriter out = new BufferedWriter(
    new OutputStreamWriter(socket.getOutputStream()));

String message = in.readLine();
out.write("Response\n");
out.flush();
```

> **Key Point:** Always call `flush()` after writing to ensure data is sent immediately rather than buffered.

---

<br>

## 4. Multi-client Server Architecture

Use a separate thread for each client connection:

```java
while (true) {
    Socket clientSocket = serverSocket.accept();
    new Thread(new ClientHandler(clientSocket)).start();
}
```

Each `ClientHandler` manages its own I/O streams independently, allowing the server to handle multiple clients concurrently.

---

<br>

## 5. GUI-based Network Applications

Combining Swing with sockets creates interactive networked applications like:
- GUI calculators where the client inputs a formula and the server returns the result
- Spell checkers where the server stores a dictionary and validates client queries
- Chat applications with real-time message exchange

> **Key Point:** Network I/O should run on a separate thread from the Swing EDT (Event Dispatch Thread) to prevent the GUI from freezing.

---

<br>

## Summary

| Concept | Key Point |
|:--------|:----------|
| Socket | IP address + port number endpoint |
| ServerSocket | Listens for incoming connections on a port |
| Socket (client) | Initiates TCP connection to a server |
| BufferedReader/Writer | Read/write text over socket streams |
| flush() | Ensure buffered data is actually sent |
| Multi-client | One thread per client connection |
| GUI + Network | Keep network I/O off the EDT |
