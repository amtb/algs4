/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PointTest {
  @Test
  @DisplayName("should compare points correctly")
  void compare() {
    Point point = new Point(1, 1);
    assertEquals(-1, point.compareTo(new Point(2, 1)));
    assertEquals(-1, point.compareTo(new Point(2, 2)));
    assertEquals(0, point.compareTo(new Point(1, 1)));
    assertEquals(1, point.compareTo(new Point(1, 0)));
  }

  @Test
  @DisplayName("should compute the correct slope")
  void slope() {
    Point point = new Point(1, 1);
    assertEquals(1.0, point.slopeTo(new Point(2, 2)));
    assertEquals(0.0, point.slopeTo(new Point(2, 1)));
    assertEquals(Double.POSITIVE_INFINITY, point.slopeTo(new Point(1, 2)));
    assertEquals(Double.NEGATIVE_INFINITY, point.slopeTo(new Point(1, 1)));
  }

  @Test
  @DisplayName("should compute the correct slope order")
  void slopeOrder() {
    Point point = new Point(1, 1);
    assertEquals(-1, point.slopeOrder().compare(new Point(2, 2), new Point(1, 2)));

    assertEquals(0, point.slopeOrder().compare(new Point(2, 1), new Point(3, 1)));
    assertEquals(0, point.slopeOrder().compare(new Point(1, 1), new Point(1, 1)));
    assertEquals(0, point.slopeOrder().compare(new Point(2, 2), new Point(3, 3)));

    assertEquals(1, point.slopeOrder().compare(new Point(2, 3), new Point(2, 2)));
  }
}
