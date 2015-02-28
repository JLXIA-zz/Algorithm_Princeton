
import java.util.Arrays;

public class Fast {
    
    public static void main(String[] args) {
        
    	Point[] points; 
     	In in = new In("./DB/collinear/rs1423.txt");      // input file
        //In in = new In(args[0]);
     	int N = in.readInt(); 
        points=new Point[N];

        StdDraw.setXscale(0, 32768);   //setup scale for drawing
        StdDraw.setYscale(0, 32768);
        
     	 for(int i = 0; i < N; i++){
     		 points[i]=new Point(in.readInt(), in.readInt());
         }
   	 
     	Arrays.sort(points);
     	Point[] sortedPoints= new Point[N];
		System.arraycopy(points, 0, sortedPoints, 0, points.length);
		 
		Point lastpoint = null;

        for (int i = 0; i < N; i++) {
            Point p = points[i];
            p.draw();
            // Sort all points acording that pointslope
            Arrays.sort(sortedPoints, i, N, p.SLOPE_ORDER);
            int j = i + 1;  

	     	 while (j < N - 1) {
		     	 int count = 1; 
	             double slope = p.slopeTo(sortedPoints[j]);
	             while ((j+count) < N && p.slopeTo(sortedPoints[j+count]) == slope){
	            	   count++;
	             }
	             if (count > 2 && sortedPoints[j + count -1] != lastpoint ) {
	            	    StdOut.print(p + " -> ");
  	                	for (int l = 0; l < count; l++){
	                		StdOut.print(sortedPoints[j + l]);
	    		        	if (l != count-1 ) StdOut.print(" -> ");
	    		        	else p.drawTo((sortedPoints[j + l]));
	    		        }	         
	                	StdOut.println("");
	             }
		        	lastpoint = sortedPoints[j + count -1] ;
	                j = j + count;
	             }

	         }
		}
}