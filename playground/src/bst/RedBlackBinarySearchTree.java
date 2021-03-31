/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package bst;

public class RedBlackBinarySearchTree<Key extends Comparable<Key>, Value>
        extends BinarySearchTree<Key, Value> {

  static final boolean BLACK = false;
  static final boolean RED = true;

  private boolean isRed(Node node) {
    if (node == null) return BLACK;
    return node.color == RED;
  }

  private Node rotateLeft(Node node) {
    if (node == null) return null;
    // save references
    Node reference = node;
    // move node
    node = node.right;
    node.color = reference.color;

    reference.color = RED;
    reference.right = node.left;
    node.left = reference;

    return node;
  }

  private Node rotateRight(Node node) {
    if (node == null) return null;
    // save references
    Node reference = node;
    // move node
    node = node.left;
    node.color = reference.color;

    reference.color = RED;
    reference.left = node.right;
    node.right = reference;

    return node;
  }

  private void flipColors(Node node) {
    node.color = RED;
    node.left.color = BLACK;
    node.right.color = BLACK;
  }

  protected Node put(Node node, Key key, Value value) {
    if (node == null) {
      return new Node(key, value, 1, RED);
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

    // right child red, left child black => rotate left
    if (isRed(node.right) && !isRed(node.left)) {
      node = rotateLeft(node);
    }
    // left child, left-left grandchild red => rotate right
    if (isRed(node.right) && isRed(node.left.left)) {
      node = rotateRight(node);
    }
    // both children red => flip colors
    if (isRed(node.right) && isRed(node.left)) {
      flipColors(node);
    }

    node.size = 1 + size(node.left) + size(node.right);

    return node;
  }
}
