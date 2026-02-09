package project_2;

import java.io.*;
import java.util.*;

public class Login {

    private List<Patient> patients;
    private List<MedicalStaff> medicalStaff;

    public Login(String patientFile, String staffFile) throws IOException {
        this.patients = loadPatients(patientFile);
        this.medicalStaff = loadMedicalStaff(staffFile);
    }

    public User authenticate() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter username:");
            String username = scanner.nextLine();
            System.out.println("Enter password:");
            String password = scanner.nextLine();

            for (Patient patient : patients) {
                if (patient.getUsername().equals(username) && patient.getPassword().equals(password)) {
                    System.out.println("Login successful as Patient.");
                    return patient;
                }
            }

            
            for (MedicalStaff staff : medicalStaff) {
                if (staff.getUsername().equals(username) && staff.getPassword().equals(password)) {
                    System.out.println("Login successful as Medical Staff.");
                    return staff;
                }
            }

            System.out.println("Invalid username or password. Try again.");
        }
    }

    private List<Patient> loadPatients(String filePath) throws IOException {
        List<Patient> patients = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            int id = Integer.parseInt(data[0]);
            patients.add(new Patient(id, data[1], data[2], data[3], data[4], data[5]));
        }
        reader.close();
        return patients;
    }

    private List<MedicalStaff> loadMedicalStaff(String filePath) throws IOException {
        List<MedicalStaff> staff = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            int id = Integer.parseInt(data[0]);
            staff.add(new MedicalStaff(id, data[1], data[2], data[3], data[4], data[5]));
        }
        reader.close();
        return staff;
    }
    
}
