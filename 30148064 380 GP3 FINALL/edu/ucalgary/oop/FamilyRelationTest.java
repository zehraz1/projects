package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;

public class FamilyRelationTest {

    @Test
    public void testObjectCreation() {
        DisasterVictim personOne = new DisasterVictim("John Dalan", "2000-01-01");
        DisasterVictim personTwo = new DisasterVictim("Jane Dalan", "2003-03-03");
        String relationshipTo = "sibling";
        FamilyRelation testFamilyRelationObject = new FamilyRelation(personOne, relationshipTo, personTwo);
        
        assertNotNull(testFamilyRelationObject);
    }
    
    @Test
    public void testSetAndGetPersonOne() {
        DisasterVictim personOne = new DisasterVictim("John", "2000-01-01");
        DisasterVictim personTwo = new DisasterVictim("Jane", "2003-03-03");
        FamilyRelation familyRelation = new FamilyRelation(personOne, "Parent", personTwo);

        DisasterVictim newPersonOne = new DisasterVictim("New Person", "2024-03-21");
        familyRelation.setPersonOne(newPersonOne);
        assertEquals("setPersonOne should update personOne", newPersonOne, familyRelation.getPersonOne());
    }

    @Test
    public void testSetAndGetPersonTwo() {
        DisasterVictim personOne = new DisasterVictim("John", "2000-01-01");
        DisasterVictim personTwo = new DisasterVictim("Jane", "2003-03-03");
        FamilyRelation familyRelation = new FamilyRelation(personOne, "Parent", personTwo);

        DisasterVictim newPersonTwo = new DisasterVictim("Another Person", "2024-04-22");
        familyRelation.setPersonTwo(newPersonTwo);
        assertEquals("setPersonTwo should update personTwo", newPersonTwo, familyRelation.getPersonTwo());
    }

    @Test
    public void testSetAndGetRelationshipTo() {
        DisasterVictim personOne = new DisasterVictim("John", "2000-01-01");
        DisasterVictim personTwo = new DisasterVictim("Jane", "2003-03-03");
        FamilyRelation familyRelation = new FamilyRelation(personOne, "Parent", personTwo);

        String newRelationship = "sibling";
        familyRelation.setRelationshipTo(newRelationship);
        assertEquals("setRelationshipTo should update the relationship", newRelationship, familyRelation.getRelationshipTo());
    }

    @Test
    public void testCheckExistingRelation() {
        DisasterVictim personOne = new DisasterVictim("John", "2000-01-01");
        DisasterVictim personTwo = new DisasterVictim("Jane", "2003-03-03");
        FamilyRelation familyRelation = new FamilyRelation(personOne, "Parent", personTwo);

        assertTrue("checkExistingRelation() should return true when the relation exists.", familyRelation.checkExistingRelation());
    }

    @Test
    public void testAddFamilyRelationConsistency() {
        DisasterVictim personOne = new DisasterVictim("Pea", "2000-01-01");
        DisasterVictim personTwo = new DisasterVictim("Sam", "2003-03-03");
        FamilyRelation familyRelation = new FamilyRelation(personOne, "Sibling", personTwo);

        assertTrue("Adding a family relation should update both individuals' familyConnections",
                personOne.getFamilyConnections().contains(familyRelation) &&
                        personTwo.getFamilyConnections().contains(familyRelation));
    }

    @Test
    public void testPreventDuplicateRelations() {
        DisasterVictim personOne = new DisasterVictim("Pea", "2000-01-01");
        DisasterVictim personTwo = new DisasterVictim("Sam", "2003-03-03");
        FamilyRelation familyRelation = new FamilyRelation(personOne, "Sibling", personTwo);

        int initialSize = personOne.getFamilyConnections().size();
        familyRelation.setRelationshipTo("Brother"); 
        personOne.addFamilyConnection(familyRelation); 
        assertEquals("Adding a duplicate family relation should not change the size of familyConnections",
                initialSize, personOne.getFamilyConnections().size());
    }
}