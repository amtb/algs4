/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package sorts;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortingTest {
  @ParameterizedTest
  @MethodSource("randomArrays")
  @DisplayName("should sort the arrays using selection sort")
  void selectionSort(Integer[] array) {
    Selection.sort(array);
    assertEquals(Utils.isSorted(array), true);
  }

  @ParameterizedTest
  @MethodSource("randomArrays")
  @DisplayName("should sort the arrays using insertion sort")
  void insertionSort(Integer[] array) {
    Insertion.sort(array);
    assertEquals(Utils.isSorted(array), true);
  }

  @ParameterizedTest
  @MethodSource("randomArrays")
  @DisplayName("should sort the arrays using merge sort")
  void mergeSort(Integer[] array) {
    Merge.sort(array);
    assertEquals(Utils.isSorted(array), true);
  }

  @Test
  @DisplayName("merge sort should sort faster than insertion sort")
  void speedTest() {
    int size = (int) Math.pow(2, 16);

    long clock = System.currentTimeMillis();
    Merge.sort(RandomArray.generate(size));
    long elapsedTimeMergeSort = System.currentTimeMillis() - clock;

    clock = System.currentTimeMillis();
    Insertion.sort(RandomArray.generate(size));
    long elapsedTimeInsertionSort = System.currentTimeMillis() - clock;

    assertEquals(elapsedTimeInsertionSort >= elapsedTimeMergeSort, true);
  }

  @ParameterizedTest
  @MethodSource("randomArrays")
  @DisplayName("should sort the arrays using quick sort")
  void quickSort(Integer[] array) {
    QuickSort.sort(array);
    assertEquals(Utils.isSorted(array), true);
  }

  static Object[][] randomArrays() {
    return new Object[][] {
            new Object[] { RandomArray.generate(1) },
            new Object[] { RandomArray.generate(10) },
            new Object[] { RandomArray.generate(1000) }
    };
  }
}
