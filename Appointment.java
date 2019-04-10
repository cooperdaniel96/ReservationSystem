import java.util.*;
/**
 * This class will be derived from Reservation
 * It will store a Reservation that is made for a hair dresser
 * addHairDresser() is the only new method added in this derived class
 * 
 * @author Daniel Cooper
 * @version 12/13/2016
 */
public class Appointment extends Reservation
{
    //This arrayList will store the hair dressers
    private ArrayList<String> list = new ArrayList<String>();
    
    /**
     * Constructor method that will use the base class constructor
     * 
     * @param String name, int timeSlot
     * @return NONE
     */
    public Appointment(String name, int timeSlot){
        super(name, timeSlot);
    }
    
    
    /**
     * This method will add a hair dresser to our list if the Reservable is active
     * If not active, then the reservation already exists
     * 
     * @param String something
     * @return NONE
     */
    public void addHairdresser(String something){
        if(isActive()){
            throw new IllegalArgumentException("The reservation already exists");
        }else{   
            //Adds a hairdresser name to an internal list in the Reservation. 
            list.add(something);
        }
    }
}
