import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickFind implements UnionFind {
  public int[] id;

  public QuickFind(int n) {
    id = new int[n];
    for (int i = 0; i < n; i++) {
      id[i] = i;
    }
  }

  /**
   * Adds a union between p and q
   */
  public void union(int p, int q) {
    if (!find(p, q)) {
      int pid = id[p];
      for (int i = 0; i < id.length; i++) {
        if (id[i] == pid) {
          id[i] = id[q];
        }
      }
    }
  }

  /**
   * Finds if p and q are connected
   */
  public boolean find(int p, int q) {
    return id[p] == id[q];
  }

  public void print() {
    for (int i = 0; i < id.length; i++) {
      StdOut.print(id[i] + " ");
    }
    StdOut.println();
  }

  public static void main(String[] args) {
    int n = StdIn.readInt();
    QuickFind qf = new QuickFind(n);

    while (!StdIn.isEmpty()) {
      int p = StdIn.readInt();
      int q = StdIn.readInt();

      qf.union(p, q);
      qf.print();
    }
  }
}
