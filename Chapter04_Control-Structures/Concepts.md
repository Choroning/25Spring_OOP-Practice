# Chapter 04 — Control Structures

> **Last Updated:** 2026-04-01

> **Prerequisites**: [Programming Language] Java grammar basics (Ch 1-3).
>
> **Learning Objectives**:
> 1. Apply conditional statements (if/else, switch)
> 2. Use loops (for, while, do-while) effectively
> 3. Implement nested control structures and break/continue

---

## Table of Contents

- [1. Conditional Operators](#1-conditional-operators)
  - [1.1 The Ternary Operator](#11-the-ternary-operator)
  - [1.2 Relational and Logical Operators](#12-relational-and-logical-operators)
- [2. Selection Statements](#2-selection-statements)
  - [2.1 if-else Statements](#21-if-else-statements)
  - [2.2 Nested if-else](#22-nested-if-else)
  - [2.3 switch Statement](#23-switch-statement)
- [3. Iteration Statements](#3-iteration-statements)
  - [3.1 while Loop](#31-while-loop)
  - [3.2 do-while Loop](#32-do-while-loop)
  - [3.3 for Loop](#33-for-loop)
  - [3.4 Enhanced for Loop (for-each)](#34-enhanced-for-loop-for-each)
- [4. Loop Control Statements](#4-loop-control-statements)
- [5. Measuring Execution Time](#5-measuring-execution-time)
- [6. GUI Input with JOptionPane](#6-gui-input-with-joptionpane)
- [Summary](#summary)

---

<br>

## 1. Conditional Operators

### 1.1 The Ternary Operator

The ternary operator `? :` provides a compact way to express simple conditional logic in a single expression.

```java
// Syntax: condition ? valueIfTrue : valueIfFalse
int diff = (a > b) ? (a - b) : (b - a);
```

It is particularly useful for inline assignments where a full `if-else` block would be unnecessarily verbose.

> **Key Point:** The ternary operator should only be used for simple conditions. Complex logic is better expressed with `if-else` for readability.

### 1.2 Relational and Logical Operators

| Operator | Description | Example |
|:---------|:------------|:--------|
| `==` | Equal to | `a == b` |
| `!=` | Not equal to | `a != b` |
| `>` | Greater than | `a > b` |
| `<` | Less than | `a < b` |
| `>=` | Greater than or equal | `a >= b` |
| `<=` | Less than or equal | `a <= b` |
| `&&` | Logical AND (short-circuit) | `a > 0 && b > 0` |
| `\|\|` | Logical OR (short-circuit) | `a > 0 \|\| b > 0` |
| `!` | Logical NOT | `!flag` |

---

<br>

## 2. Selection Statements

### 2.1 if-else Statements

The `if-else` statement evaluates a boolean expression and executes one of two branches.

```java
if (score >= 90) {
    grade = 'A';
} else if (score >= 80) {
    grade = 'B';
} else {
    grade = 'F';
}
```

### 2.2 Nested if-else

Multiple conditions can be chained with `else if`. However, deeply nested structures reduce readability — consider using `switch` or polymorphism instead.

### 2.3 switch Statement

The `switch` statement evaluates an expression and matches it against constant case labels. It supports `int`, `char`, `String`, and `enum` types.

```java
switch (score / 10) {
    case 10:
    case 9:
        grade = 'A';
        break;
    case 8:
        grade = 'B';
        break;
    case 7:
        grade = 'C';
        break;
    default:
        grade = 'F';
}
```

> **Key Point:** Always include a `break` statement for each case to prevent fall-through, unless fall-through is intentional (e.g., grouping cases 10 and 9 together).

---

<br>

## 3. Iteration Statements

### 3.1 while Loop

The `while` loop executes as long as the condition remains `true`. The condition is checked **before** each iteration.

```java
while (age < 0 || age > 130) {
    // prompt for valid input
    age = Integer.parseInt(inputStr);
}
```

### 3.2 do-while Loop

The `do-while` loop guarantees at least one execution, then checks the condition **after** each iteration.

```java
do {
    input = scanner.nextInt();
} while (input < 0);
```

### 3.3 for Loop

The `for` loop combines initialization, condition, and update in a single header.

```java
for (int i = 0; i < 10; i++) {
    System.out.println("Iteration: " + i);
}
```

### 3.4 Enhanced for Loop (for-each)

Java provides the enhanced `for` loop for iterating over arrays and `Iterable` collections.

```java
int[] numbers = {1, 2, 3, 4, 5};
for (int n : numbers) {
    System.out.println(n);
}
```

---

<br>

## 4. Loop Control Statements

| Statement | Description |
|:----------|:------------|
| `break` | Exits the innermost enclosing loop or switch immediately |
| `continue` | Skips the rest of the current iteration and proceeds to the next |
| `return` | Exits the current method entirely |

```java
for (int i = 0; i < 10; i++) {
    if (i == 5) break;      // exits the loop when i == 5
    if (i % 2 == 0) continue; // skips even numbers
    System.out.println(i);   // prints 1, 3
}
```

---

<br>

## 5. Measuring Execution Time

Java provides several ways to measure elapsed time:

```java
// Using Date class
Date startTime = new Date();
// ... code to measure ...
Date endTime = new Date();
long elapsedMs = endTime.getTime() - startTime.getTime();

// Using System.currentTimeMillis() (preferred)
long start = System.currentTimeMillis();
// ... code to measure ...
long end = System.currentTimeMillis();
System.out.println("Elapsed: " + (end - start) + " ms");

// Using System.nanoTime() (highest precision)
long startNano = System.nanoTime();
// ... code to measure ...
long endNano = System.nanoTime();
double seconds = (endNano - startNano) / 1_000_000_000.0;
```

> **Key Point:** Prefer `System.nanoTime()` for performance benchmarking as it is not affected by system clock adjustments.

---

<br>

## 6. GUI Input with JOptionPane

The `javax.swing.JOptionPane` class provides simple dialog boxes for user interaction:

```java
// Input dialog
String input = JOptionPane.showInputDialog(null, "Enter your age:");

// Message dialog
JOptionPane.showMessageDialog(null, "Hello, World!");

// Confirm dialog
int choice = JOptionPane.showConfirmDialog(null, "Continue?");
```

> **Key Point:** `JOptionPane.showInputDialog()` returns `null` when the user clicks Cancel — always check for `null` before processing.

---

<br>

## Summary

| Concept | Key Point |
|:--------|:----------|
| Ternary Operator | Compact conditional: `condition ? a : b` |
| if-else | Standard branching; avoid deep nesting |
| switch | Multi-way branching on constants; remember `break` |
| while | Pre-condition loop; may execute zero times |
| do-while | Post-condition loop; always executes at least once |
| for | Counter-controlled iteration with init, condition, update |
| for-each | Simplified iteration over arrays and collections |
| break / continue | Loop flow control: exit loop or skip iteration |
| JOptionPane | Quick GUI dialogs for input, messages, and confirmations |
