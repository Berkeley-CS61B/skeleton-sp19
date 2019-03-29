package bearmaps.hw4.lectureexample;

import bearmaps.hw4.AStarGraph;
import bearmaps.hw4.WeightedEdge;

import java.util.ArrayList;
import java.util.List;

/**
 * A very simple (and literal) example of an AStarGraph.
 * Created by hug.
 */
public class WeightedDirectedGraph implements AStarGraph<Integer> {
    /* Represents the list of edges from a single vertex. */
    private class EdgeList {
        private List<WeightedEdge<Integer>> list;
        private EdgeList() {
            list = new ArrayList<>();
        }
    }

    private EdgeList[] adj;

    public WeightedDirectedGraph(int V) {
        adj = new EdgeList[V];
        for (int i = 0; i < V; i += 1) {
            adj[i] = new EdgeList();
        }
    }

    @Override
    public List<WeightedEdge<Integer>> neighbors(Integer v) {
        return adj[v].list;
    }

    /* Very crude heuristic that just returns the weight
       of the smallest edge out of vertex s.
     */
    @Override
    public double estimatedDistanceToGoal(Integer s, Integer goal) {
        List<WeightedEdge<Integer>> edges = neighbors(s);
        double estimate = Double.POSITIVE_INFINITY;
        for (WeightedEdge<Integer> e : edges) {
            if (e.weight() < estimate) {
                estimate = e.weight();
            }
        }
        return estimate;
    }

    public void addEdge(int p, int q, double w) {
        WeightedEdge<Integer> e = new WeightedEdge<>(p, q, w);
        adj[p].list.add(e);
    }

}
