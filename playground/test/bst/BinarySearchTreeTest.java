/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package bst;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinarySearchTreeTest {
  @Test
  @DisplayName("should put and get correctly")
  void putAndGet() {
    BinarySearchTree<String, String> tree = new BinarySearchTree<>();
    tree.put("first", "one");
    tree.put("second", "two");

    assertEquals(null, tree.get(null));
    assertEquals(null, tree.get("unknown"));
    assertEquals("one", tree.get("first"));
    assertEquals("two", tree.get("second"));

    tree.put("second", "override");
    assertEquals("override", tree.get("second"));
  }

  @Test
  @DisplayName("should delete correctly")
  void delete() {
    BinarySearchTree<Integer, String> tree = new BinarySearchTree<>();
    assertEquals(0, tree.size());

    tree.put(1, "one");
    tree.delete(1);
    assertEquals(0, tree.size());

    tree.put(1, "one");
    tree.put(2, "three");
    tree.put(3, "two");

    tree.delete(2);
    assertEquals(2, tree.size());
  }

  @Test
  @DisplayName("should compute the correct size")
  void size() {
    BinarySearchTree<Integer, String> tree = new BinarySearchTree<>();
    assertEquals(0, tree.size());
  
    tree.put(1, "one");
    assertEquals(1, tree.size());

    tree.put(2, "three");
    tree.put(3, "two");

    assertEquals(3, tree.size());
  }

  @Test
  @DisplayName("should compute the correct min")
  void min() {
    BinarySearchTree<Integer, String> tree = new BinarySearchTree<>();
    assertEquals(null, tree.min());

    tree.put(1, "one");
    assertEquals(1, tree.min());

    tree.put(2, "three");
    tree.put(3, "two");

    assertEquals(1, tree.min());
  }

  @Test
  @DisplayName("should traverse in order")
  void inOrder() {
    BinarySearchTree<String, String> tree = new BinarySearchTree<>();
    tree.put("first", "one");
    tree.put("third", "three");
    tree.put("second", "two");

    String[] keys = new String[tree.size()];
    int i = 0;
    for (String key : tree.iterator()) {
      keys[i++] = key;
    }

    assertArrayEquals(new String[] { "first", "second", "third" }, keys);
  }
}
