import java.io.*;
import java.util.ArrayList;
import util.InputValidator;

/**
 * Manages a collection of AidShip objects, allowing for loading, updating,
 * deleting, and saving ship data from a CSV file.
 *
 * This class fulfills the Lab 2 assignment requirements and supports
 * interaction through Lab3.java.
 *
 * @author Fabian Figueroa
 */
public class AidShipManager {

    private ArrayList<AidShip> aidShips;
    private String filePath;

    public AidShipManager(String filePath) {
        this.filePath = filePath;
        this.aidShips = new ArrayList<>();
    }

    // Add a new AidShip
    public void addAidShip(AidShip aidShip) {
        aidShips.add(aidShip);
    }

    // Find a ship by registration number
    public AidShip findAidShip(String registrationNum) {
        for (AidShip ship : aidShips) {
            if (ship.getRegistrationNumber().equalsIgnoreCase(registrationNum)) {
                return ship;
            }
        }
        return null;
    }

    // Check if a ship exists
    public boolean isAidShipExists(String registrationNum) {
        return findAidShip(registrationNum) != null;
    }

    // Load ships from CSV file
    public void loadAidShips() throws IOException {
        aidShips.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true; // flag to skip header
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                if (!line.trim().isEmpty()) {
                    AidShip ship = convertLineToAidShip(line);
                    aidShips.add(ship);
                }
            }
        }
    }


    // Update a ship’s info and return success flag
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

    // Delete a ship and return success flag
    public boolean deleteAidShip(AidShip shipToDelete) throws IOException {
        boolean removed = aidShips.removeIf(
                ship -> ship.getRegistrationNumber().equalsIgnoreCase(shipToDelete.getRegistrationNumber())
        );
        if (removed) {
            saveDataToFile();
        }
        return removed;
    }

    // Save updated data back to file
    public void saveDataToFile() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (AidShip ship : aidShips) {
                bw.write(convertAidShipToLine(ship));
                bw.newLine();
            }
        }
    }

    // Convert CSV line → AidShip object
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

    // Convert AidShip object → CSV line
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Current Aid Ships:\n");
        for (AidShip ship : aidShips) {
            sb.append(ship).append("\n");
        }
        return sb.toString();
    }

    // Getter method for Lab3 compatibility
    public ArrayList<AidShip> getAidShipList() {
        return aidShips;
    }

    // Standard accessors
    public void setAidShips(ArrayList<AidShip> aidShips) {
        this.aidShips = aidShips;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
