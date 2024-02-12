package com.example.reskin.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private int id;
    private List<OrderDetails> listaProdotti;
    private int customerId;
    private Date data;
    private String via;
    private String citta;
    private String stato;
    private String provincia;
    private String CAP;
    private String status;
    private Double totale;

    public Order() {
    }

    public Order(int id, int customerId, Date data, String via, String citta, String stato, String provincia, String CAP, String status) {
        this.id = id;
        listaProdotti = new ArrayList<>();
        this.customerId = customerId;
        this.data = data;
        this.via = via;
        this.citta = citta;
        this.stato = stato;
        this.provincia = provincia;
        this.CAP = CAP;
        this.status = status;
    }

    public Double getTotale() {
        return totale;
    }

    public void setTotale(Double totale) {
        this.totale = totale;
    }

    public Order(int id, List<OrderDetails> listaProdotti, int customerId, Date data, String via, String citta, String stato, String provincia, String CAP, String status) {
        this.id = id;
        this.listaProdotti = listaProdotti;
        this.customerId = customerId;
        this.data = data;
        this.via = via;
        this.citta = citta;
        this.stato = stato;
        this.provincia = provincia;
        this.CAP = CAP;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<OrderDetails> getListaProdotti() {
        return listaProdotti;
    }

    public void setListaProdotti(List<OrderDetails> listaProdotti) {
        this.listaProdotti = listaProdotti;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCAP() {
        return CAP;
    }

    public void setCAP(String CAP) {
        this.CAP = CAP;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addListaProdotti(OrderDetails o) {
        listaProdotti.add(o);
    }

    public void removeListaProdotti(OrderDetails o) {
        listaProdotti.remove(o);
    }

    public OrderDetails getProdottoOrdine(int i) {
        return listaProdotti.get(i);
    }

    public boolean isListaEmpty() {
        return listaProdotti.isEmpty();
    }

    public int sizeListaProdotti() {
        return listaProdotti.size();
    }
}
