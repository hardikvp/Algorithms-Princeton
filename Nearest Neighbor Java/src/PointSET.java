import java.util.ArrayList;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;


public class PointSET {
    private RbBST<Point2D> p;
    
   public PointSET() {
       p = new RbBST<Point2D>();// construct an empty set of points 
       }
   public boolean isEmpty() {
       return p.size() == 0;// is the set empty? 
       }
   public int size() {
       return p.size();// number of points in the set 
       }
   public void insert(Point2D a) {
       p.put(a);// add the point to the set (if it is not already in the set)
       }
   public boolean contains(Point2D a) {
       return p.contains(a);// does the set contain point p? 
       }
   public void draw() {
       for (Point2D point : p.inorder()) {
           point.draw();
       }
   }                        // draw all points to standard draw 
   public Iterable<Point2D> range(RectHV rect) {
       ArrayList<Point2D> l = new ArrayList<Point2D>();
       p.inorder();
       for (Point2D item : p.inorder()) {
           if (rect.contains(item)) {
               l.add(item);  // all points that are inside the rectangle 
           }
       }
    return l;
   }    
   public Point2D nearest(Point2D a) {
       ArrayList<Double> l = new ArrayList<Double>();
       ArrayList<Point2D> m = new ArrayList<Point2D>();
       p.inorder();
       if(p == null) {
           return null;// a nearest neighbor in the set to point p; null if the set is empty 
       } else {
           for (Point2D item : p.inorder()) {
               l.add(a.distanceTo(item));
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
   
   
 public static void main(String args[]) {
       RectHV rect = new RectHV(0.5, 0.5, 1.0, 1.0);
       rect.draw();
       StdDraw.enableDoubleBuffering();
       PointSET kdtree = new PointSET();
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
       
   }
}