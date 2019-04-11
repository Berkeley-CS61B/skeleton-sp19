package bearmaps.proj2c.server.handler;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.Set;

import static spark.Spark.halt;

/**
 * This is the base class that defines the procedure for handling an API request
 * The process is defined as such that first the request parameters are read, then
 * request is process based on those parameters and finally the response is built.
 *
 * Created by rahul
 */
public abstract class APIRouteHandler<Req, Res> implements Route {

    /** HTTP failed response. */
    private static final int HALT_RESPONSE = 403;

    private Gson gson;

    public APIRouteHandler() {
        gson = new Gson();
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Req requestParams = parseRequestParams(request);
        Res result = processRequest(requestParams, response);
        return buildJsonResponse(result);
    }

    /**
     * Defines how to parse and extract the request parameters from request
     * @param request   the request object received
     * @return  extracted request parameters
     */
    protected abstract Req parseRequestParams(Request request);

    /**
     * Process the request using the given parameters
     * @param requestParams request parameters
     * @param response  response object
     * @return  the result computed after processing request
     */
    protected abstract Res processRequest(Req requestParams, Response response);

    /**
     * Builds a JSON response to return from the result object
     * @param result
     * @return
     */
    protected  Object buildJsonResponse(Res result){
        return gson.toJson(result);
    }

    /**
     * Validate & return a parameter map of the required request parameters.
     * Requires that all input parameters are doubles.
     * @param req HTTP Request.
     * @param requiredParams TestParams to validate.
     * @return A populated map of input parameter to it's numerical value.
     */
    protected  HashMap<String, Double> getRequestParams(
            spark.Request req, String[] requiredParams) {
        Set<String> reqParams = req.queryParams();
        HashMap<String, Double> params = new HashMap<>();
        for (String param : requiredParams) {
            if (!reqParams.contains(param)) {
                halt(HALT_RESPONSE, "Request failed - parameters missing.");
            } else {
                try {
                    params.put(param, Double.parseDouble(req.queryParams(param)));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    halt(HALT_RESPONSE, "Incorrect parameters - provide numbers.");
                }
            }
        }
        return params;
    }
}
