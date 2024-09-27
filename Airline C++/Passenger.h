#include <iostream>
#include "Seat.h"
using namespace std;

#ifndef PASSENGER_H
#define PASSENGER_H

class Passenger {
    // Class definition for Passenger
private:
    
    string fname;
    string lname;
    string phonenum;
    Seat *seatptr;
    int pass_id;


public:
    
    Passenger();
    // Constructor: Initializes a Passenger object with default values
    Passenger(const Passenger& src);
// Copy Constructor: Creates a copy of an existing Passenger object
    
    ~Passenger();
// Destructor: Deallocates memory used by the Passenger object
    string get_fname()const;
    string get_lname()const;
    string get_phonenum()const;
    int get_pass_id()const;
    Seat *get_seatptr()const;
    // Getter functions to retrieve passenger information
    void set_fname(string fname);
    void set_lname(string lname);
    void set_phonenum(string phonenum);
    void set_pass_id(int pass_id);
    void set_seatptr(Seat* seatptr);
// Setter functions to set passenger information
    
    

    

};
#endif