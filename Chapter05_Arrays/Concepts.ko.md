# Chapter 05 — 배열

> **최종 수정일:** 2026-03-21

---

## 목차

- [1. 배열 기초](#1-배열-기초)
  - [1.1 선언과 초기화](#11-선언과-초기화)
  - [1.2 배열 접근과 길이](#12-배열-접근과-길이)
  - [1.3 기본값](#13-기본값)
- [2. 배열 연산](#2-배열-연산)
  - [2.1 최댓값과 최솟값 탐색](#21-최댓값과-최솟값-탐색)
  - [2.2 평균 계산](#22-평균-계산)
  - [2.3 배열 순회 패턴](#23-배열-순회-패턴)
- [3. 메서드 매개변수로서의 배열](#3-메서드-매개변수로서의-배열)
- [4. 흔한 실수](#4-흔한-실수)
- [요약](#요약)

---

<br>

## 1. 배열 기초

### 1.1 선언과 초기화

Java에서 배열은 동일한 타입의 고정된 수의 요소를 저장하는 객체이다.

```java
// 선언과 할당
int[] scores = new int[5];

// 선언과 동시에 초기화
int[] values = {10, 20, 30, 40, 50};

// 대안 구문 (C 스타일, Java에서는 잘 사용하지 않음)
int numbers[] = new int[3];
```

> **핵심 포인트:** 배열의 크기는 생성 시점에 고정된다. 동적 크기 조정이 필요하면 Collections 프레임워크의 `ArrayList`를 사용한다.

### 1.2 배열 접근과 길이

배열 요소는 0 기반 인덱스로 접근한다. `length` 속성은 요소의 개수를 반환한다.

```java
int[] arr = {10, 20, 30};
System.out.println(arr[0]);      // 10
System.out.println(arr.length);  // 3
```

`[0, length - 1]` 범위를 벗어나는 인덱스에 접근하면 `ArrayIndexOutOfBoundsException`이 발생한다.

### 1.3 기본값

| 자료형 | 기본값 |
|:-------|:-------|
| `int`, `long`, `short`, `byte` | `0` |
| `float`, `double` | `0.0` |
| `boolean` | `false` |
| `char` | `'\u0000'` |
| 객체 참조 | `null` |

---

<br>

## 2. 배열 연산

### 2.1 최댓값과 최솟값 탐색

```java
public static int findMax(int[] array) {
    int max = array[0];
    for (int i = 1; i < array.length; i++) {
        if (array[i] > max) {
            max = array[i];
        }
    }
    return max;
}

public static int findMin(int[] array) {
    int min = array[0];
    for (int i = 1; i < array.length; i++) {
        if (array[i] < min) {
            min = array[i];
        }
    }
    return min;
}
```

> **핵심 포인트:** `max`와 `min`은 `0`이나 `Integer.MAX_VALUE` 같은 임의의 값이 아닌 배열의 첫 번째 요소로 초기화하여 모든 입력 범위를 올바르게 처리해야 한다.

### 2.2 평균 계산

```java
public static double computeMean(int[] array) {
    int sum = 0;
    for (int value : array) {
        sum += value;
    }
    return (double) sum / array.length;
}
```

정수 절삭을 방지하기 위해 나눗셈 전에 `double`로 캐스팅하는 것에 유의한다.

### 2.3 배열 순회 패턴

```java
// 인덱스 기반 for 루프
for (int i = 0; i < array.length; i++) {
    System.out.println(array[i]);
}

// 향상된 for 루프 (읽기 전용 순회)
for (int value : array) {
    System.out.println(value);
}
```

---

<br>

## 3. 메서드 매개변수로서의 배열

배열은 참조로 전달된다(참조 자체는 값으로 전달). 메서드 내부의 수정은 원본 배열에 영향을 미친다.

```java
public static void doubleValues(int[] array) {
    for (int i = 0; i < array.length; i++) {
        array[i] *= 2;  // 원본 배열을 수정함
    }
}
```

부작용을 방지하려면 `Arrays.copyOf()`로 복사본을 생성한다.

---

<br>

## 4. 흔한 실수

| 실수 | 해결 방법 |
|:-----|:---------|
| 루프에서의 Off-by-one 오류 | `i <= array.length`가 아닌 `i < array.length` 사용 |
| `==`로 배열 비교 | 요소별 비교를 위해 `Arrays.equals()` 사용 |
| 매직 넘버로 max/min 초기화 | `array[0]`으로 초기화 |
| 평균 계산 시 정수 나눗셈 | 나눗셈 전에 `double`로 캐스팅 |

---

<br>

## 요약

| 개념 | 핵심 포인트 |
|:-----|:-----------|
| 선언 | `int[] arr = new int[size]` 또는 `int[] arr = {values}` |
| 길이 | 생성 시 고정; `arr.length`로 접근 |
| 인덱싱 | 0 기반; 범위를 벗어나면 예외 발생 |
| 참조 전달 | 메서드 내 배열 수정은 원본에 영향을 미침 |
| 순회 | 읽기 전용은 향상된 for-each; 수정 시 인덱스 기반 for 사용 |
| 유틸리티 메서드 | `Arrays.sort()`, `Arrays.copyOf()`, `Arrays.equals()` |
