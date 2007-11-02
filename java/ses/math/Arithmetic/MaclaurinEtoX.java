/* MaclaurinEtoX
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

public class MaclaurinEtoX implements Generator {

    double eval_at;
    double numerator = 1.0;

    double factorial = 1.0;
    double next_fac_term = 1.0;

    public MaclaurinEtoX(double x) { eval_at = x; } //end constructor

    public Object yield() {

        double ret = numerator / factorial;

        factorial *= next_fac_term;
        ++next_fac_term;

        numerator *= eval_at;

        return ret;
    } //end yield

} //end class Class
