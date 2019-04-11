package bearmaps.hw4.streetmap;

import bearmaps.hw4.AStarGraph;
import bearmaps.hw4.WeightedEdge;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class StreetMapGraph implements AStarGraph<Long> {
    private Map<Long, Node> nodes = new HashMap<>();
    private Map<Long, Set<WeightedEdge<Long>>> neighbors = new HashMap<>();

    private StreetMapGraph() {
    }

    public StreetMapGraph(String filename) {
        StreetMapGraph smg = StreetMapGraph.readFromXML(filename);
        this.nodes = smg.nodes;
        this.neighbors = smg.neighbors;
    }

    /**
     * Returns a list of outgoing edges for V. Assumes V exists in this
     * graph.
     **/
    @Override
    public List<WeightedEdge<Long>> neighbors(Long v) {
        Set<WeightedEdge<Long>> incidentSet = neighbors.get(v);
        List<WeightedEdge<Long>> incidentList = new ArrayList<>();
        for (WeightedEdge<Long> e : incidentSet) {
            incidentList.add(e);
        }

        return incidentList;
    }

    /**
     * Returns the great-circle distance between S and GOAL. Assumes
     * S and GOAL exist in this graph.
     */
    @Override
    public double estimatedDistanceToGoal(Long s, Long goal) {
        Node sNode = nodes.get(s);
        Node goalNode = nodes.get(goal);
        return distance(sNode.lon(), goalNode.lon(), sNode.lat(), goalNode.lat());
    }

    /**
     * Returns a set of my vertices. Altering this set does not alter this
     * graph.
     **/
    private Set<Long> vertices() {
        Set<Long> vertices = new HashSet<>();
        for (long id : nodes.keySet()) {
            vertices.add(id);
        }

        return vertices;
    }

    /**
     * Factory method. Creates and returns a graph from an OSM XML
     * file. Assumes file is correctly formatted.
     */
    private static StreetMapGraph readFromXML(String filename) {
        StreetMapGraph smg = new StreetMapGraph();
        try {
            File inputFile = new File(filename);
            FileInputStream inputStream = new FileInputStream(inputFile);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            GraphBuildingHandler gbh = new GraphBuildingHandler(smg);
            saxParser.parse(inputStream, gbh);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        smg.clean();
        return smg;
    }

    /** Adds a node to this graph, if it doesn't yet exist. **/
    void addNode(Node node) {
        if (!nodes.containsKey(node.id())) {
            nodes.put(node.id(), node);
            neighbors.put(node.id(), new HashSet<>());
        }
    }

    /** Adds an edge to this graph, if FROMID and TOID are in this graph. Does
     *  not add additional edge if edge already exists.
     **/
    void addWeightedEdge(long fromID, long toID, String name) {
        if (nodes.containsKey(fromID) && nodes.containsKey(toID)) {
            Node from = nodes.get(fromID);
            Node to = nodes.get(toID);
            double weight = distance(from.lon(), to.lon(), from.lat(), to.lat());

            Set<WeightedEdge<Long>> edgeSet = neighbors.get(fromID);
            WeightedEdge<Long> weightedEdge = new WeightedEdge<>(from.id(), to.id(), weight);
            weightedEdge.setName(name);
            edgeSet.add(weightedEdge);
        }
    }

    /**
     * Removes vertices with 0 out-degree from graph. Note that this will
     * cause issues if edges are not bidirectional.
     **/
    private void clean() {
        List<Long> toRemove = new ArrayList<>();
        for (long id : nodes.keySet()) {
            if (neighbors(id).size() == 0) {
                toRemove.add(id);
            }
        }

        for (long id : toRemove) {
            nodes.remove(id);
            neighbors.remove(id);
        }
    }

    /**
     * We don't override hashCode(), so hash at your peril!
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        StreetMapGraph otherGraph = (StreetMapGraph) o;
        Set<Long> myVertices = vertices(), otherGraphVertices = otherGraph.vertices();

        if (myVertices.size() != otherGraphVertices.size()) {
            return false;
        }

        for (long id : myVertices) {
            if (!otherGraphVertices.contains(id)) {
                return false;
            }

            List<WeightedEdge<Long>> myNeighbors = neighbors(id);
            List<WeightedEdge<Long>> otherGraphNeighbors = otherGraph.neighbors(id);

            if (myNeighbors.size() != otherGraphNeighbors.size()) {
                return false;
            }

            Set<Long> neighborsSet = new HashSet<>();
            for (WeightedEdge<Long> e : myNeighbors) {
                neighborsSet.add(e.to());
            }

            int initialSize = neighborsSet.size();

            for (WeightedEdge<Long> e : otherGraphNeighbors) {
                neighborsSet.add(e.to());
                if (neighborsSet.size() != initialSize) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Returns the great-circle (haversine) distance between geographic coordinates
     * (LATV, LONV) and (LATW, LONW).
     *
     * @source Kevin Lowe & Antares Chen, and https://www.movable-type.co.uk/scripts/latlong.html
     **/
    private double distance(double lonV, double lonW, double latV, double latW) {
        double phi1 = Math.toRadians(latV);
        double phi2 = Math.toRadians(latW);
        double dphi = Math.toRadians(latW - latV);
        double dlambda = Math.toRadians(lonW - lonV);

        double a = Math.sin(dphi / 2.0) * Math.sin(dphi / 2.0);
        a += Math.cos(phi1) * Math.cos(phi2) * Math.sin(dlambda / 2.0) * Math.sin(dlambda / 2.0);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return 3963 * c;
    }

    /**
     * Gets the longitude of a vertex.
     * @param v The id of the vertex.
     * @return The longitude of the vertex.
     */
    public double lon(long v) {
        if (!nodes.containsKey(v)) {
            return 0.0;
        }
        return nodes.get(v).lon();
    }

    /**
     * Gets the latitude of a vertex.
     * @param v The id of the vertex.
     * @return The latitude of the vertex.
     */
    public double lat(long v) {
        if (!nodes.containsKey(v)) {
            return 0.0;
        }
        return nodes.get(v).lat();
    }

    protected List<Node> getNodes() {
        List<Node> nodes = new LinkedList<>();
        for(Map.Entry<Long, Node> nodeEntry: this.nodes.entrySet()){
            nodes.add(nodeEntry.getValue());
        }
        return nodes;
    }
}
