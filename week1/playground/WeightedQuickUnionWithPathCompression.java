/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

import java.util.ArrayList;
import java.util.List;

public class WeightedQuickUnionWithPathCompression extends QuickUnion {
  private int[] weigths;

  public WeightedQuickUnionWithPathCompression(int n) {
    super(n);
    weigths = new int[n];
  }

  protected int root(int p) {
    // list of nodes met on the path to the root
    List<Integer> path = new ArrayList<>();
    while (id[p] != p) {
      path.add(p);
      // move upwards
      p = id[p];
    }

    // compress the path by pointing directly to the root
    for (Integer i : path) {
      id[i] = p;
    }

    return p;
  }

  public void union(int p, int q) {
    int rootP = root(p);
    int rootQ = root(q);

    if (rootP != rootQ) {
      // move the smaller tree under the larger one and adjust the weight
      if (weigths[rootP] > weigths[rootQ]) {
        id[rootQ] = id[rootP];
        weigths[rootP] += weigths[rootQ];
      }
      else {
        id[rootP] = id[rootQ];
        weigths[rootQ] += weigths[rootP];
      }
    }
  }

  public static void main(String[] args) {
    int n = StdIn.readInt();
    WeightedQuickUnionWithPathCompression wqu = new WeightedQuickUnionWithPathCompression(n);

    while (!StdIn.isEmpty()) {
      int p = StdIn.readInt();
      int q = StdIn.readInt();

      wqu.union(p, q);
      wqu.print();
    }
  }
}
