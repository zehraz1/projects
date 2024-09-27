package edu.ucalgary.oop;
import java.util.Arrays;

/**
 * The Interaction class represents an interaction with a certain date and details.
 * It provides methods to retrieve and modify the interaction date and details.
 * 
 * @author Zehra
 * @version 1.2
 * @since 1.0
 */

public class Interaction{

    private String interactionDate;
    private String interactionDetails;

    /**
     * Constructs a new Interaction object with the specified date and details.
     * 
     * @param interactionDate The date of the interaction.
     * @param interactionDetails The details of the interaction.
     */
    public Interaction(String interactionDate, String interactionDetails){
        this.interactionDate = interactionDate;
        this.interactionDetails = interactionDetails;
    }

    /**
     * Gets the date of the interaction.
     * 
     * @return The date of the interaction.
     */
    public String getInteractionDate(){
        return interactionDate;
    }

    /**
     * Gets the details of the interaction.
     * 
     * @return The details of the interaction.
     */
    public String getInteractionDetails(){
        return interactionDetails;
    }

    /**
     * Sets the date of the interaction.
     * 
     * @param interactionDate The date of the interaction to set.
     */
    public void setInteractionDate(String interactionDate){
        this.interactionDate = interactionDate;
    }

    /**
     * Sets the details of the interaction.
     * 
     * @param interactionDetails The details of the interaction to set.
     */
    public void setInteractionDetails(String interactionDetails){
        this.interactionDetails = interactionDetails;
    }

    /**
     * Returns a string representation of the Interaction object.
     * 
     * @return A string representation of the Interaction object, including date and details.
     */
    @Override
    public String toString() {
        return "Date: " + interactionDate + ", Details: " + interactionDetails;
    }
}