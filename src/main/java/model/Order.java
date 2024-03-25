package model;

import java.io.Serializable;

public class Order implements Serializable {

    public Member member;
    public String address;
    public String date;
    public String status;
    public double extraFees;
    public Offer[] offers;
    public String payment;


    public Order() {
    }
}
