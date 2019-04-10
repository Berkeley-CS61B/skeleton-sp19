/**
 * Represents a Bear with a defined size. Can only be compared to Bed objects.
 * DO NOT MODIFY THIS FILE.
 */
public class Bear extends HiddenComparable implements Comparable<Bed> {

    public Bear(int size) {
        super(size);
    }

    /**
     * Compares the size of this and the other Bed object.
     */
    public int compareTo(Bed other) {
        return Integer.compare(getSize(), other.getSize());
    }

    /**
     * String representation of a Bear. For debugging purposes only.
     */
    public String toString() {
        return "Bear(" + getSize() + ")";
    }
}
