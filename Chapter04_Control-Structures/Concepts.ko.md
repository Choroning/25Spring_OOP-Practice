# Chapter 04 — 제어 구조

> **최종 수정일:** 2026-03-21

---

## 목차

- [1. 조건 연산자](#1-조건-연산자)
  - [1.1 삼항 연산자](#11-삼항-연산자)
  - [1.2 관계 및 논리 연산자](#12-관계-및-논리-연산자)
- [2. 선택문](#2-선택문)
  - [2.1 if-else 문](#21-if-else-문)
  - [2.2 중첩 if-else](#22-중첩-if-else)
  - [2.3 switch 문](#23-switch-문)
- [3. 반복문](#3-반복문)
  - [3.1 while 루프](#31-while-루프)
  - [3.2 do-while 루프](#32-do-while-루프)
  - [3.3 for 루프](#33-for-루프)
  - [3.4 향상된 for 루프 (for-each)](#34-향상된-for-루프-for-each)
- [4. 루프 제어문](#4-루프-제어문)
- [5. 실행 시간 측정](#5-실행-시간-측정)
- [6. JOptionPane을 활용한 GUI 입력](#6-joptionpane을-활용한-gui-입력)
- [요약](#요약)

---

<br>

## 1. 조건 연산자

### 1.1 삼항 연산자

삼항 연산자 `? :`는 단순한 조건 논리를 하나의 표현식으로 간결하게 표현하는 방법을 제공한다.

```java
// 구문: condition ? valueIfTrue : valueIfFalse
int diff = (a > b) ? (a - b) : (b - a);
```

전체 `if-else` 블록이 불필요하게 장황해질 수 있는 인라인 대입에 특히 유용하다.

> **핵심 포인트:** 삼항 연산자는 단순한 조건에만 사용해야 한다. 복잡한 논리는 가독성을 위해 `if-else`로 표현하는 것이 좋다.

### 1.2 관계 및 논리 연산자

| 연산자 | 설명 | 예시 |
|:-------|:-----|:-----|
| `==` | 같음 | `a == b` |
| `!=` | 같지 않음 | `a != b` |
| `>` | 보다 큼 | `a > b` |
| `<` | 보다 작음 | `a < b` |
| `>=` | 크거나 같음 | `a >= b` |
| `<=` | 작거나 같음 | `a <= b` |
| `&&` | 논리 AND (단축 평가) | `a > 0 && b > 0` |
| `\|\|` | 논리 OR (단축 평가) | `a > 0 \|\| b > 0` |
| `!` | 논리 NOT | `!flag` |

---

<br>

## 2. 선택문

### 2.1 if-else 문

`if-else` 문은 부울 표현식을 평가하고 두 분기 중 하나를 실행한다.

```java
if (score >= 90) {
    grade = 'A';
} else if (score >= 80) {
    grade = 'B';
} else {
    grade = 'F';
}
```

### 2.2 중첩 if-else

`else if`를 사용하여 여러 조건을 연결할 수 있다. 그러나 깊은 중첩은 가독성을 저하시키므로, `switch`나 다형성 활용을 고려해야 한다.

### 2.3 switch 문

`switch` 문은 표현식을 평가하여 상수 case 레이블과 비교한다. `int`, `char`, `String`, `enum` 타입을 지원한다.

```java
switch (score / 10) {
    case 10:
    case 9:
        grade = 'A';
        break;
    case 8:
        grade = 'B';
        break;
    case 7:
        grade = 'C';
        break;
    default:
        grade = 'F';
}
```

> **핵심 포인트:** fall-through를 방지하기 위해 각 case에 `break` 문을 항상 포함해야 한다. 단, case 10과 9를 묶는 것처럼 의도적인 fall-through는 예외이다.

---

<br>

## 3. 반복문

### 3.1 while 루프

`while` 루프는 조건이 `true`인 동안 실행된다. 조건은 각 반복 **이전에** 검사된다.

```java
while (age < 0 || age > 130) {
    // 유효한 입력 요청
    age = Integer.parseInt(inputStr);
}
```

### 3.2 do-while 루프

`do-while` 루프는 최소 한 번의 실행을 보장하며, 조건은 각 반복 **이후에** 검사된다.

```java
do {
    input = scanner.nextInt();
} while (input < 0);
```

### 3.3 for 루프

`for` 루프는 초기화, 조건, 갱신을 하나의 헤더에 결합한다.

```java
for (int i = 0; i < 10; i++) {
    System.out.println("Iteration: " + i);
}
```

### 3.4 향상된 for 루프 (for-each)

Java는 배열과 `Iterable` 컬렉션을 순회하기 위한 향상된 `for` 루프를 제공한다.

```java
int[] numbers = {1, 2, 3, 4, 5};
for (int n : numbers) {
    System.out.println(n);
}
```

---

<br>

## 4. 루프 제어문

| 제어문 | 설명 |
|:-------|:-----|
| `break` | 가장 안쪽의 루프 또는 switch를 즉시 종료 |
| `continue` | 현재 반복의 나머지를 건너뛰고 다음 반복으로 진행 |
| `return` | 현재 메서드를 완전히 종료 |

```java
for (int i = 0; i < 10; i++) {
    if (i == 5) break;      // i == 5일 때 루프 종료
    if (i % 2 == 0) continue; // 짝수를 건너뜀
    System.out.println(i);   // 1, 3 출력
}
```

---

<br>

## 5. 실행 시간 측정

Java는 경과 시간을 측정하는 여러 방법을 제공한다:

```java
// Date 클래스 사용
Date startTime = new Date();
// ... 측정할 코드 ...
Date endTime = new Date();
long elapsedMs = endTime.getTime() - startTime.getTime();

// System.currentTimeMillis() 사용 (권장)
long start = System.currentTimeMillis();
// ... 측정할 코드 ...
long end = System.currentTimeMillis();
System.out.println("Elapsed: " + (end - start) + " ms");

// System.nanoTime() 사용 (최고 정밀도)
long startNano = System.nanoTime();
// ... 측정할 코드 ...
long endNano = System.nanoTime();
double seconds = (endNano - startNano) / 1_000_000_000.0;
```

> **핵심 포인트:** 성능 벤치마킹에는 `System.nanoTime()`을 사용하는 것이 좋다. 시스템 시계 조정의 영향을 받지 않기 때문이다.

---

<br>

## 6. JOptionPane을 활용한 GUI 입력

`javax.swing.JOptionPane` 클래스는 사용자 상호작용을 위한 간단한 대화 상자를 제공한다:

```java
// 입력 대화 상자
String input = JOptionPane.showInputDialog(null, "Enter your age:");

// 메시지 대화 상자
JOptionPane.showMessageDialog(null, "Hello, World!");

// 확인 대화 상자
int choice = JOptionPane.showConfirmDialog(null, "Continue?");
```

> **핵심 포인트:** `JOptionPane.showInputDialog()`는 사용자가 취소를 클릭하면 `null`을 반환한다. 처리 전에 항상 `null` 여부를 확인해야 한다.

---

<br>

## 요약

| 개념 | 핵심 포인트 |
|:-----|:-----------|
| 삼항 연산자 | 간결한 조건식: `condition ? a : b` |
| if-else | 표준 분기문; 깊은 중첩 지양 |
| switch | 상수에 대한 다중 분기; `break` 필수 |
| while | 사전 조건 루프; 0번 실행될 수 있음 |
| do-while | 사후 조건 루프; 최소 1번 실행 보장 |
| for | 초기화, 조건, 갱신을 포함한 카운터 기반 반복 |
| for-each | 배열과 컬렉션의 간편한 순회 |
| break / continue | 루프 흐름 제어: 루프 종료 또는 반복 건너뛰기 |
| JOptionPane | 입력, 메시지, 확인을 위한 간편한 GUI 대화 상자 |
