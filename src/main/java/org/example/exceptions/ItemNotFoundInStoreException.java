package org.example.exceptions;

public class ItemNotFoundInStoreException extends ItemNotFoundException {
    public ItemNotFoundInStoreException(String message) {
        super("This item is not recognized by the store. " + message);
    }
}