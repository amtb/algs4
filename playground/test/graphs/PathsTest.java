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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PathsTest {
  @Test
  @DisplayName("should compute the correct path using depth first search")
  void dfs() {
    Graph graph = new Graph(13);
    graph.addEdge(0, 5);
    graph.addEdge(4, 3);
    graph.addEdge(0, 1);
    graph.addEdge(9, 12);
    graph.addEdge(5, 4);
    graph.addEdge(0, 2);
    graph.addEdge(11, 12);
    graph.addEdge(9, 10);
    graph.addEdge(0, 6);
    graph.addEdge(7, 8);
    graph.addEdge(9, 11);
    graph.addEdge(5, 3);
    graph.addEdge(6, 7);

    DepthFirstSearchPaths dfsFrom0 = new DepthFirstSearchPaths(graph, 0);
    // hasPathTo
    assertFalse(dfsFrom0.hasPathTo(12));
    assertTrue(dfsFrom0.hasPathTo(1));

    // pathTo
    assertEquals(null, dfsFrom0.pathTo(10));

    Iterator<Integer> pathTo8 = dfsFrom0.pathTo(8).iterator();
    int[] expectedPath = new int[] { 0, 6, 7 };
    for (int vertex : expectedPath) {
      assertEquals(vertex, pathTo8.next());
    }
  }

  @Test
  @DisplayName("should compute the correct path using breadth first search")
  void bfs() {
    Graph graph = new Graph(6);
    graph.addEdge(0, 5);
    graph.addEdge(2, 4);
    graph.addEdge(2, 3);
    graph.addEdge(1, 2);
    graph.addEdge(0, 1);
    graph.addEdge(3, 4);
    graph.addEdge(3, 5);
    graph.addEdge(0, 2);

    BreadthFirstSearchPaths bfsFrom0 = new BreadthFirstSearchPaths(graph, 0);
    // hasPathTo
    assertTrue(bfsFrom0.hasPathTo(1));

    // pathTo
    Iterator<Integer> pathTo4 = bfsFrom0.pathTo(4).iterator();
    int[] expectedPath = new int[] { 0, 2 };
    for (int vertex : expectedPath) {
      assertEquals(vertex, pathTo4.next());
    }
  }
}
