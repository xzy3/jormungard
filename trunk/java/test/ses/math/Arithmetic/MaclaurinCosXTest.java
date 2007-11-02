/*
 * MaclaurinCosXTest.java
 * JUnit based test
 *
 * Created on November 1, 2007, 11:39 PM
 */

package ses.math.Arithmetic;

import junit.framework.*;
import ses.Generators.Generator;

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

}
