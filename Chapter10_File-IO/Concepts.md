# Chapter 10 — File I/O

> **Last Updated:** 2026-03-21

---

## Table of Contents

- [1. File I/O Fundamentals](#1-file-io-fundamentals)
  - [1.1 Byte Streams vs. Character Streams](#11-byte-streams-vs-character-streams)
  - [1.2 The File Class](#12-the-file-class)
- [2. Text File I/O](#2-text-file-io)
  - [2.1 Reading Text Files](#21-reading-text-files)
  - [2.2 Writing Text Files](#22-writing-text-files)
- [3. Binary File I/O](#3-binary-file-io)
  - [3.1 DataInputStream and DataOutputStream](#31-datainputstream-and-dataoutputstream)
  - [3.2 ObjectInputStream and ObjectOutputStream](#32-objectinputstream-and-objectoutputstream)
- [4. Try-with-Resources](#4-try-with-resources)
- [5. The Serializable Interface](#5-the-serializable-interface)
- [Summary](#summary)

---

<br>

## 1. File I/O Fundamentals

### 1.1 Byte Streams vs. Character Streams

| Type | Base Classes | Use Case |
|:-----|:-------------|:---------|
| Byte Streams | `InputStream` / `OutputStream` | Binary data (images, audio, serialized objects) |
| Character Streams | `Reader` / `Writer` | Text data (UTF-8/16 encoded files) |

### 1.2 The File Class

The `File` class represents a file or directory path (does not actually open files):

```java
File file = new File("data.txt");
System.out.println("Exists: " + file.exists());
System.out.println("Size:   " + file.length() + " bytes");
System.out.println("Path:   " + file.getAbsolutePath());
```

---

<br>

## 2. Text File I/O

### 2.1 Reading Text Files

```java
try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
    String line;
    while ((line = reader.readLine()) != null) {
        System.out.println(line);
    }
} catch (IOException e) {
    System.err.println("Error reading file: " + e.getMessage());
}
```

### 2.2 Writing Text Files

```java
try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
    writer.write("Hello, File I/O!");
    writer.newLine();
    writer.write("Second line.");
} catch (IOException e) {
    System.err.println("Error writing file: " + e.getMessage());
}
```

> **Key Point:** Always wrap `FileReader`/`FileWriter` in `BufferedReader`/`BufferedWriter` for performance. Buffering reduces the number of actual disk I/O operations.

---

<br>

## 3. Binary File I/O

### 3.1 DataInputStream and DataOutputStream

For reading/writing primitive types in binary format:

```java
// Writing
try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("data.bin"))) {
    dos.writeInt(42);
    dos.writeDouble(3.14);
    dos.writeBoolean(true);
    dos.writeUTF("Hello");
}

// Reading (must match the write order and types)
try (DataInputStream dis = new DataInputStream(new FileInputStream("data.bin"))) {
    int i = dis.readInt();
    double d = dis.readDouble();
    boolean b = dis.readBoolean();
    String s = dis.readUTF();
}
```

### 3.2 ObjectInputStream and ObjectOutputStream

For serializing entire objects:

```java
// Writing an object
try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("obj.bin"))) {
    oos.writeObject(myAccount);
}

// Reading an object
try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("obj.bin"))) {
    Account account = (Account) ois.readObject();
}
```

---

<br>

## 4. Try-with-Resources

Java 7+ supports automatic resource management. Resources implementing `AutoCloseable` are closed automatically:

```java
try (FileReader fr = new FileReader("file.txt");
     BufferedReader br = new BufferedReader(fr)) {
    // use br
} // br and fr are automatically closed here
```

> **Key Point:** Prefer try-with-resources over manual `close()` calls in `finally` blocks. It is shorter, cleaner, and exception-safe.

---

<br>

## 5. The Serializable Interface

A class must implement `Serializable` to be written with `ObjectOutputStream`:

```java
import java.io.Serializable;

public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    private String ownerName;
    private double balance;
    // ...
}
```

Fields marked `transient` are excluded from serialization.

---

<br>

## Summary

| Concept | Key Point |
|:--------|:----------|
| Byte Streams | `InputStream`/`OutputStream` for binary data |
| Character Streams | `Reader`/`Writer` for text data |
| Buffering | Wrap raw streams in `Buffered*` for performance |
| DataInputStream | Read/write primitives in binary format |
| ObjectStream | Serialize/deserialize entire objects |
| Try-with-resources | Automatic cleanup of `AutoCloseable` resources |
| Serializable | Required interface for object serialization |
