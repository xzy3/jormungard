/*
 * Vector2d.java
 *
 * Copyright 2007 Seth Sims
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

package ses.math.linearAlgebra;

import ses.math.MathBase;
import ses.util.HashCode;

/**
 *
 * @author Seth Sims
 */
public class Vector2d extends MathBase {

    /** The component of the vector in the i direction */
    public double i;
    /** The component of the vector in the j direction */
    public double j;

    /** Creates a empty instance of Vector2d */
    public Vector2d() {this.i = 0; this.j = 0;} //end constructor

    /** Creates a unit length vector at an angle from +x
     * @param bearing The angle CCW from the positive x axis in radians
     */
    public Vector2d(double bearing) { this.i = Math.cos(bearing); this.j = Math.sin(bearing); } //end constructor

    /** Creates a vector with the specified i and j components
     * @param i The component in the i direction
     * @param j The component in the j direction
     */
    public Vector2d(double i, double j) { this.i = i; this.j = j; } //end constructor

    /** Creates a vector that is a copy of the other vector */
    public Vector2d(Vector2d other) { this.i = other.i; this.j = other.j; } //end constructor

    /** Creates a vector with the specified direction and length
     *  @param bearing The angle CCW from the positve x radius in radians
     *  @param length  The length of the vector
     *  @return A new Vector2d with the given bearing CCW from +x axis and length
     */
    public static Vector2d fromBearing(double bearing, double length) {
        Vector2d ret = new Vector2d(bearing);
        ret.scale(length);
        return ret;
    } //end bearing

    /** Returns whether this vector is equal to another within maxDoubleUlps
     *  @param other The other vector
     *  @return True if this.i == other.i && this.j == other.j
     *
     *  Uses the double compare function and maxDoubleUlps of MathBase to determine equality.
     */
    public boolean equal(Vector2d other) {
        return Vector2d.equal(this, other, this.maxDoubleUlps);
    } //end equal

    /** Compares two vectors for equality within maxUlps
     *  @param lhs The first vector to compare
     *  @param rhs The second vector to compare
     *  @param maxUlps The maximum ulps to be consitered equal
     *  @return True if lhs.i == rhs.i && lhs.j == rhs.j
     */
    public static boolean equal(Vector2d lhs, Vector2d rhs, long maxUlps) {
        if(MathBase.doubleCompare(lhs.i, rhs.i, maxUlps) &&
           MathBase.doubleCompare(lhs.j, rhs.j, maxUlps))
            return true;

        return false;
    } //end equal

    /** Adds a vector to this vector
     *  @param rhs The vector to add
     */
    public void add(Vector2d rhs) { this.i += rhs.i; this.j += rhs.j; } //end add

    /** Adds two vectors together returning the result
     *  @param lhs The first vector
     *  @param rhs The second vector
     *  @return A new Vector2d equal to (lhs + rhs)
     */
    public static Vector2d add(Vector2d lhs, Vector2d rhs) {
        Vector2d ret = new Vector2d(lhs);
        ret.add(rhs);
        return ret;
    } //end add

    /** Subtracts a vector from this vector
     *  @param rhs The vector to subtract
     */
    public void subtract(Vector2d rhs) { this.i -= rhs.i; this.j -= rhs.j; } //end subtract

    /** Subtracts one vector from another returing the resulting vector
     *  @param lhs The left vector
     *  @param rhs The vector to subtract
     *  @return A new Vector2d equal to (lhs - rhs)
     */
    public static Vector2d subtract(Vector2d lhs, Vector2d rhs) {
        Vector2d ret = new Vector2d(lhs);
        ret.subtract(rhs);
        return ret;
    } //end subtract

    /** Scales this vector by a factor
     *  @param factor The factor to multiply by
     */
    public void scale(double factor) { this.i *= factor; this.j *= factor; } //end scale

    /** Scales a vector by a factor returning the resulting vector
     *  @param lhs The vector to scale
     *  @param factor The factor to multiply by
     *  @return a copy of lhs scaled by factor
     */
    public static Vector2d scale(Vector2d lhs, double factor) {
        Vector2d ret = new Vector2d(lhs);
        ret.scale(factor);
        return ret;
    } //end scale

    /** Returns the standard cartisan inner product (dot product) of this vector and another
     *  @param rhs The other vector
     */
    public double dot(Vector2d rhs) { return Vector2d.dot(this, rhs); } //end dot

