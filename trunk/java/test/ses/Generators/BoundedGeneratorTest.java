/*
 * BoundedGeneratorTest.java
 * JUnit based test
 *
 * Created on November 22, 2007, 11:30 AM
 */

package ses.Generators;

import junit.framework.*;
import java.util.Iterator;

/**
 *
 * @author Seth Sims
 */
public class BoundedGeneratorTest extends TestCase {

    public BoundedGeneratorTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(BoundedGeneratorTest.class);

        return suite;
    }

    /**
     * Test of hasNext method, of class ses.Generators.BoundedGenerator.
     */
    public void testHasNext() {
        System.out.println("hasNext");

        BoundedGenerator instance = new BoundedGeneratorImpl();

       for(int i = 0; i < 20; ++i) {
            assertTrue(instance.hasNext());
            instance.yield();
       }

        assertTrue(!instance.hasNext());
    }

    /**
     * Test of next method, of class ses.Generators.BoundedGenerator.
     */
    public void testNext() {
        System.out.println("next");

        BoundedGenerator instance = new BoundedGeneratorImpl();

        int expResult = 0;
        for(Object result : instance) {
            assertEquals(expResult, result);
            expResult++;
        }
    }

    /**
     * Generated implementation of abstract class ses.Generators.BoundedGenerator. Please fill dummy bodies of generated methods.
     */
    private class BoundedGeneratorImpl implements BoundedGenerator<Integer> {

        int i = 0;
        final int range = 20;

        public boolean hasNext() {
            if(i < range)
                return true;

            return false;
        }

        public Integer next() {
            return yield();
        }

        public Integer yield() {
            return i++;
        }

        public void remove() {
        }

        public Iterator<Integer> iterator() {
            return this;
        }
    }

}
