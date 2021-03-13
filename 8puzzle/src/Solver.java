/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {
  // the initial board we want to solve
  private final Board initialBoard;

  // list of steps leading to the solution if any
  private Stack<Board> steps;

  // find a solution to the initial board (using the A* algorithm)
  public Solver(Board initial) {
    if (initial == null) {
      throw new IllegalArgumentException();
    }
    initialBoard = initial;
    steps = computeStepsToSolution();
  }

  // is the initial board solvable? (see below)
  public boolean isSolvable() {
    return steps != null;
  }

  // min number of moves to solve initial board; -1 if unsolvable
  public int moves() {
    if (!isSolvable()) {
      return -1;
    }
    // remove the initial board
    return steps.size() - 1;
  }

  // sequence of boards in a shortest solution; null if unsolvable
  public Iterable<Board> solution() {
    if (!isSolvable()) {
      return null;
    }

    return steps;
  }

  // computes the step the solution by creating two queues: with the initial board, and with a twin board (swapped tiles)
  // the first board that get solved, means the other can't be.
  // solving a board: using in a priority queue of board sorted by number of moves + distance to goal
  // each cycle does the following
  // - remove the board with the best priority from the queue
  // - check we we found the solution
  // - adds the neighbors of the board (by moving the empty tile)
  private Stack<Board> computeStepsToSolution() {

    MinPQ<SearchNode> mainQueue = new MinPQ<SearchNode>();
    mainQueue.insert(new SearchNode(initialBoard, 0, null));

    // evil twin
    MinPQ<SearchNode> twinQueue = new MinPQ<SearchNode>();
    twinQueue.insert(new SearchNode(initialBoard.twin(), 0, null));

    SearchNode mainPath;
    SearchNode twinPath = null;
    do {
      mainPath = dequeue(mainQueue);
      if (mainPath == null) {
        twinPath = dequeue(twinQueue);
      }
    } while (mainPath == null && twinPath == null);

    // solution found on the original board, track back the board on the path to the solution
    if (mainPath != null) {
      steps = new Stack<>();
      while (mainPath != null) {
        steps.push(mainPath.board);
        mainPath = mainPath.previous;
      }
    }

    return steps;
  }

  // remove the node at the top
  // check if it's the goal, if so we found a solution
  // otherwise enqueues the next nodes to be explored
  private SearchNode dequeue(MinPQ<SearchNode> queue) {
    SearchNode currentNode = queue.delMin();
    Board board = currentNode.board;

    // hurray!
    if (board.isGoal()) {
      return currentNode;
    }

    // optimization, do not enqueue the "parent" board of the game tree
    Board previousBoard = null;
    if (currentNode.previous != null) {
      previousBoard = currentNode.previous.board;
    }

    // add neighbors
    for (Board neighbor : board.neighbors()) {
      if (!neighbor.equals(previousBoard)) {
        queue.insert(new SearchNode(neighbor, currentNode.moves + 1, currentNode));
      }
    }

    return null;
  }

  private class SearchNode implements Comparable<SearchNode> {
    final int priority;
    Board board;
    int moves;
    SearchNode previous;

    public SearchNode(Board b, int m, SearchNode p) {
      board = b;
      moves = m;
      previous = p;
      priority = moves + board.manhattan();
    }

    public int compareTo(SearchNode searchNode) {
      return priority - searchNode.priority;
    }
  }
}
