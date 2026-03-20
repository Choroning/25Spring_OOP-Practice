# Chapter 05 — Arrays

> **Last Updated:** 2026-03-21

---

## Table of Contents

- [1. Array Fundamentals](#1-array-fundamentals)
  - [1.1 Declaration and Initialization](#11-declaration-and-initialization)
  - [1.2 Array Access and Length](#12-array-access-and-length)
  - [1.3 Default Values](#13-default-values)
- [2. Array Operations](#2-array-operations)
  - [2.1 Finding Maximum and Minimum](#21-finding-maximum-and-minimum)
  - [2.2 Computing the Mean](#22-computing-the-mean)
  - [2.3 Array Traversal Patterns](#23-array-traversal-patterns)
- [3. Arrays as Method Parameters](#3-arrays-as-method-parameters)
- [4. Common Pitfalls](#4-common-pitfalls)
- [Summary](#summary)

---

<br>

## 1. Array Fundamentals

### 1.1 Declaration and Initialization

Arrays in Java are objects that store a fixed number of elements of the same type.

```java
// Declaration and allocation
int[] scores = new int[5];

// Declaration with initialization
int[] values = {10, 20, 30, 40, 50};

// Alternative syntax (C-style, less common in Java)
int numbers[] = new int[3];
```

> **Key Point:** Array size is fixed at creation time. For dynamic sizing, use `ArrayList` from the Collections framework.

### 1.2 Array Access and Length

Array elements are accessed by zero-based index. The `length` property returns the number of elements.

```java
int[] arr = {10, 20, 30};
System.out.println(arr[0]);      // 10
System.out.println(arr.length);  // 3
```

Accessing an index outside `[0, length - 1]` throws `ArrayIndexOutOfBoundsException`.

### 1.3 Default Values

| Type | Default Value |
|:-----|:-------------|
| `int`, `long`, `short`, `byte` | `0` |
| `float`, `double` | `0.0` |
| `boolean` | `false` |
| `char` | `'\u0000'` |
| Object references | `null` |

---

<br>

## 2. Array Operations

### 2.1 Finding Maximum and Minimum

```java
public static int findMax(int[] array) {
    int max = array[0];
    for (int i = 1; i < array.length; i++) {
        if (array[i] > max) {
            max = array[i];
        }
    }
    return max;
}

public static int findMin(int[] array) {
    int min = array[0];
    for (int i = 1; i < array.length; i++) {
        if (array[i] < min) {
            min = array[i];
        }
    }
    return min;
}
```

> **Key Point:** Initialize `max` and `min` with the first element of the array rather than arbitrary values like `0` or `Integer.MAX_VALUE` to handle all input ranges correctly.

### 2.2 Computing the Mean

```java
public static double computeMean(int[] array) {
    int sum = 0;
    for (int value : array) {
        sum += value;
    }
    return (double) sum / array.length;
}
```

Note the cast to `double` before division to avoid integer truncation.

### 2.3 Array Traversal Patterns

```java
// Index-based for loop
for (int i = 0; i < array.length; i++) {
    System.out.println(array[i]);
}

// Enhanced for loop (read-only traversal)
for (int value : array) {
    System.out.println(value);
}
```

---

<br>

## 3. Arrays as Method Parameters

Arrays are passed by reference (the reference is passed by value). Modifications inside the method affect the original array.

```java
public static void doubleValues(int[] array) {
    for (int i = 0; i < array.length; i++) {
        array[i] *= 2;  // modifies the original array
    }
}
```

To avoid side effects, create a copy with `Arrays.copyOf()`.

---

<br>

## 4. Common Pitfalls

| Pitfall | Solution |
|:--------|:---------|
| Off-by-one errors in loops | Use `i < array.length`, not `i <= array.length` |
| Comparing arrays with `==` | Use `Arrays.equals()` for element-wise comparison |
| Initializing max/min with magic numbers | Initialize with `array[0]` instead |
| Integer division in mean calculation | Cast to `double` before dividing |

---

<br>

## Summary

| Concept | Key Point |
|:--------|:----------|
| Declaration | `int[] arr = new int[size]` or `int[] arr = {values}` |
| Length | Fixed at creation; accessed via `arr.length` |
| Indexing | Zero-based; out-of-bounds throws exception |
| Pass-by-reference | Array modifications in methods affect the original |
| Traversal | Use enhanced for-each for read-only; indexed for modifications |
| Utility methods | `Arrays.sort()`, `Arrays.copyOf()`, `Arrays.equals()` |
