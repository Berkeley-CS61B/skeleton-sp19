package bearmaps.hw4.slidingpuzzle;

import bearmaps.hw4.LazySolver;
import bearmaps.hw4.ShortestPathsSolver;
import bearmaps.hw4.SolutionPrinter;

/**
 * Showcases how the AStarSolver can be used for solving sliding puzzles.
 * Runs several puzzles in a row.
 * NOTE: YOU MUST REPLACE LazySolver WITH AStarSolver OR THIS DEMO WON'T WORK!
 * Created by hug.
 */
public class DemoRunSeveralPuzzles {
    private static String[] basicPuzzles = {"BasicPuzzle1.txt", "BasicPuzzle2.txt",
        "BasicPuzzle3.txt", "BasicPuzzle4.txt"};

    private static String[] hardPuzzles = {"HardPuzzle1.txt", "HardPuzzle2.txt",
        "HardPuzzle3.txt"};

    private static String[] elitePuzzles = {"ElitePuzzle1.txt", "ElitePuzzle2.txt",
        "ElitePuzzle3.txt"};

    public static void main(String[] args) {

        String[] puzzleFiles = hardPuzzles;

        System.out.println(puzzleFiles.length + " puzzle files being run.");
        for (int i = 0; i < puzzleFiles.length; i += 1) {
            Board start = Board.readBoard(puzzleFiles[i]);
            int N = start.size();
            Board goal = Board.solved(N);

            BoardGraph spg = new BoardGraph();
            System.out.println(puzzleFiles[i] + ":");
            ShortestPathsSolver<Board> solver = new LazySolver<>(spg, start, goal, 30);
            SolutionPrinter.summarizeOutcome(solver);
        }

    }
}
