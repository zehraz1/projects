package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.Scanner;

/**
 * DisasterVictim is a class which organizes information about the victim's
 * identificatoin, family connections, medical information, personal 
 * belongings and additional comments. 
 *
 * @author  Zehra
 * @version 3.2
 * @since   1.0
 */

public class DisasterVictim {
    public String firstName;
    private String lastName;
    private String dateOfBirth;
    private int age;
    private String comments;
    private final int ASSIGNED_SOCIAL_ID; 
    private List<MedicalRecord> medicalRecords;
    private List<FamilyRelation> familyConnections; 
    private final String ENTRY_DATE;
    private List<Supply> personalBelongings;
    private String gender;
    private static int counter = 0; 
    private Location location;
    private List<DietaryRestrictions> dietaryRestrictions;

    enum DietaryRestrictions{
        AVML,
        DBML,
        GFML,
        KSML,
        LSML,
        MOML,
        PFML,
        VGML,
        VJML
    }

    /**
     * Constructor for DisasterVictim class.
     *
     * @param firstName   The first name of the victim.
     * @param ENTRY_DATE  The entry date in the format yyyy-mm-dd.
     * @throws IllegalArgumentException if the ENTRY_DATE does not match the expected format.
     */
    public DisasterVictim(String firstName, String ENTRY_DATE) throws IllegalArgumentException{
        this.firstName = firstName;
        this.ASSIGNED_SOCIAL_ID = counter;
        counter++;
        this.familyConnections = new ArrayList<>();
        this.personalBelongings = new ArrayList<>();
        this.dietaryRestrictions = new ArrayList<>();
        Pattern entry_date_pat = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
        Matcher myMatcher = entry_date_pat.matcher(ENTRY_DATE);
        if(myMatcher.find()) {
            this.ENTRY_DATE = ENTRY_DATE;
        } else {
            throw new IllegalArgumentException("Invalid date format for ENTRY_DATE. Please use YYYY-MM-DD format.");
        }
    }

