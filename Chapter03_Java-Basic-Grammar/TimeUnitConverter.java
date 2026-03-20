/**
 * @file TimeUnitConverter.java
 * @brief Converts seconds to hours, minutes, and seconds format.
 * @author Cheolwon Park
 * @date 2025-03-12
 */

import java.util.Scanner;

/**
 * Reads a total number of seconds from console input and
 * converts it to hours, minutes, and seconds.
 */
public class TimeUnitConverter {

    /** Conversion factor between time units (60 seconds per minute, 60 minutes per hour). */
    static final int UNIT_CHANGER = 60;

    /**
     * Main entry point.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please input the seconds unit as integer value:");

        int time = scanner.nextInt();
        int second = time % UNIT_CHANGER;
        int minute = (time / UNIT_CHANGER) % UNIT_CHANGER;
        int hour = (time / UNIT_CHANGER) / UNIT_CHANGER;

        System.out.print(time + " seconds is ");
        System.out.print(hour + " hr., ");
        System.out.print(minute + " min., and ");
        System.out.print(second + " sec.");

        scanner.close();
    }
}
