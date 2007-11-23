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

        MaclaurinEtoX eSeries = new MaclaurinEtoX(1.0);
        FloatingPointAccumulator instance = new FloatingPointAccumulator();

        for(int i = 0; i < 10000; ++i)
            instance.accumulate((Double)eSeries.yield());

        assertEquals(Math.E, instance.getSum());

        MaclaurinCosX cosSeries = new MaclaurinCosX(0.0);
        instance = new FloatingPointAccumulator();

        for(int i = 0; i < 10000; ++i)
            instance.accumulate((Double)cosSeries.yield());

        assertEquals(Math.cos(0.0), instance.getSum());

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

    public static Test suite() {
        TestSuite suite = new TestSuite(FloatingPointAccumulatorTest.class);

        return suite;
    }

    /**
     * Test of clear method, of class ses.math.Arithmetic.FloatingPointAccumulator.
     */
    public void testClear() {
        System.out.println("clear");

        double[] in = { 1.0, 2.0, 3.0, 4.0 };
        FloatingPointAccumulator instance = new FloatingPointAccumulator();

        for(double value : in)
                instance.accumulate(value);

        instance.clear();

        double expectedSum = 0.0;
        double expectedGuard = 0.0;

        assertEquals(expectedSum, instance.getSum());
        assertEquals(expectedGuard, instance.getErrorTerm());

    }

    /**
     * Test of hashCode method, of class ses.math.Arithmetic.FloatingPointAccumulator.
     */
    public void testHashCode() {
        System.out.println("hashCode");
        double[] values = { 1.0, 2.0, 3.0, 4.0, Double.MIN_VALUE };
        FloatingPointAccumulator instance = new FloatingPointAccumulator();

        int[] expResult = new int[values.length];
        int[] result = new int[values.length];

        for(int i = 0; i < values.length; ++i) {
            result[i] = instance.hashCode();
            expResult[i] = instance.hashCode();
            instance.accumulate(values[i]);
        } //end for

        for(int i = 0; i < expResult.length; ++i)
            for(int j = 0; j < result.length; ++j)
                if(i == j)
                    assertEquals(expResult[i], result[j]);
                else
                    assertTrue(expResult[i] != result[j]);

    }

}
