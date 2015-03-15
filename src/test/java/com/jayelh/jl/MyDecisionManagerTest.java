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
        Decision decision;
        PurchaseRequest purchaseRequest = new PurchaseRequest("jl@ab.se", "first", "last", 100);

        decision = myDecisionManager.decision(purchaseRequest);
        assertEquals("reason should be ok", decision.getReason(), "ok");
        assertTrue("Should be true", decision.isAccepted());

        decision = myDecisionManager.decision(purchaseRequest);
        assertEquals("reason should be ok", decision.getReason(), "ok");
        assertTrue("Should be true", decision.isAccepted());

        purchaseRequest = new PurchaseRequest("jl@ab.se", "first", "last", 800);
        decision = myDecisionManager.decision(purchaseRequest);
        assertEquals("reason should be ok", decision.getReason(), "ok");
        assertTrue("Should be true", decision.isAccepted());

        decision = myDecisionManager.decision(purchaseRequest);
        assertEquals("reason should be debt", decision.getReason(), "debt");
        assertFalse("Should be false", decision.isAccepted());
    }
}
