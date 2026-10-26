# Lecture 3 — Java Basic Grammar

[Repository Guide](../../README.md) | [English](Concepts_Lecture.md) | [한국어](Concepts_Lecture.ko.md)

> **Last Updated:** 2026-10-26

> **Learning Objectives:**
> 1. Name variables, constants, and classes according to the identifier rules and naming conventions
> 2. Use Java data types, literals, operators, and type casting
> 3. Explain the difference between primitive and reference types with memory states
> 4. Apply String methods and formatting
> 5. Handle console input with Scanner class

---

## Table of Contents

- [1. Identifiers and Keywords](#1-identifiers-and-keywords)
  - [1.1 Identifier Rules](#11-identifier-rules)
  - [1.2 Java Keywords](#12-java-keywords)
  - [1.3 Naming Conventions](#13-naming-conventions)
- [2. Primitive Data Types](#2-primitive-data-types)
  - [2.1 Kinds and Ranges of Data Types](#21-kinds-and-ranges-of-data-types)
  - [2.2 Primitive and Reference Types](#22-primitive-and-reference-types)
  - [2.3 Literals](#23-literals)
  - [2.4 The var Keyword](#24-the-var-keyword)
- [3. Variables and Constants](#3-variables-and-constants)
- [4. Operators](#4-operators)
  - [4.1 Arithmetic Operators](#41-arithmetic-operators)
  - [4.2 Operator Precedence](#42-operator-precedence)
  - [4.3 Assignment Operators](#43-assignment-operators)
  - [4.4 Increment and Decrement](#44-increment-and-decrement)
  - [4.5 Relational and Logical Operators](#45-relational-and-logical-operators)
  - [4.6 The Two Meanings of +](#46-the-two-meanings-of-)
- [5. Type Casting](#5-type-casting)
- [6. Console and Dialog I/O](#6-console-and-dialog-io)
  - [6.1 Standard Output](#61-standard-output)
  - [6.2 Formatting Numbers with DecimalFormat](#62-formatting-numbers-with-decimalformat)
  - [6.3 Scanner for Console Input](#63-scanner-for-console-input)
  - [6.4 JOptionPane for GUI Dialogs](#64-joptionpane-for-gui-dialogs)
- [7. String Operations](#7-string-operations)
- [8. Example: Converting Seconds to Hours, Minutes, and Seconds](#8-example-converting-seconds-to-hours-minutes-and-seconds)
- [Summary](#summary)
- [Self-Check Questions](#self-check-questions)

---

<br>

## 1. Identifiers and Keywords

### 1.1 Identifier Rules

An **identifier** is a name given to distinguish variables, constants, methods, classes, and so on. Java identifiers follow these rules.

- The first character is a letter, an underscore (`_`), or a dollar sign (`$`), and digits may follow. An identifier cannot start with a digit.
- Other special characters such as `%` are not allowed.
- Keywords (Section 1.2) and the literals `true`, `false`, and `null` cannot be identifiers.
- Identifiers are case-sensitive: `barChart` and `barchart` are different identifiers.
- Unicode letters are allowed, so a Korean identifier (`가격`) is possible. There is no length limit.

```java
// Valid identifiers
int name;
char student_ID;
void $func() { }
class Monster3 { }
int whatsyournamemynameiskitae;
int barChart; int barchart;   // different variables because the case differs
int 가격;

// Invalid identifiers
int 3Chapter;        // starts with a digit
class if { }         // keyword
char false;          // literal
void null() { }      // literal
class %calc { }      // special character %
```

### 1.2 Java Keywords

Keywords are words reserved by the Java language for special meanings and cannot be used as identifiers.

| | | | | |
|:-----|:-----|:-----|:-----|:-----|
| `abstract` | `continue` | `for` | `new` | `switch` |
| `assert` | `default` | `if` | `package` | `synchronized` |
| `boolean` | `do` | `goto` | `private` | `this` |
| `break` | `double` | `implements` | `protected` | `throw` |
| `byte` | `else` | `import` | `public` | `throws` |
| `case` | `enum` | `instanceof` | `return` | `transient` |
| `catch` | `extends` | `int` | `short` | `try` |
| `char` | `final` | `interface` | `static` | `void` |
| `class` | `finally` | `long` | `strictfp` | `volatile` |
| `const` | `float` | `native` | `super` | `while` |

*Table 1. Java keywords*

`goto` and `const` are reserved but not actually used.

### 1.3 Naming Conventions

- Use names that reveal their purpose. For a variable holding a sum, `sum` is better than `s`.
- Class names start with an uppercase letter, with the first letter of each word capitalized: `PascalCase` (e.g., `BankAccount`, `HelloWorld`, `AutoVendingMachine`).
- Variable and method names start with a lowercase letter, and the first letter of each word after the first is capitalized: `camelCase` (e.g., `studentName`, `myAge`, `isSingle`, `getAge()`).
- Constant names are written in all capital letters, with words joined by underscores: `UPPER_SNAKE_CASE` (e.g., `MAX_SIZE`, `PI`).

---

<br>

## 2. Primitive Data Types

### 2.1 Kinds and Ranges of Data Types

Java has 8 primitive data types.

| Type | Size | Range / Description |
|:-----|:-----|:-------------------|
| `byte` | 1 byte | -128 to 127 |
| `short` | 2 bytes | -32,768 to 32,767 |
| `int` | 4 bytes | $-2^{31}$ to $2^{31}-1$ |
| `long` | 8 bytes | $-2^{63}$ to $2^{63}-1$ |
| `float` | 4 bytes | IEEE 754 single precision, about -3.40282347E+38 to 3.40282347E+38 |
| `double` | 8 bytes | IEEE 754 double precision, about -1.79769313486231570E+308 to 1.79769313486231570E+308 |
| `char` | 2 bytes | Unicode character (0 to 65,535) |
| `boolean` | Depends on the JVM implementation | `true` or `false` |

*Table 2. Primitive data types of Java and their ranges*

A `boolean` carries one bit of information, but the memory it actually occupies is not fixed by the Java specification and depends on the JVM implementation. The six numerical data types (`byte`, `short`, `int`, `long`, `float`, `double`) differ in the range and precision of values they can store; the four integer types store integers, and `float` and `double` store real numbers.

> **Key Point:** Except for `char` (a 16-bit unsigned Unicode code unit), all Java integer types are signed, and there is no separate unsigned integer type. Use `long` or `BigInteger` for values exceeding `int` range.

> **[Digital Logic Design]** Java integer types represent negative numbers in **two's complement**. This signed binary representation, taught in digital logic design, covers $-2^{n-1}$ through $2^{n-1}-1$ with n bits, so the range of `byte` (8 bits) is -128 to 127, one wider on the negative side, and the overflow of Lecture 4, Section 3.2, in which adding 1 to the maximum wraps around to the minimum, also comes from this representation.

### 2.2 Primitive and Reference Types

- **Primitive types:** The eight types above. Like numerical data, the value itself is stored in the variable's memory space.
- **Reference types:** Class, array, and interface types all belong here. Objects are reference data, and the variable holds a reference (address) to the memory location where the object is actually stored.

```java
int number;
number = 237;
number = 35;                 // overwrites 237

Customer clemens, twain;
clemens = new Customer();
twain = clemens;             // now two references point to the same object
```

```text
Primitive variable              Reference variables

number                         clemens
┌──────┐                       ┌───────┐
│  35  │  (overwrote 237)      │   ●───┼──┐
└──────┘                       └───────┘  │    ┌──────────┐
                                          ├──> │ Customer │
                               twain      │    └──────────┘
                               ┌───────┐  │
                               │   ●───┼──┘
                               └───────┘
```

*Figure 1. Memory states of a primitive variable and two reference variables pointing to one object*

Assigning a new value to a primitive variable overwrites the previous value. Assigning one reference variable to another does not copy the object; both variables point to one object (Lecture 2).

### 2.3 Literals

A **literal** is a value written directly in a program.

- **Integer literals:** `15` is decimal; a leading 0 as in `015` means octal (value 13); `0x15` is hexadecimal (value 21); and `0b0101` is binary (value 5). Integer literals are compiled to type `int`, and a `long` literal is indicated by appending `L` or `l` after the number, as in `24L`.
- **Real literals:** Written in decimal form such as `12.`, `12.0`, `.1234`, and `0.1234`, or in exponential form such as `1234E-4` ($1234 \times 10^{-4}$). Real literals are compiled to type `double`; append `f` (`F`) for `float`, or `d` (`D`) to state `double` explicitly.

```java
int n = 15;
int m = 015;      // octal, 13
int k = 0x15;     // hexadecimal, 21
int b = 0b0101;   // binary, 5
long g = 24L;

double d = 0.1234;
double e = 1234E-4;   // 1234E-4 = 1234 x 10^-4
float f = 0.1234f;
double w = .1234D;
```

Since JDK 7, an underscore (`_`) can be placed anywhere between the digits of a numeric literal for readability. Underscores do not affect the value.

```java
int price = 20_100;
long cardNumber = 1234_5678_1357_9998L;
long controlBits = 0b10110100_01011011_10110011_111110000L;
long maxLong = 0x7fff_ffff_ffff_ffffL;
int age = 2______5;
```

### 2.4 The var Keyword

Since Java 10, local variables can be declared with `var`. The compiler infers the type from the initializer, the same function as `auto` in C++. `var` can be used only to declare local variables, so it cannot be used for fields, and a `var` declaration needs an initializer.

```java
var price = 200;               // int
var name = "kitae";            // String
var pi = 3.14;                 // double
var point = new Point();       // java.awt.Point
var v = new Vector<Integer>(); // java.util.Vector<Integer>
```

---

<br>

## 3. Variables and Constants

The expression for adding two numbers x and y is `x + y`, but before the addition takes place, we must declare the data types of x and y. The declaration allocates memory space to store the values of x and y, and such a name that stores a value is called a **variable**. A variable has three properties.
- A memory location to store the value
- The type of data stored in the memory location
- The name used to refer to the memory location

```java
// Variable declaration and initialization
int age = 25;
double gpa = 3.95;
String name = "Park";

// Constants use the 'final' keyword
static final double PI = 3.141592;
static final int UNIT_CHANGER = 60;
```

Several variables can be declared at once (`int i, j, k;`) and initialized in the declaration (`int count = 10, height = 34;`). An assignment statement, which puts a value into a variable, has the form `<variable> = <expression>;` (e.g., `sum = firstNumber + secondNumber;`, `avg = (one + two + three) / 3.0;`).

If a value must remain the same, use a **constant**. In `final double PI = 3.14159;`, `final int MONTH_IN_YEAR = 12;`, and `final short FARADAY_CONSTANT = 23060;`, declared with the reserved word `final`, a name such as `PI` is called a named constant and a value such as `3.14159` a literal constant. A local constant inside a method takes only `final`, whereas a constant at the class level shared by all methods, as in the code above, is declared `static final`.

---

<br>

## 4. Operators

### 4.1 Arithmetic Operators

| Operator | Operation | Example |
|:---------|:----------|:--------|
| `+` | Addition | `5 + 3` = 8 |
| `-` | Subtraction | `5 - 3` = 2 |
| `*` | Multiplication | `5 * 3` = 15 |
| `/` | Division | `7 / 2` = 3 (integer division) |
| `%` | Modulus | `7 % 2` = 1 |

*Table 3. Arithmetic operators of Java*

With x = 10, y = 7, and z = 2.5, `x + y` is 17, `x - y` is 3, `x * y` is 70, `x / y` is 1, `x / z` is 4.0, and `x % y` is 3. `x / y` is an integer division that truncates the fractional part because both operands are integers, whereas `x / z` is a real division because one operand is a real number.

### 4.2 Operator Precedence

`x + 3 * y` computes `3 * y` first and then adds it to x. The precedence rules determine the order of evaluation. A higher precedence operator is evaluated before a lower one, and operators of the same precedence are evaluated left to right for most operators.

| Order | Group | Operator | Rule |
|:-----|:-----|:-----|:-----|
| High | Subexpression | `( )` | Subexpressions are evaluated first. If parentheses are nested, the innermost is evaluated first; if several are on the same level, they are evaluated from left to right. |
| | Unary operator | `-`, `+` | Unary minuses and pluses are evaluated second. |
| | Multiplicative operator | `*`, `/`, `%` | Evaluated third; several of them are evaluated from left to right. |
| Low | Additive operator | `+`, `-` | Evaluated last; several of them are evaluated from left to right. |

*Table 4. Precedence of arithmetic operators*

### 4.3 Assignment Operators

```java
a += 3;   // a = a + 3
b *= 3;   // b = b * 3
c %= 2;   // c = c % 2
```

Starting from `int a = 3, b = 3, c = 3;`, after the three statements above `a` is 6, `b` is 9, and `c` is 1.

> **Note:** A compound assignment operator converts the result to the type of the left-hand variable automatically. Precisely, for an `int` variable `a`, `a += x` equals `a = (int) (a + x)`, so `int a = 5; a += 2.7;` compiles and `a` becomes 7. In contrast, `a = a + 2.7;` assigns a `double` to an `int` and is a compile error (Section 5).

### 4.4 Increment and Decrement

```java
int d = 3;
int a = d++;  // a = 3, d = 4 (post-increment: use then increment)
a = ++d;      // d = 5, a = 5 (pre-increment: increment then use)
```

The decrement operator `--` works the same way. Continuing the code above, the following lines give the results in the comments (lab code: [OperatorDemo.java](../../Coursework/Lab03_Java-Basic-Grammar/OperatorDemo.java)).

```java
a = d--;      // a = 5, d = 4 (post-decrement: use then decrement)
a = --d;      // d = 3, a = 3 (pre-decrement: decrement then use)
```

### 4.5 Relational and Logical Operators

| Category | Operators |
|:---------|:----------|
| Relational | `==`, `!=`, `<`, `>`, `<=`, `>=` |
| Logical | `&&` (AND), `\|\|` (OR), `!` (NOT) |

*Table 5. Relational and logical operators*

### 4.6 The Two Meanings of +

The plus operator `+` can mean two different operations depending on the context. `<val1> + <val2>` is an addition if both values are numbers, and a concatenation if either one is a `String`. Evaluation goes from left to right.

```java
String output1 = "test" + 1 + 2;   // "test12"
String output2 = 1 + 2 + "test";   // "3test"
```

For `output1`, `"test" + 1` first becomes `"test1"`, and then 2 is concatenated. For `output2`, `1 + 2` is first added to 3, which is then concatenated with `"test"`.

---

<br>

## 5. Type Casting

```java
// Implicit (widening): smaller -> larger (automatic)
int i = 10;
double d = i;  // 10.0

// Explicit (narrowing): larger -> smaller (requires cast)
double pi = 3.14;
int truncated = (int) pi;  // 3
```

- **Mixed expressions and promotion rules:** If x is a `float` and y is an `int`, the result of `x * y` is a `float`. An expression that mixes operands of different data types is called a mixed expression. The operands of a mixed expression are converted by the promotion rules, which make the data type of the expression the same as that of the operand with the highest precision.
- **Explicit type casting:** Instead of relying on the promotion rules, we can convert directly with the form `(<data type>) <expression>`. `(float) x / 3` converts x to `float` and then divides it by 3, and `(int) (x / y * 3.0)` converts the result of the expression `x / y * 3.0` to `int`. Converting a real number to an integer truncates the fractional part.
- **Implicit type casting:** In `double x = 3 + 5;`, the result of `3 + 5` is the `int` value 8, but because the variable x is a `double`, 8 is promoted to 8.0 before being assigned. Promotion happens automatically, but demotion does not. Assigning a higher precision value to a lower precision variable, as in `int x = 3.5;`, is a compile error; use an explicit cast when the conversion is intended.

---

<br>

## 6. Console and Dialog I/O

### 6.1 Standard Output

`showMessageDialog` is intended for displaying short one-line messages, not as a general-purpose output mechanism. Using `System.out`, we can output multiple lines of text to the standard output window. The look of the standard output window depends on the tool you use (a command prompt, the Console of Eclipse, and so on). `println` moves to a new line after printing, and `print` does not.

```java
int x = 123, y = x + x;
System.out.println("Hello, Dr. Caffeine.");
System.out.print(" x = ");
System.out.println(x);
System.out.print(" x + x = ");
System.out.println(y);
System.out.println(" THE END");
```

```text
Hello, Dr. Caffeine.
 x = 123
 x + x = 246
 THE END
```

### 6.2 Formatting Numbers with DecimalFormat

The format of numerical output is set with a `java.text.DecimalFormat` object. The pattern `"0.000"` rounds and displays three decimal places.

```java
double num = 123.45789345;
DecimalFormat df = new DecimalFormat("0.000"); // three decimal places
System.out.print(num);             // 123.45789345
System.out.print(df.format(num));  // 123.458
```

### 6.3 Scanner for Console Input

The technique of using `System.in` to input data is called standard input. Using `System.in` directly inputs only a single byte at a time, so to input primitive data values we use the `Scanner` class, available from Java 5.0.

```java
import java.util.Scanner;

Scanner scanner = new Scanner(System.in);
int time = scanner.nextInt();
String line = scanner.nextLine();
scanner.close();
```

| Method | Example |
|:-----|:-----|
| `nextByte()` | `byte b = scanner.nextByte();` |
| `nextDouble()` | `double d = scanner.nextDouble();` |
| `nextFloat()` | `float f = scanner.nextFloat();` |
| `nextInt()` | `int i = scanner.nextInt();` |
| `nextLong()` | `long l = scanner.nextLong();` |
| `nextShort()` | `short s = scanner.nextShort();` |
| `next()` | `String str = scanner.next();` |
| `nextLine()` | `String line = scanner.nextLine();` |

*Table 6. Common Scanner methods*

`next()` reads one word delimited by whitespace, and `nextLine()` reads the rest of the line.

> **Caution:** `nextInt()` reads only the number and leaves the following newline. So if `nextLine()` is called right after `nextInt()` as in the code above, `line` gets the empty string left after `25`, not the next line, even when the input is the two lines `25` and `hello world`. To read the next line, call `nextLine()` once more to consume the remaining newline first.

### 6.4 JOptionPane for GUI Dialogs

```java
String input = JOptionPane.showInputDialog(null, "Enter radius:");
double radius = Double.parseDouble(input);
```

`showInputDialog` always returns a `String`, so assigning its result directly to an `int` variable as below is a type mismatch and a compile error.

```java
int age;
age = JOptionPane.showInputDialog(null, "Enter your age");   // error: a String cannot be assigned to an int
```

Converting a `String` to a numerical value uses the conversion methods of the wrapper classes.

```java
int age;
String inputStr;
inputStr = JOptionPane.showInputDialog(null, "Enter your age");
age = Integer.parseInt(inputStr);
```

| Class | Method | Example |
|:-----|:-----|:-----|
| `Integer` | `parseInt` | `Integer.parseInt("25")` → 25, `Integer.parseInt("25.3")` → error |
| `Long` | `parseLong` | `Long.parseLong("25")` → 25L, `Long.parseLong("25.3")` → error |
| `Float` | `parseFloat` | `Float.parseFloat("25.3")` → 25.3F, `Float.parseFloat("ab3")` → error |
| `Double` | `parseDouble` | `Double.parseDouble("25")` → 25.0, `Double.parseDouble("ab3")` → error |

*Table 7. String conversion methods of the wrapper classes*

An error in the table means that `NumberFormatException` is thrown at run time.

The following program inputs a radius and computes the area and circumference of a circle (lab code: [CircleCalculator.java](../../Coursework/Lab03_Java-Basic-Grammar/CircleCalculator.java)).

```java
import javax.swing.JOptionPane;

public class Chap3Program1 {

    static final double PI = 3.141592;

    public static void main(String[] args) {

        double radius, area, circumference;

        String radiusStr = JOptionPane.showInputDialog(null, "Enter radius: ");

        radius = Double.parseDouble(radiusStr);

        //Compute area and circumference
        area = PI * radius * radius;
        circumference = 2.0 * PI * radius;

        JOptionPane.showMessageDialog(null, "Given Radius: " + radius + "\n" + "Area: " + area + "\n"
        + "Circumference: " + circumference);
    }
}
```

---

<br>

## 7. String Operations

Key `String` methods are as follows.

| Method | Description |
|:-------|:-----------|
| `length()` | Returns number of characters |
| `charAt(i)` | Returns character at index `i` |
| `substring(begin, end)` | Extracts a substring |
| `indexOf(str)` | Finds position of substring |
| `toUpperCase()` | Converts to uppercase |
| `split(regex)` | Splits into array by delimiter |
| `trim()` | Removes leading/trailing whitespace |

*Table 8. Key methods of String*

---

<br>

## 8. Example: Converting Seconds to Hours, Minutes, and Seconds

This program inputs an integer number of seconds and prints how many hours, minutes, and seconds it is. For example, 5000 seconds gives 1 hour, 23 minutes, and 20 seconds (lab code: [TimeUnitConverter.java](../../Coursework/Lab03_Java-Basic-Grammar/TimeUnitConverter.java)).

```java
import java.util.Scanner;

public class Chap3Program3 {

    static final int UNIT_CHANGER = 60;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please input the seconds unit as integer value:");

        int time = scanner.nextInt();
        int second = time % UNIT_CHANGER;
        int minute = (time / UNIT_CHANGER) % UNIT_CHANGER;
        int hour = (time / UNIT_CHANGER) / UNIT_CHANGER;

        System.out.print(time + " seconds is ");
        System.out.print(hour + " hr., ");
        System.out.print(minute + " min., and ");
        System.out.print(second + " sec.");
    }
}
```

`time % 60` is the seconds left over after filling whole minutes. `time / 60` is the total number of minutes; dividing it by 60 again gives the hours as the quotient and the leftover minutes as the remainder. For 5000 seconds, 5000 % 60 = 20, 5000 / 60 = 83, 83 % 60 = 23, and 83 / 60 = 1. Every division is an integer division, so no fractional part appears.

---

<br>

## Summary

| Concept | Key Summary |
|:--------|:----------|
| Identifiers | An identifier starts with a letter, `_`, or `$`, cannot be a keyword or `true`, `false`, `null`, and is case-sensitive. |
| Primitives | There are 8 primitive types: byte, short, int, long, float, double, char, and boolean. |
| Reference types | Variables of class, array, and interface types store a reference to an object. |
| Literals | Integer literals default to `int` and real literals to `double`, and the suffixes `L` and `f` change the type. |
| Constants | Constants are declared with `final`, class constants with `static final`, and named in UPPER_SNAKE_CASE. |
| Operators | Java has arithmetic, assignment, increment/decrement, relational, and logical operators, evaluated from the highest precedence. |
| + operator | If either operand is a String, `+` becomes concatenation, evaluated from left to right. |
| Type Casting | Widening happens automatically, and narrowing requires an explicit cast. |
| Wrapper classes | `Integer.parseInt`, `Double.parseDouble`, and similar methods convert strings to numbers. |
| Scanner | Scanner reads console input with `nextInt()`, `nextDouble()`, `nextLine()`, and similar methods. |
| String | String is immutable and provides a rich API for manipulation. |

---

<br>

## Self-Check Questions

1. **Increment and decrement:** Write what each `println` in the following code prints.

   ```java
   int a, d = 3;
   a = d++; System.out.println("a = " + a + ", d = " + d);
   a = ++d; System.out.println("a = " + a + ", d = " + d);
   a = d--; System.out.println("a = " + a + ", d = " + d);
   a = --d; System.out.println("a = " + a + ", d = " + d);
   ```

   <details>
   <summary>Answer</summary>

   It prints `a = 3, d = 4`, `a = 5, d = 5`, `a = 5, d = 4`, and `a = 3, d = 3` in order. A postfix operator assigns the current value first and then increments or decrements, whereas a prefix operator increments or decrements first and then assigns the value.

   </details>

2. **The + operator:** Write the results of `"test" + 1 + 2` and `1 + 2 + "test"`, and explain why they differ.

   <details>
   <summary>Answer</summary>

   The first is `"test12"` and the second is `"3test"`. `+` is evaluated from left to right. The first expression has a `String` from the start, so 1 and 2 are concatenated to the string in turn; in the second, `1 + 2` is first an integer addition giving 3, which is then concatenated with `"test"`.

   </details>

3. **Time unit conversion:** If 7384 is entered into the program of Section 8, what are `hour`, `minute`, and `second`?

   <details>
   <summary>Answer</summary>

   `second` = 7384 % 60 = 4. Since 7384 / 60 = 123, `minute` = 123 % 60 = 3 and `hour` = 123 / 60 = 2. The result is 2 hours, 3 minutes, and 4 seconds.

   </details>

4. **Type casting:** Write each value. (a) `(int) 3.99` (b) `(float) 10 / 3` (c) `(int) (10 / 7 * 3.0)` (d) x after `double x = 3 + 5;` (e) Does `int y = 3.5;` compile?

   <details>
   <summary>Answer</summary>

   (a) The fractional part is truncated, so it is 3. (b) 10 is converted to `float` before the division, so it is 3.3333333. (c) `10 / 7` is an integer division giving 1, and 1 * 3.0 = 3.0 is converted to `int`, so it is 3. (d) The `int` value 8 is promoted to 8.0. (e) A `double` cannot be implicitly assigned to an `int`, so the compiler reports the error "possible lossy conversion from double to int".

   </details>

5. **Scanner and nextLine:** When the user enters `25` on the first line and `hello world` on the second, what is `line` after `int t = scanner.nextInt(); String line = scanner.nextLine();`? How do you read the second line?

   <details>
   <summary>Answer</summary>

   `nextInt()` reads only 25 and leaves the newline, so `nextLine()` returns the rest of that line, the empty string `""`. To read the second line, call `nextLine()` once more to consume the remaining newline and then call `nextLine()` again.

   </details>
