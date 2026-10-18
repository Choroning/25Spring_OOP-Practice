# 3강 — Java 기본 문법

[레포지토리 안내](../../README.ko.md) | [English](Concepts_Lecture.md) | [한국어](Concepts_Lecture.ko.md)

> **최종 수정일:** 2026-10-19

> **학습 목표:**
> 1. 식별자 규칙과 이름 짓기 관례에 맞게 변수, 상수, 클래스의 이름을 지을 수 있다
> 2. Java 데이터 타입, 리터럴, 연산자, 타입 캐스팅을 사용할 수 있다
> 3. 기본형과 참조형의 차이를 메모리 상태로 설명할 수 있다
> 4. String 메서드와 포맷팅을 적용할 수 있다
> 5. Scanner 클래스로 콘솔 입력을 처리할 수 있다

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
- [3. 변수와 상수](#3-변수와-상수)
- [4. 연산자](#4-연산자)
  - [4.1 산술 연산자](#41-산술-연산자)
  - [4.2 연산자 우선순위](#42-연산자-우선순위)
  - [4.3 대입 연산자](#43-대입-연산자)
  - [4.4 증감 연산자](#44-증감-연산자)
  - [4.5 관계 및 논리 연산자](#45-관계-및-논리-연산자)
  - [4.6 + 연산자의 두 가지 의미](#46--연산자의-두-가지-의미)
- [5. 형변환](#5-형변환)
- [6. 콘솔 및 대화 상자 입출력](#6-콘솔-및-대화-상자-입출력)
  - [6.1 표준 출력](#61-표준-출력)
  - [6.2 DecimalFormat으로 숫자 서식 지정](#62-decimalformat으로-숫자-서식-지정)
  - [6.3 콘솔 입력을 위한 Scanner](#63-콘솔-입력을-위한-scanner)
  - [6.4 GUI 대화 상자를 위한 JOptionPane](#64-gui-대화-상자를-위한-joptionpane)
- [7. 문자열 연산](#7-문자열-연산)
- [8. 예제: 초를 시, 분, 초로 바꾸기](#8-예제-초를-시-분-초로-바꾸기)
- [요약](#요약)
- [점검 문제](#점검-문제)

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

> **핵심:** `char`(16비트 부호 없는 유니코드 코드 단위)를 제외하면 Java의 정수 자료형은 모두 부호 있는 형이며, unsigned 정수 자료형이 따로 없다. `int` 범위를 초과하는 값에는 `long` 또는 `BigInteger`를 사용한다.

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

*표 3. Java의 산술 연산자*

x = 10, y = 7, z = 2.5이면 `x + y`는 17, `x - y`는 3, `x * y`는 70, `x / y`는 1, `x / z`는 4.0, `x % y`는 3이다. `x / y`는 두 피연산자가 정수이므로 소수 부분을 버리는 정수 나눗셈이고, `x / z`는 피연산자 하나가 실수이므로 실수 나눗셈이다.

### 4.2 연산자 우선순위

`x + 3 * y`는 `3 * y`를 먼저 계산한 뒤 x에 더한다. 식의 계산 순서는 우선순위 규칙이 정한다. 우선순위가 높은 연산자를 먼저 계산하고, 우선순위가 같으면 대부분의 연산자는 왼쪽에서 오른쪽으로 계산한다.

| 순서 | 그룹 | 연산자 | 규칙 |
|:-----|:-----|:-----|:-----|
| 높음 | 부분식 | `( )` | 괄호 안의 부분식을 가장 먼저 계산한다. 괄호가 중첩되면 가장 안쪽부터, 같은 수준에 여럿이면 왼쪽부터 계산한다. |
| | 단항 연산자 | `-`, `+` | 단항 마이너스와 플러스를 두 번째로 계산한다. |
| | 곱셈 연산자 | `*`, `/`, `%` | 세 번째로 계산하며, 여럿이면 왼쪽부터 계산한다. |
| 낮음 | 덧셈 연산자 | `+`, `-` | 마지막으로 계산하며, 여럿이면 왼쪽부터 계산한다. |

*표 4. 산술 연산자의 우선순위*

### 4.3 대입 연산자

```java
a += 3;   // a = a + 3
b *= 3;   // b = b * 3
c %= 2;   // c = c % 2
```

`int a = 3, b = 3, c = 3;`에서 시작하면 위 세 문장 뒤에 `a`는 6, `b`는 9, `c`는 1이 된다.

> **참고:** 복합 대입 연산자는 결과를 왼쪽 변수의 자료형으로 자동 변환한다. 정확히는 `a += 3`이 `a = (int) (a + 3)`과 같으므로, `int a = 5; a += 2.7;`은 컴파일되고 `a`는 7이 된다. 반면 `a = a + 2.7;`은 `double`을 `int`에 대입하므로 컴파일 오류이다(5절).

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

*표 5. 관계 연산자와 논리 연산자*

### 4.6 + 연산자의 두 가지 의미

`+`는 문맥에 따라 두 가지 연산을 뜻한다. `<값1> + <값2>`에서 두 값이 모두 숫자이면 덧셈이고, 하나라도 `String`이면 문자열 연결이다. 계산은 왼쪽에서 오른쪽으로 진행한다.

```java
String output1 = "test" + 1 + 2;   // "test12"
String output2 = 1 + 2 + "test";   // "3test"
```

`output1`은 `"test" + 1`이 먼저 "test1"이 되고 다시 2가 연결된다. `output2`는 `1 + 2`가 먼저 덧셈으로 3이 된 뒤 "test"와 연결된다.

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

숫자 출력의 형식은 `java.text.DecimalFormat` 객체로 지정한다. 형식 문자열 `"0.000"`은 소수점 아래 세 자리까지 반올림하여 표시한다.

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

*표 6. Scanner의 주요 메서드*

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

*표 7. 래퍼 클래스의 문자열 변환 메서드*

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

*표 8. String의 주요 메서드*

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

   앞의 식은 "test12", 뒤의 식은 "3test"이다. `+`는 왼쪽에서 오른쪽으로 계산된다. 앞의 식은 처음부터 `String`이 있어 1과 2가 차례로 문자열에 연결되고, 뒤의 식은 `1 + 2`가 먼저 정수 덧셈으로 3이 된 뒤 "test"와 연결된다.

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

   `nextInt()`가 25만 읽고 줄 바꿈 문자를 남겨 두므로, `nextLine()`은 그 줄의 나머지인 빈 문자열 ""을 반환한다. 둘째 줄을 읽으려면 `nextLine()`을 한 번 더 호출하여 남은 줄 바꿈을 소비한 뒤 다시 `nextLine()`을 호출한다.

   </details>
