import java.util.ArrayList;
import java.util.Collections;

import edu.princeton.cs.algs4.StdDraw;

public class RectHV {
    private double xmin;
    private double ymin;
    private double xmax;
    private double ymax;
    
   public RectHV(double xmin, double ymin, double xmax, double ymax) { 
       this.xmin = xmin;
       this.xmax = xmax;
       this.ymax = ymax;
       this.ymin = ymin;
       if(xmin > xmax || (ymin > ymax)) {
           throw new IllegalArgumentException();
       }
   }
   
   public double xmin() {                         // minimum x-coordinate of rectangle
       return this.xmin;
       }
   public  double ymin() {                           // minimum y-coordinate of rectangle 
       return this.ymin;
       }
   public  double xmax() {
       return this.xmax;// maximum x-coordinate of rectangle 
   }
   public  double ymax() {
       return this.ymax;// maximum y-coordinate of rectangle 
   }
   public boolean contains(Point2D p) {
       if ((p.x() >= xmin && p.x() <= xmax) && (p.y() >= ymin && p.y() <= ymax))
               return true;
       return false;
       // does this rectangle contain the point p (either inside or on boundary)? 
   }
   public boolean intersects(RectHV that) {
       if(this.ymin > that.ymax || that.ymin > this.ymax) 
           return false;
       if (this.xmax < that.xmin || that.xmax < this.xmin)
           return false;
       if(this.xmax > that.xmax && this.ymax > that.ymax && this.xmin < that.xmin && this.ymin < that.ymin)
           return false;
       if(that.xmax < this.xmax && that.ymax < this.ymax && that.xmin > this.xmin && that.ymin > this.ymin)
           return false;
       return true;// does this rectangle intersect that rectangle (at one or more points)? 
   }
   public  double distanceTo(Point2D p) {
       ArrayList<Double>dis = new ArrayList<Double>();
       Point2D r = new Point2D(xmin,ymin);
       dis.add(p.distanceTo(r));
       Point2D r1 = new Point2D(xmin,ymax);
       dis.add(p.distanceTo(r1));
       Point2D r2 = new Point2D(xmax,ymin);
       dis.add(p.distanceTo(r2));
       Point2D r3 = new Point2D(xmax,ymax);
       dis.add(p.distanceTo(r3));
       Collections.sort(dis);
       return dis.get(0);
       // Euclidean distance from point p to closest point in rectangle 
   }
   public  double distanceSquaredTo(Point2D p) {
       return (this.distanceTo(p)*this.distanceTo(p));
       // square of Euclidean distance from point p to closest point in rectangle 
   }
   public boolean equals(Object other) {
       if (other == this) return true;
       if (other == null) return false;
       if (other.getClass() != this.getClass()) return false;
       RectHV that = (RectHV) other;
       if (this.xmin != that.xmin) return false;
       if (this.ymin != that.ymin) return false;
       if (this.xmax != that.xmax) return false;
       if (this.ymax != that.ymax) return false;
       return true;// does this rectangle equal that object? 
   }
   public    void draw() {
       StdDraw.line(xmin, ymin, xmax, ymin);
       StdDraw.line(xmax, ymin, xmax, ymax);
       StdDraw.line(xmax, ymax, xmin, ymax);
       StdDraw.line(xmin, ymax, xmin, ymin);
   }                          // draw to standard draw 
   public  String toString() {
       return "[" + xmin + ", " + xmax + "] x [" + ymin + ", " + ymax + "]";
   }
}