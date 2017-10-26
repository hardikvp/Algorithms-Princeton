import edu.princeton.cs.algs4.StdDraw;

public class Point2D implements Comparable<Point2D> {
    private double x;
    private double y;
   public Point2D(double x, double y) {
       this.x = x;
       this.y = y;
   }
   public  double x() {
       return this.x; 
   }
   public  double y() {
       return this.y; 
   }
   public  double distanceTo(Point2D that) {
       double x1 = that.x - this.x;
       double y1 = that.y - this.y;
       return Math.sqrt((x1*x1) + (y1*y1));
   }
   public  double distanceSquaredTo(Point2D that) {
       double x1 = that.x - this.x;
       double y1 = that.y - this.y;
       return ((x1*x1) + (y1*y1));
   }
   public int compareTo(Point2D that) {
       if (this.y < that.y) return -1;
       if (this.y > that.y) return +1;
       if (this.x < that.x) return -1;
       if (this.x > that.x) return +1;
       return 0; 
   }
   public boolean equals(Object that) {
       if (this == that)
           return true;
       Point2D a = (Point2D)that;
       if ((a.x == this.x) && (a.y == this.y))
           return true;
       return false;
   }
   public void draw() {
       StdDraw.point(x, y);
   }
   public  String toString() {
    return "("+this.x +"," + this.y+")";
   }
}