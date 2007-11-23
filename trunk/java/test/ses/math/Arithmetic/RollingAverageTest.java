/*
 * RollingAverageTest.java
 * JUnit based test
 *
 * Created on November 21, 2007, 7:36 PM
 */

package ses.math.Arithmetic;

import java.util.Random;
import junit.framework.*;

/**
 *
 * @author Seth Sims
 */
public class RollingAverageTest extends TestCase {

    public RollingAverageTest(String testName) {
        super(testName);
    }

    /**
     * Test of resize method, of class ses.math.Arithmetic.RollingAverage.
     */
    public void testResize() {
        System.out.println("resize");

        double values[] = {1.0, 2.0, 3.0, 4.0};

        int size = 4;
        double expected = 2.5;
        RollingAverage instance = new RollingAverage();

        instance.resize(size);

        for(double value : values)
            instance.pushValue(value);

        this.assertEquals(expected, instance.getAverage());
    }

    /**
     * Test of resizeAndClear method, of class ses.math.Arithmetic.RollingAverage.
     */
    public void testResizeAndClear() {
        System.out.println("resizeAndClear");

        double values[] = {1.0, 2.0, 3.0, 4.0};
        int size = 5;
        RollingAverage instance = new RollingAverage();
        instance.resize(size);

        for(double value : values)
            instance.pushValue(value);

        instance.resizeAndClear(size);
        assertEquals(Double.NaN, instance.getAverage());

        for(double value : values)
            instance.pushValue(value);
        assertEquals(2.5, instance.getAverage());
    }

    /**
     * Test of clear method, of class ses.math.Arithmetic.RollingAverage.
     */
    public void testClear() {
        System.out.println("clear");

        double values[] = {1.0, 2.0, 3.0, 4.0};
        int size = 5;
        RollingAverage instance = new RollingAverage();
        instance.resize(size);

        for(double value : values)
            instance.pushValue(value);

        instance.clear();
        assertEquals(Double.NaN, instance.getAverage());

        for(double value : values)
            instance.pushValue(value);
        assertEquals(2.5, instance.getAverage());

    }

    /**
     * Test of getAverage method, of class ses.math.Arithmetic.RollingAverage.
     */
    public void testGetAverage() {
        System.out.println("getAverage");

        double values[] = {1.0, 2.0, 3.0, 4.0};
        int size = 5;
        RollingAverage instance = new RollingAverage();
        instance.resize(size);

        double expResult = Double.NaN;
        double result = instance.getAverage();
        assertEquals(expResult, result);

        instance.pushValue(values[0]);
        expResult = 1.0;
        result = instance.getAverage();
        assertEquals(expResult, result);

        instance.pushValue(values[1]);
        expResult = 1.5;
        result = instance.getAverage();
        assertEquals(expResult, result);

        instance.pushValue(values[2]);
        expResult = 2.0;
        result = instance.getAverage();
        assertEquals(expResult, result);

        instance.pushValue(values[3]);
        expResult = 2.5;
        result = instance.getAverage();
        assertEquals(expResult, result);

    }

    /**
     * Test of pushValue method, of class ses.math.Arithmetic.RollingAverage.
     */
    public void testPushValue() {
        System.out.println("pushValue");

       double values[] = {1.0, 2.0, 3.0, 4.0};
        int size = 5;
        RollingAverage instance = new RollingAverage();
        instance.resize(size);

        double expResult = Double.NaN;
        double result = instance.getAverage();
        assertEquals(expResult, result);

        instance.pushValue(values[0]);
        expResult = 1.0;
        result = instance.getAverage();
        assertEquals(expResult, result);

        instance.pushValue(values[1]);
        expResult = 1.5;
        result = instance.getAverage();
        assertEquals(expResult, result);

        instance.pushValue(values[2]);
        expResult = 2.0;
        result = instance.getAverage();
        assertEquals(expResult, result);

        instance.pushValue(values[3]);
        expResult = 2.5;
        result = instance.getAverage();
        assertEquals(expResult, result);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(RollingAverageTest.class);

        return suite;
    }

    /**
     * Test of hashValue method, of class ses.math.Arithmetic.RollingAverage.
     */
    public void testHashCode() {
        System.out.println("hashCode");

        final int count = 100;

        Random gen = new Random();

        RollingAverage instance = new RollingAverage();
        instance.resize(count);

        RollingAverage instanceTwo = new RollingAverage();
        instanceTwo.resize(count);

        double holder;
        for(int i = 0; i < count * 2; ++i) {
            assertEquals(instance.hashCode(), instanceTwo.hashCode());

            holder = gen.nextDouble();

            instance.pushValue(holder);
            instanceTwo.pushValue(holder);

        }

        instance.pushValue(Double.MIN_VALUE);
        assertTrue(instance.hashCode() != instanceTwo.hashCode());
    }

}
