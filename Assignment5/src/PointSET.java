/*
 *@author: JL XIA
 * 
 */


import java.util.TreeSet;


public class PointSET {
	
	TreeSet<Point2D> pointset; 
	int size; 
   // construct an empty set of points 
   public PointSET() {
	   pointset = new TreeSet<Point2D>();
	   size = 0;
   }
   // is the set empty? 
   public boolean isEmpty() {
	   return size == 0;
   }
   // number of points in the set 
   public int size() {
	   return size;
   }
   // add the point to the set (if it is not already in the set)
   public void insert(Point2D p) {
	   pointset.add(p);
   }
   // does the set contain point p? 
   public boolean contains(Point2D p) {
	   return pointset.contains(p);
   }
   // draw all points to standard draw 
   public void draw() {
	   
	   
	   for (Point2D point : pointset){
         StdDraw.point(point.x(), point.y());
	   }
   }
   // all points that are inside the rectangle 
   public Iterable<Point2D> range(RectHV rect) {
	   TreeSet<Point2D> insidepoint = new TreeSet<Point2D>();
	   for (Point2D point : pointset){
		   if (rect.contains(point)) insidepoint.add(point);
	   }
	   return insidepoint;
   }
   // a nearest neighbor in the set to point p; null if the set is empty 
   public Point2D nearest(Point2D p) {
	   Point2D nearestP = pointset.first();
	   for (Point2D point : pointset) {
		   if (point.distanceTo(p) < point.distanceTo(nearestP))
			   nearestP = point;
	   }
	   return nearestP;
   }
   // unit testing of the methods (optional) 

   public static void main(String[] args) {
	   Point2D p1 = new Point2D(7, 2);
	   Point2D p2 = new Point2D(5, 4);
	   Point2D p3 = new Point2D(2, 3);
	   Point2D p4 = new Point2D(4, 7);
	   Point2D p5 = new Point2D(9, 6);
	   
	   PointSET pic = new PointSET();
	   pic.insert(p1);
	   pic.insert(p2);
	   pic.insert(p3);
	   pic.insert(p4);
	   pic.insert(p5);
	   
	   pic.draw();

       //System.out.println(pic.root.node.toString());
       //System.out.println(pic.root.left.node.toString());
       //System.out.println(pic.root.left.left.node.toString());
       //System.out.println(pic.root.left.right.node.toString());
       //System.out.println(pic.root.right.node.toString());
       
       System.out.println(pic.contains(p4));

   }
}
