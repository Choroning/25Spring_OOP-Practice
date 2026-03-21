# Chapter 13 -- 스레딩

> **최종 수정일:** 2026-03-21

---

## 목차

- [1. 멀티태스킹과 스레드](#1-멀티태스킹과-스레드)
  - [1.1 스레드란?](#11-스레드란)
  - [1.2 스레드 생명 주기](#12-스레드-생명-주기)
- [2. 스레드 생성](#2-스레드-생성)
  - [2.1 Thread 클래스 상속](#21-thread-클래스-상속)
  - [2.2 Runnable 인터페이스 구현](#22-runnable-인터페이스-구현)
  - [2.3 Thread vs. Runnable 비교](#23-thread-vs-runnable-비교)
- [3. 스레드 연산](#3-스레드-연산)
  - [3.1 sleep()](#31-sleep)
  - [3.2 join()](#32-join)
- [4. 멀티스레드 성능](#4-멀티스레드-성능)
- [요약](#요약)

---

<br>

## 1. 멀티태스킹과 스레드

### 1.1 스레드란?

스레드는 JVM이 스케줄링하는 가장 작은 실행 단위이다. Java 애플리케이션은 항상 메인 스레드로 시작하며, 동시 작업을 위해 추가 스레드를 생성할 수 있다.

### 1.2 스레드 생명 주기

```
New -> Runnable -> Running -> Terminated
                     |
              Waiting / Blocked / Timed Waiting
```

| 상태 | 설명 |
|:------|:------------|
| **New** | 생성되었으나 아직 시작되지 않은 상태 |
| **Runnable** | 실행 준비 완료; CPU 시간을 기다리는 상태 |
| **Running** | 현재 실행 중인 상태 |
| **Blocked** | 모니터 락을 기다리는 상태 |
| **Waiting** | 다른 스레드를 무기한 기다리는 상태 |
| **Timed Waiting** | 지정된 시간 동안 대기하는 상태 (`sleep`, `wait(timeout)`) |
| **Terminated** | 실행이 완료된 상태 |

---

<br>

## 2. 스레드 생성

### 2.1 Thread 클래스 상속

```java
class TimerThread extends Thread {
    @Override
    public void run() {
        int n = 0;
        while (true) {
            System.out.println(n++);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}

// 사용법
TimerThread timer = new TimerThread();
timer.start();
```

### 2.2 Runnable 인터페이스 구현

```java
class TimerRunnable implements Runnable {
    @Override
    public void run() {
        int n = 0;
        while (true) {
            System.out.println(n++);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}

// 사용법
Thread thread = new Thread(new TimerRunnable());
thread.start();
```

### 2.3 Thread vs. Runnable 비교

| 특징 | extends Thread | implements Runnable |
|:--------|:---------------|:--------------------|
| 상속 | 다른 클래스를 상속할 수 없음 | 다른 클래스를 상속할 수 있음 |
| 재사용성 | 강한 결합 | 느슨한 결합 |
| 유연성 | 덜 유연함 | 더 유연함 |
| 권장 사항 | 단순한 독립 스레드 | 권장되는 방식 |

> **핵심 요점:** Java는 단일 상속만 지원하므로 `Runnable`을 사용하는 것이 바람직하다. `Runnable`을 사용하면 해당 클래스가 다른 클래스를 자유롭게 상속할 수 있다.

---

<br>

## 3. 스레드 연산

### 3.1 sleep()

현재 스레드를 지정된 밀리초 동안 일시 정지시킨다:

```java
try {
    Thread.sleep(1000); // 1초간 일시 정지
} catch (InterruptedException e) {
    Thread.currentThread().interrupt();
}
```

### 3.2 join()

다른 스레드가 완료될 때까지 대기한 후 계속 진행한다:

```java
Thread worker = new Thread(task);
worker.start();
worker.join(); // 메인 스레드가 worker 완료를 기다림
System.out.println("Worker finished.");
```

---

<br>

## 4. 멀티스레드 성능

대규모 연산을 여러 스레드로 분할하면 멀티코어 시스템에서 실행 시간을 크게 줄일 수 있다:

```java
// 싱글 스레드: 0부터 20억까지 합산
// 멀티 스레드: 범위를 N개의 하위 범위로 나누어 스레드당 하나씩 할당
SumThread[] threads = new SumThread[NUM_THREADS];
for (int i = 0; i < NUM_THREADS; i++) {
    threads[i] = new SumThread(start, end);
    threads[i].start();
}
for (SumThread t : threads) {
    t.join();
}
```

> **핵심 요점:** 스레드 생성과 동기화의 오버헤드로 인해 멀티스레딩은 충분히 큰 작업량에서만 성능 향상 효과가 있다.

---

<br>

## 요약

| 개념 | 핵심 요점 |
|:--------|:----------|
| 스레드 | JVM 실행의 최소 단위; 자체 호출 스택을 가짐 |
| Thread.start() | 실행을 시작; 새 스레드에서 `run()`을 호출 |
| Runnable | 스레드 동작을 정의하는 권장 방식 |
| sleep() | 현재 스레드를 일정 시간 동안 일시 정지 |
| join() | 다른 스레드가 완료될 때까지 대기 |
| 멀티스레드 합산 | 작업을 분할하여 병렬로 처리하여 속도 향상 |
