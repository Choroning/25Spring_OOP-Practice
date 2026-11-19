# Lecture 8 — Advanced Classes II

[Repository Guide](../../README.md) | [English](Concepts_Lecture.md) | [한국어](Concepts_Lecture.ko.md)

> **Last Updated:** 2026-11-19

> **Learning Objectives:**
> 1. Describe how objects are returned from methods and how the reserved word this is used
> 2. Define overloaded methods and constructors and chain constructors with this()
> 3. Explain when garbage collection reclaims an object
> 4. Organize classes into a package and document them with javadoc comments
> 5. Define a subclass that extends a superclass with extends and super()
> 6. Compile and run classes in several packages using packages, import, package access, and the class path

---

## Table of Contents

- [1. Returning an Object from a Method](#1-returning-an-object-from-a-method)
  - [1.1 Method Chaining by Returning this](#11-method-chaining-by-returning-this)
  - [1.2 Static Factory Methods](#12-static-factory-methods)
- [2. The Reserved Word this](#2-the-reserved-word-this)
  - [2.1 this as a Self-Reference](#21-this-as-a-self-reference)
  - [2.2 Calling a Constructor with this()](#22-calling-a-constructor-with-this)
- [3. Overloading Methods and Constructors](#3-overloading-methods-and-constructors)
  - [3.1 Overloading Rules](#31-overloading-rules)
  - [3.2 Return Types and Overloading](#32-return-types-and-overloading)
  - [3.3 Calling Overloaded Methods](#33-calling-overloaded-methods)
  - [3.4 Overloading and Automatic Type Conversion](#34-overloading-and-automatic-type-conversion)
- [4. The Dice Class: A Practical Example](#4-the-dice-class-a-practical-example)
- [5. Object Destruction and Garbage Collection](#5-object-destruction-and-garbage-collection)
  - [5.1 Reachability and Deciding What Is Garbage](#51-reachability-and-deciding-what-is-garbage)
  - [5.2 finalize and Releasing Resources](#52-finalize-and-releasing-resources)
- [6. Access Modifiers and Packages](#6-access-modifiers-and-packages)
  - [6.1 Access Modifiers Review](#61-access-modifiers-review)
  - [6.2 Packages](#62-packages)
  - [6.3 Package Names and Directory Structure](#63-package-names-and-directory-structure)
  - [6.4 Forms of import and Name Conflicts](#64-forms-of-import-and-name-conflicts)
  - [6.5 Package Access and the Class Path](#65-package-access-and-the-class-path)
- [7. Javadoc Comments](#7-javadoc-comments)
  - [7.1 javadoc Tags and Generating Documentation](#71-javadoc-tags-and-generating-documentation)
- [8. Inheritance Basics](#8-inheritance-basics)
  - [8.1 The extends Keyword](#81-the-extends-keyword)
  - [8.2 Superclass and Subclass Relationship](#82-superclass-and-subclass-relationship)
- [9. Constructor Chaining with super()](#9-constructor-chaining-with-super)
- [10. Class Design Best Practices](#10-class-design-best-practices)
  - [10.1 An Example of a Class Built by Composition](#101-an-example-of-a-class-built-by-composition)
- [11. Common Mistakes](#11-common-mistakes)
- [Concept Applications](#concept-applications)
- [Summary](#summary)
- [Self-Check Questions](#self-check-questions)
- [References](#references)

---

<br>

## 1. Returning an Object from a Method

Just as a method can return a primitive value, it can return an object. Returning an object actually returns a reference (address) of the object, so only a reference to the object, not a copy of it, is passed to the caller. The following `simplify` returns a new `Fraction` object holding the fraction in lowest terms.

```java
public Fraction simplify() {      // return type: the class of the returned object

    Fraction simp;

    int num   = getNumerator();
    int denom = getDenominator();
    int gcd   = gcd(num, denom);

    simp = new Fraction(num/gcd, denom/gcd);

    return simp;                  // return an instance of the Fraction class
}
```

When `f1` is 24/36, executing `f2 = f1.simplify();` creates a new `Fraction` object for 2/3 inside the method, and the local variable `simp` points to it. When the method ends, the value of `simp`, a reference, is returned to the caller and assigned to `f2`, and the memory used by `simp` is deallocated.

```text
(1) just before return
f1 ──▶ ┌───────────────┐      simp ──▶ ┌───────────────┐
       │ :Fraction     │               │ :Fraction     │
       │ numerator   24│               │ numerator   2 │
       │ denominator 36│               │ denominator 3 │
       └───────────────┘               └───────────────┘

(2) after simplify ends
f1 ──▶ ┌───────────────┐        f2 ──▶ ┌───────────────┐
       │ :Fraction     │               │ :Fraction     │
       │ numerator   24│               │ numerator   2 │
       │ denominator 36│               │ denominator 3 │
       └───────────────┘               └───────────────┘
```

*Figure 1. The reference to the Fraction object returned by simplify being assigned to f2*

The original object that `f1` points to does not change.

### 1.1 Method Chaining by Returning this

If a method returns the object itself, `this`, instead of a new object, the next message can be sent directly to that return value. Linking calls in this way, as in `a.f().g().h()`, is called **method chaining**. `StringBuilder.append` of Lecture 2, Section 4.6 works this way.

```java
public class Counter {
    private int value;

    public Counter add(int n) {
        value += n;
        return this;
    }

    public Counter reset() {
        value = 0;
        return this;
    }

    public int get() {
        return value;
    }

    public static void main(String[] args) {
        Counter c = new Counter();
        int result = c.add(5).add(10).reset().add(7).get();
        System.out.println(result);
    }
}
```

```text
7
```

`c.add(5)` makes the value of `c` 5 and returns `c` itself, so the following `.add(10)` is also sent to the same object. The value changes to 5, 15, 0, and 7, and the final `get()` returns 7. The `simplify` of Section 1 returned a new `Fraction` object and left the original unchanged, whereas the methods of `Counter` change the object and then return it. Both styles allow chaining, but in the former the original object stays as it was (an immutable object, Lecture 7, Section 8.2), while in the latter a single object keeps changing.

### 1.2 Static Factory Methods

A `static` method that creates objects is called a **static factory method**. Unlike a constructor, it can have a name, it can check or adjust the arguments before creating an object, and it can even return an existing object. `Integer.valueOf(42)`, `List.of(...)`, and `LocalDate.of(2025, 3, 10)` of Lecture 2, Section 4.8 are static factory methods. The following `Fraction` blocks its constructor with `private` and creates objects reduced to lowest terms only through `of`.

```java
public class Fraction {
    private final int numerator;
    private final int denominator;

    private Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public static Fraction of(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("denominator is zero");
        }
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
        int g = gcd(Math.abs(numerator), denominator);
        return new Fraction(numerator / g, denominator / g);
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a == 0 ? 1 : a;
    }

    public Fraction plus(Fraction other) {
        return of(numerator * other.denominator + other.numerator * denominator,
                denominator * other.denominator);
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    public static void main(String[] args) {
        Fraction a = Fraction.of(24, 36);
        Fraction b = Fraction.of(1, -6);
        System.out.println(a + " " + b + " " + a.plus(b));
        Integer x = Integer.valueOf(42);
        System.out.println(x + " " + Integer.valueOf("17"));
    }
}
```

```text
2/3 -1/6 1/2
42 17
```

- `of(24, 36)` divides by the greatest common divisor 12 (the Euclidean algorithm of Lecture 4, Section 3.10) to make 2/3, and `of(1, -6)` moves the sign of the denominator to the numerator to make -1/6. Every `Fraction` passes through `of`, so a fraction with a zero or negative denominator or one not in lowest terms cannot exist. This is the invariant of the class (Lecture 7, Section 6.1).
- `plus` passes 2/3 + (-1/6) = (2 × 6 + (-1) × 3) / (3 × 6) = 9/18 to `of`, which makes it 1/2. The fields are `final` and a new object is returned, so `Fraction` is an immutable class.
- `gcd` is a helper used only inside the class, so it is `private static` (Lecture 7, Section 5.3).

A constructor always creates a new object, but a static factory method can return an already created object for the same value. That `Integer` 127 became the same object in Lecture 3, Section 2.7 is also because boxing reuses objects in this way.

---

<br>

## 2. The Reserved Word this

### 2.1 this as a Self-Reference

The reserved word `this` is called a **self-referencing pointer** because, inside an object's method, it refers to that object itself. Even within the same method, the object that `this` refers to depends on which object the method was called on.

```java
public class Circle {
    int radius;
    public Circle(int radius) {
        this.radius = radius;
    }
    public void set(int radius) {
        this.radius = radius;
    }

    public static void main(String[] args) {
        Circle ob1 = new Circle(1);
        Circle ob2 = new Circle(2);
        Circle ob3 = new Circle(3);

        ob1.set(4);
        ob2.set(5);
        ob3.set(6);
    }
}
```

While `ob1.set(4)` runs, `this` refers to `ob1`, so the `radius` of `ob1` changes from 1 to 4. Likewise, `ob2.set(5)` and `ob3.set(6)` change the `radius` of `ob2` and `ob3` to 5 and 6. The parameter `radius` hides the data member `radius`, so the data member needs `this.` (Lecture 7, Section 4.2).

`this` is used in the following three ways.

1. It refers to a data member, as in `this.radius` (and to a method of the same object, as in `this.method()`).
2. `this(...)` calls another constructor of the same class (Section 2.2).
3. It passes the current object to another method, as in `someMethod(this)`, or returns it with `return this;`.

### 2.2 Calling a Constructor with this()

`this()` is easily confused with `this` that refers to an object, but it is a **function call** that forces a call to another constructor of the same class. `this()` can be used only inside a constructor and must be placed on the first line of the constructor; otherwise the compiler reports an error.

```java
public class Bicycle {

    protected String ownerName;

    public Bicycle() {
        this("Unknown");            // calls Bicycle(String)
    }

    public Bicycle(String ownerName) {
        this.ownerName = ownerName;
    }
    ...
}
```

The no-argument constructor does not do the initialization itself but delegates it to the parameterized constructor with `this("Unknown")`. The initialization code exists in only one place, so nothing is duplicated. With this `Bicycle`, the owner name can be given at creation time as follows.

```java
Bicycle bike1, bike2;

bike1 = new Bicycle("Minseok Seo"); // Create and assign values to bike1
bike2 = new Bicycle("IU"); // Create and assign values to bike2

System.out.println(bike1.getOwnerName() + " owns a bicycle.");
System.out.println(bike2.getOwnerName() + " also owns a bicycle.");
```

> **Note:** **Java versions and the first-statement rule.** The rule that `this()` and `super()` (Section 9) must be the first statement of a constructor holds through Java 24. From Java 25, statements that do not use the object under construction (such as argument checks) may come before them [1]. In Java 24, placing a statement before `this()` gives the error `flexible constructors is a preview feature`.

---

<br>

## 3. Overloading Methods and Constructors

### 3.1 Overloading Rules

Methods can share the same name as long as one of the following holds.

- Rule 1: They have a different number of parameters.
- Rule 2: When the number of parameters is the same, the parameters are of different data types.

The same rules apply to overloaded constructors. These rules are how a class can have more than one constructor (`Bicycle()` and `Bicycle(String)` in Section 2.2).

### 3.2 Return Types and Overloading

The return type is not a criterion for overloading.

```java
// Good overloading

class MethodOverloading {
   public int getSum(int i, int j) {
     return i + j;
   }
   public int getSum(int i, int j, int k) {
     return i + j + k;
   }
}
```

```java
// Error!!!

class MethodOverloadingFail {
   public int getSum(int i, int j) {
     return i + j;
   }
   public double getSum(int i, int j) {
     return (double)(i + j);
   }
}
```

The first class overloads correctly because the numbers of parameters differ (Rule 1). The two methods of the second class have the same parameter list and differ only in return type, so the call `getSum(1, 2)` alone cannot tell which method is meant. The compiler reports `method getSum(int,int) is already defined`.

### 3.3 Calling Overloaded Methods

The compiler chooses the method to call from the number and data types of the arguments in the call.

```java
public class MethodSample {
    public int getSum(int i, int j) {
        return i + j;
    }
    public int getSum(int i, int j, int k) {
        return i + j + k;
    }
    public double getSum(double i, double j) {
        return i + j;
    }
}

public static void main(String args[]) {
    MethodSample a = new MethodSample();
    int i = a.getSum(1, 2);           // getSum(int, int)
    int j = a.getSum(1, 2, 3);        // getSum(int, int, int)
    double k = a.getSum(1.1, 2.2);    // getSum(double, double)
}
```

`i` becomes 3 and `j` becomes 6. `k` is close to 3.3, but because of binary floating-point error it prints as `3.3000000000000003`.

### 3.4 Overloading and Automatic Type Conversion

When the types of the arguments do not exactly match the parameters, which of the overloaded methods the compiler chooses is decided by the three-phase rule of Lecture 6, Section 2.8. A method matched by widening is chosen before one matched by boxing, and one matched by boxing before a variable-arity method. A conspicuous example of this rule in the standard library is `System.out.println`, which has overloads taking `boolean`, `char`, `int`, `long`, `float`, `double`, `char[]`, `String`, and `Object`.

```java
public class PrintOverload {
    public static void main(String[] args) {
        char[] letters = {'J', 'a', 'v', 'a'};
        System.out.println(letters);
        String s = "letters: " + letters;
        System.out.println(s.startsWith("letters: [C@"));
        System.out.println(String.valueOf(letters).length());
        Object o = letters;
        System.out.println(o.toString().startsWith("[C@"));
        System.out.println('a' + 'b');
        System.out.println("" + 'a' + 'b');
    }
}
```

```text
Java
true
4
true
195
ab
```

- `println(letters)` matches `println(char[])` exactly, so it joins the characters of the array and prints `Java`. Arrays of other types (`int[]`) are taken by `println(Object)`, so `[I@...` is printed as in Lecture 5, Section 1.6.
- `"letters: " + letters` is a string concatenation, so the `toString()` of the array is called, giving `[C@` followed by hexadecimal digits. The same array produces different output depending on which method it passes through.
- After `Object o = letters;`, the compiler chooses the method by the declared type `Object` of the variable. Overloading is decided by the types fixed at compile time, not by the object at run time.
- In `'a' + 'b'` the two `char` values are promoted to `int` (Lecture 3, Section 5.2), giving 97 + 98 = 195, and `println(int)` is called. Putting `""` in front makes it a string concatenation, giving `ab`.

---

<br>

## 4. The Dice Class: A Practical Example

A `Dice` class encapsulates randomness and demonstrates clean class design:

```java
import java.util.Random;

public class Dice {
    private static final int DEFAULT_FACES = 6;
    private final int faces;
    private final Random random;

    public Dice() {
        this(DEFAULT_FACES);
    }

    public Dice(int faces) {
        this.faces = faces;
        this.random = new Random();
    }

    public int roll() {
        return random.nextInt(faces) + 1;
    }
}
```

The constructors are overloaded, so `new Dice()` creates a six-sided die and `new Dice(20)` a twenty-sided one, and the no-argument constructor delegates initialization with `this(DEFAULT_FACES)`. `random.nextInt(faces)` returns an integer from 0 up to but not including `faces`, so `roll()` gives a value from 1 to `faces`. All fields are `final`, so the number of faces never changes after construction. If `faces` is 0 or less, `nextInt` throws `IllegalArgumentException`, so in practice it is safer to check the value in the constructor. The lab code that checks in the constructor that the number of faces is at least 1 and adds Javadoc comments is [Dice.java](../../Coursework/Lab08_Advanced-Classes-II/Dice.java), and its `main` rolls a six-sided die and a twenty-sided die once each.

---

<br>

## 5. Object Destruction and Garbage Collection

> **Definition:** **Object destruction** is returning the memory of an object allocated with `new` to the available memory of the Java virtual machine (JVM), and only the JVM can do it.

> **Definition:** **Garbage** is an object with no reference to it.

> **Definition:** **Garbage collection** is the process in which the JVM's garbage collector automatically finds garbage and returns it to available memory.

The following code shows when memory becomes eligible for reclamation.

```java
public class GarbageEx {
  public static void main(String[] args) {
     String a = new String("Good");
     String b = new String("Bad");
     String c = new String("Normal");
     String d, e;
     a = null;
     d = c;
     c = null;
  }
}
```

```text
(1) before the assignments
a ──▶ "Good"
b ──▶ "Bad"
c ──▶ "Normal"
d
e

(2) after a = null; d = c; c = null;
a     null       "Good"
b ──▶ "Bad"
c     null   ┌─▶ "Normal"
d ───────────┘
e
```

*Figure 2. The `"Good"` object becoming garbage as references change*

`a = null;` removes the only reference to the `"Good"` object, so that object becomes garbage. The `"Normal"` object is not garbage even after `c = null;` because `d` still points to it. `d` and `e` are only declared, and `d` first receives a value in `d = c;`.

A developer can request garbage collection by calling `System.gc()` or `Runtime.getRuntime().gc()`. This is only a request, however, and the JVM decides whether to actually perform it.

### 5.1 Reachability and Deciding What Is Garbage

The "object with no reference to it" in the definition of Section 5 is, more precisely, an **unreachable** object. The language specification defines a **reachable** object as one that can be accessed in any potential continuing computation from any live thread [2]. In practice, objects that can be reached by following references from starting points such as the local variables and parameters of running method frames and the class variables (`static` fields) are reachable, and all other objects are garbage.

Because of this definition, objects that refer to each other can still become garbage.

```text
 (1) after a.next = b; b.next = a;

 a ●──▶ [Node1] ──next──▶ [Node2] ◀──● b
           ▲                  │
           └──────next────────┘

 (2) after a = null; b = null;

 a  null                         b  null
        [Node1] ──next──▶ [Node2]
           ▲                  │
           └──────next────────┘
```

*Figure 3. Two objects referring to each other becoming garbage once cut off from the starting points*

In (2) the two `Node` objects still hold references to each other, but there is no path from the local variables `a` and `b` to them, so they are unreachable and are garbage. Such cyclic structures, which a scheme that counts references could not reclaim, are reclaimed when garbage is decided by reachability.

Conversely, an object that is no longer used is not reclaimed as long as it is reachable. For example, if objects are only ever added to an array or collection held in a `static` field and never removed, they are reachable from the class variable and occupy memory until the program ends. Most memory leaks in languages with garbage collection take this form, and they are fixed by assigning `null` to references that are no longer needed or by removing them from the collection.

### 5.2 finalize and Releasing Resources

`Object` had a `finalize()` method, which the garbage collector calls before an object is reclaimed. However, it is not defined when it will be called or whether it will be called at all, and it can cause problems with security, performance, and reliability, so the API documentation of Java SE 21 marks this method as deprecated for removal [3]. Resources such as files and network connections must therefore not be left to be released when their objects are destroyed. Close a resource directly by calling `close()` when it is no longer needed, and use the try-with-resources of Lecture 6, Section 3.11 so that it is closed even if an exception occurs. It is worth remembering that Java has no code that is guaranteed to run when an object disappears, like the destructors of C++ (Lecture 1, Section 3.11).

---

<br>

## 6. Access Modifiers and Packages

### 6.1 Access Modifiers Review

Java has four access modifiers, `private`, default (if omitted), `protected`, and `public`, and the range of access widens in that order.

- `private`: completely blocks access from outside the class.
- default: allows access within the same package.
- `protected`: allows access from the same package and from subclasses.
- `public`: allows access from all classes.

The table of each range is in Lecture 7, Section 5.2.

### 6.2 Packages

Without packages, for a class A to use a class B, the bytecode files of both classes must be located in the same directory. This is not practical if we want to reuse programmer-defined classes in many different programs. The correct way to reuse classes is to place them in a **package**, and a package is a Java class library.

```java
package mypackage;

public class Bicycle {
    ...
}
```

The `package` statement is written as the first statement of the source file, and the compiled class file is placed in a directory with the same name as the package (`mypackage/`). A class in another package is brought in with `import mypackage.Bicycle;`, and then only the classes and members declared `public` can be used.

### 6.3 Package Names and Directory Structure

Package names are chosen so that they do not collide with other people's packages. The convention is to write them entirely in lowercase, to distinguish them from classes, and to begin them with the organization's Internet domain name reversed [4]. For example, a banking package made at `example.com` becomes `com.example.bank`. The packages of Java itself begin with `java.` or `javax.`. Each dot in a package name stands for a subdirectory, so the source and class files of the package `com.example.bank` go in the directory `com/example/bank`. The following is the directory structure of a small program made of two packages.

```text
bank/
└── src/
    └── com/
        └── example/
            ├── bank/
            │   ├── Account.java     package com.example.bank;
            │   └── Audit.java       package com.example.bank;
            └── app/
                └── Main.java        package com.example.app;
```

*Figure 4. Source directory structure of a program made of two packages*

```java
package com.example.bank;

public class Account {
    private final String owner;
    double balance;

    public Account(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }
}
```

```java
package com.example.bank;

public class Audit {
    public static void reset(Account account) {
        account.balance = 0.0;
    }
}
```

```java
package com.example.app;

import com.example.bank.Account;
import com.example.bank.Audit;
import static java.lang.Math.max;

public class Main {
    public static void main(String[] args) {
        Account a = new Account("Kim", 500.0);
        Audit.reset(a);
        java.util.List<String> names = java.util.List.of(a.getOwner());
        System.out.println(names + " " + a.getBalance() + " " + max(3, 9));
    }
}
```

The `-d out` option of `javac` creates the class files under the directory `out` following the package structure. When running, `-cp out` tells the JVM the starting directory in which to look for classes, and the class to start is written with its full name including the package name.

```text
$ javac --release 21 -d out src/com/example/bank/*.java src/com/example/app/Main.java
$ find out -name '*.class' | sort
out/com/example/app/Main.class
out/com/example/bank/Account.class
out/com/example/bank/Audit.class
$ java -cp out com.example.app.Main
[Kim] 0.0 9
```

The name of a class that belongs to a package is its **fully qualified name** including the package name, such as `com.example.app.Main`. That is why going into `out/com/example/app` and running `java Main` fails to find it. The same error occurs when the package declaration and the directory do not match; the following is the result of compiling `Loose.java`, which declares `package com.example.bank;`, in an arbitrary directory and running it.

```text
$ java Loose
Error: Could not find or load main class Loose
Caused by: java.lang.NoClassDefFoundError: Loose (wrong name: com/example/bank/Loose)
```

The name written inside `Loose.class` is `com.example.bank.Loose`, but it was looked up under the name `Loose`. To run it by its fully qualified name, the class file must be at `com/example/bank/Loose.class`, so it is safer to always compile programs with packages using `-d`.

### 6.4 Forms of import and Name Conflicts

A class of another package is either written with its fully qualified name or made usable by its simple name with `import`. The `Main` of Section 6.3 contains all of the forms.

| Form | Example | What it brings in |
|:-----|:-----|:-----|
| Single-type import | `import com.example.bank.Account;` | One class |
| Import on demand | `import java.util.*;` | All `public` classes of that package (not its subpackages) |
| Static import | `import static java.lang.Math.max;` | A `static` member of a class, used without the class name as `max(3, 9)` |
| Fully qualified name without import | `java.util.List<String> names` | Only at that place |

*Table 1. Ways to use names from other packages*

Classes of the `java.lang` package, such as `String`, `System`, and `Math`, can be used without importing them, because every source file is treated as if it contained `import java.lang.*;` [2]. `import` only lets names be shortened; it neither copies classes nor includes them in the program, so using many imports does not make the program larger or slower. This differs from `#include` in C (Lecture 1, Section 3.11). That `java.util.*` does not bring in subpackages such as `java.util.function` is also because the dots in a package name are just part of the name and do not express containment.

If two imports on demand bring in classes with the same name, a compile error occurs where that name is used. Both `java.util` and `java.sql` have a class `Date`.

```java
import java.util.*;
import java.sql.*;

public class DateClash {
    public static void main(String[] args) {
        Date d = new Date();
    }
}
```

```text
DateClash.java:6: error: reference to Date is ambiguous
        Date d = new Date();
        ^
  both class java.sql.Date in java.sql and class java.util.Date in java.util match
DateClash.java:6: error: reference to Date is ambiguous
        Date d = new Date();
                     ^
  both class java.sql.Date in java.sql and class java.util.Date in java.util match
2 errors
```

A single-type import takes precedence over imports on demand, so adding `import java.util.Date;` fixes `Date` as `java.util.Date`, and the other is written with its fully qualified name `java.sql.Date`.

```java
import java.util.*;
import java.sql.*;
import java.util.Date;

public class DateOk {
    public static void main(String[] args) {
        Date d = new Date(0);
        java.sql.Date s = new java.sql.Date(0);
        System.out.println(d.getClass().getName() + " " + s.getClass().getName());
    }
}
```

```text
java.util.Date java.sql.Date
```

### 6.5 Package Access and the Class Path

Classes, fields, and methods declared without an access modifier have **package access** and can be used only within the same package [2]. This is the (default) row of the table in Lecture 7, Section 5.2. In `Account` of Section 6.3, `balance` has no modifier, so `Audit` in the same package could change it directly with `account.balance = 0.0;`. A class in the other package `com.example.app` doing the same thing gets the following error.

```java
package com.example.app;

import com.example.bank.Account;

public class Peek {
    public static void main(String[] args) {
        Account a = new Account("Lee", 10.0);
        a.balance = 1000000.0;
    }
}
```

```text
src/com/example/app/Peek.java:8: error: balance is not public in Account; cannot be accessed from outside package
        a.balance = 1000000.0;
         ^
1 error
```

Package access lets classes of the same package cooperate closely while hiding things from outside the package. A class itself without `public` can also be used only within its package, so `public` is put only on classes to be exposed outside the package. The rule of Lecture 1, Section 3.4 that a source file can have only one `public` class is also related to this unit of exposure.

The list of directories and JAR files in which the compiler and the JVM look for class files is called the **class path**. Both look for a class file at the location formed by appending the package name, turned into directories, to each entry of the class path, and when no class path is set they look in the current directory [5]. The class path is set with the `-cp` option or the `CLASSPATH` environment variable, and multiple entries are separated by semicolons (`;`) on Windows and by colons (`:`) on Unix-like systems.

```text
$ java -cp src com.example.app.Main
Error: Could not find or load main class com.example.app.Main
Caused by: java.lang.ClassNotFoundException: com.example.app.Main
$ jar cf bank.jar -C out .
$ java -cp bank.jar com.example.app.Main
[Kim] 0.0 9
```

`src` contains only source files and no class files, so the class is not found. `jar cf bank.jar -C out .` puts the directory structure under `out` into one JAR file as is (Lecture 1, Section 3.5), and a JAR file can also be an entry of the class path. A class without a package declaration belongs to the unnamed package. Classes of the unnamed package cannot be imported by classes of named packages, so any program beyond a small example should choose its packages from the start.

---

<br>

## 7. Javadoc Comments

Classes intended for other programmers need meaningful documentation so that those programmers can understand how to use them correctly. **Javadoc comments** give the classes a consistent documentation style, and the `javadoc` command generates HTML documentation files from them.

- A javadoc comment begins with `/**` and ends with `*/`.
- Special information such as the authors, parameters, and return values is written with tags that start with the `@` marker (`@param`, `@author`, `@return`, `@version`, `@since`, and others).

```java
/**
 * The Bicycle class implements an application that
 * simply displays bike's owner based on the multiple classes
 *
 * @author Minseok Seo
 * @version 1.0
 * @since 2025-04-14
 */
public class Bicycle {

    /**
     * Data member for owner's name (String)
     */
    protected String ownerName;

    /**
     * Constructor of Bicycle class
     *
     * @param ownerName will be set on the data member
     */
    public Bicycle(String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * Returns the name of this bicycle's owner
     * @return ownerName
     */
    public String getOwnerName() {
        return ownerName;
    }
}
```

Running `javadoc -d doc Bicycle.java` generates HTML documentation in the `doc` directory with the class description, the lists of constructors and methods, and the descriptions of parameters and return values.

### 7.1 javadoc Tags and Generating Documentation

A javadoc comment is written right before the class, field, constructor, or method it describes. Its first sentence is used as a summary and appears in the listing pages, so it states in one sentence what the element does. The frequently used tags are the following.

| Tag | Where | Content |
|:-----|:-----|:-----|
| `@author`, `@version`, `@since` | Class | Author, version, and the version in which it was introduced |
| `@param name description` | Method, constructor | The meaning of one parameter (one per parameter) |
| `@return description` | Method returning a value | The meaning of the return value |
| `@throws exception description` | Method, constructor | An exception that can be thrown and its condition |
| `@see target` | Anywhere | A class or method to see together |
| `{@code code}` | Inside a description | Shown in code font |
| `{@link target}` | Inside a description | A link to another element |

*Table 2. Frequently used javadoc tags*

```java
/**
 * A thermometer that records temperatures in degrees Celsius.
 *
 * @author Example Author
 * @version 1.0
 */
public class Thermometer {

    /** The lowest temperature this thermometer accepts. */
    public static final double MIN_CELSIUS = -273.15;

    private double celsius;

    /**
     * Sets the current temperature.
     *
     * @param celsius the temperature in degrees Celsius
     * @throws IllegalArgumentException if {@code celsius} is below {@link #MIN_CELSIUS}
     */
    public void setCelsius(double celsius) {
        if (celsius < MIN_CELSIUS) {
            throw new IllegalArgumentException("below absolute zero: " + celsius);
        }
        this.celsius = celsius;
    }

    /**
     * Returns the current temperature in degrees Fahrenheit.
     *
     * @return the temperature converted with {@code F = C * 9 / 5 + 32}
     * @see #setCelsius(double)
     */
    public double getFahrenheit() {
        return celsius * 9 / 5 + 32;
    }
}
```

```text
$ javadoc -d doc Thermometer.java
Loading source file Thermometer.java...
Constructing Javadoc information...
Creating destination directory: "doc/"
Building index for all the packages and classes...
Standard Doclet version 24.0.2+12-54
Building tree for all the packages and classes...
Generating doc/Thermometer.html...
Thermometer.java:7: warning: use of default constructor, which does not provide a comment
public class Thermometer {
       ^
Generating doc/package-summary.html...
```

Only part of the output is shown. `doc/Thermometer.html` gathers the class description, the constant `MIN_CELSIUS`, and the descriptions, parameters, return values, and exceptions of the two methods, and `doc/index.html` becomes the first page of the documentation. The `private` field `celsius` is an implementation detail that clients need not know, so by default it does not appear in the documentation. The warning means that the default constructor created by the compiler (Lecture 7, Section 2.1) has no comment, and it disappears when a constructor is written with a javadoc comment. A javadoc comment is an explanation for the people who use the method, so it states what the method does and what results and exceptions it gives under which conditions, rather than how it is implemented.

---

<br>

## 8. Inheritance Basics

### 8.1 The extends Keyword

Inheritance allows a new class (subclass) to acquire the fields and methods of an existing class (superclass). A subclass is declared as `class Subclass extends Superclass`. Section 9 shows an example that uses the following `Bicycle` as the superclass.

```java
public class Bicycle {
    private String ownerName;

    public Bicycle(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerName() {
        return ownerName;
    }
}
```

### 8.2 Superclass and Subclass Relationship

- The subclass inherits the non-private members of the superclass and uses them directly. Default members, however, are accessible only when the subclass is in the same package. `private` members cannot be accessed directly in the subclass and are used through the superclass's `public` methods (such as `getOwnerName()`).
- The subclass can add new fields and methods.
- The subclass can override inherited methods.

> **Key Point:** Java supports single inheritance only: a class can extend at most one superclass. Use interfaces for multiple-type relationships.

---

<br>

## 9. Constructor Chaining with super()

Constructors are **not** inherited. Use `super()` to call the superclass constructor.

```java
public class ElectricBicycle extends Bicycle {
    private int batteryLevel;

    public ElectricBicycle(String ownerName, int battery) {
        super(ownerName);         // call Bicycle's constructor
        this.batteryLevel = battery;
    }
}
```

`ownerName` is `private` in the superclass, so `ElectricBicycle` cannot assign it directly. It therefore delegates the initialization to the superclass constructor with `super(ownerName)`.

> **Key Point:** `super()` must be the first statement in the subclass constructor (see the note in Section 2.2 for the relaxation from Java 25). If omitted, Java implicitly calls `super()` with no arguments, which fails to compile if the superclass has no no-argument constructor.

---

<br>

## 10. Class Design Best Practices

| Principle | Description |
|:----------|:------------|
| **Single Responsibility** | Each class should have one reason to change |
| **Encapsulation** | Hide internals; expose only necessary API |
| **Immutability** | Make fields `final` when they should not change after construction |
| **Favor composition** | Prefer has-a over is-a when inheritance is not a natural fit |

*Table 3. Four principles of class design*

**Composition** is a has-a relationship in which a class holds objects of other classes as fields. For example, `Dice` holds a `Random` object as a field and delegates random number generation to it. Inheritance (is-a) ties the subclass to the implementation of the superclass, so use it only when the subclass can truly be called a kind of the superclass.

### 10.1 An Example of a Class Built by Composition

Composition is a structure in which a large object has smaller objects as parts and delegates work to them. The following `Car` has an `Engine` object as a field and delegates the engine-related part of a car's work to the methods of `Engine`.

```java
public class Engine {
    private final int horsepower;
    private boolean running;

    public Engine(int horsepower) {
        this.horsepower = horsepower;
    }

    public void start() {
        running = true;
    }

    public boolean isRunning() {
        return running;
    }

    public int getHorsepower() {
        return horsepower;
    }
}
```

```java
public class Car {
    private final String model;
    private final Engine engine;

    public Car(String model, int horsepower) {
        this.model = model;
        this.engine = new Engine(horsepower);
    }

    public void drive() {
        if (!engine.isRunning()) {
            engine.start();
        }
        System.out.println(model + " drives with " + engine.getHorsepower() + " hp");
    }

    public static void main(String[] args) {
        Car car = new Car("Sedan", 150);
        car.drive();
        car.drive();
    }
}
```

```text
Sedan drives with 150 hp
Sedan drives with 150 hp
```

"A car has an engine" is a has-a relationship, and "a car is a kind of engine" does not hold, so `Car` has `Engine` as a field instead of inheriting from it. The constructor of `Car` creates the `Engine` itself and never hands it out, so the state of the engine changes only through the methods of `Car` (Lecture 7, Section 3.3). The first `drive()` starts the engine, and the second `drive()` uses the engine that is already running. Changing the implementation of the engine requires no change to `Car` as long as the public methods of `Engine` stay the same, and a car with an engine of different power is created simply by changing the constructor argument. Inheritance makes a subclass inherit and expose all the public methods of its superclass, whereas composition picks only the needed functions and exposes them wrapped in its own methods, which makes encapsulation easier to keep. The comparison with designs that use inheritance is covered in Lecture 11.

---

<br>

## 11. Common Mistakes

| Mistake | Result | Fix |
|:-----|:-----|:-----|
| A method meant for chaining returns `void` instead of `this` | Compile errors such as `void cannot be dereferenced` | Make methods meant for chaining return `this` |
| Thinking that an overloaded method is chosen by the object at run time | A different method from the one expected is called | Overloading is decided by the declared types at compile time |
| Thinking that objects referring to each other are never reclaimed | Garbage is explained incorrectly | They are reclaimed if unreachable from the starting points |
| Only ever adding objects to a `static` collection | They are not reclaimed, and memory keeps growing | Remove elements no longer needed or set references to `null` |
| Leaving the release of resources to `finalize()` | When the resource is closed is unknown | Close it directly with try-with-resources |
| The package declaration differs from the directory | `NoClassDefFoundError (wrong name: ...)` | Compile with `javac -d` and match the directories to the package name |
| Running a class in a package by its simple name | `Could not find or load main class` | Use the fully qualified name, as in `java -cp out com.example.app.Main` |
| Two imports on demand bring in the same name | `reference to Date is ambiguous` | Use a single-type import or the fully qualified name |
| Using a member without a modifier from another package | `is not public ...; cannot be accessed from outside package` | Make the member to expose `public` or go through a public method |
| Thinking that `java.util.*` also brings in subpackages | Classes of the subpackages are not found | Import subpackages separately |

*Table 4. Common mistakes with returning objects, garbage collection, and packages*

---

<br>

## Concept Applications

**True/False Check:**

| Statement | Judgment and Reason |
|:-----|:-----|
| A method ending with `return this;` creates a new object on each call. | **F.** It only returns a reference to itself, so all chained calls are sent to the same object. |
| Unlike a constructor, a static factory method can return an object created earlier. | **T.** A constructor always creates a new object, but a `static` method decides for itself what to return. |
| `Object o = new char[]{'a'}; System.out.println(o);` prints `a`. | **F.** The declared type is `Object`, so `println(Object)` is chosen and `[C@...` is printed. |
| Two objects that refer to each other are never reclaimed. | **F.** If they cannot be reached from starting points such as local variables and class variables, they are garbage. |
| Writing `import java.util.*;` includes every class of `java.util` in the program and makes the program larger. | **F.** `import` only shortens names, and classes are loaded when needed during execution (Lecture 1, Section 3.8). |
| Using `String` requires `import java.lang.String;`. | **F.** The classes of `java.lang` are imported automatically in every source file. |
| A field declared without a modifier can be accessed from a subclass in another package. | **F.** Package access is allowed only within the same package. What also allows subclasses in other packages is `protected`. |
| The class file of `com.example.bank.Account` must be at `com/example/bank/Account.class` under a class path entry. | **T.** The compiler and the JVM look at the location formed by appending the package name, turned into directories, to each class path entry. |

---

<br>

## Summary

| Concept | Key Summary |
|:--------|:----------|
| Returning objects | When a method returns an object, it returns a reference, not a copy. |
| this | It is a self-reference to the object on which the method was called. |
| this() | It calls another constructor of the same class and is placed on the first line of a constructor. |
| Overloading | Methods and constructors can share a name if the number or data types of their parameters differ, and the return type is not a criterion. |
| Garbage collection | The JVM automatically reclaims objects with no references, and `System.gc()` is only a request. |
| Packages | A package is a class library grouping reusable classes, specified with a `package` statement and brought in with `import`. |
| Javadoc | Classes are documented with `/** */` comments and `@` tags, and the `javadoc` command generates HTML. |
| Inheritance | `extends` creates an is-a relationship, and only single inheritance is supported. |
| super() | It calls the superclass constructor and must be the first statement of the subclass constructor. |
| Constructor chaining | A subclass constructor can delegate initialization to a superclass constructor, and a constructor can delegate it to another constructor of the same class. |
| Class design | Follow the single responsibility, encapsulation, immutability, and composition-first principles. |
| Method chaining | A method returning `this` lets messages be sent one after another to the same object, as in `a.f().g()`. |
| Static factory methods | A `static` method that creates objects, which has a name and can check its arguments or return existing objects. |
| Overloading and conversion | Overloading is decided by the declared types at compile time, and results can differ, as between `println(char[])` and `println(Object)`. |
| Reachability | Objects that cannot be reached by following references from local and class variables are garbage, and cyclic structures are also reclaimed. |
| finalize | It is a mechanism deprecated for removal, so resources are closed directly with try-with-resources. |
| Package names | Written in lowercase beginning with a reversed domain name, with each dot-separated part corresponding to a directory. |
| import | There are single-type, on-demand, and static imports, `java.lang` is imported automatically, and colliding names are resolved with a single-type import or a fully qualified name. |
| Package access | Classes and members without a modifier can be used only within the same package. |
| Class path | The list of directories and JARs in which class files are looked up, set with `-cp` or `CLASSPATH`, with classes run by their fully qualified names. |
| javadoc tags | Usage is described with `@param`, `@return`, `@throws`, `@see`, `{@code}`, and `{@link}`, and `javadoc -d` produces HTML. |
| Composition | A has-a relationship that keeps part objects as fields and delegates work to them, exposing only the needed functions wrapped in its own methods. |

---

<br>

## Self-Check Questions

1. **Calling overloaded methods:** For `MethodSample` in Section 3.3, which method does `a.getSum(1, 2.5)` call, and what is the result? What happens if `double getSum(int i, int j)` is added, with the same parameter list as `getSum(int, int)`?

   <details>
   <summary>Answer</summary>

   There is no `getSum(int, double)` version, so the `int` argument 1 is widened to `double`, `getSum(double, double)` is called, and the result is 3.5. Adding `double getSum(int i, int j)` gives a method with the same parameter list as the existing `int getSum(int i, int j)` and a different return type only, so the compiler reports `method getSum(int,int) is already defined`.

   </details>

2. **Garbage:** In `GarbageEx` of Section 5, which object is garbage after the last statement of `main` runs? What happens if `d = null;` is added after `c = null;`?

   <details>
   <summary>Answer</summary>

   Only the `"Good"` object is garbage. `b` points to `"Bad"`, and `d` points to `"Normal"`. Adding `d = null;` removes the last reference to `"Normal"` as well, so the `"Normal"` object also becomes garbage.

   </details>

3. **Returning objects:** In Section 1, with `f1` equal to 24/36, how many `Fraction` objects exist after `f2 = f1.simplify();` runs, and what is the value of `f1 == f2`?

   <details>
   <summary>Answer</summary>

   There are two `Fraction` objects: the original 24/36 object and the 2/3 object newly created inside `simplify`. `f1` and `f2` point to different objects, so `f1 == f2` is `false`.

   </details>

4. **Constructor chaining:** `ElectricBicycle` extends the `Bicycle` of Section 2.2 (whose no-argument constructor calls `this("Unknown")`) and has the constructor `public ElectricBicycle(int battery) { batteryLevel = battery; }`. What does `new ElectricBicycle(80).getOwnerName()` return? What happens if `Bicycle` has only the `Bicycle(String)` constructor?

   <details>
   <summary>Answer</summary>

   It returns `"Unknown"`. Because `super(...)` is omitted, the compiler inserts `super()`, and `Bicycle()` in turn calls `Bicycle(String)` through `this("Unknown")`. If only `Bicycle(String)` existed, no constructor would match the implicit `super()`, and a compile error would occur.

   </details>

5. **Position of this():** Why does `public Bicycle() { System.out.println("x"); this("Unknown"); }` not compile in Java 24?

   <details>
   <summary>Answer</summary>

   Through Java 24, the `this()` call must be the first statement of the constructor, but a `System.out.println` statement comes before it. Moving `this("Unknown");` to the first line, or moving the print statement into the `Bicycle(String)` constructor, fixes it.

   </details>

6. **Choosing an overload:** Write what each of the following lines prints.

   ```java
   char[] cs = {'o', 'k'};
   System.out.println(cs);
   System.out.println("cs=" + String.valueOf(cs));
   System.out.println('o' + 1);
   ```

   <details>
   <summary>Answer</summary>

   The first line chooses `println(char[])` and prints `ok`. In the second line `String.valueOf(char[])` builds a string from the characters, so it prints `cs=ok`. In the third line `'o'` (111) is promoted to `int` and becomes 112, and `println(int)` is called, so it prints `112`.

   </details>

7. **Deciding garbage:** Choose all the `Node` objects that are garbage when the following code ends. `Node` is a class with one field `Node next;`.

   ```java
   Node a = new Node();   // N1
   Node b = new Node();   // N2
   Node c = new Node();   // N3
   a.next = b;
   b.next = c;
   c.next = b;
   b = null;
   c = null;
   a.next = null;
   ```

   <details>
   <summary>Answer</summary>

   N2 and N3 are garbage. Until the last assignment, N1 could be reached from `a`, N2 through the `next` of N1, and N3 through the `next` of N2. After `a.next = null;`, only `a` among the local variables, the starting points, refers to N1, and N2 and N3 refer to each other but cannot be reached from any starting point.

   </details>

8. **Packages and running:** Write the commands to compile `src/edu/school/Grade.java` (`package edu.school;`) and `src/edu/school/app/Report.java` (`package edu.school.app;`, containing `main`) into the directory `out` and to run the program. What does `Report` need in order to use `Grade`?

   <details>
   <summary>Answer</summary>

   Compiling with `javac -d out src/edu/school/Grade.java src/edu/school/app/Report.java` creates `out/edu/school/Grade.class` and `out/edu/school/app/Report.class`. The program is run with `java -cp out edu.school.app.Report`. `edu.school.app` is not a subpackage of `edu.school` but a separate package, so `Report.java` must contain `import edu.school.Grade;`, and `Grade` and the members `Report` uses must be `public`.

   </details>

---

<br>

## References

1. A. Cobbs and G. Bierman, "JEP 513: Flexible Constructor Bodies," OpenJDK, 2024. [Online]. Available: https://openjdk.org/jeps/513
2. J. Gosling, B. Joy, G. Steele, G. Bracha, A. Buckley, D. Smith, and G. Bierman, *The Java Language Specification, Java SE 21 Edition*. Oracle, 2023. [Online]. Available: https://docs.oracle.com/javase/specs/jls/se21/html/index.html
3. Oracle, "Object (Java SE 21 & JDK 21)," *Java SE 21 API Specification*. [Online]. Available: https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/Object.html
4. Oracle, "Naming a Package," *The Java Tutorials*. [Online]. Available: https://docs.oracle.com/javase/tutorial/java/package/namingpkgs.html
5. Oracle, "Managing Source and Class Files," *The Java Tutorials*. [Online]. Available: https://docs.oracle.com/javase/tutorial/java/package/managingfiles.html
