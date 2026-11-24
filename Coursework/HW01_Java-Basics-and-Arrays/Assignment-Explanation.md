# HW1 Assignment Explanation — Java Basics and Arrays

[Repository Guide](../../README.md) | [English](Assignment-Explanation.md) | [한국어](Assignment-Explanation.ko.md)

> **Last Updated:** 2026-11-24

> **Learning Objectives:**
> 1. Validate input received through dialogs and `Scanner` and split it into the needed values
> 2. Process strings word by word and character by character into a given format
> 3. Parse dates with `Date`, `SimpleDateFormat`, and `Calendar` and compute the difference between two points in time
> 4. Store input values in a two-dimensional array and compute row and column sums
> 5. Explain how to solve the problem of breaking an amount into bills and coins and the problem of finding numbers in a sentence

---

## Table of Contents

- [1. Assignment Layout and How to Run](#1-assignment-layout-and-how-to-run)
- [2. Requirements](#2-requirements)
- [3. Q1: Reversing Each Word](#3-q1-reversing-each-word)
- [4. Q2: Greeting by Name and the Sum of the Student ID Digits](#4-q2-greeting-by-name-and-the-sum-of-the-student-id-digits)
- [5. Q3: A Program of Your Own Design](#5-q3-a-program-of-your-own-design)
- [6. Q4: Real-Time Age Calculator](#6-q4-real-time-age-calculator)
- [7. Q5: A GUI Program That Converts Won to Dollars](#7-q5-a-gui-program-that-converts-won-to-dollars)
- [8. Q6: Row and Column Sums of Nine Integers](#8-q6-row-and-column-sums-of-nine-integers)
- [9. Q7: Minimum Number of Bills](#9-q7-minimum-number-of-bills)
- [10. Q8: Sum of the Real Numbers in a Sentence](#10-q8-sum-of-the-real-numbers-in-a-sentence)
- [11. Q9: Identifying the Season of a Date](#11-q9-identifying-the-season-of-a-date)
- [Summary](#summary)
- [Self-Check Questions](#self-check-questions)

---

<br>

## 1. Assignment Layout and How to Run

> **Try It First:** This document contains the answers and solutions. Solve the problems in the [assignment handout](Original/Assignment.md) yourself before reading on.

The assignment asks for ten short programs that use Java's basic grammar, strings, conditionals and loops, arrays, `JOptionPane` dialogs, and date classes. The original questions and example input and output are in the [assignment handout](Original/Assignment.md). This document covers Q1-Q9, which have solutions; this repository has no solution to Q10 (a Notepad built with ChatGPT). The solution files use names starting with `Assignment1_` instead of the names `Q1.java`-`Q9.java` given in the handout.

```text
Coursework/HW01_Java-Basics-and-Arrays/
├── Original/
│   ├── Assignment.ko.md                        # assignment handout (Korean)
│   └── Assignment.md                           # assignment handout (English)
├── Solution/
│   ├── Assignment1_SentenceReverser.java       # Q1
│   ├── Assignment1_NameGreeter.java            # Q2
│   ├── Assignment1_ScheduleReminder.java       # Q3
│   ├── Assignment1_RealTimeAgeCalculator.java  # Q4
│   ├── Assignment1_CurrencyConverter.java      # Q5
│   ├── Assignment1_MatrixSumCalculator.java    # Q6
│   ├── Assignment1_OptimalBillCounter.java     # Q7
│   ├── Assignment1_NumberExtractorSum.java     # Q8
│   └── Assignment1_SeasonIdentifier.java       # Q9
├── Assignment-Explanation.ko.md                # assignment explanation (Korean)
└── Assignment-Explanation.md                   # assignment explanation (English)
```

*Figure 1. Directory layout of HW1*

| Question | Description | Solution File | Related Units |
|:-----|:-----|:-----|:-----|
| Q1 | Print an English sentence reversed word by word | [Assignment1_SentenceReverser.java](Solution/Assignment1_SentenceReverser.java) | Lectures 2 and 3 |
| Q2 | Read a name and student ID, then print a greeting and the sum of the ID digits | [Assignment1_NameGreeter.java](Solution/Assignment1_NameGreeter.java) | Lectures 2 and 4 |
| Q3 | A program of your own design using date classes and conditionals | [Assignment1_ScheduleReminder.java](Solution/Assignment1_ScheduleReminder.java) | Lectures 2 and 4 |
| Q4 | Real-time age calculator | [Assignment1_RealTimeAgeCalculator.java](Solution/Assignment1_RealTimeAgeCalculator.java) | Lectures 2 and 4 |
| Q5 | A GUI program that converts won to dollars | [Assignment1_CurrencyConverter.java](Solution/Assignment1_CurrencyConverter.java) | Lectures 2 and 3 |
| Q6 | Row sums, column sums, and the total sum of nine integers | [Assignment1_MatrixSumCalculator.java](Solution/Assignment1_MatrixSumCalculator.java) | Lecture 6 |
| Q7 | Minimum number of bills for an amount | [Assignment1_OptimalBillCounter.java](Solution/Assignment1_OptimalBillCounter.java) | Lectures 3 and 4 |
| Q8 | Sum of the real numbers in a sentence | [Assignment1_NumberExtractorSum.java](Solution/Assignment1_NumberExtractorSum.java) | Lecture 3 |
| Q9 | Identifying the season of a date | [Assignment1_SeasonIdentifier.java](Solution/Assignment1_SeasonIdentifier.java) | Lecture 4 |
| Q10 | A Notepad built with ChatGPT | None | - |

*Table 1. Questions and solution files of HW1*

Each file is an independent program made of a single `main`, so compile and run each file as follows. The `switch` expression in Q9 requires Java 14 or later.

```bash
cd Coursework/HW01_Java-Basics-and-Arrays/Solution
javac Assignment1_SentenceReverser.java
java Assignment1_SentenceReverser
```

> **Note:** Q1-Q6, Q8, and Q9 read input through `JOptionPane` dialogs, so they must be run in an environment with a display. This document writes the input and output of dialogs as text, like `[input]`.

---

<br>

## 2. Requirements

Every program must have a header comment and follow the standard naming rules. Each solution file starts with a comment holding the file name, the question number and summary, the author, and the date, and its `main` method has a Javadoc-style comment. The requirements of each question are as follows.

| Question | Input | Output and Conditions |
|:-----|:-----|:-----|
| Q1 | An English sentence through a dialog | Print it reversed word by word, finding the case rules in the example to get the same result |
| Q2 | `name / student ID` through a dialog | Separate the name and the ID automatically, and sum the ID digits cumulatively with a loop |
| Q3 | Free | Include `JOptionPane`, `String`, `Date`, `SimpleDateFormat`, and conditionals |
| Q4 | `name / date of birth` through a dialog | Print the current time, the date of birth, and the real-time age |
| Q5 | An amount in won through a dialog | Show the dollar amount converted at 1348.81 won per dollar in a GUI |
| Q6 | Nine integers | Show the input values with the row sums, column sums, and total sum as a table |
| Q7 | An amount through `Scanner` | Report the minimum number of bills and coins that make up the amount |
| Q8 | A sentence through a dialog | Find all the real numbers in the sentence and print the sum expression and result |
| Q9 | A `YYYY-MM-DD` date through a dialog | Show the season by the Korean standard (3-5 spring, 6-8 summer, 9-11 fall, 12-2 winter) in a GUI |

*Table 2. Requirements of each HW1 question*

---

<br>

## 3. Q1: Reversing Each Word

Matching the example input `Hello World! Welcome to the OOP` and output `Olleh !dlrow Emoclew Ot Eht Poo` word by word reveals the rule. `Hello` becomes `Olleh` by way of `olleH`, and `OOP` becomes `Poo`, not `POO`. Each word is therefore reversed, and then **only the first letter is made uppercase and the rest lowercase**. `World!` reversed is `!dlroW` and the output is `!dlrow`, so when the first character is a symbol rather than a letter, it is not capitalized and only the rest becomes lowercase.

The solution first converts the whole sentence to lowercase, splits it at spaces, reverses each word, and capitalizes the first character only when it is a letter.

```java
sentence = sentence.toLowerCase();
String[] words = sentence.split(" ");
StringBuilder result = new StringBuilder();

for (String word : words) {
    if (word.isEmpty()) {
        continue;
    }

    // Reverse the word
    String reversed = new StringBuilder(word).reverse().toString();

    // Capitalize first letter if it is alphabetic
    char firstChar = reversed.charAt(0);
    String rest = reversed.substring(1);

    if (Character.isLetter(firstChar)) {
        reversed = Character.toUpperCase(firstChar) + rest;
    }
    result.append(reversed).append(" ");
}
```

`StringBuilder.reverse()` reverses a string character by character. `split(" ")` produces empty strings between two or more consecutive spaces, so they are skipped with `word.isEmpty()`. A space is appended after each word, so `trim()` removes the trailing space before printing. If the user presses Cancel in the dialog, `showInputDialog` returns `null`, so the program prints `Input was cancelled.` and ends.

| Input | Output |
|:-----|:-----|
| `Hello World! Welcome to the OOP` | `Olleh !dlrow Emoclew Ot Eht Poo` |
| `  multiple   spaces here  ` (several spaces) | `Elpitlum Secaps Ereh` |

*Table 3. Results of the Q1 solution*

---

<br>

## 4. Q2: Greeting by Name and the Sum of the Student ID Digits

The input `Minseok Seo / 12345678` separates the name and the student ID with `/`. The solution splits them with `split("/")`, removes the surrounding spaces with `trim()`, then reads the ID one character at a time and adds the value of each digit.

```java
String[] parts = input.split("/");
if (parts.length < 2) {
    System.out.println("Invalid format. Example: Minseok Seo / 12345678");
    return;
}

String name = parts[0].trim();
String studentId = parts[1].trim();

// Compute digit sum
int sum = 0;
for (int i = 0; i < studentId.length(); i++) {
    char ch = studentId.charAt(i);
    if (Character.isDigit(ch)) {
        sum += ch - '0';
    }
}
```

`ch - '0'` turns a digit character into its value through the difference of character codes. The codes of the characters `'0'`-`'9'` are consecutive, so `'7' - '0'` is 7. The example ID 12345678 gives 1 + 2 + ... + 8 = 36.

```text
[input] Minseok Seo / 12345678
Hello OOP World! Minseok Seo
The sum of each number of your student number entered is 36
```

An input without `/` (for example, `Minseok Seo 12345678`) has `parts.length` of 1, so the program prints the format hint `Invalid format. Example: Minseok Seo / 12345678` and ends. If the input is empty or canceled, it prints `No input provided.`.

---

<br>

## 5. Q3: A Program of Your Own Design

Q3 asks for a freely designed program that includes `JOptionPane`, `String`, `Date`, `SimpleDateFormat`, and conditionals. The solution is a **schedule reminder (D-day) program** that tells how many days remain until, or have passed since, a date the user enters.

```java
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
sdf.setLenient(false);

Date inputDate = sdf.parse(inputDateStr.trim());
Date today = sdf.parse(sdf.format(new Date()));

long diffInMillis = inputDate.getTime() - today.getTime();
long diffInDays = diffInMillis / (1000 * 60 * 60 * 24);

String message;
if (diffInDays == 0) {
    message = "That date is today!";
} else if (diffInDays > 0) {
    message = diffInDays + " day(s) remaining.";
} else {
    message = Math.abs(diffInDays) + " day(s) have passed.";
}
```

- **Setting today's date to midnight:** `new Date()` holds the current time as well. Converting it to a `yyyy-MM-dd` string with `sdf.format` and parsing it back gives today's date at 0:00:00, so it can be compared on the same basis as the entered date (also at 0:00).
- **Counting days:** `getTime()` is the number of milliseconds since 1970-01-01 00:00:00 UTC (Lecture 4), so dividing the difference of two dates by the milliseconds in a day, $1000 \times 60 \times 60 \times 24 = 86{,}400{,}000$, gives the number of days. If the default time zone, like present-day Korean Standard Time, does not use daylight saving time, a day is always 24 hours and the division leaves no remainder. In a time zone that does use daylight saving time, the difference across a transition day can fall one hour short, and the integer division can then count one day fewer.
- **Strict parsing:** Without `setLenient(false)`, `SimpleDateFormat` rolls `2025-02-30` over to March 2. In strict mode it throws `ParseException`, and the `catch` block shows a dialog asking for a valid date.
- **A three-way conditional:** A difference of 0 means today, a positive difference gives the days remaining, and a negative one gives the days passed after `Math.abs`.

Let today be 2025-03-31. The results are as follows.

| Input | Result Dialog |
|:-----|:-----|
| `2025-04-15` | `15 day(s) remaining.` |
| `2025-03-31` | `That date is today!` |
| `2025-03-01` | `30 day(s) have passed.` |
| `2025-02-30` | `Please enter a valid date (e.g., 2025-04-01).` |

*Table 4. Results of the Q3 solution when today is 2025-03-31*

---

<br>

## 6. Q4: Real-Time Age Calculator

The real-time age is the period from 0:00 on the date of birth to the current time, expressed in years, months, days, hours, minutes, and seconds. The solution takes each field of the two points in time from `Calendar`, subtracts them field by field, and fixes any negative field by **borrowing** from the next larger unit. This is the same as borrowing in subtraction by hand.

```java
int years = nowCal.get(Calendar.YEAR) - birthCal.get(Calendar.YEAR);
int months = nowCal.get(Calendar.MONTH) - birthCal.get(Calendar.MONTH);
int days = nowCal.get(Calendar.DAY_OF_MONTH) - birthCal.get(Calendar.DAY_OF_MONTH);
int hours = nowCal.get(Calendar.HOUR_OF_DAY) - birthCal.get(Calendar.HOUR_OF_DAY);
int minutes = nowCal.get(Calendar.MINUTE) - birthCal.get(Calendar.MINUTE);
int seconds = nowCal.get(Calendar.SECOND) - birthCal.get(Calendar.SECOND);

// Normalize negative values by borrowing from the next larger unit
if (seconds < 0) { seconds += 60; minutes--; }
if (minutes < 0) { minutes += 60; hours--; }
if (hours < 0)   { hours += 24;   days--; }
if (days < 0) {
    nowCal.add(Calendar.MONTH, -1);
    days += nowCal.getActualMaximum(Calendar.DAY_OF_MONTH);
    months--;
}
if (months < 0) { months += 12; years--; }
```

Seconds, minutes, and hours borrow 60, 60, and 24. The length of a month differs from month to month, so borrowing days moves `nowCal` to the month just before the current one and adds that month's number of days obtained with `getActualMaximum(Calendar.DAY_OF_MONTH)`. A date of birth after the current time prints an error, and a wrongly formatted date is caught as `ParseException` with a format hint.

Following the handout's example time 2023-04-23 14:05:15 with the date of birth 1987-12-11 gives the following.

| Step | Years | Months | Days | Hours | Minutes | Seconds |
|:-----|-----:|-----:|-----:|-----:|-----:|-----:|
| Field-by-field difference | 36 | -8 | 12 | 14 | 5 | 15 |
| After fixing the months | 35 | 4 | 12 | 14 | 5 | 15 |

*Table 5. Computing the age at 2023-04-23 14:05:15 for the date of birth 1987-12-11*

`Calendar.MONTH` counts from 0, so April is 3, December is 11, and the difference is -8. Only the months are negative, so adding 12 and subtracting 1 from the years gives 35 years, 4 months, 12 days, 14 hours, 5 minutes, and 15 seconds. The solution prints the following.

```text
[input] Minseok Seo / 1987-12-11
Current time:       2023-04-23 14:05:15
Date of birth:      1987-12-11 00:00:00
============================================
Hello Minseok Seo, your real-time age is:
35 Yrs  4 Mons 12 Days 14 hrs  5 min 15 sec
```

At the same time with the date of birth 2000-05-31, the field-by-field differences are 23 years, -1 month, and -8 days. The days are negative, so borrowing the 31 days of March, the previous month, fixes them to 23 days and the months become -2; fixing the months in turn gives 22 years, 10 months, 23 days, 14 hours, 5 minutes, and 15 seconds.

> **Note:** The handout's example output, `35 Yrs 14 hrs 5 min 15 sec`, does not show months and days. The solution prints all fields, including months and days.

---

<br>

## 7. Q5: A GUI Program That Converts Won to Dollars

The exchange rate 1348.81 is kept as the `private static final` constant `EXCHANGE_RATE`, and the amount in won is divided by it to get dollars. Both the input and the result are exchanged through `JOptionPane` dialogs.

```java
double dollar = won / EXCHANGE_RATE;

DecimalFormat df = new DecimalFormat("#,##0.00");
String output = String.format("KRW %s = USD %s", df.format(won), df.format(dollar));
```

The `DecimalFormat` pattern `#,##0.00` inserts a comma every three digits and rounds to two decimal places (Lecture 3). A non-numeric input is caught as the `NumberFormatException` thrown by `Double.parseDouble` and shows `Invalid number format.`, and a negative input shows `Please enter a non-negative value.`, both in error dialogs.

| Input | Result Dialog |
|:-----|:-----|
| `50000` | `KRW 50,000.00 = USD 37.07` |
| `1348.81` | `KRW 1,348.81 = USD 1.00` |
| `abc` | `Invalid number format.` |
| `-100` | `Please enter a non-negative value.` |

*Table 6. Results of the Q5 solution*

50000 / 1348.81 is about 37.0697, which rounds to 37.07 at two decimal places.

---

<br>

## 8. Q6: Row and Column Sums of Nine Integers

The solution reads the nine integers in a single dialog with the rows separated by `/`, like `2 4 5 / 10 13 1 / 2 6 7`. If splitting at `/` does not give three rows or a row does not hold three numbers, it shows an error and ends.

```java
for (int i = 0; i < 3; i++) {
    String[] nums = rows[i].trim().split("\\s+");
    if (nums.length != 3) {
        JOptionPane.showMessageDialog(null,
                "Each row must contain exactly 3 numbers.",
                "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    for (int j = 0; j < 3; j++) {
        matrix[i][j] = Integer.parseInt(nums[j]);
        rowSum[i] += matrix[i][j];
        colSum[j] += matrix[i][j];
        totalSum += matrix[i][j];
    }
}
```

`split("\\s+")` splits wherever one or more spaces occur, so the numbers are obtained regardless of how many spaces separate them. The moment an element `matrix[i][j]` is read, it is added to the row sum `rowSum[i]`, the column sum `colSum[j]`, and the total at once, so a single nested loop completes every sum (two-dimensional arrays in Lecture 6). The result appears in the dialog as a table through a `JLabel` holding an HTML `<table>`. The result for the example input is as follows.

| | Values | | | Row Sum |
|:-----|-----:|-----:|-----:|-----:|
| Row 1 | 2 | 4 | 5 | 11 |
| Row 2 | 10 | 13 | 1 | 24 |
| Row 3 | 2 | 6 | 7 | 15 |
| Col Sum | 14 | 23 | 13 | 50 |

*Table 7. Table shown in the dialog of the Q6 solution for the example input*

---

<br>

## 9. Q7: Minimum Number of Bills

To make an amount with the fewest bills and coins, **pay as much as possible with the largest unit first**. A method like this, which at each step makes the choice that looks best at the moment, is called a **greedy algorithm**.

```java
private static final int[] DENOMINATIONS = {50000, 10000, 5000, 1000, 500, 100, 50, 10};
```

```java
for (int i = 0; i < DENOMINATIONS.length; i++) {
    counts[i] = amount / DENOMINATIONS[i];
    amount %= DENOMINATIONS[i];
}
```

For each unit, integer division `/` gives the count and the remainder operator `%` gives the amount left. For 50250 won, one 50000-won bill leaves 250 won, the units from 10000 down to 500 won get 0, two 100-won coins leave 50 won, and one 50-won coin finishes it. Units with a count of 0 are not printed.

```text
Enter an amount in KRW: 50250

Amount: 50250 KRW
Breakdown:
  50,000 KRW x 1
     100 KRW x 2
      50 KRW x 1
```

If the input is not an integer, the program catches `InputMismatchException` and prints `Error: Please enter a valid integer.`, and if it is negative, it prints `Error: Please enter a non-negative amount.`. The smallest unit is 10 won, so an amount that is not a multiple of 10 (for example, 12345) prints `Error: Amount must be a multiple of 10 (minimum coin unit).` and ends.

A greedy algorithm does not always give the minimum count. With units 1, 3, and 4 and an amount of 6, the greedy algorithm pays 4 + 1 + 1, three pieces, but 3 + 3 uses only two. The greedy algorithm gives the minimum count for the Korean units because **each unit is a multiple of the unit just below it** (50000 is 5 times 10000, 10000 is 2 times 5000, ..., 50 is 5 times 10). Under this condition, a minimum combination never pays an amount of $D$ or more with any unit $d$, where $D$ is the unit just above $d$. If it did, $D/d$ ($`\ge 2`$) of those pieces could be replaced by one $D$, reducing the count. Therefore the count of the largest unit in a minimum combination equals the quotient of the amount by that unit, and applying the same argument to the remaining amount in turn makes the minimum combination equal to the result of the greedy algorithm.

---

<br>

## 10. Q8: Sum of the Real Numbers in a Sentence

The numbers to find in the example input `ABCD12fealsk^%$$%^321ls!nfls10.3flw#nf0.2flaks` are 12, 321, 10.3, and 0.2. A number is a run of one or more digits, which may be followed by a decimal point and more digits. The solution expresses this shape as a **regular expression** and finds it with `Pattern` and `Matcher` from `java.util.regex`.

```java
Pattern pattern = Pattern.compile("\\d+(?:\\.\\d+)?");
Matcher matcher = pattern.matcher(input);

double sum = 0;
StringBuilder expression = new StringBuilder();

while (matcher.find()) {
    String numberStr = matcher.group();
    double number = Double.parseDouble(numberStr);
    sum += number;

    if (expression.length() > 0) {
        expression.append(" + ");
    }
    expression.append(numberStr);
}
```

| Part | Meaning |
|:-----|:-----|
| `\d+` | One or more digits |
| `(?:...)` | A group that only groups and does not capture |
| `\.` | A literal decimal point (`.` alone means any single character) |
| `?` | The preceding group appears zero times or once |

*Table 8. Structure of the regular expression `\d+(?:\.\d+)?`*

Inside a Java string a backslash must be written as `\\`, so the code reads `"\\d+(?:\\.\\d+)?"`. `find()` finds the next part that matches the pattern from left to right and returns `true`, and `group()` is the string of the part found. Each time a number is found, its value is added and an expression of the form `12 + 321 + ...` is built.

```text
[input] ABCD12fealsk^%$$%^321ls!nfls10.3flw#nf0.2flaks
Sum of all numbers: 12 + 321 + 10.3 + 0.2 = 343.5
```

> **Note:** This pattern does not treat signs or decimals without an integer part as part of a number. The input `3.14 and 2. and .5 and -7` gives `3.14 + 2 + 5 + 7 = 17.14`: `-7` is read as 7, `.5` as 5, and `2.` as 2. The handout's example has no such numbers; including signs in numbers requires widening the pattern, for example by putting `-?` in front of it.

---

<br>

## 11. Q9: Identifying the Season of a Date

The entered date is parsed with `SimpleDateFormat("yyyy-MM-dd")` in strict mode, and then the month taken from `Calendar` decides the season through a `switch` expression.

```java
Calendar cal = Calendar.getInstance();
cal.setTime(date);
int month = cal.get(Calendar.MONTH) + 1;

String season = switch (month) {
    case 3, 4, 5   -> "Spring";
    case 6, 7, 8   -> "Summer";
    case 9, 10, 11 -> "Autumn";
    case 12, 1, 2  -> "Winter";
    default        -> "Unknown";
};
```

`Calendar.MONTH` counts from 0, so 1 is added to get 1-12. The Java 14 `switch` expression lists several values in one `case` separated by commas and returns the value to the right of `->` as the value of the whole expression. No `break` is needed, so the mistake of falling through to the next `case` because of a missing `break` cannot happen. Grouping December, January, and February into winter also takes a single line.

| Input | Result Dialog |
|:-----|:-----|
| `2025-04-01` | `The date falls in: Spring` |
| `2025-12-25` | `The date falls in: Winter` |
| `2025-01-10` | `The date falls in: Winter` |
| `2025-13-01` | `Invalid date format. Please use YYYY-MM-DD.` |

*Table 9. Results of the Q9 solution*

---

<br>

## Summary

| Concept | Key Summary |
|:-----|:-----|
| Case rule of Q1 | Reverse the word, make it all lowercase, and capitalize the first character only when it is a letter. |
| Splitting the input | `split("/")` and `trim()` separate the name and the ID (or the date of birth, or the rows), and a wrong format prints a hint and ends. |
| Value of a digit character | `ch - '0'` turns a digit character into its integer value. |
| Comparing dates | Today's date is set to midnight with `format` and `parse`, and the difference of `getTime()` divided by the milliseconds in a day gives the number of days. |
| Strict date parsing | With `setLenient(false)`, a date such as `2025-02-30` becomes `ParseException`. |
| Computing the age | Subtract field by field, then fix negative fields by borrowing from larger units, using the length of the previous month when borrowing days. |
| Row and column sums | Adding each element to the row sum, column sum, and total when it is read finishes the work in a single nested loop. |
| Minimum number of bills | A greedy algorithm takes quotients and remainders from the largest unit down, and it gives the minimum count because each unit is a multiple of the unit below. |
| Real numbers in a sentence | `Matcher.find()` finds the parts matching the regular expression `\d+(?:\.\d+)?` one after another, and they are added. |
| Identifying the season | `Calendar.MONTH + 1` is mapped to seasons by multi-value `case` labels of a `switch` expression. |

---

<br>

## Self-Check Questions

1. **Case rule:** What does the Q1 solution print for the input `I love JAVA 17!`?

   <details>
   <summary>Answer</summary>

   `I Evol Avaj !71`. In lowercase the input is `i love java 17!`, and reversing each word gives `i`, `evol`, `avaj`, and `!71`. Only the first three words, which start with a letter, have their first letter capitalized, and the last word, which starts with `!`, stays as it is.

   </details>

2. **Borrowing in the age:** In the Q4 solution, if the current time is 2024-03-01 00:00:00 and the date of birth is 2000-01-31, what age is printed?

   <details>
   <summary>Answer</summary>

   The field-by-field differences are 24 years, 2 months, and -30 days. The days are negative, so `nowCal` moves to February 2024 and its 29 days are added, giving -1 day, and the months become 1. The solution borrows days only once, so the days stay at -1 and it prints `24 Yrs  1 Mons -1 Days  0 hrs  0 min  0 sec`. This happens when the previous month is shorter than the day of birth (the 31st); repeating the borrowing of another month while the days are still negative corrects the result to 24 years, 0 months, and 30 days.

   </details>

3. **Greedy algorithm:** For the input 186760, which units does the Q7 solution print, and how many of each?

   <details>
   <summary>Answer</summary>

   Three 50000-won bills (150000, leaving 36760), three 10000-won bills (6760), one 5000-won bill (1760), one 1000-won bill (760), one 500-won coin (260), two 100-won coins (60), one 50-won coin (10), and one 10-won coin, 13 pieces in total.

   </details>

4. **Condition for the greedy algorithm:** In a country whose units are 1, 5, and 12, does the greedy algorithm give the minimum count for 15?

   <details>
   <summary>Answer</summary>

   It does not. The greedy algorithm pays 12 + 1 + 1 + 1, four pieces, but 5 + 5 + 5 uses only three. The condition that each unit is a multiple of the unit below breaks because 12 is not a multiple of 5.

   </details>

5. **Regular expression:** For the input `x1.2.3y45`, which numbers does the Q8 solution add?

   <details>
   <summary>Answer</summary>

   It adds `1.2`, `3`, and `45` and prints `1.2 + 3 + 45 = 49.2`. `find()` finds `1.2` from the first position, skips the following `.`, which is not a digit, finds `3`, and finally finds `45`.

   </details>

6. **Strict mode:** In the Q3 solution, if `sdf.setLenient(false)` is removed and today is 2025-03-31, what is the result for the input `2025-02-30`?

   <details>
   <summary>Answer</summary>

   Lenient parsing rolls February 30 over to March 2, two days after February 28, so no error occurs; March 2 is 29 days before March 31, so `29 day(s) have passed.` is shown.

   </details>
