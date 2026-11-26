# 8강 — 고급 클래스 II

[레포지토리 안내](../../README.ko.md) | [English](Concepts_Lecture.md) | [한국어](Concepts_Lecture.ko.md)

> **최종 수정일:** 2026-11-27

> **학습 목표:**
> 1. 메서드에서 객체를 반환하는 과정과 예약어 this의 쓰임을 설명할 수 있다
> 2. 오버로딩된 메서드와 생성자를 정의하고 this()로 생성자를 연결할 수 있다
> 3. 가비지 컬렉션이 객체를 회수하는 조건을 설명할 수 있다
> 4. 클래스를 패키지로 구성하고 javadoc 주석으로 문서화할 수 있다
> 5. extends와 super()로 상위 클래스를 확장하는 하위 클래스를 정의할 수 있다
> 6. 패키지와 import, 패키지 전용 접근, 클래스 경로를 이용하여 여러 패키지의 클래스를 컴파일하고 실행할 수 있다

---

## 목차

- [1. 메서드에서 객체 반환](#1-메서드에서-객체-반환)
  - [1.1 this를 반환하는 메서드 연쇄 호출](#11-this를-반환하는-메서드-연쇄-호출)
  - [1.2 정적 팩토리 메서드](#12-정적-팩토리-메서드)
- [2. 예약어 this](#2-예약어-this)
  - [2.1 자기 참조 this](#21-자기-참조-this)
  - [2.2 this()를 이용한 생성자 호출](#22-this를-이용한-생성자-호출)
- [3. 메서드와 생성자 오버로딩](#3-메서드와-생성자-오버로딩)
  - [3.1 오버로딩 규칙](#31-오버로딩-규칙)
  - [3.2 반환 타입과 오버로딩](#32-반환-타입과-오버로딩)
  - [3.3 오버로딩된 메서드의 호출](#33-오버로딩된-메서드의-호출)
  - [3.4 오버로딩과 자동 형 변환](#34-오버로딩과-자동-형-변환)
- [4. Dice 클래스: 실용 예제](#4-dice-클래스-실용-예제)
- [5. 객체 소멸과 가비지 컬렉션](#5-객체-소멸과-가비지-컬렉션)
  - [5.1 도달 가능성과 가비지의 판정](#51-도달-가능성과-가비지의-판정)
  - [5.2 finalize와 자원 해제](#52-finalize와-자원-해제)
- [6. 접근 제어자와 패키지](#6-접근-제어자와-패키지)
  - [6.1 접근 제어자 복습](#61-접근-제어자-복습)
  - [6.2 패키지](#62-패키지)
  - [6.3 패키지 이름과 디렉터리 구조](#63-패키지-이름과-디렉터리-구조)
  - [6.4 import의 형식과 이름 충돌](#64-import의-형식과-이름-충돌)
  - [6.5 패키지 전용 접근과 클래스 경로](#65-패키지-전용-접근과-클래스-경로)
- [7. javadoc 주석](#7-javadoc-주석)
  - [7.1 javadoc 태그와 문서 생성](#71-javadoc-태그와-문서-생성)
- [8. 상속의 기초](#8-상속의-기초)
  - [8.1 extends 키워드](#81-extends-키워드)
  - [8.2 상위 클래스와 하위 클래스의 관계](#82-상위-클래스와-하위-클래스의-관계)
- [9. super()를 이용한 생성자 체이닝](#9-super를-이용한-생성자-체이닝)
- [10. 클래스 설계 모범 사례](#10-클래스-설계-모범-사례)
  - [10.1 합성으로 만든 클래스의 예](#101-합성으로-만든-클래스의-예)
- [11. 흔한 실수](#11-흔한-실수)
- [개념 적용](#개념-적용)
- [요약](#요약)
- [점검 문제](#점검-문제)
- [참고 문헌](#참고-문헌)

---

<br>

## 1. 메서드에서 객체 반환

메서드는 기본형 값을 반환하듯 객체도 반환할 수 있다. 객체를 반환하면 실제로는 객체의 참조(주소)를 반환하므로, 객체의 복사본이 아니라 그 객체를 가리키는 참조만 호출자에게 전달된다. 다음 `simplify`는 분수를 기약분수로 만든 새 `Fraction` 객체를 반환한다.

```java
public Fraction simplify() {      // return type: the class of the returned object

    Fraction simp;

    int num   = getNumerator();
    int denom = getDenominator();
    int gcd   = gcd(num, denom);

    simp = new Fraction(num/gcd, denom/gcd);

    return simp;                  // return an instance of the Fraction class
}
```

`f1`이 24/36일 때 `f2 = f1.simplify();`를 실행하면 메서드 안에서 2/3을 나타내는 새 `Fraction` 객체가 만들어지고 지역 변수 `simp`가 그 객체를 가리킨다. 메서드가 끝나면 `simp`의 값, 즉 참조가 호출자에게 반환되어 `f2`에 대입되고, `simp`가 차지하던 메모리는 해제된다.

```text
(1) just before return
f1 ──▶ ┌───────────────┐      simp ──▶ ┌───────────────┐
       │ :Fraction     │               │ :Fraction     │
       │ numerator   24│               │ numerator   2 │
       │ denominator 36│               │ denominator 3 │
       └───────────────┘               └───────────────┘

(2) after simplify ends
f1 ──▶ ┌───────────────┐        f2 ──▶ ┌───────────────┐
       │ :Fraction     │               │ :Fraction     │
       │ numerator   24│               │ numerator   2 │
       │ denominator 36│               │ denominator 3 │
       └───────────────┘               └───────────────┘
```

*그림 1. simplify가 반환한 Fraction 객체의 참조가 f2에 대입되는 과정*

`f1`이 가리키는 원래 객체는 바뀌지 않는다.

### 1.1 this를 반환하는 메서드 연쇄 호출

메서드가 새 객체 대신 자기 자신 `this`를 반환하면, 그 반환값에 곧바로 다음 메시지를 보낼 수 있다. 이렇게 `a.f().g().h()`처럼 호출을 잇는 것을 **메서드 연쇄 호출**(method chaining)이라 한다. 2강 4.6절의 `StringBuilder.append`가 이 방식이다.

```java
public class Counter {
    private int value;

    public Counter add(int n) {
        value += n;
        return this;
    }

    public Counter reset() {
        value = 0;
        return this;
    }

    public int get() {
        return value;
    }

    public static void main(String[] args) {
        Counter c = new Counter();
        int result = c.add(5).add(10).reset().add(7).get();
        System.out.println(result);
    }
}
```

```text
7
```

`c.add(5)`는 `c`의 값을 5로 만들고 `c` 자신을 반환하므로, 이어지는 `.add(10)`도 같은 객체에 보내진다. 값은 5, 15, 0, 7로 바뀌고 마지막 `get()`이 7을 돌려준다. 1절의 `simplify`는 새 `Fraction` 객체를 반환하여 원래 객체를 바꾸지 않았고, `Counter`의 메서드는 자신을 바꾼 뒤 자신을 반환한다. 두 방식 모두 연쇄 호출을 할 수 있지만, 앞의 방식은 원래 객체가 그대로 남고(불변 객체, 7강 8.2절) 뒤의 방식은 하나의 객체가 계속 바뀐다는 차이가 있다.

### 1.2 정적 팩토리 메서드

객체를 만드는 `static` 메서드를 **정적 팩토리 메서드**(static factory method)라 한다. 생성자와 달리 이름을 가질 수 있고, 객체를 만들기 전에 인자를 검사하거나 바꿀 수 있으며, 이미 있는 객체를 돌려줄 수도 있다. `Integer.valueOf(42)`, `List.of(...)`, 2강 4.8절의 `LocalDate.of(2025, 3, 10)`이 정적 팩토리 메서드이다. 다음 `Fraction`은 생성자를 `private`으로 막고, `of`를 통해서만 기약분수로 정리된 객체를 만든다.

```java
public class Fraction {
    private final int numerator;
    private final int denominator;

    private Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public static Fraction of(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("denominator is zero");
        }
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
        int g = gcd(Math.abs(numerator), denominator);
        return new Fraction(numerator / g, denominator / g);
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a == 0 ? 1 : a;
    }

    public Fraction plus(Fraction other) {
        return of(numerator * other.denominator + other.numerator * denominator,
                denominator * other.denominator);
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    public static void main(String[] args) {
        Fraction a = Fraction.of(24, 36);
        Fraction b = Fraction.of(1, -6);
        System.out.println(a + " " + b + " " + a.plus(b));
        Integer x = Integer.valueOf(42);
        System.out.println(x + " " + Integer.valueOf("17"));
    }
}
```

```text
2/3 -1/6 1/2
42 17
```

- `of(24, 36)`은 최대공약수 12(4강 3.10절의 유클리드 호제법)로 나누어 2/3을 만들고, `of(1, -6)`은 분모의 부호를 분자로 옮겨 -1/6을 만든다. 모든 `Fraction`이 `of`를 거치므로 분모가 0이거나 음수이거나 약분되지 않은 분수는 존재할 수 없다. 이것이 이 클래스의 불변식이다(7강 6.1절).
- `plus`는 2/3 + (-1/6) = (2 × 6 + (-1) × 3) / (3 × 6) = 9/18을 `of`에 넘겨 1/2로 만든다. 필드가 `final`이고 새 객체를 반환하므로 `Fraction`은 불변 클래스이다.
- `gcd`는 클래스 안에서만 쓰는 도우미이므로 `private static`으로 둔다(7강 5.3절).

생성자는 언제나 새 객체를 만들지만, 정적 팩토리 메서드는 같은 값에 대해 이미 만든 객체를 다시 돌려줄 수 있다. 3강 2.7절에서 `Integer` 127이 같은 객체가 된 것도 박싱이 이런 재사용을 하기 때문이다.

---

<br>

## 2. 예약어 this

### 2.1 자기 참조 this

예약어 `this`는 객체의 메서드 안에서 그 객체 자신을 가리키므로 **자기 참조 포인터**(self-referencing pointer)라고 한다. 같은 메서드라도 어느 객체로 호출했느냐에 따라 `this`가 가리키는 객체가 달라진다.

```java
public class Circle {
    int radius;
    public Circle(int radius) {
        this.radius = radius;
    }
    public void set(int radius) {
        this.radius = radius;
    }

    public static void main(String[] args) {
        Circle ob1 = new Circle(1);
        Circle ob2 = new Circle(2);
        Circle ob3 = new Circle(3);

        ob1.set(4);
        ob2.set(5);
        ob3.set(6);
    }
}
```

`ob1.set(4)`를 실행하는 동안 `this`는 `ob1`을 가리키므로 `ob1`의 `radius`가 1에서 4로 바뀐다. 마찬가지로 `ob2.set(5)`와 `ob3.set(6)`은 각각 `ob2`와 `ob3`의 `radius`를 5와 6으로 바꾼다. 매개변수 `radius`가 데이터 멤버 `radius`를 가리므로 데이터 멤버에는 `this.`를 붙여야 한다(7강 4.2절).

`this`는 다음 세 가지 방식으로 쓰인다.

1. `this.radius`처럼 데이터 멤버(그리고 `this.method()`처럼 같은 객체의 메서드)를 가리킨다.
2. `this(...)`로 같은 클래스의 다른 생성자를 호출한다(2.2절).
3. `someMethod(this)`처럼 현재 객체를 다른 메서드에 넘기거나 `return this;`로 반환한다.

### 2.2 this()를 이용한 생성자 호출

`this()`는 객체를 가리키는 `this`와 혼동하기 쉽지만, 같은 클래스의 다른 생성자를 강제로 호출하는 **함수 호출**이다. `this()`는 생성자 안에서만 쓸 수 있고 생성자의 첫 문장에 두어야 한다. 그렇지 않으면 컴파일 오류가 난다.

```java
public class Bicycle {

    protected String ownerName;

    public Bicycle() {
        this("Unknown");            // calls Bicycle(String)
    }

    public Bicycle(String ownerName) {
        this.ownerName = ownerName;
    }
    ...
}
```

매개변수 없는 생성자는 초기화를 직접 하지 않고 `this("Unknown")`으로 매개변수 있는 생성자에 맡긴다. 초기화 코드가 한 곳에만 있으므로 중복이 없다. 이 `Bicycle`로는 다음처럼 생성과 동시에 소유자 이름을 지정할 수 있다.

```java
Bicycle bike1, bike2;

bike1 = new Bicycle("Minseok Seo"); // Create and assign values to bike1
bike2 = new Bicycle("IU"); // Create and assign values to bike2

System.out.println(bike1.getOwnerName() + " owns a bicycle.");
System.out.println(bike2.getOwnerName() + " also owns a bicycle.");
```

> **참고:** **Java 버전과 첫 문장 규칙.** `this()`와 `super()`(9절)를 생성자의 첫 문장에 두어야 한다는 규칙은 Java 24까지의 규칙이다. Java 25부터는 생성 중인 객체를 사용하지 않는 문장(인자 검사 등)을 그 앞에 둘 수 있다 [1]. Java 24에서 `this()` 앞에 문장을 두면 `flexible constructors is a preview feature` 오류가 난다.

---

<br>

## 3. 메서드와 생성자 오버로딩

### 3.1 오버로딩 규칙

메서드는 다음 중 하나를 만족하면 같은 이름을 함께 쓸 수 있다.

- 규칙 1: 매개변수의 개수가 다르다.
- 규칙 2: 매개변수의 개수가 같으면 매개변수의 자료형이 다르다.

생성자도 같은 규칙으로 오버로딩한다. 이 규칙 덕분에 한 클래스에 생성자를 여러 개 정의할 수 있다(2.2절의 `Bicycle()`과 `Bicycle(String)`).

### 3.2 반환 타입과 오버로딩

반환 타입은 오버로딩의 구별 기준이 아니다.

```java
// Good overloading

class MethodOverloading {
   public int getSum(int i, int j) {
     return i + j;
   }
   public int getSum(int i, int j, int k) {
     return i + j + k;
   }
}
```

```java
// Error!!!

class MethodOverloadingFail {
   public int getSum(int i, int j) {
     return i + j;
   }
   public double getSum(int i, int j) {
     return (double)(i + j);
   }
}
```

첫 번째 클래스는 매개변수 개수가 다르므로(규칙 1) 올바른 오버로딩이다. 두 번째 클래스의 두 메서드는 매개변수 목록이 같고 반환 타입만 다르므로, 호출 `getSum(1, 2)`만 보고는 어느 메서드인지 정할 수 없다. 컴파일러는 `method getSum(int,int) is already defined` 오류를 낸다.

### 3.3 오버로딩된 메서드의 호출

컴파일러는 호출에 쓴 인자의 개수와 자료형을 보고 호출할 메서드를 고른다.

```java
public class MethodSample {
    public int getSum(int i, int j) {
        return i + j;
    }
    public int getSum(int i, int j, int k) {
        return i + j + k;
    }
    public double getSum(double i, double j) {
        return i + j;
    }
}

public static void main(String args[]) {
    MethodSample a = new MethodSample();
    int i = a.getSum(1, 2);           // getSum(int, int)
    int j = a.getSum(1, 2, 3);        // getSum(int, int, int)
    double k = a.getSum(1.1, 2.2);    // getSum(double, double)
}
```

`i`는 3, `j`는 6이 된다. `k`는 3.3에 가깝지만 이진 부동소수점 오차 때문에 출력하면 `3.3000000000000003`이 나온다.

### 3.4 오버로딩과 자동 형 변환

인자의 자료형이 매개변수와 정확히 같지 않을 때 컴파일러가 오버로딩된 메서드 가운데 무엇을 고르는지는 6강 2.8절의 세 단계 규칙이 정한다. 확대 변환으로 맞는 메서드가 박싱으로 맞는 메서드보다, 박싱으로 맞는 메서드가 가변 인자 메서드보다 먼저 선택된다. 표준 라이브러리에서 이 규칙이 눈에 띄는 예는 `System.out.println`이다. `println`에는 `boolean`, `char`, `int`, `long`, `float`, `double`, `char[]`, `String`, `Object`를 받는 오버로딩된 메서드가 있다.

```java
public class PrintOverload {
    public static void main(String[] args) {
        char[] letters = {'J', 'a', 'v', 'a'};
        System.out.println(letters);
        String s = "letters: " + letters;
        System.out.println(s.startsWith("letters: [C@"));
        System.out.println(String.valueOf(letters).length());
        Object o = letters;
        System.out.println(o.toString().startsWith("[C@"));
        System.out.println('a' + 'b');
        System.out.println("" + 'a' + 'b');
    }
}
```

```text
Java
true
4
true
195
ab
```

- `println(letters)`는 `println(char[])`가 정확히 맞으므로 배열의 문자를 이어 `Java`를 출력한다. 다른 자료형의 배열(`int[]`)은 `println(Object)`가 받으므로 5강 1.6절처럼 `[I@...`가 출력된다.
- `"letters: " + letters`는 문자열 연결이므로 배열의 `toString()`이 호출되어 `[C@` 뒤에 16진수가 붙는다. 같은 배열이라도 어느 메서드를 거치느냐에 따라 출력이 달라진다.
- `Object o = letters;`로 담으면 컴파일러는 변수의 선언된 자료형 `Object`를 보고 메서드를 고른다. 오버로딩은 실행 중의 객체가 아니라 컴파일할 때 정해진 자료형으로 결정된다.
- `'a' + 'b'`는 두 `char`가 `int`로 승격되어(3강 5.2절) 97 + 98 = 195가 되고 `println(int)`가 호출된다. 앞에 `""`를 두면 문자열 연결이 되어 `ab`가 된다.

---

<br>

## 4. Dice 클래스: 실용 예제

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

생성자가 오버로딩되어 있어 `new Dice()`는 6면체, `new Dice(20)`은 20면체 주사위를 만들며, 매개변수 없는 생성자는 `this(DEFAULT_FACES)`로 초기화를 위임한다. `random.nextInt(faces)`는 0 이상 `faces` 미만의 정수를 반환하므로 `roll()`은 1부터 `faces`까지의 값을 낸다. 필드가 모두 `final`이라 면의 수는 생성 뒤에 바뀌지 않는다. `faces`가 0 이하이면 `nextInt`가 `IllegalArgumentException`을 던지므로, 실제로는 생성자에서 값을 검사하는 편이 안전하다. 생성자에서 면의 수가 1 이상인지 검사하고 Javadoc 주석을 단 실습 코드는 [Dice.java](../../Coursework/Lab08_Advanced-Classes-II/Dice.java)이며, `main`에서 6면체와 20면체 주사위를 한 번씩 굴린다.

---

<br>

## 5. 객체 소멸과 가비지 컬렉션

> **용어 정의:** **객체 소멸**(object destruction)은 `new`로 할당한 객체의 메모리를 Java 가상 기계(JVM)의 가용 메모리로 되돌리는 일이며, JVM만 할 수 있다.

> **용어 정의:** **가비지**(garbage)는 자신을 가리키는 참조가 하나도 없는 객체이다.

> **용어 정의:** **가비지 컬렉션**(garbage collection)은 JVM의 가비지 컬렉터가 가비지를 자동으로 찾아 가용 메모리로 되돌리는 과정이다.

다음 코드에서 언제 메모리가 회수 대상이 되는지 살펴본다.

```java
public class GarbageEx {
  public static void main(String[] args) {
     String a = new String("Good");
     String b = new String("Bad");
     String c = new String("Normal");
     String d, e;
     a = null;
     d = c;
     c = null;
  }
}
```

```text
(1) before the assignments
a ──▶ "Good"
b ──▶ "Bad"
c ──▶ "Normal"
d
e

(2) after a = null; d = c; c = null;
a     null       "Good"
b ──▶ "Bad"
c     null   ┌─▶ "Normal"
d ───────────┘
e
```

*그림 2. 참조 변경에 따라 `"Good"` 객체가 가비지가 되는 과정*

`a = null;`로 `"Good"` 객체를 가리키는 참조가 사라지므로 이 객체는 가비지가 된다. `"Normal"` 객체는 `c = null;` 뒤에도 `d`가 가리키므로 가비지가 아니다. `d`와 `e`는 선언만 되었고, `d`는 `d = c;`에서 처음 값을 받는다.

개발자는 `System.gc()`나 `Runtime.getRuntime().gc()`를 호출하여 가비지 컬렉션을 요청할 수 있다. 그러나 이는 요청일 뿐이며, 실제로 수행할지는 JVM이 결정한다.

### 5.1 도달 가능성과 가비지의 판정

5절의 정의에서 "참조가 하나도 없는 객체"는 더 정확히 말하면 **도달할 수 없는**(unreachable) 객체이다. 언어 명세는 살아 있는 스레드의 앞으로의 계산에서 접근할 수 있는 객체를 **도달 가능한**(reachable) 객체로 정의한다 [2]. 실제로는 실행 중인 메서드 프레임의 지역 변수와 매개변수, 클래스 변수(`static` 필드) 같은 출발점에서 참조를 따라갈 수 있는 객체가 도달 가능하며, 그 밖의 객체가 가비지이다.

이 정의 때문에 서로를 가리키는 객체도 가비지가 될 수 있다.

```text
 (1) after a.next = b; b.next = a;

 a ●──▶ [Node1] ──next──▶ [Node2] ◀──● b
           ▲                  │
           └──────next────────┘

 (2) after a = null; b = null;

 a  null                         b  null
        [Node1] ──next──▶ [Node2]
           ▲                  │
           └──────next────────┘
```

*그림 3. 서로 가리키는 두 객체가 출발점에서 끊어져 가비지가 되는 과정*

(2)에서 두 `Node` 객체는 여전히 서로를 가리키는 참조를 가지고 있지만, 지역 변수 `a`, `b`에서 따라갈 길이 없으므로 도달할 수 없고 가비지이다. 참조의 개수를 세는 방식이라면 회수할 수 없는 이런 순환 구조도 도달 가능성으로 판정하면 회수된다.

반대로 더 이상 쓰지 않는 객체라도 도달 가능하면 회수되지 않는다. 예를 들어 `static` 필드의 배열이나 컬렉션에 객체를 계속 넣기만 하고 꺼내 버리지 않으면, 그 객체들은 클래스 변수에서 도달 가능하므로 프로그램이 끝날 때까지 메모리를 차지한다. 가비지 컬렉션이 있는 언어에서 일어나는 메모리 누수는 대부분 이런 형태이며, 다 쓴 참조에 `null`을 대입하거나 컬렉션에서 지우면 해결된다.

### 5.2 finalize와 자원 해제

`Object`에는 객체가 회수되기 전에 가비지 컬렉터가 호출하는 `finalize()` 메서드가 있었다. 그러나 언제 호출될지, 호출되기는 할지 정해져 있지 않고 보안, 성능, 신뢰성의 문제를 일으킬 수 있어, Java SE 21의 API 문서는 이 메서드를 제거 예정(deprecated for removal)으로 표시하고 있다 [3]. 따라서 파일이나 네트워크 연결 같은 자원을 객체의 소멸에 맡겨 해제해서는 안 된다. 자원은 다 쓴 시점에 `close()`를 호출하여 직접 닫고, 예외가 나도 닫히도록 6강 3.11절의 try-with-resources를 쓴다. C++의 소멸자처럼 객체가 사라질 때 확실히 실행되는 코드는 Java에 없다는 점을 기억해 둘 필요가 있다(1강 3.11절).

---

<br>

## 6. 접근 제어자와 패키지

### 6.1 접근 제어자 복습

Java의 접근 제어자는 `private`, default(생략한 경우), `protected`, `public`의 네 가지이며, 이 순서로 접근 범위가 넓어진다.

- `private`: 클래스 밖에서의 접근을 완전히 막는다.
- default: 같은 패키지 안에서 접근을 허용한다.
- `protected`: 같은 패키지와 하위 클래스에서 접근을 허용한다.
- `public`: 모든 클래스에서 접근을 허용한다.

각 범위의 표는 7강 5.2절에 있다.

### 6.2 패키지

패키지를 쓰지 않으면 클래스 A가 클래스 B를 사용하기 위해 두 클래스의 바이트코드 파일이 같은 디렉터리에 있어야 한다. 프로그래머 정의 클래스를 여러 프로그램에서 재사용하려면 이 방식은 실용적이지 않다. 재사용할 클래스는 **패키지**(package)에 넣는 것이 올바른 방법이며, 패키지는 Java 클래스 라이브러리이다.

```java
package mypackage;

public class Bicycle {
    ...
}
```

`package` 문은 소스 파일의 첫 문장으로 쓰며, 컴파일된 클래스 파일은 패키지 이름과 같은 디렉터리(`mypackage/`)에 놓인다. 다른 패키지의 클래스는 `import mypackage.Bicycle;`로 가져와 사용하며, 이때 `public`으로 선언된 클래스와 멤버만 쓸 수 있다.

### 6.3 패키지 이름과 디렉터리 구조

패키지 이름은 다른 사람의 패키지와 겹치지 않게 짓는다. 관례는 클래스와 구별되도록 모두 소문자로 쓰고, 조직의 인터넷 도메인 이름을 거꾸로 하여 시작하는 것이다 [4]. 예를 들어 `example.com`에서 만든 은행 패키지는 `com.example.bank`가 된다. Java 자체의 패키지는 `java.`나 `javax.`로 시작한다. 패키지 이름의 점은 하위 디렉터리를 뜻하므로, `com.example.bank` 패키지의 소스와 클래스 파일은 `com/example/bank` 디렉터리에 둔다. 다음은 두 패키지로 된 작은 프로그램의 디렉터리 구조이다.

```text
bank/
└── src/
    └── com/
        └── example/
            ├── bank/
            │   ├── Account.java     package com.example.bank;
            │   └── Audit.java       package com.example.bank;
            └── app/
                └── Main.java        package com.example.app;
```

*그림 4. 두 패키지로 된 프로그램의 소스 디렉터리 구조*

```java
package com.example.bank;

public class Account {
    private final String owner;
    double balance;

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
package com.example.bank;

public class Audit {
    public static void reset(Account account) {
        account.balance = 0.0;
    }
}
```

```java
package com.example.app;

import com.example.bank.Account;
import com.example.bank.Audit;
import static java.lang.Math.max;

public class Main {
    public static void main(String[] args) {
        Account a = new Account("Kim", 500.0);
        Audit.reset(a);
        java.util.List<String> names = java.util.List.of(a.getOwner());
        System.out.println(names + " " + a.getBalance() + " " + max(3, 9));
    }
}
```

`javac`의 `-d out` 옵션은 클래스 파일을 `out` 디렉터리 아래에 패키지 구조대로 만들어 넣는다. 실행할 때는 `-cp out`으로 클래스를 찾을 출발 디렉터리를 알려 주고, 시작할 클래스는 패키지 이름까지 붙인 완전한 이름으로 쓴다.

```text
$ javac --release 21 -d out src/com/example/bank/*.java src/com/example/app/Main.java
$ find out -name '*.class' | sort
out/com/example/app/Main.class
out/com/example/bank/Account.class
out/com/example/bank/Audit.class
$ java -cp out com.example.app.Main
[Kim] 0.0 9
```

패키지에 속한 클래스의 이름은 `com.example.app.Main`처럼 패키지 이름을 포함한 **완전한 이름**(fully qualified name)이다. 그래서 `out/com/example/app` 안으로 들어가 `java Main`으로 실행하면 찾지 못한다. 패키지 선언과 디렉터리가 맞지 않을 때도 같은 오류가 나는데, 다음은 `package com.example.bank;`을 선언한 `Loose.java`를 아무 디렉터리에서나 컴파일한 뒤 실행한 결과이다.

```text
$ java Loose
Error: Could not find or load main class Loose
Caused by: java.lang.NoClassDefFoundError: Loose (wrong name: com/example/bank/Loose)
```

`Loose.class` 안에 적힌 이름은 `com.example.bank.Loose`인데 `Loose`라는 이름으로 찾았기 때문이다. 완전한 이름으로 실행하려면 클래스 파일이 `com/example/bank/Loose.class` 위치에 있어야 하므로, 패키지가 있는 프로그램은 항상 `-d`로 컴파일하는 편이 안전하다.

### 6.4 import의 형식과 이름 충돌

다른 패키지의 클래스는 완전한 이름으로 쓰거나 `import`로 이름만 쓸 수 있게 한다. 6.3절의 `Main`에는 세 형식이 모두 있다.

| 형식 | 예 | 가져오는 것 |
|:-----|:-----|:-----|
| 단일 타입 import | `import com.example.bank.Account;` | 클래스 하나 |
| 요청 시 import | `import java.util.*;` | 그 패키지의 `public` 클래스 전부(하위 패키지는 포함하지 않음) |
| 정적 import | `import static java.lang.Math.max;` | 클래스의 `static` 멤버, 클래스 이름 없이 `max(3, 9)`로 쓴다 |
| import 없이 완전한 이름 | `java.util.List<String> names` | 그 자리에서만 |

*표 1. 다른 패키지의 이름을 쓰는 방법*

`String`, `System`, `Math`처럼 `java.lang` 패키지의 클래스는 import하지 않아도 쓸 수 있다. 모든 소스 파일이 `import java.lang.*;`을 쓴 것처럼 처리되기 때문이다 [2]. `import`는 이름을 줄여 쓰게 할 뿐 클래스를 복사하거나 프로그램에 포함시키지 않으므로, `import`를 많이 쓴다고 실행 파일이 커지거나 느려지지 않는다. C의 `#include`와 다른 점이다(1강 3.11절). `java.util.*`이 `java.util.function` 같은 하위 패키지를 가져오지 않는 이유도 패키지 이름의 점이 단지 이름의 일부일 뿐 포함 관계를 뜻하지 않기 때문이다.

요청 시 import 두 개가 같은 이름의 클래스를 가지면, 그 이름을 쓰는 곳에서 컴파일 오류가 난다. `java.util`과 `java.sql`은 모두 `Date` 클래스를 가진다.

```java
import java.util.*;
import java.sql.*;

public class DateClash {
    public static void main(String[] args) {
        Date d = new Date();
    }
}
```

```text
DateClash.java:6: error: reference to Date is ambiguous
        Date d = new Date();
        ^
  both class java.sql.Date in java.sql and class java.util.Date in java.util match
DateClash.java:6: error: reference to Date is ambiguous
        Date d = new Date();
                     ^
  both class java.sql.Date in java.sql and class java.util.Date in java.util match
2 errors
```

단일 타입 import는 요청 시 import보다 우선하므로, `import java.util.Date;`를 더하면 `Date`는 `java.util.Date`로 정해지고, 다른 쪽은 완전한 이름 `java.sql.Date`로 쓴다.

```java
import java.util.*;
import java.sql.*;
import java.util.Date;

public class DateOk {
    public static void main(String[] args) {
        Date d = new Date(0);
        java.sql.Date s = new java.sql.Date(0);
        System.out.println(d.getClass().getName() + " " + s.getClass().getName());
    }
}
```

```text
java.util.Date java.sql.Date
```

### 6.5 패키지 전용 접근과 클래스 경로

접근 제어자를 쓰지 않은 클래스, 필드, 메서드는 **패키지 전용 접근**(package access)을 가지며, 같은 패키지 안에서만 쓸 수 있다 [2]. 7강 5.2절 표의 (default)가 이것이다. 6.3절의 `Account`에서 `balance`는 제어자가 없으므로 같은 패키지의 `Audit`은 `account.balance = 0.0;`으로 직접 바꿀 수 있었다. 다른 패키지 `com.example.app`의 클래스가 같은 일을 하면 다음 오류가 난다.

```java
package com.example.app;

import com.example.bank.Account;

public class Peek {
    public static void main(String[] args) {
        Account a = new Account("Lee", 10.0);
        a.balance = 1000000.0;
    }
}
```

```text
src/com/example/app/Peek.java:8: error: balance is not public in Account; cannot be accessed from outside package
        a.balance = 1000000.0;
         ^
1 error
```

패키지 전용 접근은 같은 패키지의 클래스끼리 긴밀히 협력하게 하면서 패키지 밖에는 숨기는 데 쓴다. 클래스 자체도 `public`을 붙이지 않으면 패키지 안에서만 쓸 수 있으므로, 패키지 밖에 공개할 클래스에만 `public`을 붙인다. 한 소스 파일에 `public` 클래스가 하나만 있을 수 있다는 1강 3.4절의 규칙도 이 공개 단위와 관련된다.

컴파일러와 JVM이 클래스 파일을 찾는 디렉터리와 JAR 파일의 목록을 **클래스 경로**(class path)라 한다. 둘은 클래스 경로의 각 항목에 패키지 이름을 디렉터리로 바꾸어 붙인 위치에서 클래스 파일을 찾으며, 클래스 경로를 따로 정하지 않으면 현재 디렉터리를 찾는다 [5]. 클래스 경로는 `-cp` 옵션이나 `CLASSPATH` 환경 변수로 정하고, 여러 항목은 Windows에서는 세미콜론(`;`), Unix 계열에서는 콜론(`:`)으로 구분한다.

```text
$ java -cp src com.example.app.Main
Error: Could not find or load main class com.example.app.Main
Caused by: java.lang.ClassNotFoundException: com.example.app.Main
$ jar cf bank.jar -C out .
$ java -cp bank.jar com.example.app.Main
[Kim] 0.0 9
```

`src`에는 소스 파일만 있고 클래스 파일이 없으므로 찾지 못한다. `jar cf bank.jar -C out .`은 `out` 아래의 디렉터리 구조를 그대로 JAR 파일 하나에 담으며(1강 3.5절), JAR 파일도 클래스 경로의 항목이 될 수 있다. 패키지 선언이 없는 클래스는 이름 없는 패키지(unnamed package)에 속한다. 이름 없는 패키지의 클래스는 이름 있는 패키지의 클래스에서 import할 방법이 없으므로, 작은 예제가 아닌 프로그램은 처음부터 패키지를 정해 두는 것이 좋다.

---

<br>

## 7. javadoc 주석

다른 프로그래머가 사용할 클래스는 올바른 사용법을 알 수 있도록 의미 있는 문서를 제공해야 한다. **javadoc 주석**을 달면 클래스를 일관된 형식으로 문서화할 수 있고, `javadoc` 명령으로 문서 HTML 파일을 생성할 수 있다.

- javadoc 주석은 `/**`로 시작하여 `*/`로 끝난다.
- 작성자, 매개변수, 반환값 같은 특별한 정보는 `@` 표시로 시작하는 태그(`@param`, `@author`, `@return`, `@version`, `@since` 등)로 적는다.

```java
/**
 * The Bicycle class implements an application that
 * simply displays bike's owner based on the multiple classes
 *
 * @author Minseok Seo
 * @version 1.0
 * @since 2025-04-14
 */
public class Bicycle {

    /**
     * Data member for owner's name (String)
     */
    protected String ownerName;

    /**
     * Constructor of Bicycle class
     *
     * @param ownerName will be set on the data member
     */
    public Bicycle(String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * Returns the name of this bicycle's owner
     * @return ownerName
     */
    public String getOwnerName() {
        return ownerName;
    }
}
```

`javadoc -d doc Bicycle.java`를 실행하면 `doc` 디렉터리에 클래스 설명, 생성자와 메서드 목록, 매개변수와 반환값 설명을 담은 HTML 문서가 생성된다.

### 7.1 javadoc 태그와 문서 생성

javadoc 주석은 설명하려는 클래스, 필드, 생성자, 메서드 바로 앞에 쓴다. 첫 문장은 요약으로 쓰여 목록 화면에 나오므로 그 요소가 하는 일을 한 문장으로 쓴다. 자주 쓰는 태그는 다음과 같다.

| 태그 | 쓰는 곳 | 내용 |
|:-----|:-----|:-----|
| `@author`, `@version`, `@since` | 클래스 | 작성자, 버전, 처음 도입된 버전 |
| `@param 이름 설명` | 메서드, 생성자 | 매개변수 하나의 뜻(매개변수마다 하나씩) |
| `@return 설명` | 값을 반환하는 메서드 | 반환값의 뜻 |
| `@throws 예외 설명` | 메서드, 생성자 | 던질 수 있는 예외와 그 조건 |
| `@see 대상` | 어디든 | 함께 볼 클래스나 메서드 |
| `{@code 코드}` | 설명 안 | 코드 글꼴로 표시 |
| `{@link 대상}` | 설명 안 | 다른 요소로 가는 링크 |

*표 2. 자주 쓰는 javadoc 태그*

```java
/**
 * A thermometer that records temperatures in degrees Celsius.
 *
 * @author Example Author
 * @version 1.0
 */
public class Thermometer {

    /** The lowest temperature this thermometer accepts. */
    public static final double MIN_CELSIUS = -273.15;

    private double celsius;

    /**
     * Sets the current temperature.
     *
     * @param celsius the temperature in degrees Celsius
     * @throws IllegalArgumentException if {@code celsius} is below {@link #MIN_CELSIUS}
     */
    public void setCelsius(double celsius) {
        if (celsius < MIN_CELSIUS) {
            throw new IllegalArgumentException("below absolute zero: " + celsius);
        }
        this.celsius = celsius;
    }

    /**
     * Returns the current temperature in degrees Fahrenheit.
     *
     * @return the temperature converted with {@code F = C * 9 / 5 + 32}
     * @see #setCelsius(double)
     */
    public double getFahrenheit() {
        return celsius * 9 / 5 + 32;
    }
}
```

```text
$ javadoc -d doc Thermometer.java
Loading source file Thermometer.java...
Constructing Javadoc information...
Creating destination directory: "doc/"
Building index for all the packages and classes...
Standard Doclet version 24.0.2+12-54
Building tree for all the packages and classes...
Generating doc/Thermometer.html...
Thermometer.java:7: warning: use of default constructor, which does not provide a comment
public class Thermometer {
       ^
Generating doc/package-summary.html...
```

출력의 일부만 옮겼다. `doc/Thermometer.html`에 클래스 설명, 상수 `MIN_CELSIUS`, 두 메서드의 설명과 매개변수, 반환값, 예외가 정리되고, `doc/index.html`이 문서의 첫 화면이 된다. `private` 필드 `celsius`는 클라이언트가 알 필요 없는 구현이므로 기본적으로 문서에 나오지 않는다. 경고는 컴파일러가 만든 기본 생성자(7강 2.1절)에 주석이 없다는 뜻이며, 생성자를 직접 쓰고 javadoc 주석을 달면 사라진다. javadoc 주석은 그 메서드를 쓰는 사람을 위한 설명이므로, 어떻게 구현했는지보다 무엇을 하고 어떤 조건에서 어떤 결과와 예외를 내는지를 쓴다.

---

<br>

## 8. 상속의 기초

### 8.1 extends 키워드

상속을 통해 새로운 클래스(하위 클래스)가 기존 클래스(상위 클래스)의 필드와 메서드를 물려받을 수 있다. 하위 클래스는 `class 하위클래스 extends 상위클래스`로 선언한다. 다음 `Bicycle`을 상위 클래스로 쓰는 예는 9절에 있다.

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

### 8.2 상위 클래스와 하위 클래스의 관계

- 하위 클래스는 상위 클래스의 `private`이 아닌 멤버를 상속받아 직접 사용한다. 단, default 멤버는 하위 클래스가 같은 패키지에 있을 때만 접근할 수 있다. `private` 멤버는 하위 클래스에서 직접 접근할 수 없고, 상위 클래스의 `public` 메서드(예: `getOwnerName()`)를 통해 사용한다.
- 하위 클래스는 새로운 필드와 메서드를 추가할 수 있다.
- 하위 클래스는 상속받은 메서드를 오버라이드할 수 있다.

> **핵심:** Java는 단일 상속만 지원한다. 즉, 하나의 클래스는 최대 하나의 상위 클래스만 확장할 수 있다. 다중 타입 관계가 필요한 경우 인터페이스를 사용한다.

---

<br>

## 9. super()를 이용한 생성자 체이닝

생성자는 상속되지 **않는다**. `super()`를 사용하여 상위 클래스의 생성자를 호출한다.

```java
public class ElectricBicycle extends Bicycle {
    private int batteryLevel;

    public ElectricBicycle(String ownerName, int battery) {
        super(ownerName);         // call Bicycle's constructor
        this.batteryLevel = battery;
    }
}
```

`ElectricBicycle`의 `ownerName`은 상위 클래스에서 `private`이므로 하위 클래스가 직접 대입할 수 없다. 그래서 `super(ownerName)`으로 상위 클래스의 생성자에 초기화를 맡긴다.

> **핵심:** `super()`는 하위 클래스 생성자의 첫 번째 문장이어야 한다(Java 25부터의 완화는 2.2절의 참고). 생략할 경우 Java는 암묵적으로 인자 없는 `super()`를 호출하는데, 상위 클래스에 매개변수 없는 생성자가 없으면 컴파일 오류가 발생한다.

---

<br>

## 10. 클래스 설계 모범 사례

| 원칙 | 설명 |
|:-----|:-----|
| **단일 책임 원칙** | 각 클래스는 변경해야 할 이유가 하나뿐이어야 한다 |
| **캡슐화** | 내부 구현을 숨기고 필요한 API만 노출한다 |
| **불변성** | 생성 이후 변경되지 않아야 하는 필드는 `final`로 선언한다 |
| **합성 우선** | 상속이 자연스럽지 않은 경우 is-a 관계보다 has-a 관계를 선호한다 |

*표 3. 클래스 설계의 네 가지 원칙*

**합성**(composition)은 다른 클래스의 객체를 필드로 가지는 has-a 관계이다. 예를 들어 `Dice`는 `Random` 객체를 필드로 가지고 무작위 수 생성을 맡긴다. 상속(is-a)은 하위 클래스를 상위 클래스의 구현에 묶으므로, 하위 클래스가 상위 클래스의 일종이라고 말할 수 있을 때만 쓴다.

### 10.1 합성으로 만든 클래스의 예

합성은 큰 객체가 작은 객체를 부품으로 가지고 일을 나누어 맡기는 구조이다. 다음 `Car`는 `Engine` 객체를 필드로 가지며, 자동차가 해야 할 일 가운데 엔진에 관한 부분을 `Engine`의 메서드에 맡긴다.

```java
public class Engine {
    private final int horsepower;
    private boolean running;

    public Engine(int horsepower) {
        this.horsepower = horsepower;
    }

    public void start() {
        running = true;
    }

    public boolean isRunning() {
        return running;
    }

    public int getHorsepower() {
        return horsepower;
    }
}
```

```java
public class Car {
    private final String model;
    private final Engine engine;

    public Car(String model, int horsepower) {
        this.model = model;
        this.engine = new Engine(horsepower);
    }

    public void drive() {
        if (!engine.isRunning()) {
            engine.start();
        }
        System.out.println(model + " drives with " + engine.getHorsepower() + " hp");
    }

    public static void main(String[] args) {
        Car car = new Car("Sedan", 150);
        car.drive();
        car.drive();
    }
}
```

```text
Sedan drives with 150 hp
Sedan drives with 150 hp
```

"자동차는 엔진을 가진다"는 has-a 관계이고 "자동차는 엔진의 일종이다"는 성립하지 않으므로, `Car`가 `Engine`을 상속하지 않고 필드로 가진다. `Car`의 생성자가 `Engine`을 직접 만들고 바깥에 내주지 않으므로, 엔진의 상태는 `Car`의 메서드를 거쳐서만 바뀐다(7강 3.3절). 첫 `drive()`가 엔진을 켜고 둘째 `drive()`는 이미 켜진 엔진을 그대로 쓴다. 엔진의 구현을 바꾸어도 `Engine`의 공개 메서드가 같으면 `Car`는 고칠 필요가 없고, 다른 성능의 엔진을 가진 자동차는 생성자 인자만 바꾸어 만든다. 상속은 하위 클래스가 상위 클래스의 공개 메서드를 모두 물려받아 외부에 드러내지만, 합성은 필요한 기능만 골라 자기 메서드로 감싸 공개한다는 점에서 캡슐화를 지키기 쉽다. 상속을 쓰는 설계와의 비교는 11강에서 다룬다.

---

<br>

## 11. 흔한 실수

| 실수 | 나타나는 결과 | 바로잡는 방법 |
|:-----|:-----|:-----|
| 연쇄 호출할 메서드가 `this` 대신 `void`를 반환한다 | `void cannot be dereferenced` 같은 컴파일 오류 | 연쇄 호출할 메서드는 `this`를 반환한다 |
| 오버로딩된 메서드가 실행 중의 객체에 따라 정해진다고 생각한다 | 기대와 다른 메서드가 호출된다 | 오버로딩은 컴파일할 때 선언된 자료형으로 정해진다 |
| 서로 가리키는 객체는 회수되지 않는다고 생각한다 | 가비지 판정을 잘못 설명한다 | 출발점에서 도달할 수 없으면 회수된다 |
| `static` 컬렉션에 객체를 넣기만 한다 | 회수되지 않아 메모리가 계속 늘어난다 | 다 쓴 원소를 지우거나 참조를 `null`로 한다 |
| 자원 해제를 `finalize()`에 맡긴다 | 자원이 언제 닫힐지 알 수 없다 | try-with-resources로 직접 닫는다 |
| 패키지 선언과 디렉터리가 다르다 | `NoClassDefFoundError (wrong name: ...)` | `javac -d`로 컴파일하고 디렉터리를 패키지 이름에 맞춘다 |
| 패키지 안의 클래스를 단순한 이름으로 실행한다 | `Could not find or load main class` | `java -cp out com.example.app.Main`처럼 완전한 이름을 쓴다 |
| 두 요청 시 import가 같은 이름을 가진다 | `reference to Date is ambiguous` | 단일 타입 import나 완전한 이름을 쓴다 |
| 다른 패키지에서 제어자 없는 멤버를 쓴다 | `is not public ...; cannot be accessed from outside package` | 공개할 멤버에 `public`을 붙이거나 공개 메서드를 거친다 |
| `java.util.*`이 하위 패키지까지 가져온다고 생각한다 | 하위 패키지의 클래스를 찾지 못한다 | 하위 패키지는 따로 import한다 |

*표 4. 객체 반환, 가비지 컬렉션, 패키지에서 흔한 실수*

---

<br>

## 개념 적용

**T/F 점검:**

| 문장 | 판단과 근거 |
|:-----|:-----|
| `return this;`로 끝나는 메서드는 호출할 때마다 새 객체를 만든다. | **F.** 자기 자신의 참조를 돌려줄 뿐이므로 연쇄 호출은 모두 같은 객체에 보내진다. |
| 정적 팩토리 메서드는 생성자와 달리 이미 만든 객체를 돌려줄 수 있다. | **T.** 생성자는 항상 새 객체를 만들지만, `static` 메서드는 무엇을 반환할지 스스로 정한다. |
| `Object o = new char[]{'a'}; System.out.println(o);`는 `a`를 출력한다. | **F.** 선언된 자료형이 `Object`이므로 `println(Object)`가 선택되어 `[C@...`가 출력된다. |
| 서로를 가리키는 두 객체는 영원히 회수되지 않는다. | **F.** 지역 변수나 클래스 변수 같은 출발점에서 도달할 수 없으면 가비지가 된다. |
| `import java.util.*;`를 쓰면 프로그램에 `java.util`의 모든 클래스가 포함되어 실행 파일이 커진다. | **F.** `import`는 이름을 줄여 쓰게 할 뿐이며, 클래스는 실행 중에 필요할 때 적재된다(1강 3.8절). |
| `String`을 쓰려면 `import java.lang.String;`이 필요하다. | **F.** `java.lang`의 클래스는 모든 소스 파일에서 자동으로 import된다. |
| 제어자 없이 선언한 필드는 다른 패키지의 하위 클래스에서도 접근할 수 있다. | **F.** 패키지 전용 접근은 같은 패키지 안에서만 허용된다. 다른 패키지의 하위 클래스까지 허용하는 것은 `protected`이다. |
| `com.example.bank.Account`의 클래스 파일은 클래스 경로 아래 `com/example/bank/Account.class`에 있어야 한다. | **T.** 컴파일러와 JVM은 클래스 경로의 항목에 패키지 이름을 디렉터리로 바꾸어 붙인 위치에서 찾는다. |

---

<br>

## 요약

| 개념 | 핵심 요약 |
|:-----|:-----|
| 객체 반환 | 메서드가 객체를 반환하면 복사본이 아니라 참조가 반환된다. |
| this | 메서드를 호출한 객체 자신을 가리키는 자기 참조이다. |
| this() | 같은 클래스의 다른 생성자를 호출하며, 생성자의 첫 문장에 둔다. |
| 오버로딩 | 매개변수의 개수나 자료형이 다르면 메서드와 생성자가 같은 이름을 쓸 수 있으며, 반환 타입은 기준이 아니다. |
| 가비지 컬렉션 | 참조가 하나도 없는 객체를 JVM이 자동으로 회수하며, `System.gc()`는 요청일 뿐이다. |
| 패키지 | 재사용할 클래스를 묶는 클래스 라이브러리이며, `package` 문으로 지정하고 `import`로 가져온다. |
| javadoc | `/** */` 주석과 `@` 태그로 클래스를 문서화하고 `javadoc` 명령으로 HTML을 생성한다. |
| 상속 | `extends`로 is-a 관계를 만들며, 단일 상속만 지원한다. |
| super() | 상위 클래스의 생성자를 호출하며, 하위 클래스 생성자의 첫 번째 문장이어야 한다. |
| 생성자 체이닝 | 하위 클래스 생성자는 상위 클래스 생성자에, 생성자는 같은 클래스의 다른 생성자에 초기화를 위임할 수 있다. |
| 클래스 설계 | 단일 책임 원칙, 캡슐화, 불변성, 합성 우선 원칙을 따른다. |
| 메서드 연쇄 호출 | `this`를 반환하는 메서드는 `a.f().g()`처럼 같은 객체에 이어서 메시지를 보내게 한다. |
| 정적 팩토리 메서드 | 객체를 만드는 `static` 메서드로, 이름을 가지며 인자를 검사하거나 기존 객체를 돌려줄 수 있다. |
| 오버로딩과 형 변환 | 오버로딩은 컴파일할 때의 선언된 자료형으로 정해지며, `println(char[])`과 `println(Object)`처럼 결과가 달라질 수 있다. |
| 도달 가능성 | 지역 변수와 클래스 변수에서 참조를 따라 도달할 수 없는 객체가 가비지이며, 순환 구조도 회수된다. |
| finalize | 제거 예정인 메커니즘이므로 자원은 try-with-resources로 직접 닫는다. |
| 패키지 이름 | 소문자로 쓰고 도메인 이름을 거꾸로 하여 시작하며, 점으로 나뉜 각 부분이 디렉터리에 대응한다. |
| import | 단일 타입, 요청 시, 정적 import가 있고, `java.lang`은 자동으로 import되며, 같은 이름이 겹치면 단일 타입 import나 완전한 이름을 쓴다. |
| 패키지 전용 접근 | 제어자가 없는 클래스와 멤버는 같은 패키지에서만 쓸 수 있다. |
| 클래스 경로 | 클래스 파일을 찾는 디렉터리와 JAR의 목록으로, `-cp`나 `CLASSPATH`로 정하고 완전한 이름으로 실행한다. |
| javadoc 태그 | `@param`, `@return`, `@throws`, `@see`, `{@code}`, `{@link}`로 사용법을 기술하고 `javadoc -d`로 HTML을 만든다. |
| 합성 | 부품 객체를 필드로 두고 일을 맡기는 has-a 관계로, 필요한 기능만 자기 메서드로 감싸 공개한다. |

---

<br>

## 점검 문제

1. **오버로딩된 메서드의 호출:** 3.3절의 `MethodSample`에서 `a.getSum(1, 2.5)`는 어느 메서드를 호출하고 결과는 무엇인가? `getSum(int, int)`와 같은 매개변수 목록으로 `double getSum(int i, int j)`를 추가하면 어떻게 되는가?

   <details>
   <summary>정답</summary>

   `getSum(int, double)` 버전은 없으므로 `int` 인자 1이 `double`로 넓혀져 `getSum(double, double)`이 호출되고, 결과는 3.5이다. `double getSum(int i, int j)`를 추가하면 기존 `int getSum(int i, int j)`와 매개변수 목록이 같고 반환 타입만 다르므로 `method getSum(int,int) is already defined` 컴파일 오류가 난다.

   </details>

2. **가비지:** 5절의 `GarbageEx`에서 `main`의 마지막 문장까지 실행했을 때 가비지인 객체는 무엇인가? `c = null;` 다음에 `d = null;`을 추가하면 어떻게 되는가?

   <details>
   <summary>정답</summary>

   `"Good"` 객체만 가비지이다. `"Bad"`는 `b`가, `"Normal"`은 `d`가 가리킨다. `d = null;`을 추가하면 `"Normal"`을 가리키는 참조도 사라지므로 `"Normal"` 객체도 가비지가 된다.

   </details>

3. **객체 반환:** 1절에서 `f1`이 24/36일 때 `f2 = f1.simplify();`를 실행한 뒤 `Fraction` 객체는 몇 개이고, `f1 == f2`의 값은 무엇인가?

   <details>
   <summary>정답</summary>

   `Fraction` 객체는 24/36인 원래 객체와 `simplify` 안에서 새로 만든 2/3 객체의 두 개이다. `f1`과 `f2`는 서로 다른 객체를 가리키므로 `f1 == f2`는 `false`이다.

   </details>

4. **생성자 체이닝:** 2.2절의 `Bicycle`(매개변수 없는 생성자가 `this("Unknown")`을 호출)을 상속하는 `ElectricBicycle`의 생성자가 `public ElectricBicycle(int battery) { batteryLevel = battery; }`이다. `new ElectricBicycle(80).getOwnerName()`은 무엇을 반환하는가? `Bicycle`에 `Bicycle(String)` 생성자만 있다면 어떻게 되는가?

   <details>
   <summary>정답</summary>

   `"Unknown"`을 반환한다. `super(...)`를 생략했으므로 컴파일러가 `super()`를 넣고, `Bicycle()`이 다시 `this("Unknown")`으로 `Bicycle(String)`을 호출한다. `Bicycle(String)`만 있다면 암묵적인 `super()`에 맞는 생성자가 없으므로 컴파일 오류가 난다.

   </details>

5. **this()의 위치:** Java 24에서 `public Bicycle() { System.out.println("x"); this("Unknown"); }`가 컴파일되지 않는 이유는 무엇인가?

   <details>
   <summary>정답</summary>

   Java 24까지는 `this()` 호출이 생성자의 첫 문장이어야 하는데, 그 앞에 `System.out.println` 문이 있기 때문이다. `this("Unknown");`을 첫 문장으로 옮기거나, 출력문을 `Bicycle(String)` 생성자 안으로 옮기면 된다.

   </details>

6. **오버로딩 선택:** 다음 각 줄이 출력하는 내용을 쓰라.

   ```java
   char[] cs = {'o', 'k'};
   System.out.println(cs);
   System.out.println("cs=" + String.valueOf(cs));
   System.out.println('o' + 1);
   ```

   <details>
   <summary>정답</summary>

   첫 줄은 `println(char[])`가 선택되어 `ok`이다. 둘째 줄은 `String.valueOf(char[])`가 문자들로 문자열을 만들므로 `cs=ok`이다. 셋째 줄은 `'o'`(111)가 `int`로 승격되어 112가 되고 `println(int)`가 호출되므로 `112`이다.

   </details>

7. **가비지 판정:** 다음 코드가 끝났을 때 가비지인 `Node` 객체를 모두 고르라. `Node`는 `Node next;` 필드 하나를 가진 클래스이다.

   ```java
   Node a = new Node();   // N1
   Node b = new Node();   // N2
   Node c = new Node();   // N3
   a.next = b;
   b.next = c;
   c.next = b;
   b = null;
   c = null;
   a.next = null;
   ```

   <details>
   <summary>정답</summary>

   N2와 N3이 가비지이다. 마지막 대입 전까지는 `a`에서 N1, N1의 `next`로 N2, N2의 `next`로 N3에 도달할 수 있었다. `a.next = null;` 뒤에는 출발점인 지역 변수 중 `a`만 N1을 가리키고, N2와 N3은 서로를 가리키지만 어느 출발점에서도 도달할 수 없다.

   </details>

8. **패키지와 실행:** `src/edu/school/Grade.java`(`package edu.school;`)와 `src/edu/school/app/Report.java`(`package edu.school.app;`, `main` 포함)를 `out` 디렉터리로 컴파일하고 실행하는 명령을 쓰라. `Report`가 `Grade`를 쓰려면 무엇이 필요한가?

   <details>
   <summary>정답</summary>

   `javac -d out src/edu/school/Grade.java src/edu/school/app/Report.java`로 컴파일하면 `out/edu/school/Grade.class`와 `out/edu/school/app/Report.class`가 생긴다. 실행은 `java -cp out edu.school.app.Report`이다. `edu.school.app`은 `edu.school`의 하위 패키지가 아니라 별개의 패키지이므로, `Report.java`에 `import edu.school.Grade;`를 쓰고 `Grade`와 `Report`가 쓰는 멤버는 `public`이어야 한다.

   </details>

---

<br>

## 참고 문헌

1. A. Cobbs and G. Bierman, "JEP 513: Flexible Constructor Bodies," OpenJDK, 2024. [Online]. Available: https://openjdk.org/jeps/513
2. J. Gosling, B. Joy, G. Steele, G. Bracha, A. Buckley, D. Smith, and G. Bierman, *The Java Language Specification, Java SE 21 Edition*. Oracle, 2023. [Online]. Available: https://docs.oracle.com/javase/specs/jls/se21/html/index.html
3. Oracle, "Object (Java SE 21 & JDK 21)," *Java SE 21 API Specification*. [Online]. Available: https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/Object.html
4. Oracle, "Naming a Package," *The Java Tutorials*. [Online]. Available: https://docs.oracle.com/javase/tutorial/java/package/namingpkgs.html
5. Oracle, "Managing Source and Class Files," *The Java Tutorials*. [Online]. Available: https://docs.oracle.com/javase/tutorial/java/package/managingfiles.html
