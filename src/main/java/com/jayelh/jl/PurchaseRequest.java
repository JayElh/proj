package com.jayelh.jl;

/**
 * Bean holding the data for a purchase that a decision should be calculated for.
 */
public class PurchaseRequest {
    private String email;
    private String first_name;
    private String last_name;
    private int amount;

    /**
     * Default constructor for PurchaseRequest
     */
    public PurchaseRequest() {
    }

    /**
     * Initializes a Purchase request
     * @param email the email address of the user
     * @param first_name of the user
     * @param last_name of the user
     * @param amount of the purchase
     */
    public PurchaseRequest(String email, String first_name, String last_name, int amount) {
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.amount = amount;
    }

    /**
     *
     * @return the email address of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Stores the email address of the user
     * @param email the email address of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return the first name of the user
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * Stores the first name of the user
     * @param first_name
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     *
     * @return the last name of the user
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * Stores the last name of the user
     * @param last_name
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     *
     * @return the amount of the purchase
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Stores the amount of the purchase
     * @param amount of the purchase
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new StringBuffer(" email : ").append(this.email)
                .append(" first_name : ").append(this.first_name)
                .append(" last_name : ").append(this.last_name).append(" ammount : ")
                .append(this.amount).toString();
    }
}
