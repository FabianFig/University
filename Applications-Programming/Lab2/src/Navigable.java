/**
 * Interface for navigable entities capable of movement and speed control.
 *
 * Classes implementing this interface must define how to navigate to a location,
 * adjust speed, and report current speed.
 *
 * Useful for vehicles, ships, drones, or any mobile emergency units.
 *
 * @author Fabian Figueroa
 */
public interface Navigable {

    /**
     * Navigates the entity to the specified location.
     * @param location the destination to navigate to
     */
    void navigateTo(String location);

    /**
     * Sets the current speed of the entity.
     * @param speed the new speed value
     */
    void setCurrentSpeed(double speed);

    /**
     * Returns the current speed of the entity.
     * @return the current speed
     */
    double getCurrentSpeed();

}
