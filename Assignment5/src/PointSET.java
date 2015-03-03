/*
 *@author: JL XIA
 * 
 */


import java.util.TreeSet;


public class PointSET {
	
   private TreeSet<Point2D> pointset; 
   // construct an empty set of points 
   public PointSET() {
	   pointset = new TreeSet<Point2D>();
   }
   // is the set empty? 
   public boolean isEmpty() {
	   return this.pointset.isEmpty();
   }
   // number of points in the set 
   public int size() {
	   return this.pointset.size();
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
       StdDraw.setPenColor(StdDraw.BLACK);
       StdDraw.setPenRadius(.01);
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
	   if (this.size() == 0) return null;
	   Point2D nearestP = pointset.first();
	   for (Point2D point : pointset) {
		   if (p.distanceTo(point) < p.distanceTo(nearestP))
			   nearestP = point;
	   }
	   return nearestP;
   }
   // unit testing of the methods (optional) 

   public static void main(String[] args) {
	   Point2D p1 = new Point2D(0.206107, 0.095492);
	   Point2D p2 = new Point2D(0.975528, 0.654508);
	   Point2D p3 = new Point2D(0.024472, 0.345492);
	   Point2D p4 = new Point2D(0.793893, 0.095492);
	   Point2D p5 = new Point2D(0.793893, 0.904508);
	   Point2D p6 = new Point2D(0.975528, 0.345492);
	   Point2D p7 = new Point2D(0.206107, 0.904508);
	   Point2D p8 = new Point2D(0.5, 0.0);
	   Point2D p9 = new Point2D(0.024472, 0.654508);
	   Point2D p10 = new Point2D(0.5, 1.0);
	   
	   PointSET pic = new PointSET();

	   pic.insert(p1);
	   pic.insert(p2);
	   pic.insert(p3);
	   pic.insert(p4);
	   pic.insert(p5);
	   pic.insert(p6);
	   pic.insert(p7);
	   pic.insert(p8);
	   pic.insert(p9);
	   pic.insert(p10);

       pic.draw();

       RectHV rect = new RectHV(0.5, 0.5, 1, 1);
	   StdDraw.setPenRadius(.03);
       StdDraw.setPenColor(StdDraw.YELLOW);

       for (Point2D point : pic.range(rect)) {
    	   point.draw();
    	   StdOut.println(point.toString());

       }
       Point2D p = new Point2D(.9, .12);
       StdDraw.setPenColor(StdDraw.GREEN);
	   p.draw();
       StdDraw.setPenColor(StdDraw.RED);
	   pic.nearest(p).draw();

   }
}

