/**
 * @file    Lab_Dice.java
 * @brief A configurable dice class that encapsulates random number generation.
 *        Supports any number of faces (default 6).
 * @author Cheolwon Park
 * @date 2025-04-14
 */

import java.util.Random;

public class Dice {

    private static final int DEFAULT_FACES = 6;
    private final int faces;
    private final Random random;

    /** Creates a standard 6-sided die. */
    public Dice() {
        this(DEFAULT_FACES);
    }

    /**
     * Creates a die with the specified number of faces.
     *
     * @param faces the number of faces (must be at least 1)
     */
    public Dice(int faces) {
        if (faces < 1) {
            throw new IllegalArgumentException("Faces must be at least 1.");
        }
        this.faces = faces;
        this.random = new Random();
    }

    /**
     * Rolls the die and returns a value between 1 and the number of faces.
     *
     * @return the roll result
     */
    public int roll() {
        return random.nextInt(faces) + 1;
    }

    /** @return the number of faces */
    public int getFaces() {
        return faces;
    }

    /**
     * Demonstrates rolling dice with different face counts.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Dice d6 = new Dice();
        Dice d20 = new Dice(20);

        System.out.println("Rolling a 6-sided die: " + d6.roll());
        System.out.println("Rolling a 20-sided die: " + d20.roll());
    }
}
