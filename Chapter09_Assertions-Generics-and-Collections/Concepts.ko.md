# Chapter 09 — 단언문, 제네릭, 컬렉션

> **최종 수정일:** 2026-04-01

> **선수 지식**: [프로그래밍언어] Java (제1-8장). [자료구조] 기본 자료구조 개념.
>
> **학습 목표**:
> 1. 디버깅과 계약에 의한 설계를 위해 단언문을 사용할 수 있다
> 2. 제네릭 타입과 제한된 타입 매개변수를 적용할 수 있다
> 3. Java 컬렉션 프레임워크(List, Set, Map)를 활용할 수 있다

---

## 목차

- [1. 단언문 (Assertion)](#1-단언문-assertion)
  - [1.1 문법과 사용법](#11-문법과-사용법)
  - [1.2 단언문 활성화](#12-단언문-활성화)
- [2. 제네릭](#2-제네릭)
  - [2.1 제네릭 클래스](#21-제네릭-클래스)
  - [2.2 제네릭 메서드](#22-제네릭-메서드)
  - [2.3 한정적 타입 매개변수](#23-한정적-타입-매개변수)
- [3. 컬렉션 프레임워크](#3-컬렉션-프레임워크)
  - [3.1 ArrayList](#31-arraylist)
  - [3.2 HashMap](#32-hashmap)
  - [3.3 컬렉션 순회](#33-컬렉션-순회)
- [요약](#요약)

---

<br>

## 1. 단언문 (Assertion)

### 1.1 문법과 사용법

단언문은 개발 중 항상 참이어야 하는 조건을 런타임에 검사하는 데 사용된다:

```java
assert balance > oldBalance :
    "Error: balance did not increase after deposit";
```

조건이 `false`이면 제공된 메시지와 함께 `AssertionError`가 발생한다.

### 1.2 단언문 활성화

단언문은 기본적으로 비활성화되어 있다. `-ea` JVM 플래그로 활성화한다:

```bash
java -ea MyProgram
```

> **핵심 포인트:** 단언문은 프로그램 불변 조건을 검사하는 데 사용해야 하며, 사용자 입력 검증에는 사용하지 않아야 한다. 사용자 입력에는 예외 처리나 조건문을 사용한다.

---

<br>

## 2. 제네릭

### 2.1 제네릭 클래스

제네릭은 타입을 매개변수화하여 타입 안전하고 재사용 가능한 코드를 작성할 수 있게 한다:

```java
public class Box<T> {
    private T item;

    public void set(T item) { this.item = item; }
    public T get() { return item; }
}

// 사용 예
Box<String> stringBox = new Box<>();
stringBox.set("Hello");
String value = stringBox.get(); // 캐스팅 불필요
```

### 2.2 제네릭 메서드

```java
public static <T> void printArray(T[] array) {
    for (T element : array) {
        System.out.print(element + " ");
    }
    System.out.println();
}
```

### 2.3 한정적 타입 매개변수

```java
// T는 Comparable의 하위 타입이어야 함
public static <T extends Comparable<T>> T findMax(T[] array) {
    T max = array[0];
    for (int i = 1; i < array.length; i++) {
        if (array[i].compareTo(max) > 0) {
            max = array[i];
        }
    }
    return max;
}
```

> **핵심 포인트:** `<T extends SomeType>`을 사용하여 특정 인터페이스를 구현하거나 특정 클래스를 확장하는 타입으로 제네릭을 제한할 수 있다.

---

<br>

## 3. 컬렉션 프레임워크

### 3.1 ArrayList

`List` 인터페이스를 구현한 가변 크기 배열이다:

```java
ArrayList<String> names = new ArrayList<>();
names.add("Alice");
names.add("Bob");
names.get(0);       // "Alice"
names.size();       // 2
names.remove(0);    // "Alice" 제거
```

### 3.2 HashMap

평균 O(1) 접근이 가능한 키-값 맵이다:

```java
HashMap<String, Integer> scores = new HashMap<>();
scores.put("Alice", 95);
scores.put("Bob", 87);
int aliceScore = scores.get("Alice"); // 95
```

### 3.3 컬렉션 순회

```java
// 향상된 for 루프
for (String name : names) {
    System.out.println(name);
}

// Iterator
Iterator<String> it = names.iterator();
while (it.hasNext()) {
    System.out.println(it.next());
}

// forEach와 람다 (Java 8+)
names.forEach(name -> System.out.println(name));
```

---

<br>

## 요약

| 개념 | 핵심 포인트 |
|:-----|:-----------|
| 단언문 | 런타임 불변 조건 검사; `-ea`로 활성화; 사용자 입력에는 사용하지 않음 |
| 제네릭 | 타입 안전한 매개변수화된 클래스와 메서드 |
| 한정적 타입 | `<T extends Type>`으로 타입 매개변수를 제한 |
| ArrayList | 가변 크기 배열; 인덱스로 O(1) 접근 |
| HashMap | 키-값 쌍; 평균 O(1) 조회 |
| 순회 | 향상된 for, Iterator, 또는 람다 `forEach` |
