# HW1 Assignment Handout — Java Basics and Arrays

[Repository Guide](../../../README.md) | [English](Assignment.md) | [한국어](Assignment.ko.md)

> **Last Updated:** 2026-11-16

This is the first assignment (HW#1) of Object-Oriented Programming and Practice, given in Week 4. It has ten questions, Q1-Q10: read each question, write the program, and paste the result screens into the report. This repository contains solutions to Q1-Q9.

- **Submission:** Compress the report (.docx) filled in with the questions and result screenshots and all the Java code (.java) you wrote into `HW1_YourID_Yourname.zip`, and submit it to Blackboard by the set time. The Java code must run cleanly when the TA runs it.
- **Common rules:** Every program must have a header comment and follow the standard naming rules; each failure costs 1 point.
- **ChatGPT:** To prevent abuse of ChatGPT, pattern recognition for the issues is left to the developer in most questions. Raising an issue, recognizing patterns, and segmenting requirements are where ChatGPT still cannot beat a human developer, and now that everyone can use ChatGPT, who can divide a problem efficiently and ask first also matters. For this assignment, use ChatGPT actively.
- **Plagiarism:** Copying another student's code results in an F grade.

---

<br>

## Q1. Reversing Each Word

Write a program `Q1.java` that receives an English sentence and prints it reversed word by word, as in the example below. Look for the case rules in the example and be careful to get the same result.

Dialog prompt and input:

```text
Please write any English sentence:
Hello World! Welcome to the OOP
```

Output:

```text
Olleh !dlrow Emoclew Ot Eht Poo
```

---

<br>

## Q2. Greeting by Name and the Sum of the Student ID Digits

Implement `Q2.java`, which receives a name and a student ID number from a dialog and outputs them as in the example below.

1. The entered name and student ID number must be separated automatically.
2. The digits of the number must be summed cumulatively with a loop. For example, if the ID is "12345", print the result of "1 + 2 + 3 + 4 + 5".

Dialog prompt and input:

```text
Enter your name & student ID (i.e. Minseok Seo / 12345678:
Minseok Seo / 12345678
```

Output:

```text
Hello OOP World! Minseok Seo
The sum of each number of your student number entered is 36
```

---

<br>

## Q3. A Program of Your Own Design

Freely design and write a program that includes the `JOptionPane`, `String`, `Date`, and `SimpleDateFormat` classes and conditional statements learned so far. Since the basic grammar has been covered, many topics usable in real life can be implemented. You may refer to public issues on the Internet for your topic, but you must quote the link in your report. The report gives the topic of the program and screenshots of its operation.

---

<br>

## Q4. Real-Time Age Calculator

Develop a real-time age calculator `Q4.java` like the example below.

Dialog prompt and input:

```text
Please enter your name and date of birth (i.e. Minseok Seo / 1987-12-11)
Minseok Seo / 1987-12-11
```

Output:

```text
Current time: 2023.04.23 14:05:15
Your date of birth: 1987-12-11 00:00:00
=========================================
Therefore, your current real-time age is:
35 Yrs 14 hrs 5 min 15 sec
```

---

<br>

## Q5. A GUI Program That Converts Won to Dollars

Write a GUI program that converts Korean won into dollars through dialogs. The exchange rate is fixed at 1348.81 won per dollar.

---

<br>

## Q6. Row and Column Sums of Nine Integers

Freely design and implement a program `Q6.java` that receives nine integers from the user and outputs the input values together with the row sums, column sums, and total sum in the following format. The inner three rows and three columns of Table 1 are the input values.

| | Column 1 | Column 2 | Column 3 | Row Sum |
|:-----|-----:|-----:|-----:|-----:|
| Row 1 | 2 | 4 | 5 | 11 |
| Row 2 | 10 | 13 | 1 | 24 |
| Row 3 | 2 | 6 | 7 | 15 |
| Column Sum | 14 | 23 | 13 | 50 |

*Table 1. Example output of the input values with row sums, column sums, and the total sum*

---

<br>

## Q7. Minimum Number of Bills

Write a CUI program that reads an amount with `Scanner` and tells the optimal (minimal) number of bills. For example, if 50250 won is entered, it reports one 50000-won bill, two 100-won coins, and one 50-won coin.

---

<br>

## Q8. Sum of the Real Numbers in a Sentence

As in the example below, develop a program `Q8.java` that finds and adds all the real numbers hidden in a sentence.

Dialog prompt and input:

```text
Please Enter a sentence with a mix of alphabets/special characters/real numbers:
ABCD12fealsk^%$$%^321ls!nfls10.3flw#nf0.2flaks
```

Output:

```text
The result of adding all the real numbers in the input text is: 12 + 321 + 10.3 + 0.2 = 343.5
```

---

<br>

## Q9. Identifying the Season of a Date

Write a GUI program that takes a date (YYYY-MM-DD format) and tells whether the day is spring, summer, fall, or winter based on the Korean standard (3-5: spring, 6-8: summer, 9-11: fall, 12-2: winter).

---

<br>

## Q10. A Notepad Built with ChatGPT

Implement a runnable Java-based Notepad program using ChatGPT. There are no restrictions on its design and operation. However, you must find and edit the relevant part so that your student ID and name appear in the title bar. The theory of this part has not been covered yet, but the more you train repeatedly with such experience, the closer you come to the talent that companies and research centers look for.

If you have never used ChatGPT, search YouTube for keywords such as "ChatGPT Java Notepad" to find a lot of know-how. Handling ChatGPT is also a developer's ability in this era.

---

<br>

After solving the problems, see the [assignment explanation](../Assignment-Explanation.md) for the solutions and explanations of Q1-Q9.
