# Chapter 09 — Assertions, Generics, and Collections

> **Last Updated:** 2026-04-01

> **Prerequisites**: [Programming Language] Java (Ch 1-8). [Data Structures] Basic data structure concepts.
>
> **Learning Objectives**:
> 1. Use assertions for debugging and design-by-contract
> 2. Apply generic types and bounded type parameters
> 3. Utilize Java Collections Framework (List, Set, Map)

---

## Table of Contents

- [1. Assertions](#1-assertions)
  - [1.1 Syntax and Usage](#11-syntax-and-usage)
  - [1.2 Enabling Assertions](#12-enabling-assertions)
- [2. Generics](#2-generics)
  - [2.1 Generic Classes](#21-generic-classes)
  - [2.2 Generic Methods](#22-generic-methods)
  - [2.3 Bounded Type Parameters](#23-bounded-type-parameters)
- [3. Collections Framework](#3-collections-framework)
  - [3.1 ArrayList](#31-arraylist)
  - [3.2 HashMap](#32-hashmap)
  - [3.3 Iterating Collections](#33-iterating-collections)
- [Summary](#summary)

---

<br>

## 1. Assertions

### 1.1 Syntax and Usage

Assertions are runtime checks for conditions that should always be true during development:

```java
assert balance > oldBalance :
    "Error: balance did not increase after deposit";
```

If the condition is `false`, an `AssertionError` is thrown with the provided message.

### 1.2 Enabling Assertions

Assertions are disabled by default. Enable them with the `-ea` JVM flag:

```bash
java -ea MyProgram
```

> **Key Point:** Assertions should check program invariants, not validate user input. For user input, use exceptions or conditional checks.

---

<br>

## 2. Generics

### 2.1 Generic Classes

Generics enable type-safe, reusable code by parameterizing types:

```java
public class Box<T> {
    private T item;

    public void set(T item) { this.item = item; }
    public T get() { return item; }
}

// Usage
Box<String> stringBox = new Box<>();
stringBox.set("Hello");
String value = stringBox.get(); // no cast needed
```

### 2.2 Generic Methods

```java
public static <T> void printArray(T[] array) {
    for (T element : array) {
        System.out.print(element + " ");
    }
    System.out.println();
}
```

### 2.3 Bounded Type Parameters

```java
// T must be a subtype of Comparable
public static <T extends Comparable<T>> T findMax(T[] array) {
    T max = array[0];
    for (int i = 1; i < array.length; i++) {
        if (array[i].compareTo(max) > 0) {
            max = array[i];
        }
    }
    return max;
}
```

> **Key Point:** Use `<T extends SomeType>` to restrict generics to types that implement a specific interface or extend a specific class.

---

<br>

## 3. Collections Framework

### 3.1 ArrayList

A resizable array implementation of the `List` interface:

```java
ArrayList<String> names = new ArrayList<>();
names.add("Alice");
names.add("Bob");
names.get(0);       // "Alice"
names.size();       // 2
names.remove(0);    // removes "Alice"
```

### 3.2 HashMap

A key-value map with O(1) average access:

```java
HashMap<String, Integer> scores = new HashMap<>();
scores.put("Alice", 95);
scores.put("Bob", 87);
int aliceScore = scores.get("Alice"); // 95
```

### 3.3 Iterating Collections

```java
// For-each loop
for (String name : names) {
    System.out.println(name);
}

// Iterator
Iterator<String> it = names.iterator();
while (it.hasNext()) {
    System.out.println(it.next());
}

// forEach with lambda (Java 8+)
names.forEach(name -> System.out.println(name));
```

---

<br>

## Summary

| Concept | Key Point |
|:--------|:----------|
| Assertions | Runtime invariant checks; enable with `-ea`; not for user input |
| Generics | Type-safe parameterized classes and methods |
| Bounded types | `<T extends Type>` restricts type parameter |
| ArrayList | Resizable array; O(1) access by index |
| HashMap | Key-value pairs; O(1) average lookup |
| Iteration | For-each, Iterator, or lambda `forEach` |
