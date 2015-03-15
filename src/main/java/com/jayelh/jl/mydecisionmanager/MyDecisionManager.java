package com.jayelh.jl.mydecisionmanager;

import com.jayelh.jl.PurchaseRequest;
import com.jayelh.jl.DecisionManager;
import com.jayelh.jl.Decision;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by johanlekberg on 14/03/15.
 */
public class MyDecisionManager implements DecisionManager {
    private Map<String, Integer> map;
    private static MyDecisionManager instance = null;

    private MyDecisionManager() {
        map = new HashMap<String, Integer>();
    }

    public static MyDecisionManager getInstance() {
        if(instance == null) {
            instance = new MyDecisionManager();
        }
        return instance;
    }

    @Override
    public Decision decision(PurchaseRequest purchaseRequest) {
        //TODO: do I need to sync the whole method?
        Decision decision = new Decision();
        int debt = 0;

        synchronized(this) {
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
