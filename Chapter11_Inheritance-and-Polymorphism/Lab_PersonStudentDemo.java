/**
 * @file    Lab_PersonStudentDemo.java
 * @brief Demonstrates inheritance between Person and Student classes,
 *        showing how subclasses access protected and public members
 *        while private members require accessor methods.
 * @author Cheolwon Park
 * @date 2026-03-21
 */
public class PersonStudentDemo {

    /** Base class representing a person. */
    static class Person {
        private int weight;
        protected int height;
        public String name;
        int age; // default (package-private) access

        public void setWeight(int weight) { this.weight = weight; }
        public int getWeight() { return weight; }
    }

    /** Subclass representing a student, inheriting from Person. */
    static class Student extends Person {
        private String studentId;

        public Student(String id, String name, int age, int height, int weight) {
            this.studentId = id;
            this.name = name;       // public: directly accessible
            this.age = age;          // default: accessible in same package
            this.height = height;    // protected: accessible in subclass
            setWeight(weight);       // private: must use setter
        }

        public void display() {
            System.out.println("Student ID: " + studentId);
            System.out.println("Name:       " + name);
            System.out.println("Age:        " + age);
            System.out.println("Height:     " + height + " cm");
            System.out.println("Weight:     " + getWeight() + " kg");
        }
    }

    /**
     * Creates a Student object and displays its information.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Student student = new Student("2024001", "Alice", 22, 170, 65);
        student.display();
    }
}
