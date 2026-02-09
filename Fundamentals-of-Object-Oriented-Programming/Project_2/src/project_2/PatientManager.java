package project_2;

import java.io.*;
import java.util.ArrayList;

public class PatientManager {

    private User currUser;
    private ArrayList<Patient> patients;
    private Patient currViewPatient;

    public PatientManager(User currUser, String patientCSV) throws IOException {
        this.currUser = currUser;
        this.patients = new ArrayList<>();
        this.currViewPatient = null;
        loadData(patientCSV);
        sortPatientsByID();

        if (currUser instanceof Patient) {
            this.currViewPatient = (Patient) currUser;
        }
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    private void loadData(String patientCSV) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(patientCSV));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            int id = Integer.parseInt(data[0]);
            patients.add(new Patient(id, data[1], data[2], data[3], data[4], data[5]));
        }
        reader.close();
    }

    private Patient binarySearchById(String id) {
    	int targetId = Integer.parseInt(id);
        int low = 0, high = patients.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            Patient midPatient = patients.get(mid);
            if (midPatient.getId() == targetId) {
                return midPatient;
            } else if (midPatient.getId() < targetId) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    public void sortPatientsByID() {
        for (int i = 0; i < patients.size() - 1; i++) {
            for (int j = 0; j < patients.size() - i - 1; j++) {
            	if (patients.get(j).getId() > patients.get(j + 1).getId()) {
                    Patient temp = patients.get(j);
                    patients.set(j, patients.get(j + 1));
                    patients.set(j + 1, temp);
                }
            }
        }
    }

    public void savePatientsToFile(String filePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (Patient patient : patients) {
            writer.write(patient.id + "," + patient.username + "," + patient.password + ","
                    + patient.name + "," + patient.email + "," + patient.getTreatmentNotes());
            writer.newLine();
        }
        writer.close();
    }

    public void viewProfile() {
        System.out.println(currUser.toString());
    }

    public void viewPatient(String id) {
        Patient result = binarySearchById(id);
        if (result != null) {
            currViewPatient = result;
            System.out.println(result);
        } else {
            System.out.println("Patient not found.");
        }
    }

    public void editCurrentPatient(String field, String newValue) {
        if (currViewPatient == null) {
            System.out.println("No patient selected.");
            return;
        }

        switch (field) {
            case "name":
                currViewPatient.setName(newValue);
                break;
            case "email":
                currViewPatient.setEmail(newValue);
                break;
            case "treatment_notes":
                currViewPatient.setTreatmentNotes(newValue);
                break;
            case "password":
                currViewPatient.setPassword(newValue);
                break;
            default:
                System.out.println("Invalid field.");
        }
    }


}
