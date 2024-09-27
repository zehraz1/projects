package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class DisasterVictimTest {
    private DisasterVictim victim;
    private List<Supply> suppliesToSet; 
    private String expectedFirstName = "Freda";
    private String EXPECTED_ENTRY_DATE = "2024-01-18";
    private String invalidDate = "15/13/2024";
    private String expectedComments = "Initial comments";

    @Before
    public void setUp() {
        victim = new DisasterVictim(expectedFirstName, EXPECTED_ENTRY_DATE);
        suppliesToSet = new ArrayList<>();
        suppliesToSet.add(new Supply("Water Bottle", 10));
        suppliesToSet.add(new Supply("Blanket", 5));
    }

    @Test
    public void testConstructorWithValidEntryDate() {
        String validEntryDate = "2024-01-18";
        DisasterVictim victim = new DisasterVictim("Freda", validEntryDate);
        assertNotNull("Constructor should successfully create an instance with a valid entry date", victim);
        assertEquals("Constructor should set the entry date correctly", validEntryDate, victim.getEntryDate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidEntryDateFormat() {
        String invalidEntryDate = "18/01/2024"; 
        new DisasterVictim("Freda", invalidEntryDate);
    }

    @Test
    public void testSetDateOfBirth() {
        String newDateOfBirth = "1987-05-21";
        victim.setDateOfBirth(newDateOfBirth);
        assertEquals("setDateOfBirth should correctly update the date of birth", newDateOfBirth, victim.getDateOfBirth());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfBirthWithInvalidFormat() {
        victim.setDateOfBirth(invalidDate); 
    }

    @Test
    public void testSetAndGetFirstName() {
        String newFirstName = "Alice";
        victim.setFirstName(newFirstName);
        assertEquals("setFirstName should update and getFirstName should return the new first name", newFirstName, victim.getFirstName());
    }

    @Test
    public void testSetAndGetLastName() {
        String newLastName = "Smith";
        victim.setLastName(newLastName);
        assertEquals("setLastName should update and getLastName should return the new last name", newLastName, victim.getLastName());
    }

    @Test
    public void testGetComments() {
        victim.setComments(expectedComments);
        assertEquals("getComments should return the initial correct comments", expectedComments, victim.getComments());
    }

    @Test
    public void testSetComments() {
        victim.setComments(expectedComments);
        String newComments = "Has a minor injury on the left arm";
        victim.setComments(newComments);
        assertEquals("setComments should update the comments correctly", newComments, victim.getComments());
    }

    @Test
    public void testGetAssignedSocialID() {
        DisasterVictim newVictim = new DisasterVictim("Kash", "2024-01-21");
        int expectedSocialId = newVictim.getAssignedSocialID() + 1;
        DisasterVictim actualVictim = new DisasterVictim("Adeleke", "2024-01-22");

        assertEquals("getAssignedSocialID should return the expected social ID", expectedSocialId, actualVictim.getAssignedSocialID());
    }

    @Test
    public void testGetEntryDate() {
        assertEquals("getEntryDate should return the expected entry date", EXPECTED_ENTRY_DATE, victim.getEntryDate());
    }

    @Test
    public void testGetLocation() {
        Location testLocation = new Location("TestName", "TestAddress");
        victim.setLocation(testLocation);
        assertEquals("getLocation should return the selected location", testLocation, victim.getLocation());
    }

    @Test
    public void testSetAndGetAge() {
        int age = 5;
        victim.setAge(age);
        assertEquals("setAge should set the victim's age to the age specified", age, victim.getAge());
        assertEquals("getAge should return what we set the victim's age to", age, victim.getAge());
    }
   
    @Test
    public void testAddFamilyConnection() {
        DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
        DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22");

        FamilyRelation relation = new FamilyRelation(victim2, "parent", victim1);
        victim2.addFamilyConnection(relation);

        List<FamilyRelation> testFamily = victim2.getFamilyConnections();
        assertTrue("addFamilyConnection should add a family relationship", testFamily.contains(relation));
    }

    @Test
    public void testRemoveFamilyConnection() {
        DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
        DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22");
        FamilyRelation relation1 = new FamilyRelation(victim, "sibling", victim1);
        FamilyRelation relation2 = new FamilyRelation(victim, "sibling", victim2);
        victim.setFamilyConnections(Arrays.asList(relation1, relation2));

        victim.removeFamilyConnection(relation1);

        List<FamilyRelation> testFamily = victim.getFamilyConnections();
        assertFalse("removeFamilyConnection should remove the family member", testFamily.contains(relation1));
    }

    @Test
    public void testRemovePersonalBelonging() {
        Supply supplyToRemove = suppliesToSet.get(0); 
        victim.addPersonalBelonging(supplyToRemove); 
        victim.removePersonalBelonging(supplyToRemove);

        List<Supply> testSupplies = victim.getPersonalBelongings();
        assertFalse("removePersonalBelonging should remove the supply from personal belongings", testSupplies.contains(supplyToRemove));
    }

    @Test
    public void testSetFamilyConnection() {
        DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
        DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22");

        FamilyRelation relation = new FamilyRelation(victim1, "sibling", victim2);
        victim1.addFamilyConnection(relation);

        List<FamilyRelation> familyConnectionsList = victim1.getFamilyConnections();
        victim.setFamilyConnections(familyConnectionsList);

        List<FamilyRelation> actualRecords = victim.getFamilyConnections();
        assertTrue("Family relation should be set", actualRecords.contains(relation));
    }


    @Test
    public void testSetMedicalRecords() {
        Location testLocation = new Location("Shelter C", "1234 Shelter Ave");
        MedicalRecord testRecord = new MedicalRecord(testLocation, "test for strep", "2024-02-09");

        victim.setMedicalRecords(new MedicalRecord[]{testRecord});

        List<MedicalRecord> actualRecords = victim.getMedicalRecords();
        assertEquals("setMedicalRecords should correctly update medical records", Arrays.asList(testRecord), actualRecords);
    }

    @Test
    public void testSetAndGetGender() {
        String gender = "female";
        victim.setGender(gender);
        assertEquals("setGender should set the gender to the specified value", gender, victim.getGender());
        assertEquals("getGender should return the gender set", gender, victim.getGender());
    }

    @Test
    public void testAddDietaryRestriction() {
        DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
        victim1.addDietaryRestrictions(DisasterVictim.DietaryRestrictions.GFML);
        List<DisasterVictim.DietaryRestrictions> actualRestrictions = victim1.getDietaryRestrictions();
        assertTrue("addDietaryRestrictions should add a dietary restriction", actualRestrictions.contains(DisasterVictim.DietaryRestrictions.GFML));
    }

    @Test
    public void testRemoveDietaryRestriction() {
        DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
        victim1.addDietaryRestrictions(DisasterVictim.DietaryRestrictions.GFML);
        victim1.removeDietaryRestrictions(DisasterVictim.DietaryRestrictions.GFML);
        List<DisasterVictim.DietaryRestrictions> actualRestrictions = victim1.getDietaryRestrictions();
        assertFalse("removeDietaryRestrictions should remove the dietary restriction", actualRestrictions.contains(DisasterVictim.DietaryRestrictions.GFML));
    }
}