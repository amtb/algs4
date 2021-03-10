/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package bouncing_balls;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class Ball {
  // default speed
  private static double SPEED = 0.01;

  // default radius
  private static double RADIUS = 0.005;

  private double x, y;
  private double vx, vy;

  private double radius;

  public Ball() {
    this(StdRandom.uniform(0.0, 1), StdRandom.uniform(0.0, 1), SPEED, SPEED, RADIUS);
  }

  public Ball(double x, double y, double vx, double vy, double radius) {
    this.x = x;
    this.y = y;
    this.vx = vx;
    this.vy = vy;
    this.radius = radius;
  }

  public void move(double speed) {
    double targetX = getTargetX(speed);
    double targetY = getTargetY(speed);

    // reverse the vector of movement when the target beyond the wall
    if (isOutOfBounds(targetX)) {
      vx = -vx;
    }

    if (isOutOfBounds(targetY)) {
      vy = -vy;
    }

    x = getTargetX(speed);
    y = getTargetY(speed);
  }

  private double getTargetX(double speed) {
    return getTarget(x, vx, speed);
  }

  private double getTargetY(double speed) {
    return getTarget(y, vy, speed);
  }

  private double getTarget(double position, double velocity, double speed) {
    return position + velocity * speed;
  }

  private boolean isOutOfBounds(double coordinate) {
    return coordinate - radius < 0 || coordinate + radius > 1.0;
  }

  public void draw() {
    StdDraw.filledCircle(x, y, radius);
  }
}
