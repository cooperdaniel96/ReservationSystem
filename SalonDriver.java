import java.io.FileInputStream;
import java.util.*;

/**
 *  
 * @author Daniel Cooper
 * @version 12/13/2016
 */
public class SalonDriver
{
    static ResManager<HairDresser, Reservation> AppoitmentMgr = new ResManager<HairDresser, Reservation>();
    
    /**
     * Main method that will be similar to the RestaurantDriver  class main method.
     * This method will prompt the user to enter the name and requested time slot.
     * Then it will create a reservation using that info.
     * Then it will prompt the user to enter a list of hairdressers and return the hairdresser that matches the user
     * requested time slot.
     * 
     * @param String[] args
     * @return NONE
     */
    public static void main(String[] args) {
        readItems("salonFile.txt");

        Scanner keybd = null;
        try {

            keybd = new Scanner(System.in);
            while (true) {
                System.out.println("Enter your name and requested time slot.  Enter ‘end’ when finished:");

                // Read data from keyboard
                // Write to console with reservation info.  Can do that here or in makeReservation
                String name = keybd.next();
                if(name.equals("end")){
                    return;
                }
                int timeSlot = keybd.nextInt();
                //create a new appointment
                Appointment res = new Appointment(name,timeSlot);
                System.out.println("Enter a list of hairdresser names:");
                //store the names in a string
                String[] names = keybd.nextLine().split(" ");
                
                //using a for each loop to add the hairdresser in the string above
                for(String a : names){
                    res.addHairdresser(a);
                }
                //make reservation here
                AppoitmentMgr.makeReservation(res);
            }            

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            keybd.close();
        }

    }

    //similar to readItems in the other driver
    /**
     * This method will be similar to the readItems method in the RestaurantDriver class
     * It will take in a file and extract the data from that file
     * 
     * @param String fileName
     * @return NONE
     */
    public static void readItems(String filename) {
        try {
            Scanner stream = new Scanner(new FileInputStream(filename));
            while (stream.hasNext()) {
                //extract the data from the text file
                HairDresser names = new HairDresser(stream);
                //add those names to the list of reservables
                AppoitmentMgr.addReservable(names);
            }
            stream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
