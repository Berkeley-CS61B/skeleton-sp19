/**
 * Represents a Bed with a defined size. Can only be compared to Bear objects.
 * DO NOT MODIFY THIS FILE.
 */
public class Bed extends HiddenComparable implements Comparable<Bear> {

    public Bed(int size) {
        super(size);
    }

    /**
     * Compares the size of this and the other Bear object.
     */
    public int compareTo(Bear other) {
        return Integer.compare(getSize(), other.getSize());
    }

    /**
     * String representation of a Bed. For debugging purposes only.
     */
    public String toString() {
        return "Bed(" + getSize() + ")";
    }
}
