package bearmaps.hw4.streetmap;

import bearmaps.hw4.AStarGraph;
import bearmaps.hw4.WeightedEdge;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class StreetMapGraph implements AStarGraph<Long> {
    private Map<Long, Node> nodes = new HashMap<>();
    private Map<Long, Set<WeightedEdge<Long>>> neighbors = new HashMap<>();


    /**
     * Private empty constructor. StreetMapGraphs should only be
     * instantiated by clients using static creation methods
     * readFromXML and readFromSimpleFormat.
     */
    private StreetMapGraph() {}

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
    public Set<Long> vertices() {
        Set<Long> vertices = new HashSet<>();
        for (long id : nodes.keySet()) {
            vertices.add(id);
        }

        return vertices;
    }

    /**
     * Writes this graph to the file at path FILENAME, in "simple-format";
     * creates file if it does not exist. The file is formatted as follows:
     * -The first line is the number of nodes N
     * -The next N lines represent nodes in the format (ID, LAT, LON)
     * -The final N lines represent neighbors in the format ID : ID1 ID2 ... IDN
     */
    public void writeToFile(String filename) {
        try {
            File file = new File(filename);
            FileOutputStream out = new FileOutputStream(file);
            out.write(getSimpleFormat().getBytes());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Factory method. Creates and returns a graph from an OSM XML
     * file. Assumes file is correctly formatted.
     */
    public static StreetMapGraph readFromXML(String filename) {
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

    /**
     * Factory method. Creates and returns a graph from the simple-format
     * file at path FILENAME; assumes file is correctly formatted.
     * See writeToFile for file format.
     */
    public static StreetMapGraph readFromSimpleFormat(String filename) {
        StreetMapGraph graph = new StreetMapGraph();
        try {
            File file = new File(filename);
            Scanner in = new Scanner(file);
            int n = Integer.parseInt(in.nextLine());
            for (int i = 0; i < n; ++i) {
                String nodeString = in.nextLine().replace("(", "");
                String[] tokens = nodeString.split("[,)]");
                Node node = Node.of(Long.parseLong(tokens[0]),
                                    Double.parseDouble(tokens[1]),
                                    Double.parseDouble(tokens[2]));
                graph.addNode(node);
            }

            for (int i = 0; i < n; ++i) {
                String neighborsString = in.nextLine();
                String[] tokens = neighborsString.split("[^\\[0-9\\]]+");
                long fromID = Long.parseLong(tokens[0]);
                for (int j = 1; j < tokens.length; ++j) {
                    graph.addWeightedEdge(fromID, Long.parseLong(tokens[j]));
                }
            }

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        graph.clean();
        return graph;
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
    void addWeightedEdge(long fromID, long toID) {
        if (nodes.containsKey(fromID) && nodes.containsKey(toID)) {
            Node from = nodes.get(fromID);
            Node to = nodes.get(toID);
            double weight = distance(from.lon(), to.lon(), from.lat(), to.lat());

            Set<WeightedEdge<Long>> edgeSet = neighbors.get(fromID);
            edgeSet.add(new WeightedEdge<Long>(from.id(), to.id(), weight));
        }
    }

    /**
     * Removes vertices with 0 out-degree from graph. Note that this will
     * cause issues if edges are not bidirectional.
     **/
    StreetMapGraph clean() {
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

        return this;
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
     * Returns the human-readable string format of this graph. See
     * writeToFile for format.
     */
    private String getSimpleFormat() {
        StringBuilder sb = new StringBuilder();
        sb.append(nodes.keySet().size() + "\n");
        for (long id : nodes.keySet()) {
            Node node = nodes.get(id);
            sb.append(String.format("(%d, %.10f, %.10f)%n", id, node.lat(), node.lon()));
        }

        for (long id : nodes.keySet()) {
            sb.append(String.format("%d : ", id));
            for (WeightedEdge<Long> e : neighbors(id)) {
                sb.append(e.to() + " ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    /*public static void main(String[] args) {
        StreetMapGraph smg = StreetMapGraph.readFromXML("berkeley-2018.osm.xml");
        smg.writeToFile("berkeley-street-data.txt");
    }*/
}
