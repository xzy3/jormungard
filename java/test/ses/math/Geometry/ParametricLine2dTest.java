/*
 * ParametricLine2dTest.java
 * JUnit based test
 *
 * Created on October 30, 2007, 10:05 PM
 */

package ses.math.Geometry;

import junit.framework.*;
import ses.math.MathBase;
import ses.math.linearAlgebra.Vector2d;
import ses.util.HashCode;

/**
 *
 * @author Seth Sims
 */
public class ParametricLine2dTest extends TestCase {

    private final long maxUlps = 1000000;

    public ParametricLine2dTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(ParametricLine2dTest.class);

        return suite;
    }

    /**
     * Test of f method, of class ses.math.Geometry.ParametricLine2d.
     */
    public void testF() {
        System.out.println("f");

        double t = 0.0;
        ParametricLine2d instance = new ParametricLine2d();

        Vector2d expResult = new Point2d().toVector2d();
        Vector2d result = instance.f(t).toVector2d();
        assertTrue(Vector2d.equal(expResult, result, maxUlps));
    }

    /**
     * Test of closestApprochTime method, of class ses.math.Geometry.ParametricLine2d.
     */
    public void testClosestApprochTime() {
        System.out.println("closestApprochTime");

        ParametricLine2d other = new ParametricLine2d(new Point2d(), new Vector2d(0.0, 1.0));
        ParametricLine2d instance = new ParametricLine2d();

        double expResult = 0.0;
        double result = instance.closestApprochTime(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of closestApprochDistSqr method, of class ses.math.Geometry.ParametricLine2d.
     */
    public void testClosestApprochDistSqr() {
        System.out.println("closestApprochDistSqr");

        ParametricLine2d other = new ParametricLine2d(new Point2d(), new Vector2d(0.0, 1.0));
        ParametricLine2d instance = new ParametricLine2d();

        double expResult = 0.0;
        double result = instance.closestApprochDistSqr(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of closestApprochDist method, of class ses.math.Geometry.ParametricLine2d.
     */
    public void testClosestApprochDist() {
        System.out.println("closestApprochDist");

        ParametricLine2d other = new ParametricLine2d(new Point2d(), new Vector2d(0.0, 1.0));;
        ParametricLine2d instance = new ParametricLine2d();

        double expResult = 0.0;
        double result = instance.closestApprochDist(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class ses.math.Geometry.ParametricLine2d.
     */
    public void testHashCode() {
        System.out.println("hashCode");

        ParametricLine2d instance = new ParametricLine2d();
        ParametricLine2d instanceTwo = new ParametricLine2d();

        assertEquals(instance.hashCode(), instanceTwo.hashCode());

        instance.point.x += Double.MIN_VALUE;
        assertTrue(instance.hashCode() != instanceTwo.hashCode());
    }

}
