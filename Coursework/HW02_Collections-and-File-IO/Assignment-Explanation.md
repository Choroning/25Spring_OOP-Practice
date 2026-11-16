# HW2 Assignment Explanation — Collections and File I/O

[Repository Guide](../../README.md) | [English](Assignment-Explanation.md) | [한국어](Assignment-Explanation.ko.md)

> **Last Updated:** 2026-11-16

> **Learning Objectives:**
> 1. Interpret the structure of a binary file with a codebook and verify the restored result
> 2. Collect values into a separate collection for each type and save them to text files
> 3. Write a program that validates dialog input and asks again until it receives a valid value
> 4. Measure the running time of two sorting algorithms fairly and explain the result with time complexity

---

## Table of Contents

- [1. Assignment Layout and How to Run](#1-assignment-layout-and-how-to-run)
- [2. Requirements](#2-requirements)
- [3. Q1: Decrypting a Binary File with a Codebook](#3-q1-decrypting-a-binary-file-with-a-codebook)
  - [3.1 Structure of the Binary File](#31-structure-of-the-binary-file)
  - [3.2 Reading and Classifying](#32-reading-and-classifying)
  - [3.3 Results](#33-results)
- [4. Q4: A Dialog That Asks for a Name](#4-q4-a-dialog-that-asks-for-a-name)
- [5. Q7: Comparing the Speed of Quicksort and Bubble Sort](#5-q7-comparing-the-speed-of-quicksort-and-bubble-sort)
  - [5.1 Experimental Design](#51-experimental-design)
  - [5.2 Measurements and Interpretation](#52-measurements-and-interpretation)
- [Summary](#summary)
- [Self-Check Questions](#self-check-questions)

---

<br>

## 1. Assignment Layout and How to Run

> **Try It First:** This document contains the answers and solutions. Solve the problems in the [assignment handout](Original/Assignment.md) yourself before reading on.

This assignment has nine questions on binary file I/O, exception handling, class design, and comparing the running times of sorting algorithms. The original questions and example input and output are in the [assignment handout](Original/Assignment.md). This document covers Q1, Q4, and Q7, which have solutions; this repository has no solutions to the other questions.

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

*Figure 1. Directory layout of HW2*

| Question | Description | Solution File | Related Units |
|:-----|:-----|:-----|:-----|
| Q1 | Decrypting a binary file with a codebook | [Assignment2_BinaryFileDecryptor.java](Solution/Assignment2_BinaryFileDecryptor.java) | Lectures 9 and 10 |
| Q2 | Sorting eight integers in ascending order | None | - |
| Q3 | Real-time calculator | None | - |
| Q4 | A dialog that asks for a name | [Assignment2_GreetingDialog.java](Solution/Assignment2_GreetingDialog.java) | Lectures 2 and 4 |
| Q5 | The Student class | None | - |
| Q6 | The BankAccount class | None | - |
| Q7 | Comparing the speed of quicksort and bubble sort | [Assignment2_SortingComparison.java](Solution/Assignment2_SortingComparison.java) | Lectures 4 and 5 |
| Q8 | A system program that calculates date differences | None | - |
| Q9 | A used car management program | None | - |

*Table 1. Questions and solution files of HW2*

The Q1 solution reads `Q1_CodeBook.data` and `Q1.data` from the working directory and creates five result files in the same directory. Run it, therefore, in a directory to which the two files have been copied.

```bash
cd Coursework/HW02_Collections-and-File-IO
mkdir run && cp Original/Q1.data Original/Q1_CodeBook.data run/
javac -d run Solution/Assignment2_BinaryFileDecryptor.java
cd run && java Assignment2_BinaryFileDecryptor
```

Q4 uses a `JOptionPane` dialog, so run it in an environment with a display; Q7 runs anywhere with `javac` and `java`.

---

<br>

## 2. Requirements

Every program must follow the Javadoc comment and standard naming rules and must never crash because of incorrect user input. The requirements of the three questions with solutions are as follows.

| Question | Requirements |
|:-----|:-----|
| Q1 | Restore the binary file only in the order the codebook gives. Print the sum of all `double` values and the number of values of each type to confirm the restoration, and save the values of each type separately in five human-readable text files |
| Q4 | Receive a name through a dialog and print `Goodbye name from the OOP World!` |
| Q7 | Prove empirically, by measuring running time, the proposition "In general, the quick sort algorithm is faster than the bubblesort algorithm" |

*Table 2. Requirements of the questions with solutions*

---

<br>

## 3. Q1: Decrypting a Binary File with a Codebook

### 3.1 Structure of the Binary File

`Q1.data` has no separators or type markers between values; it holds only the bytes of primitive values one after another. `Q1_CodeBook.data` tells how many bytes to take at a time and as which type to read them. Line $i$ of the codebook is the type of value $i$, and its first eight lines are `double`, `char`, `int`, `char`, `double`, `boolean`, `boolean`, `int`. So the first 8 bytes of the file are a `double`, the next 2 bytes a `char`, and the next 4 bytes an `int`. Breaking this order even once misaligns every boundary after it, so, as the handout says, the binary file can be restored only in the order of the codebook.

The file is in the format written by `DataOutputStream`, so it is read with the methods of `DataInputStream` (Lecture 10, Section 3.2). The number of bytes each method reads is as follows.

| Type | Method | Bytes | Count in the Codebook | Bytes Used |
|:-----|:-----|-----:|-----:|-----:|
| `int` | `readInt()` | 4 | 11,939 | 47,756 |
| `boolean` | `readBoolean()` | 1 | 11,877 | 11,877 |
| `double` | `readDouble()` | 8 | 11,955 | 95,640 |
| `char` | `readChar()` | 2 | 11,875 | 23,750 |
| `long` | `readLong()` | 8 | 11,836 | 94,688 |
| Total | | | 59,482 | 273,711 |

*Table 3. Bytes per type and the size of `Q1.data`*

The codebook has 59,482 lines, and the sum of each type's count times its byte size, 273,711 bytes, is exactly the size of `Q1.data`. Reading along the codebook to the end therefore ends exactly at the last byte of the file. `DataInputStream` reads multi-byte values from the most significant byte first (big-endian). For example, reading the first 8 bytes of the file, `3f ed 3a dd bb e1 bf d3`, as a `double` gives 0.9134358090941482.

### 3.2 Reading and Classifying

The solution first reads the codebook line by line into a `List<String>` of type names. The codebook file ends its lines with CRLF; `BufferedReader.readLine()` strips the line terminator, and `trim()` removes any remaining whitespace.

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

Next, it keeps one `ArrayList` per type (Lecture 9), follows the codebook in order, chooses the right method with `switch`, and puts each value read into the matching list.

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

- **Chaining streams:** `FileInputStream` reads bytes, `BufferedInputStream` reads many bytes into memory at once to reduce file accesses, and `DataInputStream` turns those bytes into primitive values. Reading tens of thousands of times, the buffer helps speed.
- **try-with-resources:** When the block ends, the stream is closed automatically even if an exception occurs (Lecture 10, Section 4).
- **Exception handling:** If the file is shorter than the codebook, `EOFException` is caught, reported, and stops the loop. An unknown type name only produces a warning. The outer `catch (IOException e)` handles cases such as a missing file, so the program does not end with an exception.

After restoring, the program prints the sum of the `double` list and the size of each list, and the generic method `writeListToFile` writes each list to a text file, one value per line. This method takes a `List<T>` and writes each element's `toString()`, so one method handles the lists of all five types.

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

### 3.3 Results

```text
Sum of all double values: 5986.477978998287
=================================
Integer count:  11939
Boolean count:  11877
Double count:   11955
Char count:     11875
Long count:     11836
```

The count of every type matches Outcome 1 of the handout, and the first five lines of `Q1_Int.txt`, `Q1_Boolean.txt`, `Q1_Double.txt`, `Q1_Char.txt`, and `Q1_Long.txt` also match Table 1 of the handout. The output wording was chosen differently from the handout.

The sum of the `double` values differs from the handout's 5986.477978998308 in the last few digits. The solution adds them with `doubleList.stream().mapToDouble(Double::doubleValue).sum()`, and `DoubleStream.sum()` uses compensated summation, which reduces rounding error. Adding the same values one after another with `sum += v` in a `for` loop gives 5986.477978998308, as in the handout. Floating-point addition rounds every time, so the last digits can depend on how the values are added, while the restored values themselves are the same.

---

<br>

## 4. Q4: A Dialog That Asks for a Name

The program receives a name through a dialog and prints `Goodbye name from the OOP World!`. Following the common rule that wrong input must not end the program, it asks again until it receives a name made only of letters.

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

`String.matches` checks whether the **whole** string matches the regular expression. `[A-Za-z]+` means one or more letters, and `^` and `$` mean the start and the end (`matches` already compares the whole string, so the result is the same without them). An empty string, digits, or a name containing a space therefore shows an error dialog and asks again through `continue`. Pressing Cancel returns `null`, so the program prints a message and ends normally.

```text
[input] Min seok
[error dialog] Invalid name. Please enter letters only.
[input] Minseok
Goodbye Minseok from the OOP World!
```

---

<br>

## 5. Q7: Comparing the Speed of Quicksort and Bubble Sort

### 5.1 Experimental Design

Showing the proposition empirically requires measuring the two algorithms **under the same conditions**. The solution makes one random integer array of size 10,000 (values from 0 up to but not including 100,000), creates two identical copies with `Arrays.copyOf`, and gives one to each algorithm. If one algorithm sorted the array that the other had already sorted, it would receive an already sorted input and the comparison would be unfair.

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

The time is measured just before and just after each sort with `System.nanoTime()`, covered in Lecture 4, and converted to milliseconds. At the end, `Arrays.equals` checks that the two results are the same, showing that the faster algorithm is not fast because it sorted incorrectly.

Bubble sort repeatedly compares two neighboring elements and swaps them if they are out of order, and it stops when a full pass makes no swap, since the array is then sorted (the `swapped` flag). Quicksort takes the last element as the pivot, partitions the array by gathering the elements smaller than the pivot at the front (Lomuto partitioning), and recursively sorts the parts to the left and right of the pivot.

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

### 5.2 Measurements and Interpretation

The results of three runs with Java 24 on an Apple M2 Pro are as follows. Running times differ between computers and between runs.

| Run | Bubble Sort (ms) | Quicksort (ms) | Ratio | Results Match |
|:-----|-----:|-----:|-----:|:-----|
| 1 | 50.907 | 1.202 | 42.4x | true |
| 2 | 48.342 | 1.155 | 41.9x | true |
| 3 | 47.388 | 1.021 | 46.4x | true |

*Table 4. Running times measured on random arrays of size 10,000*

Quicksort was more than 40 times faster every time. The number of comparisons explains the difference. With $n$ elements, bubble sort makes at most $n(n-1)/2$ comparisons, and on a random array swaps continue almost to the end, so early termination does not help much. For $n = 10{,}000$ that is about 50 million. Quicksort makes about $2n \ln n$ comparisons on average, about 180,000. In terms of time complexity, bubble sort is $O(n^2)$ and quicksort is $O(n \log n)$ on average; when $n$ doubles, the time of bubble sort grows about four times and that of quicksort slightly more than twice.

> **Caution:** The ratio of running times is not equal to the ratio of comparisons (about 270 times). Factors outside the algorithm, such as the cost of a single comparison and swap and the moment JIT compilation happens, also enter the time. The solution measures each sort only once, so repeating the measurement and averaging, swapping the order, and increasing the array size would make the evidence stronger. Also, as the qualifier "In general" suggests, quicksort with the last element as the pivot partitions lopsidedly on an already sorted array and becomes $O(n^2)$, and in that case bubble sort with early termination finishes after one pass ($n - 1$ comparisons) and is faster.

---

<br>

## Summary

| Concept | Key Summary |
|:-----|:-----|
| Codebook and binary file | A binary file has no separators, so the value boundaries line up only when it is read in the codebook's order of types. |
| `DataInputStream` | `readInt` reads 4 bytes, `readBoolean` 1, `readDouble` and `readLong` 8, and `readChar` 2, in big-endian order, and turns them into primitive values. |
| Verifying the restoration | The sum of each type's count times its byte size, 273,711, equals the file size, and the counts and first values match the handout. |
| Floating-point sums | The compensated summation of `DoubleStream.sum()` and a simple loop sum can differ in the last digits. |
| Repeating input validation | Inside `while (true)`, the program breaks only when `matches("^[A-Za-z]+$")` is true, and Cancel (`null`) is handled as a normal exit. |
| Fair measurement | Give copies of the same array to both algorithms, measure with `System.nanoTime()`, and check that the results are the same. |
| Time complexity of the sorts | Bubble sort is $O(n^2)$ and quicksort is $O(n \log n)$ on average, and at size 10,000 quicksort was more than 40 times faster. |

---

<br>

## Self-Check Questions

1. **Byte boundaries:** If the first five lines of the codebook are `double`, `char`, `int`, `char`, and `double`, at which byte of `Q1.data` does the fifth value start (counting the first byte as byte 0)?

   <details>
   <summary>Answer</summary>

   The first four values take 8 + 2 + 4 + 2 = 16 bytes, so it starts at byte 16.

   </details>

2. **Reading out of order:** If `Q1.data` is read from the start entirely with `readInt()`, ignoring the codebook, how many `int` values are read and what happens?

   <details>
   <summary>Answer</summary>

   Since 273,711 = 4 × 68,427 + 3, 68,427 `int` values are read, and then the remaining 3 bytes cannot make an `int`, so `EOFException` occurs. The values read are also meaningless because they do not line up with the boundaries of the original values.

   </details>

3. **matches:** What happens when `  Minseok  ` (with leading and trailing spaces), `Min-seok`, and an empty string are each entered into the dialog of the Q4 solution?

   <details>
   <summary>Answer</summary>

   `  Minseok  ` becomes `Minseok` through `trim()`, passes, and prints `Goodbye Minseok from the OOP World!`. `Min-seok` does not match the regular expression because of `-`, and the empty string does not match because `+` requires one or more letters, so each shows an error dialog and the name is asked again. Unlike Cancel, the empty string is not `null`, so the program does not end.

   </details>

4. **Fair comparison:** In the Q7 solution, what happens to the measurements if no copies are made and `quickSort(original, ...)` runs after `bubbleSort(original)`?

   <details>
   <summary>Answer</summary>

   Quicksort receives an already sorted array. Partitioning with the last element as the pivot leaves all elements except the pivot on one side of a sorted array, so the comparisons grow to about $n(n-1)/2$, and at size 10,000 quicksort becomes more than ten times slower than on a random array (measured example: from about 1 ms to about 15 ms). The recursion depth also approaches $n$, so at size 100,000 a `StackOverflowError` occurs. The two algorithms are no longer compared on the same input, so the result is not evidence for the proposition.

   </details>

5. **Change with size:** If bubble sort took 50 ms at size 10,000, roughly how long would it be expected to take at size 20,000?

   <details>
   <summary>Answer</summary>

   The number of comparisons is proportional to $n^2$, so doubling the size is expected to take about four times as long, around 200 ms. Actual values can deviate from this expectation because of factors such as caches and JIT.

   </details>
