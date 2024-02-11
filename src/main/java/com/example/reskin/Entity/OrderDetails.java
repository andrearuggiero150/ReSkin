package com.example.reskin.Entity;

public class OrderDetails {
    private int id;
    private Product prodotto;
    private int quantita;

    public OrderDetails() {
    }

    public OrderDetails(int id, Product prodotto, int quantita) {
        this.id = id;
        this.prodotto = prodotto;
        this.quantita = quantita;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProdotto() {
        return prodotto;
    }

    public void setProdotto(Product prodotto) {
        this.prodotto = prodotto;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}
