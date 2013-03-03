public class Percolation {
    

   private WeightedQuickUnionUF qf;
   private boolean[][] open;
   private int length;
   private int sidesize;
   
   public Percolation(int N) { // create N-by-N grid, with all sites blocked
       length = N*N+2;
       sidesize = N;
       qf = new WeightedQuickUnionUF(length);
       open = new boolean [N][N];
   }
   // convert grid coordinates to 1d
   private int coordinateToInteger(int x, int y) {
    return sidesize*(x-1) + y;   
   }    
   // open site (row i, column j) if it is not already
   public void open(int i, int j)  {
           outOfBounds(i, j);
           if (!siteLocation(i, j)) {
               setSite(i, j, true);
               //if at top, link to virtual top site
            if (i == 1) {
                   qf.union(0, coordinateToInteger(i, j));
               }
               //if at bottom, link to virtual bottom site
               if (i == sidesize) {
                  qf.union(length-1, coordinateToInteger(i, j));
               }
               //link top site
               link(i, j , i-1, j);
               }
               //link bottom site
               link(i, j, i+1, j);
               //link right site
               link(i, j, i, j+1);
               //link left site
               link(i, j, i, j-1);            
             
   }
    
   
   // is site (row i, column j) open?    
   public boolean isOpen(int i, int j) {
        outOfBounds(i, j);
        return siteLocation(i, j);
   }
   
   //check validity of indices
   private void outOfBounds(int i, int j) {
       if (i < 1 || i > sidesize || j < 1 || j > sidesize) {
          String message = String.format("Indices [%d, %d] are outside bounds [%d]",
        i, j, sidesize);
      throw new IndexOutOfBoundsException(message);
       }
   }
   
   private boolean indicesOkay(int i, int j) {
       if (i <= 0 || i > sidesize || j <= 0 || j > sidesize) {
           return false;
       }
       return true;
   }
   
   // is site (row i, column j) full?    
   public boolean isFull(int i, int j) {   
        outOfBounds(i, j);
        return isOpen(i, j) && qf.connected(0, coordinateToInteger(i, j));  
   }  
   // does the system percolate?
   public boolean percolates() {
       return qf.connected(0, length-1);
       
       
   }
   
   private boolean siteLocation(int i, int j) {
       return open[i-1][j-1];
   }
   private void setSite(int i, int j, boolean val) {
       open[i-1][j-1] = val;
   }
   private void link(int x1, int y1, int x2, int y2) {
       if (indicesOkay(x2, y2)) {
           if (isOpen(x2, y2)) {
               qf.union(coordinateToInteger(x1, y1), coordinateToInteger(x2, y2));
           }
       }  
   }
   
   
   public static void main(String[] args) {
        Percolation perc = new Percolation(4);
        perc.open(1, 1);
        perc.open(2, 3);
        perc.open(3, 2);
        perc.open(3, 1);
        System.out.println(perc.isFull(3, 1));
        perc.open(2, 1);
        perc.open(4, 3);
        perc.open(3, 3);
        System.out.println("System percolates: " + perc.percolates());
    }

   
}