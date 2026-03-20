# Chapter 11 — Inheritance and Polymorphism

> **Last Updated:** 2026-03-21

---

## Table of Contents

- [1. Inheritance Hierarchy](#1-inheritance-hierarchy)
  - [1.1 Defining Classes with Inheritance](#11-defining-classes-with-inheritance)
  - [1.2 The protected Modifier](#12-the-protected-modifier)
  - [1.3 Constructor Chaining with super()](#13-constructor-chaining-with-super)
- [2. Polymorphism](#2-polymorphism)
  - [2.1 Dynamic Binding](#21-dynamic-binding)
  - [2.2 The instanceof Operator](#22-the-instanceof-operator)
- [3. Method Overriding](#3-method-overriding)
  - [3.1 Rules for Overriding](#31-rules-for-overriding)
  - [3.2 The @Override Annotation](#32-the-override-annotation)
- [4. Abstract Classes and Interfaces](#4-abstract-classes-and-interfaces)
  - [4.1 Abstract Classes](#41-abstract-classes)
  - [4.2 Interfaces](#42-interfaces)
- [5. Design Patterns Using Inheritance](#5-design-patterns-using-inheritance)
- [Summary](#summary)

---

<br>

## 1. Inheritance Hierarchy

### 1.1 Defining Classes with Inheritance

```java
public class Person {
    private int weight;
    int age;
    protected int height;
    public String name;

    public void setWeight(int weight) { this.weight = weight; }
    public int getWeight() { return weight; }
}

public class Student extends Person {
    public void set() {
        age = 39;
        name = "PSY";
        height = 182;
        setWeight(99); // cannot access weight directly (private)
    }

    public void display() {
        System.out.println("Age: " + age);
        System.out.println("Name: " + name);
        System.out.println("Height: " + height);
    }
}
```

### 1.2 The protected Modifier

`protected` members are visible to:
- The class itself
- Classes in the same package
- Subclasses in any package

### 1.3 Constructor Chaining with super()

```java
public class Point {
    private int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class ColorPoint extends Point {
    private String color;

    public ColorPoint(int x, int y, String color) {
        super(x, y);          // must be the first statement
        this.color = color;
    }
}
```

---

<br>

## 2. Polymorphism

### 2.1 Dynamic Binding

A superclass variable can reference subclass objects. The JVM determines which method to call at runtime:

```java
Student[] roster = new Student[40];
roster[0] = new GraduateStudent();
roster[1] = new UndergraduateStudent();

for (Student s : roster) {
    s.computeCourseGrade(); // calls the appropriate subclass method
}
```

> **Key Point:** Polymorphism enables writing code that works with the superclass type while automatically invoking the correct subclass behavior at runtime.

### 2.2 The instanceof Operator

```java
if (roster[i] instanceof UndergraduateStudent) {
    System.out.println("Undergraduate student found.");
}
```

---

<br>

## 3. Method Overriding

### 3.1 Rules for Overriding

- Method name, parameter types, and return type must match the superclass method.
- Access level cannot be more restrictive than the overridden method.
- Cannot override `final` or `static` methods.

### 3.2 The @Override Annotation

```java
class Shape {
    public void draw() {
        System.out.println("Shape");
    }
}

class Circle extends Shape {
    @Override
    public void draw() {
        System.out.println("Circle");
    }
}
```

> **Key Point:** Always use `@Override` to catch signature mismatches at compile time rather than silently creating a new method.

---

<br>

## 4. Abstract Classes and Interfaces

### 4.1 Abstract Classes

Abstract classes cannot be instantiated and may contain abstract methods:

```java
abstract class Shape {
    abstract double area();

    public void printArea() {
        System.out.println("Area: " + area());
    }
}
```

### 4.2 Interfaces

Interfaces define a contract that implementing classes must fulfill:

```java
interface Drawable {
    void draw();
}

class Circle extends Shape implements Drawable {
    @Override
    double area() { return Math.PI * radius * radius; }

    @Override
    public void draw() { System.out.println("Drawing circle"); }
}
```

---

<br>

## 5. Design Patterns Using Inheritance

| Pattern | Description | Example |
|:--------|:------------|:--------|
| Template Method | Superclass defines the algorithm skeleton; subclasses fill in steps | `Shape.printArea()` calls abstract `area()` |
| Strategy | Vary behavior by composing different implementations | Different `Weapon` subclasses in a game |
| Factory | Create objects without specifying exact class | `StudentFactory.create("grad")` |

---

<br>

## Summary

| Concept | Key Point |
|:--------|:----------|
| Inheritance | `extends` creates is-a relationship; single inheritance |
| protected | Accessible by subclasses and same-package classes |
| Polymorphism | Superclass variable, subclass behavior (dynamic binding) |
| instanceof | Tests the runtime type of an object |
| Method Overriding | Same signature; use `@Override` annotation |
| Abstract Class | Cannot be instantiated; may have abstract methods |
| Interface | Pure contract; a class can implement multiple interfaces |
