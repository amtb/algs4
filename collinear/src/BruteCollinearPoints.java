/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
  // the segments
  private final LineSegment[] segments;

  // finds all line segments containing 4 points
  public BruteCollinearPoints(Point[] points) {
    validate(points);
    Arrays.sort(points);
    validateUniques(points);
    this.segments = computeSegments(points.clone());
  }

  // computes the segments (bruteforce)
  private LineSegment[] computeSegments(Point[] points) {
    List<LineSegment> lineSegments = new ArrayList<>();
    int numberOfPoints = points.length;
    for (int i = 0; i < numberOfPoints; i++) {
      for (int j = i + 1; j < numberOfPoints; j++) {
        for (int k = j + 1; k < numberOfPoints; k++) {
          for (int m = k + 1; m < numberOfPoints; m++) {
            Point p = points[i];
            Point q = points[j];
            Point r = points[k];
            Point s = points[m];

            if (areAligned(p, q, r, s)) {
              lineSegments.add(new LineSegment(p, s));
            }
          }
        }
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

  // are p, q, r and s aligned
  private boolean areAligned(Point p, Point q, Point r, Point s) {
    double pqSlope = p.slopeTo(q);
    return pqSlope == p.slopeTo(r) && pqSlope == p.slopeTo(s);
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
