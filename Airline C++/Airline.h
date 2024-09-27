#include "Flight.h"
using namespace std;

#ifndef AIRLINE_H
#define AIRLINE_H


class Airline {
    // Class definition for Airline
private:
    Flight* new_flight;
     // Pointer to a Flight object
public:
    Airline();
    // Constructor
    Airline(const Airline& src);
    // Copy Constructor
    ~Airline();
    // Destructor
    Flight* get_flight()const;
    // Getter function
    void set_flight(Flight* new_flight);
    // Setter function
};
#endif