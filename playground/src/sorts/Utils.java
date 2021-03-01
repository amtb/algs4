package sorts;

/**
 * Utils for the sort algorthims
 */
public class Utils {
  /**
   * Is the item on the left less than the one on the right ?
   */
  public static boolean isLessThan(Comparable l, Comparable r) {
    return l.compareTo(r) < 0;
  }

  /**
   * Is the given array sorted ?
   */
  public static boolean isSorted(Comparable[] array) {
    for (int i = 0; i < array.length - 1; i++) {
      if (array[i].compareTo(array[i+1]) > 0) {
        return false;
      }
    }
    return true;
  }

  /**
   * Swaps the content of array[i] and array[j]
   */
  public static void swap(Comparable[] array, int i, int j) {
    Comparable tmp = array[i];
    array[i] = array[j];
    array[j] = tmp;
  }
}
