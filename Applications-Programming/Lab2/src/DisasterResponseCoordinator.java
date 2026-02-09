import java.util.ArrayList;

/**
 * Coordinates the deployment and readiness reporting of emergency support units.
 * Manages a list of responders implementing the EmergencySupport interface.
 * @author Fabian Figueroa
 */
public class DisasterResponseCoordinator {
    private ArrayList<EmergencySupport> responders;

    /**
     * Constructs a new DisasterResponseCoordinator with an empty list of responders.
     */
    public DisasterResponseCoordinator() {
        responders = new  ArrayList<>();
    }

    /**
     * Registers a new emergency responder.
     * @param responder an object implementing EmergencySupport
     */
    public void registerResponder(EmergencySupport responder){
        responders.add(responder);
    }

    /**
     * Deploys all registered emergency responders.
     * Prints a message for each responder as they deploy aid.
     */
    public void deployAll() {
        System.out.print("Deploying all emergency units:\n");
        for (EmergencySupport responder : responders) {
            responder.deployAid();
        }
    }

    /**
     * Prints emergency readiness reports for all registered responders.
     */
    public void printAllReadinessReports() {
        System.out.println("Emergency Readiness Reports:");
        for (EmergencySupport responder : responders) {
            System.out.println(responder.getEmergencyReadinessReport());
        }
    }

    /**
     * Returns a string representation of all registered responders.
     * @return a formatted string listing all responders
     */
    @Override
        public String toString() {
            StringBuilder result = new StringBuilder("DisasterResponseCoordinator [responders=\n");
            for (EmergencySupport responder : responders) {
                result.append(responder.toString()).append("\n");
            }
            //result.append("]");
            return result.toString();
        }

}
