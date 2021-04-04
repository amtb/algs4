/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package graphs;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractGraph {
  protected List<Integer>[] adjacents;

  public AbstractGraph(int V) {
    adjacents = (List<Integer>[]) new List[V];
    for (int i = 0; i < V; i++) {
      adjacents[i] = new LinkedList<>();
    }
  }

  // adds an edge between v and w
  public abstract void addEdge(int v, int w);

  // return the adjacent vertices of v
  public Iterable<Integer> adj(int v) {
    return adjacents[v];
  }

  // the number of vertices
  public int V() {
    return adjacents.length;
  }

  // the number of edges
  public abstract int E();
}
