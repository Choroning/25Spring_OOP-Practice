/**
 * @file    Lab_Bicycle.java
 * @brief A simple Bicycle class demonstrating encapsulation, constructors,
 *        and getter/setter methods.
 * @author Cheolwon Park
 * @date 2026-03-21
 */
public class Bicycle {

    private String ownerName;

    /** Default constructor: sets owner to "Unassigned". */
    public Bicycle() {
        this.ownerName = "Unassigned";
    }

    /**
     * Parameterized constructor.
     *
     * @param ownerName the name of the bicycle owner
     */
    public Bicycle(String ownerName) {
        this.ownerName = ownerName;
    }

    /** @return the owner's name */
    public String getOwnerName() {
        return ownerName;
    }

    /** @param ownerName the new owner's name */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
