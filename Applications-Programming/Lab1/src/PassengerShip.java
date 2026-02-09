/**
 * Represents a passenger ship capable of carrying passengers and providing entertainment services.
 * Extends the Ship class and implements the PassengerServices interface.
 * Tracks passenger capacity, current passenger count, cabin count, and entertainment availability.
 *
 * @author Fabian Figueroa
 */
public class PassengerShip extends Ship implements PassengerServices{

    private int passengerCapacity;
    private int passengerCnt;
    private int cabinCnt;
    private boolean hasEntertainment;

    /**
     * Constructs a PassengerShip with specified attributes.
     *
     * @param name               the name of the ship
     * @param registrationNumber the registration number of the ship
     * @param tonnage            the weight of the ship
     * @param crewSize           the number of crew members aboard
     * @param currPort           the current location of the ship
     * @param currSpeed          the current speed of the ship
     * @param passengerCapacity  the maximum number of passengers the ship can carry
     * @param cabinCnt           the number of cabins available
     * @param hasEntertainment   true if entertainment services are available, false otherwise
     */
    public PassengerShip(String name, String registrationNumber, double tonnage, int crewSize, String currPort,
                         double currSpeed, int passengerCapacity, int cabinCnt, boolean hasEntertainment) {
        super(name, registrationNumber, tonnage, crewSize, currSpeed, currPort);
        this.passengerCapacity = passengerCapacity;
        this.cabinCnt = cabinCnt;
        this.hasEntertainment = hasEntertainment;
    }

    /**
     * Returns the maximum passenger capacity of the ship.
     * @return the passenger capacity
     */
    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    /**
     * Sets the maximum passenger capacity of the ship.
     * @param passengerCapacity the new passenger capacity
     */
    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    /**
     * Returns the current number of passengers on board.
     * @return the passenger count
     */
    public int getPassengerCnt() {
        return passengerCnt;
    }

    /**
     * Sets the current number of passengers on board.
     * @param passengerCnt the new passenger count
     */
    public void setPassengerCnt(int passengerCnt) {
        this.passengerCnt = passengerCnt;
    }

    /**
     * Returns the number of cabins available on the ship.
     * @return the cabin count
     */
    public int getCabinCnt() {
        return cabinCnt;
    }

    /**
     * Sets the number of cabins available on the ship.
     * @param cabinCnt the new cabin count
     */
    public void setCabinCnt(int cabinCnt) {
        this.cabinCnt = cabinCnt;
    }

    /**
     * Returns whether the ship has entertainment services.
     * @return true if entertainment is available, false otherwise
     */
    public boolean isHasEntertainment() {
        return hasEntertainment;
    }

    /**
     * Sets the entertainment availability status.
     * @param hasEntertainment true if entertainment is available, false otherwise
     */
    public void setHasEntertainment(boolean hasEntertainment) {
        this.hasEntertainment = hasEntertainment;
    }

    /**
     * Docks the ship at the specified port and disembarks all passengers.
     * @param port the port to dock at
     */
    @Override
    public void dock(String port) {
        super.dock(port);
        disembarkPassengers();
    }

    /**
     * Disembarks all passengers and resets passenger count and speed.
     */
    public void disembarkPassengers() {
        System.out.println(passengerCnt + " passengers are disembarking.");
        passengerCnt = 0;
        setCurrentSpeed(0.0);
    }

    /**
     * Starts entertainment services if available.
     */
    @Override
    public void startEntertainment() {
        if (hasEntertainment) {
            System.out.println(getName() + " is starting entertainment services.");
        }  else {
            System.out.println(getName() + " has no entertainment services.");
        }
    }

    /**
     * Returns a formatted passenger manifest.
     * @return a string containing passenger-related details
     */
    @Override
    public String getPassengerManifest() {
        return "Passenger Ship manifest: \n" +
                "Name: " + getName() + "\n" +
                "Passenger Capacity: " + passengerCapacity + "\n" +
                "Cabins: " + cabinCnt + "\n";
    }

    /**
     * Returns a string representation of the ship's attributes.
     * @return a formatted string describing the passenger ship
     */
    @Override
    public String toString() {
        return "Passenger Ship [name=" + getName() +
                ", registrationNumber=" + getRegistrationNumber() +
                ", tonnage=" + getTonnage() +
                ", crewSize=" + getCrewSize() +
                ", currentSpeed=" + getCurrentSpeed() +
                ", currentPort=" + getCurrentPort() +
                ", passengerCapacity: " + passengerCapacity +
                ", passengerCount: " + passengerCnt +
                ", numberOfCabins: " + cabinCnt +
                ", hasEntertainmentFacilities: " + hasEntertainment + "]";
    }

}
