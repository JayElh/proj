package com.jayelh.jl;

/**
 * Created by johanlekberg on 14/03/15.
 */
public class Result {

    private boolean accepted;
    private String reason;

    public Result() {
        //TODO: what about this, initial state? shouldnt happen
        this.accepted = Boolean.FALSE;
        this.reason = "False";
    }

    public Result(boolean accepted, String reason) {
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

    @Override
    public String toString() {
        return "Result{" +
                "accepted=" + accepted +
                ", reason='" + reason + '\'' +
                '}';
    }
}
