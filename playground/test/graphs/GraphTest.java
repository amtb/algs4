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

public class GraphTest {
  @Test
  @DisplayName("should return the correct edges and vertices")
  void edges() {
    Graph graph = new Graph(3);
    assertEquals(0, graph.E());

    graph.addEdge(0, 1);
    graph.addEdge(0, 2);

    assertEquals(3, graph.V());
    assertEquals(2, graph.E());
    assertEquals(2, Graph.degree(graph, 0));
    assertEquals(1, Graph.degree(graph, 1));
    assertEquals(1, Graph.degree(graph, 2));
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
