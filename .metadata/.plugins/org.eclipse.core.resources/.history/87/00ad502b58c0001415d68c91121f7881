/*
 * @author JL Xia
 * Princeton Algorithm Assignment5
 * 
 */
public class KdTree {
	   
	   private int size; 
	   private Node root;
	   
	   private final boolean Xdirection = true;
	   private final boolean Ydirection = false;
	   
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
		   //if (this.contains(p)) return;
		   if (root == null) {
			   root = new Node(p, null, null, null);
               root.direction = Xdirection;
               size ++;
		   }
		   else {
		   Node current = root;
		   boolean togo = (current.direction) ? p.x() < current.node.x() : p.y() < current.node.y();
		   Node togonode = (togo)? current.left : current.right;
		   while(togonode != null) {
			   current = togonode;
			   if (current.node.equals(p)) return;
			   togo = (current.direction) ? p.x() < current.node.x() : p.y() < current.node.y();
			   togonode = (togo)? current.left : current.right;
		   }
		   Node toadd = new Node(p, current, null, null);
		   if (togo) current.setleft(toadd);
		   else current.setright(toadd);
		   size++;
		   }
	   }
	   
	   // does the Tree contain point p? 
	   public boolean contains(Point2D p) {
		   Node current = root;
		   if (p.equals(current.node)) return true;
		   boolean togo = (current.direction) ? p.x() < current.node.x() : p.y() < current.node.y();
		   Node togonode = (togo)? current.left : current.right;
           while (togonode != null) {
        	   current = togonode;
    		   if (p.equals(current.node)) return true;
			   togo = (current.direction) ? p.x() < current.node.x() : p.y() < current.node.y();
			   togonode = (togo)? current.left : current.right;
           }		   
           return false;
	   }
	   
	   // draw all points to standard draw 
	   public void draw() {
		    StdDraw.setXscale(0, 1);   //setup scale for drawing
	        StdDraw.setYscale(0, 1); 
		    Node current = root;
		    drawHelper(current);
	   }
	   
	   private void drawHelper(Node current) {
          if (current == null) return;
          StdDraw.setPenColor(StdDraw.BLACK);
          StdDraw.point(current.node.x(), current.node.y());
          /*
          if (current.direction) {
              StdDraw.setPenColor(StdDraw.RED);
              StdDraw.setPenRadius(.002);
              double x0 = current.node.x();
              double y0 = (current.parent == null) ? 0 : (current.node.y() < current.parent.node.y()) ? 0: current.parent.node.y();
              double y1 = (current.parent == null) ? 1 : (current.node.y() < current.parent.node.y()) ? current.parent.node.y() : 1;
              StdDraw.line(x0, y0, x0, y1);
          }
          else {
        	  StdDraw.setPenColor(StdDraw.BLUE);
        	  StdDraw.setPenRadius(.002);
              double x0 = (current.node.x() < current.parent.node.x()) ? 0: current.parent.node.x();
              double x1 = (current.node.x() < current.parent.node.x()) ? current.parent.node.x() : 1;
              double y0 = current.node.y();
              StdDraw.line(x0, y0, x1, y0);
          }
          */
          while (current.left != null) drawHelper(current.left);
          while (current.right != null) drawHelper(current.right);
	   }

	   // all points that are inside the rectangle 
	   public Iterable<Point2D> range(RectHV rect) {
		   return null;
	   }
	   
	   // a nearest neighbor in the set to point p; null if the set is empty 
	   public Point2D nearest(Point2D p) {
		   return null;
	   }
	   
	   
/********************
 * 	  private class,  Node of the kdTree
 ********************/
	   private class Node{
		   private Point2D node;
		   private Node parent, left, right;
		   private boolean direction;
		   public Node(Point2D p, Node parent, Node left, Node right){
			   node = p;
			   this.parent = parent;
			   this.left = left;
			   this.right = right;
			   if (parent == null) direction = Xdirection;
			   else direction = ! parent.direction;
		   }
		   public Node getleft(Node node) {
			   return node.left;
		   }
		   public Node getright(Node node) {
			   return node.right;
		   }
		   public Node getparent(Node node) {
			   return node.parent;
		   }
		   public void setleft(Node left) {
			   this.left = left;
		   }
		   public void setright(Node right) {
			   this.right = right;
		   }
		   public void setparent(Node parent) {
			   this.parent = parent;
		   }
	   }
	   // unit testing of the methods (optional) 

	   public static void main(String[] args) {
		   Point2D p1 = new Point2D(.7, .2);
		   Point2D p2 = new Point2D(.5, .4);
		   Point2D p3 = new Point2D(.2, .3);
		   Point2D p4 = new Point2D(.4, .7);
		   Point2D p5 = new Point2D(.9, .6);
		   Point2D p6 = new Point2D(.9, .6);
		   
		   KdTree pic = new KdTree();

		   pic.insert(p1);
		   pic.insert(p2);
		   pic.insert(p3);
		   pic.insert(p4);
		   pic.insert(p5);
		   pic.insert(p6);

           //System.out.println(pic.root.node.toString());
           //System.out.println(pic.root.left.node.toString());
           //System.out.println(pic.root.left.left.node.toString());
           //System.out.println(pic.root.left.right.node.toString());
           //System.out.println(pic.root.right.node.toString());
           
           System.out.println(pic.contains(p4));
           System.out.println(pic.contains(p5));

           
           StdDraw.clear();
           StdDraw.setPenRadius(.01);
           pic.draw();
	   }
	}
