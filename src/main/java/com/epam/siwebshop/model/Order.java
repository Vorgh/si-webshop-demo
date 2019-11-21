package com.epam.siwebshop.model;

import java.util.List;

public class Order {

    private Long id;
    private List<Item> items;

    public Order() {
    }

    public Order(Long id, List<Item> items) {
        this.id = id;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
