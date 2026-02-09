/**
 * Entry point for Lab 1: Emergency Response Simulation.
 *
 * Demonstrates the creation and coordination of various emergency units,
 * including an AidShip, RescueHelicopter, MobileClinicTruck, and PassengerShip.
 *
 * Units are registered with a DisasterResponseCoordinator, which handles
 * deployment and readiness reporting.
 *
 * Also showcases PassengerShip functionality such as navigation, entertainment,
 * docking, and manifest reporting.
 *
 * @author Fabian Figueroa
 */
public class Lab1 {

    /**
     * Main method to run the emergency response simulation.
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        // Create units
        AidShip ship = new AidShip("Madleen", "AID-001", 8000, 120, "Melona", "Medical", 200, true);
        RescueHelicopter helicopter = new RescueHelicopter("ECHO-7", "Melona", 50, true);
        MobileClinicTruck truck = new MobileClinicTruck("TRK-12", "Melona", 5, true);

        // Coordinate response
        DisasterResponseCoordinator coordinator = new DisasterResponseCoordinator();
        coordinator.registerResponder(ship);
        coordinator.registerResponder(helicopter);
        coordinator.registerResponder(truck);

        // Print readiness reports
        coordinator.printAllReadinessReports();
        System.out.println();

        // Simulate deployment
        coordinator.deployAll();
        System.out.println();

        // Display the String representation of the coordinator
        System.out.println(coordinator);

        PassengerShip passengerShip = new PassengerShip("PAL-123", "PAL-123", 10000, 100, "Melona", 100, 70,10, true);
        System.out.println(passengerShip);
        passengerShip.navigateTo("Leve");
        passengerShip.startEntertainment();
        passengerShip.dock("Leve");
        System.out.println(passengerShip.getPassengerManifest());
        System.out.println(passengerShip);

    }
}
