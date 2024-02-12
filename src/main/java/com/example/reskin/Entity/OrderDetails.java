package com.example.reskin.Entity;

public class OrderDetails {
    private int id;
    private int prodottoID;
    private int quantita;

    private int orderID;

    public OrderDetails() {
    }

    public OrderDetails(int id, int prodotto, int quantita) {
        this.id = id;
        this.prodottoID = prodotto;
        this.quantita = quantita;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProdottoID() {
        return prodottoID;
    }

    public void setProdottoID(int prodottoID) {
        this.prodottoID = prodottoID;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}
