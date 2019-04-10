import java.util.*;
/**
 * This class will store the Reservations
 * It store the reservation by name and the time requested
 * It can also print out the reservation to the user
 * 
 * @author Daniel Cooper
 * @version 12/13/2016
 */
public class Reservation
{
    //myReservable will be set to active if a reservation has been made
    //request will store the name of the person who created the reservation
    //slot will store the timeslot that has been reserved
    private Reservable myReservable = null;
    private String request; 
    private int slot;   
    /**
     * Constructor for objects of class Reservation
     * 
     * @param String name, int timeslot
     * @return NONE
     */
    public Reservation(String name, int timeslot)
    {
        this.request = name;
        this.slot = timeslot;
    }
    
    /**
     * Set myReservable to a new reservable
     * 
     * @param Reservable r
     * @return NONE
     */
    public void setMyReservable(Reservable r){
        this.myReservable = r;
    }
    
    /**
     * Getter for myReservable
     * 
     * @param NONE
     * @return myReservable
     */
    public Reservable getMyReservable(){
        return this.myReservable;
    }
    
    /**
     * Getter for the requester name
     * 
     * @param NONE
     * @return request
     */
    public String getName(){
        return this.request;
    }

    /**
     * Setter for the requester name
     * 
     * @param String n
     * @return NONE
     */
    public void setName(String n){
        this.request = n;
    }

    /**
     * Getter for the reservation time
     * 
     * @param NONE
     * @return slot
     */
    public int getReservationTime(){
        return this.slot;
    }

    /**
     * Check to see if the reservable is active or not
     * 
     * @param NONE
     * @return true || false
     */
    public boolean isActive(){
        if(myReservable == null){
            return false;
        }
        return true;
    }

    /**
     * The toString method that will print out the name for the requester and the time slot
     * 
     * @param NONE
     * @return "Reservation made for " + request + " at time " + slot
     */
    public String toString(){
        return "Reservation made for " + request + " at time " + slot;
    }

}
