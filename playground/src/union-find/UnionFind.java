/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public interface UnionFind {
  /**
   * Adds a union between p and q
   */
  public void union(int p, int q);

  /**
   * Finds if p and q are connected
   */
  public boolean find(int p, int q);
}
