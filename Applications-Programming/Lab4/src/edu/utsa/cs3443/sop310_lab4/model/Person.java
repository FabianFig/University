package edu.utsa.cs3443.sop310_lab4.model;

/**
 * Abstract base class representing a person with basic contact information.
 * <p>
 * This class serves as a superclass for user-related entities in the application,
 * encapsulating shared attributes such as name, email, and phone number.
 * It provides common accessors and mutators, as well as a utility method
 * for retrieving the full name.
 *
 * <p>Features implemented:</p>
 * <ul>
 *     <li>Encapsulation of personal details (first name, last name, email, phone)</li>
 *     <li>Standard getter and setter methods</li>
 *     <li>Utility method for full name formatting</li>
 * </ul>
 *
 * @author Fabian Figueroa
 * @version 1.0
 */
public abstract class Person {

    /** The person's first name. */
    private String firstName;

    /** The person's last name. */
    private String lastName;

    /** The person's email address. */
    private String email;

    /** The person's phone number. */
    private String phoneNumber;

    /**
     * Constructs a new {@code Person} with the specified personal details.
     *
     * @param firstName   The person's first name.
     * @param lastName    The person's last name.
     * @param email       The person's email address.
     * @param phoneNumber The person's phone number.
     */
    public Person(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the person's first name.
     *
     * @return The first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the person's last name.
     *
     * @return The last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the person's email address.
     *
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the person's phone number.
     *
     * @return The phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the person's first name.
     *
     * @param firstName The new first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the person's last name.
     *
     * @param lastName The new last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets the person's email address.
     *
     * @param email The new email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the person's phone number.
     *
     * @param phoneNumber The new phone number.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the full name of the person in "First Last" format.
     *
     * @return The full name.
     */
    public String getFullName() {
        return this.getFirstName() + " " + this.getLastName();
    }

    /**
     * Returns a string representation of the person's contact information.
     *
     * @return A formatted string with name, email, and phone number.
     */
    @Override
    public String toString() {
        return "First Name= " + firstName + ", Last Name= " + lastName + ", Email= " + email + ", Phone Number= " + phoneNumber;
    }
}