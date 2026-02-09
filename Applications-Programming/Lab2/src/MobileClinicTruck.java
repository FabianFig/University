/**
 * Represents a mobile clinic truck capable of providing emergency medical aid.
 * Implements the EmergencySupport interface.
 * Tracks truck ID, region, number of doctors, current speed, and medicine availability.
 *
 * @author Fabian Figueroa
 */
public class MobileClinicTruck implements EmergencySupport{
    private String truckID;
    private String region;
    private int doctorCnt;
    private double currSpeed;
    private boolean hasMeds;

    /**
     * Constructs a MobileClinicTruck with specified attributes.
     *
     * @param truckID   the unique identifier of the truck
     * @param region    the region the truck is assigned to
     * @param doctorCnt the number of doctors on board
     * @param hasMeds   true if the truck is stocked with medicine, false otherwise
     */
    public MobileClinicTruck(String truckID, String region, int doctorCnt, boolean hasMeds) {
        this.truckID = truckID;
        this.region = region;
        this.doctorCnt = doctorCnt;
        this.hasMeds = hasMeds;
        this.currSpeed = 0.0;
    }

    /**
     * Returns the truck's unique identifier.
     * @return the truck ID
     */
    public String getTruckID() {
        return truckID;
    }

    /**
     * Sets the truck's unique identifier.
     * @param truckID the new truck ID
     */
    public void setTruckID(String truckID) {
        this.truckID = truckID;
    }

    /**
     * Returns the region the truck is assigned to.
     * @return the region name
     */
    public String getRegion() {
        return region;
    }

    /**
     * Sets the region the truck is assigned to.
     * @param region the new region name
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Returns the number of doctors on board.
     * @return the doctor count
     */
    public int getDoctorCnt() {
        return doctorCnt;
    }

    /**
     * Sets the number of doctors on board.
     * @param doctorCnt the new doctor count
     */
    public void setDoctorCnt(int doctorCnt) {
        this.doctorCnt = doctorCnt;
    }

    /**
     * Returns the current speed of the truck.
     * @return the current speed
     */
    public double getCurrSpeed() {
        return currSpeed;
    }

    /**
     * Sets the current speed of the truck.
     * @param currSpeed the new speed
     */
    public void setCurrSpeed(double currSpeed) {
        this.currSpeed = currSpeed;
    }

    /**
     * Returns whether the truck is stocked with medicine.
     * @return true if stocked with medicine, false otherwise
     */
    public boolean isHasMeds() {
        return hasMeds;
    }

    /**
     * Sets the medicine availability status.
     * @param hasMeds true if stocked with medicine, false otherwise
     */
    public void setHasMeds(boolean hasMeds) {
        this.hasMeds = hasMeds;
    }

    /**
     * Returns a string representation of the truck's attributes.
     * @return a formatted string describing the truck
     */
    @Override
    public String toString() {
        return "MobileClinicTruck [truckId=" + truckID +
                ", region=" + region +
                ", doctorCount=" + doctorCnt +
                ", currentSpeed=" + currSpeed +
                ", stockedWithMedicine=" + (hasMeds ? "true" : "false") + "]";
    }

    /**
     * Deploys aid by printing a message indicating the truck ID and number of doctors deployed.
     */
    @Override
    public void deployAid() {
        System.out.println("Mobile clinic truck " + getTruckID() + " deploying " + getDoctorCnt() + " doctors.");
    }

    /**
     * Returns a report detailing the truck's emergency readiness.
     * @return a formatted emergency readiness report
     */
    @Override
    public String getEmergencyReadinessReport() {
        return "Truck Emergency Report:\n" +
                "ID: " + getTruckID() + "\n" +
                "Region: " + getRegion() + "\n" +
                "Doctors on Board: " + getDoctorCnt() + "\n" +
                "Medicine: " + (hasMeds ? "Stocked with Medicine" : "No Medicine Available") + "\n" +
                "=======================================";
    }
}
