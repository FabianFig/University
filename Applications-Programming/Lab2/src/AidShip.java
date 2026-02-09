/**
 * Represents an aid ship capable of providing emergency support.
 * Extends the Ship class and implements EmergencySupport interface.
 *
 * @author Fabian Figueroa
 */
public class AidShip extends Ship implements EmergencySupport {
    private String aidType;
    private int suppliesOnBoard;
    private boolean hasHelipad;

    /**
     * Constructs an AidShip with specified attributes.
     *
     * @param name               the name of the ship
     * @param registrationNumber the registration number of the ship
     * @param tonnage            the weight of the ship
     * @param crewSize           the number of crew members aboard
     * @param currentPort        the current location of the ship
     * @param aidType            the type of aid the ship is carrying
     * @param suppliesOnBoard    the amount of supplies on board
     * @param hasHelipad         true if the ship has a helipad, false otherwise
     */
    public AidShip(String name, String registrationNumber, double tonnage, int crewSize,
                   String currentPort, String aidType, int suppliesOnBoard, boolean hasHelipad) {
        super(name, registrationNumber, tonnage, crewSize, 0.0, currentPort);
        this.aidType = aidType;
        this.suppliesOnBoard = suppliesOnBoard;
        this.hasHelipad = hasHelipad;
    }

    /**
     * Returns the type of aid aboard the ship.
     * @return the aid type
     */
    public String getAidType() {
        return aidType;
    }

    /**
     * Sets the type of aid aboard the ship.
     * @param aidType the aid type
     */
    public void setAidType(String aidType) {
        this.aidType = aidType;
    }

    /**
     * Returns the number of supplies on board.
     * @return the number of supplies
     */
    public int getSuppliesOnBoard() {
        return suppliesOnBoard;
    }

    /**
     * Sets the number of supplies on board.
     * @param suppliesOnBoard the number of supplies
     */
    public void setSuppliesOnBoard(int suppliesOnBoard) {
        this.suppliesOnBoard = suppliesOnBoard;
    }

    /**
     * Indicates whether the ship has a helipad.
     * @return true if the ship has a helipad, false otherwise
     */
    public boolean isHasHelipad() {
        return hasHelipad;
    }

    /**
     * Sets the helipad availability status.
     * @param hasHelipad true if the ship has a helipad, false otherwise
     */
    public void setHasHelipad(boolean hasHelipad) {
        this.hasHelipad = hasHelipad;
    }

    /**
     * Deploys aid and prints a message indicating the ship's name and amount of aid deployed.
     */
    @Override
    public void deployAid() {
        System.out.println("Aid Ship " + getName() + " is deploying " + getSuppliesOnBoard() + " of Medical aid.");
    }

    /**
     * Returns a string representation of the ship's attributes.
     * @return a detailed description of the aid ship
     */
    @Override
    public String toString() {
        return "AidShip [name='" + getName() +
                "', registrationNumber=" + getRegistrationNumber() +
                ", tonnage=" + getTonnage() +
                ", crewSize=" + getCrewSize() +
                ", currentSpeed=" + getCurrentSpeed() +
                ", currentPort=" + getCurrentPort() +
                ", aidType=" + getAidType() +
                ", suppliesOnBoard=" + getSuppliesOnBoard() +
                ", hasHelipad=" + (hasHelipad ? "true" : "false") + "]";
    }

    /**
     * Returns a report detailing the ship's emergency readiness.
     * @return a formatted emergency readiness report
     */
    @Override
    public String getEmergencyReadinessReport() {
        return "Aid Ship Emergency Report:\n" +
                "Name: " + getName() + "\n" +
                "Aid type: " + getAidType() + "\n" +
                "Supplies on Board: " + getSuppliesOnBoard() + "\n" +
                "Helipad: " + (hasHelipad ? "Available" : "Not Available") + "\n" +
                "=======================================";
    }

    /**
     * Returns the current port of the ship.
     * @return the port of the ship
     */
    public String getPort() {
        return getCurrentPort();
    }

    /**
     * Returns the aid capacity of the ship.
     * @return the aid capacity
     */
    public int getAidCapacity() {
        return getSuppliesOnBoard();
    }

    /**
     * Sets the current speed of the ship.
     * @param speed the new speed
     */
    @Override
    public void setCurrentSpeed(double speed) {
        super.setCurrentSpeed(speed);
    }
}
