# HW2 과제 해설 — 컬렉션과 파일 입출력

[레포지토리 안내](../../README.ko.md) | [English](Assignment-Explanation.md) | [한국어](Assignment-Explanation.ko.md)

> **최종 수정일:** 2026-11-27

> **학습 목표:**
> 1. 바이너리 파일의 구조를 코드북으로 해석하고 복원 결과를 검증할 수 있다
> 2. 자료형마다 다른 컬렉션에 값을 모으고 텍스트 파일로 저장할 수 있다
> 3. 대화 상자의 입력을 검증하여 올바른 값을 받을 때까지 다시 묻는 프로그램을 작성할 수 있다
> 4. 두 정렬 알고리즘의 실행 시간을 공정하게 측정하고 결과를 시간 복잡도로 설명할 수 있다

---

## 목차

- [1. 과제 구성과 실행 방법](#1-과제-구성과-실행-방법)
- [2. 요구사항](#2-요구사항)
- [3. Q1: 코드북에 따른 바이너리 파일 복호화](#3-q1-코드북에-따른-바이너리-파일-복호화)
  - [3.1 바이너리 파일의 구조](#31-바이너리-파일의-구조)
  - [3.2 읽기와 분류](#32-읽기와-분류)
  - [3.3 실행 결과](#33-실행-결과)
- [4. Q4: 이름을 받는 대화 상자](#4-q4-이름을-받는-대화-상자)
- [5. Q7: 퀵 정렬과 버블 정렬의 속도 비교](#5-q7-퀵-정렬과-버블-정렬의-속도-비교)
  - [5.1 실험 설계](#51-실험-설계)
  - [5.2 측정 결과와 해석](#52-측정-결과와-해석)
- [요약](#요약)
- [점검 문제](#점검-문제)

---

<br>

## 1. 과제 구성과 실행 방법

> **풀어 보기:** 이 문서는 정답과 풀이를 담고 있다. 먼저 [과제 안내문](Original/Assignment.ko.md)의 문제를 직접 풀어 본 뒤 읽는다.

바이너리 파일 입출력, 예외 처리, 클래스 설계, 정렬 알고리즘의 실행 시간 비교를 다루는 아홉 문제의 과제이다. 문제의 원문과 예시 입출력은 [과제 안내문](Original/Assignment.ko.md)에 있다. 이 문서는 풀이가 있는 Q1, Q4, Q7을 다루며, 나머지 문제의 풀이는 이 레포지토리에 없다.

```text
Coursework/HW02_Collections-and-File-IO/
├── Original/
│   ├── Assignment.ko.md                     # assignment handout (Korean)
│   ├── Assignment.md                        # assignment handout (English)
│   ├── Q1.data                              # binary file for Q1 (distributed original)
│   ├── Q1_CodeBook.data                     # codebook for Q1 (distributed original)
│   └── Q9.Data                              # used car data for Q9 (distributed original)
├── Solution/
│   ├── Assignment2_BinaryFileDecryptor.java # Q1
│   ├── Assignment2_GreetingDialog.java      # Q4
│   └── Assignment2_SortingComparison.java   # Q7
├── Assignment-Explanation.ko.md             # assignment explanation (Korean)
└── Assignment-Explanation.md                # assignment explanation (English)
```

*그림 1. HW2 과제의 디렉토리 구성*

| 문제 | 내용 | 풀이 파일 | 관련 단원 |
|:-----|:-----|:-----|:-----|
| Q1 | 코드북에 따른 바이너리 파일 복호화 | [Assignment2_BinaryFileDecryptor.java](Solution/Assignment2_BinaryFileDecryptor.java) | 9강, 10강 |
| Q2 | 정수 8개의 오름차순 정렬 | 없음 | - |
| Q3 | 실시간 계산기 | 없음 | - |
| Q4 | 이름을 받는 대화 상자 | [Assignment2_GreetingDialog.java](Solution/Assignment2_GreetingDialog.java) | 2강, 4강 |
| Q5 | Student 클래스 | 없음 | - |
| Q6 | BankAccount 클래스 | 없음 | - |
| Q7 | 퀵 정렬과 버블 정렬의 속도 비교 | [Assignment2_SortingComparison.java](Solution/Assignment2_SortingComparison.java) | 4강, 5강 |
| Q8 | 날짜 차이를 계산하는 시스템 프로그램 | 없음 | - |
| Q9 | 중고차 관리 프로그램 | 없음 | - |

*표 1. HW2의 문제와 풀이 파일*

Q1 풀이는 작업 디렉토리에서 `Q1_CodeBook.data`와 `Q1.data`를 읽고, 같은 디렉토리에 결과 파일 다섯 개를 만든다. 따라서 두 파일을 복사한 디렉토리에서 실행한다.

```bash
cd Coursework/HW02_Collections-and-File-IO
mkdir run && cp Original/Q1.data Original/Q1_CodeBook.data run/
javac -d run Solution/Assignment2_BinaryFileDecryptor.java
cd run && java Assignment2_BinaryFileDecryptor
```

Q4는 `JOptionPane` 대화 상자를 쓰므로 화면이 있는 환경에서, Q7은 어디서나 `javac`와 `java`로 실행한다.

---

<br>

## 2. 요구사항

모든 프로그램은 Javadoc 주석과 표준 명명 규칙을 지켜야 하고, 사용자가 잘못 입력해도 절대 종료되면 안 된다. 풀이가 있는 세 문제의 요구사항은 다음과 같다.

| 문제 | 요구사항 |
|:-----|:-----|
| Q1 | 코드북이 정한 순서대로만 바이너리 파일을 복원한다. 모든 `double` 값의 합과 자료형별 값의 개수를 출력해 복원을 확인하고, 자료형마다 값을 사람이 읽을 수 있는 텍스트 파일 다섯 개로 나누어 저장한다 |
| Q4 | 대화 상자로 이름을 받아 `Goodbye 이름 from the OOP World!`를 출력한다 |
| Q7 | "In general, the quick sort algorithm is faster than the bubblesort algorithm"이라는 명제를 실행 시간 측정으로 경험적으로 증명한다 |

*표 2. 풀이가 있는 문제의 요구사항*

---

<br>

## 3. Q1: 코드북에 따른 바이너리 파일 복호화

### 3.1 바이너리 파일의 구조

`Q1.data`에는 값과 값 사이에 구분 기호나 자료형 표시가 없고, 기본형 값의 바이트들만 이어져 있다. 바이트를 몇 개씩 끊어 어떤 자료형으로 읽어야 하는지는 `Q1_CodeBook.data`가 알려 준다. 코드북의 $i$번째 줄이 $i$번째 값의 자료형이며, 첫 여덟 줄은 `double`, `char`, `int`, `char`, `double`, `boolean`, `boolean`, `int`이다. 따라서 파일의 처음 8바이트는 `double`, 그다음 2바이트는 `char`, 그다음 4바이트는 `int`이다. 이 순서를 하나라도 어기면 그 뒤의 모든 경계가 어긋나므로, 안내문이 말하듯 바이너리 파일은 코드북의 순서로만 복원할 수 있다.

이 파일은 `DataOutputStream`이 쓴 형식이므로 `DataInputStream`의 메서드로 읽는다(10강 3.2절). 각 메서드가 읽는 바이트 수는 다음과 같다.

| 자료형 | 읽는 메서드 | 바이트 수 | 코드북의 개수 | 차지하는 바이트 |
|:-----|:-----|-----:|-----:|-----:|
| `int` | `readInt()` | 4 | 11,939 | 47,756 |
| `boolean` | `readBoolean()` | 1 | 11,877 | 11,877 |
| `double` | `readDouble()` | 8 | 11,955 | 95,640 |
| `char` | `readChar()` | 2 | 11,875 | 23,750 |
| `long` | `readLong()` | 8 | 11,836 | 94,688 |
| 합계 | | | 59,482 | 273,711 |

*표 3. 자료형별 바이트 수와 `Q1.data`의 크기*

코드북은 59,482줄이고, 자료형별 개수에 바이트 수를 곱해 더한 273,711바이트는 `Q1.data`의 크기와 정확히 같다. 코드북을 끝까지 따라 읽으면 파일의 마지막 바이트에서 정확히 끝난다는 뜻이다. `DataInputStream`은 여러 바이트로 된 값을 높은 자리 바이트부터 읽는다(빅 엔디언). 예를 들어 파일의 처음 8바이트 `3f ed 3a dd bb e1 bf d3`을 `double`로 읽으면 0.9134358090941482이다.

### 3.2 읽기와 분류

풀이는 먼저 코드북을 줄 단위로 읽어 자료형 이름의 `List<String>`을 만든다. 코드북 파일은 줄 끝이 CRLF인데, `BufferedReader.readLine()`이 줄 끝 문자를 떼어 내고 `trim()`이 남은 공백을 지운다.

```java
private static List<String> loadCodebook(String filename) throws IOException {
    List<String> codebook = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (!line.isEmpty()) {
                codebook.add(line);
            }
        }
    }
    return codebook;
}
```

그다음 자료형마다 `ArrayList`를 하나씩 두고(9강), 코드북을 차례로 따라가며 `switch`로 알맞은 메서드를 골라 읽은 값을 해당 리스트에 넣는다.

```java
try (DataInputStream dis = new DataInputStream(
        new BufferedInputStream(new FileInputStream("Q1.data")))) {
    for (String type : codebook) {
        try {
            switch (type.toLowerCase()) {
                case "int": case "integer":
                    intList.add(dis.readInt()); break;
                case "boolean":
                    boolList.add(dis.readBoolean()); break;
                case "double":
                    doubleList.add(dis.readDouble()); break;
                case "char":
                    charList.add(dis.readChar()); break;
                case "long":
                    longList.add(dis.readLong()); break;
                default:
                    System.err.println("Unknown type: " + type);
            }
        } catch (EOFException eof) {
            System.err.println("Reached end of file unexpectedly.");
            break;
        }
    }
}
```

- **스트림의 연결:** `FileInputStream`은 바이트를 읽고, `BufferedInputStream`은 한 번에 여러 바이트를 메모리로 읽어 두어 파일 접근 횟수를 줄이며, `DataInputStream`은 그 바이트들을 기본형 값으로 바꾼다. 수만 번 읽으므로 버퍼가 속도에 도움이 된다.
- **try-with-resources:** 블록이 끝나면 예외가 나도 스트림이 자동으로 닫힌다(10강 4절).
- **예외 처리:** 코드북보다 파일이 짧으면 `EOFException`을 잡아 알리고 반복을 멈춘다. 모르는 자료형 이름은 경고만 하고 넘어간다. 바깥의 `catch (IOException e)`가 파일을 찾지 못한 경우 등을 처리하므로 프로그램이 예외로 끝나지 않는다.

복원이 끝나면 `double` 리스트의 합과 각 리스트의 크기를 출력하고, 제네릭 메서드 `writeListToFile`로 리스트마다 한 줄에 값 하나씩 텍스트 파일에 쓴다. 이 메서드는 `List<T>`를 받아 각 원소의 `toString()`을 쓰므로, 다섯 자료형의 리스트를 하나의 메서드로 처리한다.

```java
private static <T> void writeListToFile(String filename, List<T> list) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
        for (T item : list) {
            writer.write(item.toString());
            writer.newLine();
        }
    } catch (IOException e) {
        System.err.println("Error writing " + filename + ": " + e.getMessage());
    }
}
```

### 3.3 실행 결과

```text
Sum of all double values: 5986.477978998287
=================================
Integer count:  11939
Boolean count:  11877
Double count:   11955
Char count:     11875
Long count:     11836
```

자료형별 개수는 안내문의 결과 1과 모두 같고, `Q1_Int.txt`, `Q1_Boolean.txt`, `Q1_Double.txt`, `Q1_Char.txt`, `Q1_Long.txt`의 첫 다섯 줄도 안내문의 표 1과 같다. 출력 문구는 안내문과 다르게 정했다.

`double` 값의 합은 안내문의 5986.477978998308과 마지막 몇 자리가 다르다. 풀이는 `doubleList.stream().mapToDouble(Double::doubleValue).sum()`으로 더하는데, `DoubleStream.sum()`은 반올림 오차를 줄이는 보정 합산(compensated summation)을 쓴다. 같은 값들을 `for` 반복문에서 `sum += v`로 차례로 더하면 안내문과 같은 5986.477978998308이 나온다. 부동소수점 덧셈은 매번 반올림하므로 더하는 방법에 따라 마지막 자리가 달라질 수 있으며, 복원된 값 자체는 같다.

---

<br>

## 4. Q4: 이름을 받는 대화 상자

이름을 대화 상자로 받아 `Goodbye 이름 from the OOP World!`를 출력한다. 공통 규칙대로 잘못된 입력에도 끝나지 않도록, 영문자로만 된 이름을 받을 때까지 다시 묻는다.

```java
while (true) {
    name = JOptionPane.showInputDialog(null,
            "What is your first name?",
            "Greeting",
            JOptionPane.QUESTION_MESSAGE);

    if (name == null) {
        System.out.println("No name entered. Program terminates.");
        return;
    }

    name = name.trim();

    if (!name.matches("^[A-Za-z]+$")) {
        JOptionPane.showMessageDialog(null,
                "Invalid name. Please enter letters only.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        continue;
    }
    break;
}

System.out.println("Goodbye " + name + " from the OOP World!");
```

`String.matches`는 문자열 **전체**가 정규 표현식에 맞는지 검사한다. `[A-Za-z]+`는 영문자 하나 이상이고, `^`와 `$`는 처음과 끝을 뜻한다(`matches`는 원래 전체를 비교하므로 없어도 결과가 같다). 그래서 빈 문자열, 숫자, 공백이 들어간 이름은 오류 대화 상자를 띄운 뒤 `continue`로 다시 묻는다. 취소를 누르면 `null`이 돌아오므로 안내를 출력하고 정상적으로 끝낸다.

```text
[input] Min seok
[error dialog] Invalid name. Please enter letters only.
[input] Minseok
Goodbye Minseok from the OOP World!
```

---

<br>

## 5. Q7: 퀵 정렬과 버블 정렬의 속도 비교

### 5.1 실험 설계

명제를 경험적으로 보이려면 두 알고리즘을 **같은 조건**에서 측정해야 한다. 풀이는 크기 10,000의 무작위 정수 배열(값은 0 이상 100,000 미만)을 하나 만들고, `Arrays.copyOf`로 똑같은 복사본 두 개를 만들어 각 알고리즘에 하나씩 준다. 한 알고리즘이 정렬한 배열을 다른 알고리즘이 다시 정렬하면 이미 정렬된 입력을 받게 되어 비교가 불공정해지기 때문이다.

```java
int[] bubbleArray = Arrays.copyOf(original, original.length);
int[] quickArray = Arrays.copyOf(original, original.length);

// Bubble Sort
long bubbleStart = System.nanoTime();
bubbleSort(bubbleArray);
double bubbleMs = (System.nanoTime() - bubbleStart) / 1_000_000.0;

// Quick Sort
long quickStart = System.nanoTime();
quickSort(quickArray, 0, quickArray.length - 1);
double quickMs = (System.nanoTime() - quickStart) / 1_000_000.0;
```

시간은 4강에서 다룬 `System.nanoTime()`으로 정렬 직전과 직후를 재어 밀리초로 바꾼다. 마지막에 `Arrays.equals`로 두 결과가 같은지 확인하여, 빠른 쪽이 잘못 정렬해서 빠른 것이 아님을 보인다.

버블 정렬은 이웃한 두 원소를 비교해 순서가 틀리면 바꾸는 일을 반복하며, 한 바퀴 동안 한 번도 바꾸지 않으면 이미 정렬된 것이므로 멈춘다(`swapped` 플래그). 퀵 정렬은 마지막 원소를 피벗으로 삼아 피벗보다 작은 원소를 앞으로 모으는 분할(Lomuto 분할)을 한 뒤, 피벗의 왼쪽과 오른쪽을 재귀적으로 정렬한다.

```java
private static int partition(int[] arr, int low, int high) {
    int pivot = arr[high];
    int i = low - 1;
    for (int j = low; j < high; j++) {
        if (arr[j] < pivot) {
            i++;
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
    int temp = arr[i + 1];
    arr[i + 1] = arr[high];
    arr[high] = temp;
    return i + 1;
}
```

### 5.2 측정 결과와 해석

Java 24, Apple M2 Pro에서 세 번 실행한 결과는 다음과 같다. 실행 시간은 컴퓨터와 실행마다 다르다.

| 실행 | 버블 정렬 (ms) | 퀵 정렬 (ms) | 배율 | 결과 일치 |
|:-----|-----:|-----:|-----:|:-----|
| 1 | 50.907 | 1.202 | 42.4배 | true |
| 2 | 48.342 | 1.155 | 41.9배 | true |
| 3 | 47.388 | 1.021 | 46.4배 | true |

*표 4. 크기 10,000의 무작위 배열에서 측정한 실행 시간*

세 번 모두 퀵 정렬이 40배 이상 빨랐다. 이 차이는 비교 횟수로 설명된다. 버블 정렬은 원소가 $n$개이면 최대 $n(n-1)/2$번 비교하며, 무작위 배열에서는 거의 끝까지 교환이 일어나 조기 종료도 크게 도움이 되지 않는다. $n = 10{,}000$이면 약 5천만 번이다. 퀵 정렬은 평균 약 $2n \ln n$번 비교하므로 약 18만 번이다. 시간 복잡도로는 버블 정렬이 $O(n^2)$, 퀵 정렬이 평균 $O(n \log n)$이며, $n$이 두 배가 되면 버블 정렬의 시간은 약 네 배, 퀵 정렬의 시간은 두 배를 조금 넘게 늘어난다.

> **주의:** 실행 시간의 비는 비교 횟수의 비(약 270배)와 같지 않다. 비교와 교환 한 번의 비용, JIT 컴파일이 일어나는 시점처럼 알고리즘 밖의 요인도 시간에 들어가기 때문이다. 풀이는 한 번씩만 재므로, 측정을 여러 번 반복해 평균을 내거나 순서를 바꾸고 배열 크기를 늘려 가며 재면 증거가 더 강해진다. 또 "In general"이라는 단서처럼, 마지막 원소를 피벗으로 삼는 퀵 정렬은 이미 정렬된 배열에서 분할이 한쪽으로 치우쳐 $O(n^2)$이 되고, 그때는 조기 종료하는 버블 정렬이 한 바퀴($n - 1$번 비교) 만에 끝나 더 빠르다.

---

<br>

## 요약

| 개념 | 핵심 요약 |
|:-----|:-----|
| 코드북과 바이너리 파일 | 바이너리 파일에는 구분 기호가 없으므로 코드북의 자료형 순서대로 읽어야 값의 경계가 맞는다. |
| `DataInputStream` | `readInt`는 4, `readBoolean`은 1, `readDouble`과 `readLong`은 8, `readChar`는 2바이트를 빅 엔디언으로 읽어 기본형 값으로 바꾼다. |
| 복원의 검증 | 자료형별 개수와 바이트 수의 곱의 합 273,711이 파일 크기와 같고, 개수와 첫 값들이 안내문과 같다. |
| 부동소수점 합 | `DoubleStream.sum()`의 보정 합산과 단순 반복의 합은 마지막 자리가 다를 수 있다. |
| 입력 검증 반복 | `while (true)` 안에서 `matches("^[A-Za-z]+$")`가 참일 때만 `break`하고, 취소(`null`)는 정상 종료로 처리한다. |
| 공정한 측정 | 같은 배열의 복사본을 두 알고리즘에 주고, `System.nanoTime()`으로 재고, 결과가 같은지 확인한다. |
| 정렬의 시간 복잡도 | 버블 정렬은 $O(n^2)$, 퀵 정렬은 평균 $O(n \log n)$이며, 크기 10,000의 측정(표 4)에서 퀵 정렬이 40배 이상 빨랐다. |

---

<br>

## 점검 문제

1. **바이트 경계:** 코드북의 첫 다섯 줄이 `double`, `char`, `int`, `char`, `double`일 때, 다섯 번째 값은 `Q1.data`의 몇 번째 바이트부터 시작하는가(첫 바이트를 0번째로 센다)?

   <details>
   <summary>정답</summary>

   앞의 네 값이 8 + 2 + 4 + 2 = 16바이트를 차지하므로 16번째 바이트부터 시작한다.

   </details>

2. **순서를 어긴 읽기:** 코드북을 무시하고 `Q1.data`를 처음부터 모두 `readInt()`로 읽으면 몇 개의 `int`를 읽고 무슨 일이 생기는가?

   <details>
   <summary>정답</summary>

   273,711 = 4 × 68,427 + 3이므로 `int` 68,427개를 읽은 뒤, 남은 3바이트로는 `int`를 만들 수 없어 `EOFException`이 난다. 읽은 값들도 원래 값의 경계와 맞지 않아 의미가 없다.

   </details>

3. **matches:** Q4 풀이의 대화 상자에 `  Minseok  `(앞뒤 공백), `Min-seok`, 빈 문자열을 입력하면 각각 어떻게 되는가?

   <details>
   <summary>정답</summary>

   `  Minseok  `은 `trim()`으로 `Minseok`이 되어 통과하고 `Goodbye Minseok from the OOP World!`를 출력한다. `Min-seok`은 `-` 때문에, 빈 문자열은 `+`가 영문자 하나 이상을 요구하기 때문에 정규 표현식에 맞지 않으므로, 오류 대화 상자를 띄운 뒤 이름을 다시 묻는다. 빈 문자열은 취소와 달리 `null`이 아니므로 종료되지 않는다.

   </details>

4. **공정한 비교:** Q7 풀이에서 복사본을 만들지 않고 `bubbleSort(original)` 뒤에 `quickSort(original, ...)`를 실행하면 측정 결과는 어떻게 되는가?

   <details>
   <summary>정답</summary>

   퀵 정렬이 이미 정렬된 배열을 받는다. 마지막 원소를 피벗으로 삼는 분할은 정렬된 배열에서 피벗 외의 원소를 모두 한쪽에 남겨 비교가 약 $n(n-1)/2$번으로 늘어나므로, 크기 10,000에서 퀵 정렬이 무작위 배열일 때보다 열 배 넘게 느려진다(측정 예: 약 1 ms에서 20 ms 남짓). 재귀 깊이도 $n$에 가까워져 크기 100,000이면 `StackOverflowError`가 난다. 두 알고리즘을 같은 입력에서 비교한 것이 아니므로 명제의 증거가 되지 못한다.

   </details>

5. **크기에 따른 변화:** 버블 정렬이 크기 10,000에서 50 ms 걸렸다면, 크기 20,000에서는 대략 얼마가 걸릴 것으로 예상되는가?

   <details>
   <summary>정답</summary>

   비교 횟수가 $n^2$에 비례하므로 크기가 두 배이면 약 네 배인 200 ms 정도가 예상된다. 실제 값은 캐시와 JIT 같은 요인으로 이 예상에서 벗어날 수 있다.

   </details>
