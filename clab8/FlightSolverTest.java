import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class FlightSolverTest {

    /**
     * Constructs an ArrayList of Flights where the ith flight has the ith start time in
     * startTimes, the ith end time in endTimes, and the ith count in passengerCounts.
     * Arguments must have the same length.
     */
    ArrayList<Flight> makeFlights(int[] startTimes, int[] endTimes, int[] passengerCounts) {
        ArrayList<Flight> flights = new ArrayList<>();
        for (int i = 0; i < startTimes.length; i++) {
            flights.add(new Flight(startTimes[i], endTimes[i], passengerCounts[i]));
        }
        return flights;
    }

    /**
     * Test with no overlapping flights.
     */
    @Test
    public void naiveTest() {
        int[] startTimes = {10, 20, 30, 40};
        int[] endTimes = {19, 29, 39, 49};
        int[] passengerCounts = {1, 2, 3, 4};
        FlightSolver solver = new FlightSolver(makeFlights(startTimes, endTimes, passengerCounts));
        assertEquals(4, solver.solve());
    }

    /**
     * Test with one overlapping flight.
     */
    @Test
    public void smallTest() {
        int[] startTimes = {10, 20, 40};
        int[] endTimes = {25, 35, 49};
        int[] passengerCounts = {1, 4, 3};
        FlightSolver solver = new FlightSolver(makeFlights(startTimes, endTimes, passengerCounts));
        assertEquals(5, solver.solve());
    }

    /**
     * Test with some overlapping flights.
     */
    @Test
    public void mediumTest() {
        int[] startTimes = {15, 23, 18, 42, 55, 75, 78};
        int[] endTimes = {27, 45, 44, 65, 90, 95, 85};
        int[] passengerCounts = {20, 30, 10, 25, 5, 10, 15};
        FlightSolver solver = new FlightSolver(makeFlights(startTimes, endTimes, passengerCounts));
        assertEquals(65, solver.solve());
    }

}
