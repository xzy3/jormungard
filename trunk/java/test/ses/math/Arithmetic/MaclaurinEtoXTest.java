/*
 * MaclaurinEtoXTest.java
 * JUnit based test
 *
 * Created on November 1, 2007, 11:41 PM
 */

package ses.math.Arithmetic;

import junit.framework.*;
import ses.Generators.Generator;

/**
 *
 * @author Seth Sims
 */
public class MaclaurinEtoXTest extends TestCase {

    public MaclaurinEtoXTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    /**
     * Test of yield method, of class ses.math.Arithmetic.MaclaurinEtoX.
     */
    public void testYield() {
        System.out.println("yield");

        MaclaurinEtoX instance = null;

        Object expResult = null;
        Object result = instance.yield();
        assertEquals(expResult, result);

        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
