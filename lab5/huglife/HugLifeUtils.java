/**
 * Utilities for lab 5
 *
 * @author Josh Hug
 */
package huglife;

import java.util.Deque;
import java.util.Random;
import java.util.List;
import java.util.Stack;


public class HugLifeUtils {
    private static Random r = null;

    /** Returns a random number uniformly between 0 and 1 */
    public static double random() {
        if (r == null)
            r = new Random();

        return r.nextDouble();
    }

    /** Returns a random number uniformly between min and max inclusive
     Stolen from: http://stackoverflow.com/questions/363681 */
    public static int randomInt(int min, int max) {
        if (r == null)
            r = new Random();

        return r.nextInt((max - min) + 1) + min;
    }

    /** Returns a random number uniformly between 0 and max */
    public static int randomInt(int max) {
        return randomInt(0, max);
    }

    /** Returns a random Direction uniformly chosen from L */
    public static Direction randomEntry(Deque<Direction> L) {
        // In Sp19 we use a Deque instead of a List because students haven't seen types of Lists yet.

        // Choose an index.
        int dirIndex = randomInt(L.size() - 1);

        // Take the first dirIndex items off the Deque and stash them in popped.
        Stack<Direction> popped = new Stack<>();
        for (int i = 0; i < dirIndex; i++) {
            popped.push(L.removeFirst());
        }

        // Grab the desired item.
        Direction result = L.peekFirst();

        // Put everything back into the Deque before returning.
        for (int i = 0; i < dirIndex; i++) {
            L.addFirst(popped.pop());
        }

        return result;
    }
}
