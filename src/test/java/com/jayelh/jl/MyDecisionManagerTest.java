package com.jayelh.jl;

import com.jayelh.jl.mydecisionmanager.MyDecisionManager;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class MyDecisionManagerTest
    extends TestCase
{
    private MyDecisionManager myDecisionManager;
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public MyDecisionManagerTest(String testName)
    {
        super(testName);

        myDecisionManager = MyDecisionManager.getInstance();

    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( MyDecisionManagerTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testRepeatedPurchase() {
        Result result;
        PurchaseRequest purchaseRequest = new PurchaseRequest("jl@ab.se", "first", "last", 100);

        result = myDecisionManager.decision(purchaseRequest);
        assertEquals("reason should be ok", result.getReason(), "ok");
        assertTrue("Should be true", result.isAccepted());

        result = myDecisionManager.decision(purchaseRequest);
        assertEquals("reason should be ok", result.getReason(), "ok");
        assertTrue("Should be true", result.isAccepted());

        purchaseRequest = new PurchaseRequest("jl@ab.se", "first", "last", 800);
        result = myDecisionManager.decision(purchaseRequest);
        assertEquals("reason should be ok", result.getReason(), "ok");
        assertTrue("Should be true", result.isAccepted());

        result = myDecisionManager.decision(purchaseRequest);
        assertEquals("reason should be debt", result.getReason(), "debt");
        assertFalse("Should be false", result.isAccepted());
    }
}
