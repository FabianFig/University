/**
 * Interface for emergency support units capable of deploying aid
 * and reporting their readiness status.
 *
 * Implementing classes must define how aid is deployed and how
 * readiness is reported.
 */
public interface EmergencySupport {

    /**
     * Deploys aid if available.
     */
    void deployAid();

    /**
     * Returns a report detailing emergency readiness.
     * @return a formatted emergency readiness report
     */
    String getEmergencyReadinessReport();


}
