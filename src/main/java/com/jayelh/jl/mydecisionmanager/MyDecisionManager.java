package com.jayelh.jl.mydecisionmanager;

import com.jayelh.jl.PurchaseRequest;
import com.jayelh.jl.DecisionManager;
import com.jayelh.jl.Decision;

import java.util.HashMap;
import java.util.Map;

/**
 * MyDecisionManager handles the decision algorithm.
 * It stores a Map in memory holding debt for a certain user based on email
 * It is implemented as a singleton.
 */
public class MyDecisionManager implements DecisionManager {
    private Map<String, Integer> map;
    private static MyDecisionManager instance = null;

    private MyDecisionManager() {
        map = new HashMap<String, Integer>();
    }

    /**
     * @return The singleton instance of MyDecisionManager
     */
    public static MyDecisionManager getInstance() {
        if (instance == null) {
            instance = new MyDecisionManager();
        }
        return instance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Decision decision(PurchaseRequest purchaseRequest) throws IllegalArgumentException {
        Decision decision = new Decision();
        int debt = 0;

        if(purchaseRequest.getAmount() < 0) {
            throw new IllegalArgumentException("Amount should be greater than zero!");
        }

        synchronized (this) {
            if (map.containsKey(purchaseRequest.getEmail())) {
                debt = map.get(purchaseRequest.getEmail());
            }

            if (purchaseRequest.getAmount() > 1000) {
                decision.setAccepted(Boolean.FALSE);
                decision.setReason(Decision.REASON_AMOUNT);
            } else if (debt + purchaseRequest.getAmount() > 1000) {
                decision.setAccepted(Boolean.FALSE);
                decision.setReason(Decision.REASON_DEBT);
            } else {
                map.put(purchaseRequest.getEmail(), debt + purchaseRequest.getAmount());
                decision.setAccepted(Boolean.TRUE);
                decision.setReason(Decision.REASON_OK);
            }
        }
        return decision;
    }
}
