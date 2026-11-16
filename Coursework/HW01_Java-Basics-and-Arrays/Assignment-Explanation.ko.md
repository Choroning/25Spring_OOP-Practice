# HW1 과제 해설 — Java 기초와 배열

[레포지토리 안내](../../README.ko.md) | [English](Assignment-Explanation.md) | [한국어](Assignment-Explanation.ko.md)

> **최종 수정일:** 2026-11-16

> **학습 목표:**
> 1. 대화 상자와 `Scanner`로 받은 입력을 검증하고 필요한 값으로 나눌 수 있다
> 2. 문자열을 단어와 문자 단위로 다루어 정해진 형식으로 가공할 수 있다
> 3. `Date`, `SimpleDateFormat`, `Calendar`로 날짜를 해석하고 두 시점의 차이를 계산할 수 있다
> 4. 2차원 배열에 입력값을 저장하고 행과 열의 합을 구할 수 있다
> 5. 금액을 지폐와 동전으로 나누는 문제와 문장에서 수를 찾는 문제의 풀이 방법을 설명할 수 있다

---

## 목차

- [1. 과제 구성과 실행 방법](#1-과제-구성과-실행-방법)
- [2. 요구사항](#2-요구사항)
- [3. Q1: 단어별 역순 출력](#3-q1-단어별-역순-출력)
- [4. Q2: 이름 인사와 학번 자릿수의 합](#4-q2-이름-인사와-학번-자릿수의-합)
- [5. Q3: 자유 설계 프로그램](#5-q3-자유-설계-프로그램)
- [6. Q4: 실시간 나이 계산기](#6-q4-실시간-나이-계산기)
- [7. Q5: 원화를 달러로 바꾸는 GUI 프로그램](#7-q5-원화를-달러로-바꾸는-gui-프로그램)
- [8. Q6: 정수 9개의 행 합과 열 합](#8-q6-정수-9개의-행-합과-열-합)
- [9. Q7: 최소 지폐 개수](#9-q7-최소-지폐-개수)
- [10. Q8: 문장 속 실수의 합](#10-q8-문장-속-실수의-합)
- [11. Q9: 날짜의 계절 판별](#11-q9-날짜의-계절-판별)
- [요약](#요약)
- [점검 문제](#점검-문제)

---

<br>

## 1. 과제 구성과 실행 방법

> **풀어 보기:** 이 문서는 정답과 풀이를 담고 있다. 먼저 [과제 안내문](Original/Assignment.ko.md)의 문제를 직접 풀어 본 뒤 읽는다.

Java의 기본 문법, 문자열, 조건문과 반복문, 배열, `JOptionPane` 대화 상자, 날짜 클래스를 쓰는 짧은 프로그램 열 개를 작성하는 과제이다. 문제의 원문과 예시 입출력은 [과제 안내문](Original/Assignment.ko.md)에 있다. 이 문서는 풀이가 있는 Q1\~Q9를 다루며, Q10(ChatGPT로 만드는 메모장)의 풀이는 이 레포지토리에 없다. 풀이 파일은 제출할 때의 `Q1.java`\~`Q9.java` 대신 `Assignment1_`로 시작하는 이름을 쓴다.

```text
Coursework/HW01_Java-Basics-and-Arrays/
├── Original/
│   ├── Assignment.ko.md                        # 과제 안내문 (한국어)
│   └── Assignment.md                           # 과제 안내문 (영어)
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
├── Assignment-Explanation.ko.md                # 과제 해설 (한국어)
└── Assignment-Explanation.md                   # 과제 해설 (영어)
```

*그림 1. HW1 과제의 디렉토리 구성*

| 문제 | 내용 | 풀이 파일 | 관련 단원 |
|:-----|:-----|:-----|:-----|
| Q1 | 영어 문장을 단어별로 거꾸로 출력 | [Assignment1_SentenceReverser.java](Solution/Assignment1_SentenceReverser.java) | 2강, 3강 |
| Q2 | 이름과 학번을 받아 인사말과 학번 자릿수의 합 출력 | [Assignment1_NameGreeter.java](Solution/Assignment1_NameGreeter.java) | 2강, 4강 |
| Q3 | 날짜 클래스와 조건문을 쓰는 자유 설계 프로그램 | [Assignment1_ScheduleReminder.java](Solution/Assignment1_ScheduleReminder.java) | 2강, 4강 |
| Q4 | 실시간 나이 계산기 | [Assignment1_RealTimeAgeCalculator.java](Solution/Assignment1_RealTimeAgeCalculator.java) | 2강, 4강 |
| Q5 | 원화를 달러로 바꾸는 GUI 프로그램 | [Assignment1_CurrencyConverter.java](Solution/Assignment1_CurrencyConverter.java) | 2강, 3강 |
| Q6 | 정수 9개의 행 합, 열 합, 총합 | [Assignment1_MatrixSumCalculator.java](Solution/Assignment1_MatrixSumCalculator.java) | 6강 |
| Q7 | 금액에 맞는 최소 지폐 개수 | [Assignment1_OptimalBillCounter.java](Solution/Assignment1_OptimalBillCounter.java) | 3강, 4강 |
| Q8 | 문장 속 실수의 합 | [Assignment1_NumberExtractorSum.java](Solution/Assignment1_NumberExtractorSum.java) | 3강 |
| Q9 | 날짜의 계절 판별 | [Assignment1_SeasonIdentifier.java](Solution/Assignment1_SeasonIdentifier.java) | 4강 |
| Q10 | ChatGPT로 만드는 메모장 | 없음 | - |

*표 1. HW1의 문제와 풀이 파일*

각 파일은 `main` 하나로 이루어진 독립된 프로그램이므로 다음처럼 파일마다 컴파일하고 실행한다. Q9의 `switch` 식은 Java 14 이상이 필요하다.

```bash
cd Coursework/HW01_Java-Basics-and-Arrays/Solution
javac Assignment1_SentenceReverser.java
java Assignment1_SentenceReverser
```

> **참고:** Q1\~Q6, Q8, Q9는 `JOptionPane` 대화 상자로 입력을 받으므로 화면이 있는 환경에서 실행해야 한다. 이 문서에서 대화 상자의 입력과 출력은 `[입력]`처럼 글로 적는다.

---

<br>

## 2. 요구사항

모든 프로그램은 헤더 주석을 달고 표준 명명 규칙을 지켜야 한다. 풀이 파일은 파일 첫머리에 파일 이름, 문제 번호와 요약, 작성자, 날짜를 담은 주석을 두고, 클래스와 `main`에 Javadoc 형식의 주석을 단다. 문제별 요구사항은 다음과 같다.

| 문제 | 입력 | 출력과 조건 |
|:-----|:-----|:-----|
| Q1 | 대화 상자로 영어 문장 | 단어별로 거꾸로 출력하며, 예시에서 대소문자 규칙을 찾아 같은 결과를 낸다 |
| Q2 | 대화 상자로 `이름 / 학번` | 이름과 학번을 자동으로 분리하고, 학번의 자릿수를 반복문으로 누적 합산한다 |
| Q3 | 자유 | `JOptionPane`, `String`, `Date`, `SimpleDateFormat`과 조건문을 포함한다 |
| Q4 | 대화 상자로 `이름 / 생년월일` | 현재 시각과 생년월일, 실시간 나이를 출력한다 |
| Q5 | 대화 상자로 원화 금액 | 1달러당 1348.81원으로 바꾼 달러 금액을 GUI로 보인다 |
| Q6 | 정수 9개 | 입력값과 행 합, 열 합, 총합을 표 형식으로 보인다 |
| Q7 | `Scanner`로 금액 | 금액을 이루는 최소 개수의 지폐와 동전을 알려 준다 |
| Q8 | 대화 상자로 문장 | 문장 속 실수를 모두 찾아 더한 식과 결과를 출력한다 |
| Q9 | 대화 상자로 `YYYY-MM-DD` 날짜 | 한국 기준(3\~5월 봄, 6\~8월 여름, 9\~11월 가을, 12\~2월 겨울)의 계절을 GUI로 보인다 |

*표 2. HW1 문제별 요구사항*

---

<br>

## 3. Q1: 단어별 역순 출력

예시의 입력 `Hello World! Welcome to the OOP`와 출력 `Olleh !dlrow Emoclew Ot Eht Poo`를 단어별로 맞추어 보면 규칙이 드러난다. `Hello`는 `olleH`를 거쳐 `Olleh`가 되고, `OOP`는 `POO`가 아니라 `Poo`가 된다. 따라서 각 단어를 뒤집은 뒤 **첫 글자만 대문자로, 나머지는 모두 소문자로** 바꾼다. `World!`는 뒤집으면 `!dlroW`이고 출력은 `!dlrow`이므로, 첫 글자가 문자가 아닌 기호이면 대문자로 바꾸지 않고 나머지만 소문자가 된다.

풀이는 문장 전체를 먼저 소문자로 바꾼 뒤 공백으로 나누고, 단어마다 뒤집어 첫 글자가 영문자일 때만 대문자로 바꾼다.

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

`StringBuilder`의 `reverse()`는 문자열을 문자 단위로 뒤집는다. `split(" ")`은 공백이 두 칸 이상 이어지면 그 사이에서 빈 문자열을 만들므로 `word.isEmpty()`로 건너뛴다. 단어마다 뒤에 공백을 붙였으므로 마지막에 `trim()`으로 끝의 공백을 지우고 출력한다. 대화 상자에서 취소를 누르면 `showInputDialog`가 `null`을 돌려주므로 `Input was cancelled.`를 출력하고 끝낸다.

| 입력 | 출력 |
|:-----|:-----|
| `Hello World! Welcome to the OOP` | `Olleh !dlrow Emoclew Ot Eht Poo` |
| `  multiple   spaces here  ` (공백이 여러 칸) | `Elpitlum Secaps Ereh` |

*표 3. Q1 풀이의 실행 결과*

---

<br>

## 4. Q2: 이름 인사와 학번 자릿수의 합

입력 `Minseok Seo / 12345678`은 이름과 학번을 `/`로 구분한다. 풀이는 `split("/")`로 둘을 나누고 `trim()`으로 앞뒤 공백을 지운 뒤, 학번을 한 글자씩 읽어 숫자이면 그 값을 더한다.

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

`ch - '0'`은 문자 코드의 차이로 숫자 문자를 그 값으로 바꾼다. 문자 `'0'`\~`'9'`의 코드가 연속이므로 `'7' - '0'`은 7이다. 예시의 학번 12345678은 1 + 2 + ... + 8 = 36이다.

```text
[입력] Minseok Seo / 12345678
Hello OOP World! Minseok Seo
The sum of each number of your student number entered is 36
```

`/`가 없는 입력(예: `Minseok Seo 12345678`)은 `parts.length`가 1이므로 형식 안내 `Invalid format. Example: Minseok Seo / 12345678`을 출력하고 끝낸다. 입력이 비었거나 취소하면 `No input provided.`를 출력한다.

---

<br>

## 5. Q3: 자유 설계 프로그램

Q3는 `JOptionPane`, `String`, `Date`, `SimpleDateFormat`과 조건문을 포함하는 프로그램을 자유롭게 설계하는 문제이다. 풀이는 사용자가 입력한 날짜까지 며칠 남았는지, 또는 며칠 지났는지를 알려 주는 **일정 알림(D-day) 프로그램**이다.

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

- **오늘 날짜를 자정으로 맞추기:** `new Date()`는 현재 시각까지 담는다. `sdf.format`으로 `yyyy-MM-dd` 문자열로 바꾼 뒤 다시 `parse`하면 시각이 0시 0분 0초인 오늘 날짜가 되어, 입력한 날짜(역시 0시)와 같은 기준으로 비교할 수 있다.
- **일수 계산:** `getTime()`은 1970-01-01 00:00:00 UTC부터 지난 밀리초이므로(4강), 두 날짜의 차를 하루의 밀리초 $1000 \times 60 \times 60 \times 24 = 86{,}400{,}000$으로 나누면 일수가 된다. 한국 시간에는 일광 절약 시간이 없어 하루가 언제나 24시간이므로 나머지 없이 나누어떨어진다.
- **엄격한 해석:** `setLenient(false)`가 없으면 `SimpleDateFormat`은 `2025-02-30`을 3월 2일로 넘겨 해석한다. 엄격 모드에서는 `ParseException`이 나고, `catch` 블록이 올바른 날짜를 입력하라는 대화 상자를 띄운다.
- **세 갈래의 조건문:** 차이가 0이면 오늘, 양수이면 남은 일수, 음수이면 지난 일수를 `Math.abs`로 바꾸어 알린다.

오늘이 2025-03-31이라 하면 결과는 다음과 같다.

| 입력 | 결과 대화 상자 |
|:-----|:-----|
| `2025-04-15` | `15 day(s) remaining.` |
| `2025-03-31` | `That date is today!` |
| `2025-03-01` | `30 day(s) have passed.` |
| `2025-02-30` | `Please enter a valid date (e.g., 2025-04-01).` |

*표 4. 오늘이 2025-03-31일 때 Q3 풀이의 실행 결과*

---

<br>

## 6. Q4: 실시간 나이 계산기

실시간 나이는 현재 시각에서 생년월일 0시를 뺀 기간을 년, 월, 일, 시, 분, 초로 나타낸 것이다. 풀이는 `Calendar`로 두 시점의 각 필드를 꺼내 필드끼리 빼고, 음수가 된 필드는 한 단계 큰 단위에서 **빌려 와서** 고친다. 손으로 하는 뺄셈의 받아내림과 같은 방법이다.

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

초, 분, 시는 각각 60, 60, 24를 빌려 오면 된다. 한 달의 길이는 달마다 다르므로, 일을 빌려 올 때는 현재 달의 바로 앞 달로 옮긴 `nowCal`에서 `getActualMaximum(Calendar.DAY_OF_MONTH)`로 그 달의 날수를 얻어 더한다. 생년월일이 현재보다 뒤이면 오류를 출력하고, 날짜 형식이 틀리면 `ParseException`을 잡아 형식을 안내한다.

안내문의 예시 시각 2023-04-23 14:05:15에 생년월일 1987-12-11을 넣어 따라가면 다음과 같다.

| 단계 | 년 | 월 | 일 | 시 | 분 | 초 |
|:-----|-----:|-----:|-----:|-----:|-----:|-----:|
| 필드끼리 뺀 값 | 36 | -8 | 12 | 14 | 5 | 15 |
| 월을 고친 값 | 35 | 4 | 12 | 14 | 5 | 15 |

*표 5. 2023-04-23 14:05:15에 생년월일 1987-12-11의 나이를 구하는 과정*

`Calendar.MONTH`는 0부터 세므로 4월은 3, 12월은 11이고 차는 -8이다. 월만 음수이므로 12를 더하고 년에서 1을 빼면 35년 4개월 12일 14시간 5분 15초가 된다. 풀이의 출력은 다음과 같다.

```text
[입력] Minseok Seo / 1987-12-11
Current time:       2023-04-23 14:05:15
Date of birth:      1987-12-11 00:00:00
============================================
Hello Minseok Seo, your real-time age is:
35 Yrs  4 Mons 12 Days 14 hrs  5 min 15 sec
```

같은 시각에 생년월일이 2000-05-31이면 필드끼리 뺀 값은 23년, -1월, -8일이다. 일이 음수이므로 앞 달인 3월의 31일을 빌려 23일로 고치고 월은 -2가 되며, 다시 월을 고쳐 22년 10개월 23일 14시간 5분 15초가 된다.

> **참고:** 안내문의 예시 출력은 `35 Yrs 14 hrs 5 min 15 sec`로 월과 일을 적지 않았다. 풀이는 월과 일까지 모두 출력한다.

---

<br>

## 7. Q5: 원화를 달러로 바꾸는 GUI 프로그램

환율 1348.81을 `private static final` 상수 `EXCHANGE_RATE`로 두고, 원화 금액을 이 값으로 나누어 달러로 바꾼다. 입력과 결과 모두 `JOptionPane` 대화 상자로 주고받는다.

```java
double dollar = won / EXCHANGE_RATE;

DecimalFormat df = new DecimalFormat("#,##0.00");
String output = String.format("KRW %s = USD %s", df.format(won), df.format(dollar));
```

`DecimalFormat`의 패턴 `#,##0.00`은 세 자리마다 쉼표를 넣고 소수점 아래를 두 자리로 반올림한다(3강). 숫자가 아닌 입력은 `Double.parseDouble`이 던지는 `NumberFormatException`을 잡아 `Invalid number format.`을, 음수는 `Please enter a non-negative value.`를 오류 대화 상자로 보인다.

| 입력 | 결과 대화 상자 |
|:-----|:-----|
| `50000` | `KRW 50,000.00 = USD 37.07` |
| `1348.81` | `KRW 1,348.81 = USD 1.00` |
| `abc` | `Invalid number format.` |
| `-100` | `Please enter a non-negative value.` |

*표 6. Q5 풀이의 실행 결과*

50000 / 1348.81은 약 37.0697이므로 둘째 자리에서 반올림하여 37.07이다.

---

<br>

## 8. Q6: 정수 9개의 행 합과 열 합

풀이는 정수 9개를 대화 상자 한 번에 `2 4 5 / 10 13 1 / 2 6 7`처럼 행을 `/`로 나누어 입력받는다. `/`로 나눈 행이 3개가 아니거나 한 행의 수가 3개가 아니면 오류를 보이고 끝낸다.

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

`split("\\s+")`은 공백이 한 칸 이상 이어진 곳에서 나누므로 수 사이의 공백 수와 관계없이 수를 얻는다. 원소 `matrix[i][j]`를 읽는 순간 행 합 `rowSum[i]`, 열 합 `colSum[j]`, 총합에 함께 더하므로, 이중 반복문 한 번으로 모든 합이 끝난다(6강의 2차원 배열). 결과는 HTML `<table>`을 담은 `JLabel`로 대화 상자에 표처럼 보인다. 예시 입력의 결과는 다음과 같다.

| | Values | | | Row Sum |
|:-----|-----:|-----:|-----:|-----:|
| Row 1 | 2 | 4 | 5 | 11 |
| Row 2 | 10 | 13 | 1 | 24 |
| Row 3 | 2 | 6 | 7 | 15 |
| Col Sum | 14 | 23 | 13 | 50 |

*표 7. 예시 입력에 대해 Q6 풀이의 대화 상자가 보이는 표*

---

<br>

## 9. Q7: 최소 지폐 개수

금액을 가장 적은 수의 지폐와 동전으로 나타내려면, **가장 큰 단위부터 그 단위로 낼 수 있는 만큼 최대한 낸다.** 매 단계에서 지금 가장 좋아 보이는 선택을 하는 이런 방법을 **탐욕 알고리즘(greedy algorithm)** 이라 한다.

```java
private static final int[] DENOMINATIONS = {50000, 10000, 5000, 1000, 500, 100, 50, 10};
```

```java
for (int i = 0; i < DENOMINATIONS.length; i++) {
    counts[i] = amount / DENOMINATIONS[i];
    amount %= DENOMINATIONS[i];
}
```

단위마다 정수 나눗셈 `/`로 개수를, 나머지 연산 `%`로 남은 금액을 구한다. 50250원은 50000원 1개를 내고 250원이 남으며, 10000원부터 500원까지는 0개, 100원 2개를 내고 50원이 남고, 50원 1개로 끝난다. 개수가 0인 단위는 출력하지 않는다.

```text
Enter an amount in KRW: 50250

Amount: 50250 KRW
Breakdown:
  50,000 KRW x 1
     100 KRW x 2
      50 KRW x 1
```

입력이 정수가 아니면 `InputMismatchException`을 잡아 `Error: Please enter a valid integer.`를, 음수이면 `Error: Please enter a non-negative amount.`를 출력한다. 가장 작은 단위가 10원이므로 10의 배수가 아닌 금액(예: 12345)은 `Error: Amount must be a multiple of 10 (minimum coin unit).`을 출력하고 끝낸다.

탐욕 알고리즘이 언제나 최소 개수를 주는 것은 아니다. 단위가 1, 3, 4이고 금액이 6이면 탐욕 알고리즘은 4 + 1 + 1의 3개를 내지만, 3 + 3의 2개가 더 적다. 한국 화폐 단위에서 탐욕 알고리즘이 최소 개수를 주는 이유는 **각 단위가 바로 아래 단위의 배수**이기 때문이다(50000은 10000의 5배, 10000은 5000의 2배, ..., 50은 10의 5배). 이 조건에서 최소 개수의 조합은 어떤 단위 $d$로도 바로 위 단위 $D$ 이상의 금액을 내지 않는다. $d$ 단위로 $D$ 이상을 냈다면 그중 $D/d$개($`\ge 2`$)를 $D$ 1개로 바꾸어 개수를 줄일 수 있기 때문이다. 따라서 최소 조합에서 가장 큰 단위의 개수는 금액을 그 단위로 나눈 몫과 같고, 나머지 금액에 같은 논리를 차례로 적용하면 최소 조합이 탐욕 알고리즘의 결과와 같아진다.

---

<br>

## 10. Q8: 문장 속 실수의 합

예시 입력 `ABCD12fealsk^%$$%^321ls!nfls10.3flw#nf0.2flaks`에서 찾아야 할 수는 12, 321, 10.3, 0.2이다. 수는 숫자가 하나 이상 이어진 부분이고, 그 뒤에 소수점과 숫자들이 붙을 수 있다. 풀이는 이 모양을 **정규 표현식(regular expression)** 으로 나타내고 `java.util.regex`의 `Pattern`과 `Matcher`로 찾는다.

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

| 부분 | 뜻 |
|:-----|:-----|
| `\d+` | 숫자 하나 이상 |
| `(?:...)` | 묶기만 하고 따로 기억하지 않는 그룹 |
| `\.` | 소수점 문자 그대로 (`.`만 쓰면 아무 문자 하나를 뜻한다) |
| `?` | 앞의 그룹이 없거나 한 번 있음 |

*표 8. 정규 표현식 `\d+(?:\.\d+)?`의 구성*

Java 문자열 안에서는 역슬래시를 `\\`로 써야 하므로 코드에서는 `"\\d+(?:\\.\\d+)?"`가 된다. `find()`는 앞에서부터 패턴에 맞는 다음 부분을 찾아 `true`를 돌려주고, `group()`은 찾은 부분의 문자열이다. 찾을 때마다 값을 더하고 `12 + 321 + ...` 형태의 식을 만든다.

```text
[입력] ABCD12fealsk^%$$%^321ls!nfls10.3flw#nf0.2flaks
Sum of all numbers: 12 + 321 + 10.3 + 0.2 = 343.5
```

> **참고:** 이 패턴은 부호와 정수부가 없는 소수를 수로 보지 않는다. 입력 `3.14 and 2. and .5 and -7`의 결과는 `3.14 + 2 + 5 + 7 = 17.14`로, `-7`은 7로, `.5`는 5로, `2.`는 2로 읽힌다. 안내문의 예시에는 이런 수가 없으며, 부호를 수에 포함하려면 패턴 앞에 `-?`를 붙이는 것처럼 패턴을 넓혀야 한다.

---

<br>

## 11. Q9: 날짜의 계절 판별

입력한 날짜를 `SimpleDateFormat("yyyy-MM-dd")`의 엄격 모드로 해석한 뒤, `Calendar`에서 월을 꺼내 `switch` 식으로 계절을 정한다.

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

`Calendar.MONTH`가 0부터 세므로 1을 더해 1\~12로 맞춘다. Java 14의 `switch` 식은 `case` 하나에 여러 값을 쉼표로 나열하고, `->` 오른쪽의 값을 식 전체의 값으로 돌려준다. `break`가 필요 없어 빠뜨린 `break` 때문에 다음 `case`로 넘어가는 실수가 생기지 않는다. 12월, 1월, 2월이 겨울로 묶이는 것도 한 줄로 나타난다.

| 입력 | 결과 대화 상자 |
|:-----|:-----|
| `2025-04-01` | `The date falls in: Spring` |
| `2025-12-25` | `The date falls in: Winter` |
| `2025-01-10` | `The date falls in: Winter` |
| `2025-13-01` | `Invalid date format. Please use YYYY-MM-DD.` |

*표 9. Q9 풀이의 실행 결과*

---

<br>

## 요약

| 개념 | 핵심 요약 |
|:-----|:-----|
| Q1의 대소문자 규칙 | 단어를 뒤집은 뒤 전체를 소문자로 하고, 첫 글자가 영문자일 때만 대문자로 바꾼다. |
| 입력의 분리 | `split("/")`와 `trim()`으로 이름과 학번(또는 생년월일, 행)을 나누고, 형식이 맞지 않으면 안내하고 끝낸다. |
| 숫자 문자의 값 | `ch - '0'`은 숫자 문자를 그 정수 값으로 바꾼다. |
| 날짜의 비교 | 오늘 날짜를 `format`과 `parse`로 자정에 맞추고, `getTime()`의 차를 하루의 밀리초로 나누어 일수를 구한다. |
| 엄격한 날짜 해석 | `setLenient(false)`이면 `2025-02-30` 같은 날짜가 `ParseException`이 된다. |
| 나이의 계산 | 필드끼리 뺀 뒤 음수인 필드를 큰 단위에서 빌려 와 고치며, 일을 빌릴 때는 앞 달의 날수를 쓴다. |
| 행 합과 열 합 | 원소를 읽을 때 행 합, 열 합, 총합에 함께 더하면 이중 반복문 한 번으로 끝난다. |
| 최소 지폐 개수 | 큰 단위부터 몫과 나머지를 구하는 탐욕 알고리즘이며, 각 단위가 아래 단위의 배수이므로 최소 개수가 된다. |
| 문장 속 실수 | 정규 표현식 `\d+(?:\.\d+)?`에 맞는 부분을 `Matcher.find()`로 차례로 찾아 더한다. |
| 계절 판별 | `Calendar.MONTH + 1`을 `switch` 식의 여러 값 `case`로 계절에 대응시킨다. |

---

<br>

## 점검 문제

1. **대소문자 규칙:** Q1 풀이에 `I love JAVA 17!`을 입력하면 무엇이 출력되는가?

   <details>
   <summary>정답</summary>

   `I Evol Avaj !71`이다. 소문자로 바꾸면 `i love java 17!`이고, 단어마다 뒤집으면 `i`, `evol`, `avaj`, `!71`이다. 첫 글자가 영문자인 앞의 세 단어만 첫 글자를 대문자로 바꾸고, `!`로 시작하는 마지막 단어는 그대로 둔다.

   </details>

2. **나이의 빌림:** Q4 풀이에서 현재 시각이 2024-03-01 00:00:00이고 생년월일이 2000-01-31이면 출력되는 나이는 무엇인가?

   <details>
   <summary>정답</summary>

   필드끼리 빼면 24년, 2월, -30일이다. 일이 음수이므로 `nowCal`을 2024년 2월로 옮겨 그 달의 날수 29를 더하면 -1일이 되고 월은 1이 된다. 풀이는 일을 한 번만 빌리므로 일이 -1로 남아 `24 Yrs  1 Mons -1 Days  0 hrs  0 min  0 sec`이 출력된다. 앞 달이 생일의 날짜(31일)보다 짧을 때 이런 결과가 나오며, 빌린 뒤에도 일이 음수이면 한 달을 더 빌리도록 반복하면 24년 0개월 30일로 바로잡을 수 있다.

   </details>

3. **탐욕 알고리즘:** Q7 풀이에 186760을 입력하면 어떤 단위가 몇 개씩 출력되는가?

   <details>
   <summary>정답</summary>

   50000원 3개(150000, 남은 금액 36760), 10000원 3개(6760), 5000원 1개(1760), 1000원 1개(760), 500원 1개(260), 100원 2개(60), 50원 1개(10), 10원 1개이다. 모두 13개이다.

   </details>

4. **탐욕 알고리즘의 조건:** 화폐 단위가 1, 5, 12인 나라에서 15원을 탐욕 알고리즘으로 나누면 최소 개수가 되는가?

   <details>
   <summary>정답</summary>

   되지 않는다. 탐욕 알고리즘은 12 + 1 + 1 + 1의 4개를 내지만, 5 + 5 + 5의 3개가 더 적다. 12가 5의 배수가 아니어서 각 단위가 아래 단위의 배수라는 조건이 깨지기 때문이다.

   </details>

5. **정규 표현식:** Q8 풀이에 `x1.2.3y45`를 입력하면 어떤 수들이 더해지는가?

   <details>
   <summary>정답</summary>

   `1.2`, `3`, `45`가 더해져 `1.2 + 3 + 45 = 49.2`가 출력된다. `find()`는 첫 위치부터 `1.2`를 찾고, 그다음 위치의 `.`은 숫자가 아니므로 건너뛰어 `3`을 찾으며, 마지막으로 `45`를 찾는다.

   </details>

6. **엄격 모드:** Q3 풀이에서 `sdf.setLenient(false)`를 지우고 `2025-02-30`을 입력하면 오늘이 2025-03-31일 때 어떤 결과가 나오는가?

   <details>
   <summary>정답</summary>

   관대한 해석은 2월 30일을 2월 28일의 이틀 뒤인 3월 2일로 넘기므로 오류가 나지 않고, 3월 2일은 3월 31일보다 29일 앞이므로 `29 day(s) have passed.`가 표시된다.

   </details>
