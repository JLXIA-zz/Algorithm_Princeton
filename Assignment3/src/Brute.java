import java.util.Arrays;


public class Brute {
	
     public static void main(String[] args){
    	 Point[] points; 
      	 In in = new In("./DB/collinear/rs1423.txt");      // input file
         //In in = new In(args[0]);
      	 int N = in.readInt(); 
         points=new Point[N];
         
         StdDraw.setXscale(0, 32768);   //setup scale for drawing
         StdDraw.setYscale(0, 32768);
         
      	 for(int i = 0; i < N; i++){
      		 points[i]=new Point(in.readInt(), in.readInt());
      		 points[i].draw();
      	 }
    	 
   
    	 for ( int i1 = 0; i1 < N; i1++){
    		 for ( int i2 = i1 + 1; i2 < N - 2; i2++){
    			 for ( int i3 = i2 + 1; i3 < N - 1; i3++){
    				 if (points[i1].slopeTo(points[i2]) != points[i1].slopeTo(points[i3])) break;
    				 for ( int i4 = i3 + 1; i4 < N; i4++){
                         if (points[i1].slopeTo(points[i2]) == points[i1].slopeTo(points[i4])){
                        	 printhelper(points[i1], points[i2], points[i3], points[i4]);     // output of the points in line
                         }
    				 }
    			 }
    		 }
    	 }
      }

	private static void printhelper(Point p, Point q, Point r, Point s) {
		 Point[] inorder={p, q, r, s};
		 Arrays.sort(inorder);
		 StdOut.println(inorder[0].toString() + " -> " + inorder[1].toString() + " -> " +  inorder[2].toString() + " -> " + inorder[3].toString());
	  
		 
		 inorder[0].drawTo(inorder[3]);
	   }
	}
