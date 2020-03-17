import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final boolean[][] sites;
    private int openCount = 0;
    private final int size;
    private final int virtualTop;
    private final int virtualBottom;
    private final WeightedQuickUnionUF uf;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Parameter n must be greater than 0"); 
        }
        
        sites = new boolean[n][n];
        size = n;
        virtualTop = 0;
        virtualBottom = n*n+1;
        uf = new WeightedQuickUnionUF(n*n+2); //includes the 2 virtual sites (topSite is 0, bottomSite n*n+1)
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        checkBounds(row, col);

        if (!isOpen(row, col)) {
            if (row == 1) {
                //opening a site in first row needs to connect to the virtualTop
                uf.union(getIndex(row, col), virtualTop);
            }

            if (row == this.size) {
                //opening a site in last row needs to connect to the virtualBottom
                uf.union(getIndex(row, col), virtualBottom);
            }

            connectNeighbors(row, col);

            sites[row - 1][col - 1] = true;
            openCount++;
        }
    }

    private void checkBounds(int row, int col) {
        if (row <= 0 || row > this.size) {
            throw new IllegalArgumentException("Value for parameter 'row' is outside the bounds of the grid"); 
        }

        if (col <= 0 || col > this.size) {
            throw new IllegalArgumentException("Value for parameter 'col' is outside the bounds of the grid"); 
        }
    }

    //union any neighboring sites if they are already open
    private void connectNeighbors(int row, int col) {
        int nRow, nCol; //neighbor site
        //neighbor above
        nRow = row - 1; nCol = col;
        if (siteExists(nRow, nCol) && isOpen(nRow, nCol)) {
            uf.union(getIndex(nRow, nCol), getIndex(row, col));
        }
        
        //below
        nRow = row + 1; nCol = col;
        if (siteExists(nRow, nCol) && isOpen(nRow, nCol)) {
            uf.union(getIndex(nRow, nCol), getIndex(row, col));
        }
        
        //left
        nRow = row; nCol = col - 1;
        if (siteExists(nRow, nCol) && isOpen(nRow, nCol)) {
            uf.union(getIndex(nRow, nCol), getIndex(row, col));
        }
        
        //right
        nRow = row; nCol = col + 1;
        if (siteExists(nRow, nCol) && isOpen(nRow, nCol)) {
            uf.union(getIndex(nRow, nCol), getIndex(row, col));
        }
    }

    private boolean siteExists(int row, int col) {
        return (row > 0 && row <= this.size && col > 0 && col <= this.size);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkBounds(row, col);
        return sites[row - 1][col - 1];        
    }

    // is the site (row, col) full/connected to the top?
    public boolean isFull(int row, int col) {
        checkBounds(row, col);
        return uf.find(getIndex(row, col)) == uf.find(virtualTop);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(virtualTop) == uf.find(virtualBottom);
    }

    private int getIndex(int row, int col) {
        int id = (row * this.size) - (this.size - col);
        return id;
    }

    // //test client (optional)
    // public static void main(String[] args) {
    //     int n = 5000;
    //     //Stopwatch timer = new Stopwatch();
    //     Percolation perc = new Percolation(n);
    //     // perc.debug = true;

    //     // while (!perc.percolates()) {
    //     //     int row = StdRandom.uniform(1, n+1);
    //     //     int col = StdRandom.uniform(1, n+1);
    //     //     perc.open(row, col);
    //     // }

    //     // for (String key : perc.time.keySet()) {
    //     //     double time = perc.time.get(key);
    //     //     System.out.println(key + ": " + time);
    //     // }
    //     // System.out.println("Total: " + timer.elapsedTime());
    // }
}
