# 8강 — 고급 클래스 II

[레포지토리 안내](../../README.ko.md) | [English](Concepts_Lecture.md) | [한국어](Concepts_Lecture.ko.md)

> **최종 수정일:** 2026-10-26

> **학습 목표:**
> 1. 메서드에서 객체를 반환하는 과정과 예약어 this의 쓰임을 설명할 수 있다
> 2. 오버로딩된 메서드와 생성자를 정의하고 this()로 생성자를 연결할 수 있다
> 3. 가비지 컬렉션이 객체를 회수하는 조건을 설명할 수 있다
> 4. 클래스를 패키지로 구성하고 javadoc 주석으로 문서화할 수 있다
> 5. extends와 super()로 상위 클래스를 확장하는 하위 클래스를 정의할 수 있다

---

## 목차

- [1. 메서드에서 객체 반환](#1-메서드에서-객체-반환)
- [2. 예약어 this](#2-예약어-this)
  - [2.1 자기 참조 this](#21-자기-참조-this)
  - [2.2 this()를 이용한 생성자 호출](#22-this를-이용한-생성자-호출)
- [3. 메서드와 생성자 오버로딩](#3-메서드와-생성자-오버로딩)
  - [3.1 오버로딩 규칙](#31-오버로딩-규칙)
  - [3.2 반환 타입과 오버로딩](#32-반환-타입과-오버로딩)
  - [3.3 오버로딩된 메서드의 호출](#33-오버로딩된-메서드의-호출)
- [4. Dice 클래스: 실용 예제](#4-dice-클래스-실용-예제)
- [5. 객체 소멸과 가비지 컬렉션](#5-객체-소멸과-가비지-컬렉션)
- [6. 접근 제어자와 패키지](#6-접근-제어자와-패키지)
  - [6.1 접근 제어자 복습](#61-접근-제어자-복습)
  - [6.2 패키지](#62-패키지)
- [7. javadoc 주석](#7-javadoc-주석)
- [8. 상속의 기초](#8-상속의-기초)
  - [8.1 extends 키워드](#81-extends-키워드)
  - [8.2 상위 클래스와 하위 클래스의 관계](#82-상위-클래스와-하위-클래스의-관계)
- [9. super()를 이용한 생성자 체이닝](#9-super를-이용한-생성자-체이닝)
- [10. 클래스 설계 모범 사례](#10-클래스-설계-모범-사례)
- [요약](#요약)
- [점검 문제](#점검-문제)

---

<br>

## 1. 메서드에서 객체 반환

메서드는 기본형 값을 반환하듯 객체도 반환할 수 있다. 객체를 반환하면 실제로는 객체의 참조(주소)를 반환하므로, 객체의 복사본이 아니라 그 객체를 가리키는 참조만 호출자에게 전달된다. 다음 `simplify`는 분수를 기약분수로 만든 새 `Fraction` 객체를 반환한다.

```java
public Fraction simplify() {      // 반환 타입: 반환할 객체의 클래스

    Fraction simp;

    int num   = getNumerator();
    int denom = getDenominator();
    int gcd   = gcd(num, denom);

    simp = new Fraction(num/gcd, denom/gcd);

    return simp;                  // Fraction 클래스의 인스턴스를 반환
}
```

`f1`이 24/36일 때 `f2 = f1.simplify();`를 실행하면 메서드 안에서 2/3을 나타내는 새 `Fraction` 객체가 만들어지고 지역 변수 `simp`가 그 객체를 가리킨다. 메서드가 끝나면 `simp`의 값, 즉 참조가 호출자에게 반환되어 `f2`에 대입되고, `simp`가 차지하던 메모리는 해제된다.

```text
(1) return 직전
f1 ──▶ ┌───────────────┐      simp ──▶ ┌───────────────┐
       │ :Fraction     │               │ :Fraction     │
       │ numerator   24│               │ numerator   2 │
       │ denominator 36│               │ denominator 3 │
       └───────────────┘               └───────────────┘

(2) simplify 종료 후
f1 ──▶ ┌───────────────┐        f2 ──▶ ┌───────────────┐
       │ :Fraction     │               │ :Fraction     │
       │ numerator   24│               │ numerator   2 │
       │ denominator 36│               │ denominator 3 │
       └───────────────┘               └───────────────┘
```

*그림 1. simplify가 반환한 Fraction 객체의 참조가 f2에 대입되는 과정*

`f1`이 가리키는 원래 객체는 바뀌지 않는다.

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
        this("Unknown");            // Bicycle(String) 호출
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

> **참고:** **Java 버전과 첫 문장 규칙.** `this()`와 `super()`(9절)를 생성자의 첫 문장에 두어야 한다는 규칙은 Java 24까지의 규칙이다. Java 25부터는 생성 중인 객체를 사용하지 않는 문장(인자 검사 등)을 그 앞에 둘 수 있다. Java 24에서 `this()` 앞에 문장을 두면 `flexible constructors is a preview feature` 오류가 난다.

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

생성자가 오버로딩되어 있어 `new Dice()`는 6면체, `new Dice(20)`은 20면체 주사위를 만들며, 매개변수 없는 생성자는 `this(DEFAULT_FACES)`로 초기화를 위임한다. `random.nextInt(faces)`는 0 이상 `faces` 미만의 정수를 반환하므로 `roll()`은 1부터 `faces`까지의 값을 낸다. 필드가 모두 `final`이라 면의 수는 생성 뒤에 바뀌지 않는다. `faces`가 0 이하이면 `nextInt`가 `IllegalArgumentException`을 던지므로, 실제로는 생성자에서 값을 검사하는 편이 안전하다.

---

<br>

## 5. 객체 소멸과 가비지 컬렉션

> **용어 정의:** **객체 소멸**(object destruction)은 `new`로 할당한 객체의 메모리를 Java 가상 기계(JVM)의 가용 메모리로 되돌리는 일이며, JVM만 할 수 있다. **가비지**(garbage)는 자신을 가리키는 참조가 하나도 없는 객체이다. **가비지 컬렉션**(garbage collection)은 JVM의 가비지 컬렉터가 가비지를 자동으로 찾아 가용 메모리로 되돌리는 과정이다.

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
(1) 대입문 실행 전
a ──▶ "Good"
b ──▶ "Bad"
c ──▶ "Normal"
d
e

(2) a = null; d = c; c = null; 실행 후
a     null       "Good"
b ──▶ "Bad"
c     null   ┌─▶ "Normal"
d ───────────┘
e
```

*그림 2. 참조 변경에 따라 `"Good"` 객체가 가비지가 되는 과정*

`a = null;`로 `"Good"` 객체를 가리키는 참조가 사라지므로 이 객체는 가비지가 된다. `"Normal"` 객체는 `c = null;` 뒤에도 `d`가 가리키므로 가비지가 아니다. `d`와 `e`는 선언만 되었고, `d`는 `d = c;`에서 처음 값을 받는다.

개발자는 `System.gc()`나 `Runtime.getRuntime().gc()`를 호출하여 가비지 컬렉션을 요청할 수 있다. 그러나 이는 요청일 뿐이며, 실제로 수행할지는 JVM이 결정한다.

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
        super(ownerName);         // Bicycle의 생성자 호출
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

*표 1. 클래스 설계의 네 가지 원칙*

**합성**(composition)은 다른 클래스의 객체를 필드로 가지는 has-a 관계이다. 예를 들어 `Dice`는 `Random` 객체를 필드로 가지고 무작위 수 생성을 맡긴다. 상속(is-a)은 하위 클래스를 상위 클래스의 구현에 묶으므로, 하위 클래스가 상위 클래스의 일종이라고 말할 수 있을 때만 쓴다.

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
