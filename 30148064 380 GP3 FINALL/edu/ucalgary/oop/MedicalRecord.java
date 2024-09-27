package edu.ucalgary.oop;
/**
 * The MedicalRecord class represents a medical record containing treatment details
 * and the date of treatment, associated with a specific location.
 * 
 * @author Zehra
 * @version 1.0
 * @since 1.0
 */
public class MedicalRecord {
    private Location location; 
    private String treatmentDetails;
    private String dateOfTreatment; 

    /**
     * Constructs a MedicalRecord object with the specified location, treatment details, and date of treatment.
     * @param location The location associated with the medical record.
     * @param treatmentDetails The details of the treatment.
     * @param dateOfTreatment The date of the treatment in the format YYYY-MM-DD.
     * @throws IllegalArgumentException if the dateOfTreatment does not match the expected format.
     */
    public MedicalRecord(Location location, String treatmentDetails, String dateOfTreatment) throws IllegalArgumentException{
        String regex = "\\d{4}-\\d{2}-\\d{2}";
        if (!dateOfTreatment.matches(regex)) {
            throw new IllegalArgumentException("Invalid date format. Expected format is YYYY-MM-DD.");
        }
        this.location = location;
        this.treatmentDetails = new String(treatmentDetails);
        this.dateOfTreatment = new String(dateOfTreatment);
    }

     /**
     * Retrieves the location associated with the medical record.
     * 
     * @return The location of the medical record.
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Sets the location associated with the medical record.
     * 
     * @param location The location to set for the medical record.
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Retrieves the treatment details from the medical record.
     * 
     * @return The treatment details.
     */
    public String getTreatmentDetails() {
        return this.treatmentDetails;
    }

    /**
     * Sets the treatment details for the medical record.
     * 
     * @param treatmentDetails The treatment details to set.
     */
    public void setTreatmentDetails(String treatmentDetails) {
        this.treatmentDetails = new String(treatmentDetails);
    }

    /**
     * Retrieves the date of treatment from the medical record.
     * 
     * @return The date of treatment in the format YYYY-MM-DD.
     */
    public String getDateOfTreatment() {
        return this.dateOfTreatment;
    }

    /**
     * Sets the date of treatment for the medical record.
     * 
     * @param dateOfTreatment The date of treatment to set in the format YYYY-MM-DD.
     * @throws IllegalArgumentException if the dateOfTreatment does not match the expected format.
     */
    public void setDateOfTreatment(String dateOfTreatment) {
        String regex = "\\d{4}-\\d{2}-\\d{2}";
    if (!dateOfTreatment.matches(regex)) {
        throw new IllegalArgumentException("Invalid date format. Expected format is YYYY-MM-DD.");
    }
        this.dateOfTreatment = new String(dateOfTreatment);
    }
}