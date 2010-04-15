/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ses.math.Geometry;

/**
 *
 * @author seth.sims
 */
public class GeometryUtil {
    /** Calculates the signed area of the points p0, p1 and p2. This is actually
     *  twice the area of the triangle formed by the three points.
     *
     * @param p0 The first point
     * @param p1 The second point
     * @param p2 The third point
     * @return The signed area of a quadralateral formed by vectors p1 - p0 and p2 - p0
     */
    public static double area2(Point2d p0, Point2d p1, Point2d p2) {
        return (p1.x - p0.x) * (p2.y - p0.y) - (p2.x - p0.x) * (p1.y - p0.y);
    } //end area2

    /** Returns true if the canidate point is strictly to the left of the line
     *  formed by p0 and p1. In particular the three points form a counter
     *  clockwise triangle.
     *
     * @param p0 The first point on the line
     * @param p1 The second point on the line
     * @param canidate The point that is being consitered
     * @return true if this point is left of the infinite line through p0 and p1
     */
    public static boolean isLeft(Point2d p0, Point2d p1, Point2d canidate) {
        return area2(p0, p1, canidate) > 0.0001;
    } //end isLeft

    /** Returns true if the canidate point is to the left or on the line formed
     *  by p0 and p1. In particular the three points (p0, p1, canidate) form a
     *  counter clockwise triangle.
     *
     * @param p0 The first point on the line
     * @param p1 The second point on the line
     * @param canidate The point that is being consitered
     * @return true if this point is left or on the infinite line through p0 and p1
     */
    public static boolean isLeftOn(Point2d p0, Point2d p1, Point2d canidate) {
        return area2(p0, p1, canidate) > -0.0001;
    } //end isLeftOn
} //end class GeometryUtil
