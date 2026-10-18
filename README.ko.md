# [2025학년도 봄학기] 객체지향프로그래밍 연습

![Last Commit](https://img.shields.io/github/last-commit/Choroning/25Spring_OOP-Practice)
![Languages](https://img.shields.io/github/languages/top/Choroning/25Spring_OOP-Practice)

이 레포지토리는 객체지향프로그래밍및실습 과목의 이중 언어 강의 노트와 수업 결과물을 정리한 아카이브입니다.

*작성자: 박철원 (고려대학교(세종), 컴퓨터소프트웨어학과) - 2025년 기준 2학년*
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

이 레포지토리에는 대학 수준의 Java 객체지향 프로그래밍 과목을 위해 작성한 강의 노트와 Java 코드가 담겨 있습니다.

- `Lecture-Notes/`에는 단원마다 핵심 개념을 종합적으로 정리한 강의 노트(`Concepts_Lecture`)가 있습니다. 교수자가 제공한 슬라이드의 번호(Chap1\~Chap15)를 따라 단원을 `Lec01`\~`Lec15`로 나눴습니다.
- 모든 강의 노트는 영어 파일(`.md`)과 한국어 파일(`.ko.md`)이 같은 구성으로 짝을 이룹니다.
- `Coursework/`에는 강의 시연 코드를 바탕으로 단원마다 정리한 Java 실습 코드(1강과 10강을 뺀 `Lab02`\~`Lab15`), 3번의 과제 중 일부 문제의 풀이(`HW01`\~`HW03`), 텀 프로젝트가 있습니다.
- 개선된 Java 코드 파일에는 목적, 사용법, 구조를 문서화한 **Doxygen 스타일 헤더**가 있습니다.
- **텀 프로젝트**(Multiplayer Chat Application)는 해외 CS 명문 대학의 커리큘럼을 참고하여 설계했습니다. 구조, 프로토콜, 실행 방법은 프로젝트 폴더의 `README.md`에 정리되어 있습니다.
- 모든 실습 코드, 과제 풀이, 프로젝트 코드는 JDK로 컴파일하여 확인했습니다.

> **🤖 AI 에이전트 활용**
> 이 과목의 과제 안내는 ChatGPT 활용을 허용하고(1차 과제는 적극적인 활용을 권장), ChatGPT의 남용을 막기 위해 대부분의 문제에서 문제의 패턴 인식을 개발자의 몫으로 남겼습니다. 강의 노트의 정리와 번역, 레포지토리 구조 정리에는 [Claude Code](https://claude.ai/download)를 활용했습니다.

<br><a name="course-information"></a>
## 📚 강의 정보

- **학기:** 2025학년도 봄학기 (3월 - 6월)
- **소속:** 고려대학교(세종)

|학수번호      |강의명    |이수구분|교수자|개설학과|
|:----------:|:-------|:----:|:------:|:----------------|
|`DCCS213-01`|객체지향프로그래밍및실습|전공선택|서민석 교수|컴퓨터소프트웨어학과|

### 과목 개요

Java 언어로 객체 지향 프로그래밍의 핵심 개념과 Java 프로그래밍의 기초를 배우는 전공선택 과목으로, 영어로 진행된다. 실제 문제를 해결하는 고품질의 동작하는 소프트웨어를 개발하는 데 초점을 두며, 강의와 함께 퀴즈와 과제로 직접 프로그래밍하는 실습을 한다. 교수자가 제공한 슬라이드(Chap1~Chap15)를 따라 Java 개발 환경, 객체와 클래스의 개념, 기본 문법과 제어 구조, 배열과 2차원 배열, 메서드와 예외 처리, 고급 클래스 설계, 단언문, 제네릭, 컬렉션, 파일 입출력, 상속과 다형성, Swing GUI, 스레드와 동기화, 소켓 네트워크 프로그래밍을 다룬다. 교수자와 조교, 수업 일정과 형식, 평가, 수업 운영 규정, 주차별 계획 및 진도, 참고 자료는 [강의 정보 문서](Course-Information.ko.md)에 정리했다.

<br><a name="prerequisites"></a>
## ✅ 사전 요구사항

- 프로그래밍 개념에 대한 기본적인 이해가 있어야 하며, C, C++, Java, Python 같은 프로그래밍 언어 중 하나를 다룰 수 있어야 합니다.
- Java Development Kit(JDK)가 설치되어 있어야 합니다. 이 레포지토리의 코드를 컴파일하려면 JDK 19 이상이 필요합니다.
- 커맨드 라인 툴 또는 IDE 사용에 익숙해야 합니다.

- **💻 개발 환경**

| 도구 | 회사 |  운영체제  | 비고 |
|:-----|:-------:|:----:|:------|
|IntelliJ IDEA|JetBrains s.r.o.|macOS|가장 많이 사용한 IDE|
|Eclipse|Eclipse Foundation|macOS|    |

<br><a name="repository-structure"></a>
## 🗂 레포지토리 구조

```plaintext
25Spring_OOP-Practice
├── Lecture-Notes               강의별 강의 노트
├── Coursework                  강의별 Java 실습 코드, 과제 풀이, 텀 프로젝트
├── Course-Information.ko.md    수업 운영 정보
├── Course-Information.md
├── LICENSE                     문서 라이선스 (CC BY-NC-SA 4.0)
├── LICENSE-CODE                코드 라이선스 (PolyForm Noncommercial 1.0.0)
├── README.ko.md
└── README.md
```

단원 목록은 [`Lecture-Notes` 색인](Lecture-Notes/README.ko.md)에, 실습 코드, 과제 풀이, 텀 프로젝트는 [`Coursework` 색인](Coursework/README.ko.md)에 있습니다.

<br><a name="license"></a>
## 🤝 라이선스

이 레포지토리의 문서(강의 노트, 해설, 요약지)는 [CC BY-NC-SA 4.0](LICENSE), 코드는 [PolyForm Noncommercial 1.0.0](LICENSE-CODE)에 따라 배포됩니다. 강의자료와 교재에서 가져온 그림과 예제 코드, 강의가 배포한 과제 원본과 템플릿 코드는 원저작자에게 권리가 있으며 이 라이선스의 대상이 아닙니다.

---
