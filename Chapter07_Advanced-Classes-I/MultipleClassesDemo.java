/**
 * @file MultipleClassesDemo.java
 * @brief Demonstrates using the Bicycle and Account classes together,
 *        showing how multiple classes collaborate in an OOP program.
 * @author Cheolwon Park
 * @date 2026-03-21
 */
public class MultipleClassesDemo {

    /**
     * Creates Bicycle and Account objects and demonstrates their usage.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Bicycle demonstration
        Bicycle bike1 = new Bicycle("Alice");
        Bicycle bike2 = new Bicycle("Bob");

        System.out.println(bike1.getOwnerName() + " owns a bicycle.");
        System.out.println(bike2.getOwnerName() + " also owns a bicycle.");

        // Account demonstration
        Account account = new Account("Alice", 1000.0);
        System.out.println("\nInitial balance: $" + account.getBalance());

        account.deposit(500.0);
        System.out.println("After deposit:  $" + account.getBalance());

        account.withdraw(200.0);
        System.out.println("After withdraw: $" + account.getBalance());
    }
}
