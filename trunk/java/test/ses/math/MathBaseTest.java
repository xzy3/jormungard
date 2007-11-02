/*
 * MathBaseTest.java
 * JUnit based test
 *
 * Created on October 30, 2007, 10:05 PM
 */

package ses.math;

import junit.framework.*;

/**
 *
 * @author Seth Sims
 */
public class MathBaseTest extends TestCase {

    public MathBaseTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(MathBaseTest.class);

        return suite;
    }

    /**
     * Test of floatCompare method, of class ses.math.MathBase.
     */
    public void testFloatCompare() {
        System.out.println("floatCompare");

        float lhs = 0.0F;
        float rhs = 0.0F;
        MathBase instance = new MathBase();

        boolean expResult = true;
        boolean result = instance.floatCompare(lhs, rhs);
        assertEquals(expResult, result);

        lhs = 0.0F;
        rhs = Float.MIN_VALUE;
        instance = new MathBase();

        expResult = true;
        result = instance.floatCompare(lhs, rhs);
        assertEquals(expResult, result);

        lhs = 0.0F;
        rhs = 0.5F;
        instance = new MathBase();

        expResult = false;
        result = instance.floatCompare(lhs, rhs);
        assertEquals(expResult, result);
    }

    /**
     * Test of doubleCompare method, of class ses.math.MathBase.
     */
    public void testDoubleCompare() {
        System.out.println("doubleCompare");

        double lhs = 0.0;
        double rhs = 0.0;
        MathBase instance = new MathBase();

        boolean expResult = true;
        boolean result = instance.doubleCompare(lhs, rhs);
        assertEquals(expResult, result);

        lhs = 0.0;
        rhs = Double.MIN_VALUE;
        instance = new MathBase();

        expResult = true;
        result = instance.doubleCompare(lhs, rhs);
        assertEquals(expResult, result);

        lhs = 0.0;
        rhs = 0.5;
        instance = new MathBase();

        expResult = false;
        result = instance.doubleCompare(lhs, rhs);
        assertEquals(expResult, result);
    }

}
