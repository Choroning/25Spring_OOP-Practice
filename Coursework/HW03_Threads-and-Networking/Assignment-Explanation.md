# HW3 Assignment Explanation — Threads and Networking

[Repository Guide](../../README.md) | [English](Assignment-Explanation.md) | [한국어](Assignment-Explanation.ko.md)

> **Last Updated:** 2026-11-16

> **Learning Objectives:**
> 1. Count the frequencies of fixed-length substrings efficiently while reading a large text file once
> 2. Correctly skip the windows that contain characters that must not be counted
> 3. Handle thread safety and overflow in a server where several clients update one value together
> 4. Explain how messages flow in a socket-based client-server program

---

## Table of Contents

- [1. Assignment Layout and How to Run](#1-assignment-layout-and-how-to-run)
- [2. Requirements](#2-requirements)
- [3. Q1: Frequencies of Five-Letter Combinations in a DNA Sequence](#3-q1-frequencies-of-five-letter-combinations-in-a-dna-sequence)
  - [3.1 Representing a Five-Letter Combination as a Number](#31-representing-a-five-letter-combination-as-a-number)
  - [3.2 Moving the Window One Letter at a Time](#32-moving-the-window-one-letter-at-a-time)
  - [3.3 Windows Containing N](#33-windows-containing-n)
  - [3.4 Results](#34-results)
- [4. Q3: A Client-Server Program for Joint Calculation](#4-q3-a-client-server-program-for-joint-calculation)
  - [4.1 Structure of the Server](#41-structure-of-the-server)
  - [4.2 Processing Commands and Detecting Overflow](#42-processing-commands-and-detecting-overflow)
  - [4.3 The Client](#43-the-client)
  - [4.4 Results](#44-results)
- [Summary](#summary)
- [Self-Check Questions](#self-check-questions)

---

<br>

## 1. Assignment Layout and How to Run

> **Try It First:** This document contains the answers and solutions. Solve the problems in the [assignment handout](Original/Assignment.md) yourself before reading on.

This assignment has five questions on computing the frequencies of five-letter combinations in a large DNA sequence and parallelizing it, and on a client-server program that several users connect to. The original questions and example screens are in the [assignment handout](Original/Assignment.md). This document covers Q1 and Q3, which have solutions; this repository has no solutions to the other questions.

```text
Coursework/HW03_Threads-and-Networking/
├── Original/
│   ├── Assignment.ko.md                     # assignment handout (Korean)
│   ├── Assignment.md                        # assignment handout (English)
│   └── Q1_Sample.txt                        # first 300 lines of Q1.txt
├── Solution/
│   ├── Assignment3_DnaFiveMerCounter.java   # Q1
│   ├── Assignment3_CalculatorServer.java    # Q3 server
│   └── Assignment3_CalculatorClient.java    # Q3 client
├── Assignment-Explanation.ko.md             # assignment explanation (Korean)
└── Assignment-Explanation.md                # assignment explanation (English)
```

*Figure 1. Directory layout of HW3*

| Question | Description | Solution File | Related Units |
|:-----|:-----|:-----|:-----|
| Q1 | Frequencies of five-letter combinations in a DNA sequence (20 points) | [Assignment3_DnaFiveMerCounter.java](Solution/Assignment3_DnaFiveMerCounter.java) | Lecture 10 |
| Q2 | Speeding up the computation with parallel processing (30 points) | None | - |
| Q3 | A client-server program for joint calculation (30 points) | [Assignment3_CalculatorServer.java](Solution/Assignment3_CalculatorServer.java), [Assignment3_CalculatorClient.java](Solution/Assignment3_CalculatorClient.java) | Lectures 13, 14, and 15 |
| Q4 | A system program that calculates date differences (10 points) | None | - |
| Q5 | A calculator that computes on the server (10 points) | None | - |

*Table 1. Questions and solution files of HW3*

The Q1 solution reads `Q1.txt` from the working directory and writes `Q1_output.txt` to the same directory. The distributed `Q1.txt` is not in this repository, so the program can be run by copying `Q1_Sample.txt` under the name `Q1.txt`, as below.

```bash
cd Coursework/HW03_Threads-and-Networking
mkdir run && cp Original/Q1_Sample.txt run/Q1.txt
javac -d run Solution/Assignment3_DnaFiveMerCounter.java
cd run && java Assignment3_DnaFiveMerCounter
```

For Q3, start the server first, then open several terminals and run a client in each. The server and the clients communicate over port 6000 on the same computer (`localhost`).

```bash
javac -d run Solution/Assignment3_CalculatorServer.java Solution/Assignment3_CalculatorClient.java
java -cp run Assignment3_CalculatorServer      # terminal 1
java -cp run Assignment3_CalculatorClient      # terminals 2, 3, ...
```

---

<br>

## 2. Requirements

Every program must follow the Javadoc comment and standard naming rules and must not end on incorrect input. The requirements of the two questions with solutions are as follows.

| Question | Requirements |
|:-----|:-----|
| Q1 | Using a five-letter window that moves one letter at a time over the DNA sequence in `Q1.txt`, find the frequencies of the 1,024 five-letter combinations of A, C, G, and T (AAAAA-TTTTT), save them in `Q1_output.txt`, and display the elapsed time in the command window. N may be ignored |
| Q3 | N users connect to a server and calculate jointly. The server's cumulative value is an int, and when a user causes an overflow, all users are told who caused it and the connection ends. Include exception handling, multithreading support, query time display on the server side, and guidance |

*Table 2. Requirements of the questions with solutions*

---

<br>

## 3. Q1: Frequencies of Five-Letter Combinations in a DNA Sequence

### 3.1 Representing a Five-Letter Combination as a Number

A subsequence of length $k$ is called a **k-mer**, and this question counts the frequencies of 5-mers. Building a string for each 5-mer and putting it in a `HashMap` would also count them, but building a string for each of about 200 million windows is slow. The solution represents each of the four bases as a 2-bit number and turns a 5-mer into a 10-bit integer that serves as an index into an `int` array of size 1,024.

| Base | A | C | G | T |
|:-----|:-----|:-----|:-----|:-----|
| Code | 0 (`00`) | 1 (`01`) | 2 (`10`) | 3 (`11`) |

*Table 3. 2-bit codes of the bases*

The index of a 5-mer $b_1 b_2 b_3 b_4 b_5$ is a base-4 number with the first base in the highest place.

```math
\mathrm{index} = c(b_1) \cdot 4^4 + c(b_2) \cdot 4^3 + c(b_3) \cdot 4^2 + c(b_4) \cdot 4 + c(b_5)
```

For example, AGAGA is $0 \cdot 256 + 2 \cdot 64 + 0 \cdot 16 + 2 \cdot 4 + 0 = 136$. The order A < C < G < T is the same as the codes 0 < 1 < 2 < 3, and the first base is in the highest place, so the order of the indices 0-1023 is the same as the lexicographic order from AAAAA to TTTTT. Writing the array from index 0 onward therefore produces the order in the handout. `indexToSequence` turns the index into bases starting from its lowest 2 bits and fills the string from the end.

```java
private static final int WINDOW_SIZE = 5;
private static final int TOTAL_MERS = 1 << (2 * WINDOW_SIZE); // 4^5 = 1024
private static final int MASK = (1 << (2 * (WINDOW_SIZE - 1))) - 1;
```

`TOTAL_MERS` is $2^{10} = 1024$, and `MASK` is $2^8 - 1 = 255$, a number whose lowest 8 bits are all 1.

### 3.2 Moving the Window One Letter at a Time

Moving the window by one letter drops the first base and brings in a new base at the end. For the index, this means discarding the highest 2 bits, shifting the rest 2 bits to the left, and putting the new code in the lowest 2 bits. A method like this, which computes the next value from the previous one in constant time, is called a **rolling hash**.

```math
\mathrm{index}' = \big( (\mathrm{index} \mathbin{\&} 255) \ll 2 \big) \mathbin{|} c_{\mathrm{new}}
```

In the solution this update is split into two statements. When handling the outgoing base, `rollingIndex &= MASK` discards the highest 2 bits, and when handling the incoming base, `rollingIndex = (rollingIndex << 2) | code` shifts it. The index changes over the sequence AGAGAGAT as follows.

| Step | Window | Computation | Index |
|:-----|:-----|:-----|-----:|
| First five letters | AGAGA | Shift in 0, 2, 0, 2, 0 in turn | 136 |
| G comes in | GAGAG | $(136 \mathbin{\&} 255) \ll 2 \mathbin{\vert} 2 = 546$ | 546 |
| A comes in | AGAGA | $(546 \mathbin{\&} 255) \ll 2 \mathbin{\vert} 0 = 34 \ll 2 = 136$ | 136 |
| T comes in | GAGAT | $(136 \mathbin{\&} 255) \ll 2 \mathbin{\vert} 3 = 547$ | 547 |

*Table 4. Changes of the rolling index over the sequence AGAGAGAT*

`counts[136]` increases twice and `counts[546]` and `counts[547]` once each, giving AGAGA: 2, GAGAG: 1, and GAGAT: 1, as in the handout's figure.

The file is read one byte at a time with `read()` of `BufferedInputStream`. Thanks to the buffer, the actual disk accesses happen in large chunks. `charToCode` returns -2 for newline characters, which are skipped, so the lines broken every 60 letters join into one long sequence. 5-mers whose window crosses a line boundary are counted as well.

```java
private static int charToCode(char ch) {
    return switch (Character.toUpperCase(ch)) {
        case 'A' -> 0; case 'C' -> 1; case 'G' -> 2; case 'T' -> 3;
        case 'N' -> -1; default -> -2;
    };
}
```

### 3.3 Windows Containing N

N is a position whose base is unknown. A window containing N is not a 5-mer of A, C, G, and T only, so it is not counted. The solution keeps the codes of the last five letters in the circular buffer `window`, along with the number of N in the window, `nCount`, and the flag `dirty`, which means the rolling index no longer matches the window.

```java
} else {
    int outgoing = window[wPos];
    if (outgoing == -1) { nCount--; }
    else if (nCount == 0 && !dirty) { rollingIndex &= MASK; }

    window[wPos] = code;
    if (code == -1) { nCount++; dirty = true; }
    else if (nCount == 0 && !dirty) { rollingIndex = (rollingIndex << 2) | code; }
    wPos = (wPos + 1) % WINDOW_SIZE;

    if (nCount == 0) {
        if (dirty) {
            rollingIndex = computeFullIndex(window, wPos);
            dirty = false;
        }
        counts[rollingIndex]++;
    }
}
```

- `wPos` is the position of the oldest letter in the circular buffer. The code at that position is the outgoing base (`outgoing`); the new code is written to the same position, and then `wPos` moves one step.
- If the window has even one N (`nCount > 0`), nothing is counted and the rolling index is not updated. `dirty` becomes true the moment an N comes in.
- When all N have left and `nCount` becomes 0, `dirty` is true, so `computeFullIndex` recomputes the index from scratch from the five codes in the circular buffer. After that, the rolling update is used again.

For example, in the sequence CANGTACG, the windows CANGT, ANGTA, and NGTAC contain N and are not counted. When the next letter G comes in and the N leaves, the buffer from its oldest position holds G, T, A, C, G, so the index of GTACG is recomputed and counted. While the first five letters are read, N is counted under the same rule, and the first window is counted only when all five letters are bases. The first 223 lines of the file are all N, so no 5-mer is counted during them.

### 3.4 Results

Running on the whole distributed `Q1.txt` (196,449,156 letters) gives the following. The elapsed time covers everything from starting to read the file to finishing writing `Q1_output.txt`, measured with Java 24 on an Apple M2 Pro.

```text
Completed in 1.847 seconds.
```

`Q1_output.txt` has 1,024 lines, and its first five and last two lines are as follows.

```text
AAAAA: 446516
AAAAC: 344621
AAAAG: 403003
AAAAT: 570057
AAACA: 436583
...
TTTTG: 404209
TTTTT: 453674
```

The sum of the 1,024 frequencies is 161,789,838, which equals the number of five-letter windows in the sequence that do not contain N. Running on `Q1_Sample.txt` finishes in about 0.01 seconds, the sum of the frequencies is 2,636, and the first three lines are `AAAAA: 6`, `AAAAC: 15`, and `AAAAG: 1`.

---

<br>

## 4. Q3: A Client-Server Program for Joint Calculation

### 4.1 Structure of the Server

The server opens a `ServerSocket` on port 6000, and the main thread repeatedly waits for connections with `accept()` (Lecture 14). Each time a client connects, it gets a name, `User1`, `User2`, ..., and a `ClientHandler` that serves only that client runs in a new thread (Lecture 13). The server can therefore process other users' commands while waiting for one user's input.

```java
private static final AtomicInteger currentValue = new AtomicInteger(0);
private static final CopyOnWriteArrayList<ClientHandler> handlers = new CopyOnWriteArrayList<>();
private static final AtomicInteger clientCount = new AtomicInteger(0);
```

```java
while (true) {
    Socket clientSocket = serverSocket.accept();
    String userName = "User" + clientCount.incrementAndGet();
    System.out.println("[" + now() + "] " + userName + " connected.");
    ClientHandler handler = new ClientHandler(clientSocket, userName);
    handlers.add(handler);
    new Thread(handler).start();
}
```

Three pieces of data are shared by several threads, and all of them use thread-safe classes (Lecture 15, Section 5).

- **Cumulative value `currentValue`:** It is an `AtomicInteger`, so the whole update of reading, computing, and writing can be done atomically (Section 4.2).
- **Handler list `handlers`:** While one thread walks the list to broadcast, another thread may add or remove a handler. `CopyOnWriteArrayList` copies its internal array on every change, so a thread in the middle of iterating safely walks the array it started with to the end. It suits a list like this, which is iterated far more often than it is changed.
- **Client number `clientCount`:** `incrementAndGet()` is atomic, so no two clients get the same number.

When created, a `ClientHandler` sends the client a line such as `ID:User1` to tell it its name, and in `run()` it reads and processes the lines the client sends one at a time. When it receives `exit` or the connection breaks and `readLine()` returns `null`, it ends the loop, and in `finally` it removes itself from the list and closes the socket.

### 4.2 Processing Commands and Detecting Overflow

A command consists of a one-character operator and an integer, such as `+10` or `*5`. `processCommand` first checks the format, and if something is wrong it sends a message starting with `[Declined]` to that client only.

| Input | Server Response |
|:-----|:-----|
| Fewer than two characters (for example, `+`) | `[Declined] Invalid input.` |
| First character is not `+`, `-`, `*`, or `/` (for example, `x5`) | `[Declined] Invalid operator.` |
| What follows the operator is not an integer in the int range (for example, `+abc`, `*100000000000`) | `[Declined] Invalid operand.` |
| `/0` | `[Declined] Cannot divide by zero.` |

*Table 5. Inputs rejected by the Q3 server*

If the format is correct, the cumulative value is updated. The key is to compute with `long` and decide whether the result left the int range.

```java
int newVal = currentValue.accumulateAndGet(operandVal, (oldVal, x) -> {
    long r = switch (operator) {
        case '+' -> (long) oldVal + x;
        case '-' -> (long) oldVal - x;
        case '*' -> (long) oldVal * x;
        case '/' -> (long) oldVal / x;
        default -> Long.MIN_VALUE;
    };
    if (r > Integer.MAX_VALUE || r < Integer.MIN_VALUE) {
        resultHolder[0] = 1; // overflow flag
        return oldVal; // keep old value on overflow
    }
    return (int) r;
});
```

- **Computing with long:** Multiplying two ints silently gives a truncated value when the result leaves the int range. Converting the operand to `long` first keeps the sum, difference, and product of two ints within the `long` range, so the exact result is obtained and can be compared with `Integer.MAX_VALUE` (2,147,483,647) and `Integer.MIN_VALUE` (-2,147,483,648). An overflow from division, such as `Integer.MIN_VALUE / -1` (result 2,147,483,648), is caught the same way.
- **Atomic update:** `accumulateAndGet` reads the current value, applies the function, and stores the result only if no other thread changed the value in the meantime (compare-and-set). If another thread changed it first, it tries again with the new value. So even when two users send commands at the same time, neither update is lost.
- **On overflow:** The function returns the previous value unchanged, so the cumulative value does not change, and the flag `resultHolder[0]` becomes 1. The server then broadcasts a message such as `**User2** caused an overflow.` to all handlers.

When the update succeeds, the server prints the time, the user, the command, and the new value on its screen (query time display) and sends `SUCCESS` to that client.

> **Note:** The API documentation of `accumulateAndGet` requires the accumulator function to be free of side effects, because the function is called again when compare-and-set fails due to concurrent updates by several threads. The solution's function writes 1 to `resultHolder[0]` on overflow, so if a first attempt is judged an overflow and the retried computation after another thread changed the value is not an overflow, the flag stays at 1 and an overflow is reported even though the value was updated. Clearing the flag with `resultHolder[0] = 0;` at the start of the function leaves only the judgment of the last attempt.

### 4.3 The Client

The client connects to `localhost:6000`, gets its name from the first line, `ID:User1`, and divides the work between two threads.

- **Main thread:** It prints the prompt `User1) `, reads keyboard input, and sends it to the server. After sending, it waits, sleeping 10 ms at a time, until the `waiting` flag becomes false, so the server's response is printed before the next prompt. Entering `exit` notifies the server and ends the client.
- **Receiving thread:** It keeps reading the lines the server sends. For `SUCCESS` it prints `[Accepted] Operation successful.`, prints any other message as it is, and sets `waiting` to false. If a line contains `caused an overflow`, it prints an `[Overflow]` message and ends the client with `System.exit(0)`.

The separate receiving thread exists because a broadcast can arrive at any time. Even if another user causes an overflow while this user is waiting at the keyboard, the receiving thread gets the message at once and ends the connection. `waiting` is shared by the two threads, so it is an `AtomicBoolean`. The receiving thread is made a daemon thread so that it ends together with the main thread.

### 4.4 Results

The following is an example of starting the server and connecting two clients one after the other. User 1 sends `/0`, `+10`, and `*10`, making the value 100, and then user 2 connects and sends `x5`, `*100000000000`, `-5`, and `*100000000`. The user's input appears after each prompt.

```text
Connected to server on port 6000.
Welcome to the Real-time Calculator.
Enter: <operator><integer> (e.g., +10, *5, /3)

User1) /0
[Declined] Cannot divide by zero.

User1) +10
[Accepted] Operation successful.

User1) *10
[Accepted] Operation successful.

User1) 
[Overflow] **User2** caused an overflow.
```

The screen of user 2 is as follows (the three connection lines are the same and are omitted).

```text
User2) x5
[Declined] Invalid operator.

User2) *100000000000
[Declined] Invalid operand.

User2) -5
[Accepted] Operation successful.

User2) *100000000

[Overflow] **User2** caused an overflow.
```

The screen of the server is as follows.

```text
[2026/11/16 14:39:34] Server started on port 6000. Value = 0.
[2026/11/16 14:39:35] User1 connected.
[2026/11/16 14:39:35] [User1] +10 -> value=10
[2026/11/16 14:39:35] [User1] *10 -> value=100
[2026/11/16 14:39:37] User2 connected.
[2026/11/16 14:39:37] [User2] -5 -> value=95
```

The last command gives $95 \times 10^8 = 9{,}500{,}000{,}000$, which exceeds the int range, so the value stays at 95, both users are told that `User2` caused it, and both clients end. The server does not log rejected commands or overflows on its screen, and it keeps running after the connections close, accepting new clients.

> **Note:** In the handout's example, user N enters `*100000000000` and an overflow occurs, but this solution also reads the operand with `Integer.parseInt`, so it rejects an operand beyond the int range with `[Declined] Invalid operand.`. Overflow is therefore detected, as in the run above, when a computation with an operand within the int range produces a result beyond the int range. Reading the operand with `Long.parseLong` and comparing only the result with the int range would behave like the handout's example.

---

<br>

## Summary

| Concept | Key Summary |
|:-----|:-----|
| Index of a 5-mer | Turning bases into 2-bit codes (A 0, C 1, G 2, T 3) and forming a 10-bit integer with the first base in the highest place makes the index order the same as the lexicographic order of AAAAA-TTTTT. |
| Rolling hash | The next index is `((index & 255) << 2) \| code`, computed in constant time, which is enough to count while reading the file only once. |
| Handling N | A circular buffer and a count of N skip the windows containing N, and when all N have left, the index is recomputed from the five codes. |
| Server threads | The main thread repeats `accept()`, and a `ClientHandler` thread per client processes commands. |
| Shared data | The cumulative value uses `AtomicInteger`, the handler list `CopyOnWriteArrayList`, and the client number `incrementAndGet()` for thread safety. |
| Detecting overflow | The result computed in `long` is compared with the maximum and minimum of int, and if it leaves the range, the value is kept and the responsible user is broadcast to all clients. |
| The client's two threads | The main thread sends input, and the receiving thread receives responses and broadcasts, ending the client when an overflow message arrives. |

---

<br>

## Self-Check Questions

1. **Computing an index:** What is the index of the 5-mer TACGA, and on which line of `Q1_output.txt` does it appear?

   <details>
   <summary>Answer</summary>

   The codes are 3, 0, 1, 2, 0, so the index is $3 \cdot 256 + 0 \cdot 64 + 1 \cdot 16 + 2 \cdot 4 + 0 = 792$. Index 0 is the first line, so it appears on line 793.

   </details>

2. **Rolling update:** If the current window is GAGAT (index 547) and the next letter is C, what is the new index, and which 5-mer is it?

   <details>
   <summary>Answer</summary>

   $547 \mathbin{\&} 255 = 35$, $35 \ll 2 = 140$, and $140 \mathbin{\vert} 1 = 141$. Since 141 = $0 \cdot 256 + 2 \cdot 64 + 0 \cdot 16 + 3 \cdot 4 + 1$, it is AGATC.

   </details>

3. **Number of windows skipped for N:** If a sequence of length $L$ has a single N in its middle, how many windows are not counted ($`L \ge 9`$, and the N is at least 4 letters from both ends)?

   <details>
   <summary>Answer</summary>

   The five-letter windows containing the N are the five with the N in the first through fifth positions. Removing these 5 from the $L - 4$ windows in total leaves $L - 9$ windows to count.

   </details>

4. **Detecting overflow:** When the cumulative value is 2,000,000,000, how does the Q3 server respond to `+200000000`, `-200000000`, and `*-1`, each sent separately?

   <details>
   <summary>Answer</summary>

   `+200000000` gives 2,200,000,000, which exceeds `Integer.MAX_VALUE`, so the value is kept and an overflow is broadcast. `-200000000` gives 1,800,000,000, within the range, so the response is `SUCCESS`. `*-1` gives -2,000,000,000, which is greater than `Integer.MIN_VALUE`, so it is also `SUCCESS`.

   </details>

5. **CopyOnWriteArrayList:** What problems can arise if the handler list is changed to an ordinary `ArrayList`?

   <details>
   <summary>Answer</summary>

   If another thread changes the list through a connection or disconnection while one thread is broadcasting with `for (ClientHandler h : handlers)`, the iterator can detect the change and throw `ConcurrentModificationException`. In addition, `ArrayList` is not synchronized, so two threads adding at the same time can corrupt its internal state, for example by losing an element.

   </details>
