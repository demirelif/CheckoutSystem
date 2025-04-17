package org.example;

public class SpecialOffer {
    private final int amount;
    private final double specialPrice;

    public SpecialOffer(int amount, double specialPrice) {
        this.amount = amount;
        this.specialPrice = specialPrice;
    }

    public int getAmount() {
        return amount;
    }

    public double getSpecialPrice() {
        return specialPrice;
    }

    @Override
    public String toString() {
        return "Get " + amount + " for " + specialPrice;
    }

}
