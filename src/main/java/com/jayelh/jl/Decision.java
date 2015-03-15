package com.jayelh.jl;

/**
 * Bean holding the decision of a purchase
 */
public class Decision {
    /**
     * {@value #REASON_AMOUNT}
     */
    public static final String REASON_AMOUNT = "amount";
    /**
     * {@value #REASON_DEBT}
     */
    public static final String REASON_DEBT = "debt";
    /**
     * {@value #REASON_OK}
     */
    public static final String REASON_OK = "ok";

    private boolean accepted;
    private String reason;

    /**
     * Initializes a decision.
     */
    public Decision() {
        this.accepted = Boolean.FALSE;
        this.reason = "";
    }

    /**
     * Initializes a decision
     *
     * @param accepted if the decision in the accept or not.
     * @param reason   the reason of the decision.
     */
    public Decision(boolean accepted, String reason) {
        this.accepted = accepted;
        this.reason = reason;
    }

    /**
     * @return if the decision in the accept or not.
     */
    public boolean getAccepted() {
        return accepted;
    }

    /**
     * @param accepted if the decision in the accept or not.
     */
    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    /**
     * @return the reason of the decision.
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason the reason of the decision.
     */
    public void setReason(String reason) {
        this.reason = reason;
    }
}
