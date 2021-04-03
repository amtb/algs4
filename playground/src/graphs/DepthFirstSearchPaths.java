/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package graphs;

public class DepthFirstSearchPaths extends Paths {

  // finds paths in the graph g from source s
  public DepthFirstSearchPaths(Graph g, int s) {
    super(g, s);
    dfs(g, s);
  }

  // visits each neighbor of v in depth
  private void dfs(Graph g, int v) {
    visited[v] = true;
    for (Integer neighbor : g.adj(v)) {
      if (!visited[neighbor]) {
        dfs(g, neighbor);
        paths[neighbor] = v;
      }
    }
  }
}
