/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class StackUsingArray implements Stack<String> {
  private static final int N = 25;
  private String[] items;
  private int size = 0;

  public StackUsingArray() {
    items = new String[N];
  }

  public String pop() {
    String item = null;
    if (size != 0) {
      item = items[--size];
      // avoid loitering
      items[size] = null;
    }
    return item;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public void push(String word) {
    items[size++] = word;
  }
}
