# Chapter 15 -- Advanced Threading

> **Last Updated:** 2026-03-21

---

## Table of Contents

- [1. Thread Control Block and States](#1-thread-control-block-and-states)
- [2. Thread Priority and Scheduling](#2-thread-priority-and-scheduling)
- [3. Thread Termination](#3-thread-termination)
  - [3.1 Natural Termination](#31-natural-termination)
  - [3.2 interrupt() Method](#32-interrupt-method)
  - [3.3 Flag-based Termination](#33-flag-based-termination)
- [4. Thread Synchronization](#4-thread-synchronization)
  - [4.1 The synchronized Keyword](#41-the-synchronized-keyword)
  - [4.2 Synchronized Blocks vs. Methods](#42-synchronized-blocks-vs-methods)
  - [4.3 wait(), notify(), and notifyAll()](#43-wait-notify-and-notifyall)
- [5. Race Conditions and Prevention](#5-race-conditions-and-prevention)
- [Summary](#summary)

---

<br>

## 1. Thread Control Block and States

Each thread is managed by a Thread Control Block (TCB) containing:

| Field | Description |
|:------|:------------|
| Thread ID | Unique identifier |
| Thread State | Current lifecycle state |
| Program Counter | Address of the next instruction |
| Registers | Saved CPU register values |
| Stack Pointer | Points to the thread's stack |
| Priority | Scheduling priority (1--10) |

---

<br>

## 2. Thread Priority and Scheduling

```java
thread.setPriority(Thread.MAX_PRIORITY);  // 10
thread.setPriority(Thread.MIN_PRIORITY);  // 1
thread.setPriority(Thread.NORM_PRIORITY); // 5 (default)
```

- The JVM uses a strictly priority-based scheduling policy.
- Threads with equal priority are scheduled in round-robin fashion.
- The main thread starts with priority 5.

---

<br>

## 3. Thread Termination

### 3.1 Natural Termination

A thread dies when its `run()` method returns:

```java
public void run() {
    for (int i = 0; i < 100; i++) {
        System.out.println(i);
    }
    // thread terminates here
}
```

### 3.2 interrupt() Method

```java
TimerThread th = new TimerThread();
th.start();
// ...
th.interrupt(); // causes InterruptedException in sleeping thread
```

### 3.3 Flag-based Termination

```java
class WorkerThread extends Thread {
    private volatile boolean finished = false;

    public void finish() { finished = true; }

    @Override
    public void run() {
        while (!finished) {
            // do work
            try { Thread.sleep(100); }
            catch (InterruptedException e) { return; }
        }
    }
}
```

> **Key Point:** Use `volatile` for the flag variable to ensure visibility across threads.

---

<br>

## 4. Thread Synchronization

### 4.1 The synchronized Keyword

Ensures only one thread at a time can execute a critical section:

```java
synchronized void add() {
    int n = getCurrentSum();
    n += 10;
    setCurrentSum(n);
}
```

### 4.2 Synchronized Blocks vs. Methods

```java
// Synchronized method -- locks the entire method
synchronized void method() { /* ... */ }

// Synchronized block -- locks only the critical section
void method() {
    // non-critical code
    synchronized (this) {
        // critical section
    }
    // non-critical code
}
```

> **Key Point:** Prefer synchronized blocks over methods to minimize the scope of locking.

### 4.3 wait(), notify(), and notifyAll()

| Method | Description |
|:-------|:------------|
| `wait()` | Releases the lock and waits until notified |
| `notify()` | Wakes up one waiting thread |
| `notifyAll()` | Wakes up all waiting threads |

These must be called from within a `synchronized` block or method.

---

<br>

## 5. Race Conditions and Prevention

A race condition occurs when multiple threads access shared data concurrently and the result depends on the scheduling order.

**Prevention strategies:**
1. Use `synchronized` blocks/methods
2. Use `java.util.concurrent` classes (`AtomicInteger`, `ConcurrentHashMap`, etc.)
3. Use `volatile` for simple visibility guarantees
4. Minimize shared mutable state

---

<br>

## Summary

| Concept | Key Point |
|:--------|:----------|
| Thread Priority | 1 (min) to 10 (max); default is 5 |
| Thread Termination | Natural (run returns), interrupt(), or flag-based |
| synchronized | Mutual exclusion; one thread at a time |
| Synchronized block | Finer-grained locking than synchronized method |
| wait/notify | Thread coordination within synchronized context |
| volatile | Ensures variable visibility across threads |
| Race condition | Prevented by synchronization or atomic operations |
