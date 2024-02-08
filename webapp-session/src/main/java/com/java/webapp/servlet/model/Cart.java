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

    public void removeProducts(List<String> ids) {
        if(ids != null) {
            ids.forEach(this::removeProduct);
        }
    }

    public void removeProduct(String id) {
        Optional<Item> item = findItem(id);
        item.ifPresent(i -> items.remove(i));
    }

    private Optional<Item> findItem(String id) {
        return items
                .stream()
                .filter(item ->
                        id.equals(Long.toString(item.getProduct().getId()))
                )
                .findAny();
    }

    public void updateQuantity(String id, int quantity) {
        Optional<Item> item = findItem(id);
        item.ifPresent(i -> i.setQuantity(quantity));
    }

    public double getTotal() {
        return items.stream().mapToDouble(Item::getTotal).sum();
    }
}
