package com.jayelh.jl;

/**
 * Created by johanlekberg on 14/03/15.
 */
public class Data {
    private String email;
    private String first_name;
    private String last_name;
    private int amount;

    public Data() {
    }

    public Data(String email, String first_name, String last_name, int amount) {
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return new StringBuffer(" email : ").append(this.email)
                .append(" first_name : ").append(this.first_name)
                .append(" last_name : ").append(this.last_name).append(" ammount : ")
                .append(this.amount).toString();
    }

}
