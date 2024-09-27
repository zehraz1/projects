package edu.ucalgary.oop;

interface DisasterVictimInterface{
    void insertDisasterVictim(int id, String firstName, String lastName, String dateOfBirth);
    void addDisasterVictimRelationship(int id, String relationship);
    void addDisasterVictimMedicalRecord(int id, String medicalRecord);
    void addDisasterVictimLocation(int id, String locationcentre);

}