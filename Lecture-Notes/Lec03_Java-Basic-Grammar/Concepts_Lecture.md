# Lecture 3 — Java Basic Grammar

[Repository Guide](../../README.md) | [English](Concepts_Lecture.md) | [한국어](Concepts_Lecture.ko.md)

> **Last Updated:** 2026-11-27

> **Learning Objectives:**
> 1. Name variables, constants, and classes according to the identifier rules and naming conventions
> 2. Use Java data types, literals, operators, and type casting
> 3. Explain the difference between primitive and reference types with memory states
> 4. Apply String methods and formatting
> 5. Handle console input with Scanner class
> 6. Verify by calculation the results of integer overflow, floating-point error, integer division and remainder, and widening and narrowing conversions

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
  - [2.5 Binary Representation of Integers and Overflow](#25-binary-representation-of-integers-and-overflow)
  - [2.6 Representation and Error of Floating-Point Numbers](#26-representation-and-error-of-floating-point-numbers)
  - [2.7 Wrapper Classes and Autoboxing](#27-wrapper-classes-and-autoboxing)
- [3. Variables and Constants](#3-variables-and-constants)
- [4. Operators](#4-operators)
  - [4.1 Arithmetic Operators](#41-arithmetic-operators)
  - [4.2 Operator Precedence](#42-operator-precedence)
  - [4.3 Assignment Operators](#43-assignment-operators)
  - [4.4 Increment and Decrement](#44-increment-and-decrement)
  - [4.5 Relational and Logical Operators](#45-relational-and-logical-operators)
  - [4.6 The Two Meanings of +](#46-the-two-meanings-of-)
  - [4.7 Integer Division and the Sign of the Remainder](#47-integer-division-and-the-sign-of-the-remainder)
  - [4.8 Short-Circuit Evaluation and Bitwise Operators](#48-short-circuit-evaluation-and-bitwise-operators)
  - [4.9 Full Precedence and Associativity](#49-full-precedence-and-associativity)
- [5. Type Casting](#5-type-casting)
  - [5.1 Rules of Widening and Narrowing Conversions](#51-rules-of-widening-and-narrowing-conversions)
  - [5.2 Arithmetic Promotion and Compound Assignment](#52-arithmetic-promotion-and-compound-assignment)
  - [5.3 Ways to Convert a Real Number to an Integer](#53-ways-to-convert-a-real-number-to-an-integer)
- [6. Console and Dialog I/O](#6-console-and-dialog-io)
  - [6.1 Standard Output](#61-standard-output)
  - [6.2 Formatting Numbers with DecimalFormat](#62-formatting-numbers-with-decimalformat)
  - [6.3 Scanner for Console Input](#63-scanner-for-console-input)
  - [6.4 JOptionPane for GUI Dialogs](#64-joptionpane-for-gui-dialogs)
- [7. String Operations](#7-string-operations)
- [8. Example: Converting Seconds to Hours, Minutes, and Seconds](#8-example-converting-seconds-to-hours-minutes-and-seconds)
- [9. Common Mistakes](#9-common-mistakes)
- [Concept Applications](#concept-applications)
- [Summary](#summary)
- [Self-Check Questions](#self-check-questions)
- [References](#references)

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

```java wide-ok
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

Fields and array elements are filled with the **default value** of their type when they are created, even without an assignment. The default values are 0 for `byte`, `short`, and `int`, 0L for `long`, 0.0f for `float`, 0.0 for `double`, `'\u0000'` for `char`, `false` for `boolean`, and `null` for every reference type [1]. Local variables inside a method, in contrast, receive no default value, so reading one before assigning a value causes the following compile error.

```text
Uninit.java:4: error: variable local might not have been initialized
        System.out.println(local);
                           ^
1 error
```

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

Breaking an assignment into the steps that take place in memory gives the following. Executing `int firstNumber, secondNumber;` allocates space for the two variables, and executing `firstNumber = 234; secondNumber = 87;` stores a value in each space. Executing `number = 237;` and then `number = 35;` on the same variable `number` makes the second assignment erase the first value 237 and write 35 in its place. A primitive variable always holds exactly one value, and the previous value does not remain.

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

### 2.5 Binary Representation of Integers and Overflow

The integer types of Java represent negative numbers in **two's complement**. In n-bit two's complement the leftmost bit is the sign bit, and the negative -x of a positive number x is obtained by inverting all bits of x and adding 1. Calculating with the 8-bit `byte` gives the following.

| Value | 8-bit representation | Explanation |
|:-----|:-----|:-----|
| 5 | `0000 0101` | 4 + 1 |
| -5 | `1111 1011` | The bits of 5 inverted, `1111 1010`, plus 1 |
| 127 | `0111 1111` | Maximum value of `byte` |
| -128 | `1000 0000` | Minimum value of `byte` |
| -1 | `1111 1111` | All bits are 1 |

*Table 3. byte values in two's complement*

127 is `0111 1111`, so adding 1 gives `1000 0000`, and in two's complement this bit pattern is -128. A result that exceeds the range of the type and wraps around to the minimum side in this way is called **overflow**. The Java Language Specification defines the result of an overflowing integer addition as the low-order bits of the mathematical sum represented in a sufficiently large two's-complement format, and the integer operators do not indicate overflow in any way [1]. In other words, a wrong value results with neither an exception nor a warning.

```java
public class OverflowDemo {
    public static void main(String[] args) {
        byte b = 127;
        b++;
        System.out.println(b);
        int max = Integer.MAX_VALUE;
        System.out.println(max + 1);
        System.out.println(Integer.MIN_VALUE - 1);
        System.out.println(Math.abs(Integer.MIN_VALUE));
        long wrong = 24 * 60 * 60 * 1000 * 1000;
        long right = 24L * 60 * 60 * 1000 * 1000;
        System.out.println(wrong + " " + right);
        System.out.println(Integer.toBinaryString(-1) + " " + Integer.toBinaryString(5));
        try {
            Math.addExact(max, 1);
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }
}
```

```text
-128
-2147483648
2147483647
-2147483648
500654080 86400000000
11111111111111111111111111111111 101
integer overflow
```

- `Integer.MAX_VALUE + 1` becomes `Integer.MIN_VALUE`, and conversely `Integer.MIN_VALUE - 1` becomes `Integer.MAX_VALUE`.
- `Math.abs(Integer.MIN_VALUE)` is negative because $`2^{31}`$ is outside the range of `int` (maximum $`2^{31}-1`$). It is an edge case that follows from the property of two's complement that the negative side is one value wider.
- One day in microseconds, $`24 \times 60 \times 60 \times 1000 \times 1000 = 86{,}400{,}000{,}000`$, is within the range of `long`, but every operand in the expression for `wrong` is an `int`, so the computation is done in `int` and overflows. The conversion to `long` at the assignment applies to an already wrong result. The remainder of 86,400,000,000 divided by $`2^{32}`$ is 500,654,080, and since this value is within the range of `int` it remained as is. Writing the first operand as `24L` makes the whole expression compute in `long`.
- Methods such as `Math.addExact` and `Math.multiplyExact` throw an `ArithmeticException` when overflow occurs, so they are used in calculations where overflow must be caught.

> **Caution:** In a loop whose value keeps growing, as in the infinite-loop example of Lecture 4, Section 3.2, overflow happens silently and the value turns negative, so a condition such as `while (sum <= limit)` can stay true forever. For large values use `long` or `java.math.BigInteger`.

### 2.6 Representation and Error of Floating-Point Numbers

`float` and `double` are **floating-point numbers** in the IEEE 754 format. The 64 bits of a `double` are divided into 1 sign bit, 11 exponent bits, and 52 fraction bits, and an ordinary nonzero value (a normalized number) is stored in the binary form $`(-1)^{s} \times 1.f \times 2^{e - 1023}`$. A number that cannot be written with finitely many binary digits is therefore rounded to the nearest representable value. The decimal 0.1 continues endlessly in binary as $`0.000110011001100\ldots_2`$, so it is not stored exactly.

```java
import java.math.BigDecimal;

public class FloatDemo {
    public static void main(String[] args) {
        System.out.println(0.1 + 0.2);
        System.out.println(0.1 + 0.2 == 0.3);
        System.out.println(Math.abs((0.1 + 0.2) - 0.3) < 1e-9);
        double sum = 0.0;
        for (int i = 0; i < 10; i++) {
            sum += 0.1;
        }
        System.out.println(sum);
        float f = 16777216f;
        System.out.println(f + 1 == f);
        System.out.println(1.0 / 0 + " " + -1.0 / 0 + " " + 0.0 / 0);
        double nan = 0.0 / 0;
        System.out.println((nan == nan) + " " + Double.isNaN(nan));
        System.out.println(0.0 == -0.0);
        System.out.println(new BigDecimal("0.1").add(new BigDecimal("0.2")));
        System.out.println(new BigDecimal(0.1));
    }
}
```

```text
0.30000000000000004
false
true
0.9999999999999999
true
Infinity -Infinity NaN
false true
true
0.3
0.1000000000000000055511151231257827021181583404541015625
```

- 0.1 and 0.2 are each stored as rounded values, so their sum differs from the rounded value of 0.3 and `==` is `false`. To see whether real numbers are equal, check whether the absolute value of their difference is smaller than a sufficiently small tolerance.
- That adding 0.1 ten times gives 0.9999999999999999 instead of 1.0 is also the result of accumulated rounding error. This is why using a real number as a loop counter can change the number of iterations from what is expected.
- The fraction of `float` has 23 bits, so among the integers greater than $`2^{24} = 16{,}777{,}216`$ there are some that cannot be represented exactly. 16,777,217 is the first of them; it is rounded to 16,777,216, so `f + 1 == f` is `true`. `float` has about 7 significant digits and `double` about 15-16.
- Dividing a real number by 0 throws no exception but gives `Infinity` or `-Infinity`, and 0.0 / 0 gives `NaN`, meaning not a number. `NaN` is not equal even to itself (`nan == nan` is `false`), so it must be tested with `Double.isNaN` [1]. 0.0 and -0.0 are equal when compared with `==`.
- `BigDecimal` is a class that handles decimal numbers exactly and is used in calculations such as money, where error is not acceptable. It must be created from the string `"0.1"` to be exactly 0.1; `new BigDecimal(0.1)` carries over the already rounded `double` value as is and becomes the long value of the last line.

> **Key Point:** The error of floating-point numbers is not a defect of Java but a property of a finite binary representation. Do not compare real numbers with `==`, and handle money with integers (a `long` in the smallest currency unit) or `BigDecimal`.

### 2.7 Wrapper Classes and Autoboxing

Primitive types are not objects, so they cannot have methods and cannot be stored in collections that hold only objects (Lecture 9). Java therefore provides for each primitive type a **wrapper class** that wraps its value in an object. The wrapper classes of `byte`, `short`, `int`, `long`, `float`, `double`, `char`, and `boolean` are `Byte`, `Short`, `Integer`, `Long`, `Float`, `Double`, `Character`, and `Boolean` in that order. `Integer.parseInt` of Section 6.4 is also a class method of a wrapper class.

When a wrapper object is used where a primitive value is needed, or the other way around, the compiler converts automatically. Converting a primitive value into a wrapper object is called **autoboxing**, and the reverse is called **unboxing**.

```java
public class WrapperDemo {
    public static void main(String[] args) {
        Integer a = 127, b = 127;
        Integer c = 128, d = 128;
        System.out.println(a == b);
        System.out.println(c == d);
        System.out.println(c.equals(d));
        int sum = c + d;
        System.out.println(sum);
        System.out.println(Integer.MAX_VALUE + " " + Long.MIN_VALUE + " " + Byte.MAX_VALUE);
        System.out.println(Integer.toString(255, 2) + " " + Integer.toHexString(255) + " " + Integer.parseInt("ff", 16));
        System.out.println(Character.isDigit('7') + " " + Character.toUpperCase('q'));
        Integer missing = null;
        int value = missing;
    }
}
```

```text
true
false
true
256
2147483647 -9223372036854775808 127
11111111 ff 255
true Q
Exception in thread "main" java.lang.NullPointerException: Cannot invoke "java.lang.Integer.intValue()" because "missing" is null
	at WrapperDemo.main(WrapperDemo.java:14)
```

- `Integer a = 127;` is autoboxing, and `int sum = c + d;` unboxes the two objects and adds them.
- `a == b` is `true` and `c == d` is `false` because `==` compares identity for wrapper objects (Lecture 2, Section 2.7). The language specification requires that boxing an `int` constant expression in the range -128 to 127 always yield the same object, so 127 gives the same object, but 128 is outside that range and can give different objects [1]. Compare the values of wrapper objects with `equals`.
- The constants `MAX_VALUE` and `MIN_VALUE` give the range of each type. `Integer.toString(255, 2)` returns a binary string, and `Integer.parseInt("ff", 16)` returns the value of a hexadecimal string.
- Unboxing a `null` wrapper object calls `intValue()` internally, so a `NullPointerException` occurs. The variable name `"missing"` in the exception message appears when the class is compiled with `-g`; without `-g`, the number of the local variable appears instead (Lecture 2, Section 2.6).

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

*Table 4. Arithmetic operators of Java*

With x = 10, y = 7, and z = 2.5, `x + y` is 17, `x - y` is 3, `x * y` is 70, `x / y` is 1, `x / z` is 4.0, and `x % y` is 3. `x / y` is an integer division that truncates the fractional part because both operands are integers, whereas `x / z` is a real division because one operand is a real number.

### 4.2 Operator Precedence

`x + 3 * y` computes `3 * y` first and then adds it to x. The precedence rules determine the order of evaluation. A higher precedence operator is evaluated before a lower one, and operators of the same precedence are evaluated left to right for most operators.

| Order | Group | Operator | Rule |
|:-----|:-----|:-----|:-----|
| High | Subexpression | `( )` | Subexpressions are evaluated first. If parentheses are nested, the innermost is evaluated first; if several are on the same level, they are evaluated from left to right. |
| | Unary operator | `-`, `+` | Unary minuses and pluses are evaluated second. |
| | Multiplicative operator | `*`, `/`, `%` | Evaluated third; several of them are evaluated from left to right. |
| Low | Additive operator | `+`, `-` | Evaluated last; several of them are evaluated from left to right. |

*Table 5. Precedence of arithmetic operators*

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

*Table 6. Relational and logical operators*

### 4.6 The Two Meanings of +

The plus operator `+` can mean two different operations depending on the context. `<val1> + <val2>` is an addition if both values are numbers, and a concatenation if either one is a `String`. Evaluation goes from left to right.

```java
String output1 = "test" + 1 + 2;   // "test12"
String output2 = 1 + 2 + "test";   // "3test"
```

For `output1`, `"test" + 1` first becomes `"test1"`, and then 2 is concatenated. For `output2`, `1 + 2` is first added to 3, which is then concatenated with `"test"`.

### 4.7 Integer Division and the Sign of the Remainder

As seen in Section 4.1, `/` is integer division when both operands are integers. Integer division in Java discards the fractional part toward zero (round toward zero) [1]. So 7 / 2 is 3, and -7 / 2 is -3, which is -3.5 truncated toward zero. The result of the remainder operator `%` is defined so that the following relation always holds, and as a consequence the sign of the remainder follows the sign of the dividend (the left operand) [1].

```math
(a / b) \times b + (a \,\%\, b) = a
```

```java
public class DivDemo {
    public static void main(String[] args) {
        System.out.println(7 / 2 + " " + (-7) / 2 + " " + 7 / (-2) + " " + (-7) / (-2));
        System.out.println(7 % 2 + " " + (-7) % 2 + " " + 7 % (-2) + " " + (-7) % (-2));
        System.out.println(Math.floorDiv(-7, 2) + " " + Math.floorMod(-7, 2));
        System.out.println(7.5 % 2 + " " + 7.0 / 2);
        int n = -3;
        System.out.println((n % 2 == 1) + " " + (n % 2 != 0));
        System.out.println(Integer.MIN_VALUE / -1);
    }
}
```

```text
3 -3 -3 3
1 -1 1 -1
-4 1
1.5 3.5
false true
-2147483648
```

| a | b | a / b | a % b | Check (a / b) × b + a % b |
|-----:|-----:|-----:|-----:|:-----|
| 7 | 2 | 3 | 1 | 3 × 2 + 1 = 7 |
| -7 | 2 | -3 | -1 | (-3) × 2 + (-1) = -7 |
| 7 | -2 | -3 | 1 | (-3) × (-2) + 1 = 7 |
| -7 | -2 | 3 | -1 | 3 × (-2) + (-1) = -7 |

*Table 7. Integer division and remainder for each combination of signs*

- `Math.floorDiv` and `Math.floorMod` round the quotient down toward negative infinity. Dividing -7 by 2 gives the quotient -4, which is -3.5 rounded down, and the remainder -7 - (-4 × 2) = 1 follows the sign of the divisor. They are used when a non-negative remainder is needed even for negative numbers, as with the time on a clock.
- `%` also works on real numbers, and 7.5 % 2 is 7.5 - 3 × 2 = 1.5.
- Testing for an odd number with `n % 2 == 1` gives a wrong result for negative odd numbers, whose remainder is -1. Write `n % 2 != 0`.
- Dividing an integer by 0 throws an `ArithmeticException` for both `/` and `%` (Lecture 6, Section 3.6). As an exception, `Integer.MIN_VALUE / -1` overflows because the result $`2^{31}`$ exceeds the range, so the result equals the dividend and no exception is thrown [1].

### 4.8 Short-Circuit Evaluation and Bitwise Operators

The `&&` and `||` of Section 4.5 perform **short-circuit evaluation**. If the left operand alone determines the result, the right operand is not evaluated. If the right operand has a side effect such as a method call, this difference shows in the output.

```java
public class LogicDemo {
    static int calls = 0;

    static boolean check(boolean v) {
        calls++;
        return v;
    }

    public static void main(String[] args) {
        boolean r1 = check(false) && check(true);
        System.out.println(r1 + " calls=" + calls);
        calls = 0;
        boolean r2 = check(false) & check(true);
        System.out.println(r2 + " calls=" + calls);
        String s = null;
        System.out.println(s != null && s.length() > 0);
        System.out.println((6 & 3) + " " + (6 | 3) + " " + (6 ^ 3) + " " + (~6));
        System.out.println((1 << 4) + " " + (-16 >> 2) + " " + (-16 >>> 28));
        int x, y;
        x = y = 5;
        System.out.println(x + y);
        int i = 2;
        i = i++ + ++i;
        System.out.println(i);
        System.out.println(1 + 2 * 3 > 6 && !(4 < 2));
    }
}
```

```text
false calls=1
false calls=2
false
2 7 5 -7
16 -4 15
10
6
true
```

- In `check(false) && check(true)` the left side is `false`, so the right `check` is not called and the call count is 1. When the operands are `boolean`, `&` and `|` perform logical operations without short-circuit evaluation, so both sides are evaluated and the call count is 2.
- `s != null && s.length() > 0` is an idiom that relies on short-circuit evaluation. If `s` is `null`, the right side is not evaluated, so no `NullPointerException` occurs. Swapping the two conditions causes the exception.

When the operands are integers, `&`, `|`, `^`, and `~` are **bitwise operators** that operate bit by bit, and `<<`, `>>`, and `>>>` are **shift operators** that move bits.

| Operation | Calculation (lowest 4 bits shown) | Result |
|:-----|:-----|-----:|
| `6 & 3` | `0110 & 0011 = 0010` (bits that are 1 in both) | 2 |
| `6 \| 3` | `0110 \| 0011 = 0111` (bits that are 1 in either) | 7 |
| `6 ^ 3` | `0110 ^ 0011 = 0101` (bits that differ) | 5 |
| `~6` | Inverts every bit; in two's complement -(6 + 1) | -7 |
| `1 << 4` | Shifts left by 4 bits, $`1 \times 2^4`$ | 16 |
| `-16 >> 2` | Shifts right by 2 bits filling with the sign bit, -16 / 4 | -4 |
| `-16 >>> 28` | Shifts right by 28 bits filling with 0; the upper 4 bits of `0xFFFFFFF0` | 15 |

*Table 8. Calculations with bitwise and shift operators*

`>>` fills the vacated positions with the sign bit, so a negative number stays negative, while `>>>` fills them with 0, so even a negative number becomes a large positive number. Bitwise operations are used for flags that pack several true-or-false values into one integer and for calculations with powers of two.

### 4.9 Full Precedence and Associativity

The table of Section 4.2 covered only the arithmetic operators. All Java operators, listed from the highest precedence, are as follows. Operators on the same row have the same precedence, and associativity tells which side is grouped first when operators of the same precedence follow one another.

| Rank | Operators | Associativity |
|-----:|:-----|:-----|
| 1 | Postfix `x++`, `x--` | Left to right |
| 2 | Prefix `++x`, `--x`, unary `+`, `-`, `!`, `~`, cast `(type)` | Right to left |
| 3 | `*`, `/`, `%` | Left to right |
| 4 | `+`, `-` | Left to right |
| 5 | `<<`, `>>`, `>>>` | Left to right |
| 6 | `<`, `>`, `<=`, `>=`, `instanceof` | Left to right |
| 7 | `==`, `!=` | Left to right |
| 8 | `&` | Left to right |
| 9 | `^` | Left to right |
| 10 | `\|` | Left to right |
| 11 | `&&` | Left to right |
| 12 | `\|\|` | Left to right |
| 13 | `? :` | Right to left |
| 14 | `=`, `+=`, `-=`, `*=`, `/=`, `%=`, and so on | Right to left |

*Table 9. Precedence and associativity of Java operators*

The output of Section 4.8 confirms several of these.

- In `x = y = 5`, assignment associates from right to left, so it is `x = (y = 5)`. The value 5 of the assignment expression `y = 5` is assigned again to `x`, so `x + y` is 10.
- `1 + 2 * 3 > 6 && !(4 < 2)` is grouped in the order multiplication, addition, comparison, `&&`, giving `((1 + (2 * 3)) > 6) && (!(4 < 2))`; both `7 > 6` and `!false` are true, so the result is `true`.
- In `i = i++ + ++i` with i equal to 2, the left operand of a binary operator is fully evaluated before the right operand [1]. The left `i++` yields the value 2 and makes i equal to 3, and the right `++i` makes i equal to 4 and yields 4. The sum 6 is assigned to i. The result is well defined, but such an expression is hard to read, so real code writes increments as separate statements.

> **Caution:** Precedence decides only how operators are grouped, not the order in which operands are evaluated. Operands are evaluated from left to right. When the precedence of an expression is confusing, use parentheses to make the intent clear.

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

### 5.1 Rules of Widening and Narrowing Conversions

Among conversions between types, the direction in which the range of values grows is called a **widening conversion**, and it happens automatically in assignments and method calls. The directions of widening conversions are the following.

```text
byte ──▶ short ──▶ int ──▶ long ──▶ float ──▶ double
                    ▲
          char ─────┘
```

*Figure 2. Directions of widening conversions between primitive types*

A widening conversion does not lose the magnitude of a value, but converting an `int` or a `long` to a `float`, or a `long` to a `double`, can lose precision in the lower digits because the fraction has too few bits (Section 2.6).

```java
public class CastDemo {
    public static void main(String[] args) {
        long big = 123456789L;
        float f = big;
        System.out.println(f + " " + (long) f);
        int i = 1234567891;
        float g = i;
        System.out.println((int) g);
        System.out.println((byte) 200 + " " + (byte) 300 + " " + (short) 70000);
        char ch = 'A';
        System.out.println(ch + 1);
        System.out.println((char) (ch + 1));
        byte b = 10;
        b += 5;
        System.out.println(b);
        System.out.println((int) 3.99 + " " + (int) -3.99 + " " + Math.round(2.5) + " " + Math.round(-2.5) + " " + Math.round(2.4));
        System.out.println((int) Double.NaN + " " + (int) 1e20 + " " + (long) -1e30);
        System.out.println(Math.floor(-3.5) + " " + Math.ceil(-3.5) + " " + Math.rint(2.5));
        double avg = 7 / 2;
        double avg2 = 7 / 2.0;
        System.out.println(avg + " " + avg2);
    }
}
```

```text
1.2345679E8 123456792
1234567936
-56 44 4464
66
B
15
3 -3 3 -2 2
0 2147483647 -9223372036854775808
-4.0 -3.0 2.0
3.0 3.5
```

Converting 123456789 to `float` gives the nearest `float` value 123456792, and 1234567891 becomes 1234567936. Note that the value changed even though it was assigned without a cast.

A **narrowing conversion**, in which the range shrinks, requires an explicit cast, and a narrowing conversion between integer types keeps only the lowest n bits and discards the rest [1].

- `(byte) 200`: 200 is `1100 1000` in binary. Keeping the 8 bits as they are gives a sign bit of 1, so in two's complement the value is 200 - 256 = -56.
- `(byte) 300`: 300 is `1 0010 1100`. Only the lowest 8 bits `0010 1100` remain, giving 44 (300 - 256).
- `(short) 70000`: 70000 - 65536 = 4464.

### 5.2 Arithmetic Promotion and Compound Assignment

In a binary operation the operands are first converted to a common type. This is called **binary numeric promotion**. The rules are as follows.

1. If either operand is `double`, the other is converted to `double`.
2. Otherwise, if either is `float`, the other is converted to `float`.
3. Otherwise, if either is `long`, the other is converted to `long`.
4. Otherwise both are converted to `int`. That is, even a computation between `byte`, `short`, or `char` values gives an `int`.

Because of rule 4, the result of adding a `byte` value to a `byte` variable cannot be assigned back directly.

```text
CompoundErr.java:4: error: incompatible types: possible lossy conversion from int to byte
        b = b + 5;
              ^
1 error
```

The result of `b + 5` is an `int`, so assigning it to a `byte` requires a narrowing conversion. In contrast, `b += 5;` in the program of Section 5.1 compiles and gives 15, because, as in the note of Section 4.3, a compound assignment converts the result to the type of the left variable, like `b = (byte) (b + 5)`. A `char` is also promoted to `int` in computations, so `'A' + 1` is not the character `'B'` but 66, the code 65 of `'A'` plus 1; to get a character, cast again as in `(char) ('A' + 1)`.

It also matters that promotion happens for each operation separately. `double avg = 7 / 2;` first computes `7 / 2` as integer division giving 3 and converts it to 3.0 when assigning, so the result is not 3.5. Only by making one operand real, as in `7 / 2.0` or `(double) 7 / 2`, does the division become real division.

### 5.3 Ways to Convert a Real Number to an Integer

How a real number is converted to an integer depends on the purpose.

| Method | Rule | 3.5 | -3.5 | 2.5 |
|:-----|:-----|-----:|-----:|-----:|
| `(int) x` | Truncates toward zero | 3 | -3 | 2 |
| `Math.floor(x)` | Rounds down toward negative infinity | 3.0 | -4.0 | 2.0 |
| `Math.ceil(x)` | Rounds up toward positive infinity | 4.0 | -3.0 | 3.0 |
| `Math.round(x)` | Nearest integer, halves toward positive infinity | 4 | -3 | 3 |
| `Math.rint(x)` | Nearest integer, halves toward the even one | 4.0 | -4.0 | 2.0 |

*Table 10. Comparison of ways to convert a real number to an integer*

In the output of Section 5.1, `Math.round(2.5)` is 3, `Math.round(-2.5)` is -2, and `Math.rint(2.5)` is 2.0. `Math.floor`, `Math.ceil`, and `Math.rint` return their results as `double`, so they must be cast again to be stored in an integer variable.

The specification also defines the special cases of the cast `(int)`. `NaN` becomes 0, and a value too large for the range becomes the maximum or minimum of the type [1]. So `(int) Double.NaN` is 0, `(int) 1e20` is `Integer.MAX_VALUE`, and `(long) -1e30` is `Long.MIN_VALUE`. Unlike a narrowing conversion between integer types, which keeps the low bits, a conversion from a real number to an integer stops at the maximum and minimum.

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

The format of numerical output is set with a `java.text.DecimalFormat` object. The pattern `"0.000"` rounds and displays three decimal places. The default rounding mode, however, is `RoundingMode.HALF_EVEN`: when the discarded part is exactly one half, the value is rounded toward the even last digit (for example, 0.0625 becomes 0.062 and 0.1875 becomes 0.188).

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

*Table 11. Common Scanner methods*

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

*Table 12. String conversion methods of the wrapper classes*

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

*Table 13. Key methods of String*

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

## 9. Common Mistakes

| Mistake | Result | Fix |
|:-----|:-----|:-----|
| Reading a local variable without initializing it | Compile error `variable might not have been initialized` | Give an initial value at the declaration |
| Assigning a large product of `int` values to a `long` | The computation overflows in `int` before the assignment | Write the first operand as a `long`, as in `24L` |
| Computing an integer average as `sum / n` | The fractional part is discarded | Make one side real, as in `(double) sum / n` |
| Comparing real numbers with `==` | 0.1 + 0.2 == 0.3 is `false` | Compare the absolute difference with a tolerance |
| Computing money with `double` | Rounding errors accumulate | Use a `long` in the smallest unit or `BigDecimal` |
| Testing for odd numbers with `n % 2 == 1` | `false` for negative odd numbers | Use `n % 2 != 0` |
| Comparing `Integer` objects with `==` | Equal up to 127 but possibly `false` from 128 | Compare with `equals` or as unboxed `int` values |
| Writing `byte b; b = b + 1;` | `possible lossy conversion from int to byte` | Write `b += 1;` or `(byte) (b + 1)` |
| Taking `'A' + 1` as a character | 66 is printed | Cast as in `(char) ('A' + 1)` |

*Table 14. Common mistakes concerning types and operators*

---

<br>

## Concept Applications

**True/False Check:**

| Statement | Judgment and Reason |
|:-----|:-----|
| Computing `Integer.MAX_VALUE + 1` throws an `ArithmeticException`. | **F.** Integer overflow is not reported, and the result is `Integer.MIN_VALUE`. To detect it, use `Math.addExact`. |
| `-7 / 2` is -4. | **F.** Integer division in Java truncates toward zero, so it is -3. -4 is the result of `Math.floorDiv(-7, 2)`. |
| `-7 % 3` is -1. | **T.** The sign of the remainder follows the dividend, and (-2) × 3 + (-1) = -7. |
| Assigning a `long` to a `float` is a widening conversion, so the value does not change. | **F.** It happens automatically, but the fraction of `float` has 23 bits (24 bits with the hidden bit), so large values lose precision. 123456789 becomes 123456792. |
| `(byte) 130` is -126. | **T.** 130 is `1000 0010`, and its sign bit is 1, so it is 130 - 256 = -126. |
| `s.length() > 0 && s != null` returns `false` when `s` is `null`. | **F.** The left `s.length()` is evaluated first, so a `NullPointerException` occurs. Only with the order swapped does short-circuit evaluation protect the call. |
| Dividing a real number by 0 throws an `ArithmeticException`. | **F.** Real division throws no exception and gives one of `Infinity`, `-Infinity`, or `NaN`. The exception occurs only in integer division. |
| `x = y = z = 0;` compiles and sets all three variables to 0. | **T.** Assignment associates from right to left and an assignment expression has the assigned value, so 0 is assigned to `z`, `y`, and `x` in that order. |

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
| Default values | Fields and array elements receive default values such as 0, `false`, `'\u0000'`, and `null`, but a local variable cannot be read before it is assigned. |
| Two's complement and overflow | Integers are represented in two's complement and wrap around to the other end without an exception when they exceed the range. |
| Floating-point error | Decimal numbers such as 0.1 are not stored exactly in binary, so real numbers are compared with a tolerance and money is handled with `BigDecimal` or integers. |
| Wrapper classes | Each primitive type has a wrapper class that wraps it in an object, autoboxing and unboxing happen automatically, and wrapper objects are compared with `equals`. |
| Integer division and remainder | The quotient is truncated toward zero, the sign of the remainder follows the dividend, and `floorDiv` and `floorMod` round down. |
| Short-circuit evaluation | `&&` and `\|\|` skip the right side when the left side decides the result, while `&` and `\|` evaluate both sides. |
| Precedence and associativity | Precedence decides grouping, associativity decides the direction of grouping within one rank, and the prefix and unary operators, `? :`, and assignment associate from right to left. |
| Narrowing conversion | A narrowing conversion between integer types keeps only the low bits, and a conversion from real to integer truncates toward zero and stops at the ends of the range. |
| Binary numeric promotion | The operands of a binary operation are converted to a common type in the order `double`, `float`, `long`, `int`, and even a computation between `byte` values gives an `int`. |

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

6. **Calculating overflow:** Find the value of `b` after `int a = 2_000_000_000; int b = a + a;` runs. What are the values of `long c = a + a;` and `long d = (long) a + a;`?

   <details>
   <summary>Answer</summary>

   The mathematical sum 4,000,000,000 exceeds the maximum of `int`, 2,147,483,647. Only the lowest 32 bits remain, so `b` is 4,000,000,000 - $`2^{32}`$ = 4,000,000,000 - 4,294,967,296 = -294,967,296. `c` is also -294,967,296 because `a + a` is computed in `int` first. `d` converts `a` to `long` before adding, so it is 4,000,000,000.

   </details>

7. **Division and remainder:** Write each value. (a) `-17 / 5` (b) `-17 % 5` (c) `17 % -5` (d) `Math.floorMod(-17, 5)` (e) `17 / 5 * 5.0`

   <details>
   <summary>Answer</summary>

   (a) -3.4 truncated toward zero is -3. (b) From (-3) × 5 + r = -17, r = -2. (c) 17 / -5 = -3, and from (-3) × (-5) + r = 17, r = 2. (d) Taking the quotient rounded down as -4 gives -17 - (-4 × 5) = 3. (e) `17 / 5` is first integer division giving 3, and 3 × 5.0 = 15.0.

   </details>

8. **Tracing conversions:** Write the output of the following code.

   ```java
   char c = 'a';
   int code = c + 2;
   byte small = (byte) (code * 2);
   System.out.println(code + " " + (char) code + " " + small);
   ```

   <details>
   <summary>Answer</summary>

   The code of `'a'` is 97, so `code` is 99, and `(char) 99` is `'c'`. 99 × 2 = 198 is outside the range of `byte`, so the lowest 8 bits `1100 0110` remain, giving 198 - 256 = -58. The output is `99 c -58`.

   </details>

---

<br>

## References

1. J. Gosling, B. Joy, G. Steele, G. Bracha, A. Buckley, D. Smith, and G. Bierman, *The Java Language Specification, Java SE 21 Edition*. Oracle, 2023. [Online]. Available: https://docs.oracle.com/javase/specs/jls/se21/html/index.html
