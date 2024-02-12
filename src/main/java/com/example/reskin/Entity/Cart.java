package com.example.reskin.Entity;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int id;
    private List<CartObject> carrello;
    private int customerId;

    public Cart() {
        carrello = new ArrayList<>();
    }

    public Cart(int id, int customerId) {
        this.id = id;
        carrello = new ArrayList<>();
        this.customerId = customerId;
    }

    public Cart(int id, List<CartObject> carrello, int customerId) {
        this.id = id;
        this.carrello = carrello;
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<CartObject> getCarrello() {
        return carrello;
    }

    public void setCarrello(List<CartObject> carrello) {
        this.carrello = carrello;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void addCarrello(CartObject o) {
        carrello.add(o);
    }

    public void removeCarrello(CartObject o) {
        carrello.remove(o);
    }

    public CartObject getProdottoCarrello(int i) {
        return carrello.get(i);
    }

    public boolean isCarrelloEmpty() {
        return carrello.isEmpty();
    }

    public int sizeCarrello() {
        return carrello.size();
    }
}
