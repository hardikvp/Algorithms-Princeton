import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    static int count;
    ArrayList<LineSegment> line = new ArrayList<LineSegment>();
    ArrayList<Point> point = new ArrayList<Point>();
    
   public BruteCollinearPoints(Point[] points) {
       for(int i=0 ; i < points.length-3; i++) {
           for(int j = i+1 ; j < points.length-2 ; j++) {
               for(int k = j+1 ; k< points.length-1 ; k++) {
                   for (int l = k+1 ; l< points.length ;l++) {
                       if(points[i].slopeTo(points[j])== points[i].slopeTo(points[k]) && points[i].slopeTo(points[j])== points[i].slopeTo(points[l])) {
                           count ++;
                           Point[] point2 = new Point[4];
                           point2[0] = points[i];
                           point2[1] = points[j];
                           point2[2] = points[k];
                           point2[3] = points[l];
                           Arrays.sort(point2);
                           point.add(point2[0]);
                           point.add(point2[3]);
                           LineSegment seg = new LineSegment(point2[0],point2[3]);
                           line.add(seg);

                           }   
                       }
                   }    
               }
           }
       }
   public int numberOfSegments() {
       return count;
   }
   public LineSegment[] segments() {
       LineSegment[] line1 = new LineSegment[line.size()];
       line.toArray(line1);
    return line1;
   }
}