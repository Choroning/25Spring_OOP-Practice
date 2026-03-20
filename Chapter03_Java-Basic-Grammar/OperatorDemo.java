/**
 * @file OperatorDemo.java
 * @brief Demonstrates assignment, increment, and decrement operators in Java.
 * @author Cheolwon Park
 * @date 2025-03-12
 */

/**
 * Shows the behavior of compound assignment operators and
 * pre/post increment/decrement operators.
 */
public class OperatorDemo {

    /**
     * Main entry point.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        int a = 3, b = 3, c = 3;

        // Compound assignment operators
        a += 3; // a = a + 3 = 6
        b *= 3; // b = b * 3 = 9
        c %= 2; // c = c % 2 = 1

        int d = 3;

        // Post-increment: use value first, then increment
        a = d++; // a = 3, d = 4
        System.out.println("a = " + a + ", d = " + d);

        // Pre-increment: increment first, then use value
        a = ++d; // d = 5, a = 5
        System.out.println("a = " + a + ", d = " + d);

        // Post-decrement
        a = d--; // a = 5, d = 4
        System.out.println("a = " + a + ", d = " + d);

        // Pre-decrement
        a = --d; // d = 3, a = 3
        System.out.println("a = " + a + ", d = " + d);
    }
}
