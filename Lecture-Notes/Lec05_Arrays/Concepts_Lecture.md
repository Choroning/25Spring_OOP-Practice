# Lecture 5 — Arrays

[Repository Guide](../../README.md) | [English](Concepts_Lecture.md) | [한국어](Concepts_Lecture.ko.md)

> **Last Updated:** 2026-11-19

> **Learning Objectives:**
> 1. Declare and manipulate one-dimensional arrays
> 2. Apply array algorithms (search, sort, copy)
> 3. Use the Arrays utility class and enhanced for loop
> 4. Explain with memory states that assigning array variables and passing arrays to methods share the same array
> 5. Explain with trace tables and implement binary search and bubble sort
> 6. Distinguish the ways to copy arrays and shallow copying, and create and process arrays of objects

---

## Table of Contents

- [1. Array Fundamentals](#1-array-fundamentals)
  - [1.1 Why Arrays Are Needed](#11-why-arrays-are-needed)
  - [1.2 Declaration and Initialization](#12-declaration-and-initialization)
  - [1.3 Array Access and Length](#13-array-access-and-length)
  - [1.4 Default Values](#14-default-values)
  - [1.5 Array Sharing](#15-array-sharing)
  - [1.6 Arrays as Objects](#16-arrays-as-objects)
  - [1.7 Cost of Element Access and Insertion and Deletion](#17-cost-of-element-access-and-insertion-and-deletion)
- [2. Array Operations](#2-array-operations)
  - [2.1 Array Processing Example: Rainfall](#21-array-processing-example-rainfall)
  - [2.2 Finding Maximum and Minimum](#22-finding-maximum-and-minimum)
  - [2.3 Computing the Mean](#23-computing-the-mean)
  - [2.4 Array Traversal Patterns](#24-array-traversal-patterns)
  - [2.5 Linear Search](#25-linear-search)
  - [2.6 A Statistics Program Divided into Methods](#26-a-statistics-program-divided-into-methods)
  - [2.7 Reversing an Array](#27-reversing-an-array)
  - [2.8 Binary Search](#28-binary-search)
  - [2.9 Bubble Sort](#29-bubble-sort)
  - [2.10 Selection Sort](#210-selection-sort)
  - [2.11 Arrays for Counting Frequencies](#211-arrays-for-counting-frequencies)
- [3. Arrays as Method Parameters](#3-arrays-as-method-parameters)
  - [3.1 Swapping Elements Versus Swapping Variables](#31-swapping-elements-versus-swapping-variables)
  - [3.2 Variable-Arity Methods](#32-variable-arity-methods)
- [4. The Arrays Utility Class](#4-the-arrays-utility-class)
  - [4.1 Ways to Copy Arrays and Shallow Copies](#41-ways-to-copy-arrays-and-shallow-copies)
  - [4.2 Other Arrays Methods](#42-other-arrays-methods)
- [5. Common Pitfalls](#5-common-pitfalls)
- [6. Arrays of Objects](#6-arrays-of-objects)
  - [6.1 Creating Arrays of Objects](#61-creating-arrays-of-objects)
  - [6.2 Processing Arrays of Objects](#62-processing-arrays-of-objects)
- [Concept Applications](#concept-applications)
- [Summary](#summary)
- [Self-Check Questions](#self-check-questions)
- [References](#references)

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

For example, an array `rainfall` holding 12 real numbers can be declared in either form, and after the declaration an array object created with `new` is assigned to it.

```java
double[] rainfall;              // variation 1
rainfall = new double[12];

double rainfall2[];             // variation 2
rainfall2 = new double[12];
```

The declaration creates only a variable to hold a reference, like the object declaration of Lecture 2, Section 2.1, and `new double[12]` creates the actual array; in this sense arrays are handled in the same way as objects. In Java the first form (`double[] rainfall`), which shows the type at a glance, is mainly used.

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

### 1.6 Arrays as Objects

Arrays in Java are objects. They are created dynamically with `new` during execution, can be assigned to variables of type `Object`, and can have every method of `Object` invoked on them [1]. So an array sits on the heap like any other object, and an array variable holds a reference to that array (Lecture 2, Section 2.5). The array sharing of Section 1.5 also follows from this property.

```java
import java.util.Arrays;

public class ArrayObject {
    public static void main(String[] args) {
        int[] a = {1, 2, 3};
        System.out.println(a.length + " " + a.getClass().getSimpleName());
        System.out.println(a);
        System.out.println(Arrays.toString(a));
        String[] names = new String[3];
        names[0] = "Kim";
        System.out.println(names[0] + " " + names[1]);
        System.out.println(names[1].length());
    }
}
```

```text
3 int[]
[I@7ad041f3
[1, 2, 3]
Kim null
Exception in thread "main" java.lang.NullPointerException: Cannot invoke "String.length()" because "names[1]" is null
	at ArrayObject.main(ArrayObject.java:12)
```

- An array object has its own class (`int[]`) and a `final` field named `length` that gives the number of elements. Because it is `final`, trying to change it, as in `a.length = 5;`, gives the compile error `cannot assign a value to final variable length`. Unlike the method `length()` of `String`, it is written without parentheses.
- `System.out.println(a)` prints not the elements but a string such as `[I@7ad041f3`. `[I` means an array of `int`, and the hexadecimal digits after `@` differ from object to object and may change from run to run. To see the elements, use `Arrays.toString(a)` (Section 4).
- An array of a reference type, `new String[3]`, creates not three `String` objects but three slots of references that can point to `String` objects. The default value of each slot is `null`, so calling a method through `names[1]`, to which no object was assigned, throws a `NullPointerException` (Section 6). The name of the local variable, as in `"names[1]"` in the message, appears when the class is compiled with `-g` (Lecture 2, Section 2.6).

```text
 Stack                Heap
┌──────────┐        ┌──────────────────────┐
│ a     ●──┼──────▶ │ int[]  length 3      │
│          │        │ [0] 1  [1] 2  [2] 3  │
│          │        └──────────────────────┘
│ names ●──┼──────▶ ┌──────────────────────┐
└──────────┘        │ String[]  length 3   │
                    │ [0] ●──▶ "Kim"       │
                    │ [1] null             │
                    │ [2] null             │
                    └──────────────────────┘
```

*Figure 5. Memory structure of an array of a primitive type and an array of a reference type*

The element slots of `int[]` hold the values themselves, while those of `String[]` hold references. The default values in the table of Section 1.4 are the values array elements receive when they are created as part of the array object; local variables of a method receive no such default values (Lecture 3, Section 2.1).

### 1.7 Cost of Element Access and Insertion and Deletion

The elements of an array lie one after another in memory without gaps, and every element has the same size. So the location of the i-th element is found with a single calculation, the starting location of the array plus i × (the size of one element), and reading or writing `a[i]` takes the same time whether the array has length 10 or one million. Accessing any element by index at the same cost in this way is called **random access**, and it is the greatest strength of arrays. That indices start at 0 can also be understood from this: the first element is at the starting location plus 0.

On the other hand, the length of an array is fixed at creation, so inserting or deleting an element in the middle requires moving the following elements by one slot. The following program inserts and deletes elements in a partially filled array that uses only the first `count` slots of an array of length 6.

```java
import java.util.Arrays;

public class ShiftDemo {
    static int insert(int[] a, int count, int index, int value) {
        for (int i = count; i > index; i--) {
            a[i] = a[i - 1];
        }
        a[index] = value;
        return count + 1;
    }

    static int remove(int[] a, int count, int index) {
        for (int i = index; i < count - 1; i++) {
            a[i] = a[i + 1];
        }
        a[count - 1] = 0;
        return count - 1;
    }

    public static void main(String[] args) {
        int[] a = new int[6];
        int count = 0;
        count = insert(a, count, 0, 10);
        count = insert(a, count, 1, 30);
        count = insert(a, count, 1, 20);
        System.out.println(Arrays.toString(a) + " count=" + count);
        count = remove(a, count, 0);
        System.out.println(Arrays.toString(a) + " count=" + count);
    }
}
```

```text
[10, 20, 30, 0, 0, 0] count=3
[20, 30, 0, 0, 0, 0] count=2
```

When `insert(a, 2, 1, 20)` is called, the array is `[10, 30, ...]`. The loop moves 30 one slot back with `a[2] = a[1]` at i = 2, stops at i = 1, and puts 20 into the vacated position 1. The moves must go from back to front because moving from the front would make `a[i + 1] = a[i]` overwrite the value of `a[i + 1]` before that value has been moved. Deletion, conversely, pulls the elements after the deleted position forward by one slot, so it moves from front to back. Both operations move close to `count` elements in the worst case, so they take time proportional to the number of elements. Inserting into a full array (`count == a.length`) would put `a[count]` out of range, so a larger array must be created and the elements copied (Section 4.1). This is why the `ArrayList` of Lecture 9 is used for data whose size changes often, and an `ArrayList` also performs such moves and copies internally.

> **Note:** If the order of the elements does not matter, deletion can be faster. Moving the last element into the deleted position and decreasing `count` by 1 requires moving only one element. This is an example of how whether order must be preserved determines the cost of an operation.

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

The following two methods take the first element as the maximum (minimum) so far, compare it with the remaining elements in turn, and replace it whenever a larger (smaller) element appears. They assume an array with at least one element; passing an empty array throws `ArrayIndexOutOfBoundsException` at `array[0]`.

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

### 2.6 A Statistics Program Divided into Methods

The following program modifies `Chap5Program1` of Section 2.2 to print the minimum and the number of inputs as well, and then also the arithmetic mean, with the calculations divided into methods.

```java
import java.util.Scanner;

public class Chap5Program3 {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		int intArray[] = new int[5];

		System.out.println("Please input five numeric values.");

		for (int i = 0; i < 5; i++)
			intArray[i] = scanner.nextInt();

		System.out.println("Maximum value is: " + getMaximumValue(intArray));
		System.out.println("Minimum value is: " + getMinimumValue(intArray));
		System.out.println("# of elements: " + intArray.length);
		System.out.println("Mean of values: " + getMean(intArray));

		scanner.close();

	}

	//Get Maximum value
	public static int getMaximumValue(int[] array) {
		int max = 0;

		for (int i = 0; i < 5; i++) {
			if (array[i] > max)
				max = array[i];
		}

		return max;
	}


	// Get Minimum value
	public static int getMinimumValue(int[] array) {
		int min = 9999;

		for (int i = 0; i < 5; i++) {
			if (array[i] < min)
				min = array[i];
		}

		return min;
	}

	// Get mean value
	public static double getMean(int[] array) {
		int sum = 0;
		double mean = 0.0d;

		for (int i = 0; i < 5; i++)
			sum += array[i];

		mean = sum / array.length;

		return mean;
	}

}
```

`main` handles only input and output, and the maximum, minimum, and mean are each computed by a method that takes the array as an argument. The same calculation can be used for other arrays, and the shorter `main` is easier to read. Running this program with two inputs, however, reveals problems.

```text
$ echo "1 2 3 4 6" | java Chap5Program3
Please input five numeric values.
Maximum value is: 6
Minimum value is: 1
# of elements: 5
Mean of values: 3.0
$ echo "-5 -2 -9 -1 -7" | java Chap5Program3
Please input five numeric values.
Maximum value is: 0
Minimum value is: -9
# of elements: 5
Mean of values: -4.0
```

| Location | Problem | Result | Fix |
|:-----|:-----|:-----|:-----|
| `max = 0` in `getMaximumValue` | If every value is less than 0, 0 remains | The maximum of the second input is 0 instead of -1 | Start with `max = array[0]` and compare from element 1 |
| `min = 9999` in `getMinimumValue` | If every value is greater than 9999, 9999 remains | Wrong when only values of 10000 or more are entered | Start with `min = array[0]` |
| `sum / array.length` in `getMean` | Integer division of two `int` values | The mean of 1, 2, 3, 4, 6 is 3.0 instead of 3.2, and that of the second input -4.0 instead of -4.8 | `(double) sum / array.length` |
| `i < 5` in the three methods | Assumes the array length is 5 | Elements are skipped or `ArrayIndexOutOfBoundsException` occurs for arrays of other lengths | `i < array.length` |

*Table 2. Problems of Chap5Program3 and their fixes*

-4.0 appears because integer division truncated -4.8, the sum -24 divided by 5, toward zero (Lecture 3, Section 4.7). The program with all four problems fixed is as follows.

```java
import java.util.Scanner;

public class Stats {
    public static int getMaximumValue(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    public static int getMinimumValue(int[] array) {
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    public static double getMean(int[] array) {
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return (double) sum / array.length;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] intArray = new int[5];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = scanner.nextInt();
        }
        System.out.println("Maximum value is: " + getMaximumValue(intArray));
        System.out.println("Minimum value is: " + getMinimumValue(intArray));
        System.out.println("# of elements: " + intArray.length);
        System.out.println("Mean of values: " + getMean(intArray));
    }
}
```

```text
$ echo "1 2 3 4 6" | java Stats
Maximum value is: 6
Minimum value is: 1
# of elements: 5
Mean of values: 3.2
$ echo "-5 -2 -9 -1 -7" | java Stats
Maximum value is: -1
Minimum value is: -9
# of elements: 5
Mean of values: -4.8
```

The fixed methods assume an array with at least one element. Passing an empty array throws an exception at `array[0]`, so to allow empty arrays, the method must check the length at its start and decide on a meaningful result or exception.

### 2.7 Reversing an Array

To reverse the order of the elements of an array in place, keep two indices `i` and `j` starting at both ends, swap the two elements, and repeat increasing `i` and decreasing `j` until the two indices meet.

```java
static void reverse(int[] a) {
    for (int i = 0, j = a.length - 1; i < j; i++, j--) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
```

Passing `{1, 2, 3, 4, 5}` swaps twice, with (i, j) equal to (0, 4) and (1, 3), and stops at i = j = 2, leaving the middle element 3 in place, so the result is `[5, 4, 3, 2, 1]`. Writing the condition as `i < a.length` instead of `i < j` swaps again in the second half and restores the original order. A temporary variable `tmp` is needed to swap two values because the original value of `a[i]` is lost the moment `a[i] = a[j];` is executed first. This method receives the reference of the array and changes its elements, so the caller's array is reversed (Section 3).

### 2.8 Binary Search

The linear search of Section 2.5 may examine all n elements. If the array is sorted in ascending order, **binary search** finds a target much faster. It compares the middle element `a[mid]` of the search range `[low, high]` with the target, keeps the right half if the target is larger or the left half if it is smaller, and discards the rest.

```java
static int binarySearch(int[] a, int target) {
    int low = 0, high = a.length - 1;
    while (low <= high) {
        int mid = (low + high) / 2;
        System.out.println("low=" + low + " high=" + high + " mid=" + mid + " a[mid]=" + a[mid]);
        if (a[mid] == target) {
            return mid;
        } else if (a[mid] < target) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }
    return -1;
}
```

Searching `{1, 3, 5, 7, 9, 11, 13}` for 11 and for 4, as in `System.out.println("found at " + binarySearch(a, 11))`, prints the following.

```text
low=0 high=6 mid=3 a[mid]=7
low=4 high=6 mid=5 a[mid]=11
found at 5
low=0 high=6 mid=3 a[mid]=7
low=0 high=2 mid=1 a[mid]=3
low=2 high=2 mid=2 a[mid]=5
found at -1
```

When searching for 11, the middle 7 is smaller than 11, so `low` moves to 4, and 11 is found at the middle of the remaining range 4-6. When searching for 4, the range shrinks to 0-2 because 4 is smaller than 7, then to 2-2 because it is larger than 3, and when it is smaller than 5 and `high` becomes 1, `low > high` holds and -1 is returned. Each comparison halves the range, so for n elements the number of comparisons is at most about $`\log_2 n + 1`$. With one million elements, linear search makes up to one million comparisons and binary search at most 20.

> **Caution:** Binary search works correctly only on a sorted array. On an unsorted array it may fail to find a target that is present. Also, for very large arrays `low + high` can exceed the range of `int`, so computing the middle as `low + (high - low) / 2` is safer (Lecture 3, Section 2.5).

### 2.9 Bubble Sort

**Bubble sort** is a sorting algorithm that compares two neighboring elements, swaps them if they are out of order, and repeats this to the end of the array. One run to the end is called a pass, and each completed pass moves the largest element of the still unsorted part to the back like a bubble rising. So the k-th pass does not need to compare the last k - 1 elements again.

```java
static void bubbleSort(int[] a) {
    for (int pass = 1; pass < a.length; pass++) {
        for (int i = 0; i < a.length - pass; i++) {
            if (a[i] > a[i + 1]) {
                int tmp = a[i];
                a[i] = a[i + 1];
                a[i + 1] = tmp;
            }
        }
        System.out.println("after pass " + pass + ": " + Arrays.toString(a));
    }
}
```

```text
after pass 1: [3, 5, 1, 7, 9]
after pass 2: [3, 1, 5, 7, 9]
after pass 3: [1, 3, 5, 7, 9]
after pass 4: [1, 3, 5, 7, 9]
```

The first pass over `{5, 3, 9, 1, 7}` swaps (5, 3), leaves (5, 9), and swaps (9, 1) and (9, 7), sending the largest value 9 to the back. The second pass moves 7 into place within the first four slots, and the third pass moves 5 into place. For an array of n elements the number of comparisons is as follows.

```math
(n-1) + (n-2) + \cdots + 1 = \frac{n(n-1)}{2}
```

For n = 5 this is 10. If a pass makes no swap, like the fourth pass, the array is already sorted, so the method can be improved to stop there by keeping a `boolean` variable that records whether a swap occurred. Bubble sort is simple in principle, but its number of comparisons grows with the square of n, so real programs use library methods implemented with faster algorithms, such as `Arrays.sort` of Section 4.

### 2.10 Selection Sort

**Selection sort** repeatedly finds the smallest element of the unsorted part and swaps it with the first element of that part. After the i-th iteration, the i + 1 smallest elements are in order at positions 0-i.

```java
import java.util.Arrays;

public class SelectionSort {
    static void selectionSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            int tmp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = tmp;
            System.out.println("i=" + i + ": " + Arrays.toString(a));
        }
    }

    public static void main(String[] args) {
        int[] data = {5, 3, 9, 1, 7};
        selectionSort(data);
    }
}
```

```text
i=0: [1, 3, 9, 5, 7]
i=1: [1, 3, 9, 5, 7]
i=2: [1, 3, 5, 9, 7]
i=3: [1, 3, 5, 7, 9]
```

At i = 0 it finds the smallest value 1 (at position 3) in the whole array and swaps it with the first element 5. At i = 1 the minimum 3 of the remaining part is already at position 1, so it is swapped with itself and the array stays the same. The inner loop is the minimum search of Section 2.2, and it must remember the position `minIndex` rather than the value to be able to swap. The number of comparisons is n(n - 1) / 2, the same as bubble sort, but there is at most one swap per iteration, n - 1 in all. Bubble sort swaps neighboring elements many times, so its number of swaps can reach n(n - 1) / 2. This is why selection sort is preferable when moving elements is expensive. However, selection sort makes no fewer comparisons even on an already sorted array.

### 2.11 Arrays for Counting Frequencies

When the values are integers in a small range, their frequencies can be counted with an array that uses the value itself as the index. The following counts how many times each face appeared in ten rolls of a die.

```java
public class Frequency {
    public static void main(String[] args) {
        int[] rolls = {3, 6, 1, 3, 5, 6, 6, 2, 3, 6};
        int[] counts = new int[7];
        for (int r : rolls) {
            counts[r]++;
        }
        for (int face = 1; face <= 6; face++) {
            System.out.println(face + ": " + "*".repeat(counts[face]) + " (" + counts[face] + ")");
        }
    }
}
```

```text
1: * (1)
2: * (1)
3: *** (3)
4:  (0)
5: * (1)
6: **** (4)
```

The length of `counts` is set to 7 so that indices 1-6 correspond directly to the faces of the die, and index 0 is unused. `counts[r]++` increases that slot by 1 each time the value `r` appears, and since the default value of the elements is 0, no separate initialization is needed (Section 1.4). Looking at every value once completes the frequency table, so n elements take n updates. A value outside the range (for example, 7) causes an `ArrayIndexOutOfBoundsException`, so check the range first when the input cannot be trusted. The last loop prints as many `*` as the frequency with `repeat` of Lecture 2, Section 4.7, showing the result like a bar chart.

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

### 3.1 Swapping Elements Versus Swapping Variables

The difference between passing an array and passing primitive values shows clearly in a method that swaps two values. Java copies the value of each argument into the parameter (Lecture 6, Section 2.4), so when two primitive variables are passed, the method can change only the copies. When an array is passed, the reference of the array is copied, so the method can change the elements of the same array the caller has.

```java
public class SwapDemo {
    static void swap(int x, int y) {
        int tmp = x;
        x = y;
        y = tmp;
    }

    static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    static int sum(int... nums) {
        int total = 0;
        for (int n : nums) {
            total += n;
        }
        return total;
    }

    public static void main(String[] args) {
        int p = 1, q = 2;
        swap(p, q);
        System.out.println(p + " " + q);
        int[] arr = {1, 2};
        swap(arr, 0, 1);
        System.out.println(arr[0] + " " + arr[1]);
        System.out.println(sum() + " " + sum(5) + " " + sum(1, 2, 3) + " " + sum(new int[]{4, 5}));
    }
}
```

```text
1 2
2 1
0 5 6 9
```

```text
 during swap(p, q)                        during swap(arr, 0, 1)

 main:  p 1   q 2                         main:  arr ●──┐
 swap:  x 2   y 1   (copies swapped)                    ├──▶ [ 2 | 1 ]
                                          swap:  a   ●──┘
```

*Figure 6. Memory state of swap with primitive arguments and with an array argument*

The first `swap` exchanges `x` and `y`, but the two parameters are copies of `p` and `q` and disappear when the method ends, so `p` and `q` remain 1 and 2. The `a` of the second `swap` refers to the same array as `arr`, so the exchange of elements is visible to the caller as is. The two methods with the same name `swap` have different parameter lists, so they are overloads (Lecture 6, Section 2.2).

### 3.2 Variable-Arity Methods

Putting `...` after the type of a parameter, as in `sum(int... nums)` above, makes a **variable-arity** method that takes zero or more arguments of that type. The compiler bundles the arguments of the call into an array and passes it, so inside the method `nums` is an ordinary `int[]` array. That is why `sum()` receives an array of length 0 and returns 0, `sum(1, 2, 3)` returns 6, and an existing array can also be passed directly, as in `sum(new int[]{4, 5})`. The `printf` of Lecture 4, Section 5.1 can take any number of arguments after the format string thanks to variable arity. A variable-arity parameter can appear only once, at the end of the parameter list.

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

*Table 3. Main methods of the Arrays class*

The array made by `copyOf` is separate from the original, so sorting `copy` does not change `data`.

### 4.1 Ways to Copy Arrays and Shallow Copies

There are several ways to copy an array, and they all differ from the array sharing of Section 1.5 in that they create a new array object.

```java
import java.util.Arrays;

public class CopyDemo {
    public static void main(String[] args) {
        int[] src = {10, 20, 30, 40, 50};
        int[] c1 = src.clone();
        int[] c2 = Arrays.copyOf(src, 7);
        int[] c3 = Arrays.copyOfRange(src, 1, 4);
        int[] c4 = new int[5];
        System.arraycopy(src, 0, c4, 1, 3);
        src[0] = 99;
        System.out.println(Arrays.toString(c1));
        System.out.println(Arrays.toString(c2));
        System.out.println(Arrays.toString(c3));
        System.out.println(Arrays.toString(c4));

        StringBuilder[] boxes = {new StringBuilder("a"), new StringBuilder("b")};
        StringBuilder[] shallow = boxes.clone();
        shallow[0].append("!");
        shallow[1] = new StringBuilder("z");
        System.out.println(boxes[0] + " " + boxes[1] + " " + (boxes[0] == shallow[0]));
    }
}
```

```text
[10, 20, 30, 40, 50]
[10, 20, 30, 40, 50, 0, 0]
[20, 30, 40]
[0, 10, 20, 30, 0]
a! b true
```

| Method | What it does | Result in the example above |
|:-----|:-----|:-----|
| `a.clone()` | Copies all elements into a new array of the same length | `c1` is a copy of `src` |
| `Arrays.copyOf(a, n)` | Copies into a new array of length n, filling any extra slots with default values | `c2` has two 0s at the end |
| `Arrays.copyOfRange(a, from, to)` | Copies positions `from` through `to - 1` into a new array | `c3` holds positions 1-3 |
| `System.arraycopy(src, si, dst, di, n)` | Copies n elements from position `si` of `src` into an existing array `dst` starting at position `di` | Positions 1-3 of `c4` |

*Table 4. Ways to copy an array*

Changing the original with `src[0] = 99;` after copying does not change the four copies, because for primitive elements the values themselves are copied.

The situation differs when the elements are of a reference type. Copying an array copies only the values in the element slots, that is, the references, and not the objects they refer to. This is called a **shallow copy**. In the example above, `shallow` is a new array, but its elements refer to the same `StringBuilder` objects as the elements of `boxes`, so the change made with `shallow[0].append("!")` is visible through `boxes[0]` as well, and `boxes[0] == shallow[0]` is `true`. In contrast, `shallow[1] = new StringBuilder("z");` only puts another reference into one slot of `shallow`, so `boxes[1]` is still `"b"`.

```text
 boxes   ●──▶ [ ● | ● ]          shallow ●──▶ [ ● | ● ]
                │    │                         │    │
                │    └──▶ "b"                  │    └──▶ "z"
                └──────────────▶ "a!" ◀────────┘
```

*Figure 7. Two arrays sharing element objects after a shallow copy*

Copying the element objects as well by creating new ones is called a **deep copy**, and it requires writing a loop that creates a new object for each element. For an array of immutable objects such as `String`, a shallow copy is enough, because nobody can change the shared objects (Lecture 2, Section 4.5).

### 4.2 Other Arrays Methods

Besides the methods of Table 3, the following methods of `Arrays` are often used.

```java
import java.util.Arrays;

public class ArraysMore {
    public static void main(String[] args) {
        int[] a = new int[6];
        Arrays.fill(a, 7);
        System.out.println(Arrays.toString(a));
        int[] b = {9, 4, 8, 1, 6, 2};
        Arrays.sort(b, 1, 4);
        System.out.println(Arrays.toString(b));
        int[] s = {1, 3, 5, 7, 9};
        System.out.println(Arrays.binarySearch(s, 7) + " " + Arrays.binarySearch(s, 4) + " " + Arrays.binarySearch(s, 10));
        int[][] m = {{1, 2}, {3, 4}};
        int[][] n = {{1, 2}, {3, 4}};
        System.out.println(Arrays.toString(m).startsWith("[[I@") + " " + Arrays.deepToString(m));
        System.out.println(Arrays.equals(m, n) + " " + Arrays.deepEquals(m, n));
        String[] words = {"pear", "Apple", "banana"};
        Arrays.sort(words);
        System.out.println(Arrays.toString(words));
    }
}
```

```text
[7, 7, 7, 7, 7, 7]
[9, 1, 4, 8, 6, 2]
3 -3 -6
true [[1, 2], [3, 4]]
false true
[Apple, banana, pear]
```

- `Arrays.fill(a, v)` fills every element with `v`.
- `Arrays.sort(a, from, to)` sorts only positions `from` through `to - 1`. The 4, 8, 1 at positions 1-3 become 1, 4, 8, and the rest stays as is.
- `Arrays.binarySearch(a, key)` performs the binary search of Section 2.8 on a sorted array and returns the position found. If the key is not found, it returns -(p + 1) for the position p where the value would have to be inserted (the insertion point). 4 would go at position 2, giving -3, and 10 at position 5, giving -6. A negative result means the value is absent and also tells the insertion point.
- For a two-dimensional array (Lecture 6, Section 1), `Arrays.toString` shows the references of the row arrays in the form `[[I@...`, and `Arrays.equals` compares the row arrays with `==`, so it is `false` even if the contents are the same. For two-dimensional arrays use `deepToString` and `deepEquals`.
- `sort` on an array of strings follows the order of `compareTo` of Lecture 2, Section 4.7, so `"Apple"`, which starts with an uppercase letter, comes before the lowercase words.

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
| Printing an array directly with `println` | Use `Arrays.toString()` to see the elements instead of `[I@...` |
| Adding parentheses to the `length` of an array or omitting them from the `length` of a string | An array has the field `a.length`; a string has the method `s.length()` |
| Not creating the element objects after creating an array of objects | `new Account[4]` gives four `null` slots, so create an object with `new` for each element and assign it |
| Trying to change an element by assigning to the variable of an enhanced `for` | Assign `a[i] = ...` in an index-based `for` |
| Binary search on an unsorted array | Sort first with `Arrays.sort()` |
| Taking a copy of a reference-type array for a deep copy | If the element objects are needed too, create a new object for each element |

*Table 5. Common pitfalls with arrays and their solutions*

---

<br>

## 6. Arrays of Objects

### 6.1 Creating Arrays of Objects

The elements of an array can be objects of any class, not only primitive values. As seen in Section 1.6, creating an array of a reference type creates only the element slots, each of which is `null`, so an array of objects is built in two steps. First create an array to hold references with `new ClassName[size]`, and then create an object with `new` for each element and assign it. The following program prepares names and balances in two arrays, like the rainfall example of Section 2.1, and creates four `Account` objects in a loop.

```java
public class Account {
    private String owner;
    private double balance;

    public Account(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }
}
```

```java
public class AccountArray {
    public static void main(String[] args) {
        Account[] accounts = new Account[4];
        System.out.println(accounts[0]);

        String[] owners = {"Kim", "Lee", "Park", "Choi"};
        double[] balances = {1200.0, 350.5, 4800.0, 950.0};
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account(owners[i], balances[i]);
        }

        Account richest = accounts[0];
        int countOver1000 = 0;
        double total = 0.0;
        for (Account acc : accounts) {
            if (acc.getBalance() > richest.getBalance()) {
                richest = acc;
            }
            if (acc.getBalance() >= 1000.0) {
                countOver1000++;
            }
            total += acc.getBalance();
        }
        System.out.println(richest.getOwner() + " " + richest.getBalance());
        System.out.println(countOver1000 + " " + total);
    }
}
```

```text
null
Park 4800.0
2 7300.5
```

The `null` of the first output shows that right after `new Account[4]` there is no `Account` object yet. Calling `accounts[0].getBalance()` in this state throws a `NullPointerException`. When the `for` loop ends, the four slots each refer to a different `Account` object.

```text
 accounts ●──▶ [ ● | ● | ● | ● ]
                 │   │   │   └──▶ Account("Choi", 950.0)
                 │   │   └──────▶ Account("Park", 4800.0)
                 │   └──────────▶ Account("Lee", 350.5)
                 └──────────────▶ Account("Kim", 1200.0)
```

*Figure 8. The Account array after an object is created for each element*

### 6.2 Processing Arrays of Objects

The second loop of Section 6.1 finds the account with the largest balance in the same way as the maximum search of Section 2.2. The difference is that it updates not a value but the reference `richest` to an object. When the loop ends, `richest` refers to that account object, so not only the balance but also the owner's name can be obtained with `richest.getOwner()`. The same loop also computes the number of accounts with a balance of at least 1000.0 (two, those of Kim and Park) and the total balance 1200.0 + 350.5 + 4800.0 + 950.0 = 7300.5.

Separate arrays such as `owners` and `balances` are called **parallel arrays**. They rely on the convention that the same index describes the same thing, so sorting only one of them or deleting an element breaks the correspondence between the two arrays. An array of objects bundles the information about one thing into one object, so it has no such problem, and when more information is needed a field is simply added to the class. The advantage of object-oriented design seen in Lecture 1, Section 1.4 appears in arrays as well.

> **Caution:** To sort an array of objects with `Arrays.sort`, a way to order the objects is needed. `String` and the wrapper classes have a defined order, but a class written by the programmer, such as `Account`, must provide its own way of ordering, which is covered with collections in Lecture 9.

---

<br>

## Concept Applications

**True/False Check:**

| Statement | Judgment and Reason |
|:-----|:-----|
| The `a.length` of `int[] a = new int[3];` is a method, so it is written `a.length()`. | **F.** The `length` of an array is a `final` field, so it is written without parentheses. Parentheses belong to `length()` of `String`. |
| Executing `String[] s = new String[2];` creates two empty strings `""`. | **F.** Two reference slots are created, and each is `null`. String objects come into existence only when assigned separately. |
| Binary search finds the target even in an unsorted array. | **F.** The decision to discard half of the range relies on the sorted order, so in an unsorted array even a present value may not be found. |
| Bubble sort on 5 elements makes at most 10 comparisons. | **T.** The passes make 4, 3, 2, and 1 comparisons, summing to 5 × 4 / 2 = 10. |
| `swap(int x, int y)` swaps the values of the caller's two variables. | **F.** The parameters are copies of the argument values, so only the copies change. `swap(int[] a, int i, int j)`, which swaps array elements, is visible to the caller. |
| Calling `clone()` on an `Account[]` array also creates new `Account` objects. | **F.** It is a shallow copy, so the elements of the new array refer to the same `Account` objects. |
| If `Arrays.binarySearch` returns a negative number, the value is not in the array. | **T.** When the key is not found, it returns -(p + 1) for the insertion point p, which is always negative. |

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
| Arrays as objects | An array is an object created on the heap with a `final` field `length`, and the elements of a reference-type array are initially `null`. |
| Errors of statistics methods | Start the maximum and minimum from the first element, compute the mean with real division, and bound the loop with `array.length`. |
| Reversing an array | Swap elements until the two indices from both ends meet. |
| Binary search | On a sorted array, compare with the middle element and halve the range, with about $`\log_2 n`$ comparisons. |
| Bubble sort | Repeats passes that compare and swap neighboring elements, with n(n - 1) / 2 comparisons. |
| Variable arity | `int... nums` receives zero or more arguments as an array and can appear only once, at the end of the parameter list. |
| Copying arrays | `clone`, `copyOf`, `copyOfRange`, and `System.arraycopy` create new arrays, and reference-type elements are copied shallowly. |
| Arrays of objects | Create the reference array and then an object for each element; this bundles the information about one thing more safely than parallel arrays. |
| Random access | An array reaches any element at the same cost with one index calculation, but inserting or deleting in the middle requires moving elements. |
| Selection sort | Moves the minimum of the remaining part to the front, with n(n - 1) / 2 comparisons but at most n - 1 swaps. |
| Frequency arrays | Count frequencies with `counts[value]++`, using integer values in a small range as indices. |

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

6. **Tracing binary search:** When `binarySearch` of Section 2.8 looks for 14 in `{2, 4, 6, 8, 10, 12, 14, 16}`, write `low`, `high`, and `mid` for each iteration and the return value.

   <details>
   <summary>Answer</summary>

   Initially low = 0 and high = 7, so mid = 3, and a[3] = 8 < 14, so low becomes 4. Next mid = (4 + 7) / 2 = 5, and a[5] = 12 < 14, so low becomes 6. Next mid = (6 + 7) / 2 = 6, and a[6] = 14, so it returns 6. It made three comparisons.

   </details>

7. **Tracing bubble sort:** Write the array after each pass when `bubbleSort` of Section 2.9 is applied to `{4, 2, 3, 1}`.

   <details>
   <summary>Answer</summary>

   The first pass swaps all of (4, 2), (4, 3), and (4, 1), giving `[2, 3, 1, 4]`. The second pass leaves (2, 3) and swaps (3, 1), giving `[2, 1, 3, 4]`. The third pass swaps (2, 1), giving `[1, 2, 3, 4]`.

   </details>

8. **Shallow copy:** Write the output after the following code runs and explain why.

   ```java
   int[][] grid = {{1, 2}, {3, 4}};
   int[][] copy = grid.clone();
   copy[0][0] = 9;
   copy[1] = new int[]{7, 7};
   System.out.println(grid[0][0] + " " + grid[1][0]);
   ```

   <details>
   <summary>Answer</summary>

   It prints `9 3`. A two-dimensional array is an array whose elements are references to row arrays, so `clone()` copies only the row references. `copy[0]` and `grid[0]` refer to the same row array, so `copy[0][0] = 9` changes `grid[0][0]`. Only a new row was assigned to `copy[1]`, so `grid[1]` still refers to the original `{3, 4}`.

   </details>

---

<br>

## References

1. J. Gosling, B. Joy, G. Steele, G. Bracha, A. Buckley, D. Smith, and G. Bierman, *The Java Language Specification, Java SE 21 Edition*, Ch. 10. Oracle, 2023. [Online]. Available: https://docs.oracle.com/javase/specs/jls/se21/html/jls-10.html
