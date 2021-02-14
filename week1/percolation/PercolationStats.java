/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double ONE_96 = 1.96;
    private final int n;
    private final int trials;
    private double[] results;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (Integer.min(n, trials) <= 0) {
            throw new IllegalArgumentException("arguments are negative");
        }

        this.n = n;
        this.trials = trials;

        runSimulation();
    }

    private void printResults() {
        StdOut.println("mean = " + mean());
        StdOut.println("stddev = " + stddev());
        StdOut.println("95% confidence interval = [" + confidenceLo() + ", " + confidenceHi() + "]");
    }

    private void runSimulation() {
        results = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                p.open(row, col);
            }
            results[i] = ((double) p.numberOfOpenSites()) / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        double mean = mean();
        double stdDev = stddev();

        return mean - ((ONE_96 * stdDev) / Math.sqrt((double) trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        double mean = mean();
        double stdDev = stddev();

        return mean + ((ONE_96 * stdDev) / Math.sqrt((double) trials));
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats simulation = new PercolationStats(n, trials);
        simulation.printResults();
    }
}
