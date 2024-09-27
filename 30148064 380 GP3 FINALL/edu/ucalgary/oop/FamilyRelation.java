package edu.ucalgary.oop;
import java.util.Arrays;
import java.util.List;
import java.util.*;

/**
 * This is a class that defines how different DisasterVictims can be
 * related to each other. 
 * It includes the two victims and how they are related.
 * 
 * @author  Zehra
 * @version 2.1
 * @since   1.0
 */

public class FamilyRelation {
    private DisasterVictim personOne;
    private String relationshipTo; 
    private DisasterVictim personTwo;
    private static final FamilyRelation[] EMPTY_ARRAY = new FamilyRelation[0];

    /**
     * Constructor for FamilyRelation class.
     * @param personOne      The first DisasterVictim.
     * @param relationshipTo The relationship between the two victims.
     * @param personTwo      The second DisasterVictim.
     */ 

    public FamilyRelation(DisasterVictim personOne, String relationshipTo, DisasterVictim personTwo){
        this.personOne = personOne;
        this.relationshipTo = relationshipTo;
        this.personTwo = personTwo;
        this.personOne.addFamilyConnection(this);
        this.personTwo.addFamilyConnection(this);
    }

    //Getters
    /**
     * Retrieves the first person involved in the family relationship.
     *
     * @return The first person involved in the family relationship.
     */
    public DisasterVictim getPersonOne() {
        return personOne;
    }

    /**
     * Retrieves the relationship between the two persons.
     *
     * @return The relationship between the two persons.
     */
    public String getRelationshipTo() {
        return relationshipTo;
    }

    /**
     * Retrieves the second person involved in the family relationship.
     *
     * @return The second person involved in the family relationship.
     */
    public DisasterVictim getPersonTwo() {
        return personTwo;
    }

    //Setters
    /**
     * Sets the first person involved in the family relationship.
     *
     * @param personOne The first person involved in the family relationship.
     */
    public void setPersonOne(DisasterVictim personOne) {
        this.personOne = personOne;
    }

    /**
     * Sets the relationship between the two persons.
     *
     * @param relationshipTo The relationship between the two persons.
     */
    public void setRelationshipTo(String relationshipTo) {
        this.relationshipTo = relationshipTo;
    }

    /**
     * Sets the second person involved in the family relationship.
     *
     * @param personTwo The second person involved in the family relationship.
     */
    public void setPersonTwo(DisasterVictim personTwo) {
        this.personTwo = personTwo;
    }

    /**
     * Method to ensure that duplicate data is not entered.
     *
     * @return True if the objects are equal, false otherwise.
     */

    public boolean checkExistingRelation() {
        DisasterVictim personOne = this.getPersonOne();
        DisasterVictim personTwo = this.getPersonTwo();
        String relationshipTo = this.getRelationshipTo();

        List<FamilyRelation> familyConnections = personOne != null ? personOne.getFamilyConnections() : new ArrayList<>();

        for (FamilyRelation relation : familyConnections) {
            if (relation.getPersonOne() == personOne &&
                relation.getPersonTwo() == personTwo &&
                relation.getRelationshipTo().equals(relationshipTo)) {
                return true; 
            }
        }

        return false; 
    }

    @Override
    public String toString() {
        return String.format("%s is %s to %s", personOne.getFirstName(), relationshipTo, personTwo.getFirstName());
    }

    /**
     * Method to ensure consistency of relationships between two individuals.
     *
     * @param other The FamilyRelation object to compare with.
     */
    public void isConsistentWith(FamilyRelation other) {
        if (this.equals(other)) return; 

        DisasterVictim personOne = this.getPersonOne();
        DisasterVictim personTwo = this.getPersonTwo();
        DisasterVictim personThree = other.getPersonOne();
        DisasterVictim personFour = other.getPersonTwo();

        if (!personOne.equals(personTwo) && !personThree.equals(personFour)) {
            if ((personOne.equals(personThree) && personTwo.equals(personFour)) || 
                (personOne.equals(personFour) && personTwo.equals(personThree))) {
                if (!this.getRelationshipTo().equals(other.getRelationshipTo())) {
                    other.setRelationshipTo(this.getRelationshipTo());
                }
            }
        }
    
    }

}