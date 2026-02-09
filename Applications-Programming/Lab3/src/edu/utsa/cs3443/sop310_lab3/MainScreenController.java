package edu.utsa.cs3443.sop310_lab3;

import edu.utsa.cs3443.sop310_lab3.model.AidShip;
import edu.utsa.cs3443.sop310_lab3.model.AidShipManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;

/**
 * Controller class for the main JavaFX screen of the Aid Ship Management Application.
 * <p>
 * This class handles user interactions with the GUI, including listing all ships,
 * finding a specific aid ship by registration number, and deleting ships.
 * It serves as the connection between the FXML user interface and the
 * {@link AidShipManager} model class.
 *
 * <p>Features implemented:</p>
 * <ul>
 *     <li>Load aid ship data from a CSV file</li>
 *     <li>List all ships currently loaded</li>
 *     <li>Find a ship by registration number</li>
 *     <li>Delete a ship from the dataset and update the file</li>
 * </ul>
 *
 * @author Fabian Figueroa
 * @version 1.0
 */
public class MainScreenController {

    /** The manager responsible for loading, finding, and deleting aid ships. */
    private AidShipManager manager;

    /** Radio button to select Delete mode. */
    @FXML
    private RadioButton deleteRadio;

    /** Radio button to select Find mode. */
    @FXML
    private RadioButton findRadio;

    /** Button used to list all ships. */
    @FXML
    private Button listShipsButton;

    /** Text area used to display ship information or system messages. */
    @FXML
    private TextArea outputArea;

    /** Text field for entering a ship's registration number. */
    @FXML
    private TextField registrationTextField;

    /**
     * Initializes the controller and loads the CSV data.
     * <p>
     * This method runs automatically when the FXML file is loaded.
     * It prepares the {@link AidShipManager} instance and loads
     * existing ship data from the CSV file.
     */
    @FXML
    public void initialize() {
        manager = new AidShipManager("data/aid_ships.csv");
        try {
            manager.loadAidShips();
            outputArea.setText("Data loaded successfully.\nClick 'List Ships' to view all ships.");
        } catch (IOException e) {
            outputArea.setText("Error loading CSV: " + e.getMessage());
        }

        outputArea.setStyle("-fx-font-family: 'Consolas'; -fx-font-size: 13px;");
    }

    /**
     * Handles the Find operation.
     * <p>
     * Searches for a specific aid ship based on its registration number.
     * If found, the ship’s details are formatted and displayed in the text area.
     *
     * @param event The action event triggered by the Find button or Go button when Find is selected.
     */
    @FXML
    void onFind(ActionEvent event) {
        String regNum = registrationTextField.getText().trim();

        if (regNum.isEmpty()) {
            outputArea.setText("Enter a valid aid ship registration number.");
            return;
        }

        try {
            manager.loadAidShips();
            AidShip ship = manager.findAidShip(regNum);

            if (ship == null) {
                outputArea.setText("No aid ship found with registration number: " + regNum);
            } else {
                String formatted = String.format(
                        "Aid Ship Card:\n" +
                                "----------------------------------------\n" +
                                "Name: %-25s\n" +
                                "Registration Number: %-15s\n" +
                                "Tonnage: %-10.2f\n" +
                                "Crew Size: %-10d\n" +
                                "Current Port: %-20s\n" +
                                "Aid Type: %-25s\n" +
                                "Supplies On Board: %-10d\n" +
                                "Helipad: %-10s\n" +
                                "----------------------------------------",
                        ship.getName(),
                        ship.getRegistrationNumber(),
                        ship.getTonnage(),
                        ship.getCrewSize(),
                        ship.getPort(),
                        ship.getAidType(),
                        ship.getSuppliesOnBoard(),
                        ship.isHasHelipad() ? "Available" : "Not Available"
                );

                outputArea.setText(formatted);
            }

        } catch (Exception e) {
            e.printStackTrace();
            outputArea.setText("Error loading ship data.");
        }
    }

    /**
     * Handles the Delete operation.
     * <p>
     * Attempts to locate a ship with the given registration number and remove it
     * from the internal list and the CSV file. Displays success or failure messages.
     *
     * @param regNum The registration number of the ship to delete.
     */
    @FXML
    void onDeleteSelected(String regNum) {
        try {
            manager.loadAidShips();
            AidShip ship = manager.findAidShip(regNum);

            if (ship == null) {
                outputArea.setText("No aid ship found with registration number: " + regNum);
                return;
            }

            boolean removed = manager.deleteAidShip(ship);

            if (removed) {
                outputArea.setText(String.format(
                        "Aid ship '%s' (%s) has been successfully deleted.\n\nUpdated Ship List:\n%s",
                        ship.getName(),
                        ship.getRegistrationNumber(),
                        manager.toString()
                ));
            } else {
                outputArea.setText("Failed to delete ship: " + regNum);
            }

        } catch (IOException e) {
            outputArea.setText("Error deleting ship: " + e.getMessage());
        }
    }

    /**
     * Displays all currently loaded aid ships in the output text area.
     * <p>
     * Reloads the data from the CSV file each time to ensure that the
     * displayed information is up to date.
     */
    @FXML
    void onListShips() {
        try {
            manager.loadAidShips();
            outputArea.setText(manager.toString());
        } catch (IOException e) {
            outputArea.setText("Error loading CSV: " + e.getMessage());
        }
    }

    /**
     * Determines which operation to execute when the "Go" button is pressed.
     * <p>
     * If the Find radio button is selected, it executes the Find operation.
     * If the Delete radio button is selected, it executes the Delete operation.
     * If neither is selected, a message prompts the user to make a selection.
     *
     * @param event The action event triggered by clicking the Go button.
     */
    @FXML
    void onGoClicked(ActionEvent event) {
        String regNum = registrationTextField.getText().trim();

        if (regNum.isEmpty()) {
            outputArea.setText("Please enter a registration number.");
            return;
        }

        if (findRadio.isSelected()) {
            onFind(event);
        } else if (deleteRadio.isSelected()) {
            onDeleteSelected(regNum);
        } else {
            outputArea.setText("Please select 'Find' or 'Delete' before clicking Go.");
        }
    }
}
