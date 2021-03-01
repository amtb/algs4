/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package sorts;

import edu.princeton.cs.algs4.StdRandom;

public class RandomArray {

  public static Integer[] generate(int size) {
    Integer[] array = new Integer[size];
    for (int i = 0; i < size; i++) {
      array[i] = i;
    }
    StdRandom.shuffle(array);
    return array;
  }
}
