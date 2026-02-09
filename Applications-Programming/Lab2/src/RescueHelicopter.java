/**
 * Represents a rescue helicopter capable of deploying medical aid.
 * Implements the EmergencySupport interface.
 * Tracks call sign, location, speed, altitude, medical kits, and hoist availability.
 *
 * @author Fabian Figueroa
 */
public class RescueHelicopter implements EmergencySupport{
    private String callSign;
    private String location;
    private double speed;
    private int numMedKits;
    private double altitude;
    private double currSpeed;
    private boolean hasHoist;

    /**
     * Constructs a RescueHelicopter with specified attributes.
     *
     * @param callSign   the unique identifier of the helicopter
     * @param location   the base location of the helicopter
     * @param numMedKits the number of medical kits on board
     * @param hasHoist   true if the helicopter has a rescue hoist, false otherwise
     */
    public RescueHelicopter(String callSign, String location, int numMedKits, boolean hasHoist) {
        this.callSign = callSign;
        this.location = location;
        this.speed = 0.0;
        this.numMedKits = numMedKits;
        this.altitude = 0.0;
        this.currSpeed = 0.0;
        this.hasHoist = hasHoist;
    }

    /**
     * Returns the helicopter's call sign.
     * @return the call sign
     */
    public String getCallSign() {
        return callSign;
    }

    /**
     * Sets the helicopter's call sign.
     * @param callSign the new call sign
     */
    public void setCallSign(String callSign) {
        this.callSign = callSign;
    }

    /**
     * Returns the base location of the helicopter.
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the base location of the helicopter.
     * @param location the new location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Returns the configured speed of the helicopter.
     * @return the speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Sets the configured speed of the helicopter.
     * @param speed the new speed
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * Returns the number of medical kits on board.
     * @return the number of medical kits
     */
    public int getNumMedKits() {
        return numMedKits;
    }

    /**
     * Sets the number of medical kits on board.
     * @param numMedKits the new number of medical kits
     */
    public void setNumMedKits(int numMedKits) {
        this.numMedKits = numMedKits;
    }

    /**
     * Returns the current altitude of the helicopter.
     * @return the altitude
     */
    public double getAltitude() {
        return altitude;
    }

    /**
     * Sets the current altitude of the helicopter.
     * @param altitude the new altitude
     */
    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    /**
     * Returns the current speed of the helicopter.
     * @return the current speed
     */
    public double getCurrSpeed() {
        return currSpeed;
    }

    /**
     * Sets the current speed of the helicopter.
     * @param currSpeed the new current speed
     */
    public void setCurrSpeed(double currSpeed) {
        this.currSpeed = currSpeed;
    }

    /**
     * Returns whether the helicopter has a rescue hoist.
     * @return true if it has a hoist, false otherwise
     */
    public boolean isHasHoist() {
        return hasHoist;
    }

    /**
     * Sets the rescue hoist availability.
     * @param hasHoist true if the helicopter has a hoist, false otherwise
     */
    public void setHasHoist(boolean hasHoist) {
        this.hasHoist = hasHoist;
    }

    /**
     * Deploys medical aid and prints a message indicating the helicopter's call sign and number of kits.
     */
    @Override
    public void deployAid() {
        System.out.println("Rescue helicopter " + getCallSign() + " deploying " + getNumMedKits() + " of Medical Aid.");
    }

    /**
     * Returns a string representation of the helicopter's attributes.
     * @return a formatted string describing the rescue helicopter
     */
    @Override
    public String toString() {
        return "RescueHelicopter [CallSign='" + getCallSign() +
                "' , baseLocation='" + getLocation() +
                "' , currentSpeed=" + getCurrSpeed() +
                ", altitude=" + getCurrSpeed() +
                ", medicalKits=" + getNumMedKits() +
                ", hasRescueHoist=" + (hasHoist ? "true" : "false") + "]";
    }

    /**
     * Returns a report detailing the helicopter's emergency readiness.
     * @return a formatted emergency readiness report
     */
    @Override
    public String getEmergencyReadinessReport() {
        return "Helicopter Emergency Report:\n" +
                "Call Sign: " + getCallSign() + "\n" +
                "Base Location: " + getLocation() + "\n" +
                "Medical Kits: " + getNumMedKits() + "\n" +
                "Rescue Hoist: " + (hasHoist ? "Available" : "Not Available") + "\n" +
                "=======================================";
    }
}
