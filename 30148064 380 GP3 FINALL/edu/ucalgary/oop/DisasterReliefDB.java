package edu.ucalgary.oop;

import edu.ucalgary.oop.DisasterVictimInterface;
import java.sql.*;
/**
 * The DisasterReliefDB class provides methods to interact with a disaster relief database.
 * It allows connecting to the database, querying data, and performing operations such as adding, deleting, or updating records.
 * This class uses JDBC to communicate with the database.
 * @author Zehra
 * @version 2.0
 * @since 1.0
 */
public class DisasterReliefDB implements DisasterVictimInterface {
    public final String DBURL;
    public final String USERNAME;
    public final String PASSWORD;

    private Connection dbConnect;
    private ResultSet results;

    /**
     * Constructs a DisasterReliefDB object with the specified database URL, username, and password.
     * 
     * @param url   The URL of the database.
     * @param user  The username for accessing the database.
     * @param pw    The password for accessing the database.
     */
    public DisasterReliefDB(String url, String user, String pw) {
        this.DBURL = url;
        this.USERNAME = user;
        this.PASSWORD = pw;
    }

    /**
     * Initializes a connection to the database.
     * @throws SQLException If a database access error occurs.
     */

    public void initializeConnection() throws SQLException {
        dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
    }

    /**
     * Retrieves the database URL.
     * @return The database URL.
     */
    String getDburl() {
        return this.DBURL;
    }

    /**
     * Retrieves the database username.
     * 
     * @return The database username.
     */
    String getUsername() {
        return this.USERNAME;
    }

    /**
     * Retrieves the database password.
     * 
     * @return The database password.
     */
    String getPassword() {
        return this.PASSWORD;
    }

