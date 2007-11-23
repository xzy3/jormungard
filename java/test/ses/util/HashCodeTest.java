/*
 * HashCodeTest.java
 * JUnit based test
 *
 * Created on November 22, 2007, 11:30 AM
 */

package ses.util;

import junit.framework.*;

/**
 *
 * @author Seth Sims
 */
public class HashCodeTest extends TestCase {

    public HashCodeTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(HashCodeTest.class);

        return suite;
    }

    /**
     * Test of accumulateHash method, of class ses.util.HashCode.
     */
    public void testAccumulateHash() {
        System.out.println("accumulateHash");
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        
        byte[] value = null;
        HashCode instance = null;

        instance.accumulateHash(value);

        
    }

    /**
     * Test of hashCode method, of class ses.util.HashCode.
     */
    public void testHashCode() {
        System.out.println("hashCode");

        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        
        HashCode instance = null;

        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);

        
    }

    /**
     * Test of makeSixteenBits method, of class ses.util.HashCode.
     */
    public void testMakeSixteenBits() {
        System.out.println("makeSixteenBits");

        byte high = 0x7E;
        byte low = 0x1A;
        HashCode instance = null;

        short expResult = 0x7E1A;
        short result = instance.makeSixteenBits(high, low);
        assertEquals(expResult, result);

    }

}
