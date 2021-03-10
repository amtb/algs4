/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package priority_queues;

import edu.princeton.cs.algs4.StdOut;
import sorts.Utils;

public class MaxPriorityQueue<Key extends Comparable<Key>> {
  Key[] array;
  int size;

  public MaxPriorityQueue() {
    array = (Key[]) new Comparable[16];
  }

  public void insert(Key key) {
    size += 1;
    array[size] = key;
    swim(size);
  }

  // upstream
  private void swim(int k) {
    // k/2 = parent
    while (k > 1 && Utils.isLessThan(array[getParentOf(k)], array[k])) {
      int parentOfK = getParentOf(k);
      Utils.swap(array, k, parentOfK);
      k = parentOfK;
    }
  }

  private int getParentOf(int k) {
    return k / 2;
  }

  public Key max() {
    if (isEmpty()) {
      return null;
    }

    return array[1];
  }

  public Key delMax() {
    if (isEmpty()) {
      return null;
    }

    // keep a ref
    Key max = array[1];
    // swap with the last
    Utils.swap(array, 1, size);
    // empty the spot
    array[size] = null;
    size -= 1;
    // fix order
    sink(1);

    return max;
  }

  // demote
  private void sink(int parent) {
    while (2 * parent <= size) {
      int leftChild = 2 * parent;
      int rightChild = leftChild + 1;

      int childCandidate = leftChild;
      if (rightChild < size && Utils.isLessThan(array[leftChild], array[rightChild])) {
        childCandidate = rightChild;
      }

      // biggest child is smaller than the siblings parents => order is restored
      if (Utils.isLessThan(array[childCandidate], array[parent])) break;

      Utils.swap(array, childCandidate, parent);
      parent = childCandidate;
    }
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int getSize() {
    return size;
  }

  public void print() {
    StdOut.println("size: " + size);
    for (int i = 1; i <= size; i++) {
      StdOut.println("i: " + i + " -> " + array[i]);
    }
  }
}
