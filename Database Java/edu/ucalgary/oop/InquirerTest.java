package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class InquirerTest {
    
    private String expectedFirstName = "Joseph";
    private String expectedLastName = "Bouillon";
    private String expectedPhoneNumber = "+1-123-456-7890";
    private String expectedMessage = "looking for my family members";
    private Inquirer inquirer;
    private List<Interaction> interactionLog;
    private List<DisasterVictim> disasterVictims; 

    @Before
    public void setUp() {
        inquirer = new Inquirer(expectedFirstName, expectedLastName, expectedPhoneNumber, expectedMessage);
        interactionLog = new LinkedList<>();
        
        DisasterVictim victim1;
        DisasterVictim victim2;
        try {
            victim1 = new DisasterVictim("Alice", "2000-01-01");
            victim2 = new DisasterVictim("Bob", "2005-05-05");
            disasterVictims = new ArrayList<>(Arrays.asList(victim1, victim2));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            disasterVictims = new ArrayList<>();
        }
    }

    @Test
    public void testObjectCreation() {
        assertNotNull("Inquirer object should not be null", inquirer);
    }

    @Test
    public void testGetFirstName() {
        assertEquals("getFirstName() should return inquirer's first name", expectedFirstName, inquirer.getFirstName());
    }

    @Test
    public void testGetLastName() {
        assertEquals("getLastName() should return inquirer's last name", expectedLastName, inquirer.getLastName());
    }

    @Test
    public void testGetServicesPhoneNum() {
        assertEquals("getServicesPhoneNum() should return the correct Services Number", expectedPhoneNumber, inquirer.getServicesPhoneNum());
    }

    @Test
    public void testGetInfo() {
        assertEquals("getInfo() should return the inquirer message", expectedMessage, inquirer.getInfo());
    }

    @Test
    public void testAddInteractionAndGetInteractionLog() {
        Interaction newInteractionOne = new Interaction("12/12/2025", "I am looking for my friend");
        Interaction newInteractionTwo = new Interaction("12/12/2027", "I am looking for my mom");

        inquirer.addInteraction(newInteractionOne);
        inquirer.addInteraction(newInteractionTwo);

        List<Interaction> log = inquirer.getInteractionLog();

        assertEquals("interactionLog should contain the added interactions", 2, log.size());
        assertTrue("interactionLog should contain interaction1", log.contains(newInteractionOne));
        assertTrue("interactionLog should contain interaction2", log.contains(newInteractionTwo));
    }

    @Test
    public void testLogInquirerQuery() {
        String query = "Looking for updates on the situation";
        inquirer.logInquirerQuery(1, "John", "Doe", "+1-234-567-8901");
        List<Interaction> interactionLog = inquirer.getInteractionLog();
        assertEquals("Query should be logged", 1, interactionLog.size());
        assertEquals("Logged query should match the expected query", query, interactionLog.get(0).getInteractionDetails());
    }

    @Test
    public void testSearchInquirer() {
        DisasterVictim victim1 = new DisasterVictim("AliceDoe", "2000-01-01");
        DisasterVictim victim2 = new DisasterVictim("BobDoe", "2005-05-05");
        DisasterVictim victim3 = new DisasterVictim("CharlieDoe", "2010-10-10");

        Inquirer inquirer = new Inquirer("John", "Smith", "+1-234-567-8901", "Looking for family members");
        inquirer.addDisasterVictim(victim1);
        inquirer.addDisasterVictim(victim2);
        inquirer.addDisasterVictim(victim3);
        String searchResults = inquirer.searchInquirer("Doe");
        String[] victims = searchResults.split("\n");
        assertEquals("Search results should contain all victims whose first name contains 'Doe'", 3, victims.length);
        assertTrue("Search results should contain victim1", searchResults.contains("AliceDoe"));
        assertTrue("Search results should contain victim2", searchResults.contains("BobDoe"));
        assertTrue("Search results should contain victim3", searchResults.contains("CharlieDoe"));
    }


    @Test
    public void testReliefWorkerLocation() {
        Inquirer inquirer = new Inquirer("John", "Smith", "+1-234-567-8901", "Looking for family members");
        ReliefService reliefService = new ReliefService(inquirer, null, "2024-04-06", "Providing information", null);
        reliefService.setLastKnownLocation(new Location("Warehouse A", "Some address"));
        assertEquals("Inquirer should retrieve the relief worker's location", "Warehouse A", reliefService.getLastKnownLocation().getName());
    }

    @Test
    public void testIsCentralReliefWorker() {
        Inquirer inquirer = new Inquirer("John", "Smith", "+1-234-567-8901", "Looking for family members");
        inquirer.setCentralReliefWorker(true);
        assertTrue("Inquirer should be identified as a central relief worker", inquirer.isCentralReliefWorker());
    }

    @Test
    public void testSetCentralReliefWorker() {
        Inquirer inquirer = new Inquirer("John", "Smith", "+1-234-567-8901", "Looking for family members");
        inquirer.setCentralReliefWorker(true);
        assertTrue("Inquirer should be set as a central relief worker", inquirer.isCentralReliefWorker());
    }
}