/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package priority_queues;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriorityQueueTest {
  @Test
  @DisplayName("max priority queue should swim properly")
  void maxPQSwim() {
    MaxPriorityQueue<Integer> maxPQ = new MaxPriorityQueue<>();

    maxPQ.insert(5);
    maxPQ.insert(10);
    maxPQ.insert(15);

    assertEquals(15, maxPQ.max());
  }

  @Test
  @DisplayName("max priority queue should delete max and sink properly")
  void maxPQSink() {
    MaxPriorityQueue<Integer> maxPQ = new MaxPriorityQueue<>();

    maxPQ.insert(5);
    maxPQ.insert(10);
    maxPQ.insert(15);
    maxPQ.insert(13);

    int max = maxPQ.delMax();

    assertEquals(15, max);
    assertEquals(13, maxPQ.max());

    maxPQ.insert(14);
    maxPQ.insert(15);

    max = maxPQ.delMax();
    assertEquals(15, max);
  }
}
