/* ParametricLine2d
 *
 * Copyright October 29, 2007 Seth Sims
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ses.math.Geometry;

import ses.math.MathBase;
import ses.math.linearAlgebra.Vector2d;

public class ParametricLine2d extends MathBase {

    public Point2d point;
    public Vector2d vector;

    public ParametricLine2d() { point = new Point2d(); vector = new Vector2d(); } //end constructor
    public ParametricLine2d(Point2d point, Vector2d vector) {
        this.point = new Point2d(point);
        this.vector = new Vector2d(vector);
    } //end constructor
    public ParametricLine2d(Point2d one, Point2d two) {
        this.point = new Point2d(one);
        this.vector = Vector2d.subtract(two.toVector2d(), one.toVector2d());
    } //end constructor

    /** Returns the point on the line at variable t
     *  @param t The input variable
     *  @returns A new point where equal to (vector * t) + point
     */
    public Point2d f(double t) {
        return Point2d.add(point, Vector2d.scale(vector, t));
    } //end f

    public double closestApprochTime(ParametricLine2d other) {
        return ParametricLine2d.closestApprochTime(this, other, this.maxDoubleUlps);
    } //end closestApprochTime
    public static double closestApprochTime(ParametricLine2d lhs, ParametricLine2d rhs, long maxUlps) {
        Vector2d uminusv = Vector2d.subtract(lhs.vector, rhs.vector);
        double normsqr = Vector2d.dot(uminusv, uminusv);

        double time = 0;

        //if normsqr is nearly zero then the two points are moving at similar
        //speeds and along nearly parallel lines. They are as close together
        //at t=0 as any other time (within constriants of double persision arithmatic)
        if(MathBase.doubleCompare(normsqr, 0.0, maxUlps)) {
            Vector2d wnought = Vector2d.subtract(lhs.point.toVector2d(), rhs.point.toVector2d());
            wnought.scale(-1.0);

            time = Vector2d.dot(wnought, uminusv) / normsqr;
        } //end if

        return time;
    } //end closestApprochTime

    public double closestApprochDistSqr(ParametricLine2d other) {
        return ParametricLine2d.closestApprochDistSqr(this, other, this.maxDoubleUlps);
    }
    public static double closestApprochDistSqr(ParametricLine2d lhs, ParametricLine2d rhs, long maxUlps) {
        double time = ParametricLine2d.closestApprochTime(lhs, rhs, maxUlps);

        Vector2d approch = Vector2d.subtract(lhs.f(time).toVector2d(), rhs.f(time).toVector2d());
        return approch.normSqr();
    }

    public double closestApprochDist(ParametricLine2d other) {
        return ParametricLine2d.closestApprochDist(this, other, this.maxDoubleUlps);
    } //end closestApprochDist
    public static double closestApprochDist(ParametricLine2d lhs, ParametricLine2d rhs, long maxUlps) {
        return Math.sqrt(ParametricLine2d.closestApprochDistSqr(lhs, rhs, maxUlps));
    } //end closestApprochDist

} //end class Class
