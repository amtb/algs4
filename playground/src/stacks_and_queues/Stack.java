/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public interface Stack<T> {
  /**
   * Pushes on to the stack
   * @param item
   */
  void push(T item);

  /**
   * Pops the last inserted item from the stack
   * @return
   */
  T pop();

  /**
   * Is the stack empty ?
   * @return
   */
  boolean isEmpty();
}
