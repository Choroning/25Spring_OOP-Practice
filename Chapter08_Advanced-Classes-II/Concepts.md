# Chapter 08 — Advanced Classes II

> **Last Updated:** 2026-03-21

---

## Table of Contents

- [1. Inheritance Basics](#1-inheritance-basics)
  - [1.1 The extends Keyword](#11-the-extends-keyword)
  - [1.2 Superclass and Subclass Relationship](#12-superclass-and-subclass-relationship)
- [2. Constructor Chaining with super()](#2-constructor-chaining-with-super)
- [3. The Dice Class -- A Practical Example](#3-the-dice-class----a-practical-example)
- [4. Class Design Best Practices](#4-class-design-best-practices)
- [Summary](#summary)

---

<br>

## 1. Inheritance Basics

### 1.1 The extends Keyword

Inheritance allows a new class (subclass) to inherit fields and methods from an existing class (superclass).

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

### 1.2 Superclass and Subclass Relationship

- The subclass inherits all non-private members of the superclass.
- The subclass can add new fields and methods.
- The subclass can override inherited methods.

> **Key Point:** Java supports single inheritance only -- a class can extend at most one superclass. Use interfaces for multiple-type relationships.

---

<br>

## 2. Constructor Chaining with super()

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

> **Key Point:** `super()` must be the first statement in the subclass constructor. If omitted, Java implicitly calls `super()` (no-arg), which fails if the superclass lacks a no-arg constructor.

---

<br>

## 3. The Dice Class -- A Practical Example

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

---

<br>

## 4. Class Design Best Practices

| Principle | Description |
|:----------|:------------|
| **Single Responsibility** | Each class should have one reason to change |
| **Encapsulation** | Hide internals; expose only necessary API |
| **Immutability** | Make fields `final` when they should not change after construction |
| **Favor composition** | Prefer "has-a" over "is-a" when inheritance is not a natural fit |

---

<br>

## Summary

| Concept | Key Point |
|:--------|:----------|
| Inheritance | `extends` creates an is-a relationship; single inheritance only |
| super() | Calls the parent constructor; must be the first statement |
| Constructor chaining | Subclass constructor can delegate to superclass or other constructors |
| Class design | Follow SRP, encapsulation, immutability, and composition principles |
