/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package sorts;

public class Selection {

  // O(n^2)
  public static void sort(Comparable[] array) {
    int length = array.length;
    for (int i = 0; i < length - 1; i++) {
      int min = i;
      // find the min at the right of i
      for (int j = i + 1; j < length; j++) {
        if (Utils.isLessThan(array[j], array[min])) {
          min = j;
        }
      }
      // exchange array[i] and array[min]
      Utils.swap(array, i, min);
    }
  }
}
