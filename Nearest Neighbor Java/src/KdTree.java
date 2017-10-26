import java.util.ArrayList;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class KdTree  {
    private Node root;
    private ArrayList<Point2D> a = new ArrayList<Point2D>();
    
    private class Node {
        private Node left, right;
        private Point2D key;
        private int o;
        private int count = 0;
        
        public Node(Point2D p, int orientation) {
            this.key = p;
            this.o = orientation;
            this.left = null;
            this.right = null;
        }
    }
    
   public KdTree() {
       // construct an empty set of points 
   }
   public boolean isEmpty() {
       return root == null;// is the set empty? 
   }
   public int size() { 
       return size(root); 
       }
   private int size(Node x) {
       if (x == null) 
           return 0;
       return x.count;
   }
   public void insert(Point2D p) {
       root = insert (root,p,0);
       // add the point to the set (if it is not already in the set)
   }
   private Node insert(Node x, Point2D p, int orientation) {
       if(x == null) {
           a.add(p);
           return new Node (p,orientation);
       } else {
           if(orientation == 0) {
               if(p.x() < x.key.x()) {
                   orientation = orientation - 1;
                   insert(x.left,p,orientation);
               } else {
                   orientation = orientation - 1;
                   insert(x.right,p,orientation);
               }
           } else {
               if(p.y() < x.key.y()) {
                   orientation = orientation - 1;
                   insert(x.left,p,orientation);
               } else {
                   orientation = orientation - 1;
                   insert(x.right,p,orientation);
           }    
       }
           
    }
       x.count = 1 + size(x.left) + size(x.right);
       return x;
   }
   public boolean contains(Point2D p) {
       Node x = root;
       while (x != null) {
           if(x.o == 0) {
               if(p.x() < x.key.x()) {
                   x.o = x.o - 1;  
                   x = x.left;
                   } else if (p.x() > x.key.x()) {
                   x.o = x.o - 1;
                   x = x.right;
                   } else if (p.x() == x.key.x()) {
                   return true;
                   } else {
                       if(p.y() < x.key.y()) {
                           x.o = x.o - 1;  
                           x = x.left;
                           } else if (p.x() > x.key.x()) {
                           x.o = x.o - 1;
                           x = x.right;
                           } else if (p.x() == x.key.x()) {
                           return true;
                           }
                       }
               }
           }
    return false;
   }
   public void draw() {
       for (Point2D point : a) {
           point.draw();
       }
       // draw all points to standard draw 
   }   
   public Iterable<Point2D> range(RectHV rect) {
       ArrayList<Point2D> l = new ArrayList<Point2D>();
       for (Point2D item : a) {
           if (rect.contains(item)) {
               l.add(item);  // all points that are inside the rectangle 
               }
           }
    return l;
   }
   public Point2D nearest(Point2D p) {
       ArrayList<Double> l = new ArrayList<Double>();
       ArrayList<Point2D> m = new ArrayList<Point2D>();
       if(a == null) {
           return null;// a nearest neighbor in the set to point p; null if the set is empty 
       } else {
           for (Point2D item : a) {
               l.add(p.distanceTo(item));
               m.add(item);  
           }
        double min = l.get(0);
        Point2D min1 = m.get(0);
        for(int i = 1 ;i < l.size(); i++) {
            if(min > l.get(i)) {
                min = l.get(i);
                min1 = m.get(i);
            }
        }
        return min1;
        }  
   }

   public static void main(String[] args) {
       RectHV rect = new RectHV(0.5, 0.5, 1.0, 1.0);
       rect.draw();
       StdDraw.enableDoubleBuffering();
       KdTree kdtree = new KdTree();
       while (true) {
           if (StdDraw.mousePressed()) {
               double x = StdDraw.mouseX();
               double y = StdDraw.mouseY();
               Point2D p = new Point2D(x, y);
               if (rect.contains(p)) {
                   StdOut.printf("%8.6f %8.6f\n", x, y);
                   kdtree.insert(p);
                   StdDraw.clear();
                   kdtree.draw();
                   StdDraw.show();
               } else {
                   System.out.println("Not within range");
               }
           }
           StdDraw.pause(50);
       }
   }              // unit testing of the methods (optional) 
}