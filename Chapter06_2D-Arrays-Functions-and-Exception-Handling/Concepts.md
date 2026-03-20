# Chapter 06 -- 2D Arrays, Functions, and Exception Handling

> **Last Updated:** 2026-03-21

---

## Table of Contents

- [1. Two-Dimensional Arrays](#1-two-dimensional-arrays)
  - [1.1 Declaration and Initialization](#11-declaration-and-initialization)
  - [1.2 Iterating Over 2D Arrays](#12-iterating-over-2d-arrays)
  - [1.3 Ragged Arrays](#13-ragged-arrays)
- [2. Custom Functions (Methods)](#2-custom-functions-methods)
  - [2.1 Defining Methods](#21-defining-methods)
  - [2.2 Method Overloading](#22-method-overloading)
  - [2.3 Pass-by-Value vs. Pass-by-Reference Semantics](#23-pass-by-value-vs-pass-by-reference-semantics)
- [3. Exception Handling](#3-exception-handling)
  - [3.1 try-catch-finally](#31-try-catch-finally)
  - [3.2 Common Exception Types](#32-common-exception-types)
  - [3.3 Input Validation with Exceptions](#33-input-validation-with-exceptions)
- [Summary](#summary)

---

<br>

## 1. Two-Dimensional Arrays

### 1.1 Declaration and Initialization

A 2D array is an array of arrays, commonly used to represent matrices, grids, and tables.

```java
// Static initialization
double[][] scores = {
    {3.3, 3.4},
    {3.5, 3.6},
    {3.7, 4.0},
    {4.1, 4.2}
};

// Dynamic allocation
int[][] matrix = new int[3][4]; // 3 rows, 4 columns
```

### 1.2 Iterating Over 2D Arrays

```java
// Row-major traversal
for (int row = 0; row < scores.length; row++) {
    for (int col = 0; col < scores[row].length; col++) {
        sum += scores[row][col];
    }
}

// Enhanced for loop
for (double[] row : scores) {
    for (double value : row) {
        sum += value;
    }
}
```

### 1.3 Ragged Arrays

Java allows each row to have a different length:

```java
int[][] ragged = new int[3][];
ragged[0] = new int[2];
ragged[1] = new int[5];
ragged[2] = new int[3];
```

> **Key Point:** Always use `array[row].length` (not a fixed column count) to handle ragged arrays correctly.

---

<br>

## 2. Custom Functions (Methods)

### 2.1 Defining Methods

```java
public static returnType methodName(parameterList) {
    // method body
    return value;
}
```

Example:

```java
public static double computeAverage(double[][] scores) {
    double sum = 0;
    int count = 0;
    for (double[] row : scores) {
        for (double val : row) {
            sum += val;
            count++;
        }
    }
    return sum / count;
}
```

### 2.2 Method Overloading

Multiple methods can share the same name if they differ in parameter types or count:

```java
public static int max(int a, int b) { return a > b ? a : b; }
public static double max(double a, double b) { return a > b ? a : b; }
```

### 2.3 Pass-by-Value vs. Pass-by-Reference Semantics

- **Primitives** are passed by value -- a copy is made.
- **Objects and arrays** pass the reference by value -- the method can modify the object's contents but cannot reassign the caller's variable.

---

<br>

## 3. Exception Handling

### 3.1 try-catch-finally

```java
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero: " + e.getMessage());
} finally {
    // Always executed, even if an exception occurs
    System.out.println("Cleanup complete.");
}
```

### 3.2 Common Exception Types

| Exception | Cause |
|:----------|:------|
| `ArithmeticException` | Division by zero |
| `ArrayIndexOutOfBoundsException` | Invalid array index |
| `NullPointerException` | Accessing a member on a null reference |
| `NumberFormatException` | Invalid string-to-number conversion |
| `InputMismatchException` | Scanner receives unexpected input type |
| `IOException` | I/O operation failure |

### 3.3 Input Validation with Exceptions

```java
Scanner scanner = new Scanner(System.in);
int sum = 0;
for (int i = 0; i < 3; i++) {
    System.out.print((i + 1) + "th value >> ");
    try {
        sum += scanner.nextInt();
    } catch (InputMismatchException e) {
        System.out.println("Not an integer. Try again.");
        scanner.next(); // consume invalid token
        i--;            // retry the same iteration
    }
}
```

> **Key Point:** When catching `InputMismatchException` from a `Scanner`, call `scanner.next()` to consume the invalid token, otherwise the loop will spin infinitely.

---

<br>

## Summary

| Concept | Key Point |
|:--------|:----------|
| 2D Arrays | Array of arrays; rows may have different lengths (ragged) |
| Iteration | Use `array.length` for rows, `array[i].length` for columns |
| Methods | Reusable code blocks with return types and parameters |
| Overloading | Same name, different parameter signatures |
| try-catch | Gracefully handle runtime errors without crashing |
| finally | Code that always executes for cleanup |
| InputMismatchException | Consume invalid token with `scanner.next()` before retrying |
