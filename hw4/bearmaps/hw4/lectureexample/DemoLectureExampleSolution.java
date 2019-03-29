package bearmaps.hw4.lectureexample;

import bearmaps.hw4.LazySolver;
import bearmaps.hw4.ShortestPathsSolver;
import bearmaps.hw4.SolutionPrinter;

/**
 * Showcases how the AStarSolver can solve the example from lecture.
 * NOTE: YOU MUST REPLACE LazySolver WITH AStarSolver OR THIS DEMO WON'T WORK!
 * Created by hug.
 */
public class DemoLectureExampleSolution {
    public static void main(String[] args) {
        WeightedDirectedGraph wdg = new WeightedDirectedGraph(7);
        /* Edges from vertex 0. */
        wdg.addEdge(0, 1, 2);
        wdg.addEdge(0, 2, 1);

        wdg.addEdge(1, 2, 5);
        wdg.addEdge(1, 3, 11);
        wdg.addEdge(1, 4, 3);

        wdg.addEdge(2, 5, 15);

        wdg.addEdge(3, 4, 2);

        wdg.addEdge(4, 2, 1);
        wdg.addEdge(4, 5, 4);
        wdg.addEdge(4, 6, 5);

        wdg.addEdge(6, 3, 1);
        wdg.addEdge(6, 5, 1);

        int start = 0;
        int goal = 6;

        ShortestPathsSolver<Integer> solver = new LazySolver<>(wdg, start, goal, 10);
        SolutionPrinter.summarizeSolution(solver, " => ");
    }
}
