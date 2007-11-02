/*
 * GeneratorsSuite.java
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
public class GeneratorsSuite extends TestCase {
    
    public GeneratorsSuite(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    /**
     * suite method automatically generated by JUnit module
     */
    public static Test suite() {
        TestSuite suite = new TestSuite("GeneratorsSuite");
        suite.addTest(ses.Generators.GeneratorTest.suite());
        return suite;
    }
    
}
