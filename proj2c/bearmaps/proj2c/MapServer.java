package bearmaps.proj2c;


import bearmaps.proj2c.server.handler.APIRouteHandlerFactory;

/**
 * This code is using BearMaps skeleton code version 4.0.
 * @author Alan Yao, Josh Hug
 */
public class MapServer {


    /**
     * This is where the MapServer is started.
     * @param args
     */
    public static void main(String[] args) {

        MapServerInitializer.initializeServer(APIRouteHandlerFactory.handlerMap);

    }

}
