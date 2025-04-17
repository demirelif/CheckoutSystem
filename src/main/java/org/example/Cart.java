package org.example;

import org.example.exceptions.ItemNotFoundInCartException;

import java.util.Map;
import java.util.HashMap;

/**
 * The cart that holds the scanned item
 * */
public class Cart {
    private final Map<Item, Integer> items;

    public Cart() {
        this.items = new HashMap<>();
    }

    public void put( Item item ){
        items.put(item, items.getOrDefault(item, 0 ) + 1);
    }

    public int getCount( Item item ){
        return items.getOrDefault(item, 0);
    }

    public Map<Item, Integer> getItems() {
        return items;
    }

    public void removeItem( Item item ) throws ItemNotFoundInCartException {
        if ( !items.containsKey(item) ){
            throw new ItemNotFoundInCartException(" Item: " + item.getItemName());
        }
       int amount = items.get(item);
       if ( amount == 1 ) {
           items.remove(item);
       }
       else {
           items.put(item, amount - 1);
       }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Cart:\n");
        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}
