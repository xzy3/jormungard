/*
 * SquareMatrix2Test.java
 * JUnit based test
 *
 * Created on October 30, 2007, 10:05 PM
 */

package ses.math.linearAlgebra;

import junit.framework.*;
import ses.math.MathBase;

/**
 *
 * @author Seth Sims
 */
public class SquareMatrix2Test extends TestCase {

    public SquareMatrix2Test(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(SquareMatrix2Test.class);

        return suite;
    }

    /**
     * Test of multiply method, of class ses.math.linearAlgebra.SquareMatrix2.
     */
    public void testMultiply() {
        System.out.println("multiply");

        SquareMatrix2 rhs = null;
        SquareMatrix2 instance = new SquareMatrix2();

        instance.multiply(rhs);

        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of determinate method, of class ses.math.linearAlgebra.SquareMatrix2.
     */
    public void testDeterminate() {
        System.out.println("determinate");

        SquareMatrix2 instance = new SquareMatrix2();

        double expResult = 0.0;
        double result = instance.determinate();
        assertEquals(expResult, result);
    }

}
