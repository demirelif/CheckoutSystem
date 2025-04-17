package org.example;

import org.example.exceptions.SpecialOfferAlreadyDefinedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines the item that can be found in the store
 */
public class Item {
    private final String itemName;
    private final double unitPrice;
    private final List<SpecialOffer> specialOffers;

    public Item(String itemName, double unitPrice ) {
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.specialOffers = new ArrayList<>();
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public List<SpecialOffer> getSpecialOffers() {
        return sortSpecialOffers();
    }

    public void addSpecialOffer(SpecialOffer specialOffer) throws SpecialOfferAlreadyDefinedException {
        for (SpecialOffer offer: specialOffers) {
            if ( offer.getAmount() == specialOffer.getAmount() ) {
                throw new SpecialOfferAlreadyDefinedException("A special offer with the same amount already exists. Try removing it before adding a new one.");
            }
        }
        this.specialOffers.add( specialOffer );
    }

    public void removeSpecialOffer(SpecialOffer specialOffer) {
        this.specialOffers.remove( specialOffer );
    }

    public List<SpecialOffer> sortSpecialOffers() {
        List<SpecialOffer> sorted = new ArrayList<>( specialOffers );
        sorted.sort((o1, o2) -> Integer.compare(o2.getAmount(), o1.getAmount()));
        return sorted;
    }

    @Override
    public String toString() {
        return "Item: " + itemName + " Unit Price: " + unitPrice + "\n";
    }

}
