/**
 * @file    Lab_MainThreadInfoDemo.java
 * @brief Prints information about the currently running main thread,
 *        including ID, name, priority, and state.
 * @author Cheolwon Park
 * @date 2026-03-21
 */
public class MainThreadInfoDemo {

    /**
     * Displays main thread information.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Thread current = Thread.currentThread();

        System.out.println("Thread name:     " + current.getName());
        System.out.println("Thread ID:       " + current.threadId()); // getId() deprecated since Java 19
        System.out.println("Thread priority: " + current.getPriority());
        System.out.println("Thread state:    " + current.getState());
        System.out.println("Thread group:    " + current.getThreadGroup().getName());
        System.out.println("Is alive:        " + current.isAlive());
        System.out.println("Is daemon:       " + current.isDaemon());
    }
}
