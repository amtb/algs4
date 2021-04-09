/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;

import java.util.Collections;

public class SAP {
  // 404
  private static final int MINUS_ONE = -1;

  // the digraph
  private final Digraph digraph;

  private class CommonAncestor {
    private final int v;
    private final int length;

    public CommonAncestor(int v, int len) {
      this.v = v;
      length = len;
    }
  }

  private class PathWalker {
    private final int n;
    private final int[] path;
    private final Queue<Integer> queue;
    private boolean[] visited;
    private PathWalker opposite;

    public PathWalker(int n, Iterable<Integer> vertices) {
      this.n = n;
      queue = new Queue<>();
      visited = new boolean[n];
      path = new int[n];

      for (int v : vertices) {
        validate(v);
        visited[v] = true;
        path[v] = Integer.MIN_VALUE;
        queue.enqueue(v);
      }
    }

    private void validate(int v) {
      if (v < 0 || v >= n) throw new IllegalArgumentException("What's this vertex ?");
    }

    public CommonAncestor walk() {
      int v = queue.dequeue();
      // have we met this vertex in the opposite direction ?
      CommonAncestor ancestor = checkOpposite(v);
      if (ancestor != null) return ancestor;

      visited[v] = true;
      for (int w : digraph.adj(v)) {
        if (!visited[w]) {
          path[w] = v;
          queue.enqueue(w);

          ancestor = checkOpposite(w);
          if (ancestor != null) return ancestor;
        }
      }

      return null;
    }

    private CommonAncestor checkOpposite(int v) {
      if (opposite.visited[v]) {
        return new CommonAncestor(v, distance(v) + opposite.distance(v));
      }

      return null;
    }

    // computes the distance from the vertex to the initial set of nodes we went to bfs from
    private int distance(int v) {
      int length = 0;
      while (path[v] != Integer.MIN_VALUE) {
        v = path[v];
        length += 1;
      }
      return length;
    }
  }

  // constructor takes a digraph (not necessarily a DAG)
  public SAP(Digraph digraph) {
    validate(digraph);
    this.digraph = new Digraph(digraph);
  }

  private CommonAncestor getCommonAncestor(int v, int w) {
    return getCommonAncestor(Collections.singleton(v), Collections.singleton(w));
  }

  // go bfs starting from the two set of vertices
  // keep on going until we meet a vertex that we visited in the other bfs
  // or until all vertices are visited on both side i.e no ancestor
  private CommonAncestor getCommonAncestor(Iterable<Integer> vs, Iterable<Integer> ws) {
    PathWalker walkFrom = new PathWalker(digraph.V(), vs);
    PathWalker walkerTo = new PathWalker(digraph.V(), ws);

    walkFrom.opposite = walkerTo;
    walkerTo.opposite = walkFrom;

    while (!walkFrom.queue.isEmpty() || !walkerTo.queue.isEmpty()) {
      if (!walkFrom.queue.isEmpty()) {
        CommonAncestor ancestor = walkFrom.walk();
        if (ancestor != null) return ancestor;
      }

      if (!walkerTo.queue.isEmpty()) {
        CommonAncestor ancestor = walkerTo.walk();
        if (ancestor != null) return ancestor;
      }
    }

    return null;
  }

  // length of shortest ancestral path between v and w; -1 if no such path
  public int length(int v, int w) {
    CommonAncestor ancestor = getCommonAncestor(v, w);
    if (ancestor == null) return MINUS_ONE;
    return ancestor.length;
  }

  // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
  public int ancestor(int v, int w) {
    CommonAncestor ancestor = getCommonAncestor(v, w);
    if (ancestor == null) return MINUS_ONE;
    return ancestor.v;
  }

  // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
  public int length(Iterable<Integer> v, Iterable<Integer> w) {
    validate(v);
    validate(w);

    CommonAncestor ancestor = getCommonAncestor(v, w);
    if (ancestor == null) return MINUS_ONE;
    return ancestor.length;
  }

  // a common ancestor that participates in shortest ancestral path; -1 if no such path
  public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
    validate(v);
    validate(w);

    CommonAncestor ancestor = getCommonAncestor(v, w);
    if (ancestor == null) return MINUS_ONE;
    return ancestor.v;
  }

  // validates that the object is not null
  private void validate(Object object) {
    if (object == null) throw new IllegalArgumentException();
  }

  // validates that the iterables
  private void validate(Iterable<Integer> items) {
    if (items == null) throw new IllegalArgumentException();
    for (Integer item : items) validate(item);
  }
}

