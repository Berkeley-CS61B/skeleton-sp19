package bearmaps.test;

import bearmaps.proj2c.AugmentedStreetMapGraph;
import org.junit.Before;
import org.junit.Test;
import bearmaps.proj2c.Router;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/** Test of the written driving directions part of the assignment. This part of the assignment is 
  * optional and not worth any credit, and these tests should be thought of as just for fun.*/
public class TestDirections {
    private static final String PATHS_FILE = "../library-sp19/data/proj2c_test_inputs/path_results.txt";
    private static final String RESULTS_FILE = "../library-sp19/data/proj2c_test_inputs/directions_results.txt";
    private static final int NUM_TESTS = 8;
    private static final String OSM_DB_PATH = "../library-sp19/data/proj2c_xml/berkeley-2019.osm.xml";
    private static AugmentedStreetMapGraph graph;

    @Before
    public void setUp() throws Exception {
        graph = new AugmentedStreetMapGraph(OSM_DB_PATH);
    }

    @Test
    public void testRouteDirections() throws Exception {
        List<List<Long>> paths = pathsFromFile();
        List<List<Router.NavigationDirection>> expectedResults = resultsFromFile();

        for (int i = 0; i < NUM_TESTS; i++) {
            System.out.println(String.format("Running test: %d", i));
            List<Long> path = paths.get(i);
            List<Router.NavigationDirection> actual = Router.routeDirections(graph, path);
            List<Router.NavigationDirection> expected = expectedResults.get(i);
            assertEquals("The directions lengths are not equal", expected.size(), actual.size());
            for (int j = 0; j < actual.size(); j++) {
                Router.NavigationDirection actualDir = actual.get(j);
                Router.NavigationDirection expectedDir = expected.get(j);
                assertEquals("Directions did not match", expectedDir.toString(),
                        actualDir.toString());
            }
        }
    }

    private List<List<Long>> pathsFromFile() throws Exception {
        List<String> lines = Files.readAllLines(Paths.get(PATHS_FILE), Charset.defaultCharset());
        List<List<Long>> paths = new ArrayList<>();
        int lineIdx = 2; // ignore comment lines
        for (int i = 0; i < NUM_TESTS; i++) {
            int numVertices = Integer.parseInt(lines.get(lineIdx));
            lineIdx++;
            List<Long> path = new ArrayList<>();
            for (int j = 0; j < numVertices; j++) {
                path.add(Long.parseLong(lines.get(lineIdx)));
                lineIdx++;
            }
            paths.add(path);
        }
        return paths;
    }

    private List<List<Router.NavigationDirection>> resultsFromFile() throws Exception {
        List<String> lines = Files.readAllLines(Paths.get(RESULTS_FILE), Charset.defaultCharset());
        List<List<Router.NavigationDirection>> expected = new ArrayList<>();
        int lineIdx = 2; // ignore comment lines
        for (int i = 0; i < NUM_TESTS; i++) {
            int numDirections = Integer.parseInt(lines.get(lineIdx));
            lineIdx++;
            List<Router.NavigationDirection> directions = new ArrayList<>();
            for (int j = 0; j < numDirections; j++) {
                directions.add(Router.NavigationDirection.fromString(lines.get(lineIdx)));
                lineIdx++;
            }
            expected.add(directions);
        }
        return expected;
    }
}
