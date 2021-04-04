/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package graphs;

import edu.princeton.cs.algs4.Queue;

public class BreadthFirstSearchPaths extends Paths {

  public BreadthFirstSearchPaths(AbstractGraph g, int s) {
    super(g, s);
    bfs(g);
  }

  private void bfs(AbstractGraph g) {
    // vertices to process in order (starting by the source)
    Queue<Integer> queue = new Queue<>();
    queue.enqueue(source);
    visited[source] = true;

    while (!queue.isEmpty()) {
      int v = queue.dequeue();

      for (int neighbor : g.adj(v)) {
        if (!visited[neighbor]) {
          visited[neighbor] = true;
          paths[neighbor] = v;

          queue.enqueue(neighbor);
        }
      }
    }
  }
}
