/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package graphs;

import edu.princeton.cs.algs4.Stack;

public abstract class Paths {
  // the vertex from which we want to run dfs
  protected int source;

  // will contain the origin vertex leading to each vertex on the path
  protected Integer[] paths;

  // was the vertex visited during the dfs run ?
  protected boolean[] visited;

  protected Paths(Graph g, int s) {
    source = s;
    visited = new boolean[g.V()];
    paths = new Integer[g.V()];
  }

  // is there a path from s to the vertex v
  public boolean hasPathTo(int v) {
    return visited[v];
  }

  // the path from s to v
  public Iterable<Integer> pathTo(int v) {
    if (!visited[v]) return null;

    Stack<Integer> path = new Stack<>();
    while (paths[v] != source) {
      path.push(paths[v]);
      v = paths[v];
    }

    path.push(source);
    return path;
  }

}
