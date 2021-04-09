/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class Outcast {
  // the wordnet
  private final WordNet wordnet;

  // constructor takes a WordNet object
  public Outcast(WordNet w) {
    wordnet = w;
  }

  // given an array of WordNet nouns, return an outcast
  public String outcast(String[] nouns) {
    int n = nouns.length;
    int[][] distances = new int[n][];
    for (int i = 0; i < n; i++) {
      distances[i] = new int[n];
    }

    // compute the distance matrix
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        int d = wordnet.distance(nouns[i], nouns[j]);
        distances[i][j] = d;
        distances[j][i] = d;
      }
    }

    // find the outcast
    int max = -1;
    String candidate = null;
    for (int i = 0; i < n; i++) {
      int distance = 0;
      for (int j = 0; j < n; j++) {
        distance += distances[i][j];
      }

      if (distance > max) {
        max = distance;
        candidate = nouns[i];
      }
    }

    return candidate;
  }
}
