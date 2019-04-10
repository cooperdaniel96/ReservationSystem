/**
 * This class will be derived from Reservation and so it will contain all the methods of Reservation
 * The number of seats requested will be stored in here
 * 
 * @author Daniel Cooper
 * @version 12/13/2016
 */
public class RestRes extends Reservation
{
    //keep track of how many seats will be needed for the reservation
    private int seatsNeeded;

    /**
     * Construtor method
     * Call the Reservation construtor
     * 
     * @param Sring name, int startTime, int numSeatsNeeded
     * @return NONE
     */
    public RestRes(String name, int startTime, int numSeatsNeeded){
        super(name, startTime);
        seatsNeeded = numSeatsNeeded;
    }

    /**
     * Getter for seatsNeeded
     * 
     * @param NONE
     * @return seatsNeeded
     */
    public int getNumSeatsNeeded(){
        return seatsNeeded;
    }
}
