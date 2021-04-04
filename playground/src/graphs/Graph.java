/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package graphs;

public class Graph extends AbstractGraph {

  public Graph(int V) {
    super(V);
  }

  // adds an edge between v and w
  public void addEdge(int v, int w) {
    adjacents[v].add(w);
    adjacents[w].add(v);
  }

  // degree of v
  public static int degree(Graph g, int v) {
    int degree = 0;
    for (Integer n : g.adj(v)) {
      degree += 1;
    }
    return degree;
  }

  // the number of edges
  public int E() {
    int edges = 0;
    for (int i = 0; i < adjacents.length; i++) {
      edges += adjacents[i].size();
    }
    return edges / 2;
  }
}
