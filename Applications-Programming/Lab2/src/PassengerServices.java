/**
 * Interface for entities that provide passenger-related services.
 *
 * Classes implementing this interface must define how entertainment is started
 * and how passenger information is reported.
 *
 * Useful for cruise ships, transport shuttles, or any unit carrying passengers.
 *
 * @author Fabian Figueroa
 */
public interface PassengerServices {

    /**
     * Starts the onboard entertainment service if available.
     */
    void startEntertainment();

    /**
     * Returns a detailed manifest of all passengers on board.
     * @return a formatted passenger manifest
     */
    String getPassengerManifest();
}
