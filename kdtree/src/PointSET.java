/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class PointSET {
  // the set of points
  private final Set<Point2D> points;

  // construct an empty set of points
  public PointSET() {
    points = new TreeSet<>();
  }

  // is the set empty?
  public boolean isEmpty() {
    return points.isEmpty();
  }

  // number of points in the set
  public int size() {
    return points.size();
  }

  private void validate(Object p) {
    if (p == null) {
      throw new IllegalArgumentException("What's the point ?");
    }
  }

  // add the point to the set (if it is not already in the set)
  public void insert(Point2D p) {
    validate(p);
    points.add(p);
  }


  // does the set contain point p?
  public boolean contains(Point2D p) {
    validate(p);
    return points.contains(p);
  }

  // draw all points to standard draw
  public void draw() {
    for (Point2D p : points) {
      p.draw();
    }
  }

  // all points that are inside the rectangle (or on the boundary)
  public Iterable<Point2D> range(RectHV rect) {
    validate(rect);

    List<Point2D> pointsInRange = new ArrayList<>();
    for (Point2D point : points) {
      if (rect.contains(point)) {
        pointsInRange.add((point));
      }
    }

    return pointsInRange;
  }

  // a nearest neighbor in the set to point p; null if the set is empty
  public Point2D nearest(Point2D p) {
    validate(p);

    Point2D nearest = null;
    double minDistance = Double.POSITIVE_INFINITY;
    for (Point2D point : points) {
      double distance = point.distanceSquaredTo(p);
      if (distance < minDistance) {
        minDistance = distance;
        nearest = point;
      }
    }

    return nearest;
  }
}
