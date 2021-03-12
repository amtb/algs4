/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {
  @Test
  @DisplayName("compute the hamming distance properly")
  void hamming() {
    Board board = getBoard();
    assertEquals(5, board.hamming());
  }

  @Test
  @DisplayName("compute the manhattan distance properly")
  void manhattan() {
    Board board = getBoard();
    assertEquals(10, board.manhattan());
  }

  @Test
  @DisplayName("compute whether it's the goal")
  void goal() {
    assertEquals(false, getBoard().isGoal());
    assertEquals(true, getGoal().isGoal());
  }

  @Test
  @DisplayName("compute whether it's equal")
  void equals() {
    Board board = getBoard();
    Board goal = getGoal();

    assertEquals(false, board.equals(null));
    assertEquals(false, board.equals(goal));

    assertEquals(true, board.equals(board));
    assertEquals(true, board.equals(getBoard()));
  }

  private Board getBoard() {
    int[][] tiles = new int[][] {
      new int[] { 8, 1, 3 },
      new int[] { 4, 0, 2 },
      new int[] { 7, 6, 5 }
    };
    return new Board(tiles);
  }

  private Board getGoal() {
    int[][] tiles = new int[][] {
      new int[] { 1, 2, 3 },
      new int[] { 4, 5, 6 },
      new int[] { 7, 8, 0 }
    };
    return new Board(tiles);
  }

  private Board bigBoard() {
    int[][] tiles = new int[][] {
      new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9,10},
      new int[]{11,12,13,14,15,16,17,18,19,20},
      new int[]{21,22,23,24,25,26,27,28,29,30},
      new int[]{31,32,33,34,35,36,37,38,39,40},
      new int[]{41,42,43,44,45,46,47,48,49,50},
      new int[]{51,52,53,54,55,56,57,58,59,60},
      new int[]{61,62,63,64,65,66,67,68,69,70},
      new int[]{71,72,73,74,75,76,77,78,79,80},
      new int[]{81,82,83,84,85,86,87,88,89,90},
      new int[]{91,92,93,94,95,96,97,98,99,0}
    };
    return new Board(tiles);
  }
}
