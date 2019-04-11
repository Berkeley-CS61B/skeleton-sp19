package bearmaps.proj2c.utils;

/**
 * A utility class to hold objects of two different types
 *
 * Created by rahul
 */
public class Tuple<First, Second> {

    private First first;

    private Second second;

    public Tuple(First first, Second second) {
        this.first = first;
        this.second = second;
    }

    public First getFirst() {
        return first;
    }

    public void setFirst(First first) {
        this.first = first;
    }

    public Second getSecond() {
        return second;
    }

    public void setSecond(Second second) {
        this.second = second;
    }
}
