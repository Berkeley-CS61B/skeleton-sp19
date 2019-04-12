package bearmaps.proj2c.utils;

import bearmaps.proj2c.AugmentedStreetMapGraph;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * A class holding all the constant values used throughout the project
 *
 * Created by rahul
 */
public class Constants {

    /**
     * The root upper left/lower right longitudes and latitudes represent the bounding box of
     * the root tile, as the images in the img/ folder are scraped.
     * Longitude == x-axis; latitude == y-axis.
     */
    public static final double ROOT_ULLAT = 37.892195547244356, ROOT_ULLON = -122.2998046875,
            ROOT_LRLAT = 37.82280243352756, ROOT_LRLON = -122.2119140625;

    /**
     * The OSM XML file path. Downloaded from <a href="http://download.bbbike.org/osm/">here</a>
     * using custom region selection.
     **/
    public static final String OSM_DB_PATH = "../library-sp19/data/proj2c_xml/berkeley-2019.osm.xml";

    /** The tile images are in the IMG_ROOT folder. */
    public static final String IMG_ROOT = "../library-sp19/data/proj2c_imgs/";

    /** Route stroke information: Cyan with half transparency. */
    public static final Color ROUTE_STROKE_COLOR = new Color(108, 181, 230, 200);

    /** Route stroke information: typically roads are not more than 5px wide. */
    public static final float ROUTE_STROKE_WIDTH_PX = 5.0f;

    /** Each tile is 256x256 pixels. */
    public static final int TILE_SIZE = 256;

    public static AugmentedStreetMapGraph SEMANTIC_STREET_GRAPH;

    /**
     * This is used to maintain a single List of route so that the same instance(object) is accessed
     * from everywhere in the code. Enum is a cleaner way to achieve such a singleton pattern.
     */
    public static final List<Long> ROUTE_LIST = new LinkedList<>();
}
