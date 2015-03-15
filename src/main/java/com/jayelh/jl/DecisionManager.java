package com.jayelh.jl;

/**
 * Interface for DecisionManagers.
 * A DecisionManager calculates the risk of a purchase and returns a decision
 * if a purchase should be accepted or not.
 */
public interface DecisionManager {
    /**
     * Makes a decision of a purchaseRequest
     *
     * @param purchaseRequest the purchase that should be acted on
     * @return the decision of the purchase
     */
    Decision decision(PurchaseRequest purchaseRequest);

}
