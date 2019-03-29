package bearmaps.hw4;


import java.util.ArrayList;
import java.util.List;

/**
 * Handy utility class for printing solutions.
 * Created by hug.
 */
public class SolutionPrinter {
    /** Summarizes the result of the search made by this solver without actually
     *  printing the solution itself (if any).
     */
    public static <Vertex> void summarizeOutcome(ShortestPathsSolver<Vertex> solver) {
        summarizeSolution(solver, "", false);
    }

    /** Summarizes the result of the search made by this solver and also
     *  prints each vertex of the solution, connected by the given delimiter,
     *  e.g. delimiter = "," would return all states separated by commas.
     */
    public static <Vertex> void summarizeSolution(ShortestPathsSolver<Vertex> solver,
                                                  String delimiter) {
        summarizeSolution(solver, delimiter, true);
    }

    private static <Vertex> String solutionString(ShortestPathsSolver<Vertex> solver,
                                                  String delimiter) {
        List<String> solutionVertices = new ArrayList<>();
        for (Vertex v : solver.solution()) {
            solutionVertices.add(v.toString());
        }
        return String.join(delimiter, solutionVertices);
    }

    private static <Vertex> void summarizeSolution(ShortestPathsSolver<Vertex> solver,
                                                   String delimiter, boolean printSolution) {

        System.out.println("Total states explored in " + solver.explorationTime()
                            + "s: " + solver.numStatesExplored());

        if (solver.outcome() == SolverOutcome.SOLVED) {
            List<Vertex> solution = solver.solution();
            System.out.println("Search was successful.");
            System.out.println("Solution was of length " + solution.size()
                               + ", and had total weight " + solver.solutionWeight() + ":");
            if (printSolution) {
                System.out.println(solutionString(solver, delimiter));
            }
        } else if (solver.outcome() == SolverOutcome.TIMEOUT) {
            System.out.println("Search timed out, considered " + solver.numStatesExplored()
                                + " vertices before timing out.");
        } else { // (solver.outcome() == SolverOutcome.UNSOLVABLE)
            System.out.println("Search determined that the goal is unreachable from source.");
        }
    }
}
