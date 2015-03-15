package com.jayelh.jl.mydecisionmanager;

import com.jayelh.jl.Data;
import com.jayelh.jl.DecisionManager;
import com.jayelh.jl.Result;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by johanlekberg on 14/03/15.
 */
public class MyDecisionManager implements DecisionManager {
    //TODO: move these to result, enum?
    public static final String REASON_AMOUNT = "amount";
    public static final String REASON_DEBT = "debt";
    public static final String REASON_OK = "ok";

    private Map<String, Integer> map;

    public MyDecisionManager() {
        map = new ConcurrentHashMap<String, Integer>();
    }

    @Override
    public Result decision(Data data) {
        //TODO: do I need to sync the whole method?
        Result result = new Result();
        int debt = 0;

        synchronized(this) {
            if (map.containsKey(data.getEmail())) {
                debt = map.get(data.getEmail());
            }

            if (data.getAmount() > 1000) {
                result.setAccepted(Boolean.FALSE);
                result.setReason(REASON_AMOUNT);
            } else if (debt + data.getAmount() > 1000) {
                result.setAccepted(Boolean.FALSE);
                result.setReason(REASON_DEBT);
            } else {
                map.put(data.getEmail(), debt + data.getAmount());
                result.setAccepted(Boolean.TRUE);
                result.setReason(REASON_OK);
            }
        }
        return result;
    }
}
