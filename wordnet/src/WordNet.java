/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycleX;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordNet {

  // the root to which all synsets point to
  private int root = -1;

  // nouns mapped to their ids
  private Map<String, List<Integer>> words;

  // synsets ids mapped to their content
  private Map<Integer, String> synsets;

  // the digraph
  private Digraph digraph;

  // shortest path ancestor helper
  private final SAP sapHelper;

  // constructor takes the name of the two input files
  public WordNet(String synsets, String hypernyms) {
    validate(synsets);
    validate(hypernyms);

    createSynsets(synsets);
    addHypernyms(hypernyms);

    validateRootedDAG();
    sapHelper = new SAP(digraph);
  }

  private void createSynsets(String fileName) {
    List<String[]> lines = readCSV(fileName);

    digraph = new Digraph(lines.size());
    words = new HashMap<>();
    synsets = new HashMap<>();

    for (String[] line : lines) {
      int id = Integer.parseInt(line[0]);
      String[] nouns = line[1].split(" ");
      synsets.put(id, line[1]);
      for (String noun : nouns) {
        List<Integer> ids = words.get(noun);
        if (ids == null) {
          ids = new ArrayList<>();
          words.put(noun, ids);
        }
        ids.add(id);
      }
    }
  }

  private void addHypernyms(String fileName) {
    List<String[]> lines = readCSV(fileName);
    for (String[] ids : lines) {
      int id = Integer.parseInt(ids[0]);
      for (int i = 1; i < ids.length; i++) {
        digraph.addEdge(id, Integer.parseInt(ids[i]));
      }
    }
  }

  private List<String[]> readCSV(String fileName) {
    List<String[]> lines = new ArrayList<>();
    In in = new In(fileName);
    while (!in.isEmpty()) {
      String line = in.readLine();
      lines.add(line.split(","));
    }
    return lines;
  }

  // validates that the object is not null
  private void validate(Object object) {
    if (object == null) throw new IllegalArgumentException();
  }

  // validates that the noun is part of the nouns in the wordnet
  private void validateNoun(String noun) {
    if (!isNoun(noun)) throw new IllegalArgumentException();
  }

  // is the digraph rooted and acyclic ?
  private void validateRootedDAG() {
    // the root should be the only one not having outgoing edges
    for (int v = 0; v < digraph.V(); v++) {
      if (digraph.outdegree(v) == 0) {
        if (root != -1) throw new IllegalArgumentException("Not rooted");
        root = v;
      }
    }

    // the shouldn't be no cycles in the graph
    DirectedCycleX directedCycleDetector = new DirectedCycleX(digraph);
    if (directedCycleDetector.hasCycle()) throw new IllegalArgumentException("Not a DAG");
  }


  // returns all WordNet nouns
  public Iterable<String> nouns() {
    return words.keySet();
  }

  // is the word a WordNet noun?
  public boolean isNoun(String word) {
    validate(word);
    return words.containsKey(word);
  }

  // distance between nounA and nounB
  public int distance(String nounA, String nounB) {
    validateNoun(nounA);
    validateNoun(nounB);

    return sapHelper.length(words.get(nounA), words.get(nounB));
  }

  // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
  // in a shortest ancestral path
  public String sap(String nounA, String nounB) {
    validateNoun(nounA);
    validateNoun(nounB);

    int ancestor = sapHelper.ancestor(words.get(nounA), words.get(nounB));
    return synsets.get(ancestor);
  }
}
