/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Main {
  public static void main(String[] args) {
    // WordNet w = new WordNet(args[0], args[1]);
    // StdOut.println(w.distance("Cystopteris_fragilis", "gudgeon"));

    In in = new In("test-files/digraph9.txt");
    Digraph G = new Digraph(in);
    SAP sap = new SAP(G);
    int v = 4;
    int w = 7;
    int length = sap.length(v, w);
    int ancestor = sap.ancestor(v, w);
    StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
  }
}
