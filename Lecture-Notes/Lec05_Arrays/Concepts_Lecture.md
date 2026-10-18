# Lecture 5 — Arrays

[Repository Guide](../../README.md) | [English](Concepts_Lecture.md) | [한국어](Concepts_Lecture.ko.md)

> **Last Updated:** 2026-10-19

> **Learning Objectives:**
> 1. Declare and manipulate one-dimensional arrays
> 2. Apply array algorithms (search, sort, copy)
> 3. Use the Arrays utility class and enhanced for loop
> 4. Explain with memory states that assigning array variables and passing arrays to methods share the same array

---

## Table of Contents

- [1. Array Fundamentals](#1-array-fundamentals)
  - [1.1 Why Arrays Are Needed](#11-why-arrays-are-needed)
  - [1.2 Declaration and Initialization](#12-declaration-and-initialization)
  - [1.3 Array Access and Length](#13-array-access-and-length)
  - [1.4 Default Values](#14-default-values)
  - [1.5 Array Sharing](#15-array-sharing)
- [2. Array Operations](#2-array-operations)
  - [2.1 Array Processing Example: Rainfall](#21-array-processing-example-rainfall)
  - [2.2 Finding Maximum and Minimum](#22-finding-maximum-and-minimum)
  - [2.3 Computing the Mean](#23-computing-the-mean)
  - [2.4 Array Traversal Patterns](#24-array-traversal-patterns)
  - [2.5 Linear Search](#25-linear-search)
- [3. Arrays as Method Parameters](#3-arrays-as-method-parameters)
- [4. The Arrays Utility Class](#4-the-arrays-utility-class)
- [5. Common Pitfalls](#5-common-pitfalls)
- [Summary](#summary)
- [Self-Check Questions](#self-check-questions)

---

<br>

## 1. Array Fundamentals

### 1.1 Why Arrays Are Needed

An array is a collection of data values. If a program needs to deal with many values, such as 100 integers, 500 `Account` objects, or 365 real numbers, it uses an array. In Java, an array is an indexed collection of data values of the same type.

For example, declaring 10 integers as 10 separate variables (`i0`-`i9`) means that computing their sum requires listing every variable, as in `sum = i0+i1+i2+i3+i4+i5+i6+i7+i8+i9;`. Declaring a single array `i` lets a `for` loop process them.

```java
int i[] = new int[10];
// ... store values in i[0] through i[9] ...
for (sum = 0, n = 0; n < 10; n++)
    sum += i[n];
```

```text
int i0, i1, ..., i9;              int i[] = new int[10];

 i0 [ 4]   i5 [ 2]                 i
 i1 [55]   i6 [99]                ┌───┐     ┌────┐
 i2 [32]   i7 [13]                │ ●─┼───> │  4 │ i[0]
 i3 [28]   i8 [43]                └───┘     │ 55 │ i[1]
 i4 [35]   i9 [65]                          │ 32 │ i[2]
                                            │ 28 │ i[3]
 sum = i0+i1+...+i9;                        │ 35 │ i[4]
                                            │  2 │ i[5]
                                            │ 99 │ i[6]
                                            │ 13 │ i[7]
                                            │ 43 │ i[8]
                                            │ 65 │ i[9]
                                            └────┘
                                   for (...) sum += i[n];
```

*Figure 1. Ten separate variables compared with an array of ten elements*

### 1.2 Declaration and Initialization

Arrays in Java are objects that store a fixed number of elements of the same type. An array declaration takes one of two forms, `<data type>[] <variable>` or `<data type> <variable>[]`, and an array creation has the form `<variable> = new <data type>[<size>]`.

```java
// Declaration and allocation
int[] scores = new int[5];

// Declaration with initialization
int[] values = {10, 20, 30, 40, 50};

// Alternative syntax (C-style, less common in Java)
int numbers[] = new int[3];
```

> **Key Point:** Array size is fixed at creation time. For dynamic sizing, use `ArrayList` from the Collections framework.

An array variable holds a reference to the array object, and the elements are stored in order inside that array object.

```java
int intArray[] = {4, 3, 2, 1, 0};
double doubleArray[] = {0.01, 0.02, 0.03, 0.04};
```

```text
intArray                         doubleArray
┌───┐     ┌───┐                  ┌───┐     ┌──────┐
│ ●─┼───> │ 4 │ intArray[0]      │ ●─┼───> │ 0.01 │ doubleArray[0]
└───┘     │ 3 │ intArray[1]      └───┘     │ 0.02 │ doubleArray[1]
          │ 2 │ intArray[2]                │ 0.03 │ doubleArray[2]
          │ 1 │ intArray[3]                │ 0.04 │ doubleArray[3]
          │ 0 │ intArray[4]                └──────┘
          └───┘
```

*Figure 2. An int array and a double array created with initializer lists*

Like other data types, an array can be declared and initialized at the same time, and the size of the array is then determined by the number of initial values.

```java
int[] number = { 2, 4, 6, 8 };
double[] samplingData = { 2.443, 8.99, 12.3, 45.009, 18.2,
                          9.00, 3.123, 22.084, 18.08 };
String[] monthName = { "January", "February", "March",
                       "April", "May", "June", "July",
                       "August", "September", "October",
                       "November", "December" };
```

`number.length` is 4, `samplingData.length` is 9, and `monthName.length` is 12.

The size of an array does not change once it is created, but the size can be decided at run time. The following code prompts the user for the size of an array and creates an array of that size.

```java
int size;
int[] number;
size = Integer.parseInt(JOptionPane.showInputDialog(null,
        "Size of an array:"));
number = new int[size];
```

### 1.3 Array Access and Length

Array elements are accessed by zero-based index. The `length` property returns the number of elements.

```java
int[] arr = {10, 20, 30};
System.out.println(arr[0]);      // 10
System.out.println(arr.length);  // 3
```

Accessing an index outside `[0, length - 1]` throws `ArrayIndexOutOfBoundsException`.

An expression such as `rainfall[2]`, an array name followed by an index, is called an indexed expression and refers to the element at position 2. The index of the first position in an array is 0.

```text
double[] rainfall = new double[12];

rainfall
┌───┐       0     1     2     3     4     5     6     7     8     9     10    11
│ ●─┼──> ┌─────┬─────┬─────┬─────┬─────┬─────┬─────┬─────┬─────┬─────┬─────┬─────┐
└───┘    │     │     │  ▲  │     │     │     │     │     │     │     │     │     │
         └─────┴─────┴──┼──┴─────┴─────┴─────┴─────┴─────┴─────┴─────┴─────┴─────┘
                   rainfall[2]
```

*Figure 3. The 12-element array rainfall and the indexed expression rainfall[2]*

### 1.4 Default Values

| Type | Default Value |
|:-----|:-------------|
| `int`, `long`, `short`, `byte` | `0` |
| `float`, `double` | `0.0` |
| `boolean` | `false` |
| `char` | `'\u0000'` |
| Object references | `null` |

*Table 1. Default values of array elements created with new*

### 1.5 Array Sharing

Assigning an array variable to another array variable does not copy the array; both variables refer to the same array object.

```java
int intArray[] = new int[5];
int myArray[] = intArray;

intArray[1] = 2;
myArray[1] = 6;
```

```text
After intArray[1] = 2;                      After myArray[1] = 6;

intArray ●──┐                               intArray ●──┐
            ├──> [ 0 | 2 | 0 | 0 | 0 ]                  ├──> [ 0 | 6 | 0 | 0 | 0 ]
myArray  ●──┘                               myArray  ●──┘
```

*Figure 4. Two array variables sharing the same array*

`myArray[1] = 6;` changes an element of the very array that `intArray` refers to, so `intArray[1]` is also 6 afterward. For an independent copy, use `Arrays.copyOf()` of Section 4.

---

<br>

## 2. Array Operations

### 2.1 Array Processing Example: Rainfall

The following reads the rainfall of 12 months and computes the annual average. `length` is a public constant that returns the capacity of an array.

```java
double[] rainfall = new double[12];

double annualAverage,
       sum = 0.0;

for (int i = 0; i < rainfall.length; i++) {
    rainfall[i] = Double.parseDouble(
            JOptionPane.showInputDialog(null,
                    "Rainfall for month " + (i+1) ) );
    sum += rainfall[i];
}

annualAverage = sum / rainfall.length;
```

To prompt with the actual month name instead of a number, use a `String` array of month names together.

```java
double[] rainfall = new double[12];
String[] monthName = new String[12];

monthName[0] = "January";
monthName[1] = "February";
// ... the same pattern for the remaining ten months ...

double annualAverage, sum = 0.0;

for (int i = 0; i < rainfall.length; i++) {
    rainfall[i] = Double.parseDouble(
            JOptionPane.showInputDialog(null,
                    "Rainfall for "
                    + monthName[i] ));
    sum += rainfall[i];
}

annualAverage = sum / rainfall.length;
```

The quarterly averages are computed with a nested loop. The j-th month (0-2) of quarter i (0-3) is `rainfall[3*i + j]`.

```java
//assume rainfall is declared and initialized properly

double[] quarterAverage = new double[4];

for (int i = 0; i < 4; i++) {
    sum = 0;
    for (int j = 0; j < 3; j++) {         //compute the sum of
        sum += rainfall[3*i + j];         //one quarter
    }
    quarterAverage[i] = sum / 3.0;        //Quarter (i+1) average
}
```

### 2.2 Finding Maximum and Minimum

```java
public static int findMax(int[] array) {
    int max = array[0];
    for (int i = 1; i < array.length; i++) {
        if (array[i] > max) {
            max = array[i];
        }
    }
    return max;
}

public static int findMin(int[] array) {
    int min = array[0];
    for (int i = 1; i < array.length; i++) {
        if (array[i] < min) {
            min = array[i];
        }
    }
    return min;
}
```

> **Key Point:** Initialize `max` and `min` with the first element of the array rather than arbitrary values like `0` or `Integer.MAX_VALUE` to handle all input ranges correctly.

The following program finds the largest of five integers entered by the user. It can be modified to also print the minimum and the number of input values (`intArray.length`), and then extended again to print the arithmetic mean. When extending it, separating the maximum, minimum, and mean computations into methods, like `findMax` and `findMin` above and `computeMean` of Section 2.3, makes the code reusable and readable (lab code: [ArrayMaxFinder.java](../../Coursework/Lab05_Arrays/ArrayMaxFinder.java), [ArrayStatistics.java](../../Coursework/Lab05_Arrays/ArrayStatistics.java)).

```java
import java.util.Scanner;

public class Chap5Program1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int intArray[] = new int[5];

        int max = 0;

        System.out.println("Please input five numeric values.");

        for (int i = 0; i < 5; i++) {
            intArray[i] = scanner.nextInt();
            if (intArray[i] > max)
                max = intArray[i];
        }
        System.out.print("Maximum value is: " + max);

        scanner.close();
    }
}
```

> **Note:** This program initializes `max` to 0, so if every input is negative (e.g., -5, -2, -9, -1, -7), it prints 0 instead of the maximum -1. Likewise, initializing the minimum as `min = 999999` gives a wrong value when every input exceeds 999999. Initializing with the first element, as in the Key Point above, avoids this problem.

### 2.3 Computing the Mean

```java
public static double computeMean(int[] array) {
    int sum = 0;
    for (int value : array) {
        sum += value;
    }
    return (double) sum / array.length;
}
```

Note the cast to `double` before division to avoid integer truncation. Writing `double mean = sum / array.length;` without the cast assigns the result of an integer division, so the mean of 1, 2, 3, 4, and 6 becomes 3.0 instead of 3.2.

### 2.4 Array Traversal Patterns

```java
// Index-based for loop
for (int i = 0; i < array.length; i++) {
    System.out.println(array[i]);
}

// Enhanced for loop (read-only traversal)
for (int value : array) {
    System.out.println(value);
}
```

The variable `value` of the enhanced `for` loop is a copy of the element, so assigning a new value to `value` does not change the array. To change elements, use the index-based `for` loop.

### 2.5 Linear Search

The simplest way to find a target value in an array is linear search, which compares the elements in order from the front. It returns the index if found and -1 if the target is not present.

```java
public static int linearSearch(int[] array, int target) {
    for (int i = 0; i < array.length; i++) {
        if (array[i] == target) {
            return i;
        }
    }
    return -1;
}
```

Searching `{5, 3, 9, 1, 7}` for 9 returns 2, and for 4 returns -1. In the worst case it compares all n elements.

---

<br>

## 3. Arrays as Method Parameters

Java always passes arguments by value. The value of an array variable is a reference to the array object, so the method receives a copy of that reference, and the parameter and the argument refer to the same array. Modifications of elements inside the method therefore affect the original array.

```java
public static void doubleValues(int[] array) {
    for (int i = 0; i < array.length; i++) {
        array[i] *= 2;  // modifies the original array
    }
}
```

In contrast, assigning a new array to the parameter inside the method makes only the parameter refer to a different array, and the caller's variable still refers to the original array.

To avoid side effects, create a copy with `Arrays.copyOf()`.

---

<br>

## 4. The Arrays Utility Class

The `java.util.Arrays` class provides static methods for working with arrays.

```java
import java.util.Arrays;

int[] data = {5, 3, 9, 1, 7};
int[] copy = Arrays.copyOf(data, data.length);   // independent copy
Arrays.sort(copy);                               // ascending sort

System.out.println(Arrays.toString(data));       // [5, 3, 9, 1, 7]
System.out.println(Arrays.toString(copy));       // [1, 3, 5, 7, 9]
System.out.println(Arrays.equals(data, copy));   // false

int[] same = {5, 3, 9, 1, 7};
System.out.println(data == same);                // false (different array objects)
System.out.println(Arrays.equals(data, same));   // true (all elements equal)
```

| Method | Description |
|:-----|:-----|
| `Arrays.sort(a)` | Sorts the array `a` in ascending order |
| `Arrays.copyOf(a, n)` | Copies the elements of `a` into a new array of length `n` (padding with default values if longer, truncating if shorter) |
| `Arrays.equals(a, b)` | Compares whether two arrays have the same length and the same elements |
| `Arrays.toString(a)` | Makes a string of the elements in the form `[5, 3, 9]` |

*Table 2. Main methods of the Arrays class*

The array made by `copyOf` is separate from the original, so sorting `copy` does not change `data`.

---

<br>

## 5. Common Pitfalls

| Pitfall | Solution |
|:--------|:---------|
| Off-by-one errors in loops | Use `i < array.length`, not `i <= array.length` |
| Comparing arrays with `==` | Use `Arrays.equals()` for element-wise comparison |
| Initializing max/min with magic numbers | Initialize with `array[0]` instead |
| Integer division in mean calculation | Cast to `double` before dividing |
| Mistaking array variable assignment for a copy | Use `Arrays.copyOf()` when an independent array is needed |

*Table 3. Common pitfalls with arrays and their solutions*

---

<br>

## Summary

| Concept | Key Summary |
|:--------|:----------|
| Array | An array is an object that collects values of the same type, distinguished by index. |
| Declaration | An array is declared with `int[] arr = new int[size]` or `int[] arr = {values}`. |
| Length | The length is fixed at creation and accessed via `arr.length`. |
| Indexing | Indices are zero-based, and an out-of-bounds index throws `ArrayIndexOutOfBoundsException`. |
| Default values | Elements of an array created with `new` get the default for their type: 0, 0.0, `false`, `'\u0000'`, or `null`. |
| Array sharing | Assigning an array variable does not copy the array; both variables refer to the same array. |
| Passing arrays | The reference is passed by value, so modifying elements in a method affects the original. |
| Traversal | Use the enhanced for-each for read-only traversal and the indexed for for modifications. |
| Linear search | It compares elements in order from the front and returns the target's index or -1. |
| Utility methods | `Arrays` provides `Arrays.sort()`, `Arrays.copyOf()`, `Arrays.equals()`, and `Arrays.toString()`. |

---

<br>

## Self-Check Questions

1. **Array sharing:** What is the value of `a[1]` after the following code runs?

   ```java
   int[] a = new int[5];
   int[] b = a;
   a[1] = 2;
   b[1] = 6;
   ```

   <details>
   <summary>Answer</summary>

   It is 6. `b = a` does not copy the array but makes `b` refer to the same array as `a`, so `b[1] = 6` changes `a[1]`.

   </details>

2. **Quarterly indices:** In the quarterly average code of Section 2.1, write the indices of `rainfall` added when i = 2 and the names of those months.

   <details>
   <summary>Answer</summary>

   They are `3*2 + j` for j = 0, 1, 2, that is, indices 6, 7, and 8. By `monthName`, they are July, August, and September, and `quarterAverage[2]` is the third-quarter average.

   </details>

3. **Initializing the maximum:** What does `Chap5Program1` of Section 2.2 print for the input -5, -2, -9, -1, -7? How do you fix it?

   <details>
   <summary>Answer</summary>

   No input is greater than the initial value 0, so `max` never changes and the program prints "Maximum value is: 0". The correct maximum is -1. Initialize `max` with the first input value `intArray[0]` and compare it with the remaining elements.

   </details>

4. **Mean and integer division:** For the `int` array `{1, 2, 3, 4, 6}`, write the results of `double mean = sum / array.length;` and `(double) sum / array.length`.

   <details>
   <summary>Answer</summary>

   The sum is 16. The first computes the integer division 16 / 5 = 3 and then converts it to `double`, giving 3.0. The second computes 16.0 / 5, giving 3.2.

   </details>

5. **Array arguments:** After calling the following method as `int[] arr = {1, 2, 3}; modify(arr);`, what are the elements of `arr`?

   ```java
   static void modify(int[] array) {
       array[0] = 100;
       array = new int[]{7, 7};
       array[1] = 50;
   }
   ```

   <details>
   <summary>Answer</summary>

   They are `[100, 2, 3]`. At first `array` and `arr` refer to the same array, so `array[0] = 100` changes the original. Assigning a new array to `array` then makes only the parameter refer to the new array, so `array[1] = 50` does not affect the original.

   </details>
