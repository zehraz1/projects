package edu.ucalgary.oop;

import java.io.IOException;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;
import java.sql.Date;
import java.util.Scanner;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        DisasterReliefDB myJDBC = new DisasterReliefDB("jdbc:postgresql://localhost/ensf380project", "oop", "ucalgary");
        String filepath = "GenderOptions.txt";

        try {
            myJDBC.initializeConnection();
        } catch (SQLException ex) {
            System.out.println("An error occurred while initializing the database connection: " + ex.getMessage());
            return; 
        }

        Scanner scanner = new Scanner(System.in);
        Location location = new Location("Shelter", "123 Main St");

        DisasterVictim victim1 = new DisasterVictim("John", "2000-01-01");
        DisasterVictim victim2 = new DisasterVictim("Jane", "2001-02-02");

        location.addOccupant(victim1);
        location.addOccupant(victim2);

        Supply supply1 = new Supply("Water", 9);
        Supply supply2 = new Supply("Food", 2);
        location.addSupply(supply1);
        location.addSupply(supply2);

        List<Inquirer> inquirerList = new ArrayList<>();

        Inquirer inquirer1 = new Inquirer("Gordon", "Ramsay", "123-234-3232", "Looking for a friend");
        Inquirer inquirer2 = new Inquirer("Joe", "Malone", "123-234-3132", "Looking for a friend");
        Inquirer inquirer3 = new Inquirer("Todd", "Bensen", "123-234-3212", "Looking for a friend");

        inquirerList.add(inquirer1);
        inquirerList.add(inquirer2);
        inquirerList.add(inquirer3);

        DisasterVictim victim3 = new DisasterVictim("Jacob", "2024-02-01");
        DisasterVictim victim4 = new DisasterVictim("Jess", "2024-02-01");

        List<DisasterVictim> victimList = new ArrayList<>();
        victimList.add(victim3);
        victimList.add(victim4);

        victim3.addDietaryRestrictions(DisasterVictim.DietaryRestrictions.MOML);
        victim3.addDietaryRestrictions(DisasterVictim.DietaryRestrictions.AVML);

        DisasterVictim pea = new DisasterVictim("Pea", "2000-01-01");
        DisasterVictim sam = new DisasterVictim("Sam", "2003-03-03");

        List<DisasterVictim> victimList2 = new ArrayList<>();
        victimList2.add(pea);
        victimList2.add(sam);

        System.out.println("Are you a central relief worker? (yes/no)");
        String input = scanner.nextLine();

        boolean isCentral = input.equalsIgnoreCase("yes");

        Inquirer inquirer = new Inquirer("John", "Doe", "1234567890", "Info");

        inquirer.setCentralReliefWorker(isCentral);

        while (inquirer.isCentralReliefWorker()) {
            System.out.println("Please select what method you would like to perform.");
            System.out.println("Menu Options:");
            System.out.println("1. Log Inquirer Query\n2. Read options from Gender File\n3. Search for an inquirer\n4. View all registered victims (all locations)\n5. Add info about a disaster victim\n6. Add dietary restriction for a victim\n7. Add family relation between disaster Victims\n8. Quit");

            String function = scanner.nextLine();

            if (function.equals("1")) {
                System.out.println(myJDBC.selectInquiryLog());
                System.out.println("Please enter the id: ");
                int id = Integer.parseInt(scanner.nextLine());
                System.out.println("Please enter the inquirer's id: ");
                int inquirerId = Integer.parseInt(scanner.nextLine());
                System.out.println("Please enter the inquirer's call date (yyyy-mm-dd): ");
                String callDateStr = scanner.nextLine();
                Date callDate = Date.valueOf(callDateStr);
                System.out.println("Please enter the inquiry details: ");
                String details = scanner.nextLine();
                myJDBC.insertNewInquiry(id, inquirerId, callDate, details);
                System.out.println("-----The inquiry has been added------");
                System.out.println("\n");
            } else if (function.equals("2")) {
                GenderFileIO genderFileIO = new GenderFileIO(filepath);
                try {
                    List<String> genderOptions = genderFileIO.readGenderTextFile();

                    System.out.println("Gender Options:");
                    for (String option : genderOptions) {
                        System.out.println(option);
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred while reading the gender options file: " + e.getMessage());
                }
            } else if (function.equals("3")) {
                System.out.println("Please enter the inquirer's name or part of it: ");
                String partOfName = scanner.nextLine();

                String result = myJDBC.searchInquirer(partOfName);
                if (result.isEmpty()){
                    System.out.println("No inquirers matching the provided criteria were found.");
                }else{
                    System.out.println(result);
                }
                System.out.println("\n");
            } else if (function.equals("4")) {
                System.out.println(myJDBC.selectVictims());
                System.out.println("\n");
            } else if (function.equals("5")) {
                System.out.println("Select from the menu: ");
                System.out.println("Menu:\n1. Add disaster victim info\n2. Update disaster victim relationship\n3. Update disaster victim MedicalRecords\n4. Update disaster victim Location centre\n5. Quit");
                String function2 = scanner.nextLine();
                if (function2.equals("1")) {
                    System.out.println(myJDBC.selectVictims());
                    System.out.println("Enter disaster victim id: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter disaster victim first name: ");
                    String firstName = scanner.nextLine();
                    System.out.println("Enter disaster victim last name: ");
                    String lastName = scanner.nextLine();
                    System.out.println("Enter disaster victim date of Birth: ");
                    String dateOfBirth = scanner.nextLine();
                    myJDBC.insertDisasterVictim(id, firstName, lastName, dateOfBirth);
                    System.out.println("---Disaster victim successfully added----");
                } else if (function2.equals("2")) {
                    System.out.println(myJDBC.selectVictims());
                    System.out.println("Please enter the id of the disaster victim you want to update");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.println("Please enter the relationship to the disaster victim");
                    String relationship = scanner.nextLine();
                    myJDBC.addDisasterVictimRelationship(id, relationship);
                    System.out.println("successful");
                } else if (function2.equals("3")) {
                    System.out.println(myJDBC.selectVictims());
                    System.out.println("Please enter the id of the disaster victim you want to update");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.println("Please enter the medical record of the disaster victim");
                    String medicalRecord = scanner.nextLine();
                    myJDBC.addDisasterVictimMedicalRecord(id, medicalRecord);
                    System.out.println("successful");
                } else if (function2.equals("4")) {
                    System.out.println(myJDBC.selectVictims());
                    System.out.println("Please enter the id of the disaster victim you want to update");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.println("Please enter the location centre of the disaster victim");
                    System.out.println("Options:\n1. Dispatch\n2. University Of Calgary\n3. Telus\n");
                    String locationcentre = scanner.nextLine();
                    myJDBC.addDisasterVictimLocation(id, locationcentre);
                    System.out.println("successful");
                }else if (function2.equals("5")){
                    return;
                }
            
            } else if(function.equals("6")){
                System.out.println("Available dietary options:");
                for (DisasterVictim.DietaryRestrictions dietRestriction : DisasterVictim.DietaryRestrictions.values()) {
                    System.out.println(dietRestriction);
                }

                System.out.println("Please pick which disaster victim's diet to update:");
                for (DisasterVictim disasterVictim : victimList) {
                    System.out.println(disasterVictim.getFirstName());
                }
                String selectedVictim = scanner.nextLine().toLowerCase();

                DisasterVictim personName = null;
                for (DisasterVictim disasterVictim : victimList) {
                    if (disasterVictim.getFirstName().toLowerCase().equals(selectedVictim)) {
                        personName = disasterVictim;
                        break;
                    }
                }

                if (personName != null) {
                    System.out.println("What dietary restriction would you like to add?");
                    String restriction = scanner.nextLine().toUpperCase();
                    
                    try {
                        DisasterVictim.DietaryRestrictions dietaryRestriction = DisasterVictim.DietaryRestrictions.valueOf(restriction);
                        
                        personName.addDietaryRestrictions(dietaryRestriction);
                        
                        System.out.println("Updated dietary restrictions:");
                        for (DisasterVictim.DietaryRestrictions dr : personName.getDietaryRestrictions()) {
                            System.out.println(dr);
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid dietary restriction. Please enter a valid option.");
                    }
                } else {
                    System.out.println("No victim found with the specified name.");
                }

            }else if (function.equals("7")){
                System.out.println("Current Disaster victims:");
                for (DisasterVictim victim : victimList2) {
                    System.out.println(victim.getFirstName());
                }

                System.out.println("Who would you like to add a Family Relation for? ");
                String personToAddRelationTo = scanner.nextLine().toLowerCase();

                DisasterVictim victimToAddRelationTo = null;
                for (DisasterVictim victim : victimList2) {
                    if (victim.getFirstName().equalsIgnoreCase(personToAddRelationTo)) {
                        victimToAddRelationTo = victim;
                        break;
                    }
                }

                if (victimToAddRelationTo != null) {
                    System.out.println("What is the relationship you want to add? ");
                    String relationship = scanner.nextLine().toLowerCase();

                    System.out.println("Who is this person related to? ");
                    String relatedTo = scanner.nextLine().toLowerCase();

                    DisasterVictim relatedVictim = null;
                    for (DisasterVictim victim : victimList2) {
                        if (victim.getFirstName().equalsIgnoreCase(relatedTo)) {
                            relatedVictim = victim;
                            break;
                        }
                    }

                    if (relatedVictim != null) {
                        FamilyRelation relation1 = new FamilyRelation(victimToAddRelationTo, relationship, relatedVictim);
                        FamilyRelation relation2 = new FamilyRelation(relatedVictim, relationship, victimToAddRelationTo);

                        relation1.isConsistentWith(relation2);

                        System.out.println("Relationships added successfully and consistent.");
                        peace.addFamilyConnection(relation1);
                        sam.addFamilyConnection(relation2);
                        System.out.println("Peace's family connections: " + Arrays.toString(peace.getFamilyConnections().toArray()));
                        System.out.println("Sam's family connections: " + Arrays.toString(sam.getFamilyConnections().toArray()));
                    } else {
                        System.out.println("Related person not found.");
                    }
                } else {
                    System.out.println("Person not found.");
                }
            }else if (function.equals("8")) {
                return;
            }

        } 

        while (!inquirer.isCentralReliefWorker()) {
            System.out.println("Menu Options:");
            System.out.println("1. Read options from Gender File\n2. View individuals in your centre\n3. Allocate supply to individual\n4. Add an interaction for an Inquirer\n5. Quit");
            String function = scanner.nextLine();
            if (function.equals("1")) {
                GenderFileIO genderFileIO = new GenderFileIO(filepath);
                try {
                    List<String> genderOptions = genderFileIO.readGenderTextFile();

                    System.out.println("Gender Options:");
                    for (String option : genderOptions) {
                        System.out.println(option);
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred while reading the gender options file: " + e.getMessage());
                }
            } else if (function.equals("2")) {
                System.out.println("Please enter the name of the centre where you are located:\n 1. Dispatch\n 2. University of Calgary\n 3. TELUS");
                String locationcentre = scanner.nextLine().toLowerCase();
                System.out.println("Individuals at the location are: \n");
                System.out.println(myJDBC.selectLocationVictims(locationcentre));
            } else if (function.equals("3")) {
                System.out.println("\nLocation supplies before allocation: ");
                for (Supply supply : location.getSupplies()) {
                    System.out.println(supply);
                }

                System.out.println("\nWho would you like to allocate supply to?\n1. John\n2. Jane ");
                String person = scanner.nextLine().toLowerCase();

                System.out.println("\nWhat is the supply you are allocating?\n1. Food\n2. Water ");
                String supplyToAllocate = scanner.nextLine().toLowerCase();

                if (supplyToAllocate.equals("food")) {
                    if (person.equals("john")) {
                        location.allocateSupplyToDV("John", supply2);
                    } else if (person.equals("jane")) {
                        location.allocateSupplyToDV("Jane", supply2);
                    }
                } else if (supplyToAllocate.equals("water")) {
                    if (person.equals("john")) {
                        location.allocateSupplyToDV("John", supply1);
                    } else if (person.equals("jane")) {
                        location.allocateSupplyToDV("Jane", supply1);
                    }
                } else {
                    System.out.println("Enter a correct value");
                }

                System.out.println("Location supplies after allocation:");
                for (Supply supply : location.getSupplies()) {
                    System.out.println(supply);
                }
                if (person.equals("john")){
                    System.out.println("John's personal belongings:");
                    for (Supply supply : victim1.getPersonalBelongings()) {
                        System.out.println(supply);
                    }
                }else if (person.equals("jane")){
                    System.out.println("Jane's personal belongings:");
                    for (Supply supply : victim2.getPersonalBelongings()) {
                        System.out.println(supply);
                    }
                }
            } else if (function.equals("4")){
                System.out.println("These are the list of inquirers");
                for (Inquirer inq : inquirerList) {
                    System.out.println(inq.getFirstName());
                }

                System.out.println("Who would you like to add the interaction for?");
                String personName = scanner.nextLine().toLowerCase();

                Inquirer selectedInquirer = null;
                for (Inquirer inq : inquirerList) {
                    if (inq.getFirstName().toLowerCase().equals(personName)) {
                        selectedInquirer = inq;
                        break;
                    }
                }

                if (selectedInquirer != null) {
                    System.out.println("Please enter the interaction Date");
                    String dateDetails = scanner.nextLine();
                    System.out.println("Please enter the interaction Details");
                    String interactionDetails = scanner.nextLine();
                    Interaction interaction = new Interaction(dateDetails, interactionDetails);
                    selectedInquirer.addInteraction(interaction);

                    int i = 1;
                    System.out.println("These are the interactions of the individual: ");
                    for (Interaction inter : selectedInquirer.getInteractionLog()) {
                        System.out.println("Interaction " + (i++));
                        System.out.println(inter.getInteractionDate() + ": " + inter.getInteractionDetails());
                    }
                } else {
                    System.out.println("Inquirer not found.");
                }


            }else if (function.equals("5")){
                return;
            }
        }
        scanner.close();
    }
}
