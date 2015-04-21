import java.awt.Color;
import java.util.Arrays;


/**
 *  The <tt>Graph</tt> class represents an undirected graph of vertices
 *  named 0 through <em>V</em> - 1.
 *  It supports the following two primary operations: add an edge to the graph,
 *  iterate over all of the vertices adjacent to a vertex. It also provides
 *  methods for returning the number of vertices <em>V</em> and the number
 *  of edges <em>E</em>. Parallel edges and self-loops are permitted.
 *  <p>
 *  This implementation uses an adjacency-lists representation, which 
 *  is a vertex-indexed array of {@link Bag} objects.
 *  All operations take constant time (in the worst case) except
 *  iterating over the vertices adjacent to a given vertex, which takes
 *  time proportional to the number of such vertices.
 *  
 *  <i>Algorithms, Princeton class
 *
 *  @author Jilin Xia
 *  
 */

public class SeamCarver {
	private final Picture picture;
	private double[][] energymatrix;
    /** 
    * Initial the SeamCarver class, using a given <tt>picture</tt> 
    * parameter picture	
    */
   public SeamCarver(Picture picture) {              
       this.picture = picture;
   }
   
   /**
    * Returns current picture.
    * @return current picture
    */
   public Picture picture() {                         
       return this.picture;
   }
   
   /**
    * Returns width of the current picture.
    * @return width of the current picture.
    */
   public     int width() {                        
       return picture.width();
   }
   
   /**
    * Returns height of the current picture.
    * @return height of the current picture.
    */
   public     int height() {                         
       return picture.height();
   }
   
   /**
    * Returns energy of pixel at column x and row y
    * @param x column number
    * @param y row number
    * @return energy of pixel at column x and row y
    */
   public  double energy(int x, int y) {              // energy of pixel at column x and row y
      if (x < 0 || x > this.width() - 1 || y < 0 || y > this.height() - 1) throw new java.lang.IndexOutOfBoundsException(" x or y is out of boundary");
      if (x == 0 || x == this.width() - 1 || y == 0 || y == this.height() - 1) 
    	  return 195075.0;
      Color prex = picture.get(x - 1, y);
      Color postx = picture.get(x + 1, y);
      Color prey = picture.get(x, y - 1);
      Color posty = picture.get(x, y + 1);
      return calenergy(prex.getRed() - postx.getRed(), prex.getGreen() - postx.getGreen(), prex.getBlue() - postx.getBlue()) + 
    		 calenergy(prey.getRed() - posty.getRed(), prey.getGreen() - posty.getGreen(), prey.getBlue() - posty.getBlue());     
   }
   
   /**
    * private method to calculate square sum of red, green, blue
    * @param red color or pixel
    * @param green color or pixel
    * @param blue color or pixel
    * @return square sum of red, green, blue
    */   
   private double calenergy(int red, int green, int blue) {
	    return Math.pow(red, 2) + Math.pow(blue, 2) + Math.pow(green,2);
   }

   /**
    * method to find Horizontal Seam
    * @return Array of 
    */
   double [][] distance;
   public   int[] findVerticalSeam() {              // sequence of indices for horizontal seam
       getenergyMatrix();
       int[][] edgeTo = new int[this.height()][this.width()];
       distance = new double[this.height()][this.width()];
       for (int j = 0; j < this.width(); j++) {
    	   distance[0][j] = energymatrix[0][j];
    	   //System.out.println(distance[0][j]);
       }
       for (int i = 1; i < this.height(); i++) {
    	   for (int j = 1; j < this.width() - 1; j++) {
    		   System.out.println(distance[i-1][j-1] +":"+ distance[i-1][j]);
    		   double temp = Math.min(Math.min(distance[i-1][j-1], distance[i-1][j]), distance[i-1][j+1]);
    		   System.out.println(temp)	;
    		   edgeTo[i][j] = (temp == distance[i-1][j-1]) ? -1 : (temp == distance[i-1][j]) ? 0: 1;
    	   }
       }
       int shortestdistance = 0;
       for (double[] ii: distance){
    	   System.out.println(Arrays.toString(ii));
       }
       for (int k = 1; k <this.width(); k++) {
    	   if (distance[this.height() - 1][k] < distance[this.height() - 1][shortestdistance])
    		   shortestdistance = k;
       }
       int[] shortestpath = new int[this.height()];
       shortestpath[this.height() - 1] = shortestdistance;
       System.out.println("short" + shortestdistance);
       for (int l = this.height() - 2; l >= 0; l--) {
    	   shortestpath[l] = shortestpath[l + 1] + edgeTo[l+1][shortestpath[l + 1]];
       }
       return shortestpath;
       
       }

  
   
   /**
    * private method to get the energy matrix
    * @return energy matrix
    */
   private double[][] getenergyMatrix() {
	   energymatrix = new double[this.height()][this.width()];
	   for (int i = 0; i < this.height(); i++) {
		   for (int j = 0; j < this.width(); j++) {
			   energymatrix[i][j] = energy(j, i);
		   }
	   }
	   return energymatrix;
    }

   public   int[] findHorizontalSeam() {                // sequence of indices for vertical seam
       
	   
	   return null;
   }
   
   
   
   
   public    void removeHorizontalSeam(int[] seam) {   // remove horizontal seam from current picture
      if (seam.length == 0) throw new java.lang.NullPointerException("seam is empty");
      if (seam.length != this.width()) throw new java.lang.IllegalArgumentException("horizontal seam is with wrong size or not valid");
      if (this.height() == 1) throw new java.lang.IllegalArgumentException("height is smaller than 1");
   }
   
   public    void removeVerticalSeam(int[] seam) {     // remove vertical seam from current picture
	  if (seam.length == 0) throw new java.lang.NullPointerException("seam is empty");
      if (seam.length != this.height()) throw new java.lang.IllegalArgumentException("vertical seam is with wrong size or not valid");
      if (this.width() == 1) throw new java.lang.IllegalArgumentException("width is smaller than 1");

   
   }
   
   public static void main(String[] args) {
	   Picture inputImg = new Picture("./seamCarving/12x10.png");
       System.out.printf("image is %d pixels wide by %d pixels high.\n", inputImg.width(), inputImg.height());
       
       SeamCarver sc = new SeamCarver(inputImg);
       
       System.out.printf("Printing energy calculated for each pixel.\n");        

       for (int j = 0; j < sc.height(); j++)
       {
           for (int i = 0; i < sc.width(); i++)
               System.out.printf("%9.0f ", sc.energy(i, j));

           System.out.println();
       }
       System.out.println(Arrays.toString(sc.findVerticalSeam()));
       
    
   }
}

	   
	

