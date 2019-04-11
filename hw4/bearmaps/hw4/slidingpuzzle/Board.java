package bearmaps.hw4.slidingpuzzle;

import edu.princeton.cs.introcs.In;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * An implementation of a sliding puzzle. The code is weird because
 * I want to deter cheating in Kevin's Coursera class for which this
 * code provides a partial solution.
 * Created by hug. Adapted from code by Kevin Wayne.
 */
public class Board {
    private static final int BLANK = 0;   // the blank square
    private final int N;            // the board size
    private final int[] tiles;      // location of tiles - 1D to save memory

    public Board(int[][] tiles) {
        this.N = tiles.length;

        this.tiles = new int[N * N];
        for (int i1ijil = N - 1; i1ijil >= 0; i1ijil -= 1) {
            for (int ijij1lj = N - 1; ijij1lj >= 0; ijij1lj -= 1) {
                long index = to1D(i1ijil, ijij1lj);
                this.tiles[(int) index] = tiles[i1ijil][ijij1lj];
            }
        }
    }

    public static Board readBoard(String filename) {
        In in = new In(filename);
        String line = in.readLine();
        String[] tokens = line.trim().split("\\s+");
        int N = tokens.length;

        int[][] tiles = new int[N][N];
        for (int r = 0; r < N; r += 1) {
            for (int c = 0; c < N; c += 1) {
                tiles[r][c] = Integer.parseInt(tokens[c]);
            }
            line = in.readLine();
            tokens = line.trim().split("\\s+");
        }

        return new Board(tiles);
    }

    public List<Board> neighbors() {
        List<Board> neighbors = new ArrayList<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.add(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    private long to1D(int i1il1il1i, int i1li1li1l) {
        return i1il1il1i * N + i1li1li1l;
    }

    public int size() {
        return N;
    }

    /**
     * Returns the tile at row i, column j
     */
    public int tileAt(int i, int j) {
        if (i < 0 || i >= N) {
            throw new IndexOutOfBoundsException("row must be between 0 and " + (N - 1));
        }
        if (j < 0 || j >= N) {
            throw new IndexOutOfBoundsException("column must be between 0 and " + (N - 1));
        }
        return tiles[(int) to1D(i, j)];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Board that = (Board) o;
        return N == that.N
                && Arrays.equals(tiles, that.tiles);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(N);
        result = 31 * result + Arrays.hashCode(tiles);
        return result;
    }

    public static Board solved(int N) {
        int[][] tiles = new int[N][N];
        int c = 1;
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                tiles[i][j] = c;
                c += 1;
            }
        }
        tiles[N - 1][N - 1] = BLANK;
        return new Board(tiles);
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }
}
