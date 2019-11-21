package com.epam.siwebshop.model;

import java.util.List;

public class Order {

    private Long id;
    private Float totalPrice;
    private List<Item> items;

    public Order() {
    }

    public Order(Long id, Float totalPrice, List<Item> items) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
