# Chapter 03 — Java Basic Grammar

> **Last Updated:** 2026-04-01

> **Prerequisites**: [Programming Language] Java classes and objects (Ch 1-2).
>
> **Learning Objectives**:
> 1. Use Java data types, operators, and type casting
> 2. Apply String methods and formatting
> 3. Handle console input with Scanner class

---

## Table of Contents

- [1. Primitive Data Types](#1-primitive-data-types)
- [2. Variables and Constants](#2-variables-and-constants)
- [3. Operators](#3-operators)
  - [3.1 Arithmetic Operators](#31-arithmetic-operators)
  - [3.2 Assignment Operators](#32-assignment-operators)
  - [3.3 Increment and Decrement](#33-increment-and-decrement)
  - [3.4 Relational and Logical Operators](#34-relational-and-logical-operators)
- [4. Type Casting](#4-type-casting)
- [5. Console and Dialog I/O](#5-console-and-dialog-io)
  - [5.1 Scanner for Console Input](#51-scanner-for-console-input)
  - [5.2 JOptionPane for GUI Dialogs](#52-joptionpane-for-gui-dialogs)
- [6. String Operations](#6-string-operations)
- [Summary](#summary)

---

<br>

## 1. Primitive Data Types

Java has 8 primitive data types:

| Type | Size | Range / Description |
|:-----|:-----|:-------------------|
| `byte` | 1 byte | -128 to 127 |
| `short` | 2 bytes | -32,768 to 32,767 |
| `int` | 4 bytes | $-2^{31}$ to $2^{31}-1$ |
| `long` | 8 bytes | $-2^{63}$ to $2^{63}-1$ |
| `float` | 4 bytes | IEEE 754 single precision |
| `double` | 8 bytes | IEEE 754 double precision |
| `char` | 2 bytes | Unicode character (0 to 65,535) |
| `boolean` | 1 bit* | `true` or `false` |

> **Key Point:** Java does not have unsigned integer types. Use `long` or `BigInteger` for values exceeding `int` range.

---

<br>

## 2. Variables and Constants

```java
// Variable declaration and initialization
int age = 25;
double gpa = 3.95;
String name = "Park";

// Constants use the 'final' keyword
static final double PI = 3.141592;
static final int UNIT_CHANGER = 60;
```

Naming conventions:
- Variables: `camelCase` (e.g., `studentName`)
- Constants: `UPPER_SNAKE_CASE` (e.g., `MAX_SIZE`)
- Classes: `PascalCase` (e.g., `BankAccount`)

---

<br>

## 3. Operators

### 3.1 Arithmetic Operators

| Operator | Operation | Example |
|:---------|:----------|:--------|
| `+` | Addition | `5 + 3` = 8 |
| `-` | Subtraction | `5 - 3` = 2 |
| `*` | Multiplication | `5 * 3` = 15 |
| `/` | Division | `7 / 2` = 3 (integer division) |
| `%` | Modulus | `7 % 2` = 1 |

### 3.2 Assignment Operators

```java
a += 3;   // a = a + 3
b *= 3;   // b = b * 3
c %= 2;   // c = c % 2
```

### 3.3 Increment and Decrement

```java
int d = 3;
int a = d++;  // a = 3, d = 4 (post-increment: use then increment)
a = ++d;      // d = 5, a = 5 (pre-increment: increment then use)
```

### 3.4 Relational and Logical Operators

| Category | Operators |
|:---------|:----------|
| Relational | `==`, `!=`, `<`, `>`, `<=`, `>=` |
| Logical | `&&` (AND), `\|\|` (OR), `!` (NOT) |

---

<br>

## 4. Type Casting

```java
// Implicit (widening): smaller -> larger (automatic)
int i = 10;
double d = i;  // 10.0

// Explicit (narrowing): larger -> smaller (requires cast)
double pi = 3.14;
int truncated = (int) pi;  // 3
```

---

<br>

## 5. Console and Dialog I/O

### 5.1 Scanner for Console Input

```java
import java.util.Scanner;

Scanner scanner = new Scanner(System.in);
int time = scanner.nextInt();
String line = scanner.nextLine();
scanner.close();
```

### 5.2 JOptionPane for GUI Dialogs

```java
String input = JOptionPane.showInputDialog(null, "Enter radius:");
double radius = Double.parseDouble(input);
```

---

<br>

## 6. String Operations

Key `String` methods:

| Method | Description |
|:-------|:-----------|
| `length()` | Returns number of characters |
| `charAt(i)` | Returns character at index `i` |
| `substring(begin, end)` | Extracts a substring |
| `indexOf(str)` | Finds position of substring |
| `toUpperCase()` | Converts to uppercase |
| `split(regex)` | Splits into array by delimiter |
| `trim()` | Removes leading/trailing whitespace |

---

<br>

## Summary

| Concept | Key Point |
|:--------|:----------|
| Primitives | 8 types: byte, short, int, long, float, double, char, boolean |
| Constants | Declared with `static final`; convention is UPPER_SNAKE_CASE |
| Operators | Arithmetic, assignment, increment/decrement, relational, logical |
| Type Casting | Widening (automatic), narrowing (explicit cast required) |
| Scanner | Console input; `nextInt()`, `nextDouble()`, `nextLine()` |
| String | Immutable; rich API for manipulation |
