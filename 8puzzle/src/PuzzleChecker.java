/******************************************************************************
 *  Compilation:  javac-algs4 PuzzleChecker.java
 *  Execution:    java-algs4 PuzzleChecker filename1.txt filename2.txt ...
 *  Dependencies: Board.java Solver.java
 *
 *  This program creates an initial board from each filename specified
 *  on the command line and finds the minimum number of moves to
 *  reach the goal state.
 *
 *  % java-algs4 PuzzleChecker puzzle*.txt
 *  puzzle00.txt: 0
 *  puzzle01.txt: 1
 *  puzzle02.txt: 2
 *  puzzle03.txt: 3
 *  puzzle04.txt: 4
 *  puzzle05.txt: 5
 *  puzzle06.txt: 6
 *  ...
 *  puzzle3x3-impossible: -1
 *  ...
 *  puzzle42.txt: 42
 *  puzzle43.txt: 43
 *  puzzle44.txt: 44
 *  puzzle45.txt: 45
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class PuzzleChecker {

  public static void main(String[] args) {

    // String[] files = new String[] { "test-files/puzzle2x2-unsolvable3.txt" };
    String[] files = new String[] { "test-files/puzzle28.txt" };

    // for each command-line argument
    for (String filename : files) {

      // create initial board from file
      In in = new In(filename);
      int n = in.readInt();
      int[][] tiles = new int[n][n];
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          tiles[i][j] = in.readInt();
        }
      }
      Board initial = new Board(tiles);

      // solve the puzzle
      Solver solver = new Solver(initial);

      // print solution to standard output
      if (!solver.isSolvable()) {
        StdOut.println("No solution possible");
      }
      else {
        StdOut.println("Minimum number of moves = " + solver.moves());
        for (Board board : solver.solution()) {
          StdOut.println(board);
        }
      }
    }
  }
}
