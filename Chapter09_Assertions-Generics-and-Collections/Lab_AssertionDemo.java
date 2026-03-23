/**
 * @file    Lab_AssertionDemo.java
 * @brief Demonstrates Java assertions for verifying bank account invariants.
 *        Run with: java -ea AssertionDemo
 * @author Cheolwon Park
 * @date 2025-04-14
 */
public class AssertionDemo {

    /** Account balance used for demonstration. */
    static double balance;

    /**
     * Tests deposit and withdraw operations with assertion checks.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        balance = 100;

        deposit(20);
        System.out.println("After deposit:  Balance = " + balance);

        withdraw(20);
        System.out.println("After withdraw: Balance = " + balance);
    }

    /**
     * Deposits the given amount and asserts the balance increased.
     *
     * @param amount the amount to deposit
     * @return the new balance
     */
    private static double deposit(double amount) {
        double oldBalance = balance;
        balance += amount;
        assert balance > oldBalance :
            "Error: balance did not increase after deposit";
        return balance;
    }

    /**
     * Withdraws the given amount and asserts the balance decreased.
     *
     * @param amount the amount to withdraw
     * @return the new balance
     */
    private static double withdraw(double amount) {
        double oldBalance = balance;
        balance -= amount;
        assert balance < oldBalance :
            "Error: balance did not decrease after withdrawal";
        return balance;
    }
}
