/*
 * @author Zehra
 * @version 2.0
 */

package edu.ucalgary.oop;

import java.util.*;

/**
 * Represents a location where disaster relief efforts are being carried out.
 */
public class Location {
    private String name;
    private String address;
    private ArrayList<DisasterVictim> occupants;
    private ArrayList<Supply> supplies;

    /**
     * Constructs a Location object with a name and an address.
     *
     * @param name    The name of the location.
     * @param address The address of the location.
     */
    public Location(String name, String address) {
        this.name = name;
        this.address = address;
        this.occupants = new ArrayList<>();
        this.supplies = new ArrayList<>();
    }

    /**
     * Retrieves the name of the location.
     *
     * @return The name of the location.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the location.
     *
     * @param name The name of the location.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the address of the location.
     *
     * @return The address of the location.
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Sets the address of the location.
     *
     * @param address The address of the location.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Retrieves the occupants of the location.
     *
     * @return The list of occupants in the location.
     */
    public ArrayList<DisasterVictim> getOccupants() {
        return this.occupants;
    }

    /**
     * Sets the occupants of the location.
     *
     * @param occupants The list of occupants in the location.
     */
    public void setOccupants(ArrayList<DisasterVictim> occupants) {
        this.occupants = occupants;
    }

    /**
     * Retrieves the supplies available at the location.
     *
     * @return The list of supplies available at the location.
     */
    public ArrayList<Supply> getSupplies() {
        return this.supplies;
    }

    /**
     * Sets the supplies available at the location.
     *
     * @param supplies The list of supplies available at the location.
     */
    public void setSupplies(ArrayList<Supply> supplies) {
        this.supplies = supplies;
    }

    /**
     * Adds an occupant to the location.
     *
     * @param occupant The DisasterVictim to be added as an occupant.
     */
    public void addOccupant(DisasterVictim occupant) {
        if (!this.occupants.contains(occupant)) {
            this.occupants.add(occupant);
        }
    }

    /**
     * Removes an occupant from the location.
     *
     * @param occupant The DisasterVictim to be removed as an occupant.
     */
    public void removeOccupant(DisasterVictim occupant) {
        this.occupants.remove(occupant);
    }

    /**
     * Adds a supply to the location.
     *
     * @param supply The supply to be added to the location.
     */
    public void addSupply(Supply supply) {
        if (!this.supplies.contains(supply)) {
            this.supplies.add(supply);
        }
    }

    /**
     * Removes a supply from the location.
     *
     * @param supply The supply to be removed from the location.
     */
    public void removeSupply(Supply supply) {
        this.supplies.remove(supply);
    }

    /**
     * Allocates a supply to a specific DisasterVictim at the location.
     *
     * @param disasterVictimName The name of the DisasterVictim to whom the supply is to be allocated.
     * @param supply             The supply to be allocated.
     */
    public void allocateSupplyToDV(String disasterVictimName, Supply supply) {
        DisasterVictim occupant = null;
        for (DisasterVictim dv : occupants) {
            if (dv.getFirstName().equals(disasterVictimName)) {
                occupant = dv;
                break;
            }
        }
        if (occupant != null) {
            if (supply.getQuantity() > 0) {
                boolean supplyFound = false;
                for (Supply s : occupant.getPersonalBelongings()) {
                    if (s.getType().equals(supply.getType())) {
                        s.setQuantity(s.getQuantity() + 1);
                        supplyFound = true;
                        break;
                    }
                }
                if (!supplyFound) {
                    occupant.addPersonalBelonging(new Supply(supply.getType(), 1));
                }
                supply.setQuantity(supply.getQuantity() - 1);
                if (supply.getQuantity() == 0) {
                    supplies.remove(supply);
                }
                System.out.println("Supply allocated to " + disasterVictimName + ".");
            } else {
                System.out.println("Insufficient supply to allocate to " + disasterVictimName + ".");
            }
        } else {
            System.out.println("Disaster victim not found in this location.");
        }
    }

}