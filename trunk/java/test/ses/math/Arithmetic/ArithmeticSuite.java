/*
 * ArithmeticSuite.java
 * JUnit based test
 *
 * Created on November 22, 2007, 11:30 AM
 */

package ses.math.Arithmetic;

import junit.framework.*;

/**
 *
 * @author Seth Sims
 */
public class ArithmeticSuite extends TestCase {
    
    public ArithmeticSuite(String testName) {
        super(testName);
    }

    /**
     * suite method automatically generated by JUnit module
     */
    public static Test suite() {
        TestSuite suite = new TestSuite("ArithmeticSuite");
        suite.addTest(ses.math.Arithmetic.MaclaurinCosXTest.suite());
        suite.addTest(ses.math.Arithmetic.FloatingPointAccumulatorTest.suite());
        suite.addTest(ses.math.Arithmetic.RollingAverageTest.suite());
        suite.addTest(ses.math.Arithmetic.MaclaurinEtoXTest.suite());
        return suite;
    }
    
}
