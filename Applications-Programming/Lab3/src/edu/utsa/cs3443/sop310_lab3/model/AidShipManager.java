package edu.utsa.cs3443.sop310_lab3.model;

import java.io.*;
import java.util.ArrayList;

/**
 * Manages a collection of {@link AidShip} objects, allowing for loading, updating,
 * deleting, and saving ship data from a CSV file.
 *
 * Supports both console and GUI interaction.
 *
 * @author Fabian F.
 */
public class AidShipManager {

    private ArrayList<AidShip> aidShips = new ArrayList<>();
    private String filePath;

    /**
     * Default constructor for GUI use.
     * Initializes file path to "data/aid_ships.csv".
     */
    public AidShipManager() {
        this.filePath = "data/aid_ships.csv";
    }

    /**
     * Constructs an {@link AidShipManager} with a custom file path.
     *
     * @param filePath the CSV file containing aid ship data
     */
    public AidShipManager(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads aid ship data from the CSV file.
     *
     * @throws IOException if the file cannot be read
     */
    public void loadAidShips() throws IOException {
        aidShips.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // skip header
                }
                if (!line.trim().isEmpty()) {
                    AidShip ship = convertLineToAidShip(line);
                    aidShips.add(ship);
                }
            }
        }
    }

    /**
     * Loads aid ship data from a specified CSV path.
     *
     * @param path the path to the CSV file
     * @throws IOException if the file cannot be read
     */
    public void loadFromCsv(String path) throws IOException {
        this.filePath = path;
        loadAidShips();
    }

    /**
     * Adds a new {@link AidShip} to the list.
     *
     * @param aidShip the aid ship to add
     */
    public void addAidShip(AidShip aidShip) {
        aidShips.add(aidShip);
    }

    /**
     * Finds an aid ship by its registration number.
     *
     * @param registrationNum the registration number to search for
     * @return the matching {@link AidShip}, or null if not found
     */
    public AidShip findAidShip(String registrationNum) {
        for (AidShip ship : aidShips) {
            if (ship.getRegistrationNumber().equalsIgnoreCase(registrationNum)) {
                return ship;
            }
        }
        return null;
    }

    /**
     * Checks if a ship exists by registration number.
     *
     * @param registrationNum the registration number to check
     * @return true if the ship exists, false otherwise
     */
    public boolean isAidShipExists(String registrationNum) {
        return findAidShip(registrationNum) != null;
    }

    /**
     * Updates the information of an existing aid ship.
     *
     * @param updatedShip the ship with updated attributes
     * @return true if successfully updated, false otherwise
     * @throws IOException if saving the file fails
     */
    public boolean updateAidShip(AidShip updatedShip) throws IOException {
        for (int i = 0; i < aidShips.size(); i++) {
            AidShip ship = aidShips.get(i);
            if (ship.getRegistrationNumber().equalsIgnoreCase(updatedShip.getRegistrationNumber())) {
                aidShips.set(i, updatedShip);
                saveDataToFile();
                return true;
            }
        }
        return false;
    }

    /**
     * Deletes a specified aid ship.
     *
     * @param shipToDelete the ship to remove
     * @return true if the ship was successfully deleted
     * @throws IOException if the updated data cannot be saved
     */
    public boolean deleteAidShip(AidShip shipToDelete) throws IOException {
        boolean removed = aidShips.removeIf(
                ship -> ship.getRegistrationNumber().equalsIgnoreCase(shipToDelete.getRegistrationNumber())
        );
        if (removed) {
            saveDataToFile();
        }
        return removed;
    }

    /**
     * Saves all aid ship data back to the CSV file.
     *
     * @throws IOException if the file cannot be written to
     */
    public void saveDataToFile() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (AidShip ship : aidShips) {
                bw.write(convertAidShipToLine(ship));
                bw.newLine();
            }
        }
    }

    /**
     * Converts a CSV line into an {@link AidShip} object.
     *
     * @param line the CSV line to parse
     * @return a new {@link AidShip} instance
     */
    private AidShip convertLineToAidShip(String line) {
        String[] parts = line.split(",");
        if (parts.length < 8) {
            throw new IllegalArgumentException("Invalid CSV line: " + line);
        }

        String name = parts[0].trim();
        String registrationNumber = parts[1].trim();
        double tonnage = Double.parseDouble(parts[2].trim());
        int crewSize = Integer.parseInt(parts[3].trim());
        String port = parts[4].trim();
        String aidType = parts[5].trim();
        int aidCapacity = Integer.parseInt(parts[6].trim());
        boolean hasHelipad = Boolean.parseBoolean(parts[7].trim());

        return new AidShip(name, registrationNumber, tonnage, crewSize, port, aidType, aidCapacity, hasHelipad);
    }

    /**
     * Converts an {@link AidShip} object into a CSV line.
     *
     * @param ship the aid ship to convert
     * @return a comma-separated string
     */
    private String convertAidShipToLine(AidShip ship) {
        return String.join(",",
                ship.getName(),
                ship.getRegistrationNumber(),
                String.valueOf(ship.getTonnage()),
                String.valueOf(ship.getCrewSize()),
                ship.getPort(),
                ship.getAidType(),
                String.valueOf(ship.getAidCapacity()),
                String.valueOf(ship.isHasHelipad())
        );
    }

    /**
     * Returns a formatted list of all loaded ships.
     *
     * @return a table-like string representation of all aid ships
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("AidShipCoordinator List has %d responders%n", aidShips.size()));
        sb.append("---------------------------------------------------------------------------------------------------------------------------\n");
        sb.append(String.format(
                "| %-15s | %-18s | %-8s | %-9s | %-20s | %-20s | %-14s | %-13s |%n",
                "Name", "Registration Number", "Tonnage", "Crew Size", "Current Port", "Aid Type", "Supplies On Board", "Helipad"
        ));
        sb.append("---------------------------------------------------------------------------------------------------------------------------\n");

        for (AidShip ship : aidShips) {
            sb.append(ship.toString()).append("\n");
        }

        sb.append("---------------------------------------------------------------------------------------------------------------------------\n");

        return sb.toString();
    }

    /**
     * Returns the internal list of all aid ships.
     *
     * @return a list of {@link AidShip} objects
     */
    public ArrayList<AidShip> getAidShipList() {
        return aidShips;
    }

    /**
     * Returns the path of the CSV file in use.
     *
     * @return the file path
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Updates the file path used for loading and saving data.
     *
     * @param filePath the new CSV file path
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