    /** Returns the standard cartisan inner product (dot product) of two vectors
     *  @param lhs The first vector
     *  @param rhs The second vector
     *  @return The dot product of lhs and rhs
     */
    public static double dot(Vector2d lhs, Vector2d rhs) { return rhs.i * lhs.i + rhs.j * lhs.j; } //end dot

    /** Rotates this vector by pi/2 radians or 90 degrees CCW */
    public void perp() { double temp = this.i; this.i = -this.j; this.j = temp; } //end perp

    /** Rotates a vector by pi/2 radians or 90 degrees CCW returning the resulting vector
     *  @param rhs The vector to rotate
     *  @return A copy of rhs rotated pi/2 radians in the counter-clockwise direction
     */
    public static Vector2d perp(Vector2d rhs) {
        Vector2d ret = new Vector2d(rhs);
        ret.perp();
        return ret;
    } //end perp

    /** Rotates this vector by pi/2 radians or 90 degrees CW */
    public void perpCW() { double temp = -this.i; this.i = this.j; this.j = temp; } //end perpCW

    /** Rotates a vector by pi/2 radians or 90 degrees CW returning the resulting vector
     *  @param arg The vector to rotate
     *  @return A copy of arg rotated pi/2 in the clockwise direction
     */
    public static Vector2d perpCW(Vector2d arg) {
        Vector2d ret = new Vector2d(arg);
        ret.perpCW();
        return ret;
    } //end perpCW

    /** Returns the length or norm of this vector Math.sqrt(dot(this, this)) */
    public double norm() { return Vector2d.norm(this); } //end norm

    /** Returns the length of the vector arg
     *  @param arg The vector
     *  @return The length of arg
     */
    public static double norm(Vector2d arg) { return Math.sqrt(Vector2d.normSqr(arg)); } //end norm

    /** Returns the squared length of this vector
     *  @return This.norm() ** 2
     */
    public double normSqr() { return Vector2d.normSqr(this); } //end normSqr

    /** Returns the squared length of the vector arg
     *  @param arg The vector
     *  @return arg.norm() ** 2
     */
    public static double normSqr(Vector2d arg) { return Vector2d.dot(arg, arg); } //end normSqr

    /** Normalizes this vector making its length 1 unit
     *  @throws ArithmaticException if this.norm() == 0
     */
    public void normalize() { double nor = norm(); i /= nor; j /= nor; } //end normalize

    /** Returns a Normalized copy of a vector
     *  @param arg The vector to normalize
     *  @return A normalized copy of arg
     *  @throws ArithmaticException if arg.norm() == 0
     */
    public static Vector2d normalize(Vector2d arg) {
        Vector2d ret = new Vector2d(arg);
        ret.normalize();
        return ret;
    } //end normalize

    /** Rotates this vector CCW by an ammount
     *  @param radians The angle to rotate the vector through in radians
     */
    public void rotate(double radians) {

        double sin = Math.sin(radians);
        double cos = Math.cos(radians);

        i = i * cos - j * sin;
        j = i * sin + j * cos;

    } //end rotate

    /** Returns a copy of a vector rotated though a given angle
     *  @param arg The vector to rotate
     *  @param radians The angle to rotate the vector though in radians
     *  @return A copy of arg rotated through the angle radians
     */
    public static Vector2d rotate(Vector2d arg, double radians) {
        Vector2d ret = new Vector2d(arg);
        ret.rotate(radians);
        return ret;
    } //end rotate

    /** Returns the angle this vector makes CCW from the +x axis in radians
     *  @return The angle in radians made CCW from the +x axis and this vector
     */
    public double bearing() { return Vector2d.bearing(this); } //end bearing

    /** Returns the angle a vector makes CCW from the +x axis in radians
     *  @param arg The vector to find the bearing of
     *  @return The angle in radians CCW from the +x axis to arg
     */
    public static double bearing(Vector2d arg) { return Math.atan2(arg.j, arg.i); } //end bearing

    /** Returns the cos of the smallest angle made by this vector and another
     *  @param arg another vector
     *  @return The cos of the angle made by this and rhs
     */
    public double cos(Vector2d arg) {
        return Vector2d.cos(this, arg);
    } //end cos

    /** Returns the cos of the smallest angle made by two vectors
     *  @param lhs The first vector
     *  @param rhs The second
     *  @return the cos of the angle between lhs an rhs
     */
    public static double cos(Vector2d lhs, Vector2d rhs) {
        double denom = lhs.norm() * rhs.norm();
        return Vector2d.dot(lhs, rhs) / denom;
    } //end cos

