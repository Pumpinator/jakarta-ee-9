package com.java.webapp.servlet.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {
    private List<Item> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return this.items;
    }

    public void addItem(Item item) {
        if (!items.contains(item)) this.items.add(item);
        else {
            Optional<Item> itemOptional = items.stream().filter(i -> i.equals(item)).findAny();
            if (itemOptional.isPresent()) {
                Item i = itemOptional.get();
                i.setQuantity(i.getQuantity() + 1);
            }
        }
    }

    public double getTotal() {
        return items.stream().mapToDouble(Item::getTotal).sum();
    }
}
