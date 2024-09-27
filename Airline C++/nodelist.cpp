#include "Nodelist.h"
#include <iostream>
#include <iomanip>
using namespace std;

OLLIST::OLLIST(): headM(0) {}; 
// Constructor: Initializes an empty linked list (OLLIST)
OLLIST::OLLIST(const OLLIST& src) {
    if(src.headM == nullptr) {
        headM = nullptr;
        return;
    }
    // Copy Constructor: Creates a copy of an existing OLLIST object

    headM = new Node;
    Node *new_pass = headM;
    const Node *src_node = src.headM;
    while(true) {
        new_pass->person = src_node->person;
        src_node = src_node->next;
        if(src_node == 0)
            break;
        new_pass->next = new Node;
        new_pass = new_pass->next;
    } 
    new_pass->next = 0;
};
// Copying the nodes from the source list to the new list
OLLIST::~OLLIST() {
    Node *temp = headM;
    Node *prev;
    while (temp != 0) {
        prev = temp;
        temp = temp->next;
        delete prev;
    }
    headM = 0;
};
// Destructor: Deallocates memory used by the linked list
Node* OLLIST::get_fnode()const{
    return this->headM;
}
// Getter function: Returns the pointer to the first node in the linked list

void OLLIST::add(const Passenger& passenger) {
    Node *new_pass = new Node;
    new_pass->person = passenger;

    if (headM == 0) {
        new_pass->next = headM;
        headM = new_pass;
    }
    else {
        Node *before = headM;      
        Node *after = headM->next; 
        while(after != 0) {
            before = after;
            after = after->next;
        }
        new_pass->next = after;
        before->next = new_pass;
    }
}
// Function to add a Passenger node to the end of the linked list
void OLLIST::remove(int passid) {
    if (headM == nullptr) {
        cout << "wait" << endl;
        return;
    }

    Node *doomed_node = 0;

    if (headM->person.get_pass_id() == passid) {   
        doomed_node = headM;
        headM = headM->next;
    } else {
        Node *before = headM;
        Node *maybe_doomed = headM->next;

        while (maybe_doomed != 0 && maybe_doomed->person.get_pass_id() != passid) {
            before = maybe_doomed;
            maybe_doomed = maybe_doomed->next;
        }

        if (maybe_doomed != 0 && maybe_doomed->person.get_pass_id() == passid) {
            doomed_node = maybe_doomed;
            before->next = maybe_doomed->next;
        }
    }
    // Function to remove a Passenger node from the linked list by passenger ID
    delete doomed_node;
}
// Delete the node containing the Passenger to be removed


