#include <iostream>
using namespace std;

#ifndef SEAT_H
#define SEAT_H

class Seat {
    // Class definition for Seat
private:
    int row;
    char col;
    bool status;

public:
    Seat();
    // Constructor: Initializes a Seat object with default values
    Seat(const Seat& src);
// Copy Constructor
    ~Seat();
// Destructor
    int get_row()const;
    char get_col()const;
    bool get_status()const;
    // Getter functions to retrieve seat information
    void set_row(int row);
    void set_col(char column);
    void set_status(bool status);
// Setter functions to set seat information
    

};
#endif