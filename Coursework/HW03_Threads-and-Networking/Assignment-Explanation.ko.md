# HW3 과제 해설 — 스레드와 네트워킹

[레포지토리 안내](../../README.ko.md) | [English](Assignment-Explanation.md) | [한국어](Assignment-Explanation.ko.md)

> **최종 수정일:** 2026-11-16

> **학습 목표:**
> 1. 큰 텍스트 파일을 한 번 읽으면서 고정 길이 부분 문자열의 빈도를 효율적으로 셀 수 있다
> 2. 빈도를 세는 동안 세지 않아야 할 문자가 들어간 구간을 올바르게 건너뛸 수 있다
> 3. 여러 클라이언트가 하나의 값을 함께 갱신하는 서버에서 스레드 안전성과 overflow를 다룰 수 있다
> 4. 소켓 기반 클라이언트-서버 프로그램에서 메시지가 오가는 흐름을 설명할 수 있다

---

## 목차

- [1. 과제 구성과 실행 방법](#1-과제-구성과-실행-방법)
- [2. 요구사항](#2-요구사항)
- [3. Q1: DNA 서열의 5글자 조합 빈도](#3-q1-dna-서열의-5글자-조합-빈도)
  - [3.1 5글자 조합을 수로 나타내기](#31-5글자-조합을-수로-나타내기)
  - [3.2 윈도를 한 글자씩 옮기기](#32-윈도를-한-글자씩-옮기기)
  - [3.3 N이 들어간 윈도](#33-n이-들어간-윈도)
  - [3.4 실행 결과](#34-실행-결과)
- [4. Q3: 공동 계산 클라이언트-서버 프로그램](#4-q3-공동-계산-클라이언트-서버-프로그램)
  - [4.1 서버의 구성](#41-서버의-구성)
  - [4.2 명령 처리와 overflow 판정](#42-명령-처리와-overflow-판정)
  - [4.3 클라이언트](#43-클라이언트)
  - [4.4 실행 결과](#44-실행-결과)
- [요약](#요약)
- [점검 문제](#점검-문제)

---

<br>

## 1. 과제 구성과 실행 방법

> **풀어 보기:** 이 문서는 정답과 풀이를 담고 있다. 먼저 [과제 안내문](Original/Assignment.ko.md)의 문제를 직접 풀어 본 뒤 읽는다.

대용량 DNA 서열의 5글자 조합 빈도 계산과 그 병렬 처리, 여러 사용자가 접속하는 클라이언트-서버 프로그램을 다루는 다섯 문제의 과제이다. 문제의 원문과 예시 화면은 [과제 안내문](Original/Assignment.ko.md)에 있다. 이 문서는 풀이가 있는 Q1과 Q3를 다루며, 나머지 문제의 풀이는 이 레포지토리에 없다.

```text
Coursework/HW03_Threads-and-Networking/
├── Original/
│   ├── Assignment.ko.md                     # 과제 안내문 (한국어)
│   ├── Assignment.md                        # 과제 안내문 (영어)
│   └── Q1_Sample.txt                        # Q1.txt의 앞부분 300줄
├── Solution/
│   ├── Assignment3_DnaFiveMerCounter.java   # Q1
│   ├── Assignment3_CalculatorServer.java    # Q3 서버
│   └── Assignment3_CalculatorClient.java    # Q3 클라이언트
├── Assignment-Explanation.ko.md             # 과제 해설 (한국어)
└── Assignment-Explanation.md                # 과제 해설 (영어)
```

*그림 1. HW3 과제의 디렉토리 구성*

| 문제 | 내용 | 풀이 파일 | 관련 단원 |
|:-----|:-----|:-----|:-----|
| Q1 | DNA 서열의 5글자 조합 빈도 (20점) | [Assignment3_DnaFiveMerCounter.java](Solution/Assignment3_DnaFiveMerCounter.java) | 10강 |
| Q2 | 병렬 처리로 연산 속도 높이기 (30점) | 없음 | - |
| Q3 | 공동 계산 클라이언트-서버 프로그램 (30점) | [Assignment3_CalculatorServer.java](Solution/Assignment3_CalculatorServer.java), [Assignment3_CalculatorClient.java](Solution/Assignment3_CalculatorClient.java) | 13강, 14강, 15강 |
| Q4 | 날짜 차이를 계산하는 시스템 프로그램 (10점) | 없음 | - |
| Q5 | 서버에서 계산하는 계산기 (10점) | 없음 | - |

*표 1. HW3의 문제와 풀이 파일*

Q1 풀이는 작업 디렉토리의 `Q1.txt`를 읽고 같은 디렉토리에 `Q1_output.txt`를 쓴다. 배포된 `Q1.txt`는 이 레포지토리에 없으므로, 아래처럼 `Q1_Sample.txt`를 `Q1.txt`라는 이름으로 복사해 실행할 수 있다.

```bash
cd Coursework/HW03_Threads-and-Networking
mkdir run && cp Original/Q1_Sample.txt run/Q1.txt
javac -d run Solution/Assignment3_DnaFiveMerCounter.java
cd run && java Assignment3_DnaFiveMerCounter
```

Q3는 서버를 먼저 실행한 뒤, 터미널을 여러 개 열어 각각 클라이언트를 실행한다. 서버와 클라이언트는 같은 컴퓨터(`localhost`)의 포트 6000으로 통신한다.

```bash
javac -d run Solution/Assignment3_CalculatorServer.java Solution/Assignment3_CalculatorClient.java
java -cp run Assignment3_CalculatorServer      # 터미널 1
java -cp run Assignment3_CalculatorClient      # 터미널 2, 3, ...
```

---

<br>

## 2. 요구사항

모든 프로그램은 Javadoc 주석과 표준 명명 규칙을 지키고, 잘못된 입력에도 종료되지 않아야 한다. 풀이가 있는 두 문제의 요구사항은 다음과 같다.

| 문제 | 요구사항 |
|:-----|:-----|
| Q1 | `Q1.txt`의 DNA 서열에서 한 글자씩 이동하는 5글자 윈도로 A, C, G, T의 5글자 조합 1,024가지(AAAAA\~TTTTT)의 빈도를 구해 `Q1_output.txt`에 저장하고, 명령 창에 경과 시간을 표시한다. N은 무시해도 된다 |
| Q3 | N명의 사용자가 서버에 접속해 공동으로 계산한다. 서버의 누적 값은 int형이며, overflow를 일으킨 사용자가 생기면 모든 사용자에게 원인이 된 사용자를 알리고 접속을 끝낸다. 예외 처리, 멀티스레드 지원, 서버 측의 쿼리 시간 표시와 안내를 포함한다 |

*표 2. 풀이가 있는 문제의 요구사항*

---

<br>

## 3. Q1: DNA 서열의 5글자 조합 빈도

### 3.1 5글자 조합을 수로 나타내기

길이가 $k$인 부분 서열을 **k-mer**라 부르며, 이 문제는 5-mer의 빈도를 센다. 5-mer마다 문자열을 만들어 `HashMap`에 넣어도 셀 수 있지만, 약 2억 개의 윈도마다 문자열을 만들면 느리다. 풀이는 염기 네 가지를 2비트 수로 나타내고, 5-mer를 10비트 정수로 바꾸어 크기 1,024인 `int` 배열의 인덱스로 쓴다.

| 염기 | A | C | G | T |
|:-----|:-----|:-----|:-----|:-----|
| 코드 | 0 (`00`) | 1 (`01`) | 2 (`10`) | 3 (`11`) |

*표 3. 염기의 2비트 코드*

5-mer $b_1 b_2 b_3 b_4 b_5$의 인덱스는 첫 염기를 가장 높은 자리에 둔 4진수이다.

```math
\mathrm{index} = c(b_1) \cdot 4^4 + c(b_2) \cdot 4^3 + c(b_3) \cdot 4^2 + c(b_4) \cdot 4 + c(b_5)
```

예를 들어 AGAGA는 $0 \cdot 256 + 2 \cdot 64 + 0 \cdot 16 + 2 \cdot 4 + 0 = 136$이다. A < C < G < T의 순서가 코드 0 < 1 < 2 < 3과 같고 첫 염기가 가장 높은 자리이므로, 인덱스 0\~1023의 순서는 AAAAA부터 TTTTT까지의 사전순과 같다. 그래서 결과를 쓸 때 배열을 0번부터 차례로 출력하면 안내문의 순서가 된다. `indexToSequence`는 인덱스의 낮은 2비트부터 염기로 바꾸어 문자열의 뒤에서부터 채운다.

```java
private static final int WINDOW_SIZE = 5;
private static final int TOTAL_MERS = 1 << (2 * WINDOW_SIZE); // 4^5 = 1024
private static final int MASK = (1 << (2 * (WINDOW_SIZE - 1))) - 1;
```

`TOTAL_MERS`는 $2^{10} = 1024$이고, `MASK`는 $2^8 - 1 = 255$, 즉 낮은 8비트가 모두 1인 수이다.

### 3.2 윈도를 한 글자씩 옮기기

윈도를 한 글자 옮기면 맨 앞 염기가 빠지고 맨 뒤에 새 염기가 들어온다. 인덱스로는 가장 높은 2비트를 버리고, 나머지를 2비트 왼쪽으로 민 뒤 새 코드를 낮은 2비트에 넣으면 된다. 이처럼 앞의 값으로 다음 값을 상수 시간에 구하는 방법을 **롤링 해시(rolling hash)** 라 한다.

```math
\mathrm{index}' = \big( (\mathrm{index} \mathbin{\&} 255) \ll 2 \big) \mathbin{|} c_{\mathrm{new}}
```

풀이에서 이 갱신은 두 문장으로 나뉜다. 빠지는 염기를 처리할 때 `rollingIndex &= MASK`로 높은 2비트를 버리고, 들어오는 염기를 처리할 때 `rollingIndex = (rollingIndex << 2) | code`로 민다. 서열 AGAGAGAT에서 인덱스가 바뀌는 모습은 다음과 같다.

| 단계 | 윈도 | 계산 | 인덱스 |
|:-----|:-----|:-----|-----:|
| 처음 다섯 글자 | AGAGA | 0, 2, 0, 2, 0을 차례로 밀어 넣음 | 136 |
| G가 들어옴 | GAGAG | $(136 \mathbin{\&} 255) \ll 2 \mathbin{\vert} 2 = 546$ | 546 |
| A가 들어옴 | AGAGA | $(546 \mathbin{\&} 255) \ll 2 \mathbin{\vert} 0 = 34 \ll 2 = 136$ | 136 |
| T가 들어옴 | GAGAT | $(136 \mathbin{\&} 255) \ll 2 \mathbin{\vert} 3 = 547$ | 547 |

*표 4. 서열 AGAGAGAT에서 롤링 인덱스의 변화*

`counts[136]`이 두 번, `counts[546]`과 `counts[547]`이 한 번씩 늘어나므로 AGAGA: 2, GAGAG: 1, GAGAT: 1로 안내문의 그림과 같다.

파일은 `BufferedInputStream`의 `read()`로 한 바이트씩 읽는다. 버퍼 덕분에 실제 디스크 접근은 큰 묶음 단위로 일어난다. 줄바꿈 문자는 `charToCode`가 -2를 돌려주어 건너뛰므로, 60글자마다 끊긴 줄들이 하나의 긴 서열로 이어진다. 윈도가 줄 경계를 넘어가는 5-mer도 그대로 센다.

```java
private static int charToCode(char ch) {
    return switch (Character.toUpperCase(ch)) {
        case 'A' -> 0; case 'C' -> 1; case 'G' -> 2; case 'T' -> 3;
        case 'N' -> -1; default -> -2;
    };
}
```

### 3.3 N이 들어간 윈도

N은 염기를 알 수 없는 자리이다. N이 들어간 윈도는 A, C, G, T만의 5-mer가 아니므로 세지 않는다. 풀이는 최근 다섯 글자의 코드를 원형 버퍼 `window`에 두고, 윈도 안의 N 개수 `nCount`와, 롤링 인덱스가 윈도와 맞지 않게 되었음을 뜻하는 플래그 `dirty`를 유지한다.

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

- `wPos`는 원형 버퍼에서 가장 오래된 글자의 자리이다. 그 자리의 코드가 빠지는 염기(`outgoing`)이고, 같은 자리에 새 코드를 쓴 뒤 `wPos`를 한 칸 옮긴다.
- 윈도에 N이 하나라도 있으면(`nCount > 0`) 세지 않고, 롤링 인덱스도 갱신하지 않는다. N이 들어오는 순간 `dirty`가 참이 된다.
- N이 모두 빠져 `nCount`가 0이 되면 `dirty`가 참이므로, `computeFullIndex`가 원형 버퍼의 다섯 코드로 인덱스를 처음부터 다시 계산한다. 그 뒤로는 다시 롤링 갱신을 쓴다.

예를 들어 서열 CANGTACG에서 CANGT, ANGTA, NGTAC는 N이 들어 있어 세지 않는다. 다음 글자 G가 들어오면서 N이 빠지면 버퍼는 가장 오래된 자리부터 G, T, A, C, G이므로 GTACG의 인덱스를 새로 계산해 센다. 처음 다섯 글자를 읽는 동안에도 같은 규칙으로 N을 세고, 다섯 글자가 모두 염기일 때만 첫 윈도를 센다. 파일 맨 앞의 223줄은 N뿐이므로, 그동안은 아무 5-mer도 세지 않는다.

### 3.4 실행 결과

배포된 `Q1.txt`(196,449,156글자) 전체로 실행한 결과는 다음과 같다. 경과 시간은 파일을 읽기 시작해 `Q1_output.txt`를 다 쓸 때까지를 잰 것이며, Java 24와 Apple M2 Pro에서 측정한 값이다.

```text
Completed in 1.847 seconds.
```

`Q1_output.txt`는 1,024줄이며 앞의 다섯 줄과 마지막 두 줄은 다음과 같다.

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

1,024개 빈도의 합은 161,789,838로, 서열에서 N이 들어가지 않은 5글자 윈도의 수와 같다. `Q1_Sample.txt`로 실행하면 약 0.01초 만에 끝나며, 빈도의 합은 2,636이고 첫 세 줄은 `AAAAA: 6`, `AAAAC: 15`, `AAAAG: 1`이다.

---

<br>

## 4. Q3: 공동 계산 클라이언트-서버 프로그램

### 4.1 서버의 구성

서버는 포트 6000에 `ServerSocket`을 열고, main 스레드가 `accept()`로 접속을 기다리는 일을 반복한다(14강). 클라이언트가 접속할 때마다 `User1`, `User2`, ... 이름을 붙이고, 그 클라이언트만 맡는 `ClientHandler`를 새 스레드에서 실행한다(13강). 그래서 한 사용자의 입력을 기다리는 동안에도 다른 사용자의 명령을 처리할 수 있다.

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

여러 스레드가 함께 쓰는 자료는 세 가지이며, 모두 스레드에 안전한 클래스를 쓴다(15강 5절).

- **누적 값 `currentValue`:** `AtomicInteger`이므로 읽고, 계산하고, 쓰는 갱신 전체를 원자적으로 할 수 있다(4.2절).
- **핸들러 목록 `handlers`:** 한 스레드가 목록을 돌며 방송하는 동안 다른 스레드가 핸들러를 넣거나 뺄 수 있다. `CopyOnWriteArrayList`는 변경할 때마다 내부 배열을 복사하므로, 순회 중인 스레드는 시작할 때의 배열을 끝까지 안전하게 돈다. 변경보다 순회가 훨씬 잦은 이런 목록에 알맞다.
- **접속자 번호 `clientCount`:** `incrementAndGet()`이 원자적이므로 두 클라이언트가 같은 번호를 받지 않는다.

`ClientHandler`는 생성될 때 클라이언트에 `ID:User1` 같은 줄을 보내 이름을 알려 주고, `run()`에서 클라이언트가 보낸 줄을 하나씩 읽어 처리한다. `exit`를 받거나 연결이 끊겨 `readLine()`이 `null`을 돌려주면 반복을 끝내고, `finally`에서 목록에서 자신을 빼고 소켓을 닫는다.

### 4.2 명령 처리와 overflow 판정

명령은 `+10`, `*5`처럼 연산자 한 글자와 정수로 이루어진다. `processCommand`는 먼저 형식을 검사하고, 문제가 있으면 `[Declined]`로 시작하는 안내를 그 클라이언트에만 보낸다.

| 입력 | 서버의 응답 |
|:-----|:-----|
| 두 글자 미만 (예: `+`) | `[Declined] Invalid input.` |
| 첫 글자가 `+`, `-`, `*`, `/`가 아님 (예: `x5`) | `[Declined] Invalid operator.` |
| 연산자 뒤가 int 범위의 정수가 아님 (예: `+abc`, `*100000000000`) | `[Declined] Invalid operand.` |
| `/0` | `[Declined] Cannot divide by zero.` |

*표 5. Q3 서버가 거절하는 입력*

형식이 맞으면 누적 값을 갱신한다. 핵심은 계산을 `long`으로 해서 int 범위를 넘었는지 판정하는 것이다.

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

- **long으로 계산하기:** int끼리 곱하면 결과가 int 범위를 넘을 때 조용히 잘린 값이 나온다. 피연산자를 먼저 `long`으로 바꾸면 두 int의 합, 차, 곱이 모두 `long` 범위 안에 들어가므로 정확한 결과를 얻고, 그 값을 `Integer.MAX_VALUE`(2,147,483,647), `Integer.MIN_VALUE`(-2,147,483,648)와 비교할 수 있다. `Integer.MIN_VALUE / -1`(결과 2,147,483,648)처럼 나눗셈에서 생기는 overflow도 같은 방법으로 잡힌다.
- **원자적 갱신:** `accumulateAndGet`은 현재 값을 읽어 함수를 적용하고, 그동안 다른 스레드가 값을 바꾸지 않았을 때만 결과를 저장한다(compare-and-set). 다른 스레드가 먼저 바꾸었으면 새 값으로 다시 시도한다. 그래서 두 사용자가 동시에 명령을 보내도 한쪽의 갱신이 사라지지 않는다.
- **overflow일 때:** 함수가 이전 값을 그대로 돌려주어 누적 값은 바뀌지 않고, 플래그 `resultHolder[0]`이 1이 된다. 그러면 서버는 `**User2** caused an overflow.` 같은 메시지를 모든 핸들러에 방송한다.

갱신에 성공하면 서버 화면에 시각, 사용자, 명령, 새 값을 출력하고(쿼리 시간 표시), 그 클라이언트에 `SUCCESS`를 보낸다.

> **참고:** `accumulateAndGet`의 API 문서는 누적 함수가 부수 효과 없이 작성되어야 한다고 요구한다. 여러 스레드가 동시에 갱신해 compare-and-set이 실패하면 함수를 다시 호출하기 때문이다. 풀이의 함수는 overflow일 때 `resultHolder[0]`에 1을 쓰므로, 첫 시도에서 overflow로 판정된 뒤 다른 스레드가 값을 바꾸어 다시 시도한 계산이 overflow가 아니면, 값은 갱신되었는데도 플래그가 1로 남아 overflow를 알리게 된다. 함수의 첫머리에서 `resultHolder[0] = 0;`으로 플래그를 지우면 마지막 시도의 판정만 남는다.

### 4.3 클라이언트

클라이언트는 `localhost:6000`에 접속해 첫 줄 `ID:User1`에서 이름을 얻고, 두 스레드로 일을 나눈다.

- **main 스레드:** `User1) ` 프롬프트를 출력하고 키보드 입력을 읽어 서버로 보낸다. 보낸 뒤에는 `waiting` 플래그가 거짓이 될 때까지 10 ms씩 쉬며 기다리므로, 서버의 응답이 다음 프롬프트보다 먼저 출력된다. `exit`를 입력하면 서버에 알리고 끝낸다.
- **수신 스레드:** 서버가 보내는 줄을 계속 읽는다. `SUCCESS`이면 `[Accepted] Operation successful.`을, 그 밖의 안내는 그대로 출력하고 `waiting`을 거짓으로 바꾼다. 줄에 `caused an overflow`가 들어 있으면 `[Overflow]` 안내를 출력하고 `System.exit(0)`으로 클라이언트를 끝낸다.

수신 스레드를 따로 둔 이유는 방송 메시지가 언제 올지 모르기 때문이다. 사용자가 키보드 입력을 기다리는 동안 다른 사용자가 overflow를 일으켜도, 수신 스레드가 곧바로 메시지를 받아 접속을 끝낸다. `waiting`은 두 스레드가 함께 쓰므로 `AtomicBoolean`이다. 수신 스레드는 데몬 스레드로 만들어, main 스레드가 끝나면 함께 끝난다.

### 4.4 실행 결과

서버를 띄운 뒤 클라이언트 두 개를 차례로 접속시켜 실행한 예이다. 사용자 1이 `/0`, `+10`, `*10`을 보내 값이 100이 된 뒤, 사용자 2가 접속해 `x5`, `*100000000000`, `-5`, `*100000000`을 보낸다. 사용자의 입력은 프롬프트 뒤에 보인다.

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

사용자 2의 화면은 다음과 같다(접속 안내 세 줄은 같으므로 생략한다).

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

서버의 화면은 다음과 같다.

```text
[2026/11/16 14:39:34] Server started on port 6000. Value = 0.
[2026/11/16 14:39:35] User1 connected.
[2026/11/16 14:39:35] [User1] +10 -> value=10
[2026/11/16 14:39:35] [User1] *10 -> value=100
[2026/11/16 14:39:37] User2 connected.
[2026/11/16 14:39:37] [User2] -5 -> value=95
```

마지막 명령은 $95 \times 10^8 = 9{,}500{,}000{,}000$으로 int 범위를 넘으므로 값은 95로 남고, 두 사용자 모두에게 원인이 된 `User2`가 알려진 뒤 두 클라이언트가 끝난다. 서버는 거절한 명령과 overflow를 화면에 기록하지 않고, 접속이 끊긴 뒤에도 계속 실행되어 새 클라이언트를 받는다.

> **참고:** 안내문의 예시에서는 사용자 N이 `*100000000000`을 입력해 overflow가 일어나지만, 이 풀이는 피연산자도 `Integer.parseInt`로 읽으므로 int 범위를 넘는 피연산자를 `[Declined] Invalid operand.`로 거절한다. 그래서 위 실행처럼 int 범위 안의 피연산자로 계산한 결과가 int 범위를 넘을 때 overflow가 판정된다. 피연산자를 `Long.parseLong`으로 읽고 결과만 int 범위와 비교하면 안내문의 예시처럼 동작한다.

---

<br>

## 요약

| 개념 | 핵심 요약 |
|:-----|:-----|
| 5-mer의 인덱스 | 염기를 2비트 코드(A 0, C 1, G 2, T 3)로 바꾸어 첫 염기를 가장 높은 자리에 둔 10비트 정수로 만들면, 인덱스 순서가 AAAAA\~TTTTT의 사전순과 같다. |
| 롤링 해시 | 다음 인덱스는 `((index & 255) << 2) \| code`로 상수 시간에 구하므로 파일을 한 번만 읽고 세는 데 충분하다. |
| N의 처리 | 원형 버퍼와 N 개수로 N이 들어간 윈도를 건너뛰고, N이 모두 빠지면 인덱스를 다섯 코드로 다시 계산한다. |
| 서버의 스레드 | main 스레드는 `accept()`를 반복하고, 클라이언트마다 `ClientHandler` 스레드가 명령을 처리한다. |
| 공유 자료 | 누적 값은 `AtomicInteger`, 핸들러 목록은 `CopyOnWriteArrayList`, 접속자 번호는 `incrementAndGet()`으로 스레드 안전성을 확보한다. |
| overflow 판정 | `long`으로 계산한 결과를 int의 최댓값, 최솟값과 비교하고, 넘으면 값을 유지한 채 원인 사용자를 모든 클라이언트에 방송한다. |
| 클라이언트의 두 스레드 | main 스레드는 입력을 보내고, 수신 스레드는 응답과 방송을 받아 overflow 안내가 오면 클라이언트를 끝낸다. |

---

<br>

## 점검 문제

1. **인덱스 계산:** 5-mer TACGA의 인덱스는 얼마이며, `Q1_output.txt`의 몇 번째 줄에 있는가?

   <details>
   <summary>정답</summary>

   코드는 3, 0, 1, 2, 0이므로 $3 \cdot 256 + 0 \cdot 64 + 1 \cdot 16 + 2 \cdot 4 + 0 = 792$이다. 인덱스 0이 첫 줄이므로 793번째 줄에 있다.

   </details>

2. **롤링 갱신:** 현재 윈도가 GAGAT(인덱스 547)이고 다음 글자가 C이면 새 인덱스는 얼마이며, 어떤 5-mer인가?

   <details>
   <summary>정답</summary>

   $547 \mathbin{\&} 255 = 35$이고, $35 \ll 2 = 140$, $140 \mathbin{\vert} 1 = 141$이다. 141 = $0 \cdot 256 + 2 \cdot 64 + 0 \cdot 16 + 3 \cdot 4 + 1$이므로 AGATC이다.

   </details>

3. **N을 건너뛰는 윈도의 수:** 길이 $L$인 서열의 한가운데에 N이 하나만 있으면 세지 않는 윈도는 몇 개인가($`L \ge 9`$이고 N은 양 끝에서 4글자 이상 떨어져 있다)?

   <details>
   <summary>정답</summary>

   N을 포함하는 5글자 윈도는 N이 첫째부터 다섯째 자리에 오는 다섯 개이다. 전체 윈도 $L - 4$개 가운데 5개를 빼므로 $L - 9$개를 센다.

   </details>

4. **overflow 판정:** 누적 값이 2,000,000,000일 때 `+200000000`, `-200000000`, `*-1`을 각각 보내면 Q3 서버는 어떻게 응답하는가?

   <details>
   <summary>정답</summary>

   `+200000000`은 2,200,000,000으로 `Integer.MAX_VALUE`를 넘으므로 값이 유지되고 overflow가 방송된다. `-200000000`은 1,800,000,000으로 범위 안이므로 `SUCCESS`이다. `*-1`은 -2,000,000,000으로 `Integer.MIN_VALUE`보다 크므로 역시 `SUCCESS`이다.

   </details>

5. **CopyOnWriteArrayList:** 핸들러 목록을 보통의 `ArrayList`로 바꾸면 어떤 문제가 생길 수 있는가?

   <details>
   <summary>정답</summary>

   한 스레드가 `for (ClientHandler h : handlers)`로 방송하는 동안 다른 스레드가 접속이나 종료로 목록을 바꾸면, 반복자가 변경을 감지해 `ConcurrentModificationException`을 던질 수 있다. 또 `ArrayList`는 동기화되지 않아 두 스레드가 동시에 넣으면 원소가 사라지는 등 내부 상태가 깨질 수 있다.

   </details>
