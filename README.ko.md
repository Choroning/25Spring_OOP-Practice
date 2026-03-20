# [2025학년도 봄학기] 객체지향프로그래밍 연습

![Last Commit](https://img.shields.io/github/last-commit/Choroning/25Spring_OOP-Practice)
![Languages](https://img.shields.io/github/languages/top/Choroning/25Spring_OOP-Practice)

이 레포지토리는 대학 강의 및 과제를 위해 작성된 Java 예제 및 실습 코드를 체계적으로 정리하고 보관합니다.

*작성자: 박철원 (고려대학교(세종), 컴퓨터융합소프트웨어학과) - 2025년 기준 2학년*
<br><br>

## 📑 목차

- [레포지토리 소개](#about-this-repository)
- [강의 정보](#course-information)
- [사전 요구사항](#prerequisites)
- [레포지토리 구조](#repository-structure)
- [라이선스](#license)

---


<br><a name="about-this-repository"></a>
## 📝 레포지토리 소개

이 레포지토리에는 대학 수준의 Java 객체지향 프로그래밍 과목을 위해 작성된 코드가 포함되어 있습니다:

- 강의에서 소개한 코드
- 각 과제의 솔루션
- 추가적인 연습 문제

<br><a name="course-information"></a>
## 📚 강의 정보

- **학기:** 2025학년도 봄학기 (3월 - 6월)
- **소속:** 고려대학교(세종)

|학수번호      |강의명    |이수구분|교수자|개설학과|
|:----------:|:-------|:----:|:------:|:----------------|
|`DCCS213-01`|객체지향프로그래밍및실습|전공선택|서민석 교수|컴퓨터융합소프트웨어학과|

- **📖 참고 자료**

| 유형 | 내용 |
|:----:|:---------|
|강의자료|교수자 제공 슬라이드|
|온라인|[MIT OCW – Introduction to Programming in Java](https://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-092-introduction-to-programming-in-java-january-iap-2010/lecture-notes/)|
|온라인|[MOOC.fi – Programming Part 1](https://moocfi.github.io/courses/2013/programming-part-1/)|
|온라인|[Chortle – Introduction to Computer Science using Java](https://chortle.ccsu.edu/CS151/cs151java.html)|

<br><a name="prerequisites"></a>
## ✅ 사전 요구사항

- 프로그래밍 개념에 대한 기본적인 이해
- Java Development Kit (JDK) 설치
- 커맨드 라인 툴 또는 IDE 사용에 익숙함

- **💻 개발 환경**

| 도구 | 회사 |  운영체제  | 비고 |
|:-----|:-------:|:----:|:------|
|IntelliJ IDEA|JetBrains s.r.o.|macOS|가장 많이 사용한 IDE|
|Eclipse|Eclipse Foundation|macOS|    |

<br><a name="repository-structure"></a>
## 🗂 레포지토리 구조

```plaintext
25Spring_OOP-Practice
├── Chapter01_Introduction-to-OOP
│   └── Concepts.md
├── Chapter02_Objects-and-Classes
│   ├── Concepts.md
│   ├── DisplayWindow.java
│   ├── HW1_NameGreeter.java
│   ├── HW1_SentenceReverser.java
│   └── MonogramDateDisplay.java
├── Chapter03_Java-Basic-Grammar
│   ├── CircleCalculator.java
│   ├── Concepts.md
│   ├── OperatorDemo.java
│   └── TimeUnitConverter.java
├── Chapter04_Control-Structures
│   ├── Concepts.md
│   ├── ConditionalOperatorDemo.java
│   ├── ExecutionTimeMeasurement.java
│   ├── HW1_CurrencyConverter.java
│   ├── HW1_RealTimeAgeCalculator.java
│   ├── HW1_ScheduleReminder.java
│   ├── SwitchGradingSystem.java
│   └── WhileLoopValidation.java
├── Chapter05_Arrays
│   ├── ArrayMaxFinder.java
│   ├── ArrayStatistics.java
│   ├── Concepts.md
│   ├── HW1_MatrixSumCalculator.java
│   ├── HW1_NumberExtractorSum.java
│   ├── HW1_OptimalBillCounter.java
│   └── HW1_SeasonIdentifier.java
├── Chapter06_2D-Arrays-Functions-and-Exception-Handling
│   ├── AverageGradeCalculator.java
│   ├── Concepts.md
│   ├── GridDrawer.java
│   └── InputValidationWithException.java
├── Chapter07_Advanced-Classes-I
│   ├── Account.java
│   ├── Bicycle.java
│   ├── Concepts.md
│   └── MultipleClassesDemo.java
├── Chapter08_Advanced-Classes-II
│   ├── Concepts.md
│   └── Dice.java
├── Chapter09_Assertions-Generics-and-Collections
│   ├── AssertionDemo.java
│   ├── Concepts.md
│   ├── HW2_GreetingDialog.java
│   └── HW2_SortingComparison.java
├── Chapter10_File-IO
│   ├── Concepts.md
│   └── HW2_BinaryFileDecryptor.java
├── Chapter11_Inheritance-and-Polymorphism
│   ├── Concepts.md
│   ├── InheritanceDemo.java
│   └── PersonStudentDemo.java
├── Chapter12_Advanced-GUI
│   ├── BasicSwingFrame.java
│   ├── Concepts.md
│   └── FlowLayoutDemo.java
├── Chapter13_Threading
│   ├── Concepts.md
│   ├── HW3_DnaFiveMerCounter.java
│   ├── MultiThreadedSum.java
│   ├── TimerRunnableDemo.java
│   └── TimerThreadDemo.java
├── Chapter14_Network-Programming
│   ├── Concepts.md
│   ├── HW3_CalculatorClient.java
│   ├── HW3_CalculatorServer.java
│   ├── SimpleChatClient.java
│   └── SimpleChatServer.java
├── Chapter15_Advanced-Threading
│   ├── Concepts.md
│   ├── MainThreadInfoDemo.java
│   └── SynchronizationDemo.java
├── Project_Multiplayer-Chat-Application
│   ├── ChatClient.java
│   ├── ChatGUI.java
│   ├── ChatProtocol.java
│   ├── ChatRoom.java
│   ├── ChatServer.java
│   ├── MessageHandler.java
│   ├── README.md
│   └── UserManager.java
├── images
├── LICENSE
├── README.ko.md
└── README.md

17개의 디렉토리, 63개의 파일
```

<br><a name="license"></a>
## 🤝 라이선스

이 레포지토리는 [Apache License 2.0](LICENSE) 하에 배포됩니다.

---
