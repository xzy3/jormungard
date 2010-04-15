/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ses.math.Geometry;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Stack;

/**
 *
 * @author seth.sims
 */
public class Polygon {
    private LinkedList<Point2d> list = null;

    public Polygon() {
        list = new LinkedList<Point2d>();
    } //end Polygon

    private void addVertex(Point2d p) {
        list.add(p);
    } //end addVertex

    /** Implements the simple Akl-Toussaint heuristics finds the max and min x
     *  and y. Then it makes copies of all the points outside the quadrilateral
     *  formed and returns them as a Collection. This discards many points that
     *  can not be on the convex hull of a set of points.
     *
     * @param pointSet A collection of points that the heuristic is run on
     * @return A new collections of copies of points that cannot be on the
     * convex hull of the points.
     */
    public static Collection<Point2d> convexHullHeuristic(Collection<Point2d> pointSet) {
        Point2d maxX = new Point2d(Double.MIN_VALUE, Double.MIN_VALUE);
        Point2d maxY = new Point2d(Double.MIN_VALUE, Double.MIN_VALUE);
        Point2d minX = new Point2d(Double.MAX_VALUE, Double.MAX_VALUE);
        Point2d minY = new Point2d(Double.MAX_VALUE, Double.MAX_VALUE);

        for(Point2d curr : pointSet) {
            if(maxX.x < curr.x) {
                maxX = curr;
            } //end if

            if(maxY.y < curr.y) {
                maxY = curr;
            } //end if

            if(minX.x > curr.x) {
                minX = curr;
            } //end if

            if(minY.y > curr.y) {
                minY = curr;
            } //end for
        } //end for

        LinkedList<Point2d> newSet = new LinkedList<Point2d>();

        newSet.add(minX);
        newSet.add(maxY);
        newSet.add(maxX);
        newSet.add(minY);

        for(Point2d curr : pointSet) {
           if(GeometryUtil.isLeft(minX, maxY, curr) ||
              GeometryUtil.isLeft(maxY, maxX, curr) ||
              GeometryUtil.isLeft(maxX, minY, curr) ||
              GeometryUtil.isLeft(minY, minX, curr)) {
               newSet.add(new Point2d(curr));
           } //end if
        } //end for

        return newSet;
    } //end convexHullHeuristic

    public static Polygon quickHull(Collection<Point2d> pointSet) {
       //run the huristic to cut down on the points we have to deal with
       Collection<Point2d> pSet = Polygon.convexHullHeuristic(pointSet);

       //find the points furthest right and left
       Point2d minX = new Point2d(Double.MAX_VALUE, Double.MAX_VALUE);
       Point2d maxX = new Point2d(Double.MIN_VALUE, Double.MIN_VALUE);

       for(Point2d curr : pSet) {
           if(curr.x < minX.x) {
               minX = curr;
           } else if(curr.x > maxX.x) {
               maxX = curr;
           } //end if
       } //end for

       //these are added seperatly from the rest of the points
       pSet.remove(minX);
       pSet.remove(maxX);

       //split the points along the line formed by the two maximum points
       LinkedList<Point2d> s1 = new LinkedList<Point2d>();
       LinkedList<Point2d> s2 = new LinkedList<Point2d>();

       for(Point2d curr : pSet) {
           if(GeometryUtil.isLeft(minX, maxX, curr)) {
               s1.add(curr);
           } else {
               s2.add(curr);
           } //end if else
       } //end for

       //this class holds the state of the QuickHull algorithm on the stack
       class QuickHullState {
           public LinkedList<Point2d> pointSet;
           public Point2d p0, p1;

           public QuickHullState(LinkedList<Point2d> set, Point2d p0, Point2d p1) {
               pointSet = new LinkedList<Point2d>();
               pointSet.addAll(set);

               this.p0 = p0;
               this.p1 = p1;
           } //end constructor
       } //end QuickHullState

       //ok here is the meat of the algorithm
       Stack<QuickHullState> st = new Stack<QuickHullState>();

       //push the two sets to the stack with appropriate information
       st.push(new QuickHullState(s2, maxX, minX));
       st.push(new QuickHullState(s1, minX, maxX));

       Polygon poly = new Polygon();
       while(!st.empty()) {
          QuickHullState currState = st.pop();
          poly.addVertex(currState.p0);

          //find the point that has the maximum perpendicular distance from
          //the current edge being examaned. that point has to be on the hull.
          Point2d maxPoint = null;
          double maxDist = Double.MIN_VALUE;

          //precalculate some numbers for the distance calculation
          double xDiff = currState.p1.x - currState.p0.x;
          double yDiff = currState.p1.y - currState.p0.y;
          double offset = currState.p0.x * currState.p1.y -
                          currState.p1.x * currState.p0.y;
          double denom = Math.sqrt(xDiff * xDiff + yDiff * yDiff);

          //ok now for the search
          for(Point2d currPoint : currState.pointSet) {
              double currDist = (yDiff * currPoint.x +
                                 xDiff * currPoint.y +
                                 offset) / denom;

              if(currDist > maxDist) {
                  maxPoint = currPoint;
                  maxDist = currDist;
              } //end if
          } //end for

          //Now partition the points into three sets those left of the new edges
          //are associated with that edge for further processing. Those right of
          //both edges are inside the polygon and no longer need to be consitered.
          s1.clear();
          s2.clear();
          for(Point2d currPoint : currState.pointSet) {
              if(GeometryUtil.isLeft(currState.p0, maxPoint, currPoint)) {
                  s1.add(currPoint);
              } else if(GeometryUtil.isLeft(maxPoint, currState.p1, currPoint)) {
                  s2.add(currPoint);
              } //end if
          } //end for

          if(!s2.isEmpty()) {
              st.push(new QuickHullState(s2, maxPoint, currState.p1));
          } else {
              //this point is normally added when s2 is popped from the stack
              //thats not happening here so we have to push it in this case
              poly.addVertex(maxPoint);
          } //end if

          if(!s1.isEmpty()) {
              st.push(new QuickHullState(s1, currState.p0, maxPoint));
          } //end if
       } //end while

       return poly;
    } //end quickHull
} //end class Polygon
