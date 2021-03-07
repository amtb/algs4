/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package sorts;

import edu.princeton.cs.algs4.StdRandom;

public class QuickSort {
  private static int partition(Comparable[] array, int low, int high) {
    Comparable pivot = array[low];

    int i = low, j = high + 1;
    while (true) {
      while (Utils.isLessThan(array[++i], pivot)) {
        // too high
        if (i == high) break;
      }

      while (Utils.isLessThan(pivot, array[--j])) {
        // too low
        if (j == low) break;
      }

      // check if we didn't cross the pointers
      if (i >= j) break;

      //
      Utils.swap(array, i, j);
    }

    Utils.swap(array, low, j);

    return j;
  }

  private static void sort(Comparable[] array, int low, int high) {
    if (high <= low) return;
    int j = partition(array, low, high);
    sort(array, low, j - 1);
    sort(array, j + 1, high);
  }

  public static void sort(Comparable[] array) {
    StdRandom.shuffle(array);
    sort(array, 0, array.length - 1);
  }
}
