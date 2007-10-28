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

/**
 *
 * @author Seth Sims
 */
public class Vector2d {

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
     */
    public static Vector2d fromBearing(double bearing, double length) {
        Vector2d ret = new Vector2d(bearing);
        ret.scale(length);
        return ret;
    } //end bearing

    /** Adds a vector to this vector
     *  @param rhs The vector to add
     */
    public void add(Vector2d rhs) { this.i += rhs.i; this.j += rhs.j; } //end add

    /** Adds two vectors together returning the result
     *  @param lhs The first vector
     *  @param rhs The second vector
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
     */    
    public static double dot(Vector2d lhs, Vector2d rhs) { return rhs.i * lhs.i + rhs.j * lhs.j; } //end dot

    /** Rotates this vector by pi/2 radians or 90 degrees CCW */
    public void perp() { double temp = this.i; this.i = -this.j; this.j = temp; } //end perp

    /** Rotates a vector by pi/2 radians or 90 degrees CCW returning the resulting vector
     *  @param rhs The vector to rotate
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
     */
    public static Vector2d perpCW(Vector2d arg) {
        Vector2d ret = new Vector2d(arg);
        ret.perpCW();
        return ret;
    } //end perpCW

    /** Returns the length or norm of this vector Math.sqrt(dot(this, this)) */
    public double norm() { return Math.sqrt(dot(this, this)); } //end norm
    
    /** Normalizes this vector making its length 1 unit
     *  @throws ArithmaticException if this.norm() == 0
     */
    public void normalize() { double nor = norm(); i /= nor; j /= nor; } //end normalize
    
    /** Returns a Normalized copy of a vector
     *  @param rhs The vector to normalize
     *  @throws ArithmaticException if rhs.norm() == 0
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
     */
    public static Vector2d rotate(Vector2d arg, double radians) {
        Vector2d ret = new Vector2d(arg);
        ret.rotate(radians);
        return ret;
    } //end rotate

    /** Returns the angle this vector makes CCW from the +x axis in radians */
    public double bearing() { return Vector2d.bearing(this); } //end bearing
 
    /** Returns the angle a vector makes CCW from the +x axis in radians 
     *  @param arg The vector to find the bearing of
     */
    public static double bearing(Vector2d arg) { return Math.atan2(arg.j, arg.i); } //end bearing

    /** Returns the cos of the smallest angle made by this vector and another
     *  @param arg another vector
     */
    public double cos(Vector2d arg) {
        return Vector2d.cos(this, arg);
    } //end cos
    
    /** Returns the cos of the smallest angle made by two vectors
     *  @param lhs The first vector
     *  @param rhs The second vector
     */
    public static double cos(Vector2d lhs, Vector2d rhs) {
        double denom = lhs.norm() * rhs.norm();
        return Vector2d.dot(lhs, rhs) / denom;
    } //end cos

    /** Returns the sin of the smallest angle made by this vector and another
     *  @param rhs The other vector
     */
    public double sin(Vector2d rhs) {
        return Vector2d.sin(this, rhs);
    } //end sin
    
    /** Returns the sin of the smallest angle made by two vectors
     *  @param lhs The first vector
     *  @param rhs The second vector
     */
    public static double sin(Vector2d lhs, Vector2d rhs) {
        /*| i     j     k |
         *| lhs.i lhs.j 0 | = [0i, 0j, (lhs.i * rhs.j - rhs.i * lhs.j)k]
         *| rhs.i rhs.j 0 |
         */
        double len = lhs.norm() * rhs.norm();

        return (lhs.i * rhs.j - rhs.j * lhs.i) / len;
    } //end sin

    /** Returns the quadrent this vector lies in (1-4) and 0 for the vector [0,0] */
    public int quadrant() {
       return Vector2d.quadrant(this);
    } //end quadrant
    
    /** Returns the quadrent a vector lies in (1-4) and 0 for the vector [0,0] */
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
} //end class Vector2d
