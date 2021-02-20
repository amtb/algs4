/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private final WeightedQuickUnionUF grid;
  private final int n;
  private final boolean[] openSites;
  private int openSitesCount;
  private final int virtualTop;
  private final int virtualBottom;

  // creates n-by-n grid, with all sites initially blocked
  public Percolation(int n) {
    this.n = n;
    validate(n);
    int nSquared = n * n;
    // add two virtual sites
    grid = new WeightedQuickUnionUF(nSquared + 2);
    openSites = new boolean[nSquared];
    openSitesCount = 0;
    virtualTop = nSquared;
    virtualBottom = 1 + nSquared;
  }

  /**
   * Checks if the given row/col is in range
   */
  private void validate(int coordinate) {
    if (coordinate <= 0 || coordinate > n) {
      throw new IllegalArgumentException("Out of range");
    }
  }

  /**
   * Returns the index in the grid
   */
  private int getIndexOf(int row, int col) {
    validate(row);
    validate(col);
    return (row - 1) * n + (col - 1);
  }

  private void openNeighbor(int site, int neighbor) {
    if (openSites[neighbor]) {
      grid.union(site, neighbor);
    }
  }

  // opens the site (row, col) if it is not open already
  public void open(int row, int col) {
    int site = getIndexOf(row, col);
    if (!openSites[site]) {
      openSitesCount += 1;
      openSites[site] = true;

      if (row > 1) {
        openNeighbor(site, getIndexOf(row - 1, col));
      }
      if (row < n) {
        openNeighbor(site, getIndexOf(row + 1, col));
      }

      if (col > 1) {
        openNeighbor(site, getIndexOf(row, col - 1));
      }
      if (col < n) {
        openNeighbor(site, getIndexOf(row, col + 1));
      }

      // when we open a node in the top or bottom row connect it to the virtual sites
      if (row == 1) {
        grid.union(virtualTop, site);
      }
      if (row == n) {
        grid.union(virtualBottom, site);
      }
    }
  }

  private boolean isOpenAtTop() {
    // check if there is an open site at the top
    boolean openAtTop = false;
    for (int i = 0; i < n; i++) {
      openAtTop = openAtTop || openSites[i];
      if (openAtTop) {
        break;
      }
    }

    return openAtTop;
  }

  // is the site (row, col) open?
  public boolean isOpen(int row, int col) {
    return openSites[getIndexOf(row, col)];
  }

  // is the site (row, col) full?
  public boolean isFull(int row, int col) {
    int site = getIndexOf(row, col);

    // stop if the site is not opened
    if (!openSites[site]) {
      return false;
    }

    if (!isOpenAtTop()) {
      return false;
    }

    // check if we have have connection to the top
    if (grid.find(virtualTop) != grid.find(site)) {
      return false;
    }

    return true;
  }

  // returns the number of open sites
  public int numberOfOpenSites() {
    return openSitesCount;
  }

  // does the system percolate?
  public boolean percolates() {
    return isOpenAtTop() && (grid.find(virtualTop) == grid.find(virtualBottom));
  }
}
