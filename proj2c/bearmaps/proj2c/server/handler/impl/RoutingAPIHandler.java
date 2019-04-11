package bearmaps.proj2c.server.handler.impl;

import bearmaps.proj2c.Router;
import bearmaps.proj2c.server.handler.APIRouteHandler;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static bearmaps.proj2c.utils.Constants.SEMANTIC_STREET_GRAPH;
import static bearmaps.proj2c.utils.Constants.ROUTE_LIST;

/**
 * Handles requests from the web browser for routes between locations. The
 * route will be returned as image data, as well as (optionally) driving directions. *
 * Created by rahul
 */
public class RoutingAPIHandler extends APIRouteHandler<Map<String, Double>, Map<String, Object>> {

    /**
     * Each route request to the server will have the following parameters
     * as keys in the params map.<br>
     * start_lat : start point latitude,<br> start_lon : start point longitude,<br>
     * end_lat : end point latitude, <br>end_lon : end point longitude.
     **/
    private static final String[] REQUIRED_ROUTE_REQUEST_PARAMS = {"start_lat", "start_lon",
            "end_lat", "end_lon"};

    @Override
    protected Map<String, Double> parseRequestParams(Request request) {
        return getRequestParams(request, REQUIRED_ROUTE_REQUEST_PARAMS);
    }

    /**
     * Takes a user query in the form of a pair of (lat/lon) values, and finds
     * street directions between the given points. THis method has been
     * completed for you.
     *
     * The route to draw on the map should be added to the end of
     * bearmaps.proj2c.utils.Constants.ROUTE_LIST. This is a LinkedList of longs,
     * where each long corresponds to one point on the map.
     *
     * Street directions can also be provided in the form of text as a return
     * value to this function.
     *
     * @param requestParams Map of the HTTP GET request's query parameters - the starting lat/long
     *                      and the destination lat/lon.
     *
     * @param response : Not used by this function. You may ignore.
     * @return A map of results for the front end as specified: <br>
     * "routing_success" : Boolean, whether the route list should be drawn (i.e. if not empty).
     *
     * "directions_success" : Boolean, whether the query generated text directions (i.e. if
     *                        length of directions is > 0).
     * "directions"      : String. The text directions you want to display, in HTML format.
     */
    @Override
    protected Map<String, Object> processRequest(Map<String, Double> requestParams, Response response) {
        List<Long> route = Router.shortestPath(
                SEMANTIC_STREET_GRAPH,
                requestParams.get("start_lon"), requestParams.get("start_lat"),
                requestParams.get("end_lon"), requestParams.get("end_lat"));
        ROUTE_LIST.addAll(route);
        String directions = getDirectionsText();

        Map<String, Object> routeParams = new HashMap<>();
        routeParams.put("routing_success", !route.isEmpty());
        routeParams.put("directions_success", directions.length() > 0);
        routeParams.put("directions", directions);
        return routeParams;
    }

    /**
     * Takes the route of this MapServer and converts it into an HTML friendly
     * String to be passed to the frontend.
     */
    private String getDirectionsText() {

        List<Router.NavigationDirection> directions = Router.routeDirections(SEMANTIC_STREET_GRAPH, ROUTE_LIST);
        if (directions == null || directions.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int step = 1;
        for (Router.NavigationDirection d: directions) {
            sb.append(String.format("%d. %s <br>", step, d));
            step += 1;
        }
        return sb.toString();
    }
}
