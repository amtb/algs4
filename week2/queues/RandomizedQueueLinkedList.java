/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueueLinkedList<Item> implements Iterable<Item> {
  private class Node {
    Item value;
    Node next;
    Node previous;
  }

  private Node head;
  private int size;

  // construct an empty randomized queue
  public RandomizedQueueLinkedList() {
  }

  // is the randomized queue empty?
  public boolean isEmpty() {
    return size == 0;
  }

  // return the number of items on the randomized queue
  public int size() {
    return size;
  }

  // add the item
  public void enqueue(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("null item");
    }

    Node node = new Node();
    node.value = item;
    node.next = head;

    if (head != null) {
      head.previous = node;
    }

    head = node;
    size += 1;
  }

  // remove and return a random item
  public Item dequeue() {
    checkEmpty();

    int r = StdRandom.uniform(size);
    Node current = head;
    while (r > 0) {
      current = current.next;
      r -= 1;
    }

    Item item = current.value;
    Node previous = current.previous;
    Node next = current.next;

    if (head == current) {
      head = next;
    }

    if (previous != null) {
      previous.next = next;
    }

    if (next != null) {
      next.previous = previous;
    }

    current = null;

    size -= 1;
    return item;
  }

  // return a random item (but do not remove it)
  public Item sample() {
    checkEmpty();

    int r = StdRandom.uniform(size);
    Node current = head;
    while (r > 0) {
      current = current.next;
      r -= 1;
    }

    return current.value;
  }

  private void checkEmpty() {
    if (isEmpty()) {
      throw new NoSuchElementException("Empty queue");
    }
  }

  // return an independent iterator over items in random order
  public Iterator<Item> iterator() {
    return new RandomizedQueueIterator();
  }

  private class RandomizedQueueIterator implements Iterator<Item> {
    Item[] items;
    int current;

    public RandomizedQueueIterator() {
      items = (Item[]) new Object[size];
      Node node = head;
      int i = 0;
      while (node != null) {
        items[i] = node.value;
        node = node.next;
        i += 1;
      }
      StdRandom.shuffle(items);
    }

    public boolean hasNext() {
      return current != size;
    }

    public Item next() {
      if (current == size) {
        throw new NoSuchElementException("Last stop");
      }
      return items[current++];
    }

    public void remove() {
      throw new UnsupportedOperationException("No can do");
    }
  }

  // unit testing (required)
  public static void main(String[] args) {
    RandomizedQueueLinkedList<Integer> randomizedQueue = new RandomizedQueueLinkedList<>();

    assert randomizedQueue.size() == 0;

    randomizedQueue.enqueue(0);
    assert randomizedQueue.size() == 1;

    int item = randomizedQueue.dequeue();
    assert item == 0;
    assert randomizedQueue.isEmpty();

    randomizedQueue.enqueue(1);
    randomizedQueue.enqueue(2);
    randomizedQueue.enqueue(3);
    randomizedQueue.enqueue(4);
    assert randomizedQueue.size() == 4;

    print("First: ", randomizedQueue);
    print("Again: ", randomizedQueue);

    randomizedQueue.dequeue();
    print("Dequeued: ", randomizedQueue);
  }

  private static <T> void print(String label, RandomizedQueueLinkedList<T> queue) {
    StdOut.print(label);
    for (Object i : queue) {
      StdOut.print(i + " ");
    }
    StdOut.println();
  }
}
