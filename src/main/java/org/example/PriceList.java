package org.example;

import org.example.exceptions.ItemNotFoundInStoreException;
import org.example.exceptions.SpecialOfferAlreadyDefinedException;

import java.util.HashSet;
import java.util.logging.Logger;

/**
 * Defines the elements that are recognized by the market and their pricing rules
 */
public class PriceList {
    private final HashSet<Item> items = new HashSet<>();
    private final Logger logger = Logger.getAnonymousLogger();

    public void addItem(Item item) {
        items.add(item);
    }

    public boolean isItemValid(String itemName) {
        for ( Item item : items ) {
            if ( item.getItemName().equalsIgnoreCase( itemName ) ){
                return true;
            }
        }
        return false;
    }

    public Item getItemByName( String itemName ) {
        for ( Item item : items ) {
            if ( item.getItemName().equalsIgnoreCase( itemName ) ){
                return item;
            }
        }
        return null;
    }

    public void addSpecialOfferToItem( String itemName, SpecialOffer specialOffer )  {
        if ( specialOffer.getAmount() > 0 ){
            try {
                addSpecialOffer( itemName, specialOffer );
            } catch ( ItemNotFoundInStoreException e ){
                logger.warning(e.getLocalizedMessage());
            }
        } else {
            logger.warning("Invalid amount for special offer. It should be bigger than 0.");
        }

    }

    public void removeSpecialOfferFromItem( String itemName, SpecialOffer specialOffer ) {
        for ( Item item : items ) {
            if ( item.getItemName().equalsIgnoreCase( itemName ) ){
                item.removeSpecialOffer( specialOffer );
            }
        }
    }

    private void addSpecialOffer( String itemName, SpecialOffer specialOffer ) throws ItemNotFoundInStoreException{
        for ( Item item : items ) {
            if ( item.getItemName().equalsIgnoreCase( itemName ) ){
                try {
                    item.addSpecialOffer( specialOffer );
                    return;
                } catch ( SpecialOfferAlreadyDefinedException e ) {
                    logger.warning(e.getMessage());
                }
            }
        }
        throw new ItemNotFoundInStoreException( "Item: " + itemName );
    }
}
