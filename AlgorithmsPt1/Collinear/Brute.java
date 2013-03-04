import java.util.Arrays;
public class Brute {
    
    public static void main(String[] args) {
        
         StdDraw.setXscale(0, 32768);
         StdDraw.setYscale(0, 32768);
         StdDraw.show(0);
        
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x,y);
            points[i] = p;
            p.draw();
        }

        for (int i = 0; i < (N-3); i++) {
            Point p1 = points[i];
            for (int j = i+1; j < (N-2); j++) {
                Point p2 = points[j];
                double slope1 = p1.slopeTo(p2);
                for (int k = j+1; k < N-1; k++) {
                    Point p3 = points[k];
                    double slope2 = p1.slopeTo(p3);
                    if (slope1 != slope2) continue;
                    for (int l = k+1; l < N; l++) {
                        Point p4 = points[l];
                        double slope3 = p1.slopeTo(p4);
                        if (slope1 == slope2 && slope1 == slope3) {
                            drawLine(p1,p2,p3,p4);
                        }
                    }
                }
                
            }
        }
        StdDraw.show(0);
        
    }
    
    private static void drawLine(Point p1, Point p2, Point p3, Point p4) {
       Point[] lines = new Point[4];
       lines[0] = p1;
       lines[1] = p2;
       lines[2] = p3;
       lines[3] = p4;
       Arrays.sort(lines);
       StdOut.println(lines[0] + " -> " + lines[1] + " -> " 
                          + lines[2] + " -> " + lines[3]);
       lines[0].drawTo(lines[3]);
    }
}