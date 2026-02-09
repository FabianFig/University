package edu.utsa.cs3443.sop310_lab3.model;

/**
 * Abstract class representing a general-purpose ship.
 * Implements the {@link Navigable} interface, allowing movement and speed control.
 *
 * Stores common ship attributes such as name, registration number, tonnage,
 * crew size, current speed, and current port.
 *
 * Subclasses must extend this class to define specific ship behaviors.
 *
 * @author Fabian Figueroa
 */
public abstract class Ship implements Navigable {

    private String name;
    private String registrationNumber;
    private double tonnage;
    private int crewSize;
    private double currentSpeed;
    private String currentPort;

    /**
     * Constructs a {@link Ship} with specified attributes.
     *
     * @param name               the name of the ship
     * @param registrationNumber the registration number of the ship
     * @param tonnage            the weight of the ship
     * @param crewSize           the number of crew members aboard
     * @param currentSpeed       the ship’s initial speed
     * @param currentPort        the ship’s current port location
     */
    public Ship(String name, String registrationNumber, double tonnage, int crewSize, double currentSpeed, String currentPort) {
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.tonnage = tonnage;
        this.crewSize = crewSize;
        this.currentSpeed = currentSpeed;
        this.currentPort = currentPort;
    }

    /**
     * Returns the ship’s name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the ship’s name.
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the registration number.
     * @return the registration number
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * Updates the registration number.
     * @param registrationNumber the new registration number
     */
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    /**
     * Returns the ship’s tonnage.
     * @return the tonnage
     */
    public double getTonnage() {
        return tonnage;
    }

    /**
     * Updates the ship’s tonnage.
     * @param tonnage the new tonnage
     */
    public void setTonnage(double tonnage) {
        this.tonnage = tonnage;
    }

    /**
     * Returns the crew size.
     * @return the number of crew members
     */
    public int getCrewSize() {
        return crewSize;
    }

    /**
     * Updates the crew size.
     * @param crewSize the new crew size
     */
    public void setCrewSize(int crewSize) {
        this.crewSize = crewSize;
    }

    /**
     * Returns the current port.
     * @return the current port name
     */
    public String getCurrentPort() {
        return currentPort;
    }

    /**
     * Updates the ship’s current port.
     * @param currentPort the new port name
     */
    public void setCurrentPort(String currentPort) {
        this.currentPort = currentPort;
    }

    /**
     * Updates the current speed of the ship.
     * @param currentSpeed the new speed
     */
    public void setCurrentSpeed(double currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    /**
     * Returns the ship’s current speed.
     * @return the current speed
     */
    @Override
    public double getCurrentSpeed() {
        return currentSpeed;
    }

    /**
     * Navigates the ship to the specified destination.
     * Updates the current port.
     *
     * @param location the destination port
     */
    @Override
    public void navigateTo(String location) {
        System.out.println(name + " is navigating to " + location + ".");
        this.currentPort = location;
    }

    /**
     * Docks the ship and sets its speed to zero.
     * @param port the docking port
     */
    public void dock(String port) {
        this.currentPort = port;
        this.currentSpeed = 0.0;
    }

    /**
     * Returns a formatted summary of the ship’s attributes.
     * @return a string representation of the ship
     */
    @Override
    public String toString() {
        return String.format("%s [Name=%s, Reg#=%s, Tonnage=%.2f, Crew=%d, Speed=%.1f, Port=%s]",
                getClass().getSimpleName(), name, registrationNumber, tonnage, crewSize, currentSpeed, currentPort);
    }
}
