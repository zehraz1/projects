/*
 * @author Zehra
 * @version 2.0
 * @since 1.0
 */

package edu.ucalgary.oop;
import java.util.*;
import java.sql.*;

import edu.ucalgary.oop.DisasterVictim;
import edu.ucalgary.oop.Interaction;
import edu.ucalgary.oop.LogInquirerQueries;


public class Inquirer implements LogInquirerQueries {
    private String firstName;
    private String lastName;
    private String info;
    private String servicesPhoneNum;
    private List<Interaction> interactionLog;

    private List<DisasterVictim> disasterVictims = new ArrayList<>();
    private boolean isCentralReliefWorker;
    private Connection dbConnect;

    /**
     * Constructor for Inquirer class.
     *
     * @param firstName        The first name of the inquirer.
     * @param lastName         The last name of the inquirer.
     * @param servicesPhoneNum The services phone number of the inquirer.
     * @param info             Additional information about the inquirer.
     */
    public Inquirer(String firstName, String lastName, String servicesPhoneNum, String info) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.servicesPhoneNum = servicesPhoneNum;
        this.info = info;
        this.interactionLog = new ArrayList<>();
        this.disasterVictims = new ArrayList<>();
    }

    /**
     * Sets the list of disaster victims for the inquirer.
     *
     * @param disasterVictims The list of disaster victims.
     */
    public void setDisasterVictims(List<DisasterVictim> disasterVictims) {
        this.disasterVictims = disasterVictims;
    }

    /**
     * Gets the list of disaster victims for the inquirer.
     *
     * @return The list of disaster victims.
     */
    public List<DisasterVictim> getDisasterVictims() {
        return this.disasterVictims;
    }

    /**
     * Adds a disaster victim to the inquirer's list.
     *
     * @param disasterVictim The disaster victim to add.
     */
    public void addDisasterVictim(DisasterVictim disasterVictim) {
        this.disasterVictims.add(disasterVictim);
    }

    /**
     * Adds an interaction to the inquirer's interaction log.
     *
     * @param interaction The interaction to add.
     */
    public void addInteraction(Interaction interaction) {
        this.interactionLog.add(interaction);
    }

    /**
     * Gets the first name of the inquirer.
     *
     * @return The first name of the inquirer.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name of the inquirer.
     *
     * @return The last name of the inquirer.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets additional information about the inquirer.
     *
     * @return Additional information about the inquirer.
     */
    public String getInfo() {
        return info;
    }

    /**
     * Gets the services phone number of the inquirer.
     *
     * @return The services phone number of the inquirer.
     */
    public String getServicesPhoneNum() {
        return servicesPhoneNum;
    }

    /**
     * Gets the interaction log of the inquirer.
     *
     * @return The interaction log of the inquirer.
     */
    public List<Interaction> getInteractionLog() {
        return interactionLog;
    }

    /**
     * Inserts a new inquirer into the database.
     * 
     * @param id                The ID of the inquirer.
     * @param firstName         The first name of the inquirer.
     * @param lastName          The last name of the inquirer.
     * @param servicesPhoneNum  The phone number of the inquirer's service.
     */
    @Override
    public void logInquirerQuery(int id, String firstName, String lastName, String servicesPhoneNum) {
        try {
            PreparedStatement stmt = dbConnect.prepareStatement("INSERT INTO INQUIRER VALUES (?, ?, ?, ?)");
            stmt.setInt(1, id);
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);
            stmt.setString(4, servicesPhoneNum);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * Searches for an inquirer by the given part of the name.
     * 
     * @param partOfName The part of the name to search for.
     * @return A string containing the found inquirers' first and last names.
     */
   @Override
    public String searchInquirer(String partOfName) {
    partOfName = partOfName.toLowerCase(); // Convert the search term to lowercase

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
     * Sets whether the inquirer is a central relief worker.
     * 
     * @param isCentral True if the inquirer is a central relief worker, false otherwise.
     */
    @Override
    public void setCentralReliefWorker(boolean isCentral) {
        this.isCentralReliefWorker = isCentral;
    }

    /**
     * Checks if the inquirer is a central relief worker.
     * 
     * @return True if the inquirer is a central relief worker, false otherwise.
     */
    @Override
    public boolean isCentralReliefWorker() {
        return isCentralReliefWorker;
    }
}