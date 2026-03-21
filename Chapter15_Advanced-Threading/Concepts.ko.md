# Chapter 15 -- 고급 스레딩

> **최종 수정일:** 2026-03-21

---

## 목차

- [1. 스레드 제어 블록과 상태](#1-스레드-제어-블록과-상태)
- [2. 스레드 우선순위와 스케줄링](#2-스레드-우선순위와-스케줄링)
- [3. 스레드 종료](#3-스레드-종료)
  - [3.1 자연 종료](#31-자연-종료)
  - [3.2 interrupt() 메서드](#32-interrupt-메서드)
  - [3.3 플래그 기반 종료](#33-플래그-기반-종료)
- [4. 스레드 동기화](#4-스레드-동기화)
  - [4.1 synchronized 키워드](#41-synchronized-키워드)
  - [4.2 동기화 블록 vs. 동기화 메서드](#42-동기화-블록-vs-동기화-메서드)
  - [4.3 wait(), notify(), notifyAll()](#43-wait-notify-notifyall)
- [5. 경쟁 조건과 예방](#5-경쟁-조건과-예방)
- [요약](#요약)

---

<br>

## 1. 스레드 제어 블록과 상태

각 스레드는 다음 정보를 포함하는 스레드 제어 블록(TCB)으로 관리된다:

| 필드 | 설명 |
|:------|:------------|
| Thread ID | 고유 식별자 |
| Thread State | 현재 생명 주기 상태 |
| Program Counter | 다음 명령어의 주소 |
| Registers | 저장된 CPU 레지스터 값 |
| Stack Pointer | 스레드의 스택을 가리킴 |
| Priority | 스케줄링 우선순위 (1--10) |

---

<br>

## 2. 스레드 우선순위와 스케줄링

```java
thread.setPriority(Thread.MAX_PRIORITY);  // 10
thread.setPriority(Thread.MIN_PRIORITY);  // 1
thread.setPriority(Thread.NORM_PRIORITY); // 5 (기본값)
```

- 스레드 우선순위는 JVM과 OS 스케줄러에 대한 힌트이다. 우선순위 동작은 플랫폼에 따라 다르며, 높은 우선순위의 스레드가 일반적으로 선호되지만 엄격한 선점이 보장되지는 않는다.
- 동일한 우선순위의 스레드는 라운드 로빈 방식으로 스케줄링된다.
- 메인 스레드는 우선순위 5로 시작한다.

---

<br>

## 3. 스레드 종료

### 3.1 자연 종료

`run()` 메서드가 반환되면 스레드가 종료된다:

```java
public void run() {
    for (int i = 0; i < 100; i++) {
        System.out.println(i);
    }
    // 스레드가 여기서 종료됨
}
```

### 3.2 interrupt() 메서드

```java
TimerThread th = new TimerThread();
th.start();
// ...
th.interrupt(); // 슬립 중인 스레드에서 InterruptedException 발생
```

### 3.3 플래그 기반 종료

```java
class WorkerThread extends Thread {
    private volatile boolean finished = false;

    public void finish() { finished = true; }

    @Override
    public void run() {
        while (!finished) {
            // 작업 수행
            try { Thread.sleep(100); }
            catch (InterruptedException e) { return; }
        }
    }
}
```

> **핵심 요점:** 스레드 간 가시성을 보장하기 위해 플래그 변수에 `volatile`을 사용해야 한다.

---

<br>

## 4. 스레드 동기화

### 4.1 synchronized 키워드

한 번에 하나의 스레드만 임계 영역을 실행할 수 있도록 보장한다:

```java
synchronized void add() {
    int n = getCurrentSum();
    n += 10;
    setCurrentSum(n);
}
```

### 4.2 동기화 블록 vs. 동기화 메서드

```java
// 동기화 메서드 -- 메서드 전체를 잠금
synchronized void method() { /* ... */ }

// 동기화 블록 -- 임계 영역만 잠금
void method() {
    // 비임계 코드
    synchronized (this) {
        // 임계 영역
    }
    // 비임계 코드
}
```

> **핵심 요점:** 잠금 범위를 최소화하기 위해 동기화 메서드보다 동기화 블록을 사용하는 것이 바람직하다.

### 4.3 wait(), notify(), notifyAll()

| 메서드 | 설명 |
|:-------|:------------|
| `wait()` | 락을 해제하고 통지될 때까지 대기 |
| `notify()` | 대기 중인 스레드 하나를 깨움 |
| `notifyAll()` | 대기 중인 모든 스레드를 깨움 |

이 메서드들은 반드시 `synchronized` 블록 또는 메서드 내에서 호출해야 한다.

---

<br>

## 5. 경쟁 조건과 예방

경쟁 조건은 여러 스레드가 공유 데이터에 동시에 접근하고, 결과가 스케줄링 순서에 따라 달라질 때 발생한다.

**예방 전략:**
1. `synchronized` 블록/메서드 사용
2. `java.util.concurrent` 클래스 사용 (`AtomicInteger`, `ConcurrentHashMap` 등)
3. 단순한 가시성 보장을 위해 `volatile` 사용
4. 공유 가변 상태 최소화

---

<br>

## 요약

| 개념 | 핵심 요점 |
|:--------|:----------|
| 스레드 우선순위 | 1 (최소) ~ 10 (최대); 기본값은 5 |
| 스레드 종료 | 자연 종료 (run 반환), interrupt(), 또는 플래그 기반 |
| synchronized | 상호 배제; 한 번에 하나의 스레드만 접근 |
| 동기화 블록 | 동기화 메서드보다 세밀한 잠금 제어 |
| wait/notify | synchronized 컨텍스트 내에서의 스레드 간 조율 |
| volatile | 스레드 간 변수 가시성 보장 |
| 경쟁 조건 | 동기화 또는 원자적 연산으로 예방 |
