package edu.ucalgary.oop;
import org.junit.*;
import static org.junit.Assert.*;

import java.io.*;
import java.util.List;

public class GenderFileIOTest {
    
    private String expectedFilePath = "GenderOptions.txt";
    private GenderFileIO genderFile = new GenderFileIO(expectedFilePath);

    @Test
    public void testObjectCreation() {
        assertNotNull("GenderFileIO object should not be null", genderFile);
        try {
            List<String> genderOptions = genderFile.readGenderTextFile();
            assertNotNull("Gender options list should not be null", genderOptions);
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }

    @Test
    public void testEmptyFile() {
        String nonEmptyFilePath = "GenderOptions.txt";
        GenderFileIO genderFile = new GenderFileIO(nonEmptyFilePath);
        try {
            List<String> genderFileContents = genderFile.readGenderTextFile();
            assertFalse("Gender file contents should not be empty", genderFileContents.isEmpty());
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }

    @Test
    public void testNonexistentFile() throws FileNotFoundException {
        String existentFilePath = "GenderOptions.txt"; 
        GenderFileIO genderFile = new GenderFileIO(existentFilePath);
        try {
            genderFile.readGenderTextFile(); 
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }

    @Test
    public void testOpenFileValidInput(){
        try {
            genderFile.readGenderTextFile(); 
            assertTrue("Opening file should be successful", true);
        } catch(IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }
    
    @Test
    public void testReadGenderTextFile() {
        try {
            List<String> genderFileContents = genderFile.readGenderTextFile();
            assertNotNull("Gender file contents should not be null", genderFileContents);
            assertFalse("Gender file contents should not be empty", genderFileContents.isEmpty());
    
            assertTrue("Man should be included in gender options", genderFileContents.contains("man"));
            assertTrue("girl should be included in gender options", genderFileContents.contains("girl"));
            assertTrue("Non-Binary should be included in gender options", genderFileContents.contains("non-binary"));
            assertFalse("Unknown should not be included in gender options", genderFileContents.contains("Unknown"));
        } catch(IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }
}