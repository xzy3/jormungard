/*
 * Point2dTest.java
 * JUnit based test
 *
 * Created on October 30, 2007, 10:05 PM
 */

package ses.math.Geometry;

import junit.framework.*;
import ses.math.linearAlgebra.Vector2d;

/**
 *
 * @author Seth Sims
 */
public class Point2dTest extends TestCase {

    public Point2dTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(Point2dTest.class);

        return suite;
    }

    /**
     * Test of add method, of class ses.math.Geometry.Point2d.
     */
    public void testAdd() {
        System.out.println("add");

        Vector2d vec = new Vector2d();
        Point2d instance = new Point2d();

        instance.add(vec);

        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toVector2d method, of class ses.math.Geometry.Point2d.
     */
    public void testToVector2d() {
        long maxUlps = 1;
        System.out.println("toVector2d");

        Point2d instance = new Point2d();

        Vector2d expResult = new Vector2d(0.0, 0.0);
        Vector2d result = instance.toVector2d();
        assertTrue(Vector2d.equal(expResult, result, maxUlps));
    }

}
