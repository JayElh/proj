package com.jayelh.jl;

/**
 * Created by johanlekberg on 14/03/15.
 */
public class Decision {
    // enum?
    public static final String REASON_AMOUNT = "amount";
    public static final String REASON_DEBT = "debt";
    public static final String REASON_OK = "ok";

    public enum Reason { AMOUNT, DEBT, OK;
        public String toString() {
            return name().toLowerCase();
        }
    }
    private boolean accepted;
    private String reason;

    public Decision() {
        //TODO: what about this, initial state? shouldnt happen
        this.accepted = Boolean.FALSE;
        this.reason = "";
    }

    public Decision(boolean accepted, String reason) {
        this.accepted = accepted;
        this.reason = reason;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
