import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
  public static void main(String[] args) {
    String champion = null;
    int i = 0;
    while (!StdIn.isEmpty()) {
      String word = StdIn.readString();

      i += 1;
      double p = 1 / ((double) i);
      if (StdRandom.bernoulli(p)) {
        champion = word;
      }
    }

    StdOut.println(champion);
  }
}
