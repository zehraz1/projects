package edu.ucalgary.oop;

/**
 * The Supply class represents a type of supply along with its quantity.
 * 
 * @author Zehra
 * @version 1.1
 * @since 1.0
 */
public class Supply {
    private String type; 
    private int quantity; 

    /**
     * Constructs a Supply object with the specified type and quantity.
     * If the quantity provided is less than 0, the quantity is set to 1.
     * 
     * @param type The type of supply.
     * @param quantity The quantity of the supply.
     */
    public Supply(String type, int quantity){
        if(quantity < 0){
            this.quantity = 1; 
        }else{this.quantity = quantity;}
        this.type = new String(type); 
    }
    
    /**
     * Retrieves the type of the supply.
     * 
     * @return The type of the supply.
     */
    public String getType(){
        return this.type; 
    }

    /**
     * Sets the type of the supply.
     * 
     * @param type The type of the supply to set.
     */
    public void setType(String type){
        this.type = new String(type);
    }

    /**
     * Retrieves the quantity of the supply.
     * 
     * @return The quantity of the supply.
     */
    public int getQuantity(){
        return this.quantity; 
    }

    /**
     * Sets the quantity of the supply.
     * 
     * @param quantity The quantity of the supply to set.
     */
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    /**
     * Returns a string representation of the Supply object.
     * 
     * @return A string representation of the Supply object.
     */
    @Override
    public String toString() {
        return "Supply{name='" + type + "', quantity=" + quantity + "}";
    }
}