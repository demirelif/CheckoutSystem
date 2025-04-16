package org.example;

public class SpecialOffer {
    private final int amount;
    private final double specialPrice;

    // Constructor
    public SpecialOffer(int amount, double specialPrice) {
        this.amount = amount;
        this.specialPrice = specialPrice;
    }

    // Copy constructor
    public SpecialOffer( SpecialOffer originalSpecialOffer) {
        this.amount = originalSpecialOffer.getAmount();
        this.specialPrice = originalSpecialOffer.getSpecialPrice();
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
