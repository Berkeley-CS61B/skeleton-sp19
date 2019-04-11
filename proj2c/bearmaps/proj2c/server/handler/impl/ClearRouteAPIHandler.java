package bearmaps.proj2c.server.handler.impl;

import bearmaps.proj2c.server.handler.APIRouteHandler;
import spark.Request;
import spark.Response;

import static bearmaps.proj2c.utils.Constants.ROUTE_LIST;


/**
 * Handles the "Clear Route" button in Bearmaps.
 * Created by rahul
 */
public class ClearRouteAPIHandler extends APIRouteHandler {


    @Override
    protected Object parseRequestParams(Request request) {
        return null;
    }

    @Override
    protected Object processRequest(Object requestParams, Response response) {
        ROUTE_LIST.clear();
        return true;
    }
}
