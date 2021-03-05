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
  // the segments
  private final LineSegment[] segments;

  // finds all line segments containing 4 points
  public FastCollinearPoints(Point[] points) {
    validate(points);
    Arrays.sort(points);
    validateUniques(points);
    this.segments = computeSegments(points.clone());
  }

  // computes the segments
  private LineSegment[] computeSegments(Point[] points) {
    List<LineSegment> lineSegments = new ArrayList<>();

    for (int i = 0; i < points.length; i++) {
      Point currentPoint = points[i];
      Comparator<Point> currentPointComparator = currentPoint.slopeOrder();
      Point[] remainingPoints = Arrays.copyOfRange(points, i + 1, points.length);
      Arrays.sort(remainingPoints, currentPointComparator);

      int j = 0;
      while (j < remainingPoints.length) {
        // look for adjacent points
        int k = j + 1;
        while (k < remainingPoints.length
                && currentPointComparator.compare(remainingPoints[j], remainingPoints[k]) == 0) {
          k++;
        }

        // check if we have more than 3
        if (k - j >= 3) {
          Point target = remainingPoints[k - 1];

          // check if there isn't a bigger segment
          boolean shouldAdd = true;
          for (int x = i - 1; x >= 0; x--) {
            if (currentPointComparator.compare(points[x], target) == 0) {
              shouldAdd = false;
              break;
            }
          }

          if (shouldAdd) {
            lineSegments.add(new LineSegment(currentPoint, target));
          }
        }

        j = k;
      }
    }

    return lineSegments.toArray(new LineSegment[lineSegments.size()]);
  }

  // validates that points is not null and doesn't contain a null value
  private void validate(Point[] points) {
    if (points == null) {
      throw new IllegalArgumentException();
    }

    for (int i = 0; i < points.length; i++) {
      if (points[i] == null) {
        throw new IllegalArgumentException();
      }
    }
  }

  // check if two successful points (sorted already) are not the same
  private void validateUniques(Point[] points) {
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
    return Arrays.copyOf(segments, segments.length);
  }
}
