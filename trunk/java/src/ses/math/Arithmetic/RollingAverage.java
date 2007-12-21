/* RollingAverage
 *
 * Copyright November 21, 2007 Seth Sims
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

package ses.math.Arithmetic;

import ses.util.HashCode;

public class RollingAverage {

    private FloatingPointAccumulator numerator =
            new FloatingPointAccumulator();

    private int denominator = 0;

    private double[] circularBuffer;
    private int nextValue = 0;

    /** Default constructor creates a 1 element rolling average */
    public RollingAverage() {
        circularBuffer = new double[] { 0 };
    } //end constructor

    /** Resizes and the rolling average. This keeps the existing values.
     *  @param size The size to change the array to
     *  @throws IllegalArugumentException if size < 1
     */
    public void resize(int size) throws IllegalArgumentException {
        if(size < 1)
            throw new IllegalArgumentException("size must be one or greater");

        double[] temp = new double[size];
        System.arraycopy(circularBuffer, 0, temp, 0, circularBuffer.length);
        circularBuffer = temp;
    } //end resize

    /** Resizes the value buffer and clears it.
     *  @param size The size to change the array to
     *  @throws IllegalArgumentException if size < 1
     */
    public void resizeAndClear(int size) throws IllegalArgumentException {
        if(size < 1)
            throw new IllegalArgumentException("size must be one or greater");

        circularBuffer = new double[size];
        denominator = 0;
        this.nextValue = 0;
        numerator.clear();
    } //end resizeAndClear

    /** Clears the average to zero and deletes the remembered values */
    public void clear() {

        for(int i = 0; i < circularBuffer.length; ++i)
            circularBuffer[i] = 0.0;

        denominator = 0;
        this.nextValue = 0;
        numerator.clear();
    } //end clear

    /** Returns the average of the last "size" elements */
    public double getAverage() {
        return numerator.getSum() / (double)denominator;
    } //end getAverage

    /** Puts another value in the average.
     *  @param next The value to put in the average
     */
    public void pushValue(double next) {
        ++nextValue;
        nextValue %= circularBuffer.length;

        numerator.accumulate(-circularBuffer[nextValue]);
        numerator.accumulate(next);

        circularBuffer[nextValue] = next;

        if(denominator < circularBuffer.length)
            ++denominator;
    } //end pushValue

    public int hashCode() {
        final int seed = 2099;

        HashCode hash = new HashCode(seed);

        for(double value : this.circularBuffer)
            hash.accumulateHash(value);

        hash.accumulateHash(this.denominator);
        hash.accumulateHash(this.nextValue);
        hash.accumulateHash(this.numerator);

        return hash.hashCode();
    } //end hashValue
} //end class Class
