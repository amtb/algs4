/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package sorts;

public class Merge {
  /**
   * Merge the sorted (sub-) arrays
   */
  private static void merge(Comparable[] array, int low, int mid, int high,
                            Comparable[] copy) {
    for (int i = low; i <= high; i++) {
      copy[i] = array[i];
    }

    for (int i = low, j = mid + 1, k = low; k <= high; k++) {
      if (i > mid) {
        array[k] = copy[j++];
      }
      else if (j > high) {
        array[k] = copy[i++];
      }
      else {
        array[k] = Utils.isLessThan(copy[i], copy[j]) ? copy[i++] : copy[j++];
      }
    }
  }

  /**
   * Sorts the chunk (from low to high) of the array
   */
  private static void sort(Comparable[] array, int low, int high, Comparable[] copy) {
    if (low < high) {
      int mid = low + (high - low) / 2;
      // sort the left side
      sort(array, low, mid, copy);
      // sort the right side
      sort(array, mid + 1, high, copy);
      // merge the two sorted sub-arrays
      merge(array, low, mid, high, copy);
    }
  }

  public static void sort(Comparable[] array) {
    Comparable[] copy = new Comparable[array.length];
    sort(array, 0, array.length - 1, copy);
  }
}
