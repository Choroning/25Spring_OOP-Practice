# Chapter 03 — Java 기본 문법

> **최종 수정일:** 2026-04-01

> **선수 지식**: [프로그래밍언어] Java 클래스와 객체 (제1-2장).
>
> **학습 목표**:
> 1. Java 데이터 타입, 연산자, 타입 캐스팅을 사용할 수 있다
> 2. String 메서드와 포맷팅을 적용할 수 있다
> 3. Scanner 클래스로 콘솔 입력을 처리할 수 있다

---

## 목차

- [1. 기본 자료형](#1-기본-자료형)
- [2. 변수와 상수](#2-변수와-상수)
- [3. 연산자](#3-연산자)
  - [3.1 산술 연산자](#31-산술-연산자)
  - [3.2 대입 연산자](#32-대입-연산자)
  - [3.3 증감 연산자](#33-증감-연산자)
  - [3.4 관계 및 논리 연산자](#34-관계-및-논리-연산자)
- [4. 형변환](#4-형변환)
- [5. 콘솔 및 대화 상자 입출력](#5-콘솔-및-대화-상자-입출력)
  - [5.1 콘솔 입력을 위한 Scanner](#51-콘솔-입력을-위한-scanner)
  - [5.2 GUI 대화 상자를 위한 JOptionPane](#52-gui-대화-상자를-위한-joptionpane)
- [6. 문자열 연산](#6-문자열-연산)
- [요약](#요약)

---

<br>

## 1. 기본 자료형

Java에는 8가지 기본 자료형(primitive data type)이 있다:

| 자료형 | 크기 | 범위 / 설명 |
|:-------|:-----|:-----------|
| `byte` | 1 byte | -128 ~ 127 |
| `short` | 2 bytes | -32,768 ~ 32,767 |
| `int` | 4 bytes | $-2^{31}$ ~ $2^{31}-1$ |
| `long` | 8 bytes | $-2^{63}$ ~ $2^{63}-1$ |
| `float` | 4 bytes | IEEE 754 단정밀도 |
| `double` | 8 bytes | IEEE 754 배정밀도 |
| `char` | 2 bytes | 유니코드 문자 (0 ~ 65,535) |
| `boolean` | 1 bit* | `true` 또는 `false` |

> **핵심 포인트:** Java에는 unsigned 정수 자료형이 없다. `int` 범위를 초과하는 값에는 `long` 또는 `BigInteger`를 사용한다.

---

<br>

## 2. 변수와 상수

```java
// 변수 선언과 초기화
int age = 25;
double gpa = 3.95;
String name = "Park";

// 상수는 'final' 키워드를 사용
static final double PI = 3.141592;
static final int UNIT_CHANGER = 60;
```

네이밍 규칙:
- 변수: `camelCase` (예: `studentName`)
- 상수: `UPPER_SNAKE_CASE` (예: `MAX_SIZE`)
- 클래스: `PascalCase` (예: `BankAccount`)

---

<br>

## 3. 연산자

### 3.1 산술 연산자

| 연산자 | 연산 | 예시 |
|:-------|:-----|:-----|
| `+` | 덧셈 | `5 + 3` = 8 |
| `-` | 뺄셈 | `5 - 3` = 2 |
| `*` | 곱셈 | `5 * 3` = 15 |
| `/` | 나눗셈 | `7 / 2` = 3 (정수 나눗셈) |
| `%` | 나머지 | `7 % 2` = 1 |

### 3.2 대입 연산자

```java
a += 3;   // a = a + 3
b *= 3;   // b = b * 3
c %= 2;   // c = c % 2
```

### 3.3 증감 연산자

```java
int d = 3;
int a = d++;  // a = 3, d = 4 (후위 증가: 사용 후 증가)
a = ++d;      // d = 5, a = 5 (전위 증가: 증가 후 사용)
```

### 3.4 관계 및 논리 연산자

| 분류 | 연산자 |
|:-----|:-------|
| 관계 | `==`, `!=`, `<`, `>`, `<=`, `>=` |
| 논리 | `&&` (AND), `\|\|` (OR), `!` (NOT) |

---

<br>

## 4. 형변환

```java
// 묵시적(확대) 형변환: 작은 타입 -> 큰 타입 (자동)
int i = 10;
double d = i;  // 10.0

// 명시적(축소) 형변환: 큰 타입 -> 작은 타입 (캐스트 필요)
double pi = 3.14;
int truncated = (int) pi;  // 3
```

---

<br>

## 5. 콘솔 및 대화 상자 입출력

### 5.1 콘솔 입력을 위한 Scanner

```java
import java.util.Scanner;

Scanner scanner = new Scanner(System.in);
int time = scanner.nextInt();
String line = scanner.nextLine();
scanner.close();
```

### 5.2 GUI 대화 상자를 위한 JOptionPane

```java
String input = JOptionPane.showInputDialog(null, "Enter radius:");
double radius = Double.parseDouble(input);
```

---

<br>

## 6. 문자열 연산

주요 `String` 메서드:

| 메서드 | 설명 |
|:-------|:-----|
| `length()` | 문자 수를 반환 |
| `charAt(i)` | 인덱스 `i`의 문자를 반환 |
| `substring(begin, end)` | 부분 문자열을 추출 |
| `indexOf(str)` | 부분 문자열의 위치를 탐색 |
| `toUpperCase()` | 대문자로 변환 |
| `split(regex)` | 구분자로 배열을 분할 |
| `trim()` | 앞뒤 공백을 제거 |

---

<br>

## 요약

| 개념 | 핵심 포인트 |
|:-----|:-----------|
| 기본 자료형 | 8가지: byte, short, int, long, float, double, char, boolean |
| 상수 | `static final`로 선언; 규칙은 UPPER_SNAKE_CASE |
| 연산자 | 산술, 대입, 증감, 관계, 논리 |
| 형변환 | 확대(자동), 축소(명시적 캐스트 필요) |
| Scanner | 콘솔 입력; `nextInt()`, `nextDouble()`, `nextLine()` |
| String | 불변(immutable); 풍부한 조작 API 제공 |
