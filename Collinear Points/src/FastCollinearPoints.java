import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import edu.princeton.cs.algs4.StdOut;


public class FastCollinearPoints {
    static int count ;
    ArrayList<LineSegment> line = new ArrayList<LineSegment>();
    ArrayList<Point> point = new ArrayList<Point>();
    
    
    public FastCollinearPoints(Point[] points) {
//      checkDuplicatedEntries(points);
      Point[] pointsCopy = Arrays.copyOf(points, points.length);
//      Arrays.sort(pointsCopy);

      for (Point startPoint : points) {
          Arrays.sort(pointsCopy, startPoint.slopeOrder());

          List<Point> slopePoints = new ArrayList<>();
          double slope = 0;
          double previousSlope = Double.NEGATIVE_INFINITY;

          for (int i = 1; i < pointsCopy.length; i++) {
              slope = startPoint.slopeTo(pointsCopy[i]);
              if (slope == previousSlope) {
                  slopePoints.add(pointsCopy[i]);
              } else {
                  if (slopePoints.size() >= 3) {
                      slopePoints.add(startPoint);
                      Collections.sort(slopePoints);
                      LineSegment seg = new LineSegment(slopePoints.get(0),slopePoints.get(slopePoints.size()-1));
                      line.add(seg);
                  }
                  slopePoints.clear();
                  slopePoints.add(pointsCopy[i]);
              }
              previousSlope = slope;
          }

          if (slopePoints.size() >= 3) {
              slopePoints.add(startPoint);
              Collections.sort(slopePoints);
              LineSegment seg = new LineSegment(slopePoints.get(0),slopePoints.get(slopePoints.size()-1));
              line.add(seg);
              
          }
      }
  }

      
       // finds all line segments containing 4 or more points
   public int numberOfSegments() {
           return line.size();
       // the number of line segments
   }
   public LineSegment[] segments() {
       LineSegment[] line1 = new LineSegment[line.size()];
       line.toArray(line1);
       return line1;
    
       // the line segments
   }
}