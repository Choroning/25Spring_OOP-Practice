# Chapter 07 -- Advanced Classes I

> **Last Updated:** 2026-03-21

---

## Table of Contents

- [1. Objects and Classes Review](#1-objects-and-classes-review)
  - [1.1 Class Structure](#11-class-structure)
  - [1.2 Creating Objects](#12-creating-objects)
- [2. Constructors](#2-constructors)
  - [2.1 Default Constructor](#21-default-constructor)
  - [2.2 Parameterized Constructor](#22-parameterized-constructor)
  - [2.3 Constructor Overloading](#23-constructor-overloading)
- [3. Access Modifiers](#3-access-modifiers)
- [4. Encapsulation (Getters and Setters)](#4-encapsulation-getters-and-setters)
- [5. The this Keyword](#5-the-this-keyword)
- [6. Multiple Classes Working Together](#6-multiple-classes-working-together)
- [Summary](#summary)

---

<br>

## 1. Objects and Classes Review

### 1.1 Class Structure

A class is a blueprint that defines the fields (data) and methods (behavior) of objects.

```java
public class Bicycle {
    // Fields (instance variables)
    private String ownerName;

    // Methods
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String name) {
        this.ownerName = name;
    }
}
```

### 1.2 Creating Objects

```java
Bicycle bike = new Bicycle();       // create an instance
bike.setOwnerName("Alice");          // use methods to modify state
System.out.println(bike.getOwnerName()); // retrieve state
```

---

<br>

## 2. Constructors

### 2.1 Default Constructor

If no constructor is defined, Java provides a default no-argument constructor that initializes fields to their default values.

### 2.2 Parameterized Constructor

```java
public class Account {
    private String ownerName;
    private double balance;

    public Account(String name, double initialBalance) {
        this.ownerName = name;
        this.balance = initialBalance;
    }
}
```

### 2.3 Constructor Overloading

A class can have multiple constructors with different parameter lists:

```java
public Account() {
    this("Unassigned", 0.0);
}

public Account(String name, double balance) {
    this.ownerName = name;
    this.balance = balance;
}
```

> **Key Point:** Use `this()` to call another constructor from within a constructor. This avoids code duplication.

---

<br>

## 3. Access Modifiers

| Modifier | Class | Package | Subclass | World |
|:---------|:-----:|:-------:|:--------:|:-----:|
| `public` | Yes | Yes | Yes | Yes |
| `protected` | Yes | Yes | Yes | No |
| (default) | Yes | Yes | No | No |
| `private` | Yes | No | No | No |

> **Key Point:** Prefer `private` for fields and expose access through `public` getter/setter methods. This is the essence of encapsulation.

---

<br>

## 4. Encapsulation (Getters and Setters)

Encapsulation hides internal state and requires interaction through methods:

```java
public class Account {
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        }
    }
}
```

---

<br>

## 5. The this Keyword

`this` refers to the current object instance. Common uses:

1. **Disambiguate fields from parameters:** `this.name = name;`
2. **Call another constructor:** `this(defaultValue);`
3. **Pass the current object:** `someMethod(this);`

---

<br>

## 6. Multiple Classes Working Together

Object-oriented design involves multiple classes collaborating:

```java
// Main program creates and uses Account objects
Account savings = new Account("Alice", 1000.0);
savings.deposit(500.0);
System.out.println("Balance: " + savings.getBalance());
```

> **Key Point:** Each class should have a single responsibility. Separate data (model), logic (service), and presentation (UI) into different classes.

---

<br>

## Summary

| Concept | Key Point |
|:--------|:----------|
| Class | Blueprint defining fields and methods |
| Object | Instance of a class created with `new` |
| Constructor | Initializes object state; can be overloaded |
| Access Modifiers | Control visibility: `private` < default < `protected` < `public` |
| Encapsulation | Hide fields with `private`; expose via getters/setters |
| this | Reference to the current object instance |
