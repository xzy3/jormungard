/*
 * MaclaurinCosXTest.java
 * JUnit based test
 *
 * Created on November 1, 2007, 11:39 PM
 */

package ses.math.Arithmetic;

import junit.framework.*;
import ses.Generators.Generator;
import ses.util.HashCode;

/**
 *
 * @author Seth Sims
 */
public class MaclaurinCosXTest extends TestCase {

    public MaclaurinCosXTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    /**
     * Test of yield method, of class ses.math.Arithmetic.MaclaurinCosX.
     */
    public void testYield() {
        System.out.println("yield");

        MaclaurinCosX instance = new MaclaurinCosX(0);

        Object expResult = 1.0;
        Object result = instance.yield();
        assertEquals(expResult, result);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(MaclaurinCosXTest.class);

        return suite;
    }

    /**
     * Test of hashCode method, of class ses.math.Arithmetic.MaclaurinCosX.
     */
    public void testHashCode() {
        System.out.println("hashCode");

        MaclaurinCosX instance = new MaclaurinCosX(0);
        MaclaurinCosX instanceTwo = new MaclaurinCosX(0);

        for(int i = 0; i < 100; ++i) {
            assertEquals(instance.hashCode(), instanceTwo.hashCode());
            instance.yield();
            instanceTwo.yield();
        } //end for

        instanceTwo.yield();
        assertTrue(instance.hashCode() != instanceTwo.hashCode());
    }

}
