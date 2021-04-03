/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package graphs;

public class ConnectedComponents {
  // number of connected components
  private int numberOfComponents;

  // the component of each vertex
  private int[] components;

  // was the vertex visited during run ?
  private boolean[] visited;

  // find connected components in G
  public ConnectedComponents(Graph g) {
    visited = new boolean[g.V()];
    components = new int[g.V()];
    computeConnectedComponents(g);
  }

  private void computeConnectedComponents(Graph g) {
    numberOfComponents = 0;

    for (int v = 0; v < g.V(); v++) {
      if (!visited[v]) {
        dfs(g, v, numberOfComponents);
        numberOfComponents++;
      }
    }
  }

  private void dfs(Graph g, int v, int component) {
    visited[v] = true;
    components[v] = component;

    for (int n : g.adj(v)) {
      if (!visited[n]) {
        dfs(g, n, component);
      }
    }
  }

  // are v and w connected?
  public boolean connected(int v, int w) {
    return components[v] == components[w];
  }

  // number of connected components
  public int count() {
    return numberOfComponents;
  }

  // component identifier for v
  public int id(int v) {
    return components[v];
  }
}
