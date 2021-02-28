package sorts;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilsTest {
  @Test
  @DisplayName("should be sorted")
  void isSorted() {
    Integer[] array = {1, 1, 2, 3};
    assertEquals(Utils.isSorted(array), true);
  }
}
