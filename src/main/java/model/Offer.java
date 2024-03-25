package model;

import java.io.Serializable;

public class Offer implements Serializable {
    public FoodItem[] foodItems;
    public double price;

    public Offer() {
    }
}
