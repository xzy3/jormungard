/*
 * MaclaurinEtoXTest.java
 * JUnit based test
 *
 * Created on November 1, 2007, 11:41 PM
 */

package ses.math.Arithmetic;

import junit.framework.*;
import ses.Generators.Generator;
import ses.util.HashCode;

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

        MaclaurinEtoX instance = new MaclaurinEtoX(1.0);

        Double expResult = 1.0;
        Object result = instance.yield();
        assertEquals(expResult, result);


    }

    public static Test suite() {
        TestSuite suite = new TestSuite(MaclaurinEtoXTest.class);

        return suite;
    }

    /**
     * Test of hashCode method, of class ses.math.Arithmetic.MaclaurinEtoX.
     */
    public void testHashCode() {
        System.out.println("hashCode");

        MaclaurinEtoX instance = new MaclaurinEtoX(1.0);
        MaclaurinEtoX instanceTwo = new MaclaurinEtoX(1.0);

        for(int i = 0; i < 100; ++i) {
            assertEquals(instance.hashCode(), instanceTwo.hashCode());
            instance.yield();
            instanceTwo.yield();
        } //end for

        instanceTwo.yield();
        assertTrue(instance.hashCode() != instanceTwo.hashCode());
    }

}
