    #include "Passenger.h"
    #include <iostream>
    #include <iomanip>
    using namespace std;

    Passenger::Passenger(): fname("\0"), lname("\0"), phonenum("\0"), seatptr(new Seat), pass_id(0) {}; 
// Constructor: Initializes a Passenger object with default values
    Passenger::Passenger(const Passenger& src) {
        // Copy Constructor: Creates a copy of an existing Passenger object
        this->fname = src.fname;
        this->lname = src.lname;
        this->phonenum = src.phonenum;
        this->seatptr = src.seatptr;
        this->pass_id = src.pass_id;
    }

    
    Passenger::~Passenger() {
        delete seatptr;
    }
// Destructor: Deallocates memory used by the Passenger object
    
    void Passenger::set_fname(string fname) {
        this->fname = fname;
    }
// Setter function: Sets the first name of the Passenger
    void Passenger::set_lname(string lname) {
        this->lname = lname;
    }
// Setter function: Sets the last name of the Passenger
    void Passenger::set_phonenum(string phonenum) {
        this->phonenum = phonenum;
    }
    // Setter function: Sets the phone number of the Passenger

    void Passenger::set_seatptr(Seat* seatptr) {
        this->seatptr = seatptr;
    }
// Setter function: Sets the pointer to the Seat object for the Passenger
    void Passenger::set_pass_id(int pass_id) {
        this->pass_id = pass_id;
    }
// Getter function: Returns the first name of the Passenger

    string Passenger::get_fname()const {
        return this->fname;
    }
// Getter function: Returns the last name of the Passenger
    string Passenger::get_lname()const {
        return this->lname;
    }
// Getter function: Returns the phone number of the Passenger
    string Passenger::get_phonenum()const {
        return this->phonenum;
    }
// Getter function: Returns the phone number of the Passenger
    Seat* Passenger::get_seatptr()const {
        return this->seatptr;
    }
// Getter function: Returns the pointer to the Seat object of the Passenger
    int Passenger::get_pass_id()const {
        return this->pass_id;
    }
// Getter function: Returns the Passenger ID

