# [Spring 2025] Object-Oriented Programming Practice

![Last Commit](https://img.shields.io/github/last-commit/Choroning/25Spring_OOP-Practice)
![Languages](https://img.shields.io/github/languages/top/Choroning/25Spring_OOP-Practice)

This repository archives bilingual lecture notes and coursework for the Object-Oriented Programming and Practice course.

*Author: Cheolwon Park (Korea University Sejong, CSE) - Year 2 (Sophomore) as of 2025*
<br><br>

## 📑 Table of Contents

- [About This Repository](#about-this-repository)
- [Course Information](#course-information)
- [Prerequisites](#prerequisites)
- [Repository Structure](#repository-structure)
- [License](#license)

---


<br><a name="about-this-repository"></a>
## 📝 About This Repository

This repository contains the lecture notes and Java code written for a university-level Object-Oriented Programming course in Java.

- `Lecture-Notes/` holds, for each unit, lecture notes with comprehensive explanations of the key concepts (`Concepts_Lecture`). The units follow the numbers of the instructor's slides (Chap1-Chap15) and are numbered `Lec01`-`Lec15`.
- Every lecture note comes as an English file (`.md`) and a Korean file (`.ko.md`) with the same structure.
- `Coursework/` holds Java lab code that organizes the lecture demonstration code by unit for every lecture except Lectures 1 and 10 (`Lab02`-`Lab15`), solutions to some problems of the three homework assignments (`HW01`-`HW03`), and the term project.
- The improved Java code files carry **Doxygen-style headers** that document their purpose, usage, and structure.
- The **term project** (Multiplayer Chat Application) was designed with reference to the curricula of leading overseas CS universities. Its architecture, protocol, and instructions for running it are described in the `README.md` of the project folder.
- All lab code, homework solutions, and project code were compiled with the JDK.

> **🤖 AI Agent Usage**
> The homework instructions of this course allow the use of ChatGPT (Homework 1 encourages its active use) and leave the pattern recognition for the issues to the developer in most problems to prevent its abuse. [Claude Code](https://claude.ai/download) was used to organize and translate the lecture notes and to restructure the repository.

<br><a name="course-information"></a>
## 📚 Course Information

- **Semester:** Spring 2025 (March - June)
- **Affiliation:** Korea University Sejong

| Course&nbsp;Code| Course            | Type          | Instructor      | Department                              |
|:----------:|:------------------|:-------------:|:---------------:|:----------------------------------------|
|`DCCS213-01`|OBJECT-ORIENTED PROGRAMMING AND PRACTICE|Major Elective|Prof. Minseok&nbsp;Seo|Department of Computer Science and Software Engineering|

### Course Overview

A major elective course, taught in English, on the core concepts of object-oriented programming and the fundamentals of Java programming in the Java language. It focuses on developing high-quality, working software that solves real problems, and alongside the lectures, students practice programming themselves through quizzes and homework. Following the instructor's slides (Chap1-Chap15), it covers the Java development environment, the concepts of objects and classes, basic grammar and control structures, arrays and two-dimensional arrays, methods and exception handling, advanced class design, assertions, generics, collections, file I/O, inheritance and polymorphism, Swing GUI, threads and synchronization, and socket network programming. The instructor and teaching assistant, schedule and class format, assessment, course policies, weekly plan and progress, and references are in the [course information document](Course-Information.md).

<br><a name="prerequisites"></a>
## ✅ Prerequisites

- A basic understanding of programming concepts is required, along with the ability to use a programming language such as C, C++, Java, or Python.
- The Java Development Kit (JDK) must be installed. Compiling the code in this repository requires JDK 19 or later.
- Familiarity with command-line tools or an IDE is required.

- **💻 Development Environment**

| Tool | Company |  OS  | Notes |
|:-----|:-------:|:----:|:------|
|IntelliJ IDEA|JetBrains s.r.o.|macOS|Most used IDE|
|Eclipse|Eclipse Foundation|macOS|    |

<br><a name="repository-structure"></a>
## 🗂 Repository Structure

```plaintext
25Spring_OOP-Practice
├── Lecture-Notes               Lecture notes by lecture
├── Coursework                  Java lab code by lecture, homework solutions, and term project
├── Course-Information.ko.md    Course operations
├── Course-Information.md
├── LICENSE                     Documentation license (CC BY-NC-SA 4.0)
├── LICENSE-CODE                Code license (PolyForm Noncommercial 1.0.0)
├── README.ko.md
└── README.md
```

The units are listed in the [`Lecture-Notes` index](Lecture-Notes/README.md), and the lab code, homework solutions, and term project in the [`Coursework` index](Coursework/README.md).

<br><a name="license"></a>
## 🤝 License

The documents in this repository (lecture notes, explanations, and summary sheets) are licensed under [CC BY-NC-SA 4.0](LICENSE), and the code under [PolyForm Noncommercial 1.0.0](LICENSE-CODE). Figures and example code taken from lecture slides and textbooks, as well as assignment handouts and template code distributed by the course, belong to their original authors and are not covered by these licenses.

---
