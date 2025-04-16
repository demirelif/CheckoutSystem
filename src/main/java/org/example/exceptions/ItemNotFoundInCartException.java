package org.example.exceptions;

public class ItemNotFoundInCartException extends ItemNotFoundException {
    public ItemNotFoundInCartException(String message) {
        super("You don't have this item in your cart. " + message);
    }
}