# 5강 — 배열

[레포지토리 안내](../../README.ko.md) | [English](Concepts_Lecture.md) | [한국어](Concepts_Lecture.ko.md)

> **최종 수정일:** 2026-10-19

> **학습 목표:**
> 1. 1차원 배열을 선언하고 조작할 수 있다
> 2. 배열 알고리즘(검색, 정렬, 복사)을 적용할 수 있다
> 3. Arrays 유틸리티 클래스와 향상된 for 루프를 사용할 수 있다
> 4. 배열 변수의 대입과 메서드 인자 전달이 같은 배열을 공유한다는 점을 메모리 상태로 설명할 수 있다

---

## 목차

- [1. 배열 기초](#1-배열-기초)
  - [1.1 배열이 필요한 이유](#11-배열이-필요한-이유)
  - [1.2 선언과 초기화](#12-선언과-초기화)
  - [1.3 배열 접근과 길이](#13-배열-접근과-길이)
  - [1.4 기본값](#14-기본값)
  - [1.5 배열 공유](#15-배열-공유)
- [2. 배열 연산](#2-배열-연산)
  - [2.1 배열 처리 예: 강수량](#21-배열-처리-예-강수량)
  - [2.2 최댓값과 최솟값 탐색](#22-최댓값과-최솟값-탐색)
  - [2.3 평균 계산](#23-평균-계산)
  - [2.4 배열 순회 패턴](#24-배열-순회-패턴)
  - [2.5 선형 검색](#25-선형-검색)
- [3. 메서드 매개변수로서의 배열](#3-메서드-매개변수로서의-배열)
- [4. Arrays 유틸리티 클래스](#4-arrays-유틸리티-클래스)
- [5. 흔한 실수](#5-흔한-실수)
- [요약](#요약)
- [점검 문제](#점검-문제)

---

<br>

## 1. 배열 기초

### 1.1 배열이 필요한 이유

배열(array)은 데이터 값의 모음이다. 프로그램이 정수 100개, `Account` 객체 500개, 실수 365개처럼 많은 값을 다뤄야 하면 배열을 쓴다. Java에서 배열은 같은 자료형의 데이터 값을 인덱스로 구별하여 모아 둔 것이다.

예를 들어 정수 10개를 변수 10개(`i0`~`i9`)로 따로 선언하면 합을 구할 때도 `sum = i0+i1+i2+i3+i4+i5+i6+i7+i8+i9;`처럼 변수를 모두 나열해야 한다. 배열 `i` 하나로 선언하면 `for` 루프로 처리할 수 있다.

```java
int i[] = new int[10];
// ... i[0]부터 i[9]까지 값을 저장한다 ...
for (sum = 0, n = 0; n < 10; n++)
    sum += i[n];
```

```text
int i0, i1, ..., i9;              int i[] = new int[10];

 i0 [ 4]   i5 [ 2]                 i
 i1 [55]   i6 [99]                ┌───┐     ┌────┐
 i2 [32]   i7 [13]                │ ●─┼───> │  4 │ i[0]
 i3 [28]   i8 [43]                └───┘     │ 55 │ i[1]
 i4 [35]   i9 [65]                          │ 32 │ i[2]
                                            │ 28 │ i[3]
 sum = i0+i1+...+i9;                        │ 35 │ i[4]
                                            │  2 │ i[5]
                                            │ 99 │ i[6]
                                            │ 13 │ i[7]
                                            │ 43 │ i[8]
                                            │ 65 │ i[9]
                                            └────┘
                                   for (...) sum += i[n];
```

*그림 1. 개별 변수 10개와 원소 10개짜리 배열의 비교*

### 1.2 선언과 초기화

Java에서 배열은 동일한 타입의 고정된 수의 요소를 저장하는 객체이다. 배열 선언에는 `<data type>[] <variable>`과 `<data type> <variable>[]`의 두 형식이 있고, 배열 생성은 `<variable> = new <data type>[<size>]` 형식이다.

```java
// 선언과 할당
int[] scores = new int[5];

// 선언과 동시에 초기화
int[] values = {10, 20, 30, 40, 50};

// 대안 구문 (C 스타일, Java에서는 잘 사용하지 않음)
int numbers[] = new int[3];
```

> **핵심:** 배열의 크기는 생성 시점에 고정된다. 동적 크기 조정이 필요하면 Collections 프레임워크의 `ArrayList`를 사용한다.

배열 변수에는 배열 객체를 가리키는 참조가 들어 있고, 원소는 그 배열 객체 안에 차례로 저장된다.

```java
int intArray[] = {4, 3, 2, 1, 0};
double doubleArray[] = {0.01, 0.02, 0.03, 0.04};
```

```text
intArray                         doubleArray
┌───┐     ┌───┐                  ┌───┐     ┌──────┐
│ ●─┼───> │ 4 │ intArray[0]      │ ●─┼───> │ 0.01 │ doubleArray[0]
└───┘     │ 3 │ intArray[1]      └───┘     │ 0.02 │ doubleArray[1]
          │ 2 │ intArray[2]                │ 0.03 │ doubleArray[2]
          │ 1 │ intArray[3]                │ 0.04 │ doubleArray[3]
          │ 0 │ intArray[4]                └──────┘
          └───┘
```

*그림 2. 초기화 목록으로 만든 int 배열과 double 배열*

다른 자료형처럼 배열도 선언하면서 초기화할 수 있으며, 이때 배열의 크기는 초기값의 개수로 정해진다.

```java
int[] number = { 2, 4, 6, 8 };
double[] samplingData = { 2.443, 8.99, 12.3, 45.009, 18.2,
                          9.00, 3.123, 22.084, 18.08 };
String[] monthName = { "January", "February", "March",
                       "April", "May", "June", "July",
                       "August", "September", "October",
                       "November", "December" };
```

`number.length`는 4, `samplingData.length`는 9, `monthName.length`는 12이다.

배열의 크기는 생성할 때 정해지면 바뀌지 않지만, 그 크기를 실행 중에 정할 수는 있다. 다음 코드는 사용자에게 크기를 입력받아 그 크기의 배열을 만든다.

```java
int size;
int[] number;
size = Integer.parseInt(JOptionPane.showInputDialog(null,
        "Size of an array:"));
number = new int[size];
```

### 1.3 배열 접근과 길이

배열 요소는 0 기반 인덱스로 접근한다. `length` 속성은 요소의 개수를 반환한다.

```java
int[] arr = {10, 20, 30};
System.out.println(arr[0]);      // 10
System.out.println(arr.length);  // 3
```

`[0, length - 1]` 범위를 벗어나는 인덱스에 접근하면 `ArrayIndexOutOfBoundsException`이 발생한다.

`rainfall[2]`처럼 배열 이름 뒤에 인덱스를 붙인 식을 인덱스 식(indexed expression)이라 하며, 위치 2의 원소를 가리킨다. 배열의 첫 위치의 인덱스는 0이다.

```text
double[] rainfall = new double[12];

rainfall
┌───┐       0     1     2     3     4     5     6     7     8     9     10    11
│ ●─┼──> ┌─────┬─────┬─────┬─────┬─────┬─────┬─────┬─────┬─────┬─────┬─────┬─────┐
└───┘    │     │     │  ▲  │     │     │     │     │     │     │     │     │     │
         └─────┴─────┴──┼──┴─────┴─────┴─────┴─────┴─────┴─────┴─────┴─────┴─────┘
                   rainfall[2]
```

*그림 3. 원소 12개짜리 배열 rainfall과 인덱스 식 rainfall[2]*

### 1.4 기본값

| 자료형 | 기본값 |
|:-------|:-------|
| `int`, `long`, `short`, `byte` | `0` |
| `float`, `double` | `0.0` |
| `boolean` | `false` |
| `char` | `'\u0000'` |
| 객체 참조 | `null` |

*표 1. new로 생성한 배열 원소의 기본값*

### 1.5 배열 공유

배열 변수를 다른 배열 변수에 대입하면 배열이 복사되지 않고, 두 변수가 같은 배열 객체를 가리킨다.

```java
int intArray[] = new int[5];
int myArray[] = intArray;

intArray[1] = 2;
myArray[1] = 6;
```

```text
intArray[1] = 2; 실행 후                    myArray[1] = 6; 실행 후

intArray ●──┐                               intArray ●──┐
            ├──> [ 0 | 2 | 0 | 0 | 0 ]                  ├──> [ 0 | 6 | 0 | 0 | 0 ]
myArray  ●──┘                               myArray  ●──┘
```

*그림 4. 같은 배열을 공유하는 두 배열 변수*

`myArray[1] = 6;`은 `intArray`가 가리키는 바로 그 배열의 원소를 바꾸므로, 실행 후 `intArray[1]`도 6이다. 독립된 복사본이 필요하면 4절의 `Arrays.copyOf()`를 쓴다.

---

<br>

## 2. 배열 연산

### 2.1 배열 처리 예: 강수량

12개월의 강수량을 입력받아 연평균을 구한다. `length`는 배열의 용량을 반환하는 public 상수이다.

```java
double[] rainfall = new double[12];

double annualAverage,
       sum = 0.0;

for (int i = 0; i < rainfall.length; i++) {
    rainfall[i] = Double.parseDouble(
            JOptionPane.showInputDialog(null,
                    "Rainfall for month " + (i+1) ) );
    sum += rainfall[i];
}

annualAverage = sum / rainfall.length;
```

월 번호 대신 실제 월 이름으로 입력을 요청하려면 월 이름을 담는 `String` 배열을 함께 쓴다.

```java
double[] rainfall = new double[12];
String[] monthName = new String[12];

monthName[0] = "January";
monthName[1] = "February";
// ... 나머지 10개월도 같은 방식으로 ...

double annualAverage, sum = 0.0;

for (int i = 0; i < rainfall.length; i++) {
    rainfall[i] = Double.parseDouble(
            JOptionPane.showInputDialog(null,
                    "Rainfall for "
                    + monthName[i] ));
    sum += rainfall[i];
}

annualAverage = sum / rainfall.length;
```

분기별 평균은 중첩 루프로 구한다. 분기 i(0\~3)의 j번째 달(0\~2)은 `rainfall[3*i + j]`이다.

```java
//assume rainfall is declared and initialized properly

double[] quarterAverage = new double[4];

for (int i = 0; i < 4; i++) {
    sum = 0;
    for (int j = 0; j < 3; j++) {         //compute the sum of
        sum += rainfall[3*i + j];         //one quarter
    }
    quarterAverage[i] = sum / 3.0;        //Quarter (i+1) average
}
```

### 2.2 최댓값과 최솟값 탐색

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

> **핵심:** `max`와 `min`은 `0`이나 `Integer.MAX_VALUE` 같은 임의의 값이 아닌 배열의 첫 번째 요소로 초기화하여 모든 입력 범위를 올바르게 처리해야 한다.

다음은 사용자가 입력한 정수 5개 중 가장 큰 수를 찾는 프로그램이다. 이를 고쳐 최솟값과 입력 개수(`intArray.length`)를 함께 출력하고, 다시 산술 평균을 출력하도록 확장할 수 있다. 확장할 때는 최댓값, 최솟값, 평균 계산을 위의 `findMax`, `findMin`과 2.3절의 `computeMean`처럼 각각 메서드로 분리하면 코드를 재사용하고 읽기 쉽다(실습 코드: [ArrayMaxFinder.java](../../Coursework/Lab05_Arrays/ArrayMaxFinder.java), [ArrayStatistics.java](../../Coursework/Lab05_Arrays/ArrayStatistics.java)).

```java
import java.util.Scanner;

public class Chap5Program1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int intArray[] = new int[5];

        int max = 0;

        System.out.println("Please input five numeric values.");

        for (int i = 0; i < 5; i++) {
            intArray[i] = scanner.nextInt();
            if (intArray[i] > max)
                max = intArray[i];
        }
        System.out.print("Maximum value is: " + max);

        scanner.close();
    }
}
```

> **참고:** 이 프로그램은 `max`를 0으로 초기화하므로, 입력이 모두 음수이면(예: -5, -2, -9, -1, -7) 최댓값 -1 대신 0을 출력한다. 같은 방식으로 최솟값을 `min = 999999`로 초기화하면 입력이 모두 999999보다 클 때 틀린 값을 낸다. 위의 핵심 상자처럼 첫 번째 원소로 초기화하면 이 문제가 없다.

### 2.3 평균 계산

```java
public static double computeMean(int[] array) {
    int sum = 0;
    for (int value : array) {
        sum += value;
    }
    return (double) sum / array.length;
}
```

정수 절삭을 방지하기 위해 나눗셈 전에 `double`로 캐스팅하는 것에 유의한다. 캐스팅 없이 `double mean = sum / array.length;`로 쓰면 정수 나눗셈의 결과가 대입되므로, 1, 2, 3, 4, 6의 평균은 3.2가 아니라 3.0이 된다.

### 2.4 배열 순회 패턴

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

향상된 `for` 루프의 변수 `value`는 원소의 복사본이므로, `value`에 새 값을 대입해도 배열은 바뀌지 않는다. 원소를 바꾸려면 인덱스 기반 `for` 루프를 쓴다.

### 2.5 선형 검색

배열에서 목표 값을 찾는 가장 단순한 방법은 앞에서부터 차례로 비교하는 선형 검색(linear search)이다. 찾으면 그 인덱스를, 끝까지 없으면 -1을 반환한다.

```java
public static int linearSearch(int[] array, int target) {
    for (int i = 0; i < array.length; i++) {
        if (array[i] == target) {
            return i;
        }
    }
    return -1;
}
```

`{5, 3, 9, 1, 7}`에서 9를 찾으면 2를, 4를 찾으면 -1을 반환한다. 최악의 경우 원소 n개를 모두 비교한다.

---

<br>

## 3. 메서드 매개변수로서의 배열

Java는 인자를 항상 값으로 전달한다. 배열 변수의 값은 배열 객체를 가리키는 참조이므로, 메서드에는 그 참조의 복사본이 전달되고 매개변수와 인자가 같은 배열을 가리킨다. 따라서 메서드 내부에서 원소를 수정하면 원본 배열에 영향을 미친다.

```java
public static void doubleValues(int[] array) {
    for (int i = 0; i < array.length; i++) {
        array[i] *= 2;  // 원본 배열을 수정함
    }
}
```

반면 메서드 안에서 매개변수에 새 배열을 대입하면 매개변수만 다른 배열을 가리키게 되고, 호출한 쪽의 변수는 원래 배열을 그대로 가리킨다.

부작용을 방지하려면 `Arrays.copyOf()`로 복사본을 생성한다.

---

<br>

## 4. Arrays 유틸리티 클래스

`java.util.Arrays` 클래스는 배열을 다루는 정적 메서드를 제공한다.

```java
import java.util.Arrays;

int[] data = {5, 3, 9, 1, 7};
int[] copy = Arrays.copyOf(data, data.length);   // 독립된 복사본
Arrays.sort(copy);                               // 오름차순 정렬

System.out.println(Arrays.toString(data));       // [5, 3, 9, 1, 7]
System.out.println(Arrays.toString(copy));       // [1, 3, 5, 7, 9]
System.out.println(Arrays.equals(data, copy));   // false

int[] same = {5, 3, 9, 1, 7};
System.out.println(data == same);                // false (서로 다른 배열 객체)
System.out.println(Arrays.equals(data, same));   // true (원소가 모두 같음)
```

| 메서드 | 설명 |
|:-----|:-----|
| `Arrays.sort(a)` | 배열 `a`를 오름차순으로 정렬한다 |
| `Arrays.copyOf(a, n)` | 길이 `n`인 새 배열에 `a`의 원소를 복사한다(길면 기본값으로 채우고 짧으면 자른다) |
| `Arrays.equals(a, b)` | 두 배열의 길이와 원소가 모두 같은지 비교한다 |
| `Arrays.toString(a)` | 원소를 `[5, 3, 9]` 형태의 문자열로 만든다 |

*표 2. Arrays 클래스의 주요 메서드*

`copyOf`가 만든 배열은 원본과 별개이므로, `copy`를 정렬해도 `data`는 바뀌지 않는다.

---

<br>

## 5. 흔한 실수

| 실수 | 해결 방법 |
|:-----|:---------|
| 루프에서의 Off-by-one 오류 | `i <= array.length`가 아닌 `i < array.length` 사용 |
| `==`로 배열 비교 | 요소별 비교를 위해 `Arrays.equals()` 사용 |
| 매직 넘버로 max/min 초기화 | `array[0]`으로 초기화 |
| 평균 계산 시 정수 나눗셈 | 나눗셈 전에 `double`로 캐스팅 |
| 배열 변수 대입을 복사로 착각 | 독립된 배열이 필요하면 `Arrays.copyOf()` 사용 |

*표 3. 배열을 다룰 때 흔한 실수와 해결 방법*

---

<br>

## 요약

| 개념 | 핵심 요약 |
|:-----|:-----------|
| 배열 | 배열은 같은 자료형의 값을 인덱스로 구별하여 모은 객체이다. |
| 선언 | `int[] arr = new int[size]` 또는 `int[] arr = {values}`로 선언한다. |
| 길이 | 길이는 생성 시 고정되며 `arr.length`로 접근한다. |
| 인덱싱 | 인덱스는 0부터 시작하며, 범위를 벗어나면 `ArrayIndexOutOfBoundsException`이 발생한다. |
| 기본값 | `new`로 만든 배열의 원소는 0, 0.0, `false`, `'\u0000'`, `null` 중 자료형에 맞는 기본값을 가진다. |
| 배열 공유 | 배열 변수를 대입하면 배열이 복사되지 않고 두 변수가 같은 배열을 가리킨다. |
| 배열 인자 전달 | 참조가 값으로 전달되므로 메서드 내 원소 수정은 원본에 영향을 미친다. |
| 순회 | 읽기 전용은 향상된 for-each를, 수정할 때는 인덱스 기반 for를 사용한다. |
| 선형 검색 | 앞에서부터 차례로 비교하여 목표의 인덱스나 -1을 반환한다. |
| 유틸리티 메서드 | `Arrays.sort()`, `Arrays.copyOf()`, `Arrays.equals()`, `Arrays.toString()`을 제공한다. |

---

<br>

## 점검 문제

1. **배열 공유:** 다음 코드를 실행한 뒤 `a[1]`의 값은 얼마인가?

   ```java
   int[] a = new int[5];
   int[] b = a;
   a[1] = 2;
   b[1] = 6;
   ```

   <details>
   <summary>정답</summary>

   6이다. `b = a`는 배열을 복사하지 않고 `b`가 `a`와 같은 배열을 가리키게 하므로, `b[1] = 6`은 `a[1]`을 바꾼다.

   </details>

2. **분기별 인덱스:** 2.1절의 분기별 평균 코드에서 i = 2일 때 더하는 `rainfall`의 인덱스와 그 달의 이름을 쓰라.

   <details>
   <summary>정답</summary>

   j = 0, 1, 2에 대해 `3*2 + j`이므로 인덱스 6, 7, 8이다. `monthName` 기준으로 July, August, September이며, `quarterAverage[2]`는 3분기 평균이다.

   </details>

3. **최댓값 초기화:** 2.2절의 `Chap5Program1`에 -5, -2, -9, -1, -7을 입력하면 무엇이 출력되는가? 어떻게 고치는가?

   <details>
   <summary>정답</summary>

   어떤 입력도 초기값 0보다 크지 않으므로 `max`가 바뀌지 않아 "Maximum value is: 0"을 출력한다. 올바른 최댓값은 -1이다. `max`를 첫 번째 입력값 `intArray[0]`으로 초기화하고 나머지 원소와 비교하면 된다.

   </details>

4. **평균과 정수 나눗셈:** `int` 배열 `{1, 2, 3, 4, 6}`에 대해 `double mean = sum / array.length;`와 `(double) sum / array.length`의 결과를 각각 쓰라.

   <details>
   <summary>정답</summary>

   합은 16이다. 앞의 식은 정수 나눗셈 16 / 5 = 3을 계산한 뒤 `double`로 바꾸므로 3.0이다. 뒤의 식은 16.0 / 5를 계산하므로 3.2이다.

   </details>

5. **배열 인자:** 다음 메서드를 `int[] arr = {1, 2, 3}; modify(arr);`로 호출한 뒤 `arr`의 원소는 무엇인가?

   ```java
   static void modify(int[] array) {
       array[0] = 100;
       array = new int[]{7, 7};
       array[1] = 50;
   }
   ```

   <details>
   <summary>정답</summary>

   `[100, 2, 3]`이다. 처음에는 `array`와 `arr`가 같은 배열을 가리키므로 `array[0] = 100`이 원본을 바꾼다. 그 뒤 `array`에 새 배열을 대입하면 매개변수만 새 배열을 가리키므로, `array[1] = 50`은 원본에 영향을 주지 않는다.

   </details>
