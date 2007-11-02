/*
 * GeneratorTest.java
 * JUnit based test
 *
 * Created on October 30, 2007, 10:05 PM
 */

package ses.Generators;

import junit.framework.*;

/**
 *
 * @author Seth Sims
 */
public class GeneratorTest extends TestCase {

    public GeneratorTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(GeneratorTest.class);

        return suite;
    }

    /**
     * Test of yield method, of class ses.Generators.Generator.
     */
    public void testYield() {
        System.out.println("yield");

        Generator instance = new GeneratorImpl();

        Object expResult = 1;
        Object result = instance.yield();
        assertEquals(expResult, result);
    }

    /**
     * Generated implementation of abstract class ses.Generators.Generator. Please fill dummy bodies of generated methods.
     */
    private class GeneratorImpl implements Generator {

        int i = 0;

        public java.lang.Object yield() {
            ++i;
            return i;
        }
    }


}
