/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KdTree {

  private class Node {
    // the whole point of this
    public Point2D point;
    // size of the subtrees (1 by default for a new node)
    public int size = 1;
    // left subtree
    Node left;
    // right subtree
    Node right;
    // rectangle containing the point and its subtrees
    RectHV rect;

    Node(Point2D p, RectHV r) {
      point = p;
      rect = r;
    }
  }

  private Node root;

  private Point2D nearest;

  // construct an empty set of points
  public KdTree() {
  }

  // is the set empty?
  public boolean isEmpty() {
    return root == null;
  }

  // number of points in the set
  public int size() {
    return size(root);
  }

  // the size of a node
  private int size(Node node) {
    if (node == null) {
      return 0;
    }

    return node.size;
  }

  private void validate(Object p) {
    if (p == null) {
      throw new IllegalArgumentException("What's the point ?");
    }
  }

  // add the point to the set (if it is not already in the set)
  public void insert(Point2D p) {
    validate(p);
    root = insert(root, p, 0, null, false);
  }

  private Node insert(Node node, Point2D point, int level, Node parent, boolean goingLeft) {
    if (node == null) {
      return new Node(point, getContainingRectangle(parent, goingLeft, level - 1));
    }

    // the point already exist in the set
    if (node.point.equals(point)) {
      return node;
    }

    int comparison = compare(point, node, level);
    int nextLevel = 1 + level;

    if (comparison < 0) {
      node.left = insert(node.left, point, nextLevel, node, true);
    }
    else {
      node.right = insert(node.right, point, nextLevel, node, false);
    }

    // recompute the size
    node.size = 1 + size(node.left) + size(node.right);
    return node;
  }

  private RectHV getContainingRectangle(Node parent, boolean goLeft, int level) {
    if (parent == null) {
      return new RectHV(0, 0, 1, 1);
    }
    else {
      double xmin = parent.rect.xmin();
      double xmax = parent.rect.xmax();
      double ymin = parent.rect.ymin();
      double ymax = parent.rect.ymax();

      boolean overrideX = level % 2 == 0;
      if (overrideX) {
        double x = parent.point.x();
        if (goLeft) {
          xmax = x;
        }
        else {
          xmin = x;
        }
      }
      else {
        double y = parent.point.y();
        if (goLeft) {
          ymax = y;
        }
        else {
          ymin = y;
        }
      }
      return new RectHV(
        Math.min(xmin, xmax),
        Math.min(ymin, ymax),
        Math.max(xmin, xmax),
        Math.max(ymin, ymax)
      );
    }
  }

  // should the point go to the left or right of the node
  private int compare(Point2D point, Node node, int level) {
    Comparator<Point2D> comparator = level % 2 == 0 ? Point2D.X_ORDER : Point2D.Y_ORDER;
    return comparator.compare(point, node.point);
  }

  // does the set contain point p?
  public boolean contains(Point2D p) {
    validate(p);
    return contains(root, p, 0);
  }

  private boolean contains(Node node, Point2D p, int level) {
    if (node == null) {
      return false;
    }

    if (node.point.equals(p)) {
      return true;
    }

    int comparison = compare(p, node, level);
    Node child = comparison < 0 ? node.left : node.right;
    return contains(child, p, 1 + level);
  }

  // draw all points to standard draw
  public void draw() {
    // **use your imagination :)**
  }

  // all points that are inside the rectangle (or on the boundary)
  public Iterable<Point2D> range(RectHV rect) {
    validate(rect);
    List<Point2D> pointsInRange = new ArrayList<>();
    searchPointsInRange(root, rect, pointsInRange);
    return pointsInRange;
  }

  // search the points belonging to the rect in the subtrees of the given node
  private void searchPointsInRange(Node node, RectHV rect, List<Point2D> pointsInRange) {
    // shall we continue ?
    if (node != null && node.rect.intersects(rect)) {
      if (rect.contains(node.point)) {
        pointsInRange.add(node.point);
      }
      searchPointsInRange(node.left, rect, pointsInRange);
      searchPointsInRange(node.right, rect, pointsInRange);
    }
  }

  // a nearest neighbor in the set to point p; null if the set is empty
  public Point2D nearest(Point2D p) {
    validate(p);
    if (root == null) return null;

    nearest = root.point;
    searchNearestPoint(root, p, 0);
    return nearest;
  }

  // search the nearest
  private void searchNearestPoint(Node node, Point2D point, int level) {
    List<Node> children = getOrderedChildren(node, point, level);
    for (Node child : children) {
      double distanceToChampion = nearest.distanceSquaredTo(point);
      double distanceToSubtree = child.rect.distanceSquaredTo(point);

      // is the current nearest further away ?
      if (distanceToChampion > distanceToSubtree) {
        double distanceToPoint = child.point.distanceSquaredTo(point);
        if (distanceToChampion > distanceToPoint) {
          nearest = child.point;
        }

        searchNearestPoint(child, point, 1 + level);
      }
    }
  }

  // orders the children so that the first we will look into will have the highest probability
  // of containing the nearest point
  private List<Node> getOrderedChildren(Node node, Point2D point, int level) {
    List<Node> children = new ArrayList<>();
    if (node == null) return children;

    Node left = node.left;
    Node right = node.right;

    if (left != null && right != null) {
      // pick a side
      if (compare(point, node, level) > 0) {
        children.add(right);
        children.add(left);
      } else {
        children.add(left);
        children.add(right);
      }
    }
    else if (left != null) {
      children.add(left);
    }
    else if (right != null) {
      children.add(right);
    }

    return children;
  }
}
