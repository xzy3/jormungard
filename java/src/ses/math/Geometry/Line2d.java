/* Line2d
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
import ses.util.HashCode;

public class Line2d extends MathBase {

    public Vector2d normal;
    public double c;

    /** Default constructor creates a line corresponding to the x axis */
    public Line2d() {
        normal = new Vector2d(0.0, 1.0);
        c = 0.0;
    } //end constructor

    /** Constructs a line defined by the two points
     *  @param one The first point
     *  @param two The second point
     */
    public Line2d(Point2d one, Point2d two) {
        Vector2d vecOne = one.toVector2d();

        normal = Vector2d.subtract(two.toVector2d(), vecOne);
        normal.perp();

        c = -Vector2d.dot(vecOne, normal);
    } //end constructor

    /** Creates a copy of the given line
     *  @param line The line to copy
     */
    public Line2d(ParametricLine2d line) {
        normal = Vector2d.perp(line.vector);
        c = -Vector2d.dot(normal, line.point.toVector2d());
    } //end constructor

    /** Tests whether this line is parallel to the other
     *  @param other The line to test against
     */
    public boolean parallel(Line2d other) {
        return Line2d.parallel(this, other, this.maxDoubleUlps);
    } //end parallel

    /** Tests whether two lines are parallel to each other
     *  @param lhs The first line
     *  @param rhs The second line
     *  @param maxUlps The maximum error in ulps
     */
    public static boolean parallel(Line2d lhs, Line2d rhs, long maxUlps) {
        return Vector2d.parallel(lhs.normal, rhs.normal, maxUlps);
    } //end parallel

    /** Tests whether this line is parallel to a parametric line
     *  @param rhs The parametric line to test
     */
    public boolean parallel(ParametricLine2d rhs) {
        return Line2d.parallel(this, rhs, this.maxDoubleUlps);
    } //end parallel

    /** Tests whether a Line2d and a ParametricLine2d are parallel
     *  @param lhs The Line2d
     *  @param rhs The ParametricLine2d
     *  @param maxUlps The maximum error in ulps
     */
    public static boolean parallel(Line2d lhs, ParametricLine2d rhs, long maxUlps) {
        Vector2d other = Vector2d.perp(rhs.vector);
        return Vector2d.parallel(lhs.normal, other, maxUlps);
    } //end parallel

    /** Tests whether two lines are perpendicular
     *  @param other The line to test against
     */
    public boolean isPerpendicular(Line2d other) {
        return Line2d.isPerpendicular(this, other, this.maxDoubleUlps);
    } //end isPerpendicular

    /** Tests whether two lines are perpendicular
     *  @param lhs The first line
     *  @param rhs The second line
     *  @param maxUlps The maximum error in ulps
     */
    public static boolean isPerpendicular(Line2d lhs, Line2d rhs, long maxUlps) {
        return Vector2d.independentOf(lhs.normal, rhs.normal, maxUlps);
    } //end isPerpendicular

    /** Tests whether this and a ParametricLine2d are perpendicular
     *  @param other The ParametricLine2d to test against
     */
    public boolean isPerpendicular(ParametricLine2d other) {
        return Line2d.isPerpendicular(this, other, this.maxDoubleUlps);
    } //end isPerpendicular

    /** Tests whether a Line2d and a ParametricLine2d are perpendicular
     *  @param lhs The Line2d
     *  @param rhs The ParametricLine2d
     *  @param maxUlps The maximum error in ulps
     */
    public static boolean isPerpendicular(Line2d lhs, ParametricLine2d rhs, long maxUlps) {
        Vector2d other = Vector2d.perp(rhs.vector);
        return Vector2d.independentOf(lhs.normal, other, maxUlps);
    } //end isPerpendicular

    public int hashCode() {
        final int seed = 983;

        HashCode hash = new HashCode(seed);
        hash.accumulateHash(this.c);
        hash.accumulateHash(this.normal);

        return hash.hashCode();
    } //end hashCode
} //end class Class
