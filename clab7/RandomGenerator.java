import java.util.Random;

/**
 * Created by hug.
 */
public class RandomGenerator {
    /* Change this number to get different randomness.*/
    private final static long SEED = 1;

    private static Random r = new Random(SEED);

    /** Returns a random integer between 0 and max. */
    public static int getRandomInt(int max) {
        return r.nextInt(max);
    }

    /** Returns a random boolean. */
    public static boolean getRandomBoolean() {
        return r.nextBoolean();
    }
}
