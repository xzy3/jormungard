/*
 * Vector2dTest.java
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
public class Vector2dTest extends TestCase {

    private final long maxUlps = 1000000;

    public Vector2dTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(Vector2dTest.class);

        return suite;
    }

    /**
     * Test of fromBearing method, of class ses.math.linearAlgebra.Vector2d.
     */
    public void testFromBearing() {
        System.out.println("fromBearing");

        double bearing = 0.0;
        double length = 1.0;

        Vector2d expResult = new Vector2d(1.0, 0.0);
        Vector2d result = Vector2d.fromBearing(bearing, length);
        assertTrue(Vector2d.equal(expResult, result, maxUlps));

        bearing = 0.0;
        length = 0.0;
        expResult = new Vector2d(0.0, 0.0);
        result = Vector2d.fromBearing(bearing, length);
        assertTrue(Vector2d.equal(expResult, result, maxUlps));
    }

    /**
     * Test of equal method, of class ses.math.linearAlgebra.Vector2d.
     */
    public void testEqual() {
        System.out.println("equal");

        Vector2d other = new Vector2d(0.0, 1.0);
        Vector2d instance = new Vector2d(0.0, 1.0);

        boolean expResult = true;
        boolean result = instance.equal(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of add method, of class ses.math.linearAlgebra.Vector2d.
     */
    public void testAdd() {
        System.out.println("add");

        Vector2d rhs = new Vector2d(0.0, 1.0);
        Vector2d instance = new Vector2d(1.0, 0.0);
        Vector2d expected = new Vector2d(1.0, 1.0);

        instance.add(rhs);
        assertTrue(Vector2d.equal(instance, expected, maxUlps));
    }

    /**
     * Test of subtract method, of class ses.math.linearAlgebra.Vector2d.
     */
    public void testSubtract() {
        System.out.println("subtract");

        Vector2d rhs = new Vector2d(0.0, 1.0);
        Vector2d instance = new Vector2d(1.0, 0.0);
        Vector2d expected = new Vector2d(1.0, -1.0);

        instance.subtract(rhs);
        assertTrue(Vector2d.equal(instance, expected, maxUlps));
    }

    /**
     * Test of scale method, of class ses.math.linearAlgebra.Vector2d.
     */
    public void testScale() {
        System.out.println("scale");

        double factor = 0.0;
        Vector2d instance = new Vector2d(1.0, 1.0);
        Vector2d expected = new Vector2d(0.0, 0.0);

        instance.scale(factor);
        assertTrue(Vector2d.equal(instance, expected, maxUlps));

        factor = 1.0;
        instance = new Vector2d(1.0, 1.0);
        expected = new Vector2d(1.0, 1.0);

        instance.scale(factor);
        assertTrue(Vector2d.equal(instance, expected, maxUlps));

        factor = -1.0;
        instance = new Vector2d(1.0, 1.0);
        expected = new Vector2d(-1.0, -1.0);

        instance.scale(factor);
        assertTrue(Vector2d.equal(instance, expected, maxUlps));
    }

    /**
     * Test of dot method, of class ses.math.linearAlgebra.Vector2d.
     */
    public void testDot() {
        System.out.println("dot");

        Vector2d rhs = new Vector2d(1.0, 0.0);
        Vector2d instance = new Vector2d(0.0, 1.0);

        double expResult = 0.0;
        double result = instance.dot(rhs);
        assertTrue(MathBase.doubleCompare(expResult, result, maxUlps));

        rhs = new Vector2d(1.0, 0.0);
        instance = new Vector2d(1.0, 0.0);

        expResult = 1.0;
        result = instance.dot(rhs);
        assertTrue(MathBase.doubleCompare(expResult, result, maxUlps));
    }

    /**
     * Test of perp method, of class ses.math.linearAlgebra.Vector2d.
     */
    public void testPerp() {
        System.out.println("perp");

        Vector2d instance = new Vector2d(1.0, 0.0);
        Vector2d expected = new Vector2d(0.0, 1.0);

        instance.perp();
        assertTrue(Vector2d.equal(instance, expected, maxUlps));
    }

    /**
     * Test of perpCW method, of class ses.math.linearAlgebra.Vector2d.
     */
    public void testPerpCW() {
        System.out.println("perpCW");

        Vector2d instance = new Vector2d(1.0, 0.0);
        Vector2d expected = new Vector2d(0.0, -1.0);

        instance.perpCW();
        assertTrue(Vector2d.equal(instance, expected, maxUlps));
    }

    /**
     * Test of norm method, of class ses.math.linearAlgebra.Vector2d.
     */
    public void testNorm() {
        System.out.println("norm");

        Vector2d instance = new Vector2d(0.0, 0.0);

        double expResult = 0.0;
        double result = instance.norm();
        assertTrue(MathBase.doubleCompare(expResult, result, maxUlps));

        instance = new Vector2d(1.0, 0.0);

        expResult = 1.0;
        result = instance.norm();
        assertTrue(MathBase.doubleCompare(expResult, result, maxUlps));

        instance = new Vector2d(0.5, 0.5);

        expResult = Math.sqrt(2.0 * 0.5 * 0.5);
        result = instance.norm();
        assertTrue(MathBase.doubleCompare(expResult, result, maxUlps));

        instance = new Vector2d(1.0, 1.0);

        expResult = Math.sqrt(2.0);
        result = instance.norm();
        assertTrue(MathBase.doubleCompare(expResult, result, maxUlps));
    }

    /**
     * Test of normSqr method, of class ses.math.linearAlgebra.Vector2d.
     */
    public void testNormSqr() {
        System.out.println("normSqr");

        Vector2d instance = new Vector2d(0.0, 0.0);

        double expResult = 0.0;
        double result = instance.normSqr();
        assertTrue(MathBase.doubleCompare(expResult, result, maxUlps));

        instance = new Vector2d(1.0, 0.0);

        expResult = 1.0;
        result = instance.normSqr();
        assertTrue(MathBase.doubleCompare(expResult, result, maxUlps));

        instance = new Vector2d(1.0, 1.0);

        expResult = 2.0;
        result = instance.normSqr();
        assertTrue(MathBase.doubleCompare(expResult, result, maxUlps));
    }

    /**
     * Test of normalize method, of class ses.math.linearAlgebra.Vector2d.
     */
    public void testNormalize() {
        System.out.println("normalize");

        double norm = Math.sqrt(20.0 * 20.0 + 50.0 * 50.0);

        Vector2d instance = new Vector2d(20.0, 50.0);
        Vector2d expected = new Vector2d(20.0 / norm, 50.0 / norm);

        instance.normalize();
        assertTrue(Vector2d.equal(instance, expected, maxUlps));

    }

    /**
     * Test of rotate method, of class ses.math.linearAlgebra.Vector2d.
     */
    public void testRotate() {
        System.out.println("rotate");

        double radians = Math.PI / 2.0;
        Vector2d instance = new Vector2d(1.0, 0.0);
        Vector2d expected = new Vector2d(0.0, 1.0);

        instance.rotate(radians);

        System.out.println(instance);
        System.out.println(expected);

        assertTrue(Vector2d.equal(instance, expected, maxUlps));

        radians = Math.PI;
        instance = new Vector2d(1.0, 0.0);
        expected = new Vector2d(-1.0, 0.0);

        instance.rotate(radians);

        System.out.println(instance);
        System.out.println(expected);

        assertTrue(Vector2d.equal(instance, expected, maxUlps));

        radians = (3.0 * Math.PI) / 2.0;
        instance = new Vector2d(1.0, 0.0);
        expected = new Vector2d(0.0, -1.0);

        instance.rotate(radians);

        System.out.println(instance);
        System.out.println(expected);

        assertTrue(Vector2d.equal(instance, expected, maxUlps));

        radians = 2.0 * Math.PI;
        instance = new Vector2d(1.0, 0.0);
        expected = new Vector2d(1.0, 0.0);

        instance.rotate(radians);

        System.out.println(instance);
        System.out.println(expected);

        assertTrue(Vector2d.equal(instance, expected, maxUlps));
    }

    /**
     * Test of bearing method, of class ses.math.linearAlgebra.Vector2d.
     */
    public void testBearing() {
        System.out.println("bearing");

        Vector2d instance = new Vector2d(1.0, 0.0);

        double expResult = 0.0;
        double result = instance.bearing();
        assertTrue(MathBase.doubleCompare(expResult, result, maxUlps));

        instance = new Vector2d(0.0, 1.0);

        expResult = Math.PI / 2.0;
        result = instance.bearing();
        assertTrue(MathBase.doubleCompare(expResult, result, maxUlps));

        instance = new Vector2d(-1.0, 0.0);

        expResult = Math.PI;
        result = instance.bearing();
        assertTrue(MathBase.doubleCompare(expResult, result, maxUlps));

        instance = new Vector2d(0.0, -1.0);

        expResult = (2.0 * Math.PI) / 3.0;
        result = instance.bearing();
        assertTrue(MathBase.doubleCompare(expResult, result, maxUlps));

        instance = new Vector2d(1.0, 1.0);

        expResult = Math.PI / 4.0;
        result = instance.bearing();
        assertTrue(MathBase.doubleCompare(expResult, result, maxUlps));
    }

    /**
     * Test of cos method, of class ses.math.linearAlgebra.Vector2d.
     */
    public void testCos() {
        System.out.println("cos");

        Vector2d rhs = new Vector2d(0.0, 1.0);
        Vector2d instance = new Vector2d(1.0, 0.0);

        double expResult = 0.0;
        double result = instance.cos(rhs);
        assertTrue(MathBase.doubleCompare(expResult, result, maxUlps));

        rhs = new Vector2d(1.0, 0.0);
        instance = new Vector2d(1.0, 0.0);

        expResult = 1.0;
        result = instance.cos(rhs);
        assertTrue(MathBase.doubleCompare(expResult, result, maxUlps));
    }

    /**
     * Test of sin method, of class ses.math.linearAlgebra.Vector2d.
     */
    public void testSin() {
        System.out.println("sin");

        Vector2d rhs = new Vector2d(0.0, 1.0);
        Vector2d instance = new Vector2d(1.0, 0.0);

        double expResult = 1.0;
        double result = instance.sin(rhs);
        assertTrue(MathBase.doubleCompare(expResult, result, maxUlps));

        rhs = new Vector2d(1.0, 0.0);
        instance = new Vector2d(1.0, 0.0);

        expResult = 0.0;
        result = instance.sin(rhs);
        assertTrue(MathBase.doubleCompare(expResult, result, maxUlps));
    }

    /**
     * Test of quadrant method, of class ses.math.linearAlgebra.Vector2d.
     */
    public void testQuadrant() {
        System.out.println("quadrant");

        Vector2d instance = new Vector2d(0.0, 0.0);

        int expResult = 0;
        int result = instance.quadrant();
        assertEquals(expResult, result);

        instance = new Vector2d(1.0, 1.0);

        expResult = 1;
        result = instance.quadrant();
        assertEquals(expResult, result);

        instance = new Vector2d(-1.0, 1.0);

        expResult = 2;
        result = instance.quadrant();
        assertEquals(expResult, result);

        instance = new Vector2d(-1.0, -1.0);

        expResult = 3;
        result = instance.quadrant();
        assertEquals(expResult, result);

        instance = new Vector2d(1.0, -1.0);

        expResult = 4;
        result = instance.quadrant();
        assertEquals(expResult, result);
    }

    /**
     * Test of multiply method, of class ses.math.linearAlgebra.Vector2d.
     */
    public void testMultiply() {
        System.out.println("multiply");

        SquareMatrix2 rhs = new SquareMatrix2();
        Vector2d instance = new Vector2d(1.0, 1.0);
        Vector2d expected = new Vector2d(1.0, 1.0);

        instance.multiply(rhs);
        assertTrue(Vector2d.equal(instance, expected, maxUlps));
    }

    /**
     * Test of parallel method, of class ses.math.linearAlgebra.Vector2d.
     */
    public void testParallel() {
        System.out.println("parallel");

        Vector2d other = new Vector2d(1.0, 0.0);
        Vector2d instance = new Vector2d(1.0, 0.0);

        boolean expResult = true;
        boolean result = instance.parallel(other);
        assertEquals(expResult, result);

        other = new Vector2d(1.0, 0.0);
        instance = new Vector2d(0.0, 1.0);

        instance.maxDoubleUlps = maxUlps;

        expResult = false;
        result = instance.parallel(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of independentOf method, of class ses.math.linearAlgebra.Vector2d.
     */
    public void testIndependentOf() {
        System.out.println("independentOf");

        Vector2d other = new Vector2d(1.0, 0.0);
        Vector2d instance = new Vector2d(0.0, 1.0);

        boolean expResult = true;
        boolean result = instance.independentOf(other);
        assertEquals(expResult, result);

        other = new Vector2d(1.0, 0.0);
        instance = new Vector2d(1.0, 0.0);

        instance.maxDoubleUlps = maxUlps;

        expResult = false;
        result = instance.independentOf(other);
        assertEquals(expResult, result);
    }

}
