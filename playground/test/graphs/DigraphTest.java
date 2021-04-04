/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package graphs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DigraphTest {
  @Test
  @DisplayName("should return the correct edges and vertices")
  void edges() {
    Digraph digraph = new Digraph(3);
    assertEquals(0, digraph.E());

    digraph.addEdge(0, 1);
    digraph.addEdge(0, 2);

    assertEquals(3, digraph.V());
    assertEquals(2, digraph.E());
  }

  @Test
  @DisplayName("should return the correct adjacency list")
  void adj() {
    Graph graph = new Graph(3);

    graph.addEdge(0, 1);
    graph.addEdge(0, 2);

    List<Integer> neighbors = new ArrayList<>();
    for (Integer n : graph.adj(0)) {
      neighbors.add(n);
    }
    assertEquals(2, neighbors.size());
    assertTrue(neighbors.contains(1));
    assertTrue(neighbors.contains(2));
  }
}
