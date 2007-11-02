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

public class Line2d extends MathBase {

    public Vector2d normal;
    public double c;

    public Line2d() { normal = new Vector2d(0.0, 1.0); c = 0.0; } //end constructor

    public Line2d(Point2d one, Point2d two) {
        Vector2d vecOne = one.toVector2d();

        normal = Vector2d.subtract(two.toVector2d(), vecOne);
        normal.perp();

        c = -Vector2d.dot(vecOne, normal);
    } //end constructor

    public Line2d(ParametricLine2d line) {
        normal = Vector2d.perp(line.vector);
        c = -Vector2d.dot(normal, line.point.toVector2d());
    } //end constructor

    public boolean parallel(Line2d other) {
        return Line2d.parallel(this, other, this.maxDoubleUlps);
    } //end parallel

    public static boolean parallel(Line2d lhs, Line2d rhs, long maxUlps) {
        return Vector2d.parallel(lhs.normal, rhs.normal, maxUlps);
    } //end parallel

    public boolean parallel(ParametricLine2d rhs) {
        return Line2d.parallel(this, rhs, this.maxDoubleUlps);
    } //end parallel

    public static boolean parallel(Line2d lhs, ParametricLine2d rhs, long maxUlps) {
        Vector2d other = Vector2d.perp(rhs.vector);
        return Vector2d.parallel(lhs.normal, other, maxUlps);
    } //end parallel

    public boolean isPerpendicular(Line2d other) {
        return Line2d.isPerpendicular(this, other, this.maxDoubleUlps);
    } //end isPerpendicular

    public static boolean isPerpendicular(Line2d lhs, Line2d rhs, long maxUlps) {
        return Vector2d.independentOf(lhs.normal, rhs.normal, maxUlps);
    } //end isPerpendicular

    public boolean isPerpendicular(ParametricLine2d other) {
        return Line2d.isPerpendicular(this, other, this.maxDoubleUlps);
    } //end isPerpendicular

    public static boolean isPerpendicular(Line2d lhs, ParametricLine2d rhs, long maxUlps) {
        Vector2d other = Vector2d.perp(rhs.vector);
        return Vector2d.independentOf(lhs.normal, other, maxUlps);
    } //end isPerpendicular
} //end class Class
