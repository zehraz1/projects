package edu.ucalgary.oop;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a Relief Service used for logging inquiries about missing persons.
 */
public class ReliefService {
    private Inquirer inquirer;
    private DisasterVictim missingPerson;
    private String dateOfInquiry;
    private String infoProvided;
    private Location lastKnownLocation;

    /**
     * Constructs a ReliefService object.
     * 
     * @param inquirer          The inquirer making the inquiry.
     * @param missingPerson     The missing person being inquired about.
     * @param dateOfInquiry     The date of the inquiry.
     * @param infoProvided      Information provided during the inquiry.
     * @param lastKnownLocation The last known location of the missing person.
     */
    public ReliefService(Inquirer inquirer, DisasterVictim missingPerson, String dateOfInquiry,
            String infoProvided, Location lastKnownLocation) {
        this.inquirer = inquirer;
        this.missingPerson = missingPerson;
        this.dateOfInquiry = dateOfInquiry;
        this.infoProvided = infoProvided;
        this.lastKnownLocation = lastKnownLocation;
    }

    /**
     * Sets the inquirer making the inquiry.
     * 
     * @param inquirer The inquirer making the inquiry.
     */
    public void setInquirer(Inquirer inquirer) {
        this.inquirer = inquirer;
    }

    /**
     * Sets the missing person being inquired about.
     * 
     * @param missingPerson The missing person being inquired about.
     */
    public void setMissingPerson(DisasterVictim missingPerson) {
        this.missingPerson = missingPerson;
    }

    /**
     * Sets the date of the inquiry.
     * 
     * @param dateOfInquiry The date of the inquiry.
     * @throws IllegalArgumentException if the date format is invalid.
     */
    public void setDateOfInquiry(String dateOfInquiry) throws IllegalArgumentException {
        if (dateOfInquiry.matches("\\d{4}-\\d{2}-\\d{2}")) {
            this.dateOfInquiry = dateOfInquiry;
        } else {
            throw new IllegalArgumentException("Invalid date format: " + dateOfInquiry);
        }
    }

    /**
     * Sets the information provided during the inquiry.
     * 
     * @param infoProvided Information provided during the inquiry.
     */
    public void setInfoProvided(String infoProvided) {
        this.infoProvided = infoProvided;
    }

    /**
     * Sets the last known location of the missing person.
     * 
     * @param lastKnownLocation The last known location of the missing person.
     */
    public void setLastKnownLocation(Location lastKnownLocation) {
        this.lastKnownLocation = lastKnownLocation;
    }

    /**
     * Gets the inquirer making the inquiry.
     * 
     * @return The inquirer making the inquiry.
     */
    public Inquirer getInquirer() {
        return inquirer;
    }

    /**
     * Gets the missing person being inquired about.
     * 
     * @return The missing person being inquired about.
     */
    public DisasterVictim getMissingPerson() {
        return missingPerson;
    }

    /**
     * Gets the date of the inquiry.
     * 
     * @return The date of the inquiry.
     */
    public String getDateOfInquiry() {
        return dateOfInquiry;
    }

    /**
     * Gets the information provided during the inquiry.
     * 
     * @return Information provided during the inquiry.
     */
    public String getInfoProvided() {
        return infoProvided;
    }

    /**
     * Gets the last known location of the missing person.
     * 
     * @return The last known location of the missing person.
     */
    public Location getLastKnownLocation() {
        return lastKnownLocation;
    }

    /**
     * Gets details of the inquiry log.
     * 
     * @return Details of the inquiry log.
     */
    public String getLogDetails() {
        Inquirer inquirer = getInquirer();
        DisasterVictim missingPerson = getMissingPerson();
        String dateOfInquiry = getDateOfInquiry();
        String infoProvided = getInfoProvided();
        Location lastKnownLocation = getLastKnownLocation();

        return "Inquirer: " + inquirer.getFirstName() + ", " + "Missing Person: " + missingPerson.getFirstName() + ", "
                + "Date of Inquiry: " + dateOfInquiry + ", " + "Info Provided: " + infoProvided + ", "
                + "Last Known Location: " + lastKnownLocation.getName();
    }
}