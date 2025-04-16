package org.example;

import org.example.exceptions.ItemNotFoundException;
import org.example.exceptions.ItemNotFoundInStoreException;

import java.util.Map;
import java.util.logging.Logger;

/**
 * The logic behind the checkout process handling the checkout operation
 *
 */
public class Checkout {
    private final Cart cart = new Cart();
    private final PriceList priceList;
    private final Logger logger = Logger.getAnonymousLogger();


    public Checkout(PriceList priceList){
        this.priceList = priceList;
    }

    public double total(){
        double total = 0;
        for (Map.Entry<Item, Integer> cartItem : cart.getItems().entrySet() ){
            int itemAmountInCart = cartItem.getValue();
            for ( SpecialOffer specialOffer : cartItem.getKey().getSpecialOffers() ){
                    double discountedTotal = calculateDiscountedTotal( specialOffer, itemAmountInCart );
                    total += discountedTotal;
                itemAmountInCart = itemAmountInCart % specialOffer.getAmount();
            }
            total += itemAmountInCart * cartItem.getKey().getUnitPrice() ;
        }
        return total;
    }

    public void scan( String item ) {
       try {
           addElement(item);
       } catch ( ItemNotFoundException e ) {
           logger.warning(e.getMessage());
       }
    }

    public void cancelScan( String item ){
        try {
            removeElement( item );
        } catch (ItemNotFoundException e) {
            logger.warning(e.getMessage());
        }
    }

    public Cart getCart() {
        return cart;
    }

    private double calculateDiscountedTotal( SpecialOffer specialOffer, int itemAmountInCart ){
        if ( itemAmountInCart > specialOffer.getAmount() ){
            return specialOffer.getSpecialPrice() + calculateDiscountedTotal( specialOffer, itemAmountInCart - specialOffer.getAmount() );
        }
        else if ( itemAmountInCart == specialOffer.getAmount() ){
            return specialOffer.getSpecialPrice();
        }
        else {
            return 0;
        }
    }

    private void addElement( String item ) throws ItemNotFoundException {
        if ( priceList.isItemValid( item ) ){
            cart.put( priceList.getItemByName( item ) );
        }
        else {
            throw new ItemNotFoundException("The item " + item + " is not recognized");
        }
    }

    private void removeElement( String item ) throws ItemNotFoundException {
        if ( priceList.isItemValid( item ) ){
            cart.removeItem( priceList.getItemByName( item ) );
        }
        else {
            throw new ItemNotFoundInStoreException("The item " + item + " is not recognized");
        }
    }
}
