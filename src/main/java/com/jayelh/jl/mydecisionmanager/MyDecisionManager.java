package com.jayelh.jl.mydecisionmanager;

import com.jayelh.jl.PurchaseRequest;
import com.jayelh.jl.DecisionManager;
import com.jayelh.jl.Decision;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * MyDecisionManager handles the decision algorithm.
 * It stores a Map in memory holding debt for a certain user based on email
 * It is implemented as a singleton.
 */
public class MyDecisionManager implements DecisionManager {
    private static MyDecisionManager instance = null;
    private ConcurrentMap<String, Integer> debtMap;

    private MyDecisionManager() {
        debtMap = new ConcurrentHashMap<>();
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

        if(purchaseRequest.getAmount() < 0) {
            throw new IllegalArgumentException("Amount should be greater than zero!");
        }

        if (purchaseRequest.getAmount() > 1000) {
            decision.setAccepted(Boolean.FALSE);
            decision.setReason(Decision.REASON_AMOUNT);
        } else {
            debtMap.compute(purchaseRequest.getEmail(), (k, v) -> {
                        int result = 0;
                        if (v == null) {
                            result = purchaseRequest.getAmount();
                            decision.setAccepted(Boolean.TRUE);
                            decision.setReason(Decision.REASON_OK);
                        } else if (v + purchaseRequest.getAmount() < 1000) {
                            result = v + purchaseRequest.getAmount();
                            decision.setAccepted(Boolean.TRUE);
                            decision.setReason(Decision.REASON_OK);
                        } else {
                            result = v;
                            decision.setAccepted(Boolean.FALSE);
                            decision.setReason(Decision.REASON_DEBT);
                        }
                        return result;
                    }
            );
        }
        return decision;
    }
}
