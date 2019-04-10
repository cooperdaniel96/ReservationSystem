/*
 *  CSS162 Final Homework - Reservation System
 *  
 *  Skeleton Driver for Restaurant Reservation System.
 *  Fill in missing section indicated below.
 *  
 *  @author Daniel Cooper
 */
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.*;

public class RestaurantDriver {
    static ResManager<Table, Reservation> tableMgr = new ResManager<Table, Reservation>();
    /**
     * Main method for the driver
     * This method will create a reservation by taking in user input.
     * The user will put down their name, the number of people in their party, and their requested time slot.
     * The ResManager class will handle creating the reservation and checking to see if the reservation can be created.
     * 
     * @param String[] args
     * @return NONE
     */
    public static void main(String[] args) { 
        readItems("tableFile.txt");
        // You can start by testing without the user input 
        //testWithoutUI(); 
        Scanner keybd = null;
        try {
            keybd = new Scanner(System.in);
            while (true) { 
                System.out.println("Enter your name, number of people in the party, and requested time slot. Type 'end' to finish:");
                // Read data from keyboard 
                String name = keybd.next();
                if(name.equals("end")){
                    return;
                }
                int numPeople = keybd.nextInt();
                int timeSlot = keybd.nextInt();
                // Create trial reservation 
                RestRes res = new RestRes(name, timeSlot, numPeople);
                // use tableMgr to make reservation 
                if(!res.isActive()){
                    tableMgr.makeReservation(res);
                }else{
                    System.out.println("Cannot make Reservation");
                }
                // Write to console with reservation info.
                //Can do that here or in makeReservation
            } 
        } catch(Exception e) {
            System.out.println("Error, reservation input is not valid");
        } finally {
            keybd.close();
        }
    }

    public static void readItems(String filename) {
        try {
            Scanner stream = new Scanner(new FileInputStream(filename));
            while (stream.hasNext()) {
                // make a new Table
                Table table = new Table(stream);
                // add the table to the reservables of the tableMgr.
                tableMgr.addReservable(table);
                // for debugging, may print info
                //System.out.println(table.getId() + " " + table.getNumSeats());
            }
            stream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // As an intermediate step in development, you can test here without the user input
    public static void testWithoutUI() {
        RestRes trial = new RestRes("Rodriguez", 3, 4);
        tableMgr.makeReservation(trial);
        trial = new RestRes("Chan", 3, 6);
        tableMgr.makeReservation(trial);
        trial = new RestRes("Smith", 3, 2);
        tableMgr.makeReservation(trial);
        trial = new RestRes("Nguyen", 3, 4);
        tableMgr.makeReservation(trial);
        trial = new RestRes("Shah", 2, 8);
        tableMgr.makeReservation(trial);
        //Patel and David should not be able to make Reservations
        trial = new RestRes("Patel", 2, 25);
        tableMgr.makeReservation(trial);
        trial = new RestRes("David", 25, 1);
        tableMgr.makeReservation(trial);

        java.util.Iterator<Reservation> iterator = tableMgr.getAllReservations();
        while (iterator.hasNext()) {
            // to do this, you will need to add toString() methods to Reservations
            System.out.println(iterator.next());
        }
    }

}
