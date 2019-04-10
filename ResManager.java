import java.util.*;
/**
 * This class will manage both Reservations and Reservables.
 * It will create Reservations and remove Reservations.
 * It will also add Reservables to lists and keep track of them.
 * Reservations will either be made or not in the makeReservation() class.
 * ArrayLists of type Reservable and Reservation are also kept here.
 * The iterators for Reservation and Reservable are in this class as well.
 * 
 * @author Daniel Cooper
 * @version 12/13/2016
 */
public class ResManager <I, R> 
{
    //This arrayList will store reservables
    private ArrayList<Reservable> listI = new ArrayList<Reservable>();
    //This arrayList will store reservations
    private ArrayList<Reservation> listR = new ArrayList<Reservation>();

    //This arrayList will temporalily store reservations
    private ArrayList<Reservation> newList = new ArrayList<Reservation>();

    /**
     * Constructor for objects of class ResManager
     * Empty constructor
     * 
     * @param NONE
     * @return NONE
     */
    public ResManager()
    {
    }

    /**
     * This method will add an item of type Reservable to the internal arrayList
     * 
     * @param Reservable item
     * @return NONE
     */
    public void addReservable(Reservable item){
        // Adds an item to the manager
        listI.add(item);
    }

    /**
     * This method will create a Reservation
     * First it will check the internal arrayList to see if the Reservation has been made or not yet
     * Then we check to see if the time slot that has been requested is valid or not
     * Then we check to see if a Reservable is open in our arrayList
     * If it passes all our test, then we need to find the table that fits best to our reservation
     * Once that is done, we create the reservation.
     * 
     * @param Reservation trailRes
     * @return trailRes || null
     */
    public Reservation makeReservation(Reservation trailRes){
        //Using a for each loop to chekc to see if a reservation can be made or not
        if(trailRes.getName().equals("")) {
            System.out.println("Not a valid name");
            return null;
        }
        
        
        for(Reservation r: listR){
            if(trailRes.getReservationTime() == r.getReservationTime()){
                System.out.println("Could not make the Reservation");
                System.out.println("Reservation has already been made by someone else");
                return null;
            }
        }

        //if the time slot is greater than 10 or less than 0, the reservation cannot be made
        if(trailRes.getReservationTime() > 10 || trailRes.getReservationTime() < 0){
            System.out.println("Time slot not available");
            return null;
        }

        //check to see if the reservable list is empty or not
        if(listI.isEmpty())
        {
            System.out.println("No reservation available");
            return null;
        }

        //find the item and fitnessValue that will most fit our reservation
        Reservable item = listI.get(0);
        int fitnessValue = item.findFitnessValue(trailRes);

        //loop through the table list and find the best fit for our reservation
        for(int i = 0; i < listI.size() ; i++){
            if(listI.get(i).findFitnessValue(trailRes) > fitnessValue){
                item = listI.get(i);
                fitnessValue = item.findFitnessValue(trailRes);
            }
        }
        //if we have found a table that works, then we can make our reservation
        if(fitnessValue > 0){
            //add reservation to our internal list
            //point our reservable to the appropriate reservation
            //set the reservable 
            //print out the message here not using the iterator
            listR.add(trailRes);
            item.addRes(trailRes);
            trailRes.setMyReservable(item);
            System.out.println("Reservation made for "  + trailRes.getName() + " at time " + trailRes.getReservationTime() + ", " + item.getId());
            return trailRes;
        }
        System.out.println("No reservation available, number of people in party may be too large");
        return null;       
    }

    /**
     * This method will remove a reservation that has been made
     * Once the reservation has been removed, it will return the boolean status of our reservation
     * 
     * @param Reservation res
     * @return true || false
     */
    public boolean unreserve(Reservation res){
        //remove from internal lists
        listR.remove(res);
        //set that to null
        res = null;

        //return true if res is null
        if(res == null){
            return true;
        }
        return false;
    }

    /**
     * This method will reutrn an iterator of active reservations for a name.
     * First it will use a for each loop to find if the reservation by name and add that to a temporary arrayList.
     * Then the iterator of that temp arrayList will be returned.
     * Using the inner class IterName to accomplish this
     * 
     * @param String name
     * @return IterName iter
     */
    public Iterator<R> reservationsByName(String name){
        // Return an iterator of active reservations for a name.
        //loop through the arrayList
        for(Reservation r: listR){
            //if we find the name that equals our given name, add that reservation to our temp list
            if(r.getName().equals(name)){
                newList.add(r);
            }
        }
        //return iterator for temp list
        IterName iter = new IterName();
        return iter;
    }

    /**
     * Return an iterator for all reservable items
     * Using the inner class IterI to accomplish this
     * 
     * @param NONE
     * @return IterI iter
     */
    public Iterator<I> getReservables(){
        IterI iter = new IterI();
        return iter;
    }

    /**
     * Return an iterator for all active reservations 
     * Using the inner class IterI to accomplish this
     * 
     * @param NONE
     * @return IterR iter
     */
    public Iterator <R> getAllReservations(){
        IterR iter = new IterR();
        return iter;
    }

    /**
     * Inner class IterR will implement the interface of Iterator<R> which will use the Generic type R
     * This class will define a new iterator using the Generic type R
     */
    public class IterR implements Iterator<R>{
        //Index will keep track of the index in the list
        //size will be the size of the list
        private int index = 0;
        private int size = listR.size();
        /**
         * Constructor Method that is empty
         * 
         * @param NONE
         * @return NONE
         */
        public IterR(){
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
            if(index < size && listR.get(index) != null){
                return true;
            }
            return false;
        }

        /**
         * This method will return whatever is next in the list
         * 
         * @param NONE
         * @return listR.get(index++);
         */
        public R next(){
            return (R) listR.get(index++);
        }
    }

    /**
     * Inner class IterI will implement the interface of Iterator<I> which will use the Generic type I
     * This class will define a new iterator using the Generic type I
     */
    public class IterI implements Iterator<I>{
        //Index will keep track of the index in the list
        //size will be the size of the list
        private int index = 0;
        private int size = listI.size();
        /**
         * Constructor Method that is empty
         * 
         * @param NONE
         * @return NONE
         */
        public IterI(){
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
            if(index < size && listI.get(index) != null){
                return true;
            }
            return false;
        }

        /**
         * This method will return whatever is next in the list
         * 
         * @param NONE
         * @return listR.get(index++);
         */
        public I next(){
            return (I) listI.get(index++);
        }
    }

    /**
     * Inner class IterName will implement the interface of Iterator<R> which will use the Generic type R
     * This class will define a new iterator using the Generic type R
     */
    public class IterName implements Iterator<R>{
        //Index will keep track of the index in the list
        //size will be the size of the list
        private int index = 0;
        private int size = newList.size();
        /**
         * Constructor Method that is empty
         * 
         * @param NONE
         * @return NONE
         */
        public IterName(){
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
            if(index < size && newList.get(index) != null){
                return true;
            }
            return false;
        }

        /**
         * This method will return whatever is next in the list
         * 
         * @param NONE
         * @return listR.get(index++);
         */
        public R next(){
            return (R) newList.get(index++);
        }
    }
}
