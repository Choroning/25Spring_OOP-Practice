# Chapter 06 — 2차원 배열, 함수, 예외 처리

> **최종 수정일:** 2026-04-01

> **선수 지식**: [프로그래밍언어] Java 배열 (제1-5장).
>
> **학습 목표**:
> 1. 2차원 배열을 생성하고 처리할 수 있다
> 2. 매개변수와 반환값을 가진 메서드를 정의할 수 있다
> 3. try-catch로 예외 처리를 적용할 수 있다

---

## 목차

- [1. 2차원 배열](#1-2차원-배열)
  - [1.1 선언과 초기화](#11-선언과-초기화)
  - [1.2 2차원 배열의 순회](#12-2차원-배열의-순회)
  - [1.3 가변 길이 배열 (Ragged Array)](#13-가변-길이-배열-ragged-array)
- [2. 사용자 정의 함수 (메서드)](#2-사용자-정의-함수-메서드)
  - [2.1 메서드 정의](#21-메서드-정의)
  - [2.2 메서드 오버로딩](#22-메서드-오버로딩)
  - [2.3 값에 의한 전달 vs. 참조에 의한 전달](#23-값에-의한-전달-vs-참조에-의한-전달)
- [3. 예외 처리](#3-예외-처리)
  - [3.1 try-catch-finally](#31-try-catch-finally)
  - [3.2 주요 예외 유형](#32-주요-예외-유형)
  - [3.3 예외를 활용한 입력 검증](#33-예외를-활용한-입력-검증)
- [요약](#요약)

---

<br>

## 1. 2차원 배열

### 1.1 선언과 초기화

2차원 배열은 배열의 배열로, 행렬, 격자, 표 등을 표현하는 데 주로 사용된다.

```java
// 정적 초기화
double[][] scores = {
    {3.3, 3.4},
    {3.5, 3.6},
    {3.7, 4.0},
    {4.1, 4.2}
};

// 동적 할당
int[][] matrix = new int[3][4]; // 3행, 4열
```

### 1.2 2차원 배열의 순회

```java
// 행 우선 순회
for (int row = 0; row < scores.length; row++) {
    for (int col = 0; col < scores[row].length; col++) {
        sum += scores[row][col];
    }
}

// 향상된 for 루프
for (double[] row : scores) {
    for (double value : row) {
        sum += value;
    }
}
```

### 1.3 가변 길이 배열 (Ragged Array)

Java에서는 각 행의 길이가 서로 다를 수 있다:

```java
int[][] ragged = new int[3][];
ragged[0] = new int[2];
ragged[1] = new int[5];
ragged[2] = new int[3];
```

> **핵심 포인트:** 가변 길이 배열을 올바르게 처리하려면 고정된 열 수 대신 항상 `array[row].length`를 사용해야 한다.

---

<br>

## 2. 사용자 정의 함수 (메서드)

### 2.1 메서드 정의

```java
public static returnType methodName(parameterList) {
    // 메서드 본문
    return value;
}
```

예시:

```java
public static double computeAverage(double[][] scores) {
    double sum = 0;
    int count = 0;
    for (double[] row : scores) {
        for (double val : row) {
            sum += val;
            count++;
        }
    }
    return sum / count;
}
```

### 2.2 메서드 오버로딩

매개변수의 타입이나 개수가 다르면 같은 이름의 메서드를 여러 개 정의할 수 있다:

```java
public static int max(int a, int b) { return a > b ? a : b; }
public static double max(double a, double b) { return a > b ? a : b; }
```

### 2.3 값에 의한 전달 vs. 참조에 의한 전달

- **기본형(primitive)** 은 값에 의한 전달로 복사본이 전달된다.
- **객체와 배열** 은 참조가 값으로 전달된다. 즉, 메서드 내에서 객체의 내용은 수정할 수 있지만 호출자의 변수 자체를 재할당할 수는 없다.

---

<br>

## 3. 예외 처리

### 3.1 try-catch-finally

```java
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero: " + e.getMessage());
} finally {
    // 예외 발생 여부와 관계없이 항상 실행됨
    System.out.println("Cleanup complete.");
}
```

### 3.2 주요 예외 유형

| 예외 | 원인 |
|:-----|:-----|
| `ArithmeticException` | 0으로 나누기 |
| `ArrayIndexOutOfBoundsException` | 잘못된 배열 인덱스 |
| `NullPointerException` | null 참조에 대한 멤버 접근 |
| `NumberFormatException` | 잘못된 문자열-숫자 변환 |
| `InputMismatchException` | Scanner에 예상과 다른 입력 타입 전달 |
| `IOException` | 입출력 작업 실패 |

### 3.3 예외를 활용한 입력 검증

```java
Scanner scanner = new Scanner(System.in);
int sum = 0;
for (int i = 0; i < 3; i++) {
    System.out.print((i + 1) + "th value >> ");
    try {
        sum += scanner.nextInt();
    } catch (InputMismatchException e) {
        System.out.println("Not an integer. Try again.");
        scanner.next(); // 잘못된 토큰 소비
        i--;            // 같은 반복을 재시도
    }
}
```

> **핵심 포인트:** `Scanner`에서 `InputMismatchException`을 잡을 때는 `scanner.next()`를 호출하여 잘못된 토큰을 소비해야 한다. 그렇지 않으면 루프가 무한히 반복된다.

---

<br>

## 요약

| 개념 | 핵심 포인트 |
|:-----|:-----------|
| 2차원 배열 | 배열의 배열; 행마다 길이가 다를 수 있음 (가변 길이 배열) |
| 순회 | 행 수에는 `array.length`, 열 수에는 `array[i].length` 사용 |
| 메서드 | 반환 타입과 매개변수를 갖는 재사용 가능한 코드 블록 |
| 오버로딩 | 같은 이름, 다른 매개변수 시그니처 |
| try-catch | 프로그램 중단 없이 런타임 오류를 우아하게 처리 |
| finally | 정리 작업을 위해 항상 실행되는 코드 |
| InputMismatchException | 재시도 전 `scanner.next()`로 잘못된 토큰을 소비 |
