#include "Seat.h"
using namespace std;

Seat::Seat(): row(0), col('\0'), status(false) {};
// Constructor: Initializes a Seat object with default values
Seat::Seat(const Seat& src) {
    this->row = src.row;
    this->col = src.col;
    this->status = src.status;
};
// Copy Constructor: Creates a copy of an existing Seat object

Seat::~Seat() {
    // Destructor
};


void Seat::set_row(int row) {
    this->row = row;
}
// Setter function: Sets the row of the seat
void Seat::set_col(char col) {
    this->col = col;
}
// Setter function: Sets the column of the seat

void Seat::set_status(bool status) {
    this->status = status;
}
// Setter function: Sets the status of the seat

int Seat::get_row()const {
    return this->row;
}
// Getter function: Returns the row of the seat
char Seat::get_col()const {
    return this->col;
}
// Getter function: Returns the column of the seat
bool Seat::get_status()const {
    return this->status;
}
// Getter function: Returns the status of the seat
