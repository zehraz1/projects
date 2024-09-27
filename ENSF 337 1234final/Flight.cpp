#include <iostream>
#include <string>
#include <fstream>
#include <iomanip>
#include "Flight.h"
using namespace std;


Flight::Flight(): name("\0"), rows(0), cols(0), passenger_list(), seat_map() {}; 
// Default constructor
Flight::Flight(string Name, int num_row, int num_col): name(Name), rows(num_row), cols(num_col), passenger_list() {
    resizemap(num_row, num_col);
};
// Parameterized constructor
Flight::Flight(const Flight& src) {
    this->name = src.name;
    this->rows = src.rows;
    this->cols = src.cols;
    this->passenger_list = src.passenger_list;
};
// Copy constructor
Flight::~Flight() {
// Destructor
};

void Flight::set_name(string name) {
    this->name = name;
}
// Setter function
void Flight::set_rows(int rows) {
    this->rows = rows;
}
// Setter function
void Flight::set_cols(int cols) {
    this->cols = cols;
}
// Setter function
string Flight::get_name()const {
    return this->name;
}
// Getter function
int Flight::get_rows()const {
    return this->rows;
}
// Getter function
int Flight::get_cols()const {
    return this->cols;
}
// Getter function
void Flight::populate(const string& fileName) {
    ifstream file(fileName);
    if (!file.is_open()) {
        cerr << "Error opening " << fileName << endl;
        return;
    }

    file >> name >> rows >> cols;
    resizemap(rows, cols);

    string line;
    while (getline(file, line)) {

        if (line.empty()) {
            continue;
        }
       
        if (line.length() < 69) {
            continue; 
        }

        
        string fname = line.substr(0, 20);  // 20 Characters long
        string lname = line.substr(20, 20);  // 20 Characters long
        string phonenum = line.substr(40, 15);  // 12 characters
        string seat = line.substr(60, 4);  // 4 characters
        int id = stoi(line.substr(64, 5));  // 5 characters

        size_t row_part = seat.find_first_not_of("0123456789");

        int row = stoi(seat.substr(0, row_part));

        char cols = seat[row_part];

        Passenger* new_passenger = new Passenger;
        new_passenger->set_fname(fname);
        new_passenger->set_lname(lname);
        new_passenger->set_phonenum(phonenum);
        new_passenger->set_pass_id(id);
        new_passenger->get_seatptr()->set_row(row);
        new_passenger->get_seatptr()->set_col(cols);
        new_passenger->get_seatptr()->set_status(true);
        seat_map.at(row - 1).at(int(toupper(cols)) - 65) = true;
        
        passenger_list.add(*new_passenger);
    }
    file.close();
}

