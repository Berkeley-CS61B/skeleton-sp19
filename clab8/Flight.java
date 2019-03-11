/**
 * Represents a flight in the Flight problem.
 */
public class Flight {

    int startTime;
    int endTime;
    int passengers;

    public Flight(int start, int end, int numPassengers) {
        startTime = start;
        endTime = end;
        passengers = numPassengers;
    }

    public int startTime() {
        return startTime;
    }

    public int endTime() {
        return endTime;
    }

    public int passengers() {
        return passengers;
    }
}
