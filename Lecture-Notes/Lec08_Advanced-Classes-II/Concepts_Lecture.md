# Lecture 8 — Advanced Classes II

[Repository Guide](../../README.md) | [English](Concepts_Lecture.md) | [한국어](Concepts_Lecture.ko.md)

> **Last Updated:** 2026-10-19

> **Learning Objectives:**
> 1. Describe how objects are returned from methods and how the reserved word this is used
> 2. Define overloaded methods and constructors and chain constructors with this()
> 3. Explain when garbage collection reclaims an object
> 4. Organize classes into a package and document them with javadoc comments
> 5. Define a subclass that extends a superclass with extends and super()

---

## Table of Contents

- [1. Returning an Object from a Method](#1-returning-an-object-from-a-method)
- [2. The Reserved Word this](#2-the-reserved-word-this)
  - [2.1 this as a Self-Reference](#21-this-as-a-self-reference)
  - [2.2 Calling a Constructor with this()](#22-calling-a-constructor-with-this)
- [3. Overloading Methods and Constructors](#3-overloading-methods-and-constructors)
  - [3.1 Overloading Rules](#31-overloading-rules)
  - [3.2 Return Types and Overloading](#32-return-types-and-overloading)
  - [3.3 Calling Overloaded Methods](#33-calling-overloaded-methods)
- [4. The Dice Class: A Practical Example](#4-the-dice-class-a-practical-example)
- [5. Object Destruction and Garbage Collection](#5-object-destruction-and-garbage-collection)
- [6. Access Modifiers and Packages](#6-access-modifiers-and-packages)
  - [6.1 Access Modifiers Review](#61-access-modifiers-review)
  - [6.2 Packages](#62-packages)
- [7. Javadoc Comments](#7-javadoc-comments)
- [8. Inheritance Basics](#8-inheritance-basics)
  - [8.1 The extends Keyword](#81-the-extends-keyword)
  - [8.2 Superclass and Subclass Relationship](#82-superclass-and-subclass-relationship)
- [9. Constructor Chaining with super()](#9-constructor-chaining-with-super)
- [10. Class Design Best Practices](#10-class-design-best-practices)
- [Summary](#summary)
- [Self-Check Questions](#self-check-questions)

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

> **Note:** **Java versions and the first-statement rule.** The rule that `this()` and `super()` (Section 9) must be the first statement of a constructor holds through Java 24. From Java 25, statements that do not use the object under construction (such as argument checks) may come before them. In Java 24, placing a statement before `this()` gives the error `flexible constructors is a preview feature`.

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

The constructors are overloaded, so `new Dice()` creates a six-sided die and `new Dice(20)` a twenty-sided one, and the no-argument constructor delegates initialization with `this(DEFAULT_FACES)`. `random.nextInt(faces)` returns an integer from 0 up to but not including `faces`, so `roll()` gives a value from 1 to `faces`. All fields are `final`, so the number of faces never changes after construction. If `faces` is 0 or less, `nextInt` throws `IllegalArgumentException`, so in practice it is safer to check the value in the constructor.

---

<br>

## 5. Object Destruction and Garbage Collection

> **Definition:** **Object destruction** is returning the memory of an object allocated with `new` to the available memory of the Java virtual machine (JVM), and only the JVM can do it. **Garbage** is an object with no reference to it. **Garbage collection** is the process in which the JVM's garbage collector automatically finds garbage and returns it to available memory.

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

Constructors are **not** inherited. Use `super()` to call the parent constructor:

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

> **Key Point:** `super()` must be the first statement in the subclass constructor (see the note in Section 2.2 for the relaxation from Java 25). If omitted, Java implicitly calls `super()` (no-arg), which fails if the superclass lacks a no-arg constructor.

---

<br>

## 10. Class Design Best Practices

| Principle | Description |
|:----------|:------------|
| **Single Responsibility** | Each class should have one reason to change |
| **Encapsulation** | Hide internals; expose only necessary API |
| **Immutability** | Make fields `final` when they should not change after construction |
| **Favor composition** | Prefer has-a over is-a when inheritance is not a natural fit |

*Table 1. Four principles of class design*

**Composition** is a has-a relationship in which a class holds objects of other classes as fields. For example, `Dice` holds a `Random` object as a field and delegates random number generation to it. Inheritance (is-a) ties the subclass to the implementation of the superclass, so use it only when the subclass can truly be called a kind of the superclass.

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
| super() | It calls the parent constructor and must be the first statement. |
| Constructor chaining | A subclass constructor can delegate initialization to a superclass constructor, and a constructor can delegate it to another constructor of the same class. |
| Class design | Follow the single responsibility, encapsulation, immutability, and composition-first principles. |

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
