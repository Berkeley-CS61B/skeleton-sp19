package bearmaps.proj2c;


import bearmaps.proj2c.server.handler.APIRouteHandlerFactory;

/**
 * This MapServer class is the entry point for running the JavaSpark web server for the BearMaps
 * application project, receiving API calls, handling the API call processing, and generating
 * requested images and routes. You should not need to modify this file unless you're
 * doing the Autocomplete part of the project, though you are welcome to do so.
 * This code is using BearMaps skeleton code version 2.0.
 * @author Alan Yao, Josh Hug
 */
public class MapServer {


    /**
     * This is where the MapServer is started. All the API routes that need to be served are initialized here.
     * @param args
     */
    public static void main(String[] args) {

        MapServerInitializer.initializeServer(APIRouteHandlerFactory.handlerMap);

    }

}
