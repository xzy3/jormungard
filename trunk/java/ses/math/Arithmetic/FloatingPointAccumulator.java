/* FloatingPointAccumulator
 *
 * Copyright November 1, 2007 Seth Sims
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

public class FloatingPointAccumulator {
    
    private double sum = 0.0;
    private double guard_digits = 0.0;
    
    /** Uses the Kahan compinsated floating point summation algorithm to sum doubles
     *  @param nextValue The next double to add to the sum
     */
    public void accumulate(double nextValue) {
        
        double Y;
        
        if(nextValue > sum) {
            Y = sum - guard_digits;
            sum = nextValue;
        } else { 
            Y = nextValue - guard_digits;                        
        } //end if-else
        
        double temp = sum + Y;
        guard_digits = (temp - sum) - Y;
        sum = temp;        
    } //end accumulate
    
    /** Gets the results of the summation */
    public double getSum() { return sum; } //end getSum
    
    /** Gets the current compisation term */
    public double getErrorTerm() { return guard_digits; }
    
} //end class Class
