# Chapter 08 — 고급 클래스 II

> **최종 수정일:** 2026-04-01

> **선수 지식**: [프로그래밍언어] Java (제1-7장). [객체지향] 고급 클래스 설계.
>
> **학습 목표**:
> 1. 추상 클래스와 인터페이스를 구현할 수 있다
> 2. Comparable과 Cloneable 인터페이스를 적용할 수 있다
> 3. 합성과 집합을 사용하여 클래스를 설계할 수 있다

---

## 목차

- [1. 상속의 기초](#1-상속의-기초)
  - [1.1 extends 키워드](#11-extends-키워드)
  - [1.2 상위 클래스와 하위 클래스의 관계](#12-상위-클래스와-하위-클래스의-관계)
- [2. super()를 이용한 생성자 체이닝](#2-super를-이용한-생성자-체이닝)
- [3. Dice 클래스 — 실용 예제](#3-dice-클래스--실용-예제)
- [4. 클래스 설계 모범 사례](#4-클래스-설계-모범-사례)
- [요약](#요약)

---

<br>

## 1. 상속의 기초

### 1.1 extends 키워드

상속을 통해 새로운 클래스(하위 클래스)가 기존 클래스(상위 클래스)의 필드와 메서드를 물려받을 수 있다.

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

### 1.2 상위 클래스와 하위 클래스의 관계

- 하위 클래스는 상위 클래스의 비공개(non-private) 멤버를 모두 상속받는다.
- 하위 클래스는 새로운 필드와 메서드를 추가할 수 있다.
- 하위 클래스는 상속받은 메서드를 오버라이드할 수 있다.

> **핵심 포인트:** Java는 단일 상속만 지원한다. 즉, 하나의 클래스는 최대 하나의 상위 클래스만 확장할 수 있다. 다중 타입 관계가 필요한 경우 인터페이스를 사용한다.

---

<br>

## 2. super()를 이용한 생성자 체이닝

생성자는 상속되지 **않는다**. `super()`를 사용하여 부모 생성자를 호출한다:

```java
public class ElectricBicycle extends Bicycle {
    private int batteryLevel;

    public ElectricBicycle(String ownerName, int battery) {
        super(ownerName);         // Bicycle의 생성자 호출
        this.batteryLevel = battery;
    }
}
```

> **핵심 포인트:** `super()`는 하위 클래스 생성자의 첫 번째 문장이어야 한다. 생략할 경우 Java는 암묵적으로 `super()`(무인자)를 호출하는데, 상위 클래스에 무인자 생성자가 없으면 컴파일 오류가 발생한다.

---

<br>

## 3. Dice 클래스 — 실용 예제

`Dice` 클래스는 무작위성을 캡슐화하며 깔끔한 클래스 설계를 보여준다:

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

## 4. 클래스 설계 모범 사례

| 원칙 | 설명 |
|:-----|:-----|
| **단일 책임 원칙** | 각 클래스는 변경해야 할 이유가 하나뿐이어야 한다 |
| **캡슐화** | 내부 구현을 숨기고 필요한 API만 노출한다 |
| **불변성** | 생성 이후 변경되지 않아야 하는 필드는 `final`로 선언한다 |
| **구성 우선** | 상속이 자연스럽지 않은 경우 "is-a" 관계보다 "has-a" 관계를 선호한다 |

---

<br>

## 요약

| 개념 | 핵심 포인트 |
|:-----|:-----------|
| 상속 | `extends`로 is-a 관계를 생성; 단일 상속만 지원 |
| super() | 부모 생성자를 호출; 반드시 첫 번째 문장이어야 함 |
| 생성자 체이닝 | 하위 클래스 생성자가 상위 클래스 또는 다른 생성자에 위임 가능 |
| 클래스 설계 | SRP, 캡슐화, 불변성, 구성 우선 원칙을 따른다 |
