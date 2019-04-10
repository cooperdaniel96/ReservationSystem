import java.util.*;
/**
 * This class will keep track of the Reservables.
 * If a reservable is not available, then we cannot make a reservation .
 * This class will contain a list of Reservations and it will store the name of the table that have been extracted from a file.
 * 
 * @author Daniel Cooper
 * @version 12/13/2016
 */
public class Reservable 
{
    //initial capacity of list is 10
    //using Collections, we can set the size of the Array List to 10 
    private ArrayList<Reservation> list = new ArrayList<Reservation>(Collections.nCopies(10, null));
    //this will be the table number
    private String id;

    /**
     * Constructor Method that will take in a file and extract data from the file
     * The data that will be extracted is the table name
     * 
     * @param Scanner fileIn
     * @return NONE
     */
    public Reservable (Scanner fileIn){
        //Reads the next line from the Scanner of an input file.  The file has already been opened.
        //Extracts data for new item
        if(fileIn.hasNext()){
            id = fileIn.next();
        }else{
            id = null;
        }
    }

    /**
     * Returns the table name
     * 
     * @param NONE
     * @return id
     */
    public String getId(){
        return this.id;
    }

    /**
     * Checks to see if the reservable is available
     * If the reservable is not available, then we cannot make the reservation
     * 
     * @param Reservation res
     * @return true || false
     */
    public boolean isAv(Reservation res){
        if(list.get(res.getReservationTime()) == null){
            return true;
        }
        return false;
    }

    /**
     * Returns 100 if a reservation can be made and returns 0 if not
     * 
     * @param Reservation res
     * @return 100 || 0
     */
    public int findFitnessValue(Reservation res){
        if(isAv(res)){
            return 100;
        }
        return 0;
    }

    /**
     * Sets the reservation in our internal arrayList
     * 
     * @param Reservation res
     * @return NONE
     */
    public void addRes(Reservation res){
        list.set(res.getReservationTime(), res);
    }

    /**
     * reutrns an iterator for all reservations for this reservable.
     * It use the inner class Iter<Object> to create the iterator
     * 
     * @param NONE
     * @return iterRes
     * 
     */
    public <R> Iterator<R> getReservations(){
        Iter<R> iterRes = new Iter<R>();
        return iterRes;
    }

    /**
     * Inner class Iter<Object> will implement the surface Iterator<Object>
     * Create an iterator by defining a hasNext() and next()
     */
    public class Iter<Object> implements Iterator<Object>{
        //Index will keep track of the index in the list
        //size will be the size of the list
        private int index = 0;
        private int size = list.size();
         /**
         * Constructor Method that is empty
         * 
         * @param NONE
         * @return NONE
         */
        public Iter(){

        }

        /**
         * Check to see if the list has a next item 
         * If the index is less than the size of the list, then we are good.
         * If the next item is not null, then we are good.
         * 
         * @param NONE
         * @return true || false
         */
        public boolean hasNext(){
            return index < size &&  list.get(index) != null;
        }

        /**
         * This method will return whatever is next in the list
         * 
         * @param NONE
         * @return listR.get(index++);
         */
        public Object next(){
            return (Object) list.get(index++);
        }
    }
}
