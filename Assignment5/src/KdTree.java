import java.util.LinkedList;
import java.util.List;

/*
 * @author JL Xia
 * Princeton Algorithm Assignment5
 * 
 */
public class KdTree {
	   
	   private int size; 
	   private Node root;
	   
	   private static final boolean Xdirection = true;
	   private static final boolean Ydirection = false;
	   
	   // construct an empty Tree of points 
	   public KdTree(){
		   size = 0;
	   }
	  
	   // is the Tree empty? 
	   public boolean isEmpty() {
		   return size == 0;
	   }
	   // number of points in the Tree
	   public int size() {
		   return size;
	   }
	   // add the point to the Tree (if it is not already in the set)
	   public void insert(Point2D p) {
		   if (root == null) {
			   root = new Node(p, null, null, null);
               root.direction = Xdirection;
               size ++;
		   }
		   else {
		       Node current = check(root, p);
		       if (current.node.equals(p)) return;
		       else {
		    	   Node newnode = new Node(p, current, null, null);
				   boolean togo = (current.direction) ? p.x() < current.node.x() : p.y() < current.node.y();
		    	   if(togo) current.setleft(newnode);
		    	   else current.setright(newnode);
		    	   size++;
		           }
		       }
	       }
	   
	   // does the Tree contain point p? 
	   public boolean contains(Point2D p) {
		    if (this.size == 0) return false;
		    Node current = check(root, p);
		    if (current.node.equals(p)) return true;
		    else return false;
	   }
	   
	   private Node check(Node current, Point2D p) {
		   if (current.node.equals(p)) return current;
		   boolean togo = (current.direction) ? p.x() < current.node.x() : p.y() < current.node.y();
	       if (togo) {
	    	   if (current.left != null) return check(current.left, p);
	    	   else return current;
	       }
	       else {
	    	   if (current.right != null) return check(current.right, p);
	    	   else return current;
	       }
	   }

	// draw all points to standard draw 
	   public void draw() {
		    drawHelper(root);
	   }
	   
	   private void drawHelper(Node current) {
          if (current == null) return;
          
          StdDraw.setPenColor(StdDraw.BLACK);
          StdDraw.setPenRadius(.01);

          StdDraw.point(current.node.x(), current.node.y());
          if (current.direction) {
              StdDraw.setPenColor(StdDraw.RED);
              StdDraw.setPenRadius(.002);
              double x0 = current.node.x();
              double y0 = (current.parent == null) ? 0 : (current.node.y() < current.parent.node.y()) ? current.parent.rect.ymin(): current.parent.node.y();
              double y1 = (current.parent == null) ? 1 : (current.node.y() < current.parent.node.y()) ? current.parent.node.y() : current.parent.rect.ymax();
              StdDraw.line(x0, y0, x0, y1);
          }

          else {
          StdDraw.setPenColor(StdDraw.BLUE);
          StdDraw.setPenRadius(.002);
              double x0 = (current.node.x() < current.parent.node.x()) ? current.parent.rect.xmin(): current.parent.node.x();
              double x1 = (current.node.x() < current.parent.node.x()) ? current.parent.node.x() : current.parent.rect.xmax();
              double y0 = current.node.y();
              StdDraw.line(x0, y0, x1, y0);
          }
          
          if (current.left != null)  drawHelper(current.left);
          if (current.right != null) drawHelper(current.right);
          
	   }

	   private List<Point2D> insideP;
	   // all points that are inside the rectangle 
	   public Iterable<Point2D> range(RectHV rect) {
		   insideP = new LinkedList<Point2D>();
		   if (this.size == 0) return insideP;
		   rangeHelper(rect, root);
		   return insideP;
	   }
	   
	   private void rangeHelper(RectHV rect, Node current) {
		    if (rect.contains(current.node))  insideP.add(current.node);
		    if (current.left != null) {
		    	if (rect.intersects(current.left.rect)) rangeHelper(rect, current.left);
		    }
            if (current.right != null) {
		    	if (rect.intersects(current.right.rect)) rangeHelper(rect, current.right);
		    }
            return;
	   }
	   
	   private Point2D nearestP;
	   // a nearest neighbor in the set to point p; null if the set is empty 
	   public Point2D nearest(Point2D p) {
		   if (this.size == 0) return null;
		   nearestP = root.node;
		   nearestHelper(root, p);
		   return nearestP;
	   }
	   
	   private void nearestHelper(Node current, Point2D p) {
		   nearestP = (p.distanceSquaredTo(nearestP) < p.distanceSquaredTo(current.node)) ? nearestP : current.node;
		   if (current.left != null) {
			   if (current.left.rect.distanceSquaredTo(p) < nearestP.distanceSquaredTo(p)) nearestHelper(current.left, p); 
		   }
		   if (current.right != null) {
			   if (current.right.rect.distanceSquaredTo(p) < nearestP.distanceSquaredTo(p)) nearestHelper(current.right, p); 
		   }
	   }
	   
	   
/********************
 * 	  private class,  Node of the kdTree
 ********************/
	   private class Node{
		   private Point2D node;
		   private Node parent, left, right;
		   private RectHV rect;
		   private boolean direction;
		   public Node(Point2D p, Node parent, Node left, Node right){
			   node = p;
			   this.parent = parent;
			   this.left = left;
			   this.right = right;
			   if (parent == null) direction = Xdirection;
			   else direction = ! parent.direction;
			   if (parent == null) rect = new RectHV(0, 0, 1, 1);
			   else {
				   if (parent.direction) {
					   if (p.x() < parent.node.x()) rect = new RectHV(parent.rect.xmin(), parent.rect.ymin(), parent.node.x(), parent.rect.ymax());
					   else                         rect = new RectHV(parent.node.x(), parent.rect.ymin(), parent.rect.xmax(), parent.rect.ymax());
				   }
				   else {
					   if (p.y() < parent.node.y()) rect = new RectHV(parent.rect.xmin(), parent.rect.ymin(), parent.rect.xmax(), parent.node.y());
					   else                         rect = new RectHV(parent.rect.xmin(), parent.node.y(), parent.rect.xmax(), parent.rect.ymax());
				   }
			   }
		   }
		   
		public void setright(Node node) {
            this.right = node;			
		}

		public void setleft(Node node) {
		    this.left = node;	 
		}
		
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
		   
		   KdTree pic = new KdTree();

		   pic.insert(p1);
		   pic.insert(p2);
		   pic.insert(p3);
		   pic.insert(p4);
		   pic.insert(p5);
		   pic.insert(p6);
		   pic.insert(p7);
		   pic.insert(p8);
		   pic.insert(p9);
		   //pic.insert(p10);

		   pic.draw();
		   
		   System.out.println(pic.contains(p10));
		   System.out.println(pic.size());
            
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
		   
		   Point2D nearestP = pic.nearest(p);
	       StdDraw.setPenColor(StdDraw.RED);
	       nearestP.draw();
       
	   }
	   
	}
