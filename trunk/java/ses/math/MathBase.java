/* MathBase
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

package ses.math;

/** Contains common functions and constants of the math package not provided in java.lang.math.
 *  The float comparison functions are adapted from:
 *  http://www.cygnus-software.com/papers/comparingfloats/comparingfloats.htm
 */
public class MathBase {

    public int maxFloatUlps = 10000;
    public long maxDoubleUlps = 10000;

    /** Compares two float for equality based on a lexographic ulps check using this.maxFloatUlps
     *  @param lhs The first float
     *  @param rhs The second float
     *  @return True if the ulps of rhs - lhs < this.maxFloatUlps
     *  @see #floatCompare(float, float, int)
     */
    public boolean floatCompare(float lhs, float rhs) {
        return MathBase.floatCompare(rhs, lhs, maxFloatUlps);
    } //end floatCompare

    /** Compares two floats for equality based on a lexographic ulps check
     *  @param lhs The first float value
     *  @param rhs The second float value
     *  @param maxUlps The maximum lexographic distance between the two numbers
     *  @return True if the ulps of rhs - lhs < maxUlps
     *
     *  taken from
     *  @see <a href="http://www.cygnus-software.com/papers/comparingfloats/comparingfloats.htm" >comparintfloats.htm</a>
     */
    public static boolean floatCompare(float lhs, float rhs, int maxUlps) {

        //when reodered to two's compliment integers +inf and -inf become
        //indistinguishable but are not equal so waylay that error here
        if(Float.isInfinite(rhs) && Float.isInfinite(lhs))
            return rhs == lhs;

        //the special value NAN is not equal to anything however
        //if a very large maxulps is used this function could
        //say they are. So just return false here
        if(Float.isNaN(rhs) || Float.isNaN(lhs))
            return false;

        int intDiff = MathBase.floatUlps(lhs, rhs);

        if (intDiff <= maxUlps)
            return true;

        return false;
    } //end floatCompare

    /** Returns the number of ulps between the two float values provided */
    public static int floatUlps(float lhs, float rhs) {
         int aInt = Float.floatToRawIntBits(lhs);

        // Make aInt lexicographically ordered as a twos-complement int
        if (aInt < 0)
            aInt = 0x80000000 - aInt;

        // Make bInt lexicographically ordered as a twos-complement int
        int bInt = Float.floatToRawIntBits(rhs);

        if (bInt < 0)
            bInt = 0x80000000 - bInt;

        return Math.abs(aInt - bInt);
    } //end floatUlps

    /** Compares two doubles for equality based on a lexographic ulps check using this.maxDoubleUlps
     *  @param lhs The first double
     *  @param rhs The second double
     *  @return True if the ulps of rhs - lhs < this.maxFloatUlps
     *  @see #floatCompare(float, float, int)
     */
    public boolean doubleCompare(double lhs, double rhs) {
        return MathBase.doubleCompare(lhs, rhs, maxDoubleUlps);
    } //end doubleCompare

    /** Compares two doubles for equality based on a lexographic ulps check
     *  @param lhs The first double value
     *  @param rhs The second double value
     *  @param maxUlps The maximum lexographic distance between the two numbers
     *  @return True if the ulps of rhs - lhs < maxUlps
     *
     *  @see <a href="http://www.cygnus-software.com/papers/comparingfloats/comparingfloats.htm">comparintfloats.htm</a>
     */
    public static boolean doubleCompare(double lhs, double rhs, long maxUlps) {

        //when reodered to two's compliment integers +inf and -inf become
        //indistinguishable but are not equal so waylay that error here
        if(Double.isInfinite(rhs) && Double.isInfinite(lhs))
            return rhs == lhs;

        //the special value NAN is not equal to anything however
        //if a very large maxulps is used this function could
        //say they are. So just return false here
        if(Double.isNaN(rhs) || Double.isNaN(lhs))
            return false;

        long intDiff = MathBase.doubleUlps(lhs, rhs);

        if (intDiff <= maxUlps)
            return true;

        return false;
    } //end doubleCompare

    public static long doubleUlps(double lhs, double rhs) {
        long aInt = Double.doubleToRawLongBits(lhs);

        // Make aInt lexicographically ordered as a twos-complement long
        if (aInt < 0)
            aInt = 0x8000000000000000L - aInt;

        // Make bInt lexicographically ordered as a twos-complement long
        long bInt = Double.doubleToRawLongBits(rhs);

        if (bInt < 0)
            bInt = 0x8000000000000000L - bInt;

        return Math.abs(aInt - bInt);
    } //end doubleUlps

} //end class Class
