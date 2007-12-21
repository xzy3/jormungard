/* Point2d
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

public class Point2d extends MathBase {

    public double x;

    public double y;

    /** Constructs a default point */
    public Point2d() { x = 0; y = 0; } //end constructor

    /** Constructs a point from its coordinates */
    public Point2d(double x, double y) { this.x = x; this.y = y; } //end constructor

    /** Constructs a point at the head of the vector vec */
    public Point2d(Vector2d vec) { x = vec.i; y = vec.j; } //end constructor

    /** Constructs a copy of a Point2d */
    public Point2d(Point2d other) { x = other.x; y = other.y; } //end constructor

    /** Moves a this point by a distance specified by a vector
     *  @param vec The vector to add
     */
    public void add(Vector2d vec) { x += vec.i; y += vec.j; } //end add

    /** Moves a point by a distance specified by a vector
     *  @param lhs The point
     *  @param rhs The vector
     *  @return A new point equal to (lhs + rhs)
     */
    public static Point2d add(Point2d rhs, Vector2d lhs) {
        Point2d ret = new Point2d(rhs);
        ret.add(lhs);
        return ret;
    } //end add

    /** Returns the vector representation of this point
     *  @return A vector in standard position with its head at this point
     */
    public Vector2d toVector2d() { return new Vector2d(x, y); } //end toVector2d

    public String toString() { return String.format("(%f, %f)", x, y); } //end toString

    public int hashCode() {
        final int seed = 269;

        HashCode hash = new HashCode(seed);
        hash.accumulateHash(this.x);
        hash.accumulateHash(this.y);

        return hash.hashCode();
    } //end hashCode
} //end class Class
