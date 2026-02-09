import java.io.IOException;
import java.util.ArrayList;
import util.InputValidator;

public class Lab3 {

    private static AidShipManager coordinator;

    public static void main(String[] args) throws IOException {

        coordinator = new AidShipManager("data/aid_ships.csv");
        coordinator.loadAidShips();

        showAidShipList();
        findAidShip("LR12345");
        updateAidShip();
        showAidShipList();
        deleteAidShip();
        showAidShipList();
    }

    private static void findAidShip(String registrationNumber) {
        if(coordinator.findAidShip(registrationNumber) != null) {
            System.out.println("Aid Ship " + registrationNumber + " found!");
        }
        else {
            System.out.println("Aid Ship " + registrationNumber + " not found!");
        }
    }

    private static void showAidShipList(){

        ArrayList<AidShip> aidShipList = coordinator.getAidShipList();

        System.out.println("AidShipCoordinator List has " + aidShipList.size() + " responders");
        System.out.printf("\n%s\n", "____________________________________________________________________________________________________________________________________________________________________________________");
        System.out.printf("|%-15s", "Name");
        System.out.printf("|%-20s", "Registration Number");
        System.out.printf("|%-15s", "Tonnage");
        System.out.printf("|%-15s", "Crew Size");
        System.out.printf("|%-30s", "Port");
        System.out.printf("|%-30s", "Aid Type");
        System.out.printf("|%-20s", "Supplies On Board");
        System.out.printf("|%-12s", "Helipad");
        System.out.printf("\n%s\n", "____________________________________________________________________________________________________________________________________________________________________________________");

        if (aidShipList.isEmpty())
            System.out.println("\t\t\t\tNo responders available in the system!");
        else
            for (AidShip ship : aidShipList) {
                printRecordLine(ship);
                System.out.println();
            }

        System.out.printf("\n%s\n", "____________________________________________________________________________________________________________________________________________________________________________________");
    }

    private static void printRecordLine(AidShip aidShip) {
        System.out.printf("|%-15s",  aidShip.getName());
        System.out.printf("|%-20s",  aidShip.getRegistrationNumber());
        System.out.printf("|%-15.2f",  aidShip.getTonnage());
        System.out.printf("|%-15d",  aidShip.getCrewSize());
        System.out.printf("|%-30s",  aidShip.getCurrentPort());
        System.out.printf("|%-30s",  aidShip.getAidType());
        System.out.printf("|%-20d",  aidShip.getSuppliesOnBoard());
        System.out.printf("|%-12s",  aidShip.isHasHelipad() ? "Available" : "Not Available" );
    }


    private static void deleteAidShip() throws IOException {
        System.out.println("\n\nDelete Aid Ship\n");
        String registrationNumber = "";

        System.out.println("\nEnter Registration Number: ");
        registrationNumber = InputValidator.readString();
        while (!coordinator.isAidShipExists(registrationNumber)) {
            System.out.println("\nRegistration Number does not exist. Enter a different number: ");
            registrationNumber = InputValidator.readString();
        }

        AidShip aidShip = coordinator.findAidShip(registrationNumber);
        printAidShip(aidShip);

        System.out.println("\nAre you sure you want to delete this ship y/n? ");

        char answer = 'n';
        answer = InputValidator.readChar("Invalid input. Enter Y or y to confirm: ");

        if (answer == 'y' || answer == 'Y') {

            if(coordinator.deleteAidShip(aidShip)) {
                System.out.println("\nAidShip successfully deleted!");
                printAidShip(aidShip);
            } else  {
                System.out.println("\nAidShip could not be deleted!");
            }
        }
    }

    private static void readAidShipInfo(AidShip aidShip){
        System.out.println( "Enter Name: ");
        aidShip.setName(InputValidator.readString());

        System.out.println( "Enter Tonnage: ");
        aidShip.setTonnage(InputValidator.readDoubleNumber("Invalid tonnage. Enter Tonnage: "));

        System.out.println( "Enter Crew Size: ");
        aidShip.setCrewSize(InputValidator.readIntNumber("Invalid crew size. Enter Crew Size: "));

        System.out.println( "Enter Port: ");
        aidShip.setCurrentPort(InputValidator.readString());

        System.out.println( "Enter Aid Type: ");
        aidShip.setAidType(InputValidator.readString());

        System.out.println( "Enter Supplies On Board: ");
        aidShip.setSuppliesOnBoard(InputValidator.readIntNumber("Invalid supplies on board. Enter Supplies On Board:"));

        System.out.println( "Has Helipad?: ");
        aidShip.setHasHelipad(InputValidator.readBooleanValue());

    }

    private static void updateAidShip() throws IOException {
        System.out.println("\n\nUpdate Aid Ship\n");
        String registrationNumber = "";

        System.out.println("Enter Aid Ship Registration Number: ");
        registrationNumber = InputValidator.readString();

        while (!coordinator.isAidShipExists(registrationNumber)) {
            System.out.println("Registration number is not found, enter registration number: ");
            registrationNumber = InputValidator.readString();
        }

        AidShip aidShip = coordinator.findAidShip(registrationNumber);

        printAidShip(aidShip);

        System.out.println("Are you sure you want to update this aid ship y/n? ");

        char answer = 'n';
        answer = InputValidator.readChar("Invalid input. Enter Y or y to confirm: ");

        if (answer == 'y' || answer == 'Y') {

            System.out.println("\n\nUpdate Aid Ship Info:");
            System.out.println("\n____________________\n");

            readAidShipInfo(aidShip);
            if(coordinator.updateAidShip(aidShip)){
                System.out.println("\nAidShip successfully updated!");
                printAidShip(aidShip);
            } else  {
                System.out.println("\nAidShip could not be updated!");
            }
        }
    }

    private static void printAidShip(AidShip aidShip) {
        System.out.println( "Aid Ship Card:");
        System.out.println( "______________________________________");
        System.out.printf( "%-20s%-20s\n", "Name: ", aidShip.getName());
        System.out.printf( "%-20s%-20s\n", "Registration Number: ",  aidShip.getRegistrationNumber());
        System.out.printf( "%-20s%-10.2f\n", "Tonnage: ", aidShip.getTonnage());
        System.out.printf( "%-20s%-20d\n", "Crew Size: ", aidShip.getCrewSize());
        System.out.printf( "%-20s%-20s\n", "Current Port: ", aidShip.getCurrentPort());
        System.out.printf( "%-20s%-20s\n", "Aid Type: ", aidShip.getAidType());
        System.out.printf( "%-20s%-20d\n", "Supplies On Board: ", aidShip.getSuppliesOnBoard());
        System.out.printf( "%-20s%-20s\n", "Helipad: ", aidShip.isHasHelipad() ? "Available" : "Not Available" );
        System.out.println( "______________________________________");
    }
}
