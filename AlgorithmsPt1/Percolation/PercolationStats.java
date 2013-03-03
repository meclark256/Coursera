public class PercolationStats {
    
    private double [] tholds;
    private Percolation p;
    private int numExperiments = 0;
    private int N;
    
   // perform T independent computational experiments on an N-by-N grid 
    public PercolationStats(int N, int T) {
        this.N = N;
        if (N <= 0 || T < 1) {
            throw new IllegalArgumentException();
        }
        tholds = new double[T];
        while (numExperiments < T) {
           performExperiment();
           numExperiments++;

        
        }


    }
    private void performExperiment() {
            int count = 0;
            p = new Percolation(N);
            while (!p.percolates()) {
                int x = StdRandom.uniform(1, N+1);
                int y = StdRandom.uniform(1, N+1);
                if (!p.isOpen(x, y)) {
                    p.open(x, y);
                    count++;
                }    
        }
        tholds[numExperiments] = (double) count / (N * N);    
        
    }
   // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(tholds);
    }
   // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(tholds);
    }
   // returns lower bound of the 95% confidence interval
    public double confidenceLo() {
        double themean = StdStats.mean(tholds);
        double standardev = StdStats.stddev(tholds);
        return themean - (1.96*standardev/Math.sqrt(tholds.length));
    }
   // returns upper bound of the 95% confidence interval
    public double confidenceHi() {
        double themean = StdStats.mean(tholds);
        double standardev = StdStats.stddev(tholds);
        return themean + (1.96*standardev/Math.sqrt(tholds.length));
    }
   // test client, described below
    public static void main(String[] args) {

    PercolationStats ps = new PercolationStats(50, 1000);

    double mean = ps.mean();
    double stddev = ps.stddev();
    System.out.println("mean is " + mean);
    System.out.println(stddev);
    System.out.println(ps.confidenceLo());
    System.out.println(ps.confidenceHi());
    }
}