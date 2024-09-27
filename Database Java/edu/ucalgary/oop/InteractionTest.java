package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;


public class InteractionTest {
    
 
    private String expectedInteractionDate = "20/12/2023";
    private String expectedInteractionDetails = "interaction info";
    private Interaction interaction = new Interaction(expectedInteractionDate, expectedInteractionDetails);


    @Test
    public void testObjectCreation() {
        assertNotNull(interaction);
    }


    @Test
    public void testGetInteractionDate() {
        assertEquals("getInteractionDate() should return the Interaction Date", expectedInteractionDate, interaction.getInteractionDate());
    }
    
 
    @Test
    public void testGetInteractionDetails() {
        assertEquals("getInteractionDetails() should return the Interaction Details", expectedInteractionDetails, interaction.getInteractionDetails());
    }
    
    
   
    @Test
    public void testSetInteractionDetails() {
        String newExpectedInteractionDetails = "new interaction info";
        interaction.setInteractionDetails(newExpectedInteractionDetails);
        assertEquals("setInteractionDetails() should return the new Interaction Details", newExpectedInteractionDetails, interaction.getInteractionDetails());
    }

    
    @Test
    public void testSetInteractionDate() {
        String newExpectedInteractionDate = "20/12/2024";
        interaction.setInteractionDate(newExpectedInteractionDate);
        assertEquals("setInteractionDate() should return the new Interaction Date", newExpectedInteractionDate, interaction.getInteractionDate());
    }

    @Test
    public void testEarliestPossibleDate() {
        String earliestDate = "01/01/0001";
        interaction.setInteractionDate(earliestDate);
        assertEquals("Interaction date should be set correctly for the earliest possible date", earliestDate, interaction.getInteractionDate());
    }

    @Test
    public void testLatestPossibleDate() {
        String latestDate = "31/12/9999";
        interaction.setInteractionDate(latestDate);
        assertEquals("Interaction date should be set correctly for the latest possible date", latestDate, interaction.getInteractionDate());
    }

    @Test
    public void testInteractionDateWithValidFormat() {
        String validDateFormat = "01/01/2024";
        interaction.setInteractionDate(validDateFormat);
        assertEquals("Interaction date should be set correctly with valid format", validDateFormat, interaction.getInteractionDate());
    }

}