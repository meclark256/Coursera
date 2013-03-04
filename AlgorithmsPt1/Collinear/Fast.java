import java.util.Arrays;
public class Fast {
    
    private static void printLineSeg(Point[] seg) {
        String output = new String();
        for (int i = 0; i < seg.length; i++) {
            output += seg[i].toString();
            if (i < (seg.length-1) ) {
                output += " -> ";
            }
        }
        StdOut.println(output);
        seg[0].drawTo(seg[seg.length-1]);
    }
    
    private static void outputLineSegments( Point origin, Point[] sortedPoints, int marker, int adjpoints) {
        Point[] seg = new Point[adjpoints+1];
        for (int i = 0; i < adjpoints; i++) {
            seg[i] = sortedPoints[marker + i];
        }
        seg[adjpoints] = origin;
        Arrays.sort(seg);
        if (origin.compareTo(seg[0]) == 0)
            printLineSeg(seg);
        
    }
    
    private static void findLineSegments( Point origin, Point[] sortedPoints) {

        int marker = 1;
        int adjpoints = 1;

        double firstslope = origin.slopeTo(sortedPoints[1]);
        for (int j = 2; j < sortedPoints.length; j++) {
            double currentslope = origin.slopeTo(sortedPoints[j]);
            if (currentslope == firstslope) {
                adjpoints += 1;
            }
            else if (adjpoints >= 3) {
                outputLineSegments(origin,sortedPoints, marker, adjpoints);
                marker = j;
                adjpoints = 1;
                firstslope = currentslope;
                
            }
            else {
                adjpoints = 1;
                marker = j;
                firstslope = currentslope;
            }
            
        }
        if (adjpoints >=3){
            outputLineSegments(origin,sortedPoints,marker,adjpoints);
        }    
               
    }
    
    public static void main(String[] args) {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Point[] points = new Point[N];
        Point[] sortedPoints = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p  = new Point(x,y);
            points[i] = p;
            sortedPoints[i] = p;
            p.draw();
        }
        for (int i = 0; i < N; i++){
            Point origin = points[i];
            Arrays.sort(sortedPoints, origin.SLOPE_ORDER);
            findLineSegments(origin,sortedPoints);
                       
        }
        StdDraw.show(0);
    }
}