void Flight::displayseatmap () const{
    if(cols == 0 || rows == 0)
        return;
// Function to display the seat map of the Flight
    char letter = 'A'; 

    cout << setw(cols + 1) << "" << setfill(' ') << setw(20) << left << "Aircraft Seat Map" << endl << "     ";
    for (int i = 0; i < cols; i++) {
        cout << setw(4) << letter;
        letter++;
    }

    cout << endl << "   ";
    for (int j = 0; j < cols; j++) {
        cout << "+---";
    }
    cout << "+" << endl;

    for(int k = 1; k <= rows; k++) {
        cout << setw(3) << k;
        for(int l = 0; l < cols; l++) {
            cout << setw(2) << "|";
            if(seat_map.at(k-1).at(l)) {
                cout << setw(2) << "X";
            } else {
                cout << setw(2) << "\0";
            }
        }

        cout << "|" << endl << "   ";
        for (int j = 0; j < cols; j++) {
            cout << "+---";
        }
        cout << "+" << endl;
    }
}
void Flight::displaypasslist()const{
    // Function to display the passenger list of the Flight
    cout << setw(20) << left << "First Name" << setw(20) << "Last Name" << setw(15) << "Phone";
    cout << setw(7) << "Row" << setw(8) << "Seat" << "ID" << endl;

    for(const Node* current_passenger = passenger_list.get_fnode(); current_passenger != NULL; current_passenger = current_passenger->next) {
        cout << "----------------------------------------------------------------------------" << endl;
        cout << setw(20) << current_passenger->person.get_fname() << setw(20) << current_passenger->person.get_lname() << setw(15) << current_passenger->person.get_phonenum();
        cout << setw(7) << current_passenger->person.get_seatptr()->get_row() << setw(8) << current_passenger->person.get_seatptr()->get_col() << current_passenger->person.get_pass_id();
        cout << endl;
    }

    cout << "----------------------------------------------------------------------------" << endl;
}
void Flight::add_passenger() {
    // Function to add a passenger to the Flight
    string pass_in;
    int userin;
    char seat;
    bool is_new_pass = true;
    Passenger* new_pass = new Passenger;

    while (true) {
        is_new_pass = true; 
        cout << "Passenger's ID: ";
        cin >> userin;

        
        if (!cin || userin < 10000 || userin > 99999) {
            cin.clear();
            cin.ignore(10000, '\n');
            cout << "Enter a Valid ID" << endl;
            continue;
        } 
        
        
        for(Node *temp = passenger_list.get_fnode(); temp != NULL; temp = temp->next) {
            if(temp->person.get_pass_id() == userin) {
                is_new_pass = false;
                break;
            }
        }
        
        
        if(!is_new_pass) {
            cout << "Enter a different Passenger ID" << endl;
            continue;
        }
        
        new_pass->set_pass_id(userin);
        break;
    }
    cin.ignore(); 

    cout << "First Name: ";
    getline(cin, pass_in);
    new_pass->set_fname(pass_in);

    cout << "Last Name: ";
    getline(cin, pass_in);
    new_pass->set_lname(pass_in);

    while(true) {
        string phone;
        bool invalid_num = false;
        cout << "Enter the Passenger's Phone Number in the following format ### ### ####:  ";
        getline(cin, pass_in);

        if(pass_in.size() != 12) {
            cout << "\nInvalid Phone Number. Try Again\n";
            continue;
        }

        for(int i = 0; i < 12; i++) {
            if(pass_in.at(i) != ' ')
                phone += pass_in.at(i);
        }

        if(phone.size() != 10) {
            cout << "\nInvalid Number.\n";
            continue;
        }

        for(int j = 0; j < 10; j++) {
            if(int(phone.at(j) - '0') < 0 || int(phone.at(j) - '0') > 9) {
                invalid_num = true;
                break;
            }
        }

        if(invalid_num) {
            cout << "Enter a Valid Number.\n";
        } else {
            pass_in = phone.substr(0, 3) + "-" + phone.substr(3, 3) + "-" + phone.substr(6, 4);
            new_pass->set_phonenum(pass_in);
            break;
        }
    }
    
    while (true){
        
        cout << "Enter row: ";
        cin >> userin;

        if (!cin || userin < 1 || userin > rows){
            cin.clear();
            cin.ignore(10000, '\n');
            cout << "Choose a different row." << endl;
            continue;
        }

        cout << "Enter seat: ";
        cin >> seat;
        seat = toupper(seat);
        
        if (!isalpha(seat) || seat < 'A' || seat > 'A' + cols - 1){
            cin.clear();
            cin.ignore(10000, '\n');
            cout << "Choose a different seat." << endl;
            continue;
        }
        
        if (seat_map.at(userin - 1).at(int(seat) - 65)){
            cout <<"Seat taken. Choose a different seat."<< endl;
            continue;
        }
        new_pass->get_seatptr()->set_row(userin);
        new_pass->get_seatptr()->set_col(seat);
        new_pass->get_seatptr()->set_status(true);
        seat_map[userin - 1][int(seat) - 65] = true;
        break;
    }

    passenger_list.add(*new_pass);
    cin.ignore();
}

void Flight::remove_passenger() {
    // Function to remove a passenger from the Flight
    int userinpassid;

    while(true) {
        cout << "Please enter the id of the passenger that needs to be removed: ";
        cin >> userinpassid;
        
        if (!cin || userinpassid < 10000 || userinpassid > 99999) {
            cin.clear();
            cin.ignore(10000, '\n');
            cout << "Invalid ID. Please enter an integer number between 10000 and 99999" << endl;
            continue;
        } 

        break;
    }

    
    if(passenger_list.get_fnode() == NULL) {
        return;
    }

    for(Node *temp = passenger_list.get_fnode(); temp != NULL; temp = temp->next) {
        
        
        int row = temp->person.get_seatptr()->get_row() - 1;
        int col = int(toupper(temp->person.get_seatptr()->get_col())) - 65;

       
        if(temp->person.get_pass_id() == userinpassid) {
            seat_map.at(row).at(col) = false;
            passenger_list.remove(userinpassid);
        }
    }
    cin.ignore();
}

void Flight::savedata(string file) {
    // Function to save Flight data to a file
    ofstream in(file);
    if (!in.is_open()) {
        cerr << "Error opening " << file << endl;
        return;
    }
    in << left << setw(9) << name << setw(5) << rows << setw(8) << cols << endl;

    Node* current = passenger_list.get_fnode();
    while(current != 0) { 
        in << left << setw(20) << current->person.get_fname();
        in << setw(20) << current->person.get_lname();
        in << setw(20) << current->person.get_phonenum();
        if(current->person.get_seatptr()->get_row() > 9){
            in << current->person.get_seatptr()->get_row() << setw(2) << current->person.get_seatptr()->get_col();
        }
        else in << current->person.get_seatptr()->get_row() << setw(3) << current->person.get_seatptr()->get_col();
 
        in << right << setw(5) << current->person.get_pass_id() << endl;
        current = current->next;
    }

    delete current;
}



void Flight::resizemap(int rows, int cols) {
    // Function to resize the seat map of the Flight
    seat_map.resize(rows);
    for(int j = 0; j < rows; j++) {
        seat_map.at(j).resize(cols);
    }
}
