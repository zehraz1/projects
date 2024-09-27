package edu.ucalgary.oop;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * The GenderFileIO class is responsible for reading gender options from a text file.
 * It provides a method to read the gender options from the specified file.
 * 
 * @author Zehra
 * @version 1.5
 * @since 1.0
 */
public class GenderFileIO {
    private String filepath;

    /**
     * Constructs a GenderFileIO object with the specified file path.
     * 
     * @param filepath The path to the file containing gender options.
     */
    public GenderFileIO(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Reads the gender options from the text file.
     * 
     * @return A list of gender options read from the file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public List<String> readGenderTextFile() throws IOException {
        List<String> genderOptions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                genderOptions.add(line.trim());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Gender options file not found: " + filepath);
            throw e; 
        } catch (IOException e) {
            System.out.println("Error reading gender options from file: " + filepath);
            throw e;
        }
        return genderOptions;
    }

}