/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package bouncing_balls;

import edu.princeton.cs.algs4.StdDraw;

public class BouncingBalls {
  private static int NUMBER_OF_BALLS = 20;

  public static void main(String[] args) {
    Ball[] balls = new Ball[NUMBER_OF_BALLS];

    for (int i = 0; i < balls.length; i++) {
      balls[i] = new Ball();
    }

    while (true) {
      StdDraw.clear();
      for (int i = 0; i < balls.length; i++) {
        balls[i].move(0.5);
        balls[i].draw();
      }
      StdDraw.show();
      StdDraw.pause(50);
      StdDraw.enableDoubleBuffering();
    }
  }
}
