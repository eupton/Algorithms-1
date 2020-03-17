import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] trials;
    private double mean;
    private double stddev;
    private double lo;
    private double hi;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int t) {
        if (n <= 0) {
            throw new IllegalArgumentException("Parameter n must be > 0");
        }
        if (t <= 0) {
            throw new IllegalArgumentException("Parameter t must be > 0");
        }

        trials = new double[t];
    
        for (int i = 0; i < t; i++) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                int row = StdRandom.uniform(1, n+1);
                int col = StdRandom.uniform(1, n+1);
                perc.open(row, col);
            }
            double m = perc.numberOfOpenSites();
            trials[i] = m;
        }

        calculateStats();
    }

    private void calculateStats() {
        this.mean = StdStats.mean(trials);
        this.stddev = StdStats.stddev(trials);
        calculateConfidence();
    }

    private void calculateConfidence(){
        double confidenceLevel = 1.96;
        double temp = confidenceLevel * this.stddev / Math.sqrt(trials.length);
        this.lo = mean - temp;
        this.hi = mean + temp;
    }

    // sample mean of percolation threshold
    public double mean() {
        return this.mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return this.stddev;
    }

    // private double standardDeviation(int[] a) {
    //     double sum = 0;
    //     double mean = 0;
    //     double sqDiffSum = 0;
    //     double variance = 0;
    //     double dev = 0;
    //     //get mean
    //     for (var i = 0; i < a.length; i++) {
    //         sum += a[i];
    //     }

    //     mean = sum / a.length;

    //     //variance is square of diff from mean divided by population count
    //     //std dev is sqrt(variance)
    //     for (var i = 0; i < a.length; i++) {
    //         sqDiffSum  += (a[i] - mean) * (a[i] - mean);
    //     }

    //     variance = sqDiffSum / a.length;
    //     dev = Math.sqrt(variance);

    //     return dev;
    // }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.lo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.hi;
    }

   // test client (see below)
   public static void main(String[] args) {
        
        int n = 0;
        int t = 0;
        if (args.length == 2) {
            n =Integer.parseInt(args[0]);
            t = Integer.parseInt(args[1]);
        } //else {
        //     Scanner scanner = new Scanner(System.in);
        //     System.out.println("Enter a grid size");
        //     n = scanner.nextInt();
        //     System.out.println("Enter number of trials to run");
        //     t = scanner.nextInt();
        //     scanner.close();
        // }

        var percStats = new PercolationStats(n, t);
        System.out.println("mean                    = " + percStats.mean());
        System.out.println("stddev                  = " + percStats.stddev());
        System.out.println("95% confidence interval = [" + percStats.lo + ", " + percStats.hi + "]");
   }

}