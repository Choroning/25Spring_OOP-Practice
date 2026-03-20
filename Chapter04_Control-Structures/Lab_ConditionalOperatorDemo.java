/**
 * @file    Lab_ConditionalOperatorDemo.java
 * @brief Demonstrates usage of the ternary (conditional) operator
 *        to compute the absolute difference between two integers.
 * @author Cheolwon Park
 * @date 2026-03-21
 */
public class ConditionalOperatorDemo {

    /**
     * Entry point. Computes and prints the absolute difference
     * between two predefined integer values using the ternary operator.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        int firstNum = 10;
        int secondNum = 5;

        int difference = (firstNum > secondNum)
                ? (firstNum - secondNum)
                : (secondNum - firstNum);

        System.out.println("First number:  " + firstNum);
        System.out.println("Second number: " + secondNum);
        System.out.println("Absolute difference: " + difference);
    }
}
