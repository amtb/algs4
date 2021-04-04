/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package graphs;

import edu.princeton.cs.algs4.Stack;

import java.util.ArrayList;
import java.util.List;

public class TopologicalSort {
  private boolean[] visited;
  private Stack<Integer> order;

  public TopologicalSort(Digraph digraph) {
    visited = new boolean[digraph.V()];
    order = new Stack<>();

    for (int v = 0; v < digraph.V(); v++) {
      if (!visited[v]) {
        dfs(digraph, v);
      }
    }
  }

  private void dfs(Digraph digraph, int v) {
    visited[v] = true;

    for (int w : digraph.adj(v)) {
      if (!visited[w]) {
        dfs(digraph, w);
      }
    }

    // all the vertices adjacent to the current vertex have been explored and added on the stack
    // so, add the current vertex as well
    order.push(v);
  }

  public Iterable<Integer> postOrder() {
    return order;
  }

  public Iterable<Integer> topologicalOrder() {
    List<Integer> reverse = new ArrayList<>(order.size());
    for (int v : order) {
      reverse.add(v);
    }

    return reverse;
  }
}
