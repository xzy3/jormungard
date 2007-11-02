/*
 * FloatingPointAccumulatorTest.java
 * JUnit based test
 *
 * Created on November 1, 2007, 10:15 PM
 */

package ses.math.Arithmetic;

import junit.framework.*;
import ses.Generators.Generator;
import ses.math.MathBase;

/**
 *
 * @author Seth Sims
 */
public class FloatingPointAccumulatorTest extends TestCase {

    public FloatingPointAccumulatorTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    /**
     * Test of accumulate method, of class ses.math.Arithmetic.FloatingPointAccumulator.
     */
    public void testAccumulate() {
        System.out.println("accumulate");
        long maxUlps = 10;

        MaclaurinEtoX eSeries = new MaclaurinEtoX(1.0);
        FloatingPointAccumulator instance = new FloatingPointAccumulator();

        for(int i = 0; i < 10000; ++i)
            instance.accumulate((Double)eSeries.yield());

        assertTrue(MathBase.doubleCompare(Math.E, instance.getSum(), maxUlps));

        MaclaurinCosX cosSeries = new MaclaurinCosX(0.0);
        instance = new FloatingPointAccumulator();

        for(int i = 0; i < 10000; ++i)
            instance.accumulate((Double)cosSeries.yield());

        assertTrue(MathBase.doubleCompare(Math.cos(0.0), instance.getSum(), maxUlps));

    }

    /**
     * Test of getSum method, of class ses.math.Arithmetic.FloatingPointAccumulator.
     */
    public void testGetSum() {
        System.out.println("getSum");

        FloatingPointAccumulator instance = new FloatingPointAccumulator();

        double expResult = 0.0;
        double result = instance.getSum();
        assertEquals(expResult, result);
    }

    /**
     * Test of getErrorTerm method, of class ses.math.Arithmetic.FloatingPointAccumulator.
     */
    public void testGetErrorTerm() {
        System.out.println("getErrorTerm");

        FloatingPointAccumulator instance = new FloatingPointAccumulator();

        double expResult = 0.0;
        double result = instance.getErrorTerm();
        assertEquals(expResult, result);
    }

}
