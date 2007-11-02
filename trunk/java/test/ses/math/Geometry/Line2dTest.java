/*
 * Line2dTest.java
 * JUnit based test
 *
 * Created on October 30, 2007, 10:05 PM
 */

package ses.math.Geometry;

import junit.framework.*;
import ses.math.MathBase;
import ses.math.linearAlgebra.Vector2d;

/**
 *
 * @author Seth Sims
 */
public class Line2dTest extends TestCase {

    public Line2dTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(Line2dTest.class);

        return suite;
    }

    /**
     * Test of parallel method, of class ses.math.Geometry.Line2d.
     */
    public void testParallel() {
        System.out.println("parallel");

        Line2d other = new Line2d();
        Line2d instance = new Line2d();

        boolean expResult = true;
        boolean result = instance.parallel(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of isPerpendicular method, of class ses.math.Geometry.Line2d.
     */
    public void testIsPerpendicular() {
        System.out.println("isPerpendicular");

        Line2d other = new Line2d(new Point2d(0.0, 1.0), new Point2d(0.0, 0.0));
        Line2d instance = new Line2d();

        boolean expResult = true;
        boolean result = instance.isPerpendicular(other);
        assertEquals(expResult, result);
    }

}
