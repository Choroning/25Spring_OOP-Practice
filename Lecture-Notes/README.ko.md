# 강의 노트

[레포지토리 안내](../README.ko.md) | [English](README.md) | [한국어](README.ko.md)

> **최종 수정일:** 2026-10-26

| 단원 | 주제 | 문서 |
|:-----|:-----|:-----|
| [1강 객체 지향 프로그래밍 개론](Lec01_Introduction-to-OOP/) | 프로그래밍 패러다임과 OOP의 4대 핵심 원칙, Java의 역사와 설계 목표, Java 플랫폼 아키텍처와 JDK, JRE, JVM, 컴파일과 인터프리팅, Write Once, Run Anywhere, 개발 환경 설정, 첫 번째 Java 프로그램 | [강의 노트](Lec01_Introduction-to-OOP/Concepts_Lecture.ko.md) |
| [2강 객체와 클래스](Lec02_Objects-and-Classes/) | Java의 객체와 클래스와 그 관계, UML 표기, 메시지와 메서드, 클래스 데이터 값과 인스턴스 데이터 값, 상속, 소프트웨어 공학과 소프트웨어 생명 주기, 객체의 선언, 인스턴스화와 메모리 상태, 참조 변수와 메시지 보내기, 프로그램 구조, 주석, `import` 문, 클래스 선언과 메서드 선언, `JFrame`, `JOptionPane`, 문자열 조작, `Date`와 `SimpleDateFormat`, 모노그램 프로그램의 점진적 개발 | [강의 노트](Lec02_Objects-and-Classes/Concepts_Lecture.ko.md) |
| [3강 Java 기본 문법](Lec03_Java-Basic-Grammar/) | 식별자 규칙, Java 키워드, 이름 짓기 관례, 기본 자료형의 종류와 범위, 기본형과 참조형, 리터럴, `var` 키워드, 변수와 상수, 산술, 대입, 증감, 관계 및 논리 연산자, 연산자 우선순위, `+` 연산자의 두 가지 의미, 형변환, 표준 출력과 `DecimalFormat`, `Scanner`를 이용한 콘솔 입력과 `JOptionPane`을 이용한 대화 상자 입출력, 문자열 연산, 초를 시, 분, 초로 바꾸는 예제 | [강의 노트](Lec03_Java-Basic-Grammar/Concepts_Lecture.ko.md) |
| [4강 제어 구조](Lec04_Control-Structures/) | 삼항 연산자와 관계 및 논리 연산자, `if-else`, 중첩 `if-else`, `switch` 문, `while`, `do-while`, `for`, 향상된 `for` 루프, 루프 제어문, 실행 시간 측정, `JOptionPane`을 활용한 GUI 입력, 출력 서식 지정(`Formatter`, `format`) | [강의 노트](Lec04_Control-Structures/Concepts_Lecture.ko.md) |
| [5강 배열](Lec05_Arrays/) | 배열의 선언과 초기화, 배열 접근과 길이, 기본값, 최댓값과 최솟값 탐색, 평균 계산, 배열 순회 패턴, 메서드 매개변수로서의 배열, 흔한 실수, `Arrays` 유틸리티 클래스 | [강의 노트](Lec05_Arrays/Concepts_Lecture.ko.md) |
| [6강 2차원 배열, 함수, 예외 처리](Lec06_2D-Arrays-Functions-and-Exception-Handling/) | 2차원 배열의 선언, 초기화, 순회와 가변 길이 배열, 사용자 정의 메서드의 정의, 메서드 오버로딩, 값에 의한 전달과 참조에 의한 전달, `try-catch-finally`, 주요 예외 유형, 예외를 활용한 입력 검증 | [강의 노트](Lec06_2D-Arrays-Functions-and-Exception-Handling/Concepts_Lecture.ko.md) |
| [7강 고급 클래스 I](Lec07_Advanced-Classes-I/) | 클래스 구조와 객체 생성, 소스 파일과 프로그램 구성, 클래스 다이어그램, 기본 생성자와 매개변수가 있는 생성자, 생성자 오버로딩, 인자와 매개변수의 대응, 객체 전달과 공유, 지역 변수와 식별자 결정 규칙, 정보 은닉과 접근 제어자, getter와 setter를 이용한 캡슐화, `this` 키워드, `static` 필드와 메서드, `final`과 불변 클래스, 여러 클래스의 협력 | [강의 노트](Lec07_Advanced-Classes-I/Concepts_Lecture.ko.md) |
| [8강 고급 클래스 II](Lec08_Advanced-Classes-II/) | 메서드에서 객체 반환, 자기 참조 `this`와 `this()`를 이용한 생성자 호출, 메서드와 생성자 오버로딩의 규칙과 반환 타입, `Dice` 클래스 예제, 객체 소멸과 가비지 컬렉션, 접근 제어자와 패키지, javadoc 주석, `extends` 키워드와 상위 클래스와 하위 클래스의 관계, `super()`를 이용한 생성자 체이닝, 클래스 설계 모범 사례 | [강의 노트](Lec08_Advanced-Classes-II/Concepts_Lecture.ko.md) |
| [9강 단언문, 제네릭, 컬렉션](Lec09_Assertions-Generics-and-Collections/) | 단언문의 문법, 사용법, 활성화, 제네릭 클래스와 제네릭 메서드, 한정적 타입 매개변수, 컬렉션 프레임워크, `ArrayList`, `HashMap`, 컬렉션 순회 | [강의 노트](Lec09_Assertions-Generics-and-Collections/Concepts_Lecture.ko.md) |
| [10강 파일 입출력](Lec10_File-IO/) | 바이트 스트림과 문자 스트림, `File` 클래스, 텍스트 파일 읽기와 쓰기, `DataInputStream`과 `DataOutputStream`, `ObjectInputStream`과 `ObjectOutputStream`, try-with-resources, `Serializable` 인터페이스 | [강의 노트](Lec10_File-IO/Concepts_Lecture.ko.md) |
| [11강 상속과 다형성](Lec11_Inheritance-and-Polymorphism/) | 상속 계층 구조, `protected` 접근 제어자, `super()`를 이용한 생성자 체이닝, 동적 바인딩, `instanceof` 연산자, 메서드 오버라이딩 규칙과 `@Override` 애너테이션, 추상 클래스와 인터페이스, 상속을 활용한 디자인 패턴 | [강의 노트](Lec11_Inheritance-and-Polymorphism/Concepts_Lecture.ko.md) |
| [12강 고급 GUI](Lec12_Advanced-GUI/) | AWT와 Swing, GUI 클래스 계층 구조, `JFrame`의 생성과 상속, 컨텐트팬, `FlowLayout`, `BorderLayout`, `GridLayout` 배치 관리자, 주요 Swing 컴포넌트, `ActionListener`와 익명 클래스, 람다를 이용한 이벤트 처리 | [강의 노트](Lec12_Advanced-GUI/Concepts_Lecture.ko.md) |
| [13강 스레딩](Lec13_Threading/) | 멀티태스킹과 스레드, 스레드 생명 주기, `Thread` 클래스 상속과 `Runnable` 인터페이스 구현의 비교, `sleep()`, `join()`, 멀티스레드 성능 | [강의 노트](Lec13_Threading/Concepts_Lecture.ko.md) |
| [14강 네트워크 프로그래밍](Lec14_Network-Programming/) | 소켓, TCP와 UDP, 소켓 쌍(4-tuple), `ServerSocket`과 `Socket`, 클라이언트-서버 상호작용 흐름, 소켓을 통한 읽기와 쓰기, 다중 클라이언트 서버 아키텍처, GUI 기반 네트워크 애플리케이션 | [강의 노트](Lec14_Network-Programming/Concepts_Lecture.ko.md) |
| [15강 고급 스레딩](Lec15_Advanced-Threading/) | 스레드 제어 블록과 스레드 상태, 스레드 우선순위와 스케줄링, 자연 종료, `interrupt()`, 플래그 기반 종료, `synchronized` 키워드, 동기화 블록과 동기화 메서드, `wait()`, `notify()`, `notifyAll()`, 경쟁 조건과 예방 | [강의 노트](Lec15_Advanced-Threading/Concepts_Lecture.ko.md) |
