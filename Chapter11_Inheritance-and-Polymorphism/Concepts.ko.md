# Chapter 11 — 상속과 다형성

> **최종 수정일:** 2026-03-21

---

## 목차

- [1. 상속 계층 구조](#1-상속-계층-구조)
  - [1.1 상속을 이용한 클래스 정의](#11-상속을-이용한-클래스-정의)
  - [1.2 protected 접근 제한자](#12-protected-접근-제한자)
  - [1.3 super()를 이용한 생성자 체이닝](#13-super를-이용한-생성자-체이닝)
- [2. 다형성](#2-다형성)
  - [2.1 동적 바인딩](#21-동적-바인딩)
  - [2.2 instanceof 연산자](#22-instanceof-연산자)
- [3. 메서드 오버라이딩](#3-메서드-오버라이딩)
  - [3.1 오버라이딩 규칙](#31-오버라이딩-규칙)
  - [3.2 @Override 애너테이션](#32-override-애너테이션)
- [4. 추상 클래스와 인터페이스](#4-추상-클래스와-인터페이스)
  - [4.1 추상 클래스](#41-추상-클래스)
  - [4.2 인터페이스](#42-인터페이스)
- [5. 상속을 활용한 디자인 패턴](#5-상속을-활용한-디자인-패턴)
- [요약](#요약)

---

<br>

## 1. 상속 계층 구조

### 1.1 상속을 이용한 클래스 정의

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
        setWeight(99); // weight는 private이므로 직접 접근 불가
    }

    public void display() {
        System.out.println("Age: " + age);
        System.out.println("Name: " + name);
        System.out.println("Height: " + height);
    }
}
```

### 1.2 protected 접근 제한자

`protected` 멤버는 다음에서 접근 가능하다:
- 해당 클래스 자체
- 같은 패키지 내의 클래스
- 모든 패키지의 하위 클래스

### 1.3 super()를 이용한 생성자 체이닝

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
        super(x, y);          // 반드시 첫 번째 문장이어야 함
        this.color = color;
    }
}
```

---

<br>

## 2. 다형성

### 2.1 동적 바인딩

상위 클래스 변수가 하위 클래스 객체를 참조할 수 있다. JVM은 런타임에 호출할 메서드를 결정한다:

```java
Student[] roster = new Student[40];
roster[0] = new GraduateStudent();
roster[1] = new UndergraduateStudent();

for (Student s : roster) {
    s.computeCourseGrade(); // 적절한 하위 클래스 메서드가 호출됨
}
```

> **핵심 요점:** 다형성을 사용하면 상위 클래스 타입으로 코드를 작성하면서 런타임에 올바른 하위 클래스 동작을 자동으로 호출할 수 있다.

### 2.2 instanceof 연산자

```java
if (roster[i] instanceof UndergraduateStudent) {
    System.out.println("Undergraduate student found.");
}
```

---

<br>

## 3. 메서드 오버라이딩

### 3.1 오버라이딩 규칙

- 메서드 이름, 매개변수 타입, 반환 타입이 상위 클래스 메서드와 일치해야 한다.
- 접근 수준은 오버라이딩되는 메서드보다 더 제한적일 수 없다.
- `final` 또는 `static` 메서드는 오버라이딩할 수 없다.

### 3.2 @Override 애너테이션

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

> **핵심 요점:** `@Override`를 항상 사용하여 시그니처 불일치를 런타임이 아닌 컴파일 타임에 잡아내야 한다. 이를 사용하지 않으면 오버라이딩이 아닌 새로운 메서드가 생성될 수 있다.

---

<br>

## 4. 추상 클래스와 인터페이스

### 4.1 추상 클래스

추상 클래스는 인스턴스화할 수 없으며 추상 메서드를 포함할 수 있다:

```java
abstract class Shape {
    abstract double area();

    public void printArea() {
        System.out.println("Area: " + area());
    }
}
```

### 4.2 인터페이스

인터페이스는 구현 클래스가 반드시 충족해야 하는 계약을 정의한다:

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

## 5. 상속을 활용한 디자인 패턴

| 패턴 | 설명 | 예시 |
|:--------|:------------|:--------|
| Template Method | 상위 클래스가 알고리즘 골격을 정의하고, 하위 클래스가 각 단계를 구현함 | `Shape.printArea()`가 추상 `area()`를 호출 |
| Strategy | 다양한 구현을 조합하여 동작을 변경함 | 게임에서 서로 다른 `Weapon` 하위 클래스 |
| Factory | 정확한 클래스를 지정하지 않고 객체를 생성함 | `StudentFactory.create("grad")` |

---

<br>

## 요약

| 개념 | 핵심 요점 |
|:--------|:----------|
| 상속 | `extends`로 is-a 관계 생성; 단일 상속만 가능 |
| protected | 하위 클래스 및 같은 패키지 클래스에서 접근 가능 |
| 다형성 | 상위 클래스 변수, 하위 클래스 동작 (동적 바인딩) |
| instanceof | 객체의 런타임 타입을 검사 |
| 메서드 오버라이딩 | 동일한 시그니처; `@Override` 애너테이션 사용 |
| 추상 클래스 | 인스턴스화 불가; 추상 메서드를 가질 수 있음 |
| 인터페이스 | 순수 계약; 클래스는 여러 인터페이스를 구현 가능 |
