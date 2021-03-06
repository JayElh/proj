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
        assertEquals("reason should be ok", Decision.REASON_OK, decision.getReason());
        assertTrue("Should be true", decision.getAccepted());

        decision = myDecisionManager.decision(purchaseRequest);
        assertEquals("reason should be ok", Decision.REASON_OK, decision.getReason());
        assertTrue("Should be true", decision.getAccepted());

        purchaseRequest = new PurchaseRequest("a@b.se", "first", "last", 600);
        decision = myDecisionManager.decision(purchaseRequest);
        assertEquals("reason should be ok", Decision.REASON_OK, decision.getReason());
        assertTrue("Should be true", decision.getAccepted());

        decision = myDecisionManager.decision(purchaseRequest);
        assertEquals("reason should be debt", Decision.REASON_DEBT, decision.getReason());
        assertFalse("Should be false", decision.getAccepted());

        purchaseRequest = new PurchaseRequest("a@b.se", "first", "last", 100);

        decision = myDecisionManager.decision(purchaseRequest);
        assertEquals("reason should be ok", Decision.REASON_OK, decision.getReason());
        assertTrue("Should be true", decision.getAccepted());

        purchaseRequest = new PurchaseRequest("b@a.se", "first", "last", 100);
        decision = myDecisionManager.decision(purchaseRequest);
        assertEquals("reason should be debt", Decision.REASON_OK, decision.getReason());
        assertTrue("Should be true", decision.getAccepted());
    }

    /**
     * Tests a too high amount
     */
    public void testHighAmount() {
        Decision decision;
        PurchaseRequest purchaseRequest = new PurchaseRequest("b@b.se", "first", "last", 1100);

        decision = myDecisionManager.decision(purchaseRequest);
        assertEquals("Reason should be amount", Decision.REASON_AMOUNT, decision.getReason());
        assertFalse("Should be false", decision.getAccepted());
    }

    /**
     * Tests a illegal amount
     */
    public void testIllegalAmount() {
        Decision decision;
        PurchaseRequest purchaseRequest = new PurchaseRequest("c@b.se", "first", "last", -10);
        try {
            decision = myDecisionManager.decision(purchaseRequest);
        } catch (IllegalArgumentException e) {
            return;
        }
        assertTrue("Should have thrown Exception!", Boolean.FALSE);
    }
}