    /**
     * Retrieves the first name of the disaster victim.
     *
     * @return The first name of the disaster victim.
     */
    public String getFirstName() {
        return this.firstName;
    }
    /**
     * Sets the age of the disaster victim.
     * 
     * @param age The age to set for the disaster victim.
     */
    public void setAge(int age) {
        this.age = age;
    }
    /**
     * Retrieves the last name of the disaster victim.
     *
     * @return The last name of the disaster victim.
     */
    public String getLastName() {
        return this.lastName;
    }
    /**
     * Retrieves the date of birth of the disaster victim.
     *
     * @return The date of birth of the disaster victim.
     */
    public String getDateOfBirth() {
        return this.dateOfBirth;
    }
    /**
     * Retrieves the comments associated with the disaster victim.
     *
     * @return The comments associated with the disaster victim.
     */
    public String getComments() {
        return this.comments;
    }
    /**
     * Retrieves the medical records of the disaster victim.
     *
     * @return The medical records of the disaster victim.
     */
    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }
    /**
     * Retrieves the entry date of the disaster victim.
     *
     * @return The entry date of the disaster victim.
     */
    public String getEntryDate() {
        return ENTRY_DATE;
    }
    /**
     * Retrieves the assigned social ID of the disaster victim.
     *
     * @return The assigned social ID of the disaster victim.
     */
    public int getAssignedSocialID() {
        return ASSIGNED_SOCIAL_ID;
    }
    /**
     * Retrieves the personal belongings of the disaster victim.
     *
     * @return The personal belongings of the disaster victim.
     */
    public List<Supply> getPersonalBelongings() {
        return personalBelongings;
    }
    
    /**
     * Retrieves the family connections of the disaster victim.
     *
     * @return The family connections of the disaster victim.
     */
    public List<FamilyRelation> getFamilyConnections() {
        return familyConnections;
    }
    /**
     * Retrieves the age of the disaster victim.
     *
     * @return The age of the disaster victim.
     */
    public int getAge() {
        return age;
    }
    /**
     * Retrieves the location of the disaster victim.
     *
     * @return The location of the disaster victim.
     */
    public Location getLocation() {
        return this.location;
    }
    /**
     * Retrieves the gender of the disaster victim.
     *
     * @return The gender of the disaster victim.
     */
    public String getGender() {
        return this.gender;
    }
    /**
     * Sets the first name of the disaster victim.
     * 
     * @param firstName The first name to set for the disaster victim.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * Sets the last name of the disaster victim.
     * 
     * @param lastName The last name to set for the disaster victim.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * Sets the date of birth of the disaster victim.
     * 
     * @param dateOfBirth The date of birth to set for the disaster victim in the format yyyy-mm-dd.
     * @throws IllegalArgumentException if the dateOfBirth does not match the expected format.
     */
    public void setDateOfBirth(String dateOfBirth) throws IllegalArgumentException {
        String pattern = "^\\d{4}-\\d{2}-\\d{2}$"; 
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(dateOfBirth);
        if (matcher.matches()) { 
            this.dateOfBirth = dateOfBirth;
        } else { 
            throw new IllegalArgumentException("Not proper date format: " + dateOfBirth);
        }
    }
    /**
     * Sets the comments associated with the disaster victim.
     * 
     * @param comments The comments to set for the disaster victim.
     */
    public void setComments(String comments) {
        this.comments = comments;
    }
    /**
     * Sets the location of the disaster victim.
     * 
     * @param location The location to set for the disaster victim.
     */
    public void setLocation(Location location) {
        this.location = location;
    }
    public void setMedicalRecords(MedicalRecord[] medicalRecords) {
        this.medicalRecords = Arrays.asList(medicalRecords);
    }

    /**
     * Sets the personal belongings of the disaster victim.
     * 
     * @param supplies The array of supplies to set for the disaster victim.
     */
    public void setPersonalBelongings(Supply[] supplies) {
        this.personalBelongings = new ArrayList<>(Arrays.asList(supplies));
    }

    public void setFamilyConnections(List<FamilyRelation> familyConnections) {
        this.familyConnections = familyConnections;
    }
    
    /**
     * Sets the gender of the disaster victim.
     * 
     * @param gender The gender to set for the disaster victim.
     */
    public void setGender(String gender) {
        GenderFileIO genderTextFile = new GenderFileIO("GenderOptions.txt");
        try {
            if (genderTextFile.readGenderTextFile().contains(gender.toLowerCase())) {
                this.gender = gender.toLowerCase();
            } else {
                System.out.println("Invalid gender option: " + gender);
            }
        } catch (IOException e) {
            System.out.println("Error reading gender options file: " + e.getMessage());
        }
    }

    /**
    * This is a method that adds a new supply to the victim's list of 
    * personalBelongings and makes sure to remove it from the location the person is at to
    * ensure supply consistency
    * @param supply The supply to add
    */
    public void addPersonalBelonging(Supply supply) {
        if (this.personalBelongings == null) {
            this.personalBelongings = new ArrayList<>();
        }
        this.personalBelongings.add(supply);
    }
    /**
    * This is a method that removes an existing supply from the victim's list of 
    * personalBelongings
    * @param supply The supply to remove
    */
    public void removePersonalBelonging(Supply supply) {
        if (this.personalBelongings != null) {
            this.personalBelongings.remove(supply);
        }
    }
    /**
     * This is a method that adds a new familyRelation to both victim's list of 
     * family connections and makes sure there are no partial relationships
     * @param familyConnection The family relation to add
     */
    public void addFamilyConnection(FamilyRelation familyConnection) {
        if (this.familyConnections == null) {
            this.familyConnections = new ArrayList<>();
        }
        this.familyConnections.add(familyConnection);
    }
    /**
     * This is a method that removes an existing family connection from the both victim's 
     * invloved in the relationship's list of family connections
     * @param familyConnection The family relation to remove
     */
    public void removeFamilyConnection(FamilyRelation familyConnection) {
        if (this.familyConnections != null) {
            this.familyConnections.remove(familyConnection);
        }
    }
    /**
     * This is a method that adds a new medicalRecord to the victim's list 
     * of MedicalRecords
     * @param medicalRecord The medical record to add
     */
    public void addMedicalRecord(MedicalRecord medicalRecord) {
        if (this.medicalRecords == null) {
            this.medicalRecords = new ArrayList<>();
        }
        this.medicalRecords.add(medicalRecord);
    }
    public void removeMedicalRecord(MedicalRecord medicalRecord) {
        if (this.medicalRecords != null) {
            this.medicalRecords.remove(medicalRecord);
        }
    }

    /**
     * Retrieves the dietary restrictions of the disaster victim.
     *
     * @return A list of the dietary restrictions of the disaster victim.
     */
    public List<DietaryRestrictions> getDietaryRestrictions(){
        return this.dietaryRestrictions;
    }
    public void setDietaryRestrictions(List<DietaryRestrictions> dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
    }
    /**
     * This is a method that removes an exsisting Dietary Restriction from the victim's list 
     * of Dietary Restrictions
     * @param dietaryrRestriction The Dietary Restriction to remove
     */
    public void removeDietaryRestrictions(DietaryRestrictions dietaryRestriction) {
        if (this.dietaryRestrictions != null) {
            this.dietaryRestrictions.remove(dietaryRestriction);
        }
    }
    /**
     * This is a method that adds a new Dietary Restriction to the victim's list 
     * of Dietary Restrictions
     * @param dietaryrRestriction The Dietary Restriction to add
     */
    public void addDietaryRestrictions(DietaryRestrictions dietaryRestriction) {
        if (this.dietaryRestrictions == null) {
            this.dietaryRestrictions = new ArrayList<>();
        }
        if (!this.dietaryRestrictions.contains(dietaryRestriction)) {
            this.dietaryRestrictions.add(dietaryRestriction);
        } else {
            System.out.println("This dietary restriction is already added for this person.\n");
        }
    }

}