#include "Airline.h" 

Airline::Airline(): new_flight(new Flight) {};
// Constructor: Initializes an Airline object with a new Flight

Airline::Airline(const Airline& src) {
    this->new_flight = src.new_flight;
};
// Copy constructor: Creates a new Airline object as a copy of an existing Airline

Airline::~Airline() {
    delete new_flight;
};
// Destructor: Frees memory allocated to the Flight object

Flight* Airline::get_flight()const {
    return this->new_flight;
}
// Getter function for the Flight object associated with the Airline
void Airline::set_flight(Flight* new_flight) {
    this->new_flight = new_flight;
}
// Setter function to update the Flight object associated with the Airline

