#include <iostream>
#include <fstream>
#include <iomanip>
#include "Airline.h"
using namespace std;


void displayheader();
void Menu();
int validinput(string userin);


int main () {
    string userin;
    Airline new_airline;
    new_airline.get_flight()->populate("flight_info.txt");
    
    do{
        displayheader();    
        cout << "<<< Press Return to Continue >>>";
        cin.ignore();

    } while (!userin.empty()); 

    int choice;
    do{
        cout << endl;
        Menu();
        cout << "Enter your choice: (1, 2, 3, 4, 5 or 6) ";
        getline(cin, userin);


        choice = validinput(userin);
        switch (choice) {
        case 1:
            new_airline.get_flight()->displayseatmap();
            cout << "\n<<< Press Return to Continue >>>\n";
            getline(cin, userin);
            break;
        
        case 2:
            new_airline.get_flight()->displaypasslist();
            cout << "\n<<< Press Return to Continue >>>\n";
            getline(cin, userin);
            break;

        case 3: 
            new_airline.get_flight()->add_passenger();
            break;

        case 4:
            new_airline.get_flight()->remove_passenger();
            break;

        case 5:
            cout << "\nDo you want to save the data in the " << "\"flight_info.txt\"" << "? Please answer <Y or N> ";
            getline(cin, userin);
            if(userin == "Y" || userin == "y") {
                cout << "\nAll the data in the passenger list was saved into the flight_info.txt." << endl;
                new_airline.get_flight()->savedata("flight_info.txt");
            }
            cout << "\n<<< Press Return to Continue >>>\n";
            getline(cin, userin);
            break;

        default:
            break;
        }

    } while (choice != 6);

    cout << "Program terminated.\n";

    return 0;
}

void displayheader () {
    cout << "Version: 1.0" << endl;
    cout<<"Term Project - Flight Management Program in C++"<<endl;
    cout<<"Produced by: Zehra Zaidi, Adam Yuen, Saif Youssef"<<"\nYear: 2023\n"<<endl;
}

void Menu () {
    cout << "Please select one the following options:\n"
         << "1. Display Flight Seat Map.\n"
         << "2. Display Passengers Information.\n"
         << "3. Add a New Passenger.\n"
         << "4. Remove an Existing Passenger\n"
         << "5. Save data\n"
         << "6. Quit.\n";
}

int validinput (string userin) {
    int choice;
    try {
        choice = stoi(userin);
    } catch (const invalid_argument& e) { 
        choice = -1; 
    }
    
    return choice;
}

