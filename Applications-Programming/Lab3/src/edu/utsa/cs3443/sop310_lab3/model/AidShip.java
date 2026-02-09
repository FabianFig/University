package edu.utsa.cs3443.sop310_lab3.model;

/**
 * Represents an aid ship capable of providing emergency support.
 * Extends {@link Ship} and implements {@link EmergencySupport}.
 *
 * @author
 * Fabian Figueroa
 */
public class AidShip extends Ship implements EmergencySupport {

    private String aidType;
    private int suppliesOnBoard;
    private boolean hasHelipad;

    /**
     * Constructs an {@link AidShip} with specified attributes.
     *
     * @param name               the name of the ship
     * @param registrationNumber the registration number
     * @param tonnage            the ship's weight
     * @param crewSize           the number of crew members
     * @param currentPort        the current port
     * @param aidType            the type of aid the ship provides
     * @param suppliesOnBoard    the number of aid supplies onboard
     * @param hasHelipad         true if the ship has a helipad
     */
    public AidShip(String name, String registrationNumber, double tonnage, int crewSize,
                   String currentPort, String aidType, int suppliesOnBoard, boolean hasHelipad) {
        super(name, registrationNumber, tonnage, crewSize, 0.0, currentPort);
        this.aidType = aidType;
        this.suppliesOnBoard = suppliesOnBoard;
        this.hasHelipad = hasHelipad;
    }

    /** @return the type of aid aboard */
    public String getAidType() { return aidType; }

    /** @param aidType sets the type of aid aboard */
    public void setAidType(String aidType) { this.aidType = aidType; }

    /** @return the number of supplies on board */
    public int getSuppliesOnBoard() { return suppliesOnBoard; }

    /** @param suppliesOnBoard sets the number of supplies on board */
    public void setSuppliesOnBoard(int suppliesOnBoard) { this.suppliesOnBoard = suppliesOnBoard; }

    /** @return true if the ship has a helipad */
    public boolean isHasHelipad() { return hasHelipad; }

    /** @param hasHelipad sets helipad availability */
    public void setHasHelipad(boolean hasHelipad) { this.hasHelipad = hasHelipad; }

    /** Deploys aid and prints a deployment message. */
    @Override
    public void deployAid() {
        System.out.println("Aid Ship " + getName() + " deploying " + suppliesOnBoard + " units of " + aidType + ".");
    }

    /** @return formatted string representation of this ship */
    @Override
    public String toString() {
        return String.format(
                "| %-15s | %-18s | %-8.2f | %-9d | %-20s | %-20s | %-14d | %-13s |",
                getName(), getRegistrationNumber(), getTonnage(), getCrewSize(), getCurrentPort(),
                aidType, suppliesOnBoard, hasHelipad ? "Available" : "Not Available"
        );
    }

    /** @return formatted emergency readiness report */
    @Override
    public String getEmergencyReadinessReport() {
        return "Aid Ship Emergency Report:\n" +
                "Name: " + getName() + "\n" +
                "Aid Type: " + aidType + "\n" +
                "Supplies On Board: " + suppliesOnBoard + "\n" +
                "Helipad: " + (hasHelipad ? "Available" : "Not Available") + "\n" +
                "=======================================";
    }

    /** @return the current port */
    public String getPort() { return getCurrentPort(); }

    /** @return the total aid capacity */
    public int getAidCapacity() { return suppliesOnBoard; }

    /** Sets the current speed. */
    @Override
    public void setCurrentSpeed(double speed) { super.setCurrentSpeed(speed); }
}
