/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package graphs;

public class Digraph extends AbstractGraph {

  public Digraph(int V) {
    super(V);
  }

  public void addEdge(int v, int w) {
    adjacents[v].add(w);
  }

  public int E() {
    int edges = 0;
    for (int i = 0; i < adjacents.length; i++) {
      edges += adjacents[i].size();
    }
    return edges;
  }
}
