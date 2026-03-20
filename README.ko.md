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

- 각 챕터별 핵심 개념을 종합적으로 정리한 **Concepts.md** 파일
- 목적, 사용법, 구조를 문서화한 **Doxygen 스타일 헤더**가 포함된 개선된 Java 코드 파일
- 강의 시연 코드 및 과제 솔루션
- 해외 CS 명문 대학 커리큘럼을 참고하여 설계한 **텀 프로젝트** (Multiplayer Chat Application)

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
│   ├── Assignment
│   │   ├── Assignment1_NameGreeter.java
│   │   └── Assignment1_SentenceReverser.java
│   ├── Concepts.md
│   ├── Lab_DisplayWindow.java
│   └── Lab_MonogramDateDisplay.java
├── Chapter03_Java-Basic-Grammar
│   ├── Concepts.md
│   ├── Lab_CircleCalculator.java
│   ├── Lab_OperatorDemo.java
│   └── Lab_TimeUnitConverter.java
├── Chapter04_Control-Structures
│   ├── Assignment
│   │   ├── Assignment1_CurrencyConverter.java
│   │   ├── Assignment1_RealTimeAgeCalculator.java
│   │   └── Assignment1_ScheduleReminder.java
│   ├── Concepts.md
│   ├── Lab_ConditionalOperatorDemo.java
│   ├── Lab_ExecutionTimeMeasurement.java
│   ├── Lab_SwitchGradingSystem.java
│   └── Lab_WhileLoopValidation.java
├── Chapter05_Arrays
│   ├── Assignment
│   │   ├── Assignment1_MatrixSumCalculator.java
│   │   ├── Assignment1_NumberExtractorSum.java
│   │   ├── Assignment1_OptimalBillCounter.java
│   │   └── Assignment1_SeasonIdentifier.java
│   ├── Concepts.md
│   ├── Lab_ArrayMaxFinder.java
│   └── Lab_ArrayStatistics.java
├── Chapter06_2D-Arrays-Functions-and-Exception-Handling
│   ├── Concepts.md
│   ├── Lab_AverageGradeCalculator.java
│   ├── Lab_GridDrawer.java
│   └── Lab_InputValidationWithException.java
├── Chapter07_Advanced-Classes-I
│   ├── Concepts.md
│   ├── Lab_Account.java
│   ├── Lab_Bicycle.java
│   └── Lab_MultipleClassesDemo.java
├── Chapter08_Advanced-Classes-II
│   ├── Concepts.md
│   └── Lab_Dice.java
├── Chapter09_Assertions-Generics-and-Collections
│   ├── Assignment
│   │   ├── Assignment2_GreetingDialog.java
│   │   └── Assignment2_SortingComparison.java
│   ├── Concepts.md
│   └── Lab_AssertionDemo.java
├── Chapter10_File-IO
│   ├── Assignment
│   │   └── Assignment2_BinaryFileDecryptor.java
│   └── Concepts.md
├── Chapter11_Inheritance-and-Polymorphism
│   ├── Concepts.md
│   ├── Lab_InheritanceDemo.java
│   └── Lab_PersonStudentDemo.java
├── Chapter12_Advanced-GUI
│   ├── Concepts.md
│   ├── Lab_BasicSwingFrame.java
│   └── Lab_FlowLayoutDemo.java
├── Chapter13_Threading
│   ├── Assignment
│   │   └── Assignment3_DnaFiveMerCounter.java
│   ├── Concepts.md
│   ├── Lab_MultiThreadedSum.java
│   ├── Lab_TimerRunnableDemo.java
│   └── Lab_TimerThreadDemo.java
├── Chapter14_Network-Programming
│   ├── Assignment
│   │   ├── Assignment3_CalculatorClient.java
│   │   └── Assignment3_CalculatorServer.java
│   ├── Concepts.md
│   ├── Lab_SimpleChatClient.java
│   └── Lab_SimpleChatServer.java
├── Chapter15_Advanced-Threading
│   ├── Concepts.md
│   ├── Lab_MainThreadInfoDemo.java
│   └── Lab_SynchronizationDemo.java
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

25개의 디렉토리, 72개의 파일
```

<br><a name="license"></a>
## 🤝 라이선스

이 레포지토리는 [MIT License](LICENSE) 하에 배포됩니다.

---
