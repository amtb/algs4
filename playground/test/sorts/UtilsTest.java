package sorts;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilsTest {
  @ParameterizedTest
  @MethodSource("arrays")
  @DisplayName("the array should be sorted")
  void isSorted(Comparable[] array, boolean isSorted) {
    assertEquals(Utils.isSorted(array), isSorted);
  }

  static Object[][] arrays() {
    return new Object[][] {
            new Object[] {new Integer[] { }, true},
            new Object[] {new Integer[] { 1 }, true},
            new Object[] {new Integer[] { 1, 1 }, true},
            new Object[] {new Integer[] { 1, 1, 2, 3 }, true},
            new Object[] {new Integer[] { 1, -1 }, false},
            new Object[] {new Integer[] { -1, -2 }, false}
    };
  }
}
