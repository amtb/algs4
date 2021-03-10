/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickUnion implements UnionFind {
  public int[] id;

  public QuickUnion(int n) {
    id = new int[n];
    for (int i = 0; i < n; i++) {
      id[i] = i;
    }
  }

  protected int root(int p) {
    while (id[p] != p) {
      p = id[p];
    }

    return id[p];
  }

  /**
   * Adds a union between p and q
   */
  public void union(int p, int q) {
    id[root(p)] = root(q);
  }

  /**
   * Finds if p and q are connected
   */
  public boolean find(int p, int q) {
    return root(p) == root(q);
  }

  public void print() {
    for (int i = 0; i < id.length; i++) {
      StdOut.print(id[i] + " ");
    }
    StdOut.println();
  }

  public static void main(String[] args) {
    int n = StdIn.readInt();
    QuickUnion qu = new QuickUnion(n);

    while (!StdIn.isEmpty()) {
      int p = StdIn.readInt();
      int q = StdIn.readInt();

      qu.union(p, q);
      qu.print();
    }
  }
}
