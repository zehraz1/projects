#include "Nodelist.h"
#include <iostream>
#include <vector>
using namespace std;

#ifndef FLIGHT_H
#define FLIGHT_H


class Flight {
    // Class definition for Flight
private:
    string name;
    int rows;
    int cols;
    OLLIST passenger_list;
    vector<vector<bool>> seat_map; 

public:
    Flight();
    // Constructor
    Flight(string Name, int num_row, int num_col);
    // Parameterized Constructor
    Flight(const Flight& src);
// Copy Constructor
    ~Flight();
// Destructor
    string get_name()const;
    int get_rows()const;
    int get_cols()const;
// Getter functions to retrieve flight information
    void set_name(string name);
    void set_rows(int row);
    void set_cols(int cols);
// Setter functions to set flight information
   
    void populate(const string& file);
    void displayseatmap()const;
    void displaypasslist()const;
    void add_passenger();
    void remove_passenger();
    void resizemap(int rows, int cols);
    void savedata(string file);
};
// Functions to manage passenger and seat data
#endif
