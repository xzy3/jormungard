/* MaclaurinCosX
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

import ses.Generators.Generator;
import ses.util.HashCode;

public class MaclaurinCosX implements Generator {

    double num_step;
    double numerator = 1.0;

    double factorial = 1.0;
    double next_fac = 0.0;

    public MaclaurinCosX(double x) { num_step = x * x; } //end constructor

    public Double yield() {

        double ret = numerator / factorial;

        numerator *= num_step;

        ++next_fac;
        factorial *= next_fac;
        ++next_fac;
        factorial *= next_fac;

        return ret;
    } //end yield

    public int hashCode() {
        final int seed = 1549;

        HashCode hash = new HashCode(seed);
        hash.accumulateHash(this.factorial);
        hash.accumulateHash(this.next_fac);
        hash.accumulateHash(this.num_step);
        hash.accumulateHash(this.numerator);

        return hash.hashCode();
    } //end hashCode

} //end class Class
