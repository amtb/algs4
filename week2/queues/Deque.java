/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

  private class Node {
    Item value;
    Node next;
    Node previous;
  }

  private Node head;
  private Node tail;

  private int size;

  // construct an empty deque
  public Deque() {
  }

  // is the deque empty?
  public boolean isEmpty() {
    return size == 0;
  }

  // return the number of items on the deque
  public int size() {
    return size;
  }

  private void checkNullItem(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("Can't be null");
    }
  }

  // add the item to the front
  public void addFirst(Item item) {
    checkNullItem(item);

    // save a ref to the old head
    Node oldHead = head;

    // new node
    Node node = new Node();
    node.value = item;
    node.next = oldHead;

    if (oldHead != null) {
      oldHead.previous = node;
    }

    // move the head
    head = node;

    if (tail == null) {
      tail = head;
    }

    size += 1;
  }

  // add the item to the back
  public void addLast(Item item) {
    checkNullItem(item);

    Node oldTail = tail;

    Node node = new Node();
    node.value = item;
    node.previous = tail;

    if (oldTail != null) {
      oldTail.next = node;
    }

    tail = node;

    if (head == null) {
      head = tail;
    }

    size += 1;
  }

  private void checkEmptyDeque() {
    if (isEmpty()) {
      throw new NoSuchElementException("Empty deque");
    }
  }

  // remove and return the item from the front
  public Item removeFirst() {
    checkEmptyDeque();

    Node oldHead = head;
    Item item = oldHead.value;

    head = oldHead.next;
    if (head != null) {
      head.previous = null;
    }

    if (tail == oldHead) {
      tail = head;
    }

    size -= 1;
    return item;
  }

  // remove and return the item from the back
  public Item removeLast() {
    checkEmptyDeque();

    Node oldTail = tail;
    Item item = oldTail.value;

    tail = oldTail.previous;
    if (tail != null) {
      tail.next = null;
    }

    if (head == oldTail) {
      head = tail;
    }

    size -= 1;
    return item;
  }

  // return an iterator over items in order from front to back
  public Iterator<Item> iterator() {
    return new DequeIterator();
  }

  private class DequeIterator implements Iterator<Item> {
    Node current = head;

    public boolean hasNext() {
      return current != null;
    }

    public Item next() {
      if (!hasNext()) {
        throw new NoSuchElementException("Last stop");
      }

      Item item = current.value;
      current = current.next;
      return item;
    }

    public void remove() {
      throw new UnsupportedOperationException("Not available");
    }
  }

  public static void main(String[] args) {
    Deque<Integer> deque = new Deque<>();

    assert deque.size() == 0;

    deque.addFirst(0);
    assert deque.size() == 1;
    assert deque.head == deque.tail;

    deque.addFirst(1);
    assert deque.head != deque.tail;

    deque.addFirst(2);
    deque.addLast(1);
    deque.addLast(2);

    print("Initial: ", deque);

    int item = deque.removeLast();
    assert item == 2;

    item = deque.removeFirst();
    assert item == 2;

    assert deque.size() == 3;

    print("Removing head + tail: ", deque);

    deque.removeLast();
    deque.removeLast();

    assert deque.size() == 1;

    item = deque.removeFirst();
    assert item == 1;

    print("Now empty: ", deque);
  }

  private static <T> void print(String label, Deque<T> deque) {
    StdOut.print(label);
    for (Object i : deque) {
      StdOut.print(i + " ");
    }
    StdOut.println();
  }
}
