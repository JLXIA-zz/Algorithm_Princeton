
public class test {
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
		   
		   
		   while (true) {

	            // the location (x, y) of the mouse
	            double x = StdDraw.mouseX();
	            double y = StdDraw.mouseY();
	            Point2D query = new Point2D(0., 0.);

	            // draw all of the points
	            StdDraw.clear();
	            StdDraw.setPenColor(StdDraw.BLACK);
	            StdDraw.setPenRadius(.01);
	            pic.draw();

	            // draw in red the nearest neighbor (using brute-force algorithm)
	            StdDraw.setPenRadius(.03);
	            StdDraw.setPenColor(StdDraw.RED);
	            pic.nearest(query).draw();
	            StdDraw.setPenRadius(.02);

	            // draw in blue the nearest neighbor (using kd-tree algorithm)
	            //StdDraw.setPenColor(StdDraw.BLUE);
	            //kdtree.nearest(query).draw();
	            StdDraw.show(0);
	            StdDraw.show(40);

	  }
	  }
}
