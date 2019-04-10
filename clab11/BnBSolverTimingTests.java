import edu.princeton.cs.algs4.Stopwatch;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Visualizes how your solver compares to methods with different asymptotic performance, so you can gauge the
 * runtime of your solver. Selection sort is expected Theta(N^2) and Collections.sort is expected Theta(N log N).
 */
public class BnBSolverTimingTests {

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

    private static List<Integer> randomIntegers(int size) {
        List<Integer> ints = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ints.add(i);
        }
        Collections.shuffle(ints);
        return ints;
    }

    /**
     * Performs selection sort on items. Is destructive.
     */
    private static List<Integer> selectionSort(List<Integer> items) {
        items = new ArrayList<>(items);
        List<Integer> sorted = new LinkedList<>();
        while (!items.isEmpty()) {
            int min = Integer.MIN_VALUE;
            int minIndex = 0;
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i) < min) {
                    min = items.get(i);
                    minIndex = i;
                }
            }
            sorted.add(min);
            items.remove(minIndex);
        }
        return sorted;
    }

    /**
     * Compares runtime of your solution vs. runtime to sort 2 lists of Integers using Collections.sort
     * and selection sort for varying N where N is the number of bears/beds/integers.
     * Plots times for N = 1, 2, 4, 8,... 2^power
     */
    public static void plotTimes(int power) {
        List<Integer> numItems = new ArrayList<>();
        List<Double> timeToSortYourSolution = new ArrayList<>();
        List<Double> timeToSortOptimal = new ArrayList<>();
        List<Double> timeToSelectionSort = new ArrayList<>();

        for (int i = 1; i <= Math.pow(2, power); i *= 2) {
            Pair<List<Bear>, List<Bed>> bearsAndBeds = randomBearsAndBeds(i);
            Stopwatch sw = new Stopwatch();
            BnBSolver solver = new BnBSolver(bearsAndBeds.first(), bearsAndBeds.second());
            solver.solvedBears();
            solver.solvedBeds();
            timeToSortYourSolution.add(sw.elapsedTime());

            List<Integer> intBears = randomIntegers(i);
            List<Integer> intBeds = randomIntegers(i);
            sw = new Stopwatch();
            Collections.sort(intBears);
            Collections.sort(intBeds);
            timeToSortOptimal.add(sw.elapsedTime());

            intBears = randomIntegers(i);
            intBeds = randomIntegers(i);
            sw = new Stopwatch();
            selectionSort(intBears);
            selectionSort(intBeds);
            timeToSelectionSort.add(sw.elapsedTime());

            numItems.add(i);
        }

        XYChart chart =
                new XYChartBuilder().width(800).height(600).xAxisTitle("Input size").yAxisTitle("Time").build();
        chart.addSeries("Your BnBSolver", numItems, timeToSortYourSolution);
        chart.addSeries("Collections.sort", numItems, timeToSortOptimal);
        chart.addSeries("Selection Sort", numItems, timeToSelectionSort);
        new SwingWrapper(chart).displayChart();
    }

    public static void main(String[] args) {
        plotTimes(16);
    }
}
