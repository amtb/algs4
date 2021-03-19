/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package bst;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<Key extends Comparable<Key>, Value> {
  private Node root;

  private class Node {
    Key key;
    Value value;
    Node left;
    Node right;
    int size;

    Node(Key k, Value v, int s) {
      key = k;
      value = v;
      size = s;
    }
  }

  // puts a key/value in the tree
  public void put(Key key, Value value) {
    root = put(root, key, value);
  }

  private Node put(Node node, Key key, Value value) {
    if (node == null) {
      return new Node(key, value, 1);
    }

    int compare = key.compareTo(node.key);
    // key to add should go to the left
    if (compare < 0) {
      node.left = put(node.left, key, value);
    }
    else if (compare > 0) {
      node.right = put(node.right, key, value);
    }
    else {
      node.value = value;
    }

    node.size = 1 + size(node.left) + size(node.right);

    return node;
  }

  public int size() {
    return size(root);
  }

  private int size(Node node) {
    if (node == null) {
      return 0;
    }
    return node.size;
  }

  // returns the value associated to the given key
  public Value get(Key key) {
    if (key == null) {
      return null;
    }

    return get(root, key);
  }

  private Value get(Node node, Key key) {
    if (node == null) {
      return null;
    }

    int compare = key.compareTo(node.key);
    // the key is in the left sub-tree
    if (compare < 0) {
      return get(node.left, key);
    }
    else if (compare > 0) {
      return get(node.right, key);
    }
    return node.value;
  }

  public void delete(Key key) {
    if (key == null) return;
    root = delete(root, key);
  }

  private Node delete(Node node, Key key) {
    if (node == null) return null;

    int compare = key.compareTo(node.key);
    if (compare < 0) {
      node.left = delete(node.left, key);
    }
    else if (compare > 0) {
      node.right = delete(node.right, key);
    }
    else { // key found
      // nothing on the right = replace the node with its left
      if (node.right == null) return node.left;
      // nothing on the left =  replace the node with its right
      if (node.left == null) return node.right;

      // both children are alive = replace the node with the min on the right
      Node nodeRef = node;

      // point to the min on the right
      node = min(node.right);
      // remove the min from the right
      node.right = deleteMin(nodeRef.right);
      // as you were
      node.left = nodeRef.left;
    }

    node.size = 1 + size(node.left) + size(node.right);
    return node;
  }

  // deletes the min element
  private Node deleteMin(Node node) {
    if (node == null) return null;
    // next left is empty, replace it with the node on the right
    if (node.left == null) return node.right;
    // keep on going left
    node.left = deleteMin(node.left);
    node.size = 1 + size(node.left) + size(node.right);
    return node;
  }

  public Key min() {
    Node minNode = min(root);
    if (minNode == null) {
      return null;
    }
    return minNode.key;
  }

  private Node min(Node node) {
    if (node == null) return null;

    Node n = node;
    while (n.left != null) {
      n = n.left;
    }

    return n;
  }

  public Iterable<Key> iterator() {
    List<Key> items = new ArrayList<>();
    traverseInOrder(root, items);
    return items;
  }

  private void traverseInOrder(Node node, List<Key> items) {
    if (node == null) return;
    traverseInOrder(node.left, items);
    items.add(node.key);
    traverseInOrder(node.right, items);
  }
}
