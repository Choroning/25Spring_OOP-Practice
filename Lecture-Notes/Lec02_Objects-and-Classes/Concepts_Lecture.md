# Lecture 2 — Objects and Classes

[Repository Guide](../../README.md) | [English](Concepts_Lecture.md) | [한국어](Concepts_Lecture.ko.md)

> **Last Updated:** 2026-10-26

> **Learning Objectives:**
> 1. Distinguish classes and objects, messages and methods, and class data values and instance data values
> 2. Read a class with fields and methods and draw it in UML notation
> 3. Declare and create objects and invoke their methods, and explain the difference between declaration and creation with memory states
> 4. Identify the components of a Java program (comments, import statements, class declarations, method declarations)
> 5. Use the standard classes JOptionPane, String, Date, and SimpleDateFormat
> 6. Write simple Java programs with the incremental development approach

---

## Table of Contents

- [1. Objects and Classes in Java](#1-objects-and-classes-in-java)
  - [1.1 What is an Object?](#11-what-is-an-object)
  - [1.2 What is a Class?](#12-what-is-a-class)
  - [1.3 Relationship Between Objects and Classes](#13-relationship-between-objects-and-classes)
  - [1.4 UML Notation](#14-uml-notation)
  - [1.5 Messages and Methods](#15-messages-and-methods)
  - [1.6 Class Data Values and Instance Data Values](#16-class-data-values-and-instance-data-values)
  - [1.7 Inheritance](#17-inheritance)
  - [1.8 Software Engineering and the Software Life Cycle](#18-software-engineering-and-the-software-life-cycle)
- [2. Creating Objects](#2-creating-objects)
  - [2.1 Declaration and Instantiation](#21-declaration-and-instantiation)
  - [2.2 Memory States of Declaration and Creation](#22-memory-states-of-declaration-and-creation)
  - [2.3 Reference Variables](#23-reference-variables)
  - [2.4 Sending Messages to Objects](#24-sending-messages-to-objects)
- [3. Program Structure and Comments](#3-program-structure-and-comments)
  - [3.1 Sample Program](#31-sample-program)
  - [3.2 Comments](#32-comments)
  - [3.3 Import Statements](#33-import-statements)
  - [3.4 Class and Method Declarations](#34-class-and-method-declarations)
- [4. Java Standard Library Classes](#4-java-standard-library-classes)
  - [4.1 JFrame for GUI Windows](#41-jframe-for-gui-windows)
  - [4.2 JOptionPane for Dialogs](#42-joptionpane-for-dialogs)
  - [4.3 String Manipulation](#43-string-manipulation)
  - [4.4 Date and SimpleDateFormat](#44-date-and-simpledateformat)
- [5. Incremental Development: The Monogram Program](#5-incremental-development-the-monogram-program)
  - [5.1 Problem and Overall Plan](#51-problem-and-overall-plan)
  - [5.2 Step 1: Input the Name](#52-step-1-input-the-name)
  - [5.3 Step 2: Output the Monogram and Date](#53-step-2-output-the-monogram-and-date)
  - [5.4 Testing and Program Review](#54-testing-and-program-review)
- [Summary](#summary)
- [Self-Check Questions](#self-check-questions)

---

<br>

## 1. Objects and Classes in Java

### 1.1 What is an Object?

Object-oriented programs use objects. An object represents anything, tangible or intangible, such as an account, a vehicle, or an employee.

An **object** is a runtime entity that has the following.
- **State** (fields/attributes): the data it holds.
- **Behavior** (methods): the operations it can perform.
- **Identity**: a unique reference distinguishing it from other objects.

As a real-world example, a `Car` object might have state (color, speed), behavior (accelerate, brake), and identity (license plate).

### 1.2 What is a Class?

A **class** is a blueprint or template for creating objects. To create an object in a program, we must first define how the object behaves and what information it maintains, and this definition is a class. It defines the following.
- What data members (fields) an object will have
- What methods an object can perform

```java
public class Bicycle {
    private String ownerName;  // field (state)

    public String getOwnerName() {   // method (behavior)
        return ownerName;
    }

    public void setOwnerName(String name) {
        ownerName = name;
    }
}
```

The `private` field `ownerName` cannot be accessed directly from outside the class; it can be read and changed only through the `public` methods `getOwnerName` and `setOwnerName`. This is the encapsulation of Lecture 1.

### 1.3 Relationship Between Objects and Classes

```mermaid
classDiagram
    class Bicycle {
        -String ownerName
        +getOwnerName() String
        +setOwnerName(String) void
    }
    note for Bicycle "Class (Blueprint)"
```

*Figure 1. Class diagram of the Bicycle class*

- A **class** defines the structure; an **object** is a concrete instance.
- Multiple objects can be created from the same class, each with its own state.
- An object created from a class is called an **instance** of that class. For example, creating 100 bank account objects needs only one class (blueprint), and each object stamped out from that class is an instance.

### 1.4 UML Notation

Classes and objects are drawn in **UML** (Unified Modeling Language), an industry-standard notation.

- **Class:** The class name is written inside a rectangle (e.g., `Account`, `Motorcycle`, `HelloWorld`).
- **Object:** The underlined object name is written inside a rectangle (e.g., `SV198`).
- **Object with its class:** `<Object Name> : <Class Name>` is written underlined, indicating which class the object is an instance of (e.g., `SV198 : BankAccount`).

```text
   Class            Object          Object with its class
┌───────────┐   ┌───────────┐   ┌─────────────────────┐
│  Account  │   │   SV198   │   │ SV198 : BankAccount │
│           │   │   ‾‾‾‾‾   │   │ ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾ │
└───────────┘   └───────────┘   └─────────────────────┘
```

*Figure 2. UML notation for a class, an object, and an object with its class*

Given a UML class diagram, you should be able to write the matching Java code.

### 1.5 Messages and Methods

- To instruct a class or an object to perform a task, we send a **message** to it.
- A message can be sent only to classes and objects that understand it. To handle a message, the receiver must have a **method** that matches the message.
- A method defined for a class is called a **class method**, and a method defined for an object is called an **instance method**.
- A value passed along with a message is called an **argument** of the message.

```mermaid
sequenceDiagram
    participant P as Program
    participant A as SV198 : BankAccount
    participant R as MobileRobot (class)
    P->>A: deposit(250.00)
    P->>A: getCurrentBalance()
    A-->>P: current balance
    P->>R: getMaximumSpeed()
    R-->>P: maximum speed
```

*Figure 3. Sending messages to an object and a class and receiving results*

- Sending the message `deposit` with the argument 250.00 to the `BankAccount` object `SV198` deposits 250.00 into that account.
- Sending the message `getCurrentBalance()` to `SV198` returns the current balance of that account.
- Sending the message `getMaximumSpeed()` to the `MobileRobot` class returns the maximum speed common to all `MobileRobot` objects. Because the message goes to the class rather than to a particular object, a class method handles it.

### 1.6 Class Data Values and Instance Data Values

An object is comprised of data values and methods.

- **Instance data value:** Maintains information specific to an individual instance. For example, each `BankAccount` object maintains its own current balance.
- **Class data value:** Maintains information shared by all instances or aggregate information about the instances. For example, the minimum balance is information shared by all accounts, whereas the average balance of all `BankAccount` objects is aggregate information.

```text
                    ┌──────────────────────────┐
                    │       BankAccount        │
                    │ minimum balance: 100.00  │
                    └──────────────────────────┘
                     ┆            ┆           ┆
┌─────────────────────┐ ┌─────────────────────┐ ┌─────────────────────┐
│ SV129 : BankAccount │ │ SV098 : BankAccount │ │ SV211 : BankAccount │
│ current balance:    │ │ current balance:    │ │ current balance:    │
│   908.55            │ │   1304.98           │ │   354.00            │
└─────────────────────┘ └─────────────────────┘ └─────────────────────┘
```

*Figure 4. One class data value and a per-object instance data value*

All three `BankAccount` objects possess the instance data value current balance, but the actual amounts differ. In contrast, there is only one copy of minimum balance for the whole class, shared by all instances. The dotted lines show the instance-of relationship: each object is an instance of the `BankAccount` class.

### 1.7 Inheritance

**Inheritance** is an OOP mechanism for designing two or more entities that are different but share many common features.

- Features common to all classes are defined in the **superclass**.
- The classes that inherit common features from the superclass are called **subclasses**.
- We also call the superclass an ancestor and the subclass a descendant.

```mermaid
classDiagram
    Account <|-- Checking
    Account <|-- Savings
```

*Figure 5. The superclass Account and its subclasses Checking and Savings*

When subclasses have subclasses of their own, they form an **inheritance hierarchy**. The following is an inheritance hierarchy among different types of students.

```mermaid
classDiagram
    Student <|-- Graduate
    Student <|-- Undergrad
    Graduate <|-- Masters
    Graduate <|-- Doctoral
    Graduate <|-- Law
    Undergrad <|-- Commuting
    Undergrad <|-- Resident
```

*Figure 6. Inheritance hierarchy of student types*

In this hierarchy, each class has only one superclass. This differs from multiple inheritance, in which one class inherits from several superclasses at once, and Java does not allow multiple inheritance of classes (Lecture 1).

### 1.8 Software Engineering and the Software Life Cycle

Much like building a skyscraper, developing complex software needs a disciplined approach. **Software engineering** is the application of a systematic and disciplined approach to the development, testing, and maintenance of a program, and the sample program of Section 5 is also developed by its principles.

The sequence of stages from conception to operation of a program is called the **software life cycle**, and it consists of the following five stages.

1. Analysis
2. Design
3. Coding
4. Testing
5. Operation and Maintenance

> **[Software Engineering]** Software engineering studies software processes, which decide in what order the stages of the life cycle are carried out, such as the waterfall model that goes through the stages once in sequence, iterative models that repeat them in short cycles, and agile methods. The incremental development of Section 5 divides a program into small steps and repeats design, coding, and testing in each step, so it is close to an iterative model.

---

<br>

## 2. Creating Objects

### 2.1 Declaration and Instantiation

Creating an object in Java involves two steps.

```java
// Step 1: Declare a reference variable
JFrame myWindow;

// Step 2: Instantiate (create) the object
myWindow = new JFrame();

// Combined in one line:
JFrame myWindow = new JFrame();
```

The `new` keyword allocates memory on the heap and calls the constructor.

An object declaration has the form `<class name> <object name>;`, and the class must be defined before the declaration. An object creation has the form `<object name> = new <class name>(<arguments>);`, and the parentheses stay empty when there are no arguments. In the following code, the first three lines are examples of declarations and the last three are examples of creations; they illustrate each form separately rather than forming one program.

```java
Account customer;
Student jan, jim, jon;
Vehicle car1, car2;

customer = new Customer();
minseok = new Student("Minseok Seo");
car1 = new Vehicle();
```

Declaring a primitive variable such as `int a;` stores the value directly in that space, whereas declaring an object such as `JFrame myWindow;` creates only space for a reference, and the object itself is created dynamically with `new` at run time.

### 2.2 Memory States of Declaration and Creation

```java
Customer customer;           // 1. declaration
customer = new Customer();   // 2. creation
```

1. The identifier `customer` is declared and space is allocated for it in memory. It does not refer to any object yet.
2. A `Customer` object is created, and the identifier `customer` is set to refer to it.

```text
1. After declaration       2. After creation

customer                  customer
┌───────┐                 ┌───────┐
│       │                 │   ●───┼──┐
└───────┘                 └───────┘  │
                                     ▼
                              ┌────────────┐
                              │ : Customer │
                              └────────────┘
```

*Figure 7. Memory states after object declaration and creation*

Drawing the variable and the object separately with an arrow for the reference is called state-of-memory notation. Program diagram notation shows the same situation more simply as a single `customer : Customer` rectangle.

### 2.3 Reference Variables

Reference variables store the **memory address** of an object, not the object itself.

```java
JFrame window1 = new JFrame();
JFrame window2 = window1;  // Both point to the same object
```

> **Key Point:** Assigning one reference to another does not copy the object; both variables refer to the same instance in memory.

Conversely, assigning a new object to a variable breaks the link to the object it referred to before.

```java
Customer customer;
customer = new Customer();
customer = new Customer();
```

```text
customer
┌───────┐
│   ●───┼─────────────────────┐
└───────┘                     ▼
┌────────────┐         ┌────────────┐
│ : Customer │         │ : Customer │
└────────────┘         └────────────┘
 created by first new   created by second new
 (reference lost)
```

*Figure 8. The first object losing its reference after the second new*

No reference to the first `Customer` object remains, so the program can no longer access it, and the object becomes eligible for garbage collection (Lecture 1; Lecture 8, Section 5).

### 2.4 Sending Messages to Objects

A message is sent to an object in the form `<object name>.<method name>(<arguments>);`.

```java
myWindow.setVisible(true);
account.deposit(200.0);
student.setName("john");
car1.startEngine();
```

`myWindow.setVisible(true)` sends the message `setVisible` with the argument `true` to the object `myWindow`.

---

<br>

## 3. Program Structure and Comments

### 3.1 Sample Program

The following program displays a window. `main()` declares and creates the `JFrame` object `myWindow` and then sends it the messages `setSize`, `setTitle`, and `setVisible` (lab code: [DisplayWindow.java](../../Coursework/Lab02_Objects-and-Classes/DisplayWindow.java)).

```java
/*
 *  Chap2Program1 Sample Program: Displaying a Window
 *  File: Chap2Program1.java
 */

import javax.swing.*;

public class Chap2Program1 {

    public static void main(String[] args) {

        JFrame myWindow;                         // declare an object

        myWindow = new JFrame();                 // create the object

        myWindow.setSize(300, 200);              // use the object
        myWindow.setTitle("My First Java Program");
        myWindow.setVisible(true);
    }
}
```

A Java program is composed of (1) comments, (2) import statements, and (3) class declarations. Every Java source file follows this structure.

```java
// 1. Package declaration (optional)
// 2. Import statements
import javax.swing.*;

// 3. Class definition
public class MyProgram {

    // 4. main method (entry point)
    public static void main(String[] args) {
        // Program logic here
    }
}
```

### 3.2 Comments

Comment styles are as follows.
- `//`: a single-line comment.
- `/* ... */`: a multi-line comment.
- `/** ... */`: a Javadoc comment (for API documentation).

```java
/*
   This is a comment with
   three lines of
   text.
*/

// This is a comment
// This is another comment
// This is a third comment

/**
 * This class provides basic clock functions. In addition
 * to reading the current time and today's date, you can
 * use this class for stopwatch functions.
 */
```

### 3.3 Import Statements

An import statement lets a class from another package be used by its simple name. The form is `import <package name>.<class name>;`. In `import javax.swing.JFrame;`, `javax.swing` is the name of the package containing the class to use, and `JFrame`, which starts with an uppercase letter, is the class name. Writing `*` in place of the class name imports all classes of the package.

```java
import javax.swing.JFrame;
import java.util.*;
import com.drcaffeine.simplegui.*;
```

### 3.4 Class and Method Declarations

A class declaration runs from `public class Chap2Program1 {` to the matching `}`, and method declarations go inside it. A method declaration consists of the following elements.

| Element | Example in the `main` method |
|:-----|:-----|
| Modifier | `public` |
| Modifier | `static` |
| Return type | `void` |
| Method name | `main` |
| Parameter | `String[] args` |
| Method body | The statements between `{` and `}` |

*Table 1. Elements of a method declaration*

A simple Java program is therefore completed by filling in four parts of a template: the comment at the top, the import statements, the class name after `class`, and the method body of `public static void main(String[] args)`.

---

<br>

## 4. Java Standard Library Classes

Do not reinvent the wheel: when existing classes satisfy our needs, use them. Learning how to use standard Java classes is the first step toward mastering OOP, and before defining our own classes, we learn how to use existing ones. This section covers `JFrame` and four standard classes: `JOptionPane`, `String`, `Date`, and `SimpleDateFormat`.

### 4.1 JFrame for GUI Windows

`javax.swing.JFrame` represents a window with a title bar, borders, and a content area.

```java
JFrame myWindow = new JFrame();
myWindow.setSize(300, 200);
myWindow.setTitle("My First Java Program");
myWindow.setVisible(true);
```

Key methods are as follows.

| Method | Description |
|:-------|:-----------|
| `setSize(width, height)` | Set window dimensions in pixels |
| `setTitle(String)` | Set the title bar text |
| `setVisible(boolean)` | Show or hide the window |
| `setDefaultCloseOperation(int)` | Define behavior on window close |

*Table 2. Key methods of JFrame*

Without a call to `setDefaultCloseOperation`, the default is `HIDE_ON_CLOSE`, so clicking the close button only hides the window and the program keeps running. To end the program when the window closes, call `setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)`.

### 4.2 JOptionPane for Dialogs

`javax.swing.JOptionPane` provides simple dialog boxes for input and output.

```java
// Input dialog
String name = JOptionPane.showInputDialog(null, "Enter your name:");

// Message dialog
JOptionPane.showMessageDialog(null, "Hello, " + name + "!");
```

- `showMessageDialog` is a simple way to display a result of a computation to the user. With `null` as the first argument, the dialog appears at the center of the screen. `JOptionPane.showMessageDialog(null, "I Love Java");` opens a message window that displays "I Love Java".
- Putting the new line marker `\n` in a string displays multiple lines. `JOptionPane.showMessageDialog(null, "one\ntwo\nthree");` displays one, two, and three on three lines.
- `showInputDialog` is a simple way to input a string. `name = JOptionPane.showInputDialog(null, "What is your name?");` opens an input window at the center of the screen and returns the string the user typed.

### 4.3 String Manipulation

The textual values passed to `showMessageDialog` are all instances of the `String` class. A sequence of characters enclosed in double quotes is a `String` constant. Because a `String` is an object, it can be declared and created like any other object.

```java
String name;                   // 1. declare the identifier name and allocate space
name = new String("Jon Java"); // 2. create a String object and make name refer to it
```

Java `String` is immutable. All the methods below leave the original string unchanged and return a newly created result. Common operations are as follows.

```java
String fullName = "John Doe Smith";
String first = fullName.substring(0, fullName.indexOf(" "));
int length = fullName.length();
String upper = fullName.toUpperCase();
```

Each character of a string is identified by its position (index), starting from 0.

```text
String text = "Espresso";

  0   1   2   3   4   5   6   7
┌───┬───┬───┬───┬───┬───┬───┬───┐
│ E │ s │ p │ r │ e │ s │ s │ o │
└───┴───┴───┴───┴───┴───┴───┴───┘
```

*Figure 9. Character indices of the string "Espresso"*

The `String` class defines close to 50 methods. We look at `substring`, `length`, and `indexOf`, and the concatenation operation. Let `str` be a properly initialized `String` object.

- **substring:** `str.substring(i, j)` returns a new string made of the characters of `str` from position `i` to `j-1`. It requires $0 \le i \le j \le$ `str.length()`, and violating this throws `StringIndexOutOfBoundsException`. If `str` is "programming", `str.substring(3, 7)` is "gram", from g at position 3 to m at position 6.
- **length:** `str.length()` returns the number of characters in `str`. For "programming" it is 11.
- **indexOf:** `str.indexOf(substr)` returns the first position at which `substr` occurs in `str`. The position of "gram" in "programming" is 3. If `substr` does not occur, it returns -1, and the search is case-sensitive.
- **Concatenation:** `str1 + str2` returns a new string that joins the two strings. "pro" + "gram" is "program". `+` is an operator, not a method of the `String` class.

```java
String text = "Espresso";
text.substring(6, 8);   // "so"
text.substring(0, 8);   // "Espresso"
text.substring(1, 5);   // "spre"
text.substring(3, 3);   // "" (empty string)
text.substring(4, 2);   // error: StringIndexOutOfBoundsException

String str1 = "Hello", str2 = "Java", str3 = "", str4 = " ";
str1.length();          // 5
str2.length();          // 4
str3.length();          // 0 (empty string)
str4.length();          // 1 (one space)

String str = "I Love Java and Java loves me.";
str.indexOf("J");       // 7
str.indexOf("love");    // 21
str.indexOf("ove");     // 3
str.indexOf("Me");      // -1

String s1 = "Jon", s2 = "Java";
String r1 = s1 + s2;               // "JonJava"
String r2 = s1 + " " + s2;         // "Jon Java"
String r3 = s2 + ", " + s1;        // "Java, Jon"
String r4 = "Are you " + s1 + "?"; // "Are you Jon?"
```

`indexOf("love")` is 21, not 2, because "Love" at position 2 starts with an uppercase L. None of these operations changes the original strings `text`, `str`, `s1`, and `s2`.

### 4.4 Date and SimpleDateFormat

The `Date` class of the `java.util` package represents a date and time. A newly created `Date` object is set to the current date and time of the computer, and its `toString` method converts the internal format to a string such as `"Fri Oct 31 10:05:18 PST 2003"`. The `SimpleDateFormat` class displays the date information in a desired format.

```java
import java.util.Date;
import java.text.SimpleDateFormat;

Date today = new Date();
SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
String formatted = sdf.format(today);
```

| Pattern | Sample output (Monday 2025-03-10 14:05:09, English locale) |
|:-----|:-----|
| `"MM/dd/yy"` | `03/10/25` |
| `"yyyy-MM-dd"` | `2025-03-10` |
| `"HH:mm:ss"` | `14:05:09` |
| `"HH:mm:ss a"` | `14:05:09 PM` |
| `"HH:mm:ss a, E"` | `14:05:09 PM, Mon` |

*Table 3. SimpleDateFormat patterns and sample outputs*

`MM` is the month, `dd` the day, `yy` and `yyyy` the two-digit and four-digit year, `HH` the hour 0-23, `mm` the minute, `ss` the second, `a` the AM/PM marker, and `E` the day of the week. The minute `mm` and the month `MM` are distinguished by case. The output of `a` and `E` depends on the locale; a Korean locale shows `오후` and `월`.

> **Note:** `Date` and `SimpleDateFormat` are legacy APIs. Since Java 8, `LocalDate`, `LocalDateTime`, and `DateTimeFormatter` of the `java.time` package are recommended.

---

<br>

## 5. Incremental Development: The Monogram Program

### 5.1 Problem and Overall Plan

**Problem:** Write a program that asks for the user's first, middle, and last names and replies with the monogram made of their initials and the current date (e.g., input Min Seok Seo, output MSS and the date).

We need to know what to develop before we develop, so we first identify the major tasks the program has to perform.
- Get the user's first, middle, and last names.
- Extract the initials and create the monogram.
- Output the monogram and the date.

We develop this program incrementally in two steps: (1) start with the program template and add code to get input, and (2) add code to compute and display the monogram and the date.

### 5.2 Step 1: Input the Name

The specification only says to get the user's name and does not say how, so the input method is decided in the Step 1 design. We use `JOptionPane` for input. Of the two input styles, entering the first, middle, and last names separately or entering the full name at once, we choose the second because it is easier and quicker for the user.

```java
import javax.swing.*;

public class Chap2Program2 {

    public static void main(String[] args) {

        String name;

        name = JOptionPane.showInputDialog(null, "Enter your full name (first, middle, last):");

        JOptionPane.showMessageDialog(null, name);
    }
}
```

In the Step 1 test, we run the program and verify that we can enter the name and that the entered name is displayed correctly.

### 5.3 Step 2: Output the Monogram and Date

Because our programming skills are still limited, we make the following assumptions.
- The input string contains the first, middle, and last names.
- The first, middle, and last names are separated by single blank spaces.

For example, "John Quincy Adams" is valid input, but "John Kennedy" (no middle name) and "Harrison, William Henry" (a comma) are not.

Given valid input, the monogram is computed as follows: break the input name into first, middle, and last; extract the first character of each; and concatenate the three characters.

```mermaid
graph TD
    A["Aaron Ben Cosner"] --> B["Aaron"]
    A --> C["Ben Cosner"]
    C --> D["Ben"]
    C --> E["Cosner"]
    B --> F["ABC"]
    D --> F
    E --> F
```

*Figure 10. Splitting a full name to build the monogram*

```java
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class Chap2Program2 {

    public static void main(String[] args) {

        String name, first, middle, last, space, monogram;

        space = " ";

        // Input the full name
        name = JOptionPane.showInputDialog(null, "Enter your full name (first, middle, last):");

        // Extract first, middle, and last names
        first = name.substring(0, name.indexOf(space));
        name = name.substring(name.indexOf(space) + 1, name.length());

        middle = name.substring(0, name.indexOf(space));
        last = name.substring(name.indexOf(space) + 1, name.length());

        // Compute the monogram
        monogram = first.substring(0, 1) + middle.substring(0, 1) + last.substring(0, 1);

        // Get date information
        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");

        // Output the result
        JOptionPane.showMessageDialog(null, "Your monogram is " + monogram + "\n"
                + "Today is " + dateFormat.format(today));
    }
}
```

`first` is the substring before the first space. Next, `name` is replaced by the rest after the first space ("Ben Cosner"), and `middle` and `last` are obtained the same way. `substring(0, 1)` of each string is its first character, and the three first characters are concatenated with `+` to form the monogram. The lab code with the same function is [MonogramDateDisplay.java](../../Coursework/Lab02_Objects-and-Classes/MonogramDateDisplay.java).

### 5.4 Testing and Program Review

In the testing phase, we run the program and verify that correct monograms are displayed for all valid input values. Seeing one correct answer is not enough, so we run the program many times with many different types of valid input.

Even after a working program is developed, we review it critically to see if there are any missing features or possible improvements. For example, the initial prompt can be improved so the user knows that the valid input format requires single spaces between the first, middle, and last names.

---

<br>

## Summary

| Concept | Key Summary |
|:--------|:----------|
| Object | An object is an instance of a class with state, behavior, and identity. |
| Class | A class is a blueprint defining fields and methods. |
| UML notation | A class is drawn as a name in a rectangle, and an object as an underlined name (`SV198`, `SV198 : BankAccount`). |
| Messages and methods | A message sent to an object or a class is handled by a matching instance method or class method. |
| Class and instance data | Each object has its own instance data values, whereas a class data value is shared by all instances. |
| Inheritance | A subclass inherits the common features defined in its superclass. |
| `new` keyword | `new` allocates memory and calls the constructor. |
| Reference variable | A reference variable stores the address of an object, not the object itself. |
| Program components | A Java program is composed of comments, import statements, and class declarations. |
| JFrame | JFrame is a Swing class for creating GUI windows. |
| JOptionPane | JOptionPane provides input/output dialog boxes. |
| String | String is an immutable character sequence with rich manipulation methods such as `substring`, `length`, and `indexOf`. |
| Date and SimpleDateFormat | Date represents the current date and time, and SimpleDateFormat turns it into a string of the desired format. |
| Incremental development | A program is completed in several steps, repeating design, coding, and testing in each step. |

---

<br>

## Self-Check Questions

1. **String methods:** Given `String str = "programming";`, write the values of `str.substring(3, 7)`, `str.length()`, `str.indexOf("gram")`, and `str.indexOf("Gram")`.

   <details>
   <summary>Answer</summary>

   `str.substring(3, 7)` is "gram", the characters at positions 3-6. `str.length()` is 11. `str.indexOf("gram")` is 3. Because `indexOf` is case-sensitive, `str.indexOf("Gram")` is -1.

   </details>

2. **Object with a lost reference:** How many `Customer` objects does the following code create? What happens to the first object?

   ```java
   Customer customer;
   customer = new Customer();
   customer = new Customer();
   ```

   <details>
   <summary>Answer</summary>

   `new` runs twice, so two objects are created. After the second assignment, `customer` refers to the second object, and no reference to the first object remains. The program therefore cannot access the first object again, and it becomes eligible for garbage collection.

   </details>

3. **Class data and instance data:** In the `BankAccount` class, classify each of the following as a class data value or an instance data value: (a) the current balance of an account, (b) the minimum balance common to all accounts, (c) the name of the account owner, (d) the average balance of all accounts.

   <details>
   <summary>Answer</summary>

   (a) and (c) differ from account to account, so they are instance data values. (b) is information shared by all instances and (d) is aggregate information about the instances, so both are class data values.

   </details>

4. **Assumption of the monogram program:** What happens if "John Kennedy" is entered into the program of Section 5.3? Explain why.

   <details>
   <summary>Answer</summary>

   `first` becomes "John" and `name` becomes "Kennedy". "Kennedy" contains no space, so `name.indexOf(space)` returns -1, and the call `name.substring(0, -1)` throws `StringIndexOutOfBoundsException`, terminating the program abnormally. This happens because the program assumes the input contains first, middle, and last names.

   </details>
