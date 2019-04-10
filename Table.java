import java.util.*;
/**
 * This class is derived from the class Reservable.
 * It will extract data from a file and store the number of seats a table contains.
 * The method findFitnessValue() will be overridden and be used to find a table that fits best with the number 
 * of seats requested.
 * 
 * @author Daniel Cooper
 * @version 12/13/2016
 */
public class Table extends Reservable
{
    //Id will store the table name
    //numSeats will store the number of seats that table has
    //listSeats will store the numSeats in a list
    private String id;
    private int numSeats;
    private ArrayList<Integer> listSeats = new ArrayList<Integer>();

    /**
     * Constructor method that will extract data from a file
     * The line of data extracted will be split and numSeats will be the integer of that line
     * the numSeats will then be added to list
     * 
     * @param Scanner inFile
     * @return NONE
     */
    public Table(Scanner inFile){
        super(inFile);
        if(inFile.hasNext()){
            numSeats = Integer.parseInt(inFile.next());
        }

        listSeats.add(numSeats);
    }

    /**
     * Getter for numSeats
     * 
     * @param NONE
     * @return numSeats
     */
    public int getNumSeats(){
        return numSeats;
    }

    /**
     * This will override the findFitnessValue() method
     * This method will return the table that will most fit our number of people from the reservation
     * If the table is too small, then 0 will be returned
     * The formula 100 - (100 * (the difference))/(the number of seats needed) will keep track of what fits the most
     * 
     * @param Reservation res
     * @return fitnessValue
     */
    @Override
    public int findFitnessValue(Reservation res){
        int fitnessValue = 0;
        //check to see if the reservable is active and available
        if(isAv(res) && !res.isActive()){
            //casting 
            RestRes rest = (RestRes) res;
            int value = rest.getNumSeatsNeeded();
            //if there are less seats then the number or seats needed, then return 0
            if(numSeats < value){
                return fitnessValue;
            }
            //find the difference
            int temp = numSeats - value;
            //formula to keep track of what fits the most
            fitnessValue =  100 - (100 * temp)/value;
        }
        return fitnessValue;
    }

    /**
     * This will return the iterator of the number of Seats that have been requested
     * The iterator of the list will be returned
     * 
     * @param NONE
     * @return listSeats.iterator()
     */
    public Iterator<Integer> findSeating(){
        return listSeats.iterator();
    }
}
