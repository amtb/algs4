/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
  private Item[] items;
  private int size;

  private Item[] newItems(int capacity) {
    return (Item[]) new Object[capacity];
  }

  // construct an empty randomized queue
  public RandomizedQueue() {
    items = newItems(8);
  }

  // is the randomized queue empty?
  public boolean isEmpty() {
    return size == 0;
  }

  // return the number of items on the randomized queue
  public int size() {
    return size;
  }

  private void resize(int newCapacity) {
    Item[] newItems = newItems(newCapacity);
    copyItems(items, newItems);
    items = newItems;
  }

  private void copyItems(Item[] source, Item[] destination) {
    for (int i = 0; i < size; i++) {
      destination[i] = source[i];
    }
  }

  // add the item
  public void enqueue(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("null item");
    }
    if (size == items.length) {
      resize(size * 2);
    }


    items[size] = item;
    size += 1;
  }

  // remove and return a random item
  public Item dequeue() {
    checkEmpty();

    int r = StdRandom.uniform(size);
    Item item = items[r];

    // swap with the last item
    items[r] = items[size - 1];
    items[size - 1] = null;

    size -= 1;

    int lengthBy4 = items.length / 4;
    if (lengthBy4 != 0 && size == lengthBy4) {
      resize(lengthBy4 * 2);
    }

    return item;
  }

  // return a random item (but do not remove it)
  public Item sample() {
    checkEmpty();

    int r = StdRandom.uniform(size);
    return items[r];
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
    Item[] toIterateOver;
    int current;

    public RandomizedQueueIterator() {
      toIterateOver = newItems(size);
      copyItems(items, toIterateOver);
      StdRandom.shuffle(toIterateOver);
    }

    public boolean hasNext() {
      return current != size;
    }

    public Item next() {
      if (current == size) {
        throw new NoSuchElementException("Last stop");
      }
      return toIterateOver[current++];
    }

    public void remove() {
      throw new UnsupportedOperationException("No can do");
    }
  }

  // unit testing (required)
  public static void main(String[] args) {

    RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();

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
    print("Again: ", randomizedQueue);

    randomizedQueue.dequeue();
    print("Dequeued: ", randomizedQueue);

    for (int i = 100; i < 200; i++) {
      randomizedQueue.enqueue(i);
    }
    print("A lot: ", randomizedQueue);
  }

  private static <T> void print(String label, RandomizedQueue<T> queue) {
    StdOut.print(label);
    for (Object i : queue) {
      StdOut.print(i + " ");
    }
    StdOut.println();
  }
}

