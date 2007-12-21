/* BoundedGenerator
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

package ses.Generators;

import java.util.Iterator;

/** This class represents a generator with a fixed sequence length */

public interface BoundedGenerator<T> extends Generator<T>, Iterator<T>, Iterable<T> {

    /** Returns whether the sequence this generator represents has more values */
    public boolean hasNext();

    /** Returns the next element in the sequence. This should usually just
     *  be "return this.yield();"
     */
    public T next();

} //end class Class
