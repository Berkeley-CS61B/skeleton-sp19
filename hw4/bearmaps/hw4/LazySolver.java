package bearmaps.hw4;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.List;

/**
 * Very basic syntatically correct but semantically incorrect shortest paths solver.
 * It tries the first edge it sees and if that edge doesn't work, it gives up
 * and (incorrectly) says the task is UNSOLVABLE.
 * Created by hug.
 */
public class LazySolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private SolverOutcome outcome;
    private double solutionWeight;
    private List<Vertex> solution;
    private double timeSpent;

    /* ignores timeout since algorithm is so fast. */
    public LazySolver(AStarGraph<Vertex> G, Vertex start, Vertex goal, double timeout) {
        Stopwatch sw = new Stopwatch();
        List<WeightedEdge<Vertex>> neighborEdges = G.neighbors(start);
        for (WeightedEdge<Vertex> e : neighborEdges) {
            if (e.to().equals(goal)) {
                solution = List.of(start, goal);
                solutionWeight = e.weight();
                outcome = SolverOutcome.SOLVED;
                timeSpent = sw.elapsedTime();
                return;
            }
        }
        outcome = SolverOutcome.UNSOLVABLE;
        timeSpent = sw.elapsedTime();
    }

    @Override
    public SolverOutcome outcome() {
        return outcome;
    }

    @Override
    public List<Vertex> solution() {
        return solution;
    }

    @Override
    public double solutionWeight() {
        return solutionWeight;
    }

    @Override
    public int numStatesExplored() {
        return 1;
    }

    @Override
    public double explorationTime() {
        return 0;
    }
}
