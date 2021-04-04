/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package graphs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TopologicalTest {
  @Test
  @DisplayName("should return the order of the nodes topologically sorted")
  void topological() {
    Digraph digraph = new Digraph(7);

    digraph.addEdge(0, 1);
    digraph.addEdge(0, 5);
    digraph.addEdge(0, 2);
    digraph.addEdge(3, 6);
    digraph.addEdge(3, 5);
    digraph.addEdge(3, 4);
    digraph.addEdge(5, 2);
    digraph.addEdge(6, 4);
    digraph.addEdge(6, 0);
    digraph.addEdge(3, 2);
    digraph.addEdge(1, 4);

    TopologicalSort ts = new TopologicalSort(digraph);
    Iterator<Integer> topologicalOrder = ts.topologicalOrder().iterator();

    int[] expectedPath = new int[] { 3, 6, 0, 5, 2, 1, 4};
    for (int vertex : expectedPath) {
      assertEquals(vertex, topologicalOrder.next());
    }
  }
}
