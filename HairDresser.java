import java.util.*;
/**
 * This class will store a HairDresser.
 * It will be derived from Reservable and so it will use all the methods from Reservable.
 * The time from the hairDresser will be stored as timeSlot.
 * The method findFitnessValue() will be overriden and be used to rank the hairDresser appoitment times.
 * 
 * @author Daniel Cooper
 * @version 12/13/2016
 */
public class HairDresser extends Reservable
{
    private int timeSlot;

    /**
     * Constructor method that will using the base class constructor
     * 
     * @param Scanner fileIn
     * @return NONE
     */   
    public HairDresser(Scanner fileIn){
        super(fileIn);
        if(fileIn.hasNext()){
            timeSlot = Integer.parseInt(fileIn.next());
        }
    }

    /**
     * Finds the appropriate fitness value for the Appointment. 
     * Ranks the possible hairdressers appropriately.
     * So a rank of 100 is the best, the farther away you are from 100, the worst you are.
     * So 100 goes to the appoitment that fits directly to our appoitment.
     * And from there the appoitments that are farther away will have a number less than 100.
     * 
     * @param Reservation res
     * @return fitnessVal
     */
    @Override
    public int findFitnessValue(Reservation res){
        int fitnessVal = 0;
        //The appointment contains a list of possible hairdressers, 
        //so you will need to find a way to compute a fitness function that ranks them appropriately.
        Appointment apt = (Appointment) res;

        //this will be a rank system
        int value = res.getReservationTime();
        //rank 100 if spot on
        if(value == timeSlot){
            fitnessVal = 100;
            return fitnessVal;
        }
        //rank goes down by 10 every time the appointment is away from the request
        else if(timeSlot > value){
            int temp = timeSlot - value;
            int num = temp * 10;
            fitnessVal = 100 - num;
            return fitnessVal;
        }else if(value > timeSlot){
            int temp = value - timeSlot;
            int num = temp * 10;
            fitnessVal = 100 - num;
            return fitnessVal;
        }
        return fitnessVal;
    }
}
