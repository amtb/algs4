/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class StackUsingLinkedList<T> implements Stack<T> {
  private class Node {
    T value;
    public Node next;
  }

  private Node head = null;

  public void push(T item) {
    // add a new node at the beginning of the list
    Node node = new Node();
    node.value = item;
    node.next = head;
    head = node;
  }

  public T pop () {
    // remove the head
    T item = null;
    if (head != null) {
      item = head.value;
      head = head.next;
    }

    return item;
  }

  public boolean isEmpty() {
    return head == null;
  }
}
