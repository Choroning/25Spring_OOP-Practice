# 5강 — 배열

[레포지토리 안내](../../README.ko.md) | [English](Concepts_Lecture.md) | [한국어](Concepts_Lecture.ko.md)

> **최종 수정일:** 2026-11-19

> **학습 목표:**
> 1. 1차원 배열을 선언하고 조작할 수 있다
> 2. 배열 알고리즘(검색, 정렬, 복사)을 적용할 수 있다
> 3. Arrays 유틸리티 클래스와 향상된 for 루프를 사용할 수 있다
> 4. 배열 변수의 대입과 메서드 인자 전달이 같은 배열을 공유한다는 점을 메모리 상태로 설명할 수 있다
> 5. 이진 검색과 버블 정렬의 동작을 추적표로 설명하고 구현할 수 있다
> 6. 배열 복사의 방법과 얕은 복사를 구분하고, 객체의 배열을 만들어 처리할 수 있다

---

## 목차

- [1. 배열 기초](#1-배열-기초)
  - [1.1 배열이 필요한 이유](#11-배열이-필요한-이유)
  - [1.2 선언과 초기화](#12-선언과-초기화)
  - [1.3 배열 접근과 길이](#13-배열-접근과-길이)
  - [1.4 기본값](#14-기본값)
  - [1.5 배열 공유](#15-배열-공유)
  - [1.6 객체로서의 배열](#16-객체로서의-배열)
  - [1.7 원소 접근의 비용과 삽입, 삭제](#17-원소-접근의-비용과-삽입-삭제)
- [2. 배열 연산](#2-배열-연산)
  - [2.1 배열 처리 예: 강수량](#21-배열-처리-예-강수량)
  - [2.2 최댓값과 최솟값 탐색](#22-최댓값과-최솟값-탐색)
  - [2.3 평균 계산](#23-평균-계산)
  - [2.4 배열 순회 패턴](#24-배열-순회-패턴)
  - [2.5 선형 검색](#25-선형-검색)
  - [2.6 메서드로 나눈 통계 프로그램](#26-메서드로-나눈-통계-프로그램)
  - [2.7 배열 뒤집기](#27-배열-뒤집기)
  - [2.8 이진 검색](#28-이진-검색)
  - [2.9 버블 정렬](#29-버블-정렬)
  - [2.10 선택 정렬](#210-선택-정렬)
  - [2.11 빈도를 세는 배열](#211-빈도를-세는-배열)
- [3. 메서드 매개변수로서의 배열](#3-메서드-매개변수로서의-배열)
  - [3.1 원소 교환과 변수 교환](#31-원소-교환과-변수-교환)
  - [3.2 가변 인자 메서드](#32-가변-인자-메서드)
- [4. Arrays 유틸리티 클래스](#4-arrays-유틸리티-클래스)
  - [4.1 배열 복사의 방법과 얕은 복사](#41-배열-복사의-방법과-얕은-복사)
  - [4.2 그 밖의 Arrays 메서드](#42-그-밖의-arrays-메서드)
- [5. 흔한 실수](#5-흔한-실수)
- [6. 객체의 배열](#6-객체의-배열)
  - [6.1 객체 배열의 생성](#61-객체-배열의-생성)
  - [6.2 객체 배열의 처리](#62-객체-배열의-처리)
- [개념 적용](#개념-적용)
- [요약](#요약)
- [점검 문제](#점검-문제)
- [참고 문헌](#참고-문헌)

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

예를 들어 실수 12개를 담을 배열 `rainfall`은 두 형식 중 어느 것으로도 선언할 수 있으며, 선언한 뒤 `new`로 배열 객체를 만들어 대입한다.

```java
double[] rainfall;              // variation 1
rainfall = new double[12];

double rainfall2[];             // variation 2
rainfall2 = new double[12];
```

선언은 2강 2.1절의 객체 선언처럼 참조를 담을 변수만 만들고, `new double[12]`가 실제 배열을 만든다는 점에서 배열은 객체와 같은 방식으로 다룬다. Java에서는 자료형이 한눈에 보이는 첫 번째 형식(`double[] rainfall`)을 주로 쓴다.

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

### 1.6 객체로서의 배열

Java의 배열은 객체이다. 실행 중에 `new`로 동적으로 만들어지고, `Object` 타입의 변수에 대입할 수 있으며, `Object`의 메서드를 모두 호출할 수 있다 [1]. 그래서 배열은 다른 객체처럼 힙에 놓이고, 배열 변수에는 그 배열을 가리키는 참조가 들어간다(2강 2.5절). 1.5절의 배열 공유도 이 성질에서 나온다.

```java
import java.util.Arrays;

public class ArrayObject {
    public static void main(String[] args) {
        int[] a = {1, 2, 3};
        System.out.println(a.length + " " + a.getClass().getSimpleName());
        System.out.println(a);
        System.out.println(Arrays.toString(a));
        String[] names = new String[3];
        names[0] = "Kim";
        System.out.println(names[0] + " " + names[1]);
        System.out.println(names[1].length());
    }
}
```

```text
3 int[]
[I@7ad041f3
[1, 2, 3]
Kim null
Exception in thread "main" java.lang.NullPointerException: Cannot invoke "String.length()" because "names[1]" is null
	at ArrayObject.main(ArrayObject.java:12)
```

- 배열 객체는 자기 클래스(`int[]`)를 가지며, 원소의 수를 나타내는 `length`라는 `final` 필드를 가진다. `final`이므로 `a.length = 5;`처럼 바꾸려 하면 `cannot assign a value to final variable length` 컴파일 오류가 난다. 메서드인 `String`의 `length()`와 달리 괄호 없이 쓴다.
- `System.out.println(a)`는 원소가 아니라 `[I@7ad041f3` 같은 문자열을 출력한다. `[I`는 `int`의 배열이라는 뜻이고 `@` 뒤의 16진수는 객체마다 달라 실행할 때마다 바뀔 수 있다. 원소를 보려면 `Arrays.toString(a)`를 쓴다(4절).
- 참조형의 배열 `new String[3]`은 `String` 객체 세 개가 아니라 `String`을 가리킬 수 있는 참조 세 칸을 만든다. 각 칸의 기본값은 `null`이므로, 객체를 대입하지 않은 `names[1]`로 메서드를 호출하면 `NullPointerException`이 난다(6절). 메시지의 `"names[1]"`처럼 지역 변수의 이름이 나오는 것은 `-g`로 컴파일했을 때이다(2강 2.6절).

```text
 Stack                Heap
┌──────────┐        ┌──────────────────────┐
│ a     ●──┼──────▶ │ int[]  length 3      │
│          │        │ [0] 1  [1] 2  [2] 3  │
│          │        └──────────────────────┘
│ names ●──┼──────▶ ┌──────────────────────┐
└──────────┘        │ String[]  length 3   │
                    │ [0] ●──▶ "Kim"       │
                    │ [1] null             │
                    │ [2] null             │
                    └──────────────────────┘
```

*그림 5. 기본형 배열과 참조형 배열의 메모리 구조*

`int[]`의 원소 칸에는 값 자체가 들어 있지만, `String[]`의 원소 칸에는 참조가 들어 있다. 1.4절의 기본값 표는 배열 원소가 배열 객체의 일부로서 만들어질 때 받는 값이며, 메서드의 지역 변수는 이런 기본값을 받지 않는다(3강 2.1절).

### 1.7 원소 접근의 비용과 삽입, 삭제

배열의 원소는 메모리에 빈틈없이 차례로 놓이고 모든 원소의 크기가 같다. 그래서 i번째 원소의 위치는 배열이 시작하는 위치에 i × (원소 하나의 크기)를 더하는 계산 한 번으로 정해지고, 배열의 길이가 10이든 100만이든 `a[i]`를 읽고 쓰는 시간은 같다. 이처럼 인덱스로 어느 원소든 같은 비용에 접근하는 것을 **임의 접근**(random access)이라 하며, 배열의 가장 큰 장점이다. 인덱스가 0부터 시작하는 것도 첫 원소의 위치가 시작 위치에 0을 더한 곳이기 때문이라고 이해할 수 있다.

반면 배열의 길이는 생성할 때 정해지므로, 원소를 중간에 끼워 넣거나 지우려면 뒤쪽 원소를 한 칸씩 옮겨야 한다. 다음 프로그램은 길이 6인 배열의 앞부분 `count`칸만 쓰는 부분적으로 찬 배열에서 원소를 삽입하고 삭제한다.

```java
import java.util.Arrays;

public class ShiftDemo {
    static int insert(int[] a, int count, int index, int value) {
        for (int i = count; i > index; i--) {
            a[i] = a[i - 1];
        }
        a[index] = value;
        return count + 1;
    }

    static int remove(int[] a, int count, int index) {
        for (int i = index; i < count - 1; i++) {
            a[i] = a[i + 1];
        }
        a[count - 1] = 0;
        return count - 1;
    }

    public static void main(String[] args) {
        int[] a = new int[6];
        int count = 0;
        count = insert(a, count, 0, 10);
        count = insert(a, count, 1, 30);
        count = insert(a, count, 1, 20);
        System.out.println(Arrays.toString(a) + " count=" + count);
        count = remove(a, count, 0);
        System.out.println(Arrays.toString(a) + " count=" + count);
    }
}
```

```text
[10, 20, 30, 0, 0, 0] count=3
[20, 30, 0, 0, 0, 0] count=2
```

`insert(a, 2, 1, 20)`을 호출할 때 배열은 `[10, 30, ...]`이다. 루프는 i = 2에서 `a[2] = a[1]`로 30을 한 칸 뒤로 옮기고, i = 1에서 멈춘 뒤 비워진 위치 1에 20을 넣는다. 옮기는 순서가 뒤에서 앞이어야 하는 이유는 앞에서부터 옮기면 `a[i + 1] = a[i]`가 아직 옮기지 않은 `a[i + 1]`의 값을 덮어써 잃어버리기 때문이다. 삭제는 반대로 지울 위치의 뒤쪽 원소를 앞으로 한 칸씩 당기므로 앞에서 뒤로 옮긴다. 두 연산 모두 최악의 경우 `count`개에 가까운 원소를 옮기므로, 원소 수에 비례하는 시간이 든다. 배열이 가득 찬 상태(`count == a.length`)에서 삽입하면 `a[count]`가 범위를 벗어나므로, 더 큰 배열을 만들어 복사해야 한다(4.1절). 크기가 바뀌는 데이터를 자주 다룰 때 9강의 `ArrayList`를 쓰는 이유가 이것이며, `ArrayList`도 내부에서는 이런 이동과 복사를 한다.

> **참고:** 원소의 순서가 중요하지 않으면 삭제를 더 빨리 할 수 있다. 지울 위치에 마지막 원소를 옮겨 넣고 `count`를 1 줄이면 원소를 한 번만 옮기면 된다. 순서를 유지해야 하는지가 연산의 비용을 정하는 예이다.

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

다음 두 메서드는 첫 번째 원소를 지금까지의 최댓값(최솟값)으로 두고, 나머지 원소와 차례로 비교하여 더 큰(작은) 원소가 나오면 그 값으로 바꾼다. 원소가 하나 이상인 배열을 가정하며, 빈 배열을 넘기면 `array[0]`에서 `ArrayIndexOutOfBoundsException`이 발생한다.

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

### 2.6 메서드로 나눈 통계 프로그램

2.2절의 `Chap5Program1`을 고쳐 최솟값과 입력 개수를 함께 출력하고, 다시 산술 평균까지 출력하도록 계산을 메서드로 나눈 프로그램은 다음과 같다.

```java
import java.util.Scanner;

public class Chap5Program3 {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		int intArray[] = new int[5];

		System.out.println("Please input five numeric values.");

		for (int i = 0; i < 5; i++)
			intArray[i] = scanner.nextInt();

		System.out.println("Maximum value is: " + getMaximumValue(intArray));
		System.out.println("Minimum value is: " + getMinimumValue(intArray));
		System.out.println("# of elements: " + intArray.length);
		System.out.println("Mean of values: " + getMean(intArray));

		scanner.close();

	}

	//Get Maximum value
	public static int getMaximumValue(int[] array) {
		int max = 0;

		for (int i = 0; i < 5; i++) {
			if (array[i] > max)
				max = array[i];
		}

		return max;
	}


	// Get Minimum value
	public static int getMinimumValue(int[] array) {
		int min = 9999;

		for (int i = 0; i < 5; i++) {
			if (array[i] < min)
				min = array[i];
		}

		return min;
	}

	// Get mean value
	public static double getMean(int[] array) {
		int sum = 0;
		double mean = 0.0d;

		for (int i = 0; i < 5; i++)
			sum += array[i];

		mean = sum / array.length;

		return mean;
	}

}
```

`main`은 입력과 출력만 맡고, 최댓값, 최솟값, 평균은 각각 배열을 인자로 받는 메서드가 계산한다. 같은 계산을 다른 배열에도 쓸 수 있고 `main`이 짧아져 읽기 쉽다. 그러나 이 프로그램을 두 입력으로 실행하면 문제가 드러난다.

```text
$ echo "1 2 3 4 6" | java Chap5Program3
Please input five numeric values.
Maximum value is: 6
Minimum value is: 1
# of elements: 5
Mean of values: 3.0
$ echo "-5 -2 -9 -1 -7" | java Chap5Program3
Please input five numeric values.
Maximum value is: 0
Minimum value is: -9
# of elements: 5
Mean of values: -4.0
```

| 위치 | 문제 | 결과 | 고친 방법 |
|:-----|:-----|:-----|:-----|
| `getMaximumValue`의 `max = 0` | 모든 값이 0보다 작으면 0이 남는다 | 두 번째 입력의 최댓값이 -1이 아니라 0 | `max = array[0]`으로 시작하고 1번 원소부터 비교한다 |
| `getMinimumValue`의 `min = 9999` | 모든 값이 9999보다 크면 9999가 남는다 | 10000 이상만 입력하면 틀린다 | `min = array[0]`으로 시작한다 |
| `getMean`의 `sum / array.length` | 두 `int`의 정수 나눗셈 | 1, 2, 3, 4, 6의 평균이 3.2가 아니라 3.0, 두 번째 입력은 -4.8이 아니라 -4.0 | `(double) sum / array.length` |
| 세 메서드의 `i < 5` | 배열의 길이를 5로 가정한다 | 다른 길이의 배열에서 원소를 빠뜨리거나 `ArrayIndexOutOfBoundsException` | `i < array.length` |

*표 2. Chap5Program3의 문제와 고친 방법*

-4.0이 나온 것은 합 -24를 5로 나눈 -4.8을 정수 나눗셈이 0 쪽으로 버렸기 때문이다(3강 4.7절). 네 가지를 모두 고친 프로그램은 다음과 같다.

```java
import java.util.Scanner;

public class Stats {
    public static int getMaximumValue(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    public static int getMinimumValue(int[] array) {
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    public static double getMean(int[] array) {
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return (double) sum / array.length;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] intArray = new int[5];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = scanner.nextInt();
        }
        System.out.println("Maximum value is: " + getMaximumValue(intArray));
        System.out.println("Minimum value is: " + getMinimumValue(intArray));
        System.out.println("# of elements: " + intArray.length);
        System.out.println("Mean of values: " + getMean(intArray));
    }
}
```

```text
$ echo "1 2 3 4 6" | java Stats
Maximum value is: 6
Minimum value is: 1
# of elements: 5
Mean of values: 3.2
$ echo "-5 -2 -9 -1 -7" | java Stats
Maximum value is: -1
Minimum value is: -9
# of elements: 5
Mean of values: -4.8
```

고친 메서드는 원소가 하나 이상인 배열을 가정한다. 빈 배열을 넘기면 `array[0]`에서 예외가 나므로, 빈 배열을 허용하려면 메서드 첫머리에서 길이를 검사하여 의미 있는 결과나 예외를 정해야 한다.

### 2.7 배열 뒤집기

배열의 원소 순서를 제자리에서 뒤집으려면 양 끝에서 시작하는 두 인덱스 `i`와 `j`를 두고, 두 원소를 바꾼 뒤 `i`는 늘리고 `j`는 줄이기를 두 인덱스가 만날 때까지 되풀이한다.

```java
static void reverse(int[] a) {
    for (int i = 0, j = a.length - 1; i < j; i++, j--) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
```

`{1, 2, 3, 4, 5}`를 넘기면 (i, j)가 (0, 4), (1, 3)일 때 두 번 교환한 뒤 i = j = 2에서 멈추고, 가운데 원소 3은 그대로 남아 `[5, 4, 3, 2, 1]`이 된다. 조건을 `i < j` 대신 `i < a.length`로 쓰면 뒤쪽 절반에서 다시 교환하여 원래 순서로 돌아간다. 두 값을 바꿀 때 임시 변수 `tmp`가 필요한 이유는 `a[i] = a[j];`를 먼저 실행하는 순간 원래 `a[i]`의 값이 사라지기 때문이다. 이 메서드는 배열의 참조를 받아 원소를 바꾸므로 호출한 쪽의 배열이 뒤집힌다(3절).

### 2.8 이진 검색

2.5절의 선형 검색은 원소 n개를 모두 볼 수 있다. 배열이 오름차순으로 정렬되어 있으면 **이진 검색**(binary search)으로 훨씬 빨리 찾을 수 있다. 검색 범위 `[low, high]`의 가운데 원소 `a[mid]`를 목표와 비교하여, 목표가 더 크면 오른쪽 절반을, 더 작으면 왼쪽 절반을 남기고 나머지를 버린다.

```java
static int binarySearch(int[] a, int target) {
    int low = 0, high = a.length - 1;
    while (low <= high) {
        int mid = (low + high) / 2;
        System.out.println("low=" + low + " high=" + high + " mid=" + mid + " a[mid]=" + a[mid]);
        if (a[mid] == target) {
            return mid;
        } else if (a[mid] < target) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }
    return -1;
}
```

`{1, 3, 5, 7, 9, 11, 13}`에서 `System.out.println("found at " + binarySearch(a, 11))`처럼 11과 4를 찾으면 다음과 같이 출력된다.

```text
low=0 high=6 mid=3 a[mid]=7
low=4 high=6 mid=5 a[mid]=11
found at 5
low=0 high=6 mid=3 a[mid]=7
low=0 high=2 mid=1 a[mid]=3
low=2 high=2 mid=2 a[mid]=5
found at -1
```

11을 찾을 때는 가운데의 7이 11보다 작으므로 `low`를 4로 옮기고, 남은 범위 4\~6의 가운데 11에서 찾는다. 4를 찾을 때는 7보다 작아 범위가 0\~2로, 3보다 커서 2\~2로 줄고, 5보다 작아 `high`가 1이 되면 `low > high`가 되어 -1을 반환한다. 비교할 때마다 범위가 절반으로 줄므로, 원소 n개에서 비교 횟수는 많아야 약 $`\log_2 n + 1`$번이다. 원소가 100만 개이면 선형 검색은 최대 100만 번, 이진 검색은 최대 20번 비교한다.

> **주의:** 이진 검색은 정렬된 배열에서만 올바르게 동작한다. 정렬되지 않은 배열에 쓰면 목표가 있어도 찾지 못할 수 있다. 또 `low`와 `high`가 매우 큰 배열에서는 `low + high`가 `int` 범위를 넘을 수 있으므로 `low + (high - low) / 2`로 가운데를 구하는 편이 안전하다(3강 2.5절).

### 2.9 버블 정렬

**버블 정렬**(bubble sort)은 이웃한 두 원소를 비교하여 순서가 틀렸으면 바꾸는 일을 배열 끝까지 되풀이하는 정렬 알고리즘이다. 한 번 끝까지 지나가는 것을 패스(pass)라 하며, 패스가 한 번 끝날 때마다 아직 정렬되지 않은 부분의 가장 큰 원소가 거품처럼 맨 뒤로 올라간다. 그래서 k번째 패스는 뒤쪽 k - 1개를 다시 비교할 필요가 없다.

```java
static void bubbleSort(int[] a) {
    for (int pass = 1; pass < a.length; pass++) {
        for (int i = 0; i < a.length - pass; i++) {
            if (a[i] > a[i + 1]) {
                int tmp = a[i];
                a[i] = a[i + 1];
                a[i + 1] = tmp;
            }
        }
        System.out.println("after pass " + pass + ": " + Arrays.toString(a));
    }
}
```

```text
after pass 1: [3, 5, 1, 7, 9]
after pass 2: [3, 1, 5, 7, 9]
after pass 3: [1, 3, 5, 7, 9]
after pass 4: [1, 3, 5, 7, 9]
```

`{5, 3, 9, 1, 7}`의 첫 패스는 (5, 3)을 바꾸고, (5, 9)는 그대로 두고, (9, 1)과 (9, 7)을 바꾸어 가장 큰 9를 맨 뒤로 보낸다. 둘째 패스는 앞의 네 칸에서 7을 제자리로, 셋째 패스는 5를 제자리로 보낸다. 원소 n개의 배열에서 비교 횟수는 다음과 같다.

```math
(n-1) + (n-2) + \cdots + 1 = \frac{n(n-1)}{2}
```

n = 5이면 10번이다. 넷째 패스처럼 한 번도 교환하지 않은 패스가 나오면 이미 정렬된 것이므로, 교환 여부를 기록하는 `boolean` 변수를 두어 그 자리에서 멈추게 고칠 수 있다. 버블 정렬은 원리가 단순하지만 비교 횟수가 n의 제곱에 비례하므로, 실제 프로그램에서는 4절의 `Arrays.sort`처럼 더 빠른 알고리즘으로 구현된 라이브러리 메서드를 쓴다.

### 2.10 선택 정렬

**선택 정렬**(selection sort)은 정렬되지 않은 부분에서 가장 작은 원소를 찾아 그 부분의 맨 앞 원소와 바꾸는 일을 되풀이한다. i번째 반복이 끝나면 위치 0\~i에 가장 작은 원소 i + 1개가 순서대로 놓인다.

```java
import java.util.Arrays;

public class SelectionSort {
    static void selectionSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            int tmp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = tmp;
            System.out.println("i=" + i + ": " + Arrays.toString(a));
        }
    }

    public static void main(String[] args) {
        int[] data = {5, 3, 9, 1, 7};
        selectionSort(data);
    }
}
```

```text
i=0: [1, 3, 9, 5, 7]
i=1: [1, 3, 9, 5, 7]
i=2: [1, 3, 5, 9, 7]
i=3: [1, 3, 5, 7, 9]
```

i = 0에서는 전체에서 가장 작은 1(위치 3)을 찾아 맨 앞의 5와 바꾼다. i = 1에서는 남은 부분의 최솟값 3이 이미 위치 1에 있으므로 자기 자신과 바꾸어 배열이 그대로이다. 안쪽 루프는 2.2절의 최솟값 탐색이며, 값 대신 위치 `minIndex`를 기억해야 교환할 수 있다. 비교 횟수는 버블 정렬과 같은 n(n - 1) / 2이지만, 교환은 반복마다 한 번씩 많아야 n - 1번이다. 버블 정렬은 이웃한 원소를 여러 번 바꾸므로 교환 횟수가 n(n - 1) / 2에 이를 수 있다. 원소를 옮기는 비용이 큰 경우 선택 정렬이 유리한 이유이다. 다만 선택 정렬은 이미 정렬된 배열에서도 비교 횟수가 줄지 않는다.

### 2.11 빈도를 세는 배열

값의 범위가 작은 정수이면 그 값 자체를 인덱스로 쓰는 배열로 빈도를 셀 수 있다. 주사위를 열 번 던진 결과에서 각 눈이 나온 횟수를 센다.

```java
public class Frequency {
    public static void main(String[] args) {
        int[] rolls = {3, 6, 1, 3, 5, 6, 6, 2, 3, 6};
        int[] counts = new int[7];
        for (int r : rolls) {
            counts[r]++;
        }
        for (int face = 1; face <= 6; face++) {
            System.out.println(face + ": " + "*".repeat(counts[face]) + " (" + counts[face] + ")");
        }
    }
}
```

```text
1: * (1)
2: * (1)
3: *** (3)
4:  (0)
5: * (1)
6: **** (4)
```

`counts`의 길이를 7로 잡아 인덱스 1\~6을 주사위 눈에 그대로 대응시키고, 인덱스 0은 쓰지 않는다. `counts[r]++`는 값 `r`을 만날 때마다 그 칸을 1 늘리며, 원소의 기본값이 0이므로 따로 초기화할 필요가 없다(1.4절). 모든 값을 한 번씩 보면 끝나므로 원소 n개에 n번의 갱신으로 빈도표가 완성된다. 값이 범위를 벗어나면(예: 7) `ArrayIndexOutOfBoundsException`이 나므로, 입력을 믿을 수 없으면 범위를 먼저 검사한다. 마지막 루프는 2강 4.7절의 `repeat`로 빈도만큼 `*`를 찍어 막대그래프처럼 보여 준다.

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

### 3.1 원소 교환과 변수 교환

배열을 인자로 넘기는 것과 기본형 값을 넘기는 것의 차이는 두 값을 바꾸는 메서드에서 잘 드러난다. Java는 인자의 값을 매개변수에 복사하므로(6강 2.4절), 기본형 변수 두 개를 넘기면 메서드는 그 복사본만 바꿀 수 있다. 배열을 넘기면 배열의 참조가 복사되므로, 메서드는 호출한 쪽과 같은 배열의 원소를 바꿀 수 있다.

```java
public class SwapDemo {
    static void swap(int x, int y) {
        int tmp = x;
        x = y;
        y = tmp;
    }

    static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    static int sum(int... nums) {
        int total = 0;
        for (int n : nums) {
            total += n;
        }
        return total;
    }

    public static void main(String[] args) {
        int p = 1, q = 2;
        swap(p, q);
        System.out.println(p + " " + q);
        int[] arr = {1, 2};
        swap(arr, 0, 1);
        System.out.println(arr[0] + " " + arr[1]);
        System.out.println(sum() + " " + sum(5) + " " + sum(1, 2, 3) + " " + sum(new int[]{4, 5}));
    }
}
```

```text
1 2
2 1
0 5 6 9
```

```text
 swap(p, q) 실행 중                       swap(arr, 0, 1) 실행 중

 main:  p 1   q 2                         main:  arr ●──┐
 swap:  x 2   y 1   (copies swapped)                    ├──▶ [ 2 | 1 ]
                                          swap:  a   ●──┘
```

*그림 6. 기본형 인자와 배열 인자를 넘긴 swap의 메모리 상태*

첫 번째 `swap`은 `x`와 `y`를 바꾸지만 두 매개변수는 `p`와 `q`의 복사본이므로 메서드가 끝나면 사라지고, `p`와 `q`는 1과 2 그대로이다. 두 번째 `swap`의 `a`는 `arr`와 같은 배열을 가리키므로 원소 교환이 호출한 쪽에 그대로 보인다. 같은 이름 `swap`의 두 메서드는 매개변수 목록이 다르므로 오버로딩이다(6강 2.2절).

### 3.2 가변 인자 메서드

위의 `sum(int... nums)`처럼 매개변수의 자료형 뒤에 `...`을 붙이면, 그 메서드는 같은 자료형의 인자를 0개 이상 몇 개든 받는 **가변 인자**(variable arity) 메서드가 된다. 컴파일러는 호출에 쓴 인자들을 배열로 묶어 넘기므로, 메서드 안에서 `nums`는 보통의 `int[]` 배열이다. 그래서 `sum()`은 길이 0인 배열을 받아 0을, `sum(1, 2, 3)`은 6을 돌려주며, 이미 만든 배열을 `sum(new int[]{4, 5})`처럼 직접 넘길 수도 있다. 4강 5.1절의 `printf`가 서식 문자열 뒤에 인자를 몇 개든 받을 수 있는 것도 가변 인자 덕분이다. 가변 인자는 매개변수 목록의 마지막에 하나만 둘 수 있다.

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

*표 3. Arrays 클래스의 주요 메서드*

`copyOf`가 만든 배열은 원본과 별개이므로, `copy`를 정렬해도 `data`는 바뀌지 않는다.

### 4.1 배열 복사의 방법과 얕은 복사

배열을 복사하는 방법은 여러 가지이며, 모두 새 배열 객체를 만든다는 점에서 1.5절의 배열 공유와 다르다.

```java
import java.util.Arrays;

public class CopyDemo {
    public static void main(String[] args) {
        int[] src = {10, 20, 30, 40, 50};
        int[] c1 = src.clone();
        int[] c2 = Arrays.copyOf(src, 7);
        int[] c3 = Arrays.copyOfRange(src, 1, 4);
        int[] c4 = new int[5];
        System.arraycopy(src, 0, c4, 1, 3);
        src[0] = 99;
        System.out.println(Arrays.toString(c1));
        System.out.println(Arrays.toString(c2));
        System.out.println(Arrays.toString(c3));
        System.out.println(Arrays.toString(c4));

        StringBuilder[] boxes = {new StringBuilder("a"), new StringBuilder("b")};
        StringBuilder[] shallow = boxes.clone();
        shallow[0].append("!");
        shallow[1] = new StringBuilder("z");
        System.out.println(boxes[0] + " " + boxes[1] + " " + (boxes[0] == shallow[0]));
    }
}
```

```text
[10, 20, 30, 40, 50]
[10, 20, 30, 40, 50, 0, 0]
[20, 30, 40]
[0, 10, 20, 30, 0]
a! b true
```

| 방법 | 하는 일 | 위 예의 결과 |
|:-----|:-----|:-----|
| `a.clone()` | 같은 길이의 새 배열에 모든 원소를 복사한다 | `c1`은 `src`의 사본 |
| `Arrays.copyOf(a, n)` | 길이 n의 새 배열에 복사한다. 길면 기본값으로 채운다 | `c2`는 끝에 0 두 개 |
| `Arrays.copyOfRange(a, from, to)` | 위치 `from`부터 `to - 1`까지를 새 배열로 복사한다 | `c3`은 위치 1\~3 |
| `System.arraycopy(src, si, dst, di, n)` | 이미 있는 배열 `dst`의 위치 `di`부터 `src`의 위치 `si`에서 n개를 복사한다 | `c4`의 위치 1\~3 |

*표 4. 배열을 복사하는 방법*

복사한 뒤 `src[0] = 99;`로 원본을 바꾸어도 네 사본은 바뀌지 않는다. 원소가 기본형이면 값 자체가 복사되기 때문이다.

원소가 참조형이면 사정이 다르다. 배열 복사는 원소 칸에 들어 있는 값, 즉 참조만 복사하고 참조가 가리키는 객체는 복사하지 않는다. 이를 **얕은 복사**(shallow copy)라 한다. 위 예에서 `shallow`는 새 배열이지만 그 원소는 `boxes`의 원소와 같은 `StringBuilder` 객체를 가리키므로, `shallow[0].append("!")`로 바꾼 내용이 `boxes[0]`에서도 보이고 `boxes[0] == shallow[0]`은 `true`이다. 반면 `shallow[1] = new StringBuilder("z");`는 `shallow`의 칸 하나에 다른 참조를 넣을 뿐이므로 `boxes[1]`은 여전히 `"b"`이다.

```text
 boxes   ●──▶ [ ● | ● ]          shallow ●──▶ [ ● | ● ]
                │    │                         │    │
                │    └──▶ "b"                  │    └──▶ "z"
                └──────────────▶ "a!" ◀────────┘
```

*그림 7. 얕은 복사 뒤 두 배열이 원소 객체를 공유하는 모습*

원소 객체까지 새로 만들어 복사하는 것을 **깊은 복사**(deep copy)라 하며, 원소마다 새 객체를 만드는 반복문을 직접 써야 한다. `String`처럼 불변인 객체의 배열은 얕은 복사로도 충분하다. 공유된 객체를 아무도 바꿀 수 없기 때문이다(2강 4.5절).

### 4.2 그 밖의 Arrays 메서드

`Arrays`에는 표 3의 메서드 말고도 다음 메서드가 자주 쓰인다.

```java
import java.util.Arrays;

public class ArraysMore {
    public static void main(String[] args) {
        int[] a = new int[6];
        Arrays.fill(a, 7);
        System.out.println(Arrays.toString(a));
        int[] b = {9, 4, 8, 1, 6, 2};
        Arrays.sort(b, 1, 4);
        System.out.println(Arrays.toString(b));
        int[] s = {1, 3, 5, 7, 9};
        System.out.println(Arrays.binarySearch(s, 7) + " " + Arrays.binarySearch(s, 4) + " " + Arrays.binarySearch(s, 10));
        int[][] m = {{1, 2}, {3, 4}};
        int[][] n = {{1, 2}, {3, 4}};
        System.out.println(Arrays.toString(m).startsWith("[[I@") + " " + Arrays.deepToString(m));
        System.out.println(Arrays.equals(m, n) + " " + Arrays.deepEquals(m, n));
        String[] words = {"pear", "Apple", "banana"};
        Arrays.sort(words);
        System.out.println(Arrays.toString(words));
    }
}
```

```text
[7, 7, 7, 7, 7, 7]
[9, 1, 4, 8, 6, 2]
3 -3 -6
true [[1, 2], [3, 4]]
false true
[Apple, banana, pear]
```

- `Arrays.fill(a, v)`는 모든 원소를 `v`로 채운다.
- `Arrays.sort(a, from, to)`는 위치 `from`부터 `to - 1`까지만 정렬한다. 위치 1\~3의 4, 8, 1이 1, 4, 8로 바뀌고 나머지는 그대로이다.
- `Arrays.binarySearch(a, key)`는 정렬된 배열에서 2.8절의 이진 검색을 하여 찾은 위치를 돌려준다. 찾지 못하면 그 값을 넣어야 할 위치(삽입 위치) p에 대해 -(p + 1)을 돌려준다. 4는 위치 2에 들어가야 하므로 -3, 10은 위치 5에 들어가야 하므로 -6이다. 음수가 나오면 없는 것이고, 삽입 위치도 함께 알 수 있다.
- 2차원 배열(6강 1절)에서 `Arrays.toString`은 행 배열의 참조를 `[[I@...` 형태로 보여 주고, `Arrays.equals`는 행 배열을 `==`로 비교하므로 내용이 같아도 `false`이다. 2차원 배열에는 `deepToString`과 `deepEquals`를 쓴다.
- 문자열 배열의 `sort`는 2강 4.7절의 `compareTo` 순서를 따르므로 대문자로 시작하는 `"Apple"`이 소문자보다 앞에 온다.

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
| 배열을 `println`으로 바로 출력 | `[I@...` 대신 원소를 보려면 `Arrays.toString()` 사용 |
| 배열의 `length`에 괄호를 붙이거나 문자열의 `length`에 괄호를 빠뜨림 | 배열은 필드 `a.length`, 문자열은 메서드 `s.length()` |
| 객체 배열을 만든 뒤 원소 객체를 만들지 않음 | `new Account[4]`는 `null` 네 칸이므로 원소마다 `new`로 객체를 만들어 대입 |
| 향상된 `for`의 변수에 대입하여 원소를 바꾸려 함 | 인덱스 기반 `for`로 `a[i] = ...` 대입 |
| 정렬하지 않은 배열에 이진 검색 | 먼저 `Arrays.sort()`로 정렬 |
| 참조형 배열의 복사를 깊은 복사로 착각 | 원소 객체까지 필요하면 원소마다 새 객체를 만들어 복사 |

*표 5. 배열을 다룰 때 흔한 실수와 해결 방법*

---

<br>

## 6. 객체의 배열

### 6.1 객체 배열의 생성

배열의 원소는 기본형뿐 아니라 어떤 클래스의 객체여도 된다. 1.6절에서 본 것처럼 참조형 배열을 만들면 원소 칸만 생기고 각 칸은 `null`이므로, 객체의 배열은 두 단계로 만든다. 먼저 `new 클래스이름[크기]`로 참조를 담을 배열을 만들고, 그다음 원소마다 `new`로 객체를 만들어 대입한다. 다음 프로그램은 2.1절의 강수량 예처럼 이름과 잔액을 두 배열에 준비해 두고, 반복문으로 `Account` 객체 네 개를 만든다.

```java
public class Account {
    private String owner;
    private double balance;

    public Account(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }
}
```

```java
public class AccountArray {
    public static void main(String[] args) {
        Account[] accounts = new Account[4];
        System.out.println(accounts[0]);

        String[] owners = {"Kim", "Lee", "Park", "Choi"};
        double[] balances = {1200.0, 350.5, 4800.0, 950.0};
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account(owners[i], balances[i]);
        }

        Account richest = accounts[0];
        int countOver1000 = 0;
        double total = 0.0;
        for (Account acc : accounts) {
            if (acc.getBalance() > richest.getBalance()) {
                richest = acc;
            }
            if (acc.getBalance() >= 1000.0) {
                countOver1000++;
            }
            total += acc.getBalance();
        }
        System.out.println(richest.getOwner() + " " + richest.getBalance());
        System.out.println(countOver1000 + " " + total);
    }
}
```

```text
null
Park 4800.0
2 7300.5
```

첫 출력의 `null`은 `new Account[4]`를 실행한 직후에는 아직 `Account` 객체가 하나도 없다는 것을 보여 준다. 이 상태에서 `accounts[0].getBalance()`를 호출하면 `NullPointerException`이 난다. `for` 루프가 끝나면 네 칸이 각각 서로 다른 `Account` 객체를 가리킨다.

```text
 accounts ●──▶ [ ● | ● | ● | ● ]
                 │   │   │   └──▶ Account("Choi", 950.0)
                 │   │   └──────▶ Account("Park", 4800.0)
                 │   └──────────▶ Account("Lee", 350.5)
                 └──────────────▶ Account("Kim", 1200.0)
```

*그림 8. 원소마다 객체를 만든 뒤의 Account 배열*

### 6.2 객체 배열의 처리

6.1절의 두 번째 루프는 2.2절의 최댓값 탐색과 같은 방법으로 잔액이 가장 큰 계좌를 찾는다. 다른 점은 값이 아니라 객체의 참조 `richest`를 갱신한다는 것이다. 루프가 끝나면 `richest`는 그 계좌 객체를 가리키므로, 잔액뿐 아니라 계좌주 이름도 `richest.getOwner()`로 얻을 수 있다. 같은 루프 안에서 잔액이 1000.0 이상인 계좌의 수(Kim과 Park의 2개)와 잔액의 합 1200.0 + 350.5 + 4800.0 + 950.0 = 7300.5도 함께 구한다.

이름과 잔액을 `owners`와 `balances`처럼 따로 둔 배열을 **평행 배열**(parallel arrays)이라 한다. 같은 인덱스가 같은 대상의 정보를 나타낸다는 약속에 기대므로, 한 배열만 정렬하거나 원소를 지우면 두 배열의 대응이 깨진다. 객체의 배열은 한 대상의 정보를 객체 하나에 묶으므로 이런 문제가 없고, 정보가 늘어나도 클래스에 필드를 더하면 된다. 1강 1.4절에서 본 객체 지향 설계의 장점이 배열에서도 그대로 나타난다.

> **주의:** 객체 배열을 `Arrays.sort`로 정렬하려면 객체끼리의 순서를 정하는 방법이 필요하다. `String`과 래퍼 클래스는 순서가 정해져 있지만, `Account`처럼 직접 만든 클래스는 순서를 정하는 방법을 따로 제공해야 하며, 그 방법은 9강의 컬렉션에서 다룬다.

---

<br>

## 개념 적용

**T/F 점검:**

| 문장 | 판단과 근거 |
|:-----|:-----|
| `int[] a = new int[3];`의 `a.length`는 메서드이므로 `a.length()`로 쓴다. | **F.** 배열의 `length`는 `final` 필드이므로 괄호 없이 쓴다. 괄호가 붙는 것은 `String`의 `length()`이다. |
| `String[] s = new String[2];`를 실행하면 빈 문자열 `""` 두 개가 만들어진다. | **F.** 참조 두 칸이 만들어지고 각 칸은 `null`이다. 문자열 객체는 따로 대입해야 생긴다. |
| 이진 검색은 정렬되지 않은 배열에서도 목표를 찾는다. | **F.** 범위의 절반을 버리는 판단이 정렬 순서에 기대므로, 정렬되지 않은 배열에서는 있는 값도 못 찾을 수 있다. |
| 원소 5개의 버블 정렬은 최대 10번 비교한다. | **T.** 패스마다 4, 3, 2, 1번 비교하므로 합은 5 × 4 / 2 = 10이다. |
| `swap(int x, int y)`는 호출한 쪽의 두 변수의 값을 바꾼다. | **F.** 매개변수는 인자 값의 복사본이므로 복사본만 바뀐다. 배열의 원소를 바꾸는 `swap(int[] a, int i, int j)`는 호출한 쪽에 보인다. |
| `Account[]` 배열을 `clone()`하면 `Account` 객체도 새로 만들어진다. | **F.** 얕은 복사이므로 새 배열의 원소는 같은 `Account` 객체를 가리킨다. |
| `Arrays.binarySearch`가 음수를 돌려주면 그 값은 배열에 없다. | **T.** 찾지 못하면 삽입 위치 p에 대해 -(p + 1)을 돌려주며, 이 값은 항상 음수이다. |

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
| 객체로서의 배열 | 배열은 힙에 만들어지는 객체로, `final` 필드 `length`를 가지며, 참조형 배열의 원소는 처음에 `null`이다. |
| 통계 메서드의 오류 | 최댓값과 최솟값은 첫 원소로 시작하고, 평균은 실수 나눗셈으로, 반복 범위는 `array.length`로 정한다. |
| 배열 뒤집기 | 양 끝의 두 인덱스가 만날 때까지 원소를 교환한다. |
| 이진 검색 | 정렬된 배열에서 가운데 원소와 비교하여 범위를 절반씩 줄이며, 비교 횟수는 약 $`\log_2 n`$이다. |
| 버블 정렬 | 이웃한 원소를 비교하여 바꾸는 패스를 되풀이하며, 비교 횟수는 n(n - 1) / 2이다. |
| 가변 인자 | `int... nums`는 인자 0개 이상을 배열로 받으며, 매개변수 목록의 마지막에 하나만 둔다. |
| 배열 복사 | `clone`, `copyOf`, `copyOfRange`, `System.arraycopy`는 새 배열을 만들며, 참조형 원소는 얕게 복사된다. |
| 객체의 배열 | 참조 배열을 만든 뒤 원소마다 객체를 만들어 대입하며, 평행 배열보다 한 대상의 정보를 안전하게 묶는다. |
| 임의 접근 | 배열은 인덱스 계산 한 번으로 어느 원소든 같은 비용에 접근하지만, 중간 삽입과 삭제에는 원소 이동이 필요하다. |
| 선택 정렬 | 남은 부분의 최솟값을 앞으로 보내며, 비교는 n(n - 1) / 2번이지만 교환은 많아야 n - 1번이다. |
| 빈도 배열 | 작은 범위의 정수 값을 인덱스로 써서 `counts[value]++`로 빈도를 센다. |

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

6. **이진 검색 추적:** `{2, 4, 6, 8, 10, 12, 14, 16}`에서 2.8절의 `binarySearch`로 14를 찾을 때 각 반복의 `low`, `high`, `mid`를 쓰고 반환값을 쓰라.

   <details>
   <summary>정답</summary>

   처음 low = 0, high = 7이므로 mid = 3이고 a[3] = 8 < 14라서 low = 4가 된다. 다음 mid = (4 + 7) / 2 = 5이고 a[5] = 12 < 14라서 low = 6이 된다. 다음 mid = (6 + 7) / 2 = 6이고 a[6] = 14이므로 6을 반환한다. 세 번 비교했다.

   </details>

7. **버블 정렬 추적:** `{4, 2, 3, 1}`에 2.9절의 `bubbleSort`를 적용할 때 각 패스 뒤의 배열을 쓰라.

   <details>
   <summary>정답</summary>

   첫 패스는 (4, 2), (4, 3), (4, 1)을 모두 바꾸어 `[2, 3, 1, 4]`가 된다. 둘째 패스는 (2, 3)은 그대로, (3, 1)을 바꾸어 `[2, 1, 3, 4]`가 된다. 셋째 패스는 (2, 1)을 바꾸어 `[1, 2, 3, 4]`가 된다.

   </details>

8. **얕은 복사:** 다음 코드를 실행한 뒤 출력을 쓰고 이유를 설명하라.

   ```java
   int[][] grid = {{1, 2}, {3, 4}};
   int[][] copy = grid.clone();
   copy[0][0] = 9;
   copy[1] = new int[]{7, 7};
   System.out.println(grid[0][0] + " " + grid[1][0]);
   ```

   <details>
   <summary>정답</summary>

   `9 3`을 출력한다. 2차원 배열은 행 배열의 참조를 원소로 갖는 배열이므로 `clone()`은 행 참조만 복사한다. `copy[0]`과 `grid[0]`은 같은 행 배열을 가리키므로 `copy[0][0] = 9`가 `grid[0][0]`을 바꾼다. `copy[1]`에는 새 행을 대입했을 뿐이므로 `grid[1]`은 원래의 `{3, 4}`를 가리킨다.

   </details>

---

<br>

## 참고 문헌

1. J. Gosling, B. Joy, G. Steele, G. Bracha, A. Buckley, D. Smith, and G. Bierman, *The Java Language Specification, Java SE 21 Edition*, Ch. 10. Oracle, 2023. [Online]. Available: https://docs.oracle.com/javase/specs/jls/se21/html/jls-10.html
