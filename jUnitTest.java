import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Write a description of class jUnitTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class jUnitTest
{
    public RestRes trial;
    public ResManager<Table, Reservation> tableMgr;
    java.util.Iterator<Reservation> iterator;

    /**
     * Default constructor for test class LListTest
     */
    public jUnitTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        tableMgr = new ResManager<Table, Reservation>();
        trial = new RestRes(" ", 3, 4);

        iterator = tableMgr.getAllReservations();
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    //Test ResManager
    @Test
    public void testRestaurant(){
        //make the reservation using the information in setUp()
        tableMgr.makeReservation(trial);

        assertFalse(trial.getName().equals(" "));
        assertTrue(trial.getNumSeatsNeeded() == 4);
        assertTrue(trial.getReservationTime() == 3);

        while (iterator.hasNext()) {
            // to do this, you will need to add toString() methods to Reservations
            System.out.println(iterator.next());
        }
    }

}
