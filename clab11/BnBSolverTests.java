import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Tests for your BnBSolver class.
 */
public class BnBSolverTests {

    /**
     * Helper for tests. Checks two lists to see if the ith Bear is the same size as the ith Bed for i in [0, length
     * of list].
     */
    private static boolean perfectMatch(List<Bear> bears, List<Bed> beds) {
        if (bears.size() != beds.size()) {
            return false;
        }
        for (int i = 0; i < bears.size(); i++) {
            if (bears.get(i).compareTo(beds.get(i)) != 0) {
                return false;
            }
        }
        return true;
    }

    private static Pair<List<Bear>, List<Bed>> randomBearsAndBeds(int size) {
        ArrayList<Bear> bears = new ArrayList<>();
        ArrayList<Bed> beds = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            bears.add(new Bear(i));
            beds.add(new Bed(i));
        }
        Collections.shuffle(bears);
        Collections.shuffle(beds);
        return new Pair(bears, beds);
    }

    @Test
    public void reverseOrder() {
        ArrayList<Bear> bears = new ArrayList<>();
        ArrayList<Bed> beds = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            bears.add(new Bear(i));
            beds.add(new Bed(9 - i));
        }
        BnBSolver solver = new BnBSolver(bears, beds);
        assertTrue(perfectMatch(solver.solvedBears(), solver.solvedBeds()));
    }

    @Test
    public void simpleTest() {
        int[] bearSizes = {10, 50, 40, 30, 90, 20, 80, 70};
        int[] bedSizes = {70, 90, 20, 40, 50, 10, 30, 80};
        ArrayList<Bear> bears = new ArrayList<>();
        ArrayList<Bed> beds = new ArrayList<>();
        for (int i = 0; i < bearSizes.length; i++) {
            bears.add(new Bear(bearSizes[i]));
            beds.add(new Bed(bedSizes[i]));
        }
        BnBSolver solver = new BnBSolver(bears, beds);
        assertTrue(perfectMatch(solver.solvedBears(), solver.solvedBeds()));
    }

    @Test
    public void randomBearsAndBeds10() {
        Pair<List<Bear>, List<Bed>> bnb = randomBearsAndBeds(10);
        BnBSolver solver = new BnBSolver(bnb.first(), bnb.second());
        assertTrue(perfectMatch(solver.solvedBears(), solver.solvedBeds()));
    }

    @Test
    public void randomBearsAndBeds50() {
        Pair<List<Bear>, List<Bed>> bnb = randomBearsAndBeds(50);
        BnBSolver solver = new BnBSolver(bnb.first(), bnb.second());
        assertTrue(perfectMatch(solver.solvedBears(), solver.solvedBeds()));
    }

    @Test
    public void randomBearsAndBeds100() {
        Pair<List<Bear>, List<Bed>> bnb = randomBearsAndBeds(100);
        BnBSolver solver = new BnBSolver(bnb.first(), bnb.second());
        assertTrue(perfectMatch(solver.solvedBears(), solver.solvedBeds()));
    }

    @Test
    public void randomBearsAndBeds100x10() {
        for (int i = 0; i < 10; i++) {
            Pair<List<Bear>, List<Bed>> bnb = randomBearsAndBeds(100);
            BnBSolver solver = new BnBSolver(bnb.first(), bnb.second());
            assertTrue(perfectMatch(solver.solvedBears(), solver.solvedBeds()));
        }
    }

}
