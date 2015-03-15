package com.jayelh.jl;

import com.jayelh.jl.mydecisionmanager.MyDecisionManager;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for MyDecisionManager.
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
     * Test repeatedly purchases untill to high dept, then tries another user
     */
    public void testRepeatedPurchase() {
        Decision decision;
        PurchaseRequest purchaseRequest = new PurchaseRequest("a@b.se", "first", "last", 100);

        decision = myDecisionManager.decision(purchaseRequest);
        assertEquals("reason should be ok", decision.getReason(), Decision.REASON_OK);
        assertTrue("Should be true", decision.getAccepted());

        decision = myDecisionManager.decision(purchaseRequest);
        assertEquals("reason should be ok", decision.getReason(), Decision.REASON_OK);
        assertTrue("Should be true", decision.getAccepted());

        purchaseRequest = new PurchaseRequest("a@b.se", "first", "last", 800);
        decision = myDecisionManager.decision(purchaseRequest);
        assertEquals("reason should be ok", decision.getReason(), Decision.REASON_OK);
        assertTrue("Should be true", decision.getAccepted());

        decision = myDecisionManager.decision(purchaseRequest);
        assertEquals("reason should be debt", decision.getReason(), Decision.REASON_DEBT);
        assertFalse("Should be false", decision.getAccepted());

        purchaseRequest = new PurchaseRequest("b@a.se", "first", "last", 100);
        decision = myDecisionManager.decision(purchaseRequest);
        assertEquals("reason should be debt", decision.getReason(), Decision.REASON_OK);
        assertTrue("Should be true", decision.getAccepted());
    }

    /**
     * Tests a too high amount
     */
    public void testHighAmount() {
        Decision decision;
        PurchaseRequest purchaseRequest = new PurchaseRequest("a@b.se", "first", "last", 1100);

        decision = myDecisionManager.decision(purchaseRequest);
        assertEquals("Reason should be amount", decision.getReason(), Decision.REASON_AMOUNT);
        assertFalse("Should be false", decision.getAccepted());
    }

    //TODO: error cases

}