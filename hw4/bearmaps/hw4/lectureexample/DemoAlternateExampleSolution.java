package bearmaps.hw4.lectureexample;

import bearmaps.hw4.LazySolver;
import bearmaps.hw4.ShortestPathsSolver;
import bearmaps.hw4.SolutionPrinter;


/**
 * Showcases how the AStarSolver can solve the example from the spec.
 * NOTE: YOU MUST REPLACE LazySolver WITH AStarSolver OR THIS DEMO WON'T WORK!
 * Created by hug.
 */
public class DemoAlternateExampleSolution {
    public static void main(String[] args) {
        WeightedDirectedGraph wdg = new WeightedDirectedGraph(6);
        /* Edges from vertex 0. */
        wdg.addEdge(0, 1, 50);
        wdg.addEdge(0, 2, 20);

        wdg.addEdge(1, 4, 20);

        wdg.addEdge(2, 3, 10);

        wdg.addEdge(3, 4, 70);

        wdg.addEdge(4, 3, 10);
        wdg.addEdge(4, 5, 100);


        int start = 0;
        int goal = 5;

        ShortestPathsSolver<Integer> solver = new LazySolver<>(wdg, start, goal, 10);
        SolutionPrinter.summarizeSolution(solver, " => ");
    }
}
