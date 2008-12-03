/* TappedDelayArray.java
 *
 * Copyright December 21, 2007 Seth Sims
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

package ses.Datastructures;

import java.util.Iterator;
import ses.util.HashCode;

/** A structure that remembers the last n doubles presented to it and allows
 *  iteraton over the sequence.
 *
 * @author Seth Sims
 */
public class TappedDelayArray implements Iterable<Double> {

    private int nextCell = 0;
    private double[] circularBuffer = null;

    /** Creates a new instance of TappedDelayArray */
    public TappedDelayArray(int length) {
        circularBuffer = new double[length];
    } //end constructor

    public void present(double value) {
        circularBuffer[nextCell] = value;
        nextCell = (nextCell + 1) % circularBuffer.length;
    } //end push

    /** returns an iterator that traverses the data from most recently presented
     *  to least recently presented
     */
    public Iterator<Double> iterator() {
        return new TappedDelayArrayIterator(this);
    } //end iterator

    public int hashCode() {
        final int seed = 4217;
        HashCode hash = new HashCode(seed);

        hash.accumulateHash(nextCell);
        for(double temp : this)
            hash.accumulateHash(temp);

        return hash.hashCode();
    } //end hashCode

    public class TappedDelayArrayIterator implements Iterator<Double> {
        private int currentPosition;
        private TappedDelayArray ref = null;

        protected TappedDelayArrayIterator(TappedDelayArray ref) {
            this.ref = ref;
            currentPosition = ref.nextCell;
        } //end constructor

        public boolean hasNext() {
            return currentPosition - 1 != ref.nextCell;
        } //end hasnext

        public Double next() {
            if(currentPosition > 0)
                --currentPosition;
            else
                currentPosition = ref.circularBuffer.length - 1;

            return ref.circularBuffer[currentPosition];
        } //end next

        public void remove() {
            Class reference = this.ref.getClass();
            throw new UnsupportedOperationException(
                    reference.getCanonicalName() + " does not support remove");
        } //end remove

    } //end class TappedDelayArrayIterator

} //end class TappedDelayArray
