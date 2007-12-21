/* SquareMatrix2
 *
 * Copyright October 28, 2007 Seth Sims
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

public class SquareMatrix2 extends MathBase {

    /** The matrix in row major form */
    public double[][] matrix;

    /** Creates a new instance of an identity matrix */
    public SquareMatrix2() {
        matrix = new double [][] {{1,0},{0,1}};
    } //end constructor

    /** Creates a copy of this matrix */
    public SquareMatrix2(SquareMatrix2 other) {

        matrix = new double[][] {{other.matrix[0][0], other.matrix[0][1]},
                                 {other.matrix[1][0], other.matrix[1][1]}};

    } //end constructor

    /** Creates a rotation matrix */
    public SquareMatrix2(double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);

        matrix = new double[][] {{cos, -sin}, {sin, cos}};
    } //end constructor

    /** Multiply this matrix by another in the order (this * other)
     *  @param rhs The matrix to multiply
     */
    public void multiply(SquareMatrix2 rhs) {

        matrix[0][0] = matrix[0][0] * rhs.matrix[0][0] +
                       matrix[0][1] * rhs.matrix[1][0];

        matrix[0][1] = matrix[0][0] * rhs.matrix[0][1] +
                       matrix[0][1] * rhs.matrix[1][1];

        matrix[1][0] = matrix[1][0] * rhs.matrix[0][0] +
                       matrix[1][1] * rhs.matrix[1][0];

        matrix[1][1] = matrix[1][0] * rhs.matrix[0][1] +
                       matrix[1][1] * rhs.matrix[1][1];

    } //end multiply

    /** Multiply two matrices together returning a resulting matrix
     *  @param lhs The matrix on the left
     *  @param rhs The matrix on the right
     *  @return A new SquareMatrix2 equal to (lhs * rhs)
     */
    public SquareMatrix2 multiply(SquareMatrix2 lhs, SquareMatrix2 rhs) {
        SquareMatrix2 ret = new SquareMatrix2(lhs);
        lhs.multiply(rhs);
        return ret;
    } //end multiply

    /** Returns the determinate of this matrix
     *  @return The determinate of this matrix
     */
    public double determinate() {
        return SquareMatrix2.determinate(this);
    } //end determinate

    /** Returns the determinate of a matrix
     *  @param arg The matrix to calculate the determinate of
     *  @return The determinate of the matrix arg
     */
    public static double determinate(SquareMatrix2 arg) {
        return arg.matrix[0][0] * arg.matrix[1][1] - arg.matrix[1][0] * arg.matrix[0][1];
    } //end determinate

    public int hashCode() {
        final int seed = 1039;

        HashCode hash = new HashCode(seed);

        for(int i = 0; i < 2; ++i)
            for(int j = 0; j < 2; ++j)
                hash.accumulateHash(this.matrix[i][j]);

        return hash.hashCode();
    } //end hashCode
} //end class Class
