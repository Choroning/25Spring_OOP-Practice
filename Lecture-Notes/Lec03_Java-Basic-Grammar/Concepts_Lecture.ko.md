# 3강 — Java 기본 문법

[레포지토리 안내](../../README.ko.md) | [English](Concepts_Lecture.md) | [한국어](Concepts_Lecture.ko.md)

> **최종 수정일:** 2026-11-19

> **학습 목표:**
> 1. 식별자 규칙과 이름 짓기 관례에 맞게 변수, 상수, 클래스의 이름을 지을 수 있다
> 2. Java 데이터 타입, 리터럴, 연산자, 타입 캐스팅을 사용할 수 있다
> 3. 기본형과 참조형의 차이를 메모리 상태로 설명할 수 있다
> 4. String 메서드와 포맷팅을 적용할 수 있다
> 5. Scanner 클래스로 콘솔 입력을 처리할 수 있다
> 6. 정수 오버플로, 부동소수점 오차, 정수 나눗셈과 나머지, 확대 변환과 축소 변환의 결과를 계산으로 확인할 수 있다

---

## 목차

- [1. 식별자와 키워드](#1-식별자와-키워드)
  - [1.1 식별자 규칙](#11-식별자-규칙)
  - [1.2 Java 키워드](#12-java-키워드)
  - [1.3 이름 짓기 관례](#13-이름-짓기-관례)
- [2. 기본 자료형](#2-기본-자료형)
  - [2.1 자료형의 종류와 범위](#21-자료형의-종류와-범위)
  - [2.2 기본형과 참조형](#22-기본형과-참조형)
  - [2.3 리터럴](#23-리터럴)
  - [2.4 var 키워드](#24-var-키워드)
  - [2.5 정수의 2진 표현과 오버플로](#25-정수의-2진-표현과-오버플로)
  - [2.6 부동소수점 수의 표현과 오차](#26-부동소수점-수의-표현과-오차)
  - [2.7 래퍼 클래스와 오토박싱](#27-래퍼-클래스와-오토박싱)
- [3. 변수와 상수](#3-변수와-상수)
- [4. 연산자](#4-연산자)
  - [4.1 산술 연산자](#41-산술-연산자)
  - [4.2 연산자 우선순위](#42-연산자-우선순위)
  - [4.3 대입 연산자](#43-대입-연산자)
  - [4.4 증감 연산자](#44-증감-연산자)
  - [4.5 관계 및 논리 연산자](#45-관계-및-논리-연산자)
  - [4.6 + 연산자의 두 가지 의미](#46--연산자의-두-가지-의미)
  - [4.7 정수 나눗셈과 나머지의 부호](#47-정수-나눗셈과-나머지의-부호)
  - [4.8 단축 평가와 비트 연산자](#48-단축-평가와-비트-연산자)
  - [4.9 전체 우선순위와 결합 방향](#49-전체-우선순위와-결합-방향)
- [5. 형변환](#5-형변환)
  - [5.1 확대 변환과 축소 변환의 규칙](#51-확대-변환과-축소-변환의-규칙)
  - [5.2 산술 승격과 복합 대입](#52-산술-승격과-복합-대입)
  - [5.3 실수를 정수로 바꾸는 방법](#53-실수를-정수로-바꾸는-방법)
- [6. 콘솔 및 대화 상자 입출력](#6-콘솔-및-대화-상자-입출력)
  - [6.1 표준 출력](#61-표준-출력)
  - [6.2 DecimalFormat으로 숫자 서식 지정](#62-decimalformat으로-숫자-서식-지정)
  - [6.3 콘솔 입력을 위한 Scanner](#63-콘솔-입력을-위한-scanner)
  - [6.4 GUI 대화 상자를 위한 JOptionPane](#64-gui-대화-상자를-위한-joptionpane)
- [7. 문자열 연산](#7-문자열-연산)
- [8. 예제: 초를 시, 분, 초로 바꾸기](#8-예제-초를-시-분-초로-바꾸기)
- [9. 흔한 실수](#9-흔한-실수)
- [개념 적용](#개념-적용)
- [요약](#요약)
- [점검 문제](#점검-문제)
- [참고 문헌](#참고-문헌)

---

<br>

## 1. 식별자와 키워드

### 1.1 식별자 규칙

**식별자**(identifier)는 변수, 상수, 메서드, 클래스 등을 구별하기 위해 붙이는 이름이다. Java의 식별자는 다음 규칙을 따른다.

- 첫 글자는 문자, 밑줄(`_`), 달러 기호(`$`) 중 하나이고, 그 뒤에는 숫자도 올 수 있다. 숫자로 시작할 수 없다.
- `%` 같은 그 밖의 특수 문자는 쓸 수 없다.
- 키워드(1.2절)와 리터럴 `true`, `false`, `null`은 식별자로 쓸 수 없다.
- 대소문자를 구별한다. `barChart`와 `barchart`는 서로 다른 식별자이다.
- 유니코드 문자를 쓸 수 있으므로 한글 식별자(`가격`)도 가능하다. 길이 제한은 없다.

```java
// 가능한 식별자
int name;
char student_ID;
void $func() { }
class Monster3 { }
int whatsyournamemynameiskitae;
int barChart; int barchart;   // 대소문자가 다르므로 서로 다른 변수
int 가격;

// 불가능한 식별자
int 3Chapter;        // 숫자로 시작
class if { }         // 키워드
char false;          // 리터럴
void null() { }      // 리터럴
class %calc { }      // 특수 문자 %
```

### 1.2 Java 키워드

키워드는 Java 언어가 특별한 의미로 예약한 단어이며 식별자로 쓸 수 없다.

| | | | | |
|:-----|:-----|:-----|:-----|:-----|
| `abstract` | `continue` | `for` | `new` | `switch` |
| `assert` | `default` | `if` | `package` | `synchronized` |
| `boolean` | `do` | `goto` | `private` | `this` |
| `break` | `double` | `implements` | `protected` | `throw` |
| `byte` | `else` | `import` | `public` | `throws` |
| `case` | `enum` | `instanceof` | `return` | `transient` |
| `catch` | `extends` | `int` | `short` | `try` |
| `char` | `final` | `interface` | `static` | `void` |
| `class` | `finally` | `long` | `strictfp` | `volatile` |
| `const` | `float` | `native` | `super` | `while` |

*표 1. Java 키워드*

`goto`와 `const`는 예약만 되어 있고 실제로 쓰이지 않는다.

### 1.3 이름 짓기 관례

- 목적이 드러나는 이름을 쓴다. 합계를 담는 변수라면 `s`보다 `sum`이 낫다.
- 클래스 이름은 대문자로 시작하고 단어마다 첫 글자를 대문자로 쓴다: `PascalCase` (예: `BankAccount`, `HelloWorld`, `AutoVendingMachine`).
- 변수와 메서드 이름은 소문자로 시작하고, 두 번째 단어부터 첫 글자를 대문자로 쓴다: `camelCase` (예: `studentName`, `myAge`, `isSingle`, `getAge()`).
- 상수 이름은 모든 글자를 대문자로 쓰고 단어를 밑줄로 잇는다: `UPPER_SNAKE_CASE` (예: `MAX_SIZE`, `PI`).

---

<br>

## 2. 기본 자료형

### 2.1 자료형의 종류와 범위

Java에는 8가지 기본 자료형(primitive data type)이 있다.

| 자료형 | 크기 | 범위 / 설명 |
|:-------|:-----|:-----------|
| `byte` | 1 byte | -128 ~ 127 |
| `short` | 2 bytes | -32,768 ~ 32,767 |
| `int` | 4 bytes | $-2^{31}$ ~ $2^{31}-1$ |
| `long` | 8 bytes | $-2^{63}$ ~ $2^{63}-1$ |
| `float` | 4 bytes | IEEE 754 단정밀도, 약 -3.40282347E+38 ~ 3.40282347E+38 |
| `double` | 8 bytes | IEEE 754 배정밀도, 약 -1.79769313486231570E+308 ~ 1.79769313486231570E+308 |
| `char` | 2 bytes | 유니코드 문자 (0 ~ 65,535) |
| `boolean` | JVM 구현에 따라 다름 | `true` 또는 `false` |

*표 2. Java의 기본 자료형과 범위*

`boolean`이 나타내는 정보는 1비트이지만, 실제로 차지하는 메모리 크기는 Java 명세에 정해져 있지 않고 JVM 구현에 따라 다르다. 수치 자료형 여섯 가지(`byte`, `short`, `int`, `long`, `float`, `double`)는 저장할 수 있는 값의 범위와 정밀도가 다르며, 정수형 네 가지는 정수를, `float`와 `double`은 실수를 저장한다.

필드와 배열 원소는 값을 대입하지 않아도 만들어질 때 자료형의 **기본값**(default value)으로 채워진다. 기본값은 `byte`, `short`, `int`가 0, `long`이 0L, `float`가 0.0f, `double`이 0.0, `char`가 `'\u0000'`, `boolean`이 `false`이고, 모든 참조형은 `null`이다 [1]. 반면 메서드 안의 지역 변수는 기본값을 받지 않으므로, 값을 대입하기 전에 읽으면 다음 컴파일 오류가 난다.

```text
Uninit.java:4: error: variable local might not have been initialized
        System.out.println(local);
                           ^
1 error
```

> **핵심:** `char`(16비트 부호 없는 유니코드 코드 단위)를 제외하면 Java의 정수 자료형은 모두 부호 있는 형이며, unsigned 정수 자료형이 따로 없다. `int` 범위를 초과하는 값에는 `long` 또는 `BigInteger`를 사용한다.

> **[논리설계]** Java의 정수형은 음수를 **2의 보수**(two's complement)로 나타낸다. 논리설계에서 2진수의 부호 있는 표현으로 배우는 이 방식은 n비트로 $-2^{n-1}$부터 $2^{n-1}-1$까지를 나타내므로 `byte`(8비트)의 범위가 음수 쪽으로 하나 더 넓은 -128 ~ 127이 되고, 최댓값에 1을 더하면 최솟값으로 돌아가는 4강 3.2절의 오버플로도 이 표현에서 나온다.

### 2.2 기본형과 참조형

- **기본형(primitive type):** 위의 8가지이다. 수치 데이터처럼 변수의 메모리 공간에 값 자체를 저장한다.
- **참조형(reference type):** 클래스, 배열, 인터페이스 타입이 모두 여기에 속한다. 객체는 참조형 데이터이며, 변수에는 객체가 실제로 저장된 메모리 위치를 가리키는 참조(주소)가 들어간다.

```java
int number;
number = 237;
number = 35;                 // 237을 덮어쓴다

Customer clemens, twain;
clemens = new Customer();
twain = clemens;             // 같은 객체를 가리키는 참조가 둘이 된다
```

```text
기본형 변수                     참조형 변수

number                         clemens
┌──────┐                       ┌───────┐
│  35  │  (237을 덮어씀)       │   ●───┼──┐
└──────┘                       └───────┘  │    ┌──────────┐
                                          ├──> │ Customer │
                               twain      │    └──────────┘
                               ┌───────┐  │
                               │   ●───┼──┘
                               └───────┘
```

*그림 1. 기본형 변수와 같은 객체를 가리키는 두 참조형 변수의 메모리 상태*

기본형 변수에 새 값을 대입하면 이전 값이 덮어쓰인다. 참조형 변수끼리 대입하면 객체가 복사되지 않고, 두 변수가 하나의 객체를 함께 가리킨다(2강).

대입이 메모리에서 일어나는 순서를 단계로 나누면 다음과 같다. `int firstNumber, secondNumber;`를 실행하면 두 변수의 공간이 할당되고, `firstNumber = 234; secondNumber = 87;`을 실행하면 각 공간에 값이 저장된다. 같은 변수 `number`에 `number = 237;` 다음 `number = 35;`를 실행하면, 둘째 대입이 첫째 값 237을 지우고 그 자리에 35를 쓴다. 기본형 변수는 언제나 값 하나만 담으며 이전 값은 남지 않는다.

### 2.3 리터럴

**리터럴**(literal)은 프로그램에 직접 쓴 값이다.

- **정수 리터럴:** `15`는 10진수, `015`처럼 0으로 시작하면 8진수(값 13), `0x15`는 16진수(값 21), `0b0101`은 2진수(값 5)이다. 정수 리터럴은 `int` 형으로 컴파일되며, `long` 형 리터럴은 `24L`처럼 숫자 뒤에 `L`이나 `l`을 붙인다.
- **실수 리터럴:** `12.`, `12.0`, `.1234`, `0.1234`처럼 소수점 형태로 쓰거나 `1234E-4`($1234 \times 10^{-4}$)처럼 지수 형태로 쓴다. 실수 리터럴은 `double` 형으로 컴파일되며, `float`이면 `f`(`F`)를, `double`임을 명시하려면 `d`(`D`)를 붙인다.

```java
int n = 15;
int m = 015;      // 8진수, 13
int k = 0x15;     // 16진수, 21
int b = 0b0101;   // 2진수, 5
long g = 24L;

double d = 0.1234;
double e = 1234E-4;   // 1234E-4 = 1234 x 10^-4
float f = 0.1234f;
double w = .1234D;
```

JDK 7부터는 숫자 리터럴의 숫자 사이 어디에나 밑줄(`_`)을 넣어 읽기 쉽게 할 수 있다. 밑줄은 값에 영향을 주지 않는다.

```java
int price = 20_100;
long cardNumber = 1234_5678_1357_9998L;
long controlBits = 0b10110100_01011011_10110011_111110000L;
long maxLong = 0x7fff_ffff_ffff_ffffL;
int age = 2______5;
```

### 2.4 var 키워드

Java 10부터는 `var`로 지역 변수를 선언할 수 있다. 컴파일러가 초기화 식에서 타입을 추론하며, C++의 `auto`와 같은 기능이다. `var`는 지역 변수 선언에만 쓸 수 있으므로 필드에는 쓸 수 없고, 초기화 식 없이 선언할 수도 없다.

```java
var price = 200;               // int
var name = "kitae";            // String
var pi = 3.14;                 // double
var point = new Point();       // java.awt.Point
var v = new Vector<Integer>(); // java.util.Vector<Integer>
```

### 2.5 정수의 2진 표현과 오버플로

Java의 정수형은 음수를 **2의 보수**(two's complement)로 나타낸다. n비트 2의 보수에서 가장 왼쪽 비트는 부호 비트이며, 양수 x의 음수 -x는 x의 모든 비트를 뒤집고 1을 더하여 얻는다. 8비트 `byte`로 계산하면 다음과 같다.

| 값 | 8비트 표현 | 설명 |
|:-----|:-----|:-----|
| 5 | `0000 0101` | 4 + 1 |
| -5 | `1111 1011` | 5의 비트를 뒤집은 `1111 1010`에 1을 더함 |
| 127 | `0111 1111` | `byte`의 최댓값 |
| -128 | `1000 0000` | `byte`의 최솟값 |
| -1 | `1111 1111` | 모든 비트가 1 |

*표 3. 2의 보수로 나타낸 byte 값*

127은 `0111 1111`이므로 1을 더하면 `1000 0000`이 되고, 2의 보수에서 이 비트 패턴은 -128이다. 이처럼 결과가 자료형의 범위를 넘어 최솟값 쪽으로 돌아가는 것을 **오버플로**(overflow)라 한다. Java 언어 명세는 정수 덧셈이 오버플로하면 수학적인 합을 충분히 큰 2의 보수 형식으로 나타낸 값의 아래쪽 비트를 결과로 정하며, 정수 연산자는 오버플로를 어떤 방식으로도 알리지 않는다 [1]. 즉 예외도 경고도 없이 틀린 값이 나온다.

```java
public class OverflowDemo {
    public static void main(String[] args) {
        byte b = 127;
        b++;
        System.out.println(b);
        int max = Integer.MAX_VALUE;
        System.out.println(max + 1);
        System.out.println(Integer.MIN_VALUE - 1);
        System.out.println(Math.abs(Integer.MIN_VALUE));
        long wrong = 24 * 60 * 60 * 1000 * 1000;
        long right = 24L * 60 * 60 * 1000 * 1000;
        System.out.println(wrong + " " + right);
        System.out.println(Integer.toBinaryString(-1) + " " + Integer.toBinaryString(5));
        try {
            Math.addExact(max, 1);
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }
}
```

```text
-128
-2147483648
2147483647
-2147483648
500654080 86400000000
11111111111111111111111111111111 101
integer overflow
```

- `Integer.MAX_VALUE + 1`은 `Integer.MIN_VALUE`가 되고, 반대로 `Integer.MIN_VALUE - 1`은 `Integer.MAX_VALUE`가 된다.
- `Math.abs(Integer.MIN_VALUE)`가 음수인 이유는 $`2^{31}`$이 `int`의 범위(최댓값 $`2^{31}-1`$) 밖이기 때문이다. 음수 쪽이 하나 더 넓은 2의 보수의 성질에서 나오는 경계 경우이다.
- 하루를 마이크로초로 나타낸 $`24 \times 60 \times 60 \times 1000 \times 1000 = 86{,}400{,}000{,}000`$은 `long` 범위 안이지만, `wrong`의 식은 피연산자가 모두 `int`라서 `int`로 계산하다가 오버플로한다. 대입할 때 `long`으로 바뀌는 것은 이미 틀린 결과이다. 86,400,000,000을 $`2^{32}`$로 나눈 나머지가 500,654,080이며, 이 값이 `int`의 범위 안이라 그대로 남았다. 첫 피연산자를 `24L`로 쓰면 식 전체가 `long`으로 계산된다.
- `Math.addExact`, `Math.multiplyExact` 같은 메서드는 오버플로가 생기면 `ArithmeticException`을 던지므로, 오버플로를 반드시 잡아야 하는 계산에 쓴다.

> **주의:** 4강 3.2절의 무한 루프 예처럼 값이 계속 커지는 반복문에서는 오버플로가 조용히 일어나 음수가 되므로, 조건 `while (sum <= limit)`가 영원히 참일 수도 있다. 큰 값을 다루면 `long`이나 `java.math.BigInteger`를 쓴다.

### 2.6 부동소수점 수의 표현과 오차

`float`와 `double`은 IEEE 754 형식의 **부동소수점 수**(floating-point number)이다. `double` 64비트는 부호 1비트, 지수 11비트, 가수 52비트로 나뉘며, 0이 아닌 보통의 값(정규화된 수)은 $`(-1)^{s} \times 1.f \times 2^{e - 1023}`$의 2진 형태로 저장된다. 그래서 2진수로 유한하게 나타낼 수 없는 수는 가장 가까운 값으로 반올림되어 저장된다. 10진수 0.1은 2진수로 $`0.000110011001100\ldots_2`$처럼 끝없이 이어지므로 정확히 저장되지 않는다.

```java
import java.math.BigDecimal;

public class FloatDemo {
    public static void main(String[] args) {
        System.out.println(0.1 + 0.2);
        System.out.println(0.1 + 0.2 == 0.3);
        System.out.println(Math.abs((0.1 + 0.2) - 0.3) < 1e-9);
        double sum = 0.0;
        for (int i = 0; i < 10; i++) {
            sum += 0.1;
        }
        System.out.println(sum);
        float f = 16777216f;
        System.out.println(f + 1 == f);
        System.out.println(1.0 / 0 + " " + -1.0 / 0 + " " + 0.0 / 0);
        double nan = 0.0 / 0;
        System.out.println((nan == nan) + " " + Double.isNaN(nan));
        System.out.println(0.0 == -0.0);
        System.out.println(new BigDecimal("0.1").add(new BigDecimal("0.2")));
        System.out.println(new BigDecimal(0.1));
    }
}
```

```text
0.30000000000000004
false
true
0.9999999999999999
true
Infinity -Infinity NaN
false true
true
0.3
0.1000000000000000055511151231257827021181583404541015625
```

- 0.1과 0.2는 각각 반올림된 값으로 저장되므로 그 합은 0.3을 반올림한 값과 다르고, `==`는 `false`이다. 실수끼리 같은지 볼 때는 차의 절댓값이 충분히 작은 허용 오차보다 작은지를 검사한다.
- 0.1을 열 번 더한 값이 1.0이 아니라 0.9999999999999999인 것도 반올림 오차가 쌓인 결과이다. 그래서 실수를 반복문의 카운터로 쓰면 반복 횟수가 기대와 달라질 수 있다.
- `float`의 가수는 23비트이므로 $`2^{24} = 16{,}777{,}216`$보다 큰 정수 가운데에는 정확히 나타낼 수 없는 것이 생긴다. 16,777,217이 그 첫 번째 수로, 16,777,216으로 반올림되어 `f + 1 == f`가 `true`이다. `float`는 유효 숫자가 약 7자리, `double`은 약 15\~16자리이다.
- 실수를 0으로 나누면 예외가 나지 않고 `Infinity`나 `-Infinity`가 되며, 0.0 / 0은 수가 아님을 뜻하는 `NaN`이 된다. `NaN`은 자기 자신과도 같지 않으므로(`nan == nan`이 `false`) `Double.isNaN`으로 검사해야 한다 [1]. 0.0과 -0.0은 `==`로 비교하면 같다.
- `BigDecimal`은 10진수를 정확히 다루는 클래스로, 금액처럼 오차가 허용되지 않는 계산에 쓴다. 문자열 `"0.1"`로 만들어야 정확한 0.1이 되며, `new BigDecimal(0.1)`은 이미 반올림된 `double` 값을 그대로 옮겨 마지막 줄처럼 긴 값이 된다.

> **핵심:** 부동소수점 수의 오차는 Java의 결함이 아니라 2진 유한 자릿수 표현의 성질이다. 실수는 `==`로 비교하지 않고, 금액은 정수(원 단위의 `long`)나 `BigDecimal`로 다룬다.

### 2.7 래퍼 클래스와 오토박싱

기본형은 객체가 아니므로 메서드를 가질 수 없고, 객체만 담는 컬렉션(9강)에 넣을 수도 없다. 그래서 Java는 기본형마다 그 값을 객체로 감싸는 **래퍼 클래스**(wrapper class)를 둔다. `byte`, `short`, `int`, `long`, `float`, `double`, `char`, `boolean`의 래퍼 클래스는 차례로 `Byte`, `Short`, `Integer`, `Long`, `Float`, `Double`, `Character`, `Boolean`이다. 6.4절의 `Integer.parseInt`도 래퍼 클래스의 클래스 메서드이다.

기본형 값이 필요한 자리에 래퍼 객체를 쓰거나 그 반대로 쓰면 컴파일러가 자동으로 변환한다. 기본형을 래퍼 객체로 바꾸는 것을 **오토박싱**(autoboxing), 반대를 **언박싱**(unboxing)이라 한다.

```java
public class WrapperDemo {
    public static void main(String[] args) {
        Integer a = 127, b = 127;
        Integer c = 128, d = 128;
        System.out.println(a == b);
        System.out.println(c == d);
        System.out.println(c.equals(d));
        int sum = c + d;
        System.out.println(sum);
        System.out.println(Integer.MAX_VALUE + " " + Long.MIN_VALUE + " " + Byte.MAX_VALUE);
        System.out.println(Integer.toString(255, 2) + " " + Integer.toHexString(255) + " " + Integer.parseInt("ff", 16));
        System.out.println(Character.isDigit('7') + " " + Character.toUpperCase('q'));
        Integer missing = null;
        int value = missing;
    }
}
```

```text
true
false
true
256
2147483647 -9223372036854775808 127
11111111 ff 255
true Q
Exception in thread "main" java.lang.NullPointerException: Cannot invoke "java.lang.Integer.intValue()" because "missing" is null
	at WrapperDemo.main(WrapperDemo.java:14)
```

- `Integer a = 127;`은 오토박싱이고, `int sum = c + d;`는 두 객체를 언박싱하여 더한다.
- `a == b`가 `true`이고 `c == d`가 `false`인 것은 `==`가 래퍼 객체에서는 동일성을 비교하기 때문이다(2강 2.7절). 언어 명세는 -128\~127 범위의 `int` 상수 식을 박싱한 결과가 언제나 같은 객체이도록 정하므로 127은 같은 객체가 되지만, 128은 그 범위 밖이라 서로 다른 객체가 될 수 있다 [1]. 래퍼 객체의 값은 `equals`로 비교한다.
- `MAX_VALUE`, `MIN_VALUE` 상수는 각 자료형의 범위이다. `Integer.toString(255, 2)`는 2진수 문자열을, `Integer.parseInt("ff", 16)`은 16진수 문자열을 해석한 값을 돌려준다.
- `null`인 래퍼 객체를 언박싱하면 내부적으로 `intValue()`를 호출하므로 `NullPointerException`이 난다. 예외 메시지의 변수 이름 `"missing"`은 `-g`로 컴파일했을 때 나타나며, `-g` 없이 컴파일하면 지역 변수의 번호가 나온다(2강 2.6절).

---

<br>

## 3. 변수와 상수

두 수 x와 y를 더하는 식은 `x + y`로 쓰지만, 실제로 더하기 전에 x와 y의 자료형을 선언해야 한다. 선언하면 x와 y의 값을 저장할 메모리 공간이 할당되며, 이처럼 값을 저장하는 이름을 **변수**(variable)라 한다. 변수는 세 가지 속성을 가진다.
- 값을 저장하는 메모리 위치
- 그 위치에 저장되는 데이터의 자료형
- 그 위치를 가리키는 이름

```java
// 변수 선언과 초기화
int age = 25;
double gpa = 3.95;
String name = "Park";

// 상수는 'final' 키워드를 사용
static final double PI = 3.141592;
static final int UNIT_CHANGER = 60;
```

변수는 여러 개를 한 번에 선언할 수 있고(`int i, j, k;`), 선언하면서 초기화할 수도 있다(`int count = 10, height = 34;`). 변수에 값을 넣는 대입문의 형식은 `<변수> = <식>;`이다(예: `sum = firstNumber + secondNumber;`, `avg = (one + two + three) / 3.0;`).

값이 바뀌지 않아야 하면 **상수**(constant)를 쓴다. 예약어 `final`로 선언한 `final double PI = 3.14159;`, `final int MONTH_IN_YEAR = 12;`, `final short FARADAY_CONSTANT = 23060;`에서 `PI` 같은 이름을 이름 있는 상수(named constant), `3.14159` 같은 값을 리터럴 상수(literal constant)라고 한다. 메서드 안의 지역 상수는 `final`만 붙이고, 위 코드처럼 클래스 수준에서 모든 메서드가 함께 쓰는 상수는 `static final`로 선언한다.

---

<br>

## 4. 연산자

### 4.1 산술 연산자

| 연산자 | 연산 | 예시 |
|:-------|:-----|:-----|
| `+` | 덧셈 | `5 + 3` = 8 |
| `-` | 뺄셈 | `5 - 3` = 2 |
| `*` | 곱셈 | `5 * 3` = 15 |
| `/` | 나눗셈 | `7 / 2` = 3 (정수 나눗셈) |
| `%` | 나머지 | `7 % 2` = 1 |

*표 4. Java의 산술 연산자*

x = 10, y = 7, z = 2.5이면 `x + y`는 17, `x - y`는 3, `x * y`는 70, `x / y`는 1, `x / z`는 4.0, `x % y`는 3이다. `x / y`는 두 피연산자가 정수이므로 소수 부분을 버리는 정수 나눗셈이고, `x / z`는 피연산자 하나가 실수이므로 실수 나눗셈이다.

### 4.2 연산자 우선순위

`x + 3 * y`는 `3 * y`를 먼저 계산한 뒤 x에 더한다. 식의 계산 순서는 우선순위 규칙이 정한다. 우선순위가 높은 연산자를 먼저 계산하고, 우선순위가 같으면 대부분의 연산자는 왼쪽에서 오른쪽으로 계산한다.

| 순서 | 그룹 | 연산자 | 규칙 |
|:-----|:-----|:-----|:-----|
| 높음 | 부분식 | `( )` | 괄호 안의 부분식을 가장 먼저 계산한다. 괄호가 중첩되면 가장 안쪽부터, 같은 수준에 여럿이면 왼쪽부터 계산한다. |
| | 단항 연산자 | `-`, `+` | 단항 마이너스와 플러스를 두 번째로 계산한다. |
| | 곱셈 연산자 | `*`, `/`, `%` | 세 번째로 계산하며, 여럿이면 왼쪽부터 계산한다. |
| 낮음 | 덧셈 연산자 | `+`, `-` | 마지막으로 계산하며, 여럿이면 왼쪽부터 계산한다. |

*표 5. 산술 연산자의 우선순위*

### 4.3 대입 연산자

```java
a += 3;   // a = a + 3
b *= 3;   // b = b * 3
c %= 2;   // c = c % 2
```

`int a = 3, b = 3, c = 3;`에서 시작하면 위 세 문장 뒤에 `a`는 6, `b`는 9, `c`는 1이 된다.

> **참고:** 복합 대입 연산자는 결과를 왼쪽 변수의 자료형으로 자동 변환한다. 정확히는 `int` 변수 `a`에서 `a += x`가 `a = (int) (a + x)`와 같으므로, `int a = 5; a += 2.7;`은 컴파일되고 `a`는 7이 된다. 반면 `a = a + 2.7;`은 `double`을 `int`에 대입하므로 컴파일 오류이다(5절).

### 4.4 증감 연산자

```java
int d = 3;
int a = d++;  // a = 3, d = 4 (후위 증가: 사용 후 증가)
a = ++d;      // d = 5, a = 5 (전위 증가: 증가 후 사용)
```

감소 연산자 `--`도 같은 방식으로 동작한다. 위 코드에 이어 다음을 실행하면 각 줄의 결과는 주석과 같다(실습 코드: [OperatorDemo.java](../../Coursework/Lab03_Java-Basic-Grammar/OperatorDemo.java)).

```java
a = d--;      // a = 5, d = 4 (후위 감소: 사용 후 감소)
a = --d;      // d = 3, a = 3 (전위 감소: 감소 후 사용)
```

### 4.5 관계 및 논리 연산자

| 분류 | 연산자 |
|:-----|:-------|
| 관계 | `==`, `!=`, `<`, `>`, `<=`, `>=` |
| 논리 | `&&` (AND), `\|\|` (OR), `!` (NOT) |

*표 6. 관계 연산자와 논리 연산자*

### 4.6 + 연산자의 두 가지 의미

`+`는 문맥에 따라 두 가지 연산을 뜻한다. `<값1> + <값2>`에서 두 값이 모두 숫자이면 덧셈이고, 하나라도 `String`이면 문자열 연결이다. 계산은 왼쪽에서 오른쪽으로 진행한다.

```java
String output1 = "test" + 1 + 2;   // "test12"
String output2 = 1 + 2 + "test";   // "3test"
```

`output1`은 `"test" + 1`이 먼저 `"test1"`이 되고 다시 2가 연결된다. `output2`는 `1 + 2`가 먼저 덧셈으로 3이 된 뒤 `"test"`와 연결된다.

### 4.7 정수 나눗셈과 나머지의 부호

4.1절에서 본 것처럼 두 피연산자가 정수이면 `/`는 정수 나눗셈이다. Java의 정수 나눗셈은 몫을 0 쪽으로 버린다(round toward zero) [1]. 그래서 7 / 2는 3, -7 / 2는 -3.5를 0 쪽으로 버린 -3이다. 나머지 연산 `%`의 결과는 항상 다음 관계를 만족하도록 정해지며, 그 결과 나머지의 부호는 나누어지는 수(왼쪽 피연산자)의 부호를 따른다 [1].

```math
(a / b) \times b + (a \,\%\, b) = a
```

```java
public class DivDemo {
    public static void main(String[] args) {
        System.out.println(7 / 2 + " " + (-7) / 2 + " " + 7 / (-2) + " " + (-7) / (-2));
        System.out.println(7 % 2 + " " + (-7) % 2 + " " + 7 % (-2) + " " + (-7) % (-2));
        System.out.println(Math.floorDiv(-7, 2) + " " + Math.floorMod(-7, 2));
        System.out.println(7.5 % 2 + " " + 7.0 / 2);
        int n = -3;
        System.out.println((n % 2 == 1) + " " + (n % 2 != 0));
        System.out.println(Integer.MIN_VALUE / -1);
    }
}
```

```text
3 -3 -3 3
1 -1 1 -1
-4 1
1.5 3.5
false true
-2147483648
```

| a | b | a / b | a % b | 검산 (a / b) × b + a % b |
|-----:|-----:|-----:|-----:|:-----|
| 7 | 2 | 3 | 1 | 3 × 2 + 1 = 7 |
| -7 | 2 | -3 | -1 | (-3) × 2 + (-1) = -7 |
| 7 | -2 | -3 | 1 | (-3) × (-2) + 1 = 7 |
| -7 | -2 | 3 | -1 | 3 × (-2) + (-1) = -7 |

*표 7. 부호 조합에 따른 정수 나눗셈과 나머지*

- `Math.floorDiv`와 `Math.floorMod`는 몫을 음의 무한대 쪽으로 내림한다. -7을 2로 나누면 -3.5를 내린 -4가 몫이고, 나머지는 -7 - (-4 × 2) = 1로 나누는 수의 부호를 따른다. 시계의 시각처럼 음수에도 0 이상의 나머지가 필요할 때 쓴다.
- `%`는 실수에도 쓸 수 있으며, 7.5 % 2는 7.5 - 3 × 2 = 1.5이다.
- 홀수인지 검사할 때 `n % 2 == 1`로 쓰면 음의 홀수에서 나머지가 -1이므로 틀린 결과가 나온다. `n % 2 != 0`으로 쓴다.
- 정수를 0으로 나누면 `/`와 `%` 모두 `ArithmeticException`을 던진다(6강 3.6절). 예외적으로 `Integer.MIN_VALUE / -1`은 결과 $`2^{31}`$이 범위를 넘으므로 오버플로하여 나누어지는 수 그대로가 되며, 예외는 나지 않는다 [1].

### 4.8 단축 평가와 비트 연산자

4.5절의 `&&`와 `||`는 **단축 평가**(short-circuit evaluation)를 한다. 왼쪽 피연산자만으로 결과가 정해지면 오른쪽 피연산자를 계산하지 않는다. 오른쪽 피연산자에 메서드 호출 같은 부작용이 있으면 이 차이가 출력에 드러난다.

```java
public class LogicDemo {
    static int calls = 0;

    static boolean check(boolean v) {
        calls++;
        return v;
    }

    public static void main(String[] args) {
        boolean r1 = check(false) && check(true);
        System.out.println(r1 + " calls=" + calls);
        calls = 0;
        boolean r2 = check(false) & check(true);
        System.out.println(r2 + " calls=" + calls);
        String s = null;
        System.out.println(s != null && s.length() > 0);
        System.out.println((6 & 3) + " " + (6 | 3) + " " + (6 ^ 3) + " " + (~6));
        System.out.println((1 << 4) + " " + (-16 >> 2) + " " + (-16 >>> 28));
        int x, y;
        x = y = 5;
        System.out.println(x + y);
        int i = 2;
        i = i++ + ++i;
        System.out.println(i);
        System.out.println(1 + 2 * 3 > 6 && !(4 < 2));
    }
}
```

```text
false calls=1
false calls=2
false
2 7 5 -7
16 -4 15
10
6
true
```

- `check(false) && check(true)`는 왼쪽이 `false`이므로 오른쪽 `check`를 부르지 않아 호출 횟수가 1이다. 피연산자가 `boolean`일 때 `&`와 `|`는 논리 연산을 하지만 단축 평가를 하지 않으므로 두 쪽을 모두 계산하여 호출 횟수가 2이다.
- `s != null && s.length() > 0`은 단축 평가를 이용한 관용구이다. `s`가 `null`이면 오른쪽을 계산하지 않으므로 `NullPointerException`이 나지 않는다. 두 조건의 순서를 바꾸면 예외가 난다.

피연산자가 정수이면 `&`, `|`, `^`, `~`는 비트마다 연산하는 **비트 연산자**이고, `<<`, `>>`, `>>>`는 비트를 옮기는 **시프트 연산자**이다.

| 연산 | 계산 (아래 4비트만 표시) | 결과 |
|:-----|:-----|-----:|
| `6 & 3` | `0110 & 0011 = 0010` (둘 다 1인 비트) | 2 |
| `6 \| 3` | `0110 \| 0011 = 0111` (하나라도 1인 비트) | 7 |
| `6 ^ 3` | `0110 ^ 0011 = 0101` (서로 다른 비트) | 5 |
| `~6` | 모든 비트를 뒤집음, 2의 보수로 -(6 + 1) | -7 |
| `1 << 4` | 왼쪽으로 4비트 옮김, $`1 \times 2^4`$ | 16 |
| `-16 >> 2` | 부호 비트를 채우며 오른쪽으로 2비트 옮김, -16 / 4 | -4 |
| `-16 >>> 28` | 0을 채우며 오른쪽으로 28비트 옮김, `0xFFFFFFF0`의 위 4비트 | 15 |

*표 8. 비트 연산자와 시프트 연산자의 계산*

`>>`는 빈자리를 부호 비트로 채우므로 음수가 음수로 남고, `>>>`는 0으로 채우므로 음수도 큰 양수가 된다. 비트 연산은 여러 참과 거짓을 정수 하나에 담는 플래그나 2의 거듭제곱 계산에 쓴다.

### 4.9 전체 우선순위와 결합 방향

4.2절의 표는 산술 연산자만 다루었다. Java의 연산자 전체를 우선순위가 높은 것부터 나열하면 다음과 같다. 같은 줄의 연산자는 우선순위가 같으며, 결합 방향은 우선순위가 같은 연산자가 이어질 때 어느 쪽부터 묶는지를 뜻한다.

| 순위 | 연산자 | 결합 방향 |
|-----:|:-----|:-----|
| 1 | 후위 `x++`, `x--` | 왼쪽에서 오른쪽 |
| 2 | 전위 `++x`, `--x`, 단항 `+`, `-`, `!`, `~`, 캐스트 `(자료형)` | 오른쪽에서 왼쪽 |
| 3 | `*`, `/`, `%` | 왼쪽에서 오른쪽 |
| 4 | `+`, `-` | 왼쪽에서 오른쪽 |
| 5 | `<<`, `>>`, `>>>` | 왼쪽에서 오른쪽 |
| 6 | `<`, `>`, `<=`, `>=`, `instanceof` | 왼쪽에서 오른쪽 |
| 7 | `==`, `!=` | 왼쪽에서 오른쪽 |
| 8 | `&` | 왼쪽에서 오른쪽 |
| 9 | `^` | 왼쪽에서 오른쪽 |
| 10 | `\|` | 왼쪽에서 오른쪽 |
| 11 | `&&` | 왼쪽에서 오른쪽 |
| 12 | `\|\|` | 왼쪽에서 오른쪽 |
| 13 | `? :` | 오른쪽에서 왼쪽 |
| 14 | `=`, `+=`, `-=`, `*=`, `/=`, `%=` 등 | 오른쪽에서 왼쪽 |

*표 9. Java 연산자의 우선순위와 결합 방향*

4.8절의 출력으로 몇 가지를 확인한다.

- `x = y = 5`는 대입이 오른쪽에서 왼쪽으로 결합하므로 `x = (y = 5)`이다. `y = 5`라는 대입식의 값 5가 다시 `x`에 대입되어 `x + y`는 10이다.
- `1 + 2 * 3 > 6 && !(4 < 2)`는 곱셈, 덧셈, 비교, `&&`의 순서로 묶여 `((1 + (2 * 3)) > 6) && (!(4 < 2))`가 되고, `7 > 6`과 `!false`가 모두 참이므로 `true`이다.
- `i = i++ + ++i`에서 i가 2일 때, 이항 연산자의 왼쪽 피연산자는 오른쪽보다 먼저 완전히 계산된다 [1]. 왼쪽의 `i++`는 값 2를 내고 i를 3으로 만들고, 오른쪽의 `++i`는 i를 4로 만든 뒤 4를 낸다. 합 6이 i에 대입된다. 결과는 정해져 있지만 이런 식은 읽기 어려우므로 실제 코드에서는 증감을 별도의 문장으로 쓴다.

> **주의:** 우선순위는 연산자가 어떻게 묶이는지를 정할 뿐, 피연산자를 계산하는 순서를 정하지 않는다. 피연산자의 계산 순서는 왼쪽에서 오른쪽이다. 우선순위가 헷갈리는 식은 괄호를 써서 의도를 드러낸다.

---

<br>

## 5. 형변환

```java
// 묵시적(확대) 형변환: 작은 타입 -> 큰 타입 (자동)
int i = 10;
double d = i;  // 10.0

// 명시적(축소) 형변환: 큰 타입 -> 작은 타입 (캐스트 필요)
double pi = 3.14;
int truncated = (int) pi;  // 3
```

- **혼합식과 승격 규칙:** x가 `float`이고 y가 `int`이면 `x * y`의 결과는 `float`이다. 이처럼 자료형이 다른 피연산자가 섞인 식을 혼합식(mixed expression)이라 한다. 혼합식의 피연산자는 승격 규칙(promotion rule)에 따라 변환되며, 식의 자료형은 피연산자 중 정밀도가 가장 높은 자료형과 같아진다.
- **명시적 형변환:** 승격 규칙에 맡기지 않고 `(<자료형>) <식>` 형식으로 직접 변환할 수 있다. `(float) x / 3`은 x를 `float`으로 바꾼 뒤 3으로 나누고, `(int) (x / y * 3.0)`은 식 `x / y * 3.0`의 결과를 `int`로 바꾼다. 실수를 정수로 바꾸면 소수 부분은 버려진다.
- **묵시적 형변환:** `double x = 3 + 5;`에서 `3 + 5`의 결과는 `int` 값 8이지만, 변수 x가 `double`이므로 대입하기 전에 8.0으로 승격된다. 승격은 자동으로 일어나지만 강등은 자동으로 일어나지 않는다. `int x = 3.5;`처럼 정밀도가 높은 값을 낮은 자료형의 변수에 대입하면 컴파일 오류이며, 의도한 변환이면 명시적 캐스트를 쓴다.

### 5.1 확대 변환과 축소 변환의 규칙

자료형 사이의 변환 가운데 값의 범위가 넓어지는 방향을 **확대 변환**(widening conversion)이라 하며, 대입이나 메서드 호출에서 자동으로 일어난다. 확대 변환의 방향은 다음과 같다.

```text
byte ──▶ short ──▶ int ──▶ long ──▶ float ──▶ double
                    ▲
          char ─────┘
```

*그림 2. 기본형 사이의 확대 변환 방향*

확대 변환은 값의 크기를 잃지 않지만, `int`나 `long`을 `float`로, `long`을 `double`로 바꿀 때는 가수의 비트 수가 모자라 아래 자릿수의 정밀도를 잃을 수 있다(2.6절).

```java
public class CastDemo {
    public static void main(String[] args) {
        long big = 123456789L;
        float f = big;
        System.out.println(f + " " + (long) f);
        int i = 1234567891;
        float g = i;
        System.out.println((int) g);
        System.out.println((byte) 200 + " " + (byte) 300 + " " + (short) 70000);
        char ch = 'A';
        System.out.println(ch + 1);
        System.out.println((char) (ch + 1));
        byte b = 10;
        b += 5;
        System.out.println(b);
        System.out.println((int) 3.99 + " " + (int) -3.99 + " " + Math.round(2.5) + " " + Math.round(-2.5) + " " + Math.round(2.4));
        System.out.println((int) Double.NaN + " " + (int) 1e20 + " " + (long) -1e30);
        System.out.println(Math.floor(-3.5) + " " + Math.ceil(-3.5) + " " + Math.rint(2.5));
        double avg = 7 / 2;
        double avg2 = 7 / 2.0;
        System.out.println(avg + " " + avg2);
    }
}
```

```text
1.2345679E8 123456792
1234567936
-56 44 4464
66
B
15
3 -3 3 -2 2
0 2147483647 -9223372036854775808
-4.0 -3.0 2.0
3.0 3.5
```

123456789를 `float`로 바꾸면 가장 가까운 `float` 값 123456792가 되고, 1234567891은 1234567936이 된다. 캐스트 없이 대입했는데도 값이 바뀌었다는 점에 주의해야 한다.

범위가 좁아지는 **축소 변환**(narrowing conversion)은 명시적 캐스트가 있어야 하며, 정수 사이의 축소 변환은 아래쪽 n비트만 남기고 나머지를 버린다 [1].

- `(byte) 200`: 200은 2진수로 `1100 1000`이다. 8비트를 그대로 남기면 부호 비트가 1이므로 2의 보수로 200 - 256 = -56이다.
- `(byte) 300`: 300은 `1 0010 1100`이다. 아래 8비트 `0010 1100`만 남아 44이다(300 - 256).
- `(short) 70000`: 70000 - 65536 = 4464이다.

### 5.2 산술 승격과 복합 대입

이항 연산을 할 때 피연산자는 먼저 공통의 자료형으로 바뀐다. 이를 **이항 숫자 승격**(binary numeric promotion)이라 한다. 규칙은 다음과 같다.

1. 한쪽이 `double`이면 다른 쪽도 `double`로 바꾼다.
2. 그렇지 않고 한쪽이 `float`이면 다른 쪽도 `float`로 바꾼다.
3. 그렇지 않고 한쪽이 `long`이면 다른 쪽도 `long`으로 바꾼다.
4. 그 밖에는 두 쪽 모두 `int`로 바꾼다. 즉 `byte`, `short`, `char`끼리 계산해도 결과는 `int`이다.

규칙 4 때문에 `byte` 변수에 `byte` 값을 더한 결과를 그대로 대입할 수 없다.

```text
CompoundErr.java:4: error: incompatible types: possible lossy conversion from int to byte
        b = b + 5;
              ^
1 error
```

`b + 5`의 결과가 `int`이므로 `byte`에 대입하려면 축소 변환이 필요하기 때문이다. 반면 5.1절의 프로그램에서 `b += 5;`는 4.3절의 참고 상자처럼 복합 대입이 `b = (byte) (b + 5)`와 같이 결과를 왼쪽 변수의 자료형으로 바꾸므로 컴파일되고 15가 된다. `char`도 계산에서는 `int`로 승격되므로 `'A' + 1`은 문자 `'B'`가 아니라 `'A'`의 코드 65에 1을 더한 66이고, 문자를 얻으려면 `(char) ('A' + 1)`처럼 다시 캐스트한다.

승격은 연산 하나하나에서 일어난다는 점도 중요하다. `double avg = 7 / 2;`는 `7 / 2`를 먼저 정수 나눗셈으로 3을 계산한 뒤 대입하면서 3.0으로 바꾸므로 3.5가 되지 않는다. 피연산자 하나를 `7 / 2.0`이나 `(double) 7 / 2`처럼 실수로 만들어야 실수 나눗셈이 된다.

### 5.3 실수를 정수로 바꾸는 방법

실수를 정수로 바꾸는 방법은 목적에 따라 다르다.

| 방법 | 규칙 | 3.5 | -3.5 | 2.5 |
|:-----|:-----|-----:|-----:|-----:|
| `(int) x` | 0 쪽으로 버림 | 3 | -3 | 2 |
| `Math.floor(x)` | 음의 무한대 쪽으로 내림 | 3.0 | -4.0 | 2.0 |
| `Math.ceil(x)` | 양의 무한대 쪽으로 올림 | 4.0 | -3.0 | 3.0 |
| `Math.round(x)` | 가장 가까운 정수, 절반은 양의 무한대 쪽 | 4 | -3 | 3 |
| `Math.rint(x)` | 가장 가까운 정수, 절반은 짝수 쪽 | 4.0 | -4.0 | 2.0 |

*표 10. 실수를 정수로 바꾸는 방법의 비교*

5.1절의 출력에서 `Math.round(2.5)`는 3, `Math.round(-2.5)`는 -2, `Math.rint(2.5)`는 2.0이다. `Math.floor`, `Math.ceil`, `Math.rint`는 결과를 `double`로 돌려주므로 정수 변수에 넣으려면 다시 캐스트해야 한다.

캐스트 `(int)`의 특별한 경우도 명세가 정한다. `NaN`은 0이 되고, 범위를 넘는 큰 값은 그 자료형의 최댓값이나 최솟값이 된다 [1]. 그래서 `(int) Double.NaN`은 0, `(int) 1e20`은 `Integer.MAX_VALUE`, `(long) -1e30`은 `Long.MIN_VALUE`이다. 정수 사이의 축소 변환이 아래 비트를 남기는 것과 달리, 실수에서 정수로의 변환은 최댓값과 최솟값에서 멈춘다.

---

<br>

## 6. 콘솔 및 대화 상자 입출력

### 6.1 표준 출력

`showMessageDialog`는 한 줄짜리 짧은 메시지를 보여 주기 위한 것이며 범용 출력 수단은 아니다. `System.out`을 쓰면 표준 출력 창에 여러 줄의 텍스트를 출력할 수 있다. 표준 출력 창의 모습은 사용하는 도구(명령 프롬프트, Eclipse의 Console 등)에 따라 다르다. `println`은 출력한 뒤 줄을 바꾸고, `print`는 줄을 바꾸지 않는다.

```java
int x = 123, y = x + x;
System.out.println("Hello, Dr. Caffeine.");
System.out.print(" x = ");
System.out.println(x);
System.out.print(" x + x = ");
System.out.println(y);
System.out.println(" THE END");
```

```text
Hello, Dr. Caffeine.
 x = 123
 x + x = 246
 THE END
```

### 6.2 DecimalFormat으로 숫자 서식 지정

숫자 출력의 형식은 `java.text.DecimalFormat` 객체로 지정한다. 형식 문자열 `"0.000"`은 소수점 아래 세 자리까지 반올림하여 표시한다. 다만 기본 반올림 방식은 `RoundingMode.HALF_EVEN`이어서, 버리는 부분이 정확히 절반이면 남는 끝자리가 짝수가 되는 쪽으로 맞춘다(예: 0.0625는 0.062, 0.1875는 0.188).

```java
double num = 123.45789345;
DecimalFormat df = new DecimalFormat("0.000"); // 소수점 아래 세 자리
System.out.print(num);             // 123.45789345
System.out.print(df.format(num));  // 123.458
```

### 6.3 콘솔 입력을 위한 Scanner

`System.in`으로 데이터를 입력받는 기법을 표준 입력이라 한다. `System.in`을 직접 쓰면 한 번에 한 바이트만 입력받을 수 있으므로, 기본 자료형 값을 입력받을 때는 Java 5.0부터 제공되는 `Scanner` 클래스를 쓴다.

```java
import java.util.Scanner;

Scanner scanner = new Scanner(System.in);
int time = scanner.nextInt();
String line = scanner.nextLine();
scanner.close();
```

| 메서드 | 예 |
|:-----|:-----|
| `nextByte()` | `byte b = scanner.nextByte();` |
| `nextDouble()` | `double d = scanner.nextDouble();` |
| `nextFloat()` | `float f = scanner.nextFloat();` |
| `nextInt()` | `int i = scanner.nextInt();` |
| `nextLong()` | `long l = scanner.nextLong();` |
| `nextShort()` | `short s = scanner.nextShort();` |
| `next()` | `String str = scanner.next();` |
| `nextLine()` | `String line = scanner.nextLine();` |

*표 11. Scanner의 주요 메서드*

`next()`는 공백으로 구분된 단어 하나를 읽고, `nextLine()`은 줄 끝까지의 나머지를 모두 읽는다.

> **주의:** `nextInt()`는 숫자만 읽고 그 뒤의 줄 바꿈 문자는 남겨 둔다. 그래서 위 코드처럼 `nextInt()` 바로 뒤에 `nextLine()`을 호출하면, 입력이 `25`와 `hello world` 두 줄이어도 `line`에는 다음 줄이 아니라 `25` 뒤에 남은 빈 문자열이 들어간다. 다음 줄을 읽으려면 `nextLine()`을 한 번 더 호출하여 남은 줄 바꿈을 먼저 소비한다.

### 6.4 GUI 대화 상자를 위한 JOptionPane

```java
String input = JOptionPane.showInputDialog(null, "Enter radius:");
double radius = Double.parseDouble(input);
```

`showInputDialog`는 항상 `String`을 반환하므로, 다음처럼 그 결과를 `int` 변수에 바로 대입하면 자료형이 맞지 않아 컴파일 오류가 난다.

```java
int age;
age = JOptionPane.showInputDialog(null, "Enter your age");   // 오류: String을 int에 대입할 수 없다
```

`String`을 수치로 바꾸는 데에는 래퍼 클래스(wrapper class)의 변환 메서드를 쓴다.

```java
int age;
String inputStr;
inputStr = JOptionPane.showInputDialog(null, "Enter your age");
age = Integer.parseInt(inputStr);
```

| 클래스 | 메서드 | 예 |
|:-----|:-----|:-----|
| `Integer` | `parseInt` | `Integer.parseInt("25")` → 25, `Integer.parseInt("25.3")` → 오류 |
| `Long` | `parseLong` | `Long.parseLong("25")` → 25L, `Long.parseLong("25.3")` → 오류 |
| `Float` | `parseFloat` | `Float.parseFloat("25.3")` → 25.3F, `Float.parseFloat("ab3")` → 오류 |
| `Double` | `parseDouble` | `Double.parseDouble("25")` → 25.0, `Double.parseDouble("ab3")` → 오류 |

*표 12. 래퍼 클래스의 문자열 변환 메서드*

표의 오류는 실행 중에 `NumberFormatException`이 발생한다는 뜻이다.

다음은 반지름을 입력받아 원의 넓이와 둘레를 구하는 프로그램이다(실습 코드: [CircleCalculator.java](../../Coursework/Lab03_Java-Basic-Grammar/CircleCalculator.java)).

```java
import javax.swing.JOptionPane;

public class Chap3Program1 {

    static final double PI = 3.141592;

    public static void main(String[] args) {

        double radius, area, circumference;

        String radiusStr = JOptionPane.showInputDialog(null, "Enter radius: ");

        radius = Double.parseDouble(radiusStr);

        //Compute area and circumference
        area = PI * radius * radius;
        circumference = 2.0 * PI * radius;

        JOptionPane.showMessageDialog(null, "Given Radius: " + radius + "\n" + "Area: " + area + "\n"
        + "Circumference: " + circumference);
    }
}
```

---

<br>

## 7. 문자열 연산

주요 `String` 메서드는 다음과 같다.

| 메서드 | 설명 |
|:-------|:-----|
| `length()` | 문자 수를 반환 |
| `charAt(i)` | 인덱스 `i`의 문자를 반환 |
| `substring(begin, end)` | 부분 문자열을 추출 |
| `indexOf(str)` | 부분 문자열의 위치를 탐색 |
| `toUpperCase()` | 대문자로 변환 |
| `split(regex)` | 구분자로 배열을 분할 |
| `trim()` | 앞뒤 공백을 제거 |

*표 13. String의 주요 메서드*

---

<br>

## 8. 예제: 초를 시, 분, 초로 바꾸기

정수로 된 초를 입력받아 몇 시간, 몇 분, 몇 초인지 출력하는 프로그램이다. 예를 들어 5000초를 입력하면 1시간 23분 20초를 출력한다(실습 코드: [TimeUnitConverter.java](../../Coursework/Lab03_Java-Basic-Grammar/TimeUnitConverter.java)).

```java
import java.util.Scanner;

public class Chap3Program3 {

    static final int UNIT_CHANGER = 60;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please input the seconds unit as integer value:");

        int time = scanner.nextInt();
        int second = time % UNIT_CHANGER;
        int minute = (time / UNIT_CHANGER) % UNIT_CHANGER;
        int hour = (time / UNIT_CHANGER) / UNIT_CHANGER;

        System.out.print(time + " seconds is ");
        System.out.print(hour + " hr., ");
        System.out.print(minute + " min., and ");
        System.out.print(second + " sec.");
    }
}
```

`time % 60`은 분으로 채우고 남은 초이다. `time / 60`은 전체 분이며, 그것을 다시 60으로 나눈 나머지가 시간으로 채우고 남은 분, 몫이 시간이다. 5000초이면 5000 % 60 = 20, 5000 / 60 = 83, 83 % 60 = 23, 83 / 60 = 1이다. 모든 나눗셈이 정수 나눗셈이므로 소수 부분이 생기지 않는다.

---

<br>

## 9. 흔한 실수

| 실수 | 나타나는 결과 | 바로잡는 방법 |
|:-----|:-----|:-----|
| 지역 변수를 초기화하지 않고 읽는다 | `variable might not have been initialized` 컴파일 오류 | 선언할 때 초기값을 준다 |
| `int`끼리 곱한 큰 값을 `long`에 대입한다 | 계산이 `int`로 오버플로한 뒤 대입된다 | 첫 피연산자를 `24L`처럼 `long`으로 쓴다 |
| 정수 평균을 `sum / n`으로 구한다 | 소수 부분이 버려진다 | `(double) sum / n`처럼 한쪽을 실수로 바꾼다 |
| 실수를 `==`로 비교한다 | 0.1 + 0.2 == 0.3이 `false` | 차의 절댓값을 허용 오차와 비교한다 |
| 금액을 `double`로 계산한다 | 반올림 오차가 쌓인다 | 원 단위 `long`이나 `BigDecimal`을 쓴다 |
| `n % 2 == 1`로 홀수를 검사한다 | 음의 홀수에서 `false` | `n % 2 != 0`을 쓴다 |
| `Integer` 객체를 `==`로 비교한다 | 127까지는 같다가 128부터 `false`가 될 수 있다 | `equals`나 언박싱한 `int`로 비교한다 |
| `byte b; b = b + 1;`로 쓴다 | `possible lossy conversion from int to byte` | `b += 1;`이나 `(byte) (b + 1)`을 쓴다 |
| `'A' + 1`을 문자로 생각한다 | 66이 출력된다 | `(char) ('A' + 1)`로 캐스트한다 |

*표 14. 자료형과 연산자에 관한 흔한 실수*

---

<br>

## 개념 적용

**T/F 점검:**

| 문장 | 판단과 근거 |
|:-----|:-----|
| `Integer.MAX_VALUE + 1`을 계산하면 `ArithmeticException`이 던져진다. | **F.** 정수 오버플로는 알리지 않으며 결과는 `Integer.MIN_VALUE`이다. 검출하려면 `Math.addExact`를 쓴다. |
| `-7 / 2`는 -4이다. | **F.** Java의 정수 나눗셈은 0 쪽으로 버리므로 -3이다. -4는 `Math.floorDiv(-7, 2)`의 결과이다. |
| `-7 % 3`은 -1이다. | **T.** 나머지의 부호는 나누어지는 수를 따르며, (-2) × 3 + (-1) = -7이다. |
| `long`을 `float`에 대입하는 것은 확대 변환이므로 값이 바뀌지 않는다. | **F.** 자동으로 일어나지만 `float`의 가수가 23비트(숨은 비트를 더해 24비트)라 큰 값은 정밀도를 잃는다. 123456789는 123456792가 된다. |
| `(byte) 130`은 -126이다. | **T.** 130은 `1000 0010`이며, 부호 비트가 1이므로 130 - 256 = -126이다. |
| `s.length() > 0 && s != null`은 `s`가 `null`일 때 `false`를 돌려준다. | **F.** 왼쪽의 `s.length()`를 먼저 계산하므로 `NullPointerException`이 난다. 순서를 바꾸어야 단축 평가로 보호된다. |
| 실수를 0으로 나누면 `ArithmeticException`이 던져진다. | **F.** 실수 나눗셈은 예외를 던지지 않고 `Infinity`, `-Infinity`, `NaN` 중 하나를 결과로 낸다. 예외는 정수 나눗셈에서만 난다. |
| `x = y = z = 0;`은 컴파일되며 세 변수가 모두 0이 된다. | **T.** 대입은 오른쪽에서 왼쪽으로 결합하고 대입식은 대입한 값을 가지므로, `z`, `y`, `x`의 순서로 0이 대입된다. |

---

<br>

## 요약

| 개념 | 핵심 요약 |
|:-----|:-----------|
| 식별자 | 문자, `_`, `$`로 시작하고 키워드와 `true`, `false`, `null`을 쓸 수 없으며 대소문자를 구별한다. |
| 기본 자료형 | 기본 자료형은 byte, short, int, long, float, double, char, boolean의 8가지이다. |
| 참조형 | 클래스, 배열, 인터페이스 타입의 변수는 객체를 가리키는 참조를 저장한다. |
| 리터럴 | 정수 리터럴은 `int`, 실수 리터럴은 `double`이 기본이며 `L`, `f` 접미사로 자료형을 바꾼다. |
| 상수 | 상수는 `final`로 선언하고, 클래스 상수는 `static final`로 선언하며, 이름은 UPPER_SNAKE_CASE로 쓴다. |
| 연산자 | 연산자에는 산술, 대입, 증감, 관계, 논리 연산자가 있고, 우선순위가 높은 것부터 계산한다. |
| + 연산자 | 피연산자 중 하나가 String이면 `+`는 문자열 연결이 되며, 왼쪽에서 오른쪽으로 계산한다. |
| 형변환 | 확대 변환은 자동으로 일어나고, 축소 변환에는 명시적 캐스트가 필요하다. |
| 래퍼 클래스 | `Integer.parseInt`, `Double.parseDouble` 등으로 문자열을 수치로 바꾼다. |
| Scanner | `nextInt()`, `nextDouble()`, `nextLine()` 등으로 콘솔 입력을 읽는다. |
| String | String은 불변(immutable)이며 풍부한 조작 API를 제공한다. |
| 기본값 | 필드와 배열 원소는 0, `false`, `'\u0000'`, `null` 같은 기본값을 받지만, 지역 변수는 대입하기 전에 읽을 수 없다. |
| 2의 보수와 오버플로 | 정수는 2의 보수로 나타내며, 범위를 넘으면 예외 없이 반대쪽 끝으로 돌아간다. |
| 부동소수점 오차 | 0.1 같은 10진수는 2진수로 정확히 저장되지 않으므로 실수는 허용 오차로 비교하고, 금액은 `BigDecimal`이나 정수로 다룬다. |
| 래퍼 클래스 | 기본형마다 객체로 감싸는 래퍼 클래스가 있고, 오토박싱과 언박싱이 자동으로 일어나며, 래퍼 객체는 `equals`로 비교한다. |
| 정수 나눗셈과 나머지 | 몫은 0 쪽으로 버리고, 나머지의 부호는 나누어지는 수를 따르며, `floorDiv`와 `floorMod`는 내림을 쓴다. |
| 단축 평가 | `&&`와 `\|\|`는 왼쪽으로 결과가 정해지면 오른쪽을 계산하지 않으며, `&`와 `\|`는 두 쪽을 모두 계산한다. |
| 우선순위와 결합 | 우선순위는 묶음을, 결합 방향은 같은 순위의 묶는 방향을 정하며, 전위와 단항 연산자, `? :`, 대입은 오른쪽에서 왼쪽으로 결합한다. |
| 축소 변환 | 정수 사이의 축소 변환은 아래 비트만 남기고, 실수에서 정수로의 변환은 0 쪽으로 버리며 범위 끝에서 멈춘다. |
| 이항 숫자 승격 | 이항 연산의 피연산자는 `double`, `float`, `long`, `int`의 순서로 공통 자료형으로 바뀌며, `byte`끼리의 계산도 `int`이다. |

---

<br>

## 점검 문제

1. **증감 연산자:** 다음 코드의 각 `println`이 출력하는 내용을 쓰라.

   ```java
   int a, d = 3;
   a = d++; System.out.println("a = " + a + ", d = " + d);
   a = ++d; System.out.println("a = " + a + ", d = " + d);
   a = d--; System.out.println("a = " + a + ", d = " + d);
   a = --d; System.out.println("a = " + a + ", d = " + d);
   ```

   <details>
   <summary>정답</summary>

   차례로 `a = 3, d = 4`, `a = 5, d = 5`, `a = 5, d = 4`, `a = 3, d = 3`을 출력한다. 후위 연산자는 현재 값을 먼저 대입한 뒤 증감하고, 전위 연산자는 먼저 증감한 뒤 그 값을 대입한다.

   </details>

2. **+ 연산자:** `"test" + 1 + 2`와 `1 + 2 + "test"`의 결과를 쓰고 차이가 나는 이유를 설명하라.

   <details>
   <summary>정답</summary>

   앞의 식은 `"test12"`, 뒤의 식은 `"3test"`이다. `+`는 왼쪽에서 오른쪽으로 계산된다. 앞의 식은 처음부터 `String`이 있어 1과 2가 차례로 문자열에 연결되고, 뒤의 식은 `1 + 2`가 먼저 정수 덧셈으로 3이 된 뒤 `"test"`와 연결된다.

   </details>

3. **시간 단위 변환:** 8절의 프로그램에 7384를 입력하면 `hour`, `minute`, `second`는 각각 얼마인가?

   <details>
   <summary>정답</summary>

   `second` = 7384 % 60 = 4이다. 7384 / 60 = 123이므로 `minute` = 123 % 60 = 3, `hour` = 123 / 60 = 2이다. 따라서 2시간 3분 4초이다.

   </details>

4. **형변환:** 다음 값을 각각 쓰라. (a) `(int) 3.99` (b) `(float) 10 / 3` (c) `(int) (10 / 7 * 3.0)` (d) `double x = 3 + 5;` 뒤의 x (e) `int y = 3.5;`는 컴파일되는가?

   <details>
   <summary>정답</summary>

   (a) 소수 부분을 버리므로 3이다. (b) 10을 `float`으로 바꾼 뒤 나누므로 3.3333333이다. (c) `10 / 7`이 정수 나눗셈으로 1이 되고 1 * 3.0 = 3.0을 `int`로 바꾸므로 3이다. (d) `int` 값 8이 승격되어 8.0이다. (e) `double`을 `int`에 묵시적으로 대입할 수 없으므로 "possible lossy conversion from double to int" 컴파일 오류가 난다.

   </details>

5. **Scanner와 nextLine:** 사용자가 첫 줄에 `25`, 둘째 줄에 `hello world`를 입력할 때, `int t = scanner.nextInt(); String line = scanner.nextLine();`을 실행하면 `line`은 무엇인가? 둘째 줄을 읽으려면 어떻게 하는가?

   <details>
   <summary>정답</summary>

   `nextInt()`가 25만 읽고 줄 바꿈 문자를 남겨 두므로, `nextLine()`은 그 줄의 나머지인 빈 문자열 `""`을 반환한다. 둘째 줄을 읽으려면 `nextLine()`을 한 번 더 호출하여 남은 줄 바꿈을 소비한 뒤 다시 `nextLine()`을 호출한다.

   </details>

6. **오버플로 계산:** `int a = 2_000_000_000; int b = a + a;`를 실행한 뒤 `b`의 값을 구하라. `long c = a + a;`와 `long d = (long) a + a;`의 값은 각각 무엇인가?

   <details>
   <summary>정답</summary>

   수학적인 합 4,000,000,000은 `int`의 최댓값 2,147,483,647을 넘는다. 아래 32비트만 남으므로 4,000,000,000 - $`2^{32}`$ = 4,000,000,000 - 4,294,967,296 = -294,967,296이 `b`이다. `c`도 `a + a`를 `int`로 먼저 계산하므로 -294,967,296이다. `d`는 `a`를 `long`으로 바꾼 뒤 더하므로 4,000,000,000이다.

   </details>

7. **나눗셈과 나머지:** 다음 값을 각각 쓰라. (a) `-17 / 5` (b) `-17 % 5` (c) `17 % -5` (d) `Math.floorMod(-17, 5)` (e) `17 / 5 * 5.0`

   <details>
   <summary>정답</summary>

   (a) -3.4를 0 쪽으로 버려 -3이다. (b) (-3) × 5 + r = -17에서 r = -2이다. (c) 17 / -5 = -3이고 (-3) × (-5) + r = 17에서 r = 2이다. (d) 몫을 내림하여 -4로 보므로 -17 - (-4 × 5) = 3이다. (e) `17 / 5`가 먼저 정수 나눗셈으로 3이 되고 3 × 5.0 = 15.0이다.

   </details>

8. **형변환 추적:** 다음 코드의 출력을 쓰라.

   ```java
   char c = 'a';
   int code = c + 2;
   byte small = (byte) (code * 2);
   System.out.println(code + " " + (char) code + " " + small);
   ```

   <details>
   <summary>정답</summary>

   `'a'`의 코드는 97이므로 `code`는 99이고, `(char) 99`는 `'c'`이다. 99 × 2 = 198은 `byte` 범위 밖이므로 아래 8비트 `1100 0110`이 남아 198 - 256 = -58이 된다. 출력은 `99 c -58`이다.

   </details>

---

<br>

## 참고 문헌

1. J. Gosling, B. Joy, G. Steele, G. Bracha, A. Buckley, D. Smith, and G. Bierman, *The Java Language Specification, Java SE 21 Edition*. Oracle, 2023. [Online]. Available: https://docs.oracle.com/javase/specs/jls/se21/html/index.html
