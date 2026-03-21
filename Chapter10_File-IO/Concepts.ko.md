# Chapter 10 — 파일 입출력

> **최종 수정일:** 2026-03-21

---

## 목차

- [1. 파일 입출력 기초](#1-파일-입출력-기초)
  - [1.1 바이트 스트림 vs. 문자 스트림](#11-바이트-스트림-vs-문자-스트림)
  - [1.2 File 클래스](#12-file-클래스)
- [2. 텍스트 파일 입출력](#2-텍스트-파일-입출력)
  - [2.1 텍스트 파일 읽기](#21-텍스트-파일-읽기)
  - [2.2 텍스트 파일 쓰기](#22-텍스트-파일-쓰기)
- [3. 바이너리 파일 입출력](#3-바이너리-파일-입출력)
  - [3.1 DataInputStream과 DataOutputStream](#31-datainputstream과-dataoutputstream)
  - [3.2 ObjectInputStream과 ObjectOutputStream](#32-objectinputstream과-objectoutputstream)
- [4. Try-with-Resources](#4-try-with-resources)
- [5. Serializable 인터페이스](#5-serializable-인터페이스)
- [요약](#요약)

---

<br>

## 1. 파일 입출력 기초

### 1.1 바이트 스트림 vs. 문자 스트림

| 유형 | 기본 클래스 | 사용 사례 |
|:-----|:-----------|:---------|
| 바이트 스트림 | `InputStream` / `OutputStream` | 바이너리 데이터 (이미지, 오디오, 직렬화된 객체) |
| 문자 스트림 | `Reader` / `Writer` | 텍스트 데이터 (UTF-8/16 인코딩 파일) |

### 1.2 File 클래스

`File` 클래스는 파일 또는 디렉터리 경로를 나타낸다 (실제로 파일을 열지는 않는다):

```java
File file = new File("data.txt");
System.out.println("Exists: " + file.exists());
System.out.println("Size:   " + file.length() + " bytes");
System.out.println("Path:   " + file.getAbsolutePath());
```

---

<br>

## 2. 텍스트 파일 입출력

### 2.1 텍스트 파일 읽기

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

### 2.2 텍스트 파일 쓰기

```java
try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
    writer.write("Hello, File I/O!");
    writer.newLine();
    writer.write("Second line.");
} catch (IOException e) {
    System.err.println("Error writing file: " + e.getMessage());
}
```

> **핵심 포인트:** 성능을 위해 `FileReader`/`FileWriter`는 항상 `BufferedReader`/`BufferedWriter`로 감싸야 한다. 버퍼링은 실제 디스크 I/O 작업 횟수를 줄여준다.

---

<br>

## 3. 바이너리 파일 입출력

### 3.1 DataInputStream과 DataOutputStream

기본형 타입을 바이너리 형식으로 읽고 쓰기 위한 스트림이다:

```java
// 쓰기
try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("data.bin"))) {
    dos.writeInt(42);
    dos.writeDouble(3.14);
    dos.writeBoolean(true);
    dos.writeUTF("Hello");
}

// 읽기 (쓰기 순서와 타입이 일치해야 함)
try (DataInputStream dis = new DataInputStream(new FileInputStream("data.bin"))) {
    int i = dis.readInt();
    double d = dis.readDouble();
    boolean b = dis.readBoolean();
    String s = dis.readUTF();
}
```

### 3.2 ObjectInputStream과 ObjectOutputStream

객체 전체를 직렬화하기 위한 스트림이다:

```java
// 객체 쓰기
try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("obj.bin"))) {
    oos.writeObject(myAccount);
}

// 객체 읽기
try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("obj.bin"))) {
    Account account = (Account) ois.readObject();
}
```

---

<br>

## 4. Try-with-Resources

Java 7 이상에서는 자동 자원 관리를 지원한다. `AutoCloseable`을 구현한 자원은 자동으로 닫힌다:

```java
try (FileReader fr = new FileReader("file.txt");
     BufferedReader br = new BufferedReader(fr)) {
    // br 사용
} // br과 fr이 자동으로 닫힘
```

> **핵심 포인트:** `finally` 블록에서 수동으로 `close()`를 호출하는 것보다 try-with-resources를 사용하는 것이 좋다. 코드가 짧고 깔끔하며 예외에 안전하다.

---

<br>

## 5. Serializable 인터페이스

`ObjectOutputStream`으로 객체를 쓰려면 해당 클래스가 `Serializable`을 구현해야 한다:

```java
import java.io.Serializable;

public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    private String ownerName;
    private double balance;
    // ...
}
```

`transient`로 표시된 필드는 직렬화에서 제외된다.

---

<br>

## 요약

| 개념 | 핵심 포인트 |
|:-----|:-----------|
| 바이트 스트림 | 바이너리 데이터를 위한 `InputStream`/`OutputStream` |
| 문자 스트림 | 텍스트 데이터를 위한 `Reader`/`Writer` |
| 버퍼링 | 성능을 위해 원시 스트림을 `Buffered*`로 감쌈 |
| DataInputStream | 기본형 타입을 바이너리 형식으로 읽기/쓰기 |
| ObjectStream | 객체 전체를 직렬화/역직렬화 |
| Try-with-resources | `AutoCloseable` 자원의 자동 정리 |
| Serializable | 객체 직렬화에 필요한 인터페이스 |
