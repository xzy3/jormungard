/* HashCode
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

package ses.util;

/** Util function for calculating a hash values for hashCode uses Paul Hsieh's
 *  super fast hash.
 *
 *  @see <a href="http://www.azillionmonkeys.com/qed/hash.html">hash.html</a>
 */
public class HashCode {

    int hashCode = 0;

    public HashCode(int seed) {
        hashCode = seed;
    } //end constructor

    protected void accumulateHash(byte[] value) {
        int temp;
        int remander;
        int len;

        if(value == null || value.length == 0)
            return;

        len = value.length / 4;
        remander = value.length % 4;

        int i = 0;

        for(;len > 0; --len) {
            hashCode += makeSixteenBits(value[i + 1], value[i]);
            temp = makeSixteenBits(value[i + 3], value[i + 2]);
            temp = (temp << 11) ^ hashCode;
            hashCode = (hashCode << 16) ^ temp;
            hashCode += hashCode >> 11;

            i += 4;
        } //end for

        switch(remander) {
            case 3:
                hashCode += makeSixteenBits(value[i + 1], value[i]);
                hashCode ^= hashCode << 16;
                hashCode ^= value[i + 2];
                hashCode += hashCode >> 11;
                break;

            case 2:
                accumulateHash(makeSixteenBits(value[i + 1], value[i]));
                break;

            case 1:
                accumulateHash(value[i]);
        } //end switch
    } //end accumulateHash

    public void accumulateHash(byte value) {
        hashCode += value;
        hashCode ^= hashCode << 10;
        hashCode += hashCode >> 1;
    } //end accumulateHash

    public void accumulateHash(short value) {
        hashCode += value;
        hashCode ^= hashCode << 11;
        hashCode += hashCode >> 17;
    } //end accumulateHash

    public void accumulateHash(int value) {
        int temp;

        short one = ((short)(value >> 16));
        short two = ((short)(value & 0xFFFF));

        hashCode += one;
        temp = two;
        temp = (temp << 11) ^ hashCode;
        hashCode = (hashCode << 16) ^ temp;
        hashCode += hashCode >> 11;
    } //accumulateHash

    public void accumulateHash(long value) {
        int temp;

        short one = (short)((value >> 48) & 0xFFFF);
        short two = (short)((value >> 32) & 0xFFFF);

        hashCode += one;
        temp = two;
        temp = (temp << 11) ^ hashCode;
        hashCode = (hashCode << 16) ^ temp;
        hashCode += hashCode >> 11;

        one = (short)((value >> 16) & 0xFFFF);
        two = (short)(value & 0xFFFF);

        hashCode += one;
        temp = two;
        temp = (temp << 11) ^ hashCode;
        hashCode = (hashCode << 16) ^ temp;
        hashCode += hashCode >> 11;

    } //end accumulateHash

    public void accumulateHash(char value) {
        accumulateHash((short)value);
    } //end accumulateHash

    public void accumulateHash(float value) {
        accumulateHash(Float.floatToRawIntBits(value));
    } //end accumulateHash

    public void accumulateHash(double value) {
        accumulateHash(Double.doubleToRawLongBits(value));
    } //end accumulateHash

    public void accumulateHash(Object value) {
        accumulateHash(value.hashCode());
    } //end accumulateHash

    public int hashCode() {
        int temp = hashCode;

        temp ^= temp << 3;
        temp += temp >>> 5;
        temp ^= temp << 4;
        temp += temp >>> 17;
        temp ^= temp << 25;
        temp += temp >>> 6;

        return temp;
    } //end hashCode

    protected static short makeSixteenBits(byte high, byte low) {
        short ret = high;
        ret <<= 8;
        ret += low;
        return ret;
    } //end makeSixteenBits

} //end class Class
