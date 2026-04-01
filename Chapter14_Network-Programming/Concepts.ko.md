# Chapter 14 — 네트워크 프로그래밍

> **최종 수정일:** 2026-04-01

> **선수 지식**: [프로그래밍언어] Java (제1-13장). [컴퓨터네트워크] TCP/IP 기초.
>
> **학습 목표**:
> 1. 소켓을 사용하여 클라이언트-서버 통신을 구현할 수 있다
> 2. 멀티스레드 네트워크 서버를 구축할 수 있다
> 3. URLConnection으로 HTTP 기반 통신을 적용할 수 있다

---

## 목차

- [1. 소켓 기초](#1-소켓-기초)
  - [1.1 소켓이란?](#11-소켓이란)
  - [1.2 TCP vs. UDP](#12-tcp-vs-udp)
  - [1.3 소켓 쌍 (4-tuple)](#13-소켓-쌍-4-tuple)
- [2. Java TCP 소켓](#2-java-tcp-소켓)
  - [2.1 ServerSocket](#21-serversocket)
  - [2.2 Socket (클라이언트)](#22-socket-클라이언트)
  - [2.3 클라이언트-서버 상호작용 흐름](#23-클라이언트-서버-상호작용-흐름)
- [3. 소켓을 통한 읽기와 쓰기](#3-소켓을-통한-읽기와-쓰기)
- [4. 다중 클라이언트 서버 아키텍처](#4-다중-클라이언트-서버-아키텍처)
- [5. GUI 기반 네트워크 애플리케이션](#5-gui-기반-네트워크-애플리케이션)
- [요약](#요약)

---

<br>

## 1. 소켓 기초

### 1.1 소켓이란?

소켓은 IP 주소와 포트 번호의 조합이다. TCP/IP를 통해 두 컴퓨터 간 통신을 위한 엔드포인트 역할을 한다.

### 1.2 TCP vs. UDP

| 특징 | TCP | UDP |
|:--------|:----|:----|
| 연결 방식 | 연결 지향 (3-way handshake) | 비연결형 |
| 신뢰성 | 전달 보장, 순서 보장 | 최선 노력, 순서 보장 없음 |
| 속도 | 오버헤드로 인해 느림 | 빠름, 최소 오버헤드 |
| 사용 사례 | HTTP, SSH, 파일 전송 | DNS, 비디오 스트리밍, 게임 |

### 1.3 소켓 쌍 (4-tuple)

TCP 연결은 다음 4가지 요소로 고유하게 식별된다:
`(client IP, client port, server IP, server port)`

---

<br>

## 2. Java TCP 소켓

### 2.1 ServerSocket

```java
ServerSocket serverSocket = new ServerSocket(9999);
System.out.println("Waiting for connection...");
Socket clientSocket = serverSocket.accept(); // 클라이언트가 연결될 때까지 블로킹
System.out.println("Client connected!");
```

### 2.2 Socket (클라이언트)

```java
Socket socket = new Socket("localhost", 9999);
// 연결 수립 완료
```

### 2.3 클라이언트-서버 상호작용 흐름

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

## 3. 소켓을 통한 읽기와 쓰기

```java
// 서버 측
BufferedReader in = new BufferedReader(
    new InputStreamReader(socket.getInputStream()));
BufferedWriter out = new BufferedWriter(
    new OutputStreamWriter(socket.getOutputStream()));

String message = in.readLine();
out.write("Response\n");
out.flush();
```

> **핵심 요점:** 데이터가 버퍼에 남지 않고 즉시 전송되도록 쓰기 후 반드시 `flush()`를 호출해야 한다.

---

<br>

## 4. 다중 클라이언트 서버 아키텍처

각 클라이언트 연결에 대해 별도의 스레드를 사용한다:

```java
while (true) {
    Socket clientSocket = serverSocket.accept();
    new Thread(new ClientHandler(clientSocket)).start();
}
```

각 `ClientHandler`는 자체 I/O 스트림을 독립적으로 관리하므로, 서버가 여러 클라이언트를 동시에 처리할 수 있다.

---

<br>

## 5. GUI 기반 네트워크 애플리케이션

Swing과 소켓을 결합하면 다음과 같은 대화형 네트워크 애플리케이션을 만들 수 있다:
- 클라이언트가 수식을 입력하고 서버가 결과를 반환하는 GUI 계산기
- 서버가 사전을 저장하고 클라이언트의 질의를 검증하는 맞춤법 검사기
- 실시간 메시지 교환이 가능한 채팅 애플리케이션

> **핵심 요점:** GUI가 멈추는 것을 방지하기 위해, 네트워크 I/O는 Swing EDT(Event Dispatch Thread)와 별도의 스레드에서 실행해야 한다.

---

<br>

## 요약

| 개념 | 핵심 요점 |
|:--------|:----------|
| 소켓 | IP 주소 + 포트 번호로 구성된 엔드포인트 |
| ServerSocket | 포트에서 들어오는 연결을 대기 |
| Socket (클라이언트) | 서버에 TCP 연결을 시작 |
| BufferedReader/Writer | 소켓 스트림을 통해 텍스트 읽기/쓰기 |
| flush() | 버퍼링된 데이터가 실제로 전송되도록 보장 |
| 다중 클라이언트 | 클라이언트 연결당 하나의 스레드 |
| GUI + 네트워크 | 네트워크 I/O를 EDT에서 분리하여 실행 |
