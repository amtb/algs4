/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package sorts;

public class Insertion {
  public static void sort(Comparable[] array) {
    for (int i = 0; i < array.length; i++) {
      for (int j = i; j > 0; j--) {
        if (Utils.isLessThan(array[j], array[j-1])) {
          Utils.swap(array, j, j-1);
        } else {
          break;
        }
      }
    }
  }
}
