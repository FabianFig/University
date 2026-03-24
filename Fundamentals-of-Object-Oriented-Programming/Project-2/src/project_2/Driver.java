package project_2;

import java.io.IOException;
import java.io.File;
import java.util.*;

public class Driver {

    public static void main(String[] args) {
    	
        File file = new File("patient.csv");

        //System.out.println("File being search for is: " + file.getAbsolutePath());
        //System.out.println("does it exist? " + file.exists());
    	
        try {
            Login login = new Login("patient.csv", "medicalstaff.csv");
            User user = login.authenticate();
            
            PatientManager manager = new PatientManager(user, "patient.csv");
            int choice = 0;

            Scanner scnr = new Scanner(System.in);
            while (true) {
                System.out.println("1. View Profile");
                System.out.println("2. View Patient");
                System.out.println("3. Edit Current Patient");
                System.out.println("4. Exit");
                System.out.println("5. Generate Report");
                System.out.print("Enter your choice: ");
                try {
                    choice = scnr.nextInt();
                    scnr.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scnr.nextLine(); 
                    continue;
                }

                switch (choice) {
                    case 1:
                        manager.viewProfile();
                        break;
                    case 2:
                        if (user instanceof MedicalStaff) {
                            System.out.println("Enter Patient ID: ");
                            String id = scnr.nextLine();
                            manager.viewPatient(id);
                        } else {
                            System.out.println("Access denied. Only medical staff can view patient details.\n");
                        }
                        break;
                    case 3:
                        if (user instanceof MedicalStaff) {
                            System.out.print("Enter what you'd like to edit (name/email/treatment_notes): ");
                            String editChoice = scnr.nextLine().toLowerCase();
                            if (!editChoice.equals("name") && !editChoice.equals("email") && !editChoice.equals("treatment_notes")) {
                                System.out.println("Invalid field. Please choose name, email, or treatment_notes.");
                                continue;
                            }
                            System.out.print("Enter new value: ");
                            String newValue = scnr.nextLine();
                            manager.editCurrentPatient(editChoice, newValue);
                            manager.savePatientsToFile("patient.csv");
                        } else {
                            System.out.println("Access denied. Only medical staff can edit patient details\n");
                        }
                        break;
                    case 4:
                        System.out.println("Exiting...\n");
                        return;
                    case 5:
                        System.out.print("Enter report type (id/name/email): ");
                        String reportType = scnr.nextLine().toLowerCase();
                        if (!reportType.equals("id") && !reportType.equals("name") && !reportType.equals("email")) {
                            System.out.println("Invalid report type. Please choose id, name, or email.");
                            break;
                        }
                        System.out.print("Enter file path to save the report: ");
                        String filePath = scnr.nextLine();
                        try {
                            Reporting.generateReport(manager.getPatients(), filePath, reportType);
                        } catch (IOException e) {
                            System.out.println("Error generating report: " + e.getMessage());
                        }
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
