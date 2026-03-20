/**
 * @file SynchronizationDemo.java
 * @brief Demonstrates the difference between unsynchronized and synchronized
 *        shared counter access by two threads.
 * @author Cheolwon Park
 * @date 2026-03-21
 */
public class SynchronizationDemo {

    private static int sharedCounter = 0;
    private static final Object lock = new Object();

    /**
     * Runs two threads that increment a shared counter, first without
     * synchronization (showing race conditions), then with synchronization.
     *
     * @param args command-line arguments (not used)
     * @throws InterruptedException if threads are interrupted
     */
    public static void main(String[] args) throws InterruptedException {
        // Unsynchronized run
        sharedCounter = 0;
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                sharedCounter += 10;
                System.out.println(Thread.currentThread().getName() + " : " + sharedCounter);
                try { Thread.sleep(50); } catch (InterruptedException e) { return; }
            }
        }, "ThreadA");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                sharedCounter += 10;
                System.out.println(Thread.currentThread().getName() + " : " + sharedCounter);
                try { Thread.sleep(50); } catch (InterruptedException e) { return; }
            }
        }, "ThreadB");

        System.out.println("=== Without Synchronization ===");
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        // Synchronized run
        sharedCounter = 0;
        System.out.println("\n=== With Synchronization ===");

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (lock) {
                    sharedCounter += 10;
                    System.out.println(Thread.currentThread().getName() + " : " + sharedCounter);
                }
                try { Thread.sleep(50); } catch (InterruptedException e) { return; }
            }
        }, "SyncA");

        Thread t4 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (lock) {
                    sharedCounter += 10;
                    System.out.println(Thread.currentThread().getName() + " : " + sharedCounter);
                }
                try { Thread.sleep(50); } catch (InterruptedException e) { return; }
            }
        }, "SyncB");

        t3.start();
        t4.start();
        t3.join();
        t4.join();
    }
}
