package bearmaps.hw4;

/**
 * Utility class that represents a weighted edge.
 * Created by hug.
 */
public class WeightedEdge<Vertex> {
    private Vertex v;
    private Vertex w;
    private double weight;

    public WeightedEdge(Vertex v, Vertex w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }
    public Vertex from() {
        return v;
    }
    public Vertex to() {
        return w;
    }
    public double weight() {
        return weight;
    }
}
