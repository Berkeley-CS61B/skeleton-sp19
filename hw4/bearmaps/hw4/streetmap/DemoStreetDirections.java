package bearmaps.hw4.streetmap;

import bearmaps.hw4.AStarSolver;
import bearmaps.hw4.SolutionPrinter;

/**
 * Example that provides driving directions on the Berkeley street map.
 * Unfortunately, the node numbers provided are meaningless -- we don't know where
 * node 2793619975 is, for example. But we'll fix that in project 2c!
 * NOTE: YOU MUST REPLACE LazySolver WITH AStarSolver OR THIS DEMO WON'T WORK!
 * Created by hug.
 */
public class DemoStreetDirections {
    public static void main(String[] args) {
        StreetMapGraph smg = StreetMapGraph.readFromSimpleFormat("berkeley-street-data.simple");
        AStarSolver<Long> solver = new AStarSolver<>(smg, 2793619975L, 2793619967L, 5);
        SolutionPrinter.summarizeSolution(solver, "->");
    }
}
