# HW3 Assignment Handout — Threads and Networking

[Repository Guide](../../../README.md) | [English](Assignment.md) | [한국어](Assignment.ko.md)

> **Last Updated:** 2026-11-16

This is the third assignment (HW#3) of Object-Oriented Programming and Practice, given in Week 14. It has five questions, Q1-Q5, worth 20 points for Q1, 30 for Q2, 30 for Q3, 10 for Q4, and 10 for Q5. This repository contains solutions to Q1 and Q3.

- **Submission:** Compress the report (.docx) filled in with the questions and result screenshots and all the Java code (.java) you wrote into `HW3_YourID_Yourname.zip`, and submit it to Blackboard by the set time. The Java code must run cleanly when the TA runs it.
- **Common rules:** Every program must have Javadoc comments and follow the standard naming rules. In addition, the program must never crash because of incorrect user input. Each failure costs 1 point. In principle, one Java file (.java) contains one class, and writing unnecessary nested classes also costs 1 point.
- **Partial credit:** There is partial credit, so try as far as you can even if you cannot solve a question completely.
- **ChatGPT:** To prevent abuse of ChatGPT, pattern recognition for the issues is left to the developer in most questions. Raising an issue, recognizing patterns, and segmenting requirements are where ChatGPT still cannot beat a human developer.
- **Distributed file:** Q1 and Q2 read the DNA sequence file `Q1.txt` (199,723,309 bytes) distributed with the assignment. Because of its size, the file is not kept in this folder; its first 300 lines are kept as [`Q1_Sample.txt`](Q1_Sample.txt).

`Q1.txt` is a text file with no header line that consists only of the uppercase letters A, C, G, T, and N. It has 3,274,153 lines of 60 letters each (the last line has 36 letters), 196,449,156 letters in total. The first 223 lines are all N, and A, C, G, and T start from the 31st letter of line 224. `Q1_Sample.txt` copies lines 1-300 of this file as they are, 18,000 letters in total.

---

<br>

## Background of Q1-Q2

Following COVID-19 in 2020, avian influenza, which can be transmitted to humans, is expected to become another infectious disease that causes a crisis in human society. One of the areas where students majoring in computer science can contribute to this problem is vaccine development through computational methods. Follow the guide below to explore the latest technologies you can contribute to (<https://www.who.int/emergencies/disease-outbreak-news/item/2024-DON524>). The handout also shows a news screen reporting that the WHO announced the first human infection with the H5N2 avian influenza virus.

---

<br>

## Q1. Frequencies of Five-Letter Combinations in a DNA Sequence (20 points)

One of the most fundamental things in vaccine development is discovering fragment sequences that can bind to the DNA sequence of a specific species. `Q1.txt` contains a small excerpt of the sequence to be searched. Based on it, develop a program that finds the frequency of each five-letter combination XXXXX. Since it is a DNA sequence, only "A", "C", "G", or "T" is considered for each X; that is, there are 1,024 cases in total from AAAAA to TTTTT, as in the example diagram below. Develop a program that prints the frequencies of all $`4^5 = 1024`$ DNA sequences using a sliding window algorithm that moves one letter at a time, as shown in orange in the example below. Save the final results in `Q1_output.txt` and submit it together with your final exam answers. Also, be sure to display the elapsed time of the computation in the command window. The "N" in the given file can be ignored (if implemented properly, it is ignored naturally).

```text
[Query]
AGAGAGAT
AGAGA
 GAGAG
  AGAGA
   GAGAT

[Outcome]                 -> Q1_output.txt
AAAAA: 0
AAAAC: 0
AAAAG: 0
AAAAT: 0
...
AGAGA: 2
...
GAGAG: 1
GAGAT: 1
...
TTTTT: 0
```

*Figure 1. Five-letter windows moving one letter at a time over the sequence AGAGAGAT, and the output format of the 1,024 cases*

The report includes a result screenshot that shows the elapsed time.

---

<br>

## Q2. Speeding Up the Computation with Parallel Processing (30 points)

`Q1.txt` is a toy example smaller than 1/100,000,000,000 of the scale of the actual problem. Therefore, to actually perform this computation (so that it completes before you die), parallel processing is a must. The computation speed of the program developed in Q1 can be improved through various methods of parallel processing. Develop the optimal software Q2, considering that the TA's grading computer has 16 cores. This question is graded from first to last in order of computation speed (first place = 30 points). The report includes a result screenshot that shows the self-measured elapsed time.

---

<br>

## Q3. A Client-Server Program for Joint Calculation (30 points)

Develop a client-server program as shown below. Build an environment where N users can access the server and calculate jointly. Assuming that the cumulative total variable on the server side is of type int, when a user causes an overflow, inform all users who caused it and terminate the connection. As the screens below show, be sure to include exception handling, multithreading support, query time display on the server side, and guidance conditions.

In the example diagram, users 1, 2, 3, ..., N connect to one server. The screen of user 1 is as follows.

```text
#### Welcome to the Real-time calculator ####
Enter one operator and one real number (e.g. +10)
User 1) /0
You can't divided by zero, please try it again!
User 1) +10
The server successfully accepted your query. No overflow occurred.
User 1) *10
The server successfully accepted your query. No overflow occurred.
```

The screen of user N is as follows.

```text
#### Welcome to the Real-time calculator ####
Enter one operator and one real number (e.g. +10)
User N) -100
The server successfully accepted your query. No overflow occurred.
User N) *100000
The server successfully accepted your query. No overflow occurred.
User N) *100000000000
**User N** caused an overflow on this server.
```

The screen of the server is as follows.

```text
[Server UI]
Current value is 0. 2024/06/19 16:20:48

[User 1] entered a value (/0). 2024/06/19 16:20:58
Current value is 0.

[User 1] entered a value (+10). 2024/06/19 16:21:02
Current value is 10.

[User N] entered a value (*100). 2024/06/19 16:22:04
Current value is 1000.

[User 1] entered a value (*10). 2024/06/19 16:23:14
Current value is 10000.

[User N] entered a value (*100000). 2024/06/19 16:24:24
Current value is 1000000000.

[User N] entered a value (*100000000000000). 2024/06/19 16:25:00
Overflow!
```

---

<br>

## Q4. A System Program That Calculates Date Differences (10 points)

Develop a system program that calculates the difference in days from a given date as follows. Calculations must be possible for all points in time, past and future; print D+ for future points and D- for past points. Since it is a system program, receiving the date through a separate UI is treated as a wrong answer.

```text
C:\Users\Kim\eclipse-workspace\DdayCal\src>java DdayCal 2024/08/04
Today: 2024/04/21
Your input date: 2024/08/04
================================
D+105
```

---

<br>

## Q5. A Calculator That Computes on the Server (10 points)

Implement a simple calculator based on the server-client model in which all calculations are performed on the server, as shown below. The input from the buttons pressed on the user screen is sent to the server, and the result computed by the server returns to the display of the user screen.

```text
┌─────────────────────────┐
│ 계산기                  │
├─────────────────────────┤
│                       0 │
├─────┬─────┬─────┬───────┤
│  7  │  8  │  9  │   +   │
├─────┼─────┼─────┼───────┤
│  4  │  5  │  6  │   -   │
├─────┼─────┼─────┼───────┤
│  1  │  2  │  3  │   *   │
├─────┼─────┼─────┼───────┤
│  0  │ AC  │  =  │   /   │
└─────┴─────┴─────┴───────┘
```

*Figure 2. User screen (calculator window) of the example and its button layout*

---

<br>

After solving the problems, see the [assignment explanation](../Assignment-Explanation.md) for the solutions and explanations of Q1 and Q3.
