package bearmaps.hw4;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Another dummy solver. Do not distribute to students. A little worried they'll
 * try to bend this into being an AStarSolver.
 * Created by hug.
 */
public class RandomGuessSolver<Vertex> implements ShortestPathsSolver<Vertex> {

    List<Vertex> solution = new ArrayList<>();
    double totalWeight = 0;
    double timeElapsed = 0;
    int numExplored = 0;
    SolverOutcome outcome;


    public RandomGuessSolver(AStarGraph<Vertex> G, Vertex start, Vertex end, double timeout) {
        Random r = new Random();
        solution.add(start);

        Vertex current = start;
        Stopwatch sw = new Stopwatch();

        while (sw.elapsedTime() < timeout) {
            numExplored += 1;
            List<WeightedEdge<Vertex>> neighbors = G.neighbors(current);
            if (neighbors.size() == 0) {
                outcome = SolverOutcome.UNSOLVABLE;
            }
            int randomChoice = r.nextInt(neighbors.size());
            WeightedEdge<Vertex> e = neighbors.get(randomChoice);
            current = e.to();
            solution.add(current);
            totalWeight += e.weight();
            if (current.equals(end)) {
                outcome = SolverOutcome.SOLVED;
                timeElapsed = sw.elapsedTime();
                return;
            }
        }
        timeElapsed = sw.elapsedTime();
        outcome = SolverOutcome.TIMEOUT;
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
        return totalWeight;
    }

    @Override
    public int numStatesExplored() {
        return 0;
    }

    @Override
    public double explorationTime() {
        return timeElapsed;
    }
}
