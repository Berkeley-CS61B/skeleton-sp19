package bearmaps.hw4.wordladderpuzzle;


import bearmaps.hw4.LazySolver;
import bearmaps.hw4.ShortestPathsSolver;
import bearmaps.hw4.SolutionPrinter;

/**
 * Showcases how the AStarSolver can be used for solving word ladders.
 * NOTE: YOU MUST REPLACE LazySolver WITH AStarSolver OR THIS DEMO WON'T WORK!
 * Created by hug.
 */
public class DemoWordPuzzleSolution {
    /***********************************************************************
     * Test routine for your Solver class. Uncomment and run to test
     * your basic functionality. Make sure to set your current working directory
     * to be the one containing words10000.txt.
     **********************************************************************/
    public static void main(String[] args) {
        String start = "horse";
        String goal = "nurse";

        WordGraph wg = new WordGraph();

        ShortestPathsSolver<String> solver = new LazySolver<>(wg, start, goal, 10);
        SolutionPrinter.summarizeSolution(solver, "->");
    }
}
