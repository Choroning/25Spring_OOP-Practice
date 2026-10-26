# Course Information

[Repository Guide](README.md) | [English](Course-Information.md) | [한국어](Course-Information.ko.md)

> **Last Updated:** 2026-10-26

## Table of Contents

- [1. Instructor, Teaching Assistant, and Research Lab](#1-instructor-teaching-assistant-and-research-lab)
- [2. Schedule and Class Format](#2-schedule-and-class-format)
- [3. Assessment](#3-assessment)
- [4. Course Policies](#4-course-policies)
- [5. Weekly Plan and Progress](#5-weekly-plan-and-progress)
- [6. References](#6-references)

---

<br>

## 1. Instructor, Teaching Assistant, and Research Lab

- **Instructor:** Prof. Minseok Seo, Department of Computer Science and Software Engineering
- **Office:** Room 7-326A
- **Office Hours:** Tuesday 13:00 ~ 14:50, Thursday 13:00 ~ 17:00
- **Teaching Assistant:** Grades the homework and runs the submitted Java code to check it.

---

<br>

## 2. Schedule and Class Format

- **Credits (Hours):** 3 (3)
- **Intended For:** Second-year students
- **Language of Instruction:** English
- **Class Times:** Monday periods 8-9 (16:00 ~ 17:50), Wednesday periods 5-6 (13:00 ~ 14:50)
- **Classroom:** Room 7-315
- **Class Format:** Lectures and practice, with lecture, practice, quiz, and Q&A as the activity types in the syllabus.
  - Lecture: Lectures are based on the distributed slides, and students have the opportunity to think about the academic issues raised during the lecture through Q&A. Students are advised to review the relevant areas in advance according to the weekly topics of the course schedule.
  - Practice: Since programming skills never grow without doing it yourself, the course provides as many programming opportunities as possible through quizzes or homework and expands the basic grammar learned in class to practical applications.
  - The lecture slides include Self Quiz and Self-Checking problems in which students write programs themselves during class.
- **Attendance:** Attendance is checked.
- **Exam Supervision:** Exams are not held unsupervised.
- **Class Description:** The course builds on the skills gained in Java Fundamentals or Java Foundations to help advance Java programming skills. Students design object-oriented applications with Java and create Java programs through hands-on, engaging activities.
- **Course Description:** An Object-Oriented Programming and Practice course using the Java programming language that covers core concepts of object-oriented programming, in which students learn the fundamentals of Java. The focus is on developing high-quality, working software that solves real problems. The course is designed for students with some programming experience, but students with none who are motivated will do fine, and the syllabus states that the course will be one of the most important classes for students of computer majors.
- **Study Objectives:** Learners who have taken this course are expected to be able to do the following.
  1. Learn the concept and application of object-oriented programming.
  2. Learn the fundamentals of Java programming.
  3. Learn the usage of the Eclipse environment.
  4. Learn the fundamentals of GUI programming.
- **Course and Departmental Objectives:** The course provides concepts and theories about object-oriented programming, and learning the new Java language directly improves the ability to develop and maintain programs.
- **Core Competencies:** The syllabus marks Professionalism and Problem Solving under Program Practice as very highly correlated, and Challenging Spirit under Frontier Spirit, Social Engagement under Sharing and Collaboration, and Creativity under Creative Convergence as highly correlated.
- **Major Competencies:** Of the department's major competencies (Ethics & Leadership, Think & Technology, Network & Application), the course corresponds to Think & Technology.
- **Prerequisites:** No course is compulsory for understanding this course, but taking the following will help. Programming language: any programming language is required (e.g., C, C++, JAVA, Python, R, and SAS).
- **Lab Environment:** Java JDK and the Eclipse IDE. The Chap1 slides guide students through installing the JDK and Eclipse, setting the Eclipse encoding to UTF-8 for Korean, and checking the installation with a Hello World program.

---

<br>

## 3. Assessment

| Item | Weight |
|:-----|-----:|
| Quizzes | 0% |
| Midterm Exam | 30% |
| Final Exam | 30% |
| Homework | 30% |
| Attendance | 10% |

- Feedback on the evaluation is provided.
- The midterm and final exams account for 60% of the total score, and both are programming exams held in a computer lab.
- Several programming quizzes are given in class to check the students' learning progress, and depending on the situation, a quiz may be replaced by homework.
- Several homework assignments are given to practice the grammar learned in class, and three were given in practice. The weeks in which they were given are listed in [5. Weekly Plan and Progress](#5-weekly-plan-and-progress).
- **Common Homework Rules:** The rules stated in the instructions of each homework are as follows.
  - Every program must have a header comment (Homework 1) or Javadoc comments (Homeworks 2 and 3) and follow the standard naming rule, and each failure costs 1 point.
  - In Homeworks 2 and 3, the program must never crash on incorrect user input, and a failure costs 1 point. Homework 3 requires, in principle, one class per Java file, and writing unnecessary nested classes also costs 1 point.
  - The report (.docx) filled with answers and result screenshots and all the Java code (.java) written are compressed into a .zip file and submitted to Blackboard by the deadline. The compressed file is named with the homework number, student ID, and name, as in `HW1_YourID_Yourname.zip`.
  - The Java code must run cleanly when the teaching assistant runs it.
  - Homeworks 2 and 3 give partial scores, so students are encouraged to try as far as they can even if they cannot solve a problem completely.
  - To prevent abuse of ChatGPT, the pattern recognition for the issues is left to the developer in most problems. The instructor states the wish that students feel directly in what areas developers can have an edge in the AI era, such as raising issues, recognizing patterns, and segmenting requirements, where ChatGPT still cannot beat a human developer. Homework 1 tells students to use ChatGPT actively.
- **Homework 1 (HW#1):** Ten problems.
  - Q1: Write `Q1.java`, which receives an English sentence and prints it in reverse order word by word. Students must find the case rules in the example and produce the same result.
  - Q2: Write `Q2.java`, which receives a name and student ID from a dialog and prints them as in the example. The entered name and student ID must be separated automatically, and the digits of the student ID must be summed cumulatively with a loop (for the ID "12345", the result of "1 + 2 + 3 + 4 + 5").
  - Q3: Freely design a program that includes the `JOptionPane`, `String`, `Date`, and `SimpleDateFormat` classes learned so far and conditional statements. Public problems on the Internet may be referenced, but the link must be quoted in the report.
  - Q4: Develop a real-time age calculator, `Q4.java`, as in the example.
  - Q5: Write a GUI program that converts Korean won into US dollars through a dialog. The exchange rate is fixed at 1348.81 won per dollar.
  - Q6: Design `Q6.java`, which receives nine integers and prints the input values, row sums, column sums, and total sum.
  - Q7: Write a CUI program that receives an amount through `Scanner` and tells the optimal (minimal) number of bills (for 50250 won, one 50000-won bill, two 100-won coins, and one 50-won coin).
  - Q8: Develop `Q8.java`, which finds and sums all the real numbers hidden in a sentence.
  - Q9: Write a GUI program that takes a date in the YYYY-MM-DD format and tells the season by the Korean standard (March-May spring, June-August summer, September-November fall, December-February winter).
  - Q10: Implement a runnable Java-based Notepad program using ChatGPT, and find and edit the relevant part so that the student's ID and name appear in the title bar. There are no restrictions on its design and operation.
- **Homework 2 (HW#2):** Nine problems. The instructions state that as of April 10, when the homework was uploaded, some skills such as Javadoc comments had not been learned yet and would be learned the following week. The files `Q1.data`, `Q1_CodeBook.data`, and `Q9.Data` were distributed with the handout.
  - Q1: Develop a program that decrypts the given binary file based on the format described in the codebook. The binary file can be restored only in the order of the codebook; students confirm the restoration by reproducing the result in the screenshot, separate each different form, and save and submit them as five human-readable files.
  - Q2: Write `Q2.java`, which receives eight integers, stores them in a one-dimensional array, and sorts them in ascending order. A non-integer input must be handled as an exception with a warning message and asked again, and quicksort or bubble sort may be used.
  - Q3: Implement a real-time calculator, `Q3.java`, that accepts an unlimited number of inputs in the form (operator [space] real number). The starting value is 0; dividing by 0 or entering a non-real value must be handled appropriately as an exception instead of ending in an error, and entering "exit" prints the process and results of all the operations entered so far and terminates the program.
  - Q4: Write `Q4.java`, which receives a name from a dialog and prints it as in the example.
  - Q5: Implement a `Student` class and a main class `Q5` according to the class diagram. The name, student ID, gender, and GPA are data members with the given access modifiers, with a constructor and methods that set and get the value of each member. The name and student ID are chosen randomly so that objects do not overlap, the gender is randomly "Male" or "Female", and the GPA is random within 0.0-4.5. After generating 10,000 students, implement and call a method that shows the name, student ID, gender, and GPA of the student with the highest GPA.
  - Q6: Write a `BankAccount` class and `Q6` according to the class diagram. Create 1,000 accounts with owners named sequentially from "owner_1" to "owner_1000" and initial balances chosen randomly between 0 and 50,000, add 5.64% interest to every account, and print the owner and balance of the account with the largest balance. Implement `transfer(BankAccount, BankAccount, double)`, which takes the sender, the recipient, and the amount, and which must not transfer and must give an error message when the amount is greater than the balance.
  - Q7: Prove empirically through actual Java code the proposition "In general, the quick sort algorithm is faster than the bubblesort algorithm", using the runtime estimation method learned in class.
  - Q8: Develop a system program that calculates the difference in days from a given date. It must calculate any point in the past or future, print D+ for future points and D- for past points, and receiving the date through a separate UI is treated as incorrect.
  - Q9: Implement an `Automobile` class and a main class `Q9` for a used car management program. Load all the information in the tab-delimited `Q9.Data` file into individual objects, and provide at least three overloaded constructors that treat unknown values as "NA". Randomly generate 100 new cars (no duplicate model names) whose feature values lie within the range of the existing data with `genAutomobile()`, describe the ideal design direction of a CUI that searches cars by conditions such as "Miles > 20 and Number of cylinders == 4", and develop a prototype.
- **Homework 3 (HW#3):** Five problems worth 20 points (Q1), 30 points (Q2), 30 points (Q3), 10 points (Q4), and 10 points (Q5). A DNA sequence file `Q1.txt` of about 200 MB was distributed with the handout.
  - Q1 and Q2 deal with vaccine development through computational methods, against the background that avian influenza, which can be transmitted to humans, is expected to become another infectious disease that causes a crisis after COVID-19 in 2020 (<https://www.who.int/emergencies/disease-outbreak-news/item/2024-DON524>).
  - Q1: Develop a program that finds the frequencies of the 1,024 five-letter combinations of A, C, G, and T (from AAAAA to TTTTT) in the DNA sequence of `Q1.txt` with a sliding window that moves one letter at a time. The result is saved in `Q1_output.txt` and submitted together, the elapsed time is displayed in the command window, and "N" in the file may be ignored.
  - Q2: Since `Q1.txt` is a toy example smaller than 1/100,000,000,000 of the scale of the actual problem, speed up the program of Q1 with parallel processing. Students develop the optimal program considering that the teaching assistant's grading computer has 16 cores, and the grades are ranked by computation speed from first place (30 points).
  - Q3: Develop a client-server program in which N users connect to the server and perform joint calculations. Assuming that the cumulative total variable on the server is of type int, when a user causes an overflow, inform all users who caused the problem and terminate the connection. Exception handling, multi-thread support, display of the query time on the server, and the guidance conditions must be included.
  - Q4: Develop the same date difference system program as Q8 of Homework 2 (D+ for the future, D- for the past).
  - Q5: Implement a simple calculator based on the server-client model in which all calculations are performed on the server.

---

<br>

## 4. Course Policies

- Lectures are based on the slides uploaded to Blackboard, and homework is submitted to Blackboard.
- The instructions of Homework 1 state that a student caught copying another student's code receives an F grade.
- **Support for Students with Disabilities:** Students with any questions about the support services for students with disabilities may contact Prof. Minseok Seo, and they will find together the best way to take this course depending on the type of disability.
- **Learning Ethics:** Students have the following general obligations in all processes of completing the curriculum, including attending lectures, preparing and submitting assignments, and taking exams.
  1. Participate actively in all learning with honesty and a passionate attitude based on the purpose and educational philosophy of the university.
  2. Be aware of their position as students receiving higher education and devote themselves to creative learning based on moral sensitivity.
  3. Faithfully carry out what they have promised faculty members or fellow students in connection with educational activities.
  4. Respect the character of faculty members and other students and observe basic manners.
- Credits found to have been obtained through dishonest behavior such as plagiarism, duplicate use of assignments from other classes, or exam misconduct, or through any other unfair method, are canceled even after they have been granted, and disciplinary action such as suspension, withdrawal, or expulsion may follow, taking the circumstances of the act into account.
- The university operates a plagiarism detection system (Blackboard) to raise awareness of learning ethics and encourage creativity, and students must check in advance whether their work counts as plagiarism when submitting assignments.

---

<br>

## 5. Weekly Plan and Progress

| Week | Class Dates (Mon, Wed) | Planned Topic | Lecture Decks Covered |
|:-----|:-----|:-----|:-----|
| W01 | 3/3, 3/5 | Orientation (Hello World!) & Installation of Eclipse | - |
| W02 | 3/10, 3/12 | Basic Java Grammar: Identifier, Data type, Operator, and Conditional Statement | Chap1.Introduction_OOP<br>Chap2.Introduction_Object_Class |
| W03 | 3/17, 3/19 | Loop, Array, and exception handling | Chap3.JavaBasicGrammar |
| W04 | 3/24, 3/26 | Class and object | Chap4.JavaBasicGrammar_PartII<br>Homework 1 |
| W05 | 3/31, 4/2 | Inheritance | Chap5.JavaBasicGrammar_Array<br>Chap6.2DArray_CustomFunction_ExceptionHandling |
| W06 | 4/7, 4/9 | Java package | Chap7.Advanced_Class<br>Homework 2 |
| W07 | 4/14, 4/16 | Generics and Collections | Chap8.Advanced_Class_Vol2<br>Chap10.FileIO |
| W08 | 4/21, 4/23 | Mid-term Exam | Midterm exam |
| W09 | 4/28, 4/30 | I/O Streams and File I/O | Chap9.Assertion_Generic_Collection<br>Chap11.Advanced_OOP |
| W10 | 5/5, 5/7 | Java GUI basics, AWT and Swing | - |
| W11 | 5/12, 5/14 | Java event handling | Chap12.Advanced_GUI |
| W12 | 5/19, 5/21 | Swing components and applications | Chap13.Thread |
| W13 | 5/26, 5/28 | Graphic & Advanced Swing components | Chap14.NetworkProgramming<br>Chap15.Thread_v2 |
| W14 | 6/2, 6/4 | Threads and Multitasking | Homework 3 |
| W15 | 6/9, 6/11 | Network & JDBC programming | Final exam<br>End of term |
| W16 | 6/16, 6/18 | Final Exam | - |

- The planned topics follow the weekly schedule of the syllabus. The syllabus planned Problem Solving as the core competency and PPT slides as the material for every week except the exam weeks, and states that the plan for the second half of the semester could be modified depending on the students' learning achievement.
- Chap6.2DArray_CustomFunction_ExceptionHandling was covered over weeks 5 and 6, and Chap7.Advanced_Class over weeks 6 and 7.
- The midterm exam was held in week 8, and the final exam was held in week 15, when the term ended.
- The actual progress differs from the order of the deck numbers. Chap10 was covered first, together with Chap8, in week 7, and Chap9 and Chap11 were covered in week 9. No lecture deck covers JDBC programming, which the syllabus planned.
- The deck numbers Chap1-Chap15 correspond to `Lec01`-`Lec15` in this repository. The unit numbers follow the deck numbers rather than the order of coverage, and the lab code numbers in `Coursework` (`Lab02`-`Lab15`) follow the same numbers. The units are listed in the [`Lecture-Notes` index](Lecture-Notes/README.md).

---

<br>

## 6. References

| Type | Contents |
|:-----|:-----|
| Lecture Slides | PPT slides (Chap1-Chap15) and lecture demonstration code (Chap2-Chap15) provided by the instructor |
| Online | [MIT OpenCourseWare 6.092: Introduction to Programming in Java](https://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-092-introduction-to-programming-in-java-january-iap-2010/lecture-notes/) |
| Online | [MOOC.fi: Programming Part 1](https://moocfi.github.io/courses/2013/programming-part-1/) |
| Online | [Chortle: Introduction to Computer Science using Java](https://chortle.ccsu.edu/CS151/cs151java.html) |

- According to the syllabus, no textbook is required and lectures are given with the slides uploaded to Blackboard, and the three online resources above may be helpful for Java programming.
