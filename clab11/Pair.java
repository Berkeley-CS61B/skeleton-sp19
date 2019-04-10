/**
 * Utility class. Useful for returning two objects. Immutable.
 */

public class Pair<T, S> {

    private T first;
    private S second;

    public Pair(T first, S second) {
        this.first = first;
        this.second = second;
    }

    public T first() {
        return this.first;
    }

    public S second() {
        return this.second;
    }
}
