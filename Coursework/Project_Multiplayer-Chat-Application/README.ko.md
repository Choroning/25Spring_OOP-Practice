# 멀티플레이어 채팅 애플리케이션

[레포지토리 안내](../../README.ko.md) | [English](README.md) | [한국어](README.ko.md)

> **최종 수정일:** 2026-10-26

Java로 구현한 멀티스레드 TCP 기반 채팅 시스템으로, 채팅방 관리, 여러 클라이언트의 동시 처리, 콘솔 클라이언트와 GUI 클라이언트를 갖추었다.

## 아키텍처

```text
ChatServer (main server loop)
  ├── UserManager (tracks connected users)
  ├── MessageHandler (routes protocol messages)
  │     └── ChatRoom (manages room membership and broadcasting)
  └── ClientSession (per-client thread)

ChatProtocol (shared message format)

ChatClient (console client)
ChatGUI (Swing-based graphical client)
```

## 파일

| 파일 | 설명 |
|:-----|:------------|
| `ChatServer.java` | 연결과 채팅방을 관리하는 멀티스레드 서버 |
| `ChatClient.java` | 텍스트 명령을 사용하는 콘솔 기반 클라이언트 |
| `ChatGUI.java` | Swing 기반 그래픽 채팅 인터페이스 |
| `ChatProtocol.java` | 메시지 프로토콜 정의(JOIN, MSG, LEAVE, LIST 등) |
| `MessageHandler.java` | 들어온 메시지를 알맞은 핸들러로 전달 |
| `ChatRoom.java` | 채팅방 관리: 생성, 참여, 퇴장, 브로드캐스트 |
| `UserManager.java` | 사용자 관리: 등록, 조회, 브로드캐스트 |

## 프로토콜

| 명령 | 형식 | 설명 |
|:--------|:-------|:------------|
| JOIN | `JOIN:username` | 서버에 사용자 등록 |
| MSG | `MSG:roomName:content` | 채팅방에 메시지 전송 |
| LEAVE | `LEAVE:roomName` | 채팅방에서 퇴장 |
| LIST | `LIST` | 사용 가능한 모든 채팅방 목록 |
| ROOM | `ROOM:roomName` | 기존 채팅방에 참여 |
| CREATE | `CREATE:roomName` | 새 채팅방을 만들고 참여 |
| USERS | `USERS:` | 접속 중인 사용자 목록 |

## 실행 방법

### 컴파일

```bash
javac *.java
```

### 서버 시작

```bash
java ChatServer
```

### 콘솔 클라이언트로 접속

```bash
java ChatClient
```

### GUI 클라이언트로 접속

```bash
java ChatGUI
```

## 클라이언트 명령 (콘솔)

```text
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

## 설계 개념

이 프로젝트는 과목의 여러 강의에서 다룬 개념을 통합한다.

- **7~8강:** 캡슐화와 상속을 이용한 객체 지향 클래스 설계
- **9강:** 컬렉션 프레임워크(`ConcurrentHashMap`, `CopyOnWriteArrayList`)
- **10강:** 소켓을 통한 스트림 기반 입출력
- **12강:** Swing GUI 컴포넌트(`JFrame`, `JTextArea`, `JButton`, 배치 관리자)
- **13강:** 멀티스레딩(클라이언트마다 스레드 하나)
- **14강:** TCP 소켓 프로그래밍(`ServerSocket`, `Socket`)
- **15강:** 스레드 동기화(동시성 컬렉션, 스레드 안전한 연산)

## 참고 문헌

- MIT 6.005: Software Construction, Concurrency and Thread Safety
- Stanford CS108: Object-Oriented Systems Design, Network Programming in Java
