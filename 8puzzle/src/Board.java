/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.ArrayList;
import java.util.List;

public class Board {
  private Board goal;
  private final int[][] tiles;
  private final int n;

  // create a board from an n-by-n array of tiles,
  // where tiles[row][col] = tile at (row, col)
  public Board(int[][] tiles) {
    n = tiles.length;
    this.tiles = clone(tiles);
  }

  private int[][] clone(int[][] someTiles) {
    int[][] cloned = new int[n][];
    for (int i = 0; i < n; i++) {
      cloned[i] = someTiles[i].clone();
    }

    return cloned;
  }

  // string representation of this board
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append(n);
    builder.append("\n");
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        builder.append(" ");
        builder.append(tiles[i][j]);
        builder.append(" ");
      }
      builder.append("\n");
    }

    return builder.toString();
  }

  // board dimension n
  public int dimension() {
    return n;
  }

  // number of tiles out of place
  public int hamming() {
    int h = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        // skip the last tile
        if (i == n - 1 && j == n - 1) break;

        int expected = i * n + (j + 1);
        if (tiles[i][j] != expected) {
          h += 1;
        }
      }
    }

    return h;
  }

  // sum of Manhattan distances between tiles and goal
  public int manhattan() {
    int m = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        int tile = tiles[i][j];
        if (tile != 0) {
          int r = getTileRow(tile);
          int c = getTileColumn(tile);
          m += Math.abs(r - i) + Math.abs(c - j);
        }
      }
    }

    return m;
  }

  private int getTileRow(int tile) {
    return (tile - 1) / n;
  }

  private int getTileColumn(int tile) {
    return (tile - 1) % n;
  }

  // is this board the goal board?
  public boolean isGoal() {
    if (goal == null) {
      int[][] goalTiles = new int[n][];
      for (int i = 0; i < n; i++) {
        goalTiles[i] = new int[n];
        for (int j = 0; j < n; j++) {
          goalTiles[i][j] = i * n + j + 1;
        }
      }
      goalTiles[n - 1][n - 1] = 0;
      goal = new Board(goalTiles);
    }
    return equals(goal);
  }

  // does this board equal y?
  public boolean equals(Object y) {
    if (null != y && y.getClass() == this.getClass()) {
      if (this == y) {
        return true;
      }

      Board other = (Board) y;
      if (other.n != n) return false;

      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          if (other.tiles[i][j] != tiles[i][j]) {
            return false;
          }
        }
      }

      return true;
    }

    return false;
  }

  // all neighboring boards
  public Iterable<Board> neighbors() {
    List<Board> boards = new ArrayList<>();
    // lookup the empty tile
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (tiles[i][j] == 0) {
          if (i > 0) {
            boards.add(new Board(cloneAndSwap(i, j, i - 1, j)));
          }
          if (i < n - 1) {
            boards.add(new Board(cloneAndSwap(i, j, i + 1, j)));
          }
          if (j > 0) {
            boards.add(new Board(cloneAndSwap(i, j, i, j - 1)));
          }
          if (j < n - 1) {
            boards.add(new Board(cloneAndSwap(i, j, i, j + 1)));
          }

          return boards;
        }
      }
    }

    return null;
  }

  // a board that is obtained by exchanging any pair of tiles
  public Board twin() {
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n - 1; j++) {
        if (tiles[i][j] != 0 && tiles[i][j + 1] != 0) {
          return new Board(cloneAndSwap(i, j, i, j + 1));
        }
      }
    }

    return null;
  }

  private int[][] cloneAndSwap(int i, int j, int r, int c) {
    int[][] newTiles = clone(tiles);
    int tile = newTiles[i][j];
    newTiles[i][j] = newTiles[r][c];
    newTiles[r][c] = tile;

    return newTiles;
  }
}
