/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package graphs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConnectedComponentsTest {
  @Test
  @DisplayName("should compute the connected components")
  void cc() {
    Graph graph = new Graph(13);
    graph.addEdge(0, 5);
    graph.addEdge(4, 3);
    graph.addEdge(0, 1);
    graph.addEdge(9, 12);
    graph.addEdge(6, 4);
    graph.addEdge(5, 4);
    graph.addEdge(0, 2);
    graph.addEdge(11, 12);
    graph.addEdge(9, 10);
    graph.addEdge(0, 6);
    graph.addEdge(7, 8);
    graph.addEdge(9, 11);
    graph.addEdge(5, 3);

    ConnectedComponents cc = new ConnectedComponents(graph);
    // count
    assertEquals(3, cc.count());

    // connected
    assertTrue(cc.connected(0, 3));
    assertTrue(cc.connected(1, 5));
    assertTrue(cc.connected(7, 8));
    assertTrue(cc.connected(10, 11));

    // not connected
    assertFalse(cc.connected(0, 11));
    assertFalse(cc.connected(8, 11));

    // components
    assertEquals(0, cc.id(0));
    assertEquals(1, cc.id(8));
    assertEquals(2, cc.id(12));
  }
}
