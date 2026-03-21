# Chapter 07 — 고급 클래스 I

> **최종 수정일:** 2026-03-21

---

## 목차

- [1. 객체와 클래스 복습](#1-객체와-클래스-복습)
  - [1.1 클래스 구조](#11-클래스-구조)
  - [1.2 객체 생성](#12-객체-생성)
- [2. 생성자](#2-생성자)
  - [2.1 기본 생성자](#21-기본-생성자)
  - [2.2 매개변수가 있는 생성자](#22-매개변수가-있는-생성자)
  - [2.3 생성자 오버로딩](#23-생성자-오버로딩)
- [3. 접근 제어자](#3-접근-제어자)
- [4. 캡슐화 (Getter와 Setter)](#4-캡슐화-getter와-setter)
- [5. this 키워드](#5-this-키워드)
- [6. 여러 클래스의 협력](#6-여러-클래스의-협력)
- [요약](#요약)

---

<br>

## 1. 객체와 클래스 복습

### 1.1 클래스 구조

클래스는 객체의 필드(데이터)와 메서드(동작)를 정의하는 설계도이다.

```java
public class Bicycle {
    // 필드 (인스턴스 변수)
    private String ownerName;

    // 메서드
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String name) {
        this.ownerName = name;
    }
}
```

### 1.2 객체 생성

```java
Bicycle bike = new Bicycle();       // 인스턴스 생성
bike.setOwnerName("Alice");          // 메서드를 사용하여 상태 변경
System.out.println(bike.getOwnerName()); // 상태 조회
```

---

<br>

## 2. 생성자

### 2.1 기본 생성자

생성자가 정의되지 않은 경우, Java는 필드를 기본값으로 초기화하는 기본 무인자 생성자를 자동으로 제공한다.

### 2.2 매개변수가 있는 생성자

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

### 2.3 생성자 오버로딩

클래스는 매개변수 목록이 다른 여러 생성자를 가질 수 있다:

```java
public Account() {
    this("Unassigned", 0.0);
}

public Account(String name, double balance) {
    this.ownerName = name;
    this.balance = balance;
}
```

> **핵심 포인트:** `this()`를 사용하여 생성자 내에서 다른 생성자를 호출할 수 있다. 이를 통해 코드 중복을 방지할 수 있다.

---

<br>

## 3. 접근 제어자

| 제어자 | 클래스 | 패키지 | 하위 클래스 | 외부 |
|:-------|:-----:|:------:|:---------:|:----:|
| `public` | Yes | Yes | Yes | Yes |
| `protected` | Yes | Yes | Yes | No |
| (default) | Yes | Yes | No | No |
| `private` | Yes | No | No | No |

> **핵심 포인트:** 필드는 `private`으로 선언하고 `public` getter/setter 메서드를 통해 접근하는 것이 바람직하다. 이것이 캡슐화의 핵심이다.

---

<br>

## 4. 캡슐화 (Getter와 Setter)

캡슐화는 내부 상태를 숨기고 메서드를 통해서만 상호작용하도록 요구하는 것이다:

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

## 5. this 키워드

`this`는 현재 객체 인스턴스를 참조한다. 주요 용도는 다음과 같다:

1. **필드와 매개변수의 이름 충돌 해소:** `this.name = name;`
2. **다른 생성자 호출:** `this(defaultValue);`
3. **현재 객체 전달:** `someMethod(this);`

---

<br>

## 6. 여러 클래스의 협력

객체 지향 설계에서는 여러 클래스가 서로 협력한다:

```java
// 메인 프로그램에서 Account 객체를 생성하고 사용
Account savings = new Account("Alice", 1000.0);
savings.deposit(500.0);
System.out.println("Balance: " + savings.getBalance());
```

> **핵심 포인트:** 각 클래스는 단일 책임을 가져야 한다. 데이터(모델), 로직(서비스), 표현(UI)을 서로 다른 클래스로 분리하라.

---

<br>

## 요약

| 개념 | 핵심 포인트 |
|:-----|:-----------|
| 클래스 | 필드와 메서드를 정의하는 설계도 |
| 객체 | `new` 키워드로 생성하는 클래스의 인스턴스 |
| 생성자 | 객체 상태를 초기화하며 오버로딩이 가능 |
| 접근 제어자 | 가시성 제어: `private` < default < `protected` < `public` |
| 캡슐화 | `private` 필드를 getter/setter를 통해 노출 |
| this | 현재 객체 인스턴스에 대한 참조 |
