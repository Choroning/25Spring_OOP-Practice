/**
 * @file    Lab_Account.java
 * @brief A bank account class demonstrating encapsulation with private fields,
 *        constructor overloading, and input validation in mutator methods.
 * @author Cheolwon Park
 * @date 2025-04-07
 */
public class Account {

    private String ownerName;
    private double balance;

    /** Default constructor: creates an unassigned account with zero balance. */
    public Account() {
        this("Unassigned", 0.0);
    }

    /**
     * Parameterized constructor.
     *
     * @param ownerName     the account holder's name
     * @param initialBalance the starting balance (must be non-negative)
     */
    public Account(String ownerName, double initialBalance) {
        this.ownerName = ownerName;
        this.balance = Math.max(0.0, initialBalance);
    }

    /**
     * Deposits the specified amount into the account.
     *
     * @param amount the amount to deposit (must be positive)
     */
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    /**
     * Withdraws the specified amount if sufficient funds exist.
     *
     * @param amount the amount to withdraw (must be positive and within balance)
     */
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        }
    }

    /** @return the current balance */
    public double getBalance() {
        return balance;
    }

    /** @return the owner's name */
    public String getOwnerName() {
        return ownerName;
    }

    /** @param name the new owner's name */
    public void setOwnerName(String name) {
        this.ownerName = name;
    }
}
