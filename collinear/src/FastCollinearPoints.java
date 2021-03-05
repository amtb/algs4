/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FastCollinearPoints {

  private final Point[] points;

  // the segments
  private final LineSegment[] segments;

  // finds all line segments containing 4 points
  public FastCollinearPoints(Point[] points) {
    if (points == null) {
      throw new IllegalArgumentException();
    }
    // make a copy
    this.points = points.clone();
    validateNotNull();

    Arrays.sort(this.points);
    validateUniques();
    this.segments = computeSegments();
  }

  // computes the segments
  private LineSegment[] computeSegments() {
    List<LineSegment> lineSegments = new ArrayList<>();

    for (int i = 0; i < points.length; i++) {
      Point origin = points[i];
      Comparator<Point> originSlopeOrder = origin.slopeOrder();

      Point[] lowerPoints = Arrays.copyOfRange(points, 0, i);
      Point[] upperPoints = Arrays.copyOfRange(points, i + 1, points.length);

      Arrays.sort(lowerPoints, originSlopeOrder);
      Arrays.sort(upperPoints, originSlopeOrder);

      double[] lowerPointsSlopes = getSlopes(lowerPoints, origin);

      int j = 0;
      while (j < upperPoints.length) {
        int k = findAlignedPoints(upperPoints, j, originSlopeOrder);

        // check if we have more than 3
        if (k - j >= 3) {
          Point end = upperPoints[k - 1];
          double slope = origin.slopeTo(end);

          // check if there isn't a bigger segment
          if (Arrays.binarySearch(lowerPointsSlopes, slope) < 0) {
            lineSegments.add(new LineSegment(origin, end));
          }
        }

        // jump pointer
        j = k;
      }
    }

    return lineSegments.toArray(new LineSegment[0]);
  }

  // look for adjacent points in the upper points from start
  private int findAlignedPoints(Point[] points, int start, Comparator<Point> originSlopeOrder) {
    int k = start + 1;
    while (k < points.length && originSlopeOrder.compare(points[start], points[k]) == 0) {
      k++;
    }

    return k;
  }

  // validates that points is not null and doesn't contain a null value
  private double[] getSlopes(Point[] listOfPoints, Point point) {
    double[] slopes = new double[listOfPoints.length];
    for (int i = 0; i < listOfPoints.length; i++) {
      slopes[i] = listOfPoints[i].slopeTo(point);
    }
    return slopes;
    }

  // validates that points doesn't contain a null value
  private void validateNotNull() {
    for (int i = 0; i < points.length; i++) {
      if (points[i] == null) {
        throw new IllegalArgumentException();
      }
    }
  }

  // check if two successful points (sorted already) are not the same
  private void validateUniques() {
    for (int i = 1; i < points.length; i++) {
      if (points[i - 1].compareTo(points[i]) == 0) {
        throw new IllegalArgumentException();
      }
    }
  }

  // the number of line segments
  public int numberOfSegments() {
    return segments.length;
  }

  // the line segments
  public LineSegment[] segments() {
    return segments.clone();
  }
}
