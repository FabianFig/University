package edu.utsa.cs3443.sop310_lab4.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Represents a user of the Aid Ship Management Application.
 * <p>
 * A {@code User} extends the {@link Person} class and includes login credentials
 * such as username and password. This class also provides static authentication
 * logic to validate user credentials against a CSV-based user database.
 *
 * <p>Features implemented:</p>
 * <ul>
 *     <li>Encapsulation of user credentials and personal information</li>
 *     <li>CSV-based authentication method</li>
 * </ul>
 *
 * @author Fabian Figueroa
 * @version 1.0
 */
public class User extends Person {

    /** The username used for login authentication. */
    private String username;

    /** The password associated with the user account. */
    private String password;

    /**
     * Constructs a new {@code User} with the specified personal and login details.
     *
     * @param firstName   The user's first name.
     * @param lastName    The user's last name.
     * @param email       The user's email address.
     * @param phoneNumber The user's phone number.
     * @param userName    The username used for login.
     * @param password    The password used for login.
     */
    public User(String firstName, String lastName, String email, String phoneNumber, String userName, String password) {
        super(firstName, lastName, email, phoneNumber);
        this.username = userName;
        this.password = password;
    }

    /**
     * Returns the username of the user.
     *
     * @return The user's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username The new username to assign.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password of the user.
     *
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The new password to assign.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Authenticates a user based on provided credentials.
     * <p>
     * Reads from the {@code users.csv} file and compares the input username and password
     * against stored records. If a match is found, a new {@code User} object is returned.
     * Otherwise, {@code null} is returned.
     *
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     * @return A {@code User} object if authentication is successful; {@code null} otherwise.
     */
    public static User authenticate(String username, String password) {
        String filePath = "data/users.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 6) {
                    String firstName = fields[0].trim();
                    String lastName = fields[1].trim();
                    String email = fields[2].trim();
                    String phoneNumber = fields[3].trim();
                    String fileUserName = fields[4].trim();
                    String filePassword = fields[5].trim();

                    if (fileUserName.equals(username) && filePassword.equals(password)) {
                        return new User(firstName, lastName, email, phoneNumber, fileUserName, filePassword);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}