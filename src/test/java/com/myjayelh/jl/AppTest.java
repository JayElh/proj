package com.myjayelh.jl;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    private MyPurchaseManager myPurchaseManager;
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super(testName);

        myPurchaseManager = new MyPurchaseManager();

    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        Result result;
        Data data = new Data("jl@ab.se", "first", "last", 100);

        result = myPurchaseManager.purchase(data);
        assertEquals("reason should be ok", result.getReason(), "ok");
        assertTrue("Should be true", result.isAccepted());

    }
}
