/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdOut;

public class Main {
  public static void main(String[] args) {
    String filename = args[0];
    In in = new In(filename);
    KdTree kdtree = new KdTree();
    while (!in.isEmpty()) {
      double x = in.readDouble();
      double y = in.readDouble();
      Point2D p = new Point2D(x, y);
      kdtree.insert(p);
    }

    // StdOut.println(kdtree.range(new RectHV(0.51, 0.43, 0.71, 0.58)));
    StdOut.println(kdtree.nearest(new Point2D(0.873, 0.43)));
  }
}
