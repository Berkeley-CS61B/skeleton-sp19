package bearmaps.proj2c.server.handler.impl;

import bearmaps.proj2c.server.handler.APIRouteHandler;
import spark.Request;
import spark.Response;
import bearmaps.proj2c.utils.Tuple;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static bearmaps.proj2c.utils.Constants.SEMANTIC_STREET_GRAPH;

/**
 * Created by rahul
 */
public class SearchAPIHandler extends APIRouteHandler<Tuple<Set<String>, String>, Object> {


    @Override
    protected Tuple<Set<String>, String> parseRequestParams(Request request) {
        return new Tuple<>(request.queryParams(), request.queryParams("term"));
    }

    @Override
    protected Object processRequest(Tuple<Set<String>, String> requestParams, Response response) {
        Set<String> reqParams = requestParams.getFirst();
        String term = requestParams.getSecond();
        Object result;
        /* Search for actual location data. */
        if (reqParams.contains("full")) {
            result = getLocations(term);
        } else {
            /* Search for prefix matching strings. */
            result = getLocationsByPrefix(term);
        }
        return result;
    }

    /**
     * In linear time, collect all the names of OSM locations that prefix-match the query string.
     * @param prefix Prefix string to be searched for. Could be any case, with our without
     *               punctuation.
     * @return A <code>List</code> of the full names of locations whose cleaned name matches the
     * cleaned <code>prefix</code>.
     */
    public List<String> getLocationsByPrefix(String prefix) {

        return SEMANTIC_STREET_GRAPH.getLocationsByPrefix(prefix);
    }

    /**
     * Collect all locations that match a cleaned <code>locationName</code>, and return
     * information about each node that matches.
     * @param locationName A full name of a location searched for.
     * @return A list of locations whose cleaned name matches the
     * cleaned <code>locationName</code>, and each location is a map of parameters for the Json
     * response as specified: <br>
     * "lat" : Number, The latitude of the node. <br>
     * "lon" : Number, The longitude of the node. <br>
     * "name" : String, The actual name of the node. <br>
     * "id" : Number, The id of the node. <br>
     */
    public List<Map<String, Object>> getLocations(String locationName) {

        return SEMANTIC_STREET_GRAPH.getLocations(locationName);
    }
}
