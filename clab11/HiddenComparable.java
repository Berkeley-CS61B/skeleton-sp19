import java.util.List;

/**
 * Bear and Bed extend this class. Represents an object with a hidden size attribute that can only be accessed by
 * this class and its subclasses.
 * Note: This class isn't similar to something you'd normally code in real life (unless you have a very specific
 * purpose), but is necessary for the constraints of the problem.
 * DO NOT MODIFY THIS FILE.
 */
abstract class HiddenComparable {

    private int size;

    protected HiddenComparable(int size) {
        this.size = size;
    }

    protected int getSize() {
        return this.size;
    }

}