    /**
     * Retrieves information about inquirers from the database.
     * 
     * @return A string containing information about inquirers.
     */
    public String selectInquirer(){

        StringBuffer inquirers = new StringBuffer();
        
        try {                    
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM INQUIRER");
            
            while (results.next()){
                
                inquirers.append(results.getString("id") + ", " + results.getString("firstname") + ", " + results.getString("lastname") + ", " + results.getString("phonenumber"));
                inquirers.append('\n');
            }
            
            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return inquirers.toString();
    }

    /**
     * Deletes an inquirer record from the database.
     * 
     * @param id The ID of the inquirer to delete.
     */
    public void deleteInquirer(int id) {
        try {
            PreparedStatement stmt = dbConnect.prepareStatement("DELETE FROM INQUIRER WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Inserts a new inquirer record into the database.
     * 
     * @param id The ID of the new inquirer.
     * @param firstname The first name of the new inquirer.
     * @param lastname The last name of the new inquirer.
     * @param phonenumber The phone number of the new inquirer.
     */
    public void insertNewInquirer(int id, String firstname, String lastname, String phonenumber) {
        try {
            PreparedStatement stmt = dbConnect.prepareStatement("INSERT INTO INQUIRER VALUES (?, ?, ?, ?)");
            stmt.setInt(1, id);
            stmt.setString(2, firstname);
            stmt.setString(3, lastname);
            stmt.setString(4, phonenumber);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * Retrieves information about all victims from the database.
     * 
     * @return A string containing information about all victims.
     */
    public String selectVictims() {
    StringBuffer victims = new StringBuffer();
        try {
            Statement myStmt = dbConnect.createStatement();
            ResultSet results = myStmt.executeQuery("SELECT * FROM victims");
            while (results.next()) {
                System.out.println("Results: " + results.getString("id") + ", " + results.getString("firstName") + ", " + results.getString("lastName")
                + ", " + results.getString("dateOfBirth") + ", " + results.getString("locationcentre") + ", " + results.getString("relationship") + ", " + results.getString("medicalRecord"));
            } 
            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return victims.toString();
    }

    /**
     * Inserts a new DisasterVictim into the victims table in the database.
     * 
     * @param id           The ID of the new DisasterVictim.
     * @param firstName    The first name of the new DisasterVictim.
     * @param lastName     The last name of the new DisasterVictim.
     * @param dateOfBirth  The date of birth of the new DisasterVictim.
     */
    public void insertDisasterVictim(int id, String firstName, String lastName, String dateOfBirth) {
        try {
            String query = "INSERT INTO victims (id, firstName, lastName, dateOfBirth) VALUES (?,?,?,?)";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);
            myStmt.setInt(1, id);
            myStmt.setString(2, firstName);
            myStmt.setString(3, lastName);
            myStmt.setString(4, dateOfBirth);
            int rowCount = myStmt.executeUpdate();
            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Adds a relationship to a DisasterVictim in the victims table.
     * 
     * @param id The ID of the DisasterVictim.
     * @param relationship The relationship to add.
     */
    public void addDisasterVictimRelationship(int id, String relationship) {
        try {
            String query = "UPDATE victims SET relationship = ? WHERE id = ?";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);
            myStmt.setString(1, relationship);
            myStmt.setInt(2, id);
            int rowCount = myStmt.executeUpdate();
            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Adds a location centre to a DisasterVictim in the victims table.
     * 
     * @param id The ID of the DisasterVictim.
     * @param locationcentre The location centre to add.
     */
    @Override
    public void addDisasterVictimLocation(int id, String locationcentre) {
        try {
            String query = "UPDATE victims SET locationcentre = ? WHERE id = ?";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);
            myStmt.setString(1, locationcentre);
            myStmt.setInt(2, id);
            int rowCount = myStmt.executeUpdate();
            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Adds a medical record to a DisasterVictim in the victims table.
     * 
     * @param id The ID of the DisasterVictim.
     * @param medicalRecord The medical record to add.
     */
    public void addDisasterVictimMedicalRecord(int id, String medicalRecord) {
        try {
            String query = "UPDATE victims SET medicalRecord = ? WHERE id = ?";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);
            myStmt.setString(1, medicalRecord);
            myStmt.setInt(2, id);
            int rowCount = myStmt.executeUpdate();
            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * Deletes a DisasterVictim from the victims table in the database.
     * 
     * @param id The ID of the DisasterVictim to delete.
     */
    public void deleteDisasterVictim(int id) {
        try {
            String query = "DELETE FROM victims WHERE id = ?";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);
            myStmt.setInt(1, id);
            int rowCount = myStmt.executeUpdate();
            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * Retrieves information about inquiry logs from the database.
     * 
     * @return A string containing information about inquiry logs.
     */
    public String selectInquiryLog(){

        StringBuffer inquiryLog = new StringBuffer();
        
        try {                    
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM INQUIRY_LOG");
            
            while (results.next()){
                
                inquiryLog.append(results.getString("id") + ", " + results.getString("inquirer") + ", " + results.getString("callDate") + ", " + results.getString("details"));
                inquiryLog.append('\n');
            }
            
            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return inquiryLog.toString();
    }

    /**
     * Deletes an inquiry log record from the database.
     * 
     * @param id The ID of the inquiry log to delete.
     */
    public void deleteInquiryLog(int id) {
        try {
            PreparedStatement stmt = dbConnect.prepareStatement("DELETE FROM INQUIRY_LOG WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Inserts a new inquiry log record into the database.
     * 
     * @param id         The ID of the new inquiry log.
     * @param inquirer   The ID of the inquirer associated with the inquiry.
     * @param callDate    The date of the inquiry call.
     * @param details     Additional details of the inquiry.
     */
    public void insertNewInquiry(int id, int inquirer, Date callDate, String details) {
        try {
            PreparedStatement stmt = dbConnect.prepareStatement("INSERT INTO INQUIRY_LOG VALUES (?, ?, ?, ?)");
            stmt.setInt(1, id);
            stmt.setInt(2, inquirer);
            stmt.setDate(3, callDate);
            stmt.setString(4, details);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Searches for an inquirer by a part of their name.
     * 
     * @param partOfName A part of the first or last name of the inquirer to search for.
     * @return A string containing information about inquirers matching the search criteria.
     */
    public String searchInquirer(String partOfName) {
    partOfName = partOfName.toLowerCase(); 

    StringBuffer inquirers = new StringBuffer();
        try {
            Statement myStmt = dbConnect.createStatement();
            ResultSet results = myStmt.executeQuery("SELECT firstname, lastname FROM INQUIRER");

            while (results.next()) {
                String firstName = results.getString("firstname").toLowerCase();
                String lastName = results.getString("lastname");
                
                if (lastName != null) {
                    lastName = lastName.toLowerCase(); 
                } else {
                    continue; 
                }

                if (firstName.contains(partOfName) || lastName.contains(partOfName)) {
                    inquirers.append(results.getString("firstname") + ", " + results.getString("lastname"));
                    inquirers.append("\n");
                }
            }

            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return inquirers.toString();
    }

    /**
     * Retrieves information about victims located in a specific centre from the database.
     * 
     * @param locationcentre The location centre to retrieve victims from.
     * @return A string containing information about victims in the specified centre.
     */
    public String selectLocationVictims(String locationcentre){
        locationcentre = locationcentre.toLowerCase();
        StringBuffer victims = new StringBuffer();
        
        try {                    
            PreparedStatement myStmt = dbConnect.prepareStatement("SELECT firstName, lastName FROM victims WHERE locationcentre = ?");
            myStmt.setString(1, locationcentre);
            ResultSet results = myStmt.executeQuery();
            
            while (results.next()){
                victims.append(results.getString("firstName") + ", " + results.getString("lastName"));
                victims.append('\n');
            }
            
            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return victims.toString();
    }



    public void close() {
        try {
            if (dbConnect != null)
                dbConnect.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


}