    /** Returns the sin of the smallest angle made by this vector and another
     *  @param rhs The other vector
     *  @return The sin of the angle made by this and rhs
     */
    public double sin(Vector2d rhs) {
        return Vector2d.sin(this, rhs);
    } //end sin

    /** Returns the sin of the smallest angle made by two vectors
     *  @param lhs The first vector
     *  @param rhs The second vector
     *  @return The sin of the angle between lhs and rhs
     */
    public static double sin(Vector2d lhs, Vector2d rhs) {
        /*| i     j     k |
         *| lhs.i lhs.j 0 | = [0i, 0j, (lhs.i * rhs.j - rhs.i * lhs.j)k]
         *| rhs.i rhs.j 0 |
         */
        double len = lhs.norm() * rhs.norm();

        return (lhs.i * rhs.j - rhs.i * lhs.j) / len;
    } //end sin

    /** Returns the quadrent this vector lies in (1-4) and 0 for the vector [0,0]
     *  @return the quadrent this vector lies in (1-4) and 0 for the vector [0,0]
     */
    public int quadrant() {
       return Vector2d.quadrant(this);
    } //end quadrant

    /** Returns the quadrent a vector lies in (1-4) or 0 for the vector [0,0]
     *  @return The quadrent a vector lies in (1-4) or 0 for the vector [0,0]
     */
    public static int quadrant(Vector2d rhs) {
         if(rhs.i == 0 && rhs.j ==0) {
            return 0;
        } if(rhs.i >= 0 && rhs.j >= 0) {
            return 1;
        } else if(rhs.i <= 0 && rhs.j >= 0) {
            return 2;
        } else if(rhs.i <= 0 && rhs.j <= 0) {
            return 3;
        } else {
            return 4;
        } //end if-else if-else if-else if
    } //end quadrant

    /** Multiplies this vector on the left by a matrix
     *  @param rhs The matrix to multiply by
     */
    public void multiply(SquareMatrix2 rhs) {
        i = i * rhs.matrix[0][0] + j * rhs.matrix[0][1];
        j = i * rhs.matrix[1][0] + j * rhs.matrix[1][1];
    } //end multiply

    /** Multiplies a vector on the left of a SquareMatrix2
     *  @param lhs The Vector2d to multiply
     *  @param rhs The SquareMatrix2 to multiply by
     *  @return A new vector the result of vector * matrix
     */
    public static Vector2d multiply(Vector2d lhs, SquareMatrix2 rhs) {
        Vector2d ret = new Vector2d(lhs);
        ret.multiply(rhs);
        return ret;
    } //end multiply

    /** Returns true if the sin value between this vector and another is smaller then epsilon
     *  @param other The other vector
     *  @return True if Vector2d.sin(this, other) < epsilon
     */
    public boolean parallel(Vector2d other) {
        return Vector2d.parallel(this, other, this.maxDoubleUlps);
    } //end parallel

    /** Returns true if the sin value between the vectors is less then a small epsilon
     *  @param lhs The first vector
     *  @param rhs The second vector
     *  @param maxUlps The maxumum number of ulps to allow
     *  @return True if Vector2d.sin(rhs, lhs) == 0.0 within the given number of ulps
     */
    public static boolean parallel(Vector2d lhs, Vector2d rhs, long maxUlps) {
        return MathBase.doubleCompare(Vector2d.sin(lhs, rhs), 0.0, maxUlps);
    } //end parallel

    /** Returns if this vector is independent of another (perpendicular to)
     *  @param other The other vector
     *  @return True if Vector2d.dot(this, other) == 0.0 within this.maxDoubleUlps
     */
    public boolean independentOf(Vector2d other) {
        return Vector2d.independentOf(this, other, this.maxDoubleUlps);
    } //end independantOf

    /** Returns if the two vectors are independent (perpendicular)
     *  @param lhs The first vector
     *  @param rhs The second vector
     *  @return True if Vector2d.dot(rhs, lhs) == 0.0 within this.maxDoubleUlps
     */
    public static boolean independentOf(Vector2d lhs, Vector2d rhs, long maxUlps) {
        return MathBase.doubleCompare(Vector2d.dot(lhs, rhs), 0.0, maxUlps);
    } //end independantOf

    public String toString() { return String.format("[%5f, %5f]", i, j); } //end toString

    public int hashCode() {
        final int seed = 991;

        HashCode hash = new HashCode(seed);
        hash.accumulateHash(this.i);
        hash.accumulateHash(this.j);

        return hash.hashCode();
    } //end hashCode
} //end class Vector2d
