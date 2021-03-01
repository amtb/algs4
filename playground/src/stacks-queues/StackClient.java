/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class StackClient {
  public static void main(String[] args) {
    Stack<String>[] stacks = new Stack[] {
            new StackUsingArray(), new StackUsingLinkedList<String>()
    };

    while (!StdIn.isEmpty()) {
      String word = StdIn.readString();

      if ("-".equals(word)) {
        for (Stack<String> stack : stacks) {
          StdOut.print(String.format("%s ", stack.pop()));
        }
      }
      else {
        for (Stack<String> stack : stacks) {
          stack.push(word);
        }
      }
    }
  }
}
