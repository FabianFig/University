/**
 * Abstract class representing a general-purpose ship.
 * Implements the Navigable interface, allowing movement and speed control.
 *
 * Stores common ship attributes such as name, registration number, tonnage,
 * crew size, current speed, and current port.
 *
 * Subclasses must extend this class to define specific ship behaviors.
 *
 * @author Fabian Figueroa
 */
public abstract class Ship implements Navigable{
    private String name;
    private String registrationNumber;
    private double tonnage;
    private int crewSize;
    private double currentSpeed;
    private String currentPort;

    /**
     * Constructs a Ship with specified attributes.
     *
     * @param name               the name of the ship
     * @param registrationNumber the registration number of the ship
     * @param tonnage            the weight of the ship
     * @param crewSize           the number of crew members aboard
     * @param speed              the initial speed of the ship
     * @param currentPort        the current location of the ship
     */
    public Ship(String name, String registrationNumber, double tonnage, int crewSize, double speed, String currentPort) {
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.tonnage = tonnage;
        this.crewSize = crewSize;
        this.currentSpeed = currentSpeed;
        this.currentPort = currentPort;
    }

    /**
     * Returns the name of the ship.
     * @return the ship name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the ship.
     * @param name the new ship name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the registration number of the ship.
     * @return the registration number
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * Sets the registration number of the ship.
     * @param registrationNumber the new registration number
     */
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    /**
     * Returns the tonnage of the ship.
     * @return the ship's tonnage
     */
    public double getTonnage() {
        return tonnage;
    }

    /**
     * Sets the tonnage of the ship.
     * @param tonnage the new tonnage
     */
    public void setTonnage(double tonnage) {
        this.tonnage = tonnage;
    }

    /**
     * Returns the number of crew members aboard.
     * @return the crew size
     */
    public int getCrewSize() {
        return crewSize;
    }

    /**
     * Sets the number of crew members aboard.
     * @param crewSize the new crew size
     */
    public void setCrewSize(int crewSize) {
        this.crewSize = crewSize;
    }

    /**
     * Returns the current port of the ship.
     * @return the current port
     */
    public String getCurrentPort() {
        return currentPort;
    }

    /**
     * Sets the current port of the ship.
     * @param currentPort the new port
     */
    public void setCurrentPort(String currentPort) {
        this.currentPort = currentPort;
    }

    /**
     * Sets the current speed of the ship.
     * @param speed the new speed
     */
    public void setCurrentSpeed(double speed) {
        this.currentSpeed = speed;
    }

    /**
     * Returns the current speed of the ship.
     * @return the current speed
     */
    @Override
    public double getCurrentSpeed() {
        return currentSpeed;
    }

    /**
     * Navigates the ship to a specified location.
     * Updates the current port and prints a navigation message.
     * @param location the destination port
     */
    @Override
    public void navigateTo(String location) {
        System.out.println(name + " is going to " + location);
        this.currentPort = location;
    }

    /**
     * Returns a string representation of the ship's attributes.
     * @return a formatted string describing the ship
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + name +
                ", Registration Number: " + registrationNumber +
                ", Tonnage=" + tonnage +
                ", Crew Size=" + crewSize +
                ", Current Speed=" + currentSpeed +
                ", Current Port=" + currentPort + "]";
    }

    /**
     * Docks the ship at the specified port and resets its speed to zero.
     * @param port the port to dock at
     */
    public void dock(String port) {
        this.currentPort = port;
        this.currentSpeed = 0.0;
    }

}