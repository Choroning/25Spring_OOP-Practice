# Chapter 13 -- Threading

> **Last Updated:** 2026-03-21

---

## Table of Contents

- [1. Multi-tasking and Threads](#1-multi-tasking-and-threads)
  - [1.1 What is a Thread?](#11-what-is-a-thread)
  - [1.2 Thread Life Cycle](#12-thread-life-cycle)
- [2. Creating Threads](#2-creating-threads)
  - [2.1 Extending the Thread Class](#21-extending-the-thread-class)
  - [2.2 Implementing the Runnable Interface](#22-implementing-the-runnable-interface)
  - [2.3 Thread vs. Runnable Comparison](#23-thread-vs-runnable-comparison)
- [3. Thread Operations](#3-thread-operations)
  - [3.1 sleep()](#31-sleep)
  - [3.2 join()](#32-join)
- [4. Multi-threaded Performance](#4-multi-threaded-performance)
- [Summary](#summary)

---

<br>

## 1. Multi-tasking and Threads

### 1.1 What is a Thread?

A thread is the smallest unit of execution scheduled by the JVM. A Java application always starts with a main thread, and can spawn additional threads for concurrent work.

### 1.2 Thread Life Cycle

```
New -> Runnable -> Running -> Terminated
                     |
              Waiting / Blocked / Timed Waiting
```

| State | Description |
|:------|:------------|
| **New** | Created but not started |
| **Runnable** | Ready to run; waiting for CPU time |
| **Running** | Actively executing |
| **Blocked** | Waiting for a monitor lock |
| **Waiting** | Waiting indefinitely for another thread |
| **Timed Waiting** | Waiting for a specified time (`sleep`, `wait(timeout)`) |
| **Terminated** | Execution completed |

---

<br>

## 2. Creating Threads

### 2.1 Extending the Thread Class

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

// Usage
TimerThread timer = new TimerThread();
timer.start();
```

### 2.2 Implementing the Runnable Interface

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

// Usage
Thread thread = new Thread(new TimerRunnable());
thread.start();
```

### 2.3 Thread vs. Runnable Comparison

| Feature | extends Thread | implements Runnable |
|:--------|:---------------|:--------------------|
| Inheritance | Cannot extend another class | Can extend another class |
| Reusability | Tightly coupled | Loosely coupled |
| Flexibility | Less flexible | More flexible |
| Best practice | Simple standalone threads | Recommended approach |

> **Key Point:** Prefer `Runnable` because Java only supports single inheritance. Using `Runnable` leaves the class free to extend another class.

---

<br>

## 3. Thread Operations

### 3.1 sleep()

Pauses the current thread for the specified number of milliseconds:

```java
try {
    Thread.sleep(1000); // sleep for 1 second
} catch (InterruptedException e) {
    Thread.currentThread().interrupt();
}
```

### 3.2 join()

Waits for another thread to finish before continuing:

```java
Thread worker = new Thread(task);
worker.start();
worker.join(); // main thread waits for worker to finish
System.out.println("Worker finished.");
```

---

<br>

## 4. Multi-threaded Performance

Splitting a large computation across multiple threads can significantly reduce execution time on multi-core systems:

```java
// Single-threaded: sum 0 to 2 billion
// Multi-threaded: split the range into N sub-ranges, one per thread
SumThread[] threads = new SumThread[NUM_THREADS];
for (int i = 0; i < NUM_THREADS; i++) {
    threads[i] = new SumThread(start, end);
    threads[i].start();
}
for (SumThread t : threads) {
    t.join();
}
```

> **Key Point:** The overhead of thread creation and synchronization means multi-threading only pays off for sufficiently large workloads.

---

<br>

## Summary

| Concept | Key Point |
|:--------|:----------|
| Thread | Smallest unit of JVM execution; has its own call stack |
| Thread.start() | Begins execution; calls `run()` in a new thread |
| Runnable | Preferred way to define thread behavior |
| sleep() | Pauses the current thread for a duration |
| join() | Waits for another thread to complete |
| Multi-threaded sum | Split work into partitions for parallel speedup |
