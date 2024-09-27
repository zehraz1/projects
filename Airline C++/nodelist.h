#include <iostream>
#include "Passenger.h"
using namespace std;

#ifndef OLLIST_H
#define OLLIST_H
// Header Guard for OLLIST_H to prevent multiple inclusions

struct Node { 
    Passenger person;
    Node *next;
};
// Node structure for the linked list
class OLLIST {
private:
    Node *headM;
    // Class definition for OLLIST (Ordered Linked List)
public:
    
    OLLIST();
    // Constructor: Initializes an empty linked list
    OLLIST (const OLLIST & src);
// Copy Constructor: Creates a copy of an existing OLLIST object
    ~OLLIST();
// Destructor: Deallocates memory used by the linked list

    Node* get_fnode()const; 
     // Getter function: Returns the pointer to the first node in the linked list
    void add(const Passenger& passenger);
        // Function to add a Passenger node to the end of the linked list

    void remove(int passid);
    // Function to remove a Passenger node from the linked list by passenger ID
};
